
package controlador;

/**
 *
 * @author Joel
 */
public abstract class Persona implements CRUD{
    private String codigo;
    private String cedula;
    private String nombreApel;
    private String direccion;
    private String telefono;

    public Persona(String codigo, String cedula, String nombreApel, String direccion, String telefono) {
        this.codigo = codigo;
        this.cedula = cedula;
        this.nombreApel = nombreApel;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public Persona(String codigo, String nombreApel) {
        this.codigo = codigo;
        this.nombreApel = nombreApel;
    }

  

    public Persona() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombreApel() {
        return nombreApel;
    }

    public void setNombreApel(String nombreApel) {
        this.nombreApel = nombreApel;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    
    
}
