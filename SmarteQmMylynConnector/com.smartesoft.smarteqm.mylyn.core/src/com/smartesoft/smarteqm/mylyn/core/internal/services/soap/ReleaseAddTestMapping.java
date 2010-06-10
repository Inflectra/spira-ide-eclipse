
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
 *         &lt;element name="remoteReleaseTestCaseMapping" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}RemoteReleaseTestCaseMapping" minOccurs="0"/>
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
    "remoteReleaseTestCaseMapping"
})
@XmlRootElement(name = "Release_AddTestMapping")
public class ReleaseAddTestMapping {

    protected RemoteReleaseTestCaseMapping remoteReleaseTestCaseMapping;

    /**
     * Gets the value of the remoteReleaseTestCaseMapping property.
     * 
     * @return
     *     possible object is
     *     {@link RemoteReleaseTestCaseMapping }
     *     
     */
    public RemoteReleaseTestCaseMapping getRemoteReleaseTestCaseMapping() {
        return remoteReleaseTestCaseMapping;
    }

    /**
     * Sets the value of the remoteReleaseTestCaseMapping property.
     * 
     * @param value
     *     allowed object is
     *     {@link RemoteReleaseTestCaseMapping }
     *     
     */
    public void setRemoteReleaseTestCaseMapping(RemoteReleaseTestCaseMapping value) {
        this.remoteReleaseTestCaseMapping = value;
    }

}
