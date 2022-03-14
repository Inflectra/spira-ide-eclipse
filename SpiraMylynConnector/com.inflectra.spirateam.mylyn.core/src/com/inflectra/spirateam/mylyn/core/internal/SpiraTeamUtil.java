/**
 * 
 */
package com.inflectra.spirateam.mylyn.core.internal;

import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.soap.Detail;
import javax.xml.soap.SOAPFault;
import javax.xml.ws.soap.SOAPFaultException;

import org.eclipse.mylyn.tasks.core.IRepositoryQuery;
import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.w3c.dom.Node;

import com.inflectra.spirateam.mylyn.core.internal.model.Filter;
import com.inflectra.spirateam.mylyn.core.internal.model.PredefinedFilter;
import com.inflectra.spirateam.mylyn.core.internal.services.SpiraAuthenticationException;
import com.inflectra.spirateam.mylyn.core.internal.services.SpiraAuthorizationException;
import com.inflectra.spirateam.mylyn.core.internal.services.SpiraDataConcurrencyException;
import com.inflectra.spirateam.mylyn.core.internal.services.SpiraDataValidationException;
import com.inflectra.spirateam.mylyn.core.internal.services.SpiraException;
import com.inflectra.spirateam.mylyn.core.internal.services.SpiraImportExport;
import com.inflectra.spirateam.mylyn.core.internal.services.soap.ServiceFaultMessage;

/**
 * @author Inflectra Corporation
 *
 */
public class SpiraTeamUtil
{
	private static final String KEY_FILTER_ID = "FilterID"; //$NON-NLS-1$
	private static final String KEY_FILTER_NAME = "FilterName"; //$NON-NLS-1$
	
	public static final int WORKFLOW_FIELD_STATE_INACTIVE = 1;
	public static final int WORKFLOW_FIELD_STATE_REQUIRED = 2;
	public static final int WORKFLOW_FIELD_STATE_HIDDEN = 3;

	public static final String WORKFLOW_TRANSITION_STATUS_ACTIVE = "active";
	public static final String WORKFLOW_TRANSITION_STATUS_EXECUTED = "executed";
	
	/***
	 * Make sure that the server is running v6.0 or higher
	 * @param spiraImportExport
	 * @return
	 */
	public static boolean ValidateServerVersion (SpiraImportExport spiraImportExport)
	{
		boolean current = false;
		if (spiraImportExport.getProductVersionPrimary() >= 4)
		{
			//v4.0 or higher
			current = true;
		}
		if (spiraImportExport.getProductVersionPrimary() == 3 &&
				spiraImportExport.getProductVersionSecondary() >= 1)
		{
			//v3.1 or higher
			current = true;
		}
		if (spiraImportExport.getProductVersionPrimary() == 3 &&
				spiraImportExport.getProductVersionSecondary() == 0 &&
				spiraImportExport.getProductVersionTertiary() == 0 &&
				spiraImportExport.getPatchNumber() >= 9)
		{
			//v3.0 Patch 9 or higher
			current = true;
		}
		return current;

	}
	
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
	
	public static String getPrefixFromArtifactKey(String artifactKey)
	{
		if (artifactKey != null && artifactKey.length() > 2)
		{
			return artifactKey.substring(0, 2);
		}
		return null;
	}
	
	public static Date convertDatesXml2Java(XMLGregorianCalendar xmlCal)
	{
		if (xmlCal == null)
		{
			return null;
		}
		GregorianCalendar calendar = xmlCal.toGregorianCalendar();
		//We need to specify that these dates are really in UTC - Spira 4.0 and later APIs
		TimeZone utc = TimeZone.getTimeZone("UTC");
		calendar.setTimeZone(utc);
		Date date = calendar.getTime();
		return date;
	}
	
	public static XMLGregorianCalendar convertDatesJava2Xml(Date date)
	{
		if (date == null)
		{
			return null;
		}
		try
		{
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			//We need to specify that these dates are really in UTC - Spira 4.0 and later APIs
			TimeZone utc = TimeZone.getTimeZone("UTC");
			calendar.setTimeZone(utc);
			DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
			XMLGregorianCalendar xmlCal = datatypeFactory.newXMLGregorianCalendar(calendar);
			//We need to unset the timezone from the XML because SpiraTeam is not expecting it
			//and it will break concurrency
			xmlCal.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
			return xmlCal;
		}
		catch (DatatypeConfigurationException ex)
		{
			return null;
		}
	}
	
