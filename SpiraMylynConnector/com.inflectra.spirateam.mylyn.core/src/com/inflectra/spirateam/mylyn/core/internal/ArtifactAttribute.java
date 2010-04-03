package com.inflectra.spirateam.mylyn.core.internal;

import com.inflectra.spirateam.mylyn.core.internal.SpiraTeamAttributeMapper.Flag;

public interface ArtifactAttribute
{
	public abstract RequirementAttribute getByTaskKey(String taskKey);
	public abstract RequirementAttribute getByArtifactKey(String artifactKey);
	public abstract String getTaskKey();
	public abstract String getArtifactKey();
	public abstract String getKind();
	public abstract String getType();
	public abstract boolean isReadOnly();
}
