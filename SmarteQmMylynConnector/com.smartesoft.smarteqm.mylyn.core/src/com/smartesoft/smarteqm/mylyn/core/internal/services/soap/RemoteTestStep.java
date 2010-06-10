
package com.smartesoft.smarteqm.mylyn.core.internal.services.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RemoteTestStep complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RemoteTestStep">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.inflectra.com/SpiraTest/Services/v2.2/}RemoteArtifact">
 *       &lt;sequence>
 *         &lt;element name="TestStepId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="TestCaseId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ExecutionStatusId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Position" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ExpectedResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SampleData" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LinkedTestCaseId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RemoteTestStep", propOrder = {
    "testStepId",
    "testCaseId",
    "executionStatusId",
    "position",
    "description",
    "expectedResult",
    "sampleData",
    "linkedTestCaseId"
})
public class RemoteTestStep
    extends RemoteArtifact
{

    @XmlElement(name = "TestStepId", required = true, type = Integer.class, nillable = true)
    protected Integer testStepId;
    @XmlElement(name = "TestCaseId")
    protected int testCaseId;
    @XmlElement(name = "ExecutionStatusId")
    protected int executionStatusId;
    @XmlElement(name = "Position")
    protected int position;
    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "ExpectedResult")
    protected String expectedResult;
    @XmlElement(name = "SampleData")
    protected String sampleData;
    @XmlElement(name = "LinkedTestCaseId", required = true, type = Integer.class, nillable = true)
    protected Integer linkedTestCaseId;

    /**
     * Gets the value of the testStepId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTestStepId() {
        return testStepId;
    }

    /**
     * Sets the value of the testStepId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTestStepId(Integer value) {
        this.testStepId = value;
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

    /**
     * Gets the value of the executionStatusId property.
     * 
     */
    public int getExecutionStatusId() {
        return executionStatusId;
    }

    /**
     * Sets the value of the executionStatusId property.
     * 
     */
    public void setExecutionStatusId(int value) {
        this.executionStatusId = value;
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
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the expectedResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExpectedResult() {
        return expectedResult;
    }

    /**
     * Sets the value of the expectedResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExpectedResult(String value) {
        this.expectedResult = value;
    }

    /**
     * Gets the value of the sampleData property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSampleData() {
        return sampleData;
    }

    /**
     * Sets the value of the sampleData property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSampleData(String value) {
        this.sampleData = value;
    }

    /**
     * Gets the value of the linkedTestCaseId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getLinkedTestCaseId() {
        return linkedTestCaseId;
    }

    /**
     * Sets the value of the linkedTestCaseId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setLinkedTestCaseId(Integer value) {
        this.linkedTestCaseId = value;
    }

}
