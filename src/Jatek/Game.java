package Jatek;



import java.util.LinkedList;
import java.util.List;
import java.util.Random;



/**
 * Game a jatek logikajat tartalmazo osztaly.
 */

public class Game {
    /**
     * Megadja hogy eppen eletben van-e a kigyo.Ha false akkor meg van halva.Ha true akkor jatekban van.
     */
    private boolean jatekban = false;


    /**
     * A kigyo merete, ezt novelheti a tojasok evesevel
     */
    private int Kigyo_meret;

    /**
     * A vegeredmeny amit elert a jatekos.Minden egyes evessel noveli az eredmenyt.
     */
    private int eredmeny;
    /**
     * A lista amiben tarolom a Snake_testeket.Azert tarolom linkedlistben, mert ehhez konnyen tudok majd hozzadni illetve elvenni es konnyen
     * hozzatudok ferni a belso elemeihez is a beepitett funkciok segitsegevel.
     */
    private List<Snake_test> Kigyo;

    /**
     * Randomizalo ami generalja a koordinatakat a tojasnak
     */
    private Random random;
    /**
     * A jatekeban szereplo tojas amit ha a kigyo megeszik megno a merete
     */
    private Tojas t;
    /**
     * Megadja a kigyo iranyat.
     */
    private Irany irany;
    /**
     * Ez az iranynak a xkoordinataja ahova eppen tart a kigyo,ahol felvetelre kerul a kovetkezo Snake_test
     */
    private int xIrany;
    /**
     * Ez az iranynak a ykoordinataja ahova eppen tart a kigyo,ahol felvetelre kerul a kovetkezo Snake_test
     */
    private int yIrany;

    /**
     * Megkapja a menut hogy majd megtudja hivni a vegepanelt
     */
    private Menu menu;

    /**
     * A game osztaly konstruktora beallitja a menut illetve meghivja a Jatek_initet
     * @param menu A jatek menuje
     */
    public Game(Menu menu){
        this.menu = menu;
        Jatek_init();
    }

    /**
     * Elinditja a jatekot,inicializalja a valtozokat.
     */
    public void Jatek_init() {
        jatekban = true;
        irany=Irany.jobbra;
        Kigyo  = new LinkedList<Snake_test>();
        random = new Random();
        Kigyo_meret = 4;
        eredmeny=0;
        xIrany = 1;
        yIrany = 1;


        Tojas_control();

    }

    /**
     * A mozgas fv a xirany es yirany koordinatat annak fuggvenyeben noveli vagy csokkenti hogy eppen milyen iranyba tart.
     * Ahova tart oda felvesz egy uj testet,azt hozza adja a kigyo listahoz es vegerol elvesz egyet,ha nagyobb lenne mint a Kigyo_meret.
     * Addig noveli a kigyot amekorra a Kigyo_meret.
     * Illetve ha lefejelne a palya szelet akkor nem hal meg, hanem a palya masik reszen jon vissza.
     */
    public void mozgas() {

        if (irany==Irany.jobbra) {
            xIrany++;
        }
        if (irany==Irany.balra) {
            xIrany--;
        }
        if (irany==Irany.fel) {
            yIrany--;
        }
        if (irany==Irany.le) {
            yIrany++;
        }

        Snake_test test;
        //Azert 25 mert 40 szeles es magas a Snake_test kepe igy 25*25os a palya de 0-tól szamozom 24-ig igy ha 25 az irany koordinataja akkor allitsa at 0-ra.
        //A -1 meg ugy jott ki hogyha a 0-bol vonok ki egyet, akkor menne le a palyarol, igy 24 re allitom at.
        if (xIrany == 25) {
            xIrany = 0;
            test = new Snake_test(xIrany, yIrany, 40);
            Kigyo.add(test);

            } else {
                if (yIrany == 25) {
                    yIrany = 0;
                    test = new Snake_test(xIrany, yIrany, 40);
                    Kigyo.add(test);

                } else {
                    if (yIrany == -1) {
                        yIrany = 24;
                        test = new Snake_test(xIrany, yIrany, 40);
                        Kigyo.add(test);
                    } else {
                        if (xIrany == -1) {
                            xIrany = 24;
                            test = new Snake_test(xIrany, yIrany, 40);
                            Kigyo.add(test);
                        } else {
                            test = new Snake_test(xIrany, yIrany, 40);
                            Kigyo.add(test);

                        }
                    }
                }
            }
            if (Kigyo.size() > Kigyo_meret) {
                Kigyo.remove(0);
            }
    }

    /**
     * Azt szolgalja hogy, amikor a kigyo megeszik egy tojast, akkor megnojjon a merete es uj tojas keruljon a palyara
     */
    public void eves(){
        if(t.getXkoord()== xIrany && t.getYkoord()== yIrany){
            Kigyo_meret++;
            eredmeny++;

            Tojas_control();

        }
    }

    /**
     * Ugy rak le uj tojasokat hogy folyamatosan nezi, hogy hol helyezkedik el a kigyo es ott ahol tartozkodik oda nem rakhatja le az adott koordinatakkal a tojast igy addig
     * genaral ujjakat neki mig azok vegul,nem lesznek rajta a kigyon.
     */

    public void Tojas_control(){
        int a=random.nextInt(25);
        int b=random.nextInt(25);
        boolean ok=false;

                while (!ok) {
                    a = random.nextInt(25);
                    b = random.nextInt(25);

                    int i;
                    for (i = 0; i < Kigyo.size(); i++){
                        if (a == Kigyo.get(i).getXkoord() && b == Kigyo.get(i).getYkoord()){
                            break;
                        }
                    }
                    if(i==Kigyo.size()){
                        ok=true;
                    }
                }
        t=new Tojas(a,b,40);
    }

    /**
     * Folymatosan csokkenti az idejet a tojasnak majd ha eleri a nullat mashova rak le egy ujjat ( Tojas_controlt hivja meg).
     */

    public void Tojas_Time_control(){
        if(t.getElettartam()==0){
            Tojas_control();
        }
        t.setElettartam(t.getElettartam()-1);

    }

    /**
     * Ez checkolja hogy magaba utkozott-e a kigyo.Ha igen akkor meghivja a JatekVege fv-t.
     * @param x Az irany x koordinataja ahova eppen tart
     * @param y Az irany y koordinataja ahova eppen tart
     */
    public void utkozes(int x,int y){
        for(int i = 0; i< Kigyo.size()-1; i++){
           if(x== Kigyo.get(i).getXkoord() && y== Kigyo.get(i).getYkoord()){
               JatekVege();
           }
        }
    }

    /**
     * Ez a fv hivodik meg ha vege van a jateknak.Meghivja a vegepanelt es beallitja hogy ezt mutassa a menu.
     * Illetve a kigyot is megoli.(Jatekban=false)
     */
    public void JatekVege() {
        jatekban=false;

        menu.getCont().add(new VegePanel(menu,this),"4");
        menu.getCl().show(menu.getCont(),"4");
        menu.setSize(555,200);
        menu.setResizable(true);
        menu.setLocationRelativeTo(null);


    }



    /**
     * A vegeredmeny gettere
     * @return eredmeny
     */
    public int getEredmeny(){
        return eredmeny;
    }

    /**
     * A kigyo meretenek gettere
     * @return Kigyo_meret
     */
    public int getKigyo_meret() {
        return Kigyo_meret;
    }

    /**
     * A kigyo lista gettere
     * @return Kigyo
     */
    public List<Snake_test> getKigyo() {
        return Kigyo;
    }

    /**
     *  A kigyo_meret settere
     * @param kigyo_meret az aktualis kigyo_meret
     */
    public void setKigyo_meret(int kigyo_meret) {
        this.Kigyo_meret = kigyo_meret;
    }

    /**
     * A jatekban valtozo gettere
     * @return jatekban
     */
    public boolean getJatekban(){
        return jatekban;
    }

    /**
     * Az aktualis palyan levo tojas settere
     * @param t Az a tojas amit a palyara szeretne felrakni
     */
    public void setT(Tojas t) {
        this.t = t;
    }

    /**
     * Tojas gettere
     * @return tojas
     */
    public Tojas getT() {
        return t;
    }

    /**
     * xIrany gettere
     * @return xIrany
     */
    public int getxIrany() {
        return xIrany;
    }

    /**
     * yIrany gettere
     * @return yIrany
     */
    public int getyIrany() {
        return yIrany;
    }

    /**
     * xIrany settere
     * @param xIrany Kigyo xIranya
     */
    public void setxIrany(int xIrany) {
        this.xIrany = xIrany;
    }

    /**
     * yIrany settere
     * @param yIrany Kigyo yIranya
     */
    public void setyIrany(int yIrany) {
        this.yIrany = yIrany;
    }
    /**
     * Az aktualis irany gettere
     * @return Az aktualis irany
     */
    public Irany getIrany() {
        return irany;
    }

    /**
     * Az aktualis irany settere
     * @param irany uj Irany
     */
    public void setIrany(Irany irany) {
        this.irany = irany;
    }

}
