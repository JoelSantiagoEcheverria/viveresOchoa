
package modelo;

import configuracion.BD;
import controlador.generadorCodigo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import vista.Productos.frmiNuevoProductos;

/**
 *
 * @author Joel
 */
public class productosModel {
    public static String registrar= "INSERT INTO PRODUCTOS (CODPROD,IDCATEGORIA,CODPROV,"
            + "DESCRIPCION,MARCA,PCOMPRA,PVENTA,STOCK)VALUES(?,?,?,?,?,?,?,?)";
    
    public static String leer = "SELECT CODPROD,DESCRIPCION,MARCA,PCOMPRA,PVENTA,STOCK,NOMPROV "
            + " FROM PRODUCTOS INNER JOIN PROVEEDORES ON PRODUCTOS.CODPROV = PROVEEDORES.CODPROV "
            + "ORDER BY DESCRIPCION ";
    
    public static String actualizar ="UPDATE PRODUCTOS SET IDCATEGORIA=?,CODPROV=?,DESCRIPCION=?,MARCA=?,"
            + "PCOMPRA=?,PVENTA=?,STOCK=? WHERE CODPROD =?";                                                                    
    
    public static String eliminar = "DELETE FROM PRODUCTOS WHERE CODPROD=?";
    
    
    public static void idProductos(){
        String cod = null;
        String sql = "SELECT MAX(CODPROD) FROM PRODUCTOS";
        
        Statement st;
        ResultSet rs;
        
        try {
            st = BD.conexion().createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {                
                cod = rs.getString(1);
            }
            
            if (cod == null) {
                frmiNuevoProductos.txt_CodigoPro.setText("PT0001");
            }else{
                char n1 = cod.charAt(2);
                char n2 = cod.charAt(3);
                char n3 = cod.charAt(4);
                char n4 = cod.charAt(5);
                
                String cadena = "" +n1+n2+n3+n4;
                int num = Integer.parseInt(cadena);
                generadorCodigo codigo = new generadorCodigo();
                codigo.codigo(num);
                frmiNuevoProductos.txt_CodigoPro.setText("PT"+codigo.toString());
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(productosModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
