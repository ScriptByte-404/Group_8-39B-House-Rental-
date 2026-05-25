package controller;

import dao.UserDAO;

public class LoginController {

    UserDAO dao = new UserDAO();

    public boolean loginUser(String usernameOrEmail, String password) {
        return dao.checkUser(usernameOrEmail, password);
    }
}