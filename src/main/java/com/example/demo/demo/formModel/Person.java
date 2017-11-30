package com.example.demo.demo.formModel;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public class Person {

    @NotNull(message="{username.not.empty}")
    private  long id;

    @NotBlank
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
