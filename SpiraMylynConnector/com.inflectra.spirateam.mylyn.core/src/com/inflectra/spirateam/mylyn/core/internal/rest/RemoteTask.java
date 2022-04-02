package com.inflectra.spirateam.mylyn.core.internal.rest;

import java.util.Date;

public class RemoteTask extends RemoteArtifact {
    /// <summary>
    /// The id of the task
    /// </summary>
    public Integer TaskId;

    /// <summary>
    /// The id of the status of the task
    /// </summary>
    public int TaskStatusId;

    /// <summary>
    /// The id of the type of the task (null for default)
    /// </summary>
    public Integer TaskTypeId;

    /// <summary>
    /// The of the folder the task is stored in (null for root)
    /// </summary>
    public Integer TaskFolderId;

    /// <summary>
    /// The id of the parent requirement that the task belongs to
    /// </summary>
    public Integer RequirementId;

    /// <summary>
    /// The id of the release/iteration that the task is scheduled for
    /// </summary>
    public Integer ReleaseId;

    /// <summary>
    /// The id of the component that this task belongs to
    /// </summary>
    /// <remarks>Read-only</remarks>
    
    public Integer ComponentId;

    /// <summary>
    /// The id of the user that originally created the task
    /// </summary>
    /// <remarks>
    /// If no value is provided, the authenticated user is used instead
    /// </remarks>
    public Integer CreatorId;

    /// <summary>
    /// The id of the user that the task is assigned-to
    /// </summary>
    public Integer OwnerId;

    /// <summary>
    /// The id of the priority of the task
    /// </summary>
    public Integer TaskPriorityId;

    /// <summary>
    /// The name of the task
    /// </summary>
    public String Name;

    /// <summary>
    /// The detailed description of the task
    /// </summary>
    public String Description;

    /// <summary>
    /// The date/time that the task was originally created
    /// </summary>
    public Date CreationDate;

    /// <summary>
    /// The date/time that the task was last modified
    /// </summary>
    /// <remarks>
    /// This field needs to match the values retrieved to ensure data-concurrency
    /// </remarks>
    public Date LastUpdateDate;

    /// <summary>
    /// The scheduled start date for the task
    /// </summary>
    public Date StartDate;

    /// <summary>
    /// The scheduled end date for the task
    /// </summary>
    public Date EndDate;

    /// <summary>
    /// The completion percentage (value = 0-100) of the task as calculated in the system from the remaining effort
    /// vs. the original estimated effort.
    /// </summary>
    /// <remarks>Read-Only</remarks>
    
    public int CompletionPercent;

    /// <summary>
    /// The originally estimated effort (in minutes) of the task
    /// </summary>
    public Integer EstimatedEffort;

    /// <summary>
    /// The actual effort expended so far (in minutes) for the task
    /// </summary>
    public Integer ActualEffort;

    /// <summary>
    /// The effort remaining as reported by the developer
    /// </summary>
    public Integer RemainingEffort;

    /// <summary>
    /// The projected actual effort of the task when it is completed
    /// </summary>
    /// <remarks>Read-Only</remarks>
    
    public Integer ProjectedEffort;

    /// <summary>
    /// The display name of the status of the task
    /// </summary>
    
    public String TaskStatusName;

    /// <summary>
    /// The display name of the type of the task
    /// </summary>
    
    public String TaskTypeName;

    /// <summary>
    /// The display name of the user who the task is assigned-to
    /// </summary>
    /// <remarks>Read-Only</remarks>
    
    public String OwnerName;

    /// <summary>
    /// The display name of the priority of the task
    /// </summary>
    /// <remarks>Read-Only</remarks>
    
    public String TaskPriorityName;

    /// <summary>
    /// The display name of the project the task belongs to
    /// </summary>
    /// <remarks>Read-Only</remarks>
    
    public String ProjectName;

    /// <summary>
    /// The version number of the release/iteration the task is scheduled for
    /// </summary>
    /// <remarks>Read-Only</remarks>
    
    public String ReleaseVersionNumber;

    /// <summary>
    /// The name of the requirement that the task is associated with
    /// </summary>
    /// <remarks>Read-Only</remarks>
    
    public String RequirementName;

    /// <summary>
    /// The risk that the task is associated with
    /// </summary>
    public Integer RiskId;
}
