
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
 *         &lt;element name="TestRun_RetrieveResult" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}ArrayOfRemoteTestRun" minOccurs="0"/>
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
    "testRunRetrieveResult"
})
@XmlRootElement(name = "TestRun_RetrieveResponse")
public class TestRunRetrieveResponse {

    @XmlElement(name = "TestRun_RetrieveResult")
    protected ArrayOfRemoteTestRun testRunRetrieveResult;

    /**
     * Gets the value of the testRunRetrieveResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfRemoteTestRun }
     *     
     */
    public ArrayOfRemoteTestRun getTestRunRetrieveResult() {
        return testRunRetrieveResult;
    }

    /**
     * Sets the value of the testRunRetrieveResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfRemoteTestRun }
     *     
     */
    public void setTestRunRetrieveResult(ArrayOfRemoteTestRun value) {
        this.testRunRetrieveResult = value;
    }

}
