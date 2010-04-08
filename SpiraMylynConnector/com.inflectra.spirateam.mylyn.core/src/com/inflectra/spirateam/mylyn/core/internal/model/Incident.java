package com.inflectra.spirateam.mylyn.core.internal.model;

import java.util.Date;
import javax.xml.datatype.XMLGregorianCalendar;

import com.inflectra.spirateam.mylyn.core.internal.SpiraTeamCorePlugin;
import com.inflectra.spirateam.mylyn.core.internal.SpiraTeamUtil;
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
    
    /**
     * Creates an incident based on its equivalent SOAP object
     * @param remoteIncident The SOAP incident
     */
    public Incident(RemoteIncident remoteIncident)
    {
    	//Set the various member variables
        this.artifactId = remoteIncident.getIncidentId();
        this.projectId = remoteIncident.getProjectId();
        this.ownerId = remoteIncident.getOwnerId();
        this.name = remoteIncident.getName();
        this.description = remoteIncident.getDescription();
        this.creationDate = SpiraTeamUtil.ConvertDatesXml2Java(remoteIncident.getCreationDate());
        this.lastUpdateDate = SpiraTeamUtil.ConvertDatesXml2Java(remoteIncident.getLastUpdateDate());
        this.priorityId = remoteIncident.getPriorityId();
        this.severityId = remoteIncident.getSeverityId();
        this.incidentStatusId = remoteIncident.getIncidentStatusId();
        this.incidentTypeId = remoteIncident.getIncidentStatusId();
        this.openerId = remoteIncident.getOpenerId();
        this.testRunStepId = remoteIncident.getTestRunStepId();
        this.detectedReleaseId = remoteIncident.getDetectedReleaseId();
        this.resolvedReleaseId = remoteIncident.getResolvedReleaseId();
        this.verifiedReleaseId = remoteIncident.getVerifiedReleaseId();
        this.startDate = SpiraTeamUtil.ConvertDatesXml2Java(remoteIncident.getStartDate());
        this.closedDate = SpiraTeamUtil.ConvertDatesXml2Java(remoteIncident.getClosedDate());
        this.completionPercent = remoteIncident.getCompletionPercent();
        this.estimatedEffort = remoteIncident.getEstimatedEffort();
        this.actualEffort = remoteIncident.getActualEffort();
        this.priorityName = remoteIncident.getPriorityName();
        this.severityName = remoteIncident.getSeverityName();
        this.incidentStatusName = remoteIncident.getIncidentStatusName();
        this.incidentTypeName = remoteIncident.getIncidentTypeName();
        this.openerName = remoteIncident.getOpenerName();
        this.ownerName = remoteIncident.getOwnerName();
        this.projectName = remoteIncident.getProjectName();
        this.detectedReleaseVersionNumber = remoteIncident.getDetectedReleaseVersionNumber();
        this.resolvedReleaseVersionNumber = remoteIncident.getResolvedReleaseVersionNumber();
        this.verifiedReleaseVersionNumber = remoteIncident.getVerifiedReleaseVersionNumber();
        this.incidentStatusOpenStatus = remoteIncident.isIncidentStatusOpenStatus();
        
        //Now the custom properties
        this.text01 = remoteIncident.getText01();
        this.text02 = remoteIncident.getText02();
        this.text03 = remoteIncident.getText03();
        this.text04 = remoteIncident.getText04();
        this.text05 = remoteIncident.getText05();
        this.text06 = remoteIncident.getText06();
        this.text07 = remoteIncident.getText07();
        this.text08 = remoteIncident.getText08();
        this.text09 = remoteIncident.getText09();
        this.text10 = remoteIncident.getText10();
        this.list01 = remoteIncident.getList01();
        this.list02 = remoteIncident.getList02();
        this.list03 = remoteIncident.getList03();
        this.list04 = remoteIncident.getList04();
        this.list05 = remoteIncident.getList05();
        this.list06 = remoteIncident.getList06();
        this.list07 = remoteIncident.getList07();
        this.list08 = remoteIncident.getList08();
        this.list09 = remoteIncident.getList09();
        this.list10 = remoteIncident.getList10();
    }
    
    /**
     * Gets the value of the artifact key (format is IN<incident-id>)
     * @return The prefixed incident ID
     */
    @Override
    public String getArtifactKey()
    {
    	return SpiraTeamCorePlugin.ARTIFACT_PREFIX_INCIDENT + this.artifactId;
    }
    
    /**
     * Gets the value of the priorityId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPriorityId() {
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
    public void setPriorityId(Integer value) {
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
    public Integer getSeverityId() {
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
    public void setSeverityId(Integer value) {
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
    public void setIncidentStatusId(int value) {
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
    public void setIncidentTypeId(int value) {
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
    public void setOpenerId(int value) {
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
    public void setTestRunStepId(Integer value) {
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
    public void setDetectedReleaseId(Integer value) {
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
    public void setResolvedReleaseId(Integer value) {
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
    public void setVerifiedReleaseId(Integer value) {
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
    public void setStartDate(Date value) {
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
    public void setClosedDate(Date value) {
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
    public void setCompletionPercent(int value) {
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
    public void setEstimatedEffort(Integer value) {
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
    public void setActualEffort(Integer value) {
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
