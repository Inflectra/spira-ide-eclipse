package com.inflectra.spirateam.mylyn.ui.internal.editor;

import org.eclipse.mylyn.internal.tasks.ui.editors.TextAttributeEditor;
import org.eclipse.mylyn.tasks.core.data.TaskAttribute;
import org.eclipse.mylyn.tasks.core.data.TaskDataModel;

public class TextAttributeEditorEx extends TextAttributeEditor
{
	private TaskAttribute taskAttribute;

	public TextAttributeEditorEx(TaskDataModel manager, TaskAttribute taskAttribute)
	{
		super(manager, taskAttribute);
		this.taskAttribute = taskAttribute;
	}

	@Override
	public void refresh()
	{
		super.refresh();
		SpiraTeamAttributeEditorCommon.ApplyAttributeStates(taskAttribute, this.getControl(), this.getLabelControl());
	}
}
