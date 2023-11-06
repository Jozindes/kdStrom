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
public interface IObjektovyTypNaMape {
    public int dajSupisneCislo();
    public String dajPopis();
    public void nastavSupisneCislo(int paSupisneCislo);
    public void nastavPopis(String paPopis);
    public void pridatNovyObjektDoZoznamu(IObjektovyTypNaMape paData);
    public void odstranObjektZoZoznamu(IObjektovyTypNaMape paData);
    public ArrayList<IObjektovyTypNaMape> dajZoznamObjektov();
    public boolean skontrolujDostupnost(IObjektovyTypNaMape paData);
    public boolean porovnajSObjektom(IObjektovyTypNaMape paData); 
    public char dajSirku();
    public char dajDlzku();
}
