
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
 *         &lt;element name="artifactTypeId" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "artifactTypeId"
})
@XmlRootElement(name = "CustomProperty_RetrieveProjectProperties")
public class CustomPropertyRetrieveProjectProperties {

    protected int artifactTypeId;

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

}
