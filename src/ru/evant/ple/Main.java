package ru.evant.ple;

import ru.evant.ple.ast.Expression;
import ru.evant.ple.parser.Lexer;
import ru.evant.ple.parser.Parser;
import ru.evant.ple.parser.Token;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        final String input = "2 + 2";
        final String input2 = "(2 + 2) * 6";
        final String input3 = "#0E + #0F";
        final String input4 = "3.14 * 2";
        final String input5 = "PI * 2";
        final List<Token> tokens = new Lexer(input5).tokenize();
        for (Token token : tokens) {
            System.out.println(token);
        }

        final List<Expression> expressions = new Parser(tokens).parse();
        for (Expression expr:expressions) {
            System.out.println(expr + " = " + expr.eval());
        }
    }
}
