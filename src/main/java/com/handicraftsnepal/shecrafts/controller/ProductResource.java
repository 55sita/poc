package com.handicraftsnepal.shecrafts.controller;

import com.handicraftsnepal.shecrafts.entities.Product;
import com.handicraftsnepal.shecrafts.entities.TransactionDetail;
import com.handicraftsnepal.shecrafts.services.ProductServices;
import com.handicraftsnepal.shecrafts.services.Role;
import com.handicraftsnepal.shecrafts.services.Secured;
import com.handicraftsnepal.shecrafts.services.TransactionServices;

import javax.inject.Inject;
import javax.validation.constraints.PositiveOrZero;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//@Transactional
@Path("/product")
public class ProductResource {
    @Inject
    private ProductServices productServices;
    @Inject
    private TransactionServices transactionServices;

    //--------------------create new product--------------------------
    @POST
    @Path("/create")
    @Secured({Role.SELLER})
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public TransactionDetail insert_product(Product product){
        productServices.insertProduct(product);
        return transactionServices.transactionDetailForCreate(product);
    }


    //------------------add some products-------------------
    @POST
    @Path("/add/{pid}/{quantity}")
    @Secured({Role.SELLER})
    @Produces(MediaType.APPLICATION_JSON)
    public TransactionDetail add_product(@PathParam("pid") @PositiveOrZero int pid,@PathParam("quantity") @PositiveOrZero int quantity){
        productServices.addProduct(pid,quantity);
        return transactionServices.transactionDetailForAdd(pid,quantity);
    }

//    ------------------get product by id----------------
    @GET
//    @Secured(Role.SELLER)
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Product get_product_by_id(@PathParam("id") @PositiveOrZero int productId){
        return productServices.getProductById(productId);
    }

//    ------------------get all products-----------------
    @GET
//    @Secured({Role.SELLER})
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> get_all_products(){
        return productServices.getAllProduct();
    }

//    ------------------update single product-----------
    @PUT
    @Secured({Role.SELLER})
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}/{price}")
    public String update_product(@PathParam("id") @PositiveOrZero int productId,@PathParam("price") @PositiveOrZero double price){
        productServices.updateProduct(productId,price);
        return "product is updated successfully";
    }

//    ------------------delete single product by id------------
    @DELETE
    @Secured
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String delete_product_by_id(@PathParam("id") @PositiveOrZero String productId){
        String[] split = productId.split(";");

        List<Integer> collect = Arrays.stream(split).map(e -> Integer.valueOf(e)).collect(Collectors.toList());

        productServices.deleteMultipleProducts(collect);
        return "product deleted successfully";
    }

//    //--------------------delete user-------------------------
//    @DELETE
//    @Path("/user/{userId}")
//
}
