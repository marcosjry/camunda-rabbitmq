package com.execfy.teste.currency.connector;


import com.execfy.teste.currency.connector.config.CurrencyResponseModel;
import org.camunda.connect.impl.AbstractConnectorResponse;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class CurrencyRateResponse extends AbstractConnectorResponse {

    public static final String PARAM_CRC_RESPONSE = "response";
    private CurrencyResponseModel rates;

    Map<String, Object> parameters;

    public CurrencyRateResponse() {
        this.parameters = new HashMap<>();
    }

    @Override
    public Map<String, Object> getResponseParameters() {
        if (this.rates != null) {
            parameters.put("USD", this.rates.getUSD());
            parameters.put("BRL", this.rates.getBRL());
            parameters.put("RON", this.rates.getRON());

        } else {
            // Log ou mensagem para verificar se rates está nulo
            System.out.println("CurrencyRateResponse: rates está nulo.");
        }
        return parameters;
    }

    public CurrencyResponseModel getRate() {
        return rates;
    }

    @Override
    protected void collectResponseParameters(Map<String, Object> responseParameters) {
        if (responseParameters != null) {
            responseParameters.putAll(this.getResponseParameters());
        } else {
            // Log ou mensagem para verificar se responseParameters está nulo
            System.out.println("CurrencyRateResponse: responseParameters está nulo.");
        }
    }
}
