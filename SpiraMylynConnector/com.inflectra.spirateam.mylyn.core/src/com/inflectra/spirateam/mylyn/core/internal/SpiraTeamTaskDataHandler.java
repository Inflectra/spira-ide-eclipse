/**
 * 
 */
package com.inflectra.spirateam.mylyn.core.internal;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
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
import org.eclipse.mylyn.tasks.core.data.TaskData;

import com.inflectra.spirateam.mylyn.core.internal.model.Artifact;
import com.inflectra.spirateam.mylyn.core.internal.model.ArtifactField;
import com.inflectra.spirateam.mylyn.core.internal.model.Incident;
import com.inflectra.spirateam.mylyn.core.internal.model.Requirement;
import com.inflectra.spirateam.mylyn.core.internal.model.Task;
import com.inflectra.spirateam.mylyn.core.internal.services.SpiraConnectionException;
import com.inflectra.spirateam.mylyn.core.internal.services.SpiraException;
import com.inflectra.spirateam.mylyn.core.internal.services.SpiraImportExport;

/**
 * Maps SpiraTeam artifacts to the lightweight ITask objects
 * @author Inflectra Corporation
 *
 */
public class SpiraTeamTaskDataHandler extends AbstractTaskDataHandler
{
	private static final String TASK_DATA_VERSION = "1"; //$NON-NLS-1$
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
			// TODO catch TracException
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
			//TODO: Get any meta-data (priorities, etc.) from SpiraTeam
			//client.updateAttributes(monitor, false);
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
			String[] values = field.getOptions();
			if (values != null && values.length > 0)
			{
				if (field.isOptional())
				{
					attr.putOption("", ""); //$NON-NLS-1$ //$NON-NLS-2$
				}
				for (String value : values)
				{
					attr.putOption(value, value);
				}
				if (field.getDefaultValue() != null)
				{
					try
					{
						int index = Integer.parseInt(field.getDefaultValue());
						if (index > 0 && index < values.length)
						{
							attr.setValue(values[index]);
						}
					}
					catch (NumberFormatException e)
					{
						for (String value : values)
						{
							if (field.getDefaultValue().equals(value.toString()))
							{
								attr.setValue(value);
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
	}

	public static TaskAttribute createAttribute(TaskData data, ArtifactAttribute artifactAttribute)
	{
		return createAttribute(data, null, artifactAttribute);
	}

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
			//ArtifactField field = client.getArtifactFieldByName(artifactAttribute.getArtifactKey());
			//TODO: Implement repository field options (workflows, etc.)
			/*
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
			else*/ if (TaskAttribute.TYPE_SINGLE_SELECT.equals(artifactAttribute.getType()))
			{
				attr.getMetaData().setReadOnly(true);
			}
			/*
			if (field != null)
			{
				String defaultValue = field.getDefaultValue();
				if (defaultValue != null && defaultValue.length() > 0)
				{
					attr.setValue(defaultValue);
				}
			}*/
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
		
		// custom fields
		/*
		TracTicketField[] fields = client.getTicketFields();
		if (fields != null) {
			for (TracTicketField field : fields) {
				if (field.isCustom()) {
					createAttribute(data, field);
				}
			}
		}
		// people
		createAttribute(data, client, TracAttribute.OWNER);
		if (existingTask) {
			createAttribute(data, client, TracAttribute.REPORTER);
		}
		createAttribute(data, client, TracAttribute.CC);
		if (existingTask) {
			data.getRoot().createAttribute(TracAttributeMapper.NEW_CC).getMetaData().setType(
					TaskAttribute.TYPE_SHORT_TEXT).setReadOnly(false);
			data.getRoot().createAttribute(TracAttributeMapper.REMOVE_CC);
			data.getRoot().createAttribute(TaskAttribute.COMMENT_NEW).getMetaData().setType(
					TaskAttribute.TYPE_LONG_RICH_TEXT).setReadOnly(false);
		}
		// operations
		data.getRoot().createAttribute(TaskAttribute.OPERATION).getMetaData().setType(TaskAttribute.TYPE_OPERATION);*/
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
		try
		{
			if (!SpiraTeamRepositoryConnector.hasRichEditor(repository))
			{
				createDefaultAttributes(taskData, client, true);
				Set<TaskAttribute> changedAttributes = updateTaskData(repository, taskData, requirement);
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
				updateTaskData(repository, taskData, requirement);
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
		try
		{
			if (!SpiraTeamRepositoryConnector.hasRichEditor(repository))
			{
				createDefaultAttributes(taskData, client, true);
				Set<TaskAttribute> changedAttributes = updateTaskData(repository, taskData, incident);
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
				updateTaskData(repository, taskData, incident);
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
		try
		{
			if (!SpiraTeamRepositoryConnector.hasRichEditor(repository))
			{
				createDefaultAttributes(taskData, client, true);
				Set<TaskAttribute> changedAttributes = updateTaskData(repository, taskData, spiraTask);
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
				updateTaskData(repository, taskData, spiraTask);
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
	
	public static Set<TaskAttribute> updateTaskData(TaskRepository repository, TaskData data, Artifact artifact)
	{
		Set<TaskAttribute> changedAttributes = new HashSet<TaskAttribute>();

		//First we set the cross-attribute properties
		updateTaskAttribute(data, changedAttributes, ArtifactAttribute.NAME, artifact.getName());
		updateTaskAttribute(data, changedAttributes, ArtifactAttribute.DESCRIPTION, artifact.getDescription());
		updateTaskAttribute(data, changedAttributes, ArtifactAttribute.OWNER_ID, artifact.getOwnerId().toString());
		updateTaskAttribute(data, changedAttributes, ArtifactAttribute.CREATION_DATE, artifact.getCreationDate().toString());
		updateTaskAttribute(data, changedAttributes, ArtifactAttribute.LAST_UPDATE_DATE, artifact.getLastUpdateDate().toString());

		//Need to detect each type of artifact, for the other attributes

		/* TODO: Handle SpiraTeam comments
		TracComment[] comments = ticket.getComments();
		if (comments != null) {
			int count = 1;
			for (int i = 0; i < comments.length; i++) {
				if (!"comment".equals(comments[i].getField()) || "".equals(comments[i].getNewValue())) { //$NON-NLS-1$ //$NON-NLS-2$
					continue;
				}

				TaskCommentMapper mapper = new TaskCommentMapper();
				mapper.setAuthor(repository.createPerson(comments[i].getAuthor()));
				mapper.setCreationDate(comments[i].getCreated());
				mapper.setText(comments[i].getNewValue());
				// TODO mapper.setUrl();
				mapper.setNumber(count);

				TaskAttribute attribute = data.getRoot().createAttribute(TaskAttribute.PREFIX_COMMENT + count);
				mapper.applyTo(attribute);
				count++;
			}
		}*/

		/* Handle attachments
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
				mapper.setUrl(repository.getRepositoryUrl() + ITracClient.TICKET_ATTACHMENT_URL + ticket.getId() + "/" //$NON-NLS-1$
						+ TracUtil.encodeUrl(attachments[i].getFilename()));
				mapper.setAttachmentId(i + ""); //$NON-NLS-1$

				TaskAttribute attribute = data.getRoot().createAttribute(TaskAttribute.PREFIX_ATTACHMENT + (i + 1));
				mapper.applyTo(attribute);
			}
		}*/

		/* TODO: Add workflow actions
		TracAction[] actions = ticket.getActions();
		if (actions != null) {
			// add actions and set first as default
			for (TracAction action : actions) {
				addOperation(repository, data, ticket, action, action == actions[0]);
			}
		}*/

		return changedAttributes;
	}
}
