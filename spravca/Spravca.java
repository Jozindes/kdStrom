/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spravca;

import datova_cast.IObjektovyTypNaMape;
import datova_cast.Nehnutelnost;
import datova_cast.Parcela;
import java.io.File;
import java.io.FileWriter;
import strom.Porovnatelne;
import strom.Strom;
import strom.Kluc;
import strom.Vrchol;
import java.util.ArrayList;
import java.util.Random;
import com.opencsv.CSVWriter;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
/**
 *
 * @author Jozef
 */
public class Spravca {
    
    private Strom zoznamNehnutelnosti;
    private Strom zoznamParciel;
    
    public Spravca(){
        this.zoznamNehnutelnosti = new Strom(2);
        this.zoznamParciel = new Strom(2);
    }
    
    public boolean pridajNovuNehnutelnost(double[] paPoslanePoleKlucov, IObjektovyTypNaMape paData) {
        
        for (int i = 0; i < paPoslanePoleKlucov.length; i++) {
            if (paPoslanePoleKlucov[i] < 0) {
                return false;
            }
        }
        
        Porovnatelne[] poleKlucov = new Porovnatelne[2];
        
        Kluc<Double> kluc1 = new Kluc(new Double(paPoslanePoleKlucov[0]));
        Kluc<Double> kluc2 = new Kluc(new Double(paPoslanePoleKlucov[1]));
        poleKlucov[0] = kluc1;
        poleKlucov[1] = kluc2;
        Vrchol vrchol = new Vrchol(poleKlucov, paData);
        
        this.zoznamNehnutelnosti.pridajVrchol(vrchol);
        this.pridajNehnutelnostKParcele(paPoslanePoleKlucov, paData);
        
        return true;
    }
     
    public boolean pridajNovuParcelu(double[] paPoslanePoleKlucov, IObjektovyTypNaMape paData) {
        
        for (int i = 0; i < paPoslanePoleKlucov.length; i++) {
            if (paPoslanePoleKlucov[i] < 0) {
                return false;
            }
        }
        
        Porovnatelne[] poleKlucov = new Porovnatelne[2];
        
        Kluc<Double> kluc1 = new Kluc(new Double(paPoslanePoleKlucov[0]));
        Kluc<Double> kluc2 = new Kluc(new Double(paPoslanePoleKlucov[1]));
        poleKlucov[0] = kluc1;
        poleKlucov[1] = kluc2;
        Vrchol vrchol = new Vrchol(poleKlucov, paData);
        
        this.zoznamParciel.pridajVrchol(vrchol);
        this.pridajParceluKNehnutelnosti(paPoslanePoleKlucov, paData);
        
        return true;
    }
    
    public void pridajNehnutelnostKParcele(double[] paPoslanePoleKlucov, IObjektovyTypNaMape paData) {
        double[] zmena = {paPoslanePoleKlucov[0], paPoslanePoleKlucov[0], paPoslanePoleKlucov[1], paPoslanePoleKlucov[1]};
        ArrayList<Vrchol> zoznamParciel1 = this.najdiParcelyNaGPS(zmena);
        
        if (zoznamParciel1 == null) {
            System.out.println("Nenasiel som nic.");
        }
        if (zoznamParciel1 != null) {
            for (Vrchol akt : zoznamParciel1) {
                akt.dajNehnutelnost().pridatNovyObjektDoZoznamu(paData);
                System.out.println("Pridal som nehnutelnost k parcele");
                if (paData.skontrolujDostupnost(akt.dajNehnutelnost()) == false) {
                    paData.pridatNovyObjektDoZoznamu(akt.dajNehnutelnost());
                }
            }
        }
    }
    
    public void pridajParceluKNehnutelnosti(double[] paPoslanePoleKlucov, IObjektovyTypNaMape paData) {
        double[] zmena = {paPoslanePoleKlucov[0], paPoslanePoleKlucov[0], paPoslanePoleKlucov[1], paPoslanePoleKlucov[1]};

        ArrayList<Vrchol> zoznamNehnutelnosti1 = this.najdiNehnutelnostiNaGPS(zmena);
        if (zoznamNehnutelnosti1 == null) {
            System.out.println("Nenasiel som nic.");
        }
        
        if (zoznamNehnutelnosti1 != null) {
            for (Vrchol akt : zoznamNehnutelnosti1) {
                akt.dajNehnutelnost().pridatNovyObjektDoZoznamu(paData);
                System.out.println("Pridal som parcelu k nehnutelnosti.");
                if (paData.skontrolujDostupnost(akt.dajNehnutelnost()) == false) {
                    paData.pridatNovyObjektDoZoznamu(akt.dajNehnutelnost());
                }
            }
        }                    
    }
    
    public void odstranNehnutelnostZParcely(IObjektovyTypNaMape paData) {
        ArrayList<IObjektovyTypNaMape> zoznamParciel1 = paData.dajZoznamObjektov();
        System.out.println(zoznamParciel1.size());
        if (zoznamParciel1 == null) {
            System.out.println("Nenasiel som nic.");
        }
        if (zoznamParciel1 != null) {
            for (IObjektovyTypNaMape akt : zoznamParciel1) {
                akt.odstranObjektZoZoznamu(paData);
                System.out.println("Odstranil som nehnutelnost z parcely");           
            }
        }
    }
    
    public void odstranParceluZNehnutelnosti(IObjektovyTypNaMape paData) {
        ArrayList<IObjektovyTypNaMape> zoznamNehnutelnosti1 = paData.dajZoznamObjektov();
        
        if (zoznamNehnutelnosti1 == null) {
            System.out.println("Nenasiel som nic.");
        }
        if (zoznamNehnutelnosti1 != null) {
            for (IObjektovyTypNaMape akt : zoznamNehnutelnosti1) {
                akt.odstranObjektZoZoznamu(paData);
                System.out.println("Odstranil som parcelu z nehnutelnosti");           
            }
        }
    }
    
    public ArrayList<Vrchol> najdiNehnutelnostiNaGPS(double[] paSuradnice) {
        return this.zoznamNehnutelnosti.najdiVrcholyZRozsahu(paSuradnice);
    }
    
    public ArrayList<Vrchol> najdiParcelyNaGPS(double[] paSuradnice) {
        return this.zoznamParciel.najdiVrcholyZRozsahu(paSuradnice);
    }
    
    public boolean zmazNehnutelnost(Porovnatelne[] paPoleKlucov, IObjektovyTypNaMape data) {
        if (this.zoznamNehnutelnosti.vymazVrchol(paPoleKlucov, data)) {
            System.out.println("Uspesne odstranena.");
            this.odstranNehnutelnostZParcely(data);
            return true;
        } else {
            return false;
        }
        
    }
    
    public boolean zmazParcelu(Porovnatelne[] paPoleKlucov, IObjektovyTypNaMape data) {
        if (this.zoznamParciel.vymazVrchol(paPoleKlucov, data)) {
            System.out.println("Uspesne odstranena.");
            this.odstranParceluZNehnutelnosti(data);
            return true;
        } else {
            return false;
        }      
    }
    
    public void vygenerujNehnutelnosti(int pocet) {
        
        for (int i = 0; i < pocet; i++) {
                
            Random random = new Random();
        
            //double a1 = (Math.random() * 90);
            int sur1 = random.nextInt(100) + 1;
            double roundedDouble1 = (double) sur1;
            //double a2 = (Math.random() * 90);
            int sur2 = random.nextInt(100) + 1;
            double roundedDouble2 = (double) sur2;
                                
            double[] poleKlucov = {roundedDouble1, roundedDouble2};
                
            String popisStavby = "";
            int nahodneCisloPopis = random.nextInt(4);
            if (nahodneCisloPopis == 0) {
                popisStavby = "1-podlazny dom";
            }
            if (nahodneCisloPopis == 1) {
                popisStavby = "2-podlazny dom";
            }
            if (nahodneCisloPopis == 2) {
                popisStavby = "garzonka";
            }
            if (nahodneCisloPopis == 3) {
                popisStavby = "bytovka";
            }
            
            int nahodneZnaky = random.nextInt(2);
            char znak;
            char znak1;
            if (nahodneZnaky == 0) {
                znak = 'E';
            } else {
                znak = 'W';
            }
            
            nahodneZnaky = random.nextInt(2);
            if (nahodneZnaky == 0) {
                znak1 = 'N';
            } else {
                znak1 = 'S';
            }
            
            IObjektovyTypNaMape nov = new Nehnutelnost(i + 1, popisStavby, znak, znak1);
                
            this.pridajNovuNehnutelnost(poleKlucov, nov);
        }
    }
    
    public void vygenerujParcely(int pocet) {
        
        for (int i = 0; i < pocet; i++) {
                
            Random random = new Random();
        
            double a1 = (Math.random() * 90);
            double roundedDouble1 = Math.round(a1 * 100.0) / 100.0;
            double a2 = (Math.random() * 90);
            double roundedDouble2 = Math.round(a2 * 100.0) / 100.0;
                
            double[] poleKlucov = {roundedDouble1, roundedDouble2};
                
            String popisParcely = "";
            int nahodneCisloPopis = random.nextInt(4);
            if (nahodneCisloPopis == 0) {
                popisParcely = "kraj obce";
            }
            if (nahodneCisloPopis == 1) {
                popisParcely = "centrum obce";
            }
            if (nahodneCisloPopis == 2) {
                popisParcely = "mimo obce";
            }
            if (nahodneCisloPopis == 3) {
                popisParcely = "medzi krajom a centrom obce";
            }
            
            int nahodneZnaky = random.nextInt(2);
            char znak;
            char znak1;
            if (nahodneZnaky == 0) {
                znak = 'E';
            } else {
                znak = 'W';
            }
            
            nahodneZnaky = random.nextInt(2);
            if (nahodneZnaky == 0) {
                znak1 = 'N';
            } else {
                znak1 = 'S';
            }
            
            IObjektovyTypNaMape nov = new Parcela(i + 1, popisParcely, znak, znak1);
               
            this.pridajNovuParcelu(poleKlucov, nov);
        }
    }
    
