/**
 * 
 */
package com.inflectra.spirateam.mylyn.ui.internal.wizards;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.mylyn.commons.net.AuthenticationCredentials;
import org.eclipse.mylyn.commons.net.AuthenticationType;
import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.mylyn.tasks.ui.wizards.AbstractRepositorySettingsPage;
import org.eclipse.swt.widgets.Composite;
import java.net.MalformedURLException;
import java.net.URL;

import com.inflectra.spirateam.mylyn.core.internal.*;
import com.inflectra.spirateam.mylyn.core.internal.services.*;
import com.inflectra.spirateam.mylyn.ui.internal.*;

/**
 * Dialog used to specify a Spira repository address, username, and password.
 * 
 * @author Inflectra Corporation
 */
public class SpiraTeamRepositorySettingsPage extends
		AbstractRepositorySettingsPage
{
	private static final String TITLE = Messages.SpiraTeamRepositorySettingsPage_Title;
	private static final String DESCRIPTION = Messages.SpiraTeamRepositorySettingsPage_Description;

	public SpiraTeamRepositorySettingsPage(TaskRepository taskRepository)
	{
		super(TITLE, DESCRIPTION, taskRepository);

		setNeedsValidation(true);
		setNeedsHttpAuth(false);
		setNeedsAnonymousLogin(false);
		setNeedsEncoding(false);
		setNeedsTimeZone(false);
		setNeedsAdvanced(false);
		setNeedsProxy(false);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.mylyn.tasks.ui.wizards.AbstractRepositorySettingsPage#createAdditionalControls(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected void createAdditionalControls(Composite parent)
	{
		//No additional controls are needed
	}
	
	

	/*	OLD CODE - was an attempt to pre-load certain queries
	@Override
	public void applyTo(TaskRepository repository)
	{
		// First call super functionality
		super.applyTo(repository);
		
		//Next add the three predefined queries to the task repository
		PredefinedFilter[] filters = SpiraTeamUtil.createPredefinedFilters();
		for (PredefinedFilter filter : filters)
		{
			IRepositoryQuery query = TasksUi.getRepositoryModel().createRepositoryQuery(repository);
			query.setSummary(filter.getName());
			SpiraTeamUtil.setQuery(repository, query, filter);
		}
		
		//repository.
	}*/

	/* (non-Javadoc)
	 * @see org.eclipse.mylyn.tasks.ui.wizards.AbstractRepositorySettingsPage#getConnectorKind()
	 */
	@Override
	public String getConnectorKind()
	{
		// Return the connector kind string
		return SpiraTeamCorePlugin.CONNECTOR_KIND;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.mylyn.tasks.ui.wizards.AbstractRepositorySettingsPage#getValidator(org.eclipse.mylyn.tasks.core.TaskRepository)
	 */
	@Override
	protected Validator getValidator(TaskRepository repository)
	{
		return new SpiraTeamValidator(repository);
	}

	/**
	 * Validates the URL
	 */
	@Override
	protected boolean isValidUrl(String name)
	{
		if ((name.startsWith(URL_PREFIX_HTTPS) || name.startsWith(URL_PREFIX_HTTP)) && !name.endsWith("/"))
		{
			try
			{
				new URL(name);
				return true;
			}
			catch (MalformedURLException exception)
			{
			}
		}
		return false;
	}
	
	/**
	 * @author Inflectra Corporation
	 *
	 */
	private class SpiraTeamValidator extends Validator
	{
		final TaskRepository repository;

		//private ServerInfo serverInfo;

		public SpiraTeamValidator(TaskRepository repository)
		{
			this.repository = repository;
		}
		
		
		
		/* (non-Javadoc)
		 * @see org.eclipse.mylyn.tasks.ui.wizards.AbstractRepositorySettingsPage.Validator#run(org.eclipse.core.runtime.IProgressMonitor)
		 */
		@Override
		public void run(IProgressMonitor monitor) throws CoreException
		{
			try
			{
				//Instantiating the proxy class will automatically verify the URL
				String url = this.repository.getRepositoryUrl();
				AuthenticationCredentials credentials = this.repository.getCredentials(AuthenticationType.REPOSITORY);
				if (credentials == null)
				{
					throw new CoreException(new Status(IStatus.ERROR, SpiraTeamUiPlugin.PLUGIN_ID, IStatus.OK,
							Messages.SpiraTeamRepositorySettingsPage_MissingCredentials, null));	
				}
				String userName = credentials.getUserName();
				String password = credentials.getPassword();			
				SpiraImportExport spiraImportExport = new SpiraImportExport(url, userName, password);
			
				//Authenticate
				boolean success = spiraImportExport.connectionAuthenticate2();
				if (!success)
				{
					throw new CoreException(new Status(IStatus.ERROR, SpiraTeamUiPlugin.PLUGIN_ID, IStatus.OK,
							Messages.SpiraTeamRepositorySettingsPage_UnableToAuthenticate, null));	
				}
				
				//Now make sure that the version is current enough
				boolean current = SpiraTeamUtil.ValidateServerVersion(spiraImportExport);
				if (!current)
				{
					throw new CoreException(new Status(IStatus.ERROR, SpiraTeamUiPlugin.PLUGIN_ID, IStatus.OK,
							Messages.SpiraTeamRepositorySettingsPage_ServerVersionTooOld, null));
				}
			}
			catch (MalformedURLException ex)
			{
				throw new CoreException(new Status(IStatus.ERROR, SpiraTeamUiPlugin.PLUGIN_ID, IStatus.OK,
						Messages.MalformedURLException_Message, null));
			}
			catch (SpiraConnectionException ex)
			{
				throw new CoreException(new Status(IStatus.ERROR, SpiraTeamUiPlugin.PLUGIN_ID, IStatus.OK,
						ex.getMessage(), null));
			}	
		}
	}
}
