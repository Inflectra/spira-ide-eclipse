
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
 *         &lt;element name="Task_RetrieveForOwnerResult" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}ArrayOfRemoteTask" minOccurs="0"/>
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
    "taskRetrieveForOwnerResult"
})
@XmlRootElement(name = "Task_RetrieveForOwnerResponse")
public class TaskRetrieveForOwnerResponse {

    @XmlElement(name = "Task_RetrieveForOwnerResult")
    protected ArrayOfRemoteTask taskRetrieveForOwnerResult;

    /**
     * Gets the value of the taskRetrieveForOwnerResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfRemoteTask }
     *     
     */
    public ArrayOfRemoteTask getTaskRetrieveForOwnerResult() {
        return taskRetrieveForOwnerResult;
    }

    /**
     * Sets the value of the taskRetrieveForOwnerResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfRemoteTask }
     *     
     */
    public void setTaskRetrieveForOwnerResult(ArrayOfRemoteTask value) {
        this.taskRetrieveForOwnerResult = value;
    }

}
