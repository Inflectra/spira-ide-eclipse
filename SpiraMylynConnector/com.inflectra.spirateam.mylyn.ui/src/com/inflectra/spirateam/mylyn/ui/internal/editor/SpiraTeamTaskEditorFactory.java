/**
 * 
 */
package com.inflectra.spirateam.mylyn.ui.internal.editor;

import org.eclipse.mylyn.tasks.ui.ITasksUiConstants;
import org.eclipse.mylyn.tasks.ui.TasksUiImages;
import org.eclipse.mylyn.tasks.ui.TasksUiUtil;
import org.eclipse.mylyn.tasks.ui.editors.AbstractTaskEditorPageFactory;
import org.eclipse.mylyn.tasks.ui.editors.BrowserFormPage;
import org.eclipse.mylyn.tasks.ui.editors.TaskEditor;
import org.eclipse.mylyn.tasks.ui.editors.TaskEditorInput;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.mylyn.internal.provisional.commons.ui.CommonImages;

import com.inflectra.spirateam.mylyn.core.internal.SpiraTeamCorePlugin;
import com.inflectra.spirateam.mylyn.core.internal.SpiraTeamRepositoryConnector;
import com.inflectra.spirateam.mylyn.ui.internal.SpiraTeamImages;

/**
 * @author Inflectra Corporation
 *
 */
public class SpiraTeamTaskEditorFactory extends AbstractTaskEditorPageFactory
{
	@Override
	public boolean canCreatePageFor(TaskEditorInput input)
	{
		if (input.getTask().getConnectorKind().equals(SpiraTeamCorePlugin.CONNECTOR_KIND))
		{
			return true;
		}
		else if (TasksUiUtil.isOutgoingNewTask(input.getTask(), SpiraTeamCorePlugin.CONNECTOR_KIND))
		{
			return true;
		}
		return false;
	}

	@Override
	public FormPage createPage(TaskEditor parentEditor)
	{
		/*TODO: Need to determine based on artifact type
		TaskEditorInput input = parentEditor.getTaskEditorInput();
		if (TasksUiUtil.isOutgoingNewTask(input.getTask(), SpiraTeamCorePlugin.CONNECTOR_KIND))
		{
			return new TracTaskEditorPage(parentEditor);
		}
		else if (SpiraTeamRepositoryConnector.hasRichEditor(input.getTaskRepository()))
		{
			return new TracTaskEditorPage(parentEditor);
		}
		else
		{
			return new BrowserFormPage(parentEditor, Messages.TracTaskEditorPageFactory_Browser);
		}*/
		return new RequirementTaskEditorPage(parentEditor);
	}

	@Override
	public String[] getConflictingIds(TaskEditorInput input)
	{
		if (SpiraTeamRepositoryConnector.hasRichEditor(input.getTaskRepository())
				|| TasksUiUtil.isOutgoingNewTask(input.getTask(), SpiraTeamCorePlugin.CONNECTOR_KIND))
		{
			return new String[] { ITasksUiConstants.ID_PAGE_PLANNING };
		}
		else
		{
			return super.getConflictingIds(input);
		}
	}

	@Override
	public Image getPageImage()
	{
		return CommonImages.getImageWithOverlay(TasksUiImages.REPOSITORY_SMALL, SpiraTeamImages.OVERLAY_SPIRA, false, false);
	}

	@Override
	public String getPageText()
	{
		return Messages.TaskEditorFactory_PageText; //$NON-NLS-1$
	}

	@Override
	public int getPriority()
	{
		return PRIORITY_TASK;
	}

}
