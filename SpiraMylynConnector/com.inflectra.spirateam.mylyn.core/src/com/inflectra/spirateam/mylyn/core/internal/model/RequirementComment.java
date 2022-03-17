package com.inflectra.spirateam.mylyn.core.internal.model;

import java.util.Date;

import javax.xml.datatype.XMLGregorianCalendar;

import com.inflectra.spirateam.mylyn.core.internal.SpiraTeamUtil;
import com.inflectra.spirateam.mylyn.core.internal.rest.RemoteComment;

/**
 * @author Inflectra Corporation
 * Represents a single Requirement comment
 */
public class RequirementComment
{
    protected Integer commentId;
    protected int requirementId;
    protected int creatorId;
    protected String text;
    protected Date creationDate;
    protected String creatorName;
        
    /**
     * Creates a Requirement Comment based on its equivalent SOAP object
     * @param remoteComment The SOAP artifact resolution
     */
    public RequirementComment(RemoteComment remoteComment)
    {
    	//Set the various member variables
    	this.commentId = remoteComment.getCommentId().getValue();
    	this.requirementId = remoteComment.getArtifactId();
    	this.creatorId = remoteComment.getUserId().getValue();
    	this.text = remoteComment.getText().getValue();
        this.creationDate = SpiraTeamUtil.convertDatesXml2Java(remoteComment.getCreationDate().getValue());
    	this.creatorName = remoteComment.getUserName().getValue();
    }
    
    /**
     * Gets the value of the commentId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCommentId()
    {
        return commentId;
    }

    /**
     * Sets the value of the commentId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCommentId(Integer value) {
        this.commentId = value;
    }

    /**
     * Gets the value of the requirementId property.
     * 
     */
    public int setRequirementId()
    {
        return requirementId;
    }

    /**
     * Sets the value of the requirementId property.
     * 
     */
    public void setRequirementId(int value)
    {
        this.requirementId = value;
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
     * Gets the value of the text property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getText()
    {
        return text;
    }

    /**
     * Sets the value of the text property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setText(String value)
    {
        this.text = value;
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
