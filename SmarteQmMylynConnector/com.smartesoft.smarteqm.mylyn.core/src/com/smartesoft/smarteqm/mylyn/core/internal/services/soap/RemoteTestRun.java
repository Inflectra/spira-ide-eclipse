
package com.smartesoft.smarteqm.mylyn.core.internal.services.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for RemoteTestRun complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RemoteTestRun">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.inflectra.com/SpiraTest/Services/v2.2/}RemoteArtifact">
 *       &lt;sequence>
 *         &lt;element name="TestRunId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TestCaseId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="TestRunTypeId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="TesterId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ExecutionStatusId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ReleaseId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="TestSetId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="StartDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="EndDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="RunnerName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RunnerTestName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RunnerAssertCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="RunnerMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RunnerStackTrace" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TestRunSteps" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}ArrayOfRemoteTestRunStep" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RemoteTestRun", propOrder = {
    "testRunId",
    "name",
    "testCaseId",
    "testRunTypeId",
    "testerId",
    "executionStatusId",
    "releaseId",
    "testSetId",
    "startDate",
    "endDate",
    "runnerName",
    "runnerTestName",
    "runnerAssertCount",
    "runnerMessage",
    "runnerStackTrace",
    "testRunSteps"
})
public class RemoteTestRun
    extends RemoteArtifact
{

    @XmlElement(name = "TestRunId", required = true, type = Integer.class, nillable = true)
    protected Integer testRunId;
    @XmlElement(name = "Name")
    protected String name;
    @XmlElement(name = "TestCaseId")
    protected int testCaseId;
    @XmlElement(name = "TestRunTypeId")
    protected int testRunTypeId;
    @XmlElement(name = "TesterId")
    protected int testerId;
    @XmlElement(name = "ExecutionStatusId")
    protected int executionStatusId;
    @XmlElement(name = "ReleaseId", required = true, type = Integer.class, nillable = true)
    protected Integer releaseId;
    @XmlElement(name = "TestSetId", required = true, type = Integer.class, nillable = true)
    protected Integer testSetId;
    @XmlElement(name = "StartDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar startDate;
    @XmlElement(name = "EndDate", required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar endDate;
    @XmlElement(name = "RunnerName")
    protected String runnerName;
    @XmlElement(name = "RunnerTestName")
    protected String runnerTestName;
    @XmlElement(name = "RunnerAssertCount", required = true, type = Integer.class, nillable = true)
    protected Integer runnerAssertCount;
    @XmlElement(name = "RunnerMessage")
    protected String runnerMessage;
    @XmlElement(name = "RunnerStackTrace")
    protected String runnerStackTrace;
    @XmlElement(name = "TestRunSteps")
    protected ArrayOfRemoteTestRunStep testRunSteps;

    /**
     * Gets the value of the testRunId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTestRunId() {
        return testRunId;
    }

    /**
     * Sets the value of the testRunId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTestRunId(Integer value) {
        this.testRunId = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
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
     * Gets the value of the testRunTypeId property.
     * 
     */
    public int getTestRunTypeId() {
        return testRunTypeId;
    }

    /**
     * Sets the value of the testRunTypeId property.
     * 
     */
    public void setTestRunTypeId(int value) {
        this.testRunTypeId = value;
    }

    /**
     * Gets the value of the testerId property.
     * 
     */
    public int getTesterId() {
        return testerId;
    }

    /**
     * Sets the value of the testerId property.
     * 
     */
    public void setTesterId(int value) {
        this.testerId = value;
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
     * Gets the value of the releaseId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getReleaseId() {
        return releaseId;
    }

    /**
     * Sets the value of the releaseId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setReleaseId(Integer value) {
        this.releaseId = value;
    }

    /**
     * Gets the value of the testSetId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTestSetId() {
        return testSetId;
    }

    /**
     * Sets the value of the testSetId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTestSetId(Integer value) {
        this.testSetId = value;
    }

    /**
     * Gets the value of the startDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStartDate() {
        return startDate;
    }

    /**
     * Sets the value of the startDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStartDate(XMLGregorianCalendar value) {
        this.startDate = value;
    }

    /**
     * Gets the value of the endDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEndDate() {
        return endDate;
    }

    /**
     * Sets the value of the endDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEndDate(XMLGregorianCalendar value) {
        this.endDate = value;
    }

    /**
     * Gets the value of the runnerName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRunnerName() {
        return runnerName;
    }

    /**
     * Sets the value of the runnerName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRunnerName(String value) {
        this.runnerName = value;
    }

    /**
     * Gets the value of the runnerTestName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRunnerTestName() {
        return runnerTestName;
    }

    /**
     * Sets the value of the runnerTestName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRunnerTestName(String value) {
        this.runnerTestName = value;
    }

    /**
     * Gets the value of the runnerAssertCount property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRunnerAssertCount() {
        return runnerAssertCount;
    }

    /**
     * Sets the value of the runnerAssertCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRunnerAssertCount(Integer value) {
        this.runnerAssertCount = value;
    }

    /**
     * Gets the value of the runnerMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRunnerMessage() {
        return runnerMessage;
    }

    /**
     * Sets the value of the runnerMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRunnerMessage(String value) {
        this.runnerMessage = value;
    }

    /**
     * Gets the value of the runnerStackTrace property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRunnerStackTrace() {
        return runnerStackTrace;
    }

    /**
     * Sets the value of the runnerStackTrace property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRunnerStackTrace(String value) {
        this.runnerStackTrace = value;
    }

    /**
     * Gets the value of the testRunSteps property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfRemoteTestRunStep }
     *     
     */
    public ArrayOfRemoteTestRunStep getTestRunSteps() {
        return testRunSteps;
    }

    /**
     * Sets the value of the testRunSteps property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfRemoteTestRunStep }
     *     
     */
    public void setTestRunSteps(ArrayOfRemoteTestRunStep value) {
        this.testRunSteps = value;
    }

}
