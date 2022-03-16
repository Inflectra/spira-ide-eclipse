package com.inflectra.spirateam.mylyn.core.internal.rest;

import java.util.Date;

public class RemoteComment {
	/// <summary>The unique ID for this comment / artifact type.</summary>
    public Integer CommentId;

	/// <summary>The artifact ID that this comment belongs to.</summary>
	public int ArtifactId;

	/// <summary>
    /// The userID of the author.
    /// </summary>
    /// <remarks>The authenticated user is used if no value provided</remarks>
    public Integer UserId;

	/// <summary>The full name of the author.</summary>
	public String UserName;

	/// <summary>The text of the comment.</summary>
	public String Text;

	/// <summary>The date and time the comment was made.</summary>
	public Date CreationDate;

	/// <summary>Whether the comment was marked for deletion or hidden.</summary>
	/// <remarks>Not currently used, should remain false.</remarks>
	public boolean IsDeleted;

    /// <summary>
    /// Whether the comment can be deleted or is a permanent one
    /// </summary>
    public boolean IsPermanent;
}
