package com.smartesoft.smarteqm.mylyn.core.internal.services;

public class SpiraException extends Exception
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3752116260070471584L;

	public SpiraException()
	{
	}

	public SpiraException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public SpiraException(String message)
	{
		super(message);
	}

	public SpiraException(Throwable cause)
	{
		super(cause);
	}
}
