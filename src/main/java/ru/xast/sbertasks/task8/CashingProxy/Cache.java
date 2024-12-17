package ru.xast.sbertasks.task8.CashingProxy;

import java.lang.annotation.*;

/**
 * Annotation for working with cash
 * @see CacheService
 * @author Khasrovyan Artyom
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Cache {
    CacheType cacheType() default CacheType.IN_MEMORY;
    String fileName() default "";
    boolean zip() default false;
    int listLimit() default Integer.MAX_VALUE;
    Class<?>[] identityBy() default {};
}

