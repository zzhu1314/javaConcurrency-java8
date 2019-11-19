package com.cigit.validate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @Author:zhuzhou
 * @Date: 2019/11/15  17:17
 **/
@Constraint(validatedBy = GenderValidate.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.FIELD})
@Documented
public @interface gender {
     String message() default "";
     Class<?>[] groups() default {};
     Class<? extends Payload>[] payload() default { };
}
