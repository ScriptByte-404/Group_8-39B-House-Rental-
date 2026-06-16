package view;

import dao.BookingDAO;
import model.Booking;
import util.SessionManager;
import java.awt.Dimension;
import java.util.List;
import javax.swing.Box;
import javax.swing.JOptionPane;
// remove if not inside view folder
public class My_Bookings extends javax.swing.JFrame {

 private final BookingDAO bookingDAO = new BookingDAO();
public My_Bookings() {
    initComponents();
    loadBookings("All");   // default filter
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel36 = new javax.swing.JLabel();
        SearchBar_Button = new javax.swing.JButton();
        GharSathi_Text = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        SearchHouse_Button = new javax.swing.JButton();
        Bookings_Button = new javax.swing.JButton();
        SavedHouse_Button = new javax.swing.JButton();
        Profile_Button = new javax.swing.JButton();
        Dashboard_Button = new javax.swing.JButton();
        Menuelogo_Button = new javax.swing.JButton();
        Username_Text = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        Iconlogo_Button = new javax.swing.JButton();
        btnAll = new javax.swing.JButton();
        btnPending = new javax.swing.JButton();
        btnConfirmed = new javax.swing.JButton();
        btnCancelled = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        pnlBookings = new javax.swing.JPanel();
        lblEmpty = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(1536, 864));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel36.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel36.setText("House Rental ");
        jLabel36.setPreferredSize(new java.awt.Dimension(635, 244));
        getContentPane().add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 250, 40));

        SearchBar_Button.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        SearchBar_Button.setText("Search by Location,house name.......");
        SearchBar_Button.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        SearchBar_Button.addActionListener(this::SearchBar_ButtonActionPerformed);
        getContentPane().add(SearchBar_Button, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 30, 690, 40));

        GharSathi_Text.setBackground(new java.awt.Color(243, 243, 243));
        GharSathi_Text.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        GharSathi_Text.setText("System");
        GharSathi_Text.setBorder(null);
        GharSathi_Text.addActionListener(this::GharSathi_TextActionPerformed);
        getContentPane().add(GharSathi_Text, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 220, 50));

        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/GharSathi_Logo.png"))); // NOI18N
        jLabel31.setText("jLabel31");
        getContentPane().add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, -10, 120, -1));

        SearchHouse_Button.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        SearchHouse_Button.setText("Search Houses");
        SearchHouse_Button.addActionListener(this::SearchHouse_ButtonActionPerformed);
        getContentPane().add(SearchHouse_Button, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 110, 260, 50));

        Bookings_Button.setBackground(new java.awt.Color(15, 75, 155));
        Bookings_Button.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        Bookings_Button.setForeground(new java.awt.Color(255, 255, 255));
        Bookings_Button.setText("My Bookings");
        Bookings_Button.addActionListener(this::Bookings_ButtonActionPerformed);
        getContentPane().add(Bookings_Button, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 110, 260, 50));

        SavedHouse_Button.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        SavedHouse_Button.setText("Saved Houses");
        SavedHouse_Button.addActionListener(this::SavedHouse_ButtonActionPerformed);
        getContentPane().add(SavedHouse_Button, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 110, 260, 50));

        Profile_Button.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        Profile_Button.setText("Profile");
        Profile_Button.addActionListener(this::Profile_ButtonActionPerformed);
        getContentPane().add(Profile_Button, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 110, 260, 50));

        Dashboard_Button.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        Dashboard_Button.setText("Dashboard");
        Dashboard_Button.addActionListener(this::Dashboard_ButtonActionPerformed);
        getContentPane().add(Dashboard_Button, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 110, 250, 50));

        Menuelogo_Button.setBackground(new java.awt.Color(243, 243, 243));
        Menuelogo_Button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Menu.png"))); // NOI18N
        Menuelogo_Button.setBorder(null);
        Menuelogo_Button.addActionListener(this::Menuelogo_ButtonActionPerformed);
        getContentPane().add(Menuelogo_Button, new org.netbeans.lib.awtextra.AbsoluteConstraints(1490, 0, 50, 30));

        Username_Text.setBackground(new java.awt.Color(243, 243, 243));
        Username_Text.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        Username_Text.setText("Hello User");
        Username_Text.setBorder(null);
        Username_Text.setSelectedTextColor(new java.awt.Color(242, 242, 242));
        Username_Text.addActionListener(this::Username_TextActionPerformed);
        getContentPane().add(Username_Text, new org.netbeans.lib.awtextra.AbsoluteConstraints(1370, 50, -1, 20));

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/account_circle.png"))); // NOI18N
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(1320, 30, 40, 60));

        Iconlogo_Button.setBackground(new java.awt.Color(242, 242, 242));
        Iconlogo_Button.setForeground(new java.awt.Color(242, 242, 242));
        Iconlogo_Button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Bell_Icon.png"))); // NOI18N
        Iconlogo_Button.setBorder(null);
        Iconlogo_Button.addActionListener(this::Iconlogo_ButtonActionPerformed);
        getContentPane().add(Iconlogo_Button, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 20, 80, 80));

        btnAll.setBackground(new java.awt.Color(0, 102, 255));
        btnAll.setForeground(new java.awt.Color(255, 255, 255));
        btnAll.setText("All");
        btnAll.addActionListener(this::btnAllActionPerformed);
        getContentPane().add(btnAll, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, 110, 36));

        btnPending.setBackground(new java.awt.Color(243, 243, 247));
        btnPending.setForeground(new java.awt.Color(80, 80, 120));
        btnPending.setText("Pending");
        btnPending.addActionListener(this::btnPendingActionPerformed);
        getContentPane().add(btnPending, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 180, 110, 36));

        btnConfirmed.setBackground(new java.awt.Color(243, 243, 247));
        btnConfirmed.setForeground(new java.awt.Color(80, 80, 120));
        btnConfirmed.setText("Confirmed");
        btnConfirmed.addActionListener(this::btnConfirmedActionPerformed);
        getContentPane().add(btnConfirmed, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 180, 120, 36));

        btnCancelled.setBackground(new java.awt.Color(243, 243, 247));
        btnCancelled.setForeground(new java.awt.Color(80, 80, 120));
        btnCancelled.setText("Cancelled");
        btnCancelled.addActionListener(this::btnCancelledActionPerformed);
        getContentPane().add(btnCancelled, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 180, 120, 36));

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        pnlBookings.setBackground(new java.awt.Color(247, 248, 252));
        pnlBookings.setLayout(new javax.swing.BoxLayout(pnlBookings, javax.swing.BoxLayout.Y_AXIS));

        lblEmpty.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblEmpty.setForeground(new java.awt.Color(150, 150, 150));
        lblEmpty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEmpty.setText("No bookings found.");
        pnlBookings.add(lblEmpty);

        jScrollPane1.setViewportView(pnlBookings);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, 1450, 560));

        pack();
    }// </editor-fold>//GEN-END:initComponents
