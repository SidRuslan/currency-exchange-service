package ru.skillbox.currency.exchange.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.skillbox.currency.exchange.dto.CurrencyDto;
import ru.skillbox.currency.exchange.dto.CurrencyListDto;
import ru.skillbox.currency.exchange.entity.Currency;
import ru.skillbox.currency.exchange.repository.CurrencyRepository;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;


@Slf4j
@Service
@RequiredArgsConstructor

public class ExchangeRateUpdateService {

    private final CurrencyService currencyService;
    private final CurrencyRepository currencyRepository;

    @Scheduled(fixedRate = 3600000)
    public void getCurrentExchangeRate () {
        log.info("ExchangeRateUpdateService method getCurrentExchangeRate executed");
       try {

           JAXBContext context = JAXBContext.newInstance(CurrencyListDto.class, CurrencyDto.class);
           CurrencyListDto currencyDto = (CurrencyListDto) context.createUnmarshaller()
                   .unmarshal(new URI("https://www.cbr-xml-daily.ru/daily_utf8.xml").toURL());

           currencyDto.getCurrencyDtoList().forEach(this::updateExchangeRates);
       } catch (JAXBException | MalformedURLException | URISyntaxException e) {
           log.error("Error in ExchangeRateUpdateService method getCurrentExchangeRate");
           e.printStackTrace();
       }
    }

    private void updateExchangeRates(CurrencyDto currencyDto) {
        Currency currency = currencyRepository.findByIsoCharCode(currencyDto.getIsoCharCode());
        if (currency != null) {
            currencyDto.setId(currency.getId());
            currencyService.create(currencyDto);
            log.info("ExchangeRateUpdateService method updateExchangeRates. Currency: {} updated", currencyDto.getIsoCharCode());
        } else {
            currencyService.create(currencyDto);
            log.info("ExchangeRateUpdateService method updateExchangeRates. New currency: {} created", currencyDto.getIsoCharCode());
        }
    }
}
