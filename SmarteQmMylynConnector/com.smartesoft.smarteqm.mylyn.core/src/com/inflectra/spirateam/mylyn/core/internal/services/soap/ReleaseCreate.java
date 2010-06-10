
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
 *         &lt;element name="remoteRelease" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}RemoteRelease" minOccurs="0"/>
 *         &lt;element name="parentReleaseId" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "remoteRelease",
    "parentReleaseId"
})
@XmlRootElement(name = "Release_Create")
public class ReleaseCreate {

    protected RemoteRelease remoteRelease;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer parentReleaseId;

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

    /**
     * Gets the value of the parentReleaseId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getParentReleaseId() {
        return parentReleaseId;
    }

    /**
     * Sets the value of the parentReleaseId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setParentReleaseId(Integer value) {
        this.parentReleaseId = value;
    }

}
