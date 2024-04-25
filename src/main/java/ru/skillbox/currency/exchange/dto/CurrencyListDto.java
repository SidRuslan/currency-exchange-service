package ru.skillbox.currency.exchange.dto;

import lombok.*;

import javax.xml.bind.annotation.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@XmlRootElement(name = "ValCurs")
@XmlAccessorType(XmlAccessType.FIELD)
public class CurrencyListDto {

    @XmlElement(name = "Valute")
    private List<CurrencyDto> currencyDtoList;

}
