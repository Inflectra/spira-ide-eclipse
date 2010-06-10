
package com.smartesoft.smarteqm.mylyn.core.internal.services.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 *         &lt;element name="Project_RetrieveUserMembershipResult" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}ArrayOfRemoteProjectUser" minOccurs="0"/>
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
    "projectRetrieveUserMembershipResult"
})
@XmlRootElement(name = "Project_RetrieveUserMembershipResponse")
public class ProjectRetrieveUserMembershipResponse {

    @XmlElement(name = "Project_RetrieveUserMembershipResult")
    protected ArrayOfRemoteProjectUser projectRetrieveUserMembershipResult;

    /**
     * Gets the value of the projectRetrieveUserMembershipResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfRemoteProjectUser }
     *     
     */
    public ArrayOfRemoteProjectUser getProjectRetrieveUserMembershipResult() {
        return projectRetrieveUserMembershipResult;
    }

    /**
     * Sets the value of the projectRetrieveUserMembershipResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfRemoteProjectUser }
     *     
     */
    public void setProjectRetrieveUserMembershipResult(ArrayOfRemoteProjectUser value) {
        this.projectRetrieveUserMembershipResult = value;
    }

}
