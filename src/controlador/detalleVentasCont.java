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
import modelo.detalleVentaModel;
import vista.Caja.frmiCierreCaja;
import vista.Caja.frmiCobrar;
import vista.Caja.frmiSelectClie;
import vista.Ventas.frmiCatalogoVentas;
import vistaPrincipal.frmMenu;

public class detalleVentasCont extends Persona {

    ResultSet rs;
    PreparedStatement ps;
    Statement st;

    @Override
    public void guardar() {
        String sql = detalleVentaModel.ingresoDetalle;
        
        String nVenta = frmiCobrar.lbl_NumeroVenta.getText();

        
        if (frmiCobrar.siCliente) {
            String codigoProd = frmiSelectClie.cod;
            int filaselec = vista.Caja.frmiSelectClie.table_clientes.getSelectedRow();
            String cantidadProducto = vista.Caja.frmiSelectClie.cantidad;
            String codClie = vista.Caja.frmiSelectClie.table_clientes.getValueAt(filaselec, 0).toString();
            try {
                ps = BD.conexion().prepareStatement(sql);
                ps.setString(1, codigoProd);
                ps.setString(2, codClie);
                ps.setString(3, codigoUsuario());
                ps.setString(4, nVenta);
                ps.setString(5, cantidadProducto);

                ps.executeUpdate();
                

                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(detalleVentasCont.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
                String codigoProd = frmiCobrar.cod;
                String cantidadProducto = vista.Caja.frmiCobrar.cantidad; 
                
                
            try {
           
                ps = BD.conexion().prepareStatement("INSERT INTO detalle_ventas(CODPROD,CODUSU,NUMVENT,CANTIDAD)VALUES(?,?,?,?)");
                ps.setString(1, codigoProd);
                ps.setString(2, codigoUsuario());
                ps.setString(3, nVenta);
                ps.setString(4, cantidadProducto);

                ps.executeUpdate();
                

                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(detalleVentasCont.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public String codigoUsuario() {
        String codUsu = "";
        String vendedor = frmMenu.lbl_Usuario.getText();

        String sql = "SELECT CODUSU FROM USUARIOS WHERE NOMBRE = '" + vendedor + "'";

        try {
            st = BD.conexion().createStatement();
            rs = st.executeQuery(sql);
            

            while (rs.next()) {
                codUsu = rs.getString("CODUSU");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ventasCont.class.getName()).log(Level.SEVERE, null, ex);
        }
        return codUsu;
    }
    
    public void buscarFecha(String fecha){
        String sql = detalleVentaModel.buscarFecha;
        String[] datos = new String[5];
        DefaultTableModel modelo = (DefaultTableModel) frmiCatalogoVentas.table.getModel();
        
        while(modelo.getRowCount()>0){
            modelo.removeRow(0);
        }
        
        try {
            ps = BD.conexion().prepareStatement(sql);
            ps.setString(1, fecha);
            ps.setString(2, fecha);
            rs = ps.executeQuery();
            
            while (rs.next()) {    
                datos[0] = rs.getString("NUMVENT");
                datos[1] = rs.getString("NOMCLIE");
                datos[2] = rs.getString("NOMBRE");
                datos[3] = rs.getString("FECHA");
                datos[4] = rs.getString("TOTAL");
                
                modelo.addRow(datos);
            }
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(detalleVentasCont.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void buscar(String Busca) {
        String sql = detalleVentaModel.leerVenta;
        String[] datos = new String[5];

        DefaultTableModel modelo = (DefaultTableModel) frmiCatalogoVentas.table.getModel();
        
        while (modelo.getRowCount() > 0) {            
            modelo.removeRow(0);
        }

        try {
            st = BD.conexion().createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {

                datos[0] = rs.getString("NUMVENT");
                datos[1] = rs.getString("NOMCLIE");
                datos[2] = rs.getString("NOMBRE");
                datos[3] = rs.getString("FECHA");
                datos[4] = rs.getString("TOTAL");

                modelo.addRow(datos);
            }
        } catch (SQLException ex) {
            Logger.getLogger(detalleVentasCont.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void cierreCaja(String fecha){
        String sql = detalleVentaModel.hoy;
        String[] datos = new String[5];
        DefaultTableModel modelo = (DefaultTableModel) frmiCierreCaja.table.getModel();
        
        while(modelo.getRowCount()>0){
            modelo.removeRow(0);
        }
        
        try {
            ps = BD.conexion().prepareStatement(sql);
            ps.setString(1, fecha);
            rs = ps.executeQuery();
            
            while (rs.next()) {    
                datos[0] = rs.getString("CODPROD");
                datos[1] = rs.getString("DESCRIPCION");
                datos[2] = rs.getString("FECHA");
                datos[3] = rs.getString("CANTIDAD");
                datos[4] = rs.getString("PVENTA");
                
                modelo.addRow(datos);
            }
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(detalleVentasCont.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void actualizar() {
        String codigoProd = frmiSelectClie.cod;
        JOptionPane.showMessageDialog(null, codigoProd);
    }

    @Override
    public void eliminar() {
        
    }

}
