package com.inflectra.spirateam.mylyn.core.internal.model;

import java.util.Date;

import javax.xml.datatype.XMLGregorianCalendar;

import com.inflectra.spirateam.mylyn.core.internal.SpiraTeamUtil;
import com.inflectra.spirateam.mylyn.core.internal.rest.RemoteComment;

/**
 * @author Inflectra Corporation
 * Represents a single Task comment
 */
public class TaskComment
{
    protected Integer commentId;
    protected int taskId;
    protected int creatorId;
    protected String text;
    protected Date creationDate;
    protected String creatorName;
        
    /**
     * Creates a Task Comment based on its equivalent SOAP object
     * @param remoteComment The SOAP artifact resolution
     */
    public TaskComment(RemoteComment remoteComment)
    {
    	//Set the various member variables
    	this.commentId = remoteComment.CommentId;
    	this.taskId = remoteComment.ArtifactId;
    	this.creatorId = remoteComment.UserId;
    	this.text = remoteComment.Text;
        this.creationDate = SpiraTeamUtil.convertDatesToLocal(remoteComment.CreationDate);
    	this.creatorName = remoteComment.UserName;
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
     * Gets the value of the taskId property.
     * 
     */
    public int setTaskId()
    {
        return taskId;
    }

    /**
     * Sets the value of the taskId property.
     * 
     */
    public void setTaskId(int value)
    {
        this.taskId = value;
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
