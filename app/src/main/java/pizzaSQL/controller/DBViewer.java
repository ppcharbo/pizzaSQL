package pizzaSQL.controller;

import pizzaSQL.model.Rider;

import java.sql.*;

public class DBViewer {

    private final String URL;
    private final String USER;
    private final String PASSWD;

    private Connection con;

    public DBViewer(String URL, String USER, String PASSWD){
        this.URL=URL; this.USER=USER; this.PASSWD=PASSWD;
        connect();

    }

    private void connect(){
        try {
            this.con = DriverManager.getConnection(URL,USER,PASSWD);
        } catch (SQLException e) {

            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    public boolean login(String email, String passwd){
        ResultSet rs = null;
        try(Statement stmt = con.createStatement()){
            rs = stmt.executeQuery("SELECT * FROM  customers WHERE email="+email+" AND passwd="+passwd);
        }
        catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        return rs != null;
    }

    public Rider getRider() {
        ResultSet rs = null;
        try (Statement stmt = con.createStatement()) {
            rs = stmt.executeQuery("SELECT id, name FROM  riders WHERE available=1");
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        try{
            String id = rs.getString(0);
            String name = rs.getString(1);
        }catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        return new Rider(this, id, name, false)
    }

    public void deliever(String riderId){
        ResultSet rs = null;
        try (Statement stmt = con.createStatement()) {
            rs = stmt.executeQuery("UPDATE riders SET available=0 WHERE id="+riderId);
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }


}
