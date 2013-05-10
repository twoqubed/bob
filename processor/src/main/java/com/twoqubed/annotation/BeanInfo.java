package com.twoqubed.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

@Target({ TYPE, FIELD, METHOD })
@Retention(RUNTIME)
public @interface BeanInfo {

}
