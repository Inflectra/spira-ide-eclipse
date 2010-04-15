package com.inflectra.spirateam.mylyn.core.internal;

import java.io.Serializable;
import java.util.HashMap;

public class SpiraTeamClientData implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8098054046577177195L;

	public long lastUpdate;
	public HashMap<String, Integer> taskToProjectMapping;
}
