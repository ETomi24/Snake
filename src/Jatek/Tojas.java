package Jatek;

import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;

/**
 * A tojas osztaly, amelyet a kigyo megeszik.Things osztaly leszarmazottja
 */
public class Tojas extends Things{
    /**
     * A tojas elettartama
     */
    private int elettartam;

    /**
     * A tojas konstruktora,beallitja az alapertekeket,illetve beolvassa a tojas kepet.
     * @param xkoord tojas x koordinataja
     * @param ykoord tojas y koordinataja
     * @param a a tojas kepenek a magassaga es szelessege
     */
    public Tojas(int xkoord,int ykoord,int a){
        super(xkoord,ykoord,a);
        elettartam=61;
        try{
            //Beolvasas utan automatikusan bezarodnak a fajlok az ImageIO-nal
            icon= ImageIO.read(new File("tojas.png"));
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }

    /**
     * A tojas elettartamanak a gettere
     * @return elettartam
     */
    public int getElettartam() {
        return elettartam;
    }

    /**
     * A tojas elettartamanak settere
     * @param elettartam az idotartam amit beallitnak uj elettartamnak
     */
    public void setElettartam(int elettartam) {
        this.elettartam = elettartam;
    }
}

