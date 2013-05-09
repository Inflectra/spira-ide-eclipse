package com.inflectra.spirateam.mylyn.ui.internal.editor;

import org.eclipse.mylyn.internal.tasks.ui.editors.DateAttributeEditor;
import org.eclipse.mylyn.tasks.core.data.TaskAttribute;
import org.eclipse.mylyn.tasks.core.data.TaskDataModel;

public class DateAttributeEditorEx extends DateAttributeEditor
{
	private TaskAttribute taskAttribute;

	public DateAttributeEditorEx(TaskDataModel manager, TaskAttribute taskAttribute)
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
