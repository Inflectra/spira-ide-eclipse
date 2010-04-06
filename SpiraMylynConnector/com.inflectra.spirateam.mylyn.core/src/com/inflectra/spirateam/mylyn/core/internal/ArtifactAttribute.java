/**
 * 
 */
package com.inflectra.spirateam.mylyn.core.internal;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.mylyn.tasks.core.data.TaskAttribute;

import com.inflectra.spirateam.mylyn.core.internal.SpiraTeamAttributeMapper.Flag;
import com.inflectra.spirateam.mylyn.core.internal.model.Requirement;

/**
 * The attributes that exist on the various artifacts
 * 
 * @author Inflectra Corporation
 */
public enum ArtifactAttribute
{
	ARTIFACT_TYPE(Requirement.Key.ARTIFACT_TYPE, "Type", TaskAttribute.TASK_KIND, TaskAttribute.TYPE_SHORT_TEXT),
	ARTIFACT_KEY(Requirement.Key.ARTIFACT_KEY, Messages.RequirementAttribute_ArtifactKey, TaskAttribute.TASK_KEY, TaskAttribute.TYPE_SHORT_TEXT, Flag.PEOPLE),
	NAME(Requirement.Key.NAME, Messages.RequirementAttribute_Name, TaskAttribute.SUMMARY, TaskAttribute.TYPE_SHORT_RICH_TEXT),
	DESCRIPTION(Requirement.Key.DESCRIPTION, Messages.RequirementAttribute_Description, TaskAttribute.DESCRIPTION, TaskAttribute.TYPE_LONG_RICH_TEXT);

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

	ArtifactAttribute(Requirement.Key artifactKey, String prettyName, String taskKey, String type)
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
