
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
 *         &lt;element name="Release_Retrieve2Result" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}ArrayOfRemoteRelease" minOccurs="0"/>
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
    "releaseRetrieve2Result"
})
@XmlRootElement(name = "Release_Retrieve2Response")
public class ReleaseRetrieve2Response {

    @XmlElement(name = "Release_Retrieve2Result")
    protected ArrayOfRemoteRelease releaseRetrieve2Result;

    /**
     * Gets the value of the releaseRetrieve2Result property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfRemoteRelease }
     *     
     */
    public ArrayOfRemoteRelease getReleaseRetrieve2Result() {
        return releaseRetrieve2Result;
    }

    /**
     * Sets the value of the releaseRetrieve2Result property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfRemoteRelease }
     *     
     */
    public void setReleaseRetrieve2Result(ArrayOfRemoteRelease value) {
        this.releaseRetrieve2Result = value;
    }

}
