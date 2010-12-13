package com.inflectra.spirateam.mylyn.core.internal.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import com.inflectra.spirateam.mylyn.core.internal.ArtifactType;
import com.inflectra.spirateam.mylyn.core.internal.SpiraTeamUtil;
import com.inflectra.spirateam.mylyn.core.internal.services.SpiraImportExport;
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
    	//Set the various member variables
    	this.dataChanged = false;
        this.artifactId = remoteTask.getTaskId().getValue();
        this.projectId = remoteTask.getProjectId().getValue();
        this.ownerId = remoteTask.getOwnerId().getValue();
        this.releaseId = remoteTask.getReleaseId().getValue();
        this.creatorId = remoteTask.getCreatorId().getValue();
        this.name = remoteTask.getName().getValue();
        this.description = remoteTask.getDescription().getValue();
        this.creationDate = SpiraTeamUtil.convertDatesXml2Java(remoteTask.getCreationDate());
        this.lastUpdateDate = SpiraTeamUtil.convertDatesXml2Java(remoteTask.getLastUpdateDate());
        this.taskStatusId = remoteTask.getTaskStatusId();
        this.requirementId = remoteTask.getRequirementId().getValue();
        this.releaseId = remoteTask.getReleaseId().getValue();
        this.taskPriorityId = remoteTask.getTaskPriorityId().getValue();
        this.startDate = SpiraTeamUtil.convertDatesXml2Java(remoteTask.getStartDate().getValue());
        this.endDate = SpiraTeamUtil.convertDatesXml2Java(remoteTask.getEndDate().getValue());
        this.completionPercent = remoteTask.getCompletionPercent();
        this.estimatedEffort = remoteTask.getEstimatedEffort().getValue();
        this.actualEffort = remoteTask.getActualEffort().getValue();
        this.remainingEffort = remoteTask.getRemainingEffort().getValue();
        this.projectedEffort = remoteTask.getProjectedEffort().getValue();
        this.taskStatusName = remoteTask.getTaskStatusName().getValue();
        this.ownerName = remoteTask.getOwnerName().getValue();
        this.taskPriorityName = remoteTask.getTaskPriorityName().getValue();
        this.projectName = remoteTask.getProjectName().getValue();
        this.releaseVersionNumber = remoteTask.getReleaseVersionNumber().getValue();
        
        //Now the custom properties
        this.text01 = remoteTask.getText01().getValue();
        this.text02 = remoteTask.getText02().getValue();
        this.text03 = remoteTask.getText03().getValue();
        this.text04 = remoteTask.getText04().getValue();
        this.text05 = remoteTask.getText05().getValue();
        this.text06 = remoteTask.getText06().getValue();
        this.text07 = remoteTask.getText07().getValue();
        this.text08 = remoteTask.getText08().getValue();
        this.text09 = remoteTask.getText09().getValue();
        this.text10 = remoteTask.getText10().getValue();
        this.list01 = remoteTask.getList01().getValue();
        this.list02 = remoteTask.getList02().getValue();
        this.list03 = remoteTask.getList03().getValue();
        this.list04 = remoteTask.getList04().getValue();
        this.list05 = remoteTask.getList05().getValue();
        this.list06 = remoteTask.getList06().getValue();
        this.list07 = remoteTask.getList07().getValue();
        this.list08 = remoteTask.getList08().getValue();
        this.list09 = remoteTask.getList09().getValue();
        this.list10 = remoteTask.getList10().getValue();
    }
    
    /**
     * Converts this object into its soap equivalent
     * @return
     */
    public RemoteTask toSoapObject()
    {
    	//Set the properties on the SOAP object
    	RemoteTask remoteTask = new RemoteTask();
    	remoteTask.setTaskId(SpiraImportExport.CreateJAXBInteger("TaskId", this.artifactId));
    	remoteTask.setProjectId(SpiraImportExport.CreateJAXBInteger("ProjectId", this.projectId));
    	remoteTask.setOwnerId(SpiraImportExport.CreateJAXBInteger("OwnerId", this.ownerId));
    	remoteTask.setReleaseId(SpiraImportExport.CreateJAXBInteger("ReleaseId", this.releaseId));
    	remoteTask.setCreatorId(SpiraImportExport.CreateJAXBInteger("Creator", this.creatorId));
    	remoteTask.setName(SpiraImportExport.CreateJAXBString("Name", this.name));
    	remoteTask.setDescription(SpiraImportExport.CreateJAXBString("Description", this.description));
    	remoteTask.setCreationDate(SpiraTeamUtil.convertDatesJava2Xml(this.creationDate));
    	remoteTask.setLastUpdateDate(SpiraTeamUtil.convertDatesJava2Xml(this.lastUpdateDate));
    	remoteTask.setTaskStatusId(this.taskStatusId);
    	remoteTask.setRequirementId(SpiraImportExport.CreateJAXBInteger("RequirementId", this.requirementId));
    	remoteTask.setReleaseId(SpiraImportExport.CreateJAXBInteger("ReleaseId", this.releaseId));
    	remoteTask.setTaskPriorityId(SpiraImportExport.CreateJAXBInteger("TaskPriorityId", this.taskPriorityId));
    	remoteTask.setStartDate(SpiraImportExport.CreateJAXBXMLGregorianCalendar("StartDate", SpiraTeamUtil.convertDatesJava2Xml(this.startDate)));
    	remoteTask.setEndDate(SpiraImportExport.CreateJAXBXMLGregorianCalendar("EndDate", SpiraTeamUtil.convertDatesJava2Xml(this.endDate)));
    	remoteTask.setEstimatedEffort(SpiraImportExport.CreateJAXBInteger("EstimatedEffort", this.estimatedEffort));
    	remoteTask.setActualEffort(SpiraImportExport.CreateJAXBInteger("ActualEffort", this.actualEffort));
    	remoteTask.setRemainingEffort(SpiraImportExport.CreateJAXBInteger("RemainingEffort", this.remainingEffort));
        
        //Now the custom properties
    	remoteTask.setText01(SpiraImportExport.CreateJAXBString("Text01", this.text01));
    	remoteTask.setText02(SpiraImportExport.CreateJAXBString("Text02", this.text02));
    	remoteTask.setText03(SpiraImportExport.CreateJAXBString("Text03", this.text03));
    	remoteTask.setText04(SpiraImportExport.CreateJAXBString("Text04", this.text04));
    	remoteTask.setText05(SpiraImportExport.CreateJAXBString("Text05", this.text05));
    	remoteTask.setText06(SpiraImportExport.CreateJAXBString("Text06", this.text06));
    	remoteTask.setText07(SpiraImportExport.CreateJAXBString("Text07", this.text07));
    	remoteTask.setText08(SpiraImportExport.CreateJAXBString("Text08", this.text08));
    	remoteTask.setText09(SpiraImportExport.CreateJAXBString("Text09", this.text09));
    	remoteTask.setText10(SpiraImportExport.CreateJAXBString("Text10", this.text10));
    	remoteTask.setList01(SpiraImportExport.CreateJAXBInteger("List01", this.list01));
    	remoteTask.setList02(SpiraImportExport.CreateJAXBInteger("List02", this.list02));
    	remoteTask.setList03(SpiraImportExport.CreateJAXBInteger("List03", this.list03));
    	remoteTask.setList04(SpiraImportExport.CreateJAXBInteger("List04", this.list04));
    	remoteTask.setList05(SpiraImportExport.CreateJAXBInteger("List05", this.list05));
    	remoteTask.setList06(SpiraImportExport.CreateJAXBInteger("List06", this.list06));
    	remoteTask.setList07(SpiraImportExport.CreateJAXBInteger("List07", this.list07));
    	remoteTask.setList08(SpiraImportExport.CreateJAXBInteger("List08", this.list08));
    	remoteTask.setList09(SpiraImportExport.CreateJAXBInteger("List09", this.list09));
    	remoteTask.setList10(SpiraImportExport.CreateJAXBInteger("List10", this.list10));
        
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
}
