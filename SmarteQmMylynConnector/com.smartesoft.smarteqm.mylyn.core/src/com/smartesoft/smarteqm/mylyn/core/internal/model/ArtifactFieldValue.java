/**
 * 
 */
package com.smartesoft.smarteqm.mylyn.core.internal.model;

/**
 * @author Inflectra Corporation
 *
 */
public class ArtifactFieldValue
{
	private int id;
	private String name;
	boolean active = true;
	
	public ArtifactFieldValue(int id, String name)
	{
		this(id, name, true);
	}

	public ArtifactFieldValue(int id, String name, boolean active)
	{
		this.id = id;
		this.name = name;
		this.active = active;
	}
	
	public int getId()
	{
		return this.id;
	}

	public String getName()
	{
		return this.name;
	}
	
	public boolean getActive()
	{
		return this.active;
	}
}
