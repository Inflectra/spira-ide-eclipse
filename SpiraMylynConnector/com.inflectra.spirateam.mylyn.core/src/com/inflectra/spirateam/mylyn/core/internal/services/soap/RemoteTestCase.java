
package com.inflectra.spirateam.mylyn.core.internal.services.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for RemoteTestCase complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RemoteTestCase">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.inflectra.com/SpiraTest/Services/v2.2/}RemoteArtifact">
 *       &lt;sequence>
 *         &lt;element name="TestCaseId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ProjectId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ExecutionStatusId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="AuthorId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="OwnerId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="TestCasePriorityId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CreationDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="LastUpdateDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="ExecutionDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="EstimatedDuration" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Folder" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Active" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RemoteTestCase", propOrder = {
    "testCaseId",
    "projectId",
    "executionStatusId",
    "authorId",
    "ownerId",
    "testCasePriorityId",
    "name",
    "description",
    "creationDate",
    "lastUpdateDate",
    "executionDate",
    "estimatedDuration",
    "folder",
    "active"
})
public class RemoteTestCase
    extends RemoteArtifact
{

    @XmlElement(name = "TestCaseId", required = true, type = Integer.class, nillable = true)
    protected Integer testCaseId;
    @XmlElement(name = "ProjectId")
    protected int projectId;
    @XmlElement(name = "ExecutionStatusId")
    protected int executionStatusId;
    @XmlElement(name = "AuthorId")
    protected int authorId;
    @XmlElement(name = "OwnerId", required = true, type = Integer.class, nillable = true)
    protected Integer ownerId;
    @XmlElement(name = "TestCasePriorityId", required = true, type = Integer.class, nillable = true)
    protected Integer testCasePriorityId;
    @XmlElement(name = "Name")
    protected String name;
    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "CreationDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar creationDate;
    @XmlElement(name = "LastUpdateDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastUpdateDate;
    @XmlElement(name = "ExecutionDate", required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar executionDate;
    @XmlElement(name = "EstimatedDuration", required = true, type = Integer.class, nillable = true)
    protected Integer estimatedDuration;
    @XmlElement(name = "Folder")
    protected boolean folder;
    @XmlElement(name = "Active")
    protected boolean active;

    /**
     * Gets the value of the testCaseId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTestCaseId() {
        return testCaseId;
    }

    /**
     * Sets the value of the testCaseId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTestCaseId(Integer value) {
        this.testCaseId = value;
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
     * Gets the value of the authorId property.
     * 
     */
    public int getAuthorId() {
        return authorId;
    }

    /**
     * Sets the value of the authorId property.
     * 
     */
    public void setAuthorId(int value) {
        this.authorId = value;
    }

    /**
     * Gets the value of the ownerId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getOwnerId() {
        return ownerId;
    }

    /**
     * Sets the value of the ownerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setOwnerId(Integer value) {
        this.ownerId = value;
    }

    /**
     * Gets the value of the testCasePriorityId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTestCasePriorityId() {
        return testCasePriorityId;
    }

    /**
     * Sets the value of the testCasePriorityId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTestCasePriorityId(Integer value) {
        this.testCasePriorityId = value;
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
     * Gets the value of the creationDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreationDate() {
        return creationDate;
    }

    /**
     * Sets the value of the creationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreationDate(XMLGregorianCalendar value) {
        this.creationDate = value;
    }

    /**
     * Gets the value of the lastUpdateDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastUpdateDate() {
        return lastUpdateDate;
    }

    /**
     * Sets the value of the lastUpdateDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastUpdateDate(XMLGregorianCalendar value) {
        this.lastUpdateDate = value;
    }

    /**
     * Gets the value of the executionDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getExecutionDate() {
        return executionDate;
    }

    /**
     * Sets the value of the executionDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setExecutionDate(XMLGregorianCalendar value) {
        this.executionDate = value;
    }

    /**
     * Gets the value of the estimatedDuration property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getEstimatedDuration() {
        return estimatedDuration;
    }

    /**
     * Sets the value of the estimatedDuration property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setEstimatedDuration(Integer value) {
        this.estimatedDuration = value;
    }

    /**
     * Gets the value of the folder property.
     * 
     */
    public boolean isFolder() {
        return folder;
    }

    /**
     * Sets the value of the folder property.
     * 
     */
    public void setFolder(boolean value) {
        this.folder = value;
    }

    /**
     * Gets the value of the active property.
     * 
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Sets the value of the active property.
     * 
     */
    public void setActive(boolean value) {
        this.active = value;
    }

}
