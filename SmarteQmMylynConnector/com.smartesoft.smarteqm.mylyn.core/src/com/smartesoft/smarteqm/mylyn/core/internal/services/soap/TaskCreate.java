
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
 *         &lt;element name="remoteTask" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}RemoteTask" minOccurs="0"/>
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
    "remoteTask"
})
@XmlRootElement(name = "Task_Create")
public class TaskCreate {

    protected RemoteTask remoteTask;

    /**
     * Gets the value of the remoteTask property.
     * 
     * @return
     *     possible object is
     *     {@link RemoteTask }
     *     
     */
    public RemoteTask getRemoteTask() {
        return remoteTask;
    }

    /**
     * Sets the value of the remoteTask property.
     * 
     * @param value
     *     allowed object is
     *     {@link RemoteTask }
     *     
     */
    public void setRemoteTask(RemoteTask value) {
        this.remoteTask = value;
    }

}
