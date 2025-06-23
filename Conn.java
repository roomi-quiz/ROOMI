import java.sql.*;

public class Conn {
    private Connection connection;
    public Statement s;
    
    public Conn() {
        try {
            
            String url = "jdbc:mysql://localhost:3306/quizapplication?useSSL=false&serverTimezone=UTC";
            String username = "root"; 
            String password = "roomi091"; 
            
           
            connection = DriverManager.getConnection(url, username, password);
            s = connection.createStatement();
            
            System.out.println("Database connection established successfully!");
        } catch (SQLException e) {
            System.err.println("Database connection failed!");
            System.err.println("Error message: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public Connection getConnection() {
        return connection;
    }
    
    
    public void close() {
        try {
            if (s != null) s.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}