package com.example.simpleaffablebean.ds;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class CustomerInfo {

    private String name;
    private String email;
    private String address;
    private String phone;
    private int prague;
    private String creditCard;

}
