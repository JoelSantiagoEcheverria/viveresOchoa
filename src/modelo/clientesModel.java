
package modelo;

import controlador.generadorCodigo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joel
 */
public class clientesModel {
    public static String registrar = "INSERT INTO CLIENTES(CODCLIE,CEDCLIE,NOMCLIE,DIRECCLIE,TELEFCLIE)VALUES(?,?,?,?,?)";
    public static String leer = "SELECT * FROM CLIENTES ORDER BY NOMCLIE";
    public static String actualizar ="UPDATE CLIENTES SET CEDCLIE=?, NOMCLIE=?, DIRECCLIE=?, TELEFCLIE=? WHERE CODCLIE=?";
    public static String eliminar = "DELETE FROM CLIENTES WHERE CODCLIE =?";
    
    
    public static void idCliente(){
       
        String c="";
        String sql = "SELECT MAX(CODCLIE) FROM CLIENTES";
        
        
        Statement st;
        ResultSet rs;
        
        try {
            st = configuracion.BD.conexion().createStatement();
            rs = st.executeQuery(sql);
            
            while(rs.next()){
                c = rs.getString(1);
            }
            if (c == null) {
                vista.Clientes.frmiNuevo.txt_ClienteCod.setText("CL0001");
            } else {
                char n1 = c.charAt(2);
                char n2 = c.charAt(3);
                char n3 = c.charAt(4);
                char n4 = c.charAt(5);
                
                String cod = ""+n1+n2+n3+n4;
                int num = Integer.parseInt(cod);
                generadorCodigo numero = new generadorCodigo();
                numero.codigo(num);
                vista.Clientes.frmiNuevo.txt_ClienteCod.setText("CL"+numero.toString());
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(clientesModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
