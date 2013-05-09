package com.inflectra.spirateam.mylyn.ui.internal.editor;

import java.awt.Color;

import org.eclipse.core.runtime.Assert;
import org.eclipse.mylyn.internal.tasks.ui.editors.PersonAttributeEditor;
import org.eclipse.mylyn.internal.tasks.ui.editors.TaskEditorExtensions;
import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.mylyn.tasks.core.data.TaskAttribute;
import org.eclipse.mylyn.tasks.core.data.TaskDataModel;
import org.eclipse.mylyn.tasks.ui.editors.AbstractAttributeEditor;
import org.eclipse.mylyn.tasks.ui.editors.AbstractTaskEditorExtension;
import org.eclipse.mylyn.tasks.ui.editors.AttributeEditorFactory;
import org.eclipse.mylyn.tasks.ui.editors.AttributeEditorToolkit;
import org.eclipse.mylyn.tasks.ui.editors.LayoutHint;
import org.eclipse.mylyn.tasks.ui.editors.LayoutHint.ColumnSpan;
import org.eclipse.mylyn.tasks.ui.editors.LayoutHint.RowSpan;
import org.eclipse.mylyn.internal.tasks.ui.editors.RepositoryTextViewerConfiguration.Mode;
import org.eclipse.swt.SWT;
import org.eclipse.ui.contexts.IContextService;
import org.eclipse.ui.services.IServiceLocator;

import com.inflectra.spirateam.mylyn.core.internal.ArtifactAttribute;

public class SpiraTeamAttributeEditorFactory extends AttributeEditorFactory
{
	private SpiraTeamTaskEditorPage editorPage;
	private TaskDataModel model;
	private TaskRepository taskRepository;
	private final IServiceLocator serviceLocator;
	private AttributeEditorToolkit editorToolkit;


	public SpiraTeamTaskEditorPage getEditorPage()
	{
		return this.editorPage;
	}

	public TaskDataModel getModel()
	{
		return this.model;
	}

	public SpiraTeamAttributeEditorFactory(TaskDataModel model, TaskRepository taskRepository, SpiraTeamTaskEditorPage editorPage)
	{
		super(model, taskRepository, null);
		this.editorPage = editorPage;
		this.model = model;
		this.taskRepository = taskRepository;
		this.serviceLocator = null;
		this.editorToolkit = null;
	}

	@Override
	public AbstractAttributeEditor createEditor(String type, TaskAttribute taskAttribute)
	{
		// If we have Incident Type, need to create a special select list that
		// has
		// an event handler linked to it
		if (taskAttribute.getId().equals(ArtifactAttribute.INCIDENT_TYPE_ID.getArtifactKey()))
		{
			return new IncidentTypeAttributeEditor(getModel(), taskAttribute, editorPage);
		}
		else
		{
			// Create the various attribute editors, we have to create them
			// ourselves
			// because we have various extended versions that are suffixed with
			// --Ex
			// this allows us to make them hidden and bold (required)
			Assert.isNotNull(type);

			if (TaskAttribute.TYPE_BOOLEAN.equals(type))
			{
				return new BooleanAttributeEditorEx(model, taskAttribute);
			}
			else if (TaskAttribute.TYPE_DOUBLE.equals(type))
			{
				return new DoubleAttributeEditorEx(model, taskAttribute);
			}
			else if (TaskAttribute.TYPE_INTEGER.equals(type))
			{
				return new IntegerAttributeEditorEx(model, taskAttribute);
			}
			else if (TaskAttribute.TYPE_DATE.equals(type))
			{
				return new DateAttributeEditorEx(model, taskAttribute);
			}
			else if (TaskAttribute.TYPE_DATETIME.equals(type))
			{
				DateAttributeEditorEx editor = new DateAttributeEditorEx(model, taskAttribute);
				editor.setShowTime(true);
				return editor;
			}
			else if (TaskAttribute.TYPE_PERSON.equals(type))
			{
				return new PersonAttributeEditor(model, taskAttribute);
			}
			else if (TaskAttribute.TYPE_LONG_RICH_TEXT.equals(type))
			{
				RichTextAttributeEditorEx editor = null;
				if (serviceLocator != null)
				{
					IContextService contextService = (IContextService) serviceLocator.getService(IContextService.class);
					if (contextService != null)
					{
						AbstractTaskEditorExtension extension = TaskEditorExtensions.getTaskEditorExtension(model.getTaskRepository());
						if (extension != null)
						{
							editor = new RichTextAttributeEditorEx(model, taskRepository, taskAttribute, SWT.MULTI, contextService, extension);
						}
					}
				}
				if (editor == null)
				{
					editor = new RichTextAttributeEditorEx(model, taskRepository, taskAttribute);
				}
				if (editorToolkit != null)
				{
					editor.setRenderingEngine(editorToolkit.getRenderingEngine(taskAttribute));
				}
				return editor;
			}
			else if (TaskAttribute.TYPE_LONG_TEXT.equals(type))
			{
				return new LongTextAttributeEditorEx(model, taskAttribute);
			}
			else if (TaskAttribute.TYPE_MULTI_SELECT.equals(type))
			{
				return new MultiSelectionAttributeEditorEx(model, taskAttribute);
			}
			else if (TaskAttribute.TYPE_SHORT_RICH_TEXT.equals(type))
			{
				return new RichTextAttributeEditorEx(model, taskRepository, taskAttribute, SWT.SINGLE);
			}
			else if (TaskAttribute.TYPE_SHORT_TEXT.equals(type))
			{
				return new TextAttributeEditorEx(model, taskAttribute);
			}
			else if (TaskAttribute.TYPE_SINGLE_SELECT.equals(type))
			{
				return new SingleSelectionAttributeEditorEx(model, taskAttribute);
			}
			else if (TaskAttribute.TYPE_TASK_DEPENDENCY.equals(type))
			{
				//RichTextAttributeEditorEx editor = new RichTextAttributeEditorEx(model, taskRepository, taskAttribute, SWT.MULTI | TasksUiInternal.SWT_NO_SCROLL)
				RichTextAttributeEditorEx editor = new RichTextAttributeEditorEx(model, taskRepository, taskAttribute, SWT.MULTI)
				{
					@Override
					public String getValue()
					{
						return getAttributeMapper().getValueLabel(getTaskAttribute());
					}
				};
				editor.setMode(Mode.TASK_RELATION);
				editor.setLayoutHint(new LayoutHint(RowSpan.SINGLE, ColumnSpan.SINGLE)
				{
					@Override
					public int getPriority()
					{
						return DEFAULT_PRIORITY + 1;
					}
				});
				return editor;
			}
			else if (TaskAttribute.TYPE_URL.equals(type))
			{
				RichTextAttributeEditorEx editor = new RichTextAttributeEditorEx(model, taskRepository, taskAttribute, SWT.SINGLE);
				editor.setMode(Mode.URL);
				return editor;
			}

			throw new IllegalArgumentException("Unsupported editor type: \"" + type + "\""); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

}
