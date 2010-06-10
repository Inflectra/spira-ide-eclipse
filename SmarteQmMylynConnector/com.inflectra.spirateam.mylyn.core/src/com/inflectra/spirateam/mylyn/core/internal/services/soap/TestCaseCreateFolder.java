
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
 *         &lt;element name="remoteTestCase" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}RemoteTestCase" minOccurs="0"/>
 *         &lt;element name="parentTestFolderId" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "remoteTestCase",
    "parentTestFolderId"
})
@XmlRootElement(name = "TestCase_CreateFolder")
public class TestCaseCreateFolder {

    protected RemoteTestCase remoteTestCase;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer parentTestFolderId;

    /**
     * Gets the value of the remoteTestCase property.
     * 
     * @return
     *     possible object is
     *     {@link RemoteTestCase }
     *     
     */
    public RemoteTestCase getRemoteTestCase() {
        return remoteTestCase;
    }

    /**
     * Sets the value of the remoteTestCase property.
     * 
     * @param value
     *     allowed object is
     *     {@link RemoteTestCase }
     *     
     */
    public void setRemoteTestCase(RemoteTestCase value) {
        this.remoteTestCase = value;
    }

    /**
     * Gets the value of the parentTestFolderId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getParentTestFolderId() {
        return parentTestFolderId;
    }

    /**
     * Sets the value of the parentTestFolderId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setParentTestFolderId(Integer value) {
        this.parentTestFolderId = value;
    }

}
