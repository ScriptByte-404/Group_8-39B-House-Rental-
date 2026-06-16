package controller;
import dao.UserDAO;
import model.User;
import util.SessionManager;

public class LoginController {
    UserDAO dao = new UserDAO();

    public boolean loginUser(String usernameOrEmail, String password) {
        User user = dao.checkUser(usernameOrEmail, password);
        if (user != null) {
            SessionManager.setCurrentUser(user);
            return true;
        }
        return false;
    }

    // NEW METHOD — creates HouseController outside the view
    public void openDashBoard() {
        HouseController houseController = new HouseController();
        new view.DashBoard(houseController).setVisible(true);
    }
}