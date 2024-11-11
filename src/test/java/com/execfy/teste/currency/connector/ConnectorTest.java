package com.execfy.teste.currency.connector;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
public class ConnectorTest {

    @Autowired
    private CurrencyRateConnector connector;

    @Autowired
    private RabbitTemplate rabbitTemplate;



    @Test
    public void testSendMessage() {
        // Verifica se o RabbitTemplate está configurado corretamente
        assertThat(rabbitTemplate).isNotNull();

        connector.sendMessage("Mensagem enviado pelo connector");

        System.out.println("Mensagem enviada com sucesso para a fila!");
    }

}
