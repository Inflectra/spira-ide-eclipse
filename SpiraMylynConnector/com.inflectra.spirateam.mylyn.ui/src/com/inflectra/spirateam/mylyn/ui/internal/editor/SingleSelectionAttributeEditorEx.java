package com.inflectra.spirateam.mylyn.ui.internal.editor;

import org.eclipse.mylyn.internal.tasks.ui.editors.SingleSelectionAttributeEditor;
import org.eclipse.mylyn.tasks.core.data.TaskAttribute;
import org.eclipse.mylyn.tasks.core.data.TaskDataModel;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import com.inflectra.spirateam.mylyn.core.internal.SpiraTeamTaskDataHandler;

public class SingleSelectionAttributeEditorEx extends SingleSelectionAttributeEditor
{
	private TaskAttribute taskAttribute;
	
	public SingleSelectionAttributeEditorEx(TaskDataModel manager, TaskAttribute taskAttribute)
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
