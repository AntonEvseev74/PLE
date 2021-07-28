package ру.евант.япе.асд;

/**
 * @autor EvAnt
 */

public final class УнарноеВыражение implements Выражение {

    private final char операция;
    private final Выражение выражение;

    public УнарноеВыражение(char операция, Выражение выражение) {
        this.операция = операция;
        this.выражение = выражение;
    }

    @Override
    public double вычислить() {
        switch (операция) {
            case '-':
                return -выражение.вычислить();
            case '+':
            default:
                return выражение.вычислить();
        }
    }

    @Override
    public String toString() {
        return String.format("%c %s", операция, выражение);
    }
}
