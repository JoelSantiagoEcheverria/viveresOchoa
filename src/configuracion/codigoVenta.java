
package configuracion;

/**
 *
 * @author Joel
 */
public class codigoVenta {
    String codigo;
    int cont=1;
    public void nVenta(int cod){
        int cd;
        if (cod>=1&&cod<9) {
            cd = cod+cont;
            codigo = "00000000"+cd;
        }
        
        if (cod>=9&&cod<99) {
            cd = cod+cont;
            codigo = "0000000"+cd;
        }
        if (cod>=99&&cod<999) {
            cd= cod+cont;
            codigo="000000"+cd;
        }
        if (cod>=999 && cod<9999) {
            cd= cod+cont;
            codigo="00000"+cd;
        }
        if (cod>=9999 && cod<99999) {
            cd= cod+cont;
            codigo="0000"+cd;
        }
        if (cod>=99999 && cod<999999) {
            cd= cod+cont;
            codigo="000"+cd;
        }
        if (cod>=999999 && cod<9999999) {
            cd= cod+cont;
            codigo="00"+cd;
        }
        if (cod>=9999999 && cod<99999999) {
            cd= cod+cont;
            codigo="0"+cd;
        }
       
        if (cod==100000000) {
            cd= cod+cont;
            codigo=""+cd;
        }
        
        
    }

    @Override
    public String toString() {
        return  codigo;
    }
    
    
}
