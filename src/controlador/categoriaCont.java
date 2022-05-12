
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
import modelo.categoriaModel;

/**
 *
 * @author Joel
 */
public class categoriaCont implements CRUD{
    private String idCategoria;
    private String descripCat;

    public categoriaCont(String idCategoria, String descripCat) {
        this.idCategoria = idCategoria;
        this.descripCat = descripCat;
    }

    public categoriaCont(String descripCat) {
        this.descripCat = descripCat;
    }

    public categoriaCont() {
    }

    public String getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(String idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getDescripCat() {
        return descripCat;
    }

    public void setDescripCat(String descripCat) {
        this.descripCat = descripCat;
    }
    
    PreparedStatement ps;
    

    @Override
    public void guardar() {
        String sql = categoriaModel.registrar;
        
        try {
            ps = BD.conexion().prepareStatement(sql);
            ps.setString(1, getIdCategoria());
            ps.setString(2, getDescripCat());
            int registrado = ps.executeUpdate();
            if (registrado != 0) {
                JOptionPane.showMessageDialog(null, "Registro guardado");
            }
        } catch (SQLException ex) {
            Logger.getLogger(categoriaCont.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void buscar(String Buscar) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static  void laCategoria(JComboBox combo){
        String sql ="SELECT * FROM CATEGORIA ORDER BY DESCRIPCAT";
        
        Statement st;
        ResultSet rs;
        try {
            st = BD.conexion().createStatement();
            rs = st.executeQuery(sql);
            combo.addItem("Seleccione...");
            while(rs.next()){
                 combo.addItem(new categoriaCont(rs.getString(1),rs.getString(2)));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(productosCont.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @Override
    public String toString() {
        return  descripCat ;
    }

    
    
}
