package com.inflectra.spirateam.mylyn.core.internal;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.util.Date;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.mylyn.tasks.core.ITask;
import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.mylyn.tasks.core.data.AbstractTaskAttachmentHandler;
import org.eclipse.mylyn.tasks.core.data.AbstractTaskAttachmentSource;
import org.eclipse.mylyn.tasks.core.data.TaskAttachmentMapper;
import org.eclipse.mylyn.tasks.core.data.TaskAttribute;

import com.inflectra.spirateam.mylyn.core.internal.model.ArtifactAttachment;
import com.inflectra.spirateam.mylyn.core.internal.services.SpiraException;
import com.inflectra.spirateam.mylyn.core.internal.services.SpiraImportExport;

public class SpiraTeamTaskAttachmentHandler extends
		AbstractTaskAttachmentHandler
{
	private final SpiraTeamRepositoryConnector connector;

	public SpiraTeamTaskAttachmentHandler(SpiraTeamRepositoryConnector connector)
	{
		this.connector = connector;
	}
	
	@Override
	public boolean canGetContent(TaskRepository repository, ITask task)
	{
		return true;
	}

	@Override
	public boolean canPostContent(TaskRepository repository, ITask task)
	{
		return true;
	}


	/**
	 * Downloads an attachment from the repository
	 * @param repository
	 * @param task
	 * @param attachmentId
	 * @param out
	 * @param monitor
	 * @throws CoreException
	 */
	private byte[] downloadAttachment(TaskRepository repository, ITask task, String attachmentId, IProgressMonitor monitor)
		throws CoreException
	{
		try
		{
			//Get an instance of the SpiraTeam client
			SpiraImportExport client = connector.getClientManager().getSpiraTeamClient(repository);

			//Get the project id for this artifact
			String taskKey = task.getTaskId();
			SpiraTeamClientData data = client.getData();
			if (data != null)
			{
				if (data.taskToProjectMapping != null)
				{
					if (data.taskToProjectMapping.containsKey(taskKey))
					{
						int projectId = data.taskToProjectMapping.get(taskKey);
						ArtifactAttachment artifactAttachment = client.attachmentRetrieveByKey(projectId, attachmentId);
						if (artifactAttachment == null)
						{
							throw new CoreException(new Status(IStatus.ERROR, SpiraTeamCorePlugin.PLUGIN_ID, "Attachment with id \"" //$NON-NLS-1$
									+ attachmentId + "\" not found")); //$NON-NLS-1$ //$NON-NLS-2$
						}
						
						//Make sure we don't have a URL attachment
						if (!artifactAttachment.isUrlAttachment())
						{
							byte[] attachmentData = client.downloadAttachment(projectId, attachmentId);
							
							//return the bytes
							return attachmentData;
						}
					}
				}
			}
			return null;
		}
		catch (SpiraException ex)
		{
			throw new CoreException(SpiraTeamCorePlugin.toStatus(repository, ex));
		} 
		catch (MalformedURLException ex)
		{
			throw new CoreException(SpiraTeamCorePlugin.toStatus(repository, ex));
		}
	}
	
	@Override
	public InputStream getContent(TaskRepository repository, ITask task,
			TaskAttribute attachmentAttribute, IProgressMonitor monitor)
			throws CoreException
	{
		TaskAttachmentMapper attachment = TaskAttachmentMapper.createFrom(attachmentAttribute);
		byte[] attachmentData = downloadAttachment(repository, task, attachment.getAttachmentId(), monitor);
		return new ByteArrayInputStream(attachmentData);
	}

	@Override
	public void postContent(TaskRepository repository, ITask task,
			AbstractTaskAttachmentSource source, String comment,
			TaskAttribute attachmentAttribute, IProgressMonitor monitor)
			throws CoreException
	{
		try
		{
			//Get an instance of the SpiraTeam client
			SpiraImportExport client = connector.getClientManager().getSpiraTeamClient(repository);

			//Get the project id for this artifact
			String taskKey = task.getTaskId();
			SpiraTeamClientData data = client.getData();
			if (data != null)
			{
				if (data.taskToProjectMapping != null)
				{
					if (data.taskToProjectMapping.containsKey(taskKey))
					{
						int projectId = data.taskToProjectMapping.get(taskKey);
						
						//Extract the input stream as a byte array
						InputStream input = source.createInputStream(monitor);
						ByteArrayOutputStream bos = new ByteArrayOutputStream();
						int next = input.read();
						while (next > -1)
						{
							bos.write(next);
							next = input.read();
						}
						bos.flush();
						byte[] attachmentData = bos.toByteArray();
						
						//Upload the new attachment
						Date now = new Date();
						ArtifactAttachment artifactAttachment = new ArtifactAttachment(source.getName(), source.getDescription(), now, source.getLength());
						client.attachmentUpload(projectId, taskKey, artifactAttachment, attachmentData);
						
						//See if we need to add a new comment
						//TODO Implement new comment feature
					}
				}
			}
		}
		catch (SpiraException ex)
		{
			throw new CoreException(SpiraTeamCorePlugin.toStatus(repository, ex));
		} 
		catch (MalformedURLException ex)
		{
			throw new CoreException(SpiraTeamCorePlugin.toStatus(repository, ex));
		}
		catch (IOException ex)
		{
			throw new CoreException(SpiraTeamCorePlugin.toStatus(repository, ex));
		}
	}

}
