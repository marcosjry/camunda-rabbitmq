package com.execfy.teste.currency.connector.config;

import java.io.Serializable;

public class CurrencyResponseModel  implements Serializable {
    String USD;
    String BRL;
    String RON;

    public CurrencyResponseModel(){}

    public String getRON() {
        return RON;
    }

    public void setRON(String RON) {
        this.RON = RON;
    }

    public String getBRL() {
        return BRL;
    }

    public void setBRL(String BRL) {
        this.BRL = BRL;
    }

    public String getUSD() {
        return USD;
    }

    public void setUSD(String USD) {
        this.USD = USD;
    }


}
