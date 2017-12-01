package com.example.demo.demo.formModel;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Person {

    @NotNull(message="{username.not.empty}")
    private  long id;

    @NotBlank
    @Size(min = 3, max = 10, message = "must be between 3 and 10 characters")
    private  String comment;



    public long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }


    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }




}
