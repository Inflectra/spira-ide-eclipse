
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
 *         &lt;element name="remoteProject" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}RemoteProject" minOccurs="0"/>
 *         &lt;element name="existingProjectId" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "remoteProject",
    "existingProjectId"
})
@XmlRootElement(name = "Project_Create")
public class ProjectCreate {

    protected RemoteProject remoteProject;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer existingProjectId;

    /**
     * Gets the value of the remoteProject property.
     * 
     * @return
     *     possible object is
     *     {@link RemoteProject }
     *     
     */
    public RemoteProject getRemoteProject() {
        return remoteProject;
    }

    /**
     * Sets the value of the remoteProject property.
     * 
     * @param value
     *     allowed object is
     *     {@link RemoteProject }
     *     
     */
    public void setRemoteProject(RemoteProject value) {
        this.remoteProject = value;
    }

    /**
     * Gets the value of the existingProjectId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getExistingProjectId() {
        return existingProjectId;
    }

    /**
     * Sets the value of the existingProjectId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setExistingProjectId(Integer value) {
        this.existingProjectId = value;
    }

}
