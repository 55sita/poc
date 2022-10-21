package com.handicraftsnepal.shecrafts.services;

import com.handicraftsnepal.shecrafts.entities.Product;
import com.handicraftsnepal.shecrafts.entities.TransactionDetail;

public interface TransactionServices {
    public TransactionDetail transactionDetailForCreate(Product product);
    public TransactionDetail transactionDetailForAdd(int ProductId, int quantity);
}
