package com.rental;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class SignupForm extends JFrame {

    private JTextField   txtFirst, txtLast, txtEmail, txtPhone;
    private JPasswordField txtPassword, txtConfirm;
    private JComboBox<String> cmbRole;
    private JButton btnSignup;
    private JLabel lblMsg;

    public SignupForm() {
        setTitle("NestFinder — Sign Up");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 480);
        setLocationRelativeTo(null);
        setResizable(false);
        buildUI();
    }

    private void buildUI() {
        JPanel main = new JPanel(new BorderLayout(10, 10));
        main.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        main.setBackground(Color.WHITE);

        // Title
        JLabel title = new JLabel("🏠 Create your account", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 18));
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        main.add(title, BorderLayout.NORTH);

        // Form fields
        JPanel form = new JPanel(new GridLayout(0, 1, 5, 5));
        form.setBackground(Color.WHITE);

        txtFirst    = addField(form, "First name *");
        txtLast     = addField(form, "Last name *");
        txtEmail    = addField(form, "Email address *");
        txtPhone    = addField(form, "Phone number");

        form.add(new JLabel("Role *"));
        cmbRole = new JComboBox<>(new String[]{"tenant", "landlord"});
        form.add(cmbRole);

        form.add(new JLabel("Password *"));
        txtPassword = new JPasswordField();
        form.add(txtPassword);

        form.add(new JLabel("Confirm password *"));
        txtConfirm = new JPasswordField();
        form.add(txtConfirm);

        main.add(form, BorderLayout.CENTER);

        // Bottom: button + message
        JPanel bottom = new JPanel(new BorderLayout(5, 5));
        bottom.setBackground(Color.WHITE);

        btnSignup = new JButton("Create Account");
        btnSignup.setBackground(new Color(37, 99, 235));
        btnSignup.setForeground(Color.WHITE);
        btnSignup.setFocusPainted(false);
        btnSignup.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnSignup.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnSignup.addActionListener(e -> handleSignup());

        lblMsg = new JLabel("", SwingConstants.CENTER);
        lblMsg.setFont(new Font("SansSerif", Font.PLAIN, 12));

        bottom.add(btnSignup, BorderLayout.CENTER);
        bottom.add(lblMsg, BorderLayout.SOUTH);
        main.add(bottom, BorderLayout.SOUTH);

        add(main);
    }

    private JTextField addField(JPanel panel, String label) {
        panel.add(new JLabel(label));
        JTextField field = new JTextField();
        panel.add(field);
        return field;
    }

    private void handleSignup() {
        String first    = txtFirst.getText().trim();
        String last     = txtLast.getText().trim();
        String email    = txtEmail.getText().trim();
        String phone    = txtPhone.getText().trim();
        String role     = (String) cmbRole.getSelectedItem();
        String password = new String(txtPassword.getPassword());
        String confirm  = new String(txtConfirm.getPassword());

        // Validation
        if (first.isEmpty() || last.isEmpty() || email.isEmpty() || password.isEmpty()) {
            showMsg("All required fields must be filled.", Color.RED);
            return;
        }
        if (!email.matches("^[\\w.+-]+@[\\w-]+\\.[a-zA-Z]{2,}$")) {
            showMsg("Invalid email address.", Color.RED);
            return;
        }
        if (password.length() < 8) {
            showMsg("Password must be at least 8 characters.", Color.RED);
            return;
        }
        if (!password.equals(confirm)) {
            showMsg("Passwords do not match.", Color.RED);
            return;
        }

        // Save to DB
        try {
            User user = new User(first, last, email, phone, password, role);
            UserDao dao = new UserDao();
            boolean success = dao.registerUser(user);
            if (success) {
                showMsg("Account created successfully!", new Color(22, 163, 74));
                clearFields();
            } else {
                showMsg("Email already registered.", Color.RED);
            }
        } catch (SQLException ex) {
            showMsg("DB Error: " + ex.getMessage(), Color.RED);
            ex.printStackTrace();
        }
    }

    private void showMsg(String msg, Color color) {
        lblMsg.setText(msg);
        lblMsg.setForeground(color);
    }

    private void clearFields() {
        txtFirst.setText("");
        txtLast.setText("");
        txtEmail.setText("");
        txtPhone.setText("");
        txtPassword.setText("");
        txtConfirm.setText("");
        cmbRole.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SignupForm().setVisible(true));
    }
}
