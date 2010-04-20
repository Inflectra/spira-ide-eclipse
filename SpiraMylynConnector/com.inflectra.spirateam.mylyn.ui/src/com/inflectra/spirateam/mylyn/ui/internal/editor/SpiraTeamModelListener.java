package com.inflectra.spirateam.mylyn.ui.internal.editor;

import java.util.Set;

import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.mylyn.tasks.core.data.TaskAttribute;
import org.eclipse.mylyn.tasks.core.data.TaskDataModelEvent;
import org.eclipse.mylyn.tasks.core.data.TaskDataModelListener;

import com.inflectra.spirateam.mylyn.core.internal.ArtifactAttribute;
import com.inflectra.spirateam.mylyn.core.internal.SpiraTeamRepositoryConnector;
import com.inflectra.spirateam.mylyn.core.internal.SpiraTeamTaskDataHandler;

public class SpiraTeamModelListener extends TaskDataModelListener
{
	private SpiraTeamTaskEditorPage editorPage;
	
	public SpiraTeamModelListener(SpiraTeamTaskEditorPage editorPage)
	{
		this.editorPage = editorPage;
	}
	
	public SpiraTeamTaskEditorPage getEditorPage()
	{
		return this.editorPage;
	}
	
	/**
	 * Called when an attribute changes
	 */
	@Override
	public void attributeChanged(TaskDataModelEvent event)
	{	
		try
		{
			//See if the Incident Type Changes
			event.getKind();
			TaskAttribute attribute = event.getTaskAttribute();
			if (attribute != null && attribute.getId().equals(ArtifactAttribute.INCIDENT_TYPE_ID.getArtifactKey()))
			{			
				//Tell the data-handler that we've changed incident type
				//and that we need to update the workflow and fields accordingly
				SpiraTeamRepositoryConnector connector = (SpiraTeamRepositoryConnector) getEditorPage().getConnector();
				TaskRepository repository = getEditorPage().getTaskRepository();
				if (connector != null)
				{
					SpiraTeamTaskDataHandler dataHandler = connector.getTaskDataHandler();
					if (dataHandler != null)
					{
						Set<TaskAttribute> changedAttributes = dataHandler.changeIncidentType(repository, attribute.getTaskData());

						//Now we need to make the editor know that the attributes have changed
						for (TaskAttribute changedAttribute : changedAttributes)
						{
							//Don't send a changed event for Type or we'll get into an infinite loop
							if (!changedAttribute.getId().equals(ArtifactAttribute.INCIDENT_TYPE_ID.getArtifactKey()))
							{
								getEditorPage().getModel().attributeChanged(changedAttribute);
							}
						}
									
						//Finally force a refresh so that the workflow field state changes take effect
						getEditorPage().refreshFormContent();
					}
				}
			}
		}
		catch (NumberFormatException ex)
		{
			//Ignore and leave fields alone
		}
	}

}
