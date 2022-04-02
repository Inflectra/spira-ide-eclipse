package com.inflectra.spirateam.mylyn.core.internal.rest;

public class RemoteRequirementType {
    /// <summary>
    /// The id of the requirement type
    /// </summary>
    public int RequirementTypeId;

    /// <summary>
    /// The name of the type
    /// </summary>
    public String Name;

    /// <summary>
    /// The id of the workflow the type is associated with, for the current project
    /// </summary>
	public Integer WorkflowId;

    /// <summary>
    /// Is this an active type
    /// </summary>
    public boolean IsActive;

    /// <summary>
    /// Is this the default type
    /// </summary>
    public boolean IsDefault;

    /// <summary>
    /// Does this type have steps
    /// </summary>
    public boolean IsSteps;
}
