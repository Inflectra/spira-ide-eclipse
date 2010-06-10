
package com.inflectra.spirateam.mylyn.core.internal.services.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RemoteWorkflowIncidentFields complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RemoteWorkflowIncidentFields">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FieldCaption" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FieldName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FieldID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="FieldStatus" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RemoteWorkflowIncidentFields", propOrder = {
    "fieldCaption",
    "fieldName",
    "fieldID",
    "fieldStatus"
})
public class RemoteWorkflowIncidentFields {

    @XmlElement(name = "FieldCaption")
    protected String fieldCaption;
    @XmlElement(name = "FieldName")
    protected String fieldName;
    @XmlElement(name = "FieldID")
    protected int fieldID;
    @XmlElement(name = "FieldStatus")
    protected int fieldStatus;

    /**
     * Gets the value of the fieldCaption property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFieldCaption() {
        return fieldCaption;
    }

    /**
     * Sets the value of the fieldCaption property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFieldCaption(String value) {
        this.fieldCaption = value;
    }

    /**
     * Gets the value of the fieldName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * Sets the value of the fieldName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFieldName(String value) {
        this.fieldName = value;
    }

    /**
     * Gets the value of the fieldID property.
     * 
     */
    public int getFieldID() {
        return fieldID;
    }

    /**
     * Sets the value of the fieldID property.
     * 
     */
    public void setFieldID(int value) {
        this.fieldID = value;
    }

    /**
     * Gets the value of the fieldStatus property.
     * 
     */
    public int getFieldStatus() {
        return fieldStatus;
    }

    /**
     * Sets the value of the fieldStatus property.
     * 
     */
    public void setFieldStatus(int value) {
        this.fieldStatus = value;
    }

}
