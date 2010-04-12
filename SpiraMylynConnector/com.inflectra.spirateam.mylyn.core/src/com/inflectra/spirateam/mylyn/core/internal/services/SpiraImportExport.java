/**
 * 
 */
package com.inflectra.spirateam.mylyn.core.internal.services;

import javax.xml.namespace.QName;
import javax.xml.ws.*;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.osgi.util.NLS;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.inflectra.spirateam.mylyn.core.internal.ArtifactType;
import com.inflectra.spirateam.mylyn.core.internal.SpiraTeamClientData;
import com.inflectra.spirateam.mylyn.core.internal.SpiraTeamCorePlugin;
import com.inflectra.spirateam.mylyn.core.internal.model.ArtifactField;
import com.inflectra.spirateam.mylyn.core.internal.model.ArtifactFieldValue;
import com.inflectra.spirateam.mylyn.core.internal.model.Incident;
import com.inflectra.spirateam.mylyn.core.internal.model.Requirement;
import com.inflectra.spirateam.mylyn.core.internal.model.Task;
import com.inflectra.spirateam.mylyn.core.internal.model.ArtifactField.Type;
import com.inflectra.spirateam.mylyn.core.internal.services.soap.*;
import com.inflectra.spirateam.mylyn.core.internal.services.SpiraConnectionException;

/**
 * This is a facade over the auto-generated proxy class
 * that simplifies the inner-workings of the SOAP/WSDL classes
 * 
 * @author Inflectra Corporation
 */
public class SpiraImportExport
{
	private static final String WEB_SERVICE_SUFFIX = "/Services/v2_2/ImportExport.asmx";	//$NON-NLS-1$
	private static final String WEB_SERVICE_NAMESPACE = "{http://www.inflectra.com/SpiraTest/Services/v2.2/}ImportExport";	//$NON-NLS-1$
	private static final String SPIRA_PLUG_IN_NAME = "Eclipse-IDE";	//$NON-NLS-1$
	
	public static final String REQUIREMENT_URL = "RequirementDetails.aspx?requirementId=";
	
	private URL serviceUrl = null;
	private String userName = "";
	private String password = "";
	private ImportExport service = null;
	private ImportExportSoap soap = null;
	private Integer storedProjectId = null;
	
	private ArtifactField taskField_TaskStatus = null;
	private ArtifactField taskField_TaskPriority = null;

	protected SpiraTeamClientData data;
	
	/**
	 * The constructor
	 */
	public SpiraImportExport(String baseUrl)
		throws MalformedURLException, SpiraConnectionException
	{
		//Set the web service URL
		this.serviceUrl = new URL(baseUrl + WEB_SERVICE_SUFFIX);
		
		//Instantiate the SOAP proxy
		try
		{
			this.service = new ImportExport(this.serviceUrl, QName.valueOf(WEB_SERVICE_NAMESPACE));
			this.soap = this.service.getImportExportSoap();
			
			//Make sure that session is maintained
			Map<String, Object> requestContext = ((BindingProvider)this.soap).getRequestContext();
			requestContext.put(BindingProvider.SESSION_MAINTAIN_PROPERTY,true);
		}
		catch (WebServiceException ex)
		{
			throw new SpiraConnectionException(Messages.SpiraConnectionException_Message);
		}
	}
	
	/**
	 * The constructor
	 */
	public SpiraImportExport(String baseUrl, String userName, String password)
		throws MalformedURLException, SpiraConnectionException
	{
		//Set the URL, username and password
		this.serviceUrl = new URL(baseUrl + WEB_SERVICE_SUFFIX);
		this.userName = userName;
		this.password = password;
		
		//Instantiate the SOAP proxy
		try
		{
			this.service = new ImportExport(this.serviceUrl, QName.valueOf(WEB_SERVICE_NAMESPACE));
			this.soap = this.service.getImportExportSoap();
			
			//Make sure that session is maintained
			Map<String, Object> requestContext = ((BindingProvider)this.soap).getRequestContext();
			requestContext.put(BindingProvider.SESSION_MAINTAIN_PROPERTY,true);

		}
		catch (WebServiceException ex)
		{
			throw new SpiraConnectionException(Messages.SpiraConnectionException_Message);
		}
	}
	
	public Integer getStoredProjectId()
	{
		return this.storedProjectId;
	}
	public void setStoredProjectId(int projectId)
	{
		this.storedProjectId = new Integer(projectId);
	}

	/**
	 * The user name
	 */
	public String getUserName()
	{
		return this.userName;
	}
	
