/**
 * 
 */
package com.smartesoft.smarteqm.mylyn.core.internal.model;

import java.io.Serializable;

/**
 * Represents a predefined SpiraTeam filter (e.g. My Assigned Incidents)
 * @author Inflectra Corporation
 */
public class PredefinedFilter
	extends Filter
	implements Serializable
{
	private static final long serialVersionUID = -4213236334241145619L;

	public PredefinedFilter()
	{
	}
	public PredefinedFilter (String id, String name)
	{
		this.id = id;
		this.name = name;
	}
	
	private String name;
	
	public String getName()
	{
		return this.name;
	}
	public void setName(String name)
	{
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return this.name;
	}
}
