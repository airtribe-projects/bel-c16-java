package org.airtribe.AsyncApiApplication.service;

import java.util.List;
import org.airtribe.AsyncApiApplication.dto.ProductsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Service
public class DemoService {
  @Autowired
  private RestTemplate _restTemplate;

  @Autowired
  private WebClient _webClient;

  public String invokeHelloEndpoint() {
    System.out.println("Thread invoking hello endpoint: " + Thread.currentThread().getName());
    String result = _restTemplate.getForObject("http://localhost:1400/hello", String.class);
    return result;
  }

  public ProductsResult getProductsSync() {
    ProductsResult result = _restTemplate.getForObject("https://dummyjson.com/products", ProductsResult.class);
    return result;
  }

  public Mono<ProductsResult> fetchProductsAsync() {
    Mono<ProductsResult> result = _webClient.get().uri("https://dummyjson.com/products").retrieve()
        .bodyToMono(ProductsResult.class)
        .doOnSuccess(apiResult -> {
          System.out.println("Thread handling the async products request: " + Thread.currentThread().getName());
        });

    for (int i = 0; i < 1000000; i++) {
      // Simulate some processing
      double temp = Math.sqrt(i) * Math.pow(i, 2);
    }

    System.out.println("Thread invoking async products endpoint: " + Thread.currentThread().getName());
    return result;
  }

  public ProductsResult getProductsWebClientSync() {
    ProductsResult result = _webClient.get().uri("https://dummyjson.com/products").retrieve()
        .bodyToMono(ProductsResult.class)
        .doOnSuccess(apiResult -> {
          System.out.println("Thread handling the async products request: " + Thread.currentThread().getName());
        }).block();

    return result;
  }

  public Mono<List<ProductsResult>> fetchProductsParallelAll() {
    Mono<ProductsResult> result1 = _webClient.get().uri("https://dummyjson.com/products").retrieve()
        .bodyToMono(ProductsResult.class);

    Mono<ProductsResult> result2 = _webClient.get().uri("https://dummyjson.com/products").retrieve()
        .bodyToMono(ProductsResult.class);

    Mono<ProductsResult> result3 = _webClient.get().uri("https://dummyjson.com/productsdhffgdsfswtw").retrieve()
        .bodyToMono(ProductsResult.class);

    Mono<List<ProductsResult>> results = Mono.zip(result1, result2, result3)
        .map(tuple -> List.of(tuple.getT1(), tuple.getT2(), tuple.getT3()))
        .doOnSuccess(list -> {
          System.out.println("Thread handling the async parallel products request: " + Thread.currentThread().getName());
        }).doOnError(error -> {
          System.err.println("Error occurred while fetching products in parallel: " + error.getMessage());
        });

    return results;
  }

  public Mono<ProductsResult> fetchProductsParallelSome() {
    Mono<ProductsResult> result1 = _webClient.get().uri("https://dummyjson.com/products").retrieve()
        .bodyToMono(ProductsResult.class);

    Mono<ProductsResult> result2 = _webClient.get().uri("https://dummyjson.com/products").retrieve()
        .bodyToMono(ProductsResult.class);

    Mono<ProductsResult> result3 = _webClient.get().uri("https://dummyjson.com/productsdhffgdsfswtw").retrieve()
        .bodyToMono(ProductsResult.class);

    Mono<ProductsResult> result = Mono.first(result1, result2, result3).
        doOnSuccess(apiResult -> {
          System.out.println("Thread handling the async fastest parallel products request: " + Thread.currentThread().getName());
        }).doOnError(error -> {
          System.err.println("Error occurred while fetching fastest product in parallel: " + error.getMessage());
        });

    return result;
  }

  public List<ProductsResult> fetchChainedProductsSync() {
    System.out.println("Thread invoking SYNC CHAINED endpoint: " + Thread.currentThread().getName());
    ProductsResult result1 = _restTemplate.getForObject("https://dummyjson.com/products", ProductsResult.class);

    ProductsResult result2 = _webClient.get().uri("https://dummyjson.com/products")
        .retrieve().bodyToMono(ProductsResult.class).block();

    ProductsResult result3 = _restTemplate.getForObject("https://dummyjson.com/products", ProductsResult.class);

    return List.of(result1, result2, result3);
  }

  public Mono<List<ProductsResult>> fetchChainedProductsAsync() {
    Mono<List<ProductsResult>> results = _webClient.get().uri("https://dummyjson.com/products").retrieve()
        .bodyToMono(ProductsResult.class)
        .doOnSuccess(apiResult -> {
          System.out.println("Thread handling the async products request: " + Thread.currentThread().getName());
        }).flatMap(firstResult -> {
          return _webClient.get().uri("https://dummyjson.com/products").retrieve()
              .bodyToMono(ProductsResult.class)
              .doOnSuccess(apiResult -> {
                System.out.println("Thread handling the async products request: " + Thread.currentThread().getName());
              }).flatMap(secondResult -> {
                return _webClient.get().uri("https://dummyjson.com/products").retrieve()
                    .bodyToMono(ProductsResult.class)
                    .doOnSuccess(apiResult -> {
                      System.out.println("Thread handling the async products request: " + Thread.currentThread().getName());
                    }).map(thirdResult -> {
                      return List.of(firstResult, secondResult, thirdResult);
                    });
              });
        });
    return results;
  }
}
