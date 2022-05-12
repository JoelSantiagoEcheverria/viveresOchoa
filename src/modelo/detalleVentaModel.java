
package modelo;


public class detalleVentaModel {
    
  public static String ingresoDetalle = "INSERT INTO detalle_ventas(CODPROD,CODCLIE,CODUSU,NUMVENT,CANTIDAD)VALUES(?,?,?,?,?)";
  
  public static String leerVenta = "SELECT VENTAS.NUMVENT, NOMCLIE, NOMBRE, FECHA, TOTAL "
          + "FROM detalle_ventas LEFT JOIN clientes ON detalle_ventas.CODCLIE = clientes.CODCLIE "
          + "INNER JOIN USUARIOS ON detalle_ventas.CODUSU = usuarios.CODUSU "
          + "INNER JOIN VENTAS ON detalle_ventas.NUMVENT = ventas.NUMVENT "
          + "UNION SELECT VENTAS.NUMVENT, NOMCLIE, NOMBRE, FECHA, TOTAL FROM detalle_ventas "
          + "LEFT JOIN clientes ON detalle_ventas.CODCLIE = clientes.CODCLIE I"
          + "NNER JOIN USUARIOS ON detalle_ventas.CODUSU = usuarios.CODUSU "
          + "INNER JOIN VENTAS ON detalle_ventas.NUMVENT = ventas.NUMVENT ";
  
  public static String buscarFecha = "SELECT VENTAS.NUMVENT, NOMCLIE, NOMBRE, FECHA, TOTAL " +
"          FROM detalle_ventas LEFT JOIN clientes ON detalle_ventas.CODCLIE = clientes.CODCLIE" +
"          INNER JOIN USUARIOS ON detalle_ventas.CODUSU = usuarios.CODUSU " +
"          INNER JOIN VENTAS ON detalle_ventas.NUMVENT = ventas.NUMVENT WHERE ventas.FECHA = ? " +
"          UNION SELECT VENTAS.NUMVENT, NOMCLIE, NOMBRE, FECHA, TOTAL FROM detalle_ventas " +
"          LEFT JOIN clientes ON detalle_ventas.CODCLIE = clientes.CODCLIE " +
"          INNER JOIN USUARIOS ON detalle_ventas.CODUSU = usuarios.CODUSU " +
"          INNER JOIN VENTAS ON detalle_ventas.NUMVENT = ventas.NUMVENT WHERE ventas.FECHA = ? ";
  
  public static String hoy = "SELECT productos.CODPROD, DESCRIPCION, FECHA, CANTIDAD, PVENTA FROM detalle_ventas" +
"          INNER JOIN productos ON detalle_ventas.CODPROD = productos.CODPROD " +
"          INNER JOIN VENTAS ON detalle_ventas.NUMVENT = ventas.NUMVENT WHERE ventas.FECHA = ? ORDER BY descripcion";
}
