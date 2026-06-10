import java.sql.*;

public abstract class ConnectionDAO {
    Connection connection;

    PreparedStatement pst;
    Statement st;
    ResultSet rs;

    String database = "madalu";
    String user = "root";
    String password = "root";
    String url = "jdbc:mysql://localhost:3306/" + database;

    public Connection connectToDb(){
        try{
            connection = DriverManager.getConnection(url, user, password);
        }catch (SQLException e){
            System.out.println("Erro ao conectar com o banco de dados" + e.getMessage());
        }
        return connection;
    }
}