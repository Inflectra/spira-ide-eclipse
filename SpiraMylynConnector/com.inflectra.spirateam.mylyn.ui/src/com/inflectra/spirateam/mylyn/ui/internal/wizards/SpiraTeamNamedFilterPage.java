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

import com.inflectra.spirateam.mylyn.core.internal.SpiraTeamUtil;
import com.inflectra.spirateam.mylyn.core.internal.model.PredefinedFilter;

/**
 * Wizard page that allows the user to select a predefined SpiraTeam filter.
 * Eventually it will also need to support creating filters and retrieving
 * filters that were created by the user in SpiraTeam.
 */
public class SpiraTeamNamedFilterPage extends AbstractRepositoryQueryPage
{
	private List filterList;
	
	private PredefinedFilter[] filters = null;
	
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
		
	/**
	 * Applies the specified filter
	 */
	@Override
	public void applyTo(IRepositoryQuery query)
	{
		PredefinedFilter filter = getSelectedFilter();
		query.setSummary(filter.getName());
		SpiraTeamUtil.setQuery(getTaskRepository(), query, filter);
	}

	@Override
	public String getQueryTitle()
	{
		return Messages.SpiraTeamNamedFilterPage_Please_select_named_queries;
	}
	

	/** Returns the filter selected by the user or null on failure */
	private PredefinedFilter getSelectedFilter()
	{
		if (filters != null && filters.length > 0)
		{
			return filters[filterList.getSelectionIndex()];
		}
		return null;
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
		filterList.deselectAll();
		GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
		data.horizontalIndent = 15;
		filterList.setLayoutData(data);
		filterList.setEnabled(true);
	
		int n = 0;
		this.filters = SpiraTeamUtil.createPredefinedFilters(); 
		for (int i = 0; i < filters.length; i++)
		{
			filterList.add(filters[i].getName());
			/*if (filters[i].getId().equals(workingCopy.getId()))
			{
				n = i;
			}*/
		}

		/*filterList.select(n);*/
		filterList.showSelection();
		
		Dialog.applyDialogFont(innerComposite);
		setControl(innerComposite);
		//setPageComplete(true);
	}
}
