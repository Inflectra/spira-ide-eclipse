/**
 * 
 */
package com.inflectra.spirateam.mylyn.ui.internal.wizards;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.mylyn.commons.net.AbstractWebLocation;
import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.mylyn.tasks.core.TaskRepositoryLocationFactory;
import org.eclipse.mylyn.tasks.ui.wizards.AbstractRepositorySettingsPage;
import org.eclipse.mylyn.tasks.ui.wizards.AbstractRepositorySettingsPage.Validator;
import org.eclipse.swt.widgets.Composite;
import java.net.MalformedURLException;
import java.net.URL;

import com.inflectra.spirateam.mylyn.core.internal.*;
import com.inflectra.spirateam.mylyn.core.internal.services.*;
import com.inflectra.spirateam.mylyn.core.internal.services.soap.*;
import com.inflectra.spirateam.mylyn.ui.internal.*;

/**
 * Dialog used to specify a Spira repository address, username, and password.
 * 
 * @author Inflectra Corporation
 */
public class SpiraTeamRepositorySettingsPage extends
		AbstractRepositorySettingsPage
{
	private static final String TITLE = "SpiraTeam Repository Settings";
	private static final String DESCRIPTION = "Example: http://www.mycompany.com/SpiraTeam";

	public SpiraTeamRepositorySettingsPage(TaskRepository taskRepository)
	{
		super(TITLE, DESCRIPTION, taskRepository);

		setNeedsValidation(true);
		setNeedsHttpAuth(false);
		setNeedsAnonymousLogin(false);
		setNeedsEncoding(false);
		setNeedsTimeZone(false);
		setNeedsAdvanced(false);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.mylyn.tasks.ui.wizards.AbstractRepositorySettingsPage#createAdditionalControls(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected void createAdditionalControls(Composite parent)
	{
		// TODO Auto-generated method stub

	}

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
			//First make sure the URL is valid
			try
			{
				new URL(repository.getRepositoryUrl());
			}
			catch (MalformedURLException ex)
			{
				throw new CoreException(new Status(IStatus.ERROR, SpiraTeamUiPlugin.PLUGIN_ID, IStatus.OK,
						"The URL to the repository is not valid URL", null));
			}

			//Now lets try and connect
			AbstractWebLocation location = new TaskRepositoryLocationFactory().createWebLocation(repository);
		
			SpiraImportExport spiraImportExport = new SpiraImportExport("http://localhost/SpiraTeam");
		}
	}
}
