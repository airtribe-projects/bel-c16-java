package org.airtribe.AsyncApiApplication.service;

import org.airtribe.AsyncApiApplication.dto.ProductsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class DemoService {
  @Autowired
  private RestTemplate _restTemplate;

  public String invokeHelloEndpoint() {
    System.out.println("Thread invoking hello endpoint: " + Thread.currentThread().getName());
    String result = _restTemplate.getForObject("http://localhost:1400/hello", String.class);
    return result;
  }

  public ProductsResult getProductsSync() {
    ProductsResult result = _restTemplate.getForObject("https://dummyjson.com/products", ProductsResult.class);
    return result;
  }
}
