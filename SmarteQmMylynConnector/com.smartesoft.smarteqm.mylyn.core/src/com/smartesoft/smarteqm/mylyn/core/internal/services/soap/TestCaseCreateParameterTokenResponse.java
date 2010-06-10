
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
 *         &lt;element name="TestCase_CreateParameterTokenResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "testCaseCreateParameterTokenResult"
})
@XmlRootElement(name = "TestCase_CreateParameterTokenResponse")
public class TestCaseCreateParameterTokenResponse {

    @XmlElement(name = "TestCase_CreateParameterTokenResult")
    protected String testCaseCreateParameterTokenResult;

    /**
     * Gets the value of the testCaseCreateParameterTokenResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTestCaseCreateParameterTokenResult() {
        return testCaseCreateParameterTokenResult;
    }

    /**
     * Sets the value of the testCaseCreateParameterTokenResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTestCaseCreateParameterTokenResult(String value) {
        this.testCaseCreateParameterTokenResult = value;
    }

}
