package com.inflectra.spirateam.mylyn.core.internal.rest;

import java.util.ArrayList;

public class RemoteCustomList {
    /// <summary>
    /// The id of the custom list
    /// </summary>
    public Integer CustomPropertyListId;

    /// <summary>
    /// The id of the project template the custom list belongs to
    /// </summary>
    public int ProjectTemplateId;

    /// <summary>
    /// The name of the custom list
    /// </summary>
    public String Name;

    /// <summary>
    /// Whether the list is active or not
    /// </summary>
    public boolean Active;

    /// <summary>
    /// Whether the list is sorted on value or not
    /// </summary>
    public boolean SortedOnValue;

    /// <summary>
    /// The collection of values in the custom list
    /// </summary>
    public ArrayList<RemoteCustomListValue> Values;

}
