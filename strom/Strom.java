/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package strom;

import datova_cast.IObjektovyTypNaMape;
import strom.Vrchol;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

/**
 *
 * @author Jozef
 */
public class Strom {
    
    private Vrchol koren;
    private int dimenzia;
    
    public Strom(int paDimenzia) {
        this.koren = null;
        this.dimenzia = paDimenzia;
    }
    
    public boolean pridajVrchol(Vrchol paVrchol) {
        
        int uroven = 0;
        boolean vlozeny = false;
        Vrchol somVoVrchole = this.koren;
        
        if (this.koren == null) {
            this.koren = paVrchol;
            paVrchol.nastavUroven(uroven);
            System.out.println("Vlozil som koren na uroven: " + uroven);
            this.koren.nastavUroven(uroven);
            return true;
        } else {
            while (vlozeny == false) {
                
                int aktualnaDimenzia = somVoVrchole.dajUroven() % this.dimenzia;
                
                if (paVrchol.dajKlucNaIndexe(aktualnaDimenzia).dajHodnotuKluca().compareTo(somVoVrchole.dajKlucNaIndexe(aktualnaDimenzia).dajHodnotuKluca()) == -1 ||
                    paVrchol.dajKlucNaIndexe(aktualnaDimenzia).dajHodnotuKluca().compareTo(somVoVrchole.dajKlucNaIndexe(aktualnaDimenzia).dajHodnotuKluca()) == 0) {
                    if (somVoVrchole.mamLavehoSyna()) {
                        uroven = uroven + 1;
                        somVoVrchole = somVoVrchole.dajLavehoSyna();
                        continue;
                    } else {
                        somVoVrchole.nastavLavehoSyna(paVrchol);
                        System.out.println("Vlozil som ako laveho syna na uroven: " + (uroven + 1));
                        paVrchol.nastavUroven(uroven + 1);
                        paVrchol.nastavOtca(somVoVrchole);
                        vlozeny = true;
                        return true;
                    }
                }
                
                if (paVrchol.dajKlucNaIndexe(aktualnaDimenzia).dajHodnotuKluca().compareTo(somVoVrchole.dajKlucNaIndexe(aktualnaDimenzia).dajHodnotuKluca()) == 1) {
                    if (somVoVrchole.mamPravehoSyna()) {
                        uroven = uroven + 1;
                        somVoVrchole = somVoVrchole.dajPravehoSyna();
                        continue;
                    } else {
                        somVoVrchole.nastavPravehoSyna(paVrchol);
                        System.out.println("Vlozil som ako praveho syna na uroven: " + (uroven + 1));
                        paVrchol.nastavUroven(uroven + 1);
                        paVrchol.nastavOtca(somVoVrchole);
                        vlozeny = true;
                        return true;
                    }
                }                                    
            }
        }
        
        return false;
    }
    
    
    public Vrchol najdiMinumum(Vrchol paOdkial, int paIndexKluca) {
              
        if (paOdkial.mamLavehoSyna() == false && paOdkial.mamPravehoSyna() == false) {
            return paOdkial;
        }     
        
        LinkedList<Vrchol> zoznamVrcholov = new LinkedList<Vrchol>();
        zoznamVrcholov.add(paOdkial);
        Vrchol minimumVoVrchole = paOdkial;
                
        while (!zoznamVrcholov.isEmpty()) {
            
            Vrchol vyberam = zoznamVrcholov.get(0);
            
            if (vyberam.mamLavehoSyna()) {
                zoznamVrcholov.add(vyberam.dajLavehoSyna());
            }
            if (vyberam.mamPravehoSyna()) {
                zoznamVrcholov.add(vyberam.dajPravehoSyna());
            } 
            
            if (vyberam.dajKlucNaIndexe(paIndexKluca).dajHodnotuKluca().compareTo(minimumVoVrchole.dajKlucNaIndexe(paIndexKluca).dajHodnotuKluca()) == -1) {
                minimumVoVrchole = vyberam;
            }
            
            zoznamVrcholov.remove(0);

        }
        
       
       
        return minimumVoVrchole;
    }
    
    
    
