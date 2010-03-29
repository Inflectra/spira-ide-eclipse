
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
 *         &lt;element name="TestSet_RetrieveResult" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}ArrayOfRemoteTestSet" minOccurs="0"/>
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
    "testSetRetrieveResult"
})
@XmlRootElement(name = "TestSet_RetrieveResponse")
public class TestSetRetrieveResponse {

    @XmlElement(name = "TestSet_RetrieveResult")
    protected ArrayOfRemoteTestSet testSetRetrieveResult;

    /**
     * Gets the value of the testSetRetrieveResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfRemoteTestSet }
     *     
     */
    public ArrayOfRemoteTestSet getTestSetRetrieveResult() {
        return testSetRetrieveResult;
    }

    /**
     * Sets the value of the testSetRetrieveResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfRemoteTestSet }
     *     
     */
    public void setTestSetRetrieveResult(ArrayOfRemoteTestSet value) {
        this.testSetRetrieveResult = value;
    }

}
