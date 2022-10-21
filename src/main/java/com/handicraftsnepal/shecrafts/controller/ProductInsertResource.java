package com.handicraftsnepal.shecrafts.controller;

import com.handicraftsnepal.shecrafts.entities.Product;
import com.handicraftsnepal.shecrafts.services.ProductServices;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/insertproduct")
public class ProductInsertResource {
    @Inject
    private ProductServices productServices;
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String insertProduct(Product product){
        productServices.insertProduct(product);
        return"product inserted successfully";
    }
////    @Produces(MediaType.TEXT_PLAIN)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public void insertProduct(Product product){
////        productServices.insertProduct(product);
//        System.out.println(product.toString());
////        return"product inserted successfully";
//    }
}
