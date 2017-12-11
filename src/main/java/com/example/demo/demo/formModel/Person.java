package com.example.demo.demo.formModel;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class Person {

    @NotNull(message="{username.not.empty}")
    private  long id;

    @NotBlank
    @Size(min = 3, max = 10, message = "must be between 3 and 10 characters")
    private  String comment;



}
