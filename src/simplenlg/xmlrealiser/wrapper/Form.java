//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.05.13 at 11:35:07 AM CEST 
//


package simplenlg.xmlrealiser.wrapper;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for form.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="form">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="BARE_INFINITIVE"/>
 *     &lt;enumeration value="GERUND"/>
 *     &lt;enumeration value="IMPERATIVE"/>
 *     &lt;enumeration value="INFINITIVE"/>
 *     &lt;enumeration value="NORMAL"/>
 *     &lt;enumeration value="PAST_PARTICIPLE"/>
 *     &lt;enumeration value="PRESENT_PARTICIPLE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "form")
@XmlEnum
public enum Form {

    BARE_INFINITIVE,
    GERUND,
    IMPERATIVE,
    INFINITIVE,
    NORMAL,
    PAST_PARTICIPLE,
    PRESENT_PARTICIPLE;

    public String value() {
        return name();
    }

    public static Form fromValue(String v) {
        return valueOf(v);
    }

}
