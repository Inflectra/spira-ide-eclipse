
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
 *         &lt;element name="TestSet_RetrieveForOwnerResult" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}ArrayOfRemoteTestSet" minOccurs="0"/>
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
    "testSetRetrieveForOwnerResult"
})
@XmlRootElement(name = "TestSet_RetrieveForOwnerResponse")
public class TestSetRetrieveForOwnerResponse {

    @XmlElement(name = "TestSet_RetrieveForOwnerResult")
    protected ArrayOfRemoteTestSet testSetRetrieveForOwnerResult;

    /**
     * Gets the value of the testSetRetrieveForOwnerResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfRemoteTestSet }
     *     
     */
    public ArrayOfRemoteTestSet getTestSetRetrieveForOwnerResult() {
        return testSetRetrieveForOwnerResult;
    }

    /**
     * Sets the value of the testSetRetrieveForOwnerResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfRemoteTestSet }
     *     
     */
    public void setTestSetRetrieveForOwnerResult(ArrayOfRemoteTestSet value) {
        this.testSetRetrieveForOwnerResult = value;
    }

}
