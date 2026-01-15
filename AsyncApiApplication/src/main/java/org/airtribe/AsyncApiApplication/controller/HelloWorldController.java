package org.airtribe.AsyncApiApplication.controller;

import org.airtribe.AsyncApiApplication.dto.ProductsResult;
import org.airtribe.AsyncApiApplication.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloWorldController {

  @Autowired
  private DemoService _demoService;

  @GetMapping("/hello")
  public String helloWorld() {
    System.out.println("Thread handling /hello request: " + Thread.currentThread().getName());
    return "Hello, World!";
  }

  @GetMapping("/hello2")
  public String helloWorld2() {
    System.out.println("Thread handling /hello2 request: " + Thread.currentThread().getName());
    return _demoService.invokeHelloEndpoint();
  }

  @GetMapping("/products")
  public ProductsResult fetchProductsSync() {
    System.out.println("Thread handling /products request: " + Thread.currentThread().getName());
    return  _demoService.getProductsSync();
  }
}