private void loadBookings(String statusFilter) {
    pnlBookings.removeAll();

    try {
        // Get logged-in user id from SessionManager
        int customerId = -1;
        if (util.SessionManager.getCurrentUser() != null) {
            customerId = util.SessionManager.getCurrentUser().getUserId();
        }
        if (customerId <= 0) {
            lblEmpty.setText("Please log in to view your bookings.");
            lblEmpty.setVisible(true);
            pnlBookings.add(lblEmpty);
            pnlBookings.revalidate();
            pnlBookings.repaint();
            return;
        }

        List<Booking> bookings;
        if (statusFilter == null || "All".equalsIgnoreCase(statusFilter)) {
            bookings = bookingDAO.getBookingsByCustomer(customerId);
        } else {
            bookings = bookingDAO.getBookingsByCustomerAndStatus(customerId, statusFilter.toLowerCase());
        }

        if (bookings.isEmpty()) {
            lblEmpty.setText("No " + statusFilter.toLowerCase() + " bookings found.");
            lblEmpty.setVisible(true);
            pnlBookings.add(lblEmpty);
        } else {
            lblEmpty.setVisible(false);
            for (Booking b : bookings) {
                BookingCardPanel card = new BookingCardPanel();
                card.setMaximumSize(
                new Dimension(1300,160));
                card.setData(b);

                // Cancel button only for pending
                if ("pending".equalsIgnoreCase(b.getStatus())) {
                    card.setCancelListener(e -> {
                        int confirm = JOptionPane.showConfirmDialog(this,
                            "Cancel booking for " + b.getHouseName() + "?", 
                            "Confirm", JOptionPane.YES_NO_OPTION);
                        if (confirm == JOptionPane.YES_OPTION) {
                            bookingDAO.cancelBooking(b.getId());
                            loadBookings(statusFilter); // refresh
                        }
                    });
                }

                pnlBookings.add(card);
                pnlBookings.add(Box.createRigidArea(new Dimension(0, 10)));
            }
        }
    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error loading bookings: " + ex.getMessage());
        lblEmpty.setVisible(true);
        pnlBookings.add(lblEmpty);
    }

    pnlBookings.revalidate();
    pnlBookings.repaint();
}
private void resetTabs() {
        java.awt.Color bg = new java.awt.Color(243, 243, 247);
        java.awt.Color fg = new java.awt.Color(80, 80, 120);
        for (javax.swing.JButton b : new javax.swing.JButton[]{btnAll, btnPending, btnConfirmed, btnCancelled}) {
            b.setBackground(bg);
            b.setForeground(fg);
        }
    }

    private void activateTab(javax.swing.JButton b) {
        b.setBackground(new java.awt.Color(0, 102, 255));
        b.setForeground(java.awt.Color.WHITE);
    }
    // Keep your other existing button methods (Search, Dashboard, etc.)

    /**
     * @param args the command line arguments
     */
 
    private void GharSathi_TextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GharSathi_TextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_GharSathi_TextActionPerformed

    private void SearchBar_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchBar_ButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SearchBar_ButtonActionPerformed

    private void SearchHouse_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchHouse_ButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SearchHouse_ButtonActionPerformed

    private void Bookings_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bookings_ButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Bookings_ButtonActionPerformed

    private void SavedHouse_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SavedHouse_ButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SavedHouse_ButtonActionPerformed

    private void Profile_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Profile_ButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Profile_ButtonActionPerformed

    private void Dashboard_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Dashboard_ButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Dashboard_ButtonActionPerformed

    private void Menuelogo_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Menuelogo_ButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Menuelogo_ButtonActionPerformed

    private void Username_TextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Username_TextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Username_TextActionPerformed

    private void Iconlogo_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Iconlogo_ButtonActionPerformed
  

    }//GEN-LAST:event_Iconlogo_ButtonActionPerformed

    private void btnPendingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPendingActionPerformed
       resetTabs();
        activateTab(btnPending);
        loadBookings("Pending");  // TODO add your handling code here:
    }//GEN-LAST:event_btnPendingActionPerformed

    private void btnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAllActionPerformed
       resetTabs();
        activateTab(btnAll);
        loadBookings("All");  // TODO add your handling code here:
    }//GEN-LAST:event_btnAllActionPerformed

    private void btnConfirmedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmedActionPerformed
      resetTabs();
        activateTab(btnConfirmed);
        loadBookings("Confirmed");   // TODO add your handling code here:
    }//GEN-LAST:event_btnConfirmedActionPerformed

    private void btnCancelledActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelledActionPerformed
              resetTabs();
        activateTab(btnCancelled);
        loadBookings("Cancelled");  // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelledActionPerformed

    /**
     * @param args the command line arguments
     */
 public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new My_Bookings().setVisible(true));
    }

 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Bookings_Button;
    private javax.swing.JButton Dashboard_Button;
    private javax.swing.JTextField GharSathi_Text;
    private javax.swing.JButton Iconlogo_Button;
    private javax.swing.JButton Menuelogo_Button;
    private javax.swing.JButton Profile_Button;
    private javax.swing.JButton SavedHouse_Button;
    private javax.swing.JButton SearchBar_Button;
    private javax.swing.JButton SearchHouse_Button;
    private javax.swing.JTextField Username_Text;
    private javax.swing.JButton btnAll;
    private javax.swing.JButton btnCancelled;
    private javax.swing.JButton btnConfirmed;
    private javax.swing.JButton btnPending;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblEmpty;
    private javax.swing.JPanel pnlBookings;
    // End of variables declaration//GEN-END:variables

}