package com.smartesoft.smarteqm.mylyn.core.internal.services;

public class SpiraDataConcurrencyException extends SpiraException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5581149948959586653L;

	public SpiraDataConcurrencyException()
	{
	}

	public SpiraDataConcurrencyException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public SpiraDataConcurrencyException(String message)
	{
		super(message);
	}

	public SpiraDataConcurrencyException(Throwable cause)
	{
		super(cause);
	}
}