    public Vrchol najdiMaximum(Vrchol paOdkial, int paIndexKluca) {
        
        if (paOdkial.mamLavehoSyna() == false && paOdkial.mamPravehoSyna() == false) {
            return paOdkial;
        }
        
        LinkedList<Vrchol> zoznamVrcholov = new LinkedList<Vrchol>();
        zoznamVrcholov.add(paOdkial);     
        Vrchol maximumVoVrchole = paOdkial;
        
        while (!zoznamVrcholov.isEmpty()) {
            Vrchol vyberam = zoznamVrcholov.get(0);
            
            if (vyberam.mamLavehoSyna()) {
                zoznamVrcholov.add(vyberam.dajLavehoSyna());
            }
            if (vyberam.mamPravehoSyna()) {
                zoznamVrcholov.add(vyberam.dajPravehoSyna());
            }
            
            if (vyberam.dajKlucNaIndexe(paIndexKluca).dajHodnotuKluca().compareTo(maximumVoVrchole.dajKlucNaIndexe(paIndexKluca).dajHodnotuKluca()) == 1) {
                maximumVoVrchole = vyberam;
            }
            
            zoznamVrcholov.remove(0);
        }
        
        return maximumVoVrchole;
    }
    
    
    public Vrchol najdiVrchol(Porovnatelne[] paPoleKlucov, IObjektovyTypNaMape data) {
               
        if (this.koren == null) {          
            System.out.println("Strom je PRAZDNY");
            return null;
        }
        
        Vrchol somVoVrchole = this.koren;
                
        while (true) {
            
            int pocetRovnakych = 0;
            for (int i = 0; i < paPoleKlucov.length; i++) {
                
                if (paPoleKlucov[i].dajHodnotuKluca().compareTo(somVoVrchole.dajKlucNaIndexe(i).dajHodnotuKluca()) == 0) {
                    pocetRovnakych = pocetRovnakych + 1;
                }           
            }
            if (pocetRovnakych == paPoleKlucov.length) {
                if (somVoVrchole.dajNehnutelnost().porovnajSObjektom(data)) {
                    System.out.println("Porovnaval som. " + paPoleKlucov[0].dajHodnotuKluca().toString() + " " + paPoleKlucov[1].dajHodnotuKluca().toString() + " / " + somVoVrchole.dajKlucNaIndexe(0).dajHodnotuKluca().toString() + " " + somVoVrchole.dajKlucNaIndexe(1).dajHodnotuKluca().toString());
                    //System.out.println("POHODAAAAAAAAAAAAA. ******************************");
                    return somVoVrchole;
                }
            } else {
                System.out.println("Porovnaval som. " + paPoleKlucov[0].dajHodnotuKluca().toString() + " " + paPoleKlucov[1].dajHodnotuKluca().toString() + " / " + somVoVrchole.dajKlucNaIndexe(0).dajHodnotuKluca().toString() + " " + somVoVrchole.dajKlucNaIndexe(1).dajHodnotuKluca().toString());
            }
            
                     
            int aktualnaDimenzia = somVoVrchole.dajUroven() % dimenzia;
            
            if ((paPoleKlucov[aktualnaDimenzia].dajHodnotuKluca().compareTo(somVoVrchole.dajKlucNaIndexe(aktualnaDimenzia).dajHodnotuKluca()) == -1) || 
               (paPoleKlucov[aktualnaDimenzia].dajHodnotuKluca().compareTo(somVoVrchole.dajKlucNaIndexe(aktualnaDimenzia).dajHodnotuKluca()) == 0)) {           
                if (somVoVrchole.mamLavehoSyna()) {
                    somVoVrchole = somVoVrchole.dajLavehoSyna();
                    
                    continue;
                } else {
                    System.out.println("Vraciam NULL z lav");
                    return somVoVrchole;
                }
            } 
            if (paPoleKlucov[aktualnaDimenzia].dajHodnotuKluca().compareTo(somVoVrchole.dajKlucNaIndexe(aktualnaDimenzia).dajHodnotuKluca()) == 1){
                if (somVoVrchole.mamPravehoSyna()) {
                    somVoVrchole = somVoVrchole.dajPravehoSyna();
                    
                    continue;
                } else {
                    System.out.println("Vraciam NULL z prav");
                    return somVoVrchole;
                }
            }
        }
    }
    
