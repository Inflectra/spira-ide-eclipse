package com.inflectra.spirateam.mylyn.core.internal.rest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

public class RemoteArtifactCustomProperty {
    /// <summary>
    /// The number of the custom property field
    /// </summary>
    public int PropertyNumber;

    /// <summary>
    /// The value of a string custom property
    /// </summary>
    public String StringValue;

    /// <summary>
    /// The value of an integer custom property
    /// </summary>
    public Integer IntegerValue;

    /// <summary>
    /// The value of a boolean custom property
    /// </summary>
    public Boolean BooleanValue;

    /// <summary>
    /// The value of a date-time custom property
    /// </summary>
    public Date DateTimeValue;

    /// <summary>
    /// The value of a decimal custom property
    /// </summary>
    public BigDecimal DecimalValue;

    /// <summary>
    /// The value of a multi-list custom property
    /// </summary>
    public ArrayList<Integer> IntegerListValue;

    /// <summary>
    /// The associated custom property definition (read-only)
    /// </summary>
    public RemoteCustomProperty Definition;

}
