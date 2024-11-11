package com.execfy.teste.currency.connector.config;

public class CurrencyRateConnectorConfiguration implements CurrencyRateConfiguration {

    private String currencyCode;
    private String apiUrl;

    public CurrencyRateConnectorConfiguration(String currencyCode) {
        this.currencyCode = currencyCode;
        this.apiUrl = "https://api.exchangerate-api.com/v4/latest/" + currencyCode;
    }

    @Override
    public String getCurrencyCode() {
        return currencyCode;
    }

    @Override
    public String getApiUrl() {
        return apiUrl;
    }
}
