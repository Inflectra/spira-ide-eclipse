package com.inflectra.spirateam.mylyn.ui.internal.editor;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.mylyn.internal.tasks.ui.editors.RichTextAttributeEditor;
import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.mylyn.tasks.core.data.TaskAttribute;
import org.eclipse.mylyn.tasks.core.data.TaskDataModel;
import org.eclipse.mylyn.tasks.ui.editors.AbstractTaskEditorExtension;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.contexts.IContextService;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.xml.sax.SAXException;

import com.inflectra.spirateam.mylyn.core.internal.services.SpiraException;
import com.inflectra.spirateam.mylyn.ui.internal.controls.RichText;

public class RichTextAttributeEditorEx extends RichTextAttributeEditor
{
	private TaskAttribute taskAttribute;
	private RichText richText;
	int style;

	public RichTextAttributeEditorEx(TaskDataModel manager, TaskRepository taskRepository, TaskAttribute taskAttribute)
	{
		super(manager, taskRepository, taskAttribute);
		this.taskAttribute = taskAttribute;
		this.style = SWT.MULTI;
	}

	public RichTextAttributeEditorEx(TaskDataModel manager, TaskRepository taskRepository, TaskAttribute taskAttribute, int style)
	{
		super(manager, taskRepository, taskAttribute, style);
		this.taskAttribute = taskAttribute;
		this.style = style;
	}

	public RichTextAttributeEditorEx(TaskDataModel manager, TaskRepository taskRepository, TaskAttribute taskAttribute, int style,
			IContextService contextService, AbstractTaskEditorExtension extension)
	{
		super(manager, taskRepository, taskAttribute, style, contextService, extension);
		this.taskAttribute = taskAttribute;
		this.style = style;
	}

	@Override
	public void refresh()
	{		
		//Populate the control content
		try
		{
			if (richText != null)
			{
				String formattedText = this.getValue();
				richText.setFormattedText(formattedText);
			}
		}
		catch (ParserConfigurationException e)
		{
			//Fail quietly
		}
		catch (SAXException e)
		{
			//Fail quietly
			@SuppressWarnings("unused")
			String error = e.getMessage();
		}
		catch (IOException e)
		{
			//Fail quietly
		}
		
		//Handle the workflow states
		SpiraTeamAttributeEditorCommon.ApplyAttributeStates(taskAttribute, this.getControl(), this.getLabelControl());
	}
	
	public String getValue()
	{
		return getAttributeMapper().getValue(getTaskAttribute());
	}

	public String getValueLabel()
	{
		return getAttributeMapper().getValueLabel(getTaskAttribute());
	}
	
	public void setValue(String value)
	{
		String oldValue = getAttributeMapper().getValue(getTaskAttribute());
		if (!oldValue.equals(value))
		{
			getAttributeMapper().setValue(getTaskAttribute(), value);
			attributeChanged();
		}
	}

	@Override
	public void createControl(Composite parent, FormToolkit toolkit)
	{		
		//Create the old editor
		super.createControl(parent, toolkit);
		
		//Hide the old control
		this.getControl().setVisible(false);
		
		//Create our new rich text editor
		richText = new RichText(parent, this.style);
		toolkit.adapt(richText, false, false);
		setControl(richText);
		
		//Refresh the data
		refresh();
	}
}
