//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-147 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.06.04 at 02:11:57 AM CEST 
//


package org.sigaim.siie.iso13606.rm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PIVL_TS.DATETIME complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PIVL_TS.DATETIME">
 *   &lt;complexContent>
 *     &lt;extension base="{uri:iso.org:21090}QSET_TS.DATETIME">
 *       &lt;sequence>
 *         &lt;element name="phase" type="{uri:iso.org:21090}IVL_TS.DATETIME" minOccurs="0"/>
 *         &lt;element name="period" type="{uri:iso.org:21090}PQ" minOccurs="0"/>
 *         &lt;element name="frequency" type="{uri:iso.org:21090}RTO" minOccurs="0"/>
 *         &lt;element name="count" type="{uri:iso.org:21090}INT.POS" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="alignment" type="{uri:iso.org:21090}CalendarCycle" />
 *       &lt;attribute name="isFlexible" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PIVL_TS.DATETIME", propOrder = {
    "phase",
    "period",
    "frequency",
    "count"
})
public class PIVLTSDATETIME
    extends QSETTSDATETIME
{

    protected IVLTSDATETIME phase;
    protected PQ period;
    protected RTO frequency;
    protected INTPOS count;
    @XmlAttribute(name = "alignment")
    protected CalendarCycle alignment;
    @XmlAttribute(name = "isFlexible")
    protected Boolean isFlexible;

    /**
     * Gets the value of the phase property.
     * 
     * @return
     *     possible object is
     *     {@link IVLTSDATETIME }
     *     
     */
    public IVLTSDATETIME getPhase() {
        return phase;
    }

    /**
     * Sets the value of the phase property.
     * 
     * @param value
     *     allowed object is
     *     {@link IVLTSDATETIME }
     *     
     */
    public void setPhase(IVLTSDATETIME value) {
        this.phase = value;
    }

    /**
     * Gets the value of the period property.
     * 
     * @return
     *     possible object is
     *     {@link PQ }
     *     
     */
    public PQ getPeriod() {
        return period;
    }

    /**
     * Sets the value of the period property.
     * 
     * @param value
     *     allowed object is
     *     {@link PQ }
     *     
     */
    public void setPeriod(PQ value) {
        this.period = value;
    }

    /**
     * Gets the value of the frequency property.
     * 
     * @return
     *     possible object is
     *     {@link RTO }
     *     
     */
    public RTO getFrequency() {
        return frequency;
    }

    /**
     * Sets the value of the frequency property.
     * 
     * @param value
     *     allowed object is
     *     {@link RTO }
     *     
     */
    public void setFrequency(RTO value) {
        this.frequency = value;
    }

    /**
     * Gets the value of the count property.
     * 
     * @return
     *     possible object is
     *     {@link INTPOS }
     *     
     */
    public INTPOS getCount() {
        return count;
    }

    /**
     * Sets the value of the count property.
     * 
     * @param value
     *     allowed object is
     *     {@link INTPOS }
     *     
     */
    public void setCount(INTPOS value) {
        this.count = value;
    }

    /**
     * Gets the value of the alignment property.
     * 
     * @return
     *     possible object is
     *     {@link CalendarCycle }
     *     
     */
    public CalendarCycle getAlignment() {
        return alignment;
    }

    /**
     * Sets the value of the alignment property.
     * 
     * @param value
     *     allowed object is
     *     {@link CalendarCycle }
     *     
     */
    public void setAlignment(CalendarCycle value) {
        this.alignment = value;
    }

    /**
     * Gets the value of the isFlexible property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsFlexible() {
        return isFlexible;
    }

    /**
     * Sets the value of the isFlexible property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsFlexible(Boolean value) {
        this.isFlexible = value;
    }

}
