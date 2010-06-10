
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
 *         &lt;element name="remoteTestCase" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}RemoteTestCase" minOccurs="0"/>
 *         &lt;element name="remoteTestSteps" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}ArrayOfRemoteTestStep" minOccurs="0"/>
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
    "remoteTestCase",
    "remoteTestSteps"
})
@XmlRootElement(name = "TestCase_Update")
public class TestCaseUpdate {

    protected RemoteTestCase remoteTestCase;
    protected ArrayOfRemoteTestStep remoteTestSteps;

    /**
     * Gets the value of the remoteTestCase property.
     * 
     * @return
     *     possible object is
     *     {@link RemoteTestCase }
     *     
     */
    public RemoteTestCase getRemoteTestCase() {
        return remoteTestCase;
    }

    /**
     * Sets the value of the remoteTestCase property.
     * 
     * @param value
     *     allowed object is
     *     {@link RemoteTestCase }
     *     
     */
    public void setRemoteTestCase(RemoteTestCase value) {
        this.remoteTestCase = value;
    }

    /**
     * Gets the value of the remoteTestSteps property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfRemoteTestStep }
     *     
     */
    public ArrayOfRemoteTestStep getRemoteTestSteps() {
        return remoteTestSteps;
    }

    /**
     * Sets the value of the remoteTestSteps property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfRemoteTestStep }
     *     
     */
    public void setRemoteTestSteps(ArrayOfRemoteTestStep value) {
        this.remoteTestSteps = value;
    }

}
