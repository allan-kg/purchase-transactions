/*
 * CountryTableISO4217Xml
 *
 * v1.0
 *
 * 2023
 *
 * Author: Allan Krama Guimarães
 */

package kg.allan.purchasetransactions.dto.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Allan Krama Guimarães
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@XmlRootElement(name = "CcyTbl")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"countries"})
public class CountryTableISO4217Xml {
    @XmlElement(name="CcyNtry")
    List<CountryISO4217Xml> countries;
}
