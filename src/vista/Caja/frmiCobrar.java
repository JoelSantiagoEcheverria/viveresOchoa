/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.Caja;

import app.bolivia.swing.JCTextField;
import configuracion.BD;
import configuracion.estiloHeader;
import configuracion.estiloTabla;
import controlador.cajaCont;
import controlador.detalleVentasCont;
import controlador.productosCont;
import controlador.ventasCont;
import java.awt.Cursor;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import modelo.cajaModel;
import vista.Clientes.frmiCatalogo;
import vista.Ventas.frmiCatalogoVentas;
import vistaPrincipal.frmMenu;
import static vistaPrincipal.frmMenu.escritorio;
//import static vistaPrincipal frmMenu.escritorio;

/**
 *
 * @author user
 */
public class frmiCobrar extends javax.swing.JInternalFrame {

    /**
     * Creates new form frmiCobrar
     */
    cajaCont producto = new cajaCont();

    public frmiCobrar() {
        initComponents();
        setTitle("CAJA DE COBRO");
        this.setFrameIcon(new ImageIcon(getClass().getResource("/imagenes/cajacobrar.jpg")));
        table.getTableHeader().setDefaultRenderer(new estiloHeader());
        table.setDefaultRenderer(Object.class, new estiloTabla());
        lbl_InsertarFecha.setText(fechaActual());
        cajaModel.codigoVent();
        btn_Anular.setVisible(false);
        btn_Facturar.setVisible(false);
        table.setCursor(new Cursor(Cursor.HAND_CURSOR));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    }

    public void centrar(JInternalFrame ventana) {
        int x = (escritorio.getWidth() / 2) - (ventana.getWidth() / 2);
        int y = (escritorio.getHeight() / 2) - (ventana.getHeight() / 2);

        if (ventana.isVisible()) {
            ventana.setLocation(x, y);
            ventana.toFront();

        } else {
            escritorio.add(ventana);
            ventana.setVisible(true);
            ventana.setLocation(x, y);
            frmiElegirProd.giveFormat();
        }

    }

    void operacion() {
        DecimalFormat df = new DecimalFormat("0.00");
        float total = Float.parseFloat(txt_Total.getText().replaceAll("[^0-9]", "."));
        float recibi = Float.parseFloat(txt_Recibi.getText().replaceAll("[^0-9]", "."));

        if (recibi < total) {
            JOptionPane.showMessageDialog(this, "Saldo insuficiente para realizar la operación", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            float oper = recibi - total;
            txt_DarCambio.setText(df.format(oper));
        }
    }

    public static String fechaActual() {
        Date fecha = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/YYYY");
        return formato.format(fecha);
    }

    public boolean isNum(String n) {
        int valor = Integer.parseInt(n);
        try {
            if (valor >= 0) {
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
            return true;
        }

    }

    public boolean hayVentas() {
        int fila = table.getRowCount();

        if (fila > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static String cod;
    public static String cantidad;
    detalleVentasCont detalleVentaC = new detalleVentasCont();

    productosCont prodCont = new productosCont();

    public void productosVender() {
        for (int i = 0; i < table.getRowCount(); i++) {
            cod = table.getValueAt(i, 0).toString();
            cantidad = table.getValueAt(i, 4).toString();
            prodCont.actualizarStocksc();
            detalleVentaC.guardar();

        }
    }

    public static void stkActualizado() {
        String codigoC = "";
        String cantC = "";

        String codigoE = "";
        String cantE = "";
        int res = 0;

        for (int i = 0; i < table.getRowCount(); i++) {
            codigoC += table.getValueAt(i, 0).toString() + ",";
            cantC += table.getValueAt(i, 4).toString() + ",";

        }
        for (int i = 0; i < frmiElegirProd.table.getRowCount(); i++) {
            codigoE += frmiElegirProd.table.getValueAt(i, 0).toString() + ",";
            cantE += frmiElegirProd.table.getValueAt(i, 4).toString() + ",";
        }

        for (int i = 0; i < cantE.split(",").length; i++) {
            for (int j = 0; j < cantC.split(",").length; j++) {
                if (codigoE.split(",")[i].equals(codigoC.split(",")[j])) {
                    int canE = Integer.parseInt(cantE.split(",")[i]);
                    int canC = Integer.parseInt(cantC.split(",")[j]);
                    res = canE - canC;
                    frmiElegirProd.table.setValueAt(res, i, 4);

                    if (res == 0) {
                        frmiElegirProd.table.setValueAt("fuera de stock", i, 4);
                    }
                }

            }

        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    frmiElegirProd elijeProd;
    frmiSelectClie selectClie;

    public static boolean siCliente;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btn_ElegirProd = new javax.swing.JButton();
        lbl_InsertarFecha = new javax.swing.JLabel();
        txt_DarCambio = new app.bolivia.swing.JCTextField();
        txt_Recibi = new app.bolivia.swing.JCTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        txt_Total = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        btn_Calcular = new javax.swing.JButton();
        btn_Vender = new javax.swing.JButton();
        btn_Eliminar = new javax.swing.JButton();
        btn_Anular = new javax.swing.JButton();
        btn_Facturar = new javax.swing.JButton();
        btn_Salir = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lbl_NumeroVenta = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_ElegirProd.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        btn_ElegirProd.setText("Elegir Producto");
        btn_ElegirProd.setPreferredSize(new java.awt.Dimension(155, 29));
        btn_ElegirProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ElegirProdActionPerformed(evt);
            }
        });
        jPanel2.add(btn_ElegirProd, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, -1));

        lbl_InsertarFecha.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_InsertarFecha.setText("Fecha");
        jPanel2.add(lbl_InsertarFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 0, 90, 50));

        txt_DarCambio.setEditable(false);
        txt_DarCambio.setBackground(new java.awt.Color(255, 204, 204));
        txt_DarCambio.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        txt_DarCambio.setPlaceholder("Dar Cambio");
        jPanel2.add(txt_DarCambio, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 10, 110, 30));

