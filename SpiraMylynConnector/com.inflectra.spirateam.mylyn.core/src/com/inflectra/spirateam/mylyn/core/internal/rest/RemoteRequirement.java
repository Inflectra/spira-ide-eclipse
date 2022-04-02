package com.inflectra.spirateam.mylyn.core.internal.rest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

public class RemoteRequirement extends RemoteArtifact {
    /// <summary>
    /// The id of the requirement (integer)
    /// </summary>
    public Integer RequirementId;

    /// <summary>
    /// The indentation level of the artifact (String)
    /// </summary>
    /// <remarks>
    /// The system uses a set of three-letter segments to denote indent (e.g. AAA followed by AAB, etc.)
    /// </remarks>
    public String IndentLevel;

    /// <summary>
    /// The id of the requirement's status (integer).
    /// </summary>
    /// <remarks>
    /// If no value is provided, the default status is used
    /// Relevant values: Accepted 5; Completed 10; Developed 4; Evaluated 7; In Progress 3; Obsolete 8; Planned 2; Rejected 6; Requested 1; Tested 9.
    /// </remarks>
    public Integer StatusId;

    /// <summary>
    /// The type of requirement (integer).
    /// </summary>
    /// <remarks>
    /// Relevant values: Package -1; Need 1; Feature 2; Use Case 3; User Story 4; Quality 5; Design Element 6
    /// Null can be passed when created if using the default type
    /// </remarks>
    public Integer RequirementTypeId;

    /// <summary>
    /// The id of the user that wrote the requirement (integer)
    /// </summary>
    /// <remarks>
    /// If no value is provided, the authenticated user is used instead
    /// </remarks>
    public Integer AuthorId;

    /// <summary>
    /// The id of the user that the requirement is assigned-to (integer)
    /// </summary>
    public Integer OwnerId;

    /// <summary>
    /// The id of the importance of the requirement (integer)
    /// </summary>
    /// <remarks>
    /// Relevant values: 1 - Critical 1; 2 - High 2; 3 - Medium 3; 4 - Low 4
    /// </remarks>
    public Integer ImportanceId;

    /// <summary>
    /// The id of the release the requirement is scheduled to implemented in (integer)
    /// </summary>
    public Integer ReleaseId;

    /// <summary>
    /// The id of the component the requirement is a part of (integer - these are created on a per project user by an administrator)
    /// </summary>
    public Integer ComponentId;

    /// <summary>
    /// The name of the requirement (String - required for POST)
    /// </summary>
    public String Name;

    /// <summary>
    /// The description of the requirement (String)
    /// </summary>
    public String Description;

    /// <summary>
    /// The date/time the requirement was originally created (date-time)
    /// </summary>
    public Date CreationDate;

    /// <summary>
    /// The date/time the requirement was last modified (date-time)
    /// </summary>
    public Date LastUpdateDate;

    /// <summary>
    /// Is this a summary requirement or not (boolean)
    /// </summary>
    public boolean Summary;

    /// <summary>
    /// How many test cases cover this requirement (integer)
    /// </summary>
    
    public Integer CoverageCountTotal;

    /// <summary>
    /// How many of the test cases that cover this requirement have passed (integer)
    /// </summary>
    
    public Integer CoverageCountPassed;

    /// <summary>
    /// How many of the test cases that cover this requirement have failed (integer)
    /// </summary>
    
    public Integer CoverageCountFailed;

    /// <summary>
    /// How many of the test cases that cover this requirement have been marked as caution (integer)
    /// </summary>
    
    public Integer CoverageCountCaution;

    /// <summary>
    /// How many of the test cases that cover this requirement have blocked (integer)
    /// </summary>
    
    public Integer CoverageCountBlocked;

    /// <summary>
    /// The estimate of the requirement (decimal - in story points)
    /// </summary>
    public BigDecimal EstimatePoints;

    /// <summary>
    /// What was the original top-down level of effort estimated for this requirement, calculated from the points estimate (integer)
    /// </summary>
    
    public Integer EstimatedEffort;

    /// <summary>
    /// What is the bottom-up estimated effort for all the tasks associated with this requirement (integer)
    /// </summary>
    
    public Integer TaskEstimatedEffort;

    /// <summary>
    /// What is the bottom-up actual effort for all the tasks associated with this requirement (integer)
    /// </summary>
    
    public Integer TaskActualEffort;

    /// <summary>
    /// How many tasks are associated with this requirement (integer)
    /// </summary>
    
    public Integer TaskCount;

    /// <summary>
    /// The version number String of the release that the requirement is scheduled for (String)
    /// </summary>
    
    public String ReleaseVersionNumber;

    /// <summary>
    /// The display name of the user that wrote this requirement (String)
    /// </summary>
    
    public String AuthorName;
    
    /// <summary>
    /// The display name of the user that this requirement is assigned-to (String)
    /// </summary>
    
    public String OwnerName;

    /// <summary>
    /// The display name of the status the requirement is in (String)
    /// </summary>
    
    public String StatusName;

    /// <summary>
    /// The display name of the importance that the requirement is in (String)
    /// </summary>
    
    public String ImportanceName;

    /// <summary>
    /// The display name of the project that the requirement is associated with (String)
    /// </summary>
    
    public String ProjectName;

    /// <summary>
    /// The display name of the type of requirement (String)
    /// </summary>
    
    public String RequirementTypeName;

    /// <summary>
    /// The list of scenarios steps (array - only available for Use Case requirement types)
    /// </summary>
    public ArrayList<RemoteRequirementStep> Steps;

    /// <summary>
    /// The start date of the requirement for planning purposes
    /// </summary>
    public Date StartDate;

    /// <summary>
    /// The end date of the requirement for planning purposes
    /// </summary>
    public Date EndDate;

    /// <summary>
    /// The percentage complete of the requirement
    /// </summary>
    public Integer PercentComplete;

    /// <summary>
    /// The Id of the program theme that the requirement belongs to
    /// </summary>
    public Integer ThemeId;

    /// <summary>
    /// The id of the goal that the requirement belongs to
    /// </summary>
    public Integer GoalId;
}
