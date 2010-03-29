
package com.inflectra.spirateam.mylyn.core.internal.services.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="remoteIncidentSeverity" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}RemoteIncidentSeverity" minOccurs="0"/>
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
    "remoteIncidentSeverity"
})
@XmlRootElement(name = "Incident_AddSeverity")
public class IncidentAddSeverity {

    protected RemoteIncidentSeverity remoteIncidentSeverity;

    /**
     * Gets the value of the remoteIncidentSeverity property.
     * 
     * @return
     *     possible object is
     *     {@link RemoteIncidentSeverity }
     *     
     */
    public RemoteIncidentSeverity getRemoteIncidentSeverity() {
        return remoteIncidentSeverity;
    }

    /**
     * Sets the value of the remoteIncidentSeverity property.
     * 
     * @param value
     *     allowed object is
     *     {@link RemoteIncidentSeverity }
     *     
     */
    public void setRemoteIncidentSeverity(RemoteIncidentSeverity value) {
        this.remoteIncidentSeverity = value;
    }

}
