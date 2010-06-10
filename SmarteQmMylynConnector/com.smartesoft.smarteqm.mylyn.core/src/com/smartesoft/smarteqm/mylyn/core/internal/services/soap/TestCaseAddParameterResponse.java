
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
 *         &lt;element name="TestCase_AddParameterResult" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}RemoteTestCaseParameter" minOccurs="0"/>
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
    "testCaseAddParameterResult"
})
@XmlRootElement(name = "TestCase_AddParameterResponse")
public class TestCaseAddParameterResponse {

    @XmlElement(name = "TestCase_AddParameterResult")
    protected RemoteTestCaseParameter testCaseAddParameterResult;

    /**
     * Gets the value of the testCaseAddParameterResult property.
     * 
     * @return
     *     possible object is
     *     {@link RemoteTestCaseParameter }
     *     
     */
    public RemoteTestCaseParameter getTestCaseAddParameterResult() {
        return testCaseAddParameterResult;
    }

    /**
     * Sets the value of the testCaseAddParameterResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link RemoteTestCaseParameter }
     *     
     */
    public void setTestCaseAddParameterResult(RemoteTestCaseParameter value) {
        this.testCaseAddParameterResult = value;
    }

}
