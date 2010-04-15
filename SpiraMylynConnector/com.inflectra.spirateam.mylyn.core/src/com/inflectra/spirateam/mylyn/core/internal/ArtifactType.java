/**
 * 
 */
package com.inflectra.spirateam.mylyn.core.internal;

/**
 * The various Spira artifact types that we support
 * @author Inflectra Corporation
 *
 */
public enum ArtifactType
{
	REQUIREMENT(1, "RQ", Messages.ArtifactType_Requirement, "/RequirementDetails.aspx?requirementId="),
	INCIDENT(3, "IN", Messages.ArtifactType_Incident, "/IncidentDetails.aspx?incidentId="),
	TASK (6, "TK", Messages.ArtifactType_Task, "/TaskDetails.aspx?taskId=");
	
	private final int artifactTypeId;
	private final String prefix;
	private final String displayName;
	private final String baseUrl;
	
	public static ArtifactType byTaskKey (String taskKey)
	{
		String prefix = SpiraTeamUtil.getPrefixFromArtifactKey(taskKey);
		for (ArtifactType artifactType : values())
		{
			if (prefix.equals(artifactType.getPrefix()))
			{
				return artifactType;
			}
		}
		return null;
	}
	
	ArtifactType(int artifactTypeId, String prefix, String displayName, String baseUrl)
	{
		this.artifactTypeId = artifactTypeId;
		this.prefix = prefix;
		this.displayName = displayName;
		this.baseUrl = baseUrl;
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
	
	public String getBaseUrl()
	{
		return this.baseUrl;
	}
}
