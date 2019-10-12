/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ControllerCobertura;
import Controller.ControllerFuncionario;
import Controller.ControllerFormaPagamento;
import Controller.ControllerSeguradora;
import Controller.ControllerOp;
import Controller.ControllerServicos;
import Model.ModelCobertura;
import Model.ModelFuncionario;
import Model.ModelFormaPagamento;
import Model.ModelSeguradora;
import Model.ModelOp;
import Model.ModelServicos;
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
public class ViewControleProcessos extends javax.swing.JFrame {

    String CLASS = "";

    ControllerFuncionario controllerFuncionario = new ControllerFuncionario();
    ControllerSeguradora controllerSeguradora = new ControllerSeguradora();
    ControllerFormaPagamento controllerTipoPagamento = new ControllerFormaPagamento();
    ArrayList<ModelFormaPagamento> listaModelTipoPagamentos = new ArrayList<>();
    ArrayList<ModelFuncionario> listaModelFuncionario = new ArrayList<>();
    ArrayList<ModelSeguradora> listaSeguradora = new ArrayList<>();
    ControllerOp controllerOp = new ControllerOp();
    ArrayList<ModelOp> listaModelOp = new ArrayList<>();
    Mascaras datasMascaras = new Mascaras();

    ModelCobertura modelCobertura = new ModelCobertura();
    ArrayList<ModelCobertura> listaModelCobertura = new ArrayList<>();
    ControllerCobertura controllerCobertura = new ControllerCobertura();

    //SEGURADORAS
    ArrayList<ModelSeguradora> listaModelSeguradora = new ArrayList<>();
    ModelSeguradora modelSeguradora = new ModelSeguradora();

    ModelServicos modelServicos = new ModelServicos();
    ControllerServicos controllerServicos = new ControllerServicos();
    ArrayList<ModelServicos> listamModelServicos = new ArrayList<ModelServicos>();

