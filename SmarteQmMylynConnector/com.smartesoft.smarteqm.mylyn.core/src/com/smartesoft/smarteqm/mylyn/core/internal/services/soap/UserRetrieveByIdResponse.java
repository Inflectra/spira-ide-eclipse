
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
 *         &lt;element name="User_RetrieveByIdResult" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}RemoteUser" minOccurs="0"/>
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
    "userRetrieveByIdResult"
})
@XmlRootElement(name = "User_RetrieveByIdResponse")
public class UserRetrieveByIdResponse {

    @XmlElement(name = "User_RetrieveByIdResult")
    protected RemoteUser userRetrieveByIdResult;

    /**
     * Gets the value of the userRetrieveByIdResult property.
     * 
     * @return
     *     possible object is
     *     {@link RemoteUser }
     *     
     */
    public RemoteUser getUserRetrieveByIdResult() {
        return userRetrieveByIdResult;
    }

    /**
     * Sets the value of the userRetrieveByIdResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link RemoteUser }
     *     
     */
    public void setUserRetrieveByIdResult(RemoteUser value) {
        this.userRetrieveByIdResult = value;
    }

}
