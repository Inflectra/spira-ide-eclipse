
package com.inflectra.spirateam.mylyn.core.internal.services.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RemoteCustomListValue complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RemoteCustomListValue">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CustomPropertyValueId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="CustomPropertyListId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Active" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RemoteCustomListValue", propOrder = {
    "customPropertyValueId",
    "customPropertyListId",
    "name",
    "active"
})
public class RemoteCustomListValue {

    @XmlElement(name = "CustomPropertyValueId", required = true, type = Integer.class, nillable = true)
    protected Integer customPropertyValueId;
    @XmlElement(name = "CustomPropertyListId")
    protected int customPropertyListId;
    @XmlElement(name = "Name")
    protected String name;
    @XmlElement(name = "Active")
    protected boolean active;

    /**
     * Gets the value of the customPropertyValueId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCustomPropertyValueId() {
        return customPropertyValueId;
    }

    /**
     * Sets the value of the customPropertyValueId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCustomPropertyValueId(Integer value) {
        this.customPropertyValueId = value;
    }

    /**
     * Gets the value of the customPropertyListId property.
     * 
     */
    public int getCustomPropertyListId() {
        return customPropertyListId;
    }

    /**
     * Sets the value of the customPropertyListId property.
     * 
     */
    public void setCustomPropertyListId(int value) {
        this.customPropertyListId = value;
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

}
