package com.inflectra.spirateam.mylyn.core.internal;

import java.io.File;
import java.net.MalformedURLException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.mylyn.commons.core.StatusHandler;
import org.eclipse.mylyn.commons.net.Policy;
import org.eclipse.mylyn.tasks.core.AbstractRepositoryConnector;
import org.eclipse.mylyn.tasks.core.IRepositoryQuery;
import org.eclipse.mylyn.tasks.core.ITask;
import org.eclipse.mylyn.tasks.core.RepositoryStatus;
import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.mylyn.tasks.core.TaskRepositoryLocationFactory;
import org.eclipse.mylyn.tasks.core.data.TaskData;
import org.eclipse.mylyn.tasks.core.data.TaskDataCollector;
import org.eclipse.mylyn.tasks.core.sync.ISynchronizationSession;

import com.inflectra.spirateam.mylyn.core.internal.model.PredefinedFilter;
import com.inflectra.spirateam.mylyn.core.internal.services.SpiraException;
import com.inflectra.spirateam.mylyn.core.internal.services.SpiraImportExport;

public class SpiraTeamRepositoryConnector extends AbstractRepositoryConnector
{
	private static final boolean TRACE_ENABLED = Boolean.valueOf(Platform.getDebugOption("com.inflectra.spirateam.mylyn.core/debug/connector")); //$NON-NLS-1$

	private SpiraTeamClientManager clientManager;
	private File repositoryConfigurationCacheFile;
	//private final TracTaskDataHandler taskDataHandler = new TracTaskDataHandler(this);
	private TaskRepositoryLocationFactory taskRepositoryLocationFactory = new TaskRepositoryLocationFactory();
	
	/**
	 * Constructor
	 */
	public SpiraTeamRepositoryConnector()
	{
	}

	@Override
	public boolean canCreateNewTask(TaskRepository repository)
	{
		//The SpiraTeam connector doesn't currently allow the creation of new tasks
		return false;
	}

	@Override
	public boolean canCreateTaskFromKey(TaskRepository repository)
	{
		// We can create a task from its Spira ID 
		return true;
	}

	@Override
	public String getConnectorKind()
	{
		// Return the connector kind string
		return SpiraTeamCorePlugin.CONNECTOR_KIND;
	}

	@Override
	public String getLabel()
	{
		// Return the label that describes a Spira repository
		return SpiraTeamCorePlugin.LABEL;
	}

	@Override
	public String getRepositoryUrlFromTaskUrl(String taskFullUrl)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TaskData getTaskData(TaskRepository taskRepository, String taskId,
			IProgressMonitor monitor) throws CoreException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTaskIdFromTaskUrl(String taskFullUrl)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTaskUrl(String repositoryUrl, String taskId)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasTaskChanged(TaskRepository taskRepository, ITask task,
			TaskData taskData)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Gets the list of artifacts that match the query
	 */
	@Override
	public IStatus performQuery(TaskRepository repository,
			IRepositoryQuery repositoryQuery, TaskDataCollector collector,
			ISynchronizationSession session, IProgressMonitor monitor)
	{
		monitor = Policy.monitorFor(monitor);
		try
		{
			monitor.beginTask(Messages.SpiraTeamRepositoryConnector_Query_Repository, IProgressMonitor.UNKNOWN);
			try
			{
				SpiraImportExport client = getClientManager().getSpiraTeamClient(repository);
				PredefinedFilter filter;
				/*if (!client.getCache().hasDetails())
				{
					client.getCache().refreshDetails(monitor);
				}
				filter = SpiraTeamUtil.getQuery(repository, client, repositoryQuery, true, monitor);
				if (filter == null)
				{
					return RepositoryStatus.createStatus(repository, IStatus.ERROR, SpiraTeamCorePlugin.PLUGIN_ID,
							Messages.SpiraTeamRepositoryConnector_The_SpiraTeam_query_is_invalid);
				}*/
				//client.search(filter, collector, monitor);
				//resultCollector.accept(taskData);
			}
			catch (MalformedURLException e)
			{
				return SpiraTeamCorePlugin.toStatus(repository, e);
			}
			catch (SpiraException e)
			{
				return SpiraTeamCorePlugin.toStatus(repository, e);
			}
			return Status.OK_STATUS;
		}
		finally
		{
			monitor.done();
		}
	}

	@Override
	public void updateRepositoryConfiguration(TaskRepository taskRepository,
			IProgressMonitor monitor) throws CoreException
	{
		// TODO Auto-generated method stub

	}
	
	private void trace(IStatus status)
	{
		if (TRACE_ENABLED)
		{
			StatusHandler.log(status);
		}
	}

	@Override
	public void updateTaskFromTaskData(TaskRepository taskRepository,
			ITask task, TaskData taskData)
	{
		// TODO Auto-generated method stub

	}
	
	public synchronized SpiraTeamClientManager getClientManager()
	{
		if (clientManager == null)
		{
			clientManager = new SpiraTeamClientManager(repositoryConfigurationCacheFile, taskRepositoryLocationFactory);
		}
		return clientManager;
	}
}
