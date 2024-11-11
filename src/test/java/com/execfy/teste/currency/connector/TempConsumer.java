package com.execfy.teste.currency.connector;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TempConsumer {


    @RabbitListener(queues = "camundaQueue")
    public void receiveMessage(String message) {
        System.out.println("Mensagem recebida pela fila: " + message);
    }

}
