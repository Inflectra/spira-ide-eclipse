/**
 * 
 */
package com.inflectra.spirateam.mylyn.core.internal;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.mylyn.commons.net.Policy;
import org.eclipse.mylyn.tasks.core.ITaskMapping;
import org.eclipse.mylyn.tasks.core.RepositoryResponse;
import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.mylyn.tasks.core.data.AbstractTaskDataHandler;
import org.eclipse.mylyn.tasks.core.data.TaskAttribute;
import org.eclipse.mylyn.tasks.core.data.TaskAttributeMapper;
import org.eclipse.mylyn.tasks.core.data.TaskAttributeMetaData;
import org.eclipse.mylyn.tasks.core.data.TaskCommentMapper;
import org.eclipse.mylyn.tasks.core.data.TaskData;

import com.inflectra.spirateam.mylyn.core.internal.model.Artifact;
import com.inflectra.spirateam.mylyn.core.internal.model.ArtifactField;
import com.inflectra.spirateam.mylyn.core.internal.model.Incident;
import com.inflectra.spirateam.mylyn.core.internal.model.IncidentResolution;
import com.inflectra.spirateam.mylyn.core.internal.model.Requirement;
import com.inflectra.spirateam.mylyn.core.internal.model.Task;
import com.inflectra.spirateam.mylyn.core.internal.services.SpiraException;
import com.inflectra.spirateam.mylyn.core.internal.services.SpiraImportExport;

/**
 * Maps SpiraTeam artifacts to the lightweight ITask objects
 * @author Inflectra Corporation
 *
 */
public class SpiraTeamTaskDataHandler extends AbstractTaskDataHandler
{
	//The TASK_DATA_VERSION should be the version number (e.g. v2.3.1) in XXYYZZ format
	//So that we can migrate old tasks if necessary when we add new attributes
	private static final String TASK_DATA_VERSION = "020301"; //$NON-NLS-1$
	private static final String ARTIFACT_KEY = "spiraKey"; //$NON-NLS-1$
	
	private final SpiraTeamRepositoryConnector connector;
	
