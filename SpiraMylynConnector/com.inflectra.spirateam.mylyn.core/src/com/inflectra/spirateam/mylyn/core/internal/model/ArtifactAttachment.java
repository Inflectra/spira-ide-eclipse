package com.inflectra.spirateam.mylyn.core.internal.model;

import java.util.Date;

import com.inflectra.spirateam.mylyn.core.internal.SpiraTeamUtil;
import com.inflectra.spirateam.mylyn.core.internal.services.soap.RemoteDocument;

/**
 * Represents a single SpiraTeam artifact attachment
 * 
 * @author Inflectra Corporation
 */
public class ArtifactAttachment
{
	protected Integer attachmentId;
	protected Integer authorId;
	protected String authorName;
	protected String filename;
	protected String description;
	protected Date creationDate;
	protected long size;
	protected boolean urlAttachment;
	
	private final String ATTACHMENT_PREFIX = "DC";
	
    /**
     * Creates an artifact attachment based on its equivalent SOAP object
     * @param remoteDocument The SOAP artifact attachment
     */
	public ArtifactAttachment(RemoteDocument remoteDocument)
	{
    	//Set the various member variables
    	this.attachmentId = remoteDocument.getAttachmentId().getValue();
    	this.authorId = remoteDocument.getAuthorId().getValue();
    	this.filename = remoteDocument.getFilenameOrUrl().getValue();
    	this.description = remoteDocument.getDescription().getValue();
        this.creationDate = SpiraTeamUtil.convertDatesXml2Java(remoteDocument.getUploadDate());
        this.size = remoteDocument.getSize();
        this.authorName = remoteDocument.getAuthorName().getValue();
        this.urlAttachment = (remoteDocument.getAttachmentTypeId().equals(2));
	}
	
	/**
	 * @return The attachment id with its prefix
	 */
	public String getAttachmentKey()
	{
		return ATTACHMENT_PREFIX + this.attachmentId;
	}
	
	/**
	 * @return the attachmentId
	 */
	public Integer getAttachmentId()
	{
		return this.attachmentId;
	}
	/**
	 * @param attachmentId the attachmentId to set
	 */
	public void setAttachmentId(Integer attachmentId)
	{
		this.attachmentId = attachmentId;
	}

	/**
	 * @return the authorId
	 */
	public Integer getAuthorId()
	{
		return this.authorId;
	}
	/**
	 * @param authorId the authorId to set
	 */
	public void setAuthorId(Integer authorId)
	{
		this.authorId = authorId;
	}
	/**
	 * @return the filename
	 */
	public String getFilename()
	{
		return this.filename;
	}
	/**
	 * @param filename the filename to set
	 */
	public void setFilename(String filename)
	{
		this.filename = filename;
	}
	/**
	 * @return the authorName
	 */
	public String getAuthorName()
	{
		return this.authorName;
	}

	/**
	 * @return the description
	 */
	public String getDescription()
	{
		return this.description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/**
	 * @return the creationDate
	 */
	public Date getCreationDate()
	{
		return this.creationDate;
	}

	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Date creationDate)
	{
		this.creationDate = creationDate;
	}

	/**
	 * @return the size
	 */
	public long getSize()
	{
		return this.size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(long size)
	{
		this.size = size;
	}

	/**
	 * @return the urlAttachment
	 */
	public boolean isUrlAttachment()
	{
		return this.urlAttachment;
	}

	/**
	 * @param urlAttachment the urlAttachment to set
	 */
	public void setUrlAttachment(boolean urlAttachment)
	{
		this.urlAttachment = urlAttachment;
	}
}
