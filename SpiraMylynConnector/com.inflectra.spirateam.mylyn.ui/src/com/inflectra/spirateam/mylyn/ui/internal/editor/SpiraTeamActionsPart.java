package com.inflectra.spirateam.mylyn.ui.internal.editor;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.mylyn.tasks.core.data.TaskAttribute;
import org.eclipse.mylyn.tasks.core.data.TaskData;
import org.eclipse.mylyn.tasks.core.data.TaskOperation;
import org.eclipse.mylyn.tasks.ui.TasksUiImages;
import org.eclipse.mylyn.tasks.ui.editors.AbstractTaskEditorPart;
import org.eclipse.mylyn.commons.ui.CommonImages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.events.IHyperlinkListener;
import org.eclipse.ui.forms.widgets.*;

import com.inflectra.spirateam.mylyn.core.internal.ArtifactAttribute;
import com.inflectra.spirateam.mylyn.core.internal.ArtifactType;
import com.inflectra.spirateam.mylyn.core.internal.SpiraTeamRepositoryConnector;
import com.inflectra.spirateam.mylyn.core.internal.SpiraTeamTaskDataHandler;
import com.inflectra.spirateam.mylyn.core.internal.SpiraTeamUtil;


public class SpiraTeamActionsPart extends AbstractTaskEditorPart
{	
	private static final String KEY_OPERATION = "operation"; //$NON-NLS-1$
	
	private TaskAttribute selectedOperationAttribute;
	private Button submitButton;
	private Button attachContextButton;
	private List<Hyperlink> operationButtons;
	
	public SpiraTeamActionsPart()
	{
		setPartName(Messages.SpiraTeamActionsPart_PartName);
	}

	@Override
	public void refresh()
	{
		super.refresh();
		
		//Make the hyperlinks enabled or disabled based on the workflow status field
		TaskData taskData = getTaskData();
		
		//See what kind of artifact we have
		ArtifactType artifactType = ArtifactType.byTaskKey(taskData.getTaskId());
		if (artifactType.equals(ArtifactType.INCIDENT))
		{
			String workflowFieldStatus = taskData.getRoot().getAttribute(ArtifactAttribute.INCIDENT_TRANSITION_STATUS.getArtifactKey()).getValue();
			for (Hyperlink button : operationButtons)
			{
				button.setEnabled(workflowFieldStatus.equals(SpiraTeamUtil.WORKFLOW_TRANSITION_STATUS_ACTIVE));
			}
		}
		if (artifactType.equals(ArtifactType.REQUIREMENT))
		{
			String workflowFieldStatus = taskData.getRoot().getAttribute(ArtifactAttribute.REQUIREMENT_TRANSITION_STATUS.getArtifactKey()).getValue();
			for (Hyperlink button : operationButtons)
			{
				button.setEnabled(workflowFieldStatus.equals(SpiraTeamUtil.WORKFLOW_TRANSITION_STATUS_ACTIVE));
			}
		}
		if (artifactType.equals(ArtifactType.TASK))
		{
			String workflowFieldStatus = taskData.getRoot().getAttribute(ArtifactAttribute.TASK_TRANSITION_STATUS.getArtifactKey()).getValue();
			for (Hyperlink button : operationButtons)
			{
				button.setEnabled(workflowFieldStatus.equals(SpiraTeamUtil.WORKFLOW_TRANSITION_STATUS_ACTIVE));
			}
		}
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

		createActionButtons(buttonComposite, toolkit);

		toolkit.paintBordersFor(buttonComposite);
		section.setClient(buttonComposite);
		setSection(toolkit, section);
	}
	
	private void createActionButtons(Composite buttonComposite, FormToolkit toolkit)
	{
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
			addAttachContextButton(buttonComposite, toolkit);
		}
	}
	

	protected void addAttachContextButton(Composite buttonComposite, FormToolkit toolkit)
	{
		attachContextButton = toolkit.createButton(buttonComposite, Messages.TaskEditorActionPart_Attach_Context,
				SWT.CHECK);
		attachContextButton.setImage(CommonImages.getImage(TasksUiImages.CONTEXT_ATTACH));
	}
	
	private void createHyperlinks(Composite buttonComposite, FormToolkit toolkit, TaskOperation selectedOperation)
	{
		List<TaskOperation> operations = getTaskData().getAttributeMapper().getTaskOperations(selectedOperationAttribute);
		if (operations.size() > 0)
		{
			operationButtons = new ArrayList<Hyperlink>();
			for (TaskOperation operation : operations)
			{
				Hyperlink button = toolkit.createHyperlink(buttonComposite, "> " + operation.getLabel(), 0);
				button.setFont(TEXT_FONT);
				button.setData(KEY_OPERATION, operation);
				GridData radioData = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
				button.setLayoutData(radioData);
				button.addHyperlinkListener(new HyperlinkListener(button));
				operationButtons.add(button);
			}
		}
	}
	
	public void setSubmitEnabled(boolean enabled)
	{
		if (submitButton != null && !submitButton.isDisposed())
		{
			submitButton.setEnabled(enabled);
			if (enabled)
			{
				submitButton.setToolTipText(MessageFormat.format(Messages.TaskEditorActionPart_Submit_to_X,
						getTaskEditorPage().getTaskRepository().getRepositoryLabel()));
			}
		}
	}
	
	public class HyperlinkListener implements IHyperlinkListener
	{
		private Hyperlink hyperlink = null;
		
		public HyperlinkListener(Hyperlink hyperlink)
		{
			this.hyperlink = hyperlink;
		}

		@Override
		public void linkActivated(HyperlinkEvent arg0)
		{
			//Get the operation name
			String operationName = (String)arg0.data;
			
			//When an action hyperlink is clicked we need to call the function that handles this
			TaskData taskData = getTaskData();
			SpiraTeamTaskEditorPage taskEditorPage = (SpiraTeamTaskEditorPage)getTaskEditorPage();
			TaskRepository repository = taskEditorPage.getTaskRepository();
			SpiraTeamRepositoryConnector connector = (SpiraTeamRepositoryConnector) taskEditorPage.getConnector();
			SpiraTeamTaskDataHandler taskDataHandler = connector.getTaskDataHandler();
			
			//Need to extract the operation information
			TaskOperation operation = (TaskOperation) hyperlink.getData(KEY_OPERATION);
			Set<TaskAttribute> changedAttributes = taskDataHandler.executeOperation(repository, taskData, operation);
			
			//Now we need to make the editor know that the attributes have changed
			for (TaskAttribute changedAttribute : changedAttributes)
			{
				//We can only change root attributes
				taskEditorPage.getModel().attributeChanged(changedAttribute);
			}
						
			//Finally force a refesh so that the workflow field state changes take effect
			taskEditorPage.refreshFormContent();
		}

		@Override
		public void linkEntered(HyperlinkEvent arg0)
		{
			//Do Nothing			
		}

		@Override
		public void linkExited(HyperlinkEvent arg0)
		{
			// TODO Auto-generated method stub
			
		}
	}
}
