package com.inflectra.spirateam.mylyn.ui.internal;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.jface.resource.ImageDescriptor;

/**
 * Handles the various images and icon overlays
 * 
 * @author Inflectra Corporation
 */
public class SpiraTeamImages
{
	private static final URL baseURL = SpiraTeamUiPlugin.getDefault().getBundle().getEntry("/icons/"); //$NON-NLS-1$
	
	public static final String PREFIX_OVERLAYS = "overlays"; //$NON-NLS-1$

	public static final ImageDescriptor OVERLAY_REQUIREMENT = create(PREFIX_OVERLAYS, "requirement.gif"); //$NON-NLS-1$
	public static final ImageDescriptor OVERLAY_TASK = create(PREFIX_OVERLAYS, "task.gif"); //$NON-NLS-1$
	public static final ImageDescriptor OVERLAY_INCIDENT = create(PREFIX_OVERLAYS, "incident.gif"); //$NON-NLS-1$
	
	private static ImageDescriptor create(String prefix, String name)
	{
		try
		{
			return ImageDescriptor.createFromURL(makeIconFileURL(prefix, name));
		}
		catch (MalformedURLException e)
		{
			return ImageDescriptor.getMissingImageDescriptor();
		}
	}
	
	private static URL makeIconFileURL(String prefix, String name) throws MalformedURLException
	{
		if (baseURL == null)
		{
			throw new MalformedURLException();
		}

		StringBuilder buffer = new StringBuilder(prefix);
		if (prefix != "")
		{ //$NON-NLS-1$
			buffer.append('/');
		}
		buffer.append(name);
		return new URL(baseURL, buffer.toString());
	}
}
