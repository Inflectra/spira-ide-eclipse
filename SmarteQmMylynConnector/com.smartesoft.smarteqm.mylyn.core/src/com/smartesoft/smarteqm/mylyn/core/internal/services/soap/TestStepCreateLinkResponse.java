
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
 *         &lt;element name="TestStep_CreateLinkResult" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "testStepCreateLinkResult"
})
@XmlRootElement(name = "TestStep_CreateLinkResponse")
public class TestStepCreateLinkResponse {

    @XmlElement(name = "TestStep_CreateLinkResult")
    protected int testStepCreateLinkResult;

    /**
     * Gets the value of the testStepCreateLinkResult property.
     * 
     */
    public int getTestStepCreateLinkResult() {
        return testStepCreateLinkResult;
    }

    /**
     * Sets the value of the testStepCreateLinkResult property.
     * 
     */
    public void setTestStepCreateLinkResult(int value) {
        this.testStepCreateLinkResult = value;
    }

}
