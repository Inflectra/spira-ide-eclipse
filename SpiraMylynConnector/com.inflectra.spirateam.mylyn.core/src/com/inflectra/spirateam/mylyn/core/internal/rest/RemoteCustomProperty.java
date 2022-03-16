package com.inflectra.spirateam.mylyn.core.internal.rest;

import java.util.ArrayList;

public class RemoteCustomProperty {
    /// <summary>
    /// The id of the custom property (leave null for newly created ones)
    /// </summary>
    public Integer CustomPropertyId;

    /// <summary>
    /// The project template the custom property belongs to
    /// </summary>
    public int ProjectTemplateId;

    /// <summary>
    /// The artifact type that the custom property is for
    /// </summary>
    public int ArtifactTypeId;

    /// <summary>
    /// The display name for the custom property
    /// </summary>
    public String Name;

    /// <summary>
    /// The associated custom list if this is a list custom property
    /// </summary>
    /// <remarks>
    /// This will be null if this is not a list custom property
    /// </remarks>
    public RemoteCustomList CustomList;

    /// <summary>
    /// The internal field name of the custom property (e.g. Custom_01)
    /// </summary>
    public String CustomPropertyFieldName;

    /// <summary>
    /// The type of custom property. It can have the following values:
    ///     Text = 1,
    ///     Integer = 2,
    ///     Decimal = 3,
    ///     Boolean = 4,
    ///     Date = 5,
    ///     List = 6,
    ///     MultiList = 7,
    ///     User = 8
    /// </summary>
    public int CustomPropertyTypeId;

    /// <summary>
    /// The display name of the type of custom property
    /// </summary>
    public String CustomPropertyTypeName;

    /// <summary>
    /// Has this custom property been deleted
    /// </summary>
    public boolean IsDeleted;

    /// <summary>
    /// The position number of this custom property (1-30). Each artifact type can have 30 custom properties per project
    /// </summary>
    public int PropertyNumber;

    /// <summary>
    /// The physical data type that this custom property is stored as (Int32, String, DataTime, etc.)
    /// </summary>
    public String SystemDataType;

    /// <summary>
    /// The collection of custom property options
    /// </summary>
    public ArrayList<RemoteCustomPropertyOption> Options;

}
