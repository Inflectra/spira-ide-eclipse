/**
 * 
 */
package com.inflectra.spirateam.mylyn.core.internal;

import org.eclipse.mylyn.tasks.core.IRepositoryQuery;
import org.eclipse.mylyn.tasks.core.TaskRepository;

import com.inflectra.spirateam.mylyn.core.internal.model.Filter;
import com.inflectra.spirateam.mylyn.core.internal.model.PredefinedFilter;;

/**
 * @author Inflectra Corporation
 *
 */
public class SpiraTeamUtil
{
	private static final String KEY_FILTER_ID = "FilterID"; //$NON-NLS-1$
	private static final String KEY_FILTER_NAME = "FilterName"; //$NON-NLS-1$
	
	/**
	 * Sets the passed in pre-defined filter
	 * @param taskRepository
	 * @param query
	 * @param filter
	 */
	public static void setQuery(TaskRepository taskRepository, IRepositoryQuery query, Filter filter)
	{
		if (filter instanceof PredefinedFilter)
		{
			PredefinedFilter predefinedFilter = (PredefinedFilter) filter;
			query.setAttribute(KEY_FILTER_ID, predefinedFilter.getId());
			query.setAttribute(KEY_FILTER_NAME, predefinedFilter.getName());
		}
	}
	
	public static PredefinedFilter[] createPredefinedFilters()
	{
		PredefinedFilter[] filters = new PredefinedFilter[3];
		
		//Setup the three predefined queries:
		//My Assigned Requirements
		//My Assigned Incidents
		//My Assigned Tasks
		filters[0] = new PredefinedFilter("1", Messages.SpiraTeam_PredefinedFilter_MyRequirements);
		filters[1] = new PredefinedFilter("2", Messages.SpiraTeam_PredefinedFilter_MyIncidents);
		filters[2] = new PredefinedFilter("3", Messages.SpiraTeam_PredefinedFilter_MyTasks);

		return filters;
	}
	

	public static PredefinedFilter getPredefinedFilter(IRepositoryQuery query)
	{
		String id = query.getAttribute(KEY_FILTER_ID);
		if (id != null)
		{
			PredefinedFilter namedFilter = new PredefinedFilter();
			namedFilter.setId(id);
			namedFilter.setName(query.getAttribute(KEY_FILTER_NAME));
			return namedFilter;
		}
		return null;
	}
}
