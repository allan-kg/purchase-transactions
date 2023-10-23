package kg.allan.purchasetransactions.xml;

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
 * @author allan
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
