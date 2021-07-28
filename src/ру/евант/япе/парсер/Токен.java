package ру.евант.япе.парсер;

/**
 * @autor EvAnt
 */

public class Токен {

    private ТокенТип тип;
    private String текст;

    public Токен() {
    }

    public Токен(ТокенТип тип, String текст) {
        this.тип = тип;
        this.текст = текст;
    }

    public ТокенТип получитьТип() {
        return тип;
    }

    public void установитьТип(ТокенТип тип) {
        this.тип = тип;
    }

    public String получитьТекст() {
        return текст;
    }

    public void установитьТекст(String текст) {
        this.текст = текст;
    }

    @Override
    public String toString() {
        return тип + " " + текст;
    }
}
