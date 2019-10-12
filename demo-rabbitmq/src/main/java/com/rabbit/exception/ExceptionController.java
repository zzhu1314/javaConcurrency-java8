package com.rabbit.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author:zhuzhou
 * @Date: 2019/9/9  12:41
 **/
@RestControllerAdvice
public class ExceptionController {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> validate(MethodArgumentNotValidException ex){
        ex.printStackTrace();
         return new ResponseEntity<>(ex.getBindingResult().getFieldError().getDefaultMessage(), HttpStatus.OK);
    }
}
