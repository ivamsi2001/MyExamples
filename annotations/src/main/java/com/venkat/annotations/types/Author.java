package com.venkat.annotations.types;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({TYPE, FIELD, METHOD})
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface Author {
	String authorName() default "";
	String title() default "";
}
