package com.inflectra.spirateam.mylyn.ui.internal.wizards;

import org.eclipse.mylyn.tasks.core.IRepositoryQuery;
import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.mylyn.tasks.ui.wizards.AbstractRepositoryQueryPage;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;

/**
 * Wizard page that allows the user to select a predefined SpiraTeam filter.
 * Eventually it will also need to support creating filters and retrieving
 * filters that were created by the user in SpiraTeam.
 */
public class SpiraTeamNamedFilterPage extends AbstractRepositoryQueryPage
{
	private List filterList;
	
	/**
	 * Constructor method
	 * @param repository The task repository reference
	 */
	public SpiraTeamNamedFilterPage(TaskRepository repository)
	{
		super(Messages.SpiraTeamNamedFilterPage_New_Named_Query, repository);
		setTitle(Messages.SpiraTeamNamedFilterPage_New_Named_Query);
		setDescription(Messages.SpiraTeamNamedFilterPage_Please_select_named_queries);
		setPageComplete(false);
	}
	
	/**
	 * Constructor method
	 * @param repository The task repository reference
	 */
	public SpiraTeamNamedFilterPage(TaskRepository repository, IRepositoryQuery query)
	{
		super(Messages.SpiraTeamNamedFilterPage_New_Named_Query, repository, query);
		setTitle(Messages.SpiraTeamNamedFilterPage_New_Named_Query);
		setDescription(Messages.SpiraTeamNamedFilterPage_Please_select_named_queries);
		setPageComplete(false);
	}
	
	@Override
	public void applyTo(IRepositoryQuery query)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public String getQueryTitle()
	{
		return Messages.SpiraTeamNamedFilterPage_Please_select_named_queries;
	}

	/**
	 * Creates the list of predefined filters and the button
	 */
	@Override
	public void createControl(Composite parent)
	{
		final Composite innerComposite = new Composite(parent, SWT.NONE);
		innerComposite.setLayoutData(new GridData());
		GridLayout gl = new GridLayout();
		gl.numColumns = 1;
		innerComposite.setLayout(gl);
		
		filterList = new List(innerComposite, SWT.V_SCROLL | SWT.BORDER);
		filterList.add("Filter");
		filterList.deselectAll();
		GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
		data.horizontalIndent = 15;
		filterList.setLayoutData(data);
		filterList.setEnabled(true);
		
		Dialog.applyDialogFont(innerComposite);
		setControl(innerComposite);
	}

}
