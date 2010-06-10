
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
 *         &lt;element name="Connection_Authenticate2Result" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "connectionAuthenticate2Result"
})
@XmlRootElement(name = "Connection_Authenticate2Response")
public class ConnectionAuthenticate2Response {

    @XmlElement(name = "Connection_Authenticate2Result")
    protected boolean connectionAuthenticate2Result;

    /**
     * Gets the value of the connectionAuthenticate2Result property.
     * 
     */
    public boolean isConnectionAuthenticate2Result() {
        return connectionAuthenticate2Result;
    }

    /**
     * Sets the value of the connectionAuthenticate2Result property.
     * 
     */
    public void setConnectionAuthenticate2Result(boolean value) {
        this.connectionAuthenticate2Result = value;
    }

}
