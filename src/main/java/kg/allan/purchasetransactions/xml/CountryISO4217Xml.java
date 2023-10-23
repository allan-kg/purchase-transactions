/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kg.allan.purchasetransactions.xml;

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
 * @author allan
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
