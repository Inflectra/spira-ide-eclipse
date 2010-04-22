package com.inflectra.spirateam.mylyn.ui.internal;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.mylyn.tasks.core.IRepositoryQuery;
import org.eclipse.mylyn.tasks.core.ITask;
import org.eclipse.mylyn.tasks.core.ITaskMapping;
import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.mylyn.tasks.ui.AbstractRepositoryConnectorUi;
import org.eclipse.mylyn.tasks.ui.wizards.ITaskRepositoryPage;
import org.eclipse.mylyn.tasks.ui.wizards.RepositoryQueryWizard;

import com.inflectra.spirateam.mylyn.core.internal.ArtifactType;
import com.inflectra.spirateam.mylyn.core.internal.SpiraTeamCorePlugin;
import com.inflectra.spirateam.mylyn.ui.internal.wizards.SpiraTeamNamedFilterPage;
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
	public String getTaskKindLabel(ITask repositoryTask)
	{
		//Display the appropriate label depending on artifact type
		ArtifactType artifactType = ArtifactType.byTaskKey(repositoryTask.getTaskKey());
		if (artifactType != null)
		{
			return artifactType.getDisplayName();
		}
		return null;
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
		RepositoryQueryWizard wizard = new RepositoryQueryWizard(taskRepository);
		//The SpiraTeam connector doesn't currently allow ad-hoc querying
		//so for now we only allow the display of pre-defined queries
		if (queryToEdit == null)
		{
			wizard.addPage(new SpiraTeamNamedFilterPage(taskRepository));
		}
		else
		{		
			wizard.addPage(new SpiraTeamNamedFilterPage(taskRepository, queryToEdit));
		}
		return wizard;
	}

	@Override
	public ITaskRepositoryPage getSettingsPage(TaskRepository taskRepository)
	{
		//Returns the handle to the Spira settings page
		return new SpiraTeamRepositorySettingsPage(taskRepository);
	}
	

	@Override
	public ImageDescriptor getTaskKindOverlay(ITask repositoryTask)
	{
		//Display the appropriate overlay depending on artifact type
		ArtifactType artifactType = ArtifactType.byTaskKey(repositoryTask.getTaskKey());
		if (artifactType == ArtifactType.REQUIREMENT)
		{
			return SpiraTeamImages.OVERLAY_REQUIREMENT;
		}
		else if (artifactType == ArtifactType.INCIDENT)
		{
			return SpiraTeamImages.OVERLAY_INCIDENT;
		}
		else if (artifactType == ArtifactType.TASK)
		{
			return SpiraTeamImages.OVERLAY_TASK;
		}
		return super.getTaskKindOverlay(repositoryTask);
	}

	@Override
	public boolean hasSearchPage()
	{
		// The SpiraTeam connector doesn't currently allow ad-hoc searching
		return false;
	}

}
