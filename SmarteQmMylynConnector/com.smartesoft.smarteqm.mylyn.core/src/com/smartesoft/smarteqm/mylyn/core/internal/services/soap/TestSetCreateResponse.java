
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
 *         &lt;element name="TestSet_CreateResult" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}RemoteTestSet" minOccurs="0"/>
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
    "testSetCreateResult"
})
@XmlRootElement(name = "TestSet_CreateResponse")
public class TestSetCreateResponse {

    @XmlElement(name = "TestSet_CreateResult")
    protected RemoteTestSet testSetCreateResult;

    /**
     * Gets the value of the testSetCreateResult property.
     * 
     * @return
     *     possible object is
     *     {@link RemoteTestSet }
     *     
     */
    public RemoteTestSet getTestSetCreateResult() {
        return testSetCreateResult;
    }

    /**
     * Sets the value of the testSetCreateResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link RemoteTestSet }
     *     
     */
    public void setTestSetCreateResult(RemoteTestSet value) {
        this.testSetCreateResult = value;
    }

}
