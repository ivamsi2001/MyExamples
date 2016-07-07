package com.venkat.annotations.types;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target({ElementType.TYPE,ElementType.CONSTRUCTOR,ElementType.FIELD,ElementType.METHOD,ElementType.PARAMETER})
@Retention(RetentionPolicy.SOURCE)
public @interface BeanInfo {
	String description() default "";
	boolean expert() default false;
	boolean hidden() default false;
	boolean preferred() default false;
}
