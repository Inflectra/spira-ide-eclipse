
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
 *         &lt;element name="TestStep_CreateResult" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}RemoteTestStep" minOccurs="0"/>
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
    "testStepCreateResult"
})
@XmlRootElement(name = "TestStep_CreateResponse")
public class TestStepCreateResponse {

    @XmlElement(name = "TestStep_CreateResult")
    protected RemoteTestStep testStepCreateResult;

    /**
     * Gets the value of the testStepCreateResult property.
     * 
     * @return
     *     possible object is
     *     {@link RemoteTestStep }
     *     
     */
    public RemoteTestStep getTestStepCreateResult() {
        return testStepCreateResult;
    }

    /**
     * Sets the value of the testStepCreateResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link RemoteTestStep }
     *     
     */
    public void setTestStepCreateResult(RemoteTestStep value) {
        this.testStepCreateResult = value;
    }

}
