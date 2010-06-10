
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
 *         &lt;element name="Incident_RetrieveWorkflowTransitionsResult" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}ArrayOfRemoteWorkflowIncidentTransition" minOccurs="0"/>
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
    "incidentRetrieveWorkflowTransitionsResult"
})
@XmlRootElement(name = "Incident_RetrieveWorkflowTransitionsResponse")
public class IncidentRetrieveWorkflowTransitionsResponse {

    @XmlElement(name = "Incident_RetrieveWorkflowTransitionsResult")
    protected ArrayOfRemoteWorkflowIncidentTransition incidentRetrieveWorkflowTransitionsResult;

    /**
     * Gets the value of the incidentRetrieveWorkflowTransitionsResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfRemoteWorkflowIncidentTransition }
     *     
     */
    public ArrayOfRemoteWorkflowIncidentTransition getIncidentRetrieveWorkflowTransitionsResult() {
        return incidentRetrieveWorkflowTransitionsResult;
    }

    /**
     * Sets the value of the incidentRetrieveWorkflowTransitionsResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfRemoteWorkflowIncidentTransition }
     *     
     */
    public void setIncidentRetrieveWorkflowTransitionsResult(ArrayOfRemoteWorkflowIncidentTransition value) {
        this.incidentRetrieveWorkflowTransitionsResult = value;
    }

}
