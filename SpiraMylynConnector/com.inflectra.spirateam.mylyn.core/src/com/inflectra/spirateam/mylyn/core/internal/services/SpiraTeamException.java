package com.inflectra.spirateam.mylyn.core.internal.services;

public class SpiraTeamException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3557558253611448192L;

	public SpiraTeamException() {
	}

	public SpiraTeamException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public SpiraTeamException(String message)
	{
		super(message);
	}

	public SpiraTeamException(Throwable cause)
	{
		super(cause);
	}
}
