package view;   // remove if not inside view folder
import dao.BookingDAO;
import model.Booking;
import util.SessionManager;

import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;


public class OwnerBookingsApproval extends javax.swing.JFrame {
private final BookingDAO bookingDAO = new BookingDAO();
public OwnerBookingsApproval() {
    initComponents();
    setupBookingPanel();
    
    loadBookingsFromDB();
}
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel36 = new javax.swing.JLabel();
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
        Booking_Requests = new javax.swing.JPanel();
        JScrollPane = new javax.swing.JScrollPane();
        bookingPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(1536, 864));
        getContentPane().setLayout(null);

        jLabel36.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel36.setText("House Rental ");
        jLabel36.setPreferredSize(new java.awt.Dimension(635, 244));
        getContentPane().add(jLabel36);
        jLabel36.setBounds(110, 10, 250, 40);

        GharSathi_Text.setBackground(new java.awt.Color(243, 243, 243));
        GharSathi_Text.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        GharSathi_Text.setText("System");
        GharSathi_Text.setBorder(null);
        GharSathi_Text.addActionListener(this::GharSathi_TextActionPerformed);
        getContentPane().add(GharSathi_Text);
        GharSathi_Text.setBounds(110, 20, 220, 50);

        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Untitled_-_Custom-removebg-preview.png"))); // NOI18N
        jLabel31.setText("jLabel31");
        getContentPane().add(jLabel31);
        jLabel31.setBounds(-10, 0, 110, 90);

        SearchHouse_Button.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        SearchHouse_Button.setText("Add Houses");
        SearchHouse_Button.addActionListener(this::SearchHouse_ButtonActionPerformed);
        getContentPane().add(SearchHouse_Button);
        SearchHouse_Button.setBounds(450, 90, 260, 40);

        Bookings_Button.setBackground(new java.awt.Color(43, 109, 80));
        Bookings_Button.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        Bookings_Button.setForeground(new java.awt.Color(255, 255, 255));
        Bookings_Button.setText("Bookings");
        Bookings_Button.addActionListener(this::Bookings_ButtonActionPerformed);
        getContentPane().add(Bookings_Button);
        Bookings_Button.setBounds(710, 90, 260, 40);

        SavedHouse_Button.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        SavedHouse_Button.setText("Saved Houses");
        SavedHouse_Button.addActionListener(this::SavedHouse_ButtonActionPerformed);
        getContentPane().add(SavedHouse_Button);
        SavedHouse_Button.setBounds(970, 90, 260, 40);

        Profile_Button.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        Profile_Button.setText("Profile");
        Profile_Button.addActionListener(this::Profile_ButtonActionPerformed);
        getContentPane().add(Profile_Button);
        Profile_Button.setBounds(1230, 90, 260, 40);

        Dashboard_Button.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        Dashboard_Button.setText("Dashboard");
        Dashboard_Button.addActionListener(this::Dashboard_ButtonActionPerformed);
        getContentPane().add(Dashboard_Button);
        Dashboard_Button.setBounds(120, 90, 250, 40);

        Menuelogo_Button.setBackground(new java.awt.Color(243, 243, 243));
        Menuelogo_Button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Menu.png"))); // NOI18N
        Menuelogo_Button.setBorder(null);
        Menuelogo_Button.addActionListener(this::Menuelogo_ButtonActionPerformed);
        getContentPane().add(Menuelogo_Button);
        Menuelogo_Button.setBounds(1490, 0, 50, 30);

        Username_Text.setBackground(new java.awt.Color(243, 243, 243));
        Username_Text.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        Username_Text.setText("Hello User");
        Username_Text.setBorder(null);
        Username_Text.setSelectedTextColor(new java.awt.Color(242, 242, 242));
        Username_Text.addActionListener(this::Username_TextActionPerformed);
        getContentPane().add(Username_Text);
        Username_Text.setBounds(1360, 30, 111, 20);

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/account_circle.png"))); // NOI18N
        getContentPane().add(jLabel17);
        jLabel17.setBounds(1310, 10, 40, 60);

        Iconlogo_Button.setBackground(new java.awt.Color(242, 242, 242));
        Iconlogo_Button.setForeground(new java.awt.Color(242, 242, 242));
        Iconlogo_Button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Bell_Icon.png"))); // NOI18N
        Iconlogo_Button.setBorder(null);
        Iconlogo_Button.addActionListener(this::Iconlogo_ButtonActionPerformed);
        getContentPane().add(Iconlogo_Button);
        Iconlogo_Button.setBounds(1220, 0, 80, 80);

        Booking_Requests.setLayout(new java.awt.BorderLayout());

        JScrollPane.setPreferredSize(new java.awt.Dimension(1420, 650));

        bookingPanel.setLayout(new javax.swing.BoxLayout(bookingPanel, javax.swing.BoxLayout.Y_AXIS));
        JScrollPane.setViewportView(bookingPanel);

        Booking_Requests.add(JScrollPane, java.awt.BorderLayout.CENTER);

        getContentPane().add(Booking_Requests);
        Booking_Requests.setBounds(50, 160, 1420, 650);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void GharSathi_TextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GharSathi_TextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_GharSathi_TextActionPerformed

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

    /**
     * @param args the command line arguments
     */
 public static void main(String args[]) {
    java.awt.EventQueue.invokeLater(() -> {
        // temp test user — remove when using real login
        model.User testUser = new model.User();
        testUser.setUserId(1); // use a real owner_id from your DB
        SessionManager.setCurrentUser(testUser);

        OwnerBookingsApproval frame = new OwnerBookingsApproval();
        frame.setVisible(true);
    });
}
private void loadBookingsFromDB() {
    bookingPanel.removeAll();

    int ownerId = SessionManager.getCurrentUser().getUserId();

    List<Booking> list = bookingDAO.getBookingsByOwnerId(ownerId);

    for (Booking b : list) {
        int bookingId = b.getId();

        OwnerBookingCardPanel card = new OwnerBookingCardPanel();
        card.setData(b);

        card.setAcceptListener(e -> {
            bookingDAO.updateBookingStatus(bookingId, "confirmed");
            loadBookingsFromDB();
        });

        card.setRejectListener(e -> {
            bookingDAO.updateBookingStatus(bookingId, "cancelled");
            loadBookingsFromDB();
        });

        bookingPanel.add(card);
    }

    bookingPanel.revalidate();
    bookingPanel.repaint();
}
private void setupBookingPanel() {
    bookingPanel.setLayout(new BoxLayout(bookingPanel, BoxLayout.Y_AXIS));
}  
private void loadTestCard() {

    bookingPanel.removeAll(); // important reset

    OwnerBookingCardPanel card = new OwnerBookingCardPanel();

    // dummy data (for testing UI)
    Booking b = new Booking();
    b.setBookingId(1);
    b.setCustomerName("John Doe");
    b.setHouseName("Green Villa");
    b.setCustomerId(101);
    b.setBookingDate("2026-06-18");
    b.setStatus("pending");

    card.setData(b);

    // refresh callback (IMPORTANT)
card.setBookingActionListener(new BookingActionListener() {
    @Override
    public void onBookingUpdated() {
        loadBookingsFromDB();
    }
});
    bookingPanel.add(card);

    bookingPanel.revalidate();
    bookingPanel.repaint();
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Booking_Requests;
    private javax.swing.JButton Bookings_Button;
    private javax.swing.JButton Dashboard_Button;
    private javax.swing.JTextField GharSathi_Text;
    private javax.swing.JButton Iconlogo_Button;
    private javax.swing.JScrollPane JScrollPane;
    private javax.swing.JButton Menuelogo_Button;
    private javax.swing.JButton Profile_Button;
    private javax.swing.JButton SavedHouse_Button;
    private javax.swing.JButton SearchHouse_Button;
    private javax.swing.JTextField Username_Text;
    private javax.swing.JPanel bookingPanel;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel36;
    // End of variables declaration//GEN-END:variables

}
