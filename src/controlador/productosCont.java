
package controlador;

import configuracion.BD;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
import vista.Caja.frmiCobrar;
import vista.Caja.frmiSelectClie;
import vista.Productos.frmiActualizarProductos;
import vista.Productos.frmiCatalogoProducto;

/**
 *
 * @author Joel
 */
public class productosCont implements CRUD {
    private String codigoProd,idCategoria,codProv;
    private String descripcion,marca;
    private float pCompra,pVenta;
    private int stock;
    PreparedStatement ps;
    Statement st;
    ResultSet rs;

    public productosCont(String codigoProd, String idCategoria, String codProv, String descripcion, String marca, float pCompra, float pVenta, int stock) {
        this.codigoProd = codigoProd;
        this.idCategoria = idCategoria;
        this.codProv = codProv;
        this.descripcion = descripcion;
        this.marca = marca;
        this.pCompra = pCompra;
        this.pVenta = pVenta;
        this.stock = stock;
    }
 

    public productosCont() {
    }

    public String getCodigoProd() {
        return codigoProd;
    }

    public void setCodigoProd(String codigoProd) {
        this.codigoProd = codigoProd;
    }

    public String getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(String idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getCodProv() {
        return codProv;
    }

    public void setCodProv(String codProv) {
        this.codProv = codProv;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public float getpCompra() {
        return pCompra;
    }

    public void setpCompra(float pCompra) {
        this.pCompra = pCompra;
    }

    public float getpVenta() {
        return pVenta;
    }

    public void setpVenta(float pVenta) {
        this.pVenta = pVenta;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    
    
    @Override
    public void guardar() {
        String sql = productosModel.registrar;
        
        try {
            ps = BD.conexion().prepareStatement(sql);
            ps.setString(1, getCodigoProd());
            ps.setString(2, getIdCategoria());
            ps.setString(3, getCodProv());
            ps.setString(4, getDescripcion());
            ps.setString(5, getMarca());
            ps.setFloat(6, getpCompra());
            ps.setFloat(7, getpVenta());
            ps.setFloat(8, getStock());
            
            int registrado = ps.executeUpdate();
            if (registrado != 0) {
                JOptionPane.showMessageDialog(null,"Registro Guardado");
            }
        } catch (SQLException ex) {
            Logger.getLogger(productosCont.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Verifique el error"+ex);
        }
    }
    
    
    @Override
    public void buscar(String Buscar) {
        String sql;
        String[] datos = new String[7];
        
        DefaultTableModel modelo = (DefaultTableModel) frmiCatalogoProducto.table_Producto.getModel();
        
        while (modelo.getRowCount() !=0) {            
            modelo.removeRow(0);
        }
        if (Buscar.equals("")) {
            sql = productosModel.leer;
        }else{
            sql = "SELECT CODPROD,DESCRIPCION,MARCA,PCOMPRA,PVENTA,STOCK,NOMPROV,IDCATEGORIA "
                    + "FROM PRODUCTOS INNER JOIN proveedores ON productos.CODPROV = proveedores.CODPROV "
                    + "WHERE DESCRIPCION LIKE '%"+Buscar+"%' OR IDCATEGORIA LIKE '%"+Buscar+"%'";
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
                datos[5] = rs.getString(6);
                datos[6] = rs.getString(7);
                
                
                modelo.addRow(datos);
            }
                } catch (SQLException ex) {
                    Logger.getLogger(productosCont.class.getName()).log(Level.SEVERE, null, ex);
                }
        
 
    }
  

    @Override
    public void actualizar() {
        String sql = productosModel.actualizar;
        try {
            ps = BD.conexion().prepareStatement(sql);
            ps.setString(1, getIdCategoria());
            ps.setString(2, getCodProv());
            ps.setString(3, getDescripcion());
            ps.setString(4, getMarca());
            ps.setFloat(5, getpCompra());
            ps.setFloat(6, getpVenta());
            ps.setInt(7, getStock());
            ps.setString(8, getCodigoProd());
            int actualizado = ps.executeUpdate();
            if (actualizado !=0) {
                JOptionPane.showMessageDialog(null, "Registro Actualizado");
            }
        } catch (SQLException ex) {
            Logger.getLogger(productosCont.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void actualizarStocksc(){
        String sql ="SELECT STOCK FROM PRODUCTOS WHERE CODPROD = '"+frmiCobrar.cod+"'";
        String stocks = "";
        int stk = 0;
        
        try {
            st = BD.conexion().createStatement();
            rs = st.executeQuery(sql);
            
            while (rs.next()) {                
                stocks = rs.getString(1);
            }
            
            stk = Integer.parseInt(stocks);
            
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(productosCont.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        int cant = Integer.parseInt(frmiCobrar.cantidad);
        int ntk = stk-cant;
        
       
            String newStock = String.valueOf(ntk);
        
        String updateStock="UPDATE PRODUCTOS SET STOCK=? WHERE CODPROD = '"+frmiCobrar.cod+"'";
        
        try {
            ps = BD.conexion().prepareStatement(updateStock);
            
            ps.setString(1, newStock);
            
            ps.executeUpdate();
            
           
            
            ps.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(productosCont.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
        
      
    }
    
    public void actualizarStockcc(){
        String sql ="SELECT STOCK FROM PRODUCTOS WHERE CODPROD = '"+frmiSelectClie.cod+"'";
        String stocks = "";
        int stk = 0;
        
        try {
            st = BD.conexion().createStatement();
            rs = st.executeQuery(sql);
            
            while (rs.next()) {                
                stocks = rs.getString(1);
            }
            
            stk = Integer.parseInt(stocks);
            
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(productosCont.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        int cant = Integer.parseInt(frmiSelectClie.cantidad);
        int ntk = stk-cant;
        
        String newStock = String.valueOf(ntk);
        
        String updateStock="UPDATE PRODUCTOS SET STOCK=? WHERE CODPROD = '"+frmiSelectClie.cod+"'";
        
        try {
            ps = BD.conexion().prepareStatement(updateStock);
            
            ps.setString(1, newStock);
            
            ps.executeUpdate();
            
            
            
            ps.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(productosCont.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @Override
    public void eliminar() {
        String sql = productosModel.eliminar;
        String cod = frmiCatalogoProducto.codigo;
        try {
            ps = BD.conexion().prepareStatement(sql);
            ps.setString(1, cod);
            int eliminado = ps.executeUpdate();
            if (eliminado !=0) {
                JOptionPane.showMessageDialog(null, "Registro eliminado");
            }
        } catch (SQLException ex) {
            String mensajeError; 
            mensajeError = "Cannot delete or update a parent row: a foreign key constraint fails (`viveresochoa`.`detalle_ventas`, CONSTRAINT `FK_RELATIONSHIP_3` FOREIGN KEY (`CODPROD`) REFERENCES `productos` (`CODPROD`))";
            if (mensajeError.equals(ex.getMessage())) {
                JOptionPane.showMessageDialog(null, "Lo sentimos pero no se puede borrar este producto \n"
                        + "borre todas las ventas en el que está involucrado","ERROR ELIMINAR",JOptionPane.ERROR_MESSAGE);
            }else{
               Logger.getLogger(clientesCont.class.getName()).log(Level.SEVERE, null, ex); 
            }
              
        }
        
    }
    
    public String verid(){
        String idCat="";
        String sql = "SELECT IDCATEGORIA FROM PRODUCTOS WHERE codprod = '"+frmiActualizarProductos.cod+"'";
        
        try {
            st = BD.conexion().createStatement();
            rs = st.executeQuery(sql);
            
            while (rs.next()) {                
                idCat = rs.getString("IDCATEGORIA");
            }
        } catch (SQLException ex) {
            Logger.getLogger(productosCont.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idCat;
    }
    
      public String idproveedor(){
        String sql = "SELECT CODPROV FROM PRODUCTOS WHERE codprod = '"+frmiActualizarProductos.cod+"'";
        String proveedor = "";
        try {
            st = BD.conexion().createStatement();
            rs = st.executeQuery(sql);
            
            while(rs.next()){
                proveedor = rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(productosCont.class.getName()).log(Level.SEVERE, null, ex);
        }
        return proveedor;
    }
}
