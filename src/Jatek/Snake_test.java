package Jatek;

import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;

/**
 * A Snake_test a kigyo lista egy eleme,ebbol fog osszetevodni a kigyo.A things osztaly leszarmazottja.
 */
public class Snake_test extends Things {

    /**
     * A snake_test konstruktora ami beallitja a valtozokat illetve beolvassa a kepet.
     * @param xkoord A test x koordinataja
     * @param ykoord A test y koordinataja
     * @param a      A szelesseg es magassag egyutt
     */
    public Snake_test(int xkoord, int ykoord, int a){
        super(xkoord,ykoord,a);
        try{
            //Beolvasas utan automatikusan bezarodnak a fajlok az ImageIO-nal
            this.icon= ImageIO.read(new File("dot.png"));
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }

}
