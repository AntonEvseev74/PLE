package ру.евант.япе.библ;

import java.util.HashMap;
import java.util.Map;

/**
 * @author EvAnt
 */
public class Константы {

    private static final Map<String, Double> константы;

    static {
        константы = new HashMap<>();
        константы.put("PI", Math.PI);
        константы.put("ПИ", Math.PI);
        константы.put("E", Math.E);
        константы.put("GOLDEN_RATION", 1.618);
    }

    public static boolean этоСуществует(String ключ){
        return константы.containsKey(ключ);
    }

    public static Double получить(String ключ){
        if (!этоСуществует(ключ)) return 0.0;
        return константы.get(ключ);
    }
}
