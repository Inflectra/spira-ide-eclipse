package com.inflectra.spirateam.mylyn.core.internal.model;

import java.util.Date;

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
    protected Integer requirementId;
    protected int projectId;
    protected int statusId;
    protected int authorId;
    protected Integer ownerId;
    protected Integer importanceId;
    protected Integer releaseId;
    protected String name;
    protected String description;
    protected Date creationDate;
    protected Date lastUpdateDate;
    protected boolean summary;
    protected Integer plannedEffort;

    public enum Key
    {
		REQUIREMENT_ID("requirementId"), NAME("name"), DESCRIPTION("description");	//$NON-NLS-1$

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
     * Creates an empty requirement
     */
    public Requirement()
    {
    }
    
    /**
     * Creates a requirement based on its equivalent SOAP object
     * @param remoteRequirement The SOAP requirement
     */
    public Requirement(RemoteRequirement remoteRequirement)
    {
    	//Set the various member variables
        this.requirementId = remoteRequirement.getRequirementId();
        this.projectId = remoteRequirement.getProjectId();
        this.statusId = remoteRequirement.getStatusId();
        this.authorId = remoteRequirement.getAuthorId();
        this.ownerId = remoteRequirement.getOwnerId();
        this.importanceId = remoteRequirement.getImportanceId();
        this.releaseId = remoteRequirement.getReleaseId();
        this.name = remoteRequirement.getName();
        this.description = remoteRequirement.getDescription();
        this.creationDate = SpiraTeamUtil.ConvertDatesXml2Java(remoteRequirement.getCreationDate());
        this.lastUpdateDate = SpiraTeamUtil.ConvertDatesXml2Java(remoteRequirement.getLastUpdateDate());
        this.summary = remoteRequirement.isSummary();
        this.plannedEffort = remoteRequirement.getPlannedEffort();
    }
    
    /**
     * Gets the value of the requirementId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRequirementId() {
        return requirementId;
    }

    /**
     * Sets the value of the requirementId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRequirementId(Integer value) {
        this.requirementId = value;
    }

    /**
     * Gets the value of the projectId property.
     * 
     */
    public int getProjectId() {
        return projectId;
    }

    /**
     * Sets the value of the projectId property.
     * 
     */
    public void setProjectId(int value) {
        this.projectId = value;
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
     * Gets the value of the ownerId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getOwnerId() {
        return ownerId;
    }

    /**
     * Sets the value of the ownerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setOwnerId(Integer value) {
        this.ownerId = value;
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
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the creationDate property.
     * 
     * @return
     *     possible object is
     *     {@link Date }
     *     
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * Sets the value of the creationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Date }
     *     
     */
    public void setCreationDate(Date value) {
        this.creationDate = value;
    }

    /**
     * Gets the value of the lastUpdateDate property.
     * 
     * @return
     *     possible object is
     *     {@link Date }
     *     
     */
    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    /**
     * Sets the value of the lastUpdateDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Date }
     *     
     */
    public void setLastUpdateDate(Date value) {
        this.lastUpdateDate = value;
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
