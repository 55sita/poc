package com.handicraftsnepal.shecrafts.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_info")
public class User implements Serializable {
    @Id
    @GeneratedValue
    private int userId;
    private String userName;
    private String password;
    private String role;
    private String address;
    private String phonenum;
    private String email;

}
//        "userName": "kamala",
//         "password": "1234",
//         "role": "SELLER",
//         "address": "lalitpur",
//         "phonenum": "9811111111",
//         "email": "kamala@gmail.com"
