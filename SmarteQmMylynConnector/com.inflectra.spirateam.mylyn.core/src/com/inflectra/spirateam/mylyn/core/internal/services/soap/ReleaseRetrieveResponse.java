
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
 *         &lt;element name="Release_RetrieveResult" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}ArrayOfRemoteRelease" minOccurs="0"/>
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
    "releaseRetrieveResult"
})
@XmlRootElement(name = "Release_RetrieveResponse")
public class ReleaseRetrieveResponse {

    @XmlElement(name = "Release_RetrieveResult")
    protected ArrayOfRemoteRelease releaseRetrieveResult;

    /**
     * Gets the value of the releaseRetrieveResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfRemoteRelease }
     *     
     */
    public ArrayOfRemoteRelease getReleaseRetrieveResult() {
        return releaseRetrieveResult;
    }

    /**
     * Sets the value of the releaseRetrieveResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfRemoteRelease }
     *     
     */
    public void setReleaseRetrieveResult(ArrayOfRemoteRelease value) {
        this.releaseRetrieveResult = value;
    }

}
