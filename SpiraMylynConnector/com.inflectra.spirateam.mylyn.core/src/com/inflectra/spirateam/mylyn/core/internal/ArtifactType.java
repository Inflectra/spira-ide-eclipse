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
	REQUIREMENT(1, "RQ"),
	INCIDENT(3, "IN"),
	TASK (6, "TK");
	
	private final int artifactTypeId;
	private final String prefix;
	
	ArtifactType(int artifactTypeId, String prefix)
	{
		this.artifactTypeId = artifactTypeId;
		this.prefix = prefix;
	}
	
	public int getArtifactTypeId()
	{
		return this.artifactTypeId;
	}

	public String getPrefix()
	{
		return this.prefix;
	}
}
