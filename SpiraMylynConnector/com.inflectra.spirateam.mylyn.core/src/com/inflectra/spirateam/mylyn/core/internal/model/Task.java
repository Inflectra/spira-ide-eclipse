package com.inflectra.spirateam.mylyn.core.internal.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import com.inflectra.spirateam.mylyn.core.internal.ArtifactType;
import com.inflectra.spirateam.mylyn.core.internal.SpiraTeamUtil;
import com.inflectra.spirateam.mylyn.core.internal.rest.RemoteTask;
/**
 * Represents a single task in SpiraTeam
 *
 * @author Inflectra Corporation
 */
public class Task extends Artifact
{
    protected int taskStatusId;
    protected int taskTypeId;
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
    protected Integer remainingEffort;
    protected Integer projectedEffort;
    protected int creatorId;
    
    //Contains the collection of comments
    protected List<TaskComment> comments = new ArrayList<TaskComment>();
    
    public enum Key
    {
		//Task-specific attributes
    	TYPE("task.type"),
		STATUS_ID("task.statusId"),
		REQUIREMENT_ID("task.requirementId"),
		RELEASE_ID("task.releaseId"),
		PRIORITY_ID("task.priorityId"),
		CREATOR_ID("task.creatorId"),
		START_DATE("task.startDate"),
		END_DATE("task.endDate"),
		COMPLETION_PERCENTAGE("task.completionPercentage"),
		ESTIMATED_EFFORT("task.estimatedEffort"),
		ACTUAL_EFFORT("task.actualEffort"),
		REMAINING_EFFORT("task.remainingEffort"),
		PROJECTED_EFFORT("task.projectedEffort"),
		REQUIREMENT_NAME("task.requirementName"),
		COMMENT("task.comment");

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
    	//Populate the cross-artifact type properties
    	PopulateGeneralProperties(remoteTask);

    	//Set the various member variables
    	this.dataChanged = false;
        this.artifactId = remoteTask.TaskId;
        this.ownerId = remoteTask.OwnerId;
        this.releaseId = remoteTask.ReleaseId;
        this.creatorId = remoteTask.CreatorId;
        this.name = remoteTask.Name;
        this.description = remoteTask.Description;
        this.creationDate = SpiraTeamUtil.convertDatesToLocal(remoteTask.CreationDate);
        this.lastUpdateDate = SpiraTeamUtil.convertDatesToLocal(remoteTask.LastUpdateDate);
        this.taskStatusId = remoteTask.TaskStatusId;
        this.taskTypeId = remoteTask.TaskTypeId;
        this.requirementId = remoteTask.RequirementId;
        this.releaseId = remoteTask.ReleaseId;
        this.taskPriorityId = remoteTask.TaskPriorityId;
        this.startDate = SpiraTeamUtil.convertDatesToLocal(remoteTask.StartDate);
        this.endDate = SpiraTeamUtil.convertDatesToLocal(remoteTask.EndDate);
        this.completionPercent = remoteTask.CompletionPercent;
        this.estimatedEffort = remoteTask.EstimatedEffort;
        this.actualEffort = remoteTask.ActualEffort;
        this.remainingEffort = remoteTask.RemainingEffort;
        this.projectedEffort = remoteTask.ProjectedEffort;
        this.taskStatusName = remoteTask.TaskStatusName;
        this.ownerName = remoteTask.OwnerName;
        this.taskPriorityName = remoteTask.TaskPriorityName;
        this.projectName = remoteTask.ProjectName;
        this.releaseVersionNumber = remoteTask.ReleaseVersionNumber;
        this.requirementName = remoteTask.RequirementName;
    }
    
    /**
     * Converts this object into its soap equivalent
     * @return
     */
    public RemoteTask toSoapObject()
    {
    	//Set the properties on the SOAP object
    	RemoteTask remoteTask = new RemoteTask();

    	//First the artifact base properties
    	ExtractGeneralProperties(remoteTask);

    	//Next the task-specific ones
    	remoteTask.TaskId = this.artifactId;
    	remoteTask.OwnerId = this.ownerId;
    	remoteTask.ReleaseId = this.releaseId;
    	remoteTask.CreatorId = this.creatorId;
    	remoteTask.Name = this.name;
    	remoteTask.Description = this.description;
    	remoteTask.CreationDate = this.creationDate;
    	remoteTask.LastUpdateDate = this.lastUpdateDate;
    	remoteTask.TaskStatusId = this.taskStatusId;
    	remoteTask.RequirementId = this.requirementId;
    	remoteTask.ReleaseId = this.releaseId;
    	remoteTask.TaskPriorityId = this.taskPriorityId;
    	remoteTask.StartDate = SpiraTeamUtil.convertDatesToUtc(this.startDate);
    	remoteTask.EndDate = SpiraTeamUtil.convertDatesToUtc(this.endDate);
    	remoteTask.EstimatedEffort = this.estimatedEffort;
    	remoteTask.ActualEffort = this.actualEffort;
    	remoteTask.RemainingEffort = this.remainingEffort;
            	
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
    
	/**
	 * @return the comments
	 */
	public List<TaskComment> getComments()
	{
		return this.comments;
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

	/**
	 * @return the creatorId
	 */
	public int getCreatorId()
	{
		return this.creatorId;
	}

	/**
	 * @param creatorId the creatorId to set
	 */
	public void setCreatorId(int creatorId)
	{
		this.creatorId = creatorId;
	}

	public int getTaskTypeId() {
		return taskTypeId;
	}

	public void setTaskTypeId(int taskTypeId) {
		this.taskTypeId = taskTypeId;
	}
}
