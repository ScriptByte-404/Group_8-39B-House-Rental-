package Database;

import database.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class MysqlConnector implements db {
    @Override
    public Connection openConnection(){
    try {
        String username = "root";
        String password= "Rojan@123";
        String database = "house_rental_system";
    Connection connection;
    connection = DriverManager .getConnection(
           "jdbc:mysql://localhost:3306/" + database ,username, password 
          );
             if(connection == null){

                System.out.println("Database connection fail");

            }else{

                System.out.println("Database connection success");

            }

            return connection;

           } catch (SQLException e) {
            System.out.println("Connection error: " + e.getMessage());
          

        }  return null;

    }

    @Override
    public void closeConnection(Connection conn) {

        try{

            if(conn != null && !conn.isClosed() ){

                conn.close();

                System.out.println("Connection close");

            }

            

        }catch(SQLException e){

            System.out.println(e);

            

        }

    }



    @Override
    public ResultSet runQuery(Connection conn, String query) {

       try{

           Statement stmp = conn.createStatement();

           ResultSet result = stmp.executeQuery(query);

           return result;

       

       }catch (SQLException e){

           System.out.println(e);

           return null;

       }

    }

   

    @Override
    public int excecuteUpdate(Connection conn, String query) {

      try{

          Statement stmp = conn.createStatement();

          int result = stmp.executeUpdate(query);

          return result;

          

      }catch(SQLException e){

          System.out.println(e);

          return -1;

      }

    }

}