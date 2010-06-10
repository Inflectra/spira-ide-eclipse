
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
 *         &lt;element name="remoteIncidentStatus" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}RemoteIncidentStatus" minOccurs="0"/>
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
    "remoteIncidentStatus"
})
@XmlRootElement(name = "Incident_AddStatus")
public class IncidentAddStatus {

    protected RemoteIncidentStatus remoteIncidentStatus;

    /**
     * Gets the value of the remoteIncidentStatus property.
     * 
     * @return
     *     possible object is
     *     {@link RemoteIncidentStatus }
     *     
     */
    public RemoteIncidentStatus getRemoteIncidentStatus() {
        return remoteIncidentStatus;
    }

    /**
     * Sets the value of the remoteIncidentStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link RemoteIncidentStatus }
     *     
     */
    public void setRemoteIncidentStatus(RemoteIncidentStatus value) {
        this.remoteIncidentStatus = value;
    }

}
