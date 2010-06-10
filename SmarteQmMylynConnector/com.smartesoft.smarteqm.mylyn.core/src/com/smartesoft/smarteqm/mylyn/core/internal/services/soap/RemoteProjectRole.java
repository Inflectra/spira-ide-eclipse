
package com.smartesoft.smarteqm.mylyn.core.internal.services.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RemoteProjectRole complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RemoteProjectRole">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ProjectRoleId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Active" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Admin" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="DocumentsAdd" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="DocumentsEdit" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="DocumentsDelete" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="DiscussionsAdd" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="SourceCodeView" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RemoteProjectRole", propOrder = {
    "projectRoleId",
    "name",
    "description",
    "active",
    "admin",
    "documentsAdd",
    "documentsEdit",
    "documentsDelete",
    "discussionsAdd",
    "sourceCodeView"
})
public class RemoteProjectRole {

    @XmlElement(name = "ProjectRoleId", required = true, type = Integer.class, nillable = true)
    protected Integer projectRoleId;
    @XmlElement(name = "Name")
    protected String name;
    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "Active")
    protected boolean active;
    @XmlElement(name = "Admin")
    protected boolean admin;
    @XmlElement(name = "DocumentsAdd")
    protected boolean documentsAdd;
    @XmlElement(name = "DocumentsEdit")
    protected boolean documentsEdit;
    @XmlElement(name = "DocumentsDelete")
    protected boolean documentsDelete;
    @XmlElement(name = "DiscussionsAdd")
    protected boolean discussionsAdd;
    @XmlElement(name = "SourceCodeView")
    protected boolean sourceCodeView;

    /**
     * Gets the value of the projectRoleId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getProjectRoleId() {
        return projectRoleId;
    }

    /**
     * Sets the value of the projectRoleId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setProjectRoleId(Integer value) {
        this.projectRoleId = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the active property.
     * 
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Sets the value of the active property.
     * 
     */
    public void setActive(boolean value) {
        this.active = value;
    }

    /**
     * Gets the value of the admin property.
     * 
     */
    public boolean isAdmin() {
        return admin;
    }

    /**
     * Sets the value of the admin property.
     * 
     */
    public void setAdmin(boolean value) {
        this.admin = value;
    }

    /**
     * Gets the value of the documentsAdd property.
     * 
     */
    public boolean isDocumentsAdd() {
        return documentsAdd;
    }

    /**
     * Sets the value of the documentsAdd property.
     * 
     */
    public void setDocumentsAdd(boolean value) {
        this.documentsAdd = value;
    }

    /**
     * Gets the value of the documentsEdit property.
     * 
     */
    public boolean isDocumentsEdit() {
        return documentsEdit;
    }

    /**
     * Sets the value of the documentsEdit property.
     * 
     */
    public void setDocumentsEdit(boolean value) {
        this.documentsEdit = value;
    }

    /**
     * Gets the value of the documentsDelete property.
     * 
     */
    public boolean isDocumentsDelete() {
        return documentsDelete;
    }

    /**
     * Sets the value of the documentsDelete property.
     * 
     */
    public void setDocumentsDelete(boolean value) {
        this.documentsDelete = value;
    }

    /**
     * Gets the value of the discussionsAdd property.
     * 
     */
    public boolean isDiscussionsAdd() {
        return discussionsAdd;
    }

    /**
     * Sets the value of the discussionsAdd property.
     * 
     */
    public void setDiscussionsAdd(boolean value) {
        this.discussionsAdd = value;
    }

    /**
     * Gets the value of the sourceCodeView property.
     * 
     */
    public boolean isSourceCodeView() {
        return sourceCodeView;
    }

    /**
     * Sets the value of the sourceCodeView property.
     * 
     */
    public void setSourceCodeView(boolean value) {
        this.sourceCodeView = value;
    }

}
