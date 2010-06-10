
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
 *         &lt;element name="Connection_AuthenticateResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "connectionAuthenticateResult"
})
@XmlRootElement(name = "Connection_AuthenticateResponse")
public class ConnectionAuthenticateResponse {

    @XmlElement(name = "Connection_AuthenticateResult")
    protected boolean connectionAuthenticateResult;

    /**
     * Gets the value of the connectionAuthenticateResult property.
     * 
     */
    public boolean isConnectionAuthenticateResult() {
        return connectionAuthenticateResult;
    }

    /**
     * Sets the value of the connectionAuthenticateResult property.
     * 
     */
    public void setConnectionAuthenticateResult(boolean value) {
        this.connectionAuthenticateResult = value;
    }

}
