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
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for QTY complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="QTY">
 *   &lt;complexContent>
 *     &lt;extension base="{uri:iso.org:21090}ANY">
 *       &lt;sequence>
 *         &lt;element name="expression" type="{uri:iso.org:21090}ED" minOccurs="0"/>
 *         &lt;element name="originalText" type="{uri:iso.org:21090}ED.TEXT" minOccurs="0"/>
 *         &lt;element name="uncertainty" type="{uri:iso.org:21090}QTY" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="uncertaintyType" type="{uri:iso.org:21090}UncertaintyType" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QTY", propOrder = {
    "expression",
    "originalText",
    "uncertainty"
})
@XmlSeeAlso({
    RTO.class,
    MO.class,
    CO.class,
    INT.class,
    REAL.class,
    PQV.class,
    TS.class
})
public abstract class QTY
    extends ANY
{

    protected ED expression;
    protected EDTEXT originalText;
    protected QTY uncertainty;
    @XmlAttribute(name = "uncertaintyType")
    protected UncertaintyType uncertaintyType;

    /**
     * Gets the value of the expression property.
     * 
     * @return
     *     possible object is
     *     {@link ED }
     *     
     */
    public ED getExpression() {
        return expression;
    }

    /**
     * Sets the value of the expression property.
     * 
     * @param value
     *     allowed object is
     *     {@link ED }
     *     
     */
    public void setExpression(ED value) {
        this.expression = value;
    }

    /**
     * Gets the value of the originalText property.
     * 
     * @return
     *     possible object is
     *     {@link EDTEXT }
     *     
     */
    public EDTEXT getOriginalText() {
        return originalText;
    }

    /**
     * Sets the value of the originalText property.
     * 
     * @param value
     *     allowed object is
     *     {@link EDTEXT }
     *     
     */
    public void setOriginalText(EDTEXT value) {
        this.originalText = value;
    }

    /**
     * Gets the value of the uncertainty property.
     * 
     * @return
     *     possible object is
     *     {@link QTY }
     *     
     */
    public QTY getUncertainty() {
        return uncertainty;
    }

    /**
     * Sets the value of the uncertainty property.
     * 
     * @param value
     *     allowed object is
     *     {@link QTY }
     *     
     */
    public void setUncertainty(QTY value) {
        this.uncertainty = value;
    }

    /**
     * Gets the value of the uncertaintyType property.
     * 
     * @return
     *     possible object is
     *     {@link UncertaintyType }
     *     
     */
    public UncertaintyType getUncertaintyType() {
        return uncertaintyType;
    }

    /**
     * Sets the value of the uncertaintyType property.
     * 
     * @param value
     *     allowed object is
     *     {@link UncertaintyType }
     *     
     */
    public void setUncertaintyType(UncertaintyType value) {
        this.uncertaintyType = value;
    }

}
