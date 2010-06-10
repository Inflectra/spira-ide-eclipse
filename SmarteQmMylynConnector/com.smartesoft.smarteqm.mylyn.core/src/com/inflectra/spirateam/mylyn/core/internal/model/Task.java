package com.inflectra.spirateam.mylyn.core.internal.model;

import java.util.Date;
import javax.xml.datatype.XMLGregorianCalendar;

import com.inflectra.spirateam.mylyn.core.internal.ArtifactType;
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
    protected String requirementName;
    
    public enum Key
    {
		//Task-specific attributes
    	TYPE("task.type"),
		STATUS_ID("task.statusId"),
		REQUIREMENT_ID("task.requirementId"),
		RELEASE_ID("task.releaseId"),
		PRIORITY_ID("task.priorityId"),
		START_DATE("task.startDate"),
		END_DATE("task.endDate"),
		COMPLETION_PERCENTAGE("task.completionPercentage"),
		ESTIMATED_EFFORT("task.estimatedEffort"),
		ACTUAL_EFFORT("task.actualEffort"),
		REQUIREMENT_NAME("task.requirementName");

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
     * Creates a task based on its equivalent SOAP object
     * @param remoteTask The SOAP task
     */
    public Task(RemoteTask remoteTask)
    {
    	//Set the various member variables
    	this.dataChanged = false;
        this.artifactId = remoteTask.getTaskId();
        this.projectId = remoteTask.getProjectId();
        this.ownerId = remoteTask.getOwnerId();
        this.releaseId = remoteTask.getReleaseId();
        this.name = remoteTask.getName();
        this.description = remoteTask.getDescription();
        this.creationDate = SpiraTeamUtil.convertDatesXml2Java(remoteTask.getCreationDate());
        this.lastUpdateDate = SpiraTeamUtil.convertDatesXml2Java(remoteTask.getLastUpdateDate());
        this.taskStatusId = remoteTask.getTaskStatusId();
        this.requirementId = remoteTask.getRequirementId();
        this.releaseId = remoteTask.getReleaseId();
        this.taskPriorityId = remoteTask.getTaskPriorityId();
        this.startDate = SpiraTeamUtil.convertDatesXml2Java(remoteTask.getStartDate());
        this.endDate = SpiraTeamUtil.convertDatesXml2Java(remoteTask.getEndDate());
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
     * Converts this object into its soap equivalent
     * @return
     */
    public RemoteTask toSoapObject()
    {
    	//Set the properties on the SOAP object
    	RemoteTask remoteTask = new RemoteTask();
    	remoteTask.setTaskId(this.artifactId);
    	remoteTask.setProjectId(this.projectId);
    	remoteTask.setOwnerId(this.ownerId);
    	remoteTask.setReleaseId(this.releaseId);
    	remoteTask.setName(this.name);
    	remoteTask.setDescription(this.description);
    	remoteTask.setCreationDate(SpiraTeamUtil.convertDatesJava2Xml(this.creationDate));
    	remoteTask.setLastUpdateDate(SpiraTeamUtil.convertDatesJava2Xml(this.lastUpdateDate));
    	remoteTask.setTaskStatusId(this.taskStatusId);
    	remoteTask.setRequirementId(this.requirementId);
    	remoteTask.setReleaseId(this.releaseId);
    	remoteTask.setTaskPriorityId(this.taskPriorityId);
    	remoteTask.setStartDate(SpiraTeamUtil.convertDatesJava2Xml(this.startDate));
    	remoteTask.setEndDate(SpiraTeamUtil.convertDatesJava2Xml(this.endDate));
    	remoteTask.setCompletionPercent(this.completionPercent);
    	remoteTask.setEstimatedEffort(this.estimatedEffort);
    	remoteTask.setActualEffort(this.actualEffort);
        
        //Now the custom properties
    	remoteTask.setText01(this.text01);
    	remoteTask.setText02(this.text02);
    	remoteTask.setText03(this.text03);
    	remoteTask.setText04(this.text04);
    	remoteTask.setText05(this.text05);
    	remoteTask.setText06(this.text06);
    	remoteTask.setText07(this.text07);
    	remoteTask.setText08(this.text08);
    	remoteTask.setText09(this.text09);
    	remoteTask.setText10(this.text10);
    	remoteTask.setList01(this.list01);
    	remoteTask.setList02(this.list02);
    	remoteTask.setList03(this.list03);
    	remoteTask.setList04(this.list04);
    	remoteTask.setList05(this.list05);
    	remoteTask.setList06(this.list06);
    	remoteTask.setList07(this.list07);
    	remoteTask.setList08(this.list08);
    	remoteTask.setList09(this.list09);
    	remoteTask.setList10(this.list10);
        
        return remoteTask;
    }
    
    @Override
    public ArtifactType getArtifactType()
    {
    	return ArtifactType.TASK;
    }
    
    /**
     * Gets the value of the artifact key (format is TK<task-id>)
     * @return The prefixed task ID
     */
    @Override
    public String getArtifactKey()
    {
    	return getArtifactType().getPrefix() + this.artifactId;
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
    public void setTaskStatusId(int value)
    {
    	if (this.taskStatusId != value)
    	{
    		this.dataChanged = true;
    	}
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
    public void setRequirementId(Integer value)
    {
    	if (this.requirementId != value)
    	{
    		this.dataChanged = true;
    	}
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
    public void setReleaseId(Integer value)
    {
    	if (hasChanged(this.releaseId, value))
    	{
    		this.dataChanged = true;
    	}
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
    public void setTaskPriorityId(Integer value)
    {
    	if (hasChanged(this.taskPriorityId, value))
    	{
    		this.dataChanged = true;
    	}
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
    public void setStartDate(Date value)
    {
    	if (hasChanged(this.startDate, value))
    	{
    		this.dataChanged = true;
    	}
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
    public void setEndDate(Date value)
    {
    	if (hasChanged(this.endDate, value))
    	{
    		this.dataChanged = true;
    	}
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
    
    public String getRequirementName()
    {
    	return this.requirementName;
    }
    public void setRequirementName(String requirementName)
    {
    	this.requirementName = requirementName;
    }
}
