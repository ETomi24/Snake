import Jatek.DicsosegLista;
import Jatek.JatekosEredmeny;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

//Ebben a teszt osztályban a DicsosegLista es JatekosEredmeny osztályokat tesztelem
public class DicsosegListaTeszt {
    DicsosegLista lista;

    @Before
    public void before(){
        lista = new DicsosegLista();
    }
    //Azt tesztelem valoban letrehozza a jatekoseredemenyt es a listat
    @Test
    public void letrehoz(){
        JatekosEredmeny jatekosEredmeny = new JatekosEredmeny(20,"Tamas", LocalDate.now());
        Assert.assertNotNull(lista);
        Assert.assertNotNull(jatekosEredmeny);
        Assert.assertEquals(jatekosEredmeny.getPont(),20);
        Assert.assertEquals(jatekosEredmeny.getName(),"Tamas");
        Assert.assertEquals(jatekosEredmeny.getDatum(),LocalDate.now());
    }
    //Azt tesztelem valoban hozzáadja-e
    @Test
    public void hozzad(){
        lista.addJatekos("Tamas",20);
        Assert.assertEquals(lista.getScore().get(0).getName(),"Tamas");
        Assert.assertEquals(lista.getScore().get(0).getPont(),20);
        Assert.assertEquals(lista.getScore().get(0).getDatum(),LocalDate.now());
    }
    //Azt tesztelem hogy jol hasonlit-e ossze ket JatekosEredmenyt
    @Test
    public void osszehasonlit(){
        JatekosEredmeny jatekosEredmeny1 = new JatekosEredmeny(20,"Tamas", LocalDate.now());
        JatekosEredmeny jatekosEredmeny2 = new JatekosEredmeny(25,"Bela", LocalDate.now());
        //1 et kellenne adnia mivel nagyobb de ennek veszem az ellentetjét a compareben hogy (forditva) csökkenö sorrendben jelenjenek majd meg a dicsöséglistában
        Assert.assertEquals(jatekosEredmeny2.compareTo(jatekosEredmeny1),-1);
    }
    //sorbarendezest tesztelem
    @Test
    public void sorbarendez(){
        lista.addJatekos("Zoltan",20);
        lista.addJatekos("Misi",35);
        lista.addJatekos("Tamas",15);
        Assert.assertEquals(lista.getScore().get(0).getPont(),35);
        Assert.assertEquals(lista.getScore().get(1).getPont(),20);
        Assert.assertEquals(lista.getScore().get(2).getPont(),15);
    }

}
