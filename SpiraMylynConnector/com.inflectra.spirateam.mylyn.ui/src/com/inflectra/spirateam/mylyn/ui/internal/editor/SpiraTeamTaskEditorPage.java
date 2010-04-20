/**
 * 
 */
package com.inflectra.spirateam.mylyn.ui.internal.editor;

import java.util.Iterator;
import java.util.Set;

import org.eclipse.mylyn.tasks.ui.editors.AbstractAttributeEditor;
import org.eclipse.mylyn.tasks.ui.editors.AbstractTaskEditorPage;
import org.eclipse.mylyn.tasks.ui.editors.AbstractTaskEditorPart;
import org.eclipse.mylyn.tasks.ui.editors.AttributeEditorFactory;
import org.eclipse.mylyn.tasks.ui.editors.TaskEditor;
import org.eclipse.mylyn.tasks.ui.editors.TaskEditorInput;
import org.eclipse.mylyn.tasks.ui.editors.TaskEditorPartDescriptor;
import org.eclipse.mylyn.tasks.core.ITask;
import org.eclipse.mylyn.tasks.core.data.TaskAttribute;
import org.eclipse.mylyn.tasks.core.data.TaskDataModelListener;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;

import com.inflectra.spirateam.mylyn.core.internal.ArtifactAttribute;
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
		
		//Set the parts that are visible appropriately for each artifact type
		setNeedsPrivateSection(false);
		if (artifactType.equals(ArtifactType.REQUIREMENT))
		{
			setNeedsSubmitButton(false);
		}
		if (artifactType.equals(ArtifactType.TASK))
		{
			setNeedsSubmitButton(true);
		}
		if (artifactType.equals(ArtifactType.INCIDENT))
		{
			setNeedsSubmitButton(true);
		}
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
				//Requirements and Tasks don't currently support comments
				if (artifactType.equals(ArtifactType.REQUIREMENT) || artifactType.equals(ArtifactType.TASK))
				{
					it.remove();
				}
			}
			if (taskEditorPartDescriptor.getId().equals(ID_PART_ACTIONS))
			{
				//Requirements don't have Actions
				//Incidents and Tasks use a customized Actions Part
				it.remove();
			}
		}
		
		//Add the new Actions Part
		if (artifactType.equals(ArtifactType.INCIDENT) || artifactType.equals(ArtifactType.TASK))
		{
			descriptors.add(new TaskEditorPartDescriptor(ID_PART_ACTIONS)
			{
				@Override
				public AbstractTaskEditorPart createPart()
				{
					return new SpiraTeamActionsPart();
				}
			}.setPath(PATH_ACTIONS));
		}
		
		return descriptors;
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
