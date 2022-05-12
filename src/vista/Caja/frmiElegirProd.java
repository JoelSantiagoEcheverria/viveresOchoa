/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.Caja;

//import com.placeholder.PlaceHolder;
import configuracion.estiloHeader;
import configuracion.estiloTabla;
import controlador.cajaCont;
import controlador.categoriaCont;
import controlador.productosCont;
import java.awt.Cursor;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DecimalFormat;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author user
 */
public class frmiElegirProd extends javax.swing.JInternalFrame {

    /**
     * Creates new form frmiElegirProd
     */
    cajaCont producto = new cajaCont();

    public frmiElegirProd() {
        initComponents();
        this.setTitle("ENVIAR PRODUCTO A CAJA");
        this.setFrameIcon(new ImageIcon(getClass().getResource("/imagenes/catalogoicon.png")));
        table.setDefaultRenderer(Object.class, new estiloTabla());
        table.getTableHeader().setDefaultRenderer(new estiloHeader());
        categoriaCont.laCategoria(cbo_TipoProd);

        producto.buscar("");
        buscarCat();
        giveFormat();
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setCursor(new Cursor(Cursor.HAND_CURSOR));

    }

    public void buscarCat() {

        cbo_TipoProd.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie) {
                if (cbo_TipoProd.getSelectedIndex() > 0) {
                    String id = cbo_TipoProd.getItemAt(cbo_TipoProd.getSelectedIndex()).getIdCategoria();
                    producto.buscar(id);

                } else {
                    producto.buscar("");

                }
                frmiCobrar.stkActualizado();
                giveFormat();
            }
        });

    }

    public boolean isNumber(String dato) {
        try {
            int d = Integer.parseInt(dato);
            if (d > 0) {
                return false;
            } else {
                return true;
            }
        } catch (NumberFormatException e) {
            return true;
        }
    }

    int cant;

    public void enviarCaja() {

        int fila = table.getSelectedRow();
        String[] dato = new String[6];
        DefaultTableModel modelo = (DefaultTableModel) frmiCobrar.table.getModel();
        String pre = table.getValueAt(fila, 3).toString().replaceAll("[^0-9]", ".");
        float acumPrecio = 0;
        int acumCantidad = 0;
        float precio = Float.parseFloat(pre);
        int cont = 0;

        String can = "";

        if (table.getValueAt(fila, 4).toString().equals("fuera de stock")) {
            JOptionPane.showMessageDialog(this, "Este elemento esta fuera de stock", "ERROR", JOptionPane.ERROR_MESSAGE);

        } else {
            can = JOptionPane.showInputDialog(this,
                    "Cantidad:", "Productos", JOptionPane.INFORMATION_MESSAGE);

            while (can.isEmpty() || isNumber(can)) {
                can = JOptionPane.showInputDialog(this,
                        "Dijite una cantidad mayor a 0", "ERROR", JOptionPane.ERROR_MESSAGE);

            }
        }

//            int cant = Integer.parseInt(JOptionPane.showInputDialog(this, 
//                    "Cantidad:", "Productos", JOptionPane.INFORMATION_MESSAGE));
        cant = Integer.parseInt(can);

        if (isStock(cant)) {

            if (frmiCobrar.table.getRowCount() != 0) {

                for (int i = 0; i < frmiCobrar.table.getRowCount(); i++) {
                    String idOp = table.getValueAt(fila, 0).toString();
                    String idCaj = frmiCobrar.table.getValueAt(i, 0).toString();

                    if (idOp.equals(idCaj)) {

                        String cantidad = frmiCobrar.table.getValueAt(i, 4).toString();

                        acumCantidad = cant + Integer.parseInt(cantidad);
                        acumPrecio += acumCantidad * precio;
                        DecimalFormat cambiar = new DecimalFormat("0.00");
                        frmiCobrar.table.setValueAt(cambiar.format(acumPrecio), i, 5);
                        frmiCobrar.table.setValueAt(acumCantidad, i, 4);
                        cont++;

                    }

                }
            }
            if (frmiCobrar.table.getRowCount() == 0 || cont == 0) {

                DecimalFormat df = new DecimalFormat("0.00");
                dato[0] = table.getValueAt(fila, 0).toString();
                dato[1] = table.getValueAt(fila, 1).toString();
                dato[2] = table.getValueAt(fila, 2).toString();
                dato[3] = table.getValueAt(fila, 3).toString();
                dato[4] = String.valueOf(cant);

                //float total = (float)Math.round(((precio*cant)*100d)/100);
                dato[5] = df.format(precio * cant);
                modelo.addRow(dato);

            }

            int st = Integer.parseInt(table.getValueAt(fila, 4).toString());
            int res = st - cant;

            table.setValueAt(res, fila, 4);
        } else {
            JOptionPane.showMessageDialog(this, "La cantidad que pide sobre pasa el stock", "ERROR", JOptionPane.ERROR_MESSAGE);
        }

        //  .table.setModel(modelo);
    }

    boolean onStock;

    public static void sumarPrecios() {

        DecimalFormat cambio = new DecimalFormat("0.00");
        float acumulador = 0;
        if (frmiCobrar.table.getRowCount() > 0) {
            for (int i = 0; i < frmiCobrar.table.getRowCount(); i++) {
                String valor = frmiCobrar.table.getValueAt(i, 5).toString();
                float contar = Float.parseFloat(valor.replaceAll("[^0-9]", "."));
                acumulador += contar;
                frmiCobrar.txt_Total.setText(cambio.format(acumulador));

            }
        }

    }

    public static void giveFormat() {
        DecimalFormat df = new DecimalFormat("0.00");
        for (int i = 0; i < table.getRowCount(); i++) {
            String a = table.getValueAt(i, 3).toString();
            float b = Float.parseFloat(a.replaceAll("[^0-9]", "."));
            table.setValueAt(df.format(b), i, 3);
        }

    }

    void removeSelection() {
        if (table.getSelectedRow() > -1) {
            for (int i = 0; i < table.getRowCount(); i++) {
                table.removeRowSelectionInterval(i, i);
            }
        }

    }

    // public void is
    public boolean isStock(int c) {
        int fila = table.getSelectedRow();
        int stk = Integer.parseInt(table.getValueAt(fila, 4).toString());
        int isstk = stk - c;

        return isstk >= 0;
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
        cbo_TipoProd = new javax.swing.JComboBox<>();
        btn_EnviarCaja = new javax.swing.JButton();
        btn_Cancelar = new javax.swing.JButton();
        txt_Buscar = new app.bolivia.swing.JCTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "OPCIONES", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N
        jPanel1.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N

        cbo_TipoProd.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        cbo_TipoProd.setToolTipText("");
        cbo_TipoProd.setPreferredSize(new java.awt.Dimension(34, 27));

        btn_EnviarCaja.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        btn_EnviarCaja.setText("Enviar a Caja");
        btn_EnviarCaja.setPreferredSize(new java.awt.Dimension(129, 29));
        btn_EnviarCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_EnviarCajaActionPerformed(evt);
            }
        });

        btn_Cancelar.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        btn_Cancelar.setText("Cancelar");
        btn_Cancelar.setPreferredSize(new java.awt.Dimension(105, 27));
        btn_Cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CancelarActionPerformed(evt);
            }
        });

        txt_Buscar.setBackground(new java.awt.Color(204, 204, 255));
        txt_Buscar.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        txt_Buscar.setPlaceholder("Descripción");
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(cbo_TipoProd, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(btn_EnviarCaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 30, Short.MAX_VALUE)
                .addComponent(btn_Cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbo_TipoProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_EnviarCaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Tipo Producto", "Descripción", "Precio de Venta", "Stock"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setResizable(false);
            table.getColumnModel().getColumn(0).setPreferredWidth(20);
            table.getColumnModel().getColumn(1).setResizable(false);
            table.getColumnModel().getColumn(2).setResizable(false);
            table.getColumnModel().getColumn(2).setPreferredWidth(200);
            table.getColumnModel().getColumn(3).setResizable(false);
            table.getColumnModel().getColumn(3).setPreferredWidth(35);
            table.getColumnModel().getColumn(4).setResizable(false);
            table.getColumnModel().getColumn(4).setPreferredWidth(40);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CancelarActionPerformed
        this.doDefaultCloseAction();
        removeSelection();
        cbo_TipoProd.setSelectedIndex(0);
        txt_Buscar.setText("");
        producto.buscar("");
        frmiCobrar.btn_ElegirProd.setEnabled(true);

    }//GEN-LAST:event_btn_CancelarActionPerformed
    frmiCobrar vuelto;
    private void btn_EnviarCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EnviarCajaActionPerformed
        if (table.getSelectedRow() > -1) {
            try {

                enviarCaja();
                sumarPrecios();
            } catch (Exception e) {

            }

        } else {
            JOptionPane.showMessageDialog(null, "Es obligatorio seleccionar un producto", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        frmiCobrar.txt_Recibi.setText("");
        frmiCobrar.txt_DarCambio.setText("");

    }//GEN-LAST:event_btn_EnviarCajaActionPerformed

    private void txt_BuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_BuscarKeyReleased
        producto.buscar(txt_Buscar.getText());
        giveFormat();
        if (frmiCobrar.table.getRowCount() > 0) {
            frmiCobrar.stkActualizado();
        }

    }//GEN-LAST:event_txt_BuscarKeyReleased

    private void txt_BuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_BuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_BuscarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Cancelar;
    private javax.swing.JButton btn_EnviarCaja;
    private javax.swing.JComboBox<categoriaCont> cbo_TipoProd;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable table;
    private app.bolivia.swing.JCTextField txt_Buscar;
    // End of variables declaration//GEN-END:variables
}
