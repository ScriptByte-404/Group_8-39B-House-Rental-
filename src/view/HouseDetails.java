package view;

import dao.BookingDAO;
import javax.swing.*;
import java.awt.Image;
import java.util.List;
import model.House;
import util.SessionManager;

public class HouseDetails extends javax.swing.JFrame {

    private House house;

    public HouseDetails(House house) {
        initComponents();
        this.house = house;
        populateDetails();
    }

    private void populateDetails() {
        lblHouseName.setText(house.getName());
        lblLocation.setText(house.getLocation());
        lblPrice.setText(house.getPrice());
        lblBedrooms.setText("Bedrooms: " + house.getBedrooms());
        lblBathrooms.setText("Bathrooms: " + house.getBathrooms());
        lblKitchen.setText("Kitchen: " + house.getKitchens());
        lblArea.setText(house.getType());
        txtDescription.setText(house.getDescription());

        List<String> amenities = house.getAmenities();
        if (amenities != null) {
            wifiCheck.setSelected(amenities.contains("WI-FI"));
            parkingCheck.setSelected(amenities.contains("Parking"));
            balconyCheck.setSelected(amenities.contains("Balcony"));
            utilitiesCheck.setSelected(amenities.contains("Utilities"));
            securityCheck.setSelected(amenities.contains("24/7 security"));
            acCheck.setSelected(amenities.contains("Air Condition"));
        }

        List<String> images = house.getImages();
        if (images != null && !images.isEmpty()) {
            setImage(lblMainImage, images.get(0), 430, 340);
            JLabel[] thumbs = {lblImage1, lblImage2, lblImage3};
            for (int i = 0; i < thumbs.length; i++) {
                if (i + 1 < images.size()) {
                    setImage(thumbs[i], images.get(i + 1), 120, 140);
                }
            }
        }
    }

    private void setImage(JLabel label, String path, int w, int h) {
        try {
            ImageIcon icon = new ImageIcon(path);
            Image scaled = icon.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(scaled));
            label.setText("");
        } catch (Exception e) {
            label.setText("Image not found");
        }
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
        jPanel1 = new javax.swing.JPanel();
        btnBookNow = new javax.swing.JButton();
        lblMainImage = new javax.swing.JLabel();
        lblImage1 = new javax.swing.JLabel();
        txtDescription = new javax.swing.JTextField();
        Info_text2 = new javax.swing.JTextField();
        lblHouseName = new javax.swing.JLabel();
        lblLocation = new javax.swing.JLabel();
        lblPrice = new javax.swing.JLabel();
        SaveProperty = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        lblBedrooms = new javax.swing.JLabel();
        lblBathrooms = new javax.swing.JLabel();
        lblKitchen = new javax.swing.JLabel();
        lblArea = new javax.swing.JLabel();
        lblImage2 = new javax.swing.JLabel();
        lblImage3 = new javax.swing.JLabel();
        wifiCheck = new javax.swing.JCheckBox();
        parkingCheck = new javax.swing.JCheckBox();
        balconyCheck = new javax.swing.JCheckBox();
        utilitiesCheck = new javax.swing.JCheckBox();
        securityCheck = new javax.swing.JCheckBox();
        Info_text3 = new javax.swing.JTextField();
        acCheck = new javax.swing.JCheckBox();

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

