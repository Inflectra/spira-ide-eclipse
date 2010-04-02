package com.inflectra.spirateam.mylyn.core.internal.services;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS
{
	private static final String BUNDLE_NAME = "com.inflectra.spirateam.mylyn.core.internal.services.messages"; //$NON-NLS-1$

	static
	{
		// load message values from bundle file
		reloadMessages();
	}

	public static void reloadMessages()
	{
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
	
	public static String SpiraConnectionException_Message;
	public static String SpiraImportExport_UnableToAuthenticate;
}
