
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
 *         &lt;element name="Incident_AddPriorityResult" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}RemoteIncidentPriority" minOccurs="0"/>
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
    "incidentAddPriorityResult"
})
@XmlRootElement(name = "Incident_AddPriorityResponse")
public class IncidentAddPriorityResponse {

    @XmlElement(name = "Incident_AddPriorityResult")
    protected RemoteIncidentPriority incidentAddPriorityResult;

    /**
     * Gets the value of the incidentAddPriorityResult property.
     * 
     * @return
     *     possible object is
     *     {@link RemoteIncidentPriority }
     *     
     */
    public RemoteIncidentPriority getIncidentAddPriorityResult() {
        return incidentAddPriorityResult;
    }

    /**
     * Sets the value of the incidentAddPriorityResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link RemoteIncidentPriority }
     *     
     */
    public void setIncidentAddPriorityResult(RemoteIncidentPriority value) {
        this.incidentAddPriorityResult = value;
    }

}
