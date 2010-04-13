/**
 * 
 */
package com.inflectra.spirateam.mylyn.core.internal;

import java.util.Date;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.core.runtime.Assert;
import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.mylyn.tasks.core.data.TaskAttribute;
import org.eclipse.mylyn.tasks.core.data.TaskAttributeMapper;

import com.inflectra.spirateam.mylyn.core.internal.model.ArtifactField;
import com.inflectra.spirateam.mylyn.core.internal.model.ArtifactFieldValue;
import com.inflectra.spirateam.mylyn.core.internal.services.SpiraImportExport;

/**
 * Provides a mapping from Mylyn task keys to SpiraTeam artifact ids.
 *  
 * @author Inflectra Corporation
 */
public class SpiraTeamAttributeMapper extends TaskAttributeMapper
{
	private final SpiraImportExport client;
	
	public enum Flag
	{
		READ_ONLY, ATTRIBUTE, PEOPLE
	};
	
	public static final EnumSet<Flag> NO_FLAGS = EnumSet.noneOf(Flag.class);
	
	public SpiraTeamAttributeMapper(TaskRepository taskRepository, SpiraImportExport client)
	{
		super(taskRepository);
		Assert.isNotNull(client);
		this.client = client;
	}
	
	public static boolean isInternalAttribute(TaskAttribute attribute)
	{
		String type = attribute.getMetaData().getType();
		if (TaskAttribute.TYPE_ATTACHMENT.equals(type) || TaskAttribute.TYPE_OPERATION.equals(type)
				|| TaskAttribute.TYPE_COMMENT.equals(type))
		{
			return true;
		}
		String id = attribute.getId();
		return TaskAttribute.COMMENT_NEW.equals(id) || TaskAttribute.ADD_SELF_CC.equals(id);
	}
	
	@Override
	public String mapToRepositoryKey(TaskAttribute parent, String taskAttributeKey)
	{
		//First we need to find out the type of artifact we have
		//since the same Mylyn attribute maps to different Spira attributes
		//depending on the artifact type
		String taskId = parent.getTaskData().getTaskId();
		ArtifactType artifactType = ArtifactType.byTaskKey(taskId);
		ArtifactAttribute attribute = ArtifactAttribute.getByTaskKey(taskAttributeKey, artifactType);
		return (attribute != null) ? attribute.getArtifactKey() : taskAttributeKey;
	}
	
	@Override
	public Map<String, String> getOptions(TaskAttribute attribute)
	{
		Map<String, String> options = getRepositoryOptions(client, attribute.getId());
		return (options != null) ? options : super.getOptions(attribute);
	}

	public static Map<String, String> getRepositoryOptions(SpiraImportExport client, String artifactAttributeKey)
	{
		if (ArtifactAttribute.OWNER_ID.getArtifactKey().equals(artifactAttributeKey))
		{
			return getOptions(client.usersGet(), true);
		}
		else if (ArtifactAttribute.REQUIREMENT_STATUS_ID.getArtifactKey().equals(artifactAttributeKey))
		{
			return getOptions(client.requirementGetStatus(), false);
		}
		else if (ArtifactAttribute.REQUIREMENT_IMPORTANCE_ID.getArtifactKey().equals(artifactAttributeKey))
		{
			return getOptions(client.requirementGetImportance(), true);
		}
		else if (ArtifactAttribute.REQUIREMENT_RELEASE_ID.getArtifactKey().equals(artifactAttributeKey))
		{
			return getOptions(client.releasesGet(true), true);
		}
		else if (ArtifactAttribute.TASK_STATUS_ID.getArtifactKey().equals(artifactAttributeKey))
		{
			return getOptions(client.taskGetStatus(), false);
		}
		else if (ArtifactAttribute.TASK_PRIORITY_ID.getArtifactKey().equals(artifactAttributeKey))
		{
			return getOptions(client.taskGetPriority(), true);
		}
		else if (ArtifactAttribute.TASK_RELEASE_ID.getArtifactKey().equals(artifactAttributeKey))
		{
			return getOptions(client.releasesGet(true), true);
		}
		return null;
	}

	private static Map<String, String> getOptions(ArtifactField artifactField, boolean allowEmpty)
	{
		ArtifactFieldValue[] values = artifactField.getValues();
		if (values != null && values.length > 0)
		{
			Map<String, String> options = new LinkedHashMap<String, String>();
			if (allowEmpty)
			{
				options.put("", ""); //$NON-NLS-1$ //$NON-NLS-2$
			}
			for (ArtifactFieldValue value : values)
			{
				options.put(value.getId() + "", value.getName());
			}
			return options;
		}
		return null;
	}
}
