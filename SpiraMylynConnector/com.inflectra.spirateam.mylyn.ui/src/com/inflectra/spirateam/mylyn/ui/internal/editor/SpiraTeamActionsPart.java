package com.inflectra.spirateam.mylyn.ui.internal.editor;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.mylyn.internal.provisional.commons.ui.CommonImages;
import org.eclipse.mylyn.tasks.core.data.TaskAttribute;
import org.eclipse.mylyn.tasks.core.data.TaskOperation;
import org.eclipse.mylyn.tasks.ui.TasksUiImages;
import org.eclipse.mylyn.tasks.ui.editors.AbstractTaskEditorPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.ui.forms.widgets.*;


public class SpiraTeamActionsPart extends AbstractTaskEditorPart
{	
	private static final String KEY_OPERATION = "operation"; //$NON-NLS-1$
	
	private TaskAttribute selectedOperationAttribute;
	private Button submitButton;
	private Button attachContextButton;
	private List<Button> operationButtons;
	
	public SpiraTeamActionsPart()
	{
		setPartName(Messages.SpiraTeamActionsPart_PartName);
	}
	
	@Override
	public void createControl(Composite parent, FormToolkit toolkit)
	{
		Section section = createSection(parent, toolkit, true);

		Composite buttonComposite = toolkit.createComposite(section);
		GridLayout buttonLayout = new GridLayout();
		buttonLayout.numColumns = 1;
		buttonComposite.setLayout(buttonLayout);

		/*if (getTaskEditorPage().needsAddToCategory())
		{
			createCategoryChooser(buttonComposite, toolkit);
		}*/

		selectedOperationAttribute = getTaskData().getRoot().getMappedAttribute(TaskAttribute.OPERATION);
		if (selectedOperationAttribute != null
				&& TaskAttribute.TYPE_OPERATION.equals(selectedOperationAttribute.getMetaData().getType()))
		{
			TaskOperation selectedOperation = getTaskData().getAttributeMapper().getTaskOperation(
					selectedOperationAttribute);
			createHyperlinks(buttonComposite, toolkit, selectedOperation);
		}

		//createOperationAttributes(buttonComposite, toolkit);

		createActionButtons(buttonComposite, toolkit);

		toolkit.paintBordersFor(buttonComposite);
		section.setClient(buttonComposite);
		setSection(toolkit, section);
	}
	
	private void createActionButtons(Composite buttonComposite, FormToolkit toolkit)
	{
		//if (!getTaskEditorPage().needsSubmitButton()) {
		submitButton = toolkit.createButton(buttonComposite, Messages.TaskEditorActionPart_Submit, SWT.NONE);
		submitButton.setImage(CommonImages.getImage(TasksUiImages.REPOSITORY_SUBMIT));
		submitButton.addListener(SWT.Selection, new Listener()
		{
			public void handleEvent(Event e)
			{
				getTaskEditorPage().doSubmit();
			}
		});
		Point minSize = submitButton.computeSize(SWT.DEFAULT, SWT.DEFAULT, true);
		GridData submitButtonData = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
		submitButtonData.widthHint = Math.max(100, minSize.x);
		submitButton.setLayoutData(submitButtonData);

		setSubmitEnabled(true);

		toolkit.createLabel(buttonComposite, "    "); //$NON-NLS-1$
		//}

		if (!getTaskData().isNew())
		{
			//addAttachContextButton(buttonComposite, toolkit);
		}
	}
	
	private void createHyperlinks(Composite buttonComposite, FormToolkit toolkit, TaskOperation selectedOperation)
	{
		List<TaskOperation> operations = getTaskData().getAttributeMapper().getTaskOperations(selectedOperationAttribute);
		if (operations.size() > 0)
		{
			operationButtons = new ArrayList<Button>();
			Button selectedButton = null;
			for (TaskOperation operation : operations)
			{
				Button button = toolkit.createButton(buttonComposite, operation.getLabel(), SWT.RADIO);
				button.setFont(TEXT_FONT);
				button.setData(KEY_OPERATION, operation);
				GridData radioData = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
				TaskAttribute associatedAttribute = getTaskData().getAttributeMapper().getAssoctiatedAttribute(
						operation);
				radioData.horizontalSpan = 4;
				button.setLayoutData(radioData);
				//TODO: Add Listener
				//button.addSelectionListener(new SelectButtonListener(button));
				operationButtons.add(button);
				if (operation.equals(selectedOperation))
				{
					selectedButton = button;
				}
			}
		}
	}
	
	public void setSubmitEnabled(boolean enabled)
	{
		if (submitButton != null && !submitButton.isDisposed()) {
			submitButton.setEnabled(enabled);
			if (enabled)
			{
				submitButton.setToolTipText(MessageFormat.format(Messages.TaskEditorActionPart_Submit_to_X,
						getTaskEditorPage().getTaskRepository().getRepositoryUrl()));
			}
		}
	}
}
