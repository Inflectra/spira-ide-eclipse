
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
 *         &lt;element name="Project_RetrieveByIdResult" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}RemoteProject" minOccurs="0"/>
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
    "projectRetrieveByIdResult"
})
@XmlRootElement(name = "Project_RetrieveByIdResponse")
public class ProjectRetrieveByIdResponse {

    @XmlElement(name = "Project_RetrieveByIdResult")
    protected RemoteProject projectRetrieveByIdResult;

    /**
     * Gets the value of the projectRetrieveByIdResult property.
     * 
     * @return
     *     possible object is
     *     {@link RemoteProject }
     *     
     */
    public RemoteProject getProjectRetrieveByIdResult() {
        return projectRetrieveByIdResult;
    }

    /**
     * Sets the value of the projectRetrieveByIdResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link RemoteProject }
     *     
     */
    public void setProjectRetrieveByIdResult(RemoteProject value) {
        this.projectRetrieveByIdResult = value;
    }

}
