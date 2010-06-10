
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
 *         &lt;element name="TestStep_RetrieveByTestCaseIdResult" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}ArrayOfRemoteTestStep" minOccurs="0"/>
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
    "testStepRetrieveByTestCaseIdResult"
})
@XmlRootElement(name = "TestStep_RetrieveByTestCaseIdResponse")
public class TestStepRetrieveByTestCaseIdResponse {

    @XmlElement(name = "TestStep_RetrieveByTestCaseIdResult")
    protected ArrayOfRemoteTestStep testStepRetrieveByTestCaseIdResult;

    /**
     * Gets the value of the testStepRetrieveByTestCaseIdResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfRemoteTestStep }
     *     
     */
    public ArrayOfRemoteTestStep getTestStepRetrieveByTestCaseIdResult() {
        return testStepRetrieveByTestCaseIdResult;
    }

    /**
     * Sets the value of the testStepRetrieveByTestCaseIdResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfRemoteTestStep }
     *     
     */
    public void setTestStepRetrieveByTestCaseIdResult(ArrayOfRemoteTestStep value) {
        this.testStepRetrieveByTestCaseIdResult = value;
    }

}
