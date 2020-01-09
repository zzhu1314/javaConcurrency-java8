package com.cigit.aop;

import com.cigit.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

/**
 * @Author:zhuzhou
 * @Date: 2020/1/9  9:55
 * 切面校验处理参数异常
 **/
@Aspect
@Component
@Slf4j
public class DataValidAspect {

    @Pointcut("execution(* com.cigit.controller..*.*(..))")
    public void poincut(){

    }

    /**
     * 开启环绕通知，若不报错，继续执行业务代码
     * @param point
     * @return
     */
    @Around(value = "poincut()")
    public Object validate(ProceedingJoinPoint point){
        Object proceed = null;
        try {
            log.debug("校验切面介入工作");
            Object[] args = point.getArgs();
            for (Object arg : args) {
                if(arg instanceof BindingResult){
                    BindingResult bindingResult = (BindingResult)arg;
                    if(bindingResult.getErrorCount()>0){
                        //框架自动检验到错误了
                        ObjectError objectError = bindingResult.getAllErrors().get(0);
                        String defaultMessage = objectError.getDefaultMessage();
                        return new Response(500,defaultMessage);
                    }
                }
            }
            //反射 method.invoke();
            proceed = point.proceed(args);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            log.debug("异常通知");
        }finally {
            log.debug("后置通知");
        }
        return proceed;
    }
}
