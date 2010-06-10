
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
 *         &lt;element name="Incident_AddTypeResult" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}RemoteIncidentType" minOccurs="0"/>
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
    "incidentAddTypeResult"
})
@XmlRootElement(name = "Incident_AddTypeResponse")
public class IncidentAddTypeResponse {

    @XmlElement(name = "Incident_AddTypeResult")
    protected RemoteIncidentType incidentAddTypeResult;

    /**
     * Gets the value of the incidentAddTypeResult property.
     * 
     * @return
     *     possible object is
     *     {@link RemoteIncidentType }
     *     
     */
    public RemoteIncidentType getIncidentAddTypeResult() {
        return incidentAddTypeResult;
    }

    /**
     * Sets the value of the incidentAddTypeResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link RemoteIncidentType }
     *     
     */
    public void setIncidentAddTypeResult(RemoteIncidentType value) {
        this.incidentAddTypeResult = value;
    }

}
