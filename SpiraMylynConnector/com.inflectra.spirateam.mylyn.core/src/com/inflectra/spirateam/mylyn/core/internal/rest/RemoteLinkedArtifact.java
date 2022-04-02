package com.inflectra.spirateam.mylyn.core.internal.rest;

public class RemoteLinkedArtifact {
    /// <summary>
    /// The ID of the artifact
    /// </summary>
    public int ArtifactId;

    /// <summary>
    /// The ID of the type of the artifact
    /// </summary>
    /// <remarks>
    /// None = 0,
    /// Requirement = 1,
    /// TestCase = 2,
    /// Incident = 3,
    /// Release = 4,
    /// TestRun = 5,
    /// Task = 6,
    /// TestStep = 7,
    /// TestSet = 8,
    /// AutomationHost = 9,
    /// AutomationEngine = 10,
    /// Placeholder = 11,
    /// RequirementStep = 12,
    /// Document = 13
    /// </remarks>
    public int ArtifactTypeId;

    /// <summary>
    /// The name of the artifact
    /// </summary>
    public String Name;

    /// <summary>
    /// The artifact's status name/description
    /// </summary>
    public String Status;
}
