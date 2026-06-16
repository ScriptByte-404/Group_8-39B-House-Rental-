package controller;

import dao.UserDAO;
import model.logindata;
import model.User;
import util.SessionManager;

public class UserController {

    UserDAO dao = new UserDAO();

    // VALIDATE registration fields (returns null if all good, else error message)
    public String validateRegistration(String username, String email, String password) {

        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            return "Please fill all fields!";
        }

        // Username: letters, numbers, underscore only, must START with a letter
        // (prevents pure-number "names" like "12345")
        if (!username.matches("^[a-zA-Z][a-zA-Z0-9_]{2,19}$")) {
            return "Username must start with a letter and be 3-20 characters (letters, numbers, _ only)!";
        }

        // Email: must be a valid Gmail address
        if (!email.matches("^[a-zA-Z0-9._%+-]+@gmail\\.com$")) {
            return "Please enter a valid Gmail address (must end with @gmail.com)!";
        }

        // Password rules
        if (password.length() < 6) {
            return "Password must be at least 6 characters!";
        }
        if (!password.matches(".*[A-Za-z].*")) {
            return "Password must contain at least 1 letter!";
        }
        if (!password.matches(".*[0-9].*")) {
            return "Password must contain at least 1 number!";
        }
        if (!password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*")) {
            return "Password must contain at least 1 special character!";
        }

        return null; // no error
    }

    // CHECK if email already registered
    public boolean emailExists(String email) {
        return dao.emailExists(email);
    }

    // register
    public boolean registerUser(logindata user) {
        return dao.createUser(user);
    }

    // login
    public boolean loginUser(String usernameOrEmail, String password) {
        User user = dao.checkUser(usernameOrEmail, password);
        if (user != null) {
            SessionManager.setCurrentUser(user);
            return true;
        }
        return false;
    }
}