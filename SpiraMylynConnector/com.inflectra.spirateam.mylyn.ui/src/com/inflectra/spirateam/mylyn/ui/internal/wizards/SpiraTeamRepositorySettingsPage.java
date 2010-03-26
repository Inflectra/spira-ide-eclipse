/**
 * 
 */
package com.inflectra.spirateam.mylyn.ui.internal.wizards;

import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.mylyn.tasks.ui.wizards.AbstractRepositorySettingsPage;
import org.eclipse.swt.widgets.Composite;
import java.net.MalformedURLException;
import java.net.URL;

import com.inflectra.spirateam.mylyn.core.internal.SpiraTeamCorePlugin;

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
		//TODO: Add validator to check SpiraTeam version/API
		setNeedsValidation(false);
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
		// TODO Auto-generated method stub
		return null;
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

}
