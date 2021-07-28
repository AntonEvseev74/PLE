package ru.evant.ple.ast;

public final class UnaryExpression implements Expression{

    private final char operation;
    private final Expression exp1;

    public UnaryExpression(char operation, Expression exp1) {
        this.operation = operation;
        this.exp1 = exp1;
    }

    @Override
    public double eval() {
        switch (operation) {
            case '-':
                return -exp1.eval();
            case '+':
            default:
                return exp1.eval();
        }
    }

    @Override
    public String toString() {
        return String.format("%c %s", operation, exp1);
    }
}
