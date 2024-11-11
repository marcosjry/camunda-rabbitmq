package com.execfy.teste.currency.connector;

import camundajar.impl.com.google.gson.Gson;
import com.execfy.teste.currency.connector.config.CurrencyRateConnectorConfiguration;
import org.camunda.connect.impl.AbstractConnectorRequest;
import org.camunda.connect.spi.Connector;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class CurrencyRateRequest extends AbstractConnectorRequest<EmptyResponse> {
    private final CurrencyRateConnectorConfiguration configuration;

    public CurrencyRateRequest(
            Connector<?> connector,
            CurrencyRateConnectorConfiguration configuration) {
        super(connector);

        this.configuration = configuration;
    }

    public String buildRequestUrl() {
        return configuration.getApiUrl();
    }

    public String getConfiguration() {
        return configuration.getCurrencyCode();
    }

    public CurrencyRateResponse requestCreate() {
        try {
            HttpClient httpClient = HttpClient.newHttpClient();

            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create(buildRequestUrl()))
                    .build();

            try {
                HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
                if (response.statusCode() == 200) {
                    String jsonResponse = response.body();

                    Gson gson = new Gson();

                    return gson.fromJson(jsonResponse, CurrencyRateResponse.class);
                } else {
                    throw new RuntimeException("HTTP erro: " + response.statusCode());
                }
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException("Falha na requisição: " + e.getMessage(), e);
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("Erro ao tentar iniciar connector " + e);
        }
    }

    public CurrencyRateResponse requestCreateWithParameter() {
        try {
            HttpClient httpClient = HttpClient.newHttpClient();

            String urlParameter = getRequestParameter("url");
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create(urlParameter+getConfiguration()))
                    .build();

            try {
                HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
                if (response.statusCode() == 200) {
                    String jsonResponse = response.body();

                    Gson gson = new Gson();

                    return gson.fromJson(jsonResponse, CurrencyRateResponse.class);
                } else {
                    throw new RuntimeException("HTTP erro: " + response.statusCode());
                }
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException("Falha na requisição: " + e.getMessage(), e);
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("Erro ao tentar iniciar connector " + e);
        }
    }


}