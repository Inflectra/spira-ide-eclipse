package com.inflectra.spirateam.mylyn.ui.internal.wizards;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS
{
	private static final String BUNDLE_NAME = "com.inflectra.spirateam.mylyn.ui.internal.wizards.messages"; //$NON-NLS-1$

	static
	{
		// load message values from bundle file
		reloadMessages();
	}

	public static void reloadMessages()
	{
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	public static String MalformedURLException_Message;
	
	public static String SpiraTeamRepositorySettingsPage_Title;
	public static String SpiraTeamRepositorySettingsPage_Description;
	public static String SpiraTeamRepositorySettingsPage_MissingCredentials;
	public static String SpiraTeamRepositorySettingsPage_UnableToAuthenticate;
	
	public static String SpiraTeamNamedFilterPage_New_Named_Query;
	public static String SpiraTeamNamedFilterPage_Please_select_named_queries;
}
