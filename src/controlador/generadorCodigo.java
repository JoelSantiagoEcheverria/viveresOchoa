
package controlador;

import javax.swing.JOptionPane;

/**
 *
 * @author Joel
 */
public  class generadorCodigo {
  
    public String  codigo;
    public  void codigo(int cod){
        int contador =1;
        int sum;
        
        if (cod > 0 && cod<9 ) {
            sum = cod+contador;
            codigo = "000"+sum;
        }
        if (cod >=9 && cod<99 ) {
            sum = cod+contador;
            codigo = "00"+sum;
        }
        if (cod >= 99 && cod<999 ) {
            sum = cod+contador;
            codigo = "0"+sum;
        }
        if (cod == 999 ) {
            sum = cod+contador;
            codigo = ""+sum;
        }
    }

    @Override
    public String toString() {
        return codigo;
    }
    
    
}
