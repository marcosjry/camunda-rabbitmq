package com.execfy.teste.services.listener;


import com.execfy.teste.currency.connector.CurrencyRateConnector;
import com.execfy.teste.currency.connector.CurrencyRateResponse;
import com.execfy.teste.currency.connector.config.CurrencyResponseModel;
import jakarta.persistence.Access;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.impl.ProcessEngineImpl;
import org.camunda.connect.Connectors;
import org.camunda.connect.spi.Connector;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class ResponseNotify implements ExecutionListener {

    @Autowired
    private ApplicationContext context;

    @Override
    public void notify(DelegateExecution execution) throws Exception {

        CurrencyRateConnector connector = context.getBean(CurrencyRateConnector.class);

        String message = connector.getResponseModel().toString();
        System.out.println(connector.getRabbitTemplate());
        connector.sendMessage(message);

    }
}
