package com.inflectra.spirateam.mylyn.core.internal.rest;

public class RemoteComponent {
    /// <summary>
    /// The id of the Component (id - integer)
    /// </summary>
    public Integer ComponentId;

    /// <summary>
    /// The id of the project the component belongs to (id - integer)
    /// </summary>
    public int ProjectId;

    /// <summary>
    /// The name of the Component (string)
    /// </summary>
    public String Name;

    /// <summary>
    /// Is the component active (boolean)
    /// </summary>
    public boolean IsActive = true;

    /// <summary>
    /// Is the component deleted (boolean)
    /// </summary>
    public boolean IsDeleted = false;
}
