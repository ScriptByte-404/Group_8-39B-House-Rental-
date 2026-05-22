package gharsathi;

import Database.MysqlConnector;
import java.sql.Connection;

public class GharSathi {

    public static void main(String[] args) {

        MysqlConnector db = new MysqlConnector();

        Connection conn = db.openConnection();

        if (conn != null) {

            System.out.println("Program running successfully");

        } else {

            System.out.println("Database connection failed");

        }

    }
}

