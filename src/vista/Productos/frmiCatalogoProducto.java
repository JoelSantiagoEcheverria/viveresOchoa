/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.Productos;

import configuracion.estiloHeader;
import configuracion.estiloTabla;
import controlador.categoriaCont;
import controlador.productosCont;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.text.DecimalFormat;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;
import static vistaPrincipal.frmMenu.escritorio;

/**
 *
 * @author user
 */
public class frmiCatalogoProducto extends javax.swing.JInternalFrame {

    /**
     * Creates new form frmi_CatalogoProducto
     */
    productosCont prod = new productosCont();

    public frmiCatalogoProducto() {
        initComponents();
        insertarIcono();
        this.setTitle("CATALOGO DE PRODUCTOS");
        table_Producto.setDefaultRenderer(Object.class, new estiloTabla());
        table_Producto.getTableHeader().setDefaultRenderer(new estiloHeader());
//        table_Producto.prepareRenderer(table_Producto.getDefaultRenderer(new tablaStock()), WIDTH, WIDTH);
        categoriaCont.laCategoria(cbo_TipoProducto);
        prod.buscar("");
        cbo_TipoProducto.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie) {
                if (cbo_TipoProducto.getSelectedIndex() > 0) {
                    prod.buscar(codigos());
                } else {
                    prod.buscar("");
                }
                giveFormat();
            }
        });
        giveFormat();
        table_Producto.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table_Producto.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    
    void insertarIcono(){
        this.setFrameIcon(new ImageIcon(getClass().getResource("/imagenes/catalogoicon.png")));
    }

    void centrar(JInternalFrame ventana) {
        int x = (escritorio.getWidth() / 2) - (ventana.getWidth() / 2);
        int y = (escritorio.getHeight() / 2) - (ventana.getHeight() / 2);

        if (ventana.isVisible()) {
            ventana.setLocation(x, y);
            deseleccionar();
            ventana.toFront();
        } else {
            escritorio.add(ventana);
            ventana.setLocation(x, y);
            ventana.setVisible(true);
            deseleccionar();
        }
    }

    public static String codigos() {
        String cbo = "";
        if (cbo_TipoProducto.getSelectedIndex() > 0) {
            cbo = cbo_TipoProducto.getItemAt(cbo_TipoProducto.getSelectedIndex()).getIdCategoria();
            
        }
        return cbo;
    }

    public static void seleccionar(String id) {

        for (int i = 0; i < table_Producto.getRowCount(); i++) {
            if (id.equals(table_Producto.getValueAt(i, 0))) {
                table_Producto.setRowSelectionInterval(i, i);
            }
        }
    }

    public void deseleccionar() {
        for (int i = 0; i < table_Producto.getRowCount(); i++) {
            if (table_Producto.isRowSelected(i)) {
                table_Producto.removeRowSelectionInterval(i, i);
            }
        }

    }

    public static void giveFormat() {
        DecimalFormat dr = new DecimalFormat("0.00");
        for (int i = 0; i < table_Producto.getRowCount(); i++) {
            String da = table_Producto.getValueAt(i, 3).toString();
            float sf = Float.parseFloat(da);
            table_Producto.setValueAt(dr.format(sf), i, 3);
        }

        for (int i = 0; i < table_Producto.getRowCount(); i++) {
            String d = table_Producto.getValueAt(i, 4).toString();
            float s = Float.parseFloat(d);
            table_Producto.setValueAt(dr.format(s), i, 4);
        }

    }

    class tablaStock extends JTable {

        @Override
        public Component prepareRenderer(TableCellRenderer tcr, int row, int colum) {
            Component componente = super.prepareRenderer(tcr, row, colum); //To change body of generated methods, choose Tools | Templates.

            if (getValueAt(row, 5).getClass().equals(String.class)) {
                int valor = Integer.parseInt(getValueAt(row, 5).toString());

                if (valor <= 0 && colum == 5) {
                    componente.setBackground(Color.gray);
                    componente.setForeground(Color.WHITE);
                }

            }

            return componente;
        }

    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    frmiNuevoProductos productos;
    frmiActualizarProductos actualizar;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_Producto = new tablaStock();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_Buscar = new app.bolivia.swing.JCTextField();
        jPanel3 = new javax.swing.JPanel();
        btn_Nuevo = new javax.swing.JButton();
        btn_Eliminar = new javax.swing.JButton();
        btn_Actualizar = new javax.swing.JButton();
        cbo_TipoProducto = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        btn_Salir = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        table_Producto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "C??DIGO", "DESCRIPCI??N", "MARCA", "PRECIO DE COMPRA", "PRECIO DE VENTA", "STOCK", "PROVEEDOR"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_Producto.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(table_Producto);
        if (table_Producto.getColumnModel().getColumnCount() > 0) {
            table_Producto.getColumnModel().getColumn(0).setResizable(false);
            table_Producto.getColumnModel().getColumn(0).setPreferredWidth(50);
            table_Producto.getColumnModel().getColumn(1).setResizable(false);
            table_Producto.getColumnModel().getColumn(1).setPreferredWidth(180);
            table_Producto.getColumnModel().getColumn(2).setResizable(false);
            table_Producto.getColumnModel().getColumn(2).setPreferredWidth(100);
            table_Producto.getColumnModel().getColumn(3).setResizable(false);
            table_Producto.getColumnModel().getColumn(3).setPreferredWidth(100);
            table_Producto.getColumnModel().getColumn(4).setResizable(false);
            table_Producto.getColumnModel().getColumn(4).setPreferredWidth(100);
            table_Producto.getColumnModel().getColumn(5).setResizable(false);
            table_Producto.getColumnModel().getColumn(5).setPreferredWidth(25);
            table_Producto.getColumnModel().getColumn(6).setResizable(false);
            table_Producto.getColumnModel().getColumn(6).setPreferredWidth(200);
        }

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel1.setText("Buscar");

        txt_Buscar.setBackground(new java.awt.Color(204, 204, 255));
        txt_Buscar.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        txt_Buscar.setPlaceholder("Descripci??n");
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
                .addContainerGap(48, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
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

        cbo_TipoProducto.setFont(new java.awt.Font("Bookman Old Style", 0, 14)); // NOI18N
        cbo_TipoProducto.setPreferredSize(new java.awt.Dimension(33, 29));

        jLabel2.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel2.setText("Buscar Categor??a:");

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(cbo_TipoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(339, 339, 339)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 274, Short.MAX_VALUE)
                .addComponent(btn_Salir)
                .addGap(20, 20, 20))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbo_TipoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btn_Salir)
                        .addGap(21, 21, 21))))
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
        if (!(productos instanceof frmiNuevoProductos)) {
            productos = new frmiNuevoProductos();

        }
        centrar(productos);
    }//GEN-LAST:event_btn_NuevoActionPerformed

    private void btn_SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SalirActionPerformed
        this.doDefaultCloseAction();
        deseleccionar();
        cbo_TipoProducto.setSelectedIndex(0);
        txt_Buscar.setText("");
    }//GEN-LAST:event_btn_SalirActionPerformed
    public static String codigo;
    public static String descripcion;
    private void btn_EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EliminarActionPerformed

        if (table_Producto.getSelectedRow() > -1) {
            int fila = table_Producto.getSelectedRow();
            codigo = table_Producto.getValueAt(fila, 0).toString();
            descripcion = table_Producto.getValueAt(fila, 1).toString();
            int op = JOptionPane.showConfirmDialog(null, "Esta seguro que quiere eliminar: " + descripcion, "ELIMINAR", JOptionPane.OK_OPTION);
            if (op == JOptionPane.OK_OPTION) {
                prod.eliminar();
                prod.buscar("");
                giveFormat();
            }

        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un registro", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_EliminarActionPerformed

    private void btn_ActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ActualizarActionPerformed
        if (table_Producto.getSelectedRow() != -1) {
            if (!(actualizar instanceof frmiActualizarProductos)) {
                actualizar = new frmiActualizarProductos();
                actualizar.cargar();
                actualizar.enviarDatos();

            }
            centrar(actualizar);
        } else {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un registro", "Error", JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_btn_ActualizarActionPerformed

    private void txt_BuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_BuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_BuscarActionPerformed

    private void txt_BuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_BuscarKeyReleased
        prod.buscar(txt_Buscar.getText());
        txt_Buscar.setText(txt_Buscar.getText().toUpperCase());
        giveFormat();
    }//GEN-LAST:event_txt_BuscarKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Actualizar;
    private javax.swing.JButton btn_Eliminar;
    private javax.swing.JButton btn_Nuevo;
    private javax.swing.JButton btn_Salir;
    public static javax.swing.JComboBox<categoriaCont> cbo_TipoProducto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable table_Producto;
    private app.bolivia.swing.JCTextField txt_Buscar;
    // End of variables declaration//GEN-END:variables
}
