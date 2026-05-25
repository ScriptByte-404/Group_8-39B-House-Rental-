package controller;

import dao.UserDAO;
import model.logindata;

public class UserController {

    UserDAO dao = new UserDAO();

    public boolean registerUser(logindata user) {
        return dao.createUser(user);
    }
}