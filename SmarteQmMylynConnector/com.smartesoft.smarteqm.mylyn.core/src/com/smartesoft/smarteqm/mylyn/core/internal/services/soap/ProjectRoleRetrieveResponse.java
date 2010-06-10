
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
 *         &lt;element name="ProjectRole_RetrieveResult" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}ArrayOfRemoteProjectRole" minOccurs="0"/>
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
    "projectRoleRetrieveResult"
})
@XmlRootElement(name = "ProjectRole_RetrieveResponse")
public class ProjectRoleRetrieveResponse {

    @XmlElement(name = "ProjectRole_RetrieveResult")
    protected ArrayOfRemoteProjectRole projectRoleRetrieveResult;

    /**
     * Gets the value of the projectRoleRetrieveResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfRemoteProjectRole }
     *     
     */
    public ArrayOfRemoteProjectRole getProjectRoleRetrieveResult() {
        return projectRoleRetrieveResult;
    }

    /**
     * Sets the value of the projectRoleRetrieveResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfRemoteProjectRole }
     *     
     */
    public void setProjectRoleRetrieveResult(ArrayOfRemoteProjectRole value) {
        this.projectRoleRetrieveResult = value;
    }

}
