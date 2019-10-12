/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ControllerFuncionario;
import Controller.ControllerContaPagar;
import Controller.ControllerContaReceber;
import Controller.ControllerFormaPagamento;
import Controller.ControllerSeguradora;
import Controller.ControllerOp;
import Model.ModelFuncionario;
import Model.ModelContaPagar;
import Model.ModelContaReceber;
import Model.ModelFormaPagamento;
import Model.ModelSeguradora;
import Model.ModelOp;
import util.Exporter;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.File;
import java.sql.Date;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import util.Datas;
import util.Mascaras;
import util.Moeda;

/**
 *
 * @author Administrador
 */
public class ViewFluxoDados extends javax.swing.JFrame {

    String CLASS = "";

    ModelContaPagar modelContasPagar = new ModelContaPagar();
    ModelContaReceber modelContaReceber = new ModelContaReceber();
    ControllerFuncionario controllerFuncionario = new ControllerFuncionario();
    ControllerSeguradora controllerSeguradora = new ControllerSeguradora();
    ArrayList<ModelContaPagar> listaModelContaPagar = new ArrayList<>();
    ArrayList<ModelContaReceber> listaModelContaReceber = new ArrayList<>();
    ControllerContaPagar controllerContaPagar = new ControllerContaPagar();
    ControllerContaReceber controllerContaReceber = new ControllerContaReceber();
    ControllerFormaPagamento controllerTipoPagamento = new ControllerFormaPagamento();
    ArrayList<ModelFormaPagamento> listaModelTipoPagamentos = new ArrayList<>();
    ArrayList<ModelFuncionario> listaModelFuncionario = new ArrayList<>();
    ArrayList<ModelSeguradora> listaSeguradora = new ArrayList<>();
    ControllerOp controllerOp = new ControllerOp();
    ArrayList<ModelOp> listaModelOp = new ArrayList<>();
    Mascaras bLMascaras = new Mascaras();

    //SEGURADORAS
    ArrayList<ModelSeguradora> listaModelSeguradora = new ArrayList<>();
    ModelSeguradora modelSeguradora = new ModelSeguradora();

    /**
     * Creates new form ViewFluxoCaixa
     */
    public ViewFluxoDados() {
        initComponents();
        SetIcone();
        setLocationRelativeTo(null);
        carregarContas();
        somaEAtualizaValorTotal();
        listarSeguradoras();
        cbSeguradoras.setSelectedIndex(-1);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ViewCadOcupacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewCadOcupacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewCadOcupacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewCadOcupacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewFluxoDados().setVisible(true);
            }
        });
    }

    private void somaEAtualizaValorTotal() {
        float totalPagar = 0;
        float totalReceber = 0;
        float valorPagar = 0;
        float valorReceber = 0;
        int cont = tbFluxoDados.getRowCount();
        for (int i = 0; i < cont; i++) {
            if (tbFluxoDados.getValueAt(i, 6).equals("DÉBITO")) {
                valorPagar = Float.parseFloat(new Moeda().FommatoStringoSomarMil(String.valueOf(tbFluxoDados.getValueAt(i, 5))));
                totalPagar = valorPagar + totalPagar;
            } else {
                valorReceber = Float.parseFloat(new Moeda().FommatoStringoSomarMil(String.valueOf(tbFluxoDados.getValueAt(i, 5))));
                totalReceber = valorReceber + totalReceber;
            }
        }
        jlDebito.setText(bLMascaras.arredondamentoComPontoDuasCasasString(totalPagar));
        jlCredito.setText(bLMascaras.arredondamentoComPontoDuasCasasString(totalReceber));
        jlSaldoTotalCaixa.setText(bLMascaras.arredondamentoComPontoDuasCasasString(totalReceber - totalPagar));
        float corJpanel = totalReceber - totalPagar;
        if (corJpanel < 0) {
            jPanel3.setBackground(Color.red);
        } else {
            jPanel3.setBackground(Color.GREEN);
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
        jdcDataInicial = new com.toedter.calendar.JDateChooser();
        btDefinirData = new javax.swing.JButton();
        jdcDataFinal = new com.toedter.calendar.JDateChooser();
        lbFluxoCaixa = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tbFluxoDados = new javax.swing.JTable();
        kGradientPanel1 = new keeptoo.KGradientPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jlDebito = new javax.swing.JLabel();
        jlCredito = new javax.swing.JLabel();
        jlSaldoTotalCaixa = new javax.swing.JLabel();
        jbVisualizarConta = new javax.swing.JButton();
        cbSeguradoras = new javax.swing.JComboBox<String>();
        jLabel19 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btClear = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Fluxo de Dados");
        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1281, 700));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setRequestFocusEnabled(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jdcDataInicial.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jdcDataInicialFocusLost(evt);
            }
        });
        jPanel1.add(jdcDataInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 150, 28));

        btDefinirData.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btDefinirData.setForeground(new java.awt.Color(51, 51, 51));
        btDefinirData.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/page_find.png"))); // NOI18N
        btDefinirData.setText("APLICAR");
        btDefinirData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDefinirDataActionPerformed(evt);
            }
        });
        jPanel1.add(btDefinirData, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 80, 130, 35));
        jPanel1.add(jdcDataFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 80, 150, 28));

        lbFluxoCaixa.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbFluxoCaixa.setText("CONTAS A PAGAR E RECEBER E PROCESSOS FINALIZADOS!");
        jPanel1.add(lbFluxoCaixa, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 460, 30));

        jLabel3.setText("Selecione o dia:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, -1));

        jScrollPane7.setBackground(new java.awt.Color(247, 247, 247));
        jScrollPane7.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane7.setPreferredSize(new java.awt.Dimension(1220, 180));

        tbFluxoDados.setAutoCreateRowSorter(true);
        tbFluxoDados.setBackground(new java.awt.Color(247, 247, 247));
        tbFluxoDados.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        tbFluxoDados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cod.", "Status", "Destino", "Entrada", "Vencimento", "Valor", "Movimento", "Situação", "Tipo Conta"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbFluxoDados.setGridColor(new java.awt.Color(247, 247, 247));
        tbFluxoDados.setMinimumSize(new java.awt.Dimension(610, 0));
        tbFluxoDados.setRowHeight(20);
        tbFluxoDados.setSelectionBackground(new java.awt.Color(0, 153, 0));
        tbFluxoDados.getTableHeader().setReorderingAllowed(false);
        jScrollPane7.setViewportView(tbFluxoDados);
        if (tbFluxoDados.getColumnModel().getColumnCount() > 0) {
            tbFluxoDados.getColumnModel().getColumn(0).setMinWidth(80);
            tbFluxoDados.getColumnModel().getColumn(0).setPreferredWidth(80);
            tbFluxoDados.getColumnModel().getColumn(0).setMaxWidth(80);
            tbFluxoDados.getColumnModel().getColumn(1).setMinWidth(100);
            tbFluxoDados.getColumnModel().getColumn(2).setMinWidth(200);
            tbFluxoDados.getColumnModel().getColumn(3).setMinWidth(120);
            tbFluxoDados.getColumnModel().getColumn(3).setPreferredWidth(120);
            tbFluxoDados.getColumnModel().getColumn(3).setMaxWidth(120);
            tbFluxoDados.getColumnModel().getColumn(4).setMinWidth(120);
            tbFluxoDados.getColumnModel().getColumn(4).setPreferredWidth(120);
            tbFluxoDados.getColumnModel().getColumn(4).setMaxWidth(120);
            tbFluxoDados.getColumnModel().getColumn(5).setMinWidth(120);
            tbFluxoDados.getColumnModel().getColumn(5).setPreferredWidth(120);
            tbFluxoDados.getColumnModel().getColumn(5).setMaxWidth(120);
            tbFluxoDados.getColumnModel().getColumn(6).setMinWidth(100);
            tbFluxoDados.getColumnModel().getColumn(6).setPreferredWidth(100);
            tbFluxoDados.getColumnModel().getColumn(6).setMaxWidth(100);
            tbFluxoDados.getColumnModel().getColumn(7).setMinWidth(100);
            tbFluxoDados.getColumnModel().getColumn(7).setPreferredWidth(100);
            tbFluxoDados.getColumnModel().getColumn(7).setMaxWidth(100);
            tbFluxoDados.getColumnModel().getColumn(8).setMinWidth(100);
            tbFluxoDados.getColumnModel().getColumn(8).setPreferredWidth(100);
            tbFluxoDados.getColumnModel().getColumn(8).setMaxWidth(100);
        }

        jPanel1.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 1240, 400));

        kGradientPanel1.setkBorderRadius(0);
        kGradientPanel1.setkEndColor(new java.awt.Color(234, 239, 243));
        kGradientPanel1.setkGradientFocus(-5);
        kGradientPanel1.setkStartColor(new java.awt.Color(255, 255, 255));
        kGradientPanel1.setPreferredSize(new java.awt.Dimension(520, 210));
        kGradientPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(102, 255, 153));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel9.setText("Total Debito:");

        jLabel10.setText("Total Credito:");

        jLabel11.setText("Saldo:");

        jLabel12.setText("R$");

        jLabel13.setText("R$");

        jLabel14.setText("R$");

        jlDebito.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jlDebito.setText("jLabel7");

        jlCredito.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jlCredito.setText("jLabel8");

        jlSaldoTotalCaixa.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jlSaldoTotalCaixa.setText("jLabel9");

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                    .add(jPanel3Layout.createSequentialGroup()
                        .add(jLabel11)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(jLabel14)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jlSaldoTotalCaixa))
                    .add(jPanel3Layout.createSequentialGroup()
                        .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel3Layout.createSequentialGroup()
                                .add(jLabel10)
                                .add(26, 26, 26))
                            .add(jPanel3Layout.createSequentialGroup()
                                .add(jLabel9)
                                .add(30, 30, 30)))
                        .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel3Layout.createSequentialGroup()
                                .add(jLabel12)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jlDebito))
                            .add(jPanel3Layout.createSequentialGroup()
                                .add(jLabel13)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jlCredito)))))
                .addContainerGap(63, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel9)
                    .add(jLabel12)
                    .add(jlDebito))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel10)
                    .add(jLabel13)
                    .add(jlCredito))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel11)
                    .add(jLabel14)
                    .add(jlSaldoTotalCaixa))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        kGradientPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 180, -1, -1));

        jbVisualizarConta.setBackground(new java.awt.Color(0, 153, 0));
        jbVisualizarConta.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbVisualizarConta.setForeground(new java.awt.Color(255, 255, 255));
        jbVisualizarConta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/excel.png"))); // NOI18N
        jbVisualizarConta.setText("EXPORTAR");
        jbVisualizarConta.setPreferredSize(new java.awt.Dimension(200, 45));
        jbVisualizarConta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbVisualizarContaActionPerformed(evt);
            }
        });
        kGradientPanel1.add(jbVisualizarConta, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, -1, -1));

        jPanel1.add(kGradientPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 380, 1280, 300));

        cbSeguradoras.setToolTipText("");
        cbSeguradoras.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbSeguradorasPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        jPanel1.add(cbSeguradoras, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 80, 500, 30));

        jLabel19.setText("Especificar Seguradora");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 60, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/seguradoras.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 80, -1, -1));

        btClear.setBackground(new java.awt.Color(153, 153, 153));
        btClear.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btClear.setForeground(new java.awt.Color(255, 255, 255));
        btClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/24x24/Erase.png"))); // NOI18N
        btClear.setText("LIMPAR");
        btClear.setPreferredSize(new java.awt.Dimension(110, 40));
        btClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btClearActionPerformed(evt);
            }
        });
        jPanel1.add(btClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 80, 120, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/24x24/3d bar chart.png"))); // NOI18N
        jLabel2.setToolTipText("");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 670));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jbVisualizarContaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbVisualizarContaActionPerformed

        if (tbFluxoDados.getRowCount() > 0) {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de excel", "xls");
            chooser.setFileFilter(filter);
            chooser.setDialogTitle("Guardar archivo");
            chooser.setAcceptAllFileFilterUsed(false);
            if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                List<JTable> tb = new ArrayList<JTable>();
                List<String> nom = new ArrayList<String>();
                tb.add(tbFluxoDados);
                nom.add("Personas");
                String file = chooser.getSelectedFile().toString().concat(".xls");
                try {
                    Exporter e = new Exporter(new File(file), tb, nom);
                    if (e.export()) {
                        JOptionPane.showMessageDialog(null, "Os dados foram exportados com sucesso!", "ARQUIVO EXCEL", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Hubo un error " + e.getMessage(), " Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Não há dados para exportar na tabela", "ATENÇÃO", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jbVisualizarContaActionPerformed

    private void btDefinirDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDefinirDataActionPerformed
        // TODO add your handling code here:
        carregarContas();
        somaEAtualizaValorTotal();
    }//GEN-LAST:event_btDefinirDataActionPerformed

    private void jdcDataInicialFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jdcDataInicialFocusLost
        // TODO add your handling code here:
        carregarContas();
        somaEAtualizaValorTotal();
    }//GEN-LAST:event_jdcDataInicialFocusLost

    private void cbSeguradorasPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbSeguradorasPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
        if (this.cbSeguradoras.isPopupVisible() && (this.cbSeguradoras.getSelectedItem() != null)) {
            modelSeguradora = controllerSeguradora.getSeguradoraController(cbSeguradoras.getSelectedItem().toString());
            DefaultTableModel tabela = (DefaultTableModel) this.tbFluxoDados.getModel();
            final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tabela);
            this.tbFluxoDados.setRowSorter(sorter);
            String text = cbSeguradoras.getSelectedItem().toString();
            sorter.setRowFilter(RowFilter.regexFilter(text, 2));
            somaEAtualizaValorTotal();
        } else {
            DefaultTableModel tabela = (DefaultTableModel) this.tbFluxoDados.getModel();
            this.tbFluxoDados.setRowSorter(null);
            somaEAtualizaValorTotal();
        }

    }//GEN-LAST:event_cbSeguradorasPopupMenuWillBecomeInvisible

    private void btClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btClearActionPerformed
        // TODO add your handling code here:
        DefaultTableModel tabela = (DefaultTableModel) this.tbFluxoDados.getModel();
        this.tbFluxoDados.setRowSorter(null);
        listarSeguradoras();
        cbSeguradoras.setSelectedIndex(-1);
        cbSeguradoras.setSelectedItem(null);
        carregarContas();
        somaEAtualizaValorTotal();
    }//GEN-LAST:event_btClearActionPerformed

    private void listarSeguradoras() {
        listaModelSeguradora = controllerSeguradora.getListaSeguradoraController();
        cbSeguradoras.removeAllItems();
        for (int i = 0; i < listaModelSeguradora.size(); i++) {
            cbSeguradoras.addItem(listaModelSeguradora.get(i).getNome());
        }
    }

    private void carregarContas() {
        Datas bl = new Datas();
        /*Date dataInicial=null; 
         Date dataFinal=null;*/

        Date dataInicial = null;
        Date dataFinal = null;

        try {
            dataInicial = bl.converterDataParaDateUS(bl.converterDataParaDateUS(jdcDataInicial.getDate()));
            dataFinal = bl.converterDataParaDateUS(bl.converterDataParaDateUS(jdcDataFinal.getDate()));
        } catch (Exception ex) {
            /*Logger.getLogger(ViewOp.class.getName()).log(Level.SEVERE, null, ex);*/
        }

        ModelContaPagar modelContaPagar = new ModelContaPagar();
        ModelOp modelOp = new ModelOp();
        DefaultTableModel modelo = (DefaultTableModel) tbFluxoDados.getModel();
        //seta situação 1 como paga
        modelContasPagar.setSituacao(1);

        /*modelContasPagar.setPagamento(bl.converterDataParaDateUS(jdcDataInicial.getDate()));*/
        controllerOp.ListaProcessosPorDatasController(dataInicial, dataFinal);

        /*listaModelContasPagar = controllerContasPagar.getListaContasController(modelContasPagar); substituido pelo de baixo*/
        controllerContaPagar.FiltraContaPagarFluxoDados(modelContaPagar, dataInicial, dataFinal);

        /*listadatModelOp = controllerOp.*/
        listaModelOp = controllerOp.ListaProcessosPorDatasController(dataInicial, dataFinal);
        listaModelContaPagar = controllerContaPagar.FiltraContaPagarFluxoDados(modelContaPagar, dataInicial, dataFinal);
        /*listaModelOp = controllerOp.getListaOpControllerBusca(modelOp); basiei neste aqi*/
        modelo.setNumRows(0);
        String nomeBeneficiario, tipoPagamento = "";
        //CARREGA OS DADOS DA LISTA NA TABELA as contas a pagar
        int contPagar = listaModelContaPagar.size();
        for (int i = 0; i < contPagar; i++) {
            nomeBeneficiario = controllerSeguradora.getSeguradoraController(listaModelContaPagar.get(i).getCodigoPessoa()).getNome();
            tipoPagamento = controllerTipoPagamento.getFormaPagamentoController(listaModelContaPagar.get(i).getTipoPagamento()).getDescricao();

            modelo.addRow(new Object[]{
                listaModelContaPagar.get(i).getCodigo(),
                "PAGA " + listaModelContaPagar.get(i).getDescricao(),
                nomeBeneficiario,
                (new Mascaras().DataRecuperasql(String.valueOf(listaModelContaPagar.get(i).getData()))),
                (new Mascaras().DataRecuperasql(String.valueOf(listaModelContaPagar.get(i).getVencimento()))),
                (new Moeda().valorStringTODoubleAtt(String.valueOf(listaModelContaPagar.get(i).getValor()))),
                "DÉBITO",
                tipoPagamento,
                "PAGAR"
            });
            CorNaLinhaCredito();
        }

        modelContaReceber.setSituacao(1);
        //ModelContaReceber modelContaReceber = new ModelContaReceber();
        listaModelContaReceber = controllerContaReceber.getListaContasController(modelContaReceber);
        listaModelContaReceber = controllerContaReceber.FiltraContaReceberFluxoDados(modelContaReceber, dataInicial, dataFinal);
        controllerContaReceber.FiltraContaReceberFluxoDados(modelContaReceber, dataInicial, dataFinal);
        //CARREGA OS DADOS DA LISTA NA TABELA as contas receber
        int contReceber = listaModelContaReceber.size();
        for (int i = 0; i < contReceber; i++) {
            nomeBeneficiario = controllerFuncionario.getFuncionarioController(listaModelContaReceber.get(i).getCodigoPessoa()).getNome();
            tipoPagamento = controllerTipoPagamento.getFormaPagamentoController(listaModelContaReceber.get(i).getTipoPagamento()).getDescricao();
            modelo.addRow(new Object[]{
                listaModelContaReceber.get(i).getCodigo(),
                "RECEBIDO " + listaModelContaReceber.get(i).getDescricao(),
                nomeBeneficiario,
                (new Mascaras().DataRecuperasql(String.valueOf(listaModelContaReceber.get(i).getData()))),
                (new Mascaras().DataRecuperasql(String.valueOf(listaModelContaReceber.get(i).getVencimento()))),
                (new Moeda().valorStringTODoubleAtt(String.valueOf(listaModelContaReceber.get(i).getValor()))),
                "CRÉDITO",
                tipoPagamento,
                "RECEBER"
            });
            CorNaLinhaCredito();
        }

        //CARREGA OS DADOS DA LISTA NA TABELA as Op
        int cont2 = listaModelOp.size();
        for (int i = 0; i < cont2; i++) {
            nomeBeneficiario = controllerSeguradora.getSeguradoraController(listaModelOp.get(i).getSeguradoras()).getNome();
            tipoPagamento = controllerTipoPagamento.getFormaPagamentoController(listaModelOp.get(i).getTipoPagamento()).getDescricao();
            modelo.addRow(new Object[]{
                listaModelOp.get(i).getCodigo(),
                "RECEBIDO ",
                nomeBeneficiario,
                (new Mascaras().DataRecuperasql(String.valueOf(listaModelOp.get(i).getDataEntrada()))),
                (new Mascaras().DataRecuperasql(String.valueOf(listaModelOp.get(i).getDataSaida()))),
                (new Moeda().valorStringTODoubleAtt(String.valueOf(listaModelOp.get(i).getValorTotalHonorariosSemRetencao()))),
                "CRÉDITO",
                tipoPagamento,
                "PROCESSO"
            });
            CorNaLinhaCredito();
        }
    }

    public void CorNaLinhaCredito() {
        CLASS = "CRÉDITO";
        tbFluxoDados.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value,
                        isSelected, hasFocus, row, column);

                Color c = Color.WHITE;
                Object texto = table.getValueAt(row, 6);
                if (texto != null && CLASS.equals(texto.toString())) {
                    c = Color.green;
                    label.setForeground(c);
                    label.setFont(new Font("Tahoma", Font.BOLD, 12));
                    tbFluxoDados.setSelectionBackground(Color.BLACK);
                } else {
                    c = Color.RED;
                    label.setForeground(c);
                    label.setFont(new Font("Tahoma", Font.BOLD, 12));
                    tbFluxoDados.setSelectionBackground(Color.BLACK);
                }
                return label;
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btClear;
    private javax.swing.JButton btDefinirData;
    private javax.swing.JComboBox<String> cbSeguradoras;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JButton jbVisualizarConta;
    private com.toedter.calendar.JDateChooser jdcDataFinal;
    private com.toedter.calendar.JDateChooser jdcDataInicial;
    private static javax.swing.JLabel jlCredito;
    private static javax.swing.JLabel jlDebito;
    private static javax.swing.JLabel jlSaldoTotalCaixa;
    private keeptoo.KGradientPanel kGradientPanel1;
    private javax.swing.JLabel lbFluxoCaixa;
    private javax.swing.JTable tbFluxoDados;
    // End of variables declaration//GEN-END:variables

    public static ViewFluxoDados fluxodados;

    private void SetIcone() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("ictrol.png")));
    }

}
