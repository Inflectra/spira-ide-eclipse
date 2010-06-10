
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
 *         &lt;element name="remoteFilters" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}ArrayOfRemoteFilter" minOccurs="0"/>
 *         &lt;element name="startingRow" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="numberOfRows" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "remoteFilters",
    "startingRow",
    "numberOfRows"
})
@XmlRootElement(name = "Release_Retrieve2")
public class ReleaseRetrieve2 {

    protected ArrayOfRemoteFilter remoteFilters;
    protected int startingRow;
    protected int numberOfRows;

    /**
     * Gets the value of the remoteFilters property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfRemoteFilter }
     *     
     */
    public ArrayOfRemoteFilter getRemoteFilters() {
        return remoteFilters;
    }

    /**
     * Sets the value of the remoteFilters property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfRemoteFilter }
     *     
     */
    public void setRemoteFilters(ArrayOfRemoteFilter value) {
        this.remoteFilters = value;
    }

    /**
     * Gets the value of the startingRow property.
     * 
     */
    public int getStartingRow() {
        return startingRow;
    }

    /**
     * Sets the value of the startingRow property.
     * 
     */
    public void setStartingRow(int value) {
        this.startingRow = value;
    }

    /**
     * Gets the value of the numberOfRows property.
     * 
     */
    public int getNumberOfRows() {
        return numberOfRows;
    }

    /**
     * Sets the value of the numberOfRows property.
     * 
     */
    public void setNumberOfRows(int value) {
        this.numberOfRows = value;
    }

}
