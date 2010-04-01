/*******************************************************************************
 * Copyright (c) 2006-2010 Inflectra Corporation.
 * Inflectra, SpiraTest, SpiraPlan and SpiraTeam are either registered trademarks
 * or trademarks of Inflectra Corporation in the U.S. and/or other countries.
 *******************************************************************************/

package com.inflectra.spirateam.mylyn.core.internal;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;
import org.eclipse.mylyn.tasks.core.RepositoryStatus;
import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.osgi.util.NLS;

/**
 * The headless Spira plug-in class.
 * 
 * @author Inflectra Corporation
 */
public class SpiraTeamCorePlugin extends Plugin
{

	// The plug-in ID
	public static final String PLUGIN_ID = "com.inflectra.spira.mylyn.core";	//$NON-NLS-1$
	
	//The connector name and description
	public final static String CONNECTOR_KIND = "spira";
	public final static String LABEL = NLS.bind(Messages.SpiraTeamCorePlugin_SpiraTeam_description, "v2.3.1");	//$NON-NLS-1$

	// The shared instance
	private static SpiraTeamCorePlugin plugin;
	
	/**
	 * The constructor
	 */
	public SpiraTeamCorePlugin()
	{
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.Plugins#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception
	{
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception
	{
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static SpiraTeamCorePlugin getDefault()
	{
		return plugin;
	}

	public static IStatus toStatus(TaskRepository repository, Throwable e)
	{
		String url = repository.getRepositoryUrl();
		/*
		if (e instanceof JiraAuthenticationException)
		{
			return RepositoryStatus.createLoginError(url, PLUGIN_ID);
		}
		else if (e instanceof JiraServiceUnavailableException)
		{
			return new RepositoryStatus(url, IStatus.ERROR, PLUGIN_ID, RepositoryStatus.ERROR_IO, e.getMessage(), e);
		}
		else if (e instanceof JiraRemoteMessageException)
		{
			return RepositoryStatus.createHtmlStatus(url, IStatus.ERROR, PLUGIN_ID, RepositoryStatus.ERROR_REPOSITORY,
					e.getMessage(), ((JiraRemoteMessageException) e).getHtmlMessage());
		}
		else if (e instanceof JiraException)
		{
			return new RepositoryStatus(url, IStatus.ERROR, PLUGIN_ID, RepositoryStatus.ERROR_REPOSITORY,
					e.getMessage(), e);
		}
		else
		{*/
			return RepositoryStatus.createInternalError(PLUGIN_ID, Messages.SpiraTeamCorePlugin_UnexpectedError, e); //$NON-NLS-1$
		/*}*/
	}
}
