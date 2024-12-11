package ru.xast.sbertasks.task8.CashingProxy;


import java.util.Date;

public class Main {
    public static void main(String[] args) {
        CacheSettings cacheSettings = new CacheSettings(true);
        CacheProxy cacheProxy = new CacheProxy("cacheDir", cacheSettings);
        CacheService cacheService = cacheProxy.cache(new CacheServiceImpl());


        System.out.println(cacheService.run("first", 10, new Date()));
        System.out.println(cacheService.run("second", 20, new Date()));
        System.out.println(cacheService.run("third", 30, new Date()));
        System.out.println(cacheService.run("first", 10, new Date()));

    }
}
