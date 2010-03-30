
package com.inflectra.spirateam.mylyn.core.internal.services.soap;

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
 *         &lt;element name="remoteReqTestCaseMapping" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}RemoteRequirementTestCaseMapping" minOccurs="0"/>
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
    "remoteReqTestCaseMapping"
})
@XmlRootElement(name = "Requirement_AddTestCoverage")
public class RequirementAddTestCoverage {

    protected RemoteRequirementTestCaseMapping remoteReqTestCaseMapping;

    /**
     * Gets the value of the remoteReqTestCaseMapping property.
     * 
     * @return
     *     possible object is
     *     {@link RemoteRequirementTestCaseMapping }
     *     
     */
    public RemoteRequirementTestCaseMapping getRemoteReqTestCaseMapping() {
        return remoteReqTestCaseMapping;
    }

    /**
     * Sets the value of the remoteReqTestCaseMapping property.
     * 
     * @param value
     *     allowed object is
     *     {@link RemoteRequirementTestCaseMapping }
     *     
     */
    public void setRemoteReqTestCaseMapping(RemoteRequirementTestCaseMapping value) {
        this.remoteReqTestCaseMapping = value;
    }

}