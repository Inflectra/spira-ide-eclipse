package com.inflectra.spirateam.mylyn.core.internal.model;

import com.inflectra.spirateam.mylyn.core.internal.ArtifactType;
import com.inflectra.spirateam.mylyn.core.internal.SpiraTeamUtil;
import com.inflectra.spirateam.mylyn.core.internal.services.soap.RemoteRequirement;
/**
 * Represents a single requirement in SpiraTeam
 *
 * @author Inflectra Corporation
 */
public class Requirement
	extends Artifact
{
    protected int statusId;
    protected int authorId;
    protected String authorName;
    protected Integer importanceId;
    protected Integer releaseId;
    protected boolean summary;
    protected Integer plannedEffort;

    public enum Key
    {
		//Requirement-specific attributes
    	TYPE("requirement.type"),
		STATUS_ID("requirement.statusId"),
		AUTHOR_ID("requirement.authorId"),
		IMPORTANCE_ID("requirement.importanceId"),
		RELEASE_ID("requirement.releaseId"),
		PLANNED_EFFORT("requirement.plannedEffort");
		
		public static Key fromKey(String name)
		{
			for (Key key : Key.values())
			{
				if (key.getKey().equals(name))
				{
					return key;
				}
			}
			return null;
		}

		private String key;

		Key(String key)
		{
			this.key = key;
		}

		@Override
		public String toString()
		{
			return key;
		}

		public String getKey()
		{
			return key;
		}
	}
       
    /**
     * Creates a requirement based on its equivalent SOAP object
     * @param remoteRequirement The SOAP requirement
     */
    public Requirement(RemoteRequirement remoteRequirement)
    {
    	//Set the various member variables
        this.artifactId = remoteRequirement.getRequirementId().getValue();
        this.projectId = remoteRequirement.getProjectId().getValue();
        this.statusId = remoteRequirement.getStatusId().getValue();
        this.authorId = remoteRequirement.getAuthorId().getValue();
        this.authorName = remoteRequirement.getAuthorName().getValue();
        this.ownerId = remoteRequirement.getOwnerId().getValue();
        this.importanceId = remoteRequirement.getImportanceId().getValue();
        this.releaseId = remoteRequirement.getReleaseId().getValue();
        this.name = remoteRequirement.getName().getValue();
        this.description = remoteRequirement.getDescription().getValue();
        this.creationDate = SpiraTeamUtil.convertDatesXml2Java(remoteRequirement.getCreationDate());
        this.lastUpdateDate = SpiraTeamUtil.convertDatesXml2Java(remoteRequirement.getLastUpdateDate());
        this.summary = remoteRequirement.isSummary();
        this.plannedEffort = remoteRequirement.getPlannedEffort().getValue();
        
        //Now the custom properties
        this.text01 = remoteRequirement.getText01().getValue();
        this.text02 = remoteRequirement.getText02().getValue();
        this.text03 = remoteRequirement.getText03().getValue();
        this.text04 = remoteRequirement.getText04().getValue();
        this.text05 = remoteRequirement.getText05().getValue();
        this.text06 = remoteRequirement.getText06().getValue();
        this.text07 = remoteRequirement.getText07().getValue();
        this.text08 = remoteRequirement.getText08().getValue();
        this.text09 = remoteRequirement.getText09().getValue();
        this.text10 = remoteRequirement.getText10().getValue();
        this.list01 = remoteRequirement.getList01().getValue();
        this.list02 = remoteRequirement.getList02().getValue();
        this.list03 = remoteRequirement.getList03().getValue();
        this.list04 = remoteRequirement.getList04().getValue();
        this.list05 = remoteRequirement.getList05().getValue();
        this.list06 = remoteRequirement.getList06().getValue();
        this.list07 = remoteRequirement.getList07().getValue();
        this.list08 = remoteRequirement.getList08().getValue();
        this.list09 = remoteRequirement.getList09().getValue();
        this.list10 = remoteRequirement.getList10().getValue();
    }
    
    @Override
    public ArtifactType getArtifactType()
    {
    	return ArtifactType.REQUIREMENT;
    }
    
    /**
     * Gets the value of the artifact key (format is RQ<requirement-id>)
     * @return The prefixed requirement ID
     */
    @Override
    public String getArtifactKey()
    {
    	return getArtifactType().getPrefix() + this.artifactId;
    }

    /**
     * Gets the value of the statusId property.
     * 
     */
    public int getStatusId() {
        return statusId;
    }

    /**
     * Sets the value of the statusId property.
     * 
     */
    public void setStatusId(int value) {
        this.statusId = value;
    }

    /**
     * Gets the value of the authorId property.
     * 
     */
    public int getAuthorId() {
        return authorId;
    }

    /**
     * Sets the value of the authorId property.
     * 
     */
    public void setAuthorId(int value) {
        this.authorId = value;
    }

    /**
     * Gets the value of the authorName property.
     * 
     */
    public String getAuthorName()
    {
        return authorName;
    }
 
    /**
     * Sets the value of the authorName property.
     * 
     */
    public void setAuthorName(String value)
    {
        this.authorName = value;
    }

    /**
     * Gets the value of the importanceId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getImportanceId() {
        return importanceId;
    }

    /**
     * Sets the value of the importanceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setImportanceId(Integer value) {
        this.importanceId = value;
    }

    /**
     * Gets the value of the releaseId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getReleaseId() {
        return releaseId;
    }

    /**
     * Sets the value of the releaseId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setReleaseId(Integer value) {
        this.releaseId = value;
    }

 

    /**
     * Gets the value of the summary property.
     * 
     */
    public boolean isSummary() {
        return summary;
    }

    /**
     * Sets the value of the summary property.
     * 
     */
    public void setSummary(boolean value) {
        this.summary = value;
    }

    /**
     * Gets the value of the plannedEffort property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPlannedEffort() {
        return plannedEffort;
    }

    /**
     * Sets the value of the plannedEffort property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPlannedEffort(Integer value) {
        this.plannedEffort = value;
    }
}
