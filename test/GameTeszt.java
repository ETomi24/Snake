import Jatek.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GameTeszt {
    private Menu menu;
    private Game game;

    private int elozox;
    private int elozoy;


    @Before
    public void kezdo(){
        menu = new Menu();
        game = new Game(menu);
        elozox= game.getxIrany();
        elozoy= game.getyIrany();
    }
    @Test
    public void Helyes_ertekek(){
        //Megnézem hogy a JatekInit amit a konstruktor meghív rendes értékeket állit e be és a gettereket is tesztelem
        Assert.assertNotNull(game);
        Assert.assertEquals(game.getIrany(),Irany.jobbra);
        Assert.assertEquals(game.getKigyo_meret(),4);
        Assert.assertEquals(game.getT().getElettartam(),61);
        Assert.assertEquals(game.getEredmeny(),0);
        Assert.assertEquals(game.getxIrany(),1);
        Assert.assertEquals(game.getyIrany(),1);
    }
    //Következö négy teszt eset a mozgás fv-t teszteli
    @Test
    public void Kigyo_mozgas_jobbra() {

        game.setIrany(Irany.jobbra);
        game.mozgas();

        //Megnézem beállitja-e az új iranyt
        Assert.assertEquals(game.getxIrany(),elozox+1);
        Assert.assertEquals(game.getyIrany(), elozoy);

        //Hozzáadja-e a kígyóhoz az új testet
        Assert.assertEquals(game.getKigyo().get(0).getXkoord(), game.getxIrany());
        Assert.assertEquals(game.getKigyo().get(0).getYkoord(), game.getyIrany());
    }

    @Test
    public void Kigyo_mozgas_lefele(){
        game.setIrany(Irany.le);
        game.mozgas();

        //Megnézem beállitja-e az új iranyt
        Assert.assertEquals(game.getxIrany(),elozox);
        Assert.assertEquals(game.getyIrany(),elozoy+1);

        //Hozzáadja-e a kígyóhoz az új testet
        Assert.assertEquals(game.getKigyo().get(0).getXkoord(), game.getxIrany());
        Assert.assertEquals(game.getKigyo().get(0).getYkoord(), game.getyIrany());
    }
    @Test
    public void Kigyo_mozgas_felfele(){
        game.setIrany(Irany.fel);
        game.mozgas();

        //Megnézem beállitja-e az új iranyt
        Assert.assertEquals(game.getxIrany(),elozox);
        Assert.assertEquals(game.getyIrany(),elozoy-1);

        //Hozzáadja-e a kígyóhoz az új testet
        Assert.assertEquals(game.getKigyo().get(0).getXkoord(), game.getxIrany());
        Assert.assertEquals(game.getKigyo().get(0).getYkoord(), game.getyIrany());
    }
    @Test
    public void Kigyo_mozgas_balra(){
        game.setIrany(Irany.balra);
        game.mozgas();

        //Megnézem beállitja-e az új iranyt
        Assert.assertEquals(game.getxIrany(),elozox-1);
        Assert.assertEquals(game.getyIrany(),elozoy);

        //Hozzáadja-e a kígyóhoz az új testet
        Assert.assertEquals(game.getKigyo().get(0).getXkoord(), game.getxIrany());
        Assert.assertEquals(game.getKigyo().get(0).getYkoord(), game.getyIrany());

    }
    //Az utkozosest tesztelem
    @Test
    public void utkozes_teszteles(){
        game.setKigyo_meret(7);

        //mozgatom hogy letrejojjon az egesz kigyo, hogy konnyebben magaba mehessen
        game.setIrany(Irany.jobbra);
        game.mozgas();
        game.setIrany(Irany.jobbra);
        game.mozgas();
        game.setIrany(Irany.jobbra);
        game.mozgas();
        game.setIrany(Irany.jobbra);
        game.mozgas();
        game.setIrany(Irany.le);
        game.mozgas();
        game.setIrany(Irany.balra);
        game.mozgas();
        game.setIrany(Irany.fel);
        game.mozgas();
        game.utkozes(game.getxIrany(), game.getyIrany());
        //Ha false valoban meghalt a kigyo
        Assert.assertFalse(game.getJatekban());
    }
    //Azt tesztelem valoban nem hal meg a falnal,hanem visszajön a tuloldalon
    @Test
    public void falnal(){
        game.setxIrany(23);
        game.setyIrany(0);
        game.mozgas();
        game.mozgas();

        //jobbra megy bal oldalt kell kijonnie
        Assert.assertEquals(game.getxIrany(),0);
        Assert.assertEquals(game.getyIrany(),0);

        Assert.assertEquals(game.getKigyo().get(0).getXkoord(),24);
        Assert.assertEquals(game.getKigyo().get(1).getXkoord(),0);
        Assert.assertTrue(game.getJatekban());

        //ha felfele megyek akkor meg alul kell kijonnie
        game.setIrany(Irany.fel);
        game.mozgas();

        Assert.assertEquals(game.getKigyo().get(1).getYkoord(),0);
        Assert.assertEquals(game.getKigyo().get(2).getYkoord(),24);
        Assert.assertTrue(game.getJatekban());
    }

    //Ebben a tesztben azt nezem hogy valoban nem lesz nagyobb a mozgastol a kigyo mint a merete
    @Test
    public void meretnel_nem_lesz_nagyobb(){
        game.setKigyo_meret(4);

        for(int i=0;i<10;i++){
            game.setIrany(Irany.jobbra);
            game.mozgas();
        }
        Assert.assertEquals(game.getKigyo().size(), game.getKigyo_meret());
    }
    //Tesztelem hogy valoban nem kerulhet kigyora tojas
    @Test
    public void Tojas_hely(){
        //Kezdoirany 1-1 es negyszer mozgatom 4-1 lesz
        for(int i=0;i<4;i++){
            game.mozgas();
        }

        //letesztelem hogy az uj tojast nem a kigyora rakja le
        game.Tojas_control();
        for(int i = 0; i<game.getKigyo().size();i++) {
            Assert.assertNotEquals(game.getT().getXkoord(), game.getKigyo().get(i).getXkoord());
            Assert.assertNotEquals(game.getT().getYkoord(), game.getKigyo().get(i).getYkoord());

        }
    }
    //Ebben a tesztben azt nezem meg hogy valoban megnoveli a kigyo meretet ha megeszik egy tojast
    @Test
    public void  Tojas_eves(){
        Tojas t = new Tojas(2,1,40);
        int Kigyomeretregi= game.getKigyo_meret();
        game.setT(t);
        game.setIrany(Irany.jobbra);
        game.mozgas();
        game.eves();
        Assert.assertEquals(game.getKigyo_meret(),Kigyomeretregi+1);
    }
}
