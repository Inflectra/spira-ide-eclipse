
package com.inflectra.spirateam.mylyn.core.internal.services.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for RemoteProject complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RemoteProject">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ProjectId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Website" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CreationDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="Active" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="WorkingHours" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="WorkingDays" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="NonWorkingHours" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RemoteProject", propOrder = {
    "projectId",
    "name",
    "description",
    "website",
    "creationDate",
    "active",
    "workingHours",
    "workingDays",
    "nonWorkingHours"
})
public class RemoteProject {

    @XmlElement(name = "ProjectId", required = true, type = Integer.class, nillable = true)
    protected Integer projectId;
    @XmlElement(name = "Name")
    protected String name;
    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "Website")
    protected String website;
    @XmlElement(name = "CreationDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar creationDate;
    @XmlElement(name = "Active")
    protected boolean active;
    @XmlElement(name = "WorkingHours")
    protected int workingHours;
    @XmlElement(name = "WorkingDays")
    protected int workingDays;
    @XmlElement(name = "NonWorkingHours")
    protected int nonWorkingHours;

    /**
     * Gets the value of the projectId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getProjectId() {
        return projectId;
    }

    /**
     * Sets the value of the projectId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setProjectId(Integer value) {
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
     * Gets the value of the website property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWebsite() {
        return website;
    }

    /**
     * Sets the value of the website property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWebsite(String value) {
        this.website = value;
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
     * Gets the value of the workingHours property.
     * 
     */
    public int getWorkingHours() {
        return workingHours;
    }

    /**
     * Sets the value of the workingHours property.
     * 
     */
    public void setWorkingHours(int value) {
        this.workingHours = value;
    }

    /**
     * Gets the value of the workingDays property.
     * 
     */
    public int getWorkingDays() {
        return workingDays;
    }

    /**
     * Sets the value of the workingDays property.
     * 
     */
    public void setWorkingDays(int value) {
        this.workingDays = value;
    }

    /**
     * Gets the value of the nonWorkingHours property.
     * 
     */
    public int getNonWorkingHours() {
        return nonWorkingHours;
    }

    /**
     * Sets the value of the nonWorkingHours property.
     * 
     */
    public void setNonWorkingHours(int value) {
        this.nonWorkingHours = value;
    }

}