    public boolean vymazVrchol(Porovnatelne[] paPoleKlucov, IObjektovyTypNaMape data) {        
        Vrchol mazany = null;
        LinkedList<Vrchol> mazaneVrcholy = new LinkedList<Vrchol>();      
        mazany = najdiVrchol(paPoleKlucov, data);
        if (mazany == null) {
            System.out.println("Nenasiel som prvok v STROME.");
            mazany = this.hladajVPravomPodstrome(mazany, paPoleKlucov, data);
        }
        if (mazany == null) {
            System.out.println("Nenasiel som prvok ani v pravom podstrome.");
            return false;
        }
        
        
        if (mazany != null) {
            System.out.println("Prvok, ktory sa ide zmazat som nasiel na urovni: " + mazany.dajUroven());
        
            mazaneVrcholy.add(0, mazany);
            //System.out.println("Pocet vrcholov v zozname na zmazanie: " + mazaneVrcholy.size());
           
            while (mazany.mamLavehoSyna() || mazany.mamPravehoSyna()) {
                  
                int aktualnaDimenzia = mazany.dajUroven() % dimenzia;
                
                if (mazany.mamPravehoSyna()) {
                    mazany = najdiMinumum(mazany.dajPravehoSyna(), aktualnaDimenzia);
                    //System.out.println("Nasiel som najmensi prvok v pravom podstrome na urovni: " + mazany.dajUroven() + " s klucmi: " + mazany.dajKlucNaIndexe(0).dajHodnotuKluca().toString() + " a " + mazany.dajKlucNaIndexe(1).dajHodnotuKluca().toString());
                    mazaneVrcholy.add(0, mazany);
                    //System.out.println("Pocet vrcholov v zozname na zmazanie: " + mazaneVrcholy.size());
                    continue;
                }
                
                if (mazany.mamLavehoSyna()) {
                    mazany = najdiMaximum(mazany.dajLavehoSyna(), aktualnaDimenzia);
                    //System.out.println("Nasiel som najvacsi prvok v lavom podstrome na urovni: " + mazany.dajUroven() + " s klucmi: " + mazany.dajKlucNaIndexe(0).dajHodnotuKluca().toString() + " a " + mazany.dajKlucNaIndexe(1).dajHodnotuKluca().toString());
                    mazaneVrcholy.add(0, mazany);
                    //System.out.println("Pocet vrcholov v zozname na zmazanie: " + mazaneVrcholy.size());
                    continue;    
                }
            }
        
            if (mazaneVrcholy.size() == 1) {
                Vrchol vytahujem = mazaneVrcholy.get(0);
            
                // mazem koren
                if (vytahujem == this.koren) {
                    vytahujem.nastavOtca(null);
                    vytahujem.nastavPravehoSyna(null);
                    vytahujem.nastavLavehoSyna(null);
                    this.koren = null;
                    return true;
                }
                if (vytahujem.dajUroven() > 0) {
                // mazem list
                    if (vytahujem.dajOtca().dajLavehoSyna() == vytahujem) {
                        vytahujem.dajOtca().nastavLavehoSyna(null);
                    }
                    if (vytahujem.dajOtca().dajPravehoSyna() == vytahujem) {
                        vytahujem.dajOtca().nastavPravehoSyna(null);
                    }
                    vytahujem.nastavOtca(null);
                    vytahujem.nastavPravehoSyna(null);
                    vytahujem.nastavLavehoSyna(null);
                    vytahujem.nastavUroven(0);
                    return true;
                }
            }
        
            // prejdem si cez zoznam mazanych a vymienam vrcholy
            for (int i = 0; i < mazaneVrcholy.size() - 1; i++) {
            
                Vrchol posledny = mazaneVrcholy.get(i);
                Vrchol predposledny = mazaneVrcholy.get(i + 1);
            
                // medzi 2 vrcholmi je iny vrchol a posledny je list
                if ((predposledny.dajPravehoSyna() != posledny && predposledny.dajLavehoSyna() != posledny) && (posledny.mamLavehoSyna() == false && posledny.mamPravehoSyna() == false)) {
                    if (posledny.dajOtca().dajLavehoSyna() == posledny) {
                        posledny.dajOtca().nastavLavehoSyna(null);
                    }
                    if (posledny.dajOtca().dajPravehoSyna() == posledny) {
                        posledny.dajOtca().nastavPravehoSyna(null);
                    }
                    posledny.nastavOtca(null);
                
                    posledny.nastavOtca(predposledny.dajOtca());
                    posledny.nastavLavehoSyna(predposledny.dajLavehoSyna());
                    posledny.nastavPravehoSyna(predposledny.dajPravehoSyna());
                    posledny.nastavUroven(predposledny.dajUroven());
                
                    if (posledny.mamLavehoSyna()) {
                        posledny.dajLavehoSyna().nastavOtca(posledny);
                    }
                    if (posledny.mamPravehoSyna()) {
                        posledny.dajPravehoSyna().nastavOtca(posledny);
                    }
                
                    if (predposledny.dajOtca() != null) {
                        if (predposledny.dajOtca().dajLavehoSyna() == predposledny) {
                            predposledny.dajOtca().nastavLavehoSyna(posledny);
                        }
                        if (predposledny.dajOtca().dajPravehoSyna() == predposledny) {
                            predposledny.dajOtca().nastavPravehoSyna(posledny);
                        }
                    }
                
                    if (predposledny == this.koren) {
                        this.koren = posledny;
                    }
                }
            
                // medzi 2 vrcholmi je iny vrchol a posledny nie je list
                if ((predposledny.dajPravehoSyna() != posledny && predposledny.dajLavehoSyna() != posledny) && (posledny.mamLavehoSyna() == true || posledny.mamPravehoSyna() == true)) {
                
                
                
                    posledny.nastavOtca(predposledny.dajOtca());
                    posledny.nastavLavehoSyna(predposledny.dajLavehoSyna());
                    posledny.nastavPravehoSyna(predposledny.dajPravehoSyna());
                    posledny.nastavUroven(predposledny.dajUroven());
                    
                    if (posledny.mamLavehoSyna()) {
                        posledny.dajLavehoSyna().nastavOtca(posledny);
                    }
                    if (posledny.mamPravehoSyna()) {
                        posledny.dajPravehoSyna().nastavOtca(posledny);
                    }
                
                    if (posledny.dajOtca() != null) {
                        if (predposledny.dajOtca().dajLavehoSyna() == predposledny) {
                            predposledny.dajOtca().nastavLavehoSyna(posledny);
                        }
                        if (predposledny.dajOtca().dajPravehoSyna() == predposledny) {
                            predposledny.dajOtca().nastavPravehoSyna(posledny);
                        }
                    } 
                
                    if (predposledny == this.koren) {
                        this.koren = posledny;
                    }
                
                
                }
            
                // medzi 2 vrcholmi nie je iny vrchol a posledny je list 
                if ((predposledny.dajPravehoSyna() == posledny || predposledny.dajLavehoSyna() == posledny) && (posledny.mamLavehoSyna() == false && posledny.mamPravehoSyna() == false)) {
                    if (predposledny.dajLavehoSyna() == posledny) {
                        predposledny.nastavLavehoSyna(null);
                    }
                    if (predposledny.dajPravehoSyna() == posledny) {
                        predposledny.nastavPravehoSyna(null);
                    }
                    posledny.nastavOtca(null);
                
                
                
                    posledny.nastavOtca(predposledny.dajOtca());
                    posledny.nastavLavehoSyna(predposledny.dajLavehoSyna());
                    posledny.nastavPravehoSyna(predposledny.dajPravehoSyna());
                    posledny.nastavUroven(predposledny.dajUroven());
                
                    if (posledny.mamLavehoSyna()) {
                        posledny.dajLavehoSyna().nastavOtca(posledny);
                    }
                    if (posledny.mamPravehoSyna()) {
                        posledny.dajPravehoSyna().nastavOtca(posledny);
                    }
                
                
                    if (predposledny == this.koren) {
                        this.koren = posledny;
                    }
                
               
                    if (posledny.dajOtca() != null) {
                        if (predposledny.dajOtca().dajPravehoSyna() == predposledny) {
                            predposledny.dajOtca().nastavPravehoSyna(posledny);
                        }
                        if (predposledny.dajOtca().dajLavehoSyna() == predposledny) {
                            predposledny.dajOtca().nastavLavehoSyna(posledny);
                        }
                    }
                
                
                }
            
                // medzi 2 vrcholmi nie je iny vrchol a posledny nie je list 
                if ((predposledny.dajPravehoSyna() == posledny || predposledny.dajLavehoSyna() == posledny) && (posledny.mamLavehoSyna() == true || posledny.mamPravehoSyna() == true)) {
                               
                    posledny.nastavOtca(predposledny.dajOtca());
                    posledny.nastavLavehoSyna(predposledny.dajLavehoSyna());
                    posledny.nastavPravehoSyna(predposledny.dajPravehoSyna());
                    posledny.nastavUroven(predposledny.dajUroven());
                
                    if (posledny.mamLavehoSyna()) {
                        posledny.dajLavehoSyna().nastavOtca(posledny);
                    }
                    if (posledny.mamPravehoSyna()) {
                        posledny.dajPravehoSyna().nastavOtca(posledny);
                    }
                           
                    if (predposledny == this.koren) {
                        this.koren = posledny;
                    }
                
               
                    if (posledny.dajOtca() != null) {
                        if (predposledny.dajOtca().dajPravehoSyna() == predposledny) {
                            predposledny.dajOtca().nastavPravehoSyna(posledny);
                        }
                        if (predposledny.dajOtca().dajLavehoSyna() == predposledny) {
                            predposledny.dajOtca().nastavLavehoSyna(posledny);
                        }
                    }
                             
                }
                
            }
        
        }
        return true;
        
        
    }
    
