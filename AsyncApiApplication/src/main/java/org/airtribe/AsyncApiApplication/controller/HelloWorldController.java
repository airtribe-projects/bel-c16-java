package org.airtribe.AsyncApiApplication.controller;

import java.util.List;
import org.airtribe.AsyncApiApplication.dto.ProductsResult;
import org.airtribe.AsyncApiApplication.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


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

  @GetMapping("/products/async")
  public Mono<ProductsResult> fetchProductsAsync() {
    System.out.println("Thread handling /products/async request: " + Thread.currentThread().getName());
    return _demoService.fetchProductsAsync();
  }

  @GetMapping("/products/sync/webclient")
  public ProductsResult fetchProductsSyncWebClient() {
    System.out.println("Thread handling /products/sync/webclient request: " + Thread.currentThread().getName());
    return _demoService.getProductsWebClientSync();
  }

  @GetMapping("/products/parallel/all")
  public Mono<List<ProductsResult>> fetchProductsParallelAll() {
    System.out.println("Thread handling /products/parallel/all request: " + Thread.currentThread().getName());
    return _demoService.fetchProductsParallelAll();
  }

  @GetMapping("/products/parallel/some")
  public Mono<ProductsResult> fetchProductsParallelSome() {
    System.out.println("Thread handling /products/parallel/some request: " + Thread.currentThread().getName());
    return _demoService.fetchProductsParallelSome();
  }

  @GetMapping("/products/chained/sync")
  public List<ProductsResult> fetchChainedProductsSync() {
    System.out.println("Thread handling /products/chained/sync request: " + Thread.currentThread().getName());
    return _demoService.fetchChainedProductsSync();
  }

  @GetMapping("/products/chained/async")
  public Mono<List<ProductsResult>> fetchChainedProductsAsync() {
    System.out.println("Thread handling /products/chained/async request: " + Thread.currentThread().getName()); // http-mnio thread
    return _demoService.fetchChainedProductsAsync();
  }
}

