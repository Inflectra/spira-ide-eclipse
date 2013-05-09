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

	public RichTextAttributeEditorEx(TaskDataModel manager, TaskRepository taskRepository, TaskAttribute taskAttribute)
	{
		super(manager, taskRepository, taskAttribute);
		this.taskAttribute = taskAttribute;
	}

	public RichTextAttributeEditorEx(TaskDataModel manager, TaskRepository taskRepository, TaskAttribute taskAttribute, int style)
	{
		super(manager, taskRepository, taskAttribute, style);
		this.taskAttribute = taskAttribute;
	}

	public RichTextAttributeEditorEx(TaskDataModel manager, TaskRepository taskRepository, TaskAttribute taskAttribute, int style,
			IContextService contextService, AbstractTaskEditorExtension extension)
	{
		super(manager, taskRepository, taskAttribute, style, contextService, extension);
		this.taskAttribute = taskAttribute;
	}
}
