package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void saldoPalauttaaSaldonArvonOikein() {
        assertEquals(10, kortti.saldo());
    }
    
    @Test
    public void kortinSaldoAlussaOikein() {
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void rahanLataaminenKasvattaaSaldoaOikein() {
        kortti.lataaRahaa(10);
        
        assertEquals("saldo: 0.20", kortti.toString());
    }
    
    @Test
    public void negatiivisenMaaranLataaminenEiMuutaSaldoa() {
        kortti.lataaRahaa(-10);
        
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void otaRahaaVahentaaSaldoaKunRahaaOnTarpeeksi() {
        kortti.otaRahaa(5);
        
        assertEquals("saldo: 0.05", kortti.toString());
    }
    
    @Test
    public void otaRahaaEiVahennaSaldoaKunRahaaEiOleTarpeeksi() {
        kortti.otaRahaa(20);
        
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void otaRahaaEiMuutaSaldoaKunKoitetaanOttaaNegatiivinenSumma() {
        kortti.otaRahaa(-10);
        
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void otaRahaaPalauttaaTrueKunRahaaOnTarpeeksi() {
        assertEquals(true, kortti.otaRahaa(5));
    }
    
    @Test
    public void otaRahaaPalauttaaFalseKunRahaaEiOleTarpeeksi() {
        assertEquals(false, kortti.otaRahaa(20));
    }
    
    @Test
    public void otaRahaaPalauttaaFalseKunYritetaanOttaaNegatiivinenSumma() {
        assertEquals(false, kortti.otaRahaa(-10));
    }
}
