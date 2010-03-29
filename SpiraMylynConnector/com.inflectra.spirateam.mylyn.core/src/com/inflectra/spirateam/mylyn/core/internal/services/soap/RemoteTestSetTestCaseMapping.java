
package com.inflectra.spirateam.mylyn.core.internal.services.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RemoteTestSetTestCaseMapping complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RemoteTestSetTestCaseMapping">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TestSetId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="TestCaseId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RemoteTestSetTestCaseMapping", propOrder = {
    "testSetId",
    "testCaseId"
})
public class RemoteTestSetTestCaseMapping {

    @XmlElement(name = "TestSetId")
    protected int testSetId;
    @XmlElement(name = "TestCaseId")
    protected int testCaseId;

    /**
     * Gets the value of the testSetId property.
     * 
     */
    public int getTestSetId() {
        return testSetId;
    }

    /**
     * Sets the value of the testSetId property.
     * 
     */
    public void setTestSetId(int value) {
        this.testSetId = value;
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
