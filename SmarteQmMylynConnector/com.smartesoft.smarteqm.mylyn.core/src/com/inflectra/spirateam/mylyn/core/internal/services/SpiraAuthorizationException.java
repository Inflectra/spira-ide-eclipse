package com.inflectra.spirateam.mylyn.core.internal.services;

public class SpiraAuthorizationException extends SpiraException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4590763197431308522L;

	public SpiraAuthorizationException()
	{
	}

	public SpiraAuthorizationException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public SpiraAuthorizationException(String message)
	{
		super(message);
	}

	public SpiraAuthorizationException(Throwable cause)
	{
		super(cause);
	}
}
