
package com.smartesoft.smarteqm.mylyn.core.internal.services.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="remoteRelease" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}RemoteRelease" minOccurs="0"/>
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
    "remoteRelease"
})
@XmlRootElement(name = "Release_Update")
public class ReleaseUpdate {

    protected RemoteRelease remoteRelease;

    /**
     * Gets the value of the remoteRelease property.
     * 
     * @return
     *     possible object is
     *     {@link RemoteRelease }
     *     
     */
    public RemoteRelease getRemoteRelease() {
        return remoteRelease;
    }

    /**
     * Sets the value of the remoteRelease property.
     * 
     * @param value
     *     allowed object is
     *     {@link RemoteRelease }
     *     
     */
    public void setRemoteRelease(RemoteRelease value) {
        this.remoteRelease = value;
    }

}
