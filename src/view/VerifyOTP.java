/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */



package view;

import controller.OTPController;
import javax.swing.JOptionPane;

/**
 *
 * @author Safal
 */
public class VerifyOTP extends javax.swing.JFrame {

  private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(VerifyOTP.class.getName());

private final String email;
private javax.swing.Timer countdownTimer;
private int secondsLeft = 300;
private javax.swing.JLabel lblTimer;
private javax.swing.JButton btnResend;

public VerifyOTP(String email) {
    initComponents();
    this.email = email;       // ← FIXES email underline
    setupAutoJump();
    setupTimer();
    setupResendButton();
}

private void setupAutoJump() {
    txt1.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
        @Override
        public void insertUpdate(javax.swing.event.DocumentEvent e) {
            if (txt1.getText().length() == 1) txt2.requestFocus();
        }
        @Override
        public void removeUpdate(javax.swing.event.DocumentEvent e) {}
        @Override
        public void changedUpdate(javax.swing.event.DocumentEvent e) {}
    });
    txt2.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
        @Override
        public void insertUpdate(javax.swing.event.DocumentEvent e) {
            if (txt2.getText().length() == 1) txt3.requestFocus();
        }
        @Override
        public void removeUpdate(javax.swing.event.DocumentEvent e) {}
        @Override
        public void changedUpdate(javax.swing.event.DocumentEvent e) {}
    });
    txt3.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
        @Override
        public void insertUpdate(javax.swing.event.DocumentEvent e) {
            if (txt3.getText().length() == 1) txt4.requestFocus();
        }
        @Override
        public void removeUpdate(javax.swing.event.DocumentEvent e) {}
        @Override
        public void changedUpdate(javax.swing.event.DocumentEvent e) {}
    });
    txt4.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
        @Override
        public void insertUpdate(javax.swing.event.DocumentEvent e) {
            if (txt4.getText().length() == 1) txt5.requestFocus();
        }
        @Override
        public void removeUpdate(javax.swing.event.DocumentEvent e) {}
        @Override
        public void changedUpdate(javax.swing.event.DocumentEvent e) {}
    });
    txt5.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
        @Override
        public void insertUpdate(javax.swing.event.DocumentEvent e) {
            if (txt5.getText().length() == 1) txt6.requestFocus();
        }
        @Override
        public void removeUpdate(javax.swing.event.DocumentEvent e) {}
        @Override
        public void changedUpdate(javax.swing.event.DocumentEvent e) {}
    });
}

private void setupTimer() {
    lblTimer = new javax.swing.JLabel("OTP expires in: 05:00");
    lblTimer.setBounds(730, 470, 300, 30);
    lblTimer.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 16));
    lblTimer.setForeground(new java.awt.Color(15, 75, 155));
    getContentPane().add(lblTimer);
    getContentPane().setComponentZOrder(lblTimer, 0);

    countdownTimer = new javax.swing.Timer(1000, e -> {
        secondsLeft--;
        int minutes = secondsLeft / 60;
        int seconds = secondsLeft % 60;
        lblTimer.setText(String.format("OTP expires in: %02d:%02d", minutes, seconds));
        if (secondsLeft <= 60) {
            lblTimer.setForeground(java.awt.Color.RED);
        }
        if (secondsLeft <= 0) {
            countdownTimer.stop();
            lblTimer.setText("OTP Expired!");
            lblTimer.setForeground(java.awt.Color.RED);
            btnResend.setEnabled(true);
            JOptionPane.showMessageDialog(VerifyOTP.this, "OTP expired! Request a new one.");
        }
    });
    countdownTimer.start();
}

private void setupResendButton() {
    btnResend = new javax.swing.JButton("Resend OTP");
    btnResend.setBounds(730, 790, 200, 40);
    btnResend.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 16));
    btnResend.setForeground(new java.awt.Color(15, 75, 155));
    btnResend.setEnabled(false);
    btnResend.setFocusPainted(false);
    btnResend.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    getContentPane().add(btnResend);
    getContentPane().setComponentZOrder(btnResend, 0);
    btnResend.addActionListener(e -> resendOTP());
}

