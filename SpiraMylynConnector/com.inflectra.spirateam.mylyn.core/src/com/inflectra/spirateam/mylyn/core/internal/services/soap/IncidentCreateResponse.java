
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
 *         &lt;element name="Incident_CreateResult" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}RemoteIncident" minOccurs="0"/>
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
    "incidentCreateResult"
})
@XmlRootElement(name = "Incident_CreateResponse")
public class IncidentCreateResponse {

    @XmlElement(name = "Incident_CreateResult")
    protected RemoteIncident incidentCreateResult;

    /**
     * Gets the value of the incidentCreateResult property.
     * 
     * @return
     *     possible object is
     *     {@link RemoteIncident }
     *     
     */
    public RemoteIncident getIncidentCreateResult() {
        return incidentCreateResult;
    }

    /**
     * Sets the value of the incidentCreateResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link RemoteIncident }
     *     
     */
    public void setIncidentCreateResult(RemoteIncident value) {
        this.incidentCreateResult = value;
    }

}