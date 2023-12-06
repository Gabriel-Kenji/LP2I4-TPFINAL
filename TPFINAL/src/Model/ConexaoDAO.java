package Model;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexaoDAO {
    private Connection conn;
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/tpfinallp2";
    private  String user = "root";
    private  String password = "root";
    public Connection connectionDAO(){


        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url,user,password);
            return conn;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }


}
