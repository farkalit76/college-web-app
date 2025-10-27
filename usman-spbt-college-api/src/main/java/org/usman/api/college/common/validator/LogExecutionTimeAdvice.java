package org.usman.api.college.common.validator;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

/**
 * Implementation Note : This class is to implement the ...
 *
 * @author UsmanFarkalit
 * @date 28-03-2024
 * @since 1.0
 */
@Slf4j
@Aspect
@Component
@ConditionalOnExpression("${aspect.enabled:true}")
public class LogExecutionTimeAdvice {

    /**
     * This will be applied on class level either at Controller, Service or Repository Level.
     * @param point
     * @return
     * @throws Throwable
     */
    @Around(("execution(* (@org.usman.api.schools.common.annotation.LogExecutionTime *).*(..))"))
    public Object executionTime(ProceedingJoinPoint point) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object object = point.proceed();
        long endTime = System.currentTimeMillis();
        log.info("API_CLASS Name : {}, Method Name:{}, TOTAL_EXECUTION_TIME_IS :{}  {} ",
                point.getSignature().getDeclaringTypeName(), point.getSignature().getName(),
                (endTime - startTime), " ms");
        return object;
    }

    /**
     * This will be applied on method level of any class.
     *
     * @param point
     * @return
     * @throws Throwable
     */
    @Around("@annotation(org.usman.api.schools.common.annotation.LogMethodExecutionTime)")
    public Object methodExecutionTime(ProceedingJoinPoint point) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object object = point.proceed();
        long endTime = System.currentTimeMillis();
        log.info("API_CLASS NAME For API : {}, Method Name:{}, TOTAL_EXECUTION_TIME_IS :{}  {} ",
                point.getSignature().getDeclaringTypeName(), point.getSignature().getName(),
                (endTime - startTime), " ms");
        return object;
    }
}
