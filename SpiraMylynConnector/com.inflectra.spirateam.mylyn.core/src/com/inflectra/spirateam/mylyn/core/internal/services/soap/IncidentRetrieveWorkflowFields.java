
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
 *         &lt;element name="currentTypeID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="currentStatusID" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "currentTypeID",
    "currentStatusID"
})
@XmlRootElement(name = "Incident_RetrieveWorkflowFields")
public class IncidentRetrieveWorkflowFields {

    protected int currentTypeID;
    protected int currentStatusID;

    /**
     * Gets the value of the currentTypeID property.
     * 
     */
    public int getCurrentTypeID() {
        return currentTypeID;
    }

    /**
     * Sets the value of the currentTypeID property.
     * 
     */
    public void setCurrentTypeID(int value) {
        this.currentTypeID = value;
    }

    /**
     * Gets the value of the currentStatusID property.
     * 
     */
    public int getCurrentStatusID() {
        return currentStatusID;
    }

    /**
     * Sets the value of the currentStatusID property.
     * 
     */
    public void setCurrentStatusID(int value) {
        this.currentStatusID = value;
    }

}
