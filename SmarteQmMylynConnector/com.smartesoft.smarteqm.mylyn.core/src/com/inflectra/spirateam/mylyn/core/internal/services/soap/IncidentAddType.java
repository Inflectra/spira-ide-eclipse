
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
 *         &lt;element name="remoteIncidentType" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}RemoteIncidentType" minOccurs="0"/>
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
    "remoteIncidentType"
})
@XmlRootElement(name = "Incident_AddType")
public class IncidentAddType {

    protected RemoteIncidentType remoteIncidentType;

    /**
     * Gets the value of the remoteIncidentType property.
     * 
     * @return
     *     possible object is
     *     {@link RemoteIncidentType }
     *     
     */
    public RemoteIncidentType getRemoteIncidentType() {
        return remoteIncidentType;
    }

    /**
     * Sets the value of the remoteIncidentType property.
     * 
     * @param value
     *     allowed object is
     *     {@link RemoteIncidentType }
     *     
     */
    public void setRemoteIncidentType(RemoteIncidentType value) {
        this.remoteIncidentType = value;
    }

}
