package ру.евант.япе;

import ру.евант.япе.асд.Выражение;
import ру.евант.япе.парсер.Лексер;
import ру.евант.япе.парсер.Парсер;
import ру.евант.япе.парсер.Токен;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        final String ввод = "2 + 2";
        final String ввод2 = "2 + 2 * 6";
        final String ввод3 = "#0E + #0F";
        final List<Токен> токены = new Лексер(ввод2).токенизировать();
        for (Токен токен : токены) {
            System.out.println(токен);
        }

        final List<Выражение> выражения = new Парсер(токены).разобрать();
        for (Выражение выражение : выражения) {
            System.out.println(выражение + " = " + выражение.вычислить());
        }
    }
}
