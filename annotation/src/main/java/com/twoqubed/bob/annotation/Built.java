package com.twoqubed.bob.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

@Target({ TYPE  })
@Retention(SOURCE)
@Documented
/**
 * <p>Indicates a builder should be generated for the annotated class.</p>
 *
 * <p>The annotated class must have exactly one constructor that accepts parameters. No-arg constructors will
 * be ignored.</p>
 */
public @interface Built {

    /**
     * <p>Indicates whether a static copy method should be generated.</p>
     */
    boolean generateCopyMethod() default false;

}
