package com.mycompany.unicafe;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class KassapaateTest {
    
    Kassapaate kassa;
    Maksukortti kortti;
    
    public KassapaateTest() {
    }

    @Before
    public void setUp() {
        this.kassa = new Kassapaate();
        this.kortti = new Maksukortti(500);
        
    }

    @Test
    public void luotuKassapaateOlemassa() {
        assertTrue(kassa!=null);
    }
    
    @Test
    public void kassanSaldoAlussaOikein() {
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void edullisiaEiOleAlussaMyyty() {
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maukkaitaEiOleAlussaMyyty() {
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void edullisenOstoKasvattaaKassanSaldoaOikeinKunMaksuOnTarpeeksi() {
        kassa.syoEdullisesti(300);
        
        assertEquals(100240, kassa.kassassaRahaa());
    }
    
    @Test
    public void kassanRahamaaraEiMuutuJosMaksuOnLiianPieniEdullistaOstettaessa() {
        kassa.syoEdullisesti(200);
        
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void kassaAntaaOikeanMaaranVaihtorahaaKunOstetaanEdullinenRiittavallaSummalla() {
        assertEquals(60, kassa.syoEdullisesti(300));
    }
    
    @Test
    public void kassaAntaaKaikenTakaisinKunMaksetaanLiianVahanEdullistaOstettaessa() {
        assertEquals(200, kassa.syoEdullisesti(200));
    }
    
    @Test
    public void edullisenOstoKasvattaaKassanSaldoaTasamaaralla() {
        kassa.syoEdullisesti(240);
        
        assertEquals(100240, kassa.kassassaRahaa());
    }
    
    @Test
    public void kassaEiAnnaVaihtorahaaKunMaksetaanTasarahaEdullisesta() {
        assertEquals(0, kassa.syoEdullisesti(240));
    }
    
    @Test
    public void edullistenMaaraKasvaaKunOstetaanEdullinenRiittavallaSummalla() {
        kassa.syoEdullisesti(300);
        
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void edullistenMaaraEiKasvaKunKoitetaanOstaaEdullinenRiittamattomallaSummalla() {
        kassa.syoEdullisesti(200);
        
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maukkaanOstoKasvattaaKassanSaldoaOikeinKunMaksuOnTarpeeksi() {
        kassa.syoMaukkaasti(500);
        
        assertEquals(100400, kassa.kassassaRahaa());
    }
    
    @Test
    public void kassanRahamaaraEiMuutuJosMaksuOnLiianPieniMaukastaOstettaessa() {
        kassa.syoMaukkaasti(300);
        
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void kassaAntaaOikeanMaaranVaihtorahaaKunOstetaanMaukasRiittavallaSummalla() {
        assertEquals(100, kassa.syoMaukkaasti(500));
    }
    
    @Test
    public void kassaAntaaKaikenTakaisinKunMaksetaanLiianVahanMaukastaOstettaessa() {
        assertEquals(300, kassa.syoMaukkaasti(300));
    }
    
    @Test
    public void maukkaanOstoKasvattaaKassanSaldoaTasamaaralla() {
        kassa.syoMaukkaasti(400);
        
        assertEquals(100400, kassa.kassassaRahaa());
    }
    
    @Test
    public void kassaEiAnnaVaihtorahaaKunMaksetaanTasarahaMaukkaasta() {
        assertEquals(0, kassa.syoMaukkaasti(400));
    }
    
    @Test
    public void maukkaidenMaaraKasvaaKunOstetaanMaukasRiittavallaSummalla() {
        kassa.syoMaukkaasti(500);
        
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void maukkaidenMaaraEiKasvaKunKoitetaanOstaaMaukasRiittamattomallaSummalla() {
        kassa.syoMaukkaasti(300);
        
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    // Maksukortilla maksamisen testaus:
    
    @Test
    public void edullisenOstoKortillaVeloittaaKortiltaOikeinSaldonRiittaessa() {
        kassa.syoEdullisesti(kortti);
        
        assertEquals(260, kortti.saldo());
    }
    
    @Test
    public void edullisenOstoKortillaEiVeloitaKortiltaKunSaldoEiRiita() {
        kortti.otaRahaa(400);
        
        // kortilla nyt 100
        
       kassa.syoEdullisesti(kortti);
       
       assertEquals(100, kortti.saldo());
    }
    
    @Test
    public void edullisenOstoVeloittaaKortiltaOikeinKunSaldoOnTasan() {
        kortti.otaRahaa(260);
        
        // kortilla nyt 240
        
        kassa.syoEdullisesti(kortti);
        
        assertEquals(0, kortti.saldo());
    }
    
    @Test
    public void edullisenOstoPalauttaaTrueKunKortillaSaldoRiittaa() {
        assertEquals(true, kassa.syoEdullisesti(kortti));
    }
    
    @Test
    public void edullisenOstoPalauttaaFalseKunKortillaSaldoEiRiita() {
        kortti.otaRahaa(400);
        
        assertEquals(false, kassa.syoEdullisesti(kortti));
    }
    
    @Test
    public void edullisenOnnistunutOstoKortillaLisaaEdullistenMaaraaKassassa() {
        kassa.syoEdullisesti(kortti);
        
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void edullisenEpaonnistunutOstoKortillaEiKasvataEdullistenMaaraaKassassa() {
        kortti.otaRahaa(400);
        kassa.syoEdullisesti(kortti);
        
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maukkaanOstoKortillaVeloittaaKortiltaOikeinSaldonRiittaessa() {
        kassa.syoMaukkaasti(kortti);
        
        assertEquals(100, kortti.saldo());
    }
    
    @Test
    public void maukkaanOstoKortillaEiVeloitaKortiltaKunSaldoEiRiita() {
        kortti.otaRahaa(400);
        
        // kortilla nyt 100
        
       kassa.syoMaukkaasti(kortti);
       
       assertEquals(100, kortti.saldo());
    }
    
    @Test
    public void maukkaanOstoVeloittaaKortiltaOikeinKunSaldoOnTasan() {
        kortti.otaRahaa(100);
        
        // kortilla nyt 400
        
        kassa.syoMaukkaasti(kortti);
        
        assertEquals(0, kortti.saldo());
    }
    
    @Test
    public void maukkaanOstoPalauttaaTrueKunKortillaSaldoRiittaa() {
        assertEquals(true, kassa.syoMaukkaasti(kortti));
    }
    
    @Test
    public void maukkaanOstoPalauttaaFalseKunKortillaSaldoEiRiita() {
        kortti.otaRahaa(400);
        
        assertEquals(false, kassa.syoMaukkaasti(kortti));
    }
    
    @Test
    public void maukkaanOnnistunutOstoKortillaLisaaMaukkaidenMaaraaKassassa() {
        kassa.syoMaukkaasti(kortti);
        
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void maukkaanEpaonnistunutOstoKortillaEiKasvataMaukkaidenMaaraaKassassa() {
        kortti.otaRahaa(400);
        kassa.syoMaukkaasti(kortti);
        
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void edullisenOnnistunutOstoEiMuutaKassanSaldoaKortillaMaksettaessa() {
        kassa.syoEdullisesti(kortti);
        
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void maukkaanOnnistunutOstoEiMuutaKassanSaldoaKortillaMaksettaessa() {
        kassa.syoMaukkaasti(kortti);
        
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void edullisenEpaonnistunutOstoEiMuutaKassanSaldoaKortillaMaksettaessa() {
        kortti.otaRahaa(400);
        kassa.syoEdullisesti(kortti);
        
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void maukkaanEpaonnistunutOstoEiMuutaKassanSaldoaKortillaMaksettaessa() {
        kortti.otaRahaa(400);
        kassa.syoMaukkaasti(kortti);
        
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void positiivisenMaaranLatausKortilleKasvattaaKassanRahamaaraa() {
        kassa.lataaRahaaKortille(kortti, 500);
        
        assertEquals(100500, kassa.kassassaRahaa());
    }
    
    @Test
    public void negatiivisenMaaranLatausKortilleEiMuutaKassanRahamaaraa() {
        kassa.lataaRahaaKortille(kortti, -500);
        
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void positiivisenMaaranLatausKortilleKasvattaaKortinSaldoaOikein() {
        kassa.lataaRahaaKortille(kortti, 500);
        
        assertEquals(1000, kortti.saldo());
    }
    
    @Test
    public void negatiivisenMaaranLatausKortilleEiMuutaKortinSaldoa() {
        kassa.lataaRahaaKortille(kortti, -500);
        
        assertEquals(500, kortti.saldo());
    }
    
}
