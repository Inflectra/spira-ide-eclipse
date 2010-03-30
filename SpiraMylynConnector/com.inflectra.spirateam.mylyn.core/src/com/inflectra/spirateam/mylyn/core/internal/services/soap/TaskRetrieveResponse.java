
package com.inflectra.spirateam.mylyn.core.internal.services.soap;

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
 *         &lt;element name="Task_RetrieveResult" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}ArrayOfRemoteTask" minOccurs="0"/>
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
    "taskRetrieveResult"
})
@XmlRootElement(name = "Task_RetrieveResponse")
public class TaskRetrieveResponse {

    @XmlElement(name = "Task_RetrieveResult")
    protected ArrayOfRemoteTask taskRetrieveResult;

    /**
     * Gets the value of the taskRetrieveResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfRemoteTask }
     *     
     */
    public ArrayOfRemoteTask getTaskRetrieveResult() {
        return taskRetrieveResult;
    }

    /**
     * Sets the value of the taskRetrieveResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfRemoteTask }
     *     
     */
    public void setTaskRetrieveResult(ArrayOfRemoteTask value) {
        this.taskRetrieveResult = value;
    }

}