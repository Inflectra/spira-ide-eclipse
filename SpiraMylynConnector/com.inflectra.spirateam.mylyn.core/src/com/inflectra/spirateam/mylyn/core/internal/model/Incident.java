package com.inflectra.spirateam.mylyn.core.internal.model;

import java.util.Date;
import javax.xml.datatype.XMLGregorianCalendar;

import com.inflectra.spirateam.mylyn.core.internal.ArtifactType;
import com.inflectra.spirateam.mylyn.core.internal.SpiraTeamUtil;
import com.inflectra.spirateam.mylyn.core.internal.services.SpiraImportExport;
import com.inflectra.spirateam.mylyn.core.internal.services.soap.RemoteIncident;
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
    protected Integer testRunStepId;
    protected Integer detectedReleaseId;
    protected Integer resolvedReleaseId;
    protected Integer verifiedReleaseId;
    protected Date startDate;
    protected Date closedDate;
    protected int completionPercent;
    protected Integer estimatedEffort;
    protected Integer actualEffort;
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
    	//Set the various member variables
        this.artifactId = remoteIncident.getIncidentId().getValue();
        this.projectId = remoteIncident.getProjectId().getValue();
        this.ownerId = remoteIncident.getOwnerId().getValue();
        this.name = remoteIncident.getName().getValue();
        this.description = remoteIncident.getDescription().getValue();
        this.creationDate = SpiraTeamUtil.convertDatesXml2Java(remoteIncident.getCreationDate().getValue());
        this.lastUpdateDate = SpiraTeamUtil.convertDatesXml2Java(remoteIncident.getLastUpdateDate());
        this.priorityId = remoteIncident.getPriorityId().getValue();
        this.severityId = remoteIncident.getSeverityId().getValue();
        this.incidentStatusId = remoteIncident.getIncidentStatusId().getValue();
        this.incidentTypeId = remoteIncident.getIncidentTypeId().getValue();
        this.openerId = remoteIncident.getOpenerId().getValue();
        this.testRunStepId = remoteIncident.getTestRunStepId().getValue();
        this.detectedReleaseId = remoteIncident.getDetectedReleaseId().getValue();
        this.resolvedReleaseId = remoteIncident.getResolvedReleaseId().getValue();
        this.verifiedReleaseId = remoteIncident.getVerifiedReleaseId().getValue();
        this.startDate = SpiraTeamUtil.convertDatesXml2Java(remoteIncident.getStartDate().getValue());
        this.closedDate = SpiraTeamUtil.convertDatesXml2Java(remoteIncident.getClosedDate().getValue());
        this.completionPercent = remoteIncident.getCompletionPercent();
        this.estimatedEffort = remoteIncident.getEstimatedEffort().getValue();
        this.actualEffort = remoteIncident.getActualEffort().getValue();
        this.priorityName = remoteIncident.getPriorityName().getValue();
        this.severityName = remoteIncident.getSeverityName().getValue();
        this.incidentStatusName = remoteIncident.getIncidentStatusName().getValue();
        this.incidentTypeName = remoteIncident.getIncidentTypeName().getValue();
        this.openerName = remoteIncident.getOpenerName().getValue();
        this.ownerName = remoteIncident.getOwnerName().getValue();
        this.projectName = remoteIncident.getProjectName().getValue();
        this.detectedReleaseVersionNumber = remoteIncident.getDetectedReleaseVersionNumber().getValue();
        this.resolvedReleaseVersionNumber = remoteIncident.getResolvedReleaseVersionNumber().getValue();
        this.verifiedReleaseVersionNumber = remoteIncident.getVerifiedReleaseVersionNumber().getValue();
        this.incidentStatusOpenStatus = remoteIncident.getIncidentStatusOpenStatus().getValue();
        
        //Now the custom properties
        this.text01 = remoteIncident.getText01().getValue();
        this.text02 = remoteIncident.getText02().getValue();
        this.text03 = remoteIncident.getText03().getValue();
        this.text04 = remoteIncident.getText04().getValue();
        this.text05 = remoteIncident.getText05().getValue();
        this.text06 = remoteIncident.getText06().getValue();
        this.text07 = remoteIncident.getText07().getValue();
        this.text08 = remoteIncident.getText08().getValue();
        this.text09 = remoteIncident.getText09().getValue();
        this.text10 = remoteIncident.getText10().getValue();
        this.list01 = remoteIncident.getList01().getValue();
        this.list02 = remoteIncident.getList02().getValue();
        this.list03 = remoteIncident.getList03().getValue();
        this.list04 = remoteIncident.getList04().getValue();
        this.list05 = remoteIncident.getList05().getValue();
        this.list06 = remoteIncident.getList06().getValue();
        this.list07 = remoteIncident.getList07().getValue();
        this.list08 = remoteIncident.getList08().getValue();
        this.list09 = remoteIncident.getList09().getValue();
        this.list10 = remoteIncident.getList10().getValue();
    }
    
    /**
     * Converts this object into its soap equivalent
     * @return
     */
    public RemoteIncident toSoapObject()
    {
    	//Set the properties on the SOAP object
    	RemoteIncident remoteIncident = new RemoteIncident();
    	remoteIncident.setIncidentId(SpiraImportExport.CreateJAXBInteger("IncidentId",this.artifactId));
    	remoteIncident.setProjectId(SpiraImportExport.CreateJAXBInteger("ProjectId",this.projectId));
    	remoteIncident.setOwnerId(SpiraImportExport.CreateJAXBInteger("OwnerId",this.ownerId));
    	remoteIncident.setName(SpiraImportExport.CreateJAXBString("Name", this.name));
    	remoteIncident.setDescription(SpiraImportExport.CreateJAXBString("Description", this.description));
    	remoteIncident.setCreationDate(SpiraImportExport.CreateJAXBXMLGregorianCalendar("CreationDate", SpiraTeamUtil.convertDatesJava2Xml(this.creationDate)));
    	remoteIncident.setLastUpdateDate(SpiraTeamUtil.convertDatesJava2Xml(this.lastUpdateDate));

    	remoteIncident.setPriorityId(SpiraImportExport.CreateJAXBInteger("PriorityId",this.priorityId));
    	remoteIncident.setSeverityId(SpiraImportExport.CreateJAXBInteger("SeverityId",this.severityId));
    	remoteIncident.setIncidentStatusId(SpiraImportExport.CreateJAXBInteger("IncidentStatusId",this.incidentStatusId));
    	remoteIncident.setIncidentTypeId(SpiraImportExport.CreateJAXBInteger("IncidentTypeId",this.incidentTypeId));
    	remoteIncident.setOpenerId(SpiraImportExport.CreateJAXBInteger("OpenerId",this.openerId));
    	remoteIncident.setTestRunStepId(SpiraImportExport.CreateJAXBInteger("TestRunStepId",this.testRunStepId));
    	remoteIncident.setDetectedReleaseId(SpiraImportExport.CreateJAXBInteger("DetectedReleaseId",this.detectedReleaseId));
    	remoteIncident.setResolvedReleaseId(SpiraImportExport.CreateJAXBInteger("ResolvedReleaseId",this.resolvedReleaseId));
    	remoteIncident.setVerifiedReleaseId(SpiraImportExport.CreateJAXBInteger("VerifiedReleaseId",this.verifiedReleaseId));
    	remoteIncident.setStartDate(SpiraImportExport.CreateJAXBXMLGregorianCalendar("StartDate", SpiraTeamUtil.convertDatesJava2Xml(this.startDate)));
    	remoteIncident.setClosedDate(SpiraImportExport.CreateJAXBXMLGregorianCalendar("ClosedDate", SpiraTeamUtil.convertDatesJava2Xml(this.closedDate)));
    	remoteIncident.setCompletionPercent(this.completionPercent);
    	remoteIncident.setEstimatedEffort(SpiraImportExport.CreateJAXBInteger("EstimatedEffort",this.estimatedEffort));
    	remoteIncident.setActualEffort(SpiraImportExport.CreateJAXBInteger("ActualEffort",this.actualEffort));
        
        //Now the custom properties
    	remoteIncident.setText01(SpiraImportExport.CreateJAXBString("Text01", this.text01));
    	remoteIncident.setText02(SpiraImportExport.CreateJAXBString("Text02", this.text02));
    	remoteIncident.setText03(SpiraImportExport.CreateJAXBString("Text03", this.text03));
    	remoteIncident.setText04(SpiraImportExport.CreateJAXBString("Text04", this.text04));
    	remoteIncident.setText05(SpiraImportExport.CreateJAXBString("Text05", this.text05));
    	remoteIncident.setText06(SpiraImportExport.CreateJAXBString("Text06", this.text06));
    	remoteIncident.setText07(SpiraImportExport.CreateJAXBString("Text07", this.text07));
    	remoteIncident.setText08(SpiraImportExport.CreateJAXBString("Text08", this.text08));
    	remoteIncident.setText09(SpiraImportExport.CreateJAXBString("Text09", this.text09));
    	remoteIncident.setText10(SpiraImportExport.CreateJAXBString("Text10", this.text10));
    	remoteIncident.setList01(SpiraImportExport.CreateJAXBInteger("List01",this.list01));
    	remoteIncident.setList02(SpiraImportExport.CreateJAXBInteger("List02",this.list02));
    	remoteIncident.setList03(SpiraImportExport.CreateJAXBInteger("List03",this.list03));
    	remoteIncident.setList04(SpiraImportExport.CreateJAXBInteger("List04",this.list04));
    	remoteIncident.setList05(SpiraImportExport.CreateJAXBInteger("List05",this.list05));
    	remoteIncident.setList06(SpiraImportExport.CreateJAXBInteger("List06",this.list06));
    	remoteIncident.setList07(SpiraImportExport.CreateJAXBInteger("List07",this.list07));
    	remoteIncident.setList08(SpiraImportExport.CreateJAXBInteger("List08",this.list08));
    	remoteIncident.setList09(SpiraImportExport.CreateJAXBInteger("List09",this.list09));
    	remoteIncident.setList10(SpiraImportExport.CreateJAXBInteger("List10",this.list10));
        
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
     * Gets the value of the testRunStepId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTestRunStepId() {
        return testRunStepId;
    }

    /**
     * Sets the value of the testRunStepId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTestRunStepId(Integer value)
    {
    	if (hasChanged(this.testRunStepId, value))
    	{
    		this.dataChanged = true;
    	}
        this.testRunStepId = value;
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
     * Sets the value of the completionPercent property.
     * 
     */
    public void setCompletionPercent(int value)
    {
    	if (this.completionPercent != value)
    	{
    		this.dataChanged = true;
    	}
        this.completionPercent = value;
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
}
