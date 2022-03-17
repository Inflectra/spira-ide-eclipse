package com.inflectra.spirateam.mylyn.core.internal.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.inflectra.spirateam.mylyn.core.internal.ArtifactType;
import com.inflectra.spirateam.mylyn.core.internal.SpiraTeamUtil;
import com.inflectra.spirateam.mylyn.core.internal.services.SpiraImportExport;
import com.inflectra.spirateam.mylyn.core.internal.rest.RemoteArtifact;
import com.inflectra.spirateam.mylyn.core.internal.rest.RemoteArtifactCustomProperty;

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
	protected Date concurrencyDate;

	protected List<ArtifactAttachment> attachments = new ArrayList<ArtifactAttachment>();
	protected List<ArtifactCustomProperty> customProperties = new ArrayList<ArtifactCustomProperty>();

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
		URL("common.url"),
		CONCURRENCY_DATE("common.concurrencyDate");

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

	 /**
	  * @return the attachments
	  */
	 public List<ArtifactAttachment> getAttachments()
	 {
		 return this.attachments;
	 }

	 /**
	  * @return the custom properties
	  */
	 public List<ArtifactCustomProperty> getCustomProperties()
	 {
		 return this.customProperties;
	 }


	 /**
	  * @return the concurrency date
	  */
	 public Date getConcurrencyDate()
	 {
		 return concurrencyDate;
	 }

	 /**
	  * Sets the value of the concurrency date property.
	  * 
	  * @param concurrencyDate
	  *     allowed object is
	  *     {@link Date }
	  *     
	  */
	 public void setConcurrencyDate(Date concurrencyDate)
	 {
		 this.concurrencyDate = concurrencyDate;
	 }

	 /**
	  * Populates all the artifact generic properties and custom properties
	  * @param remoteArtifact
	  */
	 protected void PopulateGeneralProperties(RemoteArtifact remoteArtifact)
	 {
		 //First the standard properties
         this.projectId = remoteArtifact.ProjectId;
		 this.concurrencyDate = remoteArtifact.ConcurrencyDate;
		 
		 //Make sure we have custom properties
		 this.customProperties.clear();
		 if (remoteArtifact.CustomProperties != null && !remoteArtifact.CustomProperties.isEmpty())
		 {
			 //Loop through each of the custom properties and populate the local object
			 List<RemoteArtifactCustomProperty> remoteArtifactCustomProperties = remoteArtifact.CustomProperties;
			 for (RemoteArtifactCustomProperty remoteArtifactCustomProperty : remoteArtifactCustomProperties)
			 {
				 if (remoteArtifactCustomProperty.PropertyNumber > 0)
				 {
					 ArtifactCustomProperty acp = new ArtifactCustomProperty();
					 acp.setPropertyNumber(remoteArtifactCustomProperty.PropertyNumber);
 					 this.customProperties.add(acp);
					 
					 //Handle each of the different data types
 					 acp.setStringValue(remoteArtifactCustomProperty.StringValue);
 					 acp.setIntegerValue(remoteArtifactCustomProperty.IntegerValue);
 					 acp.setBooleanValue(remoteArtifactCustomProperty.BooleanValue);
 					 acp.setDateTimeValue(SpiraTeamUtil.convertDatesToLocal(remoteArtifactCustomProperty.DateTimeValue));
 					 acp.setDecimalValue(remoteArtifactCustomProperty.DecimalValue);
 					 if (remoteArtifactCustomProperty.IntegerListValue == null)
 					 {
 						 acp.setIntegerListValue(null);
 					 }
 					 else
 					 {
 						 acp.setIntegerListValue(remoteArtifactCustomProperty.IntegerListValue);
 					 }
				 }
			 }
		 }
	 }
	 
	 /**
	  * Populates the API object from the artifact general fields and custom properties
	  * @param remoteArtifact
	  */
	 protected void ExtractGeneralProperties(RemoteArtifact remoteArtifact)
	 {
		 //First the standard fields
		 remoteArtifact.ProjectId = this.projectId;
		 remoteArtifact.ConcurrencyDate = this.concurrencyDate;

		 //Make sure we have custom properties
		 ArrayList<RemoteArtifactCustomProperty> remoteArtifactCustomProperties = new ArrayList<RemoteArtifactCustomProperty>();
 		 remoteArtifact.CustomProperties = remoteArtifactCustomProperties;
		 if (!this.customProperties.isEmpty())
		 {
			 //Loop through each of the custom properties and populate the API object
			 for (ArtifactCustomProperty acp : this.customProperties)
			 {
				 RemoteArtifactCustomProperty remoteArtifactCustomProperty = new RemoteArtifactCustomProperty();
				 remoteArtifactCustomProperty.PropertyNumber = acp.getPropertyNumber();
				 remoteArtifactCustomProperties.add(remoteArtifactCustomProperty);
				 
				 //Handle each of the different data types
				 remoteArtifactCustomProperty.StringValue = acp.getStringValue();
				 remoteArtifactCustomProperty.IntegerValue = acp.getIntegerValue();
				 remoteArtifactCustomProperty.BooleanValue = acp.getBooleanValue();
				 remoteArtifactCustomProperty.DateTimeValue = SpiraTeamUtil.convertDatesToUtc(acp.getDateTimeValue());
				 remoteArtifactCustomProperty.DecimalValue = acp.getDecimalValue();
				 remoteArtifactCustomProperty.IntegerListValue = (ArrayList<Integer>) acp.getIntegerListValue();
			 }
		 }
	 }
}
