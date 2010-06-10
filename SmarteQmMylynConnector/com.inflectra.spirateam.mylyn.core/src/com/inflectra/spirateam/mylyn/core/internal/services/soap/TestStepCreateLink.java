
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
 *         &lt;element name="testCaseId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="position" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="linkedTestCaseId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="parameters" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}ArrayOfRemoteTestStepParameter" minOccurs="0"/>
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
    "testCaseId",
    "position",
    "linkedTestCaseId",
    "parameters"
})
@XmlRootElement(name = "TestStep_CreateLink")
public class TestStepCreateLink {

    protected int testCaseId;
    protected int position;
    protected int linkedTestCaseId;
    protected ArrayOfRemoteTestStepParameter parameters;

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

    /**
     * Gets the value of the position property.
     * 
     */
    public int getPosition() {
        return position;
    }

    /**
     * Sets the value of the position property.
     * 
     */
    public void setPosition(int value) {
        this.position = value;
    }

    /**
     * Gets the value of the linkedTestCaseId property.
     * 
     */
    public int getLinkedTestCaseId() {
        return linkedTestCaseId;
    }

    /**
     * Sets the value of the linkedTestCaseId property.
     * 
     */
    public void setLinkedTestCaseId(int value) {
        this.linkedTestCaseId = value;
    }

    /**
     * Gets the value of the parameters property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfRemoteTestStepParameter }
     *     
     */
    public ArrayOfRemoteTestStepParameter getParameters() {
        return parameters;
    }

    /**
     * Sets the value of the parameters property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfRemoteTestStepParameter }
     *     
     */
    public void setParameters(ArrayOfRemoteTestStepParameter value) {
        this.parameters = value;
    }

}
