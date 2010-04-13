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
