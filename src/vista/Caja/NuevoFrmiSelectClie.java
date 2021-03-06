/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.Caja;

import configuracion.BD;
import controlador.cajaCont;
import controlador.detalleVentasCont;
import controlador.productosCont;
import controlador.ventasCont;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.cajaModel;
import modelo.clientesModel;
import vista.Productos.frmiCatalogoProducto;
import vistaPrincipal.frmMenu;

/**
 *
 * @author user
 */
public class NuevoFrmiSelectClie extends javax.swing.JInternalFrame {

    /**
     * Creates new form pb
     */
    public NuevoFrmiSelectClie() {
        initComponents();
        buscar("");
         this.setTitle("SELECCIONAR CLIENTE");
    }
    
     public void centrarVentana(JInternalFrame ventana){
        int x = (vistaPrincipal.frmMenu.escritorio.getWidth()/2)-(ventana.getWidth()/2);
        int y = (vistaPrincipal.frmMenu.escritorio.getHeight()/2)-(ventana.getHeight()/2);
        
        if (ventana.isVisible()) {
            ventana.setLocation(x, y);
            ventana.toFront();
        }else{
            frmMenu.escritorio.add(ventana);
            ventana.setLocation(x, y);
            ventana.setVisible(true);
        }
        
    }
    public static void probar(){
       
        DefaultTableModel modelo = (DefaultTableModel) vista.Caja.NuevoFrmiSelectClie.table_clientes.getModel();

        
          while(modelo.getRowCount()>0){
            modelo.removeRow(0);
           
        }

    }
    
    

    public static void buscar(String Busca) {
    Statement st;
    ResultSet rs;
        DefaultTableModel modelo = (DefaultTableModel) table_clientes.getModel();

        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }

        String sql;

        if (Busca.isEmpty()) {
            sql = clientesModel.leer;
        } else {
            sql = "SELECT * FROM CLIENTES WHERE NOMCLIE LIKE '%" + Busca + "%' or CEDCLIE LIKE '%" + Busca + "%' ORDER BY NOMCLIE";
        }

        String[] datos = new String[5];

        try {
            st = BD.conexion().createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                datos[0] = rs.getString("CODCLIE");
                datos[1] = rs.getString("CEDCLIE");
                datos[2] = rs.getString("NOMCLIE");
                datos[3] = rs.getString("DIRECCLIE");
                datos[4] = rs.getString("TELEFCLIE");

                modelo.addRow(datos);

            }
            
        } catch (SQLException ex) {
//            Logger.getLogger(clientesCont.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public static String cod;
    public static String cantidad;

    detalleVentasCont dvc = new detalleVentasCont();
    productosCont prodCont = new productosCont();
    public void productosVender() {
        for (int i = 0; i < frmiCobrar.table.getRowCount(); i++) {
            cod = frmiCobrar.table.getValueAt(i, 0).toString();
            cantidad = frmiCobrar.table.getValueAt(i, 4).toString();
            prodCont.actualizarStockcc();
            dvc.guardar();

        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_clientes = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        txt_Buscar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btn_AniadirClie = new javax.swing.JButton();
        btn_Salir = new javax.swing.JButton();

        table_clientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "C??digo", "C??dula", "Nombre/Apellido", "Direcci??n", "Tel??fono"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_clientes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        table_clientes.getTableHeader().setReorderingAllowed(false);
        table_clientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_clientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_clientes);

        txt_Buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_BuscarKeyReleased(evt);
            }
        });

        jLabel1.setText("Buscar");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addContainerGap(59, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        btn_AniadirClie.setText("+Add Cliente");
        btn_AniadirClie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AniadirClieActionPerformed(evt);
            }
        });
        jPanel3.add(btn_AniadirClie);

        btn_Salir.setText("Cancelar");
        btn_Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(125, 125, 125))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 596, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(98, 98, 98)
                        .addComponent(btn_Salir)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_Salir)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
cajaCont productos = new cajaCont();
    private void table_clientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_clientesMouseClicked

        frmiCobrar.siCliente = true;

        ventasCont ventas = new ventasCont();
        ventas.guardar();

        productosVender();
        cajaModel.codigoVent();

        DefaultTableModel model = (DefaultTableModel) frmiCobrar.table.getModel();
        while (model.getRowCount() > 0) {
            model.removeRow(0);
        }

        try {
            dvc.buscar("");

        } catch (Exception e) {
        }

        productos.buscar("");

        try {
            prodCont.buscar("");
            frmiCatalogoProducto.giveFormat();
        } catch (Exception e) {

        }

        this.doDefaultCloseAction();
    }//GEN-LAST:event_table_clientesMouseClicked

    private void txt_BuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_BuscarKeyReleased

        // txt_Buscar.setText(txt_Buscar.getText().toUpperCase());
    }//GEN-LAST:event_txt_BuscarKeyReleased
vista.Clientes.frmiNuevo nuevoCliente;
    private void btn_AniadirClieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AniadirClieActionPerformed
        if (!(nuevoCliente instanceof vista.Clientes.frmiNuevo)) {
            nuevoCliente = new vista.Clientes.frmiNuevo();
        }
        centrarVentana(nuevoCliente);
        
    }//GEN-LAST:event_btn_AniadirClieActionPerformed

    private void btn_SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SalirActionPerformed
        this.doDefaultCloseAction();
    }//GEN-LAST:event_btn_SalirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_AniadirClie;
    private javax.swing.JButton btn_Salir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable table_clientes;
    private javax.swing.JTextField txt_Buscar;
    // End of variables declaration//GEN-END:variables
}
