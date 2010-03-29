
package com.inflectra.spirateam.mylyn.core.internal.services.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RemoteIncidentType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RemoteIncidentType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IncidentTypeId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Active" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Issue" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Risk" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RemoteIncidentType", propOrder = {
    "incidentTypeId",
    "name",
    "active",
    "issue",
    "risk"
})
public class RemoteIncidentType {

    @XmlElement(name = "IncidentTypeId", required = true, type = Integer.class, nillable = true)
    protected Integer incidentTypeId;
    @XmlElement(name = "Name")
    protected String name;
    @XmlElement(name = "Active")
    protected boolean active;
    @XmlElement(name = "Issue")
    protected boolean issue;
    @XmlElement(name = "Risk")
    protected boolean risk;

    /**
     * Gets the value of the incidentTypeId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIncidentTypeId() {
        return incidentTypeId;
    }

    /**
     * Sets the value of the incidentTypeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIncidentTypeId(Integer value) {
        this.incidentTypeId = value;
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
     * Gets the value of the issue property.
     * 
     */
    public boolean isIssue() {
        return issue;
    }

    /**
     * Sets the value of the issue property.
     * 
     */
    public void setIssue(boolean value) {
        this.issue = value;
    }

    /**
     * Gets the value of the risk property.
     * 
     */
    public boolean isRisk() {
        return risk;
    }

    /**
     * Sets the value of the risk property.
     * 
     */
    public void setRisk(boolean value) {
        this.risk = value;
    }

}
