package task2.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


    @Target(value = ElementType.FIELD)
    @Retention(value = RetentionPolicy.RUNTIME)
    public @interface Property {

        String name() default "";

        String format() default "";
    }

