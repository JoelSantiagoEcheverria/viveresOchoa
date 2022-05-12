/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.Usuario;

import controlador.usuarioCont;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import vista.Clientes.*;

/**
 *
 * @author user
 */
public class frmiActualizar extends javax.swing.JInternalFrame {

    /**
     * Creates new form frmi_Nuevo
     */
    public frmiActualizar() {
        initComponents();
        llenarCamposAct();
        seleccionFilas();
        this.setTitle("ACTUALIZAR USUARIO");
        this.setFrameIcon(new ImageIcon(getClass().getResource("/imagenes/actualizarpers.png")));
    }
     JTable tabla =  vista.Usuario.frmiCatalogo.table;
     
    public void seleccionFilas(){
        tabla.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if (tabla.getSelectedRow() > -1) {
                    llenarCamposAct();
                }
                
            }
        });
    }
    
    public void llenarCamposAct(){
        int fila =vista.Usuario.frmiCatalogo.table.getSelectedRow();
       
        
        txt_Nombre.setText(tabla.getValueAt(fila, 1).toString());
        txt_UsuarioCod.setText(tabla.getValueAt(fila, 0).toString());
        String tipoUsu = vista.Usuario.frmiCatalogo.table.getValueAt(fila, 2).toString();
        for (int i = 0; i < cbo_TipoUsuario.getItemCount(); i++) {
            if (tipoUsu.equals(cbo_TipoUsuario.getItemAt(i))) {
                cbo_TipoUsuario.setSelectedIndex(i);
                break;
            }
        }
        
        
    }
        public void seleccionarNuevo(String cod){
        int filas=vista.Usuario.frmiCatalogo.table.getRowCount();
        for (int i = 0; i < filas; i++) {
            if (cod.equals(vista.Usuario.frmiCatalogo.table.getValueAt(i, 0))  ) {
                vista.Usuario.frmiCatalogo.table.setRowSelectionInterval(i, i);
                 break;
            }
           
        }
        
    }
        

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    usuarioCont usu ;
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txt_UsuarioCod = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txt_Nombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbo_TipoUsuario = new javax.swing.JComboBox<>();
        txt_Contrasenia = new javax.swing.JPasswordField();
        txt_RepetirCont = new javax.swing.JPasswordField();
        jPanel4 = new javax.swing.JPanel();
        btn_Acualzar = new javax.swing.JButton();
        btn_Cancelar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel6.setText("Código Usuario:");

        txt_UsuarioCod.setEditable(false);
        txt_UsuarioCod.setBackground(new java.awt.Color(0, 0, 0));
        txt_UsuarioCod.setFont(new java.awt.Font("Bookman Old Style", 0, 14)); // NOI18N
        txt_UsuarioCod.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(46, 46, 46)
                .addComponent(txt_UsuarioCod, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txt_UsuarioCod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel2.setText("Nuevo Nombre:");

        txt_Nombre.setBackground(new java.awt.Color(255, 255, 204));
        txt_Nombre.setFont(new java.awt.Font("Bookman Old Style", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel3.setText("Nueva Contraseña:");

        jLabel4.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel4.setText("Repetir Contraseña:");

        jLabel5.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel5.setText("Tipo Usuario:");

        cbo_TipoUsuario.setFont(new java.awt.Font("Bookman Old Style", 0, 14)); // NOI18N
        cbo_TipoUsuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar...", "ADMINISTRADOR", "AYUDANTE" }));

        txt_Contrasenia.setBackground(new java.awt.Color(255, 255, 204));
        txt_Contrasenia.setFont(new java.awt.Font("Bookman Old Style", 0, 14)); // NOI18N

        txt_RepetirCont.setBackground(new java.awt.Color(255, 255, 204));
        txt_RepetirCont.setFont(new java.awt.Font("Bookman Old Style", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txt_RepetirCont, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                    .addComponent(txt_Contrasenia, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_Nombre, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbo_TipoUsuario, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_Contrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_RepetirCont, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cbo_TipoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        btn_Acualzar.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        btn_Acualzar.setText("Actualizar");
        btn_Acualzar.setPreferredSize(new java.awt.Dimension(105, 29));
        btn_Acualzar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AcualzarActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_Acualzar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(btn_Acualzar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_Cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/actualizar.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel1)
                .addContainerGap(54, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void btn_AcualzarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AcualzarActionPerformed
         
        if (!txt_Nombre.getText().isEmpty() && !txt_Contrasenia.getText().isEmpty()
                && !txt_RepetirCont.getText().isEmpty() && cbo_TipoUsuario.getSelectedIndex() > 0) {
            if (txt_Contrasenia.getText().equals(txt_RepetirCont.getText())) {
                String tipoUsu = cbo_TipoUsuario.getSelectedItem().toString();
                usu = new usuarioCont(txt_UsuarioCod.getText(), txt_Nombre.getText(),
                        txt_Contrasenia.getText(), tipoUsu);
                usu.actualizar();
                String cod = txt_UsuarioCod.getText();
                usu.buscar("");
                seleccionarNuevo(cod);
                txt_Contrasenia.setText("");
                txt_RepetirCont.setText("");
                this.doDefaultCloseAction();
    
            } else {
                JOptionPane.showMessageDialog(this, "Las contraseñas no son iguales"
                        + " vuelva a escribir", "ERROR", JOptionPane.ERROR_MESSAGE);
                txt_Contrasenia.setText("");
                txt_RepetirCont.setText("");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Es obligatorio llenar todos los campos"
                    + " vuelva a escribir", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_btn_AcualzarActionPerformed

    private void btn_CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CancelarActionPerformed
        this.doDefaultCloseAction();
    }//GEN-LAST:event_btn_CancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Acualzar;
    private javax.swing.JButton btn_Cancelar;
    private javax.swing.JComboBox<String> cbo_TipoUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPasswordField txt_Contrasenia;
    private javax.swing.JTextField txt_Nombre;
    private javax.swing.JPasswordField txt_RepetirCont;
    private javax.swing.JTextField txt_UsuarioCod;
    // End of variables declaration//GEN-END:variables
}