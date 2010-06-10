
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
 *         &lt;element name="Incident_AddSeverityResult" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}RemoteIncidentSeverity" minOccurs="0"/>
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
    "incidentAddSeverityResult"
})
@XmlRootElement(name = "Incident_AddSeverityResponse")
public class IncidentAddSeverityResponse {

    @XmlElement(name = "Incident_AddSeverityResult")
    protected RemoteIncidentSeverity incidentAddSeverityResult;

    /**
     * Gets the value of the incidentAddSeverityResult property.
     * 
     * @return
     *     possible object is
     *     {@link RemoteIncidentSeverity }
     *     
     */
    public RemoteIncidentSeverity getIncidentAddSeverityResult() {
        return incidentAddSeverityResult;
    }

    /**
     * Sets the value of the incidentAddSeverityResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link RemoteIncidentSeverity }
     *     
     */
    public void setIncidentAddSeverityResult(RemoteIncidentSeverity value) {
        this.incidentAddSeverityResult = value;
    }

}
