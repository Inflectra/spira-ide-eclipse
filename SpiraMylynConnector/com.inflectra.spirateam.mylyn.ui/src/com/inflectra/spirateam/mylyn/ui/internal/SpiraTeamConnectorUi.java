package com.inflectra.spirateam.mylyn.ui.internal;

import org.eclipse.jface.wizard.IWizard;
import org.eclipse.mylyn.tasks.core.IRepositoryQuery;
import org.eclipse.mylyn.tasks.core.ITaskMapping;
import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.mylyn.tasks.ui.AbstractRepositoryConnectorUi;
import org.eclipse.mylyn.tasks.ui.wizards.ITaskRepositoryPage;

import com.inflectra.spirateam.mylyn.core.internal.SpiraTeamCorePlugin;

public class SpiraTeamConnectorUi extends AbstractRepositoryConnectorUi
{

	public SpiraTeamConnectorUi()
	{
		// TODO Auto-generated constructor stub
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IWizard getQueryWizard(TaskRepository taskRepository,
			IRepositoryQuery queryToEdit)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ITaskRepositoryPage getSettingsPage(TaskRepository taskRepository)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasSearchPage()
	{
		// TODO Auto-generated method stub
		return false;
	}

}
