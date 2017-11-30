package com.example.demo.demo.controller;


import com.example.demo.demo.formModel.Person;
import com.example.demo.webConfig.businessException.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.Collections;

@RestController
@RequestMapping("/api")
public class TestController {


    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @GetMapping(value = "/person")
    public String getPerson(
            @RequestParam(value="id", defaultValue = "10") long id,
            @RequestParam(value="comment", defaultValue = "") String comment
    ) {

        if (comment == null || comment.isEmpty() ) {
            throw new BusinessException("commentRequired");
//            throw new BusinessException(1000, "comment 字段为空, 请重新输入", "comment");
        }



        logger.info("++++++++ This is an info message");


        return comment;
    }

    @GetMapping(value = "/person2")
    public Person getPerson2(
            @ModelAttribute Person person
    ) {

        if (true) {
            throw new ConstraintViolationException("error", Collections.emptySet());
        }

        logger.info("--------------");
        logger.info(person.getComment());

        return person;
    }




    @PostMapping(value = "/person")
    public ResponseEntity< Person > savePerson(
            @RequestBody @Valid Person person
    ) {

        logger.debug("This is a debug message");
        logger.info("This is an info message");
        logger.warn("This is a warn message2222");



        logger.error("This is an error message");


        return new ResponseEntity<Person> (person, HttpStatus.CREATED);
    }



    @PostMapping("/person/new")
    public Person savePerson2( @RequestBody @Valid Person person) {

        logger.info("-------------- This is an info message");

        return person;
    }


}
