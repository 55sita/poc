package com.handicraftsnepal.shecrafts.services;

import com.handicraftsnepal.shecrafts.entities.Product;

import java.util.List;

public interface ProductServices {
    //insert single product:POST(CREATE)
    public void insertProduct(Product product);
    //add product
    public void addProduct(int productId, int quantity);
    //gets single product:GET(READ)
    public Product getProductById(int id);
    //get multiple products:GET(READ)
    public List<Product> getAllProduct();
    //update single products:UPDATE(UPDATE)
    public void updateProduct(int id,double price);
    //delete single products:DELETE(DELETE)
    public void deleteProductById(int id);
    //delete multiple products
    public void deleteMultipleProducts(List<Integer> ids);
}
