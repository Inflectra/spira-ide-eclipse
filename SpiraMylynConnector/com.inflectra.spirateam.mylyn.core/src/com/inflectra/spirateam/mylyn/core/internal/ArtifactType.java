/**
 * 
 */
package com.inflectra.spirateam.mylyn.core.internal;

import org.eclipse.mylyn.tasks.core.data.TaskAttribute;

import com.inflectra.spirateam.mylyn.core.internal.SpiraTeamAttributeMapper.Flag;
import com.inflectra.spirateam.mylyn.core.internal.model.Requirement;

/**
 * The various Spira artifact types that we support
 * @author Inflectra Corporation
 *
 */
public enum ArtifactType
{
	REQUIREMENT(1, "RQ", Messages.ArtifactType_Requirement),
	INCIDENT(3, "IN", Messages.ArtifactType_Incident),
	TASK (6, "TK", Messages.ArtifactType_Task);
	
	private final int artifactTypeId;
	private final String prefix;
	private final String displayName;
	
	ArtifactType(int artifactTypeId, String prefix, String displayName)
	{
		this.artifactTypeId = artifactTypeId;
		this.prefix = prefix;
		this.displayName = displayName;
	}
	
	public int getArtifactTypeId()
	{
		return this.artifactTypeId;
	}

	public String getPrefix()
	{
		return this.prefix;
	}
	
	public String getDisplayName()
	{
		return this.displayName;
	}
}
