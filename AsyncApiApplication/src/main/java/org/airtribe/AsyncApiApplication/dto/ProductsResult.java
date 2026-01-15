package org.airtribe.AsyncApiApplication.dto;

import java.util.List;


public class ProductsResult {

  private List<Measurement> products;

  public ProductsResult(List<Measurement> products) {
    this.products = products;
  }

  public List<Measurement> getProducts() {
    return products;
  }

  public void setProducts(List<Measurement> products) {
    this.products = products;
  }
}
