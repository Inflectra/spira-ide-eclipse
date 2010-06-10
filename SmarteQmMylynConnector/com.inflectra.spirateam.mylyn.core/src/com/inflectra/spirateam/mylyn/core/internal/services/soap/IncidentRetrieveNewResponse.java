
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
 *         &lt;element name="Incident_RetrieveNewResult" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}ArrayOfRemoteIncident" minOccurs="0"/>
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
    "incidentRetrieveNewResult"
})
@XmlRootElement(name = "Incident_RetrieveNewResponse")
public class IncidentRetrieveNewResponse {

    @XmlElement(name = "Incident_RetrieveNewResult")
    protected ArrayOfRemoteIncident incidentRetrieveNewResult;

    /**
     * Gets the value of the incidentRetrieveNewResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfRemoteIncident }
     *     
     */
    public ArrayOfRemoteIncident getIncidentRetrieveNewResult() {
        return incidentRetrieveNewResult;
    }

    /**
     * Sets the value of the incidentRetrieveNewResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfRemoteIncident }
     *     
     */
    public void setIncidentRetrieveNewResult(ArrayOfRemoteIncident value) {
        this.incidentRetrieveNewResult = value;
    }

}