	public SpiraTeamTaskDataHandler(SpiraTeamRepositoryConnector connector)
	{
		this.connector = connector;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.mylyn.tasks.core.data.AbstractTaskDataHandler#getAttributeMapper(org.eclipse.mylyn.tasks.core.TaskRepository)
	 */
	@Override
	public TaskAttributeMapper getAttributeMapper(TaskRepository taskRepository)
	{
		try
		{
			SpiraImportExport client = connector.getClientManager().getSpiraTeamClient(taskRepository);
			return new SpiraTeamAttributeMapper(taskRepository, client);
		}
		catch (MalformedURLException ex)
		{
			//If we don't have a valid URL, don't return a client reference
			return null;
		}
		catch (SpiraException ex)
		{
			//If we can't connect/authenticate, don't return a reference
			return null;
		}
	}

	public TaskData getTaskData(TaskRepository repository, int projectId, String artifactKey, IProgressMonitor monitor)
			throws CoreException
	{
		monitor = Policy.monitorFor(monitor);
		try
		{
			monitor.beginTask("Task Download", IProgressMonitor.UNKNOWN); //$NON-NLS-1$
			return downloadTaskData(repository, projectId, artifactKey, monitor);
		}
		finally
		{
			monitor.done();
		}
	}

	public TaskData downloadTaskData(TaskRepository repository, int projectId, String artifactKey, IProgressMonitor monitor)
			throws CoreException
	{
		try
		{
			SpiraImportExport client = connector.getClientManager().getSpiraTeamClient(repository);
			//client.updateAttributes(monitor, false);
			//See what artifact type we have and call the appropriate method to get the data
			if (artifactKey != null && artifactKey.length() > 2)
			{
				String prefix = artifactKey.substring(0, 2);
				if (prefix.equals(ArtifactType.REQUIREMENT.getPrefix()))
				{
					Requirement requirement = client.requirementRetrieveByKey(artifactKey, projectId, monitor);
					return createTaskDataFromRequirement(client, repository, requirement, monitor);
				}
				if (prefix.equals(ArtifactType.INCIDENT.getPrefix()))
				{
					Incident incident = client.incidentRetrieveByKey(artifactKey, projectId, monitor);
					return createTaskDataFromIncident(client, repository, incident, monitor);
				}
				if (prefix.equals(ArtifactType.TASK.getPrefix()))
				{
					Task task = client.taskRetrieveByKey(artifactKey, projectId, monitor);
					return createTaskDataFromTask(client, repository, task, monitor);
				}
			}
			return null;
		}
		catch (OperationCanceledException e)
		{
			throw e;
		}
		catch (Exception e)
		{
			// TODO catch SpiraException
			throw new CoreException(SpiraTeamCorePlugin.toStatus(repository, e));
		}
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.mylyn.tasks.core.data.AbstractTaskDataHandler#initializeTaskData(org.eclipse.mylyn.tasks.core.TaskRepository, org.eclipse.mylyn.tasks.core.data.TaskData, org.eclipse.mylyn.tasks.core.ITaskMapping, org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public boolean initializeTaskData(TaskRepository repository, TaskData data,
			ITaskMapping initializationData, IProgressMonitor monitor)
			throws CoreException
	{
		try
		{
			SpiraImportExport client = connector.getClientManager().getSpiraTeamClient(repository);
			createDefaultAttributes(data, client, false);
			removeEmptySingleSelectAttributes(data);
			return true;
		}
		catch (OperationCanceledException e)
		{
			throw e;
		}
		catch (Exception e)
		{
			throw new CoreException(SpiraTeamCorePlugin.toStatus(repository, e));
		}
	}

	/**
	 * This overload is used for the custom properties, when we add support for that
	 * @param data
	 * @param field
	 * @return
	 */
	/*
	private static TaskAttribute createAttribute(TaskData data, ArtifactField field)
	{
		TaskAttribute attr = data.getRoot().createAttribute(field.getName());
		TaskAttributeMetaData metaData = attr.getMetaData();
		metaData.defaults();
		metaData.setLabel(field.getLabel() + ":"); //$NON-NLS-1$
		metaData.setKind(TaskAttribute.KIND_DEFAULT);
		metaData.setReadOnly(false);
		metaData.putValue(ARTIFACT_KEY, field.getName());
		if (field.getType() == ArtifactField.Type.CHECKBOX)
		{
			// attr.addOption("True", "1");
			// attr.addOption("False", "0");
			metaData.setType(TaskAttribute.TYPE_BOOLEAN);
			attr.putOption("1", "1"); //$NON-NLS-1$ //$NON-NLS-2$
			attr.putOption("0", "0"); //$NON-NLS-1$ //$NON-NLS-2$
			if (field.getDefaultValue() != null)
			{
				attr.setValue(field.getDefaultValue());
			}
		}
		else if (field.getType() == ArtifactField.Type.SELECT || field.getType() == ArtifactField.Type.RADIO)
		{
			metaData.setType(TaskAttribute.TYPE_SINGLE_SELECT);
			ArtifactFieldValue[] values = field.getValues();
			if (values != null && values.length > 0)
			{
				if (field.isOptional())
				{
					attr.putOption("", ""); //$NON-NLS-1$ //$NON-NLS-2$
				}
				for (ArtifactFieldValue value : values)
				{
					attr.putOption(value.getId() + "", value.getName());
				}
				if (field.getDefaultValue() != null)
				{
					try
					{
						int index = Integer.parseInt(field.getDefaultValue());
						if (index > 0 && index < values.length)
						{
							attr.setValue(values[index].getName());
						}
					}
					catch (NumberFormatException e)
					{
						for (ArtifactFieldValue value : values)
						{
							if (field.getDefaultValue().equals(value.toString()))
							{
								attr.setValue(value.getName());
								break;
							}
						}
					}
				}
			}
		}
		else if (field.getType() == ArtifactField.Type.TEXTAREA)
		{
			metaData.setType(TaskAttribute.TYPE_LONG_TEXT);
			if (field.getDefaultValue() != null)
			{
				attr.setValue(field.getDefaultValue());
			}
		}
		else
		{
			metaData.setType(TaskAttribute.TYPE_SHORT_TEXT);
			if (field.getDefaultValue() != null)
			{
				attr.setValue(field.getDefaultValue());
			}
		}
		return attr;
	}*/

	public static TaskAttribute createAttribute(TaskData data, ArtifactAttribute artifactAttribute)
	{
		return createAttribute(data, null, artifactAttribute);
	}

	/**
	 * This overload is used for standard fields
	 * @param data
	 * @param client
	 * @param artifactAttribute
	 * @return
	 */
	public static TaskAttribute createAttribute(TaskData data, SpiraImportExport client, ArtifactAttribute artifactAttribute)
	{
		TaskAttribute attr = data.getRoot().createAttribute(artifactAttribute.getArtifactKey());
		TaskAttributeMetaData metaData = attr.getMetaData();
		metaData.setType(artifactAttribute.getType());
		metaData.setKind(artifactAttribute.getKind());
		metaData.setLabel(artifactAttribute.toString());
		metaData.setReadOnly(artifactAttribute.isReadOnly());
		metaData.putValue(ARTIFACT_KEY, artifactAttribute.getArtifactKey());
		if (client != null)
		{
			ArtifactField field = client.getArtifactFieldByName(artifactAttribute.getArtifactKey());
			Map<String, String> values = SpiraTeamAttributeMapper.getRepositoryOptions(client, attr.getId());
			if (values != null && values.size() > 0)
			{
				boolean setDefault = field == null || !field.isOptional();
				for (Entry<String, String> value : values.entrySet())
				{
					attr.putOption(value.getKey(), value.getValue());
					// set first value as default, may get overwritten below
					if (setDefault)
					{
						attr.setValue(value.getKey());
					}
					setDefault = false;
				}
			}
			else if (TaskAttribute.TYPE_SINGLE_SELECT.equals(artifactAttribute.getType()))
			{
				attr.getMetaData().setReadOnly(true);
			}
			if (field != null)
			{
				String defaultValue = field.getDefaultValue();
				if (defaultValue != null && defaultValue.length() > 0)
				{
					attr.setValue(defaultValue);
				}
			}
		}
		return attr;
	}

	private void removeEmptySingleSelectAttributes(TaskData taskData)
	{
		List<TaskAttribute> attributes = new ArrayList<TaskAttribute>(taskData.getRoot().getAttributes().values());
		for (TaskAttribute attribute : attributes)
		{
			if (TaskAttribute.TYPE_SINGLE_SELECT.equals(attribute.getMetaData().getType())
					&& attribute.getValue().length() == 0 && attribute.getOptions().isEmpty())
			{
				taskData.getRoot().removeAttribute(attribute.getId());
			}
		}
	}

	
	public static void createDefaultAttributes(TaskData data, SpiraImportExport client, boolean existingTask)
	{
		data.setVersion(TASK_DATA_VERSION);

		//Common fields for all artifacts
		createAttribute(data, client, ArtifactAttribute.NAME);
		createAttribute(data, client, ArtifactAttribute.DESCRIPTION);
		if (existingTask)
		{
			createAttribute(data, client, ArtifactAttribute.CREATION_DATE);
			createAttribute(data, client, ArtifactAttribute.LAST_UPDATE_DATE);
		}
		createAttribute(data, client, ArtifactAttribute.OWNER_ID);

		//Find out what type of artifact we're dealing with
		ArtifactType artifactType = ArtifactType.byTaskKey(data.getTaskId());
		
		//Requirement-specific fields
		if (artifactType.equals(ArtifactType.REQUIREMENT))
		{
			createAttribute(data, client, ArtifactAttribute.REQUIREMENT_TYPE);
			createAttribute(data, client, ArtifactAttribute.REQUIREMENT_STATUS_ID);
			createAttribute(data, client, ArtifactAttribute.REQUIREMENT_AUTHOR_ID);
			createAttribute(data, client, ArtifactAttribute.REQUIREMENT_IMPORTANCE_ID);
			createAttribute(data, client, ArtifactAttribute.REQUIREMENT_RELEASE_ID);
			createAttribute(data, client, ArtifactAttribute.REQUIREMENT_PLANNED_EFFORT);
		}
		
		//Incident-specific fields
		if (artifactType.equals(ArtifactType.INCIDENT))
		{
			createAttribute(data, client, ArtifactAttribute.INCIDENT_OPENER_ID);
			createAttribute(data, client, ArtifactAttribute.INCIDENT_PRIORITY_ID);
			createAttribute(data, client, ArtifactAttribute.INCIDENT_SEVERITY_ID);
			createAttribute(data, client, ArtifactAttribute.INCIDENT_STATUS_ID);
			createAttribute(data, client, ArtifactAttribute.INCIDENT_TYPE_ID);
			createAttribute(data, client, ArtifactAttribute.INCIDENT_DETECTED_RELEASE_ID);
			createAttribute(data, client, ArtifactAttribute.INCIDENT_RESOLVED_RELEASE_ID);
			createAttribute(data, client, ArtifactAttribute.INCIDENT_VERIFIED_RELEASE_ID);
			createAttribute(data, client, ArtifactAttribute.INCIDENT_START_DATE);
			createAttribute(data, client, ArtifactAttribute.INCIDENT_CLOSED_DATE);
			createAttribute(data, client, ArtifactAttribute.INCIDENT_COMPLETION_PERCENTAGE);
			createAttribute(data, client, ArtifactAttribute.INCIDENT_ESTIMATED_EFFORT);
			createAttribute(data, client, ArtifactAttribute.INCIDENT_ACTUAL_EFFORT);
		}
		
		//Task-specific fields
		if (artifactType.equals(ArtifactType.TASK))
		{
			createAttribute(data, client, ArtifactAttribute.TASK_TYPE);
			createAttribute(data, client, ArtifactAttribute.TASK_STATUS_ID);
			createAttribute(data, client, ArtifactAttribute.TASK_REQUIREMENT_ID);
			createAttribute(data, client, ArtifactAttribute.TASK_RELEASE_ID);
			createAttribute(data, client, ArtifactAttribute.TASK_PRIORITY_ID);
			createAttribute(data, client, ArtifactAttribute.TASK_START_DATE);
			createAttribute(data, client, ArtifactAttribute.TASK_END_DATE);
			createAttribute(data, client, ArtifactAttribute.TASK_COMPLETION_PERCENTAGE);
			createAttribute(data, client, ArtifactAttribute.TASK_ESTIMATED_EFFORT);
			createAttribute(data, client, ArtifactAttribute.TASK_ACTUAL_EFFORT);
		}
		
		// custom fields - add when API supports it
		/*
		ArtifactField[] fields = client.getCustomProperties(ArtifactType.TASK, projectId);
		if (fields != null)
		{
			for (ArtifactField field : fields)
			{
				if (field.isCustom())
				{
					createAttribute(data, field);
				}
			}
		}*/
		// operations
		//data.getRoot().createAttribute(TaskAttribute.OPERATION).getMetaData().setType(TaskAttribute.TYPE_OPERATION);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.mylyn.tasks.core.data.AbstractTaskDataHandler#postTaskData(org.eclipse.mylyn.tasks.core.TaskRepository, org.eclipse.mylyn.tasks.core.data.TaskData, java.util.Set, org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public RepositoryResponse postTaskData(TaskRepository repository,
			TaskData taskData, Set<TaskAttribute> oldAttributes,
			IProgressMonitor monitor) throws CoreException
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	public TaskData createTaskDataFromRequirement(SpiraImportExport client, TaskRepository repository, Requirement requirement,
			IProgressMonitor monitor) throws CoreException
	{
		TaskData taskData = new TaskData(getAttributeMapper(repository), SpiraTeamCorePlugin.CONNECTOR_KIND,
				repository.getRepositoryUrl(), requirement.getArtifactKey());
		taskData.setVersion(TASK_DATA_VERSION);
		client.setStoredProjectId(requirement.getProjectId());
		try
		{
			if (!SpiraTeamRepositoryConnector.hasRichEditor(repository))
			{
				createDefaultAttributes(taskData, client, true);
				Set<TaskAttribute> changedAttributes = updateTaskData(client, repository, taskData, requirement);
				// remove attributes that were not set, i.e. were not received from the server
				List<TaskAttribute> attributes = new ArrayList<TaskAttribute>(taskData.getRoot()
						.getAttributes()
						.values());
				for (TaskAttribute attribute : attributes)
				{
					if (!changedAttributes.contains(attribute) && !SpiraTeamAttributeMapper.isInternalAttribute(attribute))
					{
						taskData.getRoot().removeAttribute(attribute.getId());
					}
				}
				taskData.setPartial(true);
			}
			else
			{
				createDefaultAttributes(taskData, client, true);
				updateTaskData(client, repository, taskData, requirement);
			}
			removeEmptySingleSelectAttributes(taskData);
			return taskData;
		}
		catch (OperationCanceledException e)
		{
			throw e;
		}
		catch (Exception e)
		{
			throw new CoreException(SpiraTeamCorePlugin.toStatus(repository, e));
		}
	}
	
	public TaskData createTaskDataFromIncident(SpiraImportExport client, TaskRepository repository, Incident incident,
			IProgressMonitor monitor) throws CoreException
	{
		TaskData taskData = new TaskData(getAttributeMapper(repository), SpiraTeamCorePlugin.CONNECTOR_KIND,
				repository.getRepositoryUrl(), incident.getArtifactKey());
		taskData.setVersion(TASK_DATA_VERSION);
		client.setStoredProjectId(incident.getProjectId());
		try
		{
			if (!SpiraTeamRepositoryConnector.hasRichEditor(repository))
			{
				createDefaultAttributes(taskData, client, true);
				Set<TaskAttribute> changedAttributes = updateTaskData(client, repository, taskData, incident);
				// remove attributes that were not set, i.e. were not received from the server
				List<TaskAttribute> attributes = new ArrayList<TaskAttribute>(taskData.getRoot()
						.getAttributes()
						.values());
				for (TaskAttribute attribute : attributes)
				{
					if (!changedAttributes.contains(attribute) && !SpiraTeamAttributeMapper.isInternalAttribute(attribute))
					{
						taskData.getRoot().removeAttribute(attribute.getId());
					}
				}
				taskData.setPartial(true);
			}
			else
			{
				createDefaultAttributes(taskData, client, true);
				updateTaskData(client, repository, taskData, incident);
			}
			removeEmptySingleSelectAttributes(taskData);
			return taskData;
		}
		catch (OperationCanceledException e)
		{
			throw e;
		}
		catch (Exception e)
		{
			throw new CoreException(SpiraTeamCorePlugin.toStatus(repository, e));
		}
	}
	
	public TaskData createTaskDataFromTask(SpiraImportExport client, TaskRepository repository, Task spiraTask,
			IProgressMonitor monitor) throws CoreException
	{
		TaskData taskData = new TaskData(getAttributeMapper(repository), SpiraTeamCorePlugin.CONNECTOR_KIND,
				repository.getRepositoryUrl(), spiraTask.getArtifactKey());
		taskData.setVersion(TASK_DATA_VERSION);
		client.setStoredProjectId(spiraTask.getProjectId());
		try
		{
			if (!SpiraTeamRepositoryConnector.hasRichEditor(repository))
			{
				createDefaultAttributes(taskData, client, true);
				Set<TaskAttribute> changedAttributes = updateTaskData(client, repository, taskData, spiraTask);
				// remove attributes that were not set, i.e. were not received from the server
				List<TaskAttribute> attributes = new ArrayList<TaskAttribute>(taskData.getRoot()
						.getAttributes()
						.values());
				for (TaskAttribute attribute : attributes)
				{
					if (!changedAttributes.contains(attribute) && !SpiraTeamAttributeMapper.isInternalAttribute(attribute))
					{
						taskData.getRoot().removeAttribute(attribute.getId());
					}
				}
				taskData.setPartial(true);
			}
			else
			{
				createDefaultAttributes(taskData, client, true);
				updateTaskData(client, repository, taskData, spiraTask);
			}
			removeEmptySingleSelectAttributes(taskData);
			return taskData;
		}
		catch (OperationCanceledException e)
		{
			throw e;
		}
		catch (Exception e)
		{
			throw new CoreException(SpiraTeamCorePlugin.toStatus(repository, e));
		}
	}
	
	private static void updateTaskAttribute (TaskData data, Set<TaskAttribute> changedAttributes, ArtifactAttribute artifactAttribute, String stringValue)
	{
		TaskAttribute taskAttribute = data.getRoot().getAttribute(artifactAttribute.getArtifactKey());
		if (stringValue == null)
		{
			taskAttribute.clearValues();
		}
		else
		{
			taskAttribute.setValue(stringValue);
		}
		changedAttributes.add(taskAttribute);
	}
	
	public static Set<TaskAttribute> updateTaskData(SpiraImportExport client, TaskRepository repository, TaskData data, Artifact artifact)
		throws SpiraException
	{
		Set<TaskAttribute> changedAttributes = new HashSet<TaskAttribute>();

		//First we set the cross-attribute properties
		updateTaskAttribute(data, changedAttributes, ArtifactAttribute.NAME, artifact.getName());
		updateTaskAttribute(data, changedAttributes, ArtifactAttribute.DESCRIPTION, artifact.getDescription());
		updateTaskAttribute(data, changedAttributes, ArtifactAttribute.OWNER_ID, artifact.getOwnerId().toString());
		updateTaskAttribute(data, changedAttributes, ArtifactAttribute.CREATION_DATE, SpiraTeamUtil.dateToString(artifact.getCreationDate()));
		updateTaskAttribute(data, changedAttributes, ArtifactAttribute.LAST_UPDATE_DATE, SpiraTeamUtil.dateToString(artifact.getLastUpdateDate()));

		//Need to detect each type of artifact, for the other attributes
		if (artifact instanceof Requirement)
		{
			Requirement requirement = (Requirement)artifact;
			updateTaskAttribute(data, changedAttributes, ArtifactAttribute.REQUIREMENT_TYPE, ArtifactType.REQUIREMENT.getDisplayName());
			updateTaskAttribute(data, changedAttributes, ArtifactAttribute.REQUIREMENT_STATUS_ID, requirement.getStatusId() + "");
			updateTaskAttribute(data, changedAttributes, ArtifactAttribute.REQUIREMENT_AUTHOR_ID, requirement.getAuthorName());
			updateTaskAttribute(data, changedAttributes, ArtifactAttribute.REQUIREMENT_IMPORTANCE_ID, requirement.getImportanceId() + "");
			updateTaskAttribute(data, changedAttributes, ArtifactAttribute.REQUIREMENT_RELEASE_ID, requirement.getReleaseId() + "");
			updateTaskAttribute(data, changedAttributes, ArtifactAttribute.REQUIREMENT_PLANNED_EFFORT, SpiraTeamUtil.effortValuesToString(requirement.getPlannedEffort()));
		}
		
		if (artifact instanceof Incident)
		{
			Incident incident = (Incident)artifact;
			updateTaskAttribute(data, changedAttributes, ArtifactAttribute.INCIDENT_OPENER_ID, incident.getOpenerName());
			updateTaskAttribute(data, changedAttributes, ArtifactAttribute.INCIDENT_PRIORITY_ID, incident.getPriorityId() + "");
			updateTaskAttribute(data, changedAttributes, ArtifactAttribute.INCIDENT_SEVERITY_ID, incident.getSeverityId() + "");
			updateTaskAttribute(data, changedAttributes, ArtifactAttribute.INCIDENT_STATUS_ID, incident.getIncidentStatusId() + "");
			updateTaskAttribute(data, changedAttributes, ArtifactAttribute.INCIDENT_TYPE_ID, incident.getIncidentTypeId() + "");
			updateTaskAttribute(data, changedAttributes, ArtifactAttribute.INCIDENT_DETECTED_RELEASE_ID, incident.getDetectedReleaseId() + "");
			updateTaskAttribute(data, changedAttributes, ArtifactAttribute.INCIDENT_RESOLVED_RELEASE_ID, incident.getResolvedReleaseId() + "");
			updateTaskAttribute(data, changedAttributes, ArtifactAttribute.INCIDENT_VERIFIED_RELEASE_ID, incident.getVerifiedReleaseId() + "");
			updateTaskAttribute(data, changedAttributes, ArtifactAttribute.INCIDENT_START_DATE, SpiraTeamUtil.dateToString(incident.getStartDate()));
			updateTaskAttribute(data, changedAttributes, ArtifactAttribute.INCIDENT_CLOSED_DATE, SpiraTeamUtil.dateToString(incident.getClosedDate()));
			updateTaskAttribute(data, changedAttributes, ArtifactAttribute.INCIDENT_COMPLETION_PERCENTAGE, incident.getCompletionPercent() + "");
			updateTaskAttribute(data, changedAttributes, ArtifactAttribute.INCIDENT_ESTIMATED_EFFORT, SpiraTeamUtil.effortValuesToString(incident.getEstimatedEffort()));
			updateTaskAttribute(data, changedAttributes, ArtifactAttribute.INCIDENT_ACTUAL_EFFORT, SpiraTeamUtil.effortValuesToString(incident.getActualEffort()));
			
			// Handle SpiraTeam comments/resolutions if we have an incident
			try
			{
				IncidentResolution[] resolutions = client.incidentRetrieveResolutions(incident.getArtifactKey()).toArray(new IncidentResolution[0]);
				if (resolutions != null)
				{
					int count = 1;
					for (int i = 0; i < resolutions.length; i++)
					{
						TaskCommentMapper mapper = new TaskCommentMapper();
						mapper.setAuthor(repository.createPerson(resolutions[i].getCreatorName()));
						mapper.setCreationDate(resolutions[i].getCreationDate());
						mapper.setText(resolutions[i].getResolution());
						mapper.setNumber(count);
	
						TaskAttribute attribute = data.getRoot().createAttribute(TaskAttribute.PREFIX_COMMENT + count);
						mapper.applyTo(attribute);
						count++;
					}
				}
			}
			catch (SpiraException ex)
			{
				//Let the user know
				throw new SpiraException(Messages.SpiraTeamTaskDataHandler_UnableToRetrieveComments);
			}
			
			//TODO: Add workflow actions
			/*
			TracAction[] actions = ticket.getActions();
			if (actions != null)
			{
				// add actions and set first as default
				for (TracAction action : actions)
				{
					addOperation(repository, data, ticket, action, action == actions[0]);
				}
			}*/
		}
		
		if (artifact instanceof Task)
		{
			Task task = (Task)artifact;
			updateTaskAttribute(data, changedAttributes, ArtifactAttribute.TASK_TYPE, ArtifactType.TASK.getDisplayName());
			updateTaskAttribute(data, changedAttributes, ArtifactAttribute.TASK_STATUS_ID, task.getTaskStatusId() + "");
			updateTaskAttribute(data, changedAttributes, ArtifactAttribute.TASK_REQUIREMENT_ID, task.getRequirementName());
			updateTaskAttribute(data, changedAttributes, ArtifactAttribute.TASK_RELEASE_ID, task.getReleaseId() + "");
			updateTaskAttribute(data, changedAttributes, ArtifactAttribute.TASK_PRIORITY_ID, task.getTaskPriorityId() + "");
			updateTaskAttribute(data, changedAttributes, ArtifactAttribute.TASK_START_DATE, SpiraTeamUtil.dateToString(task.getStartDate()));
			updateTaskAttribute(data, changedAttributes, ArtifactAttribute.TASK_END_DATE, SpiraTeamUtil.dateToString(task.getEndDate()));
			updateTaskAttribute(data, changedAttributes, ArtifactAttribute.TASK_COMPLETION_PERCENTAGE, task.getCompletionPercent() + "");
			updateTaskAttribute(data, changedAttributes, ArtifactAttribute.TASK_ESTIMATED_EFFORT, SpiraTeamUtil.effortValuesToString(task.getEstimatedEffort()));
			updateTaskAttribute(data, changedAttributes, ArtifactAttribute.TASK_ACTUAL_EFFORT, SpiraTeamUtil.effortValuesToString(task.getActualEffort()));
		}

		/* Handle attachments - future enhancement once the API has been upgraded
		TracAttachment[] attachments = ticket.getAttachments();
		if (attachments != null) {
			for (int i = 0; i < attachments.length; i++) {
				TaskAttachmentMapper mapper = new TaskAttachmentMapper();
				mapper.setAuthor(repository.createPerson(attachments[i].getAuthor()));
				mapper.setDescription(attachments[i].getDescription());
				mapper.setFileName(attachments[i].getFilename());
				mapper.setLength((long) attachments[i].getSize());
				if (attachments[i].getCreated() != null) {
					if (lastChanged == null || attachments[i].getCreated().after(lastChanged)) {
						lastChanged = attachments[i].getCreated();
					}
					mapper.setCreationDate(attachments[i].getCreated());
				}
				mapper.setUrl(repository.itoryUrl() + ITracClient.TICKET_ATTACHMENT_URL + ticket.getId() + "/" //$NON-NLS-1$
						+ TracUtil.encodeUrl(attachments[i].getFilename()));
				mapper.setAttachmentId(i + ""); //$NON-NLS-1$

				TaskAttribute attribute = data.getRoot().createAttribute(TaskAttribute.PREFIX_ATTACHMENT + (i + 1));
				mapper.applyTo(attribute);
			}
		}*/

		return changedAttributes;
	}
	

	@Override
	public void migrateTaskData(TaskRepository taskRepository, TaskData taskData)
	{
		int version = 0;
		if (taskData.getVersion() != null)
		{
			try
			{
				version = Integer.parseInt(taskData.getVersion());
			}
			catch (NumberFormatException e)
			{
				// ignore
			}
		}
		if (version < 020301)
		{
			//Data older than v2.3.1 we just set to the current version
			//because v2.3.1 was the first version of the Mylyn connector :-)
			taskData.setVersion(TASK_DATA_VERSION);
		}
	}
}
