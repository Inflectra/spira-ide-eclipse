package com.inflectra.spirateam.mylyn.core.internal;

import org.eclipse.mylyn.tasks.core.data.TaskAttribute;
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
	
	/*
	@Override
	public String getTaskKind()
	{
		String tracTaskKind = super.getTaskKind();
		
		TaskKind taskKind = TaskKind.fromType(tracTaskKind);
		return (taskKind != null) ? taskKind.toString() : tracTaskKind;
		return "Requirement";
	}*/
}
