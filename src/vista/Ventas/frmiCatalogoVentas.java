/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.Ventas;

import configuracion.estiloHeader;
import configuracion.estiloTabla;
import controlador.detalleVentasCont;
import controlador.ventasCont;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.cajaModel;
import vista.Caja.frmiCierreCaja;

/**
 *
 * @author user
 */
public class frmiCatalogoVentas extends javax.swing.JInternalFrame {

    /**
     * Creates new form CatalogoVentas
     */
    detalleVentasCont detalleV = new detalleVentasCont();

    public frmiCatalogoVentas() {
        initComponents();
        this.setTitle("CATALOGO DE VENTAS");
        table.getTableHeader().setDefaultRenderer(new estiloHeader());
        table.setDefaultRenderer(Object.class, new estiloTabla());
        detalleV.buscar("");
        this.setFrameIcon(new ImageIcon(getClass().getResource("/imagenes/catalogoicon.png")));
    }

    public void centrarVentana(JInternalFrame ventana){
        int x = (vistaPrincipal.frmMenu.escritorio.getWidth()/2)-(ventana.getWidth()/2);
        int y = (vistaPrincipal.frmMenu.escritorio.getHeight()/2)-(ventana.getHeight()/2);
        
        if (ventana.isVisible()) {
            ventana.setLocation(x, y);
            ventana.toFront();
        }else{
            vistaPrincipal.frmMenu.escritorio.add(ventana);
            ventana.setVisible(true);
            ventana.setLocation(x, y);
            
        }
    }
    public void buscarPorMes() {
        detalleV.buscar("");
        DefaultTableModel modelo = (DefaultTableModel) table.getModel();
        int fila = modelo.getRowCount();
        int columna = modelo.getColumnCount();
        String[][] datos = new String[fila][columna];

        int m = mch_fecha.getMonth() + 1;
        Calendar rightNow = Calendar.getInstance();
        int a = rightNow.getWeekYear();

        for (int i = 0; i < fila; i++) {
            String fecha = table.getValueAt(i, 3).toString();
            int mes = Integer.parseInt(fecha.substring(3, 5));
            int anio = Integer.parseInt(fecha.substring(6, 10));

            for (int j = 0; j < columna; j++) {

                if (m == mes && a == anio) {
                    try {
                        datos[i][j] = table.getValueAt(i, j).toString();
                    } catch (Exception e) {
                    }

                }
            }
        }

        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }

