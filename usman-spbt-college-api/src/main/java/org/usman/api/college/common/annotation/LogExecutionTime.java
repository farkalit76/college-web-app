package org.usman.api.college.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Implementation Note : This class is to implement the ...
 *
 * @author UsmanFarkalit
 * @date 28-03-2024
 * @since 1.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface LogExecutionTime {

}