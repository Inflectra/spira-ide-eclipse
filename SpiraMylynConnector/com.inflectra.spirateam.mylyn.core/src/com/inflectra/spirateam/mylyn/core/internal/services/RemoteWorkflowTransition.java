package com.inflectra.spirateam.mylyn.core.internal.services;

public class RemoteWorkflowTransition {
    /// <summary>
    /// Can this transition be executed by the creator of the artifact
    /// </summary>
	public boolean ExecuteByCreator;

    /// <summary>
    /// Can this transition be executed by the owner of the artifact
    /// </summary>
	public boolean ExecuteByOwner;

    /// <summary>
    /// What is the id of the input artifact status
    /// </summary>
	public int StatusId_Input;
    
    /// <summary>
    /// What is the display name of the input artifact status
    /// </summary>
    public String StatusName_Input;

    /// <summary>
    /// What is the id of the output artifact status
    /// </summary>
    public int StatusId_Output;
    
    /// <summary>
    /// What is the display name of the output artifact status
    /// </summary>
    public String StatusName_Output;

    /// <summary>
    /// What is the name of the transition
    /// </summary>
	public String Name;

    /// <summary>
    /// What workflow does this transition belong to
    /// </summary>
	public int WorkflowId;

    /// <summary>
    /// What is the id of this transition
    /// </summary>
	public int TransitionId;

    /// <summary>
    /// Does it require an electronic signature
    /// </summary>
    public boolean RequireSignature;
}
