package Jatek;

import javax.swing.table.AbstractTableModel;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A dicsoseglista az az osztaly ahol tarolom az eredmenyeket amiket a jatekosok elertek.
 */
public class DicsosegLista extends AbstractTableModel {
    /**
     * A score listaban vannak a JatekosEredmeny-ek.
     * Azert alkalmazok arraylistet mert ehhez konnyen tudok hozzadadni majd elemeket es ehhez a celhoz ezt talaltam legjobbnak.
     */
    List<JatekosEredmeny> score = new ArrayList<JatekosEredmeny>();

    /**
     * Visszadja, hany soros a tablazat.
     * @return Tablazat sorainak szama
     */
    @Override
    public int getRowCount() {
        return score.size();
    }

    /**
     * Visszadja, hogy hany oszlopbol all tablazat.
     * @return Tablazat oszlopainak szama
     */
    @Override
    public int getColumnCount() {
        return 4;
    }

    /**
     * Ez ad nevet az oszlopoknak.
     * @param column oszlopszam
     * @return  Megadja az adott oszlop nevet
     */
    @Override
    public String getColumnName(int column) {
        switch(column) {
            case 0: return "Helyezés";
            case 1: return "Név";
            case 2: return "Pontszám";
            default: return "Idő";
        }
    }

    /**
     * Visszadja az adott oszlopban talalhato peldanyok osztalyait.
     * @param columnIndex oszlopszam
     * @return Az adott peldany osztalya
     */
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch(columnIndex) {
            case 1: return String.class;
            case 3: return LocalDate.class;
            default: return Integer.class;
        }
    }

    /**
     * Visszaadja egy cella erteket
     * @param rowIndex  Sorindex
     * @param columnIndex   Oszlopindex
     * @return Visszaadja egy cella erteket
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        JatekosEredmeny jatekoseredmeny = score.get(rowIndex);
        switch(columnIndex) {
            case 0: return rowIndex + 1;
            case 1: return jatekoseredmeny.getName();
            case 2: return jatekoseredmeny.getPont();
            default:return jatekoseredmeny.getDatum();
        }
    }

    /**
     * Ezzel a fv-el lehet hozzaadni a listahoz eredmenyeket illetve amikor hozzaadunk egy uj elemet,rendezi a listat.
     * @param nev      Jatekos neve
     * @param eredmeny  Jatekos altal elert eredmeny
     */
    public void addJatekos(String nev, int eredmeny) {
        JatekosEredmeny jatekosEredmeny = new JatekosEredmeny(eredmeny,nev, LocalDate.now());
        score.add(jatekosEredmeny);
        Collections.sort(score);
        fireTableDataChanged();
    }

    /**
     * A score lista gettere
     * @return score lista
     */
    public List<JatekosEredmeny> getScore() {
        return score;
    }
}
