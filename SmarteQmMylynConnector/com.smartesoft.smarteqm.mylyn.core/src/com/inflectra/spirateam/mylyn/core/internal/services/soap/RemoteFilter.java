
package com.inflectra.spirateam.mylyn.core.internal.services.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RemoteFilter complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RemoteFilter">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PropertyName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IntValue" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="StringValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MultiValue" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}MultiValueFilter" minOccurs="0"/>
 *         &lt;element name="DateRangeValue" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}DateRange" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RemoteFilter", propOrder = {
    "propertyName",
    "intValue",
    "stringValue",
    "multiValue",
    "dateRangeValue"
})
public class RemoteFilter {

    @XmlElement(name = "PropertyName")
    protected String propertyName;
    @XmlElement(name = "IntValue", required = true, type = Integer.class, nillable = true)
    protected Integer intValue;
    @XmlElement(name = "StringValue")
    protected String stringValue;
    @XmlElement(name = "MultiValue")
    protected MultiValueFilter multiValue;
    @XmlElement(name = "DateRangeValue")
    protected DateRange dateRangeValue;

    /**
     * Gets the value of the propertyName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPropertyName() {
        return propertyName;
    }

    /**
     * Sets the value of the propertyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPropertyName(String value) {
        this.propertyName = value;
    }

    /**
     * Gets the value of the intValue property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIntValue() {
        return intValue;
    }

    /**
     * Sets the value of the intValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIntValue(Integer value) {
        this.intValue = value;
    }

    /**
     * Gets the value of the stringValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStringValue() {
        return stringValue;
    }

    /**
     * Sets the value of the stringValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStringValue(String value) {
        this.stringValue = value;
    }

    /**
     * Gets the value of the multiValue property.
     * 
     * @return
     *     possible object is
     *     {@link MultiValueFilter }
     *     
     */
    public MultiValueFilter getMultiValue() {
        return multiValue;
    }

    /**
     * Sets the value of the multiValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link MultiValueFilter }
     *     
     */
    public void setMultiValue(MultiValueFilter value) {
        this.multiValue = value;
    }

    /**
     * Gets the value of the dateRangeValue property.
     * 
     * @return
     *     possible object is
     *     {@link DateRange }
     *     
     */
    public DateRange getDateRangeValue() {
        return dateRangeValue;
    }

    /**
     * Sets the value of the dateRangeValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link DateRange }
     *     
     */
    public void setDateRangeValue(DateRange value) {
        this.dateRangeValue = value;
    }

}
