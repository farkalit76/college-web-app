package org.usman.api.college.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class ApplicationServiceAspect {

//    @Before(value = "execution(* org.usman.api.trans.services.QiwaTestService.*(..))")
//    public void beforeAdvice(JoinPoint joinPoint){
//        log.info("before advice application aspect");
//    }
//
//    @After(value = "execution(* org.usman.test.services.QiwaTestService.*(..))")
//    public void afterAdvice(JoinPoint joinPoint){
//        log.info("after advice application aspect");
//    }
//
//    @AfterReturning(value = "execution(* org.usman.test.services.QiwaTestService.*(..))")
//    public void afterReturningAdvice(JoinPoint joinPoint){
//        log.info("afterReturning advice application aspect");
//    }

//    @Around(value = "execution(* org.usman.test.services.QiwaTestService.*(..))")
//    public void aroundAdvice(ProceedingJoinPoint joinPoint){
//        log.info("around advice application aspect");
//        try {
//            joinPoint.proceed();
//        }catch (Throwable e) {
//            log.error("around advice application aspect exception");
//        }
//    }
}
