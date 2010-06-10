
package com.smartesoft.smarteqm.mylyn.core.internal.services.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RemoteWorkflowIncidentTransition complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RemoteWorkflowIncidentTransition">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ExecuteByDetector" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="ExecuteByOwner" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="IncidentStatusID_Input" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="IncidentStatusName_Input" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IncidentStatusID_Output" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="IncidentStatusName_Output" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="WorkflowID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="TransitionID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RemoteWorkflowIncidentTransition", propOrder = {
    "executeByDetector",
    "executeByOwner",
    "incidentStatusIDInput",
    "incidentStatusNameInput",
    "incidentStatusIDOutput",
    "incidentStatusNameOutput",
    "name",
    "workflowID",
    "transitionID"
})
public class RemoteWorkflowIncidentTransition {

    @XmlElement(name = "ExecuteByDetector")
    protected boolean executeByDetector;
    @XmlElement(name = "ExecuteByOwner")
    protected boolean executeByOwner;
    @XmlElement(name = "IncidentStatusID_Input")
    protected int incidentStatusIDInput;
    @XmlElement(name = "IncidentStatusName_Input")
    protected String incidentStatusNameInput;
    @XmlElement(name = "IncidentStatusID_Output")
    protected int incidentStatusIDOutput;
    @XmlElement(name = "IncidentStatusName_Output")
    protected String incidentStatusNameOutput;
    @XmlElement(name = "Name")
    protected String name;
    @XmlElement(name = "WorkflowID")
    protected int workflowID;
    @XmlElement(name = "TransitionID")
    protected int transitionID;

    /**
     * Gets the value of the executeByDetector property.
     * 
     */
    public boolean isExecuteByDetector() {
        return executeByDetector;
    }

    /**
     * Sets the value of the executeByDetector property.
     * 
     */
    public void setExecuteByDetector(boolean value) {
        this.executeByDetector = value;
    }

    /**
     * Gets the value of the executeByOwner property.
     * 
     */
    public boolean isExecuteByOwner() {
        return executeByOwner;
    }

    /**
     * Sets the value of the executeByOwner property.
     * 
     */
    public void setExecuteByOwner(boolean value) {
        this.executeByOwner = value;
    }

    /**
     * Gets the value of the incidentStatusIDInput property.
     * 
     */
    public int getIncidentStatusIDInput() {
        return incidentStatusIDInput;
    }

    /**
     * Sets the value of the incidentStatusIDInput property.
     * 
     */
    public void setIncidentStatusIDInput(int value) {
        this.incidentStatusIDInput = value;
    }

    /**
     * Gets the value of the incidentStatusNameInput property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIncidentStatusNameInput() {
        return incidentStatusNameInput;
    }

    /**
     * Sets the value of the incidentStatusNameInput property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIncidentStatusNameInput(String value) {
        this.incidentStatusNameInput = value;
    }

    /**
     * Gets the value of the incidentStatusIDOutput property.
     * 
     */
    public int getIncidentStatusIDOutput() {
        return incidentStatusIDOutput;
    }

    /**
     * Sets the value of the incidentStatusIDOutput property.
     * 
     */
    public void setIncidentStatusIDOutput(int value) {
        this.incidentStatusIDOutput = value;
    }

    /**
     * Gets the value of the incidentStatusNameOutput property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIncidentStatusNameOutput() {
        return incidentStatusNameOutput;
    }

    /**
     * Sets the value of the incidentStatusNameOutput property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIncidentStatusNameOutput(String value) {
        this.incidentStatusNameOutput = value;
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
     * Gets the value of the workflowID property.
     * 
     */
    public int getWorkflowID() {
        return workflowID;
    }

    /**
     * Sets the value of the workflowID property.
     * 
     */
    public void setWorkflowID(int value) {
        this.workflowID = value;
    }

    /**
     * Gets the value of the transitionID property.
     * 
     */
    public int getTransitionID() {
        return transitionID;
    }

    /**
     * Sets the value of the transitionID property.
     * 
     */
    public void setTransitionID(int value) {
        this.transitionID = value;
    }

}
