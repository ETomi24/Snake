package Jatek;

import javax.swing.*;
import java.awt.*;


/**
 * A menu osztaly a jatek menuje
 */
public class Menu extends JFrame {
    /**
     * Container panel amely tartalmazza a menuben szereplo paneleket.
     */
    private JPanel cont;
    /**
     * Ezt a layoutot hasznalom a cont panelhez hogy konnyen tudjak valtani mas panelekre.
     */
    private CardLayout cl;

    /**
     * A menuben szereplo DicsosegLista panel.
     */
    private DicsosegListaPanel panel2;

    /**
     * A menu konstruktora meghivja az Init-et
     */
    public Menu(){
        Init();
    }

    /**
     * Az init a beallitja a frame alapertekeit, felveszi a paneleket.A panel1(fomenut)-et fogja defaultbol mutatni.
     */
    public void Init() {

        this.setTitle("Snake");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(555,200);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        cont = new JPanel();
        cont.setFocusable(true);
        cl = new CardLayout();
        cont.setLayout(cl);
        this.add(cont);

        //főmenü
        FomenuPanel panel1 = new FomenuPanel(this);
        cont.add(panel1,"1");

        //Hatso panel (dicsosegLista)//
        panel2=new DicsosegListaPanel(this);
        cont.add(panel2,"2");

        cl.show(cont,"1");

    }

    /**
     * Cont panel gettere
     * @return cont
     */
    public JPanel getCont() {
        return cont;
    }

    /**
     * Cl(CardLayout) gettere
     * @return cl
     */
    public CardLayout getCl(){
        return cl;
    }

    /**
     * Panel2 (DicsosegLista) gettere
     * @return panel2
     */
    public DicsosegListaPanel getPanel2() {
        return panel2;
    }
}