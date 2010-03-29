
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
 *         &lt;element name="Incident_RetrievePrioritiesResult" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}ArrayOfRemoteIncidentPriority" minOccurs="0"/>
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
    "incidentRetrievePrioritiesResult"
})
@XmlRootElement(name = "Incident_RetrievePrioritiesResponse")
public class IncidentRetrievePrioritiesResponse {

    @XmlElement(name = "Incident_RetrievePrioritiesResult")
    protected ArrayOfRemoteIncidentPriority incidentRetrievePrioritiesResult;

    /**
     * Gets the value of the incidentRetrievePrioritiesResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfRemoteIncidentPriority }
     *     
     */
    public ArrayOfRemoteIncidentPriority getIncidentRetrievePrioritiesResult() {
        return incidentRetrievePrioritiesResult;
    }

    /**
     * Sets the value of the incidentRetrievePrioritiesResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfRemoteIncidentPriority }
     *     
     */
    public void setIncidentRetrievePrioritiesResult(ArrayOfRemoteIncidentPriority value) {
        this.incidentRetrievePrioritiesResult = value;
    }

}
