package gra.RodzajePrzedmiot;

import gra.ElementyPomocnicze.KolorTekstu;
import gra.ElementyPomocnicze.bron;

public class BronMagiczna extends Przedmiot implements bron {

    private final double obrazeniaBazowe;
    private final double mocUderzeniaKrytycznego;
    // moc zwoju okresla jaki % obrazen bazowych zadajemy, zakres 0-100
    private int mocZwoju;

    public BronMagiczna(String nazwa,
                        String opis,
                        int wartosc,
                        int szansaAtrybutu,
                        double obrazeniaBazowe,
                        double mocUderzeniaKrytycznego) {
        super(nazwa, opis, wartosc, szansaAtrybutu);
        this.obrazeniaBazowe = obrazeniaBazowe;
        this.mocUderzeniaKrytycznego = mocUderzeniaKrytycznego;
        mocZwoju = 100;
    }

    @Override
    public double zadajObrazenia() {
        double obrazenia = obrazeniaBazowe * mocZwoju / 100;
        boolean czyKrytyczne = (Math.random() < this.getAtrybut().getSzansaNaKrytyczne());
        mocZwoju -= 1;

        if (czyKrytyczne) {
            obrazenia *= mocUderzeniaKrytycznego;
            KolorTekstu.printCzerwony("Uderzenie krytyczne!");
        }
        return obrazenia;
    }

    @Override
    public double zadajMocneObrazenia() {
        if (Math.random() < 0.3) {
            KolorTekstu.printCzerwony("Twoje zaklecie nie trafilo wroga!");
            return 0;
        } else {
            // czar uzyty podwojnie
            return zadajObrazenia() + zadajObrazenia();
        }
    }

    @Override
    public String toString() {
        return getNazwa() + " [obrazenia: " + String.format("%1.2f", obrazeniaBazowe) + ", pozostala moc magiczna: " + mocZwoju +
                ", moc ud. krytycznego: " + String.format("%1.2f", (mocUderzeniaKrytycznego + 100)) + "%]";
    }
}
