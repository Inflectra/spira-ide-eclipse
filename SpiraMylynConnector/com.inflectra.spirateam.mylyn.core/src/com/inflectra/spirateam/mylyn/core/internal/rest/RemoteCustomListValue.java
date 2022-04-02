package com.inflectra.spirateam.mylyn.core.internal.rest;

public class RemoteCustomListValue {
    /// <summary>
    /// The id of the custom list value
    /// </summary>
    public Integer CustomPropertyValueId;

    /// <summary>
    /// The id of the custom list the value belongs to
    /// </summary>
    public int CustomPropertyListId;

    /// <summary>
    /// The name of the custom list value
    /// </summary>
    public String Name;


    /// <summary>
    /// Is the custom list value active or not
    /// </summary>
    public boolean Active;

    /// <summary>
    /// The id of any parent custom list value
    /// </summary>
    public Integer ParentCustomPropertyValueId;

}
