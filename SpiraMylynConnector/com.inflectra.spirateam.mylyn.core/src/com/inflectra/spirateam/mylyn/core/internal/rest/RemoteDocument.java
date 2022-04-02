package com.inflectra.spirateam.mylyn.core.internal.rest;

import java.util.ArrayList;
import java.util.Date;

public class RemoteDocument extends RemoteArtifact {
    /// <summary>
    /// The id of the attachment
    /// </summary>
    public Integer AttachmentId;

    /// <summary>
    /// The id of the attachment type
    /// </summary>
    /// <remarks>
    /// File = 1,
    /// URL = 2
    /// </remarks>
    public int AttachmentTypeId;

    /// <summary>
    /// The id of the document type relative to the current project template
    /// </summary>
    public Integer DocumentTypeId;

    /// <summary>
    /// The id of the document status relative to the current project template
    /// </summary>
    public Integer DocumentStatusId;

    /// <summary>
    /// The id of the attachment folder id for the current project
    /// </summary>
    public Integer ProjectAttachmentFolderId;

    /// <summary>
    /// The list of artifacts the document is attached to
    /// </summary>
    public ArrayList<RemoteLinkedArtifact> AttachedArtifacts;

    /// <summary>
    /// The id of the user that uploaded the attachment
    /// </summary>
    /// <remarks>
    /// If no value is provided, the authenticated user is used
    /// </remarks>
    public Integer AuthorId;

    /// <summary>
    /// The id of the user that edited the document
    /// </summary>
    public Integer EditorId;

    /// <summary>
    /// The filename of the file (if a file attachment) or the full URL if a URL attachment
    /// </summary>
    public String FilenameOrUrl;

    /// <summary>
    /// The description of the attachment
    /// </summary>
    public String Description;

    /// <summary>
    /// The date/time the attachment was uploaded
    /// </summary>
    public Date UploadDate;

    /// <summary>
    /// The date/time the attachment was last edited
    /// </summary>
    public Date EditedDate;

    /// <summary>
    /// The size of the attachment in bytes
    /// </summary>
    /// <remarks>
    /// Pass 0 for a URL attachment
    /// </remarks>
    public int Size;

    /// <summary>
    /// The version name of the current attachment
    /// </summary>
    public String CurrentVersion;

    /// <summary>
    /// The list of document versions
    /// </summary>
    public ArrayList<RemoteDocumentVersion> Versions;

    /// <summary>
    /// The display name of the attachment type relative to the current project template
    /// </summary>
    /// <remarks>
    /// This is not whether it's a file or url, but the project-template specific classification
    /// </remarks>
    public String DocumentTypeName;

    /// <summary>
    /// The display name of the document status relative to the current project template
    /// </summary>
    public String DocumentStatusName;

    /// <summary>
    /// The display name of the attachment type (i.e. whether it's a file or url)
    /// </summary>
    public String AttachmentTypeName;
    
    /// <summary>
    /// The display name of the user that uploaded the attachment
    /// </summary>
    public String AuthorName;

    /// <summary>
    /// The display name of the user that edited the document
    /// </summary>
    public String EditorName;
}
