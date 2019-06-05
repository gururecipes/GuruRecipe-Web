/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.hibernate.criterion.Expression.sql;
/**
 *
 * @author yukselkaradeniz
 */
public class DBLayer {
    private Connection conn;
    String dbUrl = "jdbc:derby://localhost:1527/Guru";
    String name = "guru";String pass = "123";
    public Connection connect(){
        System.out.println("yuko");
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            conn = DriverManager.getConnection(dbUrl, name, pass);
            System.out.println("connected");
        }
        catch(Exception e){
            System.out.println("connection problem");
        }
        return conn;
    }
    
    public void PrintList(){
        if(conn == null){
            System.out.println("Database is NOT connected");
            connect();
        }
        
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from MAIN");
            ResultSetMetaData rsmd = rs.getMetaData();
            System.out.println("User Name : \nPassword : ");
            while(rs.next()){
//                System.out.println(""+rs.getString(0)+" \n "+rs.getString(1) );
            }
            stmt.close();
        } catch (Exception e) {
            Logger.getLogger(DBLayer.class.getName()).log(Level.SEVERE, null, e);
        }
        
    }
    
    public void addUser(String name, String password ){
        if(conn == null){
            System.out.println("Database is NOT connected");
            connect();
        }
        try {
            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO MAIN "+"VALUES ('"+name+"','"+password+"')";

            stmt.executeUpdate(sql);
            System.out.println("executed successfully!");
            //stmt.executeUpdate("INSERT INTO Customers " + "VALUES (1001, 'Simpson', 'Mr.', 'Springfield', 2001)");
           // PreparedStatement pstmt = conn.prepareStatement(sql);
         
           
            stmt.close();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
    }
    public static void main(String args[]){
        DBLayer dblayer = new DBLayer();
        
        dblayer.PrintList();
        dblayer.addUser("yuksel", "1234");
        dblayer.PrintList();

    }
}
 