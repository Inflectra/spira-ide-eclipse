package com.inflectra.spirateam.mylyn.core.internal;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.mylyn.commons.net.Policy;
import org.eclipse.mylyn.tasks.core.AbstractRepositoryConnector;
import org.eclipse.mylyn.tasks.core.IRepositoryQuery;
import org.eclipse.mylyn.tasks.core.ITask;
import org.eclipse.mylyn.tasks.core.RepositoryStatus;
import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.mylyn.tasks.core.TaskRepositoryLocationFactory;
import org.eclipse.mylyn.tasks.core.ITask.PriorityLevel;
import org.eclipse.mylyn.tasks.core.data.AbstractTaskAttachmentHandler;
import org.eclipse.mylyn.tasks.core.data.TaskData;
import org.eclipse.mylyn.tasks.core.data.TaskDataCollector;
import org.eclipse.mylyn.tasks.core.data.TaskMapper;
import org.eclipse.mylyn.tasks.core.sync.ISynchronizationSession;

import com.inflectra.spirateam.mylyn.core.internal.model.Incident;
import com.inflectra.spirateam.mylyn.core.internal.model.PredefinedFilter;
import com.inflectra.spirateam.mylyn.core.internal.model.Requirement;
import com.inflectra.spirateam.mylyn.core.internal.model.Task;
import com.inflectra.spirateam.mylyn.core.internal.services.SpiraException;
import com.inflectra.spirateam.mylyn.core.internal.services.SpiraImportExport;

public class SpiraTeamRepositoryConnector extends AbstractRepositoryConnector
{
	private SpiraTeamClientManager clientManager;
	private File repositoryConfigurationCacheFile;
	private final SpiraTeamTaskDataHandler taskDataHandler = new SpiraTeamTaskDataHandler(this);
	private final SpiraTeamTaskAttachmentHandler taskAttachmentHandler = new SpiraTeamTaskAttachmentHandler(this);
	private TaskRepositoryLocationFactory taskRepositoryLocationFactory = new TaskRepositoryLocationFactory();
	
	/**
	 * Constructor
	 */
	public SpiraTeamRepositoryConnector()
	{
		if (SpiraTeamCorePlugin.getDefault() != null)
		{
			SpiraTeamCorePlugin.getDefault().setConnector(this);
			IPath path = SpiraTeamCorePlugin.getDefault().getRepostioryAttributeCachePath();
			this.repositoryConfigurationCacheFile = path.toFile();
		}
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
		int index = url.lastIndexOf(ArtifactType.REQUIREMENT.getBaseUrl());
		if (index != -1)
		{
			return url.substring(0, index);
		}
		index = url.lastIndexOf(ArtifactType.INCIDENT.getBaseUrl());
		if (index != -1)
		{
			return url.substring(0, index);
		}
		index = url.lastIndexOf(ArtifactType.TASK.getBaseUrl());
		if (index != -1)
		{
			return url.substring(0, index);
		}
		return null;
	}

	@Override
	public String getTaskIdFromTaskUrl(String url)
	{
		if (url == null)
		{
			return null;
		}
		
		int index = url.lastIndexOf(ArtifactType.REQUIREMENT.getBaseUrl());
		if (index != -1)
		{
			return url.substring(index + ArtifactType.REQUIREMENT.getBaseUrl().length());
		}
		index = url.lastIndexOf(ArtifactType.INCIDENT.getBaseUrl());
		if (index != -1)
		{
			return url.substring(index + ArtifactType.INCIDENT.getBaseUrl().length());
		}
		index = url.lastIndexOf(ArtifactType.TASK.getBaseUrl());
		if (index != -1)
		{
			return url.substring(index + ArtifactType.TASK.getBaseUrl().length());
		}
		return null;
	}
	
	@Override
	public String getTaskUrl(String repositoryUrl, String taskKey)
	{
		ArtifactType artifactType = ArtifactType.byTaskKey(taskKey);
		if (artifactType != null)
		{
			return repositoryUrl + artifactType.getBaseUrl() + taskKey;
		}
		return null;
	}

	@Override
	public TaskData getTaskData(TaskRepository taskRepository, String taskKey,
			IProgressMonitor monitor) throws CoreException
	{
		//We need to get the project id from the cache
		try
		{
			SpiraImportExport client = clientManager.getSpiraTeamClient(taskRepository);
			if (client != null)
			{
				//Now make sure that the version is current enough
				client.connectionAuthenticate2();
				boolean current = SpiraTeamUtil.ValidateServerVersion(client);
				if (!current)
				{
					throw new CoreException(new Status(IStatus.ERROR, SpiraTeamCorePlugin.PLUGIN_ID, IStatus.OK,
							Messages.SpiraTeamRepositoryConnector_ServerVersionTooOld, null));
				}

				SpiraTeamClientData data = client.getData();
				if (data != null)
				{
					if (data.taskToProjectMapping != null)
					{
						if (data.taskToProjectMapping.containsKey(taskKey))
						{
							int projectId = data.taskToProjectMapping.get(taskKey);
							return taskDataHandler.getTaskData(taskRepository, projectId, taskKey, monitor);
						}
					}
				}
			}
			return null;
		}
		catch (SpiraException ex)
		{
			return null;
		}
		catch (MalformedURLException ex)
		{
			return null;
		}
	}

	@Override
	public boolean hasTaskChanged(TaskRepository taskRepository, ITask task,
			TaskData taskData)
	{
		TaskMapper mapper = getTaskMapping(taskData);
		if (taskData.isPartial())
		{
			return mapper.hasChanges(task);
		}
		else
		{
			Date repositoryDate = mapper.getModificationDate();
			String localDateString = task.getAttribute(ArtifactAttribute.LAST_UPDATE_DATE.getArtifactKey());
			Date localDate = SpiraTeamUtil.parseDate(localDateString);
			if (repositoryDate != null && repositoryDate.equals(localDate))
			{
				return false;
			}
			return true;
		}
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
				client.updateAttributes(monitor, false);
				PredefinedFilter filter = SpiraTeamUtil.getPredefinedFilter(repositoryQuery);
				if (filter == null)
				{
					return RepositoryStatus.createStatus(repository, IStatus.ERROR, SpiraTeamCorePlugin.PLUGIN_ID,
							Messages.SpiraTeamRepositoryConnector_The_SpiraTeam_query_is_invalid);
				}
				
				//Now make sure that the version is current enough
				client.connectionAuthenticate2();
				boolean current = SpiraTeamUtil.ValidateServerVersion(client);
				if (!current)
				{
					throw new CoreException(new Status(IStatus.ERROR, SpiraTeamCorePlugin.PLUGIN_ID, IStatus.OK,
							Messages.SpiraTeamRepositoryConnector_ServerVersionTooOld, null));
				}
				
				//See which types of artifact we have and get appropriate data
				if (filter.getId().equals(SpiraTeamCorePlugin.MY_ASSIGNED_REQUIREMENTS))
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
				if (filter.getId().equals(SpiraTeamCorePlugin.MY_ASSIGNED_INCIDENTS))
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
				if (filter.getId().equals(SpiraTeamCorePlugin.MY_ASSIGNED_TASKS))
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
		try
		{
			SpiraImportExport client = getClientManager().getSpiraTeamClient(taskRepository);
			client.updateAttributes(monitor, true);
		}
		catch (Exception e)
		{
			throw new CoreException(RepositoryStatus.createStatus(taskRepository.getRepositoryUrl(), IStatus.WARNING,
					SpiraTeamCorePlugin.PLUGIN_ID, "Could not update attributes")); //$NON-NLS-1$
		}

	}
	

	public void stop()
	{
		if (clientManager != null)
		{
			clientManager.writeCache();
		}
	}
	
	@Override
	public void updateTaskFromTaskData(TaskRepository taskRepository,
			ITask task, TaskData taskData)
	{
		TaskMapper mapper = getTaskMapping(taskData);
		mapper.applyTo(task);
		if (!taskData.isPartial())
		{
			Date date = task.getModificationDate();
			task.setAttribute(ArtifactAttribute.LAST_UPDATE_DATE.getArtifactKey(), (date != null) ? SpiraTeamUtil.dateToString(date) : null); //$NON-NLS-1$
		}
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

	/* (non-Javadoc)
	 * @see org.eclipse.mylyn.tasks.core.AbstractRepositoryConnector#getTaskAttachmentHandler()
	 */
	@Override
	public AbstractTaskAttachmentHandler getTaskAttachmentHandler()
	{
		return taskAttachmentHandler;
	}
}
