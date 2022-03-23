package com.inflectra.spirateam.mylyn.core.internal.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import com.inflectra.spirateam.mylyn.core.internal.ArtifactType;
import com.inflectra.spirateam.mylyn.core.internal.SpiraTeamUtil;
import com.inflectra.spirateam.mylyn.core.internal.services.SpiraImportExport;
import com.inflectra.spirateam.mylyn.core.internal.rest.RemoteIncident;
/**
 * Represents a single incident in SpiraTeam
 *
 * @author Inflectra Corporation
 */
public class Incident extends Artifact
{
    protected Integer priorityId;
    protected Integer severityId;
    protected int incidentStatusId;
    protected int incidentTypeId;
    protected int openerId;
    protected ArrayList<Integer> testRunStepIds;
    protected Integer detectedReleaseId;
    protected Integer resolvedReleaseId;
    protected Integer verifiedReleaseId;
    protected Date startDate;
    protected Date closedDate;
    protected int completionPercent;
    protected Integer estimatedEffort;
    protected Integer actualEffort;
    protected Integer remainingEffort;
    protected Integer projectedEffort;
    protected String priorityName;
    protected String severityName;
    protected String incidentStatusName;
    protected String incidentTypeName;
    protected String openerName;
    protected String ownerName;
    protected String projectName;
    protected String detectedReleaseVersionNumber;
    protected String resolvedReleaseVersionNumber;
    protected String verifiedReleaseVersionNumber;
    protected Boolean incidentStatusOpenStatus;
    protected ArrayList<Integer> componentIds;
    
    //Contains the collection of resolutions
    protected List<IncidentResolution> resolutions = new ArrayList<IncidentResolution>();
    
    public enum Key
    {
		//Incident-specific attributes
		PRIORITY_ID("incident.priorityId"),
		SEVERITY_ID("incident.severityId"),
		STATUS_ID("incident.statusId"),
		TYPE_ID("incident.typeId"),
		OPENER_ID("incident.openerId"),
		DETECTED_RELEASE_ID("incident.detectedReleaseId"),
		RESOLVED_RELEASE_ID("incident.resolvedReleaseId"),
		VERIFIED_RELEASE_ID("incident.verifiedReleaseId"),
		START_DATE("incident.startDate"),
		CLOSED_DATE("incident.closedDate"),
		COMPLETION_PERCENTAGE("incident.completionPercentage"),
		ESTIMATED_EFFORT("incident.estimatedEffort"),
		ACTUAL_EFFORT("incident.actualEffort"),
		REMAINING_EFFORT("incident.remainingEffort"),
		PROJECTED_EFFORT("incident.projectedEffort"),
		COMPONENT_IDS("incident.componentIds"),
		TRANSITION_STATUS("incident.internal.transitionStatus"),
		RESOLUTION("incident.resolution");

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
     * Creates an incident based on its equivalent SOAP object
     * @param remoteIncident The SOAP incident
     */
    public Incident(RemoteIncident remoteIncident)
    {
    	//Populate the cross-artifact type properties
    	PopulateGeneralProperties(remoteIncident);

    	//Set the various member variables
        this.artifactId = remoteIncident.IncidentId;
        this.ownerId = remoteIncident.OwnerId;
        this.name = remoteIncident.Name;
        this.description = remoteIncident.Description;
        this.creationDate = SpiraTeamUtil.convertDatesToLocal(remoteIncident.CreationDate);
        this.lastUpdateDate = SpiraTeamUtil.convertDatesToLocal(remoteIncident.LastUpdateDate);
        this.priorityId = remoteIncident.PriorityId;
        this.severityId = remoteIncident.SeverityId;
        this.incidentStatusId = remoteIncident.IncidentStatusId;
        this.incidentTypeId = remoteIncident.IncidentTypeId;
        this.openerId = remoteIncident.OpenerId;
        this.testRunStepIds = remoteIncident.TestRunStepIds;
        this.detectedReleaseId = remoteIncident.DetectedReleaseId;
        this.resolvedReleaseId = remoteIncident.ResolvedReleaseId;
        this.verifiedReleaseId = remoteIncident.VerifiedReleaseId;
        this.startDate = SpiraTeamUtil.convertDatesToLocal(remoteIncident.StartDate);
        this.closedDate = SpiraTeamUtil.convertDatesToLocal(remoteIncident.ClosedDate);
        this.completionPercent = 0;
        if (remoteIncident.EstimatedEffort != null && remoteIncident.RemainingEffort != null && remoteIncident.EstimatedEffort > 0)
        {
        	int completionPercent = (remoteIncident.EstimatedEffort - remoteIncident.RemainingEffort) / remoteIncident.EstimatedEffort;
            this.completionPercent = completionPercent;
        }
        this.estimatedEffort = remoteIncident.EstimatedEffort;
        this.actualEffort = remoteIncident.ActualEffort;
        this.remainingEffort = remoteIncident.RemainingEffort;
        this.projectedEffort = remoteIncident.ProjectedEffort;
        this.priorityName = remoteIncident.PriorityName;
        this.severityName = remoteIncident.SeverityName;
        this.incidentStatusName = remoteIncident.IncidentStatusName;
        this.incidentTypeName = remoteIncident.IncidentTypeName;
        this.openerName = remoteIncident.OpenerName;
        this.ownerName = remoteIncident.OwnerName;
        this.projectName = remoteIncident.ProjectName;
        this.componentIds = remoteIncident.ComponentIds;
        this.detectedReleaseVersionNumber = remoteIncident.DetectedReleaseVersionNumber;
        this.resolvedReleaseVersionNumber = remoteIncident.ResolvedReleaseVersionNumber;
        this.verifiedReleaseVersionNumber = remoteIncident.VerifiedReleaseVersionNumber;
        this.incidentStatusOpenStatus = remoteIncident.IncidentStatusOpenStatus;
    }
    
