package Jatek;

import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


import static java.lang.Thread.sleep;

/**
 * Palya az az osztaly ami meghivja,futtatja a gamet es kirajzolja annak allapotat.A palya 25*25 meretu,mivel a kigyo meg a tojas 40 szeles es 40 magas.(1000/40=25)
 */
public class Palya extends JPanel implements Runnable, KeyListener {
    /**
     * A kigyo fejenek a kepe
     */
    private BufferedImage fej;
    /**
     * A Palya hattere
     */
    private BufferedImage hatter;

    /**
     * A jatek szala
     */
    private Thread thread;

    /**
     * A palya szelessege
     */

    private int szel=1000;

    /**
     * A palya magassaga
     */
    private int mag=1000;



    /**
     * Mikor megnyomunk egy gombot true lesz mindaddig mig ki nem lesz ertekelve a run-ban,igy mindig csak egy gombnyomas lesz kiertekelve.
     */
    private boolean lenyomva;


    /**
     * A menu amiben szerepel a palya.Tovabbadja a gamenek.
     */
    private Menu m;


    /**
     * Ez a jatek ami zajlik a palyan
     */
    private Game game;



    /**
     * A jatek sebessege
     */
    private long sebesseg;

    /**
     *Palya osztaly konstruktora.Beolvassa kepeket es elinditja a jatekot.
     * @param m    Megkapja a menut amilyekben meghivtak hogy hozzaferhessen a fv-ihez majd a game.
     * @param sebesseg Menuben beallitott sebesseget itt kapja meg.
     */
    public Palya(Menu m, long sebesseg){
        try{
            //Beolvasas utan automaitkusan bezaraodnak a fajlok az ImageIO-nal
            fej = ImageIO.read(new File("snake-head-icon-23.png"));
            hatter=ImageIO.read(new File("hatter.png"));
        }
        catch (IOException ex){
            ex.printStackTrace();
        }

        this.m = m;

        //itt minel kisebbnek kell lenni a sebessegnek, hogy valojaban gyorsabb legyen mert ennyit fog aludni
        this.sebesseg =(long)920-sebesseg;
        this.setPreferredSize(new Dimension(szel,mag));


        this.addKeyListener(this);

        game=new Game(m);
        game.Jatek_init();

        thread = new Thread(this);
        thread.start();

    }

    /**
     * Ez fv. rajzolja ki a hatteret,kigyot es a tojast is.
     * @param g Rajzolasert felelos objektum
     */
    @Override
    public void paint(Graphics g) {
        g.clearRect(0, 0, szel, mag);

        g.drawImage(hatter,0,0,szel,mag,this);

        for (int i = 0; i < game.getKigyo().size(); i++){
            game.getKigyo().get(i).draw(g);
        }

        g.drawImage(fej, game.getxIrany() *40, game.getyIrany() *40,40,40,this);

        game.getT().draw(g);
    }


    /**
     * Minden egyes orajelre meghivja a Game fuggvenyeit ami jatekhoz szukseges.
     */
    @Override
    public void run() {
        try {
            while(game.getJatekban()){
                game.mozgas();
                game.utkozes(game.getxIrany(),game.getyIrany());
                game.eves();
                game.Tojas_Time_control();
                repaint();
                lenyomva=false;
                sleep(sebesseg);
            }

            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Megnezi hogy milyen gombokat nyomtak le es annak fuggvenyeben allitja be az iranyt.
     * @param e Gombnyomas
     */
    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if(!lenyomva){

            lenyomva=true;

            if ((key == KeyEvent.VK_LEFT||key == KeyEvent.VK_A) && (game.getIrany()!=Irany.jobbra)) {
                game.setIrany(Irany.balra);
            }

            if ((key == KeyEvent.VK_RIGHT||key == KeyEvent.VK_D) && (game.getIrany()!=Irany.balra)) {
                game.setIrany(Irany.jobbra);
            }

            if (((key == KeyEvent.VK_UP||key == KeyEvent.VK_W) && (game.getIrany()!=Irany.le))) {
                game.setIrany(Irany.fel);
            }

            if ((key == KeyEvent.VK_DOWN||key == KeyEvent.VK_S) && (game.getIrany()!=Irany.fel)) {
                game.setIrany(Irany.le);
            }
        }
    }


    @Override
    public void keyReleased(KeyEvent e) {
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

}