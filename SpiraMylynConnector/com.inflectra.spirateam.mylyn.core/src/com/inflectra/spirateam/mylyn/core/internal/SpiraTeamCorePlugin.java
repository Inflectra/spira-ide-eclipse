/*******************************************************************************
 * Copyright (c) 2006-2010 Inflectra Corporation.
 * Inflectra, SpiraTest, SpiraPlan and SpiraTeam are either registered trademarks
 * or trademarks of Inflectra Corporation in the U.S. and/or other countries.
 *******************************************************************************/

package com.inflectra.spirateam.mylyn.core.internal;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;
import org.eclipse.mylyn.tasks.core.RepositoryStatus;
import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.osgi.util.NLS;

import com.inflectra.spirateam.mylyn.core.internal.services.SpiraAuthenticationException;
import com.inflectra.spirateam.mylyn.core.internal.services.SpiraConnectionException;
import com.inflectra.spirateam.mylyn.core.internal.services.SpiraException;

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
	
	//The predefined queries/filters
	public static String MY_ASSIGNED_REQUIREMENTS = "1";
	public static String MY_ASSIGNED_INCIDENTS = "2";
	public static String MY_ASSIGNED_TASKS = "3";
	
	//Custom Property types
	public static int CustomPropertyType_Text = 1;
	public static int CustomPropertyType_List = 2;
	
	private SpiraTeamRepositoryConnector connector;
	
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
		if (connector != null)
		{
			connector.stop();
			connector = null;
		}
		
		plugin = null;
		super.stop(context);
	}
	
	public SpiraTeamRepositoryConnector getConnector()
	{
		return connector;
	}

	void setConnector(SpiraTeamRepositoryConnector connector)
	{
		this.connector = connector;
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
	
	/**
	 * Returns the path to the file caching repository attributes.
	 */
	protected IPath getRepostioryAttributeCachePath()
	{
		IPath stateLocation = Platform.getStateLocation(getBundle());
		IPath cacheFile = stateLocation.append("repositoryConfigurations"); //$NON-NLS-1$
		return cacheFile;
	}
	
	public static IStatus toStatus(TaskRepository repository, Throwable e)
	{
		String url = repository.getRepositoryUrl();
		if (e instanceof SpiraAuthenticationException)
		{
			return RepositoryStatus.createLoginError(url, PLUGIN_ID);
		}
		else if (e instanceof SpiraConnectionException)
		{
			return new RepositoryStatus(url, IStatus.ERROR, PLUGIN_ID, RepositoryStatus.ERROR_IO, e.getMessage(), e);
		}
		else if (e instanceof SpiraException)
		{
			return new RepositoryStatus(url, IStatus.ERROR, PLUGIN_ID, RepositoryStatus.ERROR_REPOSITORY,
					e.getMessage(), e);
		}
		else
		{
			return RepositoryStatus.createInternalError(PLUGIN_ID, Messages.SpiraTeamCorePlugin_UnexpectedError, e); //$NON-NLS-1$
		}
	}
}
