/**
 * 
 */
package com.inflectra.spirateam.mylyn.core.internal;

import java.util.EnumSet;

import org.eclipse.core.runtime.Assert;
import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.mylyn.tasks.core.data.TaskAttribute;
import org.eclipse.mylyn.tasks.core.data.TaskAttributeMapper;

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
}
