package com.inflectra.spirateam.mylyn.core.internal.rest;

public class RemoteWorkflowField {
    /// <summary>
    /// What is the caption for the field
    /// </summary>
	public String FieldCaption;

    /// <summary>
    /// What is the system name of the field
    /// </summary>
	public String FieldName;

    /// <summary>
    /// What is the id of the field
    /// </summary>
	public int FieldId;

    /// <summary>
    /// What is the state of the field that this represents
    /// </summary>
    /// <remarks>
    /// Inactive = 1,
    /// Required = 2,
    /// Hidden = 3
    /// </remarks>
	public int FieldStateId;
}
