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
	public static final String PREFIX_EDITOR = "editor";

	public static final ImageDescriptor OVERLAY_REQUIREMENT = create(PREFIX_OVERLAYS, "requirement.gif"); //$NON-NLS-1$
	public static final ImageDescriptor OVERLAY_TASK = create(PREFIX_OVERLAYS, "task.gif"); //$NON-NLS-1$
	public static final ImageDescriptor OVERLAY_INCIDENT = create(PREFIX_OVERLAYS, "incident.gif"); //$NON-NLS-1$
	public static final ImageDescriptor OVERLAY_SPIRA = create(PREFIX_OVERLAYS, "spira.gif"); //$NON-NLS-1$
	
	//Rich Text Editor
	public static final ImageDescriptor IMG_BOLD = create(PREFIX_EDITOR, "bold.gif"); //$NON-NLS-1$
	public static final ImageDescriptor IMG_ITALIC = create(PREFIX_EDITOR, "italic.gif"); //$NON-NLS-1$
	public static final ImageDescriptor IMG_UNDERLINE = create(PREFIX_EDITOR, "underline.gif"); //$NON-NLS-1$
	public static final ImageDescriptor IMG_STRIKE_THROUGH = create(PREFIX_EDITOR, "strikethrough.gif"); //$NON-NLS-1$
	public static final ImageDescriptor IMG_CUT = create(PREFIX_EDITOR, "cut.gif"); //$NON-NLS-1$
	public static final ImageDescriptor IMG_COPY = create(PREFIX_EDITOR, "copy.gif"); //$NON-NLS-1$
	public static final ImageDescriptor IMG_PASTE = create(PREFIX_EDITOR, "paste.gif"); //$NON-NLS-1$
	public static final ImageDescriptor IMG_ERASER = create(PREFIX_EDITOR, "eraser.gif"); //$NON-NLS-1$
	
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
