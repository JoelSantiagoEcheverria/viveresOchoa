package controlador;

import configuracion.BD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.cajaModel;
import vista.Caja.frmiCobrar;
import static vista.Caja.frmiCobrar.lbl_NumeroVenta;
import static vista.Caja.frmiCobrar.table;
import vista.Caja.frmiSelectClie;
import static vista.Ventas.frmiCatalogoVentas.cod;
import vistaPrincipal.frmMenu;

/**
 *
 * @author user
 */
public class ventasCont implements CRUD {

    PreparedStatement ps;
    Statement st;
    ResultSet rs;

    @Override
    public void guardar() {
        String sql = cajaModel.ingresarVenta;
        
        String fecha = frmiCobrar.lbl_InsertarFecha.getText();
        String total = frmiCobrar.txt_Total.getText();
        String nventa = frmiCobrar.lbl_NumeroVenta.getText();
        try {
            ps = BD.conexion().prepareStatement(sql);
            
            ps.setString(1, nventa);
            ps.setString(2, fecha);
            ps.setString(3, total);

            int guardado = ps.executeUpdate();

            if (guardado != 0) {
                JOptionPane.showMessageDialog(null, "Registro Guardado");
            } else {
                JOptionPane.showMessageDialog(null, "No se guardó el registro");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ventasCont.class.getName()).log(Level.SEVERE, null, ex);
        }
           
       
    }

    

    @Override
    public void buscar(String Busca) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar() {
        String sql = cajaModel.eliminar;
        
        try {
            ps = BD.conexion().prepareStatement(sql);
            ps.setString(1, cod);
            int eliminado = ps.executeUpdate();
            
            if (eliminado != 0) {
                JOptionPane.showMessageDialog(null, "Venta eliminada");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo eliminar esta venta");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ventasCont.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
