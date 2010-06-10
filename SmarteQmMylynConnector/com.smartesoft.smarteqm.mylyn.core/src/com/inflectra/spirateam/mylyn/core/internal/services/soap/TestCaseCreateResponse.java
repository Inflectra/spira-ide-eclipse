
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
 *         &lt;element name="TestCase_CreateResult" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}RemoteTestCase" minOccurs="0"/>
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
    "testCaseCreateResult"
})
@XmlRootElement(name = "TestCase_CreateResponse")
public class TestCaseCreateResponse {

    @XmlElement(name = "TestCase_CreateResult")
    protected RemoteTestCase testCaseCreateResult;

    /**
     * Gets the value of the testCaseCreateResult property.
     * 
     * @return
     *     possible object is
     *     {@link RemoteTestCase }
     *     
     */
    public RemoteTestCase getTestCaseCreateResult() {
        return testCaseCreateResult;
    }

    /**
     * Sets the value of the testCaseCreateResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link RemoteTestCase }
     *     
     */
    public void setTestCaseCreateResult(RemoteTestCase value) {
        this.testCaseCreateResult = value;
    }

}
