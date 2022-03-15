package com.inflectra.spirateam.mylyn.core.internal.rest;

public class RemoteUser {
    /// <summary>
    /// The id of the user
    /// </summary>
    public Integer UserId;

    /// <summary>
    /// The first (given) name of the user
    /// </summary>
    public String FirstName;

    /// <summary>
    /// The last name (surname) of the user
    /// </summary>
    public String LastName;

    /// <summary>
    /// The middle initials of the user
    /// </summary>
    public String MiddleInitial;

    /// <summary>
    /// The login used by the user
    /// </summary>
    public String UserName;

    /// <summary>
    /// The LDAP Distinguished Name for the user (null for non-LDAP users)
    /// </summary>
    public String LdapDn;

    /// <summary>
    /// The email address of the user
    /// </summary>
    public String EmailAddress;

    /// <summary>
    /// Whether the user is a system administrator
    /// </summary>
    public boolean Admin;

    /// <summary>
    /// Whether the user is active in the system
    /// </summary>
    public boolean Active;

    /// <summary>
    /// The department of the user
    /// </summary>
    public String Department;

    /// <summary>
    /// Is this user approved by the system administrator
    /// </summary>
    public boolean Approved;

    /// <summary>
    /// Is this user locked-out of their account
    /// </summary>
    public boolean Locked;
    
    /// <summary>
    /// This is the RSS token for this user
    /// </summary>
    /// <remarks>
    /// For security reasons, you have to access the API as a system administrator to retrieve this field
    /// </remarks>
    public String RssToken;

    /// <summary>
    /// The full name of the user concatenated (First + Middle + Last)
    /// </summary>
    public String FullName;

}
