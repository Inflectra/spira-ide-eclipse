package com.inflectra.spirateam.mylyn.core.internal.model;

import com.inflectra.spirateam.mylyn.core.internal.rest.RemoteWorkflowTransition;

public class WorkflowTransition
{
    protected boolean executeByCreator;
    protected boolean executeByOwner;
    protected int statusIDInput;
    protected String statusNameInput;
    protected int statusIDOutput;
    protected String statusNameOutput;
    protected String name;
    protected int workflowID;
    protected int transitionID;
    
    public WorkflowTransition(RemoteWorkflowTransition remoteWorkflowIncidentTransition)
    {
        this.executeByCreator = remoteWorkflowIncidentTransition.ExecuteByCreator;
        this.executeByOwner = remoteWorkflowIncidentTransition.ExecuteByOwner;
        this.statusIDInput = remoteWorkflowIncidentTransition.StatusId_Input;
        this.statusNameInput = remoteWorkflowIncidentTransition.StatusName_Input;
        this.statusIDOutput = remoteWorkflowIncidentTransition.StatusId_Output;
        this.statusNameOutput = remoteWorkflowIncidentTransition.StatusName_Output;
        this.name = remoteWorkflowIncidentTransition.Name;
        this.workflowID = remoteWorkflowIncidentTransition.WorkflowId;
        this.transitionID = remoteWorkflowIncidentTransition.TransitionId;
    }
    
    /**
     * Gets the value of the executeByDetector property.
     * 
     */
    public boolean isExecuteByCreator() {
        return executeByCreator;
    }

    /**
     * Sets the value of the executeByDetector property.
     * 
     */
    public void setExecuteByCreator(boolean value) {
        this.executeByCreator = value;
    }

    /**
     * Gets the value of the executeByOwner property.
     * 
     */
    public boolean isExecuteByOwner() {
        return executeByOwner;
    }

    /**
     * Sets the value of the executeByOwner property.
     * 
     */
    public void setExecuteByOwner(boolean value) {
        this.executeByOwner = value;
    }

    /**
     * Gets the value of the incidentStatusIDInput property.
     * 
     */
    public int getStatusIDInput() {
        return statusIDInput;
    }

    /**
     * Sets the value of the incidentStatusIDInput property.
     * 
     */
    public void setStatusIDInput(int value) {
        this.statusIDInput = value;
    }

    /**
     * Gets the value of the incidentStatusNameInput property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIncidentStatusNameInput() {
        return statusNameInput;
    }

    /**
     * Sets the value of the incidentStatusNameInput property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusNameInput(String value) {
        this.statusNameInput = value;
    }

    /**
     * Gets the value of the incidentStatusIDOutput property.
     * 
     */
    public int getStatusIDOutput() {
        return statusIDOutput;
    }

    /**
     * Sets the value of the incidentStatusIDOutput property.
     * 
     */
    public void setStatusIDOutput(int value) {
        this.statusIDOutput = value;
    }

    /**
     * Gets the value of the incidentStatusNameOutput property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusNameOutput() {
        return statusNameOutput;
    }

    /**
     * Sets the value of the incidentStatusNameOutput property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusNameOutput(String value) {
        this.statusNameOutput = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the workflowID property.
     * 
     */
    public int getWorkflowID() {
        return workflowID;
    }

    /**
     * Sets the value of the workflowID property.
     * 
     */
    public void setWorkflowID(int value) {
        this.workflowID = value;
    }

    /**
     * Gets the value of the transitionID property.
     * 
     */
    public int getTransitionID() {
        return transitionID;
    }

    /**
     * Sets the value of the transitionID property.
     * 
     */
    public void setTransitionID(int value) {
        this.transitionID = value;
    }
}
