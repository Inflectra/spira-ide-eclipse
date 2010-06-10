
package com.inflectra.spirateam.mylyn.core.internal.services.soap;

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
 *         &lt;element name="remoteCustomList" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}RemoteCustomList" minOccurs="0"/>
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
    "remoteCustomList"
})
@XmlRootElement(name = "Project_AddCustomList")
public class ProjectAddCustomList {

    protected RemoteCustomList remoteCustomList;

    /**
     * Gets the value of the remoteCustomList property.
     * 
     * @return
     *     possible object is
     *     {@link RemoteCustomList }
     *     
     */
    public RemoteCustomList getRemoteCustomList() {
        return remoteCustomList;
    }

    /**
     * Sets the value of the remoteCustomList property.
     * 
     * @param value
     *     allowed object is
     *     {@link RemoteCustomList }
     *     
     */
    public void setRemoteCustomList(RemoteCustomList value) {
        this.remoteCustomList = value;
    }

}
