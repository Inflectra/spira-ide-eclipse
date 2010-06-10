
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
 *         &lt;element name="testSetId" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "testSetId"
})
@XmlRootElement(name = "TestSet_RetrieveById")
public class TestSetRetrieveById {

    protected int testSetId;

    /**
     * Gets the value of the testSetId property.
     * 
     */
    public int getTestSetId() {
        return testSetId;
    }

    /**
     * Sets the value of the testSetId property.
     * 
     */
    public void setTestSetId(int value) {
        this.testSetId = value;
    }

}