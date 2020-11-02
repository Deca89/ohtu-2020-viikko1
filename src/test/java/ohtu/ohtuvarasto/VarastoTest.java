package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    Varasto varasto2;
    Varasto varasto3;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void voidaanLuodaTaytettyVarasto() {
        varasto3 = new Varasto(10, 15);

        assertEquals(varasto3.getSaldo(), varasto3.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void voidaanLuodaOsittainTaysiVarasto() {
        varasto3 = new Varasto(10, 5);

        assertEquals(varasto3.getSaldo(), 5, vertailuTarkkuus);
    }
    
    @Test
    public void eiTehdaNegatiivistaVarastoaKahdella() {
        varasto3 = new Varasto(-10, 0);

        assertEquals(0, varasto3.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void eiTehdaNegatiivistaSaldoaKahdella() {
        varasto3 = new Varasto(0, -10);

        assertEquals(0, varasto3.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void eiTehdaNegatiivistaVarastoa() {
        varasto2 = new Varasto(-10);

        assertEquals(0, varasto2.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void eiNegatiivistaLisaystaSaldoon() {
        double vertailu = varasto.getSaldo();
        
        varasto.lisaaVarastoon(-100);
        assertEquals(vertailu, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void testataanYlimaaranLisays() {
        varasto.lisaaVarastoon(100);
        
        assertEquals(varasto.getTilavuus(), varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }
    
    @Test
    public void eiNegatiivistaOttoaSaldosta() {
        double vertailu = varasto.getSaldo();
        
        varasto.otaVarastosta(-5);
        assertEquals(vertailu, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void eiOtetaYliSaldon() {
        varasto.lisaaVarastoon(5);
        double otettu = varasto.otaVarastosta(500);
        
        assertEquals(otettu, 5, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void tulosteenTestaus() {
        String tuloste = varasto.toString();
        double saldo = varasto.getSaldo();
        double tilaJaljella = varasto.paljonkoMahtuu();
        Boolean palaute = tuloste.equals("saldo = " + saldo + ", vielä tilaa " + tilaJaljella);
        
        assertTrue(palaute);
    }
    


}
