package com.inflectra.spirateam.mylyn.core.internal.model;

import com.inflectra.spirateam.mylyn.core.internal.services.soap.RemoteWorkflowIncidentFields;

public class IncidentWorkflowField
{
    protected String fieldCaption;
    protected String fieldName;
    protected int fieldID;
    protected int fieldStatus;
    
    public IncidentWorkflowField (RemoteWorkflowIncidentFields remoteWorkflowField)
    {
    	this.fieldCaption = remoteWorkflowField.getFieldCaption();
    	this.fieldName = remoteWorkflowField.getFieldName();
    	this.fieldID = remoteWorkflowField.getFieldID();
    	this.fieldStatus = remoteWorkflowField.getFieldStatus();
    }
    
    /**
     * Gets the value of the fieldCaption property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFieldCaption() {
        return fieldCaption;
    }
    
    /**
     * Gets the value of the fieldName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFieldName() {
        return fieldName;
    }


    /**
     * Gets the value of the fieldID property.
     * 
     */
    public int getFieldID() {
        return fieldID;
    }

    /**
     * Gets the value of the fieldStatus property.
     * 
     */
    public int getFieldStatus() {
        return fieldStatus;
    }
}
