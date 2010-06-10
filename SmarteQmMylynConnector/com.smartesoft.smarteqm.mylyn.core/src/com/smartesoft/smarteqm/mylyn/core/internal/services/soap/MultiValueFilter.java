
package com.smartesoft.smarteqm.mylyn.core.internal.services.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MultiValueFilter complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MultiValueFilter">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Values" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}ArrayOfInt" minOccurs="0"/>
 *         &lt;element name="IsNone" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MultiValueFilter", propOrder = {
    "values",
    "isNone"
})
public class MultiValueFilter {

    @XmlElement(name = "Values")
    protected ArrayOfInt values;
    @XmlElement(name = "IsNone")
    protected boolean isNone;

    /**
     * Gets the value of the values property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfInt }
     *     
     */
    public ArrayOfInt getValues() {
        return values;
    }

    /**
     * Sets the value of the values property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfInt }
     *     
     */
    public void setValues(ArrayOfInt value) {
        this.values = value;
    }

    /**
     * Gets the value of the isNone property.
     * 
     */
    public boolean isIsNone() {
        return isNone;
    }

    /**
     * Sets the value of the isNone property.
     * 
     */
    public void setIsNone(boolean value) {
        this.isNone = value;
    }

}
