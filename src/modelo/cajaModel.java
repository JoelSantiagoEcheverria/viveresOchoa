
package modelo;

import configuracion.BD;
import configuracion.codigoVenta;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import vista.Caja.frmiCobrar;

/**
 *
 * @author Joel
 */
public class cajaModel {
    public static String ingresarVenta = "INSERT INTO VENTAS(NUMVENT,FECHA,TOTAL)VALUES(?,?,?)";
    
    public static String leerProducto = "SELECT CODPROD, DESCRIPCAT, DESCRIPCION, PVENTA, STOCK FROM productos INNER JOIN "
            + "categoria ON productos.IDCATEGORIA = categoria.IDCATEGORIA";
    
    public static String eliminar = "DELETE FROM VENTAS WHERE NUMVENT = ?";
    
    
    public static void codigoVent(){
        String sql= "SELECT MAX(NUMVENT) FROM VENTAS";
        String c = "";
            
            Statement st;
            ResultSet rs;
            
        try {
            
            st = BD.conexion().createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {                
              c = rs.getString(1);
            }
            
            if (c== null) {
                frmiCobrar.lbl_NumeroVenta.setText("NV000000001");
            } else {
                String dato = c.substring(2,11);
                int cod = Integer.parseInt(dato);
                codigoVenta cv = new codigoVenta();
                cv.nVenta(cod);
                frmiCobrar.lbl_NumeroVenta.setText("NV"+cv.toString());
              
            }
        } catch (SQLException ex) {
            Logger.getLogger(cajaModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

