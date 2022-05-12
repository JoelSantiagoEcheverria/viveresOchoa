
package controlador;

import configuracion.BD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import modelo.cajaModel;
import vista.Caja.frmiElegirProd;

/**
 *
 * @author Joel
 */
public class cajaCont implements CRUD{

    private String numVent,codProd,codClie,codUsu;
    private String fechaVent;
    private float iva,subTotal,Total;
    PreparedStatement ps;
    Statement st;
    ResultSet rs;

    public cajaCont(String numVent, String codProd, String codClie, String codUsu, String fechaVent, float iva, float subTotal, float Total) {
        this.numVent = numVent;
        this.codProd = codProd;
        this.codClie = codClie;
        this.codUsu = codUsu;
        this.fechaVent = fechaVent;
        this.iva = iva;
        this.subTotal = subTotal;
        this.Total = Total;
    }

    public cajaCont(String codProd, String codClie, String codUsu, String fechaVent, float iva, float subTotal, float Total) {
        this.codProd = codProd;
        this.codClie = codClie;
        this.codUsu = codUsu;
        this.fechaVent = fechaVent;
        this.iva = iva;
        this.subTotal = subTotal;
        this.Total = Total;
    }

    public String getNumVent() {
        return numVent;
    }

    public void setNumVent(String numVent) {
        this.numVent = numVent;
    }

    public String getCodProd() {
        return codProd;
    }

    public void setCodProd(String codProd) {
        this.codProd = codProd;
    }

    public String getCodClie() {
        return codClie;
    }

    public void setCodClie(String codClie) {
        this.codClie = codClie;
    }

    public String getCodUsu() {
        return codUsu;
    }

    public void setCodUsu(String codUsu) {
        this.codUsu = codUsu;
    }

    public String getFechaVent() {
        return fechaVent;
    }

    public void setFechaVent(String fechaVent) {
        this.fechaVent = fechaVent;
    }

    public float getIva() {
        return iva;
    }

    public void setIva(float iva) {
        this.iva = iva;
    }

    public float getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(float subTotal) {
        this.subTotal = subTotal;
    }

    public float getTotal() {
        return Total;
    }

    public void setTotal(float Total) {
        this.Total = Total;
    }

    

    public cajaCont() {
    }
    
    
    @Override
    public void guardar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void buscar(String Buscar) {
        String sql ;
        String[] datos = new String[5];
        DefaultTableModel modelo = (DefaultTableModel) frmiElegirProd.table.getModel();
        
        while (modelo.getRowCount()> 0) {            
            modelo.removeRow(0);
        }
        
        if (Buscar.isEmpty()) {
            sql = cajaModel.leerProducto;
        } else {
           sql = "SELECT CODPROD, DESCRIPCAT, DESCRIPCION, PVENTA, STOCK FROM productos INNER JOIN categoria ON "
                + "productos.IDCATEGORIA = categoria.IDCATEGORIA WHERE DESCRIPCION LIKE '%"+Buscar+"%' OR PRODUCTOS.IDCATEGORIA LIKE '%"+Buscar+"%'";
        }
        try {
            st = BD.conexion().createStatement();
            rs = st.executeQuery(sql);
            
            while (rs.next()) {                
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                
                int stock = Integer.parseInt(rs.getString(5));
                if (stock<=0) {
                    datos[4] = "fuera de stock";
                }else{
                    datos[4] = rs.getString(5);
                }
                
                modelo.addRow(datos);
            }
        } catch (SQLException ex) {
            Logger.getLogger(cajaCont.class.getName()).log(Level.SEVERE, null, ex);
        }
        
                
    }

    @Override
    public void actualizar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar() {
        
    }
    
}
