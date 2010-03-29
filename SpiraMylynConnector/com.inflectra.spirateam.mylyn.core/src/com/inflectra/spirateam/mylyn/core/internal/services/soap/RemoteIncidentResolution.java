
package com.inflectra.spirateam.mylyn.core.internal.services.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for RemoteIncidentResolution complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RemoteIncidentResolution">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IncidentResolutionId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="IncidentId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="CreatorId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Resolution" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CreationDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="CreatorName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RemoteIncidentResolution", propOrder = {
    "incidentResolutionId",
    "incidentId",
    "creatorId",
    "resolution",
    "creationDate",
    "creatorName"
})
public class RemoteIncidentResolution {

    @XmlElement(name = "IncidentResolutionId", required = true, type = Integer.class, nillable = true)
    protected Integer incidentResolutionId;
    @XmlElement(name = "IncidentId")
    protected int incidentId;
    @XmlElement(name = "CreatorId")
    protected int creatorId;
    @XmlElement(name = "Resolution")
    protected String resolution;
    @XmlElement(name = "CreationDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar creationDate;
    @XmlElement(name = "CreatorName")
    protected String creatorName;

    /**
     * Gets the value of the incidentResolutionId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIncidentResolutionId() {
        return incidentResolutionId;
    }

    /**
     * Sets the value of the incidentResolutionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIncidentResolutionId(Integer value) {
        this.incidentResolutionId = value;
    }

    /**
     * Gets the value of the incidentId property.
     * 
     */
    public int getIncidentId() {
        return incidentId;
    }

    /**
     * Sets the value of the incidentId property.
     * 
     */
    public void setIncidentId(int value) {
        this.incidentId = value;
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
     * Gets the value of the resolution property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResolution() {
        return resolution;
    }

    /**
     * Sets the value of the resolution property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResolution(String value) {
        this.resolution = value;
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
