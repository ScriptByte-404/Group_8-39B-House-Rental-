package controller;

import dao.UserDAO;

public class PasswordResetController {

    UserDAO dao = new UserDAO();

    // Validate password rules
    public String validatePassword(String newPass, String confirmPass) {

        if (newPass.isEmpty() || confirmPass.isEmpty()) {
            return "Please fill all fields!";
        }

        if (newPass.length() < 6) {
            return "Password must be at least 6 characters!";
        }

        if (!newPass.matches(".*[0-9].*")) {
            return "Password must contain at least 1 number!";
        }

        if (!newPass.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*")) {
            return "Password must contain at least 1 special character!";
        }

        if (!newPass.equals(confirmPass)) {
            return "Passwords do not match!";
        }

        return null; // null means NO error, all good
    }

    // Update password in DB
    public boolean updatePassword(String email, String newPass) {
        return dao.updatePassword(email, newPass);
    }
}