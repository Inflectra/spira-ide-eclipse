package com.inflectra.spirateam.mylyn.core.internal.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.inflectra.spirateam.mylyn.core.internal.ArtifactType;
import com.inflectra.spirateam.mylyn.core.internal.SpiraTeamUtil;
import com.inflectra.spirateam.mylyn.core.internal.rest.RemoteRequirement;
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
    protected int requirementTypeId;
    protected Integer componentId;
    protected String authorName;
    protected Integer importanceId;
    protected String importanceName;
    protected Integer releaseId;
    protected String releaseVersionNumber;
    protected boolean summary;
    protected Double estimate;
    
    //Contains the collection of comments
    protected List<RequirementComment> comments = new ArrayList<RequirementComment>();

    public enum Key
    {
		//Requirement-specific attributes
    	TYPE("requirement.type"),
		STATUS_ID("requirement.statusId"),
		AUTHOR_ID("requirement.authorId"),
		IMPORTANCE_ID("requirement.importanceId"),
		RELEASE_ID("requirement.releaseId"),
		ESTIMATE("requirement.estimate"),
		COMPONENT_ID("requirement.componentId"),
		COMMENT("requirement.comment");
		
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
    	//Populate the cross-artifact type properties
    	PopulateGeneralProperties(remoteRequirement);
    	
    	//Set the various member variables
        this.name = remoteRequirement.Name;
        this.description = remoteRequirement.Description;
        this.creationDate = SpiraTeamUtil.convertDatesToLocal(remoteRequirement.CreationDate);
        this.lastUpdateDate = SpiraTeamUtil.convertDatesToLocal(remoteRequirement.LastUpdateDate);
        this.artifactId = remoteRequirement.RequirementId;
        this.requirementTypeId = remoteRequirement.RequirementTypeId;
        this.componentId = remoteRequirement.ComponentId;
        this.statusId = remoteRequirement.StatusId;
        this.authorId = remoteRequirement.AuthorId;
        this.authorName = remoteRequirement.AuthorName;
        this.ownerId = remoteRequirement.OwnerId;
        this.importanceId = remoteRequirement.ImportanceId;
        this.importanceName = remoteRequirement.ImportanceName;
        this.releaseId = remoteRequirement.ReleaseId;
        this.releaseVersionNumber = remoteRequirement.ReleaseVersionNumber;
        this.summary = remoteRequirement.Summary;
        this.estimate = (remoteRequirement.EstimatePoints == null) ? null : remoteRequirement.EstimatePoints.doubleValue();
    }
    
    /**
     * Converts this object into its soap equivalent
     * @return
     */
    public RemoteRequirement toSoapObject()
    {
    	//Set the properties on the SOAP object
    	RemoteRequirement remoteRequirement = new RemoteRequirement();
    	
        //First the artifact base properties
    	ExtractGeneralProperties(remoteRequirement);

    	//Next the requirement-specific ones
    	remoteRequirement.RequirementId = this.artifactId;
    	remoteRequirement.OwnerId = this.ownerId;
    	remoteRequirement.AuthorId = this.authorId;
    	remoteRequirement.ReleaseId = this.releaseId;
    	remoteRequirement.Name = this.name;
    	remoteRequirement.Description = this.description;
    	remoteRequirement.CreationDate = this.creationDate;
    	remoteRequirement.LastUpdateDate = SpiraTeamUtil.convertDatesToUtc(this.lastUpdateDate);
    	remoteRequirement.StatusId = this.statusId;
    	remoteRequirement.RequirementTypeId = this.requirementTypeId;
    	remoteRequirement.ComponentId = this.componentId;
    	remoteRequirement.ReleaseId = this.releaseId;
    	remoteRequirement.ImportanceId = this.importanceId;
    	remoteRequirement.EstimatePoints = BigDecimal.valueOf(this.estimate);
            	
        return remoteRequirement;
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
     * Gets the value of the importanceName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImportanceName() {
        return importanceName;
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
     * Gets the value of the releaseVersionNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReleaseVersionNumber() {
        return releaseVersionNumber;
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
     * Gets the value of the estimate property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getEstimate() {
        return estimate;
    }

    /**
     * Sets the value of the estimate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setEstimate(Double value) {
        this.estimate = value;
    }

	/**
	 * @return the comments
	 */
	public List<RequirementComment> getComments()
	{
		return this.comments;
	}

	public int getRequirementTypeId() {
		return requirementTypeId;
	}

	public void setRequirementTypeId(int requirementTypeId) {
		this.requirementTypeId = requirementTypeId;
	}

	public Integer getComponentId() {
		return componentId;
	}

	public void setComponentId(Integer componentId) {
		this.componentId = componentId;
	}
}
