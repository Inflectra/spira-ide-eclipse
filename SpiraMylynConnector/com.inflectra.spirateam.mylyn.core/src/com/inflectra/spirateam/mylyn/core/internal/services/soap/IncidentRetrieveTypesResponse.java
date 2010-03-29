
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
 *         &lt;element name="Incident_RetrieveTypesResult" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}ArrayOfRemoteIncidentType" minOccurs="0"/>
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
    "incidentRetrieveTypesResult"
})
@XmlRootElement(name = "Incident_RetrieveTypesResponse")
public class IncidentRetrieveTypesResponse {

    @XmlElement(name = "Incident_RetrieveTypesResult")
    protected ArrayOfRemoteIncidentType incidentRetrieveTypesResult;

    /**
     * Gets the value of the incidentRetrieveTypesResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfRemoteIncidentType }
     *     
     */
    public ArrayOfRemoteIncidentType getIncidentRetrieveTypesResult() {
        return incidentRetrieveTypesResult;
    }

    /**
     * Sets the value of the incidentRetrieveTypesResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfRemoteIncidentType }
     *     
     */
    public void setIncidentRetrieveTypesResult(ArrayOfRemoteIncidentType value) {
        this.incidentRetrieveTypesResult = value;
    }

}
