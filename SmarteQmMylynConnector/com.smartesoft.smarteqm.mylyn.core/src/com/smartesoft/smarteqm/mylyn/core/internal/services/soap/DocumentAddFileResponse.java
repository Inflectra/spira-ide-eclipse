
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
 *         &lt;element name="Document_AddFileResult" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}RemoteDocument" minOccurs="0"/>
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
    "documentAddFileResult"
})
@XmlRootElement(name = "Document_AddFileResponse")
public class DocumentAddFileResponse {

    @XmlElement(name = "Document_AddFileResult")
    protected RemoteDocument documentAddFileResult;

    /**
     * Gets the value of the documentAddFileResult property.
     * 
     * @return
     *     possible object is
     *     {@link RemoteDocument }
     *     
     */
    public RemoteDocument getDocumentAddFileResult() {
        return documentAddFileResult;
    }

    /**
     * Sets the value of the documentAddFileResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link RemoteDocument }
     *     
     */
    public void setDocumentAddFileResult(RemoteDocument value) {
        this.documentAddFileResult = value;
    }

}