    public void vykresliStrom() {
        LinkedList<Vrchol> zoznamVrcholov = new LinkedList<Vrchol>();
        zoznamVrcholov.add(this.koren);
        
        int urovnicka = 0;
        while (!zoznamVrcholov.isEmpty()) {
            
            Vrchol vyberam = zoznamVrcholov.get(0);
            if (vyberam != null) {
                if (vyberam.mamLavehoSyna()) {
                    zoznamVrcholov.add(vyberam.dajLavehoSyna());
                }
                if (vyberam.mamPravehoSyna()) {
                    zoznamVrcholov.add(vyberam.dajPravehoSyna());
                }
             
            
            
            
            if (vyberam.dajOtca() != null) {
                System.out.println("Ja som: " + vyberam.dajKlucNaIndexe(0).dajHodnotuKluca().toString() + " a " + vyberam.dajKlucNaIndexe(1).dajHodnotuKluca().toString() + " (" + "Moj otec: " + vyberam.dajOtca().dajKlucNaIndexe(0).dajHodnotuKluca().toString() + " a " + vyberam.dajOtca().dajKlucNaIndexe(1).dajHodnotuKluca().toString() + ") + Uroven: " + vyberam.dajUroven());
            } else {
                System.out.println("Ja som: " + vyberam.dajKlucNaIndexe(0).dajHodnotuKluca().toString() + " a " + vyberam.dajKlucNaIndexe(1).dajHodnotuKluca().toString() + " (" + "Moj otec: null");
            }
            }
            zoznamVrcholov.remove(0);

        }
    }
    