        txt_Recibi.setBackground(new java.awt.Color(204, 204, 255));
        txt_Recibi.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        txt_Recibi.setPlaceholder("Recibí");
        txt_Recibi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_RecibiActionPerformed(evt);
            }
        });
        jPanel2.add(txt_Recibi, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, 110, 30));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        table.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Tipo Producto", "Descripción", "Precio de Venta", "Cantidad", "Importe"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setResizable(false);
            table.getColumnModel().getColumn(0).setPreferredWidth(50);
            table.getColumnModel().getColumn(1).setResizable(false);
            table.getColumnModel().getColumn(1).setPreferredWidth(100);
            table.getColumnModel().getColumn(2).setResizable(false);
            table.getColumnModel().getColumn(2).setPreferredWidth(200);
            table.getColumnModel().getColumn(3).setResizable(false);
            table.getColumnModel().getColumn(4).setResizable(false);
            table.getColumnModel().getColumn(4).setPreferredWidth(40);
            table.getColumnModel().getColumn(5).setResizable(false);
            table.getColumnModel().getColumn(5).setPreferredWidth(40);
        }

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 12, 660, 220));

        txt_Total.setEditable(false);
        txt_Total.setBackground(new java.awt.Color(0, 0, 0));
        txt_Total.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        txt_Total.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(txt_Total, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 260, 100, -1));

        jLabel2.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel2.setText("Total:");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(496, 260, 50, 20));

        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        btn_Calcular.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        btn_Calcular.setText("Calcular");
        btn_Calcular.setPreferredSize(new java.awt.Dimension(105, 29));
        btn_Calcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CalcularActionPerformed(evt);
            }
        });

        btn_Vender.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        btn_Vender.setText("Vender");
        btn_Vender.setPreferredSize(new java.awt.Dimension(105, 29));
        btn_Vender.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_VenderMouseClicked(evt);
            }
        });
        btn_Vender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_VenderActionPerformed(evt);
            }
        });

        btn_Eliminar.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        btn_Eliminar.setText("Eliminar");
        btn_Eliminar.setPreferredSize(new java.awt.Dimension(95, 29));
        btn_Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_EliminarActionPerformed(evt);
            }
        });

        btn_Anular.setText("Anular");
        btn_Anular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AnularActionPerformed(evt);
            }
        });

        btn_Facturar.setText("Facturar");

        btn_Salir.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        btn_Salir.setText("Salir");
        btn_Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_Facturar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_Salir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_Anular, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_Eliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_Vender, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_Calcular, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_Calcular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_Vender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_Anular)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_Facturar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_Salir)
                .addContainerGap())
        );

        jPanel4.add(jPanel5, java.awt.BorderLayout.CENTER);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel1.setText("Número de Venta:");

        lbl_NumeroVenta.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        lbl_NumeroVenta.setText("Numero");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(26, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_NumeroVenta)
                .addGap(48, 48, 48))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(lbl_NumeroVenta)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        jPanel4.add(jPanel6, java.awt.BorderLayout.PAGE_START);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 687, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 631, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ElegirProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ElegirProdActionPerformed
        elijeProd = new frmiElegirProd();
        escritorio.add(elijeProd);
        elijeProd.setVisible(true);
        elijeProd.toFront();
        if (table.getRowCount() > 0) {
            stkActualizado();
        }
        btn_ElegirProd.setEnabled(false);

    }//GEN-LAST:event_btn_ElegirProdActionPerformed

    private void btn_SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SalirActionPerformed
        this.doDefaultCloseAction();
        cajaModel.codigoVent();
        elijeProd.doDefaultCloseAction();
        btn_ElegirProd.setEnabled(true);
    }//GEN-LAST:event_btn_SalirActionPerformed

    private void btn_VenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_VenderActionPerformed

        if (hayVentas()) {
            int aceptar = JOptionPane.showConfirmDialog(this, "¿DESEA ELEGIR UN CLIENTE?");

            if (aceptar == JOptionPane.OK_OPTION) {
                try {
                    if (!(selectClie instanceof frmiSelectClie)) {
                        selectClie = new frmiSelectClie();
                    }
                    centrar(selectClie);

                } catch (Exception e) {
                }

            } else if (aceptar == JOptionPane.NO_OPTION) {
                siCliente = false;
                ventasCont ventas = new ventasCont();
                ventas.guardar();
                productosVender();

                cajaModel.codigoVent();

                DefaultTableModel model = (DefaultTableModel) table.getModel();

                while (model.getRowCount() > 0) {
                    model.removeRow(0);
                }
                try {
                    detalleVentaC.buscar("");
                    prodCont.buscar("");
                    vista.Productos.frmiCatalogoProducto.giveFormat();
                } catch (Exception e) {

                }
                try {
                    frmiCierreCaja.ejecutar();
                } catch (Exception e) {
                }
                txt_Total.setText("");

            }
        } else {
            JOptionPane.showMessageDialog(this, "NO HAY PRODUCTOS EN EL CAMPO", "ERROR", JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_btn_VenderActionPerformed

    private void btn_CalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CalcularActionPerformed
        if (!(txt_Recibi.getText().isEmpty() || txt_Total.getText().isEmpty())) {
            operacion();
        } else {
            JOptionPane.showMessageDialog(this, "Imposible Resolver Operación", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_CalcularActionPerformed

    private void btn_VenderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_VenderMouseClicked

    }//GEN-LAST:event_btn_VenderMouseClicked

    private void btn_AnularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AnularActionPerformed


    }//GEN-LAST:event_btn_AnularActionPerformed

    private void btn_EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EliminarActionPerformed
        int fila = table.getSelectedRow();
        int result = table.getRowCount();
        DefaultTableModel modelo = (DefaultTableModel) table.getModel();
        if (result != 0) {
            if (fila != -1) {
                modelo.removeRow(fila);
                producto.buscar("");
                stkActualizado();
                frmiElegirProd.giveFormat();
                frmiElegirProd.sumarPrecios();
                if (!(txt_Recibi.getText().isEmpty() || txt_Total.getText().isEmpty())) {
                    operacion();
                }
                
                if (table.getRowCount() == 0) {
                    txt_DarCambio.setText("");
                    txt_Recibi.setText("");
                    txt_Total.setText("");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Debe elegir un producto!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "NO HAY PRODUCTOS EN EL CAMPO", "ERROR", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btn_EliminarActionPerformed

    private void txt_RecibiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_RecibiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_RecibiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Anular;
    private javax.swing.JButton btn_Calcular;
    public static javax.swing.JButton btn_ElegirProd;
    private javax.swing.JButton btn_Eliminar;
    private javax.swing.JButton btn_Facturar;
    private javax.swing.JButton btn_Salir;
    private javax.swing.JButton btn_Vender;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JLabel lbl_InsertarFecha;
    public static javax.swing.JLabel lbl_NumeroVenta;
    public static javax.swing.JTable table;
    public static app.bolivia.swing.JCTextField txt_DarCambio;
    public static app.bolivia.swing.JCTextField txt_Recibi;
    public static javax.swing.JTextField txt_Total;
    // End of variables declaration//GEN-END:variables
}
