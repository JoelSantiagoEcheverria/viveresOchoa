
package configuracion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class BD {
   
    public static Connection conexion() throws SQLException{
        Connection cn= null;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/viveresochoa?autoReconnect=true&useSSL=False","root","085561139");
            System.out.println("Conexion establecida");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error de conexion");
        }
        return cn;
    }
}
