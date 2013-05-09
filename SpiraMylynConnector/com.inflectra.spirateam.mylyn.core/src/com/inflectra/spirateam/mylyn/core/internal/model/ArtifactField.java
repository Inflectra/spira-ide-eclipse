/**
 * 
 */
package com.inflectra.spirateam.mylyn.core.internal.model;

import java.io.Serializable;

/**
 * @author Inflectra Corporation
 *
 */
public class ArtifactField implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2795833229954123352L;

	public enum Type
	{
		TEXT, CHECKBOX, SINGLE_SELECT, MULTI_SELECT, PERSON, RICH_TEXT, DATE, INTEGER, DOUBLE;

		public static Type fromString(String value)
		{
			value = value.toLowerCase();
			if ("text".equals(value))	//$NON-NLS-1$
			{ 
				return TEXT;
			}
			else if ("checkbox".equals(value)) //$NON-NLS-1$
			{
				return CHECKBOX;
			}
			else if ("single_select".equals(value)) //$NON-NLS-1$
			{
				return SINGLE_SELECT;
			}
			else if ("person".equals(value)) //$NON-NLS-1$
			{
				return PERSON;
			}
			else if ("single_select".equals(value)) //$NON-NLS-1$
			{
				return MULTI_SELECT;
			}
			else if ("rich_text".equals(value)) //$NON-NLS-1$
			{
				return RICH_TEXT;
			}
			else if ("integer".equals(value)) //$NON-NLS-1$
			{
				return INTEGER;
			}
			else if ("double".equals(value)) //$NON-NLS-1$
			{
				return DOUBLE;
			}
			else if ("date".equals(value)) //$NON-NLS-1$
			{
				return DATE;
			}
			return TEXT;
		}
	}
	
	public static final int DEFAULT_SIZE = -1;

	private String name;

	private Type type;

	private String label;

	private ArtifactFieldValue[] values;

	private String defaultValue;

	private boolean custom;

	private int order;

	private boolean optional;

	private int width = DEFAULT_SIZE;

	private int height = DEFAULT_SIZE;
	
	private Integer precision = null;

	public ArtifactField(String name)
	{
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Type getType()
	{
		return type;
	}

	public void setType(Type type)
	{
		this.type = type;
	}

	public String getLabel()
	{
		return label;
	}

	public void setLabel(String label)
	{
		this.label = label;
	}

	public ArtifactFieldValue[] getValues()
	{
		return this.values;
	}

	public void setValues(ArtifactFieldValue[] values)
	{
		this.values = values;
	}

	public int getOrder()
	{
		return order;
	}

	public void setOrder(int order)
	{
		this.order = order;
	}

	public boolean isOptional()
	{
		return optional;
	}

	public void setOptional(boolean optional)
	{
		this.optional = optional;
	}

	public int getWidth()
	{
		return width;
	}

	public void setWidth(int width)
	{
		this.width = width;
	}

	public int getHeight()
	{
		return height;
	}

	public void setHeight(int height)
	{
		this.height = height;
	}

	public Integer getPrecision()
	{
		return this.precision;
	}

	public void setPrecision(Integer precision)
	{
		this.precision = precision;
	}
	
	public String getDefaultValue()
	{
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue)
	{
		this.defaultValue = defaultValue;
	}

	public boolean isCustom()
	{
		return custom;
	}

	public void setCustom(boolean custom)
	{
		this.custom = custom;
	}

	@Override
	public String toString()
	{
		return name;
	}
}
