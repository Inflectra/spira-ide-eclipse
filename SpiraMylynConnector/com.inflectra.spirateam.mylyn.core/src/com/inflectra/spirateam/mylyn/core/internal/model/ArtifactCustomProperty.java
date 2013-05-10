package com.inflectra.spirateam.mylyn.core.internal.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Represents a generic SpiraTeam artifact custom property instance
 * 
 * @author Inflectra Corporation
 */
public class ArtifactCustomProperty
{
	public final static String FIELD_PREPEND = "Custom_";
	
    protected int PropertyNumber;
    protected String StringValue;
    protected Integer IntegerValue;
    protected Boolean BooleanValue;
    protected Date DateTimeValue;
    protected BigDecimal DecimalValue;
    protected List<Integer> IntegerListValue;
    
    public ArtifactCustomProperty()
    {    	
    }
    
    public ArtifactCustomProperty(int propertyNumber)
    {
    	this.PropertyNumber = propertyNumber;
    }
    
	public int getPropertyNumber()
	{
		return PropertyNumber;
	}
	public void setPropertyNumber(int propertyNumber)
	{
		PropertyNumber = propertyNumber;
	}
	
	/**
	 * Gets the custom property field name (Custom_XX)
	 * @return
	 */
	public String getFieldName()
	{
		return FIELD_PREPEND + String.format("%02d", PropertyNumber);
	}
	
	public String getStringValue()
	{
		return StringValue;
	}
	public void setStringValue(String stringValue)
	{
		StringValue = stringValue;
	}
	
	public Integer getIntegerValue()
	{
		return IntegerValue;
	}
	public void setIntegerValue(Integer integerValue)
	{
		IntegerValue = integerValue;
	}
	
	public Boolean getBooleanValue()
	{
		return BooleanValue;
	}
	public void setBooleanValue(Boolean booleanValue)
	{
		BooleanValue = booleanValue;
	}
	
	public Date getDateTimeValue()
	{
		return DateTimeValue;
	}
	public void setDateTimeValue(Date dateTimeValue)
	{
		DateTimeValue = dateTimeValue;
	}
	
	public BigDecimal getDecimalValue()
	{
		return DecimalValue;
	}
	public void setDecimalValue(BigDecimal decimalValue)
	{
		DecimalValue = decimalValue;
	}
	
	public List<Integer> getIntegerListValue()
	{
		return IntegerListValue;
	}
	public void setIntegerListValue(List<Integer> integerListValue)
	{
		IntegerListValue = integerListValue;
	}
	
	/* Static methods */
	
	/**
	 * Gets the custom property number for a specific field name (or null if not a custom field)
	 * @param fieldName
	 * @return
	 */
	public static Integer GetPropertyNumber(String fieldName)
	{
		if (fieldName == null)
		{
			return null;
		}
		
		if (!fieldName.startsWith(FIELD_PREPEND))
		{
			return null;			
		}
		
		String propNumber = fieldName.substring(FIELD_PREPEND.length());
		try
		{
			int propNumberInt = Integer.parseInt(propNumber);
			return propNumberInt;
		}
		catch (NumberFormatException ex)
		{
			return null;
		}
	}
}
