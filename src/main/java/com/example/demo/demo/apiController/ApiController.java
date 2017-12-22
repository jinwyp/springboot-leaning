package com.example.demo.demo.apiController;


import com.example.demo.demo.formModel.Person;
import com.example.demo.webConfig.businessException.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Value("${global.name}")
    private String globalName;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping(value = "/err")
    public String getErr() throws Exception{

        throw new Exception("发生错误");
    }


    @GetMapping(value = "/person")
    public String getPerson(
            @RequestParam(value="id") long id,
            @RequestParam(value="comment", required = true) String comment
    ) {

        if (comment == null || comment.isEmpty() ) {
            throw new BusinessException("commentRequired");
//            throw new BusinessException(1000, "comment 字段为空, 请重新输入", "comment");
        }


        return comment;
    }

    @GetMapping(value = "/person2")
    public Person getPerson2(
            @ModelAttribute Person person
    ) {

        if (person.getComment() == null || person.getComment().isEmpty() ) {
            throw new BusinessException("commentRequired");
//            throw new BusinessException(1000, "comment 字段为空, 请重新输入", "comment");
        }

        logger.info("--------------");
        logger.info(person.getComment());

        return person;
    }




    @PostMapping(value = "/person")
    public Person savePerson(@RequestBody @Valid Person person
    ) {

        logger.debug("This is a debug message");
        logger.info("This is an info message");
        logger.warn("This is a warn message2222");

        logger.error("This is an error message");


        return person;
    }



    @PostMapping("/article")
    public Person savePerson2( @RequestBody @Valid Person person) {

        logger.info("-------------- This is an info message");

        return person;
    }




}
