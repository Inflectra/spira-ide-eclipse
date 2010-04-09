/**
 * 
 */
package com.inflectra.spirateam.mylyn.core.internal;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.mylyn.tasks.core.data.TaskAttribute;

import com.inflectra.spirateam.mylyn.core.internal.SpiraTeamAttributeMapper.Flag;
import com.inflectra.spirateam.mylyn.core.internal.model.Artifact;
import com.inflectra.spirateam.mylyn.core.internal.model.Incident;
import com.inflectra.spirateam.mylyn.core.internal.model.Requirement;
import com.inflectra.spirateam.mylyn.core.internal.model.Task;

/**
 * The attributes that exist on the various artifacts
 * 
 * @author Inflectra Corporation
 */
public enum ArtifactAttribute
{
	//Common attributes
	PROJECT_ID(Artifact.Key.PROJECT_ID, Messages.ArtifactAttribute_ProjectId, TaskAttribute.PRODUCT, TaskAttribute.TYPE_SHORT_TEXT, Flag.READ_ONLY),
	ARTIFACT_KEY(Artifact.Key.ARTIFACT_KEY, Messages.ArtifactAttribute_ArtifactKey, TaskAttribute.TASK_KEY, TaskAttribute.TYPE_SHORT_TEXT),
	NAME(Artifact.Key.NAME, Messages.ArtifactAttribute_Name, TaskAttribute.SUMMARY, TaskAttribute.TYPE_SHORT_RICH_TEXT),
	DESCRIPTION(Artifact.Key.DESCRIPTION, Messages.ArtifactAttribute_Description, TaskAttribute.DESCRIPTION, TaskAttribute.TYPE_LONG_RICH_TEXT),
	CREATION_DATE(Artifact.Key.CREATION_DATE, Messages.ArtifactAttribute_CreationDate, TaskAttribute.DATE_CREATION, TaskAttribute.TYPE_DATE, Flag.READ_ONLY),
	LAST_UPDATE_DATE(Artifact.Key.LAST_UPDATE_DATE, Messages.ArtifactAttribute_LastUpdateDate, TaskAttribute.DATE_MODIFICATION, TaskAttribute.TYPE_DATE),
	OWNER_ID(Artifact.Key.OWNER_ID, Messages.ArtifactAttribute_OwnerId, TaskAttribute.USER_ASSIGNED, TaskAttribute.TYPE_PERSON, Flag.PEOPLE),
	
	//Requirement attributes
	REQUIREMENT_STATUS_ID(Requirement.Key.STATUS_ID, Messages.RequirementAttribute_StatusId, TaskAttribute.STATUS, TaskAttribute.TYPE_SINGLE_SELECT),
	REQUIREMENT_AUTHOR_ID(Requirement.Key.AUTHOR_ID, Messages.RequirementAttribute_AuthorId, TaskAttribute.USER_REPORTER, TaskAttribute.TYPE_PERSON, Flag.PEOPLE),
	REQUIREMENT_IMPORTANCE_ID(Requirement.Key.IMPORTANCE_ID, Messages.RequirementAttribute_ImportanceId, TaskAttribute.PRIORITY, TaskAttribute.TYPE_SINGLE_SELECT),
	REQUIREMENT_RELEASE_ID(Requirement.Key.RELEASE_ID, Messages.RequirementAttribute_ReleaseId, TaskAttribute.VERSION, TaskAttribute.TYPE_SINGLE_SELECT),
	REQUIREMENT_PLANNED_EFFORT(Requirement.Key.PLANNED_EFFORT, Messages.Requirementttribute_PlannedEffort, null, TaskAttribute.TYPE_INTEGER),
	
	//Incident attributes
	INCIDENT_PRIORITY_ID(Incident.Key.PRIORITY_ID, Messages.IncidentAttribute_PriorityId, TaskAttribute.PRIORITY, TaskAttribute.TYPE_SINGLE_SELECT),
	INCIDENT_SEVERITY_ID(Incident.Key.SEVERITY_ID, Messages.IncidentAttribute_SeverityId, TaskAttribute.SEVERITY, TaskAttribute.TYPE_SINGLE_SELECT),
	INCIDENT_STATUS_ID(Incident.Key.STATUS_ID, Messages.IncidentAttribute_StatusId, TaskAttribute.STATUS, TaskAttribute.TYPE_SINGLE_SELECT),
	INCIDENT_TYPE_ID(Incident.Key.TYPE_ID, Messages.IncidentAttribute_TypeId, TaskAttribute.TASK_KIND, TaskAttribute.TYPE_SINGLE_SELECT),
	INCIDENT_DETECTED_RELEASE_ID(Incident.Key.DETECTED_RELEASE_ID, Messages.IncidentAttribute_DetectedReleaseId, TaskAttribute.VERSION, TaskAttribute.TYPE_SINGLE_SELECT),
	INCIDENT_RESOLVED_RELEASE_ID(Incident.Key.RESOLVED_RELEASE_ID, Messages.IncidentAttribute_ResolvedReleaseId, TaskAttribute.VERSION, TaskAttribute.TYPE_SINGLE_SELECT),
	INCIDENT_VERIFIED_RELEASE_ID(Incident.Key.VERIFIED_RELEASE_ID, Messages.IncidentAttribute_VerifiedReleaseId, TaskAttribute.VERSION, TaskAttribute.TYPE_SINGLE_SELECT),
	INCIDENT_START_DATE(Incident.Key.START_DATE, Messages.IncidentAttribute_StartDate, null, TaskAttribute.TYPE_DATE),
	INCIDENT_CLOSED_DATE(Incident.Key.CLOSED_DATE, Messages.IncidentAttribute_ClosedDate, TaskAttribute.DATE_DUE, TaskAttribute.TYPE_DATE),
	INCIDENT_COMPLETION_PERCENTAGE(Incident.Key.COMPLETION_PERCENTAGE, Messages.IncidentAttribute_CompletionPercentage, null, TaskAttribute.TYPE_INTEGER),
	INCIDENT_ESTIMATED_EFFORT(Incident.Key.ESTIMATED_EFFORT, Messages.IncidentAttribute_EstimatedEffort, null, TaskAttribute.TYPE_INTEGER),
	INCIDENT_ACTUAL_EFFORT(Incident.Key.ACTUAL_EFFORT, Messages.IncidentAttribute_ActualEffort, null, TaskAttribute.TYPE_INTEGER),
	
	//Task attributes
	TASK_STATUS_ID(Task.Key.STATUS_ID, Messages.TaskAttribute_StatusId, TaskAttribute.STATUS, TaskAttribute.TYPE_SINGLE_SELECT),
	TASK_REQUIREMENT_ID(Task.Key.REQUIREMENT_ID, Messages.TaskAttribute_RequirementId, null, TaskAttribute.TYPE_SHORT_TEXT, Flag.READ_ONLY),
	TASK_RELEASE_ID(Task.Key.RELEASE_ID, Messages.TaskAttribute_ReleaseId, TaskAttribute.VERSION, TaskAttribute.TYPE_SINGLE_SELECT),
	TASK_PRIORITY_ID(Task.Key.PRIORITY_ID, Messages.TaskAttribute_PriorityId, TaskAttribute.PRIORITY, TaskAttribute.TYPE_SINGLE_SELECT),
	TASK_START_DATE(Task.Key.START_DATE, Messages.TaskAttribute_StartDate, null, TaskAttribute.TYPE_DATE),
	TASK_END_DATE(Task.Key.END_DATE, Messages.TaskAttribute_EndDate, TaskAttribute.DATE_DUE, TaskAttribute.TYPE_DATE),
	TASK_COMPLETION_PERCENTAGE(Task.Key.COMPLETION_PERCENTAGE, Messages.TaskAttribute_CompletionPercentage, null, TaskAttribute.TYPE_INTEGER),
	TASK_ESTIMATED_EFFORT(Task.Key.ESTIMATED_EFFORT, Messages.TaskAttribute_EstimatedEffort, null, TaskAttribute.TYPE_INTEGER),
	TASK_ACTUAL_EFFORT(Task.Key.ACTUAL_EFFORT, Messages.TaskAttribute_ActualEffort, null, TaskAttribute.TYPE_INTEGER);
	
