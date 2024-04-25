package ru.skillbox.currency.exchange.service;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class UnmarshalDoubleAdapter extends XmlAdapter<String, Double> {

    @Override
    public Double unmarshal(String value) {
        if(value == null) {
            return null;
        }

        value = value.replace(",", ".");
        return Double.valueOf(value);
    }

    @Override
    public String marshal(Double value) {
        if(value == null) {
            return null;
        }
        return String.valueOf(value);
    }

}
