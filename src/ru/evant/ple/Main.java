package ru.evant.ple;

import ru.evant.ple.ast.Expression;
import ru.evant.ple.ast.Statement;
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
        final String input6 = "x = 2 + 2";
        final List<Token> tokens = new Lexer(input6).tokenize();
        for (Token token : tokens) {
            System.out.println(token);
        }

        final List<Statement> statements = new Parser(tokens).parse();
        for (Statement statement:statements) {
            System.out.println(statement);
        }
        for (Statement statement:statements) {
            statement.execute();
        }
    }
}
