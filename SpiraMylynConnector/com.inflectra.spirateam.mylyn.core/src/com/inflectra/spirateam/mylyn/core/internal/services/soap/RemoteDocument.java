
package com.inflectra.spirateam.mylyn.core.internal.services.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for RemoteDocument complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RemoteDocument">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AttachmentId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="AttachmentTypeId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ProjectAttachmentTypeId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ProjectAttachmentFolderId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ArtifactTypeId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ArtifactId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="AuthorId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="EditorId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="FilenameOrUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="UploadDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="EditedDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="Size" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="CurrentVersion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Tags" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RemoteDocument", propOrder = {
    "attachmentId",
    "attachmentTypeId",
    "projectAttachmentTypeId",
    "projectAttachmentFolderId",
    "artifactTypeId",
    "artifactId",
    "authorId",
    "editorId",
    "filenameOrUrl",
    "description",
    "uploadDate",
    "editedDate",
    "size",
    "currentVersion",
    "tags"
})
public class RemoteDocument {

    @XmlElement(name = "AttachmentId", required = true, type = Integer.class, nillable = true)
    protected Integer attachmentId;
    @XmlElement(name = "AttachmentTypeId")
    protected int attachmentTypeId;
    @XmlElement(name = "ProjectAttachmentTypeId", required = true, type = Integer.class, nillable = true)
    protected Integer projectAttachmentTypeId;
    @XmlElement(name = "ProjectAttachmentFolderId", required = true, type = Integer.class, nillable = true)
    protected Integer projectAttachmentFolderId;
    @XmlElement(name = "ArtifactTypeId", required = true, type = Integer.class, nillable = true)
    protected Integer artifactTypeId;
    @XmlElement(name = "ArtifactId", required = true, type = Integer.class, nillable = true)
    protected Integer artifactId;
    @XmlElement(name = "AuthorId")
    protected int authorId;
    @XmlElement(name = "EditorId")
    protected int editorId;
    @XmlElement(name = "FilenameOrUrl")
    protected String filenameOrUrl;
    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "UploadDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar uploadDate;
    @XmlElement(name = "EditedDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar editedDate;
    @XmlElement(name = "Size")
    protected int size;
    @XmlElement(name = "CurrentVersion")
    protected String currentVersion;
    @XmlElement(name = "Tags")
    protected String tags;

    /**
     * Gets the value of the attachmentId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAttachmentId() {
        return attachmentId;
    }

    /**
     * Sets the value of the attachmentId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAttachmentId(Integer value) {
        this.attachmentId = value;
    }

    /**
     * Gets the value of the attachmentTypeId property.
     * 
     */
    public int getAttachmentTypeId() {
        return attachmentTypeId;
    }

    /**
     * Sets the value of the attachmentTypeId property.
     * 
     */
    public void setAttachmentTypeId(int value) {
        this.attachmentTypeId = value;
    }

    /**
     * Gets the value of the projectAttachmentTypeId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getProjectAttachmentTypeId() {
        return projectAttachmentTypeId;
    }

    /**
     * Sets the value of the projectAttachmentTypeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setProjectAttachmentTypeId(Integer value) {
        this.projectAttachmentTypeId = value;
    }

    /**
     * Gets the value of the projectAttachmentFolderId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getProjectAttachmentFolderId() {
        return projectAttachmentFolderId;
    }

    /**
     * Sets the value of the projectAttachmentFolderId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setProjectAttachmentFolderId(Integer value) {
        this.projectAttachmentFolderId = value;
    }

    /**
     * Gets the value of the artifactTypeId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getArtifactTypeId() {
        return artifactTypeId;
    }

    /**
     * Sets the value of the artifactTypeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setArtifactTypeId(Integer value) {
        this.artifactTypeId = value;
    }

    /**
     * Gets the value of the artifactId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getArtifactId() {
        return artifactId;
    }

    /**
     * Sets the value of the artifactId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setArtifactId(Integer value) {
        this.artifactId = value;
    }

    /**
     * Gets the value of the authorId property.
     * 
     */
    public int getAuthorId() {
        return authorId;
    }

    /**
     * Sets the value of the authorId property.
     * 
     */
    public void setAuthorId(int value) {
        this.authorId = value;
    }

    /**
     * Gets the value of the editorId property.
     * 
     */
    public int getEditorId() {
        return editorId;
    }

    /**
     * Sets the value of the editorId property.
     * 
     */
    public void setEditorId(int value) {
        this.editorId = value;
    }

    /**
     * Gets the value of the filenameOrUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFilenameOrUrl() {
        return filenameOrUrl;
    }

    /**
     * Sets the value of the filenameOrUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFilenameOrUrl(String value) {
        this.filenameOrUrl = value;
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
     * Gets the value of the uploadDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getUploadDate() {
        return uploadDate;
    }

    /**
     * Sets the value of the uploadDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setUploadDate(XMLGregorianCalendar value) {
        this.uploadDate = value;
    }

    /**
     * Gets the value of the editedDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEditedDate() {
        return editedDate;
    }

    /**
     * Sets the value of the editedDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEditedDate(XMLGregorianCalendar value) {
        this.editedDate = value;
    }

    /**
     * Gets the value of the size property.
     * 
     */
    public int getSize() {
        return size;
    }

    /**
     * Sets the value of the size property.
     * 
     */
    public void setSize(int value) {
        this.size = value;
    }

    /**
     * Gets the value of the currentVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrentVersion() {
        return currentVersion;
    }

    /**
     * Sets the value of the currentVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrentVersion(String value) {
        this.currentVersion = value;
    }

    /**
     * Gets the value of the tags property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTags() {
        return tags;
    }

    /**
     * Sets the value of the tags property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTags(String value) {
        this.tags = value;
    }

}
