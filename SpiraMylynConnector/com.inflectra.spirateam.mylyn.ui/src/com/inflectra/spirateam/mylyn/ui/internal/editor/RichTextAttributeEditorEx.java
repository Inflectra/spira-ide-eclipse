package com.inflectra.spirateam.mylyn.ui.internal.editor;

import org.eclipse.mylyn.internal.tasks.ui.editors.RichTextAttributeEditor;
import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.mylyn.tasks.core.data.TaskAttribute;
import org.eclipse.mylyn.tasks.core.data.TaskDataModel;
import org.eclipse.mylyn.tasks.ui.editors.AbstractTaskEditorExtension;
import org.eclipse.ui.contexts.IContextService;

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

	@Override
	public void refresh()
	{
		super.refresh();
		SpiraTeamAttributeEditorCommon.ApplyAttributeStates(taskAttribute, this.getControl(), this.getLabelControl());
	}

}