        for (int i = 0; i < fila; i++) {

            if (datos[i][0] != null) {
                modelo.addRow(datos[i]);
            }

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

        jPopupMenu1 = new javax.swing.JPopupMenu();
        popupVerProducto = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        btn_mes_dia = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        dch_fecha = new com.toedter.calendar.JDateChooser();
        mch_fecha = new com.toedter.calendar.JMonthChooser();
        btn_mes = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        btn_todasVentas = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        btn_Eliminar = new javax.swing.JButton();
        btn_Salir = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

        popupVerProducto.setText("Ver productos");
        popupVerProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popupVerProductoActionPerformed(evt);
            }
        });
        jPopupMenu1.add(popupVerProducto);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_mes_dia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/lupa.jpg"))); // NOI18N
        btn_mes_dia.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_mes_dia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_mes_diaActionPerformed(evt);
            }
        });
        jPanel5.add(btn_mes_dia, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 70, 51, 51));

        jLabel1.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel1.setText("Buscar por d??a/mes/a??o:");
        jPanel5.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, -1, -1));

        jLabel4.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel4.setText("Buscar por mes:");
        jPanel5.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 180, -1, -1));

        dch_fecha.setFont(new java.awt.Font("Bookman Old Style", 0, 48)); // NOI18N
        jPanel5.add(dch_fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, 180, 30));

        mch_fecha.setBackground(new java.awt.Color(255, 255, 255));
        mch_fecha.setFont(new java.awt.Font("Bookman Old Style", 0, 14)); // NOI18N
        jPanel5.add(mch_fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 220, 130, 30));

        btn_mes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/lupa.jpg"))); // NOI18N
        btn_mes.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_mes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_mesActionPerformed(evt);
            }
        });
        jPanel5.add(btn_mes, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 200, 51, 50));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel3.setText("Ver todas las ventas:");

        btn_todasVentas.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        btn_todasVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/lupa.jpg"))); // NOI18N
        btn_todasVentas.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_todasVentas.setPreferredSize(new java.awt.Dimension(105, 27));
        btn_todasVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_todasVentasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(57, 57, 57)
                .addComponent(btn_todasVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(btn_todasVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel3)))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE))
                .addGap(281, 281, 281))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        btn_Eliminar.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        btn_Eliminar.setText("Eliminar");
        btn_Eliminar.setPreferredSize(new java.awt.Dimension(105, 27));
        btn_Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_EliminarActionPerformed(evt);
            }
        });
        jPanel8.add(btn_Eliminar);

        btn_Salir.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        btn_Salir.setText("Salir");
        btn_Salir.setPreferredSize(new java.awt.Dimension(105, 27));
        btn_Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SalirActionPerformed(evt);
            }
        });
        jPanel8.add(btn_Salir);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(77, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, java.awt.BorderLayout.LINE_START);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N??  Venta", "Cliente", "Vendedor", "Fecha", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setComponentPopupMenu(jPopupMenu1);
        table.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setResizable(false);
            table.getColumnModel().getColumn(0).setPreferredWidth(75);
            table.getColumnModel().getColumn(1).setResizable(false);
            table.getColumnModel().getColumn(1).setPreferredWidth(250);
            table.getColumnModel().getColumn(2).setResizable(false);
            table.getColumnModel().getColumn(2).setPreferredWidth(125);
            table.getColumnModel().getColumn(3).setResizable(false);
            table.getColumnModel().getColumn(4).setResizable(false);
        }

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 692, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 585, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(jPanel4, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    listaProductos nota;
    private void btn_SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SalirActionPerformed
        doDefaultCloseAction();
    }//GEN-LAST:event_btn_SalirActionPerformed


    private void popupVerProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popupVerProductoActionPerformed
        int fila = table.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un registro", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            if (!(nota instanceof listaProductos)) {
                nota = new listaProductos();
            }
            
            centrarVentana(nota);

            listaProductos.verProducto(table.getValueAt(fila, 0).toString());
        }
    }//GEN-LAST:event_popupVerProductoActionPerformed

    private void btn_mes_diaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_mes_diaActionPerformed
        Date fecha = dch_fecha.getDate();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/YYYY");
        String enviarFech = formato.format(fecha);
     
        detalleV.buscarFecha(enviarFech);

    }//GEN-LAST:event_btn_mes_diaActionPerformed

    private void btn_mesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_mesActionPerformed
        buscarPorMes();
    }//GEN-LAST:event_btn_mesActionPerformed

    private void btn_todasVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_todasVentasActionPerformed
        detalleV.buscar("");
    }//GEN-LAST:event_btn_todasVentasActionPerformed
    public static String cod;
    private void btn_EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EliminarActionPerformed

        int fila = table.getSelectedRow();

        if (fila < 0) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un registro", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            int op = JOptionPane.showConfirmDialog(null, "??Esta seguro que quiere eliminar esta venta?", "ELIMINAR", JOptionPane.OK_OPTION);
            if (op == JOptionPane.OK_OPTION) {
                cod = table.getValueAt(fila, 0).toString();
                ventasCont venta = new ventasCont();
                venta.eliminar();
                detalleV.buscar("");
                try {
                    cajaModel.codigoVent();
                } catch (Exception e) {
                }
                try {
                    frmiCierreCaja.ejecutar();
                } catch (Exception e) {
                }
            }

        }

    }//GEN-LAST:event_btn_EliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Eliminar;
    private javax.swing.JButton btn_Salir;
    private javax.swing.JButton btn_mes;
    private javax.swing.JButton btn_mes_dia;
    private javax.swing.JButton btn_todasVentas;
    private com.toedter.calendar.JDateChooser dch_fecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JMonthChooser mch_fecha;
    private javax.swing.JMenuItem popupVerProducto;
    public static javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
