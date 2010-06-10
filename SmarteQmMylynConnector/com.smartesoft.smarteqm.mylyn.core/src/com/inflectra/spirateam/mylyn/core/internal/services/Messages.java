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
	public static String SpiraImportExport_ArtifactKeyNull;
	public static String SpiraImportExport_InvalidArtifactKey;
	public static String SpiraImportExport_UnableToConnectToProject;
	
	public static String TaskStatus_NotStarted;
	public static String TaskStatus_InProgress;
	public static String TaskStatus_Completed;
	public static String TaskStatus_Blocked;
	public static String TaskStatus_Deferred;
	
	public static String TaskPriority_Critical;
	public static String TaskPriority_High;
	public static String TaskPriority_Medium;
	public static String TaskPriority_Low;
	
	public static String RequirementStatus_Requested;
	public static String RequirementStatus_Planned;
	public static String RequirementStatus_InProgress;
	public static String RequirementStatus_Completed;
	public static String RequirementStatus_Accepted;
	public static String RequirementStatus_Rejected;
	public static String RequirementStatus_Evaluated;

	public static String RequirementImportance_Critical;
	public static String RequirementImportance_High;
	public static String RequirementImportance_Medium;
	public static String RequirementImportance_Low;
}
