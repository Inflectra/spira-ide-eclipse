package com.inflectra.spirateam.mylyn.core.internal.model;

import java.util.Date;
import javax.xml.datatype.XMLGregorianCalendar;

import com.inflectra.spirateam.mylyn.core.internal.SpiraTeamCorePlugin;
import com.inflectra.spirateam.mylyn.core.internal.SpiraTeamUtil;
import com.inflectra.spirateam.mylyn.core.internal.services.soap.RemoteTask;
/**
 * Represents a single task in SpiraTeam
 *
 * @author Inflectra Corporation
 */
public class Task extends Artifact
{
    protected int taskStatusId;
    protected Integer requirementId;
    protected Integer releaseId;
    protected Integer taskPriorityId;
    protected Date startDate;
    protected Date endDate;
    protected int completionPercent;
    protected Integer estimatedEffort;
    protected Integer actualEffort;
    protected String taskStatusName;
    protected String ownerName;
    protected String taskPriorityName;
    protected String projectName;
    protected String releaseVersionNumber;
    
    /**
     * Creates a task based on its equivalent SOAP object
     * @param remoteTask The SOAP task
     */
    public Task(RemoteTask remoteTask)
    {
    	//Set the various member variables
        this.artifactId = remoteTask.getTaskId();
        this.projectId = remoteTask.getProjectId();
        this.ownerId = remoteTask.getOwnerId();
        this.releaseId = remoteTask.getReleaseId();
        this.name = remoteTask.getName();
        this.description = remoteTask.getDescription();
        this.creationDate = SpiraTeamUtil.ConvertDatesXml2Java(remoteTask.getCreationDate());
        this.lastUpdateDate = SpiraTeamUtil.ConvertDatesXml2Java(remoteTask.getLastUpdateDate());
        this.taskStatusId = remoteTask.getTaskStatusId();
        this.requirementId = remoteTask.getRequirementId();
        this.releaseId = remoteTask.getReleaseId();
        this.taskPriorityId = remoteTask.getTaskPriorityId();
        this.startDate = SpiraTeamUtil.ConvertDatesXml2Java(remoteTask.getStartDate());
        this.endDate = SpiraTeamUtil.ConvertDatesXml2Java(remoteTask.getEndDate());
        this.completionPercent = remoteTask.getCompletionPercent();
        this.estimatedEffort = remoteTask.getEstimatedEffort();
        this.actualEffort = remoteTask.getActualEffort();
        this.taskStatusName = remoteTask.getTaskStatusName();
        this.ownerName = remoteTask.getOwnerName();
        this.taskPriorityName = remoteTask.getTaskPriorityName();
        this.projectName = remoteTask.getProjectName();
        this.releaseVersionNumber = remoteTask.getReleaseVersionNumber();    
        
        //Now the custom properties
        this.text01 = remoteTask.getText01();
        this.text02 = remoteTask.getText02();
        this.text03 = remoteTask.getText03();
        this.text04 = remoteTask.getText04();
        this.text05 = remoteTask.getText05();
        this.text06 = remoteTask.getText06();
        this.text07 = remoteTask.getText07();
        this.text08 = remoteTask.getText08();
        this.text09 = remoteTask.getText09();
        this.text10 = remoteTask.getText10();
        this.list01 = remoteTask.getList01();
        this.list02 = remoteTask.getList02();
        this.list03 = remoteTask.getList03();
        this.list04 = remoteTask.getList04();
        this.list05 = remoteTask.getList05();
        this.list06 = remoteTask.getList06();
        this.list07 = remoteTask.getList07();
        this.list08 = remoteTask.getList08();
        this.list09 = remoteTask.getList09();
        this.list10 = remoteTask.getList10();
    }
    
    /**
     * Gets the value of the artifact key (format is TK<task-id>)
     * @return The prefixed task ID
     */
    @Override
    public String getArtifactKey()
    {
    	return SpiraTeamCorePlugin.ARTIFACT_PREFIX_TASK + this.artifactId;
    }
    
    /**
     * Gets the value of the taskStatusId property.
     * 
     */
    public int getTaskStatusId() {
        return taskStatusId;
    }

    /**
     * Sets the value of the taskStatusId property.
     * 
     */
    public void setTaskStatusId(int value) {
        this.taskStatusId = value;
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
     * Gets the value of the taskPriorityId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTaskPriorityId() {
        return taskPriorityId;
    }

    /**
     * Sets the value of the taskPriorityId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTaskPriorityId(Integer value) {
        this.taskPriorityId = value;
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
     * Gets the value of the endDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Sets the value of the endDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEndDate(Date value) {
        this.endDate = value;
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
     * Gets the value of the taskStatusName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaskStatusName() {
        return taskStatusName;
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
     * Gets the value of the taskPriorityName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaskPriorityName() {
        return taskPriorityName;
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
}
