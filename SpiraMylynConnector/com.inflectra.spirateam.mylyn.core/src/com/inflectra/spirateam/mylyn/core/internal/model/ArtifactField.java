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

	public enum Type
	{
		TEXT, CHECKBOX, SELECT, RADIO, TEXTAREA;

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
			else if ("select".equals(value)) //$NON-NLS-1$
			{
				return SELECT;
			}
			else if ("radio".equals(value)) //$NON-NLS-1$
			{
				return RADIO;
			}
			else if ("textarea".equals(value)) //$NON-NLS-1$
			{
				return TEXTAREA;
			}
			return TEXT;
		}
	}
	
	public static final int DEFAULT_SIZE = -1;

	private String name;

	private Type type;

	private String label;

	private String[] options;

	private String defaultValue;

	private boolean custom;

	private int order;

	private boolean optional;

	private int width = DEFAULT_SIZE;

	private int height = DEFAULT_SIZE;

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

	public String[] getOptions()
	{
		return options;
	}

	public void setOptions(String[] options)
	{
		this.options = options;
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
