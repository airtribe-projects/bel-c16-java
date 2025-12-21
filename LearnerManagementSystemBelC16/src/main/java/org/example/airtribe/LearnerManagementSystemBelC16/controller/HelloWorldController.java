package org.example.airtribe.LearnerManagementSystemBelC16.controller;

import org.example.airtribe.LearnerManagementSystemBelC16.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloWorldController {

  @Autowired
  private HelloWorldService helloWorldService;

  @GetMapping("/")
  public String helloWorld() {
    return helloWorldService.handleHelloRequest();
  }
}