private void resendOTP() {
    OTPController controller = new OTPController();
    boolean success = controller.resendOTP(email);

    if (success) {
        secondsLeft = 300;
        lblTimer.setForeground(new java.awt.Color(15, 75, 155));
        btnResend.setEnabled(false);
        countdownTimer.restart();
        txt1.setText(""); txt2.setText(""); txt3.setText("");
        txt4.setText(""); txt5.setText(""); txt6.setText("");
        txt1.requestFocus();
        JOptionPane.showMessageDialog(this, "New OTP sent successfully!");
    } else {
        JOptionPane.showMessageDialog(this, "Failed to send OTP!");
    }
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Verify_OTP = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        txt6 = new javax.swing.JTextField();
        btnBackToLogin = new javax.swing.JButton();
        txt1 = new javax.swing.JTextField();
        txt2 = new javax.swing.JTextField();
        txt5 = new javax.swing.JTextField();
        txt3 = new javax.swing.JTextField();
        txt4 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(1536, 864));
        getContentPane().setLayout(null);

        jPanel2.setBackground(new java.awt.Color(250, 241, 224));
        jPanel2.setPreferredSize(new java.awt.Dimension(544, 864));
        jPanel2.setLayout(null);

        jLabel32.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel32.setText("Almost there !");
        jLabel32.setPreferredSize(new java.awt.Dimension(635, 244));
        jPanel2.add(jLabel32);
        jLabel32.setBounds(60, 580, 680, 40);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setText("Find your perfect home");
        jLabel8.setPreferredSize(new java.awt.Dimension(635, 244));
        jPanel2.add(jLabel8);
        jLabel8.setBounds(80, 250, 360, 40);

        jLabel9.setFont(new java.awt.Font("Courier New", 1, 48)); // NOI18N
        jLabel9.setText("House Rental ");
        jLabel9.setPreferredSize(new java.awt.Dimension(635, 244));
        jPanel2.add(jLabel9);
        jLabel9.setBounds(90, 140, 420, 60);

        jLabel12.setFont(new java.awt.Font("Courier New", 1, 48)); // NOI18N
        jLabel12.setText("           System");
        jLabel12.setPreferredSize(new java.awt.Dimension(635, 244));
        jPanel2.add(jLabel12);
        jLabel12.setBounds(-140, 170, 880, 80);

        jLabel33.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel33.setText(" your OTP");
        jLabel33.setPreferredSize(new java.awt.Dimension(635, 244));
        jPanel2.add(jLabel33);
        jLabel33.setBounds(60, 630, 180, 20);

        jLabel34.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel34.setText("Please verify ");
        jLabel34.setPreferredSize(new java.awt.Dimension(635, 244));
        jPanel2.add(jLabel34);
        jLabel34.setBounds(60, 610, 680, 20);

        jLabel35.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel35.setText("is our priority");
        jLabel35.setPreferredSize(new java.awt.Dimension(635, 244));
        jPanel2.add(jLabel35);
        jLabel35.setBounds(180, 730, 680, 30);

        jLabel36.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(53, 100, 166));
        jLabel36.setText("Welcome Back,");
        jLabel36.setPreferredSize(new java.awt.Dimension(635, 244));
        jPanel2.add(jLabel36);
        jLabel36.setBounds(50, 540, 350, 30);

        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/Rectangle 4_1.png"))); // NOI18N
        jLabel37.setText("jLabel31");
        jPanel2.add(jLabel37);
        jLabel37.setBounds(180, 40, 120, 120);

        jLabel38.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel38.setText("Your security ");
        jLabel38.setPreferredSize(new java.awt.Dimension(635, 244));
        jPanel2.add(jLabel38);
        jLabel38.setBounds(180, 720, 680, 20);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/_7D47BD78-A051-46AC-9AA6-94ED3E6D9A1F_-removebg-preview 2.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        jPanel2.add(jLabel1);
        jLabel1.setBounds(0, 40, 540, 720);

        getContentPane().add(jPanel2);
        jPanel2.setBounds(0, 0, 544, 864);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setText("Enter 6-digit OTP");
        jLabel3.setPreferredSize(new java.awt.Dimension(635, 244));
        getContentPane().add(jLabel3);
        jLabel3.setBounds(710, 500, 220, 40);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel4.setText("Enter the OTP below to continue");
        jLabel4.setPreferredSize(new java.awt.Dimension(635, 244));
        getContentPane().add(jLabel4);
        jLabel4.setBounds(640, 140, 880, 40);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel6.setText("Please verify your OTP");
        jLabel6.setPreferredSize(new java.awt.Dimension(635, 244));
        getContentPane().add(jLabel6);
        jLabel6.setBounds(640, 60, 880, 60);

        Verify_OTP.setBackground(new java.awt.Color(15, 75, 155));
        Verify_OTP.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        Verify_OTP.setForeground(new java.awt.Color(255, 255, 255));
        Verify_OTP.setText("Verify OTP");
        Verify_OTP.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Verify_OTP.addActionListener(this::Verify_OTPActionPerformed);
        getContentPane().add(Verify_OTP);
        Verify_OTP.setBounds(690, 610, 580, 60);

        jLabel10.setFont(new java.awt.Font("Verdana", 1, 36)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(11, 75, 159));
        jLabel10.setText("Verify OTP");
        jLabel10.setPreferredSize(new java.awt.Dimension(635, 244));
        getContentPane().add(jLabel10);
        jLabel10.setBounds(640, 20, 880, 40);

        jLabel18.setFont(new java.awt.Font("Verdana", 1, 36)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(11, 75, 159));
        jLabel18.setText("House Rental System");
        jLabel18.setPreferredSize(new java.awt.Dimension(635, 244));
        getContentPane().add(jLabel18);
        jLabel18.setBounds(40, 60, 880, 40);

        jLabel19.setFont(new java.awt.Font("Verdana", 1, 36)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(11, 75, 159));
        jLabel19.setText("House Rental System");
        jLabel19.setPreferredSize(new java.awt.Dimension(635, 244));
        getContentPane().add(jLabel19);
        jLabel19.setBounds(40, 30, 880, 40);

        jLabel20.setFont(new java.awt.Font("Verdana", 1, 36)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(11, 75, 159));
        jLabel20.setText("House Rental System");
        jLabel20.setPreferredSize(new java.awt.Dimension(635, 244));
        getContentPane().add(jLabel20);
        jLabel20.setBounds(40, 50, 880, 40);

        jLabel27.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel27.setText("using GharSathi .     ");
        jLabel27.setPreferredSize(new java.awt.Dimension(635, 244));
        getContentPane().add(jLabel27);
        jLabel27.setBounds(60, 780, 680, 30);

        jLabel28.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel28.setText("using GharSathi .     ");
        jLabel28.setPreferredSize(new java.awt.Dimension(635, 244));
        getContentPane().add(jLabel28);
        jLabel28.setBounds(60, 780, 680, 30);

        jLabel29.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel29.setText("using GharSathi .     ");
        jLabel29.setPreferredSize(new java.awt.Dimension(635, 244));
        getContentPane().add(jLabel29);
        jLabel29.setBounds(60, 780, 680, 30);

        txt6.addActionListener(this::txt6ActionPerformed);
        getContentPane().add(txt6);
        txt6.setBounds(1190, 540, 70, 60);

        btnBackToLogin.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnBackToLogin.setForeground(new java.awt.Color(51, 51, 51));
        btnBackToLogin.setText("Back to Login");
        btnBackToLogin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnBackToLogin.addActionListener(this::btnBackToLoginActionPerformed);
        getContentPane().add(btnBackToLogin);
        btnBackToLogin.setBounds(700, 700, 570, 60);

        txt1.addActionListener(this::txt1ActionPerformed);
        getContentPane().add(txt1);
        txt1.setBounds(710, 540, 70, 60);

        txt2.addActionListener(this::txt2ActionPerformed);
        getContentPane().add(txt2);
        txt2.setBounds(800, 540, 70, 60);

        txt5.addActionListener(this::txt5ActionPerformed);
        getContentPane().add(txt5);
        txt5.setBounds(1090, 540, 70, 60);

        txt3.addActionListener(this::txt3ActionPerformed);
        getContentPane().add(txt3);
        txt3.setBounds(900, 540, 70, 60);

        txt4.addActionListener(this::txt4ActionPerformed);
        getContentPane().add(txt4);
        txt4.setBounds(990, 540, 70, 60);

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/ChatGPT Imag PM_1.png"))); // NOI18N
        getContentPane().add(jLabel13);
        jLabel13.setBounds(780, 170, 500, 410);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Verify_OTPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Verify_OTPActionPerformed


   
    String otp = txt1.getText() +
                 txt2.getText() +
                 txt3.getText() +
                 txt4.getText() +
                 txt5.getText() +
                 txt6.getText();

    if (otp.length() != 6) {
        JOptionPane.showMessageDialog(this, "Enter complete OTP");
        return;
    }

    OTPController controller = new OTPController();
    boolean verified = controller.verifyOTP(email, otp);

    if (verified) {
        countdownTimer.stop();  // ← stop timer on success
        JOptionPane.showMessageDialog(this, "OTP Verified");
        Reset_Password reset = new Reset_Password(email);
        reset.setVisible(true);
        this.dispose();
    } else {
        JOptionPane.showMessageDialog(this, "Invalid or Expired OTP");
    }

    }//GEN-LAST:event_Verify_OTPActionPerformed

    private void txt6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt6ActionPerformed

    private void btnBackToLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackToLoginActionPerformed

    countdownTimer.stop();  // ← stop timer when going back
    Login login = new Login();
    login.setVisible(true);
    this.dispose();
 // TODO add your handling code here:
    }//GEN-LAST:event_btnBackToLoginActionPerformed

    private void txt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt1ActionPerformed

    private void txt2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt2ActionPerformed

    private void txt5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt5ActionPerformed

    private void txt3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt3ActionPerformed

    private void txt4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt4ActionPerformed

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
       new VerifyOTP("test@gmail.com").setVisible(true);  // ← FIXED
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Verify_OTP;
    private javax.swing.JButton btnBackToLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txt1;
    private javax.swing.JTextField txt2;
    private javax.swing.JTextField txt3;
    private javax.swing.JTextField txt4;
    private javax.swing.JTextField txt5;
    private javax.swing.JTextField txt6;
    // End of variables declaration//GEN-END:variables
}
