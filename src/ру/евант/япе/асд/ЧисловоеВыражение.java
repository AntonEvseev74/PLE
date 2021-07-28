package ру.евант.япе.асд;

/**
 * @autor EvAnt
 */

public final class ЧисловоеВыражение implements Выражение {

    private final double значение;

    public ЧисловоеВыражение(double значение) {
        this.значение = значение;
    }

    @Override
    public double вычислить() {
        return значение;
    }

    @Override
    public String toString() {
        return Double.toString(значение);
    }
}
