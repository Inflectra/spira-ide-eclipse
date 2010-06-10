
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
 *         &lt;element name="TestCase_RetrieveForOwnerResult" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}ArrayOfRemoteTestCase" minOccurs="0"/>
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
    "testCaseRetrieveForOwnerResult"
})
@XmlRootElement(name = "TestCase_RetrieveForOwnerResponse")
public class TestCaseRetrieveForOwnerResponse {

    @XmlElement(name = "TestCase_RetrieveForOwnerResult")
    protected ArrayOfRemoteTestCase testCaseRetrieveForOwnerResult;

    /**
     * Gets the value of the testCaseRetrieveForOwnerResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfRemoteTestCase }
     *     
     */
    public ArrayOfRemoteTestCase getTestCaseRetrieveForOwnerResult() {
        return testCaseRetrieveForOwnerResult;
    }

    /**
     * Sets the value of the testCaseRetrieveForOwnerResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfRemoteTestCase }
     *     
     */
    public void setTestCaseRetrieveForOwnerResult(ArrayOfRemoteTestCase value) {
        this.testCaseRetrieveForOwnerResult = value;
    }

}
