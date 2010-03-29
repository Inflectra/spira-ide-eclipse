
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
 *         &lt;element name="Requirement_RetrieveByIdResult" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}RemoteRequirement" minOccurs="0"/>
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
    "requirementRetrieveByIdResult"
})
@XmlRootElement(name = "Requirement_RetrieveByIdResponse")
public class RequirementRetrieveByIdResponse {

    @XmlElement(name = "Requirement_RetrieveByIdResult")
    protected RemoteRequirement requirementRetrieveByIdResult;

    /**
     * Gets the value of the requirementRetrieveByIdResult property.
     * 
     * @return
     *     possible object is
     *     {@link RemoteRequirement }
     *     
     */
    public RemoteRequirement getRequirementRetrieveByIdResult() {
        return requirementRetrieveByIdResult;
    }

    /**
     * Sets the value of the requirementRetrieveByIdResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link RemoteRequirement }
     *     
     */
    public void setRequirementRetrieveByIdResult(RemoteRequirement value) {
        this.requirementRetrieveByIdResult = value;
    }

}
