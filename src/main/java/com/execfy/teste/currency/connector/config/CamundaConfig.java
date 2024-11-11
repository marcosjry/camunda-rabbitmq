package com.execfy.teste.currency.connector.config;


import com.execfy.teste.services.listener.ResponseNotify;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CamundaConfig {

    @Bean
    public ResponseNotify responseNotify() {
        return new ResponseNotify();
    }

}
