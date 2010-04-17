package com.inflectra.spirateam.mylyn.core.internal;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS
{
	private static final String BUNDLE_NAME = "com.inflectra.spirateam.mylyn.core.internal.messages"; //$NON-NLS-1$

	static
	{
		// load message values from bundle file
		reloadMessages();
	}

	public static void reloadMessages()
	{
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
	
	public static String ArtifactType_Requirement;
	public static String ArtifactType_Incident;
	public static String ArtifactType_Task;
	
	public static String SpiraTeamCorePlugin_SpiraTeam_description;
	public static String SpiraTeamCorePlugin_UnexpectedError;

	public static String SpiraTeam_PredefinedFilter_MyRequirements;
	public static String SpiraTeam_PredefinedFilter_MyIncidents;
	public static String SpiraTeam_PredefinedFilter_MyTasks;
	public static String SpiraTeamRepositoryConnector_Query_Repository;
	public static String SpiraTeamRepositoryConnector_The_SpiraTeam_query_is_invalid;
	public static String SpiraTeamRepositoryConnector_ServerVersionTooOld;
	
	public static String SpiraTeamClientManager_CacheNotReadable;
	public static String SpiraTeamClientManager_CacheNotWritable;
	public static String SpiraTeamClientManager_UnableToAuthenticate;
	public static String SpiraTeamClientManager_MissingCredentials;
	
	public static String SpiraTeamTaskDataHandler_UnableToRetrieveComments;
	public static String SpiraTeamTaskDataHandler_UnableToRetrieveWorkflowTransitions;
	public static String SpiraTeamTaskDataHandler_ArtifactNoLongerExists;
	
	public static String ArtifactAttribute_ProjectId;
	public static String ArtifactAttribute_ArtifactKey;
	public static String ArtifactAttribute_Name;
	public static String ArtifactAttribute_Description;
	public static String ArtifactAttribute_CreationDate;
	public static String ArtifactAttribute_LastUpdateDate;
	public static String ArtifactAttribute_OwnerId;
	public static String ArtifactAttribute_Url;
	
	public static String RequirementAttribute_Type;
	public static String RequirementAttribute_StatusId;
	public static String RequirementAttribute_AuthorId;
	public static String RequirementAttribute_ImportanceId;
	public static String RequirementAttribute_ReleaseId;
	public static String Requirementttribute_PlannedEffort;
	
	public static String TaskAttribute_Type;
	public static String TaskAttribute_StatusId;
	public static String TaskAttribute_RequirementId;
	public static String TaskAttribute_ReleaseId;
	public static String TaskAttribute_PriorityId;
	public static String TaskAttribute_StartDate;
	public static String TaskAttribute_EndDate;
	public static String TaskAttribute_CompletionPercentage;
	public static String TaskAttribute_EstimatedEffort;
	public static String TaskAttribute_ActualEffort;
	
	public static String IncidentAttribute_PriorityId;
	public static String IncidentAttribute_SeverityId;
	public static String IncidentAttribute_StatusId;
	public static String IncidentAttribute_TypeId;
	public static String IncidentAttribute_DetectedReleaseId;
	public static String IncidentAttribute_ResolvedReleaseId;
	public static String IncidentAttribute_VerifiedReleaseId;
	public static String IncidentAttribute_StartDate;
	public static String IncidentAttribute_ClosedDate;
	public static String IncidentAttribute_CompletionPercentage;
	public static String IncidentAttribute_EstimatedEffort;
	public static String IncidentAttribute_ActualEffort;
	public static String IncidentAttribute_OpenerId;
	public static String SpiraTeamRepositoryConnector_Getting_changed_tasks;
}
