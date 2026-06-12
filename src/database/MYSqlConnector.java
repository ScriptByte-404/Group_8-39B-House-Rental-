package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
public class MYSqlConnector implements db{
    @Override
    public Connection openConnection(){
        try{
            String username="root";
            String password="Someshmalla@90";
            String database="ghar_sathi";
            Connection connection;
            connection = DriverManager.getConnection(
            "jdbc:mysql://localhost:"+database,username,password
            );
            if (connection==null){
                System.out.println("Connection Null");
            }else{
                System.out.println("Connection Success");
                
            }
            return connection;
            
        }catch (SQLException e){
            System.out.println(e);
            return null;
        }
    }
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



    
    public ResultSet runQuery(Connection conn, String query) {

        try {
            Statement stmt = conn.createStatement();

            ResultSet result = stmt.executeQuery(query);

            return result;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    // INSERT, UPDATE, DELETE queries
    public int executeUpdate(Connection conn, String query) {

        try (Statement stmt = conn.createStatement()) {

            int result = stmt.executeUpdate(query);

            return result;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }
}

