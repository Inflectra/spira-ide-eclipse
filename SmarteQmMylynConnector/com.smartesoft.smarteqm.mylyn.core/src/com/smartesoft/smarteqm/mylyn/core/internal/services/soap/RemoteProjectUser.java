
package com.smartesoft.smarteqm.mylyn.core.internal.services.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RemoteProjectUser complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RemoteProjectUser">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ProjectId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="UserId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ProjectRoleId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ProjectRoleName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RemoteProjectUser", propOrder = {
    "projectId",
    "userId",
    "projectRoleId",
    "projectRoleName"
})
public class RemoteProjectUser {

    @XmlElement(name = "ProjectId")
    protected int projectId;
    @XmlElement(name = "UserId")
    protected int userId;
    @XmlElement(name = "ProjectRoleId")
    protected int projectRoleId;
    @XmlElement(name = "ProjectRoleName")
    protected String projectRoleName;

    /**
     * Gets the value of the projectId property.
     * 
     */
    public int getProjectId() {
        return projectId;
    }

    /**
     * Sets the value of the projectId property.
     * 
     */
    public void setProjectId(int value) {
        this.projectId = value;
    }

    /**
     * Gets the value of the userId property.
     * 
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the value of the userId property.
     * 
     */
    public void setUserId(int value) {
        this.userId = value;
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

    /**
     * Gets the value of the projectRoleName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProjectRoleName() {
        return projectRoleName;
    }

    /**
     * Sets the value of the projectRoleName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProjectRoleName(String value) {
        this.projectRoleName = value;
    }

}
