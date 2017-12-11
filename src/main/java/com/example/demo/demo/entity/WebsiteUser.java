package com.example.demo.demo.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class WebsiteUser {


    @Id
    private String id;


    private String name;
    private String email;

    private String firstName;
    private String lastName;



}
