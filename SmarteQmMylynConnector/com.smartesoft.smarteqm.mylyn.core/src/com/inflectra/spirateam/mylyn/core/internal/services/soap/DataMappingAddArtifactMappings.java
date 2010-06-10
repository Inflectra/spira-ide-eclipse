
package com.inflectra.spirateam.mylyn.core.internal.services.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="dataSyncSystemId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="artifactTypeId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="remoteDataMappings" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}ArrayOfRemoteDataMapping" minOccurs="0"/>
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
    "dataSyncSystemId",
    "artifactTypeId",
    "remoteDataMappings"
})
@XmlRootElement(name = "DataMapping_AddArtifactMappings")
public class DataMappingAddArtifactMappings {

    protected int dataSyncSystemId;
    protected int artifactTypeId;
    protected ArrayOfRemoteDataMapping remoteDataMappings;

    /**
     * Gets the value of the dataSyncSystemId property.
     * 
     */
    public int getDataSyncSystemId() {
        return dataSyncSystemId;
    }

    /**
     * Sets the value of the dataSyncSystemId property.
     * 
     */
    public void setDataSyncSystemId(int value) {
        this.dataSyncSystemId = value;
    }

    /**
     * Gets the value of the artifactTypeId property.
     * 
     */
    public int getArtifactTypeId() {
        return artifactTypeId;
    }

    /**
     * Sets the value of the artifactTypeId property.
     * 
     */
    public void setArtifactTypeId(int value) {
        this.artifactTypeId = value;
    }

    /**
     * Gets the value of the remoteDataMappings property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfRemoteDataMapping }
     *     
     */
    public ArrayOfRemoteDataMapping getRemoteDataMappings() {
        return remoteDataMappings;
    }

    /**
     * Sets the value of the remoteDataMappings property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfRemoteDataMapping }
     *     
     */
    public void setRemoteDataMappings(ArrayOfRemoteDataMapping value) {
        this.remoteDataMappings = value;
    }

}
