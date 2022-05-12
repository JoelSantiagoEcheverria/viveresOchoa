
package controlador;

import configuracion.BD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.productosModel;
import modelo.proveedoresModel;
import vista.Proveedores.frmiCatalogoPv;

/**
 *
 * @author Joel
 */
public class proveedoresCont extends Persona implements CRUD {

    public proveedoresCont(String codigo, String cedula, String nombreApel, String direccion, String telefono) {
        super(codigo, cedula, nombreApel, direccion, telefono);
    }

    public proveedoresCont(String codigo, String nombreApel) {
        super(codigo, nombreApel);
    }
    

    public proveedoresCont() {
    }

    
    
    PreparedStatement ps;
    Statement st;
    ResultSet rs;
    
    @Override
    public void guardar() {
        String sql = proveedoresModel.registrarPv;
        
        try {
            ps = BD.conexion().prepareStatement(sql);
            ps.setString(1, getCodigo());
            ps.setString(2, getCedula());
            ps.setString(3, getNombreApel());
            ps.setString(4, getDireccion());
            ps.setString(5, getTelefono());
            
            int guardado = ps.executeUpdate();
            
            if (guardado != 0) {
                JOptionPane.showMessageDialog(null, "Registro Guardado");
            }
        } catch (SQLException ex) {
            Logger.getLogger(proveedoresCont.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Verifique el error "+ex);
        }
    }

    @Override
    public void buscar(String Buscar) {
        String sql;
        String[] datos = new String[5];
        
        DefaultTableModel modelo = (DefaultTableModel) frmiCatalogoPv.tableProv.getModel();
        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }

        if (Buscar.isEmpty()) {
            sql= proveedoresModel.leerPv;
        }else{
            sql="SELECT * FROM PROVEEDORES WHERE CEDPROV LIKE '%"+Buscar+"%' OR NOMPROV LIKE '%"+Buscar+"%' ORDER BY NOMPROV";
        }
        try {
            
            st = BD.conexion().createStatement();
            rs = st.executeQuery(sql);
            
            while (rs.next()) {                
               datos[0] = rs.getString(1);
               datos[1] = rs.getString(2);
               datos[2] = rs.getString(3);
               datos[3] = rs.getString(4);
               datos[4] = rs.getString(5);
               
               modelo.addRow(datos);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(proveedoresCont.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void actualizar() {
        String sql = proveedoresModel.actualizar;
        
        try {
            ps = BD.conexion().prepareStatement(sql);
            ps.setString(1, getCedula());
            ps.setString(2, getNombreApel());
            ps.setString(3, getDireccion());
            ps.setString(4, getTelefono());
            ps.setString(5, getCodigo());
            
            int actualizado = ps.executeUpdate();
            if (actualizado != 0) {
                JOptionPane.showMessageDialog(null, "Registro Actualizado");
            }
        } catch (SQLException ex) {
            Logger.getLogger(proveedoresCont.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Verifica el error "+ex);
        }
        
    }

    @Override
    public void eliminar() {
        String sql = proveedoresModel.eliminar;
        
        try {
            ps = BD.conexion().prepareStatement(sql);
            ps.setString(1, frmiCatalogoPv.codigo);
            int eliminado = ps.executeUpdate();
            if (eliminado !=0) {
                JOptionPane.showMessageDialog(null, "Registro Eliminado "+frmiCatalogoPv.nombre);
            }
        } catch (SQLException ex) {
            String mensajeError = "Cannot delete or update a parent row: a foreign key constraint fails (`viveresochoa`.`productos`, CONSTRAINT `FK_RELATIONSHIP_6` FOREIGN KEY (`CODPROV`) REFERENCES `proveedores` (`CODPROV`))";
              if (mensajeError.equals(ex.getMessage())) {
                JOptionPane.showMessageDialog(null, "Lo sentimos pero no se puede borrar este proveedor \n"
                        + "borre todas los productos en el que está involucrado","ERROR ELIMINAR",JOptionPane.ERROR_MESSAGE);
            }else{
               Logger.getLogger(clientesCont.class.getName()).log(Level.SEVERE, null, ex); 
            }
        }
        
    }
    
    public void ListaProv(JComboBox cbo) {
        try {
            String sql = proveedoresModel.leerPv;
            st = BD.conexion().createStatement();
            rs = st.executeQuery(sql);
            cbo.addItem("Seleccione...");
            while (rs.next()) {
                cbo.addItem(new proveedoresCont(rs.getString(1), rs.getString(3)));
              
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(proveedoresCont.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String toString() {
        return getNombreApel();
    }

    
    
    
}
