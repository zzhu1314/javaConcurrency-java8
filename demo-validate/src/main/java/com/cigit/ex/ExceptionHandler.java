package com.cigit.ex;

import com.cigit.vo.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * @Author:zhuzhou
 * @Date: 2019/11/15  17:59
 * 参数非法异常校验
 **/
@RestControllerAdvice
public class ExceptionHandler {

    private final static Logger LOGGER = LoggerFactory.getLogger(ExceptionHandler.class);
    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public Response validateException(MethodArgumentNotValidException ex) {
        StringBuilder sb = new StringBuilder();
         /*
          *验证所有参数错误
         List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
          if(!CollectionUtils.isEmpty(fieldErrors)){
            for (FieldError fieldError : fieldErrors) {
                if(fieldError!=null|| !StringUtils.isEmpty(fieldError.getDefaultMessage())){
                    sb.append(fieldError.getDefaultMessage()).append(" ");
                }
            }
        }*/
        Response response = new Response();
        FieldError fieldError = ex.getBindingResult().getFieldError();
        if(fieldError!=null){
            String field = fieldError.getField();
            String message = fieldError.getDefaultMessage();
            response.setCode(400);
            response.setMessage(message);
            LOGGER.error("field:{} message:{}",field,message);
        }
        return response;
    }
}
