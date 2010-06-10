
package com.smartesoft.smarteqm.mylyn.core.internal.services.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for RemoteRequirement complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RemoteRequirement">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.inflectra.com/SpiraTest/Services/v2.2/}RemoteArtifact">
 *       &lt;sequence>
 *         &lt;element name="RequirementId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ProjectId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="StatusId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="AuthorId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="OwnerId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ImportanceId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ReleaseId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CreationDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="LastUpdateDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="Summary" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="CoverageCountTotal" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="CoverageCountPassed" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="CoverageCountFailed" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="CoverageCountCaution" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="CoverageCountBlocked" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="PlannedEffort" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="TaskEstimatedEffort" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="TaskActualEffort" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="TaskCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RemoteRequirement", propOrder = {
    "requirementId",
    "projectId",
    "statusId",
    "authorId",
    "ownerId",
    "importanceId",
    "releaseId",
    "name",
    "description",
    "creationDate",
    "lastUpdateDate",
    "summary",
    "coverageCountTotal",
    "coverageCountPassed",
    "coverageCountFailed",
    "coverageCountCaution",
    "coverageCountBlocked",
    "plannedEffort",
    "taskEstimatedEffort",
    "taskActualEffort",
    "taskCount"
})
public class RemoteRequirement
    extends RemoteArtifact
{

    @XmlElement(name = "RequirementId", required = true, type = Integer.class, nillable = true)
    protected Integer requirementId;
    @XmlElement(name = "ProjectId")
    protected int projectId;
    @XmlElement(name = "StatusId")
    protected int statusId;
    @XmlElement(name = "AuthorId")
    protected int authorId;
    @XmlElement(name = "OwnerId", required = true, type = Integer.class, nillable = true)
    protected Integer ownerId;
    @XmlElement(name = "ImportanceId", required = true, type = Integer.class, nillable = true)
    protected Integer importanceId;
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
    @XmlElement(name = "Summary")
    protected boolean summary;
    @XmlElement(name = "CoverageCountTotal")
    protected int coverageCountTotal;
    @XmlElement(name = "CoverageCountPassed")
    protected int coverageCountPassed;
    @XmlElement(name = "CoverageCountFailed")
    protected int coverageCountFailed;
    @XmlElement(name = "CoverageCountCaution")
    protected int coverageCountCaution;
    @XmlElement(name = "CoverageCountBlocked")
    protected int coverageCountBlocked;
    @XmlElement(name = "PlannedEffort", required = true, type = Integer.class, nillable = true)
    protected Integer plannedEffort;
    @XmlElement(name = "TaskEstimatedEffort", required = true, type = Integer.class, nillable = true)
    protected Integer taskEstimatedEffort;
    @XmlElement(name = "TaskActualEffort", required = true, type = Integer.class, nillable = true)
    protected Integer taskActualEffort;
    @XmlElement(name = "TaskCount")
    protected int taskCount;

    /**
     * Gets the value of the requirementId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRequirementId() {
        return requirementId;
    }

    /**
     * Sets the value of the requirementId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRequirementId(Integer value) {
        this.requirementId = value;
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
     * Gets the value of the statusId property.
     * 
     */
    public int getStatusId() {
        return statusId;
    }

    /**
     * Sets the value of the statusId property.
     * 
     */
    public void setStatusId(int value) {
        this.statusId = value;
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
     * Gets the value of the importanceId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getImportanceId() {
        return importanceId;
    }

    /**
     * Sets the value of the importanceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setImportanceId(Integer value) {
        this.importanceId = value;
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
     * Gets the value of the coverageCountTotal property.
     * 
     */
    public int getCoverageCountTotal() {
        return coverageCountTotal;
    }

    /**
     * Sets the value of the coverageCountTotal property.
     * 
     */
    public void setCoverageCountTotal(int value) {
        this.coverageCountTotal = value;
    }

    /**
     * Gets the value of the coverageCountPassed property.
     * 
     */
    public int getCoverageCountPassed() {
        return coverageCountPassed;
    }

    /**
     * Sets the value of the coverageCountPassed property.
     * 
     */
    public void setCoverageCountPassed(int value) {
        this.coverageCountPassed = value;
    }

    /**
     * Gets the value of the coverageCountFailed property.
     * 
     */
    public int getCoverageCountFailed() {
        return coverageCountFailed;
    }

    /**
     * Sets the value of the coverageCountFailed property.
     * 
     */
    public void setCoverageCountFailed(int value) {
        this.coverageCountFailed = value;
    }

    /**
     * Gets the value of the coverageCountCaution property.
     * 
     */
    public int getCoverageCountCaution() {
        return coverageCountCaution;
    }

    /**
     * Sets the value of the coverageCountCaution property.
     * 
     */
    public void setCoverageCountCaution(int value) {
        this.coverageCountCaution = value;
    }

    /**
     * Gets the value of the coverageCountBlocked property.
     * 
     */
    public int getCoverageCountBlocked() {
        return coverageCountBlocked;
    }

    /**
     * Sets the value of the coverageCountBlocked property.
     * 
     */
    public void setCoverageCountBlocked(int value) {
        this.coverageCountBlocked = value;
    }

    /**
     * Gets the value of the plannedEffort property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPlannedEffort() {
        return plannedEffort;
    }

    /**
     * Sets the value of the plannedEffort property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPlannedEffort(Integer value) {
        this.plannedEffort = value;
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

}
