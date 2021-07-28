package ру.евант.япе.парсер;

import ру.евант.япе.асд.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @autor EvAnt
 */

public final class Парсер {

    private static final Токен КФ = new Токен(ТокенТип.КФ, "");

    private final List<Токен> токены;
    private final int размер;
    private int поз;

    public Парсер(List<Токен> токены) {
        this.токены = токены;
        размер = токены.size();
    }

    public List<Выражение> разобрать() {
        final List<Выражение> result = new ArrayList<>();
        while (!совпадение(ТокенТип.КФ)){
            result.add(выражение());
        }
        return result;
    }

    private Выражение выражение() {
        return сложитьВычесть();
    }

    private Выражение сложитьВычесть() {
        Выражение result = умножитьДелить();
        while (true){
            // 2 + 6 - 3
            if (совпадение(ТокенТип.ПЛЮС)){
                result = new БинарноеВыражение('+', result, умножитьДелить());
                continue;
            }
            if (совпадение(ТокенТип.МИНУС)){
                result = new БинарноеВыражение('-', result, умножитьДелить());
                continue;
            }
            break;
        }
        return result;
    }

    private Выражение умножитьДелить() {
        Выражение result = унарное();
        while (true){
            // 2 * 6 / 3
            if (совпадение(ТокенТип.УМНОЖИТЬ)){
                result = new БинарноеВыражение('*', result, унарное());
                continue;
            }
            if (совпадение(ТокенТип.РАЗДЕЛИТЬ)){
                result = new БинарноеВыражение('/', result, унарное());
                continue;
            }
            break;
        }
        return result;
    }

    private Выражение унарное() {
       if (совпадение(ТокенТип.МИНУС)){
           return new УнарноеВыражение('-', первичное());
       }
        if (совпадение(ТокенТип.ПЛЮС)){
            return первичное();
        }
        return первичное();
    }

    private Выражение первичное() {
        final Токен текущий = получить(0);
        if (совпадение(ТокенТип.ЧИСЛО)){
            return new ЧисловоеВыражение(Double.parseDouble(текущий.получитьТекст()));
        }
        if (совпадение(ТокенТип.Ш_ЧИСЛО)){
            return new ЧисловоеВыражение(Long.parseLong(текущий.получитьТекст(), 16));
        }
        if (совпадение(ТокенТип.СЛОВО)){
            return new КонстантаВыражение(текущий.получитьТекст());
        }
        if (совпадение(ТокенТип.ЛСКОБКА)){
            Выражение результат = выражение();
            совпадение(ТокенТип.ПСКОБКА);
            return результат;
        }
        throw new RuntimeException("неизвестное выражение");
    }

    private boolean совпадение(ТокенТип тип) {
        final Токен текущий = получить(0);
        if (тип != текущий.получитьТип()) return false;
        поз++;
        return true;
    }

    private Токен получить(int relativePosition) {
        final int позиция = поз + relativePosition;
        if (позиция >= размер) return КФ;
        return токены.get(позиция);
    }
}
