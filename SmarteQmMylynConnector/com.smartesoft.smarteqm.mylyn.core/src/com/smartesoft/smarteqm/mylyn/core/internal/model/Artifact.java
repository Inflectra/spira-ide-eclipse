package com.smartesoft.smarteqm.mylyn.core.internal.model;

import java.util.Date;

import com.smartesoft.smarteqm.mylyn.core.internal.ArtifactType;

/**
 * Represents a generic SpiraTeam artifact
 * 
 * @author Inflectra Corporation
 */
public class Artifact
{
    protected Integer artifactId;
    protected int projectId;
    protected Integer ownerId;
    protected String name;
    protected String description;
    protected Date creationDate;
	protected Date lastUpdateDate;
    protected String text01;
    protected String text02;
    protected String text03;
    protected String text04;
    protected String text05;
    protected String text06;
    protected String text07;
    protected String text08;
    protected String text09;
    protected String text10;
    protected Integer list01;
    protected Integer list02;
    protected Integer list03;
    protected Integer list04;
    protected Integer list05;
    protected Integer list06;
    protected Integer list07;
    protected Integer list08;
    protected Integer list09;
    protected Integer list10;
    
    protected boolean dataChanged = false;
    
    public enum Key
    {
    	//Common attributes
    	PROJECT_ID("common.projectId"),
    	ARTIFACT_KEY("common.artifactKey"),
    	ARTIFACT_TYPE("common.artifactType"),
    	NAME("common.name"),
    	DESCRIPTION("common.description"),		
    	CREATION_DATE("common.creationDate"),
    	LAST_UPDATE_DATE("common.lastUpdateDate"),
    	OWNER_ID("common.ownerId"),
    	URL("common.url");
		
		public static Key fromKey(String name)
		{
			for (Key key : Key.values())
			{
				if (key.getKey().equals(name))
				{
					return key;
				}
			}
			return null;
		}

		private String key;

		Key(String key)
		{
			this.key = key;
		}

		@Override
		public String toString()
		{
			return key;
		}

		public String getKey()
		{
			return key;
		}
	}
    
    public boolean isDataChanged()
    {
    	return this.dataChanged;
    }
    
    protected boolean hasChanged (String newValue, String oldValue)
    {
    	if (newValue == null)
    	{
    		return (oldValue == null);
    	}
    	if (oldValue == null)
    	{
    		return (newValue == null);
    	}
    	if (!newValue.equals(oldValue))
    	{
    		return true;
    	}
    	return false;
    }
    
    protected boolean hasChanged (Integer newValue, Integer oldValue)
    {
    	if (newValue == null)
    	{
    		return (oldValue == null);
    	}
    	if (oldValue == null)
    	{
    		return (newValue == null);
    	}
    	if (!newValue.equals(oldValue))
    	{
    		return true;
    	}
    	return false;
    }
    
    protected boolean hasChanged (Date newValue, Date oldValue)
    {
    	if (newValue == null)
    	{
    		return (oldValue == null);
    	}
    	if (oldValue == null)
    	{
    		return (newValue == null);
    	}
    	if (!newValue.equals(oldValue))
    	{
    		return true;
    	}
    	return false;
    }
    
    protected boolean hasChanged (int newValue, int oldValue)
    {
    	if (newValue != oldValue)
    	{
    		return true;
    	}
    	return false;
    }
    
    /**
     * Gets the value of the artifactId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getArtifactId()
    {
        return this.artifactId;
    }

    /**
     * Sets the value of the artifactId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setArtifactId(Integer value)
    {
        this.artifactId = value;
    }

    /**
     * Gets the value of the projectId property.
     * 
     */
    public int getProjectId()
    {
        return projectId;
    }

    /**
     * Sets the value of the projectId property.
     * 
     */
    public void setProjectId(int value)
    {
        this.projectId = value;
    }
    
	    /**
	     * Gets the unique artifact key (two-letter prefix + ID)
	     * @return the artifact's key
	     * 
	     * Needs to be overridden by the derived class
	     */
	    public String getArtifactKey()
	    {
	    	return null;
	    }
	    
	    /**
	     * Gets the unique artifact type enum
	     * @return the artifact's type
	     * 
	     * Needs to be overridden by the derived class
	     */
	    public ArtifactType getArtifactType()
	    {
	    	return null;
	    }
	    
	    /**
	     * Gets the value of the ownerId property.
	     * 
	     * @return
	     *     possible object is
	     *     {@link Integer }
	     *     
	     */
	    public Integer getOwnerId() {
	        return ownerId;
	    }

