
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
 *         &lt;element name="Incident_RetrieveSeveritiesResult" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}ArrayOfRemoteIncidentSeverity" minOccurs="0"/>
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
    "incidentRetrieveSeveritiesResult"
})
@XmlRootElement(name = "Incident_RetrieveSeveritiesResponse")
public class IncidentRetrieveSeveritiesResponse {

    @XmlElement(name = "Incident_RetrieveSeveritiesResult")
    protected ArrayOfRemoteIncidentSeverity incidentRetrieveSeveritiesResult;

    /**
     * Gets the value of the incidentRetrieveSeveritiesResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfRemoteIncidentSeverity }
     *     
     */
    public ArrayOfRemoteIncidentSeverity getIncidentRetrieveSeveritiesResult() {
        return incidentRetrieveSeveritiesResult;
    }

    /**
     * Sets the value of the incidentRetrieveSeveritiesResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfRemoteIncidentSeverity }
     *     
     */
    public void setIncidentRetrieveSeveritiesResult(ArrayOfRemoteIncidentSeverity value) {
        this.incidentRetrieveSeveritiesResult = value;
    }

}
