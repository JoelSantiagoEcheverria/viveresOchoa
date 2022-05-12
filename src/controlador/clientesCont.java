
package controlador;

import configuracion.BD;
import modelo.clientesModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import vista.Caja.frmiSelectClie;
import vista.Clientes.frmiCatalogo;

/**
 *
 * @author Joel
 */
public class clientesCont extends Persona{

   
    public clientesCont(String codigo, String cedula, String nombreApel, String direccion, String telefono) {
        super(codigo, cedula, nombreApel, direccion, telefono);
    }

    
    public clientesCont() {
    }
    
    PreparedStatement ps ;
    Statement st;
    ResultSet rs;
    @Override
    public void guardar() {
        try {
            Connection cn = BD.conexion();
            ps = cn.prepareStatement(clientesModel.registrar);
            
            ps.setString(1, getCodigo());
            ps.setString(2, getCedula());
            ps.setString(3, getNombreApel());
            ps.setString(4, getDireccion());
            ps.setString(5, getTelefono());
            
            int conectar = ps.executeUpdate();
            if (conectar !=0) {
                JOptionPane.showMessageDialog(null,"Registro Guardado");
            }else{
                JOptionPane.showMessageDialog(null,"No se guardó el registro");
            }
            
            ps.close();
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(clientesCont.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Verifique el error "+ex);
        }
  
    }

    
     
    @Override
    public void buscar(String Busca) {
        
            DefaultTableModel modelo = (DefaultTableModel) frmiCatalogo.table_clientes.getModel();
        
        while(modelo.getRowCount()>0){
            modelo.removeRow(0);
        }
        
        String sql;
        
        if (Busca.isEmpty()) {
            sql = clientesModel.leer;
        }else{
            sql = "SELECT * FROM CLIENTES WHERE NOMCLIE LIKE '%"+Busca+"%' or CEDCLIE LIKE '%"+Busca+"%' ORDER BY NOMCLIE";
        }
        
        String[] datos = new String[5];
        
        try {
            st = BD.conexion().createStatement();
            rs = st.executeQuery(sql);
            
            while(rs.next()){
                datos[0] = rs.getString("CODCLIE");
                datos[1] = rs.getString("CEDCLIE");
                datos[2] = rs.getString("NOMCLIE");
                datos[3] = rs.getString("DIRECCLIE");
                datos[4] = rs.getString("TELEFCLIE");
                
                modelo.addRow(datos);
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(clientesCont.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @Override
    public void actualizar() {
        String sql = clientesModel.actualizar;
        
        try {
            ps = BD.conexion().prepareStatement(sql);
            
            ps.setString(1, getCedula());
            ps.setString(2, getNombreApel());
            ps.setString(3, getDireccion());
            ps.setString(4, getTelefono());
            ps.setString(5, getCodigo());
            
            int registro = ps.executeUpdate();
            if (registro != 0) {
                JOptionPane.showMessageDialog(null, "Registro Actualizado");
                
            }else{
                JOptionPane.showMessageDialog(null, "Error de registro","Error",JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(clientesCont.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @Override
    public void eliminar() {
        String sql = clientesModel.eliminar;
        String cod = frmiCatalogo.codigo;
        try {
            ps = BD.conexion().prepareStatement(sql);
            ps.setString(1, cod);
            int eliminado = ps.executeUpdate();
            if (eliminado !=0) {
              JOptionPane.showMessageDialog(null, "Registro Eliminado "+frmiCatalogo.nombre);
            }
        } catch (SQLException ex) {
            String mensajeError; 
            mensajeError = "Cannot delete or update a parent row: a foreign key constraint fails (`viveresochoa`.`detalle_ventas`, CONSTRAINT `FK_RELATIONSHIP_4` FOREIGN KEY (`CODCLIE`) REFERENCES `clientes` (`CODCLIE`))";
            if (mensajeError.equals(ex.getMessage())) {
                JOptionPane.showMessageDialog(null, "Lo sentimos pero no se puede borrar este cliente \n"
                        + "borre todas las ventas en el que está involucrado","ERROR ELIMINAR",JOptionPane.ERROR_MESSAGE);
            }else{
               Logger.getLogger(clientesCont.class.getName()).log(Level.SEVERE, null, ex); 
            }
            
        }
    }
    
    
}
