package com.inflectra.spirateam.mylyn.core.internal;

import org.eclipse.mylyn.tasks.core.ITask;
import org.eclipse.mylyn.tasks.core.ITask.PriorityLevel;
import org.eclipse.mylyn.tasks.core.data.TaskAttribute;
import org.eclipse.mylyn.tasks.core.data.TaskData;
import org.eclipse.mylyn.tasks.core.data.TaskMapper;

import com.inflectra.spirateam.mylyn.core.internal.model.ArtifactField;
import com.inflectra.spirateam.mylyn.core.internal.model.ArtifactFieldValue;
import com.inflectra.spirateam.mylyn.core.internal.services.SpiraImportExport;

public class SpiraTeamTaskMapper extends TaskMapper
{
	private final SpiraImportExport client;
	TaskData taskData;

	public SpiraTeamTaskMapper(TaskData taskData, SpiraImportExport client)
	{
		super(taskData);
		this.client = client;
		this.taskData = taskData;
	}
	
	@Override
	//We can't use the built-in version because we have different artifact fields
	//mapped to the SAME mylyn fields
	//e.g. Requirement has different fields to Incidents and Tasks.
	public boolean applyTo(ITask task)
	{
		//Common Attributes
		boolean changed = false;
		if (hasChanges(task.getCreationDate(), ArtifactAttribute.CREATION_DATE))
		{
			task.setCreationDate(getCreationDate());
			changed = true;
		}
		if (hasChanges(task.getModificationDate(), ArtifactAttribute.LAST_UPDATE_DATE))
		{
			task.setModificationDate(getModificationDate());
			changed = true;
		}
		if (hasChanges(task.getSummary(), ArtifactAttribute.NAME))
		{
			task.setSummary(getSummary());
			changed = true;
		}
		String taskKey = task.getTaskKey();
		if (hasChanges(taskKey, ArtifactAttribute.ARTIFACT_KEY))
		{
			task.setTaskKey(getTaskKey());
			changed = true;
		}
		if (hasChanges(task.getOwner(), ArtifactAttribute.OWNER_ID))
		{
			task.setOwner(getOwner());
			changed = true;
		}
		
		//Task Attributes
		ArtifactType artifactType = ArtifactType.byTaskKey(taskKey);
		if (artifactType.equals(ArtifactType.TASK))
		{
			task.setTaskKind(getTaskKind());
			
			if (hasChanges(task.getDueDate(), ArtifactAttribute.TASK_END_DATE))
			{
				task.setDueDate(getDueDate());
				changed = true;
			}
			if (hasChanges(task.getPriority(), ArtifactAttribute.TASK_PRIORITY_ID))
			{
				if (getPriorityLevel() != null)
				{
					task.setPriority(getPriorityLevel().toString());
				}
				else
				{
					task.setPriority(PriorityLevel.getDefault().toString());
				}
				changed = true;
			}
		}

		return changed;
	}

	private boolean hasChanges(Object value, ArtifactAttribute artifactAttribute)
	{
		TaskData taskData = getTaskData();
		TaskAttribute attribute = taskData.getRoot().getAttribute(artifactAttribute.getArtifactKey());
		String attributeKey = artifactAttribute.getTaskKey();
		if (attribute != null)
		{
			if (TaskAttribute.TYPE_BOOLEAN.equals(attribute.getMetaData().getType()))
			{
				return areNotEquals(value, taskData.getAttributeMapper().getBooleanValue(attribute));
			}
			else if (TaskAttribute.TYPE_DATE.equals(attribute.getMetaData().getType()))
			{
				return areNotEquals(value, taskData.getAttributeMapper().getDateValue(attribute));
			}
			else if (TaskAttribute.TYPE_INTEGER.equals(attribute.getMetaData().getType()))
			{
				return areNotEquals(value, taskData.getAttributeMapper().getIntegerValue(attribute));
			}
			else if (TaskAttribute.PRIORITY.equals(attributeKey))
			{
				PriorityLevel priorityLevel = getPriorityLevel();
				return areNotEquals(value, (priorityLevel != null) ? priorityLevel.toString() : getPriority());
			}
			else if (TaskAttribute.TASK_KIND.equals(attributeKey))
			{
				return areNotEquals(value, getTaskKind());
			}
			else 
			{
				return areNotEquals(value, taskData.getAttributeMapper().getValue(attribute));
			}
		}
		return false;
	}

	private boolean areNotEquals(Object existingProperty, Object newProperty) {
		return (existingProperty != null) ? !existingProperty.equals(newProperty) : newProperty != null;
	}
	
	
	@Override
	public PriorityLevel getPriorityLevel()
	{
		//See which type of artifact we have
		String taskKey = taskData.getTaskId();
		ArtifactType artifactType = ArtifactType.byTaskKey(taskKey);
		if (artifactType == null)
		{
			return super.getPriorityLevel();
		}
		if (artifactType.equals(ArtifactType.TASK))
		{
			//Get the current priority
			TaskAttribute priorityAttribute = taskData.getRoot().getAttribute(ArtifactAttribute.TASK_PRIORITY_ID.getArtifactKey());
			if (priorityAttribute != null)
			{
				String priority = priorityAttribute.getValue();
				return SpiraTeamRepositoryConnector.getMylynPriorityForTask(priority);
			}
		}
		return null;
	}
	
	@Override
	public String getTaskStatus()
	{
		//See which type of artifact we have
		String taskKey = taskData.getTaskId();
		ArtifactType artifactType = ArtifactType.byTaskKey(taskKey);
		if (artifactType.equals(ArtifactType.TASK))
		{
			return "Buggered";
		}
		return super.getTaskStatus();
	}
	
	@Override
	public String getTaskKind()
	{
		//See which type of artifact we have
		String taskKey = taskData.getTaskId();
		ArtifactType artifactType = ArtifactType.byTaskKey(taskKey);
		if (artifactType == null)
		{
			return super.getTaskKind();
		}
		return artifactType.getDisplayName();
	}
}
