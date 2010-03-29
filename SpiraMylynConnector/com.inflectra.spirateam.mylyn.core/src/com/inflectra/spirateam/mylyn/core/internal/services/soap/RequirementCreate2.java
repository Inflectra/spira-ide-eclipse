
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
 *         &lt;element name="remoteRequirement" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}RemoteRequirement" minOccurs="0"/>
 *         &lt;element name="parentRequirementId" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "remoteRequirement",
    "parentRequirementId"
})
@XmlRootElement(name = "Requirement_Create2")
public class RequirementCreate2 {

    protected RemoteRequirement remoteRequirement;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer parentRequirementId;

    /**
     * Gets the value of the remoteRequirement property.
     * 
     * @return
     *     possible object is
     *     {@link RemoteRequirement }
     *     
     */
    public RemoteRequirement getRemoteRequirement() {
        return remoteRequirement;
    }

    /**
     * Sets the value of the remoteRequirement property.
     * 
     * @param value
     *     allowed object is
     *     {@link RemoteRequirement }
     *     
     */
    public void setRemoteRequirement(RemoteRequirement value) {
        this.remoteRequirement = value;
    }

    /**
     * Gets the value of the parentRequirementId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getParentRequirementId() {
        return parentRequirementId;
    }

    /**
     * Sets the value of the parentRequirementId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setParentRequirementId(Integer value) {
        this.parentRequirementId = value;
    }

}
