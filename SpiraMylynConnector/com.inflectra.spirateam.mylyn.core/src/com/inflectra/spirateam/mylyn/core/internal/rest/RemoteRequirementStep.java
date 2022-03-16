package com.inflectra.spirateam.mylyn.core.internal.rest;

import java.util.Date;

public class RemoteRequirementStep {
    /// <summary>
    /// The id of the step
    /// </summary>
    public Integer RequirementStepId;

    /// <summary>
    /// The id of the requirement the step belongs to
    /// </summary>
    public int RequirementId;

    /// <summary>
    /// The position of the step in the requirement
    /// </summary>
    public int Position;
    
    /// <summary>
    /// The description of this step
    /// </summary>
    public String Description;

    /// <summary>
    /// The date the step was last updated
    /// </summary>
    public Date LastUpdateDate;

    /// <summary>
    /// The date the step was edited
    /// </summary>
    public Date ConcurrencyDate;

    /// <summary>
    /// The date the step was created
    /// </summary>
    public Date CreationDate;
}
