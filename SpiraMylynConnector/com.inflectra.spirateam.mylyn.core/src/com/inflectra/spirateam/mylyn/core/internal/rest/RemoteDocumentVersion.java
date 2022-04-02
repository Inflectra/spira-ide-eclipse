package com.inflectra.spirateam.mylyn.core.internal.rest;

import java.util.Date;

public class RemoteDocumentVersion {
    /// <summary>
    /// The id of the document version
    /// </summary>
    public Integer AttachmentVersionId;

    /// <summary>
    /// The id of the document
    /// </summary>
    public int AttachmentId;

    /// <summary>
    /// The id of the user that uploaded the version
    /// </summary>
    /// <remarks>
    /// If no value is provided, the authenticated user is used
    /// </remarks>
    public Integer AuthorId;

    /// <summary>
    /// The filename of the file (if a file attachment) or the full URL if a URL attachment
    /// </summary>
    public String FilenameOrUrl;

    /// <summary>
    /// The description of the attachment version
    /// </summary>
    public String Description;

    /// <summary>
    /// The date/time the attachment version was uploaded
    /// </summary>
    public Date UploadDate;

    /// <summary>
    /// The size of the attachment version in bytes
    /// </summary>
    /// <remarks>
    /// Pass 0 for a URL attachment
    /// </remarks>
    public int Size;

    /// <summary>
    /// The version number
    /// </summary>
    public String VersionNumber;
    
    /// <summary>
    /// The display name of the user that uploaded the attachment version
    /// </summary>
    public String AuthorName;
}
