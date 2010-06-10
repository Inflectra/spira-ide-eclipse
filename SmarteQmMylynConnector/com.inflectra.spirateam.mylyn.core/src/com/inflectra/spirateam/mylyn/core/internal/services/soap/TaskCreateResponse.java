
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
 *         &lt;element name="Task_CreateResult" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}RemoteTask" minOccurs="0"/>
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
    "taskCreateResult"
})
@XmlRootElement(name = "Task_CreateResponse")
public class TaskCreateResponse {

    @XmlElement(name = "Task_CreateResult")
    protected RemoteTask taskCreateResult;

    /**
     * Gets the value of the taskCreateResult property.
     * 
     * @return
     *     possible object is
     *     {@link RemoteTask }
     *     
     */
    public RemoteTask getTaskCreateResult() {
        return taskCreateResult;
    }

    /**
     * Sets the value of the taskCreateResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link RemoteTask }
     *     
     */
    public void setTaskCreateResult(RemoteTask value) {
        this.taskCreateResult = value;
    }

}
