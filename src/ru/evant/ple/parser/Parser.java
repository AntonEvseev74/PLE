package ru.evant.ple.parser;

import ru.evant.ple.ast.*;

import java.util.ArrayList;
import java.util.List;

public final class Parser {

    private static final Token EOF = new Token(TokenType.EOF, "");

    private final List<Token> tokens;
    private final int size;
    private int pos;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
        size = tokens.size();
    }

    public List<Statement> parse() {
        final List<Statement> result = new ArrayList<>();
        while (!match(TokenType.EOF)){
            result.add(statement());
        }
        return result;
    }

    private Statement statement() {
        return assignmentStatement();
    }

    private Statement assignmentStatement() {
        // WORD, EQ
        final Token current = get(0);
        if (current.getType() == TokenType.WORD && get(1).getType() == TokenType.EQ){
            match(TokenType.WORD);
            final String variable = current.getText();
            match(TokenType.EQ);
            return new AssignmentStatement(variable,expression());
        }
        throw new RuntimeException("неизвестный оператор");
    }

    private Expression expression() {
        return additive();
    }

    private Expression additive() {
        Expression result = multiplicative();
        while (true){
            // 2 + 6 - 3
            if (match(TokenType.PLUS)){
                result = new BinaryExpression('+', result, multiplicative());
                continue;
            }
            if (match(TokenType.MINUS)){
                result = new BinaryExpression('-', result, multiplicative());
                continue;
            }
            break;
        }
        return result;
    }

    private Expression multiplicative() {
        Expression result = unary();
        while (true){
            // 2 * 6 / 3
            if (match(TokenType.MULTIPLY)){
                result = new BinaryExpression('*', result, unary());
                continue;
            }
            if (match(TokenType.DIVIDE)){
                result = new BinaryExpression('/', result, unary());
                continue;
            }
            break;
        }
        return result;
    }

    private Expression unary() {
       if (match(TokenType.MINUS)){
           return new UnaryExpression('-', primary());
       }
        if (match(TokenType.PLUS)){
            return primary();
        }
        return primary();
    }

    private Expression primary() {
        final Token current = get(0);
        if (match(TokenType.NUMBER)){
            return new NumberExpression(Double.parseDouble(current.getText()));
        }
        if (match(TokenType.HEX_NUMBER)){
            return new NumberExpression(Long.parseLong(current.getText(), 16));
        }
        if (match(TokenType.WORD)){
            return new ConstantExpression(current.getText());
        }
        if (match(TokenType.LPAREN)){
            Expression result = expression();
            match(TokenType.RPAREN);
            return result;
        }
        throw new RuntimeException("неизвестное выражение");
    }

    private boolean match(TokenType type) {
        final Token current = get(0);
        if (type != current.getType()) return false;
        pos++;
        return true;
    }

    private Token get(int relativePosition) {
        final int position = pos + relativePosition;
        if (position >= size) return EOF;
        return tokens.get(position);
    }
}
