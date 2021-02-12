package Jatek;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A menu fomenuje amit a jatekos eloszor lat meg amikor a programot elinditja
 */
public class FomenuPanel extends JPanel {

    /**
     * Megkapja a menut hogy hozzaferjen a valtozoihoz
     */
    private Menu menu;

    /**
     * A fomenuben szereplo JSlider (ezen lehet beallitani a jatek sebesseget)
     */
    private JSlider Gyorsasag;

    /**
     * Az aktualis sebesseg mutatasara szolgal
     */
    private JLabel JL1;

    /**
     * A Jslideren megjeleno lassu ertek
     */
    private int lassu=750;

    /**
     * A Jslideren megjeleno gyors ertek
     */
    private int gyors=900;
    /**
     * A Jslideren megjeleno aktualis ertek
     */
    private int akt=800;

    /**
     * A FomenuPanel konstruktora letrehozza a panelt felveszi rajta a gombokat a slidert illetve a Jlabelt.Illetve beallitja a menut hogy hozzaferjen a valtozoihoz.
     * @param menu A menu amiben o is talalhato
     */
    public FomenuPanel(Menu menu){
        this.menu=menu;

        menu.setSize(555,200);

        BorderLayout L1=new BorderLayout();
        this.setLayout(L1);

        JPanel panelbelul = new JPanel();
        this.add(panelbelul,BorderLayout.CENTER);

        GridLayout L3=new GridLayout();
        panelbelul.setLayout(L3);
        L3.setColumns(1);
        L3.setRows(2);

        this.setBackground(Color.GRAY);

        //gombok
        JButton JB1=new JButton("Játék Indítása");
        JB1.setBackground(Color.GREEN);
        JB1.setOpaque(true);
        JB1.setFont(new Font("Tahoma",Font.PLAIN,36));
        JB1.addActionListener(new Jatek());

        JButton JB2=new JButton("Dicsőség Lista megtekintése");
        JB2.setBackground(Color.PINK);
        JB2.setFont(new Font("Tahoma",Font.PLAIN,36));
        JB2.addActionListener(new Csere());

        //Kigyo gyorssasagara szolgalo slider//
        Gyorsasag=new JSlider(JSlider.VERTICAL,lassu,gyors,akt);
        Gyorsasag.setMajorTickSpacing(50);
        Gyorsasag.setMinorTickSpacing(25);
        Gyorsasag.setPaintTicks(true);
        Gyorsasag.setPaintLabels(true);
        Gyorsasag.addChangeListener(new Slider_figyelo());

        this.add(Gyorsasag,BorderLayout.EAST);

        JL1=new JLabel("Aktuális sebbesség: 800 (Minél nagyobb az érték annál gyorsabb lesz a játék)");
        JL1.setBackground(Color.WHITE);


        panelbelul.add(JB1);
        panelbelul.add(JB2);
        this.add(JL1,BorderLayout.SOUTH);

    }

    /**
     * A JSlider aktualis ertekenek valtozasat figyelo belso osztaly
     */
    private class Slider_figyelo implements ChangeListener {

        /**
         *  Ez a fv kerdezi le az aktualis allapotat a JSlidernek es irja ki a JL1-en.
         * @param e esemeny ami tortent
         */
        @Override
        public void stateChanged(ChangeEvent e) {
            int value=Gyorsasag.getValue();
            JL1.setText("Aktuális sebbesség: "+value+" (Minél nagyobb az érték annál gyorsabb lesz a játék)");
        }
    }
    /**
     * A JB2(DigyosegLista megtekintes) gomb actionlistenere
     */
    private class Csere implements ActionListener {

        /**
         * Ha megnyomtak a gombot akkor DicsosegListaPanelt fogja mutatni
         * @param e esemeny ami tortent
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            menu.getCl().show(menu.getCont(),"2");
            menu.setSize(555,400);
            menu.setLocationRelativeTo(null);
        }
    }

    /**
     * A JB1 (Jatek Inditasa)gomb actionlistenere
     */
    private class Jatek implements  ActionListener{

        /**
         * A gomb nyomasara elinditja a jatekot.
         * @param e esemeny ami tortent
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            Palya palya = new Palya(menu,Gyorsasag.getValue());
            menu.getCont().add(palya,"3");
            menu.pack();

            menu.setLocationRelativeTo(null);

            menu.getCl().show(menu.getCont(),"3");
            palya.setFocusable(true);
            palya.requestFocusInWindow();

        }
    }
}
