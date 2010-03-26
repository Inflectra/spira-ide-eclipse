package com.inflectra.spirateam.mylyn.core.internal;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.mylyn.tasks.core.AbstractRepositoryConnector;
import org.eclipse.mylyn.tasks.core.IRepositoryQuery;
import org.eclipse.mylyn.tasks.core.ITask;
import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.mylyn.tasks.core.data.TaskData;
import org.eclipse.mylyn.tasks.core.data.TaskDataCollector;
import org.eclipse.mylyn.tasks.core.sync.ISynchronizationSession;


public class SpiraTeamRepositoryConnector extends AbstractRepositoryConnector
{

	public SpiraTeamRepositoryConnector()
	{
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean canCreateNewTask(TaskRepository repository)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canCreateTaskFromKey(TaskRepository repository)
	{
		// TODO Auto-generated method stub
		return false;
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

	@Override
	public IStatus performQuery(TaskRepository repository,
			IRepositoryQuery query, TaskDataCollector collector,
			ISynchronizationSession session, IProgressMonitor monitor)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateRepositoryConfiguration(TaskRepository taskRepository,
			IProgressMonitor monitor) throws CoreException
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void updateTaskFromTaskData(TaskRepository taskRepository,
			ITask task, TaskData taskData)
	{
		// TODO Auto-generated method stub

	}

}
