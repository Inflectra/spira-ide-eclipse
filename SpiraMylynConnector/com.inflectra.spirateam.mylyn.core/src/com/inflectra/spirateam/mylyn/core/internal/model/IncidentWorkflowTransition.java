package com.inflectra.spirateam.mylyn.core.internal.model;

import com.inflectra.spirateam.mylyn.core.internal.rest.RemoteWorkflowTransition;

public class IncidentWorkflowTransition
{
    protected boolean executeByDetector;
    protected boolean executeByOwner;
    protected int incidentStatusIDInput;
    protected String incidentStatusNameInput;
    protected int incidentStatusIDOutput;
    protected String incidentStatusNameOutput;
    protected String name;
    protected int workflowID;
    protected int transitionID;
    
    public IncidentWorkflowTransition(RemoteWorkflowTransition remoteWorkflowIncidentTransition)
    {
        this.executeByDetector = remoteWorkflowIncidentTransition.ExecuteByCreator;
        this.executeByOwner = remoteWorkflowIncidentTransition.ExecuteByOwner;
        this.incidentStatusIDInput = remoteWorkflowIncidentTransition.StatusId_Input;
        this.incidentStatusNameInput = remoteWorkflowIncidentTransition.StatusName_Input;
        this.incidentStatusIDOutput = remoteWorkflowIncidentTransition.StatusId_Output;
        this.incidentStatusNameOutput = remoteWorkflowIncidentTransition.StatusName_Output;
        this.name = remoteWorkflowIncidentTransition.Name;
        this.workflowID = remoteWorkflowIncidentTransition.WorkflowId;
        this.transitionID = remoteWorkflowIncidentTransition.TransitionId;
    }
    
    /**
     * Gets the value of the executeByDetector property.
     * 
     */
    public boolean isExecuteByDetector() {
        return executeByDetector;
    }

    /**
     * Sets the value of the executeByDetector property.
     * 
     */
    public void setExecuteByDetector(boolean value) {
        this.executeByDetector = value;
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
    public int getIncidentStatusIDInput() {
        return incidentStatusIDInput;
    }

    /**
     * Sets the value of the incidentStatusIDInput property.
     * 
     */
    public void setIncidentStatusIDInput(int value) {
        this.incidentStatusIDInput = value;
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
        return incidentStatusNameInput;
    }

    /**
     * Sets the value of the incidentStatusNameInput property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIncidentStatusNameInput(String value) {
        this.incidentStatusNameInput = value;
    }

    /**
     * Gets the value of the incidentStatusIDOutput property.
     * 
     */
    public int getIncidentStatusIDOutput() {
        return incidentStatusIDOutput;
    }

    /**
     * Sets the value of the incidentStatusIDOutput property.
     * 
     */
    public void setIncidentStatusIDOutput(int value) {
        this.incidentStatusIDOutput = value;
    }

    /**
     * Gets the value of the incidentStatusNameOutput property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIncidentStatusNameOutput() {
        return incidentStatusNameOutput;
    }

    /**
     * Sets the value of the incidentStatusNameOutput property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIncidentStatusNameOutput(String value) {
        this.incidentStatusNameOutput = value;
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
