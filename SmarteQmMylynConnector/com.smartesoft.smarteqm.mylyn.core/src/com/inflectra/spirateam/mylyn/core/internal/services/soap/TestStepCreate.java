
package com.inflectra.spirateam.mylyn.core.internal.services.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="remoteTestStep" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}RemoteTestStep" minOccurs="0"/>
 *         &lt;element name="testCaseId" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "remoteTestStep",
    "testCaseId"
})
@XmlRootElement(name = "TestStep_Create")
public class TestStepCreate {

    protected RemoteTestStep remoteTestStep;
    protected int testCaseId;

    /**
     * Gets the value of the remoteTestStep property.
     * 
     * @return
     *     possible object is
     *     {@link RemoteTestStep }
     *     
     */
    public RemoteTestStep getRemoteTestStep() {
        return remoteTestStep;
    }

    /**
     * Sets the value of the remoteTestStep property.
     * 
     * @param value
     *     allowed object is
     *     {@link RemoteTestStep }
     *     
     */
    public void setRemoteTestStep(RemoteTestStep value) {
        this.remoteTestStep = value;
    }

    /**
     * Gets the value of the testCaseId property.
     * 
     */
    public int getTestCaseId() {
        return testCaseId;
    }

    /**
     * Sets the value of the testCaseId property.
     * 
     */
    public void setTestCaseId(int value) {
        this.testCaseId = value;
    }

}
