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
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.hibernate.criterion.Expression.sql;
/**
 *
 * @author yukselkaradeniz
 */
public class DBLayer {
    int recipeId = 0;
    private Connection conn;
    String dbUrl = "jdbc:derby://localhost:1527/Guru";
    String name = "guru";String pass = "123";
    
    
   public class Node<E> {

   /** The element stored at this node */
   public String content1;            // reference to the element stored at this node
   public int photoId1;            // reference to the element stored at this node
   public String owner1;            // reference to the element stored at this node
   public String viewers1;            // reference to the element stored at this node
   public String title1;            // reference to the element stored at this node


   /** A reference to the subsequent node in the list */
   private Node<E> next;         // reference to the subsequent node in the list

   public Node(String c, int p , String o , String v, String t) {
     title1 = t;
     owner1 = o ;
     content1 = c;
     photoId1 = p;
     viewers1 = v;
   }

 //  public E getElement() { return; }
   
   public String getTitle() { return title1; }
   public String getOwner() { return owner1; }
   public String getContent() { return content1; }
   public String getViewers() { return (String)viewers1; }
   public int getPhotoId() { return photoId1; }

   /**
    * Returns the node that follows this one (or null if no such node).
    * @return the following node
    */
   public Node<E> getNext() { return next; }

   // Modifier methods
   /**
    * Sets the node's next reference to point to Node n.
    * @param n    the node that should follow this one
    */
   public void setNext(Node<E> n) { next = n; }
 } //----------- end of nested Node class -----------
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
            stmt.close();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
    }
    
    public boolean loginUser(String name, String password){
        if(conn == null){
            System.out.println("Database is NOT connected");
            connect();
        }
        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM MAIN WHERE NAME = '"+name+"' AND PASSWORD = '"+password+"'";
            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            if(rs.next() == false){
                System.out.println("Invalid Password or username!!! ");
                return false;
            }else{
                System.out.println("Succesfully Login");
                login._username=name;
            }
            stmt.close();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }
    
    public void addRecipe(String content, int pid, String owner, ArrayList<String> viewers, String title){
        if(conn == null){
            System.out.println("Database is NOT connected");
            connect();
        }
        try {
            String sql = "INSERT INTO RECIPE (RECIPE_ID, NAME, CONTENT, VIEWER, TITTLE)" +
                    "VALUES(?, ?, ?, ?, ?)";
            PreparedStatement stmt;
            stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, pid);
            stmt.setString(2, owner);
            stmt.setString(3, content);
            stmt.setString(4, null);
            stmt.setString(5, title);
            System.out.println("heyyo --> " + sql);
            //stmt.executeUpdate(sql);
            stmt.executeUpdate();
            System.out.println("executed successfully!");
            System.out.println("heyyo2");
            stmt.close();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        recipeId++;
    }
    
    public void addRecipe(int rid, String owner, String content, ArrayList<String> viewers, String title){
        if(conn == null){
            System.out.println("Database is NOT connected");
            connect();
        }
        try {
            //Statement stmt = conn.createStatement();
            String sql = "INSERT INTO RECIPE (RECIPE_ID, NAME, CONTENT, VIEWER, TITTLE)" +
                    "VALUES(?, ?, ?, ?, ?)";
            PreparedStatement stmt;
            //String sql = "INSERT INTO RECIPE(RECIPE_ID,NAME,CONTENT,VIEWERS,TITTLE) "+"VALUES ( '"+rid+"' , "+owner+" , '"+content+"' , '"+viewers+"' , '"+ title+"' )";
            //stmt.executeUpdate(sql);
                //stmt.close();
            stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, rid);
            stmt.setString(2, owner);
            stmt.setString(3, content);
            stmt.setString(4, null);
            stmt.setString(5, title);
            stmt.close();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        recipeId++;
    }

    public LinkedList getRecipe(){
        String owner = login._username;
        LinkedList<Node> list = new LinkedList();
         if(conn == null){
            System.out.println("Database is NOT connected");
            connect();
        }
        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM RECIPE WHERE NAME = '"+owner + "'";
            System.out.println("owner:"+owner);
            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            //System.out.println("DEBUG SIZE));
           
               

                while(rs.next() != false){
                                System.out.println("DEBUG2");

                    System.out.println("12345");
                    int recipeId = (int) rs.getObject("RECIPE_ID") ;
                    String content = (String) rs.getObject("CONTENT");
                    String viewers = (String) rs.getObject("VIEWER") ;
                    String title = (String) rs.getObject("TITTLE") ;
                    Node newest;
                    System.out.println("content-->" +content);
                    System.out.println("viewers-->" + viewers);
                    System.out.println("recipe_id-->"+recipeId);
                    System.out.println("title-->"+title);
                    newest = new Node(content, recipeId,owner, viewers, title);
                    list.add(newest);
                }
            
            stmt.close();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("listtt: " + list.size());
        return list;
        

    }
        
    public String getContent(Node e){
        return e.getContent();
    }
    public String getOwner(Node e){
        return e.getOwner();
    }
    public String getTitle(Node e){
        return e.getTitle();
    }
    public String getViewers(Node e){
        return e.getViewers();
    }
    public int getPhotoId(Node e){
        return e.getPhotoId();
    }

    public static void main(String args[]){
        DBLayer db = new DBLayer();
        db.addUser("test", "test");
        db.loginUser("test", "test");
        //db.loginUser("yuksel", "1234");
        db.addRecipe("sucuk,yumurta,ekmek,kaşar,baharatlar", 0, "test", null, "tost");
        System.out.println("size ---> " + db.getRecipe().size());
    }
}
 