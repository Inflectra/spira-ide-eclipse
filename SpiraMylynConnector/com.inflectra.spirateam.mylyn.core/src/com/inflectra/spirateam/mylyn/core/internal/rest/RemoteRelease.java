package com.inflectra.spirateam.mylyn.core.internal.rest;

import java.math.BigDecimal;
import java.util.Date;

public class RemoteRelease extends RemoteArtifact {
    /// <summary>
    /// The id of the release
    /// </summary>
    public Integer ReleaseId;

    /// <summary>
    /// The id of the user that created the release
    /// </summary>
    public Integer CreatorId;

    /// <summary>
    /// The id of the user that the release is assigned to
    /// </summary>
    public Integer OwnerId;

    /// <summary>
    /// The name of the user that the release is assigned to
    /// </summary>
    
    public String OwnerName;

    /// <summary>
    /// The indentation level of the artifact
    /// </summary>
    /// <remarks>
    /// The system uses a set of three-letter segments to denote indent (e.g. AAA followed by AAB, etc.)
    /// </remarks>
    public String IndentLevel;

    /// <summary>
    /// The name of the release
    /// </summary>
    public String Name;

    /// <summary>
    /// The description of the release
    /// </summary>
    public String Description;

    /// <summary>
    /// The version number String of the release
    /// </summary>
    public String VersionNumber;

    /// <summary>
    /// The date the release was originally created
    /// </summary>
    public Date CreationDate;

    /// <summary>
    /// The date the release was last modified
    /// </summary>
    public Date LastUpdateDate;

    /// <summary>
    /// Is this release a summary one (i.e. does it have child releases)
    /// </summary>
    public boolean Summary;

    /// <summary>
    /// Is this release active for the project
    /// </summary>
    
    public boolean Active;

    /// <summary>
    /// The status of the release
    /// </summary>
    /// <remarks>
    /// Planned = 1,
    /// InProgress = 2,
    /// Completed = 3,
    /// Closed = 4,
    /// Deferred = 5,
    /// Cancelled = 6
    /// </remarks>
    public int ReleaseStatusId;

    /// <summary>
    /// The type of the release
    /// </summary>
    /// <remarks>
    /// MajorRelease = 1,
    /// MinorRelease = 2,
    /// Iteration = 3,
    /// Phase = 4
    /// </remarks>
    public int ReleaseTypeId;

    /// <summary>
    /// What is the start date for the release
    /// </summary>
    public Date StartDate;

    /// <summary>
    /// What is the end date for the release
    /// </summary>
    public Date EndDate;

    /// <summary>
    /// How many people are working on the release
    /// </summary>
    public BigDecimal ResourceCount;

    /// <summary>
    /// How many non-working days are associated with the release
    /// </summary>
    public BigDecimal DaysNonWorking;

    /// <summary>
    /// What is the estimated planned effort associated with the release
    /// </summary>
    public Integer PlannedEffort;

    /// <summary>
    /// How much effort is still available in the release for planning
    /// </summary>
    public Integer AvailableEffort;

    /// <summary>
    /// How much effort was estimated for all the tasks scheduled for this release
    /// </summary>
    public Integer TaskEstimatedEffort;

    /// <summary>
    /// How much effort was actually expended for all the tasks scheduled for this release
    /// </summary>
    public Integer TaskActualEffort;

    /// <summary>
    /// How many tasks are scheduled for this release
    /// </summary>
    public Integer TaskCount;

    /// <summary>
    /// What is the full display name of the person who created this release
    /// </summary>
    
    public String CreatorName;

    /// <summary>
    /// The full name and version number of the release combined
    /// </summary>
    
    public String FullName;

    /// <summary>
    /// The display name for the release status
    /// </summary>
    
    public String ReleaseStatusName;

    /// <summary>
    /// The display name for the release type
    /// </summary>
    
    public String ReleaseTypeName;

    /// <summary>
    /// The count of blocked test cases in this release
    /// </summary>
    
    public int CountBlocked;

    /// <summary>
    /// The count of caution test cases in this release
    /// </summary>
    
    public int CountCaution;

    /// <summary>
    /// The count of failed test cases in this release
    /// </summary>
    
    public int CountFailed;

    /// <summary>
    /// The count of N/A test cases in this release
    /// </summary>
    
    public int CountNotApplicable;

    /// <summary>
    /// The count of not run test cases in this release
    /// </summary>
    
    public int CountNotRun;

    /// <summary>
    /// The count of passed test cases in this release
    /// </summary>
    
    public int CountPassed;

    /// <summary>
    /// The percentage complete of the project/sprint
    /// </summary>
    public int PercentComplete;

    /// <summary>
    /// The id of any program milestones that this release is associated with
    /// </summary>
    public Integer MilestoneId;
}
