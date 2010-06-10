
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
 *         &lt;element name="TestRun_RecordAutomated1Result" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}RemoteTestRun" minOccurs="0"/>
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
    "testRunRecordAutomated1Result"
})
@XmlRootElement(name = "TestRun_RecordAutomated1Response")
public class TestRunRecordAutomated1Response {

    @XmlElement(name = "TestRun_RecordAutomated1Result")
    protected RemoteTestRun testRunRecordAutomated1Result;

    /**
     * Gets the value of the testRunRecordAutomated1Result property.
     * 
     * @return
     *     possible object is
     *     {@link RemoteTestRun }
     *     
     */
    public RemoteTestRun getTestRunRecordAutomated1Result() {
        return testRunRecordAutomated1Result;
    }

    /**
     * Sets the value of the testRunRecordAutomated1Result property.
     * 
     * @param value
     *     allowed object is
     *     {@link RemoteTestRun }
     *     
     */
    public void setTestRunRecordAutomated1Result(RemoteTestRun value) {
        this.testRunRecordAutomated1Result = value;
    }

}
