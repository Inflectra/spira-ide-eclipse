
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
 *         &lt;element name="Requirement_Create2Result" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}RemoteRequirement" minOccurs="0"/>
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
    "requirementCreate2Result"
})
@XmlRootElement(name = "Requirement_Create2Response")
public class RequirementCreate2Response {

    @XmlElement(name = "Requirement_Create2Result")
    protected RemoteRequirement requirementCreate2Result;

    /**
     * Gets the value of the requirementCreate2Result property.
     * 
     * @return
     *     possible object is
     *     {@link RemoteRequirement }
     *     
     */
    public RemoteRequirement getRequirementCreate2Result() {
        return requirementCreate2Result;
    }

    /**
     * Sets the value of the requirementCreate2Result property.
     * 
     * @param value
     *     allowed object is
     *     {@link RemoteRequirement }
     *     
     */
    public void setRequirementCreate2Result(RemoteRequirement value) {
        this.requirementCreate2Result = value;
    }

}
