
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
 *         &lt;element name="TestRun_RetrieveByIdResult" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}RemoteTestRun" minOccurs="0"/>
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
    "testRunRetrieveByIdResult"
})
@XmlRootElement(name = "TestRun_RetrieveByIdResponse")
public class TestRunRetrieveByIdResponse {

    @XmlElement(name = "TestRun_RetrieveByIdResult")
    protected RemoteTestRun testRunRetrieveByIdResult;

    /**
     * Gets the value of the testRunRetrieveByIdResult property.
     * 
     * @return
     *     possible object is
     *     {@link RemoteTestRun }
     *     
     */
    public RemoteTestRun getTestRunRetrieveByIdResult() {
        return testRunRetrieveByIdResult;
    }

    /**
     * Sets the value of the testRunRetrieveByIdResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link RemoteTestRun }
     *     
     */
    public void setTestRunRetrieveByIdResult(RemoteTestRun value) {
        this.testRunRetrieveByIdResult = value;
    }

}