	/**
	 * The user name
	 */
	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	/**
	 * The password
	 */
	public String getPassword()
	{
		return this.password;
	}
	
	/**
	 * The password
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	/**
	 * Returns the web service client handle
	 * @return Handle to the web service client
	 */
	public ImportExport getService()
	{
		return this.service;
	}
	
	/**
	 * Returns the soap proxy handle
	 * @return Handle to the soap proxy
	 */
	public ImportExportSoap getSoap()
	{
		return this.soap;
	}

	public void setData(SpiraTeamClientData data)
	{
		this.data = data;
	}
	
	/**
	 * Authenticates the user/password and stores the cookie
	 * for accessing future methods of the API
	 * @return true if the username/password authenticates successfully
	 */
	public boolean connectionAuthenticate2() throws SpiraConnectionException
	{
		try
		{
			boolean success = false;
		
			//Call the appropriate method
	        success = soap.connectionAuthenticate2(this.userName, this.password, SPIRA_PLUG_IN_NAME);
	        return success;
		}
		catch (WebServiceException ex)
		{
			throw new SpiraConnectionException(Messages.SpiraConnectionException_Message);
		}
	}
	
	/**
	 * Gets a single requirement by its key (RQ prefix + requirement id)
	 * @param artifactKey The key for the requirement (RQ prefix + requirement id)
	 * @param monitor
	 * @param projectId The project id
	 * @return Single requirement object
	 * @throws SpiraException
	 */
	public Requirement requirementRetrieveByKey(String artifactKey, int projectId, IProgressMonitor monitor)
		throws SpiraException
	{
		try
		{	
			//First make sure that the artifact key is in the correct format
			if (artifactKey == null)
			{
				throw new SpiraInvalidArtifactKeyException(Messages.SpiraImportExport_ArtifactKeyNull);
			}
			if (artifactKey.length() < 3)
			{
				throw new SpiraInvalidArtifactKeyException(NLS.bind(Messages.SpiraImportExport_InvalidArtifactKey, artifactKey));
			}
			if (!artifactKey.substring(0, 2).equals(ArtifactType.REQUIREMENT.getPrefix()))
			{
				throw new SpiraInvalidArtifactKeyException(NLS.bind(Messages.SpiraImportExport_InvalidArtifactKey, artifactKey));
			}
			int requirementId;
			try
			{
				requirementId = Integer.parseInt(artifactKey.substring(2));
			}
			catch (NumberFormatException e)
			{
				throw new SpiraInvalidArtifactKeyException(NLS.bind(Messages.SpiraImportExport_InvalidArtifactKey, artifactKey));
			}
			
			//Next we need to re-authenticate
			boolean success = soap.connectionAuthenticate2(this.userName, this.password, SPIRA_PLUG_IN_NAME);
			if (!success)
			{
				//throw new SpiraException (this.userName + "/" + this.password);
				throw new SpiraAuthenticationException(Messages.SpiraImportExport_UnableToAuthenticate);
			}
			
			//Next we need to connect to the appropriate project
			success = soap.connectionConnectToProject(projectId);
			if (!success)
			{
				//throw new SpiraException (this.userName + "/" + this.password);
				throw new SpiraAuthorizationException(NLS.bind(Messages.SpiraImportExport_UnableToConnectToProject, projectId));
			}
				
			//Call the appropriate method
			RemoteRequirement remoteRequirement = soap.requirementRetrieveById(requirementId);
			
			//Convert the SOAP requirement into the local version
			Requirement requirement = new Requirement(remoteRequirement);
	        return requirement;
		}
		catch (WebServiceException ex)
		{
			throw new SpiraException(ex.getMessage());
		}
	}
	
	/**
	 * Gets the list of requirements assigned to the current user
	 * @return List of requirements
	 * @throws SpiraException
	 */
	public List<Requirement> requirementRetrieveAssigned(IProgressMonitor monitor)
		throws SpiraException
	{
		try
		{	
			//First we need to re-authenticate
			boolean success = soap.connectionAuthenticate2(this.userName, this.password, SPIRA_PLUG_IN_NAME);
			if (!success)
			{
				//throw new SpiraException (this.userName + "/" + this.password);
				throw new SpiraAuthenticationException(Messages.SpiraImportExport_UnableToAuthenticate);
			}
				
			//Call the appropriate method
			List<RemoteRequirement> remoteRequirements = soap.requirementRetrieveForOwner().getRemoteRequirement();
			
			//Convert the SOAP requirements into the local versions
			List<Requirement> requirements = new ArrayList<Requirement>();
			for (RemoteRequirement remoteRequirement : remoteRequirements)
			{
				Requirement requirement = new Requirement(remoteRequirement);
				requirements.add(requirement);
			}
	        return requirements;
		}
		catch (WebServiceException ex)
		{
			throw new SpiraException(ex.getMessage());
		}
	}
	
