/*
 * Copyright (c)
 *
 * Copyright (c) 2020, 2025, Takamol and its affiliates. All rights reserved.
 * Use is subject to license terms.
 */

package org.usman.api.college.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Implementation Note : This class is to implement the ...
 *
 * @author UsmanFarkalit
 * @date 06-03-2024
 * @since 1.0
 */

@Slf4j
@Aspect
@Component
public class LoggingAspectConfig {

    @Pointcut("within(@org.springframework.stereotype.Repository *)"
            + " || within(@org.springframework.stereotype.Service *)"
            + " || within(@org.springframework.web.bind.annotation.RestController *)")
    public void springBeanPointcut() {
        throw new UnsupportedOperationException();
    }

    @Pointcut("within(org.usman.api.schools.repository..*)"
            + " || within(org.usman.api.schools.service..*)"
            + " || within(org.usman.api.schools.apis..*)")
    public void applicationPackagePointcut() {
        throw new UnsupportedOperationException();
    }

    @AfterThrowing(pointcut = "applicationPackagePointcut() && springBeanPointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        log.error("Exception in {}.{}() with cause = {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), e.getCause() != null ? e.getMessage() : "NULL");
    }

    @Around("applicationPackagePointcut() && springBeanPointcut()")
    public Object invoke(ProceedingJoinPoint joinPoint) throws Throwable {
        final String joinPoints = Arrays.toString(joinPoint.getArgs());
        if (joinPoints != null) {
            log.info("Application Enter: {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(), joinPoints);
        }
        final Object result = joinPoint.proceed();
        log.info("Application Exit: {}.{}() with result = {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), result);
        return result;
    }

}
