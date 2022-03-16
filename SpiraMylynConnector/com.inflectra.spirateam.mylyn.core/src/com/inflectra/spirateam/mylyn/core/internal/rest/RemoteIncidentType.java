package com.inflectra.spirateam.mylyn.core.internal.rest;

public class RemoteIncidentType {
    /// <summary>
    /// The id of the incident type (integer)
    /// </summary>
    public Integer IncidentTypeId;

    /// <summary>
    /// The name of the incident type (string)
    /// </summary>
    public String Name;

    /// <summary>
    /// Whether the incident type is active or not (boolean)
    /// </summary>
    public boolean Active;

    /// <summary>
    /// Should incidents of this type appear in the 'Top Open Issues' section (boolean)
    /// </summary>
    public boolean Issue;

    /// <summary>
    /// Should incidents of this type appear in the 'Top Open Issues' section (boolean)
    /// </summary>
    public boolean Risk;

    /// <summary>
    /// The id of the workflow the incidents are associated with (integer)
    /// </summary>
	public int WorkflowId;
}
