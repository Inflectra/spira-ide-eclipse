package com.inflectra.spirateam.mylyn.core.internal.rest;

public class RemoteWorkflowCustomProperty {
    /// <summary>
    /// What is the caption for the custom property
    /// </summary>
    public String FieldCaption;

    /// <summary>
    /// What is the system name of the custom property
    /// </summary>
    /// <remarks>Uses the format Custom_01, Custom_02, Custom_03, etc.</remarks>
    public String FieldName;

    /// <summary>
    /// What is the id of the custom property
    /// </summary>
    public int CustomPropertyId;

    /// <summary>
    /// What is the state of the custom property that this represents
    /// </summary>
    /// <remarks>
    /// Active = 1,
    /// Required = 2
    /// </remarks>
    public int FieldStateId;
}