        SearchHouse_Button.setBackground(new java.awt.Color(15, 75, 155));
        SearchHouse_Button.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        SearchHouse_Button.setForeground(new java.awt.Color(255, 255, 255));
        SearchHouse_Button.setText("Search Houses");
        SearchHouse_Button.addActionListener(this::SearchHouse_ButtonActionPerformed);
        getContentPane().add(SearchHouse_Button, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 110, 260, 50));

        Bookings_Button.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        Bookings_Button.setText("Bookings");
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

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(null);

        btnBookNow.setBackground(new java.awt.Color(15, 75, 155));
        btnBookNow.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnBookNow.setForeground(new java.awt.Color(255, 255, 255));
        btnBookNow.setText("Book Now");
        btnBookNow.addActionListener(this::btnBookNowActionPerformed);
        jPanel1.add(btnBookNow);
        btnBookNow.setBounds(1020, 500, 320, 60);

        lblMainImage.setText("jLabel1");
        lblMainImage.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(lblMainImage);
        lblMainImage.setBounds(70, 50, 430, 340);

        lblImage1.setText("jLabel1");
        lblImage1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(lblImage1);
        lblImage1.setBounds(230, 410, 120, 140);

        txtDescription.setText("jTextField1");
        txtDescription.addActionListener(this::txtDescriptionActionPerformed);
        jPanel1.add(txtDescription);
        txtDescription.setBounds(570, 280, 670, 100);

        Info_text2.setBackground(new java.awt.Color(242, 242, 242));
        Info_text2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Info_text2.setText("Amenities");
        Info_text2.setBorder(null);
        Info_text2.addActionListener(this::Info_text2ActionPerformed);
        jPanel1.add(Info_text2);
        Info_text2.setBounds(570, 400, 303, 20);

        lblHouseName.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblHouseName.setText(" Modern Apartment");
        jPanel1.add(lblHouseName);
        lblHouseName.setBounds(560, 50, 260, 30);

        lblLocation.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblLocation.setText("Kathmandu,Pipalbot Marga");
        jPanel1.add(lblLocation);
        lblLocation.setBounds(570, 80, 230, 20);

        lblPrice.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblPrice.setForeground(new java.awt.Color(53, 100, 166));
        lblPrice.setText("Rs 55,000/month");
        jPanel1.add(lblPrice);
        lblPrice.setBounds(570, 100, 180, 25);

        SaveProperty.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        SaveProperty.setText("Save Property");
        SaveProperty.addActionListener(this::SavePropertyActionPerformed);
        jPanel1.add(SaveProperty);
        SaveProperty.setBounds(580, 500, 320, 60);

        jButton1.setBackground(new java.awt.Color(242, 242, 242));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton1.setText("<  Back to search");
        jButton1.setBorder(null);
        jButton1.addActionListener(this::jButton1ActionPerformed);
        jPanel1.add(jButton1);
        jButton1.setBounds(10, 10, 160, 30);

        lblBedrooms.setText("jLabel8");
        lblBedrooms.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(lblBedrooms);
        lblBedrooms.setBounds(570, 140, 180, 40);

        lblBathrooms.setText("jLabel8");
        lblBathrooms.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(lblBathrooms);
        lblBathrooms.setBounds(770, 140, 180, 40);

        lblKitchen.setText("jLabel8");
        lblKitchen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(lblKitchen);
        lblKitchen.setBounds(570, 200, 180, 40);

        lblArea.setText("jLabel8");
        lblArea.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(lblArea);
        lblArea.setBounds(770, 200, 180, 40);

        lblImage2.setText("jLabel1");
        lblImage2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(lblImage2);
        lblImage2.setBounds(380, 410, 120, 140);

        lblImage3.setText("jLabel1");
        lblImage3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(lblImage3);
        lblImage3.setBounds(70, 410, 120, 140);

        wifiCheck.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        wifiCheck.setText("  WI-FI");
        wifiCheck.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        wifiCheck.addActionListener(this::wifiCheckActionPerformed);
        jPanel1.add(wifiCheck);
        wifiCheck.setBounds(570, 430, 110, 40);

        parkingCheck.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        parkingCheck.setText("  Parking");
        parkingCheck.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(parkingCheck);
        parkingCheck.setBounds(690, 430, 110, 40);

        balconyCheck.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        balconyCheck.setText("Balcony");
        balconyCheck.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(balconyCheck);
        balconyCheck.setBounds(980, 430, 120, 40);

        utilitiesCheck.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        utilitiesCheck.setText("Utilities");
        utilitiesCheck.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        utilitiesCheck.addActionListener(this::utilitiesCheckActionPerformed);
        jPanel1.add(utilitiesCheck);
        utilitiesCheck.setBounds(1260, 430, 120, 40);

        securityCheck.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        securityCheck.setText("24/7 security");
        securityCheck.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(securityCheck);
        securityCheck.setBounds(820, 430, 150, 40);

        Info_text3.setBackground(new java.awt.Color(242, 242, 242));
        Info_text3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Info_text3.setText("Description");
        Info_text3.setBorder(null);
        Info_text3.addActionListener(this::Info_text3ActionPerformed);
        jPanel1.add(Info_text3);
        Info_text3.setBounds(570, 260, 303, 20);

        acCheck.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        acCheck.setText("Air Condition");
        acCheck.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        acCheck.addActionListener(this::acCheckActionPerformed);
        jPanel1.add(acCheck);
        acCheck.setBounds(1100, 430, 150, 40);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, 1400, 580));

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void btnBookNowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBookNowActionPerformed
  try {
        int customerId = 4;

        // Safe access to SessionManager
        if (SessionManager.getCurrentUser() != null) {
            model.User user = SessionManager.getCurrentUser();
            // Try common ID getter names
            try {
                customerId = user.getUserId();                    // most common
            } catch (Exception e1) {
                try {
                    customerId = user.getUserId();            // alternative
                } catch (Exception e2) {
                    // If you know the exact method name, put it here
                    System.out.println("User ID method not found");
                }
            }
        }

        if (customerId <= 0) {
            JOptionPane.showMessageDialog(this, "Please login first!", "Login Required", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (house == null || house.getId() <= 0) {
            JOptionPane.showMessageDialog(this, "House information is missing!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        BookingDAO bookingDAO = new BookingDAO();
        boolean success = bookingDAO.insertBooking(house.getId(), customerId);

        if (success) {
            int choice = JOptionPane.showConfirmDialog(this,
                "✅ Booking submitted successfully!\n\nStatus: Pending (Admin approval required)\n\nView My Bookings?",
                "Booking Successful", JOptionPane.YES_NO_OPTION);

            if (choice == JOptionPane.YES_OPTION) {
                new My_Bookings().setVisible(true);
                this.dispose();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Failed to create booking.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Booking Failed", JOptionPane.ERROR_MESSAGE);
    }// TODO add your handling code here:
    }//GEN-LAST:event_btnBookNowActionPerformed

    private void Info_text2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Info_text2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Info_text2ActionPerformed

    private void SavePropertyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SavePropertyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SavePropertyActionPerformed

    private void txtDescriptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescriptionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescriptionActionPerformed

    private void Info_text3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Info_text3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Info_text3ActionPerformed

    private void utilitiesCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_utilitiesCheckActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_utilitiesCheckActionPerformed

    private void acCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acCheckActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_acCheckActionPerformed

    private void wifiCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wifiCheckActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_wifiCheckActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
Searches_Customer backPage = new Searches_Customer(); 
    backPage.setVisible(true);
    this.dispose();   
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
public static void main(String args[]) {

    java.awt.EventQueue.invokeLater(() -> {
        House dummy = new House();
        dummy.setName("Test House");
        dummy.setLocation("Kathmandu, Test Location");
        dummy.setPrice("Rs 30,000/month");
        dummy.setType("Modern House");
        dummy.setBedrooms("3BHK");
        dummy.setBathrooms("2");
        dummy.setKitchens("1");
        dummy.setDescription("This is a test description.");
        dummy.setAmenities(java.util.Arrays.asList("WI-FI", "Parking"));
        dummy.setImages(new java.util.ArrayList<>(java.util.Arrays.asList(
                "C:/Users/User/Desktop/download (3).jpg"
        )));

        new HouseDetails(dummy).setVisible(true);
    });

 }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Bookings_Button;
    private javax.swing.JButton Dashboard_Button;
    private javax.swing.JTextField GharSathi_Text;
    private javax.swing.JButton Iconlogo_Button;
    private javax.swing.JTextField Info_text2;
    private javax.swing.JTextField Info_text3;
    private javax.swing.JButton Menuelogo_Button;
    private javax.swing.JButton Profile_Button;
    private javax.swing.JButton SaveProperty;
    private javax.swing.JButton SavedHouse_Button;
    private javax.swing.JButton SearchBar_Button;
    private javax.swing.JButton SearchHouse_Button;
    private javax.swing.JTextField Username_Text;
    private javax.swing.JCheckBox acCheck;
    private javax.swing.JCheckBox balconyCheck;
    private javax.swing.JButton btnBookNow;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblArea;
    private javax.swing.JLabel lblBathrooms;
    private javax.swing.JLabel lblBedrooms;
    private javax.swing.JLabel lblHouseName;
    private javax.swing.JLabel lblImage1;
    private javax.swing.JLabel lblImage2;
    private javax.swing.JLabel lblImage3;
    private javax.swing.JLabel lblKitchen;
    private javax.swing.JLabel lblLocation;
    private javax.swing.JLabel lblMainImage;
    private javax.swing.JLabel lblPrice;
    private javax.swing.JCheckBox parkingCheck;
    private javax.swing.JCheckBox securityCheck;
    private javax.swing.JTextField txtDescription;
    private javax.swing.JCheckBox utilitiesCheck;
    private javax.swing.JCheckBox wifiCheck;
    // End of variables declaration//GEN-END:variables
}