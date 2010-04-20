package com.inflectra.spirateam.mylyn.ui.internal.editor;

import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.mylyn.tasks.core.data.TaskAttribute;
import org.eclipse.mylyn.tasks.core.data.TaskDataModel;
import org.eclipse.mylyn.tasks.ui.editors.AbstractAttributeEditor;
import org.eclipse.mylyn.tasks.ui.editors.AttributeEditorFactory;

import com.inflectra.spirateam.mylyn.core.internal.ArtifactAttribute;

public class SpiraTeamAttributeEditorFactory extends AttributeEditorFactory
{
	private SpiraTeamTaskEditorPage editorPage;
	private TaskDataModel model;
	
	public SpiraTeamTaskEditorPage getEditorPage()
	{
		return this.editorPage;
	}
	
	public TaskDataModel getModel()
	{
		return this.model;
	}
	
	public SpiraTeamAttributeEditorFactory(TaskDataModel model, TaskRepository taskRepository, SpiraTeamTaskEditorPage editorPage)
	{
		super(model, taskRepository);
		this.editorPage = editorPage;
		this.model = model;
	}
	
	@Override
	public AbstractAttributeEditor createEditor(String type, TaskAttribute taskAttribute)
	{
		//If we have Incident Type, need to create a special select list that has
		//an event handler linked to it
		if (taskAttribute.getId().equals(ArtifactAttribute.INCIDENT_TYPE_ID.getArtifactKey()))
		{
			return new IncidentTypeAttributeEditor(getModel(), taskAttribute, editorPage);
		}
		else
		{
			return super.createEditor(type, taskAttribute);
		}
	}

}
