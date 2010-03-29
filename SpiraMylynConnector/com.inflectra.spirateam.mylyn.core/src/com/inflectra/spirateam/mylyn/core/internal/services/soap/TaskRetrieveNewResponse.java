
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
 *         &lt;element name="Task_RetrieveNewResult" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}ArrayOfRemoteTask" minOccurs="0"/>
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
    "taskRetrieveNewResult"
})
@XmlRootElement(name = "Task_RetrieveNewResponse")
public class TaskRetrieveNewResponse {

    @XmlElement(name = "Task_RetrieveNewResult")
    protected ArrayOfRemoteTask taskRetrieveNewResult;

    /**
     * Gets the value of the taskRetrieveNewResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfRemoteTask }
     *     
     */
    public ArrayOfRemoteTask getTaskRetrieveNewResult() {
        return taskRetrieveNewResult;
    }

    /**
     * Sets the value of the taskRetrieveNewResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfRemoteTask }
     *     
     */
    public void setTaskRetrieveNewResult(ArrayOfRemoteTask value) {
        this.taskRetrieveNewResult = value;
    }

}
