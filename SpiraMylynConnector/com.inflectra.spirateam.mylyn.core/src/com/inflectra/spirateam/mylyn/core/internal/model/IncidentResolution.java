/**
 * 
 */
package com.inflectra.spirateam.mylyn.core.internal.model;

import java.util.Date;
import javax.xml.datatype.XMLGregorianCalendar;

import com.inflectra.spirateam.mylyn.core.internal.SpiraTeamUtil;
import com.inflectra.spirateam.mylyn.core.internal.rest.RemoteComment;

/**
 * @author Inflectra Corporation
 * Represents a single Incident comment/resolution
 */
public class IncidentResolution
{
    protected Integer incidentResolutionId;
    protected int incidentId;
    protected int creatorId;
    protected String resolution;
    protected Date creationDate;
    protected String creatorName;
    
    /**
     * Creates an incident resolution based on its equivalent SOAP object
     * @param remoteIncidentResolution The SOAP incident resolution
     */
    public IncidentResolution(RemoteComment remoteComment)
    {
    	//Set the various member variables
    	this.incidentResolutionId = remoteComment.CommentId;
    	this.incidentId = remoteComment.ArtifactId;
    	this.creatorId = remoteComment.UserId;
    	this.resolution = remoteComment.Text;
        this.creationDate = SpiraTeamUtil.convertDatesToLocal(remoteComment.CreationDate);
    	this.creatorName = remoteComment.UserName;
    }
    
    /**
     * Gets the value of the incidentResolutionId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIncidentResolutionId()
    {
        return incidentResolutionId;
    }

    /**
     * Sets the value of the incidentResolutionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIncidentResolutionId(Integer value) {
        this.incidentResolutionId = value;
    }

    /**
     * Gets the value of the incidentId property.
     * 
     */
    public int getIncidentId()
    {
        return incidentId;
    }

    /**
     * Sets the value of the incidentId property.
     * 
     */
    public void setIncidentId(int value)
    {
        this.incidentId = value;
    }

    /**
     * Gets the value of the creatorId property.
     * 
     */
    public int getCreatorId()
    {
        return creatorId;
    }

    /**
     * Sets the value of the creatorId property.
     * 
     */
    public void setCreatorId(int value)
    {
        this.creatorId = value;
    }

    /**
     * Gets the value of the resolution property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResolution()
    {
        return resolution;
    }

    /**
     * Sets the value of the resolution property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResolution(String value)
    {
        this.resolution = value;
    }

    /**
     * Gets the value of the creationDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public Date getCreationDate()
    {
        return this.creationDate;
    }

    /**
     * Gets the value of the creatorName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreatorName()
    {
        return creatorName;
    }
}