    public void zapisNehnutelnosti() {
        
        File file = new File("nehnutelnosti.csv");
        try {
            FileWriter output = new FileWriter(file);
            CSVWriter write = new CSVWriter(output);

            // Header column value
            String[] header = { "Suradnica X", "Sirka", "Suradnica Y", "Dlzka", "Supisne cislo", "Popis" };
            write.writeNext(header);
            // Value
            
            double[] poleRozsahov = {0, 100, 0, 100};
            for (Vrchol akt : this.zoznamNehnutelnosti.najdiVrcholyZRozsahu(poleRozsahov)) {
                String xova = akt.dajKlucNaIndexe(0).dajHodnotuKluca().toString();
                String yova = akt.dajKlucNaIndexe(1).dajHodnotuKluca().toString();
                String supCis = String.valueOf(akt.dajNehnutelnost().dajSupisneCislo());
                String pop = akt.dajNehnutelnost().dajPopis();
                
                String znak = String.valueOf(akt.dajNehnutelnost().dajSirku());
                String znak1 = String.valueOf(akt.dajNehnutelnost().dajDlzku());
                
                String[] data1 = {xova, znak, yova, znak1, supCis, pop};
                write.writeNext(data1);
            }
                       
            write.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
    
    public void zapisParcely() {
        
        File file = new File("parcely.csv");
        try {
            FileWriter output = new FileWriter(file);
            CSVWriter write = new CSVWriter(output);

            // Header column value
            String[] header = { "Suradnica X", "Sirka", "Suradnica Y", "Dlzka", "Supisne cislo", "Popis" };
            write.writeNext(header);
            // Value
            
            double[] poleRozsahov = {0, 100, 0, 100};
            for (Vrchol akt : this.zoznamParciel.najdiVrcholyZRozsahu(poleRozsahov)) {
                String xova = akt.dajKlucNaIndexe(0).dajHodnotuKluca().toString();
                String yova = akt.dajKlucNaIndexe(1).dajHodnotuKluca().toString();
                String supCis = String.valueOf(akt.dajNehnutelnost().dajSupisneCislo());
                String pop = akt.dajNehnutelnost().dajPopis();
                
                String znak = String.valueOf(akt.dajNehnutelnost().dajSirku());
                String znak1 = String.valueOf(akt.dajNehnutelnost().dajDlzku());
                
                String[] data1 = {xova, znak, yova, znak1, supCis, pop};
                write.writeNext(data1);
            }
                       
            write.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
    
    public void citajNehnutelnosti() {
        try { 
  
            // Create an object of filereader class 
            // with CSV file as a parameter. 
            File file = new File("nehnutelnosti.csv");
            FileReader filereader = new FileReader(file); 
  
            // create csvReader object passing 
            // filereader as parameter 
            CSVReader csvReader = new CSVReader(filereader); 
            String[] nextRecord; 
            int zac = 0;
            // we are going to read data line by line 
            while ((nextRecord = csvReader.readNext()) != null) { 
                if (zac != 0) {
                double a1 = Double.parseDouble(nextRecord[0]);
                char znak = nextRecord[1].charAt(0);
                double a2 = Double.parseDouble(nextRecord[2]);
                char znak1 = nextRecord[3].charAt(0);
                                
                double[] poleKlucov = {a1, a2};
                int supCis = Integer.parseInt(nextRecord[4]);
                String popisStavby = nextRecord[5].toString();
            
                IObjektovyTypNaMape nov = new Nehnutelnost(supCis, popisStavby, znak, znak1);
                
                this.pridajNovuNehnutelnost(poleKlucov, nov);
                }
                zac = zac + 1;
                System.out.println(zac - 1);
            } 
        } 
        catch (Exception e) { 
            e.printStackTrace(); 
        } 
    }
      
    public void citajParcely() {
        try { 
  
            // Create an object of filereader class 
            // with CSV file as a parameter. 
            File file = new File("parcely.csv");
            FileReader filereader = new FileReader(file); 
  
            // create csvReader object passing 
            // filereader as parameter 
            CSVReader csvReader = new CSVReader(filereader); 
            String[] nextRecord; 
            int zac = 0;
            // we are going to read data line by line 
            while ((nextRecord = csvReader.readNext()) != null) { 
                if (zac != 0) {
                double a1 = Double.parseDouble(nextRecord[0]);
                char znak = nextRecord[1].charAt(0);
                double a2 = Double.parseDouble(nextRecord[2]);
                char znak1 = nextRecord[3].charAt(0);
                
                double[] poleKlucov = {a1, a2};
                int supCis = Integer.parseInt(nextRecord[4]);
                String popisStavby = nextRecord[5].toString();
            
                IObjektovyTypNaMape nov = new Parcela(supCis, popisStavby, znak, znak1);
                
                this.pridajNovuParcelu(poleKlucov, nov);
                }
                zac = zac + 1;
                System.out.println(zac - 1);
            } 
        } 
        catch (Exception e) { 
            e.printStackTrace(); 
        } 
    }
}
