
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
 *         &lt;element name="CustomProperty_RetrieveProjectPropertiesResult" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}ArrayOfRemoteCustomProperty" minOccurs="0"/>
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
    "customPropertyRetrieveProjectPropertiesResult"
})
@XmlRootElement(name = "CustomProperty_RetrieveProjectPropertiesResponse")
public class CustomPropertyRetrieveProjectPropertiesResponse {

    @XmlElement(name = "CustomProperty_RetrieveProjectPropertiesResult")
    protected ArrayOfRemoteCustomProperty customPropertyRetrieveProjectPropertiesResult;

    /**
     * Gets the value of the customPropertyRetrieveProjectPropertiesResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfRemoteCustomProperty }
     *     
     */
    public ArrayOfRemoteCustomProperty getCustomPropertyRetrieveProjectPropertiesResult() {
        return customPropertyRetrieveProjectPropertiesResult;
    }

    /**
     * Sets the value of the customPropertyRetrieveProjectPropertiesResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfRemoteCustomProperty }
     *     
     */
    public void setCustomPropertyRetrieveProjectPropertiesResult(ArrayOfRemoteCustomProperty value) {
        this.customPropertyRetrieveProjectPropertiesResult = value;
    }

}
