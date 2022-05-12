
package controlador;

import configuracion.BD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.usuarioModel;



public class usuarioCont implements CRUD  {
    private String codigoUsu;
    private String nombreUsu, contrasenia,tipoUsu;
    PreparedStatement ps;
    Statement st;
    ResultSet rs;

    public usuarioCont(String codigoUsu, String nombreUsu, String contrasenia, String tipoUsu) {
        this.codigoUsu = codigoUsu;
        this.nombreUsu = nombreUsu;
        this.contrasenia = contrasenia;
        this.tipoUsu = tipoUsu;
    }

    public usuarioCont(String nombreUsu, String contrasenia, String tipoUsu) {
        this.nombreUsu = nombreUsu;
        this.contrasenia = contrasenia;
        this.tipoUsu = tipoUsu;
    }
    
    
    public usuarioCont(){
        
    }
    
    
    public void index(){
        new vistaPrincipal.SplashScreen().setVisible(true);
//        new vistaPrincipal.frmLogin().setVisible(true);
       
    }

    public String getCodigoUsu() {
        return codigoUsu;
    }

    public void setCodigoUsu(String codigoUsu) {
        this.codigoUsu = codigoUsu;
    }

    public String getNombreUsu() {
        return nombreUsu;
    }

    public void setNombreUsu(String nombreUsu) {
        this.nombreUsu = nombreUsu;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getTipoUsu() {
        return tipoUsu;
    }

    public void setTipoUsu(String tipoUsu) {
        this.tipoUsu = tipoUsu;
    }

    
    @Override
    public void guardar() {
        String sql= usuarioModel.registrar;
        
        
        
        try {
            ps = BD.conexion().prepareStatement(sql);
            ps.setString(1, getCodigoUsu());
            ps.setString(2, getNombreUsu());
            ps.setString(3, getContrasenia());
            ps.setString(4, getTipoUsu());
            int registrado = ps.executeUpdate();
            if (registrado > 0) {
                JOptionPane.showMessageDialog(null, "Registro Exitoso");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo realizar el registro");
            }
        } catch (SQLException ex) {
            Logger.getLogger(usuarioCont.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "No se pudo realizar el registro: "+ex);
        }
    }

    @Override
    public void buscar(String Buscar) {
        String sql;
        String[] dato=new String[3];
        
        DefaultTableModel modelo = (DefaultTableModel) vista.Usuario.frmiCatalogo.table.getModel();
        
        while(modelo.getRowCount()>0){
            modelo.removeRow(0);
        }
        
        if (Buscar.isEmpty()) {
               sql = usuarioModel.leer; 
            }else{
            sql = "SELECT CODUSU,NOMBRE,TIPOUSU FROM USUARIOS WHERE NOMBRE LIKE '%"+Buscar+"%' ORDER BY NOMBRE";
        }
        
        try {
            
            st= BD.conexion().createStatement();
            rs = st.executeQuery(sql);
            
            while(rs.next()){
                dato[0]=rs.getString("CODUSU");
                dato[1]=rs.getString("NOMBRE");
                dato[2]=rs.getString("TIPOUSU");
                modelo.addRow(dato);
            }
            
            
            
        }catch (SQLException ex) {
            Logger.getLogger(usuarioCont.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @Override
    public void actualizar() {
        String sql= usuarioModel.actualizar;
        
        try {
            ps =BD.conexion().prepareStatement(sql);
            ps.setString(1, getNombreUsu());
            ps.setString(2, getContrasenia());
            ps.setString(3, getTipoUsu());
            ps.setString(4, getCodigoUsu());
            
            int actualizado =ps.executeUpdate();
            
            String act = (actualizado >0)?"Registro Actualizado":"Lo sentimos no se pudo actualizar";
            JOptionPane.showMessageDialog(null, act);
            
        } catch (SQLException ex) {
            Logger.getLogger(usuarioCont.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void eliminar() {
        String sql = usuarioModel.eliminar;
        String cod = vista.Usuario.frmiCatalogo.codigo;
        
        try {
            ps = BD.conexion().prepareStatement(sql);
            ps.setString(1,cod);
            int eliminado = ps.executeUpdate();
            if (eliminado !=0) {
              JOptionPane.showMessageDialog(null, "Registro Eliminado "+vista.Usuario.frmiCatalogo.nombre);
            }
        } catch (SQLException ex) {
            String mensajeError; 
            mensajeError = "Cannot delete or update a parent row: a foreign key constraint fails (`viveresochoa`.`detalle_ventas`, CONSTRAINT `FK_RELATIONSHIP_7` FOREIGN KEY (`CODUSU`) REFERENCES `usuarios` (`CODUSU`))";
            if (mensajeError.equals(ex.getMessage())) {
                JOptionPane.showMessageDialog(null, "Lo sentimos pero no se puede borrar este usuario \n"
                        + "borre todas las ventas en el que está involucrado","ERROR ELIMINAR",JOptionPane.ERROR_MESSAGE);
            }else{
               Logger.getLogger(clientesCont.class.getName()).log(Level.SEVERE, null, ex); 
            }
            System.out.println(ex.getMessage());
        }
    }
    
    
}
