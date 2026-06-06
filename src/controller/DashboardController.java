/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import dao.DashboardDao;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.DashboardData;

public class DashboardController {

    private final DashboardDao dao;

    public DashboardController() {
        dao = new DashboardDao();
    }

    // Method to load data into JTable
    public void loadUserData(JTable table) {

        ArrayList<DashboardData> list = dao.getUsers();

        // Table model from JTable
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        // Clear old rows
        model.setRowCount(0);

        // Add rows from database
        for (DashboardData data : list) {

            Object[] row = new Object[4];

            row[0] = data.getUsername();
            row[1] = data.getEmail();
            row[2] = data.getRole();
            row[3] = data.getStatus();

            model.addRow(row);
        }
    }
}
