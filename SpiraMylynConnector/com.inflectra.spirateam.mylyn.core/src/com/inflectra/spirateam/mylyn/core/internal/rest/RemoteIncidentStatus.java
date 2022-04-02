package com.inflectra.spirateam.mylyn.core.internal.rest;

public class RemoteIncidentStatus {
    /// <summary>
    /// The id of the incident status (integer)
    /// </summary>
    public Integer IncidentStatusId;

    /// <summary>
    /// The name of the incident status (string)
    /// </summary>
    public String Name;

    /// <summary>
    /// Whether this status is active or not (boolean)
    /// </summary>
    public boolean Active;

    /// <summary>
    /// Whether this status is considered an 'open' status or not (boolean)
    /// </summary>
    public boolean Open;
}
