/**
 * 
 */
package com.inflectra.spirateam.mylyn.core.internal.services;

import javax.xml.namespace.QName;
import javax.xml.ws.*;
import java.net.MalformedURLException;
import java.net.URL;

import com.inflectra.spirateam.mylyn.core.internal.SpiraTeamClientData;
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
	
	private URL serviceUrl = null;
	private String userName = "";
	private String password = "";
	private ImportExport service = null;
	private ImportExportSoap soap = null;

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
		}
		catch (WebServiceException ex)
		{
			throw new SpiraConnectionException(Messages.SpiraConnectionException_Message);
		}
	}

	/**
	 * The user name
	 */
	public String getUserName()
	{
		return this.userName;
	}

	/**
	 * The password
	 */
	public String getPassword()
	{
		return this.password;
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
}
