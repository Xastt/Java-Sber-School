package ru.xast.sbertasks.task14;

import java.lang.annotation.*;

/**
 * The {@code @Cachable} annotation is used to note methods
 * the results of which can be cached.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Cachable {
    Class<? extends Source> value();
}
