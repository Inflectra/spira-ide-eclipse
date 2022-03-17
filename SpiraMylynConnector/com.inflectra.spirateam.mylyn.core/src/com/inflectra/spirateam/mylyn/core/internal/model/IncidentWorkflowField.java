package com.inflectra.spirateam.mylyn.core.internal.model;

import com.inflectra.spirateam.mylyn.core.internal.rest.RemoteWorkflowCustomProperty;
import com.inflectra.spirateam.mylyn.core.internal.rest.RemoteWorkflowField;

public class IncidentWorkflowField
{
	protected String fieldCaption;
	protected String fieldName;
	protected int fieldID;
	protected int fieldStatus;

	/**
	 * Used when created from a standard field
	 * 
	 * @param remoteWorkflowField
	 */
	public IncidentWorkflowField(RemoteWorkflowField remoteWorkflowField)
	{
		this.fieldCaption = remoteWorkflowField.getFieldCaption().getValue();
		this.fieldName = remoteWorkflowField.getFieldName().getValue();
		this.fieldID = remoteWorkflowField.getFieldId();
		this.fieldStatus = remoteWorkflowField.getFieldStateId();
	}

	/**
	 * Used when created from a custom property
	 * 
	 * @param remoteWorkflowCustomProperty
	 */
	public IncidentWorkflowField(RemoteWorkflowCustomProperty remoteWorkflowCustomProperty)
	{
		this.fieldCaption = ""; // Not provided
		this.fieldName = remoteWorkflowCustomProperty.getFieldName().getValue();
		this.fieldID = remoteWorkflowCustomProperty.getCustomPropertyId();
		this.fieldStatus = remoteWorkflowCustomProperty.getFieldStateId();
	}

	/**
	 * Gets the value of the fieldCaption property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getFieldCaption()
	{
		return fieldCaption;
	}

	/**
	 * Gets the value of the fieldName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getFieldName()
	{
		return fieldName;
	}

	/**
	 * Gets the value of the fieldID property.
	 * 
	 */
	public int getFieldID()
	{
		return fieldID;
	}

	/**
	 * Gets the value of the fieldStatus property.
	 * 
	 */
	public int getFieldStatus()
	{
		return fieldStatus;
	}
}
