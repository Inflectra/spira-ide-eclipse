package com.inflectra.spirateam.mylyn.core.internal;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS
{
	private static final String BUNDLE_NAME = "com.inflectra.spirateam.mylyn.core.internal.messages"; //$NON-NLS-1$

	static
	{
		// load message values from bundle file
		reloadMessages();
	}

	public static void reloadMessages()
	{
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
	
	public static String SpiraTeamCorePlugin_SpiraTeam_description;
	public static String SpiraTeamCorePlugin_UnexpectedError;

	public static String SpiraTeam_PredefinedFilter_MyRequirements;
	public static String SpiraTeam_PredefinedFilter_MyIncidents;
	public static String SpiraTeam_PredefinedFilter_MyTasks;
	public static String SpiraTeamRepositoryConnector_Query_Repository;
	public static String SpiraTeamRepositoryConnector_The_SpiraTeam_query_is_invalid;
}
