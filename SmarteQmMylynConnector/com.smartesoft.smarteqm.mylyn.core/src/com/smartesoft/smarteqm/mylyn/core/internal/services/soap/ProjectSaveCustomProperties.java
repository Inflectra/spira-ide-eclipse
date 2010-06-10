
package com.smartesoft.smarteqm.mylyn.core.internal.services.soap;

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
 *         &lt;element name="remoteProjectCustomProperties" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}ArrayOfRemoteProjectCustomProperty" minOccurs="0"/>
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
    "artifactTypeId",
    "remoteProjectCustomProperties"
})
@XmlRootElement(name = "Project_SaveCustomProperties")
public class ProjectSaveCustomProperties {

    protected int artifactTypeId;
    protected ArrayOfRemoteProjectCustomProperty remoteProjectCustomProperties;

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
     * Gets the value of the remoteProjectCustomProperties property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfRemoteProjectCustomProperty }
     *     
     */
    public ArrayOfRemoteProjectCustomProperty getRemoteProjectCustomProperties() {
        return remoteProjectCustomProperties;
    }

    /**
     * Sets the value of the remoteProjectCustomProperties property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfRemoteProjectCustomProperty }
     *     
     */
    public void setRemoteProjectCustomProperties(ArrayOfRemoteProjectCustomProperty value) {
        this.remoteProjectCustomProperties = value;
    }

}
