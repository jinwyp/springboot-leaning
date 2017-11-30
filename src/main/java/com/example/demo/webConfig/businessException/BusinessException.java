package com.example.demo.webConfig.businessException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.Map;

public class BusinessException extends RuntimeException{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private  int code;
    private  String errorMessage;
    private  String field;
    private  ErrorMessage errorMessageObject;


    public BusinessException(String codeName) {
        super("CodeName: " + codeName);

        try {
            this.errorMessageObject = this.readJson(codeName);
        } catch (IOException e) {

            logger.error("\n----------" + "Jackson Error: " + "\n");
            e.printStackTrace();
        }

        this.code = this.errorMessageObject.getCode();
        this.errorMessage = this.errorMessageObject.getMessage();
        this.field = this.errorMessageObject.getField();

    }


    public BusinessException(int code) {
        super("Code: " + code);
        this.code = code;
        this.errorMessage = "";
        this.field = "";
    }

    public BusinessException(int code, String errorMessage) {
        super("Message: " + errorMessage);
        this.code = code;
        this.errorMessage = errorMessage;
        this.field = "";
    }


    public BusinessException(int code, String errorMessage, String field) {
        super("Message: " + errorMessage);
        this.code = code;
        this.errorMessage = errorMessage;
        this.field = field;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }


//    private Map<Integer, String> data = new HashMap();
//    {
//        data.put(1000,"1111");
//        data.put(1111,"1111");
//        data.put(1111,"1111");
//    }





    public ErrorMessage readJson(String codeName) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String , ErrorMessage> empMap = objectMapper.readValue(new ClassPathResource("errorMessage.json").getInputStream(), new TypeReference<Map<String, ErrorMessage>>(){});

//        for (Map.Entry<?, ?> entry : empMap.entrySet()) {
//            logger.info("\n----------" + entry.getKey() + " : " + entry.getValue() + "\n");
//        }
//        logger.info("\n----------" + "codeName: " + codeName +  empMap.get(codeName) + "\n");

        return empMap.get(codeName);
    }

}
