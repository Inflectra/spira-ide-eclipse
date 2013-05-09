package com.inflectra.spirateam.mylyn.ui.internal.editor;

import org.eclipse.mylyn.internal.tasks.ui.editors.BooleanAttributeEditor;
import org.eclipse.mylyn.tasks.core.data.TaskAttribute;
import org.eclipse.mylyn.tasks.core.data.TaskDataModel;

@SuppressWarnings("restriction")
public class BooleanAttributeEditorEx extends BooleanAttributeEditor
{
	private TaskAttribute taskAttribute;

	public BooleanAttributeEditorEx(TaskDataModel manager, TaskAttribute taskAttribute)
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
