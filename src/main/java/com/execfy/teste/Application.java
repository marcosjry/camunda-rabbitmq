package com.execfy.teste;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.execfy.teste.currency.connector")
public class Application {

  public static void main(String... args) {
    SpringApplication.run(Application.class, args);
  }

}