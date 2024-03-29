package sharefactory;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Antesh
 */
public class IPFrame extends javax.swing.JFrame {

    static String ip;
    static String fname;

    public IPFrame() {
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

        sip = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        filename = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(getExtendedState());
        setResizable(false);
        getContentPane().setLayout(null);

        sip.setToolTipText("IPv4");
        sip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sipActionPerformed(evt);
            }
        });
        getContentPane().add(sip);
        sip.setBounds(150, 40, 216, 30);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("Server IP");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(29, 38, 76, 20);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 0, 0));
        jLabel2.setText("File Name");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(20, 110, 100, 20);

        filename.setToolTipText("File name with extension");
        filename.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filenameActionPerformed(evt);
            }
        });
        getContentPane().add(filename);
        filename.setBounds(150, 110, 216, 30);

        jButton1.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(204, 0, 0));
        jButton1.setText("Recieve");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(80, 200, 100, 31);

        jButton2.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(204, 0, 0));
        jButton2.setText("Cancel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(290, 199, 110, 30);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sharefactory/DV1996145.jpg"))); // NOI18N
        jLabel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(jLabel3);
        jLabel3.setBounds(0, 0, 518, 310);

        setSize(new java.awt.Dimension(524, 339));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Send_Recieve sr = new Send_Recieve();
        sr.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ip = sip.getText();
        fname = filename.getText();
        File f = null;
        try {

            //Initialize the FileOutputStream to the output file's full path.
            f = new File("C:\\Shared Document");
            if (!f.exists()) {
                f.mkdir();

                Socket socket = new Socket(ip, 8787);
                byte[] contents = new byte[10000];
                ////////////////////////////////
                FileOutputStream fos = new FileOutputStream("C:\\Shared Document\\" + fname);
                BufferedOutputStream bos = new BufferedOutputStream(fos);
                InputStream is = socket.getInputStream();

                //No of bytes read in one read() call
                int bytesRead = 0;

                while ((bytesRead = is.read(contents)) != -1) {
                    bos.write(contents, 0, bytesRead);
                }

                bos.flush();
                socket.close();
                System.out.println("File saved successfully!");
            } else {
                // String s=(new StringBuilder())
                File pa = new File("C:\\Shared Document\\" + fname);
                if (pa.exists()) {
                    JOptionPane.showMessageDialog(null, "Name already Exists");

                    new Send_Recieve().setVisible(false);
                    new IPFrame().setVisible(true);

                } else {

                    Socket socket = new Socket(ip, 8787);
                    byte[] contents = new byte[10000];
                    FileOutputStream fos = new FileOutputStream("C:\\Shared Document\\" + fname);
                    BufferedOutputStream bos = new BufferedOutputStream(fos);
                    InputStream is = socket.getInputStream();

                    //No of bytes read in one read() call
                    int bytesRead = 0;

                    while ((bytesRead = is.read(contents)) != -1) {
                        bos.write(contents, 0, bytesRead);
                    }

                    bos.flush();
                    socket.close();
                    System.out.println("File saved successfully!");
                }
            }

            setVisible(false);
        } catch (IOException ex) {
            Logger.getLogger(IPFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void filenameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filenameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_filenameActionPerformed

    private void sipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sipActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sipActionPerformed

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
            java.util.logging.Logger.getLogger(IPFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IPFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IPFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IPFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IPFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField filename;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField sip;
    // End of variables declaration//GEN-END:variables
}
