
package com.inflectra.spirateam.mylyn.core.internal.services.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


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
 *         &lt;element name="userName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="projectId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="testerUserId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="testCaseId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="releaseId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="testSetId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="startDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="endDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="executionStatusId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="runnerName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="runnerTestName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="runnerAssertCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="runnerMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="runnerStackTrace" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "userName",
    "password",
    "projectId",
    "testerUserId",
    "testCaseId",
    "releaseId",
    "testSetId",
    "startDate",
    "endDate",
    "executionStatusId",
    "runnerName",
    "runnerTestName",
    "runnerAssertCount",
    "runnerMessage",
    "runnerStackTrace"
})
@XmlRootElement(name = "TestRun_RecordAutomated2")
public class TestRunRecordAutomated2 {

    protected String userName;
    protected String password;
    protected int projectId;
    protected int testerUserId;
    protected int testCaseId;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer releaseId;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer testSetId;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar startDate;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar endDate;
    protected int executionStatusId;
    protected String runnerName;
    protected String runnerTestName;
    protected int runnerAssertCount;
    protected String runnerMessage;
    protected String runnerStackTrace;

    /**
     * Gets the value of the userName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the value of the userName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserName(String value) {
        this.userName = value;
    }

    /**
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * Gets the value of the projectId property.
     * 
     */
    public int getProjectId() {
        return projectId;
    }

    /**
     * Sets the value of the projectId property.
     * 
     */
    public void setProjectId(int value) {
        this.projectId = value;
    }

    /**
     * Gets the value of the testerUserId property.
     * 
     */
    public int getTesterUserId() {
        return testerUserId;
    }

    /**
     * Sets the value of the testerUserId property.
     * 
     */
    public void setTesterUserId(int value) {
        this.testerUserId = value;
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
     */
    public int getRunnerAssertCount() {
        return runnerAssertCount;
    }

    /**
     * Sets the value of the runnerAssertCount property.
     * 
     */
    public void setRunnerAssertCount(int value) {
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

}
