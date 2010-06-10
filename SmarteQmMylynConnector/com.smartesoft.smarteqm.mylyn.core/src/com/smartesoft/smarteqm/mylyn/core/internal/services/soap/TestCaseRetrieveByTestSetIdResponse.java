
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
 *         &lt;element name="TestCase_RetrieveByTestSetIdResult" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}ArrayOfRemoteTestCase" minOccurs="0"/>
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
    "testCaseRetrieveByTestSetIdResult"
})
@XmlRootElement(name = "TestCase_RetrieveByTestSetIdResponse")
public class TestCaseRetrieveByTestSetIdResponse {

    @XmlElement(name = "TestCase_RetrieveByTestSetIdResult")
    protected ArrayOfRemoteTestCase testCaseRetrieveByTestSetIdResult;

    /**
     * Gets the value of the testCaseRetrieveByTestSetIdResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfRemoteTestCase }
     *     
     */
    public ArrayOfRemoteTestCase getTestCaseRetrieveByTestSetIdResult() {
        return testCaseRetrieveByTestSetIdResult;
    }

    /**
     * Sets the value of the testCaseRetrieveByTestSetIdResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfRemoteTestCase }
     *     
     */
    public void setTestCaseRetrieveByTestSetIdResult(ArrayOfRemoteTestCase value) {
        this.testCaseRetrieveByTestSetIdResult = value;
    }

}
