package com.inflectra.spirateam.mylyn.core.internal.rest;

public class RemoteTaskPriority {
    /// <summary>
    /// The id of the task priority (integer)
    /// </summary>
    public Integer PriorityId;

    /// <summary>
    /// The name of the task priority (string)
    /// </summary>
    public String Name;

    /// <summary>
    /// Whether the priority is active or not (boolean)
    /// </summary>
    public boolean Active;

    /// <summary>
    /// The hex color code associated with the priority (string)
    /// </summary>
    public String Color;

    /// <summary>
    /// The score value
    /// </summary>
    public Integer Score;
}
