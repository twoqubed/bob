package com.twoqubed.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static com.twoqubed.annotation.ComplexityLevel.*;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

@Target(TYPE)
@Retention(RUNTIME)
public @interface Complexity {

    ComplexityLevel value() default MEDIUM;

}
