
package modelo;

import configuracion.BD;
import controlador.generadorCodigo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class usuarioModel {
    public static String registrar="INSERT INTO USUARIOS(CODUSU,NOMBRE,CONTRASENA,TIPOUSU)VALUES(?,?,?,?)";
    public static String leer ="SELECT CODUSU,NOMBRE,TIPOUSU FROM USUARIOS ORDER BY NOMBRE";
    public static String actualizar="UPDATE USUARIOS SET NOMBRE=?, CONTRASENA=?,TIPOUSU=? WHERE CODUSU=?";
    public static String eliminar="DELETE FROM USUARIOS WHERE CODUSU=?";
    
    
    public static void codUsu(){
        String sql = "SELECT MAX(CODUSU) FROM USUARIOS";
        String c ="";
        
        Statement st;
        ResultSet rs;
        try {
            st = BD.conexion().createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
            c= rs.getString(1);
            }
            
                if (c == null) {
                    vista.Usuario.frmiNuevo.txt_UsuarioCod.setText("USU0001");
                }else{
                    String cod = c.substring(3,7);
                    int codigo = Integer.parseInt(cod);
                    generadorCodigo cd = new generadorCodigo(); 
                    cd.codigo(codigo);
                    vista.Usuario.frmiNuevo.txt_UsuarioCod.setText("USU"+cd.toString());
                }
        
        } catch (SQLException ex) {
            Logger.getLogger(usuarioModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}