	/**
	 * Gets a single incident by its key (RQ prefix + incident id)
	 * @param artifactKey The key for the incident (RQ prefix + incident id)
	 * @param monitor
	 * @param projectId The project id
	 * @return Single incident object
	 * @throws SpiraException
	 */
	public Incident incidentRetrieveByKey(String artifactKey, int projectId, IProgressMonitor monitor)
		throws SpiraException
	{
		try
		{	
			//First make sure that the artifact key is in the correct format
			if (artifactKey == null)
			{
				throw new SpiraInvalidArtifactKeyException(Messages.SpiraImportExport_ArtifactKeyNull);
			}
			if (artifactKey.length() < 3)
			{
				throw new SpiraInvalidArtifactKeyException(NLS.bind(Messages.SpiraImportExport_InvalidArtifactKey, artifactKey));
			}
			if (!artifactKey.substring(0, 2).equals(ArtifactType.INCIDENT.getPrefix()))
			{
				throw new SpiraInvalidArtifactKeyException(NLS.bind(Messages.SpiraImportExport_InvalidArtifactKey, artifactKey));
			}
			int incidentId;
			try
			{
				incidentId = Integer.parseInt(artifactKey.substring(2));
			}
			catch (NumberFormatException e)
			{
				throw new SpiraInvalidArtifactKeyException(NLS.bind(Messages.SpiraImportExport_InvalidArtifactKey, artifactKey));
			}
			
			//Next we need to re-authenticate
			boolean success = soap.connectionAuthenticate2(this.userName, this.password, SPIRA_PLUG_IN_NAME);
			if (!success)
			{
				//throw new SpiraException (this.userName + "/" + this.password);
				throw new SpiraAuthenticationException(Messages.SpiraImportExport_UnableToAuthenticate);
			}
			
			//Next we need to connect to the appropriate project
			success = soap.connectionConnectToProject(projectId);
			if (!success)
			{
				//throw new SpiraException (this.userName + "/" + this.password);
				throw new SpiraAuthorizationException(NLS.bind(Messages.SpiraImportExport_UnableToConnectToProject, projectId));
			}
				
			//Call the appropriate method
			RemoteIncident remoteIncident = soap.incidentRetrieveById(incidentId);
			
			//Convert the SOAP incident into the local version
			Incident incident = new Incident(remoteIncident);
	        return incident;
		}
		catch (WebServiceException ex)
		{
			throw new SpiraException(ex.getMessage());
		}
	}
	
	public ArtifactField getArtifactFieldByName(String name)
	{
		if (name.equals("TaskStatus"))
		{
			return taskGetStatus();
		}
		if (name.equals("TaskPriority"))
		{
			return taskGetPriority();
		}
		return null;
	}
	
	/*TODO: Add support for custom fields once API supports it
	public ArtifactField[] getCustomProperties(ArtifactType artifactType, int projectId)
	{
		try
		{	
			//First we need to re-authenticate
			boolean success = soap.connectionAuthenticate2(this.userName, this.password, SPIRA_PLUG_IN_NAME);
			if (!success)
			{
				//throw new SpiraException (this.userName + "/" + this.password);
				throw new SpiraAuthenticationException(Messages.SpiraImportExport_UnableToAuthenticate);
			}

			//Next we need to connect to the appropriate project
			success = soap.connectionConnectToProject(projectId);
			if (!success)
			{
				//throw new SpiraException (this.userName + "/" + this.password);
				throw new SpiraAuthorizationException(NLS.bind(Messages.SpiraImportExport_UnableToConnectToProject, projectId));
			}
				
			//Get the list of custom properties
			List<RemoteCustomProperty> remoteCustomProperties = soap.projectRetrieveCustomProperties(artifactType.getArtifactTypeId()).getRemoteCustomProperty();
			
			//Convert the SOAP custom properties into the ArtifactField class
			ArrayList<ArtifactField> artifactFields = new ArrayList<ArtifactField>();
			for (RemoteCustomProperty remoteCustomProperty : remoteCustomProperties)
			{
				ArtifactField artifactField = new ArtifactField(remoteCustomProperty.getCustomPropertyName());
				artifactField.setLabel(remoteCustomProperty.getAlias());
				artifactField.setCustom(true);
				if (remoteCustomProperty.getCustomPropertyTypeId() == SpiraTeamCorePlugin.CustomPropertyType_Text)
				{
					artifactField.setType(Type.TEXT);
				}
				if (remoteCustomProperty.getCustomPropertyTypeId() == SpiraTeamCorePlugin.CustomPropertyType_List)
				{
					artifactField.setType(Type.SELECT);
					
					//Now we need to get the custom list values
					//artifactField.setValues(values);
				}
				artifactFields.add(artifactField);
			}
	        return artifactFields.toArray(new ArtifactField[0]);
		}
		catch (SpiraException ex)
		{
			return null;
		}
		catch (WebServiceException ex)
		{
			return null;
		}
	}*/
	
	public ArtifactField taskGetRelease()
	{
		return this.taskGetRelease(this.storedProjectId);
	}
	
	public ArtifactField taskGetRelease(int projectId)
	{
		try
		{
			//Get the list of releases from the SOAP API
			//First we need to re-authenticate
			boolean success = soap.connectionAuthenticate2(this.userName, this.password, SPIRA_PLUG_IN_NAME);
			if (!success)
			{
				//throw new SpiraException (this.userName + "/" + this.password);
				throw new SpiraAuthenticationException(Messages.SpiraImportExport_UnableToAuthenticate);
			}
	
			//Next we need to connect to the appropriate project
			success = soap.connectionConnectToProject(projectId);
			if (!success)
			{
				//throw new SpiraException (this.userName + "/" + this.password);
				throw new SpiraAuthorizationException(NLS.bind(Messages.SpiraImportExport_UnableToConnectToProject, projectId));
			}
				
			//Get the list of releases
			List<RemoteRelease> remoteReleases = soap.releaseRetrieve(true).getRemoteRelease();
			
			//Convert the SOAP release into the ArtifactField class
			ArtifactField artifactField = new ArtifactField("Release");
			ArrayList<ArtifactFieldValue> lookupValues = new ArrayList<ArtifactFieldValue>();
			for (RemoteRelease remoteRelease : remoteReleases)
			{
				if (remoteRelease.isIteration())
				{
					//Indent iterations with spaces for now
					lookupValues.add(new ArtifactFieldValue(remoteRelease.getReleaseId(), "  " + remoteRelease.getVersionNumber() + " - " + remoteRelease.getName()));				
				}
				else
				{
					lookupValues.add(new ArtifactFieldValue(remoteRelease.getReleaseId(), remoteRelease.getVersionNumber() + " - " + remoteRelease.getName()));
				}
			}		
			artifactField.setValues(lookupValues.toArray(new ArtifactFieldValue[0]));
			return artifactField;
		}
		catch (SpiraException ex)
		{
			return null;
		}
		catch (WebServiceException ex)
		{
			return null;
		}
	}
	
	public ArtifactField taskGetStatus()
	{
		if (this.taskField_TaskStatus == null)
		{
			this.taskField_TaskStatus = new ArtifactField("TaskStatus");
			this.taskField_TaskStatus.setOptional(false);
			
			ArtifactFieldValue[] lookupValues = new ArtifactFieldValue[5];
			lookupValues[0] = new ArtifactFieldValue(1, "Not Started");
			lookupValues[1] = new ArtifactFieldValue(2, "In Progress");
			lookupValues[2] = new ArtifactFieldValue(3, "Completed");
			lookupValues[3] = new ArtifactFieldValue(4, "Blocked");
			lookupValues[4] = new ArtifactFieldValue(5, "Deferred");
			this.taskField_TaskStatus.setValues(lookupValues);
		}
		return this.taskField_TaskStatus;
	}
	
	public ArtifactField taskGetPriority()
	{
		if (this.taskField_TaskPriority == null)
		{
			this.taskField_TaskPriority = new ArtifactField("TaskPriority");
			this.taskField_TaskStatus.setOptional(true);
	
			ArtifactFieldValue[] lookupValues = new ArtifactFieldValue[4];
			lookupValues[0] = new ArtifactFieldValue(1, "1 - Critical");
			lookupValues[1] = new ArtifactFieldValue(2, "2 - High");
			lookupValues[2] = new ArtifactFieldValue(3, "3 - Medium");
			lookupValues[3] = new ArtifactFieldValue(4, "4 - Low");
			this.taskField_TaskPriority.setValues(lookupValues);
		}
		return this.taskField_TaskPriority;
	}
	
	/**
	 * Gets the list of incidents assigned to the current user
	 * @return List of incidents
	 * @throws SpiraException
	 */
	public List<Incident> incidentRetrieveAssigned(IProgressMonitor monitor)
		throws SpiraException
	{
		try
		{	
			//First we need to re-authenticate
			boolean success = soap.connectionAuthenticate2(this.userName, this.password, SPIRA_PLUG_IN_NAME);
			if (!success)
			{
				//throw new SpiraException (this.userName + "/" + this.password);
				throw new SpiraAuthenticationException(Messages.SpiraImportExport_UnableToAuthenticate);
			}
				
			//Call the appropriate method
			List<RemoteIncident> remoteIncidents = soap.incidentRetrieveForOwner().getRemoteIncident();
			
			//Convert the SOAP incidents into the local versions
			List<Incident> incidents = new ArrayList<Incident>();
			for (RemoteIncident remoteIncident : remoteIncidents)
			{
				Incident incident = new Incident(remoteIncident);
				incidents.add(incident);
			}
	        return incidents;
		}
		catch (WebServiceException ex)
		{
			throw new SpiraException(ex.getMessage());
		}
	}
	
	/**
	 * Gets a single task by its key (RQ prefix + task id)
	 * @param artifactKey The key for the task (RQ prefix + task id)
	 * @param monitor
	 * @param projectId The project id
	 * @return Single task object
	 * @throws SpiraException
	 */
	public Task taskRetrieveByKey(String artifactKey, int projectId, IProgressMonitor monitor)
		throws SpiraException
	{
		try
		{	
			//First make sure that the artifact key is in the correct format
			if (artifactKey == null)
			{
				throw new SpiraInvalidArtifactKeyException(Messages.SpiraImportExport_ArtifactKeyNull);
			}
			if (artifactKey.length() < 3)
			{
				throw new SpiraInvalidArtifactKeyException(NLS.bind(Messages.SpiraImportExport_InvalidArtifactKey, artifactKey));
			}
			if (!artifactKey.substring(0, 2).equals(ArtifactType.TASK.getPrefix()))
			{
				throw new SpiraInvalidArtifactKeyException(NLS.bind(Messages.SpiraImportExport_InvalidArtifactKey, artifactKey));
			}
			int taskId;
			try
			{
				taskId = Integer.parseInt(artifactKey.substring(2));
			}
			catch (NumberFormatException e)
			{
				throw new SpiraInvalidArtifactKeyException(NLS.bind(Messages.SpiraImportExport_InvalidArtifactKey, artifactKey));
			}
			
			//Next we need to re-authenticate
			boolean success = soap.connectionAuthenticate2(this.userName, this.password, SPIRA_PLUG_IN_NAME);
			if (!success)
			{
				//throw new SpiraException (this.userName + "/" + this.password);
				throw new SpiraAuthenticationException(Messages.SpiraImportExport_UnableToAuthenticate);
			}
			
			//Next we need to connect to the appropriate project
			success = soap.connectionConnectToProject(projectId);
			if (!success)
			{
				//throw new SpiraException (this.userName + "/" + this.password);
				throw new SpiraAuthorizationException(NLS.bind(Messages.SpiraImportExport_UnableToConnectToProject, projectId));
			}
				
			//Call the appropriate method
			RemoteTask remoteTask = soap.taskRetrieveById(taskId);
			
			//Convert the SOAP task into the local version
			Task task = new Task(remoteTask);
	        return task;
		}
		catch (WebServiceException ex)
		{
			throw new SpiraException(ex.getMessage());
		}
	}
	
	/**
	 * Gets the list of tasks assigned to the current user
	 * @return List of tasks
	 * @throws SpiraException
	 */
	public List<Task> taskRetrieveAssigned(IProgressMonitor monitor)
		throws SpiraException
	{
		try
		{	
			//First we need to re-authenticate
			boolean success = soap.connectionAuthenticate2(this.userName, this.password, SPIRA_PLUG_IN_NAME);
			if (!success)
			{
				//throw new SpiraException (this.userName + "/" + this.password);
				throw new SpiraAuthenticationException(Messages.SpiraImportExport_UnableToAuthenticate);
			}
				
			//Call the appropriate method
			List<RemoteTask> remoteTasks = soap.taskRetrieveForOwner().getRemoteTask();
			
			//Convert the SOAP tasks into the local versions
			List<Task> tasks = new ArrayList<Task>();
			for (RemoteTask remoteTask : remoteTasks)
			{
				Task task = new Task(remoteTask);
				tasks.add(task);
			}
	        return tasks;
		}
		catch (WebServiceException ex)
		{
			throw new SpiraException(ex.getMessage());
		}
	}
}
