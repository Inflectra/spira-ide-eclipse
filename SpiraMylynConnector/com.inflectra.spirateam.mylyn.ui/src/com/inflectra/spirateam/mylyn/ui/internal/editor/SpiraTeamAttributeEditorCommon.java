package com.inflectra.spirateam.mylyn.ui.internal.editor;

import org.eclipse.mylyn.tasks.core.data.TaskAttribute;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

import com.inflectra.spirateam.mylyn.core.internal.SpiraTeamTaskDataHandler;

/**
 * Provides some common functions used by the various attribute editors
 */
public class SpiraTeamAttributeEditorCommon
{

	public SpiraTeamAttributeEditorCommon()
	{
	}	
	
	public static void ApplyAttributeStates(TaskAttribute taskAttribute, Control control, Label label)
	{
		if (taskAttribute == null || control == null || label == null)
		{
			return;
		}
		
		String isRequired = taskAttribute.getMetaData().getValue(SpiraTeamTaskDataHandler.ATTRIBUTE_REQUIRED);
		String isAllowEmpty = taskAttribute.getMetaData().getValue(SpiraTeamTaskDataHandler.ATTRIBUTE_ALLOW_EMPTY);
		if ((isRequired != null && isRequired.equals("true")) || (isAllowEmpty != null && isAllowEmpty.equals("false")))
		{
			Font f = label.getFont();
			FontData[] fontData = f.getFontData();
			for (FontData fd : fontData)
			{
				// make bold
				fd.setStyle(fd.getStyle() | SWT.BOLD);
			}
			Font newFont = new Font(f.getDevice(), fontData);

			label.setFont(newFont);
		}
		else
		{
			Font f = label.getFont();
			FontData[] fontData = f.getFontData();
			for (FontData fd : fontData)
			{
				// Make un-bold
				if ((fd.getStyle() & SWT.BOLD) == SWT.BOLD)
				{
					fd.setStyle(fd.getStyle() ^ SWT.BOLD);
				}
			}
			Font newFont = new Font(f.getDevice(), fontData);

			label.setFont(newFont);
		}

		String isHidden = taskAttribute.getMetaData().getValue(SpiraTeamTaskDataHandler.ATTRIBUTE_HIDDEN);
		if (isHidden != null && isHidden.equals("true"))
		{
			control.setVisible(false);
			label.setVisible(false);
		}
		else
		{
			control.setVisible(true);
			label.setVisible(true);
		}
	}
}
