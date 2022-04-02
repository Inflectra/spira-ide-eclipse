package com.inflectra.spirateam.mylyn.core.internal.rest;

public class RemoteTaskType {
    /// <summary>
    /// The id of the task type
    /// </summary>
    public int TaskTypeId;

    /// <summary>
    /// The name of the task type
    /// </summary>
    public String Name;

    /// <summary>
    /// The id of the workflow the task type is associated with, for the current project
    /// </summary>
	public Integer WorkflowId;

    /// <summary>
    /// Is this an active task type
    /// </summary>
    public boolean IsActive;

    /// <summary>
    /// The display position of this type
    /// </summary>
    public int Position;

    /// <summary>
    /// Is this the default task type
    /// </summary>
    public boolean IsDefault;

    /// <summary>
    /// Is this a code review task
    /// </summary>
    public boolean IsCodeReview;
    
    /// <summary>
    /// Is this a pull request task
    /// </summary>
    public boolean IsPullRequest;
}
