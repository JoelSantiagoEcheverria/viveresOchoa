
package modelo;

import configuracion.BD;
import controlador.generadorCodigo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import vista.Productos.frmiCategoria;

/**
 *
 * @author Joel
 */
public class categoriaModel {
    public static String registrar = "INSERT INTO CATEGORIA(IDCATEGORIA,DESCRIPCAT)VALUES(?,?)";
    public static String eliminar = "DELETE FROM CATEGORIA WHERE IDCATEGORIA=?";
    
    
    public static void idCategoria(){
        String cod=null;
        String sql = "SELECT MAX(IDCATEGORIA) FROM CATEGORIA";
        
        Statement st;
        ResultSet rs;
        
        try {
            st = BD.conexion().createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {            
            cod = rs.getString(1);
        }
            if (cod == null) {
                frmiCategoria.txt_codigoCat.setText("CT0001");
            }else{
                char n1 = cod.charAt(2);
                char n2 = cod.charAt(3);
                char n3 = cod.charAt(4);
                char n4 = cod.charAt(5);
                
                String num = ""+n1+n2+n3+n4;
                int codigo = Integer.parseInt(num);
                generadorCodigo gc = new generadorCodigo();
                gc.codigo(codigo);
                frmiCategoria.txt_codigoCat.setText("CT"+gc.toString());
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(categoriaModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}
