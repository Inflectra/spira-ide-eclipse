package com.inflectra.spirateam.mylyn.ui.internal.controls;

import org.eclipse.osgi.util.NLS;

import com.inflectra.spirateam.mylyn.ui.internal.controls.Messages;

public class Messages extends NLS
{
	private static final String BUNDLE_NAME = "com.inflectra.spirateam.mylyn.ui.internal.controls.messages"; //$NON-NLS-1$

	static
	{
		// load message values from bundle file
		reloadMessages();
	}

	public static void reloadMessages()
	{
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	public static String eraserBtn_tooltipText;
	public static String pasteBtn_tooltipText;
	public static String copyBtn_tooltipText;
	public static String cutBtn_tooltipText;
	public static String underlineBtn_tooltipText;
	public static String italicBtn_tooltipText;
	public static String boldBtn_tooltipText;
	public static String strikeThroughBtn_tooltipText;
}