	public static SpiraException convertSoapFaults(SOAPFaultException ex)
	{
		//See if we have the authentication or authorization exceptions
		//unfortunately SpiraTeam doesn't use special exception types for them
		//so we have to look for the exception message name
		if (ex.getMessage().contains("Session Not Authenticated"))	//$NON-NLS-1$
		{
			return new SpiraAuthenticationException(ex.getMessage());
		}
		if (ex.getMessage().contains("Not Connected to a Project"))	//$NON-NLS-1$
		{
			return new SpiraAuthorizationException(ex.getMessage());
		}
		
		//See if we have data validation exceptions or data concurrency exceptions
		//as those need to be handled separately
		SOAPFault fault = ex.getFault();
		if (fault == null)
		{
			return new SpiraException(ex.getMessage());				
		}
		Detail faultDetail = fault.getDetail();
		if (faultDetail == null)
		{
			return new SpiraException(ex.getMessage());				
		}
		Node exceptionTypeNode = faultDetail.getFirstChild();
		if (exceptionTypeNode == null)
		{
			return new SpiraException(ex.getMessage());				
		}
		Node exceptionMessageNode = exceptionTypeNode.getFirstChild();
		if (exceptionTypeNode == null)
		{
			return new SpiraException(ex.getMessage());				
		}
		String exceptionType = exceptionTypeNode.getLocalName();
		String exceptionMessage = exceptionMessageNode.getTextContent();
		
		//See if we have a known exception type
		if (exceptionMessage == null || exceptionMessage.equals(""))
		{
			return new SpiraException(ex.getMessage());		
		}
		if (exceptionType.equals("DataAccessConcurrencyException"))	//$NON-NLS-1$
		{
			return new SpiraDataConcurrencyException(Messages.SpiraTeamCorePlugin_DataConcurrencyError);
		}
		if (exceptionType.equals("DataValidationException"))	//$NON-NLS-1$
		{
			return new SpiraDataValidationException(exceptionMessage);
		}
		return new SpiraException(exceptionMessage);
	}

	public static SpiraException convertFaultException(Exception ex)
	{
		//See if we have the authentication or authorization exceptions
		//unfortunately SpiraTeam doesn't use special exception types for them
		//so we have to look for the exception message name
		if (ex.getMessage().contains("Session Not Authenticated"))	//$NON-NLS-1$
		{
			return new SpiraAuthenticationException(ex.getMessage());
		}
		if (ex.getMessage().contains("Not Connected to a Project"))	//$NON-NLS-1$
		{
			return new SpiraAuthorizationException(ex.getMessage());
		}
		
		//See if we have data validation exceptions or data concurrency exceptions
		//as those need to be handled separately
		//Need to use reflection because the various fault messages don't have a shared super class
		ServiceFaultMessage faultInfo = null;
		try
		{
			Class noparams[] = {};
			Class cls = ex.getClass();
			Method getFaultInfo = cls.getDeclaredMethod("getFaultInfo", noparams);
			faultInfo = (ServiceFaultMessage)getFaultInfo.invoke(ex, null);
		}
		catch (Exception ex2)
		{
			//Not able to access by reflection so just return the message
			return new SpiraException(ex.getMessage());
		}
		if (faultInfo == null)
		{
			return new SpiraException(ex.getMessage());				
		}
		
		String exceptionType = faultInfo.getType().getValue();
		String exceptionMessage = faultInfo.getMessage().getValue();
		
		//See if we have a known exception type
		if (exceptionMessage == null || exceptionMessage.equals(""))
		{
			return new SpiraException(ex.getMessage());		
		}
		if (exceptionType.equals("DataAccessConcurrencyException"))	//$NON-NLS-1$
		{
			return new SpiraDataConcurrencyException(Messages.SpiraTeamCorePlugin_DataConcurrencyError);
		}
		if (exceptionType.equals("DataValidationException"))	//$NON-NLS-1$
		{
			return new SpiraDataValidationException(exceptionMessage);
		}
		return new SpiraException(exceptionMessage);
	}

	
	public static Date parseDate(String time)
		throws NumberFormatException
	{
		if (time != null)
		{
			return new Date(Long.valueOf(time));
		}
		return null;
	}
	
	/**
	 * Converts a Java date into the appropriate localized time string
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date)
	{
		if (date == null)
		{
			return ""; //$NON-NLS-1$
		}
		else
		{
			return date.getTime() + ""; //$NON-NLS-1$
		}
	}
	
	public static String booleanToString(Boolean bool)
	{
		if (bool == null)
		{
			return ""; //$NON-NLS-1$
		}
		else
		{
			boolean boolValue = bool.booleanValue();
			return (boolValue) ? "true" : "false"; //$NON-NLS-1$
		}
	}
	
	/**
	 * Converts an effort value in minutes (integer)
	 * into an hours/mins one that can be displayed in Eclipse
	 * @param effort Effort in minutes
	 * @return string representation in hours and minutes (1 dec place only)
	 */
	public static String effortValuesToString(Integer effort)
	{
		if (effort == null)
		{
			return ""; //$NON-NLS-1$
		}
		else
		{
			DecimalFormat onePlace = new DecimalFormat("0.0");
			int effortInt = effort.intValue();
			double effortHours = (double)effortInt / 60.0;
			String effortHoursRounded = onePlace.format(effortHours);
			return effortHoursRounded;
		}
	}
	