    /**
     * Converts this object into its soap equivalent
     * @return
     */
    public RemoteIncident toSoapObject()
    {
    	//Set the properties on the SOAP object
    	RemoteIncident remoteIncident = new RemoteIncident();
    	
        //First the artifact base properties
    	ExtractGeneralProperties(remoteIncident);

    	//Next the incident-specific ones
    	remoteIncident.IncidentId = this.artifactId;
    	remoteIncident.OwnerId = this.ownerId;
    	remoteIncident.Name = this.name;
    	remoteIncident.Description = this.description;
    	remoteIncident.CreationDate = SpiraTeamUtil.convertDatesToUtc(this.creationDate);
    	remoteIncident.LastUpdateDate= SpiraTeamUtil.convertDatesToUtc(this.lastUpdateDate);

    	remoteIncident.PriorityId = this.priorityId;
    	remoteIncident.SeverityId = this.severityId;
    	remoteIncident.IncidentStatusId = this.incidentStatusId;
    	remoteIncident.IncidentTypeId = this.incidentTypeId;
    	remoteIncident.OpenerId = this.openerId;
    	remoteIncident.TestRunStepIds = this.testRunStepIds;
    	remoteIncident.ComponentIds = this.componentIds;
    	remoteIncident.DetectedReleaseId = this.detectedReleaseId;
    	remoteIncident.ResolvedReleaseId = this.resolvedReleaseId;
    	remoteIncident.VerifiedReleaseId = this.verifiedReleaseId;
    	remoteIncident.StartDate = SpiraTeamUtil.convertDatesToUtc(this.startDate);
    	remoteIncident.ClosedDate = SpiraTeamUtil.convertDatesToUtc(this.closedDate);
    	remoteIncident.EstimatedEffort = this.estimatedEffort;
    	remoteIncident.ActualEffort = this.actualEffort;
    	remoteIncident.RemainingEffort = this.remainingEffort;
               
        return remoteIncident;
    }
    
    @Override
    public ArtifactType getArtifactType()
    {
    	return ArtifactType.INCIDENT;
    }
    
    /**
     * Gets the value of the artifact key (format is IN<incident-id>)
     * @return The prefixed incident ID
     */
    @Override
    public String getArtifactKey()
    {
    	return getArtifactType().getPrefix() + this.artifactId;
    }
    
    /**
     * Gets the value of the priorityId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPriorityId()
    {
        return priorityId;
    }

    /**
     * Sets the value of the priorityId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPriorityId(Integer value)
    {
    	if (hasChanged(this.priorityId, value))
    	{
    		this.dataChanged = true;
    	}
        this.priorityId = value;
    }

    /**
     * Gets the value of the severityId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSeverityId()
    {
        return severityId;
    }

    /**
     * Sets the value of the severityId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSeverityId(Integer value)
    {
    	if (hasChanged(this.severityId, value))
    	{
    		this.dataChanged = true;
    	}
        this.severityId = value;
    }

    /**
     * Gets the value of the incidentStatusId property.
     * 
     */
    public int getIncidentStatusId() {
        return incidentStatusId;
    }

    /**
     * Sets the value of the incidentStatusId property.
     * 
     */
    public void setIncidentStatusId(int value)
    {
    	if (this.incidentStatusId != value)
    	{
    		this.dataChanged = true;
    	}
        this.incidentStatusId = value;
    }

    /**
     * Gets the value of the incidentTypeId property.
     * 
     */
    public int getIncidentTypeId() {
        return incidentTypeId;
    }

    /**
     * Sets the value of the incidentTypeId property.
     * 
     */
    public void setIncidentTypeId(int value)
    {
    	if (this.incidentTypeId != value)
    	{
    		this.dataChanged = true;
    	}
        this.incidentTypeId = value;
    }

    /**
     * Gets the value of the openerId property.
     * 
     */
    public int getOpenerId() {
        return openerId;
    }

    /**
     * Sets the value of the openerId property.
     * 
     */
    public void setOpenerId(int value)
    {
    	if (this.openerId != value)
    	{
    		this.dataChanged = true;
    	}
        this.openerId = value;
    }


