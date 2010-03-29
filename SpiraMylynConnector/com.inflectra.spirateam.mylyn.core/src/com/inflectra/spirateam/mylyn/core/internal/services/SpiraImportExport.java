/**
 * 
 */
package com.inflectra.spirateam.mylyn.core.internal.services;

import com.inflectra.spirateam.mylyn.core.internal.services.soap.*;

/**
 * This is a facade over the auto-generated proxy class
 * that simplifies the inner-workings of the SOAP/WSDL classes
 * 
 * @author Inflectra Corporation
 */
public class SpiraImportExport
{
	private static final String WEB_SERVICE_SUFFIX = "/Services/v2_2/ImportExport.asmx";
	
	private String serviceUrl = "";
	private String userName = "";
	private String password = "";
	
	/**
	 * The constructor
	 */
	public SpiraImportExport(String baseUrl)
	{
		//Set the web service URL
		this.serviceUrl = baseUrl + WEB_SERVICE_SUFFIX;
	}
	
	/**
	 * The constructor
	 */
	public SpiraImportExport(String baseUrl, String userName, String password)
	{
		//Set the URL, username and password
		this.serviceUrl = baseUrl + WEB_SERVICE_SUFFIX;
		this.userName = userName;
		this.password = password;
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
	
}
