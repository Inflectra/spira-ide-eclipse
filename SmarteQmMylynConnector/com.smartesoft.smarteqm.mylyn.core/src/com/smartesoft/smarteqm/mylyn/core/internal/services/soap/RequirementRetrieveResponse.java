
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
 *         &lt;element name="Requirement_RetrieveResult" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}ArrayOfRemoteRequirement" minOccurs="0"/>
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
    "requirementRetrieveResult"
})
@XmlRootElement(name = "Requirement_RetrieveResponse")
public class RequirementRetrieveResponse {

    @XmlElement(name = "Requirement_RetrieveResult")
    protected ArrayOfRemoteRequirement requirementRetrieveResult;

    /**
     * Gets the value of the requirementRetrieveResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfRemoteRequirement }
     *     
     */
    public ArrayOfRemoteRequirement getRequirementRetrieveResult() {
        return requirementRetrieveResult;
    }

    /**
     * Sets the value of the requirementRetrieveResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfRemoteRequirement }
     *     
     */
    public void setRequirementRetrieveResult(ArrayOfRemoteRequirement value) {
        this.requirementRetrieveResult = value;
    }

}
