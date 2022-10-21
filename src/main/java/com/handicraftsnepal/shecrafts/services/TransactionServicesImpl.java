package com.handicraftsnepal.shecrafts.services;

import com.handicraftsnepal.shecrafts.entities.Product;
import com.handicraftsnepal.shecrafts.entities.TransactionDetail;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
public class TransactionServicesImpl implements TransactionServices {
    @PersistenceContext
    private EntityManager entityManager;
    @Inject
    private TransactionDetail transactionDetail;

    @Override
    public TransactionDetail transactionDetailForCreate(Product product) {
        transactionDetail.setProductId(product.getProductId());
        transactionDetail.setProductName(product.getProductName());
        transactionDetail.setQuantity(product.getQuantity());
        transactionDetail.setTotalPrice(product.getPrice()*product.getQuantity());
        transactionDetail.setManufacturer(product.getHandicraftBy().getManufacturerName());
        return transactionDetail;
    }

    @Override
    public TransactionDetail transactionDetailForAdd(int productId, int quantity) {
        Product product=entityManager.find(Product.class,productId);
        transactionDetail.setProductId(productId);
        transactionDetail.setProductName(product.getProductName());
        transactionDetail.setQuantity(quantity);
        transactionDetail.setTotalPrice(product.getPrice()*quantity);
        transactionDetail.setManufacturer(product.getHandicraftBy().getManufacturerName());
        return transactionDetail;
    }
}
