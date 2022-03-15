/**
 * 
 */
package com.inflectra.spirateam.mylyn.core.internal.services;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.osgi.util.NLS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.apache.http.auth.InvalidCredentialsException;

import com.inflectra.spirateam.mylyn.core.internal.ArtifactType;
import com.inflectra.spirateam.mylyn.core.internal.SpiraTeamClientData;
import com.inflectra.spirateam.mylyn.core.internal.SpiraTeamCorePlugin;
import com.inflectra.spirateam.mylyn.core.internal.SpiraTeamUtil;
import com.inflectra.spirateam.mylyn.core.internal.model.ArtifactAttachment;
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
import com.inflectra.spirateam.mylyn.core.internal.model.ArtifactField.Type;
import com.inflectra.spirateam.mylyn.core.internal.services.SpiraConnectionException;
import com.inflectra.spirateam.mylyn.core.internal.rest.*;
/**
 * This defines the 'SpiraImportExport' class that provides the Java facade
 * for calling the REST web service exposed by SpiraTest
 * 
 * @author		Inflectra Corporation
 * @version		6.0.0
 *
 */
public class SpiraImportExport
{
	private static final String WEB_SERVICE_SUFFIX = "/Services/v6_0/RestService.svc"; //$NON-NLS-1$
	private static final String SPIRA_PLUG_IN_NAME = "Eclipse-IDE"; //$NON-NLS-1$

	//The JSON date format used by the Spira 6.0 API
	private static final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS";
	
	private String url;
	private URL fullUrl;
	private String userName;
	private String apiKey;
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

	// Specific constant ID values
	public static int TASK_STATUS_COMPLETED = 3;

	protected SpiraTeamClientData data;

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

	public SpiraImportExport(String url) throws MalformedURLException
	{
		// Trust all SSL certificates 
		SSLUtilities.trustAllHttpsCertificates(); 
 
		// Verify the url is OK
		this.fullUrl = new URL(url + WEB_SERVICE_SUFFIX); 
		this.url = url;
	}

	public SpiraImportExport(String url, String userName, String apiKey) throws MalformedURLException
	{
		// Trust all SSL certificates 
		SSLUtilities.trustAllHttpsCertificates(); 
 
		// Verify the url is OK
		this.fullUrl = new URL(url + WEB_SERVICE_SUFFIX); 
		this.url = url;
		this.userName = userName;
		this.apiKey = apiKey;
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
	 * The API key
	 */
	public String getApiKey()
	{
		return this.apiKey;
	}

	/**
	 * The API key
	 */
	public void setPassword(String apiKey)
	{
		this.apiKey = apiKey;
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
	 * Authenticates the user/password and stores the Spira version information
	 * 
	 * @return true if the username/password authenticates successfully
	 */
	public boolean connectionAuthenticate2() throws SpiraConnectionException, InvalidCredentialsException
	{
		try
		{
			boolean success = false;

			
			//Trust all SSL certificates
			SSLUtilities.trustAllHttpsCertificates();
			
			// Call the /users method
			String url = this.fullUrl + "/users";
			String json = httpGet(url, this.userName, this.apiKey);

			//Parse the returned data to make sure it is a valid user
			Gson gson = new Gson();
			RemoteUser remoteUser = gson.fromJson(json, RemoteUser.class);

			//Store the user id
			if (remoteUser != null)
			{
				this.authenticatedUserId = remoteUser.UserId;
			}

			// Now get the version and product information
			url = this.fullUrl + "/system/settings/product-name";
			json = httpGet(url, this.userName, this.apiKey);
			this.productName = json;

			// Version number
			url = this.fullUrl + "/system/product-version";
			json = httpGet(url, this.userName, this.apiKey);
			RemoteVersion productVersion = gson.fromJson(json, RemoteVersion.class);
			
			String versionString = productVersion.Version;
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

			// Patch Number
			this.patchNumber = productVersion.Patch;

			return success;
		}
		catch (IOException ex)
		{
			throw new SpiraConnectionException(Messages.SpiraConnectionException_Message);
		}
	}
	
	/**
	 * Performs an HTTP GET request ot the specified URL
	 *
	 * @param input The URL to perform the query on
	 * @param apiKey The Spira API Key to use
	 * @param login The Spira login to use
	 * @return An InputStream containing the JSON returned from the POST request
	 * @throws IOException
	 * @throws InvalidCredentialsException
	 */
	public static String httpGet(String input, String login, String apiKey) throws IOException, InvalidCredentialsException {
		URL url = new URL(input);

		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		//allow sending a request body
		connection.setDoOutput(true);
		connection.setRequestMethod("GET");
		//have the connection send and retrieve JSON
		connection.setRequestProperty("accept", "application/json; charset=utf-8");
		connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
		//Set the authentication headers
		connection.setRequestProperty("username", login);
		connection.setRequestProperty("api-key", apiKey);
		connection.connect();
		int responseCode = connection.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// return result
			return response.toString();
		} else {
			if (responseCode == 403)
			{
				throw new InvalidCredentialsException("Invalid Spira login and API Key were provided!");
			}
			if (responseCode == 400)
			{
				throw new InvalidCredentialsException("Invalid Spira login and API Key were provided!");
			}
			throw new IOException("GET request not worked: " + responseCode);
		}
	}

	/**
	 * Retrieves an attachment by its key (DC prefix plus ID)
	 * 
	 * @param attachmentKey
	 *            The id of the attachment prefixed by 'DC'
	 * @param projectId
	 *            The id of the current project
	 * @return
	 */
	public ArtifactAttachment attachmentRetrieveByKey(int projectId, String attachmentKey) throws SpiraException
	{
		try
		{
			// First make sure that the attachment key is in the correct format
			if (attachmentKey == null)
			{
				throw new SpiraInvalidArtifactKeyException(Messages.SpiraImportExport_ArtifactKeyNull);
			}
			if (attachmentKey.length() < 3)
			{
				throw new SpiraInvalidArtifactKeyException(NLS.bind(Messages.SpiraImportExport_InvalidArtifactKey, attachmentKey));
			}
			if (!attachmentKey.substring(0, 2).equals(ArtifactAttachment.ATTACHMENT_PREFIX))
			{
				throw new SpiraInvalidArtifactKeyException(NLS.bind(Messages.SpiraImportExport_InvalidArtifactKey, attachmentKey));
			}
			int attachmentId;
			try
			{
				attachmentId = Integer.parseInt(attachmentKey.substring(2));
			}
			catch (NumberFormatException e)
			{
				throw new SpiraInvalidArtifactKeyException(NLS.bind(Messages.SpiraImportExport_InvalidArtifactKey, attachmentKey));
			}

			// Next we need to re-authenticate
			boolean success = soap.connectionAuthenticate2(this.userName, this.password, SPIRA_PLUG_IN_NAME);
			if (!success)
			{
				// throw new SpiraException (this.userName + "/" +
				// this.password);
				throw new SpiraAuthenticationException(Messages.SpiraImportExport_UnableToAuthenticate);
			}

			// Next we need to connect to the appropriate project
			success = soap.connectionConnectToProject(projectId);
			if (!success)
			{
				// throw new SpiraException (this.userName + "/" +
				// this.password);
				throw new SpiraAuthorizationException(NLS.bind(Messages.SpiraImportExport_UnableToConnectToProject, projectId));
			}

			// Call the appropriate method
			RemoteDocument remoteDocument = soap.documentRetrieveById(attachmentId);

			// Convert the SOAP document into the local version
			ArtifactAttachment artifactAttachment = new ArtifactAttachment(remoteDocument);

			return artifactAttachment;
		}
		catch (WebServiceException ex)
		{
			throw new SpiraException(ex.getMessage());
		}
		catch (IImportExportConnectionAuthenticate2ServiceFaultMessageFaultFaultMessage exception)
		{
			throw new SpiraException(exception.getMessage());
		}
		catch (IImportExportConnectionConnectToProjectServiceFaultMessageFaultFaultMessage exception)
		{
			throw new SpiraException(exception.getMessage());
		}
		catch (IImportExportDocumentRetrieveByIdServiceFaultMessageFaultFaultMessage exception)
		{
			throw new SpiraException(exception.getMessage());
		}
	}

	/**
	 * Downloads a file attachment
	 * 
	 * @param attachmentKey
	 * @param out
	 * @author Inflectra Corporation
	 * @param projectId
	 */
	public byte[] downloadAttachment(int projectId, String attachmentKey) throws SpiraException
	{
		try
		{
			// First make sure that the attachment key is in the correct format
			if (attachmentKey == null)
			{
				throw new SpiraInvalidArtifactKeyException(Messages.SpiraImportExport_ArtifactKeyNull);
			}
			if (attachmentKey.length() < 3)
			{
				throw new SpiraInvalidArtifactKeyException(NLS.bind(Messages.SpiraImportExport_InvalidArtifactKey, attachmentKey));
			}
			if (!attachmentKey.substring(0, 2).equals(ArtifactAttachment.ATTACHMENT_PREFIX))
			{
				throw new SpiraInvalidArtifactKeyException(NLS.bind(Messages.SpiraImportExport_InvalidArtifactKey, attachmentKey));
			}
			int attachmentId;
			try
			{
				attachmentId = Integer.parseInt(attachmentKey.substring(2));
			}
			catch (NumberFormatException e)
			{
				throw new SpiraInvalidArtifactKeyException(NLS.bind(Messages.SpiraImportExport_InvalidArtifactKey, attachmentKey));
			}

			// Next we need to re-authenticate
			boolean success = soap.connectionAuthenticate2(this.userName, this.password, SPIRA_PLUG_IN_NAME);
			if (!success)
			{
				// throw new SpiraException (this.userName + "/" +
				// this.password);
				throw new SpiraAuthenticationException(Messages.SpiraImportExport_UnableToAuthenticate);
			}

			// Next we need to connect to the appropriate project
			success = soap.connectionConnectToProject(projectId);
			if (!success)
			{
				// throw new SpiraException (this.userName + "/" +
				// this.password);
				throw new SpiraAuthorizationException(NLS.bind(Messages.SpiraImportExport_UnableToConnectToProject, projectId));
			}

			// Call the appropriate method
			byte[] attachmentData = soap.documentOpenFile(attachmentId);

			return attachmentData;
		}
		catch (WebServiceException ex)
		{
			throw new SpiraException(ex.getMessage());
		}
		catch (IImportExportConnectionAuthenticate2ServiceFaultMessageFaultFaultMessage exception)
		{
			throw new SpiraException(exception.getMessage());
		}
		catch (IImportExportConnectionConnectToProjectServiceFaultMessageFaultFaultMessage exception)
		{
			throw new SpiraException(exception.getMessage());
		}
		catch (IImportExportDocumentOpenFileServiceFaultMessageFaultFaultMessage exception)
		{
			throw new SpiraException(exception.getMessage());
		}
	}

	/**
	 * Uploads an attachment into the system
	 * 
	 * @param artifactKey
	 *            The artifact prefix + numeric ID (e.g. RQ45)
	 * @param projectId
	 *            The current project
	 * @param artifactAttachment
	 *            The artifact attachment info
	 * @param attachmentData
	 *            The actual data
	 * @param comment
	 *            Any added comments
	 * @return
	 */
	public ArtifactAttachment attachmentUpload(int projectId, String artifactKey, ArtifactAttachment artifactAttachment, byte[] attachmentData, String comment)
			throws SpiraException
	{
		try
		{
			// First make sure that the artifact key is in the correct format
			if (artifactKey == null)
			{
				throw new SpiraInvalidArtifactKeyException(Messages.SpiraImportExport_ArtifactKeyNull);
			}
			if (artifactKey.length() < 3)
			{
				throw new SpiraInvalidArtifactKeyException(NLS.bind(Messages.SpiraImportExport_InvalidArtifactKey, artifactKey));
			}
			int artifactId;
			try
			{
				artifactId = Integer.parseInt(artifactKey.substring(2));
			}
			catch (NumberFormatException e)
			{
				throw new SpiraInvalidArtifactKeyException(NLS.bind(Messages.SpiraImportExport_InvalidArtifactKey, artifactKey));
			}

			// Get the artifact type id from the prefix
			ArtifactType artifactType = ArtifactType.byTaskKey(artifactKey);
			int artifactTypeId = artifactType.getArtifactTypeId();

			// Next we need to re-authenticate
			boolean success = soap.connectionAuthenticate2(this.userName, this.password, SPIRA_PLUG_IN_NAME);
			if (!success)
			{
				// throw new SpiraException (this.userName + "/" +
				// this.password);
				throw new SpiraAuthenticationException(Messages.SpiraImportExport_UnableToAuthenticate);
			}

			// Next we need to connect to the appropriate project
			success = soap.connectionConnectToProject(projectId);
			if (!success)
			{
				// throw new SpiraException (this.userName + "/" +
				// this.password);
				throw new SpiraAuthorizationException(NLS.bind(Messages.SpiraImportExport_UnableToConnectToProject, projectId));
			}

			// Call the appropriate method
			RemoteDocument remoteDocument = artifactAttachment.toSoapObject();
			remoteDocument.setArtifactId(SpiraImportExport.CreateJAXBInteger("ArtifactId", artifactId));
			remoteDocument.setArtifactTypeId(SpiraImportExport.CreateJAXBInteger("ArtifactTypeId", artifactTypeId));
			remoteDocument = soap.documentAddFile(remoteDocument, attachmentData);

			// See if we have to add a new comment
			if (comment != null && !comment.isEmpty())
			{
				// See what type of artifact we have and handle appropriately
				if (artifactType.equals(ArtifactType.REQUIREMENT))
				{
					// Add the new comment
					RemoteComment remoteComment = new RemoteComment();
					remoteComment.setCreationDate(SpiraImportExport.CreateJAXBXMLGregorianCalendar("CreationDate",
							SpiraTeamUtil.convertDatesJava2Xml(artifactAttachment.getCreationDate())));
					remoteComment.setArtifactId(artifactId);
					remoteComment.setText(CreateJAXBString("Text", comment));
					soap.requirementCreateComment(remoteComment);

				}
				else if (artifactType.equals(ArtifactType.INCIDENT))
				{
					// Add the new comment
					RemoteComment remoteComment = new RemoteComment();
					remoteComment.setCreationDate(SpiraImportExport.CreateJAXBXMLGregorianCalendar("CreationDate",
							SpiraTeamUtil.convertDatesJava2Xml(artifactAttachment.getCreationDate())));
					remoteComment.setArtifactId(artifactId);
					remoteComment.setText(CreateJAXBString("Text", comment));
					ArrayOfRemoteComment remoteComments = new ArrayOfRemoteComment();
					remoteComments.getRemoteComment().add(remoteComment);
					soap.incidentAddComments(remoteComments);

				}
				else if (artifactType.equals(ArtifactType.TASK))
				{
					// Add the new comment
					RemoteComment remoteComment = new RemoteComment();
					remoteComment.setCreationDate(SpiraImportExport.CreateJAXBXMLGregorianCalendar("CreationDate",
							SpiraTeamUtil.convertDatesJava2Xml(artifactAttachment.getCreationDate())));
					remoteComment.setArtifactId(artifactId);
					remoteComment.setText(CreateJAXBString("Text", comment));
					soap.taskCreateComment(remoteComment);
				}
			}

			return new ArtifactAttachment(remoteDocument);
		}
		catch (WebServiceException ex)
		{
			throw new SpiraException(ex.getMessage());
		}
		catch (IImportExportConnectionAuthenticate2ServiceFaultMessageFaultFaultMessage exception)
		{
			throw new SpiraException(exception.getMessage());
		}
		catch (IImportExportConnectionConnectToProjectServiceFaultMessageFaultFaultMessage exception)
		{
			throw new SpiraException(exception.getMessage());
		}
		catch (IImportExportDocumentAddFileServiceFaultMessageFaultFaultMessage exception)
		{
			throw new SpiraException(exception.getMessage());
		}
		catch (IImportExportRequirementCreateCommentServiceFaultMessageFaultFaultMessage exception)
		{
			throw new SpiraException(exception.getMessage());
		}
		catch (IImportExportTaskCreateCommentServiceFaultMessageFaultFaultMessage exception)
		{
			throw new SpiraException(exception.getMessage());
		}
		catch (IImportExportIncidentAddCommentsServiceFaultMessageFaultFaultMessage exception)
		{
			throw new SpiraException(exception.getMessage());
		}
	}

	/**
	 * Gets a single requirement by its key (RQ prefix + requirement id)
	 * 
	 * @param artifactKey
	 *            The key for the requirement (RQ prefix + requirement id)
	 * @param monitor
	 * @param projectId
	 *            The project id
	 * @return Single requirement object
	 * @throws SpiraException
	 */
	public Requirement requirementRetrieveByKey(String artifactKey, int projectId, IProgressMonitor monitor) throws SpiraException
	{
		try
		{
			// First make sure that the artifact key is in the correct format
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

			// Next we need to re-authenticate
			boolean success = soap.connectionAuthenticate2(this.userName, this.password, SPIRA_PLUG_IN_NAME);
			if (!success)
			{
				// throw new SpiraException (this.userName + "/" +
				// this.password);
				throw new SpiraAuthenticationException(Messages.SpiraImportExport_UnableToAuthenticate);
			}

			// Next we need to connect to the appropriate project
			success = soap.connectionConnectToProject(projectId);
			if (!success)
			{
				// throw new SpiraException (this.userName + "/" +
				// this.password);
				throw new SpiraAuthorizationException(NLS.bind(Messages.SpiraImportExport_UnableToConnectToProject, projectId));
			}

			// Call the appropriate method
			RemoteRequirement remoteRequirement = soap.requirementRetrieveById(requirementId);

			// Convert the SOAP requirement into the local version
			Requirement requirement = new Requirement(remoteRequirement);

			// Now get any associated comments
			List<RemoteComment> remoteComments = soap.requirementRetrieveComments(requirementId).getRemoteComment();

			// Convert the SOAP resolutions into the local version
			for (RemoteComment remoteComment : remoteComments)
			{
				RequirementComment requirementComment = new RequirementComment(remoteComment);
				requirement.getComments().add(requirementComment);
			}

			// Now get any associated attachments
			RemoteSort remoteSort = new RemoteSort();
			remoteSort.setPropertyName(CreateJAXBString("PropertyName", "UploadDate"));
			remoteSort.setSortAscending(false);
			List<RemoteDocument> remoteDocuments = soap.documentRetrieveForArtifact(ArtifactType.REQUIREMENT.getArtifactTypeId(), requirementId, null,
					remoteSort).getRemoteDocument();

			// Convert the SOAP attachments into the local version
			for (RemoteDocument remoteDocument : remoteDocuments)
			{
				ArtifactAttachment artifactAttachment = new ArtifactAttachment(remoteDocument);
				requirement.getAttachments().add(artifactAttachment);
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
		}
		catch (IImportExportRequirementRetrieveCommentsServiceFaultMessageFaultFaultMessage exception)
		{
			throw new SpiraException(exception.getMessage());
		}
		catch (IImportExportDocumentRetrieveForArtifactServiceFaultMessageFaultFaultMessage exception)
		{
			throw new SpiraException(exception.getMessage());
		}
	}

	/**
	 * Gets the list of requirements assigned to the current user
	 * 
	 * @return List of requirements
	 * @throws SpiraException
	 */
	public List<Requirement> requirementRetrieveAssigned(IProgressMonitor monitor) throws SpiraException
	{
		try
		{
			// First we need to re-authenticate
			boolean success = soap.connectionAuthenticate2(this.userName, this.password, SPIRA_PLUG_IN_NAME);
			if (!success)
			{
				// throw new SpiraException (this.userName + "/" +
				// this.password);
				throw new SpiraAuthenticationException(Messages.SpiraImportExport_UnableToAuthenticate);
			}

			// Call the appropriate method
			List<RemoteRequirement> remoteRequirements = soap.requirementRetrieveForOwner().getRemoteRequirement();

			// Convert the SOAP requirements into the local versions
			List<Requirement> requirements = new ArrayList<Requirement>();
			for (RemoteRequirement remoteRequirement : remoteRequirements)
			{
				Requirement requirement = new Requirement(remoteRequirement);
				requirements.add(requirement);
				// Add to the stored artifact-key to project mapping
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
	 * 
	 * @param artifactKey
	 *            The key for the incident (RQ prefix + incident id)
	 * @param monitor
	 * @param projectId
	 *            The project id
	 * @return Single incident object
	 * @throws SpiraException
	 */
	public Incident incidentRetrieveByKey(String artifactKey, int projectId, IProgressMonitor monitor) throws SpiraException
	{
		try
		{
			// First make sure that the artifact key is in the correct format
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

			// Next we need to re-authenticate
			boolean success = soap.connectionAuthenticate2(this.userName, this.password, SPIRA_PLUG_IN_NAME);
			if (!success)
			{
				// throw new SpiraException (this.userName + "/" +
				// this.password);
				throw new SpiraAuthenticationException(Messages.SpiraImportExport_UnableToAuthenticate);
			}

			// Next we need to connect to the appropriate project
			success = soap.connectionConnectToProject(projectId);
			if (!success)
			{
				// throw new SpiraException (this.userName + "/" +
				// this.password);
				throw new SpiraAuthorizationException(NLS.bind(Messages.SpiraImportExport_UnableToConnectToProject, projectId));
			}

			// Call the appropriate method
			RemoteIncident remoteIncident = soap.incidentRetrieveById(incidentId);

			// Convert the SOAP incident into the local version
			Incident incident = new Incident(remoteIncident);

			// Now get the comments
			List<RemoteComment> remoteComments = soap.incidentRetrieveComments(incidentId).getRemoteComment();

			// Convert the SOAP comments into the local version
			for (RemoteComment remoteComment : remoteComments)
			{
				IncidentResolution incidentResolution = new IncidentResolution(remoteComment);
				incident.getResolutions().add(incidentResolution);
			}

			// Now get any associated attachments
			RemoteSort remoteSort = new RemoteSort();
			remoteSort.setPropertyName(CreateJAXBString("PropertyName", "UploadDate"));
			remoteSort.setSortAscending(false);
			List<RemoteDocument> remoteDocuments = soap.documentRetrieveForArtifact(ArtifactType.INCIDENT.getArtifactTypeId(), incidentId, null, remoteSort)
					.getRemoteDocument();

			// Convert the SOAP attachments into the local version
			for (RemoteDocument remoteDocument : remoteDocuments)
			{
				ArtifactAttachment artifactAttachment = new ArtifactAttachment(remoteDocument);
				incident.getAttachments().add(artifactAttachment);
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
		}
		catch (IImportExportDocumentRetrieveForArtifactServiceFaultMessageFaultFaultMessage exception)
		{
			throw new SpiraException(exception.getMessage());
		}
		catch (IImportExportIncidentRetrieveCommentsServiceFaultMessageFaultFaultMessage exception)
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

	public ArtifactField releasesGet(boolean activeOnly)
	{
		// Don't return releases if we have no project set
		if (this.storedProjectId == null)
		{
			return null;
		}
		int projectId = this.storedProjectId.intValue();
		return this.releasesGet(activeOnly, projectId);
	}

	public ArtifactField usersGet()
	{
		// Don't return releases if we have no project set
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
			// Get the list of users from the SOAP API
			// First we need to re-authenticate
			boolean success = soap.connectionAuthenticate2(this.userName, this.password, SPIRA_PLUG_IN_NAME);
			if (!success)
			{
				// throw new SpiraException (this.userName + "/" +
				// this.password);
				throw new SpiraAuthenticationException(Messages.SpiraImportExport_UnableToAuthenticate);
			}

			// Next we need to connect to the appropriate project
			success = soap.connectionConnectToProject(projectId);
			if (!success)
			{
				// throw new SpiraException (this.userName + "/" +
				// this.password);
				throw new SpiraAuthorizationException(NLS.bind(Messages.SpiraImportExport_UnableToConnectToProject, projectId));
			}

			// Get the list of users
			List<RemoteProjectUser> remoteProjectUsers = soap.projectRetrieveUserMembership().getRemoteProjectUser();

			// Convert the SOAP project user into the ArtifactField class
			ArtifactField artifactField = new ArtifactField("User");
			ArrayList<ArtifactFieldValue> lookupValues = new ArrayList<ArtifactFieldValue>();
			for (RemoteProjectUser remoteProjectUser : remoteProjectUsers)
			{
				int userId = remoteProjectUser.getUserId().getValue();
				lookupValues.add(new ArtifactFieldValue(userId, remoteProjectUser.getFirstName().getValue() + " " + remoteProjectUser.getLastName().getValue()
						+ " [" + remoteProjectUser.getEmailAddress().getValue() + "]"));
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
			// Get the list of releases from the SOAP API
			// First we need to re-authenticate
			boolean success = soap.connectionAuthenticate2(this.userName, this.password, SPIRA_PLUG_IN_NAME);
			if (!success)
			{
				// throw new SpiraException (this.userName + "/" +
				// this.password);
				throw new SpiraAuthenticationException(Messages.SpiraImportExport_UnableToAuthenticate);
			}

			// Next we need to connect to the appropriate project
			success = soap.connectionConnectToProject(projectId);
			if (!success)
			{
				// throw new SpiraException (this.userName + "/" +
				// this.password);
				throw new SpiraAuthorizationException(NLS.bind(Messages.SpiraImportExport_UnableToConnectToProject, projectId));
			}

			// Get the list of releases
			List<RemoteRelease> remoteReleases = soap.releaseRetrieve(activeOnly).getRemoteRelease();

			// Convert the SOAP release into the ArtifactField class
			ArtifactField artifactField = new ArtifactField("Release");
			ArrayList<ArtifactFieldValue> lookupValues = new ArrayList<ArtifactFieldValue>();
			for (RemoteRelease remoteRelease : remoteReleases)
			{
				// Indent with spaces. Also need to make releases look slightly
				// different
				String indentDisplay = remoteRelease.getIndentLevel().getValue().replaceAll("[A-Z]", " ");
				if (remoteRelease.isIteration())
				{
					lookupValues.add(new ArtifactFieldValue(remoteRelease.getReleaseId().getValue(), indentDisplay
							+ remoteRelease.getVersionNumber().getValue() + " - " + remoteRelease.getName().getValue() + "*"));
				}
				else
				{
					lookupValues.add(new ArtifactFieldValue(remoteRelease.getReleaseId().getValue(), indentDisplay
							+ remoteRelease.getVersionNumber().getValue() + " - " + remoteRelease.getName().getValue()));
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
		catch (IImportExportConnectionConnectToProjectServiceFaultMessageFaultFaultMessage exception)
		{
			return null;
		}
		catch (IImportExportReleaseRetrieveServiceFaultMessageFaultFaultMessage exception)
		{
			return null;
		}
		catch (IImportExportConnectionAuthenticate2ServiceFaultMessageFaultFaultMessage exception)
		{
			return null;
		}
	}

	public List<IncidentWorkflowTransition> incidentRetrieveWorkflowTransitions(int currentTypeId, int currentStatusId, boolean isDetector, boolean isOwner)
			throws SpiraException
	{
		// Don't return releases if we have no project set
		if (this.storedProjectId == null)
		{
			return null;
		}
		int projectId = this.storedProjectId.intValue();
		return this.incidentRetrieveWorkflowTransitions(projectId, currentTypeId, currentStatusId, isDetector, isOwner);
	}

	public List<IncidentWorkflowTransition> incidentRetrieveWorkflowTransitions(int projectId, int currentTypeId, int currentStatusId, boolean isDetector,
			boolean isOwner) throws SpiraException
	{
		try
		{
			// Get the list of incident statuses from the SOAP API
			// First we need to re-authenticate
			boolean success = soap.connectionAuthenticate2(this.userName, this.password, SPIRA_PLUG_IN_NAME);
			if (!success)
			{
				// throw new SpiraException (this.userName + "/" +
				// this.password);
				throw new SpiraAuthenticationException(Messages.SpiraImportExport_UnableToAuthenticate);
			}

			// Next we need to connect to the appropriate project
			success = soap.connectionConnectToProject(projectId);
			if (!success)
			{
				// throw new SpiraException (this.userName + "/" +
				// this.password);
				throw new SpiraAuthorizationException(NLS.bind(Messages.SpiraImportExport_UnableToConnectToProject, projectId));
			}

			// Get the list of workflow transitions
			List<RemoteWorkflowIncidentTransition> remoteTransitions = soap.incidentRetrieveWorkflowTransitions(currentTypeId, currentStatusId, isDetector,
					isOwner).getRemoteWorkflowIncidentTransition();

			// Convert the SOAP transitions into local versions
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
		}
		catch (IImportExportConnectionAuthenticate2ServiceFaultMessageFaultFaultMessage ex)
		{
			throw new SpiraException(ex.getMessage());
		}
		catch (IImportExportConnectionConnectToProjectServiceFaultMessageFaultFaultMessage ex)
		{
			throw new SpiraException(ex.getMessage());
		}
		catch (IImportExportIncidentRetrieveWorkflowTransitionsServiceFaultMessageFaultFaultMessage ex)
		{
			throw new SpiraException(ex.getMessage());
		}
	}

	public List<IncidentWorkflowField> incidentRetrieveWorkflowFields(int currentIncidentTypeId, int currentIncidentStatusId) throws SpiraException
	{
		// Don't return fields if we have no project set
		if (this.storedProjectId == null)
		{
			return null;
		}
		int projectId = this.storedProjectId.intValue();
		return this.incidentRetrieveWorkflowFields(projectId, currentIncidentTypeId, currentIncidentStatusId);
	}

	/**
	 * Gets the list of incident workflow fields and custom properties for the
	 * current incident
	 * 
	 * @param projectId
	 * @param currentIncidentTypeId
	 * @param currentIncidentStatusId
	 * @return
	 * @throws SpiraException
	 */
	public List<IncidentWorkflowField> incidentRetrieveWorkflowFields(int projectId, int currentIncidentTypeId, int currentIncidentStatusId)
			throws SpiraException
	{
		try
		{
			// Get the list of incident statuses from the SOAP API
			// First we need to re-authenticate
			boolean success = soap.connectionAuthenticate2(this.userName, this.password, SPIRA_PLUG_IN_NAME);
			if (!success)
			{
				// throw new SpiraException (this.userName + "/" +
				// this.password);
				throw new SpiraAuthenticationException(Messages.SpiraImportExport_UnableToAuthenticate);
			}

			// Next we need to connect to the appropriate project
			success = soap.connectionConnectToProject(projectId);
			if (!success)
			{
				// throw new SpiraException (this.userName + "/" +
				// this.password);
				throw new SpiraAuthorizationException(NLS.bind(Messages.SpiraImportExport_UnableToConnectToProject, projectId));
			}

			// Get the list of workflow fields (inactive/required/hidden)
			List<RemoteWorkflowIncidentFields> remoteFields = soap.incidentRetrieveWorkflowFields(currentIncidentTypeId, currentIncidentStatusId)
					.getRemoteWorkflowIncidentFields();

			// Convert the SOAP workflow fields into local versions
			ArrayList<IncidentWorkflowField> fields = new ArrayList<IncidentWorkflowField>();
			for (RemoteWorkflowIncidentFields remoteField : remoteFields)
			{
				fields.add(new IncidentWorkflowField(remoteField));
			}

			// Get the list of workflow-controlled custom-properties
			// (inactive/required/hidden)
			List<RemoteWorkflowIncidentCustomProperties> remoteWorkflowCustomProperties = soap.incidentRetrieveWorkflowCustomProperties(currentIncidentTypeId,
					currentIncidentStatusId).getRemoteWorkflowIncidentCustomProperties();
			for (RemoteWorkflowIncidentCustomProperties remoteWorkflowCustomProperty : remoteWorkflowCustomProperties)
			{
				fields.add(new IncidentWorkflowField(remoteWorkflowCustomProperty));
			}

			return fields;
		}
		catch (WebServiceException ex)
		{
			throw new SpiraException(ex.getMessage());
		}
		catch (IImportExportConnectionAuthenticate2ServiceFaultMessageFaultFaultMessage ex)
		{
			throw new SpiraException(ex.getMessage());
		}
		catch (IImportExportIncidentRetrieveWorkflowFieldsServiceFaultMessageFaultFaultMessage ex)
		{
			throw new SpiraException(ex.getMessage());
		}
		catch (IImportExportConnectionConnectToProjectServiceFaultMessageFaultFaultMessage ex)
		{
			throw new SpiraException(ex.getMessage());
		}
		catch (IImportExportIncidentRetrieveWorkflowCustomPropertiesServiceFaultMessageFaultFaultMessage exception)
		{
			throw new SpiraException(exception.getMessage());
		}
	}

	public ArtifactField incidentGetStatus()
	{
		// Don't return releases if we have no project set
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
			// Get the list of incident statuses from the SOAP API
			// First we need to re-authenticate
			boolean success = soap.connectionAuthenticate2(this.userName, this.password, SPIRA_PLUG_IN_NAME);
			if (!success)
			{
				// throw new SpiraException (this.userName + "/" +
				// this.password);
				throw new SpiraAuthenticationException(Messages.SpiraImportExport_UnableToAuthenticate);
			}

			// Next we need to connect to the appropriate project
			success = soap.connectionConnectToProject(projectId);
			if (!success)
			{
				// throw new SpiraException (this.userName + "/" +
				// this.password);
				throw new SpiraAuthorizationException(NLS.bind(Messages.SpiraImportExport_UnableToConnectToProject, projectId));
			}

			// Get the list of statuses
			List<RemoteIncidentStatus> remoteStatuses = soap.incidentRetrieveStatuses().getRemoteIncidentStatus();

			// Convert the SOAP release into the ArtifactField class
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
		}
		catch (IImportExportIncidentRetrieveStatusesServiceFaultMessageFaultFaultMessage exception)
		{
			return null;
		}
		catch (IImportExportConnectionConnectToProjectServiceFaultMessageFaultFaultMessage exception)
		{
			return null;
		}
		catch (IImportExportConnectionAuthenticate2ServiceFaultMessageFaultFaultMessage exception)
		{
			return null;
		}
	}

	public ArtifactField incidentGetType()
	{
		// Don't return releases if we have no project set
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
			// Get the list of incident types from the SOAP API
			// First we need to re-authenticate
			boolean success = soap.connectionAuthenticate2(this.userName, this.password, SPIRA_PLUG_IN_NAME);
			if (!success)
			{
				// throw new SpiraException (this.userName + "/" +
				// this.password);
				throw new SpiraAuthenticationException(Messages.SpiraImportExport_UnableToAuthenticate);
			}

			// Next we need to connect to the appropriate project
			success = soap.connectionConnectToProject(projectId);
			if (!success)
			{
				// throw new SpiraException (this.userName + "/" +
				// this.password);
				throw new SpiraAuthorizationException(NLS.bind(Messages.SpiraImportExport_UnableToConnectToProject, projectId));
			}

			// Get the list of types
			List<RemoteIncidentType> remoteTypes = soap.incidentRetrieveTypes().getRemoteIncidentType();

			// Convert the SOAP release into the ArtifactField class
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
		}
		catch (IImportExportConnectionConnectToProjectServiceFaultMessageFaultFaultMessage exception)
		{
			return null;
		}
		catch (IImportExportIncidentRetrieveTypesServiceFaultMessageFaultFaultMessage exception)
		{
			return null;
		}
		catch (IImportExportConnectionAuthenticate2ServiceFaultMessageFaultFaultMessage exception)
		{
			return null;
		}
	}

	public ArtifactField incidentGetPriority()
	{
		// Don't return releases if we have no project set
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
			// Get the list of incident priorities from the SOAP API
			// First we need to re-authenticate
			boolean success = soap.connectionAuthenticate2(this.userName, this.password, SPIRA_PLUG_IN_NAME);
			if (!success)
			{
				// throw new SpiraException (this.userName + "/" +
				// this.password);
				throw new SpiraAuthenticationException(Messages.SpiraImportExport_UnableToAuthenticate);
			}

			// Next we need to connect to the appropriate project
			success = soap.connectionConnectToProject(projectId);
			if (!success)
			{
				// throw new SpiraException (this.userName + "/" +
				// this.password);
				throw new SpiraAuthorizationException(NLS.bind(Messages.SpiraImportExport_UnableToConnectToProject, projectId));
			}

			// Get the list of priorities
			List<RemoteIncidentPriority> remotePriorities = soap.incidentRetrievePriorities().getRemoteIncidentPriority();

			// Convert the SOAP release into the ArtifactField class
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
		}
		catch (IImportExportConnectionConnectToProjectServiceFaultMessageFaultFaultMessage exception)
		{
			return null;
		}
		catch (IImportExportIncidentRetrievePrioritiesServiceFaultMessageFaultFaultMessage exception)
		{
			return null;
		}
		catch (IImportExportConnectionAuthenticate2ServiceFaultMessageFaultFaultMessage exception)
		{
			return null;
		}
	}

	public ArtifactField incidentGetSeverity()
	{
		// Don't return releases if we have no project set
		if (this.storedProjectId == null)
		{
			return null;
		}
		int projectId = this.storedProjectId.intValue();
		return this.incidentGetSeverity(projectId);
	}

	/**
	 * Gets the list of custom properties for a specific project and artifact
	 * type
	 * 
	 * @param artifactType
	 *            The artifact type (Requirement, Task, Incident)
	 * @return Array of artifact fields
	 * @param projectId
	 *            Project id (optional, uses stored ID if set to null)
	 */
	public ArtifactField[] getCustomProperties(ArtifactType artifactType, Integer projectId)
	{
		try
		{
			// Get the stored project id if not set
			if (projectId == null)
			{
				projectId = this.getStoredProjectId();
			}

			// First we need to re-authenticate
			boolean success = soap.connectionAuthenticate2(this.userName, this.password, SPIRA_PLUG_IN_NAME);
			if (!success)
			{
				// throw new SpiraException (this.userName + "/" +
				// this.password);
				throw new SpiraAuthenticationException(Messages.SpiraImportExport_UnableToAuthenticate);
			}

			// Next we need to connect to the appropriate project
			success = soap.connectionConnectToProject(projectId);
			if (!success)
			{
				// throw new SpiraException (this.userName + "/" +
				// this.password);
				throw new SpiraAuthorizationException(NLS.bind(Messages.SpiraImportExport_UnableToConnectToProject, projectId));
			}

			// Get the list of custom property definitions
			List<RemoteCustomProperty> remoteCustomProperties = soap.customPropertyRetrieveForArtifactType(artifactType.getArtifactTypeId(), false)
					.getRemoteCustomProperty();

			// Convert the SOAP custom properties into the ArtifactField class
			ArrayList<ArtifactField> artifactFields = new ArrayList<ArtifactField>();
			for (RemoteCustomProperty remoteCustomProperty : remoteCustomProperties)
			{
				ArtifactField artifactField = new ArtifactField(remoteCustomProperty.getCustomPropertyFieldName().getValue());
				artifactField.setLabel(remoteCustomProperty.getName().getValue());
				artifactField.setCustom(true);

				// Get the list of options
				List<RemoteCustomPropertyOption> remoteCustomPropertyOptions = null;
				if (remoteCustomProperty.getOptions() != null && remoteCustomProperty.getOptions().getValue() != null
						&& !remoteCustomProperty.getOptions().getValue().getRemoteCustomPropertyOption().isEmpty())
				{
					remoteCustomPropertyOptions = remoteCustomProperty.getOptions().getValue().getRemoteCustomPropertyOption();
				}

				// Check to see if we have the allow-empty or default value
				// option specified
				boolean allowEmpty = true;
				String defaultValue = null;
				if (remoteCustomPropertyOptions != null)
				{
					for (RemoteCustomPropertyOption remoteCustomPropertyOption : remoteCustomPropertyOptions)
					{
						// Check for allow-empty option
						if (remoteCustomPropertyOption.getCustomPropertyOptionId().intValue() == SpiraTeamCorePlugin.CustomPropertyOption_AllowEmpty
								&& remoteCustomPropertyOption.getValue() != null)
						{
							allowEmpty = (remoteCustomPropertyOption.getValue().getValue().equals("Y"));
						}

						// Check for default-value option
						if (remoteCustomPropertyOption.getCustomPropertyOptionId().intValue() == SpiraTeamCorePlugin.CustomPropertyOption_Default
								&& remoteCustomPropertyOption.getValue() != null)
						{
							defaultValue = remoteCustomPropertyOption.getValue().getValue();
						}
					}
				}
				artifactField.setOptional(allowEmpty);
				artifactField.setDefaultValue(defaultValue);

				if (remoteCustomProperty.getCustomPropertyTypeId().intValue() == SpiraTeamCorePlugin.CustomPropertyType_Text)
				{
					// See if we have a rich-text or plain text custom property
					boolean isRichText = false;
					if (remoteCustomPropertyOptions != null)
					{
						for (RemoteCustomPropertyOption remoteCustomPropertyOption : remoteCustomPropertyOptions)
						{
							if (remoteCustomPropertyOption.getCustomPropertyOptionId().intValue() == SpiraTeamCorePlugin.CustomPropertyOption_RichText
									&& remoteCustomPropertyOption.getValue() != null)
							{
								isRichText = (remoteCustomPropertyOption.getValue().getValue().equals("Y"));
							}
						}
					}
					artifactField.setType((isRichText) ? Type.RICH_TEXT : Type.TEXT);
				}
				if (remoteCustomProperty.getCustomPropertyTypeId().intValue() == SpiraTeamCorePlugin.CustomPropertyType_Integer)
				{
					artifactField.setType(Type.INTEGER);
				}
				if (remoteCustomProperty.getCustomPropertyTypeId().intValue() == SpiraTeamCorePlugin.CustomPropertyType_Boolean)
				{
					artifactField.setType(Type.CHECKBOX);
				}
				if (remoteCustomProperty.getCustomPropertyTypeId().intValue() == SpiraTeamCorePlugin.CustomPropertyType_Decimal)
				{
					artifactField.setType(Type.DOUBLE);

					// Determine what precision is set (if any)
					Integer precision = null;
					if (remoteCustomPropertyOptions != null)
					{
						for (RemoteCustomPropertyOption remoteCustomPropertyOption : remoteCustomPropertyOptions)
						{
							if (remoteCustomPropertyOption.getCustomPropertyOptionId().intValue() == SpiraTeamCorePlugin.CustomPropertyOption_Precision
									&& remoteCustomPropertyOption.getValue() != null)
							{
								String rawValue = remoteCustomPropertyOption.getValue().getValue();
								try
								{
									int intValue = Integer.parseInt(rawValue);
									precision = new Integer(intValue);
								}
								catch (NumberFormatException ex)
								{
									// Do nothing as it will leave the precision
									// null
								}
							}
						}
					}

					artifactField.setPrecision(precision);
				}
				if (remoteCustomProperty.getCustomPropertyTypeId().intValue() == SpiraTeamCorePlugin.CustomPropertyType_Date)
				{
					artifactField.setType(Type.DATE);
				}
				if (remoteCustomProperty.getCustomPropertyTypeId().intValue() == SpiraTeamCorePlugin.CustomPropertyType_User)
				{
					artifactField.setType(Type.PERSON);

					// Now we need to get the list of project users
					try
					{
						// Get the list of users from the SOAP API
						// First we need to re-authenticate
						success = soap.connectionAuthenticate2(this.userName, this.password, SPIRA_PLUG_IN_NAME);
						if (!success)
						{
							// throw new SpiraException (this.userName + "/" +
							// this.password);
							throw new SpiraAuthenticationException(Messages.SpiraImportExport_UnableToAuthenticate);
						}

						// Next we need to connect to the appropriate project
						success = soap.connectionConnectToProject(projectId);
						if (!success)
						{
							// throw new SpiraException (this.userName + "/" +
							// this.password);
							throw new SpiraAuthorizationException(NLS.bind(Messages.SpiraImportExport_UnableToConnectToProject, projectId));
						}

						// Get the list of users
						List<RemoteProjectUser> remoteProjectUsers = soap.projectRetrieveUserMembership().getRemoteProjectUser();

						// Now populate the list of custom property option
						// values
						if (!remoteProjectUsers.isEmpty())
						{
							ArtifactFieldValue[] values = new ArtifactFieldValue[remoteProjectUsers.size()];
							int i = 0;
							for (RemoteProjectUser remoteProjectUser : remoteProjectUsers)
							{
								int userId = remoteProjectUser.getUserId().getValue();
								values[i] = new ArtifactFieldValue(userId, remoteProjectUser.getFirstName().getValue() + " "
										+ remoteProjectUser.getLastName().getValue() + " [" + remoteProjectUser.getEmailAddress().getValue() + "]");
								i++;
							}
							artifactField.setValues(values);
						}
					}
					catch (SpiraException ex)
					{
						// Leave the list unpopulated
					}
					catch (WebServiceException ex)
					{
						// Leave the list unpopulated
					}
					catch (IImportExportConnectionConnectToProjectServiceFaultMessageFaultFaultMessage exception)
					{
						// Leave the list unpopulated
					}
					catch (IImportExportProjectRetrieveUserMembershipServiceFaultMessageFaultFaultMessage exception)
					{
						// Leave the list unpopulated
					}
					catch (IImportExportConnectionAuthenticate2ServiceFaultMessageFaultFaultMessage exception)
					{
						// Leave the list unpopulated
					}
				}
				if (remoteCustomProperty.getCustomPropertyTypeId().intValue() == SpiraTeamCorePlugin.CustomPropertyType_List
						|| remoteCustomProperty.getCustomPropertyTypeId().intValue() == SpiraTeamCorePlugin.CustomPropertyType_MultiList)
				{
					if (remoteCustomProperty.getCustomPropertyTypeId().intValue() == SpiraTeamCorePlugin.CustomPropertyType_List)
					{
						artifactField.setType(Type.SINGLE_SELECT);
					}
					else
					{
						artifactField.setType(Type.MULTI_SELECT);
					}

					// Now we need to get the custom list values
					RemoteCustomList remoteCustomList = remoteCustomProperty.getCustomList().getValue();
					if (remoteCustomList != null)
					{
						if (remoteCustomList.getValues().getValue() != null)
						{
							List<RemoteCustomListValue> remoteCustomListValues = remoteCustomList.getValues().getValue().getRemoteCustomListValue();
							if (remoteCustomListValues != null)
							{
								ArtifactFieldValue[] values = new ArtifactFieldValue[remoteCustomListValues.size()];
								int i = 0;
								for (RemoteCustomListValue remoteCustomListValue : remoteCustomListValues)
								{
									values[i] = new ArtifactFieldValue(remoteCustomListValue.getCustomPropertyValueId().getValue(), remoteCustomListValue
											.getName().getValue());
									i++;
								}
								artifactField.setValues(values);
							}
						}
					}
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
		catch (IImportExportCustomPropertyRetrieveForArtifactTypeServiceFaultMessageFaultFaultMessage exception)
		{
			return null;
		}
		catch (IImportExportConnectionConnectToProjectServiceFaultMessageFaultFaultMessage exception)
		{
			return null;
		}
		catch (IImportExportConnectionAuthenticate2ServiceFaultMessageFaultFaultMessage exception)
		{
			return null;
		}
	}

	public ArtifactField incidentGetSeverity(int projectId)
	{
		try
		{
			// Get the list of incident severities from the SOAP API
			// First we need to re-authenticate
			boolean success = soap.connectionAuthenticate2(this.userName, this.password, SPIRA_PLUG_IN_NAME);
			if (!success)
			{
				// throw new SpiraException (this.userName + "/" +
				// this.password);
				throw new SpiraAuthenticationException(Messages.SpiraImportExport_UnableToAuthenticate);
			}

			// Next we need to connect to the appropriate project
			success = soap.connectionConnectToProject(projectId);
			if (!success)
			{
				// throw new SpiraException (this.userName + "/" +
				// this.password);
				throw new SpiraAuthorizationException(NLS.bind(Messages.SpiraImportExport_UnableToConnectToProject, projectId));
			}

			// Get the list of severities
			List<RemoteIncidentSeverity> remoteSeverities = soap.incidentRetrieveSeverities().getRemoteIncidentSeverity();

			// Convert the SOAP release into the ArtifactField class
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
		}
		catch (IImportExportConnectionAuthenticate2ServiceFaultMessageFaultFaultMessage exception)
		{
			return null;
		}
		catch (IImportExportIncidentRetrieveSeveritiesServiceFaultMessageFaultFaultMessage exception)
		{
			return null;
		}
		catch (IImportExportConnectionConnectToProjectServiceFaultMessageFaultFaultMessage exception)
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
	 * 
	 * @return List of incidents
	 * @throws SpiraException
	 */
	public List<Incident> incidentRetrieveAssigned(IProgressMonitor monitor) throws SpiraException
	{
		try
		{
			// First we need to re-authenticate
			boolean success = soap.connectionAuthenticate2(this.userName, this.password, SPIRA_PLUG_IN_NAME);
			if (!success)
			{
				// throw new SpiraException (this.userName + "/" +
				// this.password);
				throw new SpiraAuthenticationException(Messages.SpiraImportExport_UnableToAuthenticate);
			}

			// Call the appropriate method
			List<RemoteIncident> remoteIncidents = soap.incidentRetrieveForOwner().getRemoteIncident();

			// Convert the SOAP incidents into the local versions
			List<Incident> incidents = new ArrayList<Incident>();
			for (RemoteIncident remoteIncident : remoteIncidents)
			{
				Incident incident = new Incident(remoteIncident);
				incidents.add(incident);

				// Add to the stored artifact-key to project mapping
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
		}
		catch (IImportExportConnectionAuthenticate2ServiceFaultMessageFaultFaultMessage ex)
		{
			throw new SpiraException(ex.getMessage());
		}
		catch (IImportExportIncidentRetrieveForOwnerServiceFaultMessageFaultFaultMessage ex)
		{
			throw new SpiraException(ex.getMessage());
		}
	}

	/**
	 * Updates a task object on the server
	 * 
	 * @param task
	 *            The task object
	 * @param newComment
	 *            A new comment to be added
	 */
	public void taskUpdate(Task task, String newComment) throws SpiraException
	{
		try
		{
			// First we need to re-authenticate
			boolean success = soap.connectionAuthenticate2(this.userName, this.password, SPIRA_PLUG_IN_NAME);
			if (!success)
			{
				// throw new SpiraException (this.userName + "/" +
				// this.password);
				throw new SpiraAuthenticationException(Messages.SpiraImportExport_UnableToAuthenticate);
			}

			// Next we need to connect to the appropriate project
			success = soap.connectionConnectToProject(task.getProjectId());
			if (!success)
			{
				// throw new SpiraException (this.userName + "/" +
				// this.password);
				throw new SpiraAuthorizationException(NLS.bind(Messages.SpiraImportExport_UnableToConnectToProject, task.getProjectId()));
			}

			// Convert the local task into the SOAP version
			RemoteTask remoteTask = task.toSoapObject();

			// Call the appropriate method
			soap.taskUpdate(remoteTask);

			// See if we need to add a new comment as well
			if (newComment != null && !newComment.isEmpty())
			{
				// Add the new comment
				Date date = new Date(); // Defaults to now
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
		}
		catch (IImportExportConnectionConnectToProjectServiceFaultMessageFaultFaultMessage exception)
		{
			throw SpiraTeamUtil.convertFaultException(exception);
		}
		catch (IImportExportTaskUpdateServiceFaultMessageFaultFaultMessage exception)
		{
			throw SpiraTeamUtil.convertFaultException(exception);
		}
		catch (IImportExportConnectionAuthenticate2ServiceFaultMessageFaultFaultMessage exception)
		{
			throw SpiraTeamUtil.convertFaultException(exception);
		}
		catch (IImportExportTaskCreateCommentServiceFaultMessageFaultFaultMessage exception)
		{
			throw SpiraTeamUtil.convertFaultException(exception);
		}
		catch (IImportExportTaskUpdateValidationFaultMessageFaultFaultMessage exception)
		{
			// TODO May need to add more intelligent handling of validation
			// messages
			throw SpiraTeamUtil.convertFaultException(exception);
		}
	}

	/**
	 * Updates a requirement object on the server
	 * 
	 * @param requirement
	 *            The requirement object
	 * @param newComment
	 *            A new comment to be added
	 * @throws SpiraException
	 */
	public void requirementUpdate(Requirement requirement, String newComment) throws SpiraException
	{
		try
		{
			// First we need to re-authenticate
			boolean success = soap.connectionAuthenticate2(this.userName, this.password, SPIRA_PLUG_IN_NAME);
			if (!success)
			{
				// throw new SpiraException (this.userName + "/" +
				// this.password);
				throw new SpiraAuthenticationException(Messages.SpiraImportExport_UnableToAuthenticate);
			}

			// Next we need to connect to the appropriate project
			success = soap.connectionConnectToProject(requirement.getProjectId());
			if (!success)
			{
				// throw new SpiraException (this.userName + "/" +
				// this.password);
				throw new SpiraAuthorizationException(NLS.bind(Messages.SpiraImportExport_UnableToConnectToProject, requirement.getProjectId()));
			}

			// Convert the local requirement into the SOAP version
			RemoteRequirement remoteRequirement = requirement.toSoapObject();

			// Call the appropriate method to update the incident
			soap.requirementUpdate(remoteRequirement);

			// See if we need to add a new comment/resolution as well
			if (newComment != null && !newComment.isEmpty())
			{
				// Add the new resolution
				Date date = new Date(); // Defaults to now
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
			throw SpiraTeamUtil.convertFaultException(exception);
		}
		catch (IImportExportConnectionConnectToProjectServiceFaultMessageFaultFaultMessage exception)
		{
			throw SpiraTeamUtil.convertFaultException(exception);
		}
		catch (IImportExportRequirementUpdateServiceFaultMessageFaultFaultMessage exception)
		{
			throw SpiraTeamUtil.convertFaultException(exception);
		}
		catch (IImportExportRequirementCreateCommentServiceFaultMessageFaultFaultMessage exception)
		{
			throw SpiraTeamUtil.convertFaultException(exception);
		}
		catch (IImportExportRequirementUpdateValidationFaultMessageFaultFaultMessage exception)
		{
			// TODO May need to add more intelligent handling of validation
			// messages
			throw SpiraTeamUtil.convertFaultException(exception);
		}
	}

	/**
	 * Updates an incident object on the server
	 * 
	 * @param incident
	 *            The incident object
	 * @param newComment
	 *            A new comment to be added
	 */
	public void incidentUpdate(Incident incident, String newComment) throws SpiraException
	{
		try
		{
			// First we need to re-authenticate
			boolean success = soap.connectionAuthenticate2(this.userName, this.password, SPIRA_PLUG_IN_NAME);
			if (!success)
			{
				// throw new SpiraException (this.userName + "/" +
				// this.password);
				throw new SpiraAuthenticationException(Messages.SpiraImportExport_UnableToAuthenticate);
			}

			// Next we need to connect to the appropriate project
			success = soap.connectionConnectToProject(incident.getProjectId());
			if (!success)
			{
				// throw new SpiraException (this.userName + "/" +
				// this.password);
				throw new SpiraAuthorizationException(NLS.bind(Messages.SpiraImportExport_UnableToConnectToProject, incident.getProjectId()));
			}

			// Convert the local incident into the SOAP version
			RemoteIncident remoteIncident = incident.toSoapObject();

			// Call the appropriate method to update the incident
			soap.incidentUpdate(remoteIncident);

			// See if we need to add a new comment/resolution as well
			if (newComment != null && !newComment.isEmpty())
			{
				// Add the new resolution
				Date date = new Date(); // Defaults to now
				RemoteComment remoteComment = new RemoteComment();
				remoteComment.setCreationDate(SpiraImportExport.CreateJAXBXMLGregorianCalendar("CreationDate", SpiraTeamUtil.convertDatesJava2Xml(date)));
				remoteComment.setArtifactId(incident.getArtifactId());
				remoteComment.setText(CreateJAXBString("Text", newComment));
				ArrayOfRemoteComment remoteComments = new ArrayOfRemoteComment();
				remoteComments.getRemoteComment().add(remoteComment);
				soap.incidentAddComments(remoteComments);
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
		catch (IImportExportIncidentUpdateServiceFaultMessageFaultFaultMessage exception)
		{
			throw SpiraTeamUtil.convertFaultException(exception);
		}
		catch (IImportExportConnectionAuthenticate2ServiceFaultMessageFaultFaultMessage exception)
		{
			throw SpiraTeamUtil.convertFaultException(exception);
		}
		catch (IImportExportConnectionConnectToProjectServiceFaultMessageFaultFaultMessage exception)
		{
			throw SpiraTeamUtil.convertFaultException(exception);
		}
		catch (IImportExportIncidentUpdateValidationFaultMessageFaultFaultMessage exception)
		{
			// TODO Add better validation message handling
			throw SpiraTeamUtil.convertFaultException(exception);
		}
		catch (IImportExportIncidentAddCommentsServiceFaultMessageFaultFaultMessage exception)
		{
			throw SpiraTeamUtil.convertFaultException(exception);
		}
	}

	/**
	 * Gets a single task by its key (RQ prefix + task id)
	 * 
	 * @param artifactKey
	 *            The key for the task (RQ prefix + task id)
	 * @param monitor
	 * @param projectId
	 *            The project id
	 * @return Single task object
	 * @throws SpiraException
	 */
	public Task taskRetrieveByKey(String artifactKey, int projectId, IProgressMonitor monitor) throws SpiraException
	{
		try
		{
			// First make sure that the artifact key is in the correct format
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

			// Next we need to re-authenticate
			boolean success = soap.connectionAuthenticate2(this.userName, this.password, SPIRA_PLUG_IN_NAME);
			if (!success)
			{
				// throw new SpiraException (this.userName + "/" +
				// this.password);
				throw new SpiraAuthenticationException(Messages.SpiraImportExport_UnableToAuthenticate);
			}

			// Next we need to connect to the appropriate project
			success = soap.connectionConnectToProject(projectId);
			if (!success)
			{
				// throw new SpiraException (this.userName + "/" +
				// this.password);
				throw new SpiraAuthorizationException(NLS.bind(Messages.SpiraImportExport_UnableToConnectToProject, projectId));
			}

			// Call the appropriate method
			RemoteTask remoteTask = soap.taskRetrieveById(taskId);

			// Convert the SOAP task into the local version
			Task task = new Task(remoteTask);

			// Now get any associated comments
			List<RemoteComment> remoteComments = soap.taskRetrieveComments(taskId).getRemoteComment();

			// Convert the SOAP resolutions into the local version
			for (RemoteComment remoteComment : remoteComments)
			{
				TaskComment taskComment = new TaskComment(remoteComment);
				task.getComments().add(taskComment);
			}

			// Now get any associated attachments
			RemoteSort remoteSort = new RemoteSort();
			remoteSort.setPropertyName(CreateJAXBString("PropertyName", "UploadDate"));
			remoteSort.setSortAscending(false);
			List<RemoteDocument> remoteDocuments = soap.documentRetrieveForArtifact(ArtifactType.TASK.getArtifactTypeId(), taskId, null, remoteSort)
					.getRemoteDocument();

			// Convert the SOAP attachments into the local version
			for (RemoteDocument remoteDocument : remoteDocuments)
			{
				ArtifactAttachment artifactAttachment = new ArtifactAttachment(remoteDocument);
				task.getAttachments().add(artifactAttachment);
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
		}
		catch (IImportExportConnectionAuthenticate2ServiceFaultMessageFaultFaultMessage ex)
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
		catch (IImportExportDocumentRetrieveForArtifactServiceFaultMessageFaultFaultMessage ex)
		{
			throw new SpiraException(ex.getMessage());
		}
	}

	/**
	 * Gets the list of tasks assigned to the current user
	 * 
	 * @return List of tasks
	 * @throws SpiraException
	 */
	public List<Task> taskRetrieveAssigned(IProgressMonitor monitor) throws SpiraException
	{
		try
		{
			// First we need to re-authenticate
			boolean success = soap.connectionAuthenticate2(this.userName, this.password, SPIRA_PLUG_IN_NAME);
			if (!success)
			{
				// throw new SpiraException (this.userName + "/" +
				// this.password);
				throw new SpiraAuthenticationException(Messages.SpiraImportExport_UnableToAuthenticate);
			}

			// Call the appropriate method
			List<RemoteTask> remoteTasks = soap.taskRetrieveForOwner().getRemoteTask();

			// Convert the SOAP tasks into the local versions
			List<Task> tasks = new ArrayList<Task>();
			for (RemoteTask remoteTask : remoteTasks)
			{
				Task task = new Task(remoteTask);
				tasks.add(task);

				// Add to the stored artifact-key to project mapping
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
		}
		catch (IImportExportTaskRetrieveForOwnerServiceFaultMessageFaultFaultMessage exception)
		{
			throw new SpiraException(exception.getMessage());
		}
		catch (IImportExportConnectionAuthenticate2ServiceFaultMessageFaultFaultMessage exception)
		{
			throw new SpiraException(exception.getMessage());
		}
	}
}
