
package configuracion;


import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Joel
 */
public class estiloHeader implements TableCellRenderer {

    JComponent componente;
    @Override
    public JComponent getTableCellRendererComponent(JTable jtable, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        
        if (value instanceof String ) {
            
            componente = new JLabel((String)value);
            
            ((JLabel)componente).setHorizontalAlignment(0);
            ((JLabel)componente).setSize(30, componente.getWidth());//dibuja los campos de la tabla
            ((JLabel)componente).setPreferredSize(new Dimension(3,componente.getWidth()));//establec tamaño consigue le ancho de mi texto de campo
 
        }
        
        componente.setEnabled(true);
        componente.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 2, new Color(0,0,0)));
        componente.setOpaque(true);//ocultar el color por defecto
        componente.setBackground(new Color(79,80,82));//color de fondo
        componente.setForeground(Color.WHITE);//color de las letras
       
        return componente;
    }
    
}
