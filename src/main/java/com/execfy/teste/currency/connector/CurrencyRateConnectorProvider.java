package com.execfy.teste.currency.connector;

import org.camunda.connect.spi.Connector;
import org.camunda.connect.spi.ConnectorProvider;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CurrencyRateConnectorProvider implements ConnectorProvider {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public String getConnectorId() {
        return CurrencyRateConnector.CONNECTOR_ID;
    }

    @Override
    public Connector<?> createConnectorInstance() {
        System.out.println("Template Provider: "+rabbitTemplate);
        return new CurrencyRateConnector(rabbitTemplate);
    }



}