    public int dajPocetPrvkov() {
        if (this.koren != null) {
            Vrchol somV = this.koren;
            int pocetPrvkov = 0;
        
            LinkedList<Vrchol> zoznamVrcholov = new LinkedList<Vrchol>();
            zoznamVrcholov.add(somV);     
        
            while (!zoznamVrcholov.isEmpty()) {
                Vrchol vyberam = zoznamVrcholov.get(0);
            
                if (vyberam.mamLavehoSyna()) {
                    zoznamVrcholov.add(vyberam.dajLavehoSyna());
                }
                if (vyberam.mamPravehoSyna()) {
                    zoznamVrcholov.add(vyberam.dajPravehoSyna());
                }
                       
                zoznamVrcholov.remove(0);
                pocetPrvkov = pocetPrvkov + 1;
             }
        
            return pocetPrvkov;
        } else {
            return 0;
        }
    }
    
    public ArrayList<Vrchol> najdiVrcholyZRozsahu(double[] paPoleRozsahov) {
        
        ArrayList<Vrchol> zoznamVrcholov = new ArrayList<Vrchol>();
        ArrayList<Vrchol> zoznamPrejdenych = new ArrayList<Vrchol>();
        
        if (this.koren != null) {
        zoznamPrejdenych.add(this.koren);
                                
        while (!zoznamPrejdenych.isEmpty()) {
            
            Vrchol vyberam = zoznamPrejdenych.get(0);
            Vrchol lavySyn = vyberam.dajLavehoSyna();
            Vrchol pravySyn = vyberam.dajPravehoSyna();
            
            //double dolX = paPoleRozsahov[0];
            //double horX = paPoleRozsahov[1];
            //double dolY = paPoleRozsahov[2];
            //double horY = paPoleRozsahov[3];
            int dim = vyberam.dajUroven() % 2;
            /*
            if (dim == 0) {
                if (paPoleRozsahov[dim] <= (double) vyberam.dajKlucNaIndexe(dim).dajHodnotuKluca()) {
                    if (lavySyn != null) {
                        zoznamPrejdenych.add(lavySyn);
                    }
                }
                if (paPoleRozsahov[dim + 1] > (double) vyberam.dajKlucNaIndexe(dim).dajHodnotuKluca()) {
                    if (pravySyn != null) {
                        zoznamPrejdenych.add(pravySyn);
                    }
                }
            }
            if (dim == 1) {
                if (paPoleRozsahov[dim + 1] <= (double) vyberam.dajKlucNaIndexe(dim).dajHodnotuKluca()) {
                    if (lavySyn != null) {
                        zoznamPrejdenych.add(lavySyn);
                    }
                }
                if (paPoleRozsahov[dim + 2] > (double) vyberam.dajKlucNaIndexe(dim).dajHodnotuKluca()) {
                    if (pravySyn != null) {
                        zoznamPrejdenych.add(pravySyn);
                    }
                }
            }
            */
            
            Porovnatelne[] poleKlucov = new Porovnatelne[4];
        
            Kluc<Double> dolneX = new Kluc(new Double(paPoleRozsahov[0]));
            Kluc<Double> horneX = new Kluc(new Double(paPoleRozsahov[1]));
            Kluc<Double> dolneY = new Kluc(new Double(paPoleRozsahov[2]));
            Kluc<Double> horneY = new Kluc(new Double(paPoleRozsahov[3]));
            
            poleKlucov[0] = dolneX;
            poleKlucov[1] = horneX;
            poleKlucov[2] = dolneY;
            poleKlucov[3] = horneY;
            /*
            if (paPoleRozsahov[(dim % dimenzia) + dim] <= (double) vyberam.dajKlucNaIndexe(dim).dajHodnotuKluca()) {
                if (lavySyn != null) {
                    zoznamPrejdenych.add(lavySyn);
                }
            }
            if (paPoleRozsahov[(dim % dimenzia) + dim + 1] > (double) vyberam.dajKlucNaIndexe(dim).dajHodnotuKluca()) {
                if (pravySyn != null) {
                    zoznamPrejdenych.add(pravySyn);
                }
            }            
            */
            
            if ((poleKlucov[(dim % dimenzia) + dim].dajHodnotuKluca().compareTo(vyberam.dajKlucNaIndexe(dim).dajHodnotuKluca()) == -1) || (poleKlucov[(dim % dimenzia) + dim].dajHodnotuKluca().compareTo(vyberam.dajKlucNaIndexe(dim).dajHodnotuKluca()) == 0)) {
                if (lavySyn != null) {
                    zoznamPrejdenych.add(lavySyn);
                }
            }
            if (poleKlucov[(dim % dimenzia) + dim + 1].dajHodnotuKluca().compareTo(vyberam.dajKlucNaIndexe(dim).dajHodnotuKluca()) == 1) {
                if (pravySyn != null) {
                    zoznamPrejdenych.add(pravySyn);
                }
            } 
            
            
            
            int pocetUspechov = 0;
            for (int i = 0; i < paPoleRozsahov.length; i = i + 2) {
                int aktualnaDimenzia = i / this.dimenzia;
                                
                if (((vyberam.dajKlucNaIndexe(aktualnaDimenzia).dajHodnotuKluca().compareTo(paPoleRozsahov[i]) == 0) || 
                    (vyberam.dajKlucNaIndexe(aktualnaDimenzia).dajHodnotuKluca().compareTo(paPoleRozsahov[i]) == 1)) &&
                    ((vyberam.dajKlucNaIndexe(aktualnaDimenzia).dajHodnotuKluca().compareTo(paPoleRozsahov[i + 1]) == -1) ||
                     (vyberam.dajKlucNaIndexe(aktualnaDimenzia).dajHodnotuKluca().compareTo(paPoleRozsahov[i + 1]) == 0))) {
                        pocetUspechov = pocetUspechov + 1;
                }
            }
            
            if (pocetUspechov == this.dimenzia) {
                zoznamVrcholov.add(vyberam);
            }
            
            zoznamPrejdenych.remove(0);
        }
        }
        
        return zoznamVrcholov;
        
        /*
        if (this.koren == null) {
            return null;
        }
        
        Stack<Vrchol> zasobnik = new Stack<Vrchol>();
        ArrayList<Vrchol> zoznamVrcholov = new ArrayList<Vrchol>();
        
        Vrchol aktualny = this.koren;
        
        while (aktualny != null || zasobnik.size() > 0) 
        { 
            while (aktualny !=  null) 
            { 
                zasobnik.push(aktualny); 
                aktualny = aktualny.dajLavehoSyna(); 
            } 
  
            aktualny = zasobnik.pop(); 
            
            int pocetUspechov = 0;
            for (int i = 0; i < paPoleRozsahov.length; i = i + 2) {
                int aktualnaDimenzia = i / this.dimenzia;
                                
                if (((aktualny.dajKlucNaIndexe(aktualnaDimenzia).dajHodnotuKluca().compareTo(paPoleRozsahov[i]) == 0) || 
                    (aktualny.dajKlucNaIndexe(aktualnaDimenzia).dajHodnotuKluca().compareTo(paPoleRozsahov[i]) == 1)) &&
                    ((aktualny.dajKlucNaIndexe(aktualnaDimenzia).dajHodnotuKluca().compareTo(paPoleRozsahov[i + 1]) == -1) ||
                     (aktualny.dajKlucNaIndexe(aktualnaDimenzia).dajHodnotuKluca().compareTo(paPoleRozsahov[i + 1]) == 0))) {
                        pocetUspechov = pocetUspechov + 1;
                }
            }
            
            if (pocetUspechov == this.dimenzia) {
                zoznamVrcholov.add(aktualny);
            }

            aktualny = aktualny.dajPravehoSyna();
        } 
                                
        
            
            
            
            


        */
        //return zoznamVrcholov;
    }
    
