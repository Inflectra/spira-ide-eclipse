package com.inflectra.spirateam.mylyn.core.internal.rest;

import java.util.ArrayList;
import java.util.Date;

public class RemoteArtifact {
    /// <summary>
    /// The id of the project that the artifact belongs to
    /// </summary>
    public int ProjectId;

    /// <summary>
    /// The type of artifact that we have
    /// </summary>
    
    public int ArtifactTypeId;

    /// <summary>
    /// The datetime used to track optimistic concurrency to prevent edit conflicts
    /// </summary>
    public String ConcurrencyDate;

    /// <summary>
    /// The list of associated custom properties/fields for this artifact
    /// </summary>
    public ArrayList<RemoteArtifactCustomProperty> CustomProperties;

    /// <summary>
    /// Does this artifact have any attachments?
    /// </summary>
    
    public boolean IsAttachments;

    /// <summary>
    /// The list of meta-tags that should be associated with the artifact
    /// </summary>
    public String Tags;

}
