package com.epam.utilities.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/* These annotation is used to mark fields, that should be shown
 * on the table.
 * */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Viewed {
    String name() default "";
    int order() default 0;
}