/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework.module.userregister.view;

import framework.module.admin.model.functions.json_auto_admin;
import framework.module.menu.view.*;
import framework.module.config.view.Config;
import framework.module.userregister.model.functions.pagina_userregister;
import framework.module.userregister.model.BLL.BLL_userregister.BLL_userregister;
import framework.module.userregister.model.classes.miniSimpleTableModel_userregister;
import framework.module.admin.view.List_admin;
import framework.module.client.model.functions.json_auto_client;
import framework.module.client.view.List_client;
import framework.module.userregister.model.classes.Singleton_userregister;
import framework.module.userregister.model.functions.autocomplete.AutocompleteJComboBox_userregister;
import framework.module.userregister.model.functions.autocomplete.StringSearchable_userregister;
import framework.module.userregister.model.functions.json_auto_userregister;
import framework.module.userregister.view.List_userregister;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author angel
 */
public class List_userregister extends javax.swing.JFrame {

    public static TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(new miniSimpleTableModel_userregister());
    public static AutocompleteJComboBox_userregister combo = null;
    /**
     * Creates new form Menu
     */
    public List_userregister() {
        initComponents();
        
        this.setTitle("Framework");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); //la aplicación se abre maximizada
	//this.setLocationRelativeTo(null);
	//this.setSize(525,425);//ancho x alto
	//this.setResizable(false);
	//Image icono=Toolkit.getDefaultToolkit().getImage("p1.jpg");
	//this.setIconImage(icono);
        
        TABLA.setModel( new miniSimpleTableModel_userregister() );
        ((miniSimpleTableModel_userregister)TABLA.getModel()).cargar();
        TABLA.setFillsViewportHeight(true);
        TABLA.setRowSorter(sorter);
        
        pagina_userregister.inicializa();
        pagina_userregister.initLinkBox();
        pagina_userregister.itemsPerPage=Integer.parseInt(jComboBox2.getSelectedItem().toString());
        pagina_userregister.currentPageIndex = 1;
        pagina_userregister.initLinkBox();
        