	    /**
	     * Sets the value of the ownerId property.
	     * 
	     * @param value
	     *     allowed object is
	     *     {@link Integer }
	     *     
	     */
	    public void setOwnerId(Integer value)
	    {
	    	if (hasChanged(this.ownerId, value))
	    	{
	    		this.dataChanged = true;
	    	}
	        this.ownerId = value;
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
	    public void setName(String value)
	    {
	    	if (hasChanged(this.name, value))
	    	{
	    		this.dataChanged = true;
	    	}
	        this.name = value;
	    }

	    /**
	     * Gets the value of the description property.
	     * 
	     * @return
	     *     possible object is
	     *     {@link String }
	     *     
	     */
	    public String getDescription() {
	        return description;
	    }

	    /**
	     * Sets the value of the description property.
	     * 
	     * @param value
	     *     allowed object is
	     *     {@link String }
	     *     
	     */
	    public void setDescription(String value)
	    {
	    	if (hasChanged(this.description, value))
	    	{
	    		this.dataChanged = true;
	    	}
	        this.description = value;
	    }

	    /**
	     * Gets the value of the creationDate property.
	     * 
	     * @return
	     *     possible object is
	     *     {@link Date }
	     *     
	     */
	    public Date getCreationDate() {
	        return creationDate;
	    }

	    /**
	     * Sets the value of the creationDate property.
	     * 
	     * @param value
	     *     allowed object is
	     *     {@link Date }
	     *     
	     */
	    public void setCreationDate(Date value)
	    {
	    	if (hasChanged(this.creationDate, value))
	    	{
	    		this.dataChanged = true;
	    	}
	        this.creationDate = value;
	    }
	    
	    /**
	     * Gets the value of the text01 property.
	     * 
	     * @return
	     *     possible object is
	     *     {@link String }
	     *     
	     */
	    public String getText01() {
	        return text01;
	    }

	    /**
	     * Sets the value of the text01 property.
	     * 
	     * @param value
	     *     allowed object is
	     *     {@link String }
	     *     
	     */
	    public void setText01(String value)
	    {
	    	if (hasChanged(this.text01, value))
	    	{
	    		this.dataChanged = true;
	    	}
	        this.text01 = value;
	    }

	    /**
	     * Gets the value of the text02 property.
	     * 
	     * @return
	     *     possible object is
	     *     {@link String }
	     *     
	     */
	    public String getText02() {
	        return text02;
	    }

	    /**
	     * Sets the value of the text02 property.
	     * 
	     * @param value
	     *     allowed object is
	     *     {@link String }
	     *     
	     */
	    public void setText02(String value)
	    {
	    	if (hasChanged(this.text02, value))
	    	{
	    		this.dataChanged = true;
	    	}
	        this.text02 = value;
	    }

	    /**
	     * Gets the value of the text03 property.
	     * 
	     * @return
	     *     possible object is
	     *     {@link String }
	     *     
	     */
	    public String getText03() {
	        return text03;
	    }

	    /**
	     * Sets the value of the text03 property.
	     * 
	     * @param value
	     *     allowed object is
	     *     {@link String }
	     *     
	     */
	    public void setText03(String value)
	    {
	    	if (hasChanged(this.text03, value))
	    	{
	    		this.dataChanged = true;
	    	}
	        this.text03 = value;
	    }

	    /**
	     * Gets the value of the text04 property.
	     * 
	     * @return
	     *     possible object is
	     *     {@link String }
	     *     
	     */
	    public String getText04() {
	        return text04;
	    }

	    /**
	     * Sets the value of the text04 property.
	     * 
	     * @param value
	     *     allowed object is
	     *     {@link String }
	     *     
	     */
	    public void setText04(String value)
	    {
	    	if (hasChanged(this.text04, value))
	    	{
	    		this.dataChanged = true;
	    	}
	        this.text04 = value;
	    }

	    /**
	     * Gets the value of the text05 property.
	     * 
	     * @return
	     *     possible object is
	     *     {@link String }
	     *     
	     */
	    public String getText05() {
	        return text05;
	    }

	    /**
	     * Sets the value of the text05 property.
	     * 
	     * @param value
	     *     allowed object is
	     *     {@link String }
	     *     
	     */
	    public void setText05(String value)
	    {
	    	if (hasChanged(this.text05, value))
	    	{
	    		this.dataChanged = true;
	    	}
	        this.text05 = value;
	    }

	    /**
	     * Gets the value of the text06 property.
	     * 
	     * @return
	     *     possible object is
	     *     {@link String }
	     *     
	     */
	    public String getText06() {
	        return text06;
	    }

	    /**
	     * Sets the value of the text06 property.
	     * 
	     * @param value
	     *     allowed object is
	     *     {@link String }
	     *     
	     */
	    public void setText06(String value)
	    {
	    	if (hasChanged(this.text06, value))
	    	{
	    		this.dataChanged = true;
	    	}
	        this.text06 = value;
	    }

	    /**
	     * Gets the value of the text07 property.
	     * 
	     * @return
	     *     possible object is
	     *     {@link String }
	     *     
	     */
	    public String getText07() {
	        return text07;
	    }

	    /**
	     * Sets the value of the text07 property.
	     * 
	     * @param value
	     *     allowed object is
	     *     {@link String }
	     *     
	     */
	    public void setText07(String value)
	    {
	    	if (hasChanged(this.text07, value))
	    	{
	    		this.dataChanged = true;
	    	}
	        this.text07 = value;
	    }

	    /**
	     * Gets the value of the text08 property.
	     * 
	     * @return
	     *     possible object is
	     *     {@link String }
	     *     
	     */
	    public String getText08()
	    {
	        return text08;
	    }

	    /**
	     * Sets the value of the text08 property.
	     * 
	     * @param value
	     *     allowed object is
	     *     {@link String }
	     *     
	     */
	    public void setText08(String value)
	    {
	    	if (hasChanged(this.text08, value))
	    	{
	    		this.dataChanged = true;
	    	}
	        this.text08 = value;
	    }

	    /**
	     * Gets the value of the text09 property.
	     * 
	     * @return
	     *     possible object is
	     *     {@link String }
	     *     
	     */
	    public String getText09() {
	        return text09;
	    }

	    /**
	     * Sets the value of the text09 property.
	     * 
	     * @param value
	     *     allowed object is
	     *     {@link String }
	     *     
	     */
	    public void setText09(String value)
	    {
	    	if (hasChanged(this.text09, value))
	    	{
	    		this.dataChanged = true;
	    	}
	        this.text09 = value;
	    }

	    /**
	     * Gets the value of the text10 property.
	     * 
	     * @return
	     *     possible object is
	     *     {@link String }
	     *     
	     */
	    public String getText10() {
	        return text10;
	    }

	    /**
	     * Sets the value of the text10 property.
	     * 
	     * @param value
	     *     allowed object is
	     *     {@link String }
	     *     
	     */
	    public void setText10(String value)
	    {
	    	if (hasChanged(this.text10, value))
	    	{
	    		this.dataChanged = true;
	    	}
	        this.text10 = value;
	    }

	    /**
	     * Gets the value of the list01 property.
	     * 
	     * @return
	     *     possible object is
	     *     {@link Integer }
	     *     
	     */
	    public Integer getList01() {
	        return list01;
	    }

	    /**
	     * Sets the value of the list01 property.
	     * 
	     * @param value
	     *     allowed object is
	     *     {@link Integer }
	     *     
	     */
	    public void setList01(Integer value)
	    {
	    	if (hasChanged(this.list01, value))
	    	{
	    		this.dataChanged = true;
	    	}
	        this.list01 = value;
	    }

	    /**
	     * Gets the value of the list02 property.
	     * 
	     * @return
	     *     possible object is
	     *     {@link Integer }
	     *     
	     */
	    public Integer getList02() {
	        return list02;
	    }

	    /**
	     * Sets the value of the list02 property.
	     * 
	     * @param value
	     *     allowed object is
	     *     {@link Integer }
	     *     
	     */
	    public void setList02(Integer value)
	    {
	    	if (hasChanged(this.list02, value))
	    	{
	    		this.dataChanged = true;
	    	}
	        this.list02 = value;
	    }

	    /**
	     * Gets the value of the list03 property.
	     * 
	     * @return
	     *     possible object is
	     *     {@link Integer }
	     *     
	     */
	    public Integer getList03() {
	        return list03;
	    }

	    /**
	     * Sets the value of the list03 property.
	     * 
	     * @param value
	     *     allowed object is
	     *     {@link Integer }
	     *     
	     */
	    public void setList03(Integer value)
	    {
	    	if (hasChanged(this.list03, value))
	    	{
	    		this.dataChanged = true;
	    	}
	        this.list03 = value;
	    }

	    /**
	     * Gets the value of the list04 property.
	     * 
	     * @return
	     *     possible object is
	     *     {@link Integer }
	     *     
	     */
	    public Integer getList04() {
	        return list04;
	    }

	    /**
	     * Sets the value of the list04 property.
	     * 
	     * @param value
	     *     allowed object is
	     *     {@link Integer }
	     *     
	     */
	    public void setList04(Integer value)
	    {
	    	if (hasChanged(this.list04, value))
	    	{
	    		this.dataChanged = true;
	    	}
	        this.list04 = value;
	    }

	    /**
	     * Gets the value of the list05 property.
	     * 
	     * @return
	     *     possible object is
	     *     {@link Integer }
	     *     
	     */
	    public Integer getList05()
	    {
	        return list05;
	    }

	    /**
	     * Sets the value of the list05 property.
	     * 
	     * @param value
	     *     allowed object is
	     *     {@link Integer }
	     *     
	     */
	    public void setList05(Integer value)
	    {
	    	if (hasChanged(this.list05, value))
	    	{
	    		this.dataChanged = true;
	    	}
	        this.list05 = value;
	    }

	    /**
	     * Gets the value of the list06 property.
	     * 
	     * @return
	     *     possible object is
	     *     {@link Integer }
	     *     
	     */
	    public Integer getList06() {
	        return list06;
	    }

	    /**
	     * Sets the value of the list06 property.
	     * 
	     * @param value
	     *     allowed object is
	     *     {@link Integer }
	     *     
	     */
	    public void setList06(Integer value)
	    {
	    	if (hasChanged(this.list06, value))
	    	{
	    		this.dataChanged = true;
	    	}
	        this.list06 = value;
	    }

	    /**
	     * Gets the value of the list07 property.
	     * 
	     * @return
	     *     possible object is
	     *     {@link Integer }
	     *     
	     */
	    public Integer getList07() {
	        return list07;
	    }

	    /**
	     * Sets the value of the list07 property.
	     * 
	     * @param value
	     *     allowed object is
	     *     {@link Integer }
	     *     
	     */
	    public void setList07(Integer value)
	    {
	    	if (hasChanged(this.list07, value))
	    	{
	    		this.dataChanged = true;
	    	}
	        this.list07 = value;
	    }

	    /**
	     * Gets the value of the list08 property.
	     * 
	     * @return
	     *     possible object is
	     *     {@link Integer }
	     *     
	     */
	    public Integer getList08() {
	        return list08;
	    }

	    /**
	     * Sets the value of the list08 property.
	     * 
	     * @param value
	     *     allowed object is
	     *     {@link Integer }
	     *     
	     */
	    public void setList08(Integer value)
	    {
	    	if (hasChanged(this.list08, value))
	    	{
	    		this.dataChanged = true;
	    	}
	        this.list08 = value;
	    }

	    /**
	     * Gets the value of the list09 property.
	     * 
	     * @return
	     *     possible object is
	     *     {@link Integer }
	     *     
	     */
	    public Integer getList09() {
	        return list09;
	    }

	    /**
	     * Sets the value of the list09 property.
	     * 
	     * @param value
	     *     allowed object is
	     *     {@link Integer }
	     *     
	     */
	    public void setList09(Integer value)
	    {
	    	if (hasChanged(this.list09, value))
	    	{
	    		this.dataChanged = true;
	    	}
	        this.list09 = value;
	    }

	    /**
	     * Gets the value of the list10 property.
	     * 
	     * @return
	     *     possible object is
	     *     {@link Integer }
	     *     
	     */
	    public Integer getList10() {
	        return list10;
	    }

	    /**
	     * Sets the value of the list10 property.
	     * 
	     * @param value
	     *     allowed object is
	     *     {@link Integer }
	     *     
	     */
	    public void setList10(Integer value)
	    {
	    	if (hasChanged(this.list10, value))
	    	{
	    		this.dataChanged = true;
	    	}
	        this.list10 = value;
	    }
	    
	    /**
	     * Gets the value of the lastUpdateDate property.
	     * 
	     * @return
	     *     possible object is
	     *     {@link Date }
	     *     
	     */
	    public Date getLastUpdateDate()
	    {
	        return lastUpdateDate;
	    }

	    /**
	     * Sets the value of the lastUpdateDate property.
	     * 
	     * @param value
	     *     allowed object is
	     *     {@link Date }
	     *     
	     */
	    public void setLastUpdateDate(Date value)
	    {
	    	//We don't update the data-changed flag for this field
	        this.lastUpdateDate = value;
	    }
}
