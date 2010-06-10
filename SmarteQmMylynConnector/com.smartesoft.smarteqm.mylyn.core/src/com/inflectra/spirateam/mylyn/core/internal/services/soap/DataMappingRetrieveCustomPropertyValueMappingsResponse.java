
package com.inflectra.spirateam.mylyn.core.internal.services.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DataMapping_RetrieveCustomPropertyValueMappingsResult" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}ArrayOfRemoteDataMapping" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "dataMappingRetrieveCustomPropertyValueMappingsResult"
})
@XmlRootElement(name = "DataMapping_RetrieveCustomPropertyValueMappingsResponse")
public class DataMappingRetrieveCustomPropertyValueMappingsResponse {

    @XmlElement(name = "DataMapping_RetrieveCustomPropertyValueMappingsResult")
    protected ArrayOfRemoteDataMapping dataMappingRetrieveCustomPropertyValueMappingsResult;

    /**
     * Gets the value of the dataMappingRetrieveCustomPropertyValueMappingsResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfRemoteDataMapping }
     *     
     */
    public ArrayOfRemoteDataMapping getDataMappingRetrieveCustomPropertyValueMappingsResult() {
        return dataMappingRetrieveCustomPropertyValueMappingsResult;
    }

    /**
     * Sets the value of the dataMappingRetrieveCustomPropertyValueMappingsResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfRemoteDataMapping }
     *     
     */
    public void setDataMappingRetrieveCustomPropertyValueMappingsResult(ArrayOfRemoteDataMapping value) {
        this.dataMappingRetrieveCustomPropertyValueMappingsResult = value;
    }

}
