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
public class Parcela implements IObjektovyTypNaMape {
    private int supisneCislo;
    private String popis;
    private char dlzka;
    private char sirka;
    
    private ArrayList<IObjektovyTypNaMape> zoznamNehnutelnosti = new ArrayList<IObjektovyTypNaMape>();
    
    public Parcela(int paSupisneCislo, String paPopis, char paSirka, char paDlzka) {
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
        this.zoznamNehnutelnosti.add(paData);
        System.out.println("pridal som");
    }
    
    public void odstranObjektZoZoznamu(IObjektovyTypNaMape paData) {
        for (IObjektovyTypNaMape akt : this.zoznamNehnutelnosti) {
            System.out.println(akt.dajSupisneCislo() + "  +  " + akt.dajPopis());
            if (akt.porovnajSObjektom(paData)) {
                this.zoznamNehnutelnosti.remove(akt);
                System.out.println("Odstranil som nehnutelnost z parcely"); 
                break;
            }
        }
        System.out.println("bol som tu");
    }
    
    public ArrayList<IObjektovyTypNaMape> dajZoznamObjektov() {
        return this.zoznamNehnutelnosti;
    }
    
    public boolean skontrolujDostupnost(IObjektovyTypNaMape paData) {
        for (IObjektovyTypNaMape akt : this.zoznamNehnutelnosti) {
            if (akt.porovnajSObjektom(paData)) {
                return true;
            }
        }
        
        return false;
    }
    
    public boolean porovnajSObjektom(IObjektovyTypNaMape paData) {
            if (paData.dajSupisneCislo() == this.dajSupisneCislo()) {
                if (paData.dajPopis() == this.dajPopis()) {
                    System.out.println("+++++++++++++");
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
