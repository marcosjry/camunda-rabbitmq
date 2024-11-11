package com.execfy.teste.currency.connector.config;

import jakarta.annotation.PostConstruct;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class RabbitConfig {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private ProcessEngine processEngine;
/*
    @Bean
    @DependsOn("processEngine")
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        // Aqui você pode configurar o connectionFactory usando valores do application.yaml
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        System.out.println("Finalizando Connection Factory");
        return connectionFactory;
    }
    */

    @Bean
    public Queue camundaQueue() {
        return new Queue("camundaQueue", true); // Define a fila como durável
    }

    @RabbitListener(queues = "camundaQueue")
    public void receiveMessage(String message) {
        System.out.println("Mensagem recebida do RabbitMQ: " + message);
    }

}
