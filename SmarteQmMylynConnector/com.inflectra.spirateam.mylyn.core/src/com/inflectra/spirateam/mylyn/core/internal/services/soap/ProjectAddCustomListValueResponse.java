
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
 *         &lt;element name="Project_AddCustomListValueResult" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}RemoteCustomListValue" minOccurs="0"/>
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
    "projectAddCustomListValueResult"
})
@XmlRootElement(name = "Project_AddCustomListValueResponse")
public class ProjectAddCustomListValueResponse {

    @XmlElement(name = "Project_AddCustomListValueResult")
    protected RemoteCustomListValue projectAddCustomListValueResult;

    /**
     * Gets the value of the projectAddCustomListValueResult property.
     * 
     * @return
     *     possible object is
     *     {@link RemoteCustomListValue }
     *     
     */
    public RemoteCustomListValue getProjectAddCustomListValueResult() {
        return projectAddCustomListValueResult;
    }

    /**
     * Sets the value of the projectAddCustomListValueResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link RemoteCustomListValue }
     *     
     */
    public void setProjectAddCustomListValueResult(RemoteCustomListValue value) {
        this.projectAddCustomListValueResult = value;
    }

}
