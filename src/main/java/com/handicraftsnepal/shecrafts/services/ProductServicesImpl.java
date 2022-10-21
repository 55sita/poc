package com.handicraftsnepal.shecrafts.services;

import com.handicraftsnepal.shecrafts.entities.HandicraftBy;
import com.handicraftsnepal.shecrafts.entities.Product;
import com.handicraftsnepal.shecrafts.entities.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class ProductServicesImpl implements ProductServices{

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void insertProduct(Product product) {
//        entityManager.persist(product.getHandicraftBy());   //getHandicraftBy object must also be stored in database
        entityManager.persist(product);
    }

    @Override
    public void addProduct(int productId, int quantity) {
        Product product=entityManager.find(Product.class,productId);
        product.setQuantity(product.getQuantity()+quantity);
        entityManager.merge(product);
    }

    @Override
    public Product getProductById(int id) {

        return entityManager.find(Product.class,id);
    }

    @Override
    public List<Product> getAllProduct() {
//        return entityManager.createQuery("from product").getResultList();
//        entityManager.createQuery("select m from HandicraftBy m", HandicraftBy.class).getResultList();
//        return entityManager.createQuery("select e from Product e",Product.class).getResultList();
//        entityManager.createQuery("select m from HandicraftBy m", HandicraftBy.class).getResultList();
        TypedQuery<Product> query = entityManager.createQuery("select e from Product e", Product.class);
        return query.getResultList();

    }

    @Override
    public void updateProduct(int id,double price) {
        Product product=entityManager.find(Product.class,id);
        product.setPrice(price);

        entityManager.merge(product);
    }

    @Override
    public void deleteProductById(int id) {
        Product product=entityManager.find(Product.class,id);
//        entityManager.remove(product.getHandicraftBy());
        entityManager.remove(product);

    }

    @Override
    public void deleteMultipleProducts(List<Integer> ids) {
        for(int p:ids){
            Product product=entityManager.find(Product.class,p);
            entityManager.remove(product);
        }
    }
}
