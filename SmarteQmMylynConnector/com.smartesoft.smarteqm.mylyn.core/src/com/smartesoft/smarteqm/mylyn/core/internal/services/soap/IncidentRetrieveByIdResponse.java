
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
 *         &lt;element name="Incident_RetrieveByIdResult" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}RemoteIncident" minOccurs="0"/>
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
    "incidentRetrieveByIdResult"
})
@XmlRootElement(name = "Incident_RetrieveByIdResponse")
public class IncidentRetrieveByIdResponse {

    @XmlElement(name = "Incident_RetrieveByIdResult")
    protected RemoteIncident incidentRetrieveByIdResult;

    /**
     * Gets the value of the incidentRetrieveByIdResult property.
     * 
     * @return
     *     possible object is
     *     {@link RemoteIncident }
     *     
     */
    public RemoteIncident getIncidentRetrieveByIdResult() {
        return incidentRetrieveByIdResult;
    }

    /**
     * Sets the value of the incidentRetrieveByIdResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link RemoteIncident }
     *     
     */
    public void setIncidentRetrieveByIdResult(RemoteIncident value) {
        this.incidentRetrieveByIdResult = value;
    }

}
