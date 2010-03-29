
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
 *         &lt;element name="TestSet_RetrieveByIdResult" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}RemoteTestSet" minOccurs="0"/>
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
    "testSetRetrieveByIdResult"
})
@XmlRootElement(name = "TestSet_RetrieveByIdResponse")
public class TestSetRetrieveByIdResponse {

    @XmlElement(name = "TestSet_RetrieveByIdResult")
    protected RemoteTestSet testSetRetrieveByIdResult;

    /**
     * Gets the value of the testSetRetrieveByIdResult property.
     * 
     * @return
     *     possible object is
     *     {@link RemoteTestSet }
     *     
     */
    public RemoteTestSet getTestSetRetrieveByIdResult() {
        return testSetRetrieveByIdResult;
    }

    /**
     * Sets the value of the testSetRetrieveByIdResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link RemoteTestSet }
     *     
     */
    public void setTestSetRetrieveByIdResult(RemoteTestSet value) {
        this.testSetRetrieveByIdResult = value;
    }

}