	/**
	 * Renders HTML content as plain textsince Mylyn cannot handle HTML tags
	 */
	public static String HtmlRenderAsPlainText (String source)
	{
		if (source == null)
		{
			return "";
		}
		try
		{
			String result;

			// Remove HTML Development formatting
			// Replace line breaks with space
			// because browsers inserts space
			result = source.replace("\r"," ");
			// Replace line breaks with space
			// because browsers inserts space
			result = result.replace("\n"," ");
			// Remove step-formatting
			result = result.replace("\t","");
			// Remove repeating speces becuase browsers ignore them
			result = result.replace( 
				"( )+"," ");

			// Remove the header (prepare first by clearing attributes)
			result = result.replaceAll( 
				"<( )*head([^>])*>","<head>"
				);
			result = result.replaceAll( 
				"(<( )*(/)( )*head( )*>)","</head>"
				);
			result = result.replaceAll( 
				"(<head>).*(</head>)",""
				);

			// remove all scripts (prepare first by clearing attributes)
			result = result.replaceAll( 
				"<( )*script([^>])*>","<script>"
				);
			result = result.replaceAll( 
				"(<( )*(/)( )*script( )*>)","</script>"
				);
			//result = result.replace( 
			//         @"(<script>)([^(<script>\.</script>)])*(</script>)",
			//         ""
			//         );
			result = result.replaceAll( 
				"(<script>).*(</script>)",""
				);
    
			// remove all styles (prepare first by clearing attributes)
			result = result.replaceAll( 
				"<( )*style([^>])*>","<style>"
				);
			result = result.replaceAll( 
				"(<( )*(/)( )*style( )*>)","</style>"
				);
			result = result.replaceAll( 
				"(<style>).*(</style>)",""
				);

			// insert tabs in spaces of <td> tags
			result = result.replaceAll( 
				"<( )*td([^>])*>","\t"
				);

			// insert line breaks in places of <BR> and <LI> tags
			result = result.replaceAll( 
				"<( )*br( )*>","\r"
				);
			result = result.replaceAll( 
				"<( )*li( )*>","\r"
				);

			// insert line paragraphs (double line breaks) in place
			// if <P><DIV> and <TR> tags
			result = result.replaceAll( 
				"<( )*div([^>])*>","\r\r"
				);
			result = result.replaceAll( 
				"<( )*tr([^>])*>","\r\r"
				);
			result = result.replaceAll( 
				"<( )*p([^>])*>","\r\r"
				);

			// Remove remaining tags like <a>linksimages,
			// comments etc - anything thats enclosed inside < >
			result = result.replaceAll( 
				"<[^>]*>",""
				);

			// replace special characters:
			result = result.replaceAll( 
				"&nbsp;"," "
				);
    
			result = result.replaceAll( 
				"&bull;"," * "
				);    
			result = result.replaceAll( 
				"&lsaquo;","<"
				);        
			result = result.replaceAll( 
				"&rsaquo;",">"
				);        
			result = result.replaceAll( 
				"&trade;","(tm)"
				);        
			result = result.replaceAll( 
				"&frasl;","/"
				);        
			result = result.replaceAll( 
				"<","<"
				);        
			result = result.replaceAll( 
				">",">"
				);        
			result = result.replaceAll( 
				"&copy;","(c)"
				);        
			result = result.replaceAll( 
				"&reg;","(r)"
				);    
			// Remove all others. More can be addedsee
			// http://hotwired.lycos.com/webmonkey/reference/special_characters/
			result = result.replaceAll( 
				"&(.{2,6});",""
				);    

			// for testng
			//result.replace( 
			//       this.txtRegex.Text,""
			//       );

			// make line breaking consistent
			result = result.replace("\n","\r");

			// Remove extra line breaks and tabs:
			// replace over 2 breaks with 2 and over 4 tabs with 4. 
			// Prepare first to remove any whitespaces inbetween
			// the escaped characters and remove redundant tabs inbetween linebreaks
			result = result.replaceAll( 
				"(\r)( )+(\r)","\r\r"
				);
			result = result.replaceAll( 
				"(\t)( )+(\t)","\t\t"
				);
			result = result.replaceAll( 
				"(\t)( )+(\r)","\t\r"
				);
			result = result.replaceAll( 
				"(\r)( )+(\t)","\r\t"
				);
			// Remove redundant tabs
			result = result.replaceAll( 
				"(\r)(\t)+(\r)","\r\r"
				);
			// Remove multible tabs followind a linebreak with just one tab
			result = result.replaceAll( 
				"(\r)(\t)+","\r\t"
				);
			// Initial replacement target string for linebreaks
			String breaks = "\r\r\r";
			// Initial replacement target string for tabs
			String tabs = "\t\t\t\t\t";
			for (int index=0; index<result.length(); index++)
			{
				result = result.replace(breaks, "\r\r");
				result = result.replace(tabs, "\t\t\t\t");
				breaks = breaks + "\r";    
				tabs = tabs + "\t";
			}

			// Thats it.
			return result;

		}
		catch (Exception ex)
		{
			return source;
		}
	}
}
