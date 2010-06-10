package com.smartesoft.smarteqm.mylyn.ui.internal.editor;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS
{
	private static final String BUNDLE_NAME = "com.inflectra.spirateam.mylyn.ui.internal.editor.messages"; //$NON-NLS-1$

	static
	{
		// load message values from bundle file
		reloadMessages();
	}

	public static void reloadMessages()
	{
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	public static String TaskEditorFactory_PageText;
	public static String TaskEditorActionPart_Submit;
	public static String TaskEditorActionPart_Submit_to_X;
	public static String SpiraTeamActionsPart_PartName;
	public static String TaskEditorActionPart_Attach_Context;
}
