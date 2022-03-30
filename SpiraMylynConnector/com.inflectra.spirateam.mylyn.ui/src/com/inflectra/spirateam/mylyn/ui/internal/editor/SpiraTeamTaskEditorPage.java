/**
 * 
 */
package com.inflectra.spirateam.mylyn.ui.internal.editor;

import java.util.Iterator;
import java.util.Set;

import org.eclipse.mylyn.tasks.ui.editors.AbstractTaskEditorPage;
import org.eclipse.mylyn.tasks.ui.editors.AbstractTaskEditorPart;
import org.eclipse.mylyn.tasks.ui.editors.AttributeEditorFactory;
import org.eclipse.mylyn.tasks.ui.editors.TaskEditor;
import org.eclipse.mylyn.tasks.ui.editors.TaskEditorInput;
import org.eclipse.mylyn.tasks.ui.editors.TaskEditorPartDescriptor;
import org.eclipse.mylyn.tasks.core.ITask;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;

import com.inflectra.spirateam.mylyn.core.internal.ArtifactType;
import com.inflectra.spirateam.mylyn.core.internal.SpiraTeamCorePlugin;

/**
 * @author Inflectra Corporation
 *
 */
public class SpiraTeamTaskEditorPage extends AbstractTaskEditorPage
{
	private ArtifactType artifactType = null;
	
	public SpiraTeamTaskEditorPage(TaskEditor parentEditor)
	{
		super(parentEditor, SpiraTeamCorePlugin.CONNECTOR_KIND);

		//Need to see what kind of artifact we're displaying
		TaskEditorInput input = parentEditor.getTaskEditorInput();
		ITask task = input.getTask();
		artifactType = null;
		if (task != null)
		{
			String taskKey = task.getTaskKey();
			if (taskKey != null)
			{
				artifactType = ArtifactType.byTaskKey(taskKey);
			}
		}
		
		//Set the parts that are visible
		setNeedsPrivateSection(false);
		setNeedsSubmitButton(true);
	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
	{
		super.init(site, input);
	}
	
	@Override
	protected Set<TaskEditorPartDescriptor> createPartDescriptors()
	{
		Set<TaskEditorPartDescriptor> descriptors = super.createPartDescriptors();
		// remove unnecessary default editor parts
		for (Iterator<TaskEditorPartDescriptor> it = descriptors.iterator(); it.hasNext();)
		{
			TaskEditorPartDescriptor taskEditorPartDescriptor = it.next();
			
			if (taskEditorPartDescriptor.getId().equals(ID_PART_COMMENTS))
			{
				//All artifacts support comments
			}
			if (taskEditorPartDescriptor.getId().equals(ID_PART_ACTIONS))
			{
				//Spira artifacts use a customized Actions Part with workflow operations
				it.remove();
			}
		}
		
		//Add the new Actions Part
		descriptors.add(new TaskEditorPartDescriptor(ID_PART_ACTIONS)
		{
			@Override
			public AbstractTaskEditorPart createPart()
			{
				return new SpiraTeamActionsPart();
			}
		}.setPath(PATH_ACTIONS));
		
		return descriptors;
	}

	/**
	 * Refreshes just the parts affected by a change in incident type
	 */
	public void refreshAfterTypeChange()
	{
		//Refresh the actions part
		AbstractTaskEditorPart part = getPart(ID_PART_ACTIONS);
		if (part != null)
		{
			part.refresh();
		}
		
		//Now refresh the summary
		part = getPart(ID_PART_SUMMARY);
		if (part != null)
		{
			part.refresh();
		}

		//Now refresh the description
		part = getPart(ID_PART_SUMMARY);
		if (part != null)
		{
			part.refresh();
		}
		
		//Now refresh all attributes
		part = getPart(ID_PART_ATTRIBUTES);
		if (part != null)
		{
			part.refresh();
		}
	}
	
	@Override
	protected void createParts()
	{
		super.createParts();
	}

	@Override
	protected AttributeEditorFactory createAttributeEditorFactory()
	{
		AttributeEditorFactory factory = new SpiraTeamAttributeEditorFactory(getModel(), getTaskRepository(), this);
		return factory;
	}
}