    /*
    public ArrayList<Vrchol> najdiRovnakeVPodstrome(Vrchol paOdkial, int paIndexKluca, Porovnatelne[] paPole) {
        
        
        
        if (paOdkial.mamPravehoSyna() == false) {
            return null;
        }
        
        ArrayList<Vrchol> rovnake = new ArrayList<Vrchol>();
        LinkedList<Vrchol> presielSom = new LinkedList<Vrchol>();
        presielSom.add(paOdkial.dajPravehoSyna());
        
        
        while (!presielSom.isEmpty()) {
            Vrchol vyberam = presielSom.get(0);
            
            if (vyberam.mamLavehoSyna()) {
                presielSom.add(vyberam.dajLavehoSyna());
            }
            if (vyberam.mamPravehoSyna()) {
                presielSom.add(vyberam.dajPravehoSyna());
            }
            
            if (paIndexKluca == 0) {
                if ((vyberam.dajKlucNaIndexe(0).dajHodnotuKluca().compareTo(paPole[0].dajHodnotuKluca()) == 0) || 
                        (vyberam.dajKlucNaIndexe(0).dajHodnotuKluca().compareTo(paPole[0].dajHodnotuKluca()) == 1)) {
                    rovnake.add(vyberam);
                }
            }
            
            if (paIndexKluca == 1) {
                if ((vyberam.dajKlucNaIndexe(1).dajHodnotuKluca().compareTo(paPole[1].dajHodnotuKluca()) == 0) || 
                        (vyberam.dajKlucNaIndexe(1).dajHodnotuKluca().compareTo(paPole[1].dajHodnotuKluca()) == 1)) {
                    rovnake.add(vyberam);
                }
            }
            
            presielSom.remove(0);
        }
        
        
        return rovnake;
    }
    */
    
