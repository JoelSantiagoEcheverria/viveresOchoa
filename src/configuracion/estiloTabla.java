
package configuracion;

import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import vista.Productos.frmiCatalogoProducto;

public class estiloTabla extends DefaultTableCellRenderer{
    
    Component componente;


    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        componente = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); //To change body of generated methods, choose Tools | Templates.
        this.setHorizontalAlignment(0);//centrar mis datos
        setBorder(BorderFactory.createMatteBorder(0,1,1,0,new Color(0,0,0)));//establecer color y grosor de mis bordes
        
        if (row%2 == 0) {
            componente.setBackground(new Color(249,236,231));
            componente.setForeground(Color.BLACK);
            
        } else {
            componente.setBackground(Color.WHITE);
            componente.setForeground(Color.BLACK);
        }
        if (isSelected) {
            componente.setBackground(new Color(221,49,77));
            componente.setForeground(Color.WHITE);
        }
      
        
        
        return componente;
    }
    
    
}
