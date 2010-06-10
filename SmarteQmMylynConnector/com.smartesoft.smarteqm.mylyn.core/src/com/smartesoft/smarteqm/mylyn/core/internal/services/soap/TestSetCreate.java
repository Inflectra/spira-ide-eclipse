
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
 *         &lt;element name="remoteTestSet" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}RemoteTestSet" minOccurs="0"/>
 *         &lt;element name="parentTestSetFolderId" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "remoteTestSet",
    "parentTestSetFolderId"
})
@XmlRootElement(name = "TestSet_Create")
public class TestSetCreate {

    protected RemoteTestSet remoteTestSet;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer parentTestSetFolderId;

    /**
     * Gets the value of the remoteTestSet property.
     * 
     * @return
     *     possible object is
     *     {@link RemoteTestSet }
     *     
     */
    public RemoteTestSet getRemoteTestSet() {
        return remoteTestSet;
    }

    /**
     * Sets the value of the remoteTestSet property.
     * 
     * @param value
     *     allowed object is
     *     {@link RemoteTestSet }
     *     
     */
    public void setRemoteTestSet(RemoteTestSet value) {
        this.remoteTestSet = value;
    }

    /**
     * Gets the value of the parentTestSetFolderId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getParentTestSetFolderId() {
        return parentTestSetFolderId;
    }

    /**
     * Sets the value of the parentTestSetFolderId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setParentTestSetFolderId(Integer value) {
        this.parentTestSetFolderId = value;
    }

}
