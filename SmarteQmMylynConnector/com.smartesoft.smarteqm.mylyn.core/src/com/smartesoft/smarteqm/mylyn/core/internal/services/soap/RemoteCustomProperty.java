
package com.smartesoft.smarteqm.mylyn.core.internal.services.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RemoteCustomProperty complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RemoteCustomProperty">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CustomPropertyId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ProjectId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ArtifactTypeId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Alias" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CustomPropertyListId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="CustomPropertyName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CustomPropertyTypeId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="CustomPropertyTypeName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RemoteCustomProperty", propOrder = {
    "customPropertyId",
    "projectId",
    "artifactTypeId",
    "alias",
    "customPropertyListId",
    "customPropertyName",
    "customPropertyTypeId",
    "customPropertyTypeName"
})
public class RemoteCustomProperty {

    @XmlElement(name = "CustomPropertyId")
    protected int customPropertyId;
    @XmlElement(name = "ProjectId")
    protected int projectId;
    @XmlElement(name = "ArtifactTypeId")
    protected int artifactTypeId;
    @XmlElement(name = "Alias")
    protected String alias;
    @XmlElement(name = "CustomPropertyListId", required = true, type = Integer.class, nillable = true)
    protected Integer customPropertyListId;
    @XmlElement(name = "CustomPropertyName")
    protected String customPropertyName;
    @XmlElement(name = "CustomPropertyTypeId", required = true, type = Integer.class, nillable = true)
    protected Integer customPropertyTypeId;
    @XmlElement(name = "CustomPropertyTypeName")
    protected String customPropertyTypeName;

    /**
     * Gets the value of the customPropertyId property.
     * 
     */
    public int getCustomPropertyId() {
        return customPropertyId;
    }

    /**
     * Sets the value of the customPropertyId property.
     * 
     */
    public void setCustomPropertyId(int value) {
        this.customPropertyId = value;
    }

    /**
     * Gets the value of the projectId property.
     * 
     */
    public int getProjectId() {
        return projectId;
    }

    /**
     * Sets the value of the projectId property.
     * 
     */
    public void setProjectId(int value) {
        this.projectId = value;
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
     * Gets the value of the alias property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Sets the value of the alias property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlias(String value) {
        this.alias = value;
    }

    /**
     * Gets the value of the customPropertyListId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCustomPropertyListId() {
        return customPropertyListId;
    }

    /**
     * Sets the value of the customPropertyListId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCustomPropertyListId(Integer value) {
        this.customPropertyListId = value;
    }

    /**
     * Gets the value of the customPropertyName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomPropertyName() {
        return customPropertyName;
    }

    /**
     * Sets the value of the customPropertyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomPropertyName(String value) {
        this.customPropertyName = value;
    }

    /**
     * Gets the value of the customPropertyTypeId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCustomPropertyTypeId() {
        return customPropertyTypeId;
    }

    /**
     * Sets the value of the customPropertyTypeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCustomPropertyTypeId(Integer value) {
        this.customPropertyTypeId = value;
    }

    /**
     * Gets the value of the customPropertyTypeName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomPropertyTypeName() {
        return customPropertyTypeName;
    }

    /**
     * Sets the value of the customPropertyTypeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomPropertyTypeName(String value) {
        this.customPropertyTypeName = value;
    }

}
