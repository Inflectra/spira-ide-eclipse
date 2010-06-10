
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
 *         &lt;element name="artifactFieldId" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "artifactFieldId"
})
@XmlRootElement(name = "DataMapping_RetrieveFieldValueMappings")
public class DataMappingRetrieveFieldValueMappings {

    protected int dataSyncSystemId;
    protected int artifactFieldId;

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
     * Gets the value of the artifactFieldId property.
     * 
     */
    public int getArtifactFieldId() {
        return artifactFieldId;
    }

    /**
     * Sets the value of the artifactFieldId property.
     * 
     */
    public void setArtifactFieldId(int value) {
        this.artifactFieldId = value;
    }

}
