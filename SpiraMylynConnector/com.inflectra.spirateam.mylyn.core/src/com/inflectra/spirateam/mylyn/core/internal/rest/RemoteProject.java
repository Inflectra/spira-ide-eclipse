package com.inflectra.spirateam.mylyn.core.internal.rest;

import java.util.Date;

public class RemoteProject {
    /// <summary>
    /// The id of the project (integer)
    /// </summary>
    public Integer ProjectId;

    /// <summary>
    /// The id of the project template (null on create, means create a new template)
    /// </summary>
    public Integer ProjectTemplateId;

    /// <summary>
    /// The id of the project group / program (null on create, means use default group)
    /// </summary>
    public Integer ProjectGroupId;

    /// <summary>
    /// The name of the project (string)
    /// </summary>
    public String Name;

    /// <summary>
    /// The description of the project (string)
    /// </summary>
    public String Description;

    /// <summary>
    /// The url associated with the project (string)
    /// </summary>
    public String Website;
    
    /// <summary>
    /// The date/time the project was created (datetime)
    /// </summary>
    public Date CreationDate;

    /// <summary>
    /// Whether the project is active or not (boolean)
    /// </summary>
    public boolean Active = true;

    /// <summary>
    /// How many working hours are in a day for this project (integer)
    /// </summary>
    public int WorkingHours;

    /// <summary>
    /// How many working days are in a week for this project (integer)
    /// </summary>
    public int WorkingDays;

    /// <summary>
    /// How many special non-working hours are there in a month in the project (integer)
    /// </summary>
    public int NonWorkingHours;

    /// <summary>
    /// The start date of the project for planning purposes
    /// </summary>
    public Date StartDate;

    /// <summary>
    /// The end date of the project for planning purposes
    /// </summary>
    public Date EndDate;

    /// <summary>
    /// The percentage complete of the project
    /// </summary>
    public int PercentComplete;
}