    /**
     * Creates new form ViewControleProcessos
     */
    public ViewControleProcessos() {
        initComponents();
        SetIcone();
        setLocationRelativeTo(null);
        CarregarProcessos();
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
                new ViewControleProcessos().setVisible(true);
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        kGPanelFiltro = new keeptoo.KGradientPanel();
        btClear = new javax.swing.JButton();
        cbSeguradoras = new javax.swing.JComboBox<String>();
        jLabel1 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        btDefinirData = new javax.swing.JButton();
        jdcDataFinal = new com.toedter.calendar.JDateChooser();
        jdcDataInicial = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        lbFluxoCaixa = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        kGPanelExtrair = new keeptoo.KGradientPanel();
        jbVisualizarConta = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbControleProcessos = new javax.swing.JTable();

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
                .addContainerGap(12, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Controle de Processos");
        setBackground(new java.awt.Color(255, 255, 255));
        setFocusable(false);
        setMinimumSize(new java.awt.Dimension(1290, 730));
        setPreferredSize(new java.awt.Dimension(1290, 730));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(1290, 710));
        jPanel1.setMinimumSize(new java.awt.Dimension(1290, 710));
        jPanel1.setPreferredSize(new java.awt.Dimension(1290, 710));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setMaximumSize(new java.awt.Dimension(1290, 740));
        jPanel2.setMinimumSize(new java.awt.Dimension(1290, 740));
        jPanel2.setPreferredSize(new java.awt.Dimension(1280, 710));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        kGPanelFiltro.setkBorderRadius(0);
        kGPanelFiltro.setkEndColor(new java.awt.Color(228, 235, 241));
        kGPanelFiltro.setkGradientFocus(0);
        kGPanelFiltro.setkStartColor(new java.awt.Color(255, 255, 255));
        kGPanelFiltro.setMaximumSize(new java.awt.Dimension(1280, 140));
        kGPanelFiltro.setMinimumSize(new java.awt.Dimension(1280, 140));
        kGPanelFiltro.setPreferredSize(new java.awt.Dimension(1280, 140));
        kGPanelFiltro.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btClear.setBackground(new java.awt.Color(153, 153, 153));
        btClear.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btClear.setForeground(new java.awt.Color(255, 255, 255));
        btClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/24x24/Find.png"))); // NOI18N
        btClear.setText("LIMPAR");
        btClear.setPreferredSize(new java.awt.Dimension(110, 40));
        btClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btClearActionPerformed(evt);
            }
        });
        kGPanelFiltro.add(btClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 80, 120, -1));

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
        kGPanelFiltro.add(cbSeguradoras, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 80, 500, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/24x24/Application.png"))); // NOI18N
        kGPanelFiltro.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 14, -1, 30));

        jLabel19.setText("Especificar Seguradora");
        kGPanelFiltro.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 60, -1, -1));

        btDefinirData.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btDefinirData.setForeground(new java.awt.Color(51, 51, 51));
        btDefinirData.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/page_find.png"))); // NOI18N
        btDefinirData.setText("APLICAR");
        btDefinirData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDefinirDataActionPerformed(evt);
            }
        });
        kGPanelFiltro.add(btDefinirData, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 80, 130, 35));
        kGPanelFiltro.add(jdcDataFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 80, 150, 28));

        jdcDataInicial.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jdcDataInicialFocusLost(evt);
            }
        });
        kGPanelFiltro.add(jdcDataInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 150, 28));

        jLabel3.setText("Selecione o dia:");
        kGPanelFiltro.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, -1));

        lbFluxoCaixa.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbFluxoCaixa.setText("CONTROLE DE PROCESSOS");
        kGPanelFiltro.add(lbFluxoCaixa, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 290, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/seguradoras.png"))); // NOI18N
        kGPanelFiltro.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 80, -1, -1));

        jPanel2.add(kGPanelFiltro, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 130));

        kGPanelExtrair.setkBorderRadius(0);
        kGPanelExtrair.setkEndColor(new java.awt.Color(228, 235, 241));
        kGPanelExtrair.setkGradientFocus(0);
        kGPanelExtrair.setkStartColor(new java.awt.Color(255, 255, 255));
        kGPanelExtrair.setMaximumSize(new java.awt.Dimension(1280, 100));
        kGPanelExtrair.setMinimumSize(new java.awt.Dimension(1280, 100));
        kGPanelExtrair.setPreferredSize(new java.awt.Dimension(1280, 100));
        kGPanelExtrair.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        kGPanelExtrair.add(jbVisualizarConta, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 20, -1, -1));

        jPanel2.add(kGPanelExtrair, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 610, 1280, 100));

        tbControleProcessos.setAutoCreateRowSorter(true);
        tbControleProcessos.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        tbControleProcessos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cod. Processo", "Data Entrada", "Nº Sinistro", "Cobertura", "Endereço", "Data Sinistro", "Analista", "Seguradora", "Segurado", "Placa Segurado", "Agente", "Data Pgto.", "NF Processo", "Data Saida", "Honoráros", "Observações", "Total Despesas", "Previsão Pgto. Honorários", "Situação Pgto.", "Total Desp.", "Valor Negativa", "Emissão Neg.", "Pgto. Negativa", "Observações Neg.", "Situação Neg."
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbControleProcessos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbControleProcessos.setGridColor(new java.awt.Color(247, 247, 247));
        tbControleProcessos.setSelectionBackground(new java.awt.Color(0, 153, 0));
        jScrollPane1.setViewportView(tbControleProcessos);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 147, 1250, 460));

        jPanel1.add(jPanel2);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jbVisualizarContaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbVisualizarContaActionPerformed

        if (tbControleProcessos.getRowCount() > 0) {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de excel", "xls");
            chooser.setFileFilter(filter);
            chooser.setDialogTitle("Guardar archivo");
            chooser.setAcceptAllFileFilterUsed(false);
            if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                List<JTable> tb = new ArrayList<JTable>();
                List<String> nom = new ArrayList<String>();
                tb.add(tbControleProcessos);
                nom.add("Personas");
                String file = chooser.getSelectedFile().toString().concat(".xls");
                try {
                    Exporter e = new Exporter(new File(file), tb, nom);
                    if (e.export()) {
                        JOptionPane.showMessageDialog(null, "Os processos foram exportados com sucesso!", "ARQUIVO EXCEL", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Hubo un error " + e.getMessage(), " Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Não há processos para exportar na tabela", "ATENÇÃO", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jbVisualizarContaActionPerformed

    private void btDefinirDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDefinirDataActionPerformed
        // TODO add your handling code here:
        CarregarProcessos();
    }//GEN-LAST:event_btDefinirDataActionPerformed

    private void jdcDataInicialFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jdcDataInicialFocusLost
        // TODO add your handling code here:
        CarregarProcessos();
    }//GEN-LAST:event_jdcDataInicialFocusLost

    private void cbSeguradorasPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbSeguradorasPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
        if (this.cbSeguradoras.isPopupVisible() && (this.cbSeguradoras.getSelectedItem() != null)) {
            modelSeguradora = controllerSeguradora.getSeguradoraController(cbSeguradoras.getSelectedItem().toString());
            DefaultTableModel tabela = (DefaultTableModel) this.tbControleProcessos.getModel();
            final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tabela);
            this.tbControleProcessos.setRowSorter(sorter);
            String text = cbSeguradoras.getSelectedItem().toString();
            sorter.setRowFilter(RowFilter.regexFilter(text, 7));
        } else {
            DefaultTableModel tabela = (DefaultTableModel) this.tbControleProcessos.getModel();
            this.tbControleProcessos.setRowSorter(null);
        }

    }//GEN-LAST:event_cbSeguradorasPopupMenuWillBecomeInvisible

    private void btClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btClearActionPerformed
        // TODO add your handling code here:
        DefaultTableModel tabela = (DefaultTableModel) this.tbControleProcessos.getModel();
        this.tbControleProcessos.setRowSorter(null);
        listarSeguradoras();
        cbSeguradoras.setSelectedIndex(-1);
        cbSeguradoras.setSelectedItem(null);
        CarregarProcessos();
    }//GEN-LAST:event_btClearActionPerformed

    private void listarSeguradoras() {
        listaModelSeguradora = controllerSeguradora.getListaSeguradoraController();
        cbSeguradoras.removeAllItems();
        for (int i = 0; i < listaModelSeguradora.size(); i++) {
            cbSeguradoras.addItem(listaModelSeguradora.get(i).getNome());
        }
    }

    private void CarregarProcessos() {
        this.tbControleProcessos.setRowSorter(null);
        Datas bl = new Datas();
        Date dataInicial = null;
        Date dataFinal = null;
        String DataPgtoNF = "";
        try {
            dataInicial = bl.converterDataParaDateUS(bl.converterDataParaDateUS(jdcDataInicial.getDate()));
            dataFinal = bl.converterDataParaDateUS(bl.converterDataParaDateUS(jdcDataFinal.getDate()));
        } catch (Exception ex) {
        }
        ModelOp modelOp = new ModelOp();
        DefaultTableModel modelo = (DefaultTableModel) tbControleProcessos.getModel();
        modelo.setNumRows(0);
        listaModelOp = controllerOp.ListaProcessosPorDatasController(dataInicial, dataFinal);
        String nomeBeneficiario, tipoPagamento, Cobertura, Seguradora, Agente = "";
        //CARREGA OS DADOS DA LISTA NA TABELA as Op
        int cont = listaModelOp.size();
        for (int i = 0; i < cont; i++) {
            nomeBeneficiario = controllerSeguradora.getSeguradoraController(listaModelOp.get(i).getSeguradoras()).getNome();
            tipoPagamento = controllerTipoPagamento.getFormaPagamentoController(listaModelOp.get(i).getTipoPagamento()).getDescricao();
            Cobertura = controllerServicos.getServicosController(listaModelOp.get(i).getServicosCodigo()).getNome();
            Seguradora = controllerSeguradora.getSeguradoraController(listaModelOp.get(i).getSeguradoras()).getNome();
            Agente = controllerFuncionario.getFuncionarioController(listaModelOp.get(i).getCod_agente()).getNome();
            DataPgtoNF = (new Mascaras().DataRecuperasql(String.valueOf(listaModelOp.get(i).getDataPgtoNF())));

            modelo.addRow(new Object[]{
                listaModelOp.get(i).getCodigo(),
                (new Mascaras().DataRecuperasql(String.valueOf(listaModelOp.get(i).getDataEntrada()))),
                listaModelOp.get(i).getNumeroSinistro(),
                Cobertura,
                listaModelOp.get(i).getSinistroBairro(),
                (new Mascaras().DataRecuperasql(String.valueOf(listaModelOp.get(i).getDataSinistro()))),
                listaModelOp.get(i).getAnalista(),
                Seguradora,
                listaModelOp.get(i).getNomeSegurado(),
                listaModelOp.get(i).getSeguradoPlaca(),
                Agente,
                DataPgtoNF,
                listaModelOp.get(i).getNumeroNFProcesso(),
                (new Mascaras().DataRecuperasql(String.valueOf(listaModelOp.get(i).getDataSaida()))),
                (new Moeda().valorStringTODoubleAtt(String.valueOf(listaModelOp.get(i).getValorHonorarioProcesso()))),
                listaModelOp.get(i).getObsHonorariosProcesso(),
                (new Moeda().valorStringTODoubleAtt(String.valueOf(listaModelOp.get(i).getValorHonorarioProcesso()))),
                (new Mascaras().DataRecuperasql(String.valueOf(listaModelOp.get(i).getDataPrevisaoPgtoNFhonorarios()))),
                (String.valueOf(listaModelOp.get(i).getSituacaoNotaFiscal())),
                (new Moeda().valorStringTODoubleAtt(String.valueOf(listaModelOp.get(i).getValorDespesasTotalRegistro()))),
                (new Moeda().valorStringTODoubleAtt(String.valueOf(listaModelOp.get(i).getValorNegativaTotal()))),
                (new Mascaras().DataRecuperasql(String.valueOf(listaModelOp.get(i).getDataEmissaoNegativa()))),
                (new Mascaras().DataRecuperasql(String.valueOf(listaModelOp.get(i).getDataPgtoNegativa()))),
                listaModelOp.get(i).getObsRetencaoISSNeg(),
                listaModelOp.get(i).getSituacaoPgtoNegativa()
            });
            // CorNaLinhaCredito();
        }
    }

    public void CorNaLinhaCredito() {
        CLASS = "CRÉDITO";
        tbControleProcessos.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
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
                    tbControleProcessos.setSelectionBackground(Color.BLACK);
                } else {
                    c = Color.RED;
                    label.setForeground(c);
                    label.setFont(new Font("Tahoma", Font.BOLD, 12));
                    tbControleProcessos.setSelectionBackground(Color.BLACK);
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbVisualizarConta;
    private com.toedter.calendar.JDateChooser jdcDataFinal;
    private com.toedter.calendar.JDateChooser jdcDataInicial;
    private static javax.swing.JLabel jlCredito;
    private static javax.swing.JLabel jlDebito;
    private static javax.swing.JLabel jlSaldoTotalCaixa;
    private keeptoo.KGradientPanel kGPanelExtrair;
    private keeptoo.KGradientPanel kGPanelFiltro;
    private javax.swing.JLabel lbFluxoCaixa;
    private javax.swing.JTable tbControleProcessos;
    // End of variables declaration//GEN-END:variables

    // public static ViewControleProcessos controleprocessos;
    private void SetIcone() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("ictrol.png")));
    }

}