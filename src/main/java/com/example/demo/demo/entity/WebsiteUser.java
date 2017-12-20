package com.example.demo.demo.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "users")
public class WebsiteUser {


    @Id
    private String id;


    private String name;
    private String email;

    private String firstName;
    private String lastName;


    @DBRef
    private List<UserRole> roleList;

}
