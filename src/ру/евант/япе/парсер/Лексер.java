package ру.евант.япе.парсер;

// Урок https://vk.com/bookmarks?from_menu=1&z=video-101965347_456279297%2F5a3c8b15329510d21b%2Fpl_post_-3183750_349237

import java.util.ArrayList;
import java.util.List;

/**
 * @autor EvAnt
 */

public class Лексер {

    private static final String ОПЕРАТОР_СИМВОЛ = "+-*/()";
    private static final ТокенТип[] ОПЕРАТОР_ТОКЕНЫ = {
            ТокенТип.ПЛЮС, ТокенТип.МИНУС, ТокенТип.УМНОЖИТЬ, ТокенТип.РАЗДЕЛИТЬ,
            ТокенТип.ЛСКОБКА, ТокенТип.ПСКОБКА,
    };

    private final String ввод;
    private final int длина;

    private final List<Токен> токены;

    private int поз;

    public Лексер(String ввод) {
        this.ввод = ввод;
        длина = ввод.length();

        токены = new ArrayList<>();
    }

    public List<Токен> токенизировать() {
        while (поз < длина) {
            final char текущий = peek(0);
            if (Character.isDigit(текущий)) {
                токенизироватьЧисло();
            } else if (текущий == '#') {
                дальше();
                токенизироватьШЧисло();
            } else if (ОПЕРАТОР_СИМВОЛ.indexOf(текущий) != -1) {
                токенизироватьОператор();
            } else {
                // могут быть пробелы
                дальше();
            }
        }
        return токены;
    }

    private void токенизироватьШЧисло() {
        final StringBuilder буфер = new StringBuilder();
        char текущий = peek(0);
        while (Character.isDigit(текущий) || этоШЧисло(текущий)) {
            буфер.append(текущий);
            текущий = дальше();
        }
        добавитьТокен(ТокенТип.Ш_ЧИСЛО, буфер.toString());
    }

    private static boolean этоШЧисло(char текущий) {
        return "abcdef".indexOf(Character.toLowerCase(текущий)) != -1;
    }

    private void токенизироватьЧисло() {
        final StringBuilder буфер = new StringBuilder();
        char текущий = peek(0);
        while (Character.isDigit(текущий)) {
            буфер.append(текущий);
            текущий = дальше();
        }
        добавитьТокен(ТокенТип.ЧИСЛО, буфер.toString());
    }

    private void токенизироватьОператор() {
        final int позиция = ОПЕРАТОР_СИМВОЛ.indexOf(peek(0));
        добавитьТокен(ОПЕРАТОР_ТОКЕНЫ[позиция]);
        дальше();
    }

    private char дальше() {
        поз++;
        return peek(0);
    }

    private char peek(int относительноеПоложение) {
        final int позиция = поз + относительноеПоложение;
        if (позиция >= длина) return '\0';
        return ввод.charAt(позиция);
    }

    private void добавитьТокен(ТокенТип тип) {
        добавитьТокен(тип, "");
    }

    private void добавитьТокен(ТокенТип тип, String текст) {
        токены.add(new Токен(тип, текст));
    }
}
