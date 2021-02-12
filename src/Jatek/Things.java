package Jatek;

import java.awt.*;
import java.awt.image.BufferedImage;


/**
 * A Things egy absztrakt osztaly aminek van x es y koordinataja illetve kep magassaga es szelessege,es ezt kirajzolo draw fuggvenye
 */
public abstract class Things {
    /**
     * A Things kepenek szellesege amit kirajzol majd a draw
     */
    protected int szel;
    /**
     * A Things kepenek magassaga amit kirajzol majd a draw
     */
    protected int mag;
    /**
     *  A Things x koordinataja
     */
    protected int xkoord;
    /**
     * A Things  y koordinataja
     */
    protected int ykoord;

    /**
     * A Things kepe amit kirajzol majd a draw
     */
    protected BufferedImage icon;


    /**
     * A Things konstruktora,beallitja az alapertekeket.
     * @param xkoord Things x koordinataja
     * @param ykoord Things y koordinataja
     * @param a a Things kepenek a magassaga es szelessege
     */
    public Things(int xkoord,int ykoord,int a){
        this.xkoord=xkoord;
        this.ykoord=ykoord;
        this.mag=a;
        this.szel=a;
    }

    /**
     * A Things kirajzolo fuggvenye
     * @param g Rajzolasert felelos objektum
     */
    public void draw(Graphics g) {

        g.drawImage(icon,xkoord*szel, ykoord * mag, szel ,mag,null);
    }

    /**
     * xkoord gettere
     * @return xkoord
     */
    public int getXkoord() {
        return xkoord;
    }

    /**
     * ykoord gettere
     * @return ykoord
     */
    public int getYkoord() {
        return ykoord;
    }
}
