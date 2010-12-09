package com.inflectra.spirateam.mylyn.core.internal.model;

import java.util.ArrayList;
import java.util.List;

import com.inflectra.spirateam.mylyn.core.internal.ArtifactType;
import com.inflectra.spirateam.mylyn.core.internal.SpiraTeamUtil;
import com.inflectra.spirateam.mylyn.core.internal.services.SpiraImportExport;
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
    protected String importanceName;
    protected Integer releaseId;
    protected String releaseVersionNumber;
    protected boolean summary;
    protected Integer plannedEffort;
    
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
		PLANNED_EFFORT("requirement.plannedEffort"),
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
    	//Set the various member variables
        this.artifactId = remoteRequirement.getRequirementId().getValue();
        this.projectId = remoteRequirement.getProjectId().getValue();
        this.statusId = remoteRequirement.getStatusId().getValue();
        this.authorId = remoteRequirement.getAuthorId().getValue();
        this.authorName = remoteRequirement.getAuthorName().getValue();
        this.ownerId = remoteRequirement.getOwnerId().getValue();
        this.importanceId = remoteRequirement.getImportanceId().getValue();
        this.importanceName = remoteRequirement.getImportanceName().getValue();
        this.releaseId = remoteRequirement.getReleaseId().getValue();
        this.releaseVersionNumber = remoteRequirement.getReleaseVersionNumber().getValue();
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
    
    /**
     * Converts this object into its soap equivalent
     * @return
     */
    public RemoteRequirement toSoapObject()
    {
    	//Set the properties on the SOAP object
    	RemoteRequirement remoteRequirement = new RemoteRequirement();
    	remoteRequirement.setRequirementId(SpiraImportExport.CreateJAXBInteger("RequirementId", this.artifactId));
    	remoteRequirement.setProjectId(SpiraImportExport.CreateJAXBInteger("ProjectId", this.projectId));
    	remoteRequirement.setOwnerId(SpiraImportExport.CreateJAXBInteger("OwnerId", this.ownerId));
    	remoteRequirement.setAuthorId(SpiraImportExport.CreateJAXBInteger("AuthorId", this.authorId));
    	remoteRequirement.setReleaseId(SpiraImportExport.CreateJAXBInteger("ReleaseId", this.releaseId));
    	remoteRequirement.setName(SpiraImportExport.CreateJAXBString("Name", this.name));
    	remoteRequirement.setDescription(SpiraImportExport.CreateJAXBString("Description", this.description));
    	remoteRequirement.setCreationDate(SpiraTeamUtil.convertDatesJava2Xml(this.creationDate));
    	remoteRequirement.setLastUpdateDate(SpiraTeamUtil.convertDatesJava2Xml(this.lastUpdateDate));
    	remoteRequirement.setStatusId(SpiraImportExport.CreateJAXBInteger("StatusId", this.statusId));
    	remoteRequirement.setReleaseId(SpiraImportExport.CreateJAXBInteger("ReleaseId", this.releaseId));
    	remoteRequirement.setImportanceId(SpiraImportExport.CreateJAXBInteger("ImportanceId", this.importanceId));
    	remoteRequirement.setPlannedEffort(SpiraImportExport.CreateJAXBInteger("PlannedEffort", this.plannedEffort));
        
        //Now the custom properties
    	remoteRequirement.setText01(SpiraImportExport.CreateJAXBString("Text01", this.text01));
    	remoteRequirement.setText02(SpiraImportExport.CreateJAXBString("Text02", this.text02));
    	remoteRequirement.setText03(SpiraImportExport.CreateJAXBString("Text03", this.text03));
    	remoteRequirement.setText04(SpiraImportExport.CreateJAXBString("Text04", this.text04));
    	remoteRequirement.setText05(SpiraImportExport.CreateJAXBString("Text05", this.text05));
    	remoteRequirement.setText06(SpiraImportExport.CreateJAXBString("Text06", this.text06));
    	remoteRequirement.setText07(SpiraImportExport.CreateJAXBString("Text07", this.text07));
    	remoteRequirement.setText08(SpiraImportExport.CreateJAXBString("Text08", this.text08));
    	remoteRequirement.setText09(SpiraImportExport.CreateJAXBString("Text09", this.text09));
    	remoteRequirement.setText10(SpiraImportExport.CreateJAXBString("Text10", this.text10));
    	remoteRequirement.setList01(SpiraImportExport.CreateJAXBInteger("List01", this.list01));
    	remoteRequirement.setList02(SpiraImportExport.CreateJAXBInteger("List02", this.list02));
    	remoteRequirement.setList03(SpiraImportExport.CreateJAXBInteger("List03", this.list03));
    	remoteRequirement.setList04(SpiraImportExport.CreateJAXBInteger("List04", this.list04));
    	remoteRequirement.setList05(SpiraImportExport.CreateJAXBInteger("List05", this.list05));
    	remoteRequirement.setList06(SpiraImportExport.CreateJAXBInteger("List06", this.list06));
    	remoteRequirement.setList07(SpiraImportExport.CreateJAXBInteger("List07", this.list07));
    	remoteRequirement.setList08(SpiraImportExport.CreateJAXBInteger("List08", this.list08));
    	remoteRequirement.setList09(SpiraImportExport.CreateJAXBInteger("List09", this.list09));
    	remoteRequirement.setList10(SpiraImportExport.CreateJAXBInteger("List10", this.list10));
        
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

	/**
	 * @return the comments
	 */
	public List<RequirementComment> getComments()
	{
		return this.comments;
	}
}
