
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
 *         &lt;element name="System_GetWebServerUrlResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "systemGetWebServerUrlResult"
})
@XmlRootElement(name = "System_GetWebServerUrlResponse")
public class SystemGetWebServerUrlResponse {

    @XmlElement(name = "System_GetWebServerUrlResult")
    protected String systemGetWebServerUrlResult;

    /**
     * Gets the value of the systemGetWebServerUrlResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSystemGetWebServerUrlResult() {
        return systemGetWebServerUrlResult;
    }

    /**
     * Sets the value of the systemGetWebServerUrlResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSystemGetWebServerUrlResult(String value) {
        this.systemGetWebServerUrlResult = value;
    }

}
