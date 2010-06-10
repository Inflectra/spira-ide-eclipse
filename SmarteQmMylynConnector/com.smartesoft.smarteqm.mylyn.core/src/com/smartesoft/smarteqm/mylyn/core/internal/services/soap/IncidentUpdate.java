
package com.smartesoft.smarteqm.mylyn.core.internal.services.soap;

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
 *         &lt;element name="remoteIncident" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}RemoteIncident" minOccurs="0"/>
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
    "remoteIncident"
})
@XmlRootElement(name = "Incident_Update")
public class IncidentUpdate {

    protected RemoteIncident remoteIncident;

    /**
     * Gets the value of the remoteIncident property.
     * 
     * @return
     *     possible object is
     *     {@link RemoteIncident }
     *     
     */
    public RemoteIncident getRemoteIncident() {
        return remoteIncident;
    }

    /**
     * Sets the value of the remoteIncident property.
     * 
     * @param value
     *     allowed object is
     *     {@link RemoteIncident }
     *     
     */
    public void setRemoteIncident(RemoteIncident value) {
        this.remoteIncident = value;
    }

}