    public Vrchol hladajVPravomPodstrome(Vrchol paOdkial, Porovnatelne[] paPoleKlucov, IObjektovyTypNaMape data) {
        
        if (this.koren == null) {
            return null;
        }
        /*
        if (this.koren.mamPravehoSyna() == false) {
            return null;
        }
        */
        if (paOdkial.mamPravehoSyna() == false) {
            return null;
        }
        
        LinkedList<Vrchol> zoznamVrcholov = new LinkedList<Vrchol>();
        zoznamVrcholov.add(paOdkial.dajPravehoSyna());     
        
        while (!zoznamVrcholov.isEmpty()) {
            Vrchol vyberam = zoznamVrcholov.get(0);
            
            if (vyberam.mamLavehoSyna()) {
                zoznamVrcholov.add(vyberam.dajLavehoSyna());
            }
            if (vyberam.mamPravehoSyna()) {
                zoznamVrcholov.add(vyberam.dajPravehoSyna());
            }
            
            int pocetRovnakychSuradnic = 0;
            for (int i = 0; i < paPoleKlucov.length; i++) {
                if (vyberam.dajKlucNaIndexe(i).dajHodnotuKluca().compareTo(paPoleKlucov[i].dajHodnotuKluca()) == 0) {
                    pocetRovnakychSuradnic = pocetRovnakychSuradnic + 1;
                }
            }
            
            if (pocetRovnakychSuradnic == paPoleKlucov.length) {
                if (vyberam.dajNehnutelnost().equals(data)) {
                    return vyberam;
                }
            }
            
            zoznamVrcholov.remove(0);
        }
        return null;
    }
}
