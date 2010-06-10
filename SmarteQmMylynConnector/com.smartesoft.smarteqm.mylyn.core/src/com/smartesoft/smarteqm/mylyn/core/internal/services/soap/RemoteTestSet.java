
package com.smartesoft.smarteqm.mylyn.core.internal.services.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for RemoteTestSet complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RemoteTestSet">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.inflectra.com/SpiraTest/Services/v2.2/}RemoteArtifact">
 *       &lt;sequence>
 *         &lt;element name="TestSetId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ProjectId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="TestSetStatusId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="CreatorId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="OwnerId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ReleaseId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CreationDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="LastUpdateDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="PlannedDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="ExecutionDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="Folder" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="CountPassed" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="CountFailed" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="CountCaution" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="CountBlocked" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="CountNotRun" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="CountNotApplicable" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RemoteTestSet", propOrder = {
    "testSetId",
    "projectId",
    "testSetStatusId",
    "creatorId",
    "ownerId",
    "releaseId",
    "name",
    "description",
    "creationDate",
    "lastUpdateDate",
    "plannedDate",
    "executionDate",
    "folder",
    "countPassed",
    "countFailed",
    "countCaution",
    "countBlocked",
    "countNotRun",
    "countNotApplicable"
})
public class RemoteTestSet
    extends RemoteArtifact
{

    @XmlElement(name = "TestSetId", required = true, type = Integer.class, nillable = true)
    protected Integer testSetId;
    @XmlElement(name = "ProjectId")
    protected int projectId;
    @XmlElement(name = "TestSetStatusId")
    protected int testSetStatusId;
    @XmlElement(name = "CreatorId")
    protected int creatorId;
    @XmlElement(name = "OwnerId", required = true, type = Integer.class, nillable = true)
    protected Integer ownerId;
    @XmlElement(name = "ReleaseId", required = true, type = Integer.class, nillable = true)
    protected Integer releaseId;
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
    @XmlElement(name = "PlannedDate", required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar plannedDate;
    @XmlElement(name = "ExecutionDate", required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar executionDate;
    @XmlElement(name = "Folder")
    protected boolean folder;
    @XmlElement(name = "CountPassed", required = true, type = Integer.class, nillable = true)
    protected Integer countPassed;
    @XmlElement(name = "CountFailed", required = true, type = Integer.class, nillable = true)
    protected Integer countFailed;
    @XmlElement(name = "CountCaution", required = true, type = Integer.class, nillable = true)
    protected Integer countCaution;
    @XmlElement(name = "CountBlocked", required = true, type = Integer.class, nillable = true)
    protected Integer countBlocked;
    @XmlElement(name = "CountNotRun", required = true, type = Integer.class, nillable = true)
    protected Integer countNotRun;
    @XmlElement(name = "CountNotApplicable", required = true, type = Integer.class, nillable = true)
    protected Integer countNotApplicable;

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
     * Gets the value of the testSetStatusId property.
     * 
     */
    public int getTestSetStatusId() {
        return testSetStatusId;
    }

    /**
     * Sets the value of the testSetStatusId property.
     * 
     */
    public void setTestSetStatusId(int value) {
        this.testSetStatusId = value;
    }

    /**
     * Gets the value of the creatorId property.
     * 
     */
    public int getCreatorId() {
        return creatorId;
    }

    /**
     * Sets the value of the creatorId property.
     * 
     */
    public void setCreatorId(int value) {
        this.creatorId = value;
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
     * Gets the value of the plannedDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPlannedDate() {
        return plannedDate;
    }

    /**
     * Sets the value of the plannedDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPlannedDate(XMLGregorianCalendar value) {
        this.plannedDate = value;
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
     * Gets the value of the countPassed property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCountPassed() {
        return countPassed;
    }

    /**
     * Sets the value of the countPassed property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCountPassed(Integer value) {
        this.countPassed = value;
    }

    /**
     * Gets the value of the countFailed property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCountFailed() {
        return countFailed;
    }

    /**
     * Sets the value of the countFailed property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCountFailed(Integer value) {
        this.countFailed = value;
    }

    /**
     * Gets the value of the countCaution property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCountCaution() {
        return countCaution;
    }

    /**
     * Sets the value of the countCaution property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCountCaution(Integer value) {
        this.countCaution = value;
    }

    /**
     * Gets the value of the countBlocked property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCountBlocked() {
        return countBlocked;
    }

    /**
     * Sets the value of the countBlocked property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCountBlocked(Integer value) {
        this.countBlocked = value;
    }

    /**
     * Gets the value of the countNotRun property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCountNotRun() {
        return countNotRun;
    }

    /**
     * Sets the value of the countNotRun property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCountNotRun(Integer value) {
        this.countNotRun = value;
    }

    /**
     * Gets the value of the countNotApplicable property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCountNotApplicable() {
        return countNotApplicable;
    }

    /**
     * Sets the value of the countNotApplicable property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCountNotApplicable(Integer value) {
        this.countNotApplicable = value;
    }

}
