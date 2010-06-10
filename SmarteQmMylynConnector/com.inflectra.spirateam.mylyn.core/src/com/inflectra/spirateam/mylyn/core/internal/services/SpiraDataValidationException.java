package com.inflectra.spirateam.mylyn.core.internal.services;

public class SpiraDataValidationException extends SpiraException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5581149948959586653L;

	public SpiraDataValidationException()
	{
	}

	public SpiraDataValidationException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public SpiraDataValidationException(String message)
	{
		super(message);
	}

	public SpiraDataValidationException(Throwable cause)
	{
		super(cause);
	}
}
