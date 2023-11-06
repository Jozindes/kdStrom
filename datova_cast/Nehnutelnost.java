/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datova_cast;

import java.util.ArrayList;

/**
 *
 * @author Jozef
 */
public class Nehnutelnost implements IObjektovyTypNaMape {
    private int supisneCislo;
    private String popis;
    private char sirka;
    private char dlzka;
    
    private ArrayList<IObjektovyTypNaMape> zoznamParciel = new ArrayList<IObjektovyTypNaMape>();
    
    public Nehnutelnost(int paSupisneCislo, String paPopis, char paSirka, char paDlzka) {
        this.supisneCislo = paSupisneCislo;
        this.popis = paPopis;
        this.sirka = paSirka;
        this.dlzka = paDlzka;
    }
    
    public int dajSupisneCislo() {
        return this.supisneCislo;
    }
    
    public String dajPopis() {
        return this.popis;
    }
    
    public void nastavSupisneCislo(int paSupisneCislo) {
        this.supisneCislo = paSupisneCislo;
    }
    
    public void nastavPopis(String paPopis) {
        this.popis = paPopis;
    }
    
    public void pridatNovyObjektDoZoznamu(IObjektovyTypNaMape paData) {
        this.zoznamParciel.add(paData);
        System.out.println("pridal som");
    }
    
    public void odstranObjektZoZoznamu(IObjektovyTypNaMape paData) {
        for (IObjektovyTypNaMape akt : this.zoznamParciel) {
            if (akt.porovnajSObjektom(paData)) {
                this.zoznamParciel.remove(akt);
                System.out.println("Odstranil som parcelu z nehnutelnosti"); 
                break;
            }
        }
        System.out.println("bol som tu");
    }
    
    public ArrayList<IObjektovyTypNaMape> dajZoznamObjektov() {
        return this.zoznamParciel;
    }
    
    public boolean skontrolujDostupnost(IObjektovyTypNaMape paData) {
        for (IObjektovyTypNaMape akt : zoznamParciel) {
            System.out.println(akt.dajSupisneCislo() + " " + akt.dajPopis());
            if (akt.skontrolujDostupnost(paData)) {
                return true;
            }
        }
        
        return false;
    }
    
    public boolean porovnajSObjektom(IObjektovyTypNaMape paData) {
            if (paData.dajSupisneCislo() == this.dajSupisneCislo()) {
                if (paData.dajPopis() == this.dajPopis()) {
                    return true;
                }
        }
        return false;
    }
    
    public char dajSirku() {
        return this.sirka;
    }
    
    public char dajDlzku() {
        return this.dlzka;
    }
}