    /**
     * Gets the value of the detectedReleaseId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDetectedReleaseId() {
        return detectedReleaseId;
    }

    /**
     * Sets the value of the detectedReleaseId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDetectedReleaseId(Integer value)
    {
    	if (hasChanged(this.detectedReleaseId, value))
    	{
    		this.dataChanged = true;
    	}
        this.detectedReleaseId = value;
    }

    /**
     * Gets the value of the resolvedReleaseId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getResolvedReleaseId() {
        return resolvedReleaseId;
    }

    /**
     * Sets the value of the resolvedReleaseId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setResolvedReleaseId(Integer value)
    {
    	if (hasChanged(this.resolvedReleaseId, value))
    	{
    		this.dataChanged = true;
    	}
        this.resolvedReleaseId = value;
    }

    /**
     * Gets the value of the verifiedReleaseId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getVerifiedReleaseId() {
        return verifiedReleaseId;
    }

    /**
     * Sets the value of the verifiedReleaseId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setVerifiedReleaseId(Integer value)
    {
    	if (hasChanged(this.verifiedReleaseId, value))
    	{
    		this.dataChanged = true;
    	}
        this.verifiedReleaseId = value;
    }

    /**
     * Gets the value of the startDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Sets the value of the startDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStartDate(Date value)
    {
    	if (hasChanged(this.startDate, value))
    	{
    		this.dataChanged = true;
    	}
        this.startDate = value;
    }

    /**
     * Gets the value of the closedDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public Date getClosedDate() {
        return closedDate;
    }

    /**
     * Sets the value of the closedDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setClosedDate(Date value)
    {
    	if (hasChanged(this.closedDate, value))
    	{
    		this.dataChanged = true;
    	}
        this.closedDate = value;
    }

    /**
     * Gets the value of the completionPercent property.
     * 
     */
    public int getCompletionPercent() {
        return completionPercent;
    }

    /**
     * Gets the value of the estimatedEffort property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getEstimatedEffort() {
        return estimatedEffort;
    }

    /**
     * Sets the value of the estimatedEffort property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setEstimatedEffort(Integer value)
    {
    	if (hasChanged(this.estimatedEffort, value))
    	{
    		this.dataChanged = true;
    	}
        this.estimatedEffort = value;
    }

    /**
     * Gets the value of the actualEffort property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getActualEffort() {
        return actualEffort;
    }

    /**
     * Sets the value of the actualEffort property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setActualEffort(Integer value)
    {
    	if (hasChanged(this.actualEffort, value))
    	{
    		this.dataChanged = true;
    	}
        this.actualEffort = value;
    }

 
    /**
     * Gets the value of the priorityName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPriorityName() {
        return priorityName;
    }

     /**
     * Gets the value of the severityName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeverityName() {
        return severityName;
    }

    /**
     * Gets the value of the incidentStatusName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIncidentStatusName() {
        return incidentStatusName;
    }

 
    /**
     * Gets the value of the incidentTypeName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIncidentTypeName() {
        return incidentTypeName;
    }

     /**
     * Gets the value of the openerName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOpenerName() {
        return openerName;
    }

    /**
     * Sets the value of the openerName property.
     * 
     */
    public void setOpenerName(String value)
    {
        this.openerName = value;
    }
    
    /**
     * Gets the value of the ownerName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOwnerName() {
        return ownerName;
    }

    /**
     * Gets the value of the projectName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * Gets the value of the detectedReleaseVersionNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDetectedReleaseVersionNumber() {
        return detectedReleaseVersionNumber;
    }

     /**
     * Gets the value of the resolvedReleaseVersionNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResolvedReleaseVersionNumber() {
        return resolvedReleaseVersionNumber;
    }

    /**
     * Gets the value of the verifiedReleaseVersionNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVerifiedReleaseVersionNumber() {
        return verifiedReleaseVersionNumber;
    }

    /**
     * Gets the value of the incidentStatusOpenStatus property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIncidentStatusOpenStatus() {
        return incidentStatusOpenStatus;
    }
    
	/**
	 * @return the resolutions
	 */
	public List<IncidentResolution> getResolutions()
	{
		return this.resolutions;
	}

	/**
	 * @return the remainingEffort
	 */
	public Integer getRemainingEffort()
	{
		return this.remainingEffort;
	}

	/**
	 * @param remainingEffort the remainingEffort to set
	 */
	public void setRemainingEffort(Integer remainingEffort)
	{
		this.remainingEffort = remainingEffort;
	}

	/**
	 * @return the projectedEffort
	 */
	public Integer getProjectedEffort()
	{
		return this.projectedEffort;
	}

	public ArrayList<Integer> getTestRunStepIds() {
		return testRunStepIds;
	}

	public void setTestRunStepIds(ArrayList<Integer> testRunStepIds) {
		this.testRunStepIds = testRunStepIds;
	}

	public ArrayList<Integer> getComponentIds() {
		return componentIds;
	}

	public void setComponentIds(ArrayList<Integer> componentIds) {
		this.componentIds = componentIds;
	}

	public void setCompletionPercent(int completionPercent) {
		this.completionPercent = completionPercent;
	}
}
