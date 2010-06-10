
package com.smartesoft.smarteqm.mylyn.core.internal.services.soap;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfRemoteProjectCustomProperty complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfRemoteProjectCustomProperty">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RemoteProjectCustomProperty" type="{http://www.inflectra.com/SpiraTest/Services/v2.2/}RemoteProjectCustomProperty" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfRemoteProjectCustomProperty", propOrder = {
    "remoteProjectCustomProperty"
})
public class ArrayOfRemoteProjectCustomProperty {

    @XmlElement(name = "RemoteProjectCustomProperty", nillable = true)
    protected List<RemoteProjectCustomProperty> remoteProjectCustomProperty;

    /**
     * Gets the value of the remoteProjectCustomProperty property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the remoteProjectCustomProperty property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRemoteProjectCustomProperty().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RemoteProjectCustomProperty }
     * 
     * 
     */
    public List<RemoteProjectCustomProperty> getRemoteProjectCustomProperty() {
        if (remoteProjectCustomProperty == null) {
            remoteProjectCustomProperty = new ArrayList<RemoteProjectCustomProperty>();
        }
        return this.remoteProjectCustomProperty;
    }

}
