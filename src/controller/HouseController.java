package controller;

import dao.HouseDAO;
import model.House;
import java.util.List;

public class HouseController {

    private HouseDAO houseDAO = new HouseDAO();

    public void addHouse(House house) {
        houseDAO.insertHouse(house);
    }

    public List<House> getAllHouses() {
        return houseDAO.getAllHouses();
    }

    public List<House> searchHouses(String keyword) {
        return houseDAO.searchHouses(keyword);
    }
}