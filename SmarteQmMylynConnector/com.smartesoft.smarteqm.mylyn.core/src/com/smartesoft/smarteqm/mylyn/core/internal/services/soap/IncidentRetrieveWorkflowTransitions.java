
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
 *         &lt;element name="currentTypeID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="currentStatusID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="isDetector" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="isOwner" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "currentStatusID",
    "isDetector",
    "isOwner"
})
@XmlRootElement(name = "Incident_RetrieveWorkflowTransitions")
public class IncidentRetrieveWorkflowTransitions {

    protected int currentTypeID;
    protected int currentStatusID;
    protected boolean isDetector;
    protected boolean isOwner;

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

    /**
     * Gets the value of the isDetector property.
     * 
     */
    public boolean isIsDetector() {
        return isDetector;
    }

    /**
     * Sets the value of the isDetector property.
     * 
     */
    public void setIsDetector(boolean value) {
        this.isDetector = value;
    }

    /**
     * Gets the value of the isOwner property.
     * 
     */
    public boolean isIsOwner() {
        return isOwner;
    }

    /**
     * Sets the value of the isOwner property.
     * 
     */
    public void setIsOwner(boolean value) {
        this.isOwner = value;
    }

}
