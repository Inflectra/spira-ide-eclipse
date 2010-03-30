package com.inflectra.spirateam.mylyn.ui.internal;

import org.eclipse.jface.wizard.IWizard;
import org.eclipse.mylyn.tasks.core.IRepositoryQuery;
import org.eclipse.mylyn.tasks.core.ITaskMapping;
import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.mylyn.tasks.ui.AbstractRepositoryConnectorUi;
import org.eclipse.mylyn.tasks.ui.wizards.ITaskRepositoryPage;

import com.inflectra.spirateam.mylyn.core.internal.SpiraTeamCorePlugin;
import com.inflectra.spirateam.mylyn.ui.internal.wizards.SpiraTeamRepositorySettingsPage;

public class SpiraTeamConnectorUi extends AbstractRepositoryConnectorUi
{

	/**
	 * Constructor
	 */
	public SpiraTeamConnectorUi()
	{
	}

	@Override
	public String getConnectorKind()
	{
		// Return the connector kind string
		return SpiraTeamCorePlugin.CONNECTOR_KIND;
	}

	@Override
	public IWizard getNewTaskWizard(TaskRepository taskRepository,
			ITaskMapping selection)
	{
		//The SpiraTeam connector doesn't currently allow addition of new tasks
		return null;
	}

	@Override
	public IWizard getQueryWizard(TaskRepository taskRepository,
			IRepositoryQuery queryToEdit)
	{
		//The SpiraTeam connector doesn't currently allow ad-hoc querying
		return null;
	}

	@Override
	public ITaskRepositoryPage getSettingsPage(TaskRepository taskRepository)
	{
		//Returns the handle to the Spira settings page
		return new SpiraTeamRepositorySettingsPage(taskRepository);
	}

	@Override
	public boolean hasSearchPage()
	{
		// The SpiraTeam connector doesn't currently allow ad-hoc searching
		return false;
	}

}
