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
import org.eclipse.mylyn.tasks.ui.editors.TaskEditorPartDescriptor;
import org.eclipse.mylyn.tasks.core.data.TaskAttribute;

import com.inflectra.spirateam.mylyn.core.internal.SpiraTeamCorePlugin;

/**
 * @author Inflectra Corporation
 *
 */
public class RequirementTaskEditorPage extends AbstractTaskEditorPage
{
	//private TracRenderingEngine renderingEngine;

	public RequirementTaskEditorPage(TaskEditor editor)
	{
		super(editor, SpiraTeamCorePlugin.CONNECTOR_KIND);
		setNeedsPrivateSection(true);
		setNeedsSubmitButton(true);
	}

	@Override
	protected Set<TaskEditorPartDescriptor> createPartDescriptors()
	{
		Set<TaskEditorPartDescriptor> descriptors = super.createPartDescriptors();
		// remove unnecessary default editor parts
		/*
		for (Iterator<TaskEditorPartDescriptor> it = descriptors.iterator(); it.hasNext();)
		{
			TaskEditorPartDescriptor taskEditorPartDescriptor = it.next();
			if (taskEditorPartDescriptor.getId().equals(ID_PART_PEOPLE))
			{
				it.remove();
			}
		}
		descriptors.add(new TaskEditorPartDescriptor(ID_PART_PEOPLE)
		{
			@Override
			public AbstractTaskEditorPart createPart()
			{
				return new TracPeoplePart();
			}
		}.setPath(PATH_PEOPLE));*/
		return descriptors;
	}

	@Override
	protected void createParts()
	{
		/*TODO: Add HTML preview
		if (renderingEngine == null)
		{
			renderingEngine = new TracRenderingEngine();
		}
		getAttributeEditorToolkit().setRenderingEngine(renderingEngine);*/
		super.createParts();
	}

	@Override
	protected AttributeEditorFactory createAttributeEditorFactory()
	{
		AttributeEditorFactory factory = new AttributeEditorFactory(getModel(), getTaskRepository(), getEditorSite())
		{
			@Override
			public AbstractAttributeEditor createEditor(String type, TaskAttribute taskAttribute)
			{
				/*
				if (TracAttribute.CC.getTracKey().equals(taskAttribute.getId()))
				{
					return new TracCcAttributeEditor(getModel(), taskAttribute);
				}*/
				return super.createEditor(type, taskAttribute);
			}
		};
		return factory;
	}
}
