package com.inflectra.spirateam.mylyn.core.internal.rest;

import java.util.ArrayList;
import java.util.Date;

public class RemoteIncident extends RemoteArtifact {
    /// <summary>
    /// The id of the incident (integer)
    /// </summary>
    public Integer IncidentId;

    /// <summary>
    /// The id of the priority of the incident (integer)
    /// </summary>
    public Integer PriorityId;

    /// <summary>
    /// The id of the severity of the incident (integer)
    /// </summary>
    public Integer SeverityId;

    /// <summary>
    /// The id of the status of the incident (integer)
    /// </summary>
    /// <remarks>
    /// If no value is provided, the default status for the workflow is used
    /// </remarks>
    public Integer IncidentStatusId;

    /// <summary>
    /// The id of the type of the incident (integer)
    /// </summary>
    /// <remarks>
    /// If no value is provided, the default type for the project is used
    /// </remarks>
    public Integer IncidentTypeId;

    /// <summary>
    /// The id of the user who detected the incident (integer)
    /// </summary>
    /// <remarks>
    /// If a value is not provided, the authenticated user is used
    /// </remarks>
    public Integer OpenerId;

    /// <summary>
    /// The id of the user to the incident is assigned-to (integer)
    /// </summary>
    public Integer OwnerId;

    /// <summary>
    /// The id of the test run steps that the incident relates to (integer)
    /// </summary>
    public ArrayList<Integer> TestRunStepIds;

    /// <summary>
    /// The id of the release/iteration that the incident was detected in (integer)
    /// </summary>
    public Integer DetectedReleaseId;

    /// <summary>
    /// The id of the release/iteration that the incident will be fixed in (integer)
    /// </summary>
    public Integer ResolvedReleaseId;
    
    /// <summary>
    /// The id of the release/iteration that the incident was retested in (integer)
    /// </summary>
    public Integer VerifiedReleaseId;

    /// <summary>
    /// The list of components that this incident belongs to (array of integers)
    /// </summary>
    public ArrayList<Integer> ComponentIds;

    /// <summary>
    /// The name of the incident (string)
    /// </summary>
    public String Name;

    /// <summary>
    /// The description of the incident (string)
    /// </summary>
    public String Description;

    /// <summary>
    /// The date/time that the incident was originally created
    /// </summary>
    /// <remarks>
    /// If no value is provided, the current date/time on the server is used (date-time)
    /// </remarks>
    public Date CreationDate;

    /// <summary>
    /// The date that work started on the incident (date-time)
    /// </summary>
    public Date StartDate;

    /// <summary>
    /// The date that work is scheduled to finish on the incident (date-time)
    /// </summary>
    public Date EndDate;

    /// <summary>
    /// The date that the incident was closed (date-time)
    /// </summary>
    public Date ClosedDate;

    /// <summary>
    /// The completion percentage (value = 0-100) of the incident as calculated in the system from the remaining effort
    /// vs. the original estimated effort. (integer)
    /// </summary>
    /// <remarks>Read-Only</remarks>
    
    public int CompletionPercent;

    /// <summary>
    /// The estimated effort (in minutes) to resolve the incident (integer)
    /// </summary>
    public Integer EstimatedEffort;

    /// <summary>
    /// The actual effort (in minutes) it took to resolve the incident (integer)
    /// </summary>
    public Integer ActualEffort;

    /// <summary>
    /// The effort remaining as reported by the developer
    /// </summary>
    public Integer RemainingEffort;

    /// <summary>
    /// The projected actual effort of the incident when it is completed (integer)
    /// </summary>
    /// <remarks>Read-Only</remarks>
    
    public Integer ProjectedEffort;

    /// <summary>
    /// The date/time that the incident was last modified (date-time)
    /// </summary>
    public Date LastUpdateDate;

    /// <summary>
    /// The display name of the priority of the incident (string)
    /// </summary>
    
    public String PriorityName;

    /// <summary>
    /// The display name of the severity of the incident (string)
    /// </summary>
    
    public String SeverityName;

    /// <summary>
    /// The display name of the status of the incident (string)
    /// </summary>
    
    public String IncidentStatusName;

    /// <summary>
    /// The display name of the type of the incident (string)
    /// </summary>
    
    public String IncidentTypeName;

    /// <summary>
    /// The display name of the user that detected the incident (string)
    /// </summary>
    
    public String OpenerName;

    /// <summary>
    /// The display name of the user that the incident is assigned to (string)
    /// </summary>
    
    public String OwnerName;

    /// <summary>
    /// The display name of the project the incident belongs to (string)
    /// </summary>
    
    public String ProjectName;

    /// <summary>
    /// The version number of the release/iteration that the incident was detected in (string)
    /// </summary>
    
    public String DetectedReleaseVersionNumber;

    /// <summary>
    /// The version number of the release/iteration that the incident will be resolved in (string)
    /// </summary>
    
    public String ResolvedReleaseVersionNumber;

    /// <summary>
    /// The version number of the release/iteration that the incident was retested in (string)
    /// </summary>
    
    public String VerifiedReleaseVersionNumber;

    /// <summary>
    /// Is the incident in an 'open' status or not?
    /// </summary>
    public Boolean IncidentStatusOpenStatus;

    /// <summary>
    /// The id of the build that the incident was fixed in (integer)
    /// </summary>
    public Integer FixedBuildId;

    /// <summary>
    /// The name of the build that the incident was fixed in (string)
    /// </summary>
    public String FixedBuildName;

    /// <summary>
    /// The id of the build that the incident was detected in (integer)
    /// </summary>
    public Integer DetectedBuildId;

    /// <summary>
    /// The name of the build that the incident was detected in (string)
    /// </summary>
    public String DetectedBuildName;

}