        jLabel3.setText(String.valueOf(Singleton_userregister.userregister.size()));
        
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                json_auto_admin.savejson_admin();
                json_auto_client.savejson_client();
                json_auto_userregister.savejson_userregister();
                JOptionPane.showMessageDialog(null,"Saliendo de la aplicación");
                dispose();
                System.exit(0);
            }
        });
        
        List<String> myWords = new ArrayList<String>();
        for(int i=0;i<=Singleton_userregister.userregister.size()-1;i++) {
            myWords.add(Singleton_userregister.userregister.get(i).getName());
        }

	StringSearchable_userregister searchable = new StringSearchable_userregister(myWords);
	combo = new AutocompleteJComboBox_userregister(searchable);
        //JPanel5 se utiliza solamente para que JPanel3 que contendrá combo, no se redimensione
        jPanel3.setLayout(new java.awt.BorderLayout());
        jPanel3.add(combo);
        
        combo.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboActionPerformed(evt);
                
            }
        });
    }
    
    public static void comboActionPerformed(java.awt.event.ActionEvent evt) {                                            
        System.out.println("word selected: " + ((JComboBox)combo).getSelectedItem());
        pagina_userregister.currentPageIndex = 1;
        ((miniSimpleTableModel_userregister)TABLA.getModel()).filtrar();
        combo.requestFocus();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jPanelLateral = new javax.swing.JPanel();
        txtusuarios = new javax.swing.JLabel();
        txtadministrador = new javax.swing.JLabel();
        txtcliente = new javax.swing.JLabel();
        txtusuario = new javax.swing.JLabel();
        txtconfiguracion = new javax.swing.JLabel();
        txtajustes = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanelTitulo = new javax.swing.JPanel();
        txttitulo = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jPanelBase = new javax.swing.JPanel();
        jPanelCentral = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TABLA = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        ANTERIOR = new javax.swing.JButton();
        SIGUIENTE = new javax.swing.JButton();
        CAJA = new javax.swing.JTextField();
        primero = new javax.swing.JButton();
        ultimo = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanelLateral.setBackground(java.awt.Color.white);

        txtusuarios.setFont(new java.awt.Font("Purisa", 3, 15)); // NOI18N
        txtusuarios.setForeground(java.awt.Color.blue);
        txtusuarios.setText("Usuarios");

        txtadministrador.setFont(new java.awt.Font("Purisa", 0, 15)); // NOI18N
        txtadministrador.setForeground(java.awt.Color.cyan);
        txtadministrador.setText("Administrador");
        txtadministrador.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtadministradorMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtadministradorMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtadministradorMouseEntered(evt);
            }
        });

        txtcliente.setFont(new java.awt.Font("Purisa", 0, 15)); // NOI18N
        txtcliente.setForeground(java.awt.Color.cyan);
        txtcliente.setText("Clientes");
        txtcliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtclienteMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtclienteMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtclienteMouseEntered(evt);
            }
        });

        txtusuario.setFont(new java.awt.Font("Purisa", 0, 15)); // NOI18N
        txtusuario.setForeground(java.awt.Color.cyan);
        txtusuario.setText("Usuarios");
        txtusuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtusuarioMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtusuarioMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtusuarioMouseEntered(evt);
            }
        });

        txtconfiguracion.setFont(new java.awt.Font("Purisa", 3, 15)); // NOI18N
        txtconfiguracion.setForeground(java.awt.Color.blue);
        txtconfiguracion.setText("Configuración");

        txtajustes.setFont(new java.awt.Font("Purisa", 0, 15)); // NOI18N
        txtajustes.setForeground(java.awt.Color.cyan);
        txtajustes.setText("Ajustes");
        txtajustes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtajustesMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtajustesMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtajustesMouseEntered(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel9.setFont(new java.awt.Font("Purisa", 0, 15)); // NOI18N
        jLabel9.setForeground(java.awt.Color.blue);
        jLabel9.setText("Inicio");

        jLabel10.setFont(new java.awt.Font("Purisa", 0, 15)); // NOI18N
        jLabel10.setForeground(java.awt.Color.cyan);
        jLabel10.setText("Inicio");
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel10MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel10MouseEntered(evt);
            }
        });

        javax.swing.GroupLayout jPanelLateralLayout = new javax.swing.GroupLayout(jPanelLateral);
        jPanelLateral.setLayout(jPanelLateralLayout);
        jPanelLateralLayout.setHorizontalGroup(
            jPanelLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLateralLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(txtusuarios)
                    .addComponent(txtconfiguracion)
                    .addGroup(jPanelLateralLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanelLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(txtcliente)
                            .addComponent(txtadministrador)
                            .addComponent(txtusuario)
                            .addComponent(txtajustes))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanelLateralLayout.setVerticalGroup(
            jPanelLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLateralLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(txtusuarios)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtadministrador)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtcliente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtusuario)
                .addGap(18, 18, 18)
                .addComponent(txtconfiguracion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtajustes)
                .addContainerGap(189, Short.MAX_VALUE))
            .addComponent(jSeparator1)
        );

        jPanel1.add(jPanelLateral, java.awt.BorderLayout.WEST);

        jPanelTitulo.setBackground(java.awt.Color.white);
        jPanelTitulo.setLayout(new java.awt.BorderLayout());

        txttitulo.setBackground(java.awt.Color.white);
        txttitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txttitulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framework/img/titulo.JPG"))); // NOI18N
        jPanelTitulo.add(txttitulo, java.awt.BorderLayout.CENTER);
        jPanelTitulo.add(jSeparator2, java.awt.BorderLayout.SOUTH);

        jPanel1.add(jPanelTitulo, java.awt.BorderLayout.NORTH);

        jPanelBase.setBackground(java.awt.Color.lightGray);

        javax.swing.GroupLayout jPanelBaseLayout = new javax.swing.GroupLayout(jPanelBase);
        jPanelBase.setLayout(jPanelBaseLayout);
        jPanelBaseLayout.setHorizontalGroup(
            jPanelBaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1206, Short.MAX_VALUE)
        );
        jPanelBaseLayout.setVerticalGroup(
            jPanelBaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 49, Short.MAX_VALUE)
        );

        jPanel1.add(jPanelBase, java.awt.BorderLayout.SOUTH);

        jPanelCentral.setBackground(java.awt.Color.white);
        jPanelCentral.setLayout(new java.awt.BorderLayout());

        jPanel6.setBackground(java.awt.Color.white);
        jPanel6.setLayout(new java.awt.BorderLayout());

        jPanel9.setBackground(java.awt.Color.white);

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framework/img/color_line.png"))); // NOI18N
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel8MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel8MouseEntered(evt);
            }
        });

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framework/img/edit_add.png"))); // NOI18N
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel11MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel11MouseEntered(evt);
            }
        });

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framework/img/edit_remove.png"))); // NOI18N
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel12MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel12MouseEntered(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(174, Short.MAX_VALUE))
        );

        jPanel6.add(jPanel9, java.awt.BorderLayout.EAST);

        jPanel2.setBackground(java.awt.Color.white);
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        TABLA.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(TABLA);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 962, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel6.add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanelCentral.add(jPanel6, java.awt.BorderLayout.CENTER);

        jPanel7.setBackground(java.awt.Color.white);
        jPanel7.setLayout(new java.awt.BorderLayout());

        jPanel11.setBackground(java.awt.Color.white);
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder("GUARDAR"));
        jPanel11.setLayout(new java.awt.GridLayout(1, 3));

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framework/img/json.png"))); // NOI18N
        jPanel11.add(jLabel13);

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framework/img/xml.jpg"))); // NOI18N
        jPanel11.add(jLabel14);

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framework/img/txt.jpg"))); // NOI18N
        jPanel11.add(jLabel15);

        jPanel7.add(jPanel11, java.awt.BorderLayout.EAST);

        jPanel10.setBackground(java.awt.Color.white);
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("BUSCAR"));

        jLabel1.setText("Filtra First Name");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 192, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jLabel3.setText("jLabel3");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 31, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 32, Short.MAX_VALUE)
        );

        jPanel12.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ANTERIOR.setText("<");
        ANTERIOR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ANTERIORActionPerformed(evt);
            }
        });
        jPanel12.add(ANTERIOR, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, -1, -1));

        SIGUIENTE.setText(">");
        SIGUIENTE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SIGUIENTEActionPerformed(evt);
            }
        });
        jPanel12.add(SIGUIENTE, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, -1, -1));

        CAJA.setEditable(false);
        CAJA.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        CAJA.setPreferredSize(new java.awt.Dimension(140, 20));
        jPanel12.add(CAJA, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 80, 30));

        primero.setText("|<");
        primero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                primeroActionPerformed(evt);
            }
        });
        jPanel12.add(primero, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        ultimo.setText(">|");
        ultimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ultimoActionPerformed(evt);
            }
        });
        jPanel12.add(ultimo, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, -1, -1));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "5", "10", "15", "20", "50", "100" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jLabel2.setText("Show entries:");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(331, 331, 331)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(24, Short.MAX_VALUE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        jPanel7.add(jPanel10, java.awt.BorderLayout.CENTER);

        jPanelCentral.add(jPanel7, java.awt.BorderLayout.SOUTH);

        jPanel1.add(jPanelCentral, java.awt.BorderLayout.CENTER);

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1033, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 709, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtadministradorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtadministradorMouseClicked
        new List_admin().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_txtadministradorMouseClicked

    private void txtadministradorMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtadministradorMouseExited
        txtadministrador.setForeground(java.awt.Color.cyan);
    }//GEN-LAST:event_txtadministradorMouseExited

    private void txtadministradorMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtadministradorMouseEntered
        txtadministrador.setForeground(java.awt.Color.blue);
    }//GEN-LAST:event_txtadministradorMouseEntered

    private void txtclienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtclienteMouseClicked
        new List_client().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_txtclienteMouseClicked

    private void txtclienteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtclienteMouseExited
        txtcliente.setForeground(java.awt.Color.cyan);
    }//GEN-LAST:event_txtclienteMouseExited

    private void txtclienteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtclienteMouseEntered
        txtcliente.setForeground(java.awt.Color.blue);
    }//GEN-LAST:event_txtclienteMouseEntered

    private void txtusuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtusuarioMouseClicked
        new List_userregister().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_txtusuarioMouseClicked

    private void txtusuarioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtusuarioMouseExited
        txtusuario.setForeground(java.awt.Color.cyan);
    }//GEN-LAST:event_txtusuarioMouseExited

    private void txtusuarioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtusuarioMouseEntered
        txtusuario.setForeground(java.awt.Color.blue);
    }//GEN-LAST:event_txtusuarioMouseEntered

    private void txtajustesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtajustesMouseClicked
        new Config().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_txtajustesMouseClicked

    private void txtajustesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtajustesMouseExited
        txtajustes.setForeground(java.awt.Color.cyan);
    }//GEN-LAST:event_txtajustesMouseExited

    private void txtajustesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtajustesMouseEntered
        txtajustes.setForeground(java.awt.Color.blue);
    }//GEN-LAST:event_txtajustesMouseEntered

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        this.dispose();
        new Menu().setVisible(true);
    }//GEN-LAST:event_jLabel10MouseClicked

    private void jLabel10MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseExited
        jLabel10.setForeground(java.awt.Color.cyan);
    }//GEN-LAST:event_jLabel10MouseExited

    private void jLabel10MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseEntered
        jLabel10.setForeground(java.awt.Color.blue);
    }//GEN-LAST:event_jLabel10MouseEntered

    private void jLabel11MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseEntered
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framework/img/edit_add_bn.png")));
    }//GEN-LAST:event_jLabel11MouseEntered

    private void jLabel11MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseExited
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framework/img/edit_add.png")));
    }//GEN-LAST:event_jLabel11MouseExited

    private void jLabel12MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseEntered
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framework/img/edit_remove_bn.png")));
    }//GEN-LAST:event_jLabel12MouseEntered

    private void jLabel12MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseExited
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framework/img/edit_remove.png")));
    }//GEN-LAST:event_jLabel12MouseExited

    private void jLabel8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseEntered
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framework/img/color_line_bn.png")));
    }//GEN-LAST:event_jLabel8MouseEntered

    private void jLabel8MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseExited
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framework/img/color_line.png")));
    }//GEN-LAST:event_jLabel8MouseExited

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        this.dispose();
        new Create_userregister().setVisible(true);
    }//GEN-LAST:event_jLabel11MouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        int select = -1;
        select = TABLA.getSelectedRow();
        if (select == -1){
            JOptionPane.showMessageDialog(null, "Administrador no seleccionado");
        }else{
            new Update_userregister().setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_jLabel8MouseClicked

    private void ANTERIORActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ANTERIORActionPerformed
        pagina_userregister.currentPageIndex -= 1;
        pagina_userregister.initLinkBox();
    }//GEN-LAST:event_ANTERIORActionPerformed

    private void SIGUIENTEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SIGUIENTEActionPerformed
        pagina_userregister.currentPageIndex += 1;
        pagina_userregister.initLinkBox();
    }//GEN-LAST:event_SIGUIENTEActionPerformed

    private void primeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_primeroActionPerformed
        pagina_userregister.currentPageIndex = 1;
        pagina_userregister.initLinkBox();
    }//GEN-LAST:event_primeroActionPerformed

    private void ultimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ultimoActionPerformed
        pagina_userregister.currentPageIndex = pagina_userregister.maxPageIndex;
        pagina_userregister.initLinkBox();
    }//GEN-LAST:event_ultimoActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        pagina_userregister.itemsPerPage=Integer.parseInt(jComboBox2.getSelectedItem().toString());
        pagina_userregister.currentPageIndex = 1;
        pagina_userregister.initLinkBox();
    }//GEN-LAST:event_jComboBox2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton ANTERIOR;
    public static javax.swing.JTextField CAJA;
    public static javax.swing.JButton SIGUIENTE;
    public static javax.swing.JTable TABLA;
    public static javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    public static javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    public static javax.swing.JPanel jPanel3;
    public static javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelBase;
    private javax.swing.JPanel jPanelCentral;
    private javax.swing.JPanel jPanelLateral;
    private javax.swing.JPanel jPanelTitulo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    public static javax.swing.JButton primero;
    private javax.swing.JLabel txtadministrador;
    private javax.swing.JLabel txtajustes;
    private javax.swing.JLabel txtcliente;
    private javax.swing.JLabel txtconfiguracion;
    private javax.swing.JLabel txttitulo;
    private javax.swing.JLabel txtusuario;
    private javax.swing.JLabel txtusuarios;
    public static javax.swing.JButton ultimo;
    // End of variables declaration//GEN-END:variables

}
