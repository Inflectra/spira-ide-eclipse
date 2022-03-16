package com.inflectra.spirateam.mylyn.core.internal.rest;

public class RemoteCustomPropertyOption {
    /// <summary>
    /// The id of the custom property option. Allowed values are:
    ///     AllowEmpty = 1,
    ///     MaxLength = 2,
    ///     MinLength = 3,
    ///     RichText = 4,
    ///     Default = 5,
    ///     MaxValue = 6,
    ///     MinValue = 7,
    ///     Precision = 8
    /// </summary>
    public int CustomPropertyOptionId;

    /// <summary>
    /// The value of the custom property option
    /// </summary>
    public String Value;
}
