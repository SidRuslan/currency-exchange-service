package ru.skillbox.currency.exchange.mapper;

import org.mapstruct.Mapper;
import ru.skillbox.currency.exchange.dto.CurrenciesListResponseDto;
import ru.skillbox.currency.exchange.dto.CurrencyDto;
import ru.skillbox.currency.exchange.dto.CurrencyResponseDto;
import ru.skillbox.currency.exchange.entity.Currency;

import java.util.List;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
public interface CurrencyMapper {

    CurrencyDto convertToDto(Currency currency);

    Currency convertToEntity(CurrencyDto currencyDto);

    CurrencyResponseDto convertToResponseDto(Currency currency);

    default CurrenciesListResponseDto convertToResponseListDto(List<Currency> currencyList) {
        return new CurrenciesListResponseDto(currencyList.stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList()));
    }


}
