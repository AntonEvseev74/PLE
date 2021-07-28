package ру.евант.япе.асд;

/**
 * @autor EvAnt
 */

public final class БинарноеВыражение implements Выражение {

    private final char операция;
    private final Выражение выражение1, выражение2;

    public БинарноеВыражение(char операция, Выражение выражение1, Выражение выражение2) {
        this.операция = операция;
        this.выражение1 = выражение1;
        this.выражение2 = выражение2;
    }

    @Override
    public double вычислить() {
        switch (операция) {
            case '-':
                return выражение1.вычислить() - выражение2.вычислить();
            case '*':
                return выражение1.вычислить() * выражение2.вычислить();
            case '/':
                return выражение1.вычислить() / выражение2.вычислить();
            case '+':
            default:
                return выражение1.вычислить() + выражение2.вычислить();
        }
    }

    @Override
    public String toString() {
        return String.format("%s %c %s", выражение1, операция, выражение2);
    }
}
