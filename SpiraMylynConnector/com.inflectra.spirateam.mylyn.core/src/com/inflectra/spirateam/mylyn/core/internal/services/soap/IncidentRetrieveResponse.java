
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
 *         &lt;element name="Incident_RetrieveResult" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}ArrayOfRemoteIncident" minOccurs="0"/>
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
    "incidentRetrieveResult"
})
@XmlRootElement(name = "Incident_RetrieveResponse")
public class IncidentRetrieveResponse {

    @XmlElement(name = "Incident_RetrieveResult")
    protected ArrayOfRemoteIncident incidentRetrieveResult;

    /**
     * Gets the value of the incidentRetrieveResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfRemoteIncident }
     *     
     */
    public ArrayOfRemoteIncident getIncidentRetrieveResult() {
        return incidentRetrieveResult;
    }

    /**
     * Sets the value of the incidentRetrieveResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfRemoteIncident }
     *     
     */
    public void setIncidentRetrieveResult(ArrayOfRemoteIncident value) {
        this.incidentRetrieveResult = value;
    }

}