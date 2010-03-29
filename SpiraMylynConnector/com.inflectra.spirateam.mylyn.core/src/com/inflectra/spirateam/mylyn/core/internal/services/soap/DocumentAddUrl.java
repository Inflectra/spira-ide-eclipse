
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
 *         &lt;element name="remoteDocument" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}RemoteDocument" minOccurs="0"/>
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
    "remoteDocument"
})
@XmlRootElement(name = "Document_AddUrl")
public class DocumentAddUrl {

    protected RemoteDocument remoteDocument;

    /**
     * Gets the value of the remoteDocument property.
     * 
     * @return
     *     possible object is
     *     {@link RemoteDocument }
     *     
     */
    public RemoteDocument getRemoteDocument() {
        return remoteDocument;
    }

    /**
     * Sets the value of the remoteDocument property.
     * 
     * @param value
     *     allowed object is
     *     {@link RemoteDocument }
     *     
     */
    public void setRemoteDocument(RemoteDocument value) {
        this.remoteDocument = value;
    }

}
