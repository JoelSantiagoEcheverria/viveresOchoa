
package modelo;

import configuracion.BD;
import controlador.generadorCodigo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import vista.Proveedores.frmiNuevoPv;

/**
 *
 * @author Joel
 */
public class proveedoresModel {
    
    public static String registrarPv= "INSERT INTO PROVEEDORES(CODPROV,CEDPROV,NOMPROV,DIRECPROV,TELEFPROV)VALUES(?,?,?,?,?)";
    
    public static String leerPv =" SELECT * FROM PROVEEDORES ORDER BY NOMPROV";
    
    public static String actualizar = "UPDATE PROVEEDORES SET CEDPROV=?, NOMPROV=?, DIRECPROV=?, TELEFPROV=?"
            + "WHERE CODPROV=?";
    
    public static String eliminar = "DELETE FROM PROVEEDORES WHERE CODPROV=?";
    
    
    public static void generarCodigo(){
        String codigo = null;
        String sql = "SELECT MAX(CODPROV) FROM PROVEEDORES";
        
        Statement st;
        ResultSet rs;
        
        try {
            st = BD.conexion().createStatement();
            rs = st.executeQuery(sql);
            
            while (rs.next()) {                
                codigo = rs.getString(1);
            }
            
            if (codigo != null) {
                char n1 = codigo.charAt(2);
                char n2 = codigo.charAt(3);
                char n3 = codigo.charAt(4);
                char n4 = codigo.charAt(5);
                
                String cadena = ""+n1+n2+n3+n4;
                int id = Integer.parseInt(cadena);
                generadorCodigo cod = new generadorCodigo();
                cod.codigo(id);
                frmiNuevoPv.txt_ProveedorCod.setText("PV"+cod.toString());
            } else {
                frmiNuevoPv.txt_ProveedorCod.setText("PV0001");
            }
        } catch (SQLException ex) {
            Logger.getLogger(proveedoresModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
