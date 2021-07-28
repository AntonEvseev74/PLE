package ru.evant.ple.lib;

import java.util.HashMap;
import java.util.Map;

/**
 * @author EvAnt
 */
public final class Constants {
    private static final Map<String, Double> constants;

    static {
        constants = new HashMap<>();
        constants.put("PI", Math.PI);
        constants.put("ПИ", Math.PI);
        constants.put("E", Math.E);
        constants.put("GOLDEN_RATION", 1.618);
    }

    public static boolean isExists(String key){
        return constants.containsKey(key);
    }

    public static Double get(String key){
        if (!isExists(key)) return 0.0;
        return constants.get(key);
    }
}
