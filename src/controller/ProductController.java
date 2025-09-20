package controller;

import model.Product;

public interface ProductController {

    public Product createProduct();

    public void listAllProducts();

    public void updateProduct();

    public void searchBySku();
    
}
