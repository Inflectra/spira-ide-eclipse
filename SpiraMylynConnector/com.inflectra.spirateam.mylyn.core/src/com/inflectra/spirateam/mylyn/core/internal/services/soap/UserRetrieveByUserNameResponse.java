
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
 *         &lt;element name="User_RetrieveByUserNameResult" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}RemoteUser" minOccurs="0"/>
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
    "userRetrieveByUserNameResult"
})
@XmlRootElement(name = "User_RetrieveByUserNameResponse")
public class UserRetrieveByUserNameResponse {

    @XmlElement(name = "User_RetrieveByUserNameResult")
    protected RemoteUser userRetrieveByUserNameResult;

    /**
     * Gets the value of the userRetrieveByUserNameResult property.
     * 
     * @return
     *     possible object is
     *     {@link RemoteUser }
     *     
     */
    public RemoteUser getUserRetrieveByUserNameResult() {
        return userRetrieveByUserNameResult;
    }

    /**
     * Sets the value of the userRetrieveByUserNameResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link RemoteUser }
     *     
     */
    public void setUserRetrieveByUserNameResult(RemoteUser value) {
        this.userRetrieveByUserNameResult = value;
    }

}
