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
	PROJECT_ID(Artifact.Key.PROJECT_ID, Messages.ArtifactAttribute_ProjectId, TaskAttribute.PRODUCT, TaskAttribute.TYPE_SHORT_TEXT, "", Flag.READ_ONLY),
	ARTIFACT_KEY(Artifact.Key.ARTIFACT_KEY, Messages.ArtifactAttribute_ArtifactKey, TaskAttribute.TASK_KEY, TaskAttribute.TYPE_SHORT_TEXT, ""),
	NAME(Artifact.Key.NAME, Messages.ArtifactAttribute_Name, TaskAttribute.SUMMARY, TaskAttribute.TYPE_SHORT_RICH_TEXT, "Name"),
	DESCRIPTION(Artifact.Key.DESCRIPTION, Messages.ArtifactAttribute_Description, TaskAttribute.DESCRIPTION, TaskAttribute.TYPE_LONG_RICH_TEXT, "Description"),
	CREATION_DATE(Artifact.Key.CREATION_DATE, Messages.ArtifactAttribute_CreationDate, TaskAttribute.DATE_CREATION, TaskAttribute.TYPE_DATE, "", Flag.READ_ONLY),
	LAST_UPDATE_DATE(Artifact.Key.LAST_UPDATE_DATE, Messages.ArtifactAttribute_LastUpdateDate, TaskAttribute.DATE_MODIFICATION, TaskAttribute.TYPE_DATE, ""),
	OWNER_ID(Artifact.Key.OWNER_ID, Messages.ArtifactAttribute_OwnerId, TaskAttribute.USER_ASSIGNED, TaskAttribute.TYPE_SINGLE_SELECT, "OwnerId", Flag.PEOPLE),
	URL(Artifact.Key.URL, Messages.ArtifactAttribute_Url, TaskAttribute.TASK_URL, TaskAttribute.TYPE_URL, ""),
	CONCURRENCY_DATE(Artifact.Key.CONCURRENCY_DATE, Messages.ArtifactAttribute_ConcurrencyDate, null, TaskAttribute.TYPE_DATE, ""),
	
	//Requirement attributes
	REQUIREMENT_TYPE(Requirement.Key.TYPE, Messages.RequirementAttribute_Type, TaskAttribute.TASK_KIND, TaskAttribute.TYPE_SHORT_TEXT),
	REQUIREMENT_STATUS_ID(Requirement.Key.STATUS_ID, Messages.RequirementAttribute_StatusId, TaskAttribute.STATUS, TaskAttribute.TYPE_SINGLE_SELECT, Flag.ATTRIBUTE),
	REQUIREMENT_AUTHOR_ID(Requirement.Key.AUTHOR_ID, Messages.RequirementAttribute_AuthorId, TaskAttribute.USER_REPORTER, TaskAttribute.TYPE_SHORT_TEXT, Flag.PEOPLE, Flag.READ_ONLY),
	REQUIREMENT_IMPORTANCE_ID(Requirement.Key.IMPORTANCE_ID, Messages.RequirementAttribute_ImportanceId, TaskAttribute.PRIORITY, TaskAttribute.TYPE_SINGLE_SELECT, Flag.ATTRIBUTE),
	REQUIREMENT_RELEASE_ID(Requirement.Key.RELEASE_ID, Messages.RequirementAttribute_ReleaseId, TaskAttribute.VERSION, TaskAttribute.TYPE_SINGLE_SELECT, Flag.ATTRIBUTE),
	REQUIREMENT_PLANNED_EFFORT(Requirement.Key.PLANNED_EFFORT, Messages.RequirementAttribute_PlannedEffort, null, TaskAttribute.TYPE_SHORT_TEXT, Flag.ATTRIBUTE),
	REQUIREMENT_NEW_COMMENT(Requirement.Key.COMMENT, Messages.RequirementAttribute_NewComment, TaskAttribute.COMMENT_NEW, TaskAttribute.TYPE_LONG_RICH_TEXT),
	
	//Incident attributes
	INCIDENT_OPENER_ID(Incident.Key.OPENER_ID, Messages.IncidentAttribute_OpenerId, TaskAttribute.USER_REPORTER, TaskAttribute.TYPE_SHORT_TEXT, "", Flag.PEOPLE, Flag.READ_ONLY),
	INCIDENT_PRIORITY_ID(Incident.Key.PRIORITY_ID, Messages.IncidentAttribute_PriorityId, TaskAttribute.PRIORITY, TaskAttribute.TYPE_SINGLE_SELECT, "PriorityId", Flag.ATTRIBUTE),
	INCIDENT_SEVERITY_ID(Incident.Key.SEVERITY_ID, Messages.IncidentAttribute_SeverityId, TaskAttribute.SEVERITY, TaskAttribute.TYPE_SINGLE_SELECT, "SeverityId", Flag.ATTRIBUTE),
	INCIDENT_STATUS_ID(Incident.Key.STATUS_ID, Messages.IncidentAttribute_StatusId, TaskAttribute.STATUS, TaskAttribute.TYPE_SINGLE_SELECT, "", Flag.ATTRIBUTE, Flag.READ_ONLY),
	INCIDENT_TYPE_ID(Incident.Key.TYPE_ID, Messages.IncidentAttribute_TypeId, TaskAttribute.TASK_KIND, TaskAttribute.TYPE_SINGLE_SELECT, "IncidentTypeId", Flag.ATTRIBUTE),
	INCIDENT_DETECTED_RELEASE_ID(Incident.Key.DETECTED_RELEASE_ID, Messages.IncidentAttribute_DetectedReleaseId, TaskAttribute.VERSION, TaskAttribute.TYPE_SINGLE_SELECT, "DetectedReleaseId", Flag.ATTRIBUTE),
	INCIDENT_RESOLVED_RELEASE_ID(Incident.Key.RESOLVED_RELEASE_ID, Messages.IncidentAttribute_ResolvedReleaseId, TaskAttribute.VERSION, TaskAttribute.TYPE_SINGLE_SELECT, "ResolvedReleaseId", Flag.ATTRIBUTE),
	INCIDENT_VERIFIED_RELEASE_ID(Incident.Key.VERIFIED_RELEASE_ID, Messages.IncidentAttribute_VerifiedReleaseId, TaskAttribute.VERSION, TaskAttribute.TYPE_SINGLE_SELECT, "VerifiedReleaseId", Flag.ATTRIBUTE),
	INCIDENT_START_DATE(Incident.Key.START_DATE, Messages.IncidentAttribute_StartDate, TaskAttribute.DATE_DUE, TaskAttribute.TYPE_DATE, "StartDate", Flag.ATTRIBUTE),
	INCIDENT_CLOSED_DATE(Incident.Key.CLOSED_DATE, Messages.IncidentAttribute_ClosedDate, TaskAttribute.DATE_COMPLETION, TaskAttribute.TYPE_DATE, "ClosedDate", Flag.ATTRIBUTE),
	INCIDENT_COMPLETION_PERCENTAGE(Incident.Key.COMPLETION_PERCENTAGE, Messages.IncidentAttribute_CompletionPercentage, null, TaskAttribute.TYPE_SHORT_TEXT, "", Flag.READ_ONLY, Flag.ATTRIBUTE),
	INCIDENT_ESTIMATED_EFFORT(Incident.Key.ESTIMATED_EFFORT, Messages.IncidentAttribute_EstimatedEffort, null, TaskAttribute.TYPE_SHORT_TEXT, "EstimatedEffort", Flag.ATTRIBUTE),
	INCIDENT_ACTUAL_EFFORT(Incident.Key.ACTUAL_EFFORT, Messages.IncidentAttribute_ActualEffort, null, TaskAttribute.TYPE_SHORT_TEXT, "ActualEffort", Flag.ATTRIBUTE),
	INCIDENT_REMAINING_EFFORT(Incident.Key.REMAINING_EFFORT, Messages.IncidentAttribute_RemainingEffort, null, TaskAttribute.TYPE_SHORT_TEXT, "RemainingEffort", Flag.ATTRIBUTE),
	INCIDENT_PROJECTED_EFFORT(Incident.Key.PROJECTED_EFFORT, Messages.IncidentAttribute_ProjectedEffort, null, TaskAttribute.TYPE_SHORT_TEXT, "", Flag.READ_ONLY, Flag.ATTRIBUTE),
	INCIDENT_TRANSITION_STATUS(Incident.Key.TRANSITION_STATUS, null, null, TaskAttribute.TYPE_SHORT_TEXT, ""),
	INCIDENT_NEW_RESOLUTION(Incident.Key.RESOLUTION, Messages.IncidentAttribute_NewResolution, TaskAttribute.COMMENT_NEW, TaskAttribute.TYPE_LONG_RICH_TEXT, "Resolution"),
	
	//Task attributes
	TASK_TYPE(Task.Key.TYPE, Messages.TaskAttribute_Type, TaskAttribute.TASK_KIND, TaskAttribute.TYPE_SHORT_TEXT),
	TASK_STATUS_ID(Task.Key.STATUS_ID, Messages.TaskAttribute_StatusId, TaskAttribute.STATUS, TaskAttribute.TYPE_SINGLE_SELECT, Flag.ATTRIBUTE),
	TASK_REQUIREMENT_ID(Task.Key.REQUIREMENT_NAME, Messages.TaskAttribute_RequirementId, null, TaskAttribute.TYPE_SHORT_RICH_TEXT, Flag.READ_ONLY, Flag.ATTRIBUTE),
	TASK_RELEASE_ID(Task.Key.RELEASE_ID, Messages.TaskAttribute_ReleaseId, TaskAttribute.VERSION, TaskAttribute.TYPE_SINGLE_SELECT, Flag.ATTRIBUTE),
	TASK_PRIORITY_ID(Task.Key.PRIORITY_ID, Messages.TaskAttribute_PriorityId, TaskAttribute.PRIORITY, TaskAttribute.TYPE_SINGLE_SELECT, Flag.ATTRIBUTE),
	TASK_CREATOR_ID(Task.Key.CREATOR_ID, Messages.TaskAttribute_CreatorId, TaskAttribute.USER_REPORTER, TaskAttribute.TYPE_SINGLE_SELECT, Flag.ATTRIBUTE),
	TASK_START_DATE(Task.Key.START_DATE, Messages.TaskAttribute_StartDate, null, TaskAttribute.TYPE_DATE, Flag.ATTRIBUTE),
	TASK_END_DATE(Task.Key.END_DATE, Messages.TaskAttribute_EndDate, TaskAttribute.DATE_DUE, TaskAttribute.TYPE_DATE, Flag.ATTRIBUTE),
	TASK_COMPLETION_PERCENTAGE(Task.Key.COMPLETION_PERCENTAGE, Messages.TaskAttribute_CompletionPercentage, null, TaskAttribute.TYPE_SHORT_TEXT, Flag.READ_ONLY, Flag.ATTRIBUTE),
	TASK_ESTIMATED_EFFORT(Task.Key.ESTIMATED_EFFORT, Messages.TaskAttribute_EstimatedEffort, null, TaskAttribute.TYPE_SHORT_TEXT, Flag.ATTRIBUTE),
	TASK_ACTUAL_EFFORT(Task.Key.ACTUAL_EFFORT, Messages.TaskAttribute_ActualEffort, null, TaskAttribute.TYPE_SHORT_TEXT, Flag.ATTRIBUTE),
	TASK_REMAINING_EFFORT(Task.Key.REMAINING_EFFORT, Messages.TaskAttribute_RemainingEffort, null, TaskAttribute.TYPE_SHORT_TEXT, Flag.ATTRIBUTE),
	TASK_PROJECTED_EFFORT(Task.Key.PROJECTED_EFFORT, Messages.TaskAttribute_ProjectedEffort, null, TaskAttribute.TYPE_SHORT_TEXT, Flag.READ_ONLY, Flag.ATTRIBUTE),
	TASK_NEW_COMMENT(Task.Key.COMMENT, Messages.TaskAttribute_NewComment, TaskAttribute.COMMENT_NEW, TaskAttribute.TYPE_LONG_RICH_TEXT);
	
	static Map<String, ArtifactAttribute> attributeByArtifactKey = new HashMap<String, ArtifactAttribute>();
	static Map<String, String> artifactKeyByTaskKey = new HashMap<String, String>();

	private final String artifactKey;
	private final String prettyName;
	private final String taskKey;
	private final String type;
	private String workflowField = "";
	private ArtifactType artifactType = null;

	private EnumSet<Flag> flags;

	public static ArtifactAttribute getByTaskKey(String taskKey, ArtifactType artifactType)
	{
		if (taskKey == null || artifactType == null)
		{
			return null;
		}
		for (ArtifactAttribute attribute : values())
		{
			if (taskKey.equals(attribute.getTaskKey()) && (attribute.getArtifactType() == null || attribute.getArtifactType() == artifactType))
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

	ArtifactAttribute(Artifact.Key artifactKey, String prettyName, String taskKey, String type, String workflowField, Flag firstFlag, Flag... moreFlags)
	{
		this.artifactType = null;	//Common attribute
		this.artifactKey = artifactKey.getKey();
		this.taskKey = taskKey;
		this.prettyName = prettyName;
		this.type = type;
		this.workflowField = workflowField;
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
		this.artifactType = ArtifactType.REQUIREMENT;	//Requirement attribute
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
	
	ArtifactAttribute(Incident.Key artifactKey, String prettyName, String taskKey, String type, String workflowField, Flag firstFlag, Flag... moreFlags)
	{
		this.artifactType = ArtifactType.INCIDENT;	//Incident attribute
		this.artifactKey = artifactKey.getKey();
		this.taskKey = taskKey;
		this.prettyName = prettyName;
		this.type = type;
		this.workflowField = workflowField;
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
		this.artifactType = ArtifactType.TASK;	//Task attribute
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

	ArtifactAttribute(Artifact.Key artifactKey, String prettyName, String taskKey, String type, String workflowField)
	{
		this(artifactKey, prettyName, taskKey, type, workflowField, null);
	}
	ArtifactAttribute(Requirement.Key artifactKey, String prettyName, String taskKey, String type)
	{
		this(artifactKey, prettyName, taskKey, type, null);
	}
	ArtifactAttribute(Incident.Key artifactKey, String prettyName, String taskKey, String type, String workflowField)
	{
		this(artifactKey, prettyName, taskKey, type, workflowField, null);
	}
	ArtifactAttribute(Task.Key artifactKey, String prettyName, String taskKey, String type)
	{
		this(artifactKey, prettyName, taskKey, type, null);
	}

	public ArtifactType getArtifactType()
	{
		return artifactType;
	}
	
	public String getTaskKey()
	{
		return taskKey;
	}
	
	public String getWorkflowField()
	{
		return workflowField;
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
