/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.Clientes;

import configuracion.estiloHeader;
import configuracion.estiloTabla;
import controlador.clientesCont;
import java.awt.Cursor;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import modelo.clientesModel;
import vistaPrincipal.frmMenu;

/**
 *
 * @author user
 */
public class frmiCatalogo extends javax.swing.JInternalFrame {

    clientesCont cliente = new clientesCont();

    public frmiCatalogo() {
        initComponents();
        System.out.println(this.getSize());
        cliente.buscar("");
        setTitle("CATALOGO DE CLIENTES");
        table_clientes.getTableHeader().setDefaultRenderer(new estiloHeader());
        table_clientes.setDefaultRenderer(Object.class, new estiloTabla());
        table_clientes.setCursor(new Cursor(12));
        table_clientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        limpiar();
        insertaricono();
    }
    void insertaricono(){
        this.setFrameIcon(new ImageIcon(getClass().getResource("/imagenes/catalogoicon.png")));
    }

    public void centrado(JInternalFrame ventana) {
        int x = (frmMenu.escritorio.getWidth() / 2) - (ventana.getWidth() / 2);
        int y = (frmMenu.escritorio.getHeight() / 2) - (ventana.getHeight() / 2);

        if (ventana.isShowing()) {
            ventana.setLocation(x, y);
            ventana.toFront();
        } else {
            frmMenu.escritorio.add(ventana);
            ventana.setLocation(x, y);
            ventana.setVisible(true);
        }

    }

    public void limpiar() {
        int i = table_clientes.getSelectedRow();
        txt_Buscar.setText("");

        if (table_clientes.getSelectedRow() > -1) {
            table_clientes.removeRowSelectionInterval(i, i);
        }
        cliente.buscar("");
    }
    
    
    public static void marcarFila(String id){
        
       // String id = actualizar.txt_ClienteCod.getText();
        for (int i = 0; i < table_clientes.getRowCount(); i++) {
            if (id.equals(table_clientes.getValueAt(i, 0))) {
                table_clientes.setRowSelectionInterval(i, i);
                break;
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    frmiNuevo n;
    frmiActualizar actualizar;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_clientes = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_Buscar = new app.bolivia.swing.JCTextField();
        jPanel3 = new javax.swing.JPanel();
        btn_Nuevo = new javax.swing.JButton();
        btn_Eliminar = new javax.swing.JButton();
        btn_Actualizar = new javax.swing.JButton();
        btn_Salir = new javax.swing.JButton();

        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        table_clientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "C??digo", "C??dula", "Nombre-Apellido", "Direcci??n", "Tel??fono"
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
        jScrollPane1.setViewportView(table_clientes);
        if (table_clientes.getColumnModel().getColumnCount() > 0) {
            table_clientes.getColumnModel().getColumn(0).setResizable(false);
            table_clientes.getColumnModel().getColumn(1).setResizable(false);
            table_clientes.getColumnModel().getColumn(1).setPreferredWidth(150);
            table_clientes.getColumnModel().getColumn(2).setResizable(false);
            table_clientes.getColumnModel().getColumn(2).setPreferredWidth(200);
            table_clientes.getColumnModel().getColumn(3).setResizable(false);
            table_clientes.getColumnModel().getColumn(3).setPreferredWidth(250);
            table_clientes.getColumnModel().getColumn(4).setResizable(false);
            table_clientes.getColumnModel().getColumn(4).setPreferredWidth(120);
        }

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel1.setText("Buscar");

        txt_Buscar.setBackground(new java.awt.Color(204, 204, 255));
        txt_Buscar.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        txt_Buscar.setPlaceholder("Nombre y Apellido");
        txt_Buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_BuscarActionPerformed(evt);
            }
        });
        txt_Buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_BuscarKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(txt_Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(70, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        btn_Nuevo.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        btn_Nuevo.setText("Nuevo");
        btn_Nuevo.setPreferredSize(new java.awt.Dimension(105, 27));
        btn_Nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_NuevoActionPerformed(evt);
            }
        });
        jPanel3.add(btn_Nuevo);

        btn_Eliminar.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        btn_Eliminar.setText("Eliminar");
        btn_Eliminar.setPreferredSize(new java.awt.Dimension(105, 27));
        btn_Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_EliminarActionPerformed(evt);
            }
        });
        jPanel3.add(btn_Eliminar);

        btn_Actualizar.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        btn_Actualizar.setText("Actualizar");
        btn_Actualizar.setPreferredSize(new java.awt.Dimension(105, 29));
        btn_Actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ActualizarActionPerformed(evt);
            }
        });
        jPanel3.add(btn_Actualizar);

        btn_Salir.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        btn_Salir.setText("Salir");
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 343, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(197, 197, 197)
                        .addComponent(btn_Salir))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_Salir)
                .addContainerGap())
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

    private void btn_NuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_NuevoActionPerformed
        if (!(n instanceof frmiNuevo)) {//si no existe una instancia o no esta abierto
            n = new frmiNuevo();        //entoces que me instancie
        }
        centrado(n);                    //caso contrario solo me centre la ventana
        limpiar();

    }//GEN-LAST:event_btn_NuevoActionPerformed

    private void btn_SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SalirActionPerformed
        this.doDefaultCloseAction();
        limpiar();

    }//GEN-LAST:event_btn_SalirActionPerformed
    boolean estado = false;
    private void btn_ActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ActualizarActionPerformed
        
        if (table_clientes.getSelectedRow() > -1) {
            if (!(actualizar instanceof frmiActualizar)) {
                actualizar = new frmiActualizar();
                actualizar.cargarDatos();
                actualizar.enviarDatos();

            }
            
            centrado(actualizar);
        } else {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un registro", "Error", JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_btn_ActualizarActionPerformed
    public static String codigo;
    public static String nombre;
    private void btn_EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EliminarActionPerformed

        if (table_clientes.getSelectedRow() != -1) {
            int fila = table_clientes.getSelectedRow();
            nombre = table_clientes.getValueAt(fila, 2).toString();
            int opcion = JOptionPane.showConfirmDialog(null, "Est?? seguro que quiere eliminar a: " + nombre, "Eliminar", JOptionPane.OK_OPTION);
            if (opcion == JOptionPane.OK_OPTION) {

                codigo = table_clientes.getValueAt(fila, 0).toString();
                cliente.eliminar();
              
                limpiar();
                clientesModel.idCliente();
               
            }

        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un registro", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btn_EliminarActionPerformed

    private void txt_BuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_BuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_BuscarActionPerformed

    private void txt_BuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_BuscarKeyReleased
        cliente.buscar(txt_Buscar.getText());
        txt_Buscar.setText(txt_Buscar.getText().toUpperCase());

    }//GEN-LAST:event_txt_BuscarKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Actualizar;
    private javax.swing.JButton btn_Eliminar;
    private javax.swing.JButton btn_Nuevo;
    private javax.swing.JButton btn_Salir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable table_clientes;
    private app.bolivia.swing.JCTextField txt_Buscar;
    // End of variables declaration//GEN-END:variables
}
