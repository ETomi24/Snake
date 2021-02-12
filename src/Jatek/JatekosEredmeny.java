package Jatek;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * JatekosEredmeny osztaly eltarolja a jatekos nevet illetve pontszamat amit elert es a datumot amikor azt elerte.
 */
public class JatekosEredmeny implements Serializable ,Comparable<JatekosEredmeny>{
    /**
     * A pontszam amit elert a jatek soran
     */
    private int pont;
    /**
     * A jatekos neve amit megadott, ha nem akkor uresen marad
     */
    private String name;
    /**
     * A datum amikor jatszotta a jatekot
     */
    private LocalDate datum;

    /**
     * A name gettere
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * A pont gettere
     * @return pont
     */
    public int getPont() {
        return pont;
    }

    /**
     * A datum gettere
     * @return datum
     */
    public LocalDate getDatum() {
        return datum;
    }

    /**
     * JatekosEredmeny konstruktora beallitja a valtozokat.
     * @param a pontszam
     * @param b nev
     * @param datum A szamitogep helyi datuma
     */
    public JatekosEredmeny(int a, String b, LocalDate datum){
        this.pont=a;
        this.name=b;
        this.datum=datum;
    }

    /**
     * Osszehasonlit ket JatekosEredmenyt a pontszamok alapjan.
     * @param o A masik JatekosEredmeny
     * @return  Visszadja az Intager.compare fv eredmenyenek a ellentetjet hogy majd a dicsoseglistaban csokkeno sorrendben legyenek benne az eredmenyek.
     */
    @Override
    public int compareTo(JatekosEredmeny o) {
        return -Integer.compare(this.getPont(),o.getPont());
    }
}
