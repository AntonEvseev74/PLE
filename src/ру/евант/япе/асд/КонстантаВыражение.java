package ру.евант.япе.асд;

import ру.евант.япе.библ.Константы;

/**
 * @author EvAnt
 */
public class КонстантаВыражение implements Выражение{

    private final String имя;

    public КонстантаВыражение(String имя) {
        this.имя = имя;
    }

    @Override
    public double вычислить() {
        if (!Константы.этоСуществует(имя)) throw new RuntimeException("Константа не существует");
        return Константы.получить(имя);
    }

    @Override
    public String toString() {
        //return  String.format("%s (%f)", имя, Константы.получить(имя));
        return  String.format("%s", имя);
    }
}
