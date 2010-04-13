package com.inflectra.spirateam.mylyn.core.internal;

import java.util.Date;

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
		if (artifactType.equals(ArtifactType.REQUIREMENT))
		{
			//Get the current priority
			TaskAttribute priorityAttribute = taskData.getRoot().getAttribute(ArtifactAttribute.REQUIREMENT_IMPORTANCE_ID.getArtifactKey());
			if (priorityAttribute != null)
			{
				String priority = priorityAttribute.getValue();
				return SpiraTeamRepositoryConnector.getMylynPriorityForRequirement(priority);
			}
		}
		if (artifactType.equals(ArtifactType.INCIDENT))
		{
			//Get the current priority
			//Incidents have customizable values, so we can't infer from the ID
			//However if the first character of its name is numeric, we will use that
			TaskAttribute priorityAttribute = taskData.getRoot().getAttribute(ArtifactAttribute.INCIDENT_PRIORITY_ID.getArtifactKey());
			if (priorityAttribute != null)
			{
				String priority = priorityAttribute.getValue();
				if (priority != null)
				{
					String priorityName = priorityAttribute.getOption(priorityAttribute.getValue());
					if (priorityName != null)
					{
						if (priorityName.startsWith("1"))
						{
							return PriorityLevel.P1;
						}
						if (priorityName.startsWith("2"))
						{
							return PriorityLevel.P2;
						}
						if (priorityName.startsWith("3"))
						{
							return PriorityLevel.P3;
						}
						if (priorityName.startsWith("4"))
						{
							return PriorityLevel.P4;
						}
						if (priorityName.startsWith("5"))
						{
							return PriorityLevel.P5;
						}
					}
				}
			}
		}
		return null;
	}
	
	@Override
	public Date getCompletionDate()
	{
		//If we have a Task then we don't have a separate completion date
		//from due-date, so if it's in the completed status, we need to
		//set the completion date as the creation date
		//See which type of artifact we have
		String taskKey = taskData.getTaskId();
		ArtifactType artifactType = ArtifactType.byTaskKey(taskKey);
		if (artifactType == null)
		{
			return super.getCompletionDate();
		}
		if (artifactType.equals(ArtifactType.TASK))
		{
			//Get the status and due-date
			TaskAttribute taskStatusAttribute = taskData.getRoot().getAttribute(ArtifactAttribute.TASK_STATUS_ID.getArtifactKey());
			if (taskStatusAttribute != null && taskStatusAttribute.getValue().equals(SpiraImportExport.TASK_STATUS_COMPLETED + ""))
			{
				return getDueDate();
			}
		}
		return super.getCompletionDate();
	}
}
