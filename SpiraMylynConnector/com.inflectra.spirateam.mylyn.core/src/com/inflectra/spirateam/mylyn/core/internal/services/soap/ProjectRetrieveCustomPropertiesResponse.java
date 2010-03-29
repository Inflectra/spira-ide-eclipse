
package com.inflectra.spirateam.mylyn.core.internal.services.soap;

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
 *         &lt;element name="Project_RetrieveCustomPropertiesResult" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}ArrayOfRemoteProjectCustomProperty" minOccurs="0"/>
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
    "projectRetrieveCustomPropertiesResult"
})
@XmlRootElement(name = "Project_RetrieveCustomPropertiesResponse")
public class ProjectRetrieveCustomPropertiesResponse {

    @XmlElement(name = "Project_RetrieveCustomPropertiesResult")
    protected ArrayOfRemoteProjectCustomProperty projectRetrieveCustomPropertiesResult;

    /**
     * Gets the value of the projectRetrieveCustomPropertiesResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfRemoteProjectCustomProperty }
     *     
     */
    public ArrayOfRemoteProjectCustomProperty getProjectRetrieveCustomPropertiesResult() {
        return projectRetrieveCustomPropertiesResult;
    }

    /**
     * Sets the value of the projectRetrieveCustomPropertiesResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfRemoteProjectCustomProperty }
     *     
     */
    public void setProjectRetrieveCustomPropertiesResult(ArrayOfRemoteProjectCustomProperty value) {
        this.projectRetrieveCustomPropertiesResult = value;
    }

}
