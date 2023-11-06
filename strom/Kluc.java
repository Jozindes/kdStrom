/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package strom;

/**
 *
 * @author Jozef
 */
public class Kluc<T extends Comparable<T>> implements Porovnatelne {
    
    private T hodnotaKluca;
    
    public Kluc(T paHodnotaKluca) {
        this.hodnotaKluca = paHodnotaKluca;
    }
    
    public T dajHodnotuKluca() {
        return hodnotaKluca;
    }
}
