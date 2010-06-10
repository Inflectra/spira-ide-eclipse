
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
 *         &lt;element name="Document_AddUrlResult" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}RemoteDocument" minOccurs="0"/>
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
    "documentAddUrlResult"
})
@XmlRootElement(name = "Document_AddUrlResponse")
public class DocumentAddUrlResponse {

    @XmlElement(name = "Document_AddUrlResult")
    protected RemoteDocument documentAddUrlResult;

    /**
     * Gets the value of the documentAddUrlResult property.
     * 
     * @return
     *     possible object is
     *     {@link RemoteDocument }
     *     
     */
    public RemoteDocument getDocumentAddUrlResult() {
        return documentAddUrlResult;
    }

    /**
     * Sets the value of the documentAddUrlResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link RemoteDocument }
     *     
     */
    public void setDocumentAddUrlResult(RemoteDocument value) {
        this.documentAddUrlResult = value;
    }

}
