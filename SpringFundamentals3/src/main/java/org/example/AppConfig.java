package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;


@Configuration
@ComponentScan("org.example")
@PropertySource("classpath:application.properties")
public class AppConfig {

//  @Bean
//  public Engine engine1() {
//    Engine engine = new Engine();
//    engine.setEngineType("V6");
//    engine.setEnginePower("3000");
//    return engine;
//  }
//
//  @Bean
//  public Car car1() {
//    return new Car("Toyota", "Camry", engine1());
//  }
//
//  @Bean
//  public Car car2() {
//    return new Car("Honda", "Accord", engine1());
//  }
}
