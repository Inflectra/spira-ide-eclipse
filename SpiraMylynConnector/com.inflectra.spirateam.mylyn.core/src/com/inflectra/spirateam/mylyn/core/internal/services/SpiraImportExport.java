/**
 * 
 */
package com.inflectra.spirateam.mylyn.core.internal.services;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.osgi.util.NLS;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
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
import com.inflectra.spirateam.mylyn.core.internal.model.WorkflowField;
import com.inflectra.spirateam.mylyn.core.internal.model.WorkflowTransition;
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
	private Integer storedProjectTemplateId = null;
	private String productName = "";
	private Integer authenticatedUserId = null;
	private int patchNumber = 0;
	private int productVersionPrimary = 0;
	private int productVersionSecondary = 0;
	private int productVersionTertiary = 0;

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
		this.storedProjectId = Integer.valueOf(projectId);
	}
	
	public Integer getStoredProjectTemplateId()
	{
		return this.storedProjectTemplateId;
	}

	public void setStoredProjectTemplateId(int projectTemplateId)
	{
		this.storedProjectTemplateId = Integer.valueOf(projectTemplateId);
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
	public boolean connectionAuthenticate2() throws SpiraConnectionException, SpiraAuthenticationException
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
				success = true;
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
			throw new SpiraConnectionException(Messages.SpiraConnectionException_Message + ": " + ex.getMessage());
		}
	}
	
	/**
	 * Performs an HTTP POST request ot the specified URL
	 *
	 * @param input The URL to perform the query on
	 * @param body  The request body to be sent
	 * @param apiKey The Spira API Key to use
	 * @param login The Spira login to use
	 * @return An InputStream containing the JSON returned from the POST request
	 * @throws IOException
	 * @throws SpiraAuthenticationException
	 */
	public static String httpPost(String input, String login, String apiKey, String body) throws IOException,  SpiraAuthenticationException {
		URL url = new URL(input);

		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		//allow sending a request body
		connection.setDoOutput(true);
		connection.setRequestMethod("POST");
		//have the connection send and retrieve JSON
		connection.setRequestProperty("accept", "application/json; charset=utf-8");
		connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
		//Set the authentication headers
		connection.setRequestProperty("username", login);
		connection.setRequestProperty("api-key", apiKey);
		connection.connect();

		//used to send data in the REST request
		DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
		//write the body to the stream
		outputStream.writeBytes(body);
		//send the OutputStream to the server
		outputStream.flush();
		outputStream.close();

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
				throw new SpiraAuthenticationException("Invalid Spira login and API Key were provided!");
			}
			if (responseCode == 401)
			{
				throw new SpiraAuthenticationException("Invalid Spira login and API Key were provided!");
			}
			throw new IOException("POST request not worked: " + responseCode);
		}
	}

	/**
	 * Performs an HTTP PUT request ot the specified URL
	 *
	 * @param input The URL to perform the query on
	 * @param body  The request body to be sent
	 * @param apiKey The Spira API Key to use
	 * @param login The Spira login to use
	 * @return An InputStream containing the JSON returned from the POST request
	 * @throws IOException
	 * @throws SpiraAuthenticationException
	 */
	public static String httpPut(String input, String login, String apiKey, String body) throws IOException,  SpiraAuthenticationException {
		URL url = new URL(input);

		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		//allow sending a request body
		connection.setDoOutput(true);
		connection.setRequestMethod("PUT");
		//have the connection send and retrieve JSON
		connection.setRequestProperty("accept", "application/json; charset=utf-8");
		connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
		//Set the authentication headers
		connection.setRequestProperty("username", login);
		connection.setRequestProperty("api-key", apiKey);
		connection.connect();

		//used to send data in the REST request
		DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
		//write the body to the stream
		outputStream.writeBytes(body);
		//send the OutputStream to the server
		outputStream.flush();
		outputStream.close();

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
				throw new SpiraAuthenticationException("Invalid Spira login and API Key were provided!");
			}
			if (responseCode == 401)
			{
				throw new SpiraAuthenticationException("Invalid Spira login and API Key were provided!");
			}
			throw new IOException("PUT request not worked: " + responseCode);
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
	public static String httpGet(String input, String login, String apiKey) throws IOException, SpiraAuthenticationException {
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
				throw new SpiraAuthenticationException("Invalid Spira login and API Key were provided!");
			}
			if (responseCode == 401)
			{
				throw new SpiraAuthenticationException("Invalid Spira login and API Key were provided!");
			}
			throw new IOException("GET request not worked: " + responseCode);
		}
	}
	
	/**
	 * Gets the project template id of a project
	 * @param projectId - the id of the project
	 * @return - the id of the project template
	 * @throws SpiraException
	 */
	public int getTemplateIdForProject(int projectId)
	{
		try
		{
			// Call the appropriate method
			String url = this.fullUrl + "/projects/{project_id}";
			url = url.replace("{project_id}", String.valueOf(projectId));
			String json = httpGet(url, this.userName, this.apiKey);
			
			//Parse the returned data
			Gson gson = new Gson();
			RemoteProject remoteProject = gson.fromJson(json, RemoteProject.class);
			if (remoteProject != null)
			{
				return remoteProject.ProjectTemplateId;
			}
			return -1;
		}
		catch (IOException ex)
		{
			return -1;
		}
		catch (SpiraException ex)
		{
			return -1;
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

			// Call the appropriate method
			String url = this.fullUrl + "/projects/{project_id}/documents/{document_id}";
			url = url.replace("{project_id}", String.valueOf(projectId));
			url = url.replace("{document_id}", String.valueOf(attachmentId));
			String json = httpGet(url, this.userName, this.apiKey);

			//Parse the returned data
			Gson gson = new Gson();
			RemoteDocument remoteDocument = gson.fromJson(json, RemoteDocument.class);

			// Convert the remote document into the local version
			ArtifactAttachment artifactAttachment = new ArtifactAttachment(remoteDocument);

			return artifactAttachment;
		}
		catch (IOException ex)
		{
			throw new SpiraException(ex.getMessage());
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

			// Call the appropriate method
			String url = this.fullUrl + "/projects/{project_id}/documents/{document_id}/open";
			url = url.replace("{project_id}", String.valueOf(projectId));
			url = url.replace("{document_id}", String.valueOf(attachmentId));
			String json = httpGet(url, this.userName, this.apiKey);

			//Parse the returned data
			byte[] attachmentData = Base64.getDecoder().decode(json);

			return attachmentData;
		}
		catch (IOException ex)
		{
			throw new SpiraException(ex.getMessage());
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

			// Call the appropriate method
			RemoteDocumentFile remoteDocumentFile = artifactAttachment.toSoapObject();
			RemoteLinkedArtifact linkedArtifact = new RemoteLinkedArtifact();
			linkedArtifact.ArtifactId = artifactId;
			linkedArtifact.ArtifactTypeId = artifactTypeId;
			remoteDocumentFile.AttachedArtifacts = new ArrayList<RemoteLinkedArtifact>();
			remoteDocumentFile.AttachedArtifacts.add(linkedArtifact);
			remoteDocumentFile.BinaryData = attachmentData;
			
			String addFileUrl = this.fullUrl + "/projects/{project_id}/documents/file";
			addFileUrl = addFileUrl.replace("{project_id}", String.valueOf(projectId));
			Gson gson = new Gson();
			String json = gson.toJson(remoteDocumentFile);
			json = httpPost(addFileUrl, this.userName, this.apiKey, json);
			RemoteDocument remoteDocument = gson.fromJson(json, RemoteDocument.class);

			// See if we have to add a new comment
			if (comment != null && !comment.isEmpty())
			{
				// See what type of artifact we have and handle appropriately
				if (artifactType.equals(ArtifactType.REQUIREMENT))
				{
					// Add the new comment
					RemoteComment remoteComment = new RemoteComment();
					remoteComment.CreationDate = SpiraTeamUtil.convertDatesToUtc(artifactAttachment.getCreationDate());
					remoteComment.ArtifactId = artifactId;
					remoteComment.Text = comment;
					
					String postCommentUrl = this.fullUrl + "/projects/{project_id}/requirements/{requirement_id}/comments";
					postCommentUrl = postCommentUrl.replace("{project_id}", String.valueOf(projectId));
					postCommentUrl = postCommentUrl.replace("{requirement_id}", String.valueOf(artifactId));
					json = gson.toJson(remoteComment);
					json = httpPost(postCommentUrl, this.userName, this.apiKey, json);
				}
				else if (artifactType.equals(ArtifactType.INCIDENT))
				{
					// Add the new comment
					RemoteComment remoteComment = new RemoteComment();
					remoteComment.CreationDate = SpiraTeamUtil.convertDatesToUtc(artifactAttachment.getCreationDate());
					remoteComment.ArtifactId = artifactId;
					remoteComment.Text = comment;
					ArrayList<RemoteComment> remoteComments = new ArrayList<RemoteComment>();
					remoteComments.add(remoteComment);
					
					String postCommentUrl = this.fullUrl + "/projects/{project_id}/incidents/{incident_id}/comments";
					postCommentUrl = postCommentUrl.replace("{project_id}", String.valueOf(projectId));
					postCommentUrl = postCommentUrl.replace("{incident_id}", String.valueOf(artifactId));
					json = gson.toJson(remoteComments);
					json = httpPost(postCommentUrl, this.userName, this.apiKey, json);
				}
				else if (artifactType.equals(ArtifactType.TASK))
				{
					// Add the new comment
					RemoteComment remoteComment = new RemoteComment();
					remoteComment.CreationDate = SpiraTeamUtil.convertDatesToUtc(artifactAttachment.getCreationDate());
					remoteComment.ArtifactId = artifactId;
					remoteComment.Text = comment;

					String postCommentUrl = this.fullUrl + "/projects/{project_id}/tasks/{task_id}/comments";
					postCommentUrl = postCommentUrl.replace("{project_id}", String.valueOf(projectId));
					postCommentUrl = postCommentUrl.replace("{task_id}", String.valueOf(artifactId));
					json = gson.toJson(remoteComment);
					json = httpPost(postCommentUrl, this.userName, this.apiKey, json);
				}
			}

			return new ArtifactAttachment(remoteDocument);
		}
		catch (IOException ex)
		{
			throw new SpiraException(ex.getMessage());
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

			// Call the appropriate method
			String url = this.fullUrl + "/projects/{project_id}/requirements/{requirement_id}";
			url = url.replace("{project_id}", String.valueOf(projectId));
			url = url.replace("{requirement_id}", String.valueOf(requirementId));
			String json = httpGet(url, this.userName, this.apiKey);

			//Parse the returned data
			Gson gson = new Gson();
			RemoteRequirement remoteRequirement = gson.fromJson(json, RemoteRequirement.class);

			// Convert the remote requirement into the local version
			Requirement requirement = new Requirement(remoteRequirement);

			// Now get any associated comments
			url = this.fullUrl + "/projects/{project_id}/requirements/{requirement_id}/comments";
			url = url.replace("{project_id}", String.valueOf(projectId));
			url = url.replace("{requirement_id}", String.valueOf(requirementId));
			json = httpGet(url, this.userName, this.apiKey);

			//Parse the returned data
			ArrayList<RemoteComment> remoteComments;
			java.lang.reflect.Type remoteCommentArrayList = new TypeToken<ArrayList<RemoteComment>>(){}.getType();
			remoteComments = gson.fromJson(json, remoteCommentArrayList);

			// Convert the remote comments into the local version
			for (RemoteComment remoteComment : remoteComments)
			{
				RequirementComment requirementComment = new RequirementComment(remoteComment);
				requirement.getComments().add(requirementComment);
			}

			// Now get any associated attachments
			url = this.fullUrl + "/projects/{project_id}/artifact-types/{artifact_type_id}/artifacts/{artifact_id}/documents";
			url = url.replace("{project_id}", String.valueOf(projectId));
			url = url.replace("{artifact_type_id}", String.valueOf(ArtifactType.REQUIREMENT.getArtifactTypeId()));
			url = url.replace("{artifact_id}", String.valueOf(requirementId));
			json = httpGet(url, this.userName, this.apiKey);

			//Parse the returned data
			ArrayList<RemoteDocument> remoteDocuments;
			java.lang.reflect.Type remoteDocumentArrayList = new TypeToken<ArrayList<RemoteDocument>>(){}.getType();
			remoteDocuments = gson.fromJson(json, remoteDocumentArrayList);

			// Convert the remote attachments into the local version
			for (RemoteDocument remoteDocument : remoteDocuments)
			{
				ArtifactAttachment artifactAttachment = new ArtifactAttachment(remoteDocument);
				requirement.getAttachments().add(artifactAttachment);
			}

			return requirement;
		}
		catch (IOException ex)
		{
			throw new SpiraException(ex.getMessage());
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
			// Call the appropriate method
			String url = this.fullUrl + "/requirements";
			String json = httpGet(url, this.userName, this.apiKey);
			
			//Parse the returned data
			Gson gson = new Gson();
			ArrayList<RemoteRequirement> remoteRequirements;
			java.lang.reflect.Type remoteRequirementsArrayList = new TypeToken<ArrayList<RemoteRequirement>>(){}.getType();
			remoteRequirements = gson.fromJson(json, remoteRequirementsArrayList);

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
		catch (IOException ex)
		{
			throw new SpiraException(ex.getMessage());
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

			// Call the appropriate method
			String url = this.fullUrl + "/projects/{project_id}/incidents/{incident_id}";
			url = url.replace("{project_id}", String.valueOf(projectId));
			url = url.replace("{incident_id}", String.valueOf(incidentId));
			String json = httpGet(url, this.userName, this.apiKey);

			//Parse the returned data
			Gson gson = new Gson();
			RemoteIncident remoteIncident = gson.fromJson(json, RemoteIncident.class);
			
			// Convert the remote incident into the local version
			Incident incident = new Incident(remoteIncident);

			// Now get the comments
			url = this.fullUrl + "/projects/{project_id}/incidents/{incident_id}/comments";
			url = url.replace("{project_id}", String.valueOf(projectId));
			url = url.replace("{incident_id}", String.valueOf(incidentId));
			json = httpGet(url, this.userName, this.apiKey);

			//Parse the returned data
			ArrayList<RemoteComment> remoteComments;
			java.lang.reflect.Type remoteCommentArrayList = new TypeToken<ArrayList<RemoteComment>>(){}.getType();
			remoteComments = gson.fromJson(json, remoteCommentArrayList);

			// Convert the SOAP comments into the local version
			for (RemoteComment remoteComment : remoteComments)
			{
				IncidentResolution incidentResolution = new IncidentResolution(remoteComment);
				incident.getResolutions().add(incidentResolution);
			}

			// Now get any associated attachments
			url = this.fullUrl + "/projects/{project_id}/artifact-types/{artifact_type_id}/artifacts/{artifact_id}/documents";
			url = url.replace("{project_id}", String.valueOf(projectId));
			url = url.replace("{artifact_type_id}", String.valueOf(ArtifactType.INCIDENT.getArtifactTypeId()));
			url = url.replace("{artifact_id}", String.valueOf(incidentId));
			json = httpGet(url, this.userName, this.apiKey);

			//Parse the returned data
			ArrayList<RemoteDocument> remoteDocuments;
			java.lang.reflect.Type remoteDocumentArrayList = new TypeToken<ArrayList<RemoteDocument>>(){}.getType();
			remoteDocuments = gson.fromJson(json, remoteDocumentArrayList);

			// Convert the remote attachments into the local version
			for (RemoteDocument remoteDocument : remoteDocuments)
			{
				ArtifactAttachment artifactAttachment = new ArtifactAttachment(remoteDocument);
				incident.getAttachments().add(artifactAttachment);
			}

			return incident;
		}
		catch (IOException ex)
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
			// Get the list of users from the API
			String url = this.fullUrl + "/projects/{project_id}/users";
			url = url.replace("{project_id}", String.valueOf(projectId));
			String json = httpGet(url, this.userName, this.apiKey);
			
			//Parse the returned data
			Gson gson = new Gson();
			ArrayList<RemoteProjectUser> remoteProjectUsers;
			java.lang.reflect.Type remoteProjectUsersType = new TypeToken<ArrayList<RemoteProjectUser>>(){}.getType();
			remoteProjectUsers = gson.fromJson(json, remoteProjectUsersType);
			
			// Convert the remote project user into the ArtifactField class
			ArtifactField artifactField = new ArtifactField("User");
			ArrayList<ArtifactFieldValue> lookupValues = new ArrayList<ArtifactFieldValue>();
			for (RemoteProjectUser remoteProjectUser : remoteProjectUsers)
			{
				int userId = remoteProjectUser.UserId.intValue();
				lookupValues.add(new ArtifactFieldValue(userId, remoteProjectUser.FirstName + " " + remoteProjectUser.LastName
						+ " [" + remoteProjectUser.EmailAddress + "]"));
			}
			artifactField.setValues(lookupValues.toArray(new ArtifactFieldValue[0]));
			return artifactField;
		}
		catch (SpiraException ex)
		{
			return null;
		}
		catch (IOException ex)
		{
			return null;
		}
	}

	public ArtifactField releasesGet(boolean activeOnly, int projectId)
	{
		try
		{
			// Get the list of releases
			String url = this.fullUrl + "/projects/{project_id}/releases";
			url = url.replace("{project_id}", String.valueOf(projectId));
			String json = httpGet(url, this.userName, this.apiKey);
			
			//Parse the returned data
			Gson gson = new Gson();
			ArrayList<RemoteRelease> remoteReleases;
			java.lang.reflect.Type remoteReleasesType = new TypeToken<ArrayList<RemoteRelease>>(){}.getType();
			remoteReleases = gson.fromJson(json, remoteReleasesType);
			
			// Convert the SOAP release into the ArtifactField class
			ArtifactField artifactField = new ArtifactField("Release");
			ArrayList<ArtifactFieldValue> lookupValues = new ArrayList<ArtifactFieldValue>();
			for (RemoteRelease remoteRelease : remoteReleases)
			{
				// Indent with spaces. Also need to make releases look slightly
				// different to sprint/phase
				String indentDisplay = remoteRelease.IndentLevel.replaceAll("[A-Z]", " ");
				if (remoteRelease.ReleaseTypeId == 1 || remoteRelease.ReleaseTypeId == 2)
				{
					lookupValues.add(new ArtifactFieldValue(remoteRelease.ReleaseId, indentDisplay
							+ remoteRelease.VersionNumber + " - " + remoteRelease.Name + "*"));
				}
				else
				{
					lookupValues.add(new ArtifactFieldValue(remoteRelease.ReleaseId, indentDisplay
							+ remoteRelease.VersionNumber + " - " + remoteRelease.Name));
				}
			}
			artifactField.setValues(lookupValues.toArray(new ArtifactFieldValue[0]));
			return artifactField;
		}
		catch (SpiraException ex)
		{
			return null;
		}
		catch (IOException ex)
		{
			return null;
		}
	}

	public List<WorkflowTransition> incidentRetrieveWorkflowTransitions(int currentTypeId, int currentStatusId, boolean isDetector, boolean isOwner)
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

	public List<WorkflowTransition> incidentRetrieveWorkflowTransitions(int projectId, int currentTypeId, int currentStatusId, boolean isDetector,
			boolean isOwner) throws SpiraException
	{
		try
		{
			// Get the list of workflow transitions
			String url = this.fullUrl + "/projects/{project_id}/incidents/types/{incident_type_id}/workflow/transitions?status_id={incident_status_id}&is_detector={is_detector}&isOwner={is_owner}";
			url = url.replace("{project_id}", String.valueOf(projectId));
			url = url.replace("{incident_type_id}", String.valueOf(currentTypeId));
			url = url.replace("{incident_status_id}", String.valueOf(currentStatusId));
			url = url.replace("{is_detector}", String.valueOf(isDetector));
			url = url.replace("{is_owner}", String.valueOf(isOwner));
			String json = httpGet(url, this.userName, this.apiKey);
			
			//Parse the returned data
			Gson gson = new Gson();
			ArrayList<RemoteWorkflowTransition> remoteTransitions;
			java.lang.reflect.Type remoteTransitionsType = new TypeToken<ArrayList<RemoteWorkflowTransition>>(){}.getType();
			remoteTransitions = gson.fromJson(json, remoteTransitionsType);
			
			// Convert the remote transitions into local versions
			ArrayList<WorkflowTransition> transitions = new ArrayList<WorkflowTransition>();
			for (RemoteWorkflowTransition remoteTransition : remoteTransitions)
			{
				transitions.add(new WorkflowTransition(remoteTransition));
			}
			return transitions;
		}
		catch (IOException ex)
		{
			throw new SpiraException(ex.getMessage());
		}
	}

	public List<WorkflowField> incidentRetrieveWorkflowFields(int currentIncidentTypeId, int currentIncidentStatusId) throws SpiraException
	{
		// Don't return fields if we have no project set
		if (this.storedProjectTemplateId == null)
		{
			return null;
		}
		int projectTemplateId = this.storedProjectTemplateId.intValue();
		return this.incidentRetrieveWorkflowFields(projectTemplateId, currentIncidentTypeId, currentIncidentStatusId);
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
	public List<WorkflowField> incidentRetrieveWorkflowFields(int projectTemplateId, int currentIncidentTypeId, int currentIncidentStatusId)
			throws SpiraException
	{
		try
		{
			// Get the list of workflow field states
			// (inactive/required/hidden)
			String url = this.fullUrl + "/project-templates/{project_template_id}/incidents/types/{incident_type_id}/workflow/fields?status_id={incident_status_id}";
			url = url.replace("{project_template_id}", String.valueOf(projectTemplateId));
			url = url.replace("{incident_type_id}", String.valueOf(currentIncidentTypeId));
			url = url.replace("{incident_status_id}", String.valueOf(currentIncidentStatusId));
			String json = httpGet(url, this.userName, this.apiKey);
			
			//Parse the returned data
			Gson gson = new Gson();
			ArrayList<RemoteWorkflowField> remoteWorkflowFields;
			java.lang.reflect.Type remoteWorkflowFieldsType = new TypeToken<ArrayList<RemoteWorkflowField>>(){}.getType();
			remoteWorkflowFields = gson.fromJson(json, remoteWorkflowFieldsType);

			// Convert the SOAP workflow fields into local versions
			ArrayList<WorkflowField> fields = new ArrayList<WorkflowField>();
			for (RemoteWorkflowField remoteField : remoteWorkflowFields)
			{
				fields.add(new WorkflowField(remoteField));
			}

			// Get the list of workflow-controlled custom-properties
			// (inactive/required/hidden)
			url = this.fullUrl + "/project-templates/{project_template_id}/incidents/types/{incident_type_id}/workflow/custom-properties?status_id={incident_status_id}";
			url = url.replace("{project_template_id}", String.valueOf(projectTemplateId));
			url = url.replace("{incident_type_id}", String.valueOf(currentIncidentTypeId));
			url = url.replace("{incident_status_id}", String.valueOf(currentIncidentStatusId));
			json = httpGet(url, this.userName, this.apiKey);
			
			//Parse the returned data
			ArrayList<RemoteWorkflowCustomProperty> remoteWorkflowCustomProperties;
			java.lang.reflect.Type remoteWorkflowCustomPropertiesType = new TypeToken<ArrayList<RemoteWorkflowCustomProperty>>(){}.getType();
			remoteWorkflowCustomProperties = gson.fromJson(json, remoteWorkflowCustomPropertiesType);

			for (RemoteWorkflowCustomProperty remoteWorkflowCustomProperty : remoteWorkflowCustomProperties)
			{
				fields.add(new WorkflowField(remoteWorkflowCustomProperty));
			}

			return fields;
		}
		catch (IOException ex)
		{
			throw new SpiraException(ex.getMessage());
		}
	}

	public ArtifactField incidentGetStatus()
	{
		// Don't return statuses if we have no project set
		if (this.storedProjectTemplateId == null)
		{
			return null;
		}
		int projectTemplateId = this.storedProjectTemplateId.intValue();
		return this.incidentGetStatus(projectTemplateId);
	}

	public ArtifactField incidentGetStatus(int projectTemplateId)
	{
		try
		{
			// Get the list of statuses
			String url = this.fullUrl + "/project-templates/{project_template_id}/incidents/statuses";
			url = url.replace("{project_template_id}", String.valueOf(projectTemplateId));
			String json = httpGet(url, this.userName, this.apiKey);
			
			//Parse the returned data
			Gson gson = new Gson();
			ArrayList<RemoteIncidentStatus> remoteStatuses;
			java.lang.reflect.Type remoteStatusesType = new TypeToken<ArrayList<RemoteIncidentStatus>>(){}.getType();
			remoteStatuses = gson.fromJson(json, remoteStatusesType);

			// Convert the remote release into the ArtifactField class
			ArtifactField artifactField = new ArtifactField("IncidentStatus");
			ArrayList<ArtifactFieldValue> lookupValues = new ArrayList<ArtifactFieldValue>();
			for (RemoteIncidentStatus remoteStatus : remoteStatuses)
			{
				lookupValues.add(new ArtifactFieldValue(remoteStatus.IncidentStatusId, remoteStatus.Name));
			}
			artifactField.setValues(lookupValues.toArray(new ArtifactFieldValue[0]));
			return artifactField;
		}
		catch (SpiraException ex)
		{
			return null;
		}
		catch (IOException ex)
		{
			return null;
		}
	}

	public ArtifactField incidentGetType()
	{
		// Don't return releases if we have no project set
		if (this.storedProjectTemplateId == null)
		{
			return null;
		}
		int projectTemplateId = this.storedProjectTemplateId.intValue();
		return this.incidentGetType(projectTemplateId);
	}

	public ArtifactField incidentGetType(int projectTemplateId)
	{
		try
		{
			// Get the list of types
			String url = this.fullUrl + "/project-templates/{project_template_id}/incidents/types";
			url = url.replace("{project_template_id}", String.valueOf(projectTemplateId));
			String json = httpGet(url, this.userName, this.apiKey);
			
			//Parse the returned data
			Gson gson = new Gson();
			ArrayList<RemoteIncidentType> remoteTypes;
			java.lang.reflect.Type remoteTypesType = new TypeToken<ArrayList<RemoteIncidentType>>(){}.getType();
			remoteTypes = gson.fromJson(json, remoteTypesType);

			// Convert the SOAP release into the ArtifactField class
			ArtifactField artifactField = new ArtifactField("IncidentType");
			ArrayList<ArtifactFieldValue> lookupValues = new ArrayList<ArtifactFieldValue>();
			for (RemoteIncidentType remoteType : remoteTypes)
			{
				lookupValues.add(new ArtifactFieldValue(remoteType.IncidentTypeId, remoteType.Name));
			}
			artifactField.setValues(lookupValues.toArray(new ArtifactFieldValue[0]));
			return artifactField;
		}
		catch (SpiraException ex)
		{
			return null;
		}
		catch (IOException ex)
		{
			return null;
		}
	}

	public ArtifactField incidentGetPriority()
	{
		// Don't return releases if we have no project set
		if (this.storedProjectTemplateId == null)
		{
			return null;
		}
		int projectTemplateId = this.storedProjectTemplateId.intValue();
		return this.incidentGetPriority(projectTemplateId);
	}

	public ArtifactField incidentGetPriority(int projectTemplateId)
	{
		try
		{
			// Get the list of priorities
			String url = this.fullUrl + "/project-templates/{project_template_id}/incidents/priorities";
			url = url.replace("{project_template_id}", String.valueOf(projectTemplateId));
			String json = httpGet(url, this.userName, this.apiKey);
			
			//Parse the returned data
			Gson gson = new Gson();
			ArrayList<RemoteIncidentPriority> remotePriorities;
			java.lang.reflect.Type remoteTypesType = new TypeToken<ArrayList<RemoteIncidentPriority>>(){}.getType();
			remotePriorities = gson.fromJson(json, remoteTypesType);

			// Convert the SOAP release into the ArtifactField class
			ArtifactField artifactField = new ArtifactField("IncidentPriority");
			ArrayList<ArtifactFieldValue> lookupValues = new ArrayList<ArtifactFieldValue>();
			for (RemoteIncidentPriority remotePriority : remotePriorities)
			{
				lookupValues.add(new ArtifactFieldValue(remotePriority.PriorityId, remotePriority.Name));
			}
			artifactField.setValues(lookupValues.toArray(new ArtifactFieldValue[0]));
			return artifactField;
		}
		catch (SpiraException ex)
		{
			return null;
		}
		catch (IOException ex)
		{
			return null;
		}
	}

	public ArtifactField incidentGetSeverity()
	{
		// Don't return releases if we have no project set
		if (this.storedProjectTemplateId == null)
		{
			return null;
		}
		int projectTemplateId = this.storedProjectTemplateId.intValue();
		return this.incidentGetSeverity(projectTemplateId);
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
	 * @param projectTemplateId
	 *            Project template id (optional, uses stored ID if set to null)
	 */
	public ArtifactField[] getCustomProperties(ArtifactType artifactType, Integer projectTemplateId, Integer projectId)
	{
		try
		{
			// Get the stored project id if not set
			if (projectId == null)
			{
				projectId = this.getStoredProjectId();
			}
			
			// Get the stored project template id if not set
			if (projectTemplateId == null)
			{
				projectTemplateId = this.getStoredProjectTemplateId();
			}

			// Get the list of custom property definitions
			String url = this.fullUrl + "/project-templates/{project_template_id}/custom-properties/{artifact_type_name}";
			url = url.replace("{project_template_id}", String.valueOf(projectTemplateId));
			url = url.replace("{artifact_type_name}", artifactType.getDisplayName());		
			String json = httpGet(url, this.userName, this.apiKey);
			
			//Parse the returned data
			Gson gson = new Gson();
			ArrayList<RemoteCustomProperty> remoteCustomProperties;
			java.lang.reflect.Type remoteCustomPropertiesType = new TypeToken<ArrayList<RemoteCustomProperty>>(){}.getType();
			remoteCustomProperties = gson.fromJson(json, remoteCustomPropertiesType);

			// Convert the SOAP custom properties into the ArtifactField class
			ArrayList<ArtifactField> artifactFields = new ArrayList<ArtifactField>();
			for (RemoteCustomProperty remoteCustomProperty : remoteCustomProperties)
			{
				ArtifactField artifactField = new ArtifactField(remoteCustomProperty.CustomPropertyFieldName);
				artifactField.setLabel(remoteCustomProperty.Name);
				artifactField.setCustom(true);

				// Get the list of options
				List<RemoteCustomPropertyOption> remoteCustomPropertyOptions = null;
				if (remoteCustomProperty.Options != null && !remoteCustomProperty.Options.isEmpty())
				{
					remoteCustomPropertyOptions = remoteCustomProperty.Options;
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
						if (remoteCustomPropertyOption.CustomPropertyOptionId == SpiraTeamCorePlugin.CustomPropertyOption_AllowEmpty
								&& remoteCustomPropertyOption.Value != null)
						{
							allowEmpty = (remoteCustomPropertyOption.Value.equals("Y"));
						}

						// Check for default-value option
						if (remoteCustomPropertyOption.CustomPropertyOptionId == SpiraTeamCorePlugin.CustomPropertyOption_Default
								&& remoteCustomPropertyOption.Value != null)
						{
							defaultValue = remoteCustomPropertyOption.Value;
						}
					}
				}
				artifactField.setOptional(allowEmpty);
				artifactField.setDefaultValue(defaultValue);

				if (remoteCustomProperty.CustomPropertyTypeId == SpiraTeamCorePlugin.CustomPropertyType_Text)
				{
					// See if we have a rich-text or plain text custom property
					boolean isRichText = false;
					if (remoteCustomPropertyOptions != null)
					{
						for (RemoteCustomPropertyOption remoteCustomPropertyOption : remoteCustomPropertyOptions)
						{
							if (remoteCustomPropertyOption.CustomPropertyOptionId == SpiraTeamCorePlugin.CustomPropertyOption_RichText
									&& remoteCustomPropertyOption.Value != null)
							{
								isRichText = (remoteCustomPropertyOption.Value.equals("Y"));
							}
						}
					}
					artifactField.setType((isRichText) ? Type.RICH_TEXT : Type.TEXT);
				}
				if (remoteCustomProperty.CustomPropertyTypeId == SpiraTeamCorePlugin.CustomPropertyType_Integer)
				{
					artifactField.setType(Type.INTEGER);
				}
				if (remoteCustomProperty.CustomPropertyTypeId == SpiraTeamCorePlugin.CustomPropertyType_Boolean)
				{
					artifactField.setType(Type.CHECKBOX);
				}
				if (remoteCustomProperty.CustomPropertyTypeId == SpiraTeamCorePlugin.CustomPropertyType_Decimal)
				{
					artifactField.setType(Type.DOUBLE);

					// Determine what precision is set (if any)
					Integer precision = null;
					if (remoteCustomPropertyOptions != null)
					{
						for (RemoteCustomPropertyOption remoteCustomPropertyOption : remoteCustomPropertyOptions)
						{
							if (remoteCustomPropertyOption.CustomPropertyOptionId == SpiraTeamCorePlugin.CustomPropertyOption_Precision
									&& remoteCustomPropertyOption.Value != null)
							{
								String rawValue = remoteCustomPropertyOption.Value;
								try
								{
									int intValue = Integer.parseInt(rawValue);
									precision = Integer.valueOf(intValue);
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
				if (remoteCustomProperty.CustomPropertyTypeId == SpiraTeamCorePlugin.CustomPropertyType_Date)
				{
					artifactField.setType(Type.DATE);
				}
				if (remoteCustomProperty.CustomPropertyTypeId == SpiraTeamCorePlugin.CustomPropertyType_User)
				{
					artifactField.setType(Type.PERSON);

					// Now we need to get the list of project users
					try
					{
						// Get the list of users from the REST API
						url = this.fullUrl + "/projects/{project_id}/users";
						url = url.replace("{project_id}", String.valueOf(projectId));
						json = httpGet(url, this.userName, this.apiKey);
						
						//Parse the returned data
						ArrayList<RemoteProjectUser> remoteProjectUsers;
						java.lang.reflect.Type remoteProjectUsersType = new TypeToken<ArrayList<RemoteProjectUser>>(){}.getType();
						remoteProjectUsers = gson.fromJson(json, remoteProjectUsersType);

						// Now populate the list of custom property option
						// values
						if (!remoteProjectUsers.isEmpty())
						{
							ArtifactFieldValue[] values = new ArtifactFieldValue[remoteProjectUsers.size()];
							int i = 0;
							for (RemoteProjectUser remoteProjectUser : remoteProjectUsers)
							{
								int userId = remoteProjectUser.UserId;
								values[i] = new ArtifactFieldValue(userId, remoteProjectUser.FirstName + " "
										+ remoteProjectUser.LastName + " [" + remoteProjectUser.EmailAddress + "]");
								i++;
							}
							artifactField.setValues(values);
						}
					}
					catch (SpiraException ex)
					{
						// Leave the list unpopulated
					}
					catch (IOException ex)
					{
						// Leave the list unpopulated
					}
				}
				if (remoteCustomProperty.CustomPropertyTypeId == SpiraTeamCorePlugin.CustomPropertyType_List
						|| remoteCustomProperty.CustomPropertyTypeId == SpiraTeamCorePlugin.CustomPropertyType_MultiList)
				{
					if (remoteCustomProperty.CustomPropertyTypeId == SpiraTeamCorePlugin.CustomPropertyType_List)
					{
						artifactField.setType(Type.SINGLE_SELECT);
					}
					else
					{
						artifactField.setType(Type.MULTI_SELECT);
					}

					// Now we need to get the custom list values
					RemoteCustomList remoteCustomList = remoteCustomProperty.CustomList;
					if (remoteCustomList != null)
					{
						if (remoteCustomList.Values != null)
						{
							List<RemoteCustomListValue> remoteCustomListValues = remoteCustomList.Values;
							if (remoteCustomListValues != null)
							{
								ArtifactFieldValue[] values = new ArtifactFieldValue[remoteCustomListValues.size()];
								int i = 0;
								for (RemoteCustomListValue remoteCustomListValue : remoteCustomListValues)
								{
									values[i] = new ArtifactFieldValue(remoteCustomListValue.CustomPropertyValueId, remoteCustomListValue.Name);
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
		catch (IOException ex)
		{
			return null;
		}
	}

	public ArtifactField incidentGetSeverity(int projectTemplateId)
	{
		try
		{
			// Get the list of severities
			String url = this.fullUrl + "/project-templates/{project_template_id}/incidents/severities";
			url = url.replace("{project_template_id}", String.valueOf(projectTemplateId));
			String json = httpGet(url, this.userName, this.apiKey);
			
			//Parse the returned data
			Gson gson = new Gson();
			ArrayList<RemoteIncidentSeverity> remoteSeverities;
			java.lang.reflect.Type remoteSeveritiesType = new TypeToken<ArrayList<RemoteIncidentSeverity>>(){}.getType();
			remoteSeverities = gson.fromJson(json, remoteSeveritiesType);

			// Convert the SOAP release into the ArtifactField class
			ArtifactField artifactField = new ArtifactField("IncidentSeverity");
			ArrayList<ArtifactFieldValue> lookupValues = new ArrayList<ArtifactFieldValue>();
			for (RemoteIncidentSeverity remoteSeverity : remoteSeverities)
			{
				lookupValues.add(new ArtifactFieldValue(remoteSeverity.SeverityId, remoteSeverity.Name));
			}
			artifactField.setValues(lookupValues.toArray(new ArtifactFieldValue[0]));
			return artifactField;
		}
		catch (SpiraException ex)
		{
			return null;
		}
		catch (IOException ex)
		{
			return null;
		}
	}
	
	public ArtifactField componentGet()
	{
		// Don't return components if we have no project set
		if (this.storedProjectId == null)
		{
			return null;
		}
		int projectId = this.storedProjectId.intValue();
		return this.componentGet(projectId);
	}

	public ArtifactField componentGet(int projectId)
	{
		try
		{
			// Get the list of components
			String url = this.fullUrl + "/projects/{project_id}/components?active_only=true&include_deleted=false";
			url = url.replace("{project_id}", String.valueOf(projectId));
			String json = httpGet(url, this.userName, this.apiKey);
			
			//Parse the returned data
			Gson gson = new Gson();
			ArrayList<RemoteComponent> remoteComponents;
			java.lang.reflect.Type remoteComponentsType = new TypeToken<ArrayList<RemoteComponent>>(){}.getType();
			remoteComponents = gson.fromJson(json, remoteComponentsType);

			// Convert the remote release into the ArtifactField class
			ArtifactField artifactField = new ArtifactField("Component");
			ArrayList<ArtifactFieldValue> lookupValues = new ArrayList<ArtifactFieldValue>();
			for (RemoteComponent remoteComponent : remoteComponents)
			{
				lookupValues.add(new ArtifactFieldValue(remoteComponent.ComponentId, remoteComponent.Name));
			}
			artifactField.setValues(lookupValues.toArray(new ArtifactFieldValue[0]));
			return artifactField;
		}
		catch (SpiraException ex)
		{
			return null;
		}
		catch (IOException ex)
		{
			return null;
		}
	}

	public ArtifactField taskGetStatus()
	{
		// Don't return statuses if we have no project set
		if (this.storedProjectTemplateId == null)
		{
			return null;
		}
		int projectTemplateId = this.storedProjectTemplateId.intValue();
		return this.taskGetStatus(projectTemplateId);
	}

	public ArtifactField taskGetStatus(int projectTemplateId)
	{
		try
		{
			// Get the list of statuses
			String url = this.fullUrl + "/project-templates/{project_template_id}/tasks/statuses";
			url = url.replace("{project_template_id}", String.valueOf(projectTemplateId));
			String json = httpGet(url, this.userName, this.apiKey);
			
			//Parse the returned data
			Gson gson = new Gson();
			ArrayList<RemoteTaskStatus> remoteStatuses;
			java.lang.reflect.Type remoteStatusesType = new TypeToken<ArrayList<RemoteTaskStatus>>(){}.getType();
			remoteStatuses = gson.fromJson(json, remoteStatusesType);

			// Convert the remote release into the ArtifactField class
			ArtifactField artifactField = new ArtifactField("TaskStatus");
			ArrayList<ArtifactFieldValue> lookupValues = new ArrayList<ArtifactFieldValue>();
			for (RemoteTaskStatus remoteStatus : remoteStatuses)
			{
				lookupValues.add(new ArtifactFieldValue(remoteStatus.TaskStatusId, remoteStatus.Name));
			}
			artifactField.setValues(lookupValues.toArray(new ArtifactFieldValue[0]));
			return artifactField;
		}
		catch (SpiraException ex)
		{
			return null;
		}
		catch (IOException ex)
		{
			return null;
		}
	}

	public ArtifactField taskGetPriority()
	{
		// Don't return releases if we have no project set
		if (this.storedProjectTemplateId == null)
		{
			return null;
		}
		int projectTemplateId = this.storedProjectTemplateId.intValue();
		return this.taskGetPriority(projectTemplateId);
	}

	public ArtifactField taskGetPriority(int projectTemplateId)
	{
		try
		{
			// Get the list of priorities
			String url = this.fullUrl + "/project-templates/{project_template_id}/tasks/priorities";
			url = url.replace("{project_template_id}", String.valueOf(projectTemplateId));
			String json = httpGet(url, this.userName, this.apiKey);
			
			//Parse the returned data
			Gson gson = new Gson();
			ArrayList<RemoteTaskPriority> remotePriorities;
			java.lang.reflect.Type remoteTypesType = new TypeToken<ArrayList<RemoteTaskPriority>>(){}.getType();
			remotePriorities = gson.fromJson(json, remoteTypesType);

			// Convert the SOAP release into the ArtifactField class
			ArtifactField artifactField = new ArtifactField("TaskPriority");
			ArrayList<ArtifactFieldValue> lookupValues = new ArrayList<ArtifactFieldValue>();
			for (RemoteTaskPriority remotePriority : remotePriorities)
			{
				lookupValues.add(new ArtifactFieldValue(remotePriority.PriorityId, remotePriority.Name));
			}
			artifactField.setValues(lookupValues.toArray(new ArtifactFieldValue[0]));
			return artifactField;
		}
		catch (SpiraException ex)
		{
			return null;
		}
		catch (IOException ex)
		{
			return null;
		}
	}

	public ArtifactField taskGetType()
	{
		// Don't return types if we have no project set
		if (this.storedProjectTemplateId == null)
		{
			return null;
		}
		int projectTemplateId = this.storedProjectTemplateId.intValue();
		return this.taskGetType(projectTemplateId);

	}

	public ArtifactField taskGetType(int projectTemplateId)
	{
		try
		{
			// Get the list of statuses
			String url = this.fullUrl + "/project-templates/{project_template_id}/tasks/types";
			url = url.replace("{project_template_id}", String.valueOf(projectTemplateId));
			String json = httpGet(url, this.userName, this.apiKey);
			
			//Parse the returned data
			Gson gson = new Gson();
			ArrayList<RemoteTaskType> remoteTypees;
			java.lang.reflect.Type remoteTypeesType = new TypeToken<ArrayList<RemoteTaskType>>(){}.getType();
			remoteTypees = gson.fromJson(json, remoteTypeesType);

			// Convert the remote release into the ArtifactField class
			ArtifactField artifactField = new ArtifactField("TaskType");
			ArrayList<ArtifactFieldValue> lookupValues = new ArrayList<ArtifactFieldValue>();
			for (RemoteTaskType remoteType : remoteTypees)
			{
				lookupValues.add(new ArtifactFieldValue(remoteType.TaskTypeId, remoteType.Name));
			}
			artifactField.setValues(lookupValues.toArray(new ArtifactFieldValue[0]));
			return artifactField;
		}
		catch (SpiraException ex)
		{
			return null;
		}
		catch (IOException ex)
		{
			return null;
		}
	}
	
	public List<WorkflowTransition> taskRetrieveWorkflowTransitions(int currentTypeId, int currentStatusId, boolean isDetector, boolean isOwner)
			throws SpiraException
	{
		// Don't return releases if we have no project set
		if (this.storedProjectId == null)
		{
			return null;
		}
		int projectId = this.storedProjectId.intValue();
		return this.taskRetrieveWorkflowTransitions(projectId, currentTypeId, currentStatusId, isDetector, isOwner);
	}

	public List<WorkflowTransition> taskRetrieveWorkflowTransitions(int projectId, int currentTypeId, int currentStatusId, boolean isCreator,
			boolean isOwner) throws SpiraException
	{
		try
		{
			// Get the list of workflow transitions
			String url = this.fullUrl + "/projects/{project_id}/tasks/types/{task_type_id}/workflow/transitions?status_id={task_status_id}&is_creator={is_creator}&isOwner={is_owner}";
			url = url.replace("{project_id}", String.valueOf(projectId));
			url = url.replace("{task_type_id}", String.valueOf(currentTypeId));
			url = url.replace("{task_status_id}", String.valueOf(currentStatusId));
			url = url.replace("{is_creator}", String.valueOf(isCreator));
			url = url.replace("{is_owner}", String.valueOf(isOwner));
			String json = httpGet(url, this.userName, this.apiKey);
			
			//Parse the returned data
			Gson gson = new Gson();
			ArrayList<RemoteWorkflowTransition> remoteTransitions;
			java.lang.reflect.Type remoteTransitionsType = new TypeToken<ArrayList<RemoteWorkflowTransition>>(){}.getType();
			remoteTransitions = gson.fromJson(json, remoteTransitionsType);
			
			// Convert the remote transitions into local versions
			ArrayList<WorkflowTransition> transitions = new ArrayList<WorkflowTransition>();
			for (RemoteWorkflowTransition remoteTransition : remoteTransitions)
			{
				transitions.add(new WorkflowTransition(remoteTransition));
			}
			return transitions;
		}
		catch (IOException ex)
		{
			throw new SpiraException(ex.getMessage());
		}
	}

	public List<WorkflowField> taskRetrieveWorkflowFields(int currentTaskTypeId, int currentTaskStatusId) throws SpiraException
	{
		// Don't return fields if we have no project set
		if (this.storedProjectTemplateId == null)
		{
			return null;
		}
		int projectTemplateId = this.storedProjectTemplateId.intValue();
		return this.taskRetrieveWorkflowFields(projectTemplateId, currentTaskTypeId, currentTaskStatusId);
	}

	/**
	 * Gets the list of task workflow fields and custom properties for the
	 * current task
	 * 
	 * @param projectId
	 * @param currentTaskTypeId
	 * @param currentTaskStatusId
	 * @return
	 * @throws SpiraException
	 */
	public List<WorkflowField> taskRetrieveWorkflowFields(int projectTemplateId, int currentTaskTypeId, int currentTaskStatusId)
			throws SpiraException
	{
		try
		{
			// Get the list of workflow field states
			// (inactive/required/hidden)
			String url = this.fullUrl + "/project-templates/{project_template_id}/tasks/types/{task_type_id}/workflow/fields?status_id={task_status_id}";
			url = url.replace("{project_template_id}", String.valueOf(projectTemplateId));
			url = url.replace("{task_type_id}", String.valueOf(currentTaskTypeId));
			url = url.replace("{task_status_id}", String.valueOf(currentTaskStatusId));
			String json = httpGet(url, this.userName, this.apiKey);
			
			//Parse the returned data
			Gson gson = new Gson();
			ArrayList<RemoteWorkflowField> remoteWorkflowFields;
			java.lang.reflect.Type remoteWorkflowFieldsType = new TypeToken<ArrayList<RemoteWorkflowField>>(){}.getType();
			remoteWorkflowFields = gson.fromJson(json, remoteWorkflowFieldsType);

			// Convert the SOAP workflow fields into local versions
			ArrayList<WorkflowField> fields = new ArrayList<WorkflowField>();
			for (RemoteWorkflowField remoteField : remoteWorkflowFields)
			{
				fields.add(new WorkflowField(remoteField));
			}

			// Get the list of workflow-controlled custom-properties
			// (inactive/required/hidden)
			url = this.fullUrl + "/project-templates/{project_template_id}/tasks/types/{task_type_id}/workflow/custom-properties?status_id={task_status_id}";
			url = url.replace("{project_template_id}", String.valueOf(projectTemplateId));
			url = url.replace("{task_type_id}", String.valueOf(currentTaskTypeId));
			url = url.replace("{task_status_id}", String.valueOf(currentTaskStatusId));
			json = httpGet(url, this.userName, this.apiKey);
			
			//Parse the returned data
			ArrayList<RemoteWorkflowCustomProperty> remoteWorkflowCustomProperties;
			java.lang.reflect.Type remoteWorkflowCustomPropertiesType = new TypeToken<ArrayList<RemoteWorkflowCustomProperty>>(){}.getType();
			remoteWorkflowCustomProperties = gson.fromJson(json, remoteWorkflowCustomPropertiesType);

			for (RemoteWorkflowCustomProperty remoteWorkflowCustomProperty : remoteWorkflowCustomProperties)
			{
				fields.add(new WorkflowField(remoteWorkflowCustomProperty));
			}

			return fields;
		}
		catch (IOException ex)
		{
			throw new SpiraException(ex.getMessage());
		}
	}
	
	public List<WorkflowTransition> requirementRetrieveWorkflowTransitions(int currentTypeId, int currentStatusId, boolean isDetector, boolean isOwner)
			throws SpiraException
	{
		// Don't return releases if we have no project set
		if (this.storedProjectId == null)
		{
			return null;
		}
		int projectId = this.storedProjectId.intValue();
		return this.requirementRetrieveWorkflowTransitions(projectId, currentTypeId, currentStatusId, isDetector, isOwner);
	}

	public List<WorkflowTransition> requirementRetrieveWorkflowTransitions(int projectId, int currentTypeId, int currentStatusId, boolean isCreator,
			boolean isOwner) throws SpiraException
	{
		try
		{
			// Get the list of workflow transitions
			String url = this.fullUrl + "/projects/{project_id}/requirements/types/{requirement_type_id}/workflow/transitions?status_id={requirement_status_id}&is_creator={is_creator}&isOwner={is_owner}";
			url = url.replace("{project_id}", String.valueOf(projectId));
			url = url.replace("{requirement_type_id}", String.valueOf(currentTypeId));
			url = url.replace("{requirement_status_id}", String.valueOf(currentStatusId));
			url = url.replace("{is_creator}", String.valueOf(isCreator));
			url = url.replace("{is_owner}", String.valueOf(isOwner));
			String json = httpGet(url, this.userName, this.apiKey);
			
			//Parse the returned data
			Gson gson = new Gson();
			ArrayList<RemoteWorkflowTransition> remoteTransitions;
			java.lang.reflect.Type remoteTransitionsType = new TypeToken<ArrayList<RemoteWorkflowTransition>>(){}.getType();
			remoteTransitions = gson.fromJson(json, remoteTransitionsType);
			
			// Convert the remote transitions into local versions
			ArrayList<WorkflowTransition> transitions = new ArrayList<WorkflowTransition>();
			for (RemoteWorkflowTransition remoteTransition : remoteTransitions)
			{
				transitions.add(new WorkflowTransition(remoteTransition));
			}
			return transitions;
		}
		catch (IOException ex)
		{
			throw new SpiraException(ex.getMessage());
		}
	}

	public List<WorkflowField> requirementRetrieveWorkflowFields(int currentRequirementTypeId, int currentRequirementStatusId) throws SpiraException
	{
		// Don't return fields if we have no project set
		if (this.storedProjectTemplateId == null)
		{
			return null;
		}
		int projectTemplateId = this.storedProjectTemplateId.intValue();
		return this.requirementRetrieveWorkflowFields(projectTemplateId, currentRequirementTypeId, currentRequirementStatusId);
	}

	/**
	 * Gets the list of requirement workflow fields and custom properties for the
	 * current requirement
	 * 
	 * @param projectId
	 * @param currentRequirementTypeId
	 * @param currentRequirementStatusId
	 * @return
	 * @throws SpiraException
	 */
	public List<WorkflowField> requirementRetrieveWorkflowFields(int projectTemplateId, int currentRequirementTypeId, int currentRequirementStatusId)
			throws SpiraException
	{
		try
		{
			// Get the list of workflow field states
			// (inactive/required/hidden)
			String url = this.fullUrl + "/project-templates/{project_template_id}/requirements/types/{requirement_type_id}/workflow/fields?status_id={requirement_status_id}";
			url = url.replace("{project_template_id}", String.valueOf(projectTemplateId));
			url = url.replace("{requirement_type_id}", String.valueOf(currentRequirementTypeId));
			url = url.replace("{requirement_status_id}", String.valueOf(currentRequirementStatusId));
			String json = httpGet(url, this.userName, this.apiKey);
			
			//Parse the returned data
			Gson gson = new Gson();
			ArrayList<RemoteWorkflowField> remoteWorkflowFields;
			java.lang.reflect.Type remoteWorkflowFieldsType = new TypeToken<ArrayList<RemoteWorkflowField>>(){}.getType();
			remoteWorkflowFields = gson.fromJson(json, remoteWorkflowFieldsType);

			// Convert the SOAP workflow fields into local versions
			ArrayList<WorkflowField> fields = new ArrayList<WorkflowField>();
			for (RemoteWorkflowField remoteField : remoteWorkflowFields)
			{
				fields.add(new WorkflowField(remoteField));
			}

			// Get the list of workflow-controlled custom-properties
			// (inactive/required/hidden)
			url = this.fullUrl + "/project-templates/{project_template_id}/requirements/types/{requirement_type_id}/workflow/custom-properties?status_id={requirement_status_id}";
			url = url.replace("{project_template_id}", String.valueOf(projectTemplateId));
			url = url.replace("{requirement_type_id}", String.valueOf(currentRequirementTypeId));
			url = url.replace("{requirement_status_id}", String.valueOf(currentRequirementStatusId));
			json = httpGet(url, this.userName, this.apiKey);
			
			//Parse the returned data
			ArrayList<RemoteWorkflowCustomProperty> remoteWorkflowCustomProperties;
			java.lang.reflect.Type remoteWorkflowCustomPropertiesType = new TypeToken<ArrayList<RemoteWorkflowCustomProperty>>(){}.getType();
			remoteWorkflowCustomProperties = gson.fromJson(json, remoteWorkflowCustomPropertiesType);

			for (RemoteWorkflowCustomProperty remoteWorkflowCustomProperty : remoteWorkflowCustomProperties)
			{
				fields.add(new WorkflowField(remoteWorkflowCustomProperty));
			}

			return fields;
		}
		catch (IOException ex)
		{
			throw new SpiraException(ex.getMessage());
		}
	}

	public ArtifactField requirementGetStatus()
	{
		// Don't return statuses if we have no project set
		if (this.storedProjectTemplateId == null)
		{
			return null;
		}
		int projectTemplateId = this.storedProjectTemplateId.intValue();
		return this.requirementGetStatus(projectTemplateId);
	}

	public ArtifactField requirementGetStatus(int projectTemplateId)
	{
		try
		{
			// Get the list of statuses
			String url = this.fullUrl + "/project-templates/{project_template_id}/requirements/statuses";
			url = url.replace("{project_template_id}", String.valueOf(projectTemplateId));
			String json = httpGet(url, this.userName, this.apiKey);
			
			//Parse the returned data
			Gson gson = new Gson();
			ArrayList<RemoteRequirementStatus> remoteStatuses;
			java.lang.reflect.Type remoteStatusesType = new TypeToken<ArrayList<RemoteRequirementStatus>>(){}.getType();
			remoteStatuses = gson.fromJson(json, remoteStatusesType);

			// Convert the remote release into the ArtifactField class
			ArtifactField artifactField = new ArtifactField("RequirementStatus");
			ArrayList<ArtifactFieldValue> lookupValues = new ArrayList<ArtifactFieldValue>();
			for (RemoteRequirementStatus remoteStatus : remoteStatuses)
			{
				lookupValues.add(new ArtifactFieldValue(remoteStatus.RequirementStatusId, remoteStatus.Name));
			}
			artifactField.setValues(lookupValues.toArray(new ArtifactFieldValue[0]));
			return artifactField;
		}
		catch (SpiraException ex)
		{
			return null;
		}
		catch (IOException ex)
		{
			return null;
		}
	}

	public ArtifactField requirementGetImportance()
	{
		// Don't return statuses if we have no project set
		if (this.storedProjectTemplateId == null)
		{
			return null;
		}
		int projectTemplateId = this.storedProjectTemplateId.intValue();
		return this.requirementGetImportance(projectTemplateId);
	}

	public ArtifactField requirementGetImportance(int projectTemplateId)
	{
		try
		{
			// Get the list of statuses
			String url = this.fullUrl + "/project-templates/{project_template_id}/requirements/importances";
			url = url.replace("{project_template_id}", String.valueOf(projectTemplateId));
			String json = httpGet(url, this.userName, this.apiKey);
			
			//Parse the returned data
			Gson gson = new Gson();
			ArrayList<RemoteRequirementImportance> remoteImportancees;
			java.lang.reflect.Type remoteImportanceesType = new TypeToken<ArrayList<RemoteRequirementImportance>>(){}.getType();
			remoteImportancees = gson.fromJson(json, remoteImportanceesType);

			// Convert the remote release into the ArtifactField class
			ArtifactField artifactField = new ArtifactField("RequirementImportance");
			ArrayList<ArtifactFieldValue> lookupValues = new ArrayList<ArtifactFieldValue>();
			for (RemoteRequirementImportance remoteImportance : remoteImportancees)
			{
				lookupValues.add(new ArtifactFieldValue(remoteImportance.ImportanceId, remoteImportance.Name));
			}
			artifactField.setValues(lookupValues.toArray(new ArtifactFieldValue[0]));
			return artifactField;
		}
		catch (SpiraException ex)
		{
			return null;
		}
		catch (IOException ex)
		{
			return null;
		}
	}

	public ArtifactField requirementGetType()
	{
		// Don't return statuses if we have no project set
		if (this.storedProjectTemplateId == null)
		{
			return null;
		}
		int projectTemplateId = this.storedProjectTemplateId.intValue();
		return this.requirementGetType(projectTemplateId);
	}

	public ArtifactField requirementGetType(int projectTemplateId)
	{
		try
		{
			// Get the list of statuses
			String url = this.fullUrl + "/project-templates/{project_template_id}/requirements/types";
			url = url.replace("{project_template_id}", String.valueOf(projectTemplateId));
			String json = httpGet(url, this.userName, this.apiKey);
			
			//Parse the returned data
			Gson gson = new Gson();
			ArrayList<RemoteRequirementType> remoteTypees;
			java.lang.reflect.Type remoteTypeesType = new TypeToken<ArrayList<RemoteRequirementType>>(){}.getType();
			remoteTypees = gson.fromJson(json, remoteTypeesType);

			// Convert the remote release into the ArtifactField class
			ArtifactField artifactField = new ArtifactField("RequirementType");
			ArrayList<ArtifactFieldValue> lookupValues = new ArrayList<ArtifactFieldValue>();
			for (RemoteRequirementType remoteType : remoteTypees)
			{
				lookupValues.add(new ArtifactFieldValue(remoteType.RequirementTypeId, remoteType.Name));
			}
			artifactField.setValues(lookupValues.toArray(new ArtifactFieldValue[0]));
			return artifactField;
		}
		catch (SpiraException ex)
		{
			return null;
		}
		catch (IOException ex)
		{
			return null;
		}
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
			// Call the appropriate method
			String url = this.fullUrl + "/incidents";
			String json = httpGet(url, this.userName, this.apiKey);
			
			//Parse the returned data
			Gson gson = new Gson();
			ArrayList<RemoteIncident> remoteIncidents;
			java.lang.reflect.Type remoteIncidentsType = new TypeToken<ArrayList<RemoteIncident>>(){}.getType();
			remoteIncidents = gson.fromJson(json, remoteIncidentsType);

			// Convert the remote incidents into the local versions
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
		catch (IOException ex)
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
			// Convert the local task into the SOAP version
			RemoteTask remoteTask = task.toSoapObject();

			// Call the appropriate method
			String url = this.fullUrl + "/projects/{project_id}/tasks";
			url = url.replace("{project_id}", String.valueOf(task.getProjectId()));
			Gson gson = new Gson();
			String json = gson.toJson(remoteTask);
			json = httpPut(url, this.userName, this.apiKey, json);

			// See if we need to add a new comment as well
			if (newComment != null && !newComment.isEmpty())
			{
				// Add the new comment
				Date date = new Date(); // Defaults to now
				RemoteComment remoteComment = new RemoteComment();
				remoteComment.CreationDate = SpiraTeamUtil.convertDatesToUtc(date);
				remoteComment.ArtifactId = task.getArtifactId();
				remoteComment.Text = newComment;

				String postCommentUrl = this.fullUrl + "/projects/{project_id}/tasks/{task_id}/comments";
				postCommentUrl = postCommentUrl.replace("{project_id}", String.valueOf(task.getProjectId()));
				postCommentUrl = postCommentUrl.replace("{task_id}", String.valueOf(task.getArtifactId()));
				json = gson.toJson(remoteComment);
				json = httpPost(postCommentUrl, this.userName, this.apiKey, json);
			}
		}
		catch (IOException ex)
		{
			// TODO May need to add more intelligent handling of validation
			// messages
			//throw SpiraTeamUtil.convertFaultException(ex);
			//throw SpiraTeamUtil.convertSoapFaults(ex);
			throw new SpiraException(ex.getMessage());
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
			// Convert the local requirement into the SOAP version
			RemoteRequirement remoteRequirement = requirement.toSoapObject();

			// Call the appropriate method to update the incident
			String url = this.fullUrl + "/projects/{project_id}/requirements";
			url = url.replace("{project_id}", String.valueOf(requirement.getProjectId()));
			Gson gson = new Gson();
			String json = gson.toJson(remoteRequirement);
			json = httpPut(url, this.userName, this.apiKey, json);

			// See if we need to add a new comment/resolution as well
			if (newComment != null && !newComment.isEmpty())
			{
				// Add the new resolution
				Date date = new Date(); // Defaults to now
				RemoteComment remoteComment = new RemoteComment();
				remoteComment.CreationDate = SpiraTeamUtil.convertDatesToUtc(date);
				remoteComment.ArtifactId = requirement.getArtifactId();
				remoteComment.Text = newComment;

				String postCommentUrl = this.fullUrl + "/projects/{project_id}/requirements/{requirement_id}/comments";
				postCommentUrl = postCommentUrl.replace("{project_id}", String.valueOf(requirement.getProjectId()));
				postCommentUrl = postCommentUrl.replace("{requirement_id}", String.valueOf(requirement.getArtifactId()));
				json = gson.toJson(remoteComment);
				json = httpPost(postCommentUrl, this.userName, this.apiKey, json);
			}
		}
		catch (IOException ex)
		{
			// TODO May need to add more intelligent handling of validation
			// messages
			//throw SpiraTeamUtil.convertFaultException(ex);
			//throw SpiraTeamUtil.convertSoapFaults(ex);
			throw new SpiraException(ex.getMessage());
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
			// Convert the local incident into the SOAP version
			RemoteIncident remoteIncident = incident.toSoapObject();

			// Call the appropriate method to update the incident
			String url = this.fullUrl + "/projects/{project_id}/incidents/{incident_id}";
			url = url.replace("{project_id}", String.valueOf(incident.getProjectId()));
			url = url.replace("{incident_id}", String.valueOf(incident.getArtifactId()));
			Gson gson = new Gson();
			String json = gson.toJson(remoteIncident);
			json = httpPut(url, this.userName, this.apiKey, json);
			
			// See if we need to add a new comment/resolution as well
			if (newComment != null && !newComment.isEmpty())
			{
				// Add the new resolution
				Date date = new Date(); // Defaults to now
				RemoteComment remoteComment = new RemoteComment();
				remoteComment.CreationDate = SpiraTeamUtil.convertDatesToUtc(date);
				remoteComment.ArtifactId = incident.getArtifactId();
				remoteComment.Text = newComment;
				ArrayList<RemoteComment> remoteComments = new ArrayList<RemoteComment>();
				remoteComments.add(remoteComment);

				String postCommentUrl = this.fullUrl + "/projects/{project_id}/incidents/{incident_id}/comments";
				postCommentUrl = postCommentUrl.replace("{project_id}", String.valueOf(incident.getProjectId()));
				postCommentUrl = postCommentUrl.replace("{incident_id}", String.valueOf(incident.getArtifactId()));
				json = gson.toJson(remoteComments);
				json = httpPost(postCommentUrl, this.userName, this.apiKey, json);
			}
		}
		catch (IOException ex)
		{
			// TODO May need to add more intelligent handling of validation
			// messages
			//throw SpiraTeamUtil.convertFaultException(ex);
			//throw SpiraTeamUtil.convertSoapFaults(ex);
			throw new SpiraException(ex.getMessage());
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

			// Call the appropriate method
			String url = this.fullUrl + "/projects/{project_id}/tasks/{task_id}";
			url = url.replace("{project_id}", String.valueOf(projectId));
			url = url.replace("{task_id}", String.valueOf(taskId));
			String json = httpGet(url, this.userName, this.apiKey);

			//Parse the returned data
			Gson gson = new Gson();
			RemoteTask remoteTask = gson.fromJson(json, RemoteTask.class);

			// Convert the SOAP task into the local version
			Task task = new Task(remoteTask);

			// Now get any associated comments
			url = this.fullUrl + "/projects/{project_id}/tasks/{task_id}/comments";
			url = url.replace("{project_id}", String.valueOf(projectId));
			url = url.replace("{task_id}", String.valueOf(taskId));
			json = httpGet(url, this.userName, this.apiKey);

			//Parse the returned data
			ArrayList<RemoteComment> remoteComments;
			java.lang.reflect.Type remoteCommentArrayList = new TypeToken<ArrayList<RemoteComment>>(){}.getType();
			remoteComments = gson.fromJson(json, remoteCommentArrayList);

			// Convert the SOAP resolutions into the local version
			for (RemoteComment remoteComment : remoteComments)
			{
				TaskComment taskComment = new TaskComment(remoteComment);
				task.getComments().add(taskComment);
			}

			// Now get any associated attachments
			url = this.fullUrl + "/projects/{project_id}/artifact-types/{artifact_type_id}/artifacts/{artifact_id}/documents";
			url = url.replace("{project_id}", String.valueOf(projectId));
			url = url.replace("{artifact_type_id}", String.valueOf(ArtifactType.TASK.getArtifactTypeId()));
			url = url.replace("{artifact_id}", String.valueOf(taskId));
			json = httpGet(url, this.userName, this.apiKey);

			//Parse the returned data
			ArrayList<RemoteDocument> remoteDocuments;
			java.lang.reflect.Type remoteDocumentArrayList = new TypeToken<ArrayList<RemoteDocument>>(){}.getType();
			remoteDocuments = gson.fromJson(json, remoteDocumentArrayList);

			// Convert the SOAP attachments into the local version
			for (RemoteDocument remoteDocument : remoteDocuments)
			{
				ArtifactAttachment artifactAttachment = new ArtifactAttachment(remoteDocument);
				task.getAttachments().add(artifactAttachment);
			}

			return task;
		}
		catch (IOException ex)
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
			// Call the appropriate method
			String url = this.fullUrl + "/tasks";
			String json = httpGet(url, this.userName, this.apiKey);
			
			//Parse the returned data
			Gson gson = new Gson();
			ArrayList<RemoteTask> remoteTasks;
			java.lang.reflect.Type remoteTasksType = new TypeToken<ArrayList<RemoteTask>>(){}.getType();
			remoteTasks = gson.fromJson(json, remoteTasksType);

			
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
		catch (IOException ex)
		{
			throw new SpiraException(ex.getMessage());
		}
	}
}
