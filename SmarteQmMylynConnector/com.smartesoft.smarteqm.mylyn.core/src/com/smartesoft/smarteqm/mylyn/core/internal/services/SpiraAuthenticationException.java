/**
 * 
 */
package com.smartesoft.smarteqm.mylyn.core.internal.services;

/**
 * Thrown if the client cannot authenticate with the SpiraTeam server
 * 
 * @author Inflectra Corporation
 */
public class SpiraAuthenticationException extends SpiraException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8048430889003313272L;

	public SpiraAuthenticationException()
	{
	}

	public SpiraAuthenticationException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public SpiraAuthenticationException(String message)
	{
		super(message);
	}

	public SpiraAuthenticationException(Throwable cause)
	{
		super(cause);
	}
}
