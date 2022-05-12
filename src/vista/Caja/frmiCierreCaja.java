/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.Caja;

import configuracion.estiloHeader;
import configuracion.estiloTabla;
import controlador.detalleVentasCont;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author user
 */
public class frmiCierreCaja extends javax.swing.JInternalFrame {

    /**
     * Creates new form frmiCierreCaja
     */
    public static detalleVentasCont cerrar = new detalleVentasCont();

    public frmiCierreCaja() {
        initComponents();
        table.setDefaultRenderer(Object.class, new estiloTabla());
        table.getTableHeader().setDefaultRenderer(new estiloHeader());
        ejecutar();
        this.setFrameIcon(new ImageIcon(getClass().getResource("/imagenes/catalogoicon.png")));
    }

    public static void ejecutar() {
        verFechaHoy();
        llenarVacios();
        mostrarProducto();
        llenarTotal();
        giveFormat();
        calcular();
    }

    public static void llenarVacios() {
        int filas = table.getRowCount();

        for (int j = 0; j < filas; j++) {
            table.setValueAt(j, j, 5);
        }

    }

    public static void verFechaHoy() {
        Date fecha = new Date();
        SimpleDateFormat simple = new SimpleDateFormat("dd/MM/YYYY");
        cerrar.cierreCaja(simple.format(fecha));
    }

    public static void calcular() {
        int filas = table.getRowCount();
        double precios = 0;
        for (int i = 0; i < filas; i++) {
            precios += Double.parseDouble(table.getValueAt(i, 5).toString().replace(",", "."));
        }

        DecimalFormat forma = new DecimalFormat("0.00");
        txt_totalDia.setText(forma.format(precios));

    }

    public static void giveFormat() {
        int filas = table.getRowCount();
        DecimalFormat forma = new DecimalFormat("0.00");
        for (int i = 0; i < filas; i++) {
            String coma = forma.format(Double.parseDouble(table.getValueAt(i, 4).toString()));
            table.setValueAt(coma, i, 4);

            String comaTotal = forma.format(Double.parseDouble(table.getValueAt(i, 5).toString()));
            table.setValueAt(comaTotal, i, 5);
        }

    }

    public static void llenarTotal() {
        int filas = table.getRowCount();
        for (int i = 0; i < filas; i++) {
            int cantidad = Integer.parseInt(table.getValueAt(i, 3).toString());
            double precio = Double.parseDouble(table.getValueAt(i, 4).toString()/*.replace(",", ".")*/);
            double total = cantidad * precio;
            table.setValueAt(total, i, 5);
        }

    }

    public static void mostrarProducto() {
        DefaultTableModel modelo = (DefaultTableModel) table.getModel();
        int filas = table.getRowCount();
        int colum = table.getColumnCount();
        int cont = 0;
        String[][] modelo1 = new String[filas][colum];
        String[][] modelo2 = new String[filas][colum];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < colum; j++) {
                modelo1[i][j] = table.getValueAt(i, j).toString();
            }

        }

        int pos = 0;
        String cod = "";
        int k = 0;

        boolean bandera = true;

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < filas; j++) {

                if (modelo1[i][0].equals(modelo1[j][0])) {

                    for (int l = 0; l < filas; l++) {
                        if (modelo2[l][0] != null) {
                            if (modelo2[l][0].equals(modelo1[i][0])) {
                                bandera = false;
                                break;
                            }
                        } else {
                            bandera = true;
                            break;
                        }

                    }

                    if (bandera) {
                        for (int h = 0; h < colum; h++) {
                            modelo2[k][h] = modelo1[i][h];
                        }
                        k++;
                    }

                    bandera = true;

                    cont++;

                    cod = modelo1[i][0];
                    pos = i;
                } else {
                    for (int l = 0; l < filas; l++) {
                        if (modelo2[l][0] != null) {
                            if (modelo2[l][0].equals(modelo1[i][0])) {
                                bandera = false;
                                break;
                            }
                        } else {
                            bandera = true;
                            break;
                        }

                    }
                    if (bandera) {
                        for (int h = 0; h < colum; h++) {
                            modelo2[k][h] = modelo1[i][h];
                        }
                        k++;
                    }
                    bandera = true;
                }

            }
            System.out.println(cod + "  " + cont + "  " + pos);
            cont = 0;

        }

        System.out.println("--------------------modelo 2--------------------------------");

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < colum; j++) {
                if (modelo2[i][0] != null) {
                    System.out.print(modelo2[i][j] + "  ");
                } else {

                }

            }
            System.out.println();
        }

        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
        for (int i = 0; i < filas; i++) {

            modelo.addRow(modelo2[i]);

        }

        contar(modelo1, modelo2, modelo);

    }

    public static void contar(String[][] modelo1, String[][] modelo2, DefaultTableModel modelo) {
        int filas = table.getRowCount();
        int colum = table.getColumnCount();

        int cantidad = 0;
        int cont = 0;

        parar:
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < filas; j++) {
                if (modelo2[i][0] != null) {
                    if (modelo2[i][0].equals(modelo1[j][0])) {
                        cantidad += Integer.parseInt(modelo1[j][3]);

                        cont++;
                    }
                } else {
                    continue parar;
                }
            }
            table.setValueAt(cantidad, i, 3);
            System.out.println(modelo2[i][0] + "  total conteo-----> " + cont + " cantidad= " + cantidad + "  " + i);
            cont = 0;
            cantidad = 0;
        }

        int ct = 0;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < colum; j++) {
                try {
                    modelo2[i][j] = table.getValueAt(i, j).toString();
                } catch (Exception e) {

                }
            }

        }

        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
        for (int i = 0; i < filas; i++) {
            if (modelo2[i][0] != null) {
                modelo.addRow(modelo2[i]);
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        txt_totalDia = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("DETALLE DE HOY"));
        jPanel1.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código ", "Producto", "Fecha", "Cantidad", "Precio", "Total"
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
            table.getColumnModel().getColumn(1).setPreferredWidth(200);
            table.getColumnModel().getColumn(2).setPreferredWidth(200);
            table.getColumnModel().getColumn(3).setPreferredWidth(120);
            table.getColumnModel().getColumn(5).setPreferredWidth(75);
        }

        txt_totalDia.setEditable(false);
        txt_totalDia.setBackground(new java.awt.Color(0, 0, 0));
        txt_totalDia.setFont(new java.awt.Font("Bookman Old Style", 0, 14)); // NOI18N
        txt_totalDia.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel1.setText("Ganancia:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txt_totalDia, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 832, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_totalDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable table;
    public static javax.swing.JTextField txt_totalDia;
    // End of variables declaration//GEN-END:variables
}
