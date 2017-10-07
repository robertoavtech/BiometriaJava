/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biometria.view;

import br.com.cis.biox.sdk.CisBiox;
import javax.swing.JOptionPane;

/**
 *
 * @author Samuelson
 */
public class ViewBiometria extends javax.swing.JFrame {

    private static byte[] digital1;
    private static byte[] digital2;

    /**
     * Creates new form ViewBiometria
     */
    public ViewBiometria() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnDigital1 = new javax.swing.JButton();
        btnDigital2 = new javax.swing.JButton();
        btnComparar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Biometria");

        btnDigital1.setText("Digital1");
        btnDigital1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDigital1ActionPerformed(evt);
            }
        });

        btnDigital2.setText("Digital2");
        btnDigital2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDigital2ActionPerformed(evt);
            }
        });

        btnComparar.setText("Comparar");
        btnComparar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompararActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(btnDigital1)
                        .addGap(76, 76, 76)
                        .addComponent(btnDigital2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(151, 151, 151)
                        .addComponent(btnComparar)))
                .addContainerGap(103, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(124, 124, 124)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDigital1)
                    .addComponent(btnDigital2))
                .addGap(37, 37, 37)
                .addComponent(btnComparar)
                .addContainerGap(93, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnDigital1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDigital1ActionPerformed
        // TODO add your handling code here:

        CisBiox biox = new CisBiox();

        int iRetorno = biox.iniciar();

        if (iRetorno != 1) {
            JOptionPane.showMessageDialog(null, "Erro: " + CisBiox.mensagens(iRetorno));
            return;
        }

        new Thread(LerDigital1).start();
    }//GEN-LAST:event_btnDigital1ActionPerformed

    private void btnDigital2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDigital2ActionPerformed
        // TODO add your handling code here:

        CisBiox biox = new CisBiox();

        int iRetorno = biox.iniciar();

        if (iRetorno != 1) {
            JOptionPane.showMessageDialog(null, "Erro: " + CisBiox.mensagens(iRetorno));
            return;
        }

        new Thread(LerDigital2).start();
    }//GEN-LAST:event_btnDigital2ActionPerformed

    private void btnCompararActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompararActionPerformed
        // TODO add your handling code here:

        CisBiox biox = new CisBiox();

        biox.iniciar();

        int iRetorno = biox.compararDigital(digital1, digital2);

        switch (iRetorno) {
            case 1:
                JOptionPane.showMessageDialog(null, "Digitais são iguais!");
                break;
            case -2:
                JOptionPane.showMessageDialog(null, "Digitais são diferentes!");
                break;
            default:
                JOptionPane.showMessageDialog(null, "Erro: " + CisBiox.mensagens(iRetorno));
                break;
        }

        biox.finalizar();

    }//GEN-LAST:event_btnCompararActionPerformed

    private static Runnable LerDigital1 = new Runnable() {
        @Override
        public void run() {

            CisBiox biox = new CisBiox();

            digital1 = biox.capturarDigital();

            if (biox.getResultado() != 1) {
                biox.finalizar();
                return;
            }

            int iRetorno = biox.finalizar();

            if (iRetorno != 1) {
                JOptionPane.showMessageDialog(null, "Erro: " + CisBiox.mensagens(iRetorno));
                return;
            }

            JOptionPane.showMessageDialog(null, "Templante Gerado!");

        }
    };

    private static Runnable LerDigital2 = new Runnable() {
        @Override
        public void run() {

            CisBiox biox = new CisBiox();

            digital2 = biox.capturarDigital();

            if (biox.getResultado() != 1) {
                biox.finalizar();
                return;
            }

            int iRetorno = biox.finalizar();

            if (iRetorno != 1) {
                JOptionPane.showMessageDialog(null, "Erro: " + CisBiox.mensagens(iRetorno));
                return;
            }

            JOptionPane.showMessageDialog(null, "Templante Gerado!");

        }
    };

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
            java.util.logging.Logger.getLogger(ViewBiometria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewBiometria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewBiometria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewBiometria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewBiometria().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnComparar;
    private javax.swing.JButton btnDigital1;
    private javax.swing.JButton btnDigital2;
    // End of variables declaration//GEN-END:variables
}
