package com.inflectra.spirateam.mylyn.core.internal.rest;

public class RemoteVersion {
    /// <summary>
    /// The version number of the installation
    /// </summary>
	public String Version;

    /// <summary>
    /// The patch number (if any)
    /// </summary>
    /// <remarks>
    /// (Alpha) = -2
    /// (Beta) = -1
    /// </remarks>
	public Integer Patch;

}
