package com.inflectra.spirateam.mylyn.core.internal.rest;

public class RemoteProjectUser extends RemoteUser {
    /// <summary>
    /// The ID of the project
    /// </summary>
    public int ProjectId;

    /// <summary>
    /// The ID of the role the user has on the project
    /// </summary>
    public int ProjectRoleId;

    /// <summary>
    /// The name of the role the user has on the project
    /// </summary>
    /// <remarks>Read-only</remarks>
    public String ProjectRoleName;
}
