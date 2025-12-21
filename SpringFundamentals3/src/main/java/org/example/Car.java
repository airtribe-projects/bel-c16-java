package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class Car {
  private String color;

  private String model;

  private Engine engine;

  public Car(@Value("${car.color}") String color, @Value("${car.model}") String model, Engine engine) {
    this.color = color;
    this.model = model;
    this.engine = engine;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public Engine getEngine() {
    return engine;
  }

  public void setEngine(Engine engine) {
    this.engine = engine;
  }

  public void printCar() {
    System.out.println("Car Model: " + model + ", Car Color: " + color + ", Engine Type: " + engine.getEngineType() + ", Engine Power: " + engine.getEnginePower());
  }
}

/// Application A -> PostgreeSql -> SQL
