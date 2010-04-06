package com.inflectra.spirateam.mylyn.core.internal;

import org.eclipse.mylyn.tasks.core.data.TaskData;
import org.eclipse.mylyn.tasks.core.data.TaskMapper;

import com.inflectra.spirateam.mylyn.core.internal.services.SpiraImportExport;

public class SpiraTeamTaskMapper extends TaskMapper
{
	private final SpiraImportExport client;

	public SpiraTeamTaskMapper(TaskData taskData, SpiraImportExport client)
	{
		super(taskData);
		this.client = client;
	}
}
