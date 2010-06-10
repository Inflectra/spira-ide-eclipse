
package com.inflectra.spirateam.mylyn.core.internal.services.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="remoteUser" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}RemoteUser" minOccurs="0"/>
 *         &lt;element name="projectRoleId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "remoteUser",
    "projectRoleId"
})
@XmlRootElement(name = "User_Create")
public class UserCreate {

    protected RemoteUser remoteUser;
    protected int projectRoleId;

    /**
     * Gets the value of the remoteUser property.
     * 
     * @return
     *     possible object is
     *     {@link RemoteUser }
     *     
     */
    public RemoteUser getRemoteUser() {
        return remoteUser;
    }

    /**
     * Sets the value of the remoteUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link RemoteUser }
     *     
     */
    public void setRemoteUser(RemoteUser value) {
        this.remoteUser = value;
    }

    /**
     * Gets the value of the projectRoleId property.
     * 
     */
    public int getProjectRoleId() {
        return projectRoleId;
    }

    /**
     * Sets the value of the projectRoleId property.
     * 
     */
    public void setProjectRoleId(int value) {
        this.projectRoleId = value;
    }

}
