package ru.xast.sbertasks.task14;

/**
 * interface defines a contract for data sources,
 * which allow to save and retrieve values for given keys.
 */
public interface Source {
    void save(String key, Object value);
    Object get(String key);
}
