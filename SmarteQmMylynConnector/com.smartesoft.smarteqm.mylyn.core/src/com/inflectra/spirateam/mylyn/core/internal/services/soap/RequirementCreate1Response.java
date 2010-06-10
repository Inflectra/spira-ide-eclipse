
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
 *         &lt;element name="Requirement_Create1Result" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}RemoteRequirement" minOccurs="0"/>
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
    "requirementCreate1Result"
})
@XmlRootElement(name = "Requirement_Create1Response")
public class RequirementCreate1Response {

    @XmlElement(name = "Requirement_Create1Result")
    protected RemoteRequirement requirementCreate1Result;

    /**
     * Gets the value of the requirementCreate1Result property.
     * 
     * @return
     *     possible object is
     *     {@link RemoteRequirement }
     *     
     */
    public RemoteRequirement getRequirementCreate1Result() {
        return requirementCreate1Result;
    }

    /**
     * Sets the value of the requirementCreate1Result property.
     * 
     * @param value
     *     allowed object is
     *     {@link RemoteRequirement }
     *     
     */
    public void setRequirementCreate1Result(RemoteRequirement value) {
        this.requirementCreate1Result = value;
    }

}
