
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
 *         &lt;element name="System_GetSettingsResult" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}ArrayOfRemoteSetting" minOccurs="0"/>
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
    "systemGetSettingsResult"
})
@XmlRootElement(name = "System_GetSettingsResponse")
public class SystemGetSettingsResponse {

    @XmlElement(name = "System_GetSettingsResult")
    protected ArrayOfRemoteSetting systemGetSettingsResult;

    /**
     * Gets the value of the systemGetSettingsResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfRemoteSetting }
     *     
     */
    public ArrayOfRemoteSetting getSystemGetSettingsResult() {
        return systemGetSettingsResult;
    }

    /**
     * Sets the value of the systemGetSettingsResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfRemoteSetting }
     *     
     */
    public void setSystemGetSettingsResult(ArrayOfRemoteSetting value) {
        this.systemGetSettingsResult = value;
    }

}
