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
public interface Porovnatelne<T extends Comparable<T>> {
    public T dajHodnotuKluca();
}
