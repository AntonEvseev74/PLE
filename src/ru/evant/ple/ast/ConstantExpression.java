package ru.evant.ple.ast;

import ru.evant.ple.lib.Constants;

/**
 * @author EvAnt
 */
public final class ConstantExpression implements Expression{

    private final String name;

    public ConstantExpression(String name) {
        this.name = name;
    }

    @Override
    public double eval() {
        if (!Constants.isExists(name)) throw new RuntimeException("Константа не существует");
        return Constants.get(name);
    }

    @Override
    public String toString() {
        //return  String.format("%s (%f)", name, Constants.get(name));
        return  String.format("%s", name);
    }
}
