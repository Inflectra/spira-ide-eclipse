
package com.smartesoft.smarteqm.mylyn.core.internal.services.soap;

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
 *         &lt;element name="remoteTestCaseParameter" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}RemoteTestCaseParameter" minOccurs="0"/>
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
    "remoteTestCaseParameter"
})
@XmlRootElement(name = "TestCase_AddParameter")
public class TestCaseAddParameter {

    protected RemoteTestCaseParameter remoteTestCaseParameter;

    /**
     * Gets the value of the remoteTestCaseParameter property.
     * 
     * @return
     *     possible object is
     *     {@link RemoteTestCaseParameter }
     *     
     */
    public RemoteTestCaseParameter getRemoteTestCaseParameter() {
        return remoteTestCaseParameter;
    }

    /**
     * Sets the value of the remoteTestCaseParameter property.
     * 
     * @param value
     *     allowed object is
     *     {@link RemoteTestCaseParameter }
     *     
     */
    public void setRemoteTestCaseParameter(RemoteTestCaseParameter value) {
        this.remoteTestCaseParameter = value;
    }

}
