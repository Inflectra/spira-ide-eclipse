package com.inflectra.spirateam.mylyn.core.internal;

import java.io.InputStream;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.mylyn.tasks.core.ITask;
import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.mylyn.tasks.core.data.AbstractTaskAttachmentHandler;
import org.eclipse.mylyn.tasks.core.data.AbstractTaskAttachmentSource;
import org.eclipse.mylyn.tasks.core.data.TaskAttribute;

public class SpiraTeamTaskAttachmentHandler extends
		AbstractTaskAttachmentHandler
{

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

	@Override
	public InputStream getContent(TaskRepository repository, ITask task,
			TaskAttribute attachmentAttribute, IProgressMonitor monitor)
			throws CoreException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void postContent(TaskRepository repository, ITask task,
			AbstractTaskAttachmentSource source, String comment,
			TaskAttribute attachmentAttribute, IProgressMonitor monitor)
			throws CoreException
	{
		// TODO Auto-generated method stub

	}

}
