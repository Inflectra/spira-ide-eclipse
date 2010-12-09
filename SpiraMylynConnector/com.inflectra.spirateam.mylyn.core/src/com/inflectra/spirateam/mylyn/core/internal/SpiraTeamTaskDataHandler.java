/**
 * 
 */
package com.inflectra.spirateam.mylyn.core.internal;

import java.net.MalformedURLException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
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
import org.eclipse.mylyn.tasks.core.RepositoryResponse.ResponseKind;
import org.eclipse.mylyn.tasks.core.data.AbstractTaskDataHandler;
import org.eclipse.mylyn.tasks.core.data.TaskAttribute;
import org.eclipse.mylyn.tasks.core.data.TaskAttributeMapper;
import org.eclipse.mylyn.tasks.core.data.TaskAttributeMetaData;
import org.eclipse.mylyn.tasks.core.data.TaskCommentMapper;
import org.eclipse.mylyn.tasks.core.data.TaskData;
import org.eclipse.mylyn.tasks.core.data.TaskOperation;
import org.eclipse.osgi.util.NLS;

import com.inflectra.spirateam.mylyn.core.internal.model.Artifact;
import com.inflectra.spirateam.mylyn.core.internal.model.ArtifactField;
import com.inflectra.spirateam.mylyn.core.internal.model.Incident;
import com.inflectra.spirateam.mylyn.core.internal.model.IncidentResolution;
import com.inflectra.spirateam.mylyn.core.internal.model.IncidentWorkflowField;
import com.inflectra.spirateam.mylyn.core.internal.model.IncidentWorkflowTransition;
import com.inflectra.spirateam.mylyn.core.internal.model.Requirement;
import com.inflectra.spirateam.mylyn.core.internal.model.RequirementComment;
import com.inflectra.spirateam.mylyn.core.internal.model.Task;
import com.inflectra.spirateam.mylyn.core.internal.services.SpiraDataValidationException;
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
	private static final String TASK_DATA_VERSION = "030000"; //$NON-NLS-1$
	private static final String ATTRIBUTE_ARTIFACT_KEY = "spira.key"; //$NON-NLS-1$
	public static final String ATTRIBUTE_PROJECT_ID = "spira.projectId"; //$NON-NLS-1$
	
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
		metaData.putValue(ATTRIBUTE_ARTIFACT_KEY, artifactAttribute.getArtifactKey());
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
			createAttribute(data, client, ArtifactAttribute.URL);
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
			createAttribute(data, client, ArtifactAttribute.INCIDENT_TRANSITION_STATUS);
			createAttribute(data, client, ArtifactAttribute.INCIDENT_NEW_RESOLUTION);
					
			// Workflow Transitions
			data.getRoot().createAttribute(TaskAttribute.OPERATION).getMetaData().setType(TaskAttribute.TYPE_OPERATION);
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
	}

	public static void addWorkflowTransitions(SpiraImportExport client, Incident incident, TaskRepository repository, TaskData data)
		throws SpiraException
	{
		try
		{
			boolean isOwner = true;	//Always TRUE
			boolean isDetector = false;
			if (incident.getOwnerId() != null)
			{
				//Since we have to be the owner; we are the detector if both are the same
				if (incident.getOpenerId() == incident.getOwnerId().intValue())
				{
					isDetector = true;
				}
			}
			int currentTypeId = incident.getIncidentTypeId();
			int currentStatusId = incident.getIncidentStatusId();
			List<IncidentWorkflowTransition> transitions = client.incidentRetrieveWorkflowTransitions(currentTypeId, currentStatusId, isDetector, isOwner);
			if (transitions != null)
			{
				// add transitions and set first as default
				for (IncidentWorkflowTransition transition : transitions)
				{
					addOperation(repository, data, incident, transition);
				}
			}
		}
		catch (SpiraException ex)
		{
			//Let the user know
			throw new SpiraException(Messages.SpiraTeamTaskDataHandler_UnableToRetrieveWorkflowTransitions);
		}
	}
	
	/**
	 * Called when the typeof the incident is changed
	 */
	public Set<TaskAttribute> changeIncidentType(TaskRepository repository, TaskData taskData, String newIncidentTypeName)
	{
		try
		{
			String taskKey = taskData.getTaskId();
			Set<TaskAttribute> changedAttributes = new HashSet<TaskAttribute>();
			
			//Get the project id for this artifact
			SpiraImportExport client = connector.getClientManager().getSpiraTeamClient(repository);
			SpiraTeamClientData data = client.getData();
			if (data != null)
			{
				if (data.taskToProjectMapping != null)
				{
					if (data.taskToProjectMapping.containsKey(taskKey))
					{
						int projectId = data.taskToProjectMapping.get(taskKey);
	
						//See what kind of artifact we have
	
						//Currently only incidents support workflow operations
						ArtifactType artifactType = ArtifactType.byTaskKey(taskKey);
						if (artifactType.equals(ArtifactType.INCIDENT))
						{
							//Need to match the incident type id that was selected
							TaskAttribute attribute = taskData.getRoot().getAttribute(ArtifactAttribute.INCIDENT_TYPE_ID.getArtifactKey());
							Set<String> types = attribute.getOptions().keySet();
							int newIncidentTypeId = -1;
							for (String typeKey : types)
							{
								String incidentType = attribute.getOptions().get(typeKey);
								if (incidentType.equals(newIncidentTypeName))
								{
									newIncidentTypeId = Integer.parseInt(typeKey);
									break;
								}
							}
							if (newIncidentTypeId != -1)
							{
								//We need to get the current status of the incident
								int currentStatusId = getTaskAttributeIntValue(taskData, ArtifactAttribute.INCIDENT_STATUS_ID);
	
								//Need to change the attributes read-only state for the new type
								updateAttributesForWorkflow(client, taskData, projectId, newIncidentTypeId, currentStatusId, changedAttributes);
								
								//Finally we need to disable the transition operations
								//as the type has changed and we need to submit to server first
								updateTaskAttribute(taskData, changedAttributes, ArtifactAttribute.INCIDENT_TRANSITION_STATUS, SpiraTeamUtil.WORKFLOW_TRANSITION_STATUS_EXECUTED, projectId);
							}
						}
					}
				}
			}
			return changedAttributes;
		}
		catch (MalformedURLException ex)
		{
			throw new RuntimeException (ex.getMessage());
		}
		catch (SpiraException ex)
		{
			throw new RuntimeException (ex.getMessage());
		}
		catch (RuntimeException ex)
		{
			throw ex;
		}
	}
	
	/**
	 * Called when a workflow operation is activated
	 */
	public Set<TaskAttribute> executeOperation(TaskRepository repository, TaskData taskData, TaskOperation operation)
	{
		try
		{
			String taskKey = taskData.getTaskId();
			Set<TaskAttribute> changedAttributes = new HashSet<TaskAttribute>();
			
			//Get the project id for this artifact
			SpiraImportExport client = connector.getClientManager().getSpiraTeamClient(repository);
			SpiraTeamClientData data = client.getData();
			if (data != null)
			{
				if (data.taskToProjectMapping != null)
				{
					if (data.taskToProjectMapping.containsKey(taskKey))
					{
						int projectId = data.taskToProjectMapping.get(taskKey);
	
						//See what kind of artifact we have
	
						//Currently only incidents support workflow operations
						ArtifactType artifactType = ArtifactType.byTaskKey(taskKey);
						if (artifactType.equals(ArtifactType.INCIDENT))
						{
							//Get the id of the workflow transition that was just executed
							int workflowTransitionId = Integer.parseInt(operation.getOperationId());
							
							//We need to get the list of transitions for the incident
							int currentStatusId = getTaskAttributeIntValue(taskData, ArtifactAttribute.INCIDENT_STATUS_ID);
							int currentTypeId = getTaskAttributeIntValue(taskData, ArtifactAttribute.INCIDENT_TYPE_ID);
							//Get all the transitions, even if owner and detector
							//Doesn't matter that we have more, since we already know the ID we want
							List<IncidentWorkflowTransition> transitions = client.incidentRetrieveWorkflowTransitions(currentTypeId, currentStatusId, true, true);
							int destinationIncidentStatusId = -1;
							for (IncidentWorkflowTransition transition : transitions)
							{
								if (transition.getTransitionID() == workflowTransitionId)
								{
									destinationIncidentStatusId = transition.getIncidentStatusIDOutput();
								}
							}
							
							//Need to change the status of the Incident
							if (destinationIncidentStatusId != -1)
							{
								updateTaskAttribute(taskData, changedAttributes, ArtifactAttribute.INCIDENT_STATUS_ID, destinationIncidentStatusId + "", projectId);

								//Need to change the attributes read-only state for the new status
								updateAttributesForWorkflow(client, taskData, projectId, currentTypeId, destinationIncidentStatusId, changedAttributes);
								
								//Finally we need to change the transition status to executed
								updateTaskAttribute(taskData, changedAttributes, ArtifactAttribute.INCIDENT_TRANSITION_STATUS, SpiraTeamUtil.WORKFLOW_TRANSITION_STATUS_EXECUTED, projectId);
							}
						}
					}
				}
			}
			return changedAttributes;
		}
		catch (MalformedURLException ex)
		{
			throw new RuntimeException (ex.getMessage());
		}
		catch (SpiraException ex)
		{
			throw new RuntimeException (ex.getMessage());
		}
		catch (RuntimeException ex)
		{
			throw ex;
		}
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.mylyn.tasks.core.data.AbstractTaskDataHandler#postTaskData(org.eclipse.mylyn.tasks.core.TaskRepository, org.eclipse.mylyn.tasks.core.data.TaskData, java.util.Set, org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public RepositoryResponse postTaskData(TaskRepository repository,
			TaskData taskData, Set<TaskAttribute> oldAttributes,
			IProgressMonitor monitor) throws CoreException
	{
		try
		{
			SpiraImportExport client = connector.getClientManager().getSpiraTeamClient(repository);
			if (taskData.isNew())
			{
				//We don't currently support creating new tasks in Eclipse/Mylyn
				return null;
			}
			else
			{
				String taskKey = taskData.getTaskId();
				
				//Get the project id for this artifact
				SpiraTeamClientData data = client.getData();
				if (data != null)
				{
					if (data.taskToProjectMapping != null)
					{
						if (data.taskToProjectMapping.containsKey(taskKey))
						{
							int projectId = data.taskToProjectMapping.get(taskKey);

							//See what kind of artifact we have

							//Currently only incidents and tasks can be updated
							ArtifactType artifactType = ArtifactType.byTaskKey(taskKey);
							if (artifactType.equals(ArtifactType.TASK))
							{
								updateTaskFromTaskData(client, repository, taskData, projectId);
								return new RepositoryResponse(ResponseKind.TASK_UPDATED, taskKey); //$NON-NLS-1$
							}			
							if (artifactType.equals(ArtifactType.INCIDENT))
							{
								updateIncidentFromTaskData(client, repository, taskData, projectId);
								return new RepositoryResponse(ResponseKind.TASK_UPDATED, taskKey); //$NON-NLS-1$
							}
						}
					}
				}
				
				return null;
			}
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
	
	/**
	 * Gets the string attribute value from a Mylyn task
	 * @param taskData The Mylyn task object data
	 * @param attribute The attribute we want the value of
	 * @return The string value
	 */
	private String getTaskAttributeStringValue (TaskData taskData, ArtifactAttribute attribute)
	{
		TaskAttribute taskAttribute = taskData.getRoot().getAttribute(attribute.getArtifactKey());
		String value = taskAttribute.getValue();
		return value;
	}
	
	private Integer getTaskAttributeIntegerValue(TaskData taskData, ArtifactAttribute attribute)
		throws SpiraDataValidationException
	{
		try
		{
			//First get the string value
			String stringValue = getTaskAttributeStringValue(taskData, attribute);
			
			//Now parse into an Integer object
			if (stringValue == null || stringValue.equals("null") || stringValue.equals(""))
			{
				return null;
			}
			Integer intValue = new Integer(stringValue);
			return intValue;
		}
		catch (NumberFormatException ex)
		{
			//Convert into data validation exception
			throw new SpiraDataValidationException(NLS.bind(Messages.SpiraTeamTaskDataHandler_FieldIsNotValidInteger, attribute.toString()));
		}
	}
	
	private Integer getTaskAttributeEffortValue(TaskData taskData, ArtifactAttribute attribute)
	throws SpiraDataValidationException
	{
		try
		{
			//First get the string value
			String stringValue = getTaskAttributeStringValue(taskData, attribute);
			
			//Now parse into a double object
			if (stringValue == null || stringValue.equals(""))
			{
				return null;
			}
			NumberFormat fmt = NumberFormat.getInstance();
			Number number = fmt.parse(stringValue);

			double doubleValue = number.doubleValue();
			
			//Next we need to convert into a whole number of minutes
			doubleValue = doubleValue * 60;
			return new Double(doubleValue).intValue();
		}
		catch (ParseException ex)
		{
			//Convert into data validation exception
			throw new SpiraDataValidationException(NLS.bind(Messages.SpiraTeamTaskDataHandler_FieldIsNotValidEffort, attribute.toString()));
		}
	}
	
	private int getTaskAttributeIntValue(TaskData taskData, ArtifactAttribute attribute)
		throws SpiraDataValidationException
	{
		try
		{
			//First get the string value
			String stringValue = getTaskAttributeStringValue(taskData, attribute);
			
			//Now parse into an Integer object
			if (stringValue == null || stringValue.equals(""))
			{
				//Used by SpiraTeam for non-nullable integers
				return -1;
			}
			return Integer.parseInt(stringValue);
		}
		catch (NumberFormatException ex)
		{
			//Convert into data validation exception
			throw new SpiraDataValidationException(NLS.bind(Messages.SpiraTeamTaskDataHandler_FieldIsNotValidInteger, attribute.toString()));
		}
	}
	
	private Date getTaskAttributeDateValue(TaskData taskData, ArtifactAttribute attribute)
		throws SpiraDataValidationException
	{
		try
		{
			//First get the string value
			String stringValue = getTaskAttributeStringValue(taskData, attribute);
			
			//Now parse into a Date object
			if (stringValue == null || stringValue.equals(""))
			{
				return null;
			}
			Date dateValue = SpiraTeamUtil.parseDate(stringValue);
			return dateValue;
		}
		catch (NumberFormatException ex)
		{
			//Convert into data validation exception
			throw new SpiraDataValidationException(NLS.bind(Messages.SpiraTeamTaskDataHandler_FieldIsNotValidDate, attribute.toString()));
		}
	}
	
	/**
	 * Updates the Spira Task on the server from the local changes
	 * @param client Reference to the SOAP proxy
	 * @param repository Reference to the Mylyn Repository
	 * @param taskData The task information
	 */
	private void updateTaskFromTaskData(SpiraImportExport client, TaskRepository repository, TaskData taskData, int projectId)
		throws SpiraException
	{	
		//First we need to get a fresh copy of the Task from the server
		String artifactKey = taskData.getTaskId();
		Task task = client.taskRetrieveByKey(artifactKey, projectId, null);
		if (task == null)
		{
			//Need to throw an exception because the task doesn't exist on the server anymore
			throw new SpiraException(Messages.SpiraTeamTaskDataHandler_ArtifactNoLongerExists);
		}
		
		//Next we need to validate that required fields are populated
		String valueToTest = getTaskAttributeStringValue(taskData, ArtifactAttribute.TASK_COMPLETION_PERCENTAGE);
		if (valueToTest == null || valueToTest.equals(""))
		{
			throw new SpiraDataValidationException(NLS.bind(Messages.SpiraTeamTaskDataHandler_FieldIsRequired, ArtifactAttribute.TASK_COMPLETION_PERCENTAGE.toString()));
		}
				
		//Next we need to update the task with the values from IDE
		
		//First we set the cross-attribute properties
		task.setName(getTaskAttributeStringValue(taskData, ArtifactAttribute.NAME));
		task.setDescription(getTaskAttributeStringValue(taskData, ArtifactAttribute.DESCRIPTION));
		task.setOwnerId(getTaskAttributeIntegerValue(taskData, ArtifactAttribute.OWNER_ID));
		//This will be checked by the server to see if it matches the current record
		//since the server uses optimistic concurrency
		task.setLastUpdateDate(getTaskAttributeDateValue(taskData, ArtifactAttribute.LAST_UPDATE_DATE));

		//Now we need to set the task-specific attributes
		task.setTaskStatusId(getTaskAttributeIntValue(taskData, ArtifactAttribute.TASK_STATUS_ID));
		task.setReleaseId(getTaskAttributeIntegerValue(taskData, ArtifactAttribute.TASK_RELEASE_ID));
		task.setTaskPriorityId(getTaskAttributeIntegerValue(taskData, ArtifactAttribute.TASK_PRIORITY_ID));
		task.setStartDate(getTaskAttributeDateValue(taskData, ArtifactAttribute.TASK_START_DATE));
		task.setEndDate(getTaskAttributeDateValue(taskData, ArtifactAttribute.TASK_END_DATE));
		task.setCompletionPercent(getTaskAttributeIntValue(taskData, ArtifactAttribute.TASK_COMPLETION_PERCENTAGE));
		task.setEstimatedEffort(getTaskAttributeEffortValue(taskData, ArtifactAttribute.TASK_ESTIMATED_EFFORT));
		task.setActualEffort(getTaskAttributeEffortValue(taskData, ArtifactAttribute.TASK_ACTUAL_EFFORT));
		
		//Finally we need to commit the changes on the server
		if (task.isDataChanged())
		{
			client.taskUpdate(task);
		}
	}
	
	/**
	 * Updates the Spira Incident on the server from the local changes
	 * @param client Reference to the SOAP proxy
	 * @param repository Reference to the Mylyn Repository
	 * @param taskData The task information
	 */
	private void updateIncidentFromTaskData(SpiraImportExport client, TaskRepository repository, TaskData taskData, int projectId)
		throws SpiraException
	{	
		//First we need to get a fresh copy of the Incident from the server
		String artifactKey = taskData.getTaskId();
		Incident incident = client.incidentRetrieveByKey(artifactKey, projectId, null);
		if (incident == null)
		{
			//Need to throw an exception because the incident doesn't exist on the server anymore
			throw new SpiraException(Messages.SpiraTeamTaskDataHandler_ArtifactNoLongerExists);
		}
		
		//Next we need to validate that non-workflow required fields are populated
		//% Complete
		String valueToTest = getTaskAttributeStringValue(taskData, ArtifactAttribute.INCIDENT_COMPLETION_PERCENTAGE);
		if (valueToTest == null || valueToTest.equals(""))
		{
			throw new SpiraDataValidationException(NLS.bind(Messages.SpiraTeamTaskDataHandler_FieldIsRequired, ArtifactAttribute.INCIDENT_COMPLETION_PERCENTAGE.toString()));
		}
		valueToTest = getTaskAttributeStringValue(taskData, ArtifactAttribute.NAME);
		if (valueToTest == null || valueToTest.equals(""))
		{
			throw new SpiraDataValidationException(NLS.bind(Messages.SpiraTeamTaskDataHandler_FieldIsRequired, ArtifactAttribute.NAME.toString()));
		}
		valueToTest = getTaskAttributeStringValue(taskData, ArtifactAttribute.DESCRIPTION);
		if (valueToTest == null || valueToTest.equals(""))
		{
			throw new SpiraDataValidationException(NLS.bind(Messages.SpiraTeamTaskDataHandler_FieldIsRequired, ArtifactAttribute.DESCRIPTION.toString()));
		}
		
		//Next we need to validate that workflow-required fields are populated
		int currentIncidentTypeId = getTaskAttributeIntValue(taskData, ArtifactAttribute.INCIDENT_TYPE_ID);
		int currentIncidentStatusId = getTaskAttributeIntValue(taskData, ArtifactAttribute.INCIDENT_STATUS_ID);
		validateWorkflowRequiredAttributes(client, taskData, projectId, currentIncidentTypeId, currentIncidentStatusId);
				
		//Next we need to update the incident with the values from IDE
		
		//First we set the cross-attribute properties
		incident.setName(getTaskAttributeStringValue(taskData, ArtifactAttribute.NAME));
		incident.setDescription(getTaskAttributeStringValue(taskData, ArtifactAttribute.DESCRIPTION));
		incident.setOwnerId(getTaskAttributeIntegerValue(taskData, ArtifactAttribute.OWNER_ID));
		//This will be checked by the server to see if it matches the current record
		//since the server uses optimistic concurrency
		incident.setLastUpdateDate(getTaskAttributeDateValue(taskData, ArtifactAttribute.LAST_UPDATE_DATE));

		//Now we need to set the incident-specific attributes
		incident.setPriorityId(getTaskAttributeIntegerValue(taskData, ArtifactAttribute.INCIDENT_PRIORITY_ID));
		incident.setSeverityId(getTaskAttributeIntegerValue(taskData, ArtifactAttribute.INCIDENT_SEVERITY_ID));
		incident.setIncidentStatusId(getTaskAttributeIntValue(taskData, ArtifactAttribute.INCIDENT_STATUS_ID));
		incident.setIncidentTypeId(getTaskAttributeIntValue(taskData, ArtifactAttribute.INCIDENT_TYPE_ID));
		incident.setDetectedReleaseId(getTaskAttributeIntegerValue(taskData, ArtifactAttribute.INCIDENT_DETECTED_RELEASE_ID));
		incident.setResolvedReleaseId(getTaskAttributeIntegerValue(taskData, ArtifactAttribute.INCIDENT_RESOLVED_RELEASE_ID));
		incident.setVerifiedReleaseId(getTaskAttributeIntegerValue(taskData, ArtifactAttribute.INCIDENT_VERIFIED_RELEASE_ID));
		incident.setStartDate(getTaskAttributeDateValue(taskData, ArtifactAttribute.INCIDENT_START_DATE));
		incident.setClosedDate(getTaskAttributeDateValue(taskData, ArtifactAttribute.INCIDENT_CLOSED_DATE));
		incident.setCompletionPercent(getTaskAttributeIntValue(taskData, ArtifactAttribute.INCIDENT_COMPLETION_PERCENTAGE));
		incident.setEstimatedEffort(getTaskAttributeEffortValue(taskData, ArtifactAttribute.INCIDENT_ESTIMATED_EFFORT));
		incident.setActualEffort(getTaskAttributeEffortValue(taskData, ArtifactAttribute.INCIDENT_ACTUAL_EFFORT));

		//Now we need to see if any new comments were submitted
		TaskAttribute newCommentAttribute = taskData.getRoot().getAttribute(ArtifactAttribute.INCIDENT_NEW_RESOLUTION.getArtifactKey());
		String newComment = null;
		if (newCommentAttribute != null)
		{
			newComment = newCommentAttribute.getValue();
		}

		//Finally we need to commit the changes on the server
		if (incident.isDataChanged() || newComment != null)
		{
			client.incidentUpdate(incident, newComment);
		}
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
	
	private static void updateTaskAttribute (TaskData data, Set<TaskAttribute> changedAttributes, ArtifactAttribute artifactAttribute, String stringValue, int projectId)
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
		
		//Next we need to store the project id in the meta-data
		//As many of the attribute options depend on project
		TaskAttributeMetaData metaData = taskAttribute.getMetaData();
		if (metaData != null)
		{
			metaData.putValue(ATTRIBUTE_PROJECT_ID, projectId + "");
		}		
		changedAttributes.add(taskAttribute);
	}
	
	public static Set<TaskAttribute> updateTaskData(SpiraImportExport client, TaskRepository repository, TaskData data, Artifact artifact)
		throws SpiraException
	{
		Set<TaskAttribute> changedAttributes = new HashSet<TaskAttribute>();

		//First we set the cross-attribute properties
		int projectId = artifact.getProjectId();
		updateTaskAttribute(data, changedAttributes, ArtifactAttribute.NAME, artifact.getName(), projectId);
		updateTaskAttribute(data, changedAttributes, ArtifactAttribute.DESCRIPTION, SpiraTeamUtil.HtmlRenderAsPlainText(artifact.getDescription()), projectId);
		updateTaskAttribute(data, changedAttributes, ArtifactAttribute.OWNER_ID, artifact.getOwnerId().toString(), projectId);
		updateTaskAttribute(data, changedAttributes, ArtifactAttribute.CREATION_DATE, SpiraTeamUtil.dateToString(artifact.getCreationDate()), projectId);
		updateTaskAttribute(data, changedAttributes, ArtifactAttribute.LAST_UPDATE_DATE, SpiraTeamUtil.dateToString(artifact.getLastUpdateDate()), projectId);
		
		//Need to detect each type of artifact, for the other attributes
		if (artifact instanceof Requirement)
		{
			updateTaskAttribute(data, changedAttributes, ArtifactAttribute.URL, repository.getRepositoryUrl() + ArtifactType.REQUIREMENT.getBaseUrl() + artifact.getArtifactId(), projectId);
			Requirement requirement = (Requirement)artifact;
			updateTaskAttribute(data, changedAttributes, ArtifactAttribute.REQUIREMENT_TYPE, ArtifactType.REQUIREMENT.getDisplayName(), projectId);
			updateTaskAttribute(data, changedAttributes, ArtifactAttribute.REQUIREMENT_STATUS_ID, requirement.getStatusId() + "", projectId);
			updateTaskAttribute(data, changedAttributes, ArtifactAttribute.REQUIREMENT_AUTHOR_ID, requirement.getAuthorName(), projectId);
			updateTaskAttribute(data, changedAttributes, ArtifactAttribute.REQUIREMENT_IMPORTANCE_ID, requirement.getImportanceId() + "", projectId);
			updateTaskAttribute(data, changedAttributes, ArtifactAttribute.REQUIREMENT_RELEASE_ID, requirement.getReleaseId() + "", projectId);
			updateTaskAttribute(data, changedAttributes, ArtifactAttribute.REQUIREMENT_PLANNED_EFFORT, SpiraTeamUtil.effortValuesToString(requirement.getPlannedEffort()), projectId);
			
			// Handle SpiraTeam comments
			if (requirement.getComments() != null)
			{
				List<RequirementComment> comments = requirement.getComments();
				int count = 1;
				for (RequirementComment comment : comments)
				{
					TaskCommentMapper mapper = new TaskCommentMapper();
					mapper.setAuthor(repository.createPerson(comment.getCreatorName()));
					mapper.setCreationDate(comment.getCreationDate());
					mapper.setText(SpiraTeamUtil.HtmlRenderAsPlainText(comment.getText()));
					mapper.setNumber(count);

					TaskAttribute attribute = data.getRoot().createAttribute(TaskAttribute.PREFIX_COMMENT + count);
					mapper.applyTo(attribute);
					count++;
				}
			}
		}
		
		if (artifact instanceof Incident)
		{
			updateTaskAttribute(data, changedAttributes, ArtifactAttribute.URL, repository.getRepositoryUrl() + ArtifactType.INCIDENT.getBaseUrl() + artifact.getArtifactId(), projectId);
			Incident incident = (Incident)artifact;
			updateTaskAttribute(data, changedAttributes, ArtifactAttribute.INCIDENT_OPENER_ID, incident.getOpenerName(), projectId);
			updateTaskAttribute(data, changedAttributes, ArtifactAttribute.INCIDENT_PRIORITY_ID, incident.getPriorityId() + "", projectId);
			updateTaskAttribute(data, changedAttributes, ArtifactAttribute.INCIDENT_SEVERITY_ID, incident.getSeverityId() + "", projectId);
			updateTaskAttribute(data, changedAttributes, ArtifactAttribute.INCIDENT_STATUS_ID, incident.getIncidentStatusId() + "", projectId);
			updateTaskAttribute(data, changedAttributes, ArtifactAttribute.INCIDENT_TYPE_ID, incident.getIncidentTypeId() + "", projectId);
			updateTaskAttribute(data, changedAttributes, ArtifactAttribute.INCIDENT_DETECTED_RELEASE_ID, incident.getDetectedReleaseId() + "", projectId);
			updateTaskAttribute(data, changedAttributes, ArtifactAttribute.INCIDENT_RESOLVED_RELEASE_ID, incident.getResolvedReleaseId() + "", projectId);
			updateTaskAttribute(data, changedAttributes, ArtifactAttribute.INCIDENT_VERIFIED_RELEASE_ID, incident.getVerifiedReleaseId() + "", projectId);
			updateTaskAttribute(data, changedAttributes, ArtifactAttribute.INCIDENT_START_DATE, SpiraTeamUtil.dateToString(incident.getStartDate()), projectId);
			updateTaskAttribute(data, changedAttributes, ArtifactAttribute.INCIDENT_CLOSED_DATE, SpiraTeamUtil.dateToString(incident.getClosedDate()), projectId);
			updateTaskAttribute(data, changedAttributes, ArtifactAttribute.INCIDENT_COMPLETION_PERCENTAGE, incident.getCompletionPercent() + "", projectId);
			updateTaskAttribute(data, changedAttributes, ArtifactAttribute.INCIDENT_ESTIMATED_EFFORT, SpiraTeamUtil.effortValuesToString(incident.getEstimatedEffort()), projectId);
			updateTaskAttribute(data, changedAttributes, ArtifactAttribute.INCIDENT_ACTUAL_EFFORT, SpiraTeamUtil.effortValuesToString(incident.getActualEffort()), projectId);

			//Used to denote that we have not yet executed a transition
			updateTaskAttribute(data, changedAttributes, ArtifactAttribute.INCIDENT_TRANSITION_STATUS, SpiraTeamUtil.WORKFLOW_TRANSITION_STATUS_ACTIVE, projectId);
			
			//Get the workflow field status for the current type and status
			updateAttributesForWorkflow(client, data, projectId, incident.getIncidentTypeId(), incident.getIncidentStatusId(), changedAttributes);
			
			// Handle SpiraTeam comments/resolutions if we have an incident
			if (incident.getResolutions() != null)
			{
				int count = 1;
				for (IncidentResolution resolution : incident.getResolutions())
				{
					TaskCommentMapper mapper = new TaskCommentMapper();
					mapper.setAuthor(repository.createPerson(resolution.getCreatorName()));
					mapper.setCreationDate(resolution.getCreationDate());
					mapper.setText(SpiraTeamUtil.HtmlRenderAsPlainText(resolution.getResolution()));
					mapper.setNumber(count);

					TaskAttribute attribute = data.getRoot().createAttribute(TaskAttribute.PREFIX_COMMENT + count);
					mapper.applyTo(attribute);
					count++;
				}
			}
			
			//Workflow Transitions
			addWorkflowTransitions(client, incident, repository, data);
		}
		
		if (artifact instanceof Task)
		{
			updateTaskAttribute(data, changedAttributes, ArtifactAttribute.URL, repository.getRepositoryUrl() + ArtifactType.TASK.getBaseUrl() + artifact.getArtifactId(), projectId);
			Task task = (Task)artifact;
			updateTaskAttribute(data, changedAttributes, ArtifactAttribute.TASK_TYPE, ArtifactType.TASK.getDisplayName(), projectId);
			updateTaskAttribute(data, changedAttributes, ArtifactAttribute.TASK_STATUS_ID, task.getTaskStatusId() + "", projectId);
			updateTaskAttribute(data, changedAttributes, ArtifactAttribute.TASK_REQUIREMENT_ID, task.getRequirementName(), projectId);
			updateTaskAttribute(data, changedAttributes, ArtifactAttribute.TASK_RELEASE_ID, task.getReleaseId() + "", projectId);
			updateTaskAttribute(data, changedAttributes, ArtifactAttribute.TASK_PRIORITY_ID, task.getTaskPriorityId() + "", projectId);
			updateTaskAttribute(data, changedAttributes, ArtifactAttribute.TASK_START_DATE, SpiraTeamUtil.dateToString(task.getStartDate()), projectId);
			updateTaskAttribute(data, changedAttributes, ArtifactAttribute.TASK_END_DATE, SpiraTeamUtil.dateToString(task.getEndDate()), projectId);
			updateTaskAttribute(data, changedAttributes, ArtifactAttribute.TASK_COMPLETION_PERCENTAGE, task.getCompletionPercent() + "", projectId);
			updateTaskAttribute(data, changedAttributes, ArtifactAttribute.TASK_ESTIMATED_EFFORT, SpiraTeamUtil.effortValuesToString(task.getEstimatedEffort()), projectId);
			updateTaskAttribute(data, changedAttributes, ArtifactAttribute.TASK_ACTUAL_EFFORT, SpiraTeamUtil.effortValuesToString(task.getActualEffort()), projectId);
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
	
	private static void updateAttributesForWorkflow(SpiraImportExport client, TaskData data, int projectId, int currentIncidentTypeId, int currentIncidentStatusId, Set<TaskAttribute> changedAttributes)
		throws SpiraException
	{
		List<IncidentWorkflowField> workflowFields = client.incidentRetrieveWorkflowFields(projectId, currentIncidentTypeId, currentIncidentStatusId);
		for (String attributeKey : data.getRoot().getAttributes().keySet())
		{
			ArtifactAttribute artifactAttribute = ArtifactAttribute.getByArtifactKey(attributeKey);
			if (artifactAttribute != null)
			{
				//See if we have a workflow controlled field
				String workflowFieldName = artifactAttribute.getWorkflowField();
				if (!workflowFieldName.equals(""))
				{
					//See if we have this in the field list
					boolean matched = false;
					for(IncidentWorkflowField workflowField : workflowFields)
					{
						//We only care about the active flag (i.e. state = 1)
						if (workflowField.getFieldStatus() == SpiraTeamUtil.WORKFLOW_FIELD_STATE_ACTIVE && workflowFieldName.equals(workflowField.getFieldName()))
						{
							matched = true;
						}
					}
					TaskAttribute taskAttribute = data.getRoot().getAttributes().get(attributeKey);
					if (taskAttribute != null)
					{
						//If we didn't find a match in the workflow, we need to
						//make the field Read-Only
						taskAttribute.getMetaData().setReadOnly(!matched);
						changedAttributes.add(taskAttribute);						
					}
				}
			}
		}
	}
	
	private void validateWorkflowRequiredAttributes(SpiraImportExport client, TaskData data, int projectId, int currentIncidentTypeId, int currentIncidentStatusId)
	throws SpiraException
{
	List<IncidentWorkflowField> workflowFields = client.incidentRetrieveWorkflowFields(projectId, currentIncidentTypeId, currentIncidentStatusId);
	for (String attributeKey : data.getRoot().getAttributes().keySet())
	{
		ArtifactAttribute artifactAttribute = ArtifactAttribute.getByArtifactKey(attributeKey);
		if (artifactAttribute != null)
		{
			//See if we have a workflow controlled field
			String workflowFieldName = artifactAttribute.getWorkflowField();
			if (!workflowFieldName.equals(""))
			{
				//See if we have this in the field list
				boolean required = false;
				for(IncidentWorkflowField workflowField : workflowFields)
				{
					//We only care about the required flag (i.e. state = 2)
					if (workflowField.getFieldStatus() == SpiraTeamUtil.WORKFLOW_FIELD_STATE_REQUIRED && workflowFieldName.equals(workflowField.getFieldName()))
					{
						required = true;
					}
				}
				//If required, check to see if a value and throw an exception if not
				if (required)
				{
					String valueToTest = getTaskAttributeStringValue(data, artifactAttribute);
					if (valueToTest == null || valueToTest.equals("null") || valueToTest.equals(""))
					{
						throw new SpiraDataValidationException(NLS.bind(Messages.SpiraTeamTaskDataHandler_FieldIsRequired, artifactAttribute.toString()));
					}
				}
			}
		}
	}
}
	
	private static void addOperation(TaskRepository repository, TaskData data, Incident incident, IncidentWorkflowTransition transition)
	{
		String label = transition.getName();
		if (label != null)
		{
			TaskAttribute attribute = data.getRoot().createAttribute(TaskAttribute.PREFIX_OPERATION + transition.getTransitionID());
			TaskOperation.applyTo(attribute, transition.getTransitionID() + "", label);
			attribute.getMetaData().setReadOnly(false);
		}
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
