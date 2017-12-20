package com.example.demo.demo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "userroles")
public class UserRole {

    @Id
    private String id;


    private String roleName;

}
