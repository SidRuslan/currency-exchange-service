package ru.skillbox.currency.exchange.dto;

import lombok.*;
import ru.skillbox.currency.exchange.service.UnmarshalDoubleAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@XmlRootElement(name = "Valute")
@XmlAccessorType(XmlAccessType.FIELD)
public class CurrencyDto {

    @XmlTransient
    private Long id;

    @XmlElement(name = "Name")
    private String name;

    @XmlElement(name = "Nominal")
    private Long nominal;

    @XmlElement(name = "Value")
    @XmlJavaTypeAdapter(UnmarshalDoubleAdapter.class)
    private Double value;

    @XmlElement(name = "NumCode")
    private Long isoNumCode;

    @XmlElement(name = "CharCode")
    private String isoCharCode;
}