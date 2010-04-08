package com.inflectra.spirateam.mylyn.core.internal.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.datatype.XMLGregorianCalendar;

import com.inflectra.spirateam.mylyn.core.internal.SpiraTeamCorePlugin;
import com.inflectra.spirateam.mylyn.core.internal.SpiraTeamUtil;
import com.inflectra.spirateam.mylyn.core.internal.services.soap.RemoteRequirement;
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
