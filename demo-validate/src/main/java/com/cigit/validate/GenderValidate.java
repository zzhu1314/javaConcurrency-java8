package com.cigit.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @Author:zhuzhou
 * @Date: 2019/11/15  17:16
 **/
    public class GenderValidate implements ConstraintValidator<gender,Integer> {
    @Override
    public boolean isValid(Integer gender, ConstraintValidatorContext constraintValidatorContext) {
        if(gender==null||!(gender==1||gender==2)){
            return false;
        }
        return  true;
    }
}
