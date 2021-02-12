package Jatek;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

/**
 *  A Dicsoseg Listat tartalmazo Panel
 */
public class DicsosegListaPanel extends JPanel {
    /**
     * A menu kell neki hogy eltudja erni azt a panelt ahova vissza kell lepnie(az lesz ujra mutatva a frameben)
     */
    private Menu m;
    /**
     *  Ez tartalmazza az eredmenyeket listaban
     */
    private DicsosegLista data;

    /**
     * Az osztaly konstruktora meghivja az Initet illetve beolvassa DicsosegLista.txt-bol az adatokat szerializalassal.Beolvasas utan bezarja a fajlt.
     * @param m Megkapja a menut
     */
    public DicsosegListaPanel(Menu m){
        this.m = m;
        try {
            data = new DicsosegLista();
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("DicsosegLista.txt"));
            data.score = (List<JatekosEredmeny>)ois.readObject();
            ois.close();
        } catch(Exception ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
        Init();
    }

    /**
     * Beallitja a panel adatait illetve felhelyezi a panelre egy gombot egy tablat.
     */
    public void Init(){
        GridLayout L2=new GridLayout();
        L2.setColumns(1);
        L2.setRows(2);
        this.setLayout(L2);

        JTable jt = new JTable(data);
        jt.setFillsViewportHeight(true);
        this.add(new JScrollPane(jt));


        JButton JB = new JButton("Vissza a főmenübe");
        JB.setBackground(Color.green);
        JB.setFont(new Font("Tahoma",Font.PLAIN,36));
        JB.addActionListener(new CsereVissza());
        this.add(JB);
    }

    /**
     * data gettere
     * @return data
     */
    public DicsosegLista getData() {
        return data;
    }

    /**
     * A JB gomb actionlistenere
     */
    private class CsereVissza implements ActionListener {
        /**
         * A gomb megnyomasara visszavalt a fomenure.
         * @param e esemeny ami tortent
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            m.getCl().show(m.getCont(),"1");
            m.setSize(555,200);
            m.setLocationRelativeTo(null);

        }
    }

}
