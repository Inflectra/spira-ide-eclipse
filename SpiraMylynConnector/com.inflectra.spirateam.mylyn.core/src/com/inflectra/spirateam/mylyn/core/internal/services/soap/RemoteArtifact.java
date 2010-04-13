
package com.inflectra.spirateam.mylyn.core.internal.services.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RemoteArtifact complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RemoteArtifact">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Text01" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Text02" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Text03" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Text04" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Text05" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Text06" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Text07" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Text08" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Text09" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Text10" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="List01" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="List02" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="List03" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="List04" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="List05" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="List06" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="List07" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="List08" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="List09" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="List10" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RemoteArtifact", propOrder = {
    "text01",
    "text02",
    "text03",
    "text04",
    "text05",
    "text06",
    "text07",
    "text08",
    "text09",
    "text10",
    "list01",
    "list02",
    "list03",
    "list04",
    "list05",
    "list06",
    "list07",
    "list08",
    "list09",
    "list10"
})
@XmlSeeAlso({
    RemoteTestRun.class,
    RemoteIncident.class,
    RemoteTestSet.class,
    RemoteRelease.class,
    RemoteRequirement.class,
    RemoteTask.class,
    RemoteTestCase.class,
    RemoteTestStep.class
})
public class RemoteArtifact {

    @XmlElement(name = "Text01")
    protected String text01;
    @XmlElement(name = "Text02")
    protected String text02;
    @XmlElement(name = "Text03")
    protected String text03;
    @XmlElement(name = "Text04")
    protected String text04;
    @XmlElement(name = "Text05")
    protected String text05;
    @XmlElement(name = "Text06")
    protected String text06;
    @XmlElement(name = "Text07")
    protected String text07;
    @XmlElement(name = "Text08")
    protected String text08;
    @XmlElement(name = "Text09")
    protected String text09;
    @XmlElement(name = "Text10")
    protected String text10;
    @XmlElement(name = "List01", required = true, type = Integer.class, nillable = true)
    protected Integer list01;
    @XmlElement(name = "List02", required = true, type = Integer.class, nillable = true)
    protected Integer list02;
    @XmlElement(name = "List03", required = true, type = Integer.class, nillable = true)
    protected Integer list03;
    @XmlElement(name = "List04", required = true, type = Integer.class, nillable = true)
    protected Integer list04;
    @XmlElement(name = "List05", required = true, type = Integer.class, nillable = true)
    protected Integer list05;
    @XmlElement(name = "List06", required = true, type = Integer.class, nillable = true)
    protected Integer list06;
    @XmlElement(name = "List07", required = true, type = Integer.class, nillable = true)
    protected Integer list07;
    @XmlElement(name = "List08", required = true, type = Integer.class, nillable = true)
    protected Integer list08;
    @XmlElement(name = "List09", required = true, type = Integer.class, nillable = true)
    protected Integer list09;
    @XmlElement(name = "List10", required = true, type = Integer.class, nillable = true)
    protected Integer list10;

    /**
     * Gets the value of the text01 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getText01() {
        return text01;
    }

    /**
     * Sets the value of the text01 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setText01(String value) {
        this.text01 = value;
    }

    /**
     * Gets the value of the text02 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getText02() {
        return text02;
    }

    /**
     * Sets the value of the text02 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setText02(String value) {
        this.text02 = value;
    }

    /**
     * Gets the value of the text03 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getText03() {
        return text03;
    }

    /**
     * Sets the value of the text03 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setText03(String value) {
        this.text03 = value;
    }

    /**
     * Gets the value of the text04 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getText04() {
        return text04;
    }

    /**
     * Sets the value of the text04 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setText04(String value) {
        this.text04 = value;
    }

    /**
     * Gets the value of the text05 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getText05() {
        return text05;
    }

    /**
     * Sets the value of the text05 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setText05(String value) {
        this.text05 = value;
    }

    /**
     * Gets the value of the text06 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getText06() {
        return text06;
    }

    /**
     * Sets the value of the text06 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setText06(String value) {
        this.text06 = value;
    }

    /**
     * Gets the value of the text07 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getText07() {
        return text07;
    }

    /**
     * Sets the value of the text07 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setText07(String value) {
        this.text07 = value;
    }

    /**
     * Gets the value of the text08 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getText08() {
        return text08;
    }

    /**
     * Sets the value of the text08 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setText08(String value) {
        this.text08 = value;
    }

    /**
     * Gets the value of the text09 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getText09() {
        return text09;
    }

    /**
     * Sets the value of the text09 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setText09(String value) {
        this.text09 = value;
    }

    /**
     * Gets the value of the text10 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getText10() {
        return text10;
    }

    /**
     * Sets the value of the text10 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setText10(String value) {
        this.text10 = value;
    }

    /**
     * Gets the value of the list01 property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getList01() {
        return list01;
    }

    /**
     * Sets the value of the list01 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setList01(Integer value) {
        this.list01 = value;
    }

    /**
     * Gets the value of the list02 property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getList02() {
        return list02;
    }

    /**
     * Sets the value of the list02 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setList02(Integer value) {
        this.list02 = value;
    }

    /**
     * Gets the value of the list03 property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getList03() {
        return list03;
    }

    /**
     * Sets the value of the list03 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setList03(Integer value) {
        this.list03 = value;
    }

    /**
     * Gets the value of the list04 property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getList04() {
        return list04;
    }

    /**
     * Sets the value of the list04 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setList04(Integer value) {
        this.list04 = value;
    }

    /**
     * Gets the value of the list05 property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getList05() {
        return list05;
    }

    /**
     * Sets the value of the list05 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setList05(Integer value) {
        this.list05 = value;
    }

    /**
     * Gets the value of the list06 property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getList06() {
        return list06;
    }

    /**
     * Sets the value of the list06 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setList06(Integer value) {
        this.list06 = value;
    }

    /**
     * Gets the value of the list07 property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getList07() {
        return list07;
    }

    /**
     * Sets the value of the list07 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setList07(Integer value) {
        this.list07 = value;
    }

    /**
     * Gets the value of the list08 property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getList08() {
        return list08;
    }

    /**
     * Sets the value of the list08 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setList08(Integer value) {
        this.list08 = value;
    }

    /**
     * Gets the value of the list09 property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getList09() {
        return list09;
    }

    /**
     * Sets the value of the list09 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setList09(Integer value) {
        this.list09 = value;
    }

    /**
     * Gets the value of the list10 property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getList10() {
        return list10;
    }

    /**
     * Sets the value of the list10 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setList10(Integer value) {
        this.list10 = value;
    }

}
