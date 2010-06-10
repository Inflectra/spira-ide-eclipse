
package com.inflectra.spirateam.mylyn.core.internal.services.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for RemoteRelease complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RemoteRelease">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.inflectra.com/SpiraTest/Services/v2.2/}RemoteArtifact">
 *       &lt;sequence>
 *         &lt;element name="ReleaseId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="CreatorId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ProjectId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="VersionNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CreationDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="LastUpdateDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="Summary" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Active" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Iteration" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="StartDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="EndDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="ResourceCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="DaysNonWorking" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="PlannedEffort" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="AvailableEffort" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="TaskEstimatedEffort" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="TaskActualEffort" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="TaskCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="CreatorName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RemoteRelease", propOrder = {
    "releaseId",
    "creatorId",
    "projectId",
    "name",
    "description",
    "versionNumber",
    "creationDate",
    "lastUpdateDate",
    "summary",
    "active",
    "iteration",
    "startDate",
    "endDate",
    "resourceCount",
    "daysNonWorking",
    "plannedEffort",
    "availableEffort",
    "taskEstimatedEffort",
    "taskActualEffort",
    "taskCount",
    "creatorName"
})
public class RemoteRelease
    extends RemoteArtifact
{

    @XmlElement(name = "ReleaseId", required = true, type = Integer.class, nillable = true)
    protected Integer releaseId;
    @XmlElement(name = "CreatorId")
    protected int creatorId;
    @XmlElement(name = "ProjectId")
    protected int projectId;
    @XmlElement(name = "Name")
    protected String name;
    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "VersionNumber")
    protected String versionNumber;
    @XmlElement(name = "CreationDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar creationDate;
    @XmlElement(name = "LastUpdateDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastUpdateDate;
    @XmlElement(name = "Summary")
    protected boolean summary;
    @XmlElement(name = "Active")
    protected boolean active;
    @XmlElement(name = "Iteration")
    protected boolean iteration;
    @XmlElement(name = "StartDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar startDate;
    @XmlElement(name = "EndDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar endDate;
    @XmlElement(name = "ResourceCount")
    protected int resourceCount;
    @XmlElement(name = "DaysNonWorking")
    protected int daysNonWorking;
    @XmlElement(name = "PlannedEffort")
    protected int plannedEffort;
    @XmlElement(name = "AvailableEffort")
    protected int availableEffort;
    @XmlElement(name = "TaskEstimatedEffort", required = true, type = Integer.class, nillable = true)
    protected Integer taskEstimatedEffort;
    @XmlElement(name = "TaskActualEffort", required = true, type = Integer.class, nillable = true)
    protected Integer taskActualEffort;
    @XmlElement(name = "TaskCount")
    protected int taskCount;
    @XmlElement(name = "CreatorName")
    protected String creatorName;

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
     * Gets the value of the versionNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersionNumber() {
        return versionNumber;
    }

    /**
     * Sets the value of the versionNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersionNumber(String value) {
        this.versionNumber = value;
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
     * Gets the value of the summary property.
     * 
     */
    public boolean isSummary() {
        return summary;
    }

    /**
     * Sets the value of the summary property.
     * 
     */
    public void setSummary(boolean value) {
        this.summary = value;
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

    /**
     * Gets the value of the iteration property.
     * 
     */
    public boolean isIteration() {
        return iteration;
    }

    /**
     * Sets the value of the iteration property.
     * 
     */
    public void setIteration(boolean value) {
        this.iteration = value;
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
     * Gets the value of the resourceCount property.
     * 
     */
    public int getResourceCount() {
        return resourceCount;
    }

    /**
     * Sets the value of the resourceCount property.
     * 
     */
    public void setResourceCount(int value) {
        this.resourceCount = value;
    }

    /**
     * Gets the value of the daysNonWorking property.
     * 
     */
    public int getDaysNonWorking() {
        return daysNonWorking;
    }

    /**
     * Sets the value of the daysNonWorking property.
     * 
     */
    public void setDaysNonWorking(int value) {
        this.daysNonWorking = value;
    }

    /**
     * Gets the value of the plannedEffort property.
     * 
     */
    public int getPlannedEffort() {
        return plannedEffort;
    }

    /**
     * Sets the value of the plannedEffort property.
     * 
     */
    public void setPlannedEffort(int value) {
        this.plannedEffort = value;
    }

    /**
     * Gets the value of the availableEffort property.
     * 
     */
    public int getAvailableEffort() {
        return availableEffort;
    }

    /**
     * Sets the value of the availableEffort property.
     * 
     */
    public void setAvailableEffort(int value) {
        this.availableEffort = value;
    }

    /**
     * Gets the value of the taskEstimatedEffort property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTaskEstimatedEffort() {
        return taskEstimatedEffort;
    }

    /**
     * Sets the value of the taskEstimatedEffort property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTaskEstimatedEffort(Integer value) {
        this.taskEstimatedEffort = value;
    }

    /**
     * Gets the value of the taskActualEffort property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTaskActualEffort() {
        return taskActualEffort;
    }

    /**
     * Sets the value of the taskActualEffort property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTaskActualEffort(Integer value) {
        this.taskActualEffort = value;
    }

    /**
     * Gets the value of the taskCount property.
     * 
     */
    public int getTaskCount() {
        return taskCount;
    }

    /**
     * Sets the value of the taskCount property.
     * 
     */
    public void setTaskCount(int value) {
        this.taskCount = value;
    }

    /**
     * Gets the value of the creatorName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreatorName() {
        return creatorName;
    }

    /**
     * Sets the value of the creatorName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreatorName(String value) {
        this.creatorName = value;
    }

}
