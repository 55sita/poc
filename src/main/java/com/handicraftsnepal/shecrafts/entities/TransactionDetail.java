package com.handicraftsnepal.shecrafts.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TransactionDetail implements Serializable {
    @Id
    @GeneratedValue
    private int transactionDetailId;
//    @OneToMany(mappedBy = "manufacturerId",cascade = CascadeType.ALL)
//    private List<HandicraftBy> manufacturer;
//    @OneToMany(mappedBy = "productId",cascade = CascadeType.ALL)
//    private List<Product> product;
    private String manufacturer;
    private String productName;
    private int productId;
    private int quantity;
    private double totalPrice;

}
