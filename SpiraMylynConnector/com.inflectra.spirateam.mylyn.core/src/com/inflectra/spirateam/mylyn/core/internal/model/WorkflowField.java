package com.inflectra.spirateam.mylyn.core.internal.model;

import com.inflectra.spirateam.mylyn.core.internal.rest.RemoteWorkflowCustomProperty;
import com.inflectra.spirateam.mylyn.core.internal.rest.RemoteWorkflowField;

public class WorkflowField
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
	public WorkflowField(RemoteWorkflowField remoteWorkflowField)
	{
		this.fieldCaption = remoteWorkflowField.FieldCaption;
		this.fieldName = remoteWorkflowField.FieldName;
		this.fieldID = remoteWorkflowField.FieldId;
		this.fieldStatus = remoteWorkflowField.FieldStateId;
	}

	/**
	 * Used when created from a custom property
	 * 
	 * @param remoteWorkflowCustomProperty
	 */
	public WorkflowField(RemoteWorkflowCustomProperty remoteWorkflowCustomProperty)
	{
		this.fieldCaption = remoteWorkflowCustomProperty.FieldCaption;
		this.fieldName = remoteWorkflowCustomProperty.FieldName;
		this.fieldID = remoteWorkflowCustomProperty.CustomPropertyId;
		this.fieldStatus = remoteWorkflowCustomProperty.FieldStateId;
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
