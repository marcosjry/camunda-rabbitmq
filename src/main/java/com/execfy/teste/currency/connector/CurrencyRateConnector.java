package com.execfy.teste.currency.connector;


import com.execfy.teste.currency.connector.config.CurrencyRateConnectorConfiguration;
import com.execfy.teste.currency.connector.config.CurrencyResponseModel;
import jakarta.persistence.Access;
import org.camunda.connect.impl.AbstractConnector;
import org.camunda.connect.spi.ConnectorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CurrencyRateConnector extends AbstractConnector<CurrencyRateRequest, EmptyResponse> {

    private static final Logger logger = LoggerFactory.getLogger(CurrencyRateConnector.class);

    private CurrencyResponseModel responseModel = new CurrencyResponseModel();

    @Autowired
    public final RabbitTemplate rabbitTemplate;

    public static final String CONNECTOR_ID = "CurrencyRateConnector";

    public CurrencyRateConnector(RabbitTemplate rabbitTemplate) {
        super(CONNECTOR_ID);
        this.rabbitTemplate = rabbitTemplate;
        logger.info("Template Connector: {}", rabbitTemplate);

    }

    @Override
    public CurrencyRateRequest createRequest() {

        //System.out.println("CreateRequest do Connector");
        CurrencyRateConnectorConfiguration config = new CurrencyRateConnectorConfiguration("BRL");
        return new CurrencyRateRequest(this, config);
    }

    @Override
    public ConnectorResponse execute(CurrencyRateRequest request) {

        try {

            CurrencyRateResponse currencyRateResponse = request.requestCreateWithParameter();
            this.responseModel.setBRL(currencyRateResponse.getRate().getBRL());
            this.responseModel.setUSD(currencyRateResponse.getRate().getUSD());
            this.responseModel.setRON(currencyRateResponse.getRate().getRON());

            CurrencyRateInvocation currencyInvocation = new CurrencyRateInvocation(currencyRateResponse, request, requestInterceptors);
            return currencyInvocation.invokeTarget();
        } catch (Exception e) {
            throw  new RuntimeException("Erro na execução do Executor" + e.getMessage());
        }


    }

    public void sendMessage(Object message) {
        try {
            // Log para verificar se o RabbitTemplate não é nulo
            System.out.println("Enviando mensagem para o RabbitMQ...");
            rabbitTemplate.convertAndSend("camundaQueue", message.toString());
            System.out.println("Mensagem enviada com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao enviar mensagem: " + e.getMessage());
        }
    }

    public RabbitTemplate getRabbitTemplate() {
        return this.rabbitTemplate;
    }

    public CurrencyResponseModel getResponseModel() {
        return this.responseModel;
    }
}