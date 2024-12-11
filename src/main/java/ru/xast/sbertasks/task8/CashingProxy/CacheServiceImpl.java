package ru.xast.sbertasks.task8.CashingProxy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CacheServiceImpl implements CacheService {
    @Override
    public List<String> run(String item, double value, Date date) {
        List<String> result = new ArrayList<>();
        result.add("Result from run: " + item + " with value " + value + " at " + date);
        return result;
    }

    @Override
    public List<String> work(String item) {
       List<String> result = new ArrayList<>();
       result.add("Result from work: " + item);
       return result;
    }
}
