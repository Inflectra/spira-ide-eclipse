
package com.inflectra.spirateam.mylyn.core.internal.services.soap;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
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
 *         &lt;element name="System_GetArtifactUrlResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "systemGetArtifactUrlResult"
})
@XmlRootElement(name = "System_GetArtifactUrlResponse")
public class SystemGetArtifactUrlResponse {

    @XmlElementRef(name = "System_GetArtifactUrlResult", namespace = "http://www.inflectra.com/SpiraTest/Services/v3.0/", type = JAXBElement.class)
    protected JAXBElement<String> systemGetArtifactUrlResult;

    /**
     * Gets the value of the systemGetArtifactUrlResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSystemGetArtifactUrlResult() {
        return systemGetArtifactUrlResult;
    }

    /**
     * Sets the value of the systemGetArtifactUrlResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSystemGetArtifactUrlResult(JAXBElement<String> value) {
        this.systemGetArtifactUrlResult = ((JAXBElement<String> ) value);
    }

}
