
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
 *         &lt;element name="remoteTestSetTestCaseMapping" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}RemoteTestSetTestCaseMapping" minOccurs="0"/>
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
    "remoteTestSetTestCaseMapping"
})
@XmlRootElement(name = "TestSet_AddTestMapping")
public class TestSetAddTestMapping {

    protected RemoteTestSetTestCaseMapping remoteTestSetTestCaseMapping;

    /**
     * Gets the value of the remoteTestSetTestCaseMapping property.
     * 
     * @return
     *     possible object is
     *     {@link RemoteTestSetTestCaseMapping }
     *     
     */
    public RemoteTestSetTestCaseMapping getRemoteTestSetTestCaseMapping() {
        return remoteTestSetTestCaseMapping;
    }

    /**
     * Sets the value of the remoteTestSetTestCaseMapping property.
     * 
     * @param value
     *     allowed object is
     *     {@link RemoteTestSetTestCaseMapping }
     *     
     */
    public void setRemoteTestSetTestCaseMapping(RemoteTestSetTestCaseMapping value) {
        this.remoteTestSetTestCaseMapping = value;
    }

}
