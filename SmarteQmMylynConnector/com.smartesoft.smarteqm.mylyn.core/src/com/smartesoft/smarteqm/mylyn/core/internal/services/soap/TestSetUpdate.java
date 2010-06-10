
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
 *         &lt;element name="remoteTestSet" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}RemoteTestSet" minOccurs="0"/>
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
    "remoteTestSet"
})
@XmlRootElement(name = "TestSet_Update")
public class TestSetUpdate {

    protected RemoteTestSet remoteTestSet;

    /**
     * Gets the value of the remoteTestSet property.
     * 
     * @return
     *     possible object is
     *     {@link RemoteTestSet }
     *     
     */
    public RemoteTestSet getRemoteTestSet() {
        return remoteTestSet;
    }

    /**
     * Sets the value of the remoteTestSet property.
     * 
     * @param value
     *     allowed object is
     *     {@link RemoteTestSet }
     *     
     */
    public void setRemoteTestSet(RemoteTestSet value) {
        this.remoteTestSet = value;
    }

}
