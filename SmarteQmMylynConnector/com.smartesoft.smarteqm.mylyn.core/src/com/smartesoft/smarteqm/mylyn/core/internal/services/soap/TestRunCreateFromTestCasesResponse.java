
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
 *         &lt;element name="TestRun_CreateFromTestCasesResult" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}RemoteTestRun" minOccurs="0"/>
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
    "testRunCreateFromTestCasesResult"
})
@XmlRootElement(name = "TestRun_CreateFromTestCasesResponse")
public class TestRunCreateFromTestCasesResponse {

    @XmlElement(name = "TestRun_CreateFromTestCasesResult")
    protected RemoteTestRun testRunCreateFromTestCasesResult;

    /**
     * Gets the value of the testRunCreateFromTestCasesResult property.
     * 
     * @return
     *     possible object is
     *     {@link RemoteTestRun }
     *     
     */
    public RemoteTestRun getTestRunCreateFromTestCasesResult() {
        return testRunCreateFromTestCasesResult;
    }

    /**
     * Sets the value of the testRunCreateFromTestCasesResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link RemoteTestRun }
     *     
     */
    public void setTestRunCreateFromTestCasesResult(RemoteTestRun value) {
        this.testRunCreateFromTestCasesResult = value;
    }

}
