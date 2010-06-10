
package com.smartesoft.smarteqm.mylyn.core.internal.services.soap;

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
 *         &lt;element name="DataMapping_RetrieveFieldValueMappingsResult" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}ArrayOfRemoteDataMapping" minOccurs="0"/>
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
    "dataMappingRetrieveFieldValueMappingsResult"
})
@XmlRootElement(name = "DataMapping_RetrieveFieldValueMappingsResponse")
public class DataMappingRetrieveFieldValueMappingsResponse {

    @XmlElement(name = "DataMapping_RetrieveFieldValueMappingsResult")
    protected ArrayOfRemoteDataMapping dataMappingRetrieveFieldValueMappingsResult;

    /**
     * Gets the value of the dataMappingRetrieveFieldValueMappingsResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfRemoteDataMapping }
     *     
     */
    public ArrayOfRemoteDataMapping getDataMappingRetrieveFieldValueMappingsResult() {
        return dataMappingRetrieveFieldValueMappingsResult;
    }

    /**
     * Sets the value of the dataMappingRetrieveFieldValueMappingsResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfRemoteDataMapping }
     *     
     */
    public void setDataMappingRetrieveFieldValueMappingsResult(ArrayOfRemoteDataMapping value) {
        this.dataMappingRetrieveFieldValueMappingsResult = value;
    }

}
