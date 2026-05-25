package controller;

import dao.UserDAO;
import model.logindata;

public class UserController {

    UserDAO dao = new UserDAO();

    // register
    public boolean registerUser(logindata user) {
        return dao.createUser(user);
    }

    // login
    public boolean loginUser(String usernameOrEmail, String password) {
        return dao.checkUser(usernameOrEmail, password);
    }
}