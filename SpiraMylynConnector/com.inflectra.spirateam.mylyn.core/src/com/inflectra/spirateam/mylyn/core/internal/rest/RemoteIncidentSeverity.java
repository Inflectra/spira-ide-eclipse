package com.inflectra.spirateam.mylyn.core.internal.rest;

public class RemoteIncidentSeverity {
    /// <summary>
    /// The id of the incident severity (integer)
    /// </summary>
    public Integer SeverityId;

    /// <summary>
    /// The name of the severity (string)
    /// </summary>
    public String Name;

    /// <summary>
    /// Whether the severity is active or not (boolean)
    /// </summary>
    public boolean Active;

    /// <summary>
    /// The hex color code associated with the severity (string)
    /// </summary>
    public String Color;

    /// <summary>
    /// The score value
    /// </summary>
    public Integer Score;
}
