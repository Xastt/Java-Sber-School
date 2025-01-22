package ru.xast.sbertasks.task14;

public interface Source {
    void save(String key, Object value);
    Object get(String key);
}
