//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-147 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.06.04 at 02:11:57 AM CEST 
//


package org.sigaim.siie.iso13606.rm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IVL.LOW_PQ complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IVL.LOW_PQ">
 *   &lt;complexContent>
 *     &lt;restriction base="{uri:iso.org:21090}IVL_PQ">
 *       &lt;sequence>
 *         &lt;element name="originalText" type="{uri:iso.org:21090}ED.TEXT" minOccurs="0"/>
 *         &lt;element name="low" type="{uri:iso.org:21090}PQ" minOccurs="0"/>
 *         &lt;element name="high" type="{uri:iso.org:21090}PQ" maxOccurs="0" minOccurs="0"/>
 *         &lt;element name="width" type="{uri:iso.org:21090}QTY" maxOccurs="0" minOccurs="0"/>
 *         &lt;element name="any" type="{uri:iso.org:21090}PQ" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IVL.LOW_PQ")
public class IVLLOWPQ
    extends IVLPQ
{


}
