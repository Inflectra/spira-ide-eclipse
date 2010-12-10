/**
 * 
 */
package com.inflectra.spirateam.mylyn.core.internal.services;

import javax.xml.bind.JAXBElement;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import javax.xml.ws.*;
import javax.xml.ws.soap.SOAPFaultException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.osgi.util.NLS;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.inflectra.spirateam.mylyn.core.internal.ArtifactType;
import com.inflectra.spirateam.mylyn.core.internal.SpiraTeamClientData;
import com.inflectra.spirateam.mylyn.core.internal.SpiraTeamUtil;
import com.inflectra.spirateam.mylyn.core.internal.model.ArtifactField;
import com.inflectra.spirateam.mylyn.core.internal.model.ArtifactFieldValue;
import com.inflectra.spirateam.mylyn.core.internal.model.Incident;
import com.inflectra.spirateam.mylyn.core.internal.model.IncidentResolution;
import com.inflectra.spirateam.mylyn.core.internal.model.IncidentWorkflowField;
import com.inflectra.spirateam.mylyn.core.internal.model.IncidentWorkflowTransition;
import com.inflectra.spirateam.mylyn.core.internal.model.Requirement;
import com.inflectra.spirateam.mylyn.core.internal.model.RequirementComment;
import com.inflectra.spirateam.mylyn.core.internal.model.Task;
import com.inflectra.spirateam.mylyn.core.internal.model.TaskComment;
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
	private static final String WEB_SERVICE_SUFFIX = "/Services/v3_0/ImportExport.svc";	//$NON-NLS-1$
	private static final String WEB_SERVICE_NAMESPACE = "{http://www.inflectra.com/SpiraTest/Services/v3.0/}ImportExport";	//$NON-NLS-1$
	private static final String WEB_SERVICE_NAMESPACE_DATA_OBJECTS = "http://schemas.datacontract.org/2004/07/Inflectra.SpiraTest.Web.Services.v3_0.DataObjects";	//$NON-NLS-1$
	private static final String SPIRA_PLUG_IN_NAME = "Eclipse-IDE";	//$NON-NLS-1$
	
	private URL serviceUrl = null;
	private String userName = "";
	private String password = "";
	private ImportExport service = null;
	private IImportExport soap = null;
	private Integer storedProjectId = null;
	private String productName = "";
	private Integer authenticatedUserId = null;
	private int patchNumber = 0;
	private int productVersionPrimary = 0;
	private int productVersionSecondary = 0;
	private int productVersionTertiary = 0;
	
	private ArtifactField requirementField_Status = null;
	private ArtifactField requirementField_Importance = null;
	private ArtifactField taskField_TaskStatus = null;
	private ArtifactField taskField_TaskPriority = null;
	
	//Specific constant ID values
	public static int TASK_STATUS_COMPLETED = 3;

	protected SpiraTeamClientData data;
	
	/***
	 * Creates a JAXB web service string element from a Java string
	 * @param value
	 * @return
	 */
	public static JAXBElement<String> CreateJAXBString(String fieldName, String value)
	{
		JAXBElement<String> jaxString = new JAXBElement<String>(new QName(WEB_SERVICE_NAMESPACE_DATA_OBJECTS, fieldName), String.class, value);
		if (value == null)
		{
			jaxString.setNil(true);
		}
		return jaxString;
	}
	
	/***
	 * Creates a JAXB web service integer element from a Java integer
	 * @param value
	 * @return
	 */
	public static JAXBElement<Integer> CreateJAXBInteger(String fieldName, Integer value)
	{
		JAXBElement<Integer> jaxInteger = new JAXBElement<Integer>(new QName(WEB_SERVICE_NAMESPACE_DATA_OBJECTS, fieldName), Integer.class, value);
		if (value == null)
		{
			jaxInteger.setNil(true);
		}
		return jaxInteger;
	}
	
	/***
	 * Creates a JAXB web service XMLGregorianCalendar element from a Java XMLGregorianCalendar object
	 * @param value
	 * @return
	 */
	public static JAXBElement<XMLGregorianCalendar> CreateJAXBXMLGregorianCalendar(String fieldName, XMLGregorianCalendar value)
	{
		JAXBElement<XMLGregorianCalendar> jaxXMLGregorianCalendar = new JAXBElement<XMLGregorianCalendar>(new QName(WEB_SERVICE_NAMESPACE_DATA_OBJECTS, fieldName), XMLGregorianCalendar.class, value);
		if (value == null)
		{
			jaxXMLGregorianCalendar.setNil(true);
		}
		return jaxXMLGregorianCalendar;
	}
	
	public boolean hasAttributes()
	{
		return (data.lastUpdate != 0);
	}

	public void updateAttributes(IProgressMonitor monitor, boolean force)
	{
		if (!hasAttributes() || force)
		{
			data.lastUpdate = System.currentTimeMillis();
		}
	}
	
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
			this.soap = this.service.getBasicHttpBindingIImportExport();
			
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
			this.soap = this.service.getBasicHttpBindingIImportExport();
			
			//Make sure that session is maintained
			Map<String, Object> requestContext = ((BindingProvider)this.soap).getRequestContext();
			requestContext.put(BindingProvider.SESSION_MAINTAIN_PROPERTY,true);

		}
		catch (WebServiceException ex)
		{
			throw new SpiraConnectionException(Messages.SpiraConnectionException_Message + " (" + ex.getMessage() + ")");
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

	public String getProductName()
	{
		return this.productName;
	}
	public int getPatchNumber()
	{
		return this.patchNumber;
	}
	public int getProductVersionPrimary()
	{
		return this.productVersionPrimary;
	}
	public int getProductVersionSecondary()
	{
		return this.productVersionSecondary;
	}
	public int getProductVersionTertiary()
	{
		return this.productVersionTertiary;
	}
	public Integer getAuthenticatedUserId()
	{
		return this.authenticatedUserId;
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
	public IImportExport getSoap()
	{
		return this.soap;
	}

	public void setData(SpiraTeamClientData data)
	{
		this.data = data;
	}
	
	public SpiraTeamClientData getData()
	{
		return this.data;
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
	        
	        //Now get the version and product information
	        this.productName = soap.systemGetProductName();
	        
	        //Version number
	        RemoteVersion productVersion = soap.systemGetProductVersion();
	        String versionString = productVersion.getVersion().getValue();
	        String[] versionElements = versionString.split("\\.");
	        this.productVersionPrimary = 0;
	        this.productVersionSecondary = 0;
	        this.productVersionTertiary = 0;
	        if (versionElements.length >= 1)
	        {
	        	this.productVersionPrimary = Integer.parseInt(versionElements[0]);
	        }
	        if (versionElements.length >= 2)
	        {
	        	this.productVersionSecondary = Integer.parseInt(versionElements[1]);
	        }
	        if (versionElements.length >= 3)
	        {
	        	this.productVersionTertiary = Integer.parseInt(versionElements[2]);
	        }
	        	
	        //Patch Number
	        this.patchNumber = productVersion.getPatch().getValue();
	        
	        //Get the ID of the currently authenticated user
	        RemoteUser remoteUser = soap.userRetrieveByUserName(userName);
	        if (remoteUser != null)
	        {
	        	this.authenticatedUserId = remoteUser.getUserId().getValue();
	        }
	        
	        return success;
		}
		catch (WebServiceException ex)
		{	
			throw new SpiraConnectionException(Messages.SpiraConnectionException_Message);
		}
		catch (IImportExportUserRetrieveByUserNameServiceFaultMessageFaultFaultMessage exception)
		{
			throw new SpiraConnectionException(Messages.SpiraConnectionException_Message);
		}
		catch (IImportExportSystemGetProductNameServiceFaultMessageFaultFaultMessage exception)
		{
			throw new SpiraConnectionException(Messages.SpiraConnectionException_Message);
		}
		catch (IImportExportConnectionAuthenticate2ServiceFaultMessageFaultFaultMessage exception)
		{
			throw new SpiraConnectionException(Messages.SpiraConnectionException_Message);
		}
		catch (IImportExportSystemGetProductVersionServiceFaultMessageFaultFaultMessage exception)
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
			
			//Now get any associated comments
			List<RemoteComment> remoteComments = soap.requirementRetrieveComments(requirementId).getRemoteComment();
			
			//Convert the SOAP resolutions into the local version
			for (RemoteComment remoteComment : remoteComments)
			{
				RequirementComment requirementComment = new RequirementComment(remoteComment);
				requirement.getComments().add(requirementComment);
			}
						
	        return requirement;
		}
		catch (WebServiceException ex)
		{
			throw new SpiraException(ex.getMessage());
		}
		catch (IImportExportConnectionAuthenticate2ServiceFaultMessageFaultFaultMessage exception)
		{
			throw new SpiraException(exception.getMessage());
		}
		catch (IImportExportRequirementRetrieveByIdServiceFaultMessageFaultFaultMessage exception)
		{
			throw new SpiraException(exception.getMessage());
		}
		catch (IImportExportConnectionConnectToProjectServiceFaultMessageFaultFaultMessage exception)
		{
			throw new SpiraException(exception.getMessage());
		} catch (IImportExportRequirementRetrieveCommentsServiceFaultMessageFaultFaultMessage exception)
		{
			throw new SpiraException(exception.getMessage());
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
				//Add to the stored artifact-key to project mapping
				if (data != null)
				{
					if (data.taskToProjectMapping == null)
					{
						data.taskToProjectMapping = new HashMap<String, Integer>();
					}
					if (!data.taskToProjectMapping.containsKey(requirement.getArtifactKey()))
					{
						data.taskToProjectMapping.put(requirement.getArtifactKey(), requirement.getProjectId());
					}
				}
			}
	        return requirements;
		}
		catch (WebServiceException ex)
		{
			throw new SpiraException(ex.getMessage());
		}
		catch (IImportExportConnectionAuthenticate2ServiceFaultMessageFaultFaultMessage exception)
		{
			throw new SpiraException(exception.getMessage());
		}
		catch (IImportExportRequirementRetrieveForOwnerServiceFaultMessageFaultFaultMessage exception)
		{
			throw new SpiraException(exception.getMessage());
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
			
			//Now get the resolutions
			List<RemoteIncidentResolution> remoteResolutions = soap.incidentRetrieveResolutions(incidentId).getRemoteIncidentResolution();
			
			//Convert the SOAP resolutions into the local version
			for (RemoteIncidentResolution remoteResolution : remoteResolutions)
			{
				IncidentResolution incidentResolution = new IncidentResolution(remoteResolution);
				incident.getResolutions().add(incidentResolution);
			}
					
	        return incident;
		}
		catch (WebServiceException ex)
		{
			throw new SpiraException(ex.getMessage());
		}
		catch (IImportExportConnectionConnectToProjectServiceFaultMessageFaultFaultMessage exception)
		{
			throw new SpiraException(exception.getMessage());
		}
		catch (IImportExportIncidentRetrieveByIdServiceFaultMessageFaultFaultMessage exception)
		{
			throw new SpiraException(exception.getMessage());
		}
		catch (IImportExportConnectionAuthenticate2ServiceFaultMessageFaultFaultMessage exception)
		{
			throw new SpiraException(exception.getMessage());
		} catch (IImportExportIncidentRetrieveResolutionsServiceFaultMessageFaultFaultMessage exception)
		{
			throw new SpiraException(exception.getMessage());
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
		if (name.equals("RequirementStatus"))
		{
			return requirementGetStatus();
		}
		if (name.equals("RequirementImportance"))
		{
			return requirementGetImportance();
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
	
	public ArtifactField releasesGet(boolean activeOnly)
	{
		//Don't return releases if we have no project set
		if (this.storedProjectId == null)
		{
			return null;
		}
		int projectId = this.storedProjectId.intValue();
		return this.releasesGet(activeOnly, projectId);
	}
	
	public ArtifactField usersGet()
	{
		//Don't return releases if we have no project set
		if (this.storedProjectId == null)
		{
			return null;
		}
		int projectId = this.storedProjectId.intValue();
		return this.usersGet(projectId);
	}
	
	public ArtifactField usersGet(int projectId)
	{
		try
		{
			//Get the list of users from the SOAP API
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
				
			//Get the list of users
			List<RemoteProjectUser> remoteProjectUsers = soap.projectRetrieveUserMembership().getRemoteProjectUser();
			
			//Convert the SOAP project user into the ArtifactField class
			ArtifactField artifactField = new ArtifactField("User");
			ArrayList<ArtifactFieldValue> lookupValues = new ArrayList<ArtifactFieldValue>();
			for (RemoteProjectUser remoteProjectUser : remoteProjectUsers)
			{
				int userId = remoteProjectUser.getUserId().getValue();
				lookupValues.add(new ArtifactFieldValue(userId, remoteProjectUser.getFirstName().getValue() + " " + remoteProjectUser.getLastName().getValue() + " [" + remoteProjectUser.getEmailAddress().getValue() + "]"));
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
		catch (IImportExportConnectionConnectToProjectServiceFaultMessageFaultFaultMessage exception)
		{
			return null;
		}
		catch (IImportExportProjectRetrieveUserMembershipServiceFaultMessageFaultFaultMessage exception)
		{
			return null;
		}
		catch (IImportExportConnectionAuthenticate2ServiceFaultMessageFaultFaultMessage exception)
		{
			return null;
		}
	}
	
	public ArtifactField releasesGet(boolean activeOnly, int projectId)
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
			List<RemoteRelease> remoteReleases = soap.releaseRetrieve(activeOnly).getRemoteRelease();
			
			//Convert the SOAP release into the ArtifactField class
			ArtifactField artifactField = new ArtifactField("Release");
			ArrayList<ArtifactFieldValue> lookupValues = new ArrayList<ArtifactFieldValue>();
			for (RemoteRelease remoteRelease : remoteReleases)
			{
				//Indent with spaces. Also need to make releases look slightly different
				String indentDisplay = remoteRelease.getIndentLevel().getValue().replaceAll("[A-Z]", " ");
				if (remoteRelease.isIteration())
				{
					lookupValues.add(new ArtifactFieldValue(remoteRelease.getReleaseId().getValue(), indentDisplay + remoteRelease.getVersionNumber().getValue() + " - " + remoteRelease.getName().getValue() + "*"));				
				}
				else
				{
					lookupValues.add(new ArtifactFieldValue(remoteRelease.getReleaseId().getValue(), indentDisplay + remoteRelease.getVersionNumber().getValue() + " - " + remoteRelease.getName().getValue()));
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
		} catch (IImportExportConnectionConnectToProjectServiceFaultMessageFaultFaultMessage exception)
		{
			return null;
		} catch (IImportExportReleaseRetrieveServiceFaultMessageFaultFaultMessage exception)
		{
			return null;
		} catch (IImportExportConnectionAuthenticate2ServiceFaultMessageFaultFaultMessage exception)
		{
			return null;
		}
	}
	
	public List<IncidentWorkflowTransition> incidentRetrieveWorkflowTransitions(int currentTypeId, int currentStatusId, boolean isDetector, boolean isOwner)
		throws SpiraException
	{
		//Don't return releases if we have no project set
		if (this.storedProjectId == null)
		{
			return null;
		}
		int projectId = this.storedProjectId.intValue();
		return this.incidentRetrieveWorkflowTransitions(projectId, currentTypeId, currentStatusId, isDetector, isOwner);
	}
	
	public List<IncidentWorkflowTransition> incidentRetrieveWorkflowTransitions(int projectId, int currentTypeId, int currentStatusId, boolean isDetector, boolean isOwner)
		throws SpiraException
	{
		try
		{
			//Get the list of incident statuses from the SOAP API
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
				
			//Get the list of workflow transitions
			List<RemoteWorkflowIncidentTransition> remoteTransitions = soap.incidentRetrieveWorkflowTransitions(currentTypeId, currentStatusId, isDetector, isOwner).getRemoteWorkflowIncidentTransition();
			
			//Convert the SOAP transitions into local versions
			ArrayList<IncidentWorkflowTransition> transitions = new ArrayList<IncidentWorkflowTransition>();
			for (RemoteWorkflowIncidentTransition remoteTransition : remoteTransitions)
			{
				transitions.add(new IncidentWorkflowTransition(remoteTransition));
			}		
			return transitions;
		}
		catch (WebServiceException ex)
		{
			throw new SpiraException(ex.getMessage());
		} catch (IImportExportConnectionAuthenticate2ServiceFaultMessageFaultFaultMessage ex)
		{
			throw new SpiraException(ex.getMessage());
		} catch (IImportExportConnectionConnectToProjectServiceFaultMessageFaultFaultMessage ex)
		{
			throw new SpiraException(ex.getMessage());
		} catch (IImportExportIncidentRetrieveWorkflowTransitionsServiceFaultMessageFaultFaultMessage ex)
		{
			throw new SpiraException(ex.getMessage());
		}
	}
	
	public List<IncidentWorkflowField> incidentRetrieveWorkflowFields(int currentIncidentTypeId, int currentIncidentStatusId)
	throws SpiraException
{
	//Don't return fields if we have no project set
	if (this.storedProjectId == null)
	{
		return null;
	}
	int projectId = this.storedProjectId.intValue();
	return this.incidentRetrieveWorkflowFields(projectId, currentIncidentTypeId, currentIncidentStatusId);
}

public List<IncidentWorkflowField> incidentRetrieveWorkflowFields(int projectId, int currentIncidentTypeId, int currentIncidentStatusId)
	throws SpiraException
{
	try
	{
		//Get the list of incident statuses from the SOAP API
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
			
		//Get the list of workflow fields (active/required)
		List<RemoteWorkflowIncidentFields> remoteFields = soap.incidentRetrieveWorkflowFields(currentIncidentTypeId, currentIncidentStatusId).getRemoteWorkflowIncidentFields();
		
		//Convert the SOAP workflow fields into local versions
		ArrayList<IncidentWorkflowField> fields = new ArrayList<IncidentWorkflowField>();
		for (RemoteWorkflowIncidentFields remoteField : remoteFields)
		{
			fields.add(new IncidentWorkflowField(remoteField));
		}		
		return fields;
	}
	catch (WebServiceException ex)
	{
		throw new SpiraException(ex.getMessage());
	} catch (IImportExportConnectionAuthenticate2ServiceFaultMessageFaultFaultMessage ex)
	{
		throw new SpiraException(ex.getMessage());
	} catch (IImportExportIncidentRetrieveWorkflowFieldsServiceFaultMessageFaultFaultMessage ex)
	{
		throw new SpiraException(ex.getMessage());
	} catch (IImportExportConnectionConnectToProjectServiceFaultMessageFaultFaultMessage ex)
	{
		throw new SpiraException(ex.getMessage());
	}
}
	
	public ArtifactField incidentGetStatus()
	{
		//Don't return releases if we have no project set
		if (this.storedProjectId == null)
		{
			return null;
		}
		int projectId = this.storedProjectId.intValue();
		return this.incidentGetStatus(projectId);
	}
	
	public ArtifactField incidentGetStatus(int projectId)
	{
		try
		{
			//Get the list of incident statuses from the SOAP API
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
				
			//Get the list of statuses
			List<RemoteIncidentStatus> remoteStatuses = soap.incidentRetrieveStatuses().getRemoteIncidentStatus();
			
			//Convert the SOAP release into the ArtifactField class
			ArtifactField artifactField = new ArtifactField("IncidentStatus");
			ArrayList<ArtifactFieldValue> lookupValues = new ArrayList<ArtifactFieldValue>();
			for (RemoteIncidentStatus remoteStatus : remoteStatuses)
			{
				lookupValues.add(new ArtifactFieldValue(remoteStatus.getIncidentStatusId().getValue(), remoteStatus.getName().getValue()));
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
		} catch (IImportExportIncidentRetrieveStatusesServiceFaultMessageFaultFaultMessage exception)
		{
			return null;
		} catch (IImportExportConnectionConnectToProjectServiceFaultMessageFaultFaultMessage exception)
		{
			return null;
		} catch (IImportExportConnectionAuthenticate2ServiceFaultMessageFaultFaultMessage exception)
		{
			return null;
		}
	}
	
	public ArtifactField incidentGetType()
	{
		//Don't return releases if we have no project set
		if (this.storedProjectId == null)
		{
			return null;
		}
		int projectId = this.storedProjectId.intValue();
		return this.incidentGetType(projectId);
	}
	
	public ArtifactField incidentGetType(int projectId)
	{
		try
		{
			//Get the list of incident types from the SOAP API
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
				
			//Get the list of types
			List<RemoteIncidentType> remoteTypes = soap.incidentRetrieveTypes().getRemoteIncidentType();
			
			//Convert the SOAP release into the ArtifactField class
			ArtifactField artifactField = new ArtifactField("IncidentType");
			ArrayList<ArtifactFieldValue> lookupValues = new ArrayList<ArtifactFieldValue>();
			for (RemoteIncidentType remoteType : remoteTypes)
			{
				lookupValues.add(new ArtifactFieldValue(remoteType.getIncidentTypeId().getValue(), remoteType.getName().getValue()));
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
		} catch (IImportExportConnectionConnectToProjectServiceFaultMessageFaultFaultMessage exception)
		{
			return null;
		} catch (IImportExportIncidentRetrieveTypesServiceFaultMessageFaultFaultMessage exception)
		{
			return null;
		} catch (IImportExportConnectionAuthenticate2ServiceFaultMessageFaultFaultMessage exception)
		{
			return null;
		}
	}
	
	public ArtifactField incidentGetPriority()
	{
		//Don't return releases if we have no project set
		if (this.storedProjectId == null)
		{
			return null;
		}
		int projectId = this.storedProjectId.intValue();
		return this.incidentGetPriority(projectId);
	}
	
	public ArtifactField incidentGetPriority(int projectId)
	{
		try
		{
			//Get the list of incident priorities from the SOAP API
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
				
			//Get the list of priorities
			List<RemoteIncidentPriority> remotePriorities = soap.incidentRetrievePriorities().getRemoteIncidentPriority();
			
			//Convert the SOAP release into the ArtifactField class
			ArtifactField artifactField = new ArtifactField("IncidentPriority");
			ArrayList<ArtifactFieldValue> lookupValues = new ArrayList<ArtifactFieldValue>();
			for (RemoteIncidentPriority remotePriority : remotePriorities)
			{
				lookupValues.add(new ArtifactFieldValue(remotePriority.getPriorityId().getValue(), remotePriority.getName().getValue()));
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
		} catch (IImportExportConnectionConnectToProjectServiceFaultMessageFaultFaultMessage exception)
		{
			return null;
		} catch (IImportExportIncidentRetrievePrioritiesServiceFaultMessageFaultFaultMessage exception)
		{
			return null;
		} catch (IImportExportConnectionAuthenticate2ServiceFaultMessageFaultFaultMessage exception)
		{
			return null;
		}
	}
	
	public ArtifactField incidentGetSeverity()
	{
		//Don't return releases if we have no project set
		if (this.storedProjectId == null)
		{
			return null;
		}
		int projectId = this.storedProjectId.intValue();
		return this.incidentGetSeverity(projectId);
	}
	
	public ArtifactField incidentGetSeverity(int projectId)
	{
		try
		{
			//Get the list of incident severities from the SOAP API
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
				
			//Get the list of severities
			List<RemoteIncidentSeverity> remoteSeverities = soap.incidentRetrieveSeverities().getRemoteIncidentSeverity();
			
			//Convert the SOAP release into the ArtifactField class
			ArtifactField artifactField = new ArtifactField("IncidentSeverity");
			ArrayList<ArtifactFieldValue> lookupValues = new ArrayList<ArtifactFieldValue>();
			for (RemoteIncidentSeverity remoteSeverity : remoteSeverities)
			{
				lookupValues.add(new ArtifactFieldValue(remoteSeverity.getSeverityId().getValue(), remoteSeverity.getName().getValue()));
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
		} catch (IImportExportConnectionAuthenticate2ServiceFaultMessageFaultFaultMessage exception)
		{
			return null;
		} catch (IImportExportIncidentRetrieveSeveritiesServiceFaultMessageFaultFaultMessage exception)
		{
			return null;
		} catch (IImportExportConnectionConnectToProjectServiceFaultMessageFaultFaultMessage exception)
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
			lookupValues[0] = new ArtifactFieldValue(1, Messages.TaskStatus_NotStarted);
			lookupValues[1] = new ArtifactFieldValue(2, Messages.TaskStatus_InProgress);
			lookupValues[2] = new ArtifactFieldValue(TASK_STATUS_COMPLETED, Messages.TaskStatus_Completed);
			lookupValues[3] = new ArtifactFieldValue(4, Messages.TaskStatus_Blocked);
			lookupValues[4] = new ArtifactFieldValue(5, Messages.TaskStatus_Deferred);
			this.taskField_TaskStatus.setValues(lookupValues);
		}
		return this.taskField_TaskStatus;
	}
	
	public ArtifactField taskGetPriority()
	{
		if (this.taskField_TaskPriority == null)
		{
			this.taskField_TaskPriority = new ArtifactField("TaskPriority");
			this.taskField_TaskPriority.setOptional(true);
	
			ArtifactFieldValue[] lookupValues = new ArtifactFieldValue[4];
			lookupValues[0] = new ArtifactFieldValue(1, Messages.TaskPriority_Critical);
			lookupValues[1] = new ArtifactFieldValue(2, Messages.TaskPriority_High);
			lookupValues[2] = new ArtifactFieldValue(3, Messages.TaskPriority_Medium);
			lookupValues[3] = new ArtifactFieldValue(4, Messages.TaskPriority_Low);
			this.taskField_TaskPriority.setValues(lookupValues);
		}
		return this.taskField_TaskPriority;
	}
	
	public ArtifactField requirementGetStatus()
	{
		if (this.requirementField_Status == null)
		{
			this.requirementField_Status = new ArtifactField("RequirementStatus");
			this.requirementField_Status.setOptional(false);
			
			ArtifactFieldValue[] lookupValues = new ArtifactFieldValue[7];
			lookupValues[0] = new ArtifactFieldValue(1, Messages.RequirementStatus_Requested);
			lookupValues[1] = new ArtifactFieldValue(2, Messages.RequirementStatus_Planned);
			lookupValues[2] = new ArtifactFieldValue(3, Messages.RequirementStatus_InProgress);
			lookupValues[3] = new ArtifactFieldValue(4, Messages.RequirementStatus_Completed);
			lookupValues[4] = new ArtifactFieldValue(5, Messages.RequirementStatus_Accepted);
			lookupValues[5] = new ArtifactFieldValue(6, Messages.RequirementStatus_Rejected);
			lookupValues[6] = new ArtifactFieldValue(7, Messages.RequirementStatus_Evaluated);
			
			this.requirementField_Status.setValues(lookupValues);
		}
		return this.requirementField_Status;
	}
	
	public ArtifactField requirementGetImportance()
	{
		if (this.requirementField_Importance == null)
		{
			this.requirementField_Importance = new ArtifactField("RequirementImportance");
			this.requirementField_Importance.setOptional(true);
	
			ArtifactFieldValue[] lookupValues = new ArtifactFieldValue[4];
			lookupValues[0] = new ArtifactFieldValue(1, Messages.RequirementImportance_Critical);
			lookupValues[1] = new ArtifactFieldValue(2, Messages.RequirementImportance_High);
			lookupValues[2] = new ArtifactFieldValue(3, Messages.RequirementImportance_Medium);
			lookupValues[3] = new ArtifactFieldValue(4, Messages.RequirementImportance_Low);
			this.requirementField_Importance.setValues(lookupValues);
		}
		return this.requirementField_Importance;
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
				
				//Add to the stored artifact-key to project mapping
				if (data != null)
				{
					if (data.taskToProjectMapping == null)
					{
						data.taskToProjectMapping = new HashMap<String, Integer>();
					}
					if (!data.taskToProjectMapping.containsKey(incident.getArtifactKey()))
					{
						data.taskToProjectMapping.put(incident.getArtifactKey(), incident.getProjectId());
					}
				}
			}
	        return incidents;
		}
		catch (WebServiceException ex)
		{
			throw new SpiraException(ex.getMessage());
		} catch (IImportExportConnectionAuthenticate2ServiceFaultMessageFaultFaultMessage ex)
		{
			throw new SpiraException(ex.getMessage());
		} catch (IImportExportIncidentRetrieveForOwnerServiceFaultMessageFaultFaultMessage ex)
		{
			throw new SpiraException(ex.getMessage());
		}
	}
	
	/**
	 * Updates a task object on the server
	 * @param task The task object
	 * @param newComment A new comment to be added
	 */
	public void taskUpdate(Task task, String newComment)
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
			
			//Next we need to connect to the appropriate project
			success = soap.connectionConnectToProject(task.getProjectId());
			if (!success)
			{
				//throw new SpiraException (this.userName + "/" + this.password);
				throw new SpiraAuthorizationException(NLS.bind(Messages.SpiraImportExport_UnableToConnectToProject, task.getProjectId()));
			}

			//Convert the local task into the SOAP version
			RemoteTask remoteTask = task.toSoapObject();
			
			//Call the appropriate method
			soap.taskUpdate(remoteTask);
			
			//See if we need to add a new comment as well
			if (newComment != null)
			{
				//Add the new comment
				Date date = new Date();	//Defaults to now
				RemoteComment remoteComment = new RemoteComment();
				remoteComment.setCreationDate(SpiraImportExport.CreateJAXBXMLGregorianCalendar("CreationDate", SpiraTeamUtil.convertDatesJava2Xml(date)));
				remoteComment.setArtifactId(task.getArtifactId());
				remoteComment.setText(CreateJAXBString("Text", newComment));
				soap.taskCreateComment(remoteComment);
			}
		}
		catch (SOAPFaultException ex)
		{
			throw SpiraTeamUtil.convertSoapFaults(ex);
		}
		catch (WebServiceException ex)
		{
			throw new SpiraException(ex.getMessage());
		} catch (IImportExportConnectionConnectToProjectServiceFaultMessageFaultFaultMessage exception)
		{
			throw new SpiraException(exception.getMessage());
		} catch (IImportExportTaskUpdateServiceFaultMessageFaultFaultMessage exception)
		{
			throw new SpiraException(exception.getMessage());
		} catch (IImportExportConnectionAuthenticate2ServiceFaultMessageFaultFaultMessage exception)
		{
			throw new SpiraException(exception.getMessage());
		} catch (IImportExportTaskCreateCommentServiceFaultMessageFaultFaultMessage exception)
		{
			throw new SpiraException(exception.getMessage());
		}
	}
	
	/**
	 * Updates a requirement object on the server
	 * @param requirement The requirement object
	 * @param newComment A new comment to be added
	 * @throws SpiraException
	 */
	public void requirementUpdate(Requirement requirement, String newComment)
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
			
			//Next we need to connect to the appropriate project
			success = soap.connectionConnectToProject(requirement.getProjectId());
			if (!success)
			{
				//throw new SpiraException (this.userName + "/" + this.password);
				throw new SpiraAuthorizationException(NLS.bind(Messages.SpiraImportExport_UnableToConnectToProject, requirement.getProjectId()));
			}

			//Convert the local requirement into the SOAP version
			RemoteRequirement remoteRequirement = requirement.toSoapObject();
			
			//Call the appropriate method to update the incident
			soap.requirementUpdate(remoteRequirement);
			
			//See if we need to add a new comment/resolution as well
			if (newComment != null)
			{
				//Add the new resolution
				Date date = new Date();	//Defaults to now
				RemoteComment remoteComment = new RemoteComment();
				remoteComment.setCreationDate(SpiraImportExport.CreateJAXBXMLGregorianCalendar("CreationDate", SpiraTeamUtil.convertDatesJava2Xml(date)));
				remoteComment.setArtifactId(requirement.getArtifactId());
				remoteComment.setText(CreateJAXBString("Text", newComment));
				soap.requirementCreateComment(remoteComment);
			}
		}
		catch (SOAPFaultException ex)
		{
			throw SpiraTeamUtil.convertSoapFaults(ex);
		}
		catch (WebServiceException ex)
		{
			throw new SpiraException(ex.getMessage());
		}
		catch (IImportExportConnectionAuthenticate2ServiceFaultMessageFaultFaultMessage exception)
		{
			throw new SpiraException(exception.getMessage());
		} catch (IImportExportConnectionConnectToProjectServiceFaultMessageFaultFaultMessage exception)
		{
			throw new SpiraException(exception.getMessage());
		} catch (IImportExportRequirementUpdateServiceFaultMessageFaultFaultMessage exception)
		{
			throw new SpiraException(exception.getMessage());
		} catch (IImportExportRequirementCreateCommentServiceFaultMessageFaultFaultMessage exception)
		{
			throw new SpiraException(exception.getMessage());
		}
	}
	
	/**
	 * Updates an incident object on the server
	 * @param incident The incident object
	 * @param newComment A new comment to be added
	 */
	public void incidentUpdate(Incident incident, String newComment)
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
			
			//Next we need to connect to the appropriate project
			success = soap.connectionConnectToProject(incident.getProjectId());
			if (!success)
			{
				//throw new SpiraException (this.userName + "/" + this.password);
				throw new SpiraAuthorizationException(NLS.bind(Messages.SpiraImportExport_UnableToConnectToProject, incident.getProjectId()));
			}

			//Convert the local incident into the SOAP version
			RemoteIncident remoteIncident = incident.toSoapObject();
			
			//Call the appropriate method to update the incident
			soap.incidentUpdate(remoteIncident);
			
			//See if we need to add a new comment/resolution as well
			if (newComment != null)
			{
				//Add the new resolution
				Date date = new Date();	//Defaults to now
				RemoteIncidentResolution remoteIncidentResolution = new RemoteIncidentResolution();
				remoteIncidentResolution.setCreationDate(SpiraTeamUtil.convertDatesJava2Xml(date));
				remoteIncidentResolution.setIncidentId(incident.getArtifactId());
				remoteIncidentResolution.setResolution(CreateJAXBString("Resolution", newComment));
				ArrayOfRemoteIncidentResolution remoteIncidentResolutions = new ArrayOfRemoteIncidentResolution();
				remoteIncidentResolutions.getRemoteIncidentResolution().add(remoteIncidentResolution);
				soap.incidentAddResolutions(remoteIncidentResolutions);
			}
		}
		catch (SOAPFaultException ex)
		{
			throw SpiraTeamUtil.convertSoapFaults(ex);
		}
		catch (WebServiceException ex)
		{
			throw new SpiraException(ex.getMessage());
		} catch (IImportExportIncidentUpdateServiceFaultMessageFaultFaultMessage exception)
		{
			throw new SpiraException(exception.getMessage());
		} catch (IImportExportIncidentAddResolutionsServiceFaultMessageFaultFaultMessage exception)
		{
			throw new SpiraException(exception.getMessage());
		} catch (IImportExportConnectionAuthenticate2ServiceFaultMessageFaultFaultMessage exception)
		{
			throw new SpiraException(exception.getMessage());
		} catch (IImportExportConnectionConnectToProjectServiceFaultMessageFaultFaultMessage exception)
		{
			throw new SpiraException(exception.getMessage());
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
			
			//We need to also get the requirement if one is set to get the name
			try
			{
				if (task.getRequirementId() != null)
				{					
					//Call the appropriate method
					RemoteRequirement remoteRequirement = soap.requirementRetrieveById(task.getRequirementId());
					if (remoteRequirement != null)
					{
						task.setRequirementName(remoteRequirement.getName().getValue() + " [RQ:" + task.getRequirementId() + "]");
					}
				}
			}
			catch (WebServiceException ex)
			{
				//The user might not have permissions so just use the ID in that case
				task.setRequirementName("[RQ:" + task.getRequirementId() + "]");
			} catch (IImportExportRequirementRetrieveByIdServiceFaultMessageFaultFaultMessage exception)
			{
				//The user might not have permissions so just use the ID in that case
				task.setRequirementName("[RQ:" + task.getRequirementId() + "]");
			}
			
			//Now get any associated comments
			List<RemoteComment> remoteComments = soap.taskRetrieveComments(taskId).getRemoteComment();
			
			//Convert the SOAP resolutions into the local version
			for (RemoteComment remoteComment : remoteComments)
			{
				TaskComment taskComment = new TaskComment(remoteComment);
				task.getComments().add(taskComment);
			}

			
	        return task;
		}
		catch (SOAPFaultException ex)
		{
			throw SpiraTeamUtil.convertSoapFaults(ex);
		}
		catch (WebServiceException ex)
		{
			throw new SpiraException(ex.getMessage());
		} catch (IImportExportConnectionAuthenticate2ServiceFaultMessageFaultFaultMessage ex)
		{
			throw new SpiraException(ex.getMessage());
		}
		catch (IImportExportConnectionConnectToProjectServiceFaultMessageFaultFaultMessage ex)
		{
			throw new SpiraException(ex.getMessage());
		}
		catch (IImportExportTaskRetrieveByIdServiceFaultMessageFaultFaultMessage ex)
		{
			throw new SpiraException(ex.getMessage());
		}
		catch (IImportExportTaskRetrieveCommentsServiceFaultMessageFaultFaultMessage ex)
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
				
				//Add to the stored artifact-key to project mapping
				if (data != null)
				{
					if (data.taskToProjectMapping == null)
					{
						data.taskToProjectMapping = new HashMap<String, Integer>();
					}
					if (!data.taskToProjectMapping.containsKey(task.getArtifactKey()))
					{
						data.taskToProjectMapping.put(task.getArtifactKey(), task.getProjectId());
					}
				}
			}
	        return tasks;
		}
		catch (WebServiceException ex)
		{
			throw new SpiraException(ex.getMessage());
		} catch (IImportExportTaskRetrieveForOwnerServiceFaultMessageFaultFaultMessage exception)
		{
			throw new SpiraException(exception.getMessage());
		} catch (IImportExportConnectionAuthenticate2ServiceFaultMessageFaultFaultMessage exception)
		{
			throw new SpiraException(exception.getMessage());
		}
	}
}
