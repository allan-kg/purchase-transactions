/*
 * CountryISO4217Xml
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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * {@literal <ISO_4217>}<br>
 * &nbsp;{@literal <CcyTbl>}<br>
 * &nbsp;&nbsp;{@literal <CcyNtry>}<br>
 * &nbsp;&nbsp;&nbsp;{@literal <CtryNm>BRAZIL</CtryNm>}<br>
 * &nbsp;&nbsp;&nbsp;{@literal <CcyNm>Brazilian Real</CcyNm>}<br>
 * &nbsp;&nbsp;&nbsp;{@literal <Ccy>BRL</Ccy>}<br>
 * &nbsp;&nbsp;&nbsp;{@literal <CcyNbr>986</CcyNbr>}<br>
 * &nbsp;&nbsp;&nbsp;{@literal <CcyMnrUnts>2</CcyMnrUnts>}<br>
 * &nbsp;&nbsp;{@literal </CcyNtry>}<br>
 * &nbsp;&nbsp;{@literal <!-- ... -->}<br>
 * &nbsp;{@literal </CcyTbl>}<br>
 * {@literal </ISO_4217>}<br>
 * @author Allan Krama Guimarães
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@XmlRootElement(name = "CcyNtry")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"country", "currency", "code"})
public class CountryISO4217Xml {
    @XmlElement(name="CtryNm")
    private String country;
    
    @XmlElement(name="CcyNm")
    private String currency;
    
    @XmlElement(name="Ccy")
    private String code;
}
