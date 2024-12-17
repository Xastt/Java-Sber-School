package ru.xast.sbertasks.task8.CashingProxy;

/**
 * helps work with cache
 * @see CacheProxy
 * @author Khasrovyan Artyom
 */
public class CacheSettings {

    private final boolean zip;

    public CacheSettings(boolean zip) {
        this.zip = zip;
    }

    public boolean isZip() {
        return zip;
    }
}
