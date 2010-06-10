
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
 *         &lt;element name="remoteTestRun" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}RemoteTestRun" minOccurs="0"/>
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
    "remoteTestRun"
})
@XmlRootElement(name = "TestRun_RecordAutomated1")
public class TestRunRecordAutomated1 {

    protected RemoteTestRun remoteTestRun;

    /**
     * Gets the value of the remoteTestRun property.
     * 
     * @return
     *     possible object is
     *     {@link RemoteTestRun }
     *     
     */
    public RemoteTestRun getRemoteTestRun() {
        return remoteTestRun;
    }

    /**
     * Sets the value of the remoteTestRun property.
     * 
     * @param value
     *     allowed object is
     *     {@link RemoteTestRun }
     *     
     */
    public void setRemoteTestRun(RemoteTestRun value) {
        this.remoteTestRun = value;
    }

}
