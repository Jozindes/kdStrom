/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package strom;

import datova_cast.IObjektovyTypNaMape;
import datova_cast.Nehnutelnost;

/**
 *
 * @author Jozef
 */
public class Vrchol<T extends IObjektovyTypNaMape> {
    
    private Porovnatelne[] poleKlucov;
       
    private Vrchol lavySyn;
    private Vrchol pravySyn;
    private Vrchol otec;
    
    private int somNaUrovni;
    
    private T data;
    
    public Vrchol(Porovnatelne[] paPoleKlucov, T paData) {
        this.poleKlucov = paPoleKlucov;
        this.lavySyn = null;
        this.pravySyn = null;
        this.otec = null;
        this.data = paData;
    }
    
    public Porovnatelne dajKlucNaIndexe(int paIndexKluca) {
        return this.poleKlucov[paIndexKluca];
    }
    
    public Porovnatelne[] dajPoleKlucov() {
        return this.poleKlucov;
    }
    
    public void nastavLavehoSyna(Vrchol paVrchol) {
        this.lavySyn = paVrchol;
    }
    
    public void nastavPravehoSyna(Vrchol paVrchol) {
        this.pravySyn = paVrchol;
    }
    
    public void nastavOtca(Vrchol paOtec) {
        this.otec = paOtec;
    }
       
    public boolean mamLavehoSyna() {
        if (this.lavySyn != null) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean mamPravehoSyna() {
        if (this.pravySyn != null) {
            return true;
        } else {
            return false;
        }
    }
    
    public Vrchol dajLavehoSyna() {
        return this.lavySyn;
    }
    
    public Vrchol dajPravehoSyna() {
        return this.pravySyn;
    }
    
    public Vrchol dajOtca() {
        return this.otec;
    }
    
    public void nastavUroven(int paUroven) {
        this.somNaUrovni = paUroven;
    }
    
    public int dajUroven() {
        return this.somNaUrovni;
    }
    
    public T dajNehnutelnost() {
        return this.data;
    }
}
