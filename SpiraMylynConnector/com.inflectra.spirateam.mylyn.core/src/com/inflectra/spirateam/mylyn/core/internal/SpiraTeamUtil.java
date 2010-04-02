/**
 * 
 */
package com.inflectra.spirateam.mylyn.core.internal;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.XMLGregorianCalendar;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.mylyn.tasks.core.IRepositoryQuery;
import org.eclipse.mylyn.tasks.core.TaskRepository;

import com.inflectra.spirateam.mylyn.core.internal.model.Filter;
import com.inflectra.spirateam.mylyn.core.internal.model.PredefinedFilter;
import com.inflectra.spirateam.mylyn.core.internal.services.SpiraImportExport;

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
		filters[0] = new PredefinedFilter(SpiraTeamCorePlugin.MY_ASSIGNED_REQUIREMENTS, Messages.SpiraTeam_PredefinedFilter_MyRequirements);
		filters[1] = new PredefinedFilter(SpiraTeamCorePlugin.MY_ASSIGNED_INCIDENTS, Messages.SpiraTeam_PredefinedFilter_MyIncidents);
		filters[2] = new PredefinedFilter(SpiraTeamCorePlugin.MY_ASSIGNED_TASKS, Messages.SpiraTeam_PredefinedFilter_MyTasks);

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
	
	public static Date ConvertDatesXml2Java(XMLGregorianCalendar xmlCal)
	{
		if (xmlCal == null)
		{
			return null;
		}
		GregorianCalendar calendar = xmlCal.toGregorianCalendar();
		Date date = calendar.getTime();
		return date;
	}
}
