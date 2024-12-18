/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import spravca.Spravca;
import datova_cast.IObjektovyTypNaMape;
import datova_cast.Nehnutelnost;
import strom.Vrchol;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import strom.Kluc;
import strom.Porovnatelne;

/**
 *
 * @author Jozef
 */
public class ZoznamNehnutelnosti extends javax.swing.JFrame {
    private static Spravca instancia;
    private ArrayList<Vrchol> zoznamNajdenych;
    /**
     * Creates new form ZoznamNehnutelnosti
     */
    public ZoznamNehnutelnosti(Spravca paInstancia) {
        initComponents();
        this.instancia = paInstancia;
    }
    
    public void vyplnTabulkuNehnutelnosti() {

        double doleX = Double.parseDouble(dolneX.getText());
        double horeX = Double.parseDouble(horneX.getText());
        double doleY = Double.parseDouble(dolneY.getText());
        double horeY = Double.parseDouble(horneY.getText());
        
        double[] poleSuradnic = {doleX, horeX, doleY, horeY};
        
        ArrayList<Vrchol> zoznamNehnutelnosti = instancia.najdiNehnutelnostiNaGPS(poleSuradnic);
        
        DefaultTableModel model = (DefaultTableModel) tabulkaNehnutelnosti.getModel();
        
        while(model.getRowCount() > 0) {
            model.removeRow(0);
        }
        
        int pocitadlo = 0;
        if (zoznamNehnutelnosti != null) {
            for (Vrchol akt : zoznamNehnutelnosti) {
                pocitadlo = pocitadlo + 1;
                model.addRow(new Object[]{pocitadlo, akt.dajKlucNaIndexe(0).dajHodnotuKluca(), akt.dajNehnutelnost().dajSirku(), akt.dajKlucNaIndexe(1).dajHodnotuKluca(), akt.dajNehnutelnost().dajDlzku(), akt.dajNehnutelnost().dajSupisneCislo(), akt.dajNehnutelnost().dajPopis()});
            }
        }
        
        this.zoznamNajdenych = zoznamNehnutelnosti;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        dolneX = new javax.swing.JTextField();
        dolneY = new javax.swing.JTextField();
        horneX = new javax.swing.JTextField();
        horneY = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabulkaNehnutelnosti = new javax.swing.JTable();
        vyhladat = new javax.swing.JButton();
        hlavneMenu = new javax.swing.JButton();
        upravit = new javax.swing.JButton();
        refresh = new javax.swing.JButton();
        zmaz = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Zadajte dolné X:");

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Zadajte horné X:");

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Zadajte dolné Y:");

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Zadajte horné Y:");

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Vyhľadanie nehnuteľností:");

        tabulkaNehnutelnosti.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Súradnica X", "E/W", "Súradnica Y", "N/S", "Súpisné číslo", "Popis"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabulkaNehnutelnosti);

        vyhladat.setText("Vyhľadať");
        vyhladat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vyhladatActionPerformed(evt);
            }
        });

        hlavneMenu.setText("Hlavné menu");
        hlavneMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hlavneMenuActionPerformed(evt);
            }
        });

        upravit.setText("Upraviť");
        upravit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                upravitActionPerformed(evt);
            }
        });

        refresh.setText("Refresh");
        refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshActionPerformed(evt);
            }
        });

        zmaz.setText("Zmaž");
        zmaz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zmazActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(171, 171, 171)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                        .addComponent(hlavneMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(72, 72, 72)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(18, 18, 18)
                                        .addComponent(dolneY, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel4))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(18, 18, 18)
                                        .addComponent(dolneX, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(30, 30, 30)
                                        .addComponent(jLabel2)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(horneX, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(horneY, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(234, 234, 234)
                                .addComponent(vyhladat)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(zmaz)
                                .addGap(135, 135, 135)
                                .addComponent(upravit)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(refresh)
                                .addGap(14, 14, 14))
                            .addComponent(jScrollPane1))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel5))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(hlavneMenu)))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(dolneX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(horneX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(dolneY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(horneY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(vyhladat)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(upravit)
                    .addComponent(refresh)
                    .addComponent(zmaz))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void vyhladatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vyhladatActionPerformed
        this.vyplnTabulkuNehnutelnosti();
    }//GEN-LAST:event_vyhladatActionPerformed

    private void hlavneMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hlavneMenuActionPerformed
        this.setVisible(false);
        Menu hm = new Menu(instancia);
        hm.setVisible(true);
    }//GEN-LAST:event_hlavneMenuActionPerformed

    private void upravitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_upravitActionPerformed
        int column = 0;
        int row = tabulkaNehnutelnosti.getSelectedRow();
        int vybrane = (int) tabulkaNehnutelnosti.getModel().getValueAt(row, column);
        
        Vrchol upravovany = this.zoznamNajdenych.get(vybrane - 1);
        UpravenieObjektu nu = new UpravenieObjektu(upravovany, instancia);
        nu.setVisible(true);             
    }//GEN-LAST:event_upravitActionPerformed

    private void refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshActionPerformed
        DefaultTableModel model = (DefaultTableModel) tabulkaNehnutelnosti.getModel();
        
        while(model.getRowCount() > 0) {
            model.removeRow(0);
        }
        
        double doleX = Double.parseDouble(dolneX.getText());
        double horeX = Double.parseDouble(horneX.getText());
        double doleY = Double.parseDouble(dolneY.getText());
        double horeY = Double.parseDouble(horneY.getText());
        
        double[] poleSuradnic = {doleX, horeX, doleY, horeY};
        ArrayList<Vrchol> zoznamNehnutelnosti = instancia.najdiNehnutelnostiNaGPS(poleSuradnic);
        
        int pocitadlo = 0;
        for (Vrchol akt : zoznamNehnutelnosti) {
            pocitadlo = pocitadlo + 1;
            model.addRow(new Object[]{pocitadlo, akt.dajKlucNaIndexe(0).dajHodnotuKluca(), akt.dajNehnutelnost().dajSirku(), akt.dajKlucNaIndexe(1).dajHodnotuKluca(), akt.dajNehnutelnost().dajDlzku(), akt.dajNehnutelnost().dajSupisneCislo(), akt.dajNehnutelnost().dajPopis()});
        }
    }//GEN-LAST:event_refreshActionPerformed

    private void zmazActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zmazActionPerformed
        int row = tabulkaNehnutelnosti.getSelectedRow();
        String value = tabulkaNehnutelnosti.getModel().getValueAt(row, 1).toString();
        double zvalue1 = Double.parseDouble(value);
        value = tabulkaNehnutelnosti.getModel().getValueAt(row, 3).toString();
        double zvalue2 = Double.parseDouble(value);
        
        Porovnatelne[] poleSuradnic = new Porovnatelne[2];
        
        Kluc<Double> kluc1 = new Kluc(zvalue1);
        Kluc<Double> kluc2 = new Kluc(zvalue2);
        
        poleSuradnic[0] = kluc1;       
        poleSuradnic[1] = kluc2;
        
        String supi = tabulkaNehnutelnosti.getModel().getValueAt(row, 5).toString();
        int sup = Integer.parseInt(supi);
        String popis = tabulkaNehnutelnosti.getModel().getValueAt(row, 6).toString();
        
        for (Vrchol akt : this.zoznamNajdenych) {
            if ((akt.dajKlucNaIndexe(0).dajHodnotuKluca().compareTo(poleSuradnic[0].dajHodnotuKluca()) == 0) && 
                 (akt.dajKlucNaIndexe(1).dajHodnotuKluca().compareTo(poleSuradnic[1].dajHodnotuKluca()) == 0)) {
                if ((akt.dajNehnutelnost().dajSupisneCislo() == sup) && (akt.dajNehnutelnost().dajPopis() == popis)) {
                    instancia.zmazNehnutelnost(akt.dajPoleKlucov(), akt.dajNehnutelnost());
                }
            }   
        }
        
        System.out.println(sup + " " + popis);
        
        this.vyplnTabulkuNehnutelnosti();
    }//GEN-LAST:event_zmazActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ZoznamNehnutelnosti.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ZoznamNehnutelnosti.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ZoznamNehnutelnosti.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ZoznamNehnutelnosti.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ZoznamNehnutelnosti(instancia).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField dolneX;
    private javax.swing.JTextField dolneY;
    private javax.swing.JButton hlavneMenu;
    private javax.swing.JTextField horneX;
    private javax.swing.JTextField horneY;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton refresh;
    private javax.swing.JTable tabulkaNehnutelnosti;
    private javax.swing.JButton upravit;
    private javax.swing.JButton vyhladat;
    private javax.swing.JButton zmaz;
    // End of variables declaration//GEN-END:variables
}
