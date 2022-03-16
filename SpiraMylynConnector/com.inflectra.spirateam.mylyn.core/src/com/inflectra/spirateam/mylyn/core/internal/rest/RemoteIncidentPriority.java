package com.inflectra.spirateam.mylyn.core.internal.rest;

public class RemoteIncidentPriority {
    /// <summary>
    /// The id of the incident priority (integer)
    /// </summary>
    public Integer PriorityId;

    /// <summary>
    /// The name of the incident priority (string)
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
