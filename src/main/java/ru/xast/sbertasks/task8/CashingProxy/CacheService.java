package ru.xast.sbertasks.task8.CashingProxy;

import java.util.*;

public interface CacheService {
    @Cache(cacheType = CacheType.FILE, fileName = "data", zip = true, identityBy = {String.class, double.class})
    List<String> run(String item, double value, Date date);

    @Cache(cacheType = CacheType.IN_MEMORY, listLimit = 100_000)
    List<String> work(String item);
}
