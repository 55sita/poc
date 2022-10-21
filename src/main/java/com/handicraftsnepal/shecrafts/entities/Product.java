package com.handicraftsnepal.shecrafts.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product implements Serializable {
    @Id
    @GeneratedValue
    private int productId;
    private String productName;
    private int quantity;
    private @PositiveOrZero double price;
    private String description;
    @ManyToOne(cascade = CascadeType.ALL)
//    @ManyToOne
    @JoinColumn(name="manufacturerId")
    private HandicraftBy handicraftBy;
    private String image;

}
//------------------This is the json format to be send----------------
//    "description": "Special Nepali scalf",
//            "handicraftBy": {
//        "experience": 4,
//                "itemSold": 4,
//                "manufacturerName": "Namita"
//    },
//            "image": "image of scalf",
//            "price": 4,
//            "productName": "scalf",
//            "quantity": 4