	static Map<String, ArtifactAttribute> attributeByArtifactKey = new HashMap<String, ArtifactAttribute>();
	static Map<String, String> artifactKeyByTaskKey = new HashMap<String, String>();

	private final String artifactKey;
	private final String prettyName;
	private final String taskKey;
	private final String type;

	private EnumSet<Flag> flags;

	public static ArtifactAttribute getByTaskKey(String taskKey)
	{
		for (ArtifactAttribute attribute : values())
		{
			if (taskKey.equals(attribute.getTaskKey()))
			{
				return attribute;
			}
		}
		return null;
	}

	public static ArtifactAttribute getByArtifactKey(String artifactKey)
	{
		for (ArtifactAttribute attribute : values())
		{
			if (artifactKey.equals(attribute.getArtifactKey()))
			{
				return attribute;
			}
		}
		return null;
	}

	ArtifactAttribute(Artifact.Key artifactKey, String prettyName, String taskKey, String type, Flag firstFlag, Flag... moreFlags)
	{
		this.artifactKey = artifactKey.getKey();
		this.taskKey = taskKey;
		this.prettyName = prettyName;
		this.type = type;
		if (firstFlag == null)
		{
			this.flags = SpiraTeamAttributeMapper.NO_FLAGS;
		}
		else
		{
			this.flags = EnumSet.of(firstFlag, moreFlags);
		}
	}
	
	ArtifactAttribute(Requirement.Key artifactKey, String prettyName, String taskKey, String type, Flag firstFlag, Flag... moreFlags)
	{
		this.artifactKey = artifactKey.getKey();
		this.taskKey = taskKey;
		this.prettyName = prettyName;
		this.type = type;
		if (firstFlag == null)
		{
			this.flags = SpiraTeamAttributeMapper.NO_FLAGS;
		}
		else
		{
			this.flags = EnumSet.of(firstFlag, moreFlags);
		}
	}
	
	ArtifactAttribute(Incident.Key artifactKey, String prettyName, String taskKey, String type, Flag firstFlag, Flag... moreFlags)
	{
		this.artifactKey = artifactKey.getKey();
		this.taskKey = taskKey;
		this.prettyName = prettyName;
		this.type = type;
		if (firstFlag == null)
		{
			this.flags = SpiraTeamAttributeMapper.NO_FLAGS;
		}
		else
		{
			this.flags = EnumSet.of(firstFlag, moreFlags);
		}
	}
	
	ArtifactAttribute(Task.Key artifactKey, String prettyName, String taskKey, String type, Flag firstFlag, Flag... moreFlags)
	{
		this.artifactKey = artifactKey.getKey();
		this.taskKey = taskKey;
		this.prettyName = prettyName;
		this.type = type;
		if (firstFlag == null)
		{
			this.flags = SpiraTeamAttributeMapper.NO_FLAGS;
		}
		else
		{
			this.flags = EnumSet.of(firstFlag, moreFlags);
		}
	}

	ArtifactAttribute(Artifact.Key artifactKey, String prettyName, String taskKey, String type)
	{
		this(artifactKey, prettyName, taskKey, type, null);
	}
	ArtifactAttribute(Requirement.Key artifactKey, String prettyName, String taskKey, String type)
	{
		this(artifactKey, prettyName, taskKey, type, null);
	}
	ArtifactAttribute(Incident.Key artifactKey, String prettyName, String taskKey, String type)
	{
		this(artifactKey, prettyName, taskKey, type, null);
	}
	ArtifactAttribute(Task.Key artifactKey, String prettyName, String taskKey, String type)
	{
		this(artifactKey, prettyName, taskKey, type, null);
	}

	
	public String getTaskKey()
	{
		return taskKey;
	}

	public String getArtifactKey()
	{
		return artifactKey;
	}

	public String getKind()
	{
		if (flags.contains(Flag.ATTRIBUTE))
		{
			return TaskAttribute.KIND_DEFAULT;
		}
		else if (flags.contains(Flag.PEOPLE))
		{
			return TaskAttribute.KIND_PEOPLE;
		}
		return null;
	}

	public String getType()
	{
		return type;
	}

	public boolean isReadOnly()
	{
		return flags.contains(Flag.READ_ONLY);
	}

	@Override
	public String toString()
	{
		return prettyName;
	}
}
