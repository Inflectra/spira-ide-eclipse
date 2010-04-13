package com.inflectra.spirateam.mylyn.core.internal;

import java.io.File;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.eclipse.mylyn.tasks.core.ITask.PriorityLevel;
import org.eclipse.mylyn.tasks.core.data.TaskData;
import org.eclipse.mylyn.tasks.core.data.TaskDataCollector;
import org.eclipse.mylyn.tasks.core.data.TaskMapper;
import org.eclipse.mylyn.tasks.core.sync.ISynchronizationSession;

import com.inflectra.spirateam.mylyn.core.internal.model.ArtifactFieldValue;
import com.inflectra.spirateam.mylyn.core.internal.model.Incident;
import com.inflectra.spirateam.mylyn.core.internal.model.PredefinedFilter;
import com.inflectra.spirateam.mylyn.core.internal.model.Requirement;
import com.inflectra.spirateam.mylyn.core.internal.model.Task;
import com.inflectra.spirateam.mylyn.core.internal.services.SpiraAuthenticationException;
import com.inflectra.spirateam.mylyn.core.internal.services.SpiraException;
import com.inflectra.spirateam.mylyn.core.internal.services.SpiraImportExport;

public class SpiraTeamRepositoryConnector extends AbstractRepositoryConnector
{
	private static final boolean TRACE_ENABLED = Boolean.valueOf(Platform.getDebugOption("com.inflectra.spirateam.mylyn.core/debug/connector")); //$NON-NLS-1$

	private SpiraTeamClientManager clientManager;
	private File repositoryConfigurationCacheFile;
	private final SpiraTeamTaskDataHandler taskDataHandler = new SpiraTeamTaskDataHandler(this);
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
		// We can create a task from its artifact key
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

	//Returns the equivalent Mylyn priority for a Spira task priority
	public static PriorityLevel getMylynPriorityForTask(String priority)
	{
		if (priority == null)
		{
			return null;
		}
		if (priority.equals("1"))
		{
			return PriorityLevel.P1;
		}
		if (priority.equals("2"))
		{
			return PriorityLevel.P2;
		}
		if (priority.equals("3"))
		{
			return PriorityLevel.P3;
		}
		if (priority.equals("4"))
		{
			return PriorityLevel.P4;
		}
		return null;
	}
	
	//Returns the equivalent Mylyn priority for a Spira requirement importance
	public static PriorityLevel getMylynPriorityForRequirement(String importance)
	{
		if (importance == null)
		{
			return null;
		}
		if (importance.equals("1"))
		{
			return PriorityLevel.P1;
		}
		if (importance.equals("2"))
		{
			return PriorityLevel.P2;
		}
		if (importance.equals("3"))
		{
			return PriorityLevel.P3;
		}
		if (importance.equals("4"))
		{
			return PriorityLevel.P4;
		}
		return null;
	}
	
	@Override
	public String getRepositoryUrlFromTaskUrl(String url)
	{
		if (url == null)
		{
			return null;
		}
		int index = url.lastIndexOf(SpiraImportExport.REQUIREMENT_URL);
		return index == -1 ? null : url.substring(0, index);
	}

	@Override
	public String getTaskIdFromTaskUrl(String url)
	{
		if (url == null)
		{
			return null;
		}
		int index = url.lastIndexOf(SpiraImportExport.REQUIREMENT_URL);
		return index == -1 ? null : url.substring(index + SpiraImportExport.REQUIREMENT_URL.length());
	}
	
	@Override
	public String getTaskUrl(String repositoryUrl, String taskId)
	{
		return repositoryUrl + SpiraImportExport.REQUIREMENT_URL + taskId;
	}

	@Override
	public TaskData getTaskData(TaskRepository taskRepository, String taskKey,
			IProgressMonitor monitor) throws CoreException
	{
		//TODO: Remove hard-coded project ID, need to store a mapping somewhere
		return taskDataHandler.getTaskData(taskRepository, 1, taskKey, monitor);
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
				Map<String, ITask> taskById = null;
				SpiraImportExport client = getClientManager().getSpiraTeamClient(repository);
				PredefinedFilter filter = SpiraTeamUtil.getPredefinedFilter(repositoryQuery);
				if (filter == null)
				{
					return RepositoryStatus.createStatus(repository, IStatus.ERROR, SpiraTeamCorePlugin.PLUGIN_ID,
							Messages.SpiraTeamRepositoryConnector_The_SpiraTeam_query_is_invalid);
				}
				
				//See which types of artifact we have and get appropriate data
				if (filter.getId() == SpiraTeamCorePlugin.MY_ASSIGNED_REQUIREMENTS)
				{
					List<Requirement> requirements = client.requirementRetrieveAssigned(monitor);

					for (Requirement requirement : requirements)
					{
						TaskData taskData = taskDataHandler.createTaskDataFromRequirement(client, repository, requirement, monitor);
						taskData.setPartial(true);
						if (session != null && hasRichEditor(repository))
						{
							if (taskById == null)
							{
								taskById = new HashMap<String, ITask>();
								for (ITask task : session.getTasks())
								{
									taskById.put(task.getTaskId(), task);
								}
							}
							// preSyncronization() only handles full synchronizations
							ITask task = taskById.get(requirement.getArtifactKey()); //$NON-NLS-1$
							if (task != null && hasTaskChanged(repository, task, taskData))
							{
								session.markStale(task);
							}
						}
						collector.accept(taskData);
					}
				}
				if (filter.getId() == SpiraTeamCorePlugin.MY_ASSIGNED_INCIDENTS)
				{
					List<Incident> incidents = client.incidentRetrieveAssigned(monitor);

					for (Incident incident : incidents)
					{
						TaskData taskData = taskDataHandler.createTaskDataFromIncident(client, repository, incident, monitor);
						taskData.setPartial(true);
						if (session != null && hasRichEditor(repository))
						{
							if (taskById == null)
							{
								taskById = new HashMap<String, ITask>();
								for (ITask task : session.getTasks())
								{
									taskById.put(task.getTaskId(), task);
								}
							}
							// preSyncronization() only handles full synchronizations
							ITask task = taskById.get(incident.getArtifactKey()); //$NON-NLS-1$
							if (task != null && hasTaskChanged(repository, task, taskData))
							{
								session.markStale(task);
							}
						}
						collector.accept(taskData);
					}
				}
				if (filter.getId() == SpiraTeamCorePlugin.MY_ASSIGNED_TASKS)
				{
					List<Task> tasks = client.taskRetrieveAssigned(monitor);

					for (Task spiraTask : tasks)
					{
						TaskData taskData = taskDataHandler.createTaskDataFromTask(client, repository, spiraTask, monitor);
						taskData.setPartial(true);
						if (session != null && hasRichEditor(repository))
						{
							if (taskById == null)
							{
								taskById = new HashMap<String, ITask>();
								for (ITask task : session.getTasks())
								{
									taskById.put(task.getTaskId(), task);
								}
							}
							// preSyncronization() only handles full synchronizations
							ITask task = taskById.get(spiraTask.getArtifactKey()); //$NON-NLS-1$
							if (task != null && hasTaskChanged(repository, task, taskData))
							{
								session.markStale(task);
							}
						}
						collector.accept(taskData);
					}
				}
			}
			catch (MalformedURLException e)
			{
				return SpiraTeamCorePlugin.toStatus(repository, e);
			}
			catch (SpiraException e)
			{
				return SpiraTeamCorePlugin.toStatus(repository, e);
			}
			catch (CoreException e)
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
	public SpiraTeamTaskDataHandler getTaskDataHandler()
	{
		return taskDataHandler;
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
		TaskMapper mapper = getTaskMapping(taskData);
		mapper.applyTo(task);
		String status = mapper.getStatus();
		/*
		if (status != null)
		{
			if (isCompleted(mapper.getStatus()))
			{
				Date modificationDate = mapper.getModificationDate();
				if (modificationDate == null)
				{
					// web mode does not set a date
					modificationDate = DEFAULT_COMPLETION_DATE;
				}
				task.setCompletionDate(modificationDate);
			}
			else
			{
				task.setCompletionDate(null);
			}
		}
		task.setUrl(taskRepository.getRepositoryUrl() + ITracClient.TICKET_URL + taskData.getTaskId());
		if (!taskData.isPartial())
		{
			task.setAttribute(TASK_KEY_SUPPORTS_SUBTASKS, Boolean.toString(taskDataHandler.supportsSubtasks(taskData)));
			Date date = task.getModificationDate();
			task.setAttribute(TASK_KEY_UPDATE_DATE, (date != null) ? TracUtil.toTracTime(date) + "" : null); //$NON-NLS-1$
		}*/
	}
	
	@Override
	public SpiraTeamTaskMapper getTaskMapping(TaskData taskData)
	{
		try
		{
			TaskRepository taskRepository = taskData.getAttributeMapper().getTaskRepository();
			SpiraImportExport client = (taskRepository != null) ? getClientManager().getSpiraTeamClient(taskRepository) : null;
			return new SpiraTeamTaskMapper(taskData, client);
		}
		catch (MalformedURLException e)
		{
			return null;
		}
		catch (SpiraException e)
		{
			return null;
		}
	}

	@Override
	public boolean canSynchronizeTask(TaskRepository taskRepository, ITask task)
	{
		return hasRichEditor(taskRepository, task);
	}
	
	public static boolean hasRichEditor(TaskRepository repository)
	{
		//All artifacts have a rich-editor
		return true;
	}

	public static boolean hasRichEditor(TaskRepository repository, ITask task)
	{
		return hasRichEditor(repository);
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
