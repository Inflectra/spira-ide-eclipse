
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
 *         &lt;element name="Incident_RetrieveWorkflowFieldsResult" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}ArrayOfRemoteWorkflowIncidentFields" minOccurs="0"/>
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
    "incidentRetrieveWorkflowFieldsResult"
})
@XmlRootElement(name = "Incident_RetrieveWorkflowFieldsResponse")
public class IncidentRetrieveWorkflowFieldsResponse {

    @XmlElement(name = "Incident_RetrieveWorkflowFieldsResult")
    protected ArrayOfRemoteWorkflowIncidentFields incidentRetrieveWorkflowFieldsResult;

    /**
     * Gets the value of the incidentRetrieveWorkflowFieldsResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfRemoteWorkflowIncidentFields }
     *     
     */
    public ArrayOfRemoteWorkflowIncidentFields getIncidentRetrieveWorkflowFieldsResult() {
        return incidentRetrieveWorkflowFieldsResult;
    }

    /**
     * Sets the value of the incidentRetrieveWorkflowFieldsResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfRemoteWorkflowIncidentFields }
     *     
     */
    public void setIncidentRetrieveWorkflowFieldsResult(ArrayOfRemoteWorkflowIncidentFields value) {
        this.incidentRetrieveWorkflowFieldsResult = value;
    }

}
