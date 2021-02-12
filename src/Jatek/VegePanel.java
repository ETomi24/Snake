package Jatek;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * A VegePanel az a JPanel amely akkor jelenik meg amikor vege van a jateknak.
 */
public class VegePanel extends JPanel {

    /**
     * Megkapja a menut hogy tudja hova kell majd visszalepni
     */
    private Menu m;

    /**
     * Megkapja a Gamet is hogy letudja kerdezni az eredmenyt
     */
    private Game g;

    /**
     * Ide irja a nevet a jatekos
     */
    private JTextField JT;

    /**
     * A VegePanel konstruktora,beallitja a valtozokat,felveszi a panelen a swing elemeket.
     * @param m   Megkapja a menut hogy tudja hova kell majd visszalepni
     * @param g   Megkapja a gamet is hogy letudja kerdezni az eredmenyt
     */
    public VegePanel(Menu m, Game g){
        this.m = m;
        this.g = g;
        m.pack();

        GridLayout gr=new GridLayout();
        gr.setColumns(1);
        gr.setRows(3);
        this.setLayout(gr);

        setSize(500,500);
        JLabel JL1=new JLabel("Vége a játéknak");
        JL1.setFont(new Font("Tahoma",Font.PLAIN,36));
        JLabel JL2=new JLabel("Eredménye: "+ g.getEredmeny());
        JL2.setFont(new Font("Tahoma",Font.PLAIN,36));

        this.add(JL1);
        this.add(JL2);

        JPanel JP=new JPanel();
        this.add(JP);

        FlowLayout fl=new FlowLayout();
        JP.setLayout(fl);

        JButton JB=new JButton("Vége");
        JB.addActionListener(new Vissza_a_menube());
        JT=new JTextField(20);

        JP.add(new JLabel("Ide irja a nevét"));
        JP.add(JT);
        JP.add(JB);

    }

    /**
     * Vege gomb actionlistenere
     */
    private class Vissza_a_menube implements ActionListener{
        /**
         * Gomb nyomasara visszavalt a panel1-re(Fomenure) es kiirja a fajlba az aktualis score allapotat,szerializalassal.
         * @param e esemeny ami tortent
         */
        @Override
        public void actionPerformed(ActionEvent e) {

            m.getPanel2().getData().addJatekos(JT.getText(), g.getEredmeny());
            m.getCl().show(m.getCont(),"1");

            try {
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("DicsosegLista.txt"));
                oos.writeObject(m.getPanel2().getData().score);
                oos.close();
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
