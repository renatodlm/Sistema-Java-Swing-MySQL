/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import util.Datas;
import util.Mascaras;
import Controller.ControllerFuncionario;
import Controller.ControllerContaAgentes;
import Controller.ControllerCobertura;
import Model.ModelFuncionario;
import Model.ModelContaAgentes;
import Model.ModelCobertura;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import Controller.ControllerBancos;
import Controller.ControllerFormaPagamento;
import Controller.ControllerOp;
import Controller.ControllerSeguradora;
import Controller.ControllerServicos;
import Controller.ControllerTipoDespesas;
import Model.ModelBancos;
import Model.ModelFormaPagamento;
import Model.ModelOp;
import Model.ModelSeguradora;
import Model.ModelServicos;
import Model.ModelTipoDespesas;
import conexao.ConexaoBanco;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import util.Exporter;
import util.Moeda;

/**
 *
 * @author Administrador
 */
public final class ViewDespesasAgentes extends javax.swing.JFrame {

    //DESPESAS
    ModelContaAgentes modelContaAgentes = new ModelContaAgentes();
    ControllerFuncionario controllerFuncionario = new ControllerFuncionario();
    ArrayList<ModelContaAgentes> listaModelContaAgentes = new ArrayList<>();
    ControllerContaAgentes controllerContaAgentes = new ControllerContaAgentes();
    //COBERTURAS
    ControllerCobertura controllerCobertura = new ControllerCobertura();
    ArrayList<ModelCobertura> listaModelCobertura = new ArrayList<>();
    //FUNCIONARIOS
    ArrayList<ModelFuncionario> listaModelFuncionario = new ArrayList<>();
    ModelFuncionario modelFuncionario = new ModelFuncionario();
    String tipoCadastro;
    //Processos
    ControllerOp controllerOp = new ControllerOp();
    ArrayList<ModelOp> listaModelOp = new ArrayList<>();
    ModelOp modelOp = new ModelOp();
    //Bancos
    ControllerBancos controllerBancos = new ControllerBancos();
    ArrayList<ModelBancos> listaModelBancos = new ArrayList<>();
    //Processos
    ControllerSeguradora controllerSeguradora = new ControllerSeguradora();
    ArrayList<ModelSeguradora> listaModelSeguradora = new ArrayList<>();
    ModelSeguradora modelSeguradora = new ModelSeguradora();
    //TIPODESPESAS
    ControllerTipoDespesas controllerTipoDespesas = new ControllerTipoDespesas();
    ArrayList<ModelTipoDespesas> listaModelTipoDespesas = new ArrayList<>();

    ControllerFormaPagamento controllerTipoPagamento = new ControllerFormaPagamento();
    ArrayList<ModelFormaPagamento> listaModelTipoPagamentos = new ArrayList<>();
    ModelFormaPagamento modelFormaPagamento = new ModelFormaPagamento();

    ModelServicos modelServicos = new ModelServicos();
    ArrayList<ModelServicos> listaModelServicos = new ArrayList<>();
    ControllerServicos controllerServicos = new ControllerServicos();

    /**
     * /**
     * Creates new form ViewContasRecebe
     */
    public ViewDespesasAgentes() {
        initComponents();
        SetIcone();
        listarFuncionarios();
        limparPendentes();
        limparPagas();
        jTabbedPanelDespesas.setSelectedIndex(0);
        panelTabbedKGVerficiar();
        kButtonPendentes.setSelected(true);
        limparDados();
        cbFuncionariosPendentes.setSelectedIndex(-1);
        cbFuncionariosEfetuado.setSelectedIndex(-1);
        jLCampoNegativo.setVisible(false);
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
            java.util.logging.Logger.getLogger(ViewCadVinculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewCadVinculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewCadVinculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewCadVinculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewDespesasAgentes().setVisible(true);
            }
        });
    }

    private void listarFuncionarios() {
        listaModelFuncionario = controllerFuncionario.getListaSomenteAgentesController();
        cbFuncionario.removeAllItems();
        cbCodFuncionario.removeAllItems();
        //cbFuncionariosEfetuado.removeAllItems();
        //cbFuncionariosPendentes.removeAllItems();
        for (int i = 0; i < listaModelFuncionario.size(); i++) {
            modelFuncionario = controllerFuncionario.getFuncionarioController(listaModelFuncionario.get(i).getCodigo());
            cbFuncionariosEfetuado.addItem(modelFuncionario.getNome());
            cbFuncionariosPendentes.addItem(modelFuncionario.getNome());
            cbFuncionario.addItem(modelFuncionario.getNome());
            cbCodFuncionario.addItem(modelFuncionario.getCodigo());
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

        cbCodAgenteDesignar = new javax.swing.JComboBox();
        cbCodFuncionario = new javax.swing.JComboBox();
        cbFuncionario = new componentes.UJComboBox();
        jScrollPane15 = new javax.swing.JScrollPane();
        tbProcessosDoAgenteRegistrarConta = new javax.swing.JTable();
        jScrollPaneAnalista3 = new javax.swing.JScrollPane();
        jLDescricaoAdiantamento = new javax.swing.JTextArea();
        jPanelPrincipal = new javax.swing.JPanel();
        kGradientPanel11 = new keeptoo.KGradientPanel();
        kGradientPanel18 = new keeptoo.KGradientPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        kButtonPendentes = new keeptoo.KButton();
        kButtonPagas = new keeptoo.KButton();
        kButtonProcessos2 = new keeptoo.KButton();
        kButtonRegistrar = new keeptoo.KButton();
        jTabbedPanelDespesas = new javax.swing.JTabbedPane();
        DespesasPendentes = new javax.swing.JPanel();
        kGradientPanel7 = new keeptoo.KGradientPanel();
        jScrollPane19 = new javax.swing.JScrollPane();
        tbDespesasPendentes = new javax.swing.JTable();
        jLTotalNeg1 = new javax.swing.JLabel();
        jFTotalnegPendentes = new javax.swing.JLabel();
        jLtotalHr1 = new javax.swing.JLabel();
        jFTotalHrPendentes = new javax.swing.JLabel();
        jLtotalKmPerco1 = new javax.swing.JLabel();
        jFTotalKmPercoPendentes = new javax.swing.JLabel();
        jLTotalPgtoKm1 = new javax.swing.JLabel();
        jFTotalPgtoKmPendentes = new javax.swing.JLabel();
        kGradientPanel6 = new keeptoo.KGradientPanel();
        tfAreceberPendentes = new javax.swing.JTextField();
        tfNegHrKmPendentes = new javax.swing.JTextField();
        tfAdiantamentosPendentes = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        cbFuncionariosPendentes = new javax.swing.JComboBox<String>();
        btClearPendentes = new javax.swing.JButton();
        btDefinirDataPendentes = new javax.swing.JButton();
        jdcDataFinalPendentes = new com.toedter.calendar.JDateChooser();
        jdcDataInicialPendentes = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        kGradientPanel5 = new keeptoo.KGradientPanel();
        btAlterar = new javax.swing.JButton();
        jbPagarConta = new javax.swing.JButton();
        jbExcluirPagar = new javax.swing.JButton();
        DespesasEfetuados = new javax.swing.JPanel();
        kGradientPanel8 = new keeptoo.KGradientPanel();
        jScrollPane18 = new javax.swing.JScrollPane();
        tbDespesasEfetuadas = new javax.swing.JTable();
        jFTotalKmPercoEfetuado = new javax.swing.JLabel();
        jLtotalKmPercoEfetuado = new javax.swing.JLabel();
        jFTotalPgtoKmEfetuado = new javax.swing.JLabel();
        jLTotalPgtoKmEfetuado = new javax.swing.JLabel();
        jLtotalHrEfetuado = new javax.swing.JLabel();
        jFTotalHrEfetuado = new javax.swing.JLabel();
        jFTotalnegEfetuado = new javax.swing.JLabel();
        jLTotalNegEfetuado = new javax.swing.JLabel();
        kGradientPanel10 = new keeptoo.KGradientPanel();
        cbFuncionariosEfetuado = new javax.swing.JComboBox<String>();
        btClearEfetuado = new javax.swing.JButton();
        tfAreceberEfetuado = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        tfAdiantamentosEfetuado = new javax.swing.JTextField();
        tfNegHrkmEfetuado = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        btDefinirDataEfetuado = new javax.swing.JButton();
        jdcDataFinalEfetuado = new com.toedter.calendar.JDateChooser();
        jdcDataInicialEfetuado = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        kGradientPanel9 = new keeptoo.KGradientPanel();
        jpRevorgarConta = new javax.swing.JButton();
        btExportarDados = new javax.swing.JButton();
        RegistrarContas = new javax.swing.JPanel();
        kGFuncionarioAgente = new keeptoo.KGradientPanel();
        tfSeguradora1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jFDataEnvio = new javax.swing.JFormattedTextField();
        jFDataRecebimento = new javax.swing.JFormattedTextField();
        cbBancos = new componentes.UJComboBox();
        cbTipoDespesa = new componentes.UJComboBox();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        labelPagamento = new javax.swing.JLabel();
        kGTotalGeral1 = new keeptoo.KGradientPanel();
        jLAgenteTotalKm1 = new javax.swing.JLabel();
        kGradientPanel3 = new keeptoo.KGradientPanel();
        jLAgenteRepasseTotal1 = new javax.swing.JLabel();
        jScrollPaneAnalista1 = new javax.swing.JScrollPane();
        tfSeguradoraProcesso = new javax.swing.JTextArea();
        tfDataProcesso = new javax.swing.JLabel();
        jLTotalGeral2 = new javax.swing.JLabel();
        kGradientPanel4 = new keeptoo.KGradientPanel();
        jLTotalGeral1 = new javax.swing.JLabel();
        jScrollPaneAnalista2 = new javax.swing.JScrollPane();
        tfSinistroProcesso = new javax.swing.JTextArea();
        tfCodigoProcesso = new javax.swing.JLabel();
        jScrollPaneAnalista = new javax.swing.JScrollPane();
        jLNomeAgente = new javax.swing.JTextArea();
        kGradientPanel14 = new keeptoo.KGradientPanel();
        jtfCodigoConta = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tfObservacao = new javax.swing.JTextArea();
        jLabel10 = new javax.swing.JLabel();
        kGPAlterarAdiantamentos = new keeptoo.KGradientPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbAdiantamentoAlterar = new javax.swing.JTable();
        btAdiantamentoAlterar = new javax.swing.JButton();
        btExcluirAdiantamento = new javax.swing.JButton();
        tfValorDespesaAlterar = new javax.swing.JFormattedTextField();
        tfDataDespesaAlterar = new javax.swing.JFormattedTextField();
        tfDescricaoDespesaAlterar = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        tfValidarDescricaoAlterar = new javax.swing.JLabel();
        tfValidarValorAlterar = new javax.swing.JLabel();
        tfValidarDataAlterar = new javax.swing.JLabel();
        kGradientPanel15 = new keeptoo.KGradientPanel();
        totalAdiantamentosAlterar = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        kButtonProcessos4 = new keeptoo.KButton();
        kGradientPanel16 = new keeptoo.KGradientPanel();
        jFValorNeg = new javax.swing.JFormattedTextField();
        jLabel11 = new javax.swing.JLabel();
        jFDataNeg = new javax.swing.JFormattedTextField();
        jLabel12 = new javax.swing.JLabel();
        kGRepasseAoAgente = new keeptoo.KGradientPanel();
        jLAgenteHonorarios = new javax.swing.JLabel();
        jfAgenteHonorario = new javax.swing.JFormattedTextField();
        jLAgenteSeguradoraKm = new javax.swing.JLabel();
        jLAgenteKMpercorrido = new javax.swing.JLabel();
        jfAgenteKmPercorrido = new javax.swing.JFormattedTextField();
        kGTotalGeral = new keeptoo.KGradientPanel();
        jLTotalGeral = new javax.swing.JLabel();
        tfTotalAReceber = new javax.swing.JLabel();
        jLAgenteTotalKm = new javax.swing.JLabel();
        jFPagamentoTotalKM = new javax.swing.JLabel();
        kGradientPanel2 = new keeptoo.KGradientPanel();
        jLAgenteRepasseTotal = new javax.swing.JLabel();
        jfAgenteTotalRepasse = new javax.swing.JLabel();
        jLCampoNegativo = new javax.swing.JLabel();
        jfPgtoKmSegudradora = new javax.swing.JFormattedTextField();
        kGradientPanel1 = new keeptoo.KGradientPanel();
        JbSAlvar = new javax.swing.JButton();
        btCancelarVoltar = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();

        cbCodAgenteDesignar.setEnabled(false);
        cbCodAgenteDesignar.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbCodAgenteDesignarPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        cbCodFuncionario.setEnabled(false);
        cbCodFuncionario.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbCodFuncionarioPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        cbFuncionario.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbFuncionarioPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jScrollPane15.setBackground(new java.awt.Color(234, 239, 243));
        jScrollPane15.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane15.setPreferredSize(new java.awt.Dimension(1220, 180));

        tbProcessosDoAgenteRegistrarConta.setAutoCreateRowSorter(true);
        tbProcessosDoAgenteRegistrarConta.setBackground(new java.awt.Color(234, 239, 243));
        tbProcessosDoAgenteRegistrarConta.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        tbProcessosDoAgenteRegistrarConta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cod.", "Seguradora", "Data Entrada", "Nº Sinistro", "Honorário", "SITUAÇÃO DO PROCESSO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbProcessosDoAgenteRegistrarConta.setGridColor(new java.awt.Color(234, 239, 243));
        tbProcessosDoAgenteRegistrarConta.setMinimumSize(new java.awt.Dimension(610, 0));
        tbProcessosDoAgenteRegistrarConta.setRowHeight(20);
        tbProcessosDoAgenteRegistrarConta.setSelectionBackground(new java.awt.Color(0, 153, 0));
        tbProcessosDoAgenteRegistrarConta.getTableHeader().setReorderingAllowed(false);
        tbProcessosDoAgenteRegistrarConta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbProcessosDoAgenteRegistrarContaMouseClicked(evt);
            }
        });
        jScrollPane15.setViewportView(tbProcessosDoAgenteRegistrarConta);

        jScrollPaneAnalista3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPaneAnalista3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPaneAnalista3.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jScrollPaneAnalista3.setPreferredSize(new java.awt.Dimension(675, 25));

        jLDescricaoAdiantamento.setEditable(false);
        jLDescricaoAdiantamento.setBackground(new java.awt.Color(242, 245, 247));
        jLDescricaoAdiantamento.setColumns(20);
        jLDescricaoAdiantamento.setFont(new java.awt.Font("Tahoma", 1, 8)); // NOI18N
        jLDescricaoAdiantamento.setRows(5);
        jLDescricaoAdiantamento.setText("\n");
        jLDescricaoAdiantamento.setFocusable(false);
        jScrollPaneAnalista3.setViewportView(jLDescricaoAdiantamento);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("PLANILHAS E CONTROLE DE AGENTES");
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFocusable(false);
        setMinimumSize(new java.awt.Dimension(1360, 690));
        setPreferredSize(new java.awt.Dimension(1360, 690));
        setResizable(false);

        jPanelPrincipal.setBackground(new java.awt.Color(146, 171, 197));
        jPanelPrincipal.setMinimumSize(new java.awt.Dimension(1360, 650));
        jPanelPrincipal.setPreferredSize(new java.awt.Dimension(1360, 660));
        jPanelPrincipal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        kGradientPanel11.setkBorderRadius(0);
        kGradientPanel11.setkEndColor(new java.awt.Color(146, 171, 197));
        kGradientPanel11.setkGradientFocus(0);
        kGradientPanel11.setkStartColor(new java.awt.Color(146, 171, 197));
        kGradientPanel11.setMinimumSize(new java.awt.Dimension(1170, 1170));
        kGradientPanel11.setName(""); // NOI18N
        kGradientPanel11.setPreferredSize(new java.awt.Dimension(1360, 50));
        kGradientPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        kGradientPanel18.setkBorderRadius(0);
        kGradientPanel18.setkEndColor(new java.awt.Color(255, 255, 255));
        kGradientPanel18.setkStartColor(new java.awt.Color(146, 171, 197));

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 51, 102));
        jLabel24.setText("VER REGISTROS");

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/24x24/Search.png"))); // NOI18N

        org.jdesktop.layout.GroupLayout kGradientPanel18Layout = new org.jdesktop.layout.GroupLayout(kGradientPanel18);
        kGradientPanel18.setLayout(kGradientPanel18Layout);
        kGradientPanel18Layout.setHorizontalGroup(
            kGradientPanel18Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(kGradientPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel25)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel24)
                .addContainerGap(39, Short.MAX_VALUE))
        );
        kGradientPanel18Layout.setVerticalGroup(
            kGradientPanel18Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(kGradientPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .add(kGradientPanel18Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jLabel24)
                    .add(jLabel25))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        kGradientPanel11.add(kGradientPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 50));

        kButtonPendentes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Fall.png"))); // NOI18N
        kButtonPendentes.setText("             DESPESAS PGTO. PENDENTE");
        kButtonPendentes.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        kButtonPendentes.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        kButtonPendentes.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        kButtonPendentes.setIconTextGap(10);
        kButtonPendentes.setkBackGroundColor(new java.awt.Color(255, 255, 255));
        kButtonPendentes.setkBorderRadius(0);
        kButtonPendentes.setkEndColor(new java.awt.Color(197, 201, 206));
        kButtonPendentes.setkForeGround(new java.awt.Color(102, 102, 102));
        kButtonPendentes.setkHoverEndColor(new java.awt.Color(234, 239, 243));
        kButtonPendentes.setkHoverForeGround(new java.awt.Color(102, 102, 102));
        kButtonPendentes.setkHoverStartColor(new java.awt.Color(234, 239, 243));
        kButtonPendentes.setkPressedColor(new java.awt.Color(153, 153, 153));
        kButtonPendentes.setkSelectedColor(new java.awt.Color(255, 255, 255));
        kButtonPendentes.setkStartColor(new java.awt.Color(197, 201, 206));
        kButtonPendentes.setPreferredSize(new java.awt.Dimension(200, 45));
        kButtonPendentes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButtonPendentesActionPerformed(evt);
            }
        });
        kGradientPanel11.add(kButtonPendentes, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 0, 220, 50));

        kButtonPagas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Down.png"))); // NOI18N
        kButtonPagas.setText("     DESPESAS JÁ PAGAS");
        kButtonPagas.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        kButtonPagas.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        kButtonPagas.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        kButtonPagas.setIconTextGap(10);
        kButtonPagas.setkBackGroundColor(new java.awt.Color(255, 255, 255));
        kButtonPagas.setkBorderRadius(0);
        kButtonPagas.setkEndColor(new java.awt.Color(197, 201, 206));
        kButtonPagas.setkForeGround(new java.awt.Color(102, 102, 102));
        kButtonPagas.setkHoverEndColor(new java.awt.Color(234, 239, 243));
        kButtonPagas.setkHoverForeGround(new java.awt.Color(102, 102, 102));
        kButtonPagas.setkHoverStartColor(new java.awt.Color(234, 239, 243));
        kButtonPagas.setkPressedColor(new java.awt.Color(153, 153, 153));
        kButtonPagas.setkSelectedColor(new java.awt.Color(255, 255, 255));
        kButtonPagas.setkStartColor(new java.awt.Color(197, 201, 206));
        kButtonPagas.setPreferredSize(new java.awt.Dimension(170, 45));
        kButtonPagas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButtonPagasActionPerformed(evt);
            }
        });
        kGradientPanel11.add(kButtonPagas, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 0, -1, 50));

        kButtonProcessos2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/24x24/Refresh.png"))); // NOI18N
        kButtonProcessos2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        kButtonProcessos2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        kButtonProcessos2.setIconTextGap(25);
        kButtonProcessos2.setkBackGroundColor(new java.awt.Color(146, 171, 197));
        kButtonProcessos2.setkBorderRadius(0);
        kButtonProcessos2.setkEndColor(new java.awt.Color(255, 255, 255));
        kButtonProcessos2.setkForeGround(new java.awt.Color(51, 51, 51));
        kButtonProcessos2.setkHoverEndColor(new java.awt.Color(234, 239, 243));
        kButtonProcessos2.setkHoverForeGround(new java.awt.Color(51, 51, 51));
        kButtonProcessos2.setkHoverStartColor(new java.awt.Color(234, 239, 243));
        kButtonProcessos2.setkPressedColor(new java.awt.Color(255, 255, 255));
        kButtonProcessos2.setkSelectedColor(new java.awt.Color(255, 255, 255));
        kButtonProcessos2.setkStartColor(new java.awt.Color(146, 171, 197));
        kButtonProcessos2.setPreferredSize(new java.awt.Dimension(210, 45));
        kButtonProcessos2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButtonProcessos2ActionPerformed(evt);
            }
        });
        kGradientPanel11.add(kButtonProcessos2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1281, 0, 80, 50));

        kButtonRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Text.png"))); // NOI18N
        kButtonRegistrar.setText("EDITANDO");
        kButtonRegistrar.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        kButtonRegistrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        kButtonRegistrar.setIconTextGap(28);
        kButtonRegistrar.setkBackGroundColor(new java.awt.Color(229, 229, 229));
        kButtonRegistrar.setkBorderRadius(0);
        kButtonRegistrar.setkEndColor(new java.awt.Color(197, 201, 206));
        kButtonRegistrar.setkForeGround(new java.awt.Color(102, 102, 102));
        kButtonRegistrar.setkHoverEndColor(new java.awt.Color(234, 239, 243));
        kButtonRegistrar.setkHoverForeGround(new java.awt.Color(102, 102, 102));
        kButtonRegistrar.setkHoverStartColor(new java.awt.Color(234, 239, 243));
        kButtonRegistrar.setkPressedColor(new java.awt.Color(153, 153, 153));
        kButtonRegistrar.setkSelectedColor(new java.awt.Color(255, 255, 255));
        kButtonRegistrar.setkStartColor(new java.awt.Color(197, 201, 206));
        kButtonRegistrar.setPreferredSize(new java.awt.Dimension(170, 50));
        kButtonRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButtonRegistrarActionPerformed(evt);
            }
        });
        kGradientPanel11.add(kButtonRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 0, -1, -1));

        jPanelPrincipal.add(kGradientPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jTabbedPanelDespesas.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPanelDespesas.setMinimumSize(new java.awt.Dimension(1180, 650));
        jTabbedPanelDespesas.setPreferredSize(new java.awt.Dimension(1180, 630));

        DespesasPendentes.setBackground(new java.awt.Color(255, 255, 255));
        DespesasPendentes.setMinimumSize(new java.awt.Dimension(1170, 370));
        DespesasPendentes.setPreferredSize(new java.awt.Dimension(1170, 370));

        kGradientPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "CONTAS COM PAGAMENTO PENDENTE", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(204, 102, 0))); // NOI18N
        kGradientPanel7.setkBorderRadius(0);
        kGradientPanel7.setkEndColor(new java.awt.Color(240, 230, 214));
        kGradientPanel7.setkGradientFocus(0);
        kGradientPanel7.setkStartColor(new java.awt.Color(255, 255, 255));
        kGradientPanel7.setMinimumSize(new java.awt.Dimension(1170, 430));
        kGradientPanel7.setPreferredSize(new java.awt.Dimension(1360, 430));
        kGradientPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane19.setBackground(new java.awt.Color(247, 247, 247));
        jScrollPane19.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane19.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane19.setPreferredSize(new java.awt.Dimension(1220, 180));

        tbDespesasPendentes.setAutoCreateRowSorter(true);
        tbDespesasPendentes.setBackground(new java.awt.Color(247, 247, 247));
        tbDespesasPendentes.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        tbDespesasPendentes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Agente", "Despesa", "Processo", "Envio", "Seguradora", "Sinistro", "Serviço", "Negativa", "Honorário", "KM Perco.", "Valor KM", "Total KM", "Recebimento", "Descrição", "Total Adiantamento por despesa", "Neg+HR+KM por Despesa", "A receber por despesa"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbDespesasPendentes.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbDespesasPendentes.setGridColor(new java.awt.Color(247, 247, 247));
        tbDespesasPendentes.setMinimumSize(new java.awt.Dimension(610, 0));
        tbDespesasPendentes.setRowHeight(20);
        tbDespesasPendentes.setSelectionBackground(new java.awt.Color(0, 153, 0));
        tbDespesasPendentes.getTableHeader().setReorderingAllowed(false);
        jScrollPane19.setViewportView(tbDespesasPendentes);
        if (tbDespesasPendentes.getColumnModel().getColumnCount() > 0) {
            tbDespesasPendentes.getColumnModel().getColumn(0).setMinWidth(150);
            tbDespesasPendentes.getColumnModel().getColumn(1).setMinWidth(50);
            tbDespesasPendentes.getColumnModel().getColumn(2).setMinWidth(80);
            tbDespesasPendentes.getColumnModel().getColumn(3).setMinWidth(80);
            tbDespesasPendentes.getColumnModel().getColumn(4).setMinWidth(200);
            tbDespesasPendentes.getColumnModel().getColumn(5).setMinWidth(100);
            tbDespesasPendentes.getColumnModel().getColumn(6).setMinWidth(100);
            tbDespesasPendentes.getColumnModel().getColumn(7).setMinWidth(80);
            tbDespesasPendentes.getColumnModel().getColumn(8).setMinWidth(80);
            tbDespesasPendentes.getColumnModel().getColumn(9).setMinWidth(80);
            tbDespesasPendentes.getColumnModel().getColumn(10).setMinWidth(80);
            tbDespesasPendentes.getColumnModel().getColumn(11).setMinWidth(80);
            tbDespesasPendentes.getColumnModel().getColumn(12).setMinWidth(80);
            tbDespesasPendentes.getColumnModel().getColumn(14).setMinWidth(80);
            tbDespesasPendentes.getColumnModel().getColumn(15).setMinWidth(80);
            tbDespesasPendentes.getColumnModel().getColumn(16).setMinWidth(80);
        }

        kGradientPanel7.add(jScrollPane19, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 1320, 370));

        jLTotalNeg1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLTotalNeg1.setText("Total Negativa:");
        kGradientPanel7.add(jLTotalNeg1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 410, -1, -1));

        jFTotalnegPendentes.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jFTotalnegPendentes.setForeground(new java.awt.Color(182, 92, 2));
        jFTotalnegPendentes.setText("R$ 0,00");
        kGradientPanel7.add(jFTotalnegPendentes, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 410, 120, -1));

        jLtotalHr1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLtotalHr1.setText("Total Honorário:");
        kGradientPanel7.add(jLtotalHr1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 410, -1, -1));

        jFTotalHrPendentes.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jFTotalHrPendentes.setForeground(new java.awt.Color(182, 92, 2));
        jFTotalHrPendentes.setText("R$ 0,00");
        kGradientPanel7.add(jFTotalHrPendentes, new org.netbeans.lib.awtextra.AbsoluteConstraints(525, 410, 150, -1));

        jLtotalKmPerco1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLtotalKmPerco1.setText("Total Percorrido:");
        kGradientPanel7.add(jLtotalKmPerco1, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 410, -1, -1));

        jFTotalKmPercoPendentes.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jFTotalKmPercoPendentes.setForeground(new java.awt.Color(182, 92, 2));
        jFTotalKmPercoPendentes.setText("0");
        kGradientPanel7.add(jFTotalKmPercoPendentes, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 410, 110, -1));

        jLTotalPgtoKm1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLTotalPgtoKm1.setText("Total Pago em KM:");
        kGradientPanel7.add(jLTotalPgtoKm1, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 410, -1, -1));

        jFTotalPgtoKmPendentes.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jFTotalPgtoKmPendentes.setForeground(new java.awt.Color(182, 92, 2));
        jFTotalPgtoKmPendentes.setText("R$ 0,00");
        kGradientPanel7.add(jFTotalPgtoKmPendentes, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 410, 140, -1));

        DespesasPendentes.add(kGradientPanel7);

        kGradientPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "FILTRAR", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(51, 51, 51))); // NOI18N
        kGradientPanel6.setkBorderRadius(0);
        kGradientPanel6.setkEndColor(new java.awt.Color(234, 239, 243));
        kGradientPanel6.setkGradientFocus(0);
        kGradientPanel6.setkStartColor(new java.awt.Color(255, 255, 255));
        kGradientPanel6.setMinimumSize(new java.awt.Dimension(1170, 91));
        kGradientPanel6.setPreferredSize(new java.awt.Dimension(1360, 110));
        kGradientPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tfAreceberPendentes.setBackground(new java.awt.Color(234, 239, 243));
        tfAreceberPendentes.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tfAreceberPendentes.setForeground(new java.awt.Color(182, 92, 2));
        tfAreceberPendentes.setText("R$ 0,00");
        kGradientPanel6.add(tfAreceberPendentes, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 40, 140, 40));

        tfNegHrKmPendentes.setBackground(new java.awt.Color(234, 239, 243));
        tfNegHrKmPendentes.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tfNegHrKmPendentes.setForeground(new java.awt.Color(182, 92, 2));
        tfNegHrKmPendentes.setText("R$ 0,00");
        kGradientPanel6.add(tfNegHrKmPendentes, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 40, 120, 30));

        tfAdiantamentosPendentes.setBackground(new java.awt.Color(234, 239, 243));
        tfAdiantamentosPendentes.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tfAdiantamentosPendentes.setForeground(new java.awt.Color(182, 92, 2));
        tfAdiantamentosPendentes.setText("R$ 0,00");
        kGradientPanel6.add(tfAdiantamentosPendentes, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 40, 120, 30));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(51, 51, 51));
        jLabel15.setText("NEG+Honorário+KM");
        kGradientPanel6.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 20, -1, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(51, 51, 51));
        jLabel16.setText("ADIANTAMENTO");
        kGradientPanel6.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 20, -1, -1));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(51, 51, 51));
        jLabel17.setText("VALOR A RECEBER");
        kGradientPanel6.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 20, -1, -1));

        cbFuncionariosPendentes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
        cbFuncionariosPendentes.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbFuncionariosPendentesPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        kGradientPanel6.add(cbFuncionariosPendentes, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 40, 290, 30));

        btClearPendentes.setBackground(new java.awt.Color(199, 223, 242));
        btClearPendentes.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btClearPendentes.setForeground(new java.awt.Color(0, 0, 102));
        btClearPendentes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Erase.png"))); // NOI18N
        btClearPendentes.setText("LIMPAR");
        btClearPendentes.setPreferredSize(new java.awt.Dimension(110, 40));
        btClearPendentes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btClearPendentesActionPerformed(evt);
            }
        });
        kGradientPanel6.add(btClearPendentes, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 40, 100, -1));

        btDefinirDataPendentes.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btDefinirDataPendentes.setForeground(new java.awt.Color(51, 51, 51));
        btDefinirDataPendentes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/page_find.png"))); // NOI18N
        btDefinirDataPendentes.setText("APLICAR");
        btDefinirDataPendentes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDefinirDataPendentesActionPerformed(evt);
            }
        });
        kGradientPanel6.add(btDefinirDataPendentes, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 40, 130, 35));
        kGradientPanel6.add(jdcDataFinalPendentes, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 45, 130, 28));
        kGradientPanel6.add(jdcDataInicialPendentes, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 45, 130, 28));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel4.setText("Periodo por Data de Envio:");
        kGradientPanel6.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 25, 240, -1));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setText("Filtrar Agente:");
        kGradientPanel6.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 20, 110, -1));

        DespesasPendentes.add(kGradientPanel6);

        kGradientPanel5.setkBorderRadius(0);
        kGradientPanel5.setkEndColor(new java.awt.Color(234, 239, 243));
        kGradientPanel5.setkGradientFocus(0);
        kGradientPanel5.setkStartColor(new java.awt.Color(255, 255, 255));
        kGradientPanel5.setMinimumSize(new java.awt.Dimension(1170, 1170));
        kGradientPanel5.setName(""); // NOI18N
        kGradientPanel5.setPreferredSize(new java.awt.Dimension(1360, 60));
        kGradientPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btAlterar.setBackground(new java.awt.Color(0, 102, 255));
        btAlterar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btAlterar.setForeground(new java.awt.Color(255, 255, 255));
        btAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/page_edit.png"))); // NOI18N
        btAlterar.setText("Alterar");
        btAlterar.setPreferredSize(new java.awt.Dimension(150, 30));
        btAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAlterarActionPerformed(evt);
            }
        });
        kGradientPanel5.add(btAlterar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jbPagarConta.setBackground(new java.awt.Color(0, 153, 0));
        jbPagarConta.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jbPagarConta.setForeground(new java.awt.Color(255, 255, 255));
        jbPagarConta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Good-mark.png"))); // NOI18N
        jbPagarConta.setText("FAZER PGTO.");
        jbPagarConta.setPreferredSize(new java.awt.Dimension(200, 30));
        jbPagarConta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbPagarContaActionPerformed(evt);
            }
        });
        kGradientPanel5.add(jbPagarConta, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 10, -1, -1));

        jbExcluirPagar.setBackground(new java.awt.Color(255, 0, 0));
        jbExcluirPagar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbExcluirPagar.setForeground(new java.awt.Color(255, 255, 255));
        jbExcluirPagar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/page_delete.png"))); // NOI18N
        jbExcluirPagar.setPreferredSize(new java.awt.Dimension(150, 30));
        jbExcluirPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbExcluirPagarActionPerformed(evt);
            }
        });
        kGradientPanel5.add(jbExcluirPagar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, 40, -1));

        DespesasPendentes.add(kGradientPanel5);

        jTabbedPanelDespesas.addTab("DESPESAS PENDENTES", DespesasPendentes);

        DespesasEfetuados.setBackground(new java.awt.Color(255, 255, 255));
        DespesasEfetuados.setMinimumSize(new java.awt.Dimension(1170, 380));
        DespesasEfetuados.setPreferredSize(new java.awt.Dimension(1170, 370));

        kGradientPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "CONTAS COM PAGAMENTO EFETUADO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(0, 51, 102))); // NOI18N
        kGradientPanel8.setkBorderRadius(0);
        kGradientPanel8.setkEndColor(new java.awt.Color(199, 223, 242));
        kGradientPanel8.setkGradientFocus(0);
        kGradientPanel8.setkStartColor(new java.awt.Color(255, 255, 255));
        kGradientPanel8.setMinimumSize(new java.awt.Dimension(1170, 430));
        kGradientPanel8.setPreferredSize(new java.awt.Dimension(1360, 430));
        kGradientPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane18.setBackground(new java.awt.Color(247, 247, 247));
        jScrollPane18.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane18.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane18.setPreferredSize(new java.awt.Dimension(1220, 180));

        tbDespesasEfetuadas.setAutoCreateRowSorter(true);
        tbDespesasEfetuadas.setBackground(new java.awt.Color(247, 247, 247));
        tbDespesasEfetuadas.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        tbDespesasEfetuadas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Agente", "Despesa", "Processo", "Envio", "Seguradora", "Sinistro", "Serviço", "Negativa", "Honorário", "KM Perco.", "Valor KM", "Total KM", "Recebimento", "Descrição", "Total Adiantamento por despesa", "Neg+HR+KM por Despesa", "A receber por despesa"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbDespesasEfetuadas.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbDespesasEfetuadas.setGridColor(new java.awt.Color(247, 247, 247));
        tbDespesasEfetuadas.setMinimumSize(new java.awt.Dimension(610, 0));
        tbDespesasEfetuadas.setRowHeight(20);
        tbDespesasEfetuadas.setSelectionBackground(new java.awt.Color(0, 153, 0));
        tbDespesasEfetuadas.getTableHeader().setReorderingAllowed(false);
        jScrollPane18.setViewportView(tbDespesasEfetuadas);
        if (tbDespesasEfetuadas.getColumnModel().getColumnCount() > 0) {
            tbDespesasEfetuadas.getColumnModel().getColumn(0).setMinWidth(150);
            tbDespesasEfetuadas.getColumnModel().getColumn(1).setMinWidth(50);
            tbDespesasEfetuadas.getColumnModel().getColumn(2).setMinWidth(80);
            tbDespesasEfetuadas.getColumnModel().getColumn(3).setMinWidth(80);
            tbDespesasEfetuadas.getColumnModel().getColumn(4).setMinWidth(200);
            tbDespesasEfetuadas.getColumnModel().getColumn(5).setMinWidth(100);
            tbDespesasEfetuadas.getColumnModel().getColumn(6).setMinWidth(100);
            tbDespesasEfetuadas.getColumnModel().getColumn(7).setMinWidth(80);
            tbDespesasEfetuadas.getColumnModel().getColumn(8).setMinWidth(80);
            tbDespesasEfetuadas.getColumnModel().getColumn(9).setMinWidth(80);
            tbDespesasEfetuadas.getColumnModel().getColumn(10).setMinWidth(80);
            tbDespesasEfetuadas.getColumnModel().getColumn(11).setMinWidth(80);
            tbDespesasEfetuadas.getColumnModel().getColumn(12).setMinWidth(80);
            tbDespesasEfetuadas.getColumnModel().getColumn(14).setMinWidth(80);
            tbDespesasEfetuadas.getColumnModel().getColumn(15).setMinWidth(80);
            tbDespesasEfetuadas.getColumnModel().getColumn(16).setMinWidth(80);
        }

        kGradientPanel8.add(jScrollPane18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 1320, 370));

        jFTotalKmPercoEfetuado.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jFTotalKmPercoEfetuado.setForeground(new java.awt.Color(0, 51, 102));
        jFTotalKmPercoEfetuado.setText("0");
        kGradientPanel8.add(jFTotalKmPercoEfetuado, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 410, 110, -1));

        jLtotalKmPercoEfetuado.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLtotalKmPercoEfetuado.setText("Total Percorrido:");
        kGradientPanel8.add(jLtotalKmPercoEfetuado, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 410, -1, -1));

        jFTotalPgtoKmEfetuado.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jFTotalPgtoKmEfetuado.setForeground(new java.awt.Color(0, 51, 102));
        jFTotalPgtoKmEfetuado.setText("R$ 0,00");
        kGradientPanel8.add(jFTotalPgtoKmEfetuado, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 410, 170, -1));

        jLTotalPgtoKmEfetuado.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLTotalPgtoKmEfetuado.setText("Total Pago em KM:");
        kGradientPanel8.add(jLTotalPgtoKmEfetuado, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 410, -1, -1));

        jLtotalHrEfetuado.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLtotalHrEfetuado.setText("Total Honorário:");
        kGradientPanel8.add(jLtotalHrEfetuado, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 410, -1, -1));

        jFTotalHrEfetuado.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jFTotalHrEfetuado.setForeground(new java.awt.Color(0, 51, 102));
        jFTotalHrEfetuado.setText("R$ 0,00");
        kGradientPanel8.add(jFTotalHrEfetuado, new org.netbeans.lib.awtextra.AbsoluteConstraints(525, 410, 160, -1));

        jFTotalnegEfetuado.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jFTotalnegEfetuado.setForeground(new java.awt.Color(0, 51, 102));
        jFTotalnegEfetuado.setText("R$ 0,00");
        kGradientPanel8.add(jFTotalnegEfetuado, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 410, 120, -1));

        jLTotalNegEfetuado.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLTotalNegEfetuado.setText("Total Negativa:");
        kGradientPanel8.add(jLTotalNegEfetuado, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 410, -1, -1));

        DespesasEfetuados.add(kGradientPanel8);

        kGradientPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "FILTRAR", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(51, 51, 51))); // NOI18N
        kGradientPanel10.setkBorderRadius(0);
        kGradientPanel10.setkEndColor(new java.awt.Color(234, 239, 243));
        kGradientPanel10.setkGradientFocus(0);
        kGradientPanel10.setkStartColor(new java.awt.Color(255, 255, 255));
        kGradientPanel10.setMinimumSize(new java.awt.Dimension(1170, 99));
        kGradientPanel10.setPreferredSize(new java.awt.Dimension(1360, 110));
        kGradientPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbFuncionariosEfetuado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
        cbFuncionariosEfetuado.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbFuncionariosEfetuadoPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        kGradientPanel10.add(cbFuncionariosEfetuado, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 40, 290, 30));

        btClearEfetuado.setBackground(new java.awt.Color(199, 223, 242));
        btClearEfetuado.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btClearEfetuado.setForeground(new java.awt.Color(0, 0, 102));
        btClearEfetuado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Erase.png"))); // NOI18N
        btClearEfetuado.setText("LIMPAR");
        btClearEfetuado.setPreferredSize(new java.awt.Dimension(110, 40));
        btClearEfetuado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btClearEfetuadoActionPerformed(evt);
            }
        });
        kGradientPanel10.add(btClearEfetuado, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 40, 100, -1));

        tfAreceberEfetuado.setBackground(new java.awt.Color(234, 239, 243));
        tfAreceberEfetuado.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tfAreceberEfetuado.setForeground(new java.awt.Color(0, 0, 102));
        tfAreceberEfetuado.setText("R$ 0,00");
        kGradientPanel10.add(tfAreceberEfetuado, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 40, 140, 40));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(51, 51, 51));
        jLabel18.setText("VALOR A RECEBER");
        kGradientPanel10.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 20, -1, -1));

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(51, 51, 51));
        jLabel21.setText("ADIANTAMENTO");
        kGradientPanel10.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 20, -1, -1));

        tfAdiantamentosEfetuado.setBackground(new java.awt.Color(234, 239, 243));
        tfAdiantamentosEfetuado.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tfAdiantamentosEfetuado.setForeground(new java.awt.Color(0, 0, 102));
        tfAdiantamentosEfetuado.setText("R$ 0,00");
        kGradientPanel10.add(tfAdiantamentosEfetuado, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 40, 120, 30));

        tfNegHrkmEfetuado.setBackground(new java.awt.Color(234, 239, 243));
        tfNegHrkmEfetuado.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tfNegHrkmEfetuado.setForeground(new java.awt.Color(0, 0, 102));
        tfNegHrkmEfetuado.setText("R$ 0,00");
        kGradientPanel10.add(tfNegHrkmEfetuado, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 40, 120, 30));

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(51, 51, 51));
        jLabel22.setText("NEG+Honorário+KM");
        kGradientPanel10.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 20, -1, -1));

        btDefinirDataEfetuado.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btDefinirDataEfetuado.setForeground(new java.awt.Color(51, 51, 51));
        btDefinirDataEfetuado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/page_find.png"))); // NOI18N
        btDefinirDataEfetuado.setText("APLICAR");
        btDefinirDataEfetuado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDefinirDataEfetuadoActionPerformed(evt);
            }
        });
        kGradientPanel10.add(btDefinirDataEfetuado, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 40, 130, 35));
        kGradientPanel10.add(jdcDataFinalEfetuado, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 45, 130, 28));
        kGradientPanel10.add(jdcDataInicialEfetuado, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 45, 130, 28));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel3.setText("Periodo por Data de Envio:");
        kGradientPanel10.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 25, 250, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Filtrar Agente:");
        kGradientPanel10.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 20, 110, -1));

        DespesasEfetuados.add(kGradientPanel10);

        kGradientPanel9.setBackground(new java.awt.Color(255, 255, 255));
        kGradientPanel9.setkBorderRadius(0);
        kGradientPanel9.setkEndColor(new java.awt.Color(234, 239, 243));
        kGradientPanel9.setkGradientFocus(0);
        kGradientPanel9.setkStartColor(new java.awt.Color(255, 255, 255));
        kGradientPanel9.setMinimumSize(new java.awt.Dimension(1170, 50));
        kGradientPanel9.setPreferredSize(new java.awt.Dimension(1360, 50));
        kGradientPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpRevorgarConta.setBackground(new java.awt.Color(255, 0, 0));
        jpRevorgarConta.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jpRevorgarConta.setForeground(new java.awt.Color(255, 255, 255));
        jpRevorgarConta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Bad mark.png"))); // NOI18N
        jpRevorgarConta.setText("REVOGAR PGTO.");
        jpRevorgarConta.setPreferredSize(new java.awt.Dimension(200, 30));
        jpRevorgarConta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jpRevorgarContaActionPerformed(evt);
            }
        });
        kGradientPanel9.add(jpRevorgarConta, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 10, -1, -1));

        btExportarDados.setBackground(new java.awt.Color(0, 153, 0));
        btExportarDados.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btExportarDados.setForeground(new java.awt.Color(255, 255, 255));
        btExportarDados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/excel.png"))); // NOI18N
        btExportarDados.setText("EXPORTAR");
        btExportarDados.setPreferredSize(new java.awt.Dimension(150, 30));
        btExportarDados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExportarDadosActionPerformed(evt);
            }
        });
        kGradientPanel9.add(btExportarDados, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        DespesasEfetuados.add(kGradientPanel9);

        jTabbedPanelDespesas.addTab("DESPESAS PAGAS", DespesasEfetuados);

        RegistrarContas.setBackground(new java.awt.Color(255, 255, 255));
        RegistrarContas.setMinimumSize(new java.awt.Dimension(1170, 400));
        RegistrarContas.setPreferredSize(new java.awt.Dimension(1170, 400));

        kGFuncionarioAgente.setBackground(new java.awt.Color(255, 255, 255));
        kGFuncionarioAgente.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "         DADOS DO AGENTE E PROCESSO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(51, 51, 51))); // NOI18N
        kGFuncionarioAgente.setkBorderRadius(0);
        kGFuncionarioAgente.setkEndColor(new java.awt.Color(234, 239, 243));
        kGFuncionarioAgente.setkGradientFocus(0);
        kGFuncionarioAgente.setkStartColor(new java.awt.Color(255, 255, 255));
        kGFuncionarioAgente.setMinimumSize(new java.awt.Dimension(1170, 120));
        kGFuncionarioAgente.setPreferredSize(new java.awt.Dimension(1360, 160));
        kGFuncionarioAgente.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tfSeguradora1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tfSeguradora1.setText("AGENTE:");
        kGFuncionarioAgente.add(tfSeguradora1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, 50, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("ESCOLHER BANCO:");
        kGFuncionarioAgente.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 40, 100, -1));

        try {
            jFDataEnvio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFDataEnvio.setPreferredSize(new java.awt.Dimension(130, 28));
        jFDataEnvio.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jFDataEnvioFocusLost(evt);
            }
        });
        jFDataEnvio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFDataEnvioKeyPressed(evt);
            }
        });
        kGFuncionarioAgente.add(jFDataEnvio, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 60, -1, -1));

        try {
            jFDataRecebimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFDataRecebimento.setPreferredSize(new java.awt.Dimension(130, 28));
        jFDataRecebimento.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jFDataRecebimentoFocusLost(evt);
            }
        });
        jFDataRecebimento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFDataRecebimentoKeyPressed(evt);
            }
        });
        kGFuncionarioAgente.add(jFDataRecebimento, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 60, -1, -1));
        kGFuncionarioAgente.add(cbBancos, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 60, 250, 28));

        cbTipoDespesa.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbTipoDespesaPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        kGFuncionarioAgente.add(cbTipoDespesa, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 60, 170, 28));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("TIPO DE CONTA:");
        kGFuncionarioAgente.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 40, 150, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("ENVIO:");
        kGFuncionarioAgente.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 40, 90, -1));

        labelPagamento.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        labelPagamento.setText("RECEBIMENTO:");
        kGFuncionarioAgente.add(labelPagamento, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 40, 150, -1));

        kGTotalGeral1.setkBorderRadius(0);
        kGTotalGeral1.setkEndColor(new java.awt.Color(234, 239, 243));
        kGTotalGeral1.setkGradientFocus(0);
        kGTotalGeral1.setkStartColor(new java.awt.Color(234, 239, 243));
        kGTotalGeral1.setkTransparentControls(false);
        kGTotalGeral1.setPreferredSize(new java.awt.Dimension(310, 120));
        kGTotalGeral1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLAgenteTotalKm1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLAgenteTotalKm1.setForeground(new java.awt.Color(0, 102, 153));
        jLAgenteTotalKm1.setText("PROCESSO:");
        kGTotalGeral1.add(jLAgenteTotalKm1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 70, 20));

        kGradientPanel3.setkBorderRadius(0);
        kGradientPanel3.setkEndColor(new java.awt.Color(255, 255, 255));
        kGradientPanel3.setkGradientFocus(0);
        kGradientPanel3.setkStartColor(new java.awt.Color(234, 239, 243));
        kGradientPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLAgenteRepasseTotal1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLAgenteRepasseTotal1.setForeground(new java.awt.Color(0, 102, 153));
        jLAgenteRepasseTotal1.setText("SEG:");
        kGradientPanel3.add(jLAgenteRepasseTotal1, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 20, 36, 20));

        jScrollPaneAnalista1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPaneAnalista1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPaneAnalista1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jScrollPaneAnalista1.setPreferredSize(new java.awt.Dimension(675, 25));

        tfSeguradoraProcesso.setEditable(false);
        tfSeguradoraProcesso.setBackground(new java.awt.Color(242, 245, 247));
        tfSeguradoraProcesso.setColumns(20);
        tfSeguradoraProcesso.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tfSeguradoraProcesso.setRows(5);
        tfSeguradoraProcesso.setText("\n");
        tfSeguradoraProcesso.setFocusable(false);
        jScrollPaneAnalista1.setViewportView(tfSeguradoraProcesso);

        kGradientPanel3.add(jScrollPaneAnalista1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 570, 24));

        kGTotalGeral1.add(kGradientPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 0, 650, 50));

        tfDataProcesso.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tfDataProcesso.setForeground(new java.awt.Color(0, 102, 153));
        tfDataProcesso.setText("Data Entrada");
        kGTotalGeral1.add(tfDataProcesso, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 20, 140, 20));

        jLTotalGeral2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLTotalGeral2.setForeground(new java.awt.Color(0, 102, 153));
        jLTotalGeral2.setText("DATA:");
        kGTotalGeral1.add(jLTotalGeral2, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 20, 50, 20));

        kGradientPanel4.setkBorderRadius(0);
        kGradientPanel4.setkEndColor(new java.awt.Color(255, 255, 255));
        kGradientPanel4.setkGradientFocus(0);
        kGradientPanel4.setkStartColor(new java.awt.Color(234, 239, 243));
        kGradientPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLTotalGeral1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLTotalGeral1.setForeground(new java.awt.Color(0, 102, 153));
        jLTotalGeral1.setText("SINISTRO:");
        kGradientPanel4.add(jLTotalGeral1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 23, 87, 20));

        jScrollPaneAnalista2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPaneAnalista2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPaneAnalista2.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jScrollPaneAnalista2.setPreferredSize(new java.awt.Dimension(675, 25));

        tfSinistroProcesso.setEditable(false);
        tfSinistroProcesso.setBackground(new java.awt.Color(242, 245, 247));
        tfSinistroProcesso.setColumns(20);
        tfSinistroProcesso.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tfSinistroProcesso.setRows(5);
        tfSinistroProcesso.setText("\n");
        tfSinistroProcesso.setFocusable(false);
        jScrollPaneAnalista2.setViewportView(tfSinistroProcesso);

        kGradientPanel4.add(jScrollPaneAnalista2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 170, 24));

        kGTotalGeral1.add(kGradientPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 0, 320, 50));

        tfCodigoProcesso.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        tfCodigoProcesso.setForeground(new java.awt.Color(153, 0, 0));
        tfCodigoProcesso.setText("Código");
        kGTotalGeral1.add(tfCodigoProcesso, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 90, 20));

        kGFuncionarioAgente.add(kGTotalGeral1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 1360, 50));

        jScrollPaneAnalista.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPaneAnalista.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPaneAnalista.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jScrollPaneAnalista.setPreferredSize(new java.awt.Dimension(675, 25));

        jLNomeAgente.setEditable(false);
        jLNomeAgente.setBackground(new java.awt.Color(242, 245, 247));
        jLNomeAgente.setColumns(20);
        jLNomeAgente.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLNomeAgente.setRows(5);
        jLNomeAgente.setText("\n");
        jLNomeAgente.setFocusable(false);
        jScrollPaneAnalista.setViewportView(jLNomeAgente);

        kGFuncionarioAgente.add(jScrollPaneAnalista, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 460, 24));

        RegistrarContas.add(kGFuncionarioAgente);

        kGradientPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "         DESPESA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(0, 0, 51))); // NOI18N
        kGradientPanel14.setForeground(new java.awt.Color(0, 0, 51));
        kGradientPanel14.setkBorderRadius(0);
        kGradientPanel14.setkEndColor(new java.awt.Color(234, 239, 243));
        kGradientPanel14.setkGradientFocus(0);
        kGradientPanel14.setkStartColor(new java.awt.Color(255, 255, 255));
        kGradientPanel14.setPreferredSize(new java.awt.Dimension(1360, 120));
        kGradientPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtfCodigoConta.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jtfCodigoConta.setForeground(new java.awt.Color(255, 0, 51));
        jtfCodigoConta.setText("NOVO+");
        kGradientPanel14.add(jtfCodigoConta, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 150, 50));

        tfObservacao.setColumns(20);
        tfObservacao.setRows(5);
        tfObservacao.setText("Nenhuma observação.");
        tfObservacao.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfObservacaoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfObservacaoFocusLost(evt);
            }
        });
        jScrollPane1.setViewportView(tfObservacao);

        kGradientPanel14.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, 1110, 60));

        jLabel10.setText("OBSERVAÇÕES:");
        kGradientPanel14.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, -1, -1));

        RegistrarContas.add(kGradientPanel14);

        kGPAlterarAdiantamentos.setBackground(new java.awt.Color(172, 193, 212));
        kGPAlterarAdiantamentos.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "ADIANTAMENTOS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(51, 51, 51))); // NOI18N
        kGPAlterarAdiantamentos.setkBorderRadius(0);
        kGPAlterarAdiantamentos.setkEndColor(new java.awt.Color(234, 239, 243));
        kGPAlterarAdiantamentos.setkGradientFocus(0);
        kGPAlterarAdiantamentos.setkStartColor(new java.awt.Color(255, 255, 255));
        kGPAlterarAdiantamentos.setMinimumSize(new java.awt.Dimension(1280, 200));
        kGPAlterarAdiantamentos.setPreferredSize(new java.awt.Dimension(1360, 130));
        kGPAlterarAdiantamentos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbAdiantamentoAlterar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cod", "Descrição", "Valor", "Data"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbAdiantamentoAlterar.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tbAdiantamentoAlterar);

        kGPAlterarAdiantamentos.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 20, 450, 100));

        btAdiantamentoAlterar.setBackground(new java.awt.Color(0, 153, 0));
        btAdiantamentoAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Create.png"))); // NOI18N
        btAdiantamentoAlterar.setPreferredSize(new java.awt.Dimension(50, 35));
        btAdiantamentoAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAdiantamentoAlterarActionPerformed(evt);
            }
        });
        kGPAlterarAdiantamentos.add(btAdiantamentoAlterar, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 40, -1, -1));

        btExcluirAdiantamento.setBackground(new java.awt.Color(204, 0, 0));
        btExcluirAdiantamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/delete.png"))); // NOI18N
        btExcluirAdiantamento.setPreferredSize(new java.awt.Dimension(50, 30));
        btExcluirAdiantamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExcluirAdiantamentoActionPerformed(evt);
            }
        });
        kGPAlterarAdiantamentos.add(btExcluirAdiantamento, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 90, -1, -1));

        tfValorDespesaAlterar.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getCurrencyInstance())));
        tfValorDespesaAlterar.setText("R$ 0,00");
        tfValorDespesaAlterar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tfValorDespesaAlterar.setPreferredSize(new java.awt.Dimension(130, 35));
        tfValorDespesaAlterar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfValorDespesaAlterarFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfValorDespesaAlterarFocusLost(evt);
            }
        });
        tfValorDespesaAlterar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfValorDespesaAlterarKeyPressed(evt);
            }
        });
        kGPAlterarAdiantamentos.add(tfValorDespesaAlterar, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 50, -1, -1));

        try {
            tfDataDespesaAlterar.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfDataDespesaAlterar.setPreferredSize(new java.awt.Dimension(130, 28));
        tfDataDespesaAlterar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfDataDespesaAlterarFocusLost(evt);
            }
        });
        tfDataDespesaAlterar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfDataDespesaAlterarKeyPressed(evt);
            }
        });
        kGPAlterarAdiantamentos.add(tfDataDespesaAlterar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 50, -1, -1));

        tfDescricaoDespesaAlterar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfDescricaoDespesaAlterarFocusLost(evt);
            }
        });
        tfDescricaoDespesaAlterar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfDescricaoDespesaAlterarKeyPressed(evt);
            }
        });
        kGPAlterarAdiantamentos.add(tfDescricaoDespesaAlterar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 230, 28));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setText("DESCRIÇÃO");
        kGPAlterarAdiantamentos.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 210, 20));

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel23.setText("DATA:");
        kGPAlterarAdiantamentos.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 30, -1, -1));

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel26.setText("VALOR:");
        kGPAlterarAdiantamentos.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 30, 90, -1));

        tfValidarDescricaoAlterar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tfValidarDescricaoAlterar.setForeground(new java.awt.Color(255, 0, 0));
        tfValidarDescricaoAlterar.setText("Preecha a Descrição");
        kGPAlterarAdiantamentos.add(tfValidarDescricaoAlterar, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 80, 140, -1));

        tfValidarValorAlterar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tfValidarValorAlterar.setForeground(new java.awt.Color(255, 0, 0));
        tfValidarValorAlterar.setText("Preencha o valor");
        kGPAlterarAdiantamentos.add(tfValidarValorAlterar, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 90, 140, -1));

        tfValidarDataAlterar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tfValidarDataAlterar.setForeground(new java.awt.Color(255, 0, 0));
        tfValidarDataAlterar.setText("Preecha a Data");
        kGPAlterarAdiantamentos.add(tfValidarDataAlterar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 80, 120, -1));

        kGradientPanel15.setkBorderRadius(0);
        kGradientPanel15.setkEndColor(new java.awt.Color(255, 255, 255));
        kGradientPanel15.setkStartColor(new java.awt.Color(255, 255, 255));

        totalAdiantamentosAlterar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        totalAdiantamentosAlterar.setForeground(new java.awt.Color(255, 51, 51));
        totalAdiantamentosAlterar.setText("R$ 0,00");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Total Adiantamentos:");

        org.jdesktop.layout.GroupLayout kGradientPanel15Layout = new org.jdesktop.layout.GroupLayout(kGradientPanel15);
        kGradientPanel15.setLayout(kGradientPanel15Layout);
        kGradientPanel15Layout.setHorizontalGroup(
            kGradientPanel15Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(kGradientPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel6)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .add(org.jdesktop.layout.GroupLayout.TRAILING, kGradientPanel15Layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(totalAdiantamentosAlterar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 170, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        kGradientPanel15Layout.setVerticalGroup(
            kGradientPanel15Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, kGradientPanel15Layout.createSequentialGroup()
                .add(jLabel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(totalAdiantamentosAlterar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        kGPAlterarAdiantamentos.add(kGradientPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 40, 150, 90));

        kButtonProcessos4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/refresh.png"))); // NOI18N
        kButtonProcessos4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        kButtonProcessos4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        kButtonProcessos4.setIconTextGap(15);
        kButtonProcessos4.setkBackGroundColor(new java.awt.Color(234, 239, 243));
        kButtonProcessos4.setkBorderRadius(0);
        kButtonProcessos4.setkEndColor(new java.awt.Color(255, 255, 255));
        kButtonProcessos4.setkForeGround(new java.awt.Color(51, 51, 51));
        kButtonProcessos4.setkHoverEndColor(new java.awt.Color(234, 239, 243));
        kButtonProcessos4.setkHoverForeGround(new java.awt.Color(51, 51, 51));
        kButtonProcessos4.setkHoverStartColor(new java.awt.Color(234, 239, 243));
        kButtonProcessos4.setkPressedColor(new java.awt.Color(255, 255, 255));
        kButtonProcessos4.setkSelectedColor(new java.awt.Color(255, 255, 255));
        kButtonProcessos4.setkStartColor(new java.awt.Color(255, 255, 255));
        kButtonProcessos4.setPreferredSize(new java.awt.Dimension(210, 45));
        kButtonProcessos4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButtonProcessos4ActionPerformed(evt);
            }
        });
        kGPAlterarAdiantamentos.add(kButtonProcessos4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1260, 0, 50, 40));

        RegistrarContas.add(kGPAlterarAdiantamentos);

        kGradientPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "         NEGATIVA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(51, 51, 51))); // NOI18N
        kGradientPanel16.setkBorderRadius(0);
        kGradientPanel16.setkEndColor(new java.awt.Color(234, 239, 243));
        kGradientPanel16.setkGradientFocus(0);
        kGradientPanel16.setkStartColor(new java.awt.Color(255, 255, 255));
        kGradientPanel16.setPreferredSize(new java.awt.Dimension(340, 120));
        kGradientPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jFValorNeg.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getCurrencyInstance())));
        jFValorNeg.setText("R$ 0,00");
        jFValorNeg.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jFValorNeg.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jFValorNegFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jFValorNegFocusLost(evt);
            }
        });
        jFValorNeg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFValorNegKeyPressed(evt);
            }
        });
        kGradientPanel16.add(jFValorNeg, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 110, 35));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("VALOR:");
        kGradientPanel16.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 90, -1));

        try {
            jFDataNeg.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFDataNeg.setPreferredSize(new java.awt.Dimension(130, 28));
        jFDataNeg.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jFDataNegFocusLost(evt);
            }
        });
        jFDataNeg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFDataNegKeyPressed(evt);
            }
        });
        kGradientPanel16.add(jFDataNeg, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 70, -1, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("DATA:");
        kGradientPanel16.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, -1, -1));

        RegistrarContas.add(kGradientPanel16);

        kGRepasseAoAgente.setBackground(new java.awt.Color(228, 235, 241));
        kGRepasseAoAgente.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "         HONORÁRIOS + KM", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(51, 51, 51))); // NOI18N
        kGRepasseAoAgente.setForeground(new java.awt.Color(51, 51, 51));
        kGRepasseAoAgente.setkBorderRadius(0);
        kGRepasseAoAgente.setkEndColor(new java.awt.Color(234, 239, 243));
        kGRepasseAoAgente.setkGradientFocus(0);
        kGRepasseAoAgente.setkStartColor(new java.awt.Color(255, 255, 255));
        kGRepasseAoAgente.setMinimumSize(new java.awt.Dimension(1170, 80));
        kGRepasseAoAgente.setPreferredSize(new java.awt.Dimension(1005, 120));
        kGRepasseAoAgente.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLAgenteHonorarios.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLAgenteHonorarios.setForeground(new java.awt.Color(51, 51, 51));
        jLAgenteHonorarios.setText("PGTO. HONORÁRIOS");
        kGRepasseAoAgente.add(jLAgenteHonorarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 130, 20));

        jfAgenteHonorario.setForeground(new java.awt.Color(51, 51, 51));
        jfAgenteHonorario.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getCurrencyInstance())));
        jfAgenteHonorario.setText("R$ 0,00");
        jfAgenteHonorario.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jfAgenteHonorario.setMinimumSize(new java.awt.Dimension(80, 24));
        jfAgenteHonorario.setPreferredSize(new java.awt.Dimension(100, 28));
        jfAgenteHonorario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jfAgenteHonorarioFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jfAgenteHonorarioFocusLost(evt);
            }
        });
        jfAgenteHonorario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jfAgenteHonorarioKeyPressed(evt);
            }
        });
        kGRepasseAoAgente.add(jfAgenteHonorario, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 130, 35));

        jLAgenteSeguradoraKm.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLAgenteSeguradoraKm.setForeground(new java.awt.Color(51, 51, 51));
        jLAgenteSeguradoraKm.setText("PGTO. POR KM");
        kGRepasseAoAgente.add(jLAgenteSeguradoraKm, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 30, 100, 20));

        jLAgenteKMpercorrido.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLAgenteKMpercorrido.setForeground(new java.awt.Color(51, 51, 51));
        jLAgenteKMpercorrido.setText("KM PERCORRIDOS");
        kGRepasseAoAgente.add(jLAgenteKMpercorrido, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 30, 130, 20));

        jfAgenteKmPercorrido.setForeground(new java.awt.Color(51, 51, 51));
        jfAgenteKmPercorrido.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jfAgenteKmPercorrido.setText("0");
        jfAgenteKmPercorrido.setToolTipText("");
        jfAgenteKmPercorrido.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jfAgenteKmPercorrido.setMinimumSize(new java.awt.Dimension(80, 24));
        jfAgenteKmPercorrido.setPreferredSize(new java.awt.Dimension(80, 24));
        jfAgenteKmPercorrido.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jfAgenteKmPercorridoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jfAgenteKmPercorridoFocusLost(evt);
            }
        });
        jfAgenteKmPercorrido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jfAgenteKmPercorridoKeyPressed(evt);
            }
        });
        kGRepasseAoAgente.add(jfAgenteKmPercorrido, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, 120, 35));

        kGTotalGeral.setkBorderRadius(0);
        kGTotalGeral.setkEndColor(new java.awt.Color(255, 255, 255));
        kGTotalGeral.setkGradientFocus(0);
        kGTotalGeral.setkStartColor(new java.awt.Color(255, 255, 255));
        kGTotalGeral.setkTransparentControls(false);
        kGTotalGeral.setPreferredSize(new java.awt.Dimension(310, 120));
        kGTotalGeral.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLTotalGeral.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLTotalGeral.setForeground(new java.awt.Color(51, 51, 51));
        jLTotalGeral.setText("VALOR A RECEBER");
        kGTotalGeral.add(jLTotalGeral, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 30, 140, 20));

        tfTotalAReceber.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        tfTotalAReceber.setForeground(new java.awt.Color(0, 102, 102));
        tfTotalAReceber.setText("R$ 0,00");
        kGTotalGeral.add(tfTotalAReceber, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 60, 170, -1));

        jLAgenteTotalKm.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLAgenteTotalKm.setForeground(new java.awt.Color(51, 51, 51));
        jLAgenteTotalKm.setText("PAGAMENTO TOTAL KM");
        kGTotalGeral.add(jLAgenteTotalKm, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 140, 20));

        jFPagamentoTotalKM.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jFPagamentoTotalKM.setForeground(new java.awt.Color(51, 51, 51));
        jFPagamentoTotalKM.setText("R$ 0,00");
        kGTotalGeral.add(jFPagamentoTotalKM, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 140, -1));

        kGradientPanel2.setkBorderRadius(0);
        kGradientPanel2.setkEndColor(new java.awt.Color(234, 239, 243));
        kGradientPanel2.setkGradientFocus(0);
        kGradientPanel2.setkStartColor(new java.awt.Color(255, 255, 255));

        jLAgenteRepasseTotal.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLAgenteRepasseTotal.setForeground(new java.awt.Color(51, 51, 51));
        jLAgenteRepasseTotal.setText("PAGAMENTO TOTAL");

        jfAgenteTotalRepasse.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jfAgenteTotalRepasse.setForeground(new java.awt.Color(51, 51, 51));
        jfAgenteTotalRepasse.setText("R$ 0,00");

        org.jdesktop.layout.GroupLayout kGradientPanel2Layout = new org.jdesktop.layout.GroupLayout(kGradientPanel2);
        kGradientPanel2.setLayout(kGradientPanel2Layout);
        kGradientPanel2Layout.setHorizontalGroup(
            kGradientPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, kGradientPanel2Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .add(kGradientPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                    .add(jfAgenteTotalRepasse, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jLAgenteRepasseTotal, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
                .add(22, 22, 22))
        );
        kGradientPanel2Layout.setVerticalGroup(
            kGradientPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(kGradientPanel2Layout.createSequentialGroup()
                .add(29, 29, 29)
                .add(jLAgenteRepasseTotal, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(10, 10, 10)
                .add(jfAgenteTotalRepasse)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        kGTotalGeral.add(kGradientPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 0, 170, 120));

        jLCampoNegativo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLCampoNegativo.setForeground(new java.awt.Color(255, 0, 51));
        jLCampoNegativo.setText("Campo não pode ser negativo.");
        kGTotalGeral.add(jLCampoNegativo, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 90, 150, -1));

        kGRepasseAoAgente.add(kGTotalGeral, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 0, 540, 120));

        jfPgtoKmSegudradora.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getCurrencyInstance())));
        jfPgtoKmSegudradora.setText("R$ 0,00");
        jfPgtoKmSegudradora.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jfPgtoKmSegudradora.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jfPgtoKmSegudradoraFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jfPgtoKmSegudradoraFocusLost(evt);
            }
        });
        jfPgtoKmSegudradora.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jfPgtoKmSegudradoraKeyPressed(evt);
            }
        });
        kGRepasseAoAgente.add(jfPgtoKmSegudradora, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 60, 120, 35));

        RegistrarContas.add(kGRepasseAoAgente);

        kGradientPanel1.setkBorderRadius(0);
        kGradientPanel1.setkEndColor(new java.awt.Color(234, 239, 243));
        kGradientPanel1.setkGradientFocus(0);
        kGradientPanel1.setkStartColor(new java.awt.Color(255, 255, 255));
        kGradientPanel1.setMinimumSize(new java.awt.Dimension(1170, 50));
        kGradientPanel1.setPreferredSize(new java.awt.Dimension(1360, 50));
        kGradientPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JbSAlvar.setBackground(new java.awt.Color(184, 252, 184));
        JbSAlvar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        JbSAlvar.setForeground(new java.awt.Color(0, 51, 51));
        JbSAlvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/disk.png"))); // NOI18N
        JbSAlvar.setText("SALVAR");
        JbSAlvar.setPreferredSize(new java.awt.Dimension(150, 30));
        JbSAlvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JbSAlvarActionPerformed(evt);
            }
        });
        kGradientPanel1.add(JbSAlvar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 0, -1, -1));

        btCancelarVoltar.setBackground(new java.awt.Color(236, 209, 191));
        btCancelarVoltar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btCancelarVoltar.setForeground(new java.awt.Color(102, 51, 0));
        btCancelarVoltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/24x24/No.png"))); // NOI18N
        btCancelarVoltar.setText("CANCELAR");
        btCancelarVoltar.setPreferredSize(new java.awt.Dimension(150, 30));
        btCancelarVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarVoltarActionPerformed(evt);
            }
        });
        kGradientPanel1.add(btCancelarVoltar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, -1, -1));

        jLabel13.setText("Tecla Enter para se movimentar.");
        kGradientPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 20, -1, -1));

        RegistrarContas.add(kGradientPanel1);

        jTabbedPanelDespesas.addTab("REGISTRAR DESPESAS", RegistrarContas);

        jPanelPrincipal.add(jTabbedPanelDespesas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 1360, 640));

        getContentPane().add(jPanelPrincipal, java.awt.BorderLayout.PAGE_START);

        getAccessibleContext().setAccessibleDescription("");
        getAccessibleContext().setAccessibleParent(this);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jbExcluirPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbExcluirPagarActionPerformed
        this.excluirConta();
        carregarContasPendentes();
    }//GEN-LAST:event_jbExcluirPagarActionPerformed

    private void btAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAlterarActionPerformed
        try {
            int linha = tbDespesasPendentes.getSelectedRow();
            int codigo = (int) tbDespesasPendentes.getValueAt(linha, 1);
            recuperarConta(codigo);
            tipoCadastro = "alteracao";
            jTabbedPanelDespesas.setSelectedIndex(2);
            panelTabbedKGVerficiar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Você selecionar uma Despesa para alterar!", "ATENÇÂO", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btAlterarActionPerformed

    private void jbVisualizarPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbVisualizarPagarActionPerformed

    }//GEN-LAST:event_jbVisualizarPagarActionPerformed

    private void jbVisualizarPagar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbVisualizarPagar1ActionPerformed

    }//GEN-LAST:event_jbVisualizarPagar1ActionPerformed

    void pagarDespesas(int codigoDespesas, String Verifica) throws Exception {
        Datas bl = new Datas();
        modelContaAgentes = new ModelContaAgentes();
        //   modelContaAgentes = controllerContaAgentes.getContaAgentesController(codigoDespesas);
        modelContaAgentes.setCodigo(codigoDespesas);
        modelContaAgentes.setSituacao(1);
        if ("  /  /    ".equals(Verifica) || "null".equals(Verifica) || Verifica == null) {
            modelContaAgentes.setDataRecebimento(String.valueOf(bl.converterDataParaDateUS(new java.util.Date(System.currentTimeMillis()))));
        } else {
            modelContaAgentes.setDataRecebimento(new Mascaras().DataSalvanosql(Verifica));
        }
        controllerContaAgentes.pagarContaAgentesController(modelContaAgentes);

        System.out.println("Conta paga de numero:");
        System.out.println(codigoDespesas);
    }
    private void jbPagarContaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbPagarContaActionPerformed
        Datas bl = new Datas();
        try {
            //int linha = tbDespesasPendentes.getSelectedRow();
            int linha;
            int codigoMulti;
            //pegunta de todas
            if ((int) tbDespesasPendentes.getSelectedRowCount() > 1) {
                int opcaoPagarTodas = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja"
                        + " pagar todas despesas selecionadas?", "Atenção", JOptionPane.YES_NO_OPTION);
                //se sim paga, se não não faz nada
                if (opcaoPagarTodas == JOptionPane.OK_OPTION) {
                    for (int i = 0; i < tbDespesasPendentes.getSelectedRowCount(); i++) {
                        modelContaAgentes = new ModelContaAgentes();
                        linha = tbDespesasPendentes.getSelectedRow() + i;
                        codigoMulti = (int) tbDespesasPendentes.getValueAt(linha, 1);
                        String Verifica = (String) tbDespesasPendentes.getValueAt(linha, 12);
                        pagarDespesas(codigoMulti, Verifica);
                    }
                    JOptionPane.showMessageDialog(this, "Despesas Pagas!");
                }
                if (opcaoPagarTodas == JOptionPane.OK_OPTION) {
                    carregarContasPendentes();
                    somaDespesasPendentes();
                    carregarContasEfetuadas();
                    somaDespesasEfetuados();
                }

            } else {
                modelContaAgentes = new ModelContaAgentes();
                linha = tbDespesasPendentes.getSelectedRow();
                int codigoSingle = (int) tbDespesasPendentes.getValueAt(linha, 1);
                //pegunta
                int opcao = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja"
                        + " pagar a Despesa nº:\n" + "\n " + codigoSingle + "?", "Atenção", JOptionPane.YES_NO_OPTION);
                //se sim paga, se não não faz nada
                if (opcao == JOptionPane.OK_OPTION) {
                    modelContaAgentes.setCodigo(codigoSingle);
                    modelContaAgentes.setSituacao(1);
                    String Verifica = (String) tbDespesasPendentes.getValueAt(linha, 12);
                    if ("  /  /    ".equals(Verifica) || "null".equals(Verifica) || Verifica == null) {
                        modelContaAgentes.setDataRecebimento(String.valueOf(bl.converterDataParaDateUS(new java.util.Date(System.currentTimeMillis()))));
                    } else {
                        modelContaAgentes.setDataRecebimento(new Mascaras().DataSalvanosql(Verifica));
                    }

                    if (controllerContaAgentes.pagarContaAgentesController(modelContaAgentes)) {
                        JOptionPane.showMessageDialog(this, "Conta Paga!");
                        carregarContasPendentes();
                        somaDespesasPendentes();
                    } else {
                        JOptionPane.showConfirmDialog(this, "Erro ao pagar!", "Atenção", JOptionPane.WARNING_MESSAGE);
                    }
                }
                if (opcao == JOptionPane.OK_OPTION) {
                    carregarContasPendentes();
                    somaDespesasPendentes();
                    carregarContasEfetuadas();
                    somaDespesasEfetuados();
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Você deve selecionar uma Despesa!", "ATENÇÂO", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jbPagarContaActionPerformed

    void revogarDespesas(int codigoDespesa, String Verifica) throws Exception {
        Datas bl = new Datas();
        modelContaAgentes.setCodigo(codigoDespesa);
        modelContaAgentes.setSituacao(0);
        modelContaAgentes.setDataRecebimento(new Mascaras().DataSalvanosql(Verifica));
        controllerContaAgentes.pagarContaAgentesController(modelContaAgentes);
        System.out.println("Despesa Revogada");
        System.out.println(codigoDespesa);
    }
    private void jpRevorgarContaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jpRevorgarContaActionPerformed
        Datas bl = new Datas();
        try {
            //int linha = tbDespesasPendentes.getSelectedRow();
            int linha;
            int codigoMulti;
            String Verifica;
            //pegunta de todas
            if ((int) tbDespesasEfetuadas.getSelectedRowCount() > 1) {
                int opcaoRevogarTodas = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja"
                        + " revogar todas despesas selecionadas?", "Atenção", JOptionPane.YES_NO_OPTION);
                //se sim paga, se não não faz nada
                if (opcaoRevogarTodas == JOptionPane.OK_OPTION) {
                    int removerRecebimentos = JOptionPane.showConfirmDialog(this, "DESEJA REMOVER DATAS DE RECEBIMENTO?", "Atenção", JOptionPane.YES_NO_OPTION);
                    for (int i = 0; i < tbDespesasEfetuadas.getSelectedRowCount(); i++) {
                        modelContaAgentes = new ModelContaAgentes();
                        linha = tbDespesasEfetuadas.getSelectedRow() + i;
                        codigoMulti = (int) tbDespesasEfetuadas.getValueAt(linha, 1);
                        if (removerRecebimentos == JOptionPane.OK_OPTION) {
                            Verifica = "";
                        } else {
                            Verifica = (String) tbDespesasEfetuadas.getValueAt(linha, 12);
                        }
                        revogarDespesas(codigoMulti, Verifica);
                    }
                    JOptionPane.showMessageDialog(this, "Despesas revogadas!");
                }
                if (opcaoRevogarTodas == JOptionPane.OK_OPTION) {
                    carregarContasPendentes();
                    somaDespesasPendentes();
                    carregarContasEfetuadas();
                    somaDespesasEfetuados();
                }

            } else {
                modelContaAgentes = new ModelContaAgentes();
                linha = tbDespesasEfetuadas.getSelectedRow();
                String VerificaSingle;
                int codigoSingle = (int) tbDespesasEfetuadas.getValueAt(linha, 1);
                //pegunta
                int opcao = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja"
                        + " revogar a Despesa nº:\n" + "\n " + codigoSingle + "?", "Atenção", JOptionPane.YES_NO_OPTION);
                //se sim paga, se não não faz nada
                if (opcao == JOptionPane.OK_OPTION) {
                    int removerRecebimentos = JOptionPane.showConfirmDialog(this, "DESEJA REMOVER A DATA DE RECEBIMENTO?", "Atenção", JOptionPane.YES_NO_OPTION);
                    if (removerRecebimentos == JOptionPane.OK_OPTION) {
                        VerificaSingle = "";
                    } else {
                        VerificaSingle = (String) tbDespesasEfetuadas.getValueAt(linha, 12);
                    }
                    modelContaAgentes.setCodigo(codigoSingle);
                    modelContaAgentes.setSituacao(0);
                    modelContaAgentes.setDataRecebimento(new Mascaras().DataSalvanosql(VerificaSingle));

                    if (controllerContaAgentes.pagarContaAgentesController(modelContaAgentes)) {
                        JOptionPane.showMessageDialog(this, "Conta Revogada!");
                    } else {
                        JOptionPane.showConfirmDialog(this, "Erro ao pagar!", "Atenção", JOptionPane.WARNING_MESSAGE);
                    }
                }
                if (opcao == JOptionPane.OK_OPTION) {
                    carregarContasPendentes();
                    somaDespesasPendentes();
                    carregarContasEfetuadas();
                    somaDespesasEfetuados();
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Você deve selecionar uma Despesa!", "ATENÇÂO", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jpRevorgarContaActionPerformed

    private void jTableDespesasAnexoMouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
    }

    private void cbCodAgenteDesignarPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbCodAgenteDesignarPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
    }//GEN-LAST:event_cbCodAgenteDesignarPopupMenuWillBecomeInvisible

    private void btExportarDadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExportarDadosActionPerformed

        String aReceber, adiantamento, hrmaiskm, tituloaReceber, tituloaAdiantamento, tituloaHrMaisKm;

        aReceber = tfAreceberEfetuado.getText();
        adiantamento = tfAdiantamentosEfetuado.getText();
        hrmaiskm = tfNegHrkmEfetuado.getText();
        tituloaReceber = "A RECEBER";
        tituloaAdiantamento = "ADIANTAMENTO";
        tituloaHrMaisKm = "NEG+Honorário+KM";

        String jLTotalneg, jLTotalHr, jLTotalKmPerco, jLTotalPgtoKm, Totalneg, TotalHr, TotalKmPerco, TotalPgtoKm;

        jLTotalneg = "Total Negativa:";
        jLTotalHr = "Total Honorário:";
        jLTotalKmPerco = "Total Percorrido:";
        jLTotalPgtoKm = "Total Pago em KM:";
        Totalneg = jFTotalnegEfetuado.getText();
        TotalHr = jFTotalHrEfetuado.getText();
        TotalKmPerco = jFTotalKmPercoEfetuado.getText();
        TotalPgtoKm = jFTotalPgtoKmEfetuado.getText();

        if (tbDespesasEfetuadas.getRowCount() > 0) {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivos de excel", "xls");
            chooser.setFileFilter(filter);
            chooser.setDialogTitle("Salvar Arquivo");
            chooser.setAcceptAllFileFilterUsed(false);
            if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                List<JTable> tb = new ArrayList<JTable>();
                List<String> nom = new ArrayList<String>();
                tb.add(tbDespesasEfetuadas);
                nom.add("Por Agentes");
                String file = chooser.getSelectedFile().toString().concat(".xls");
                try {
                    Exporter e = new Exporter(new File(file), tb, nom);
                    if (e.exportTotal(aReceber, adiantamento, hrmaiskm, tituloaReceber, tituloaAdiantamento, tituloaHrMaisKm, jLTotalneg, jLTotalHr, jLTotalKmPerco, jLTotalPgtoKm, Totalneg, TotalHr, TotalKmPerco, TotalPgtoKm)) {
                        JOptionPane.showMessageDialog(null, "Os dados foram exportados com sucesso!", "ARQUIVO EXCEL", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Exception Erro " + e.getMessage(), " Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Não há dados para exportar na tabela", "ATENÇÃO", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btExportarDadosActionPerformed

    private void cbFuncionariosEfetuadoPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbFuncionariosEfetuadoPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
        if (this.cbFuncionariosEfetuado.isPopupVisible() && (this.cbFuncionariosEfetuado.getSelectedItem() != null) && !(this.cbFuncionariosEfetuado.getSelectedItem().equals(""))) {
            modelSeguradora = controllerSeguradora.getSeguradoraController(cbFuncionariosEfetuado.getSelectedItem().toString());
            DefaultTableModel tabela = (DefaultTableModel) this.tbDespesasEfetuadas.getModel();
            final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tabela);
            this.tbDespesasEfetuadas.setRowSorter(sorter);
            String text = cbFuncionariosEfetuado.getSelectedItem().toString();
            sorter.setRowFilter(RowFilter.regexFilter(text, 0));
            carregarContasEfetuadas();
            somaDespesasEfetuados();
        } else {
            DefaultTableModel tabela = (DefaultTableModel) this.tbDespesasEfetuadas.getModel();
            this.tbDespesasEfetuadas.setRowSorter(null);
            somaDespesasEfetuados();
        }

    }//GEN-LAST:event_cbFuncionariosEfetuadoPopupMenuWillBecomeInvisible

    private void btClearEfetuadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btClearEfetuadoActionPerformed
        // TODO add your handling code here:
        limparPagas();
    }//GEN-LAST:event_btClearEfetuadoActionPerformed

    private void kButtonPagasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButtonPagasActionPerformed
        // TODO add your handling code here:
        jTabbedPanelDespesas.setSelectedIndex(1);
        panelTabbedKGVerficiar();
    }//GEN-LAST:event_kButtonPagasActionPerformed

    private void kButtonPendentesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButtonPendentesActionPerformed
        // TODO add your handling code here:
        jTabbedPanelDespesas.setSelectedIndex(0);
        panelTabbedKGVerficiar();
    }//GEN-LAST:event_kButtonPendentesActionPerformed

    private void kButtonProcessos2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButtonProcessos2ActionPerformed
        // TODO add your handling code here:
        carregarContasPendentes();
        somaDespesasPendentes();
        carregarContasEfetuadas();
        somaDespesasEfetuados();
    }//GEN-LAST:event_kButtonProcessos2ActionPerformed

    private void btDefinirDataEfetuadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDefinirDataEfetuadoActionPerformed
        // TODO add your handling code here:
        carregarContasEfetuadas();
        somaDespesasEfetuados();
    }//GEN-LAST:event_btDefinirDataEfetuadoActionPerformed

    private void cbFuncionariosPendentesPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbFuncionariosPendentesPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
        if (this.cbFuncionariosPendentes.isPopupVisible() && (this.cbFuncionariosPendentes.getSelectedItem() != null) && !(this.cbFuncionariosPendentes.getSelectedItem().equals(""))) {
            modelSeguradora = controllerSeguradora.getSeguradoraController(cbFuncionariosPendentes.getSelectedItem().toString());
            DefaultTableModel tabela = (DefaultTableModel) this.tbDespesasPendentes.getModel();
            final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tabela);
            this.tbDespesasPendentes.setRowSorter(sorter);
            String text = cbFuncionariosPendentes.getSelectedItem().toString();
            sorter.setRowFilter(RowFilter.regexFilter(text, 0));
            carregarContasPendentes();
            somaDespesasPendentes();
        } else {
            DefaultTableModel tabela = (DefaultTableModel) this.tbDespesasPendentes.getModel();
            this.tbDespesasPendentes.setRowSorter(null);
            somaDespesasPendentes();
        }
    }//GEN-LAST:event_cbFuncionariosPendentesPopupMenuWillBecomeInvisible

    private void btClearPendentesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btClearPendentesActionPerformed
        // TODO add your handling code here:
        limparPendentes();
    }//GEN-LAST:event_btClearPendentesActionPerformed

    private void btDefinirDataPendentesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDefinirDataPendentesActionPerformed
        // TODO add your handling code here:
        carregarContasPendentes();
        somaDespesasPendentes();
    }//GEN-LAST:event_btDefinirDataPendentesActionPerformed

    private void cbCodFuncionarioPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbCodFuncionarioPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
        if (this.cbCodFuncionario.isPopupVisible()) {
            modelFuncionario = controllerFuncionario.getFuncionarioController(Integer.parseInt(cbCodFuncionario.getSelectedItem().toString()));
            //recupera o nome
            this.cbFuncionario.setSelectedItem(String.valueOf(modelFuncionario.getNome()));
        }
    }//GEN-LAST:event_cbCodFuncionarioPopupMenuWillBecomeInvisible

    private void cbFuncionarioPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbFuncionarioPopupMenuWillBecomeInvisible
        if (this.cbFuncionario.isPopupVisible() && this.cbFuncionario.getSelectedObject() != null) {
            modelFuncionario = controllerFuncionario.getFuncionarioController(cbFuncionario.getSelectedItem().toString());
            //recupera o código
            this.cbCodFuncionario.setSelectedItem(modelFuncionario.getCodigo());
            carregarDosAgentesNaTabelaEmContas();
            this.tfCodigoProcesso.setText("");
            this.tfSeguradoraProcesso.setText("");
            this.tfDataProcesso.setText("");
            this.tfSinistroProcesso.setText("");
        }
    }//GEN-LAST:event_cbFuncionarioPopupMenuWillBecomeInvisible

    private void tbProcessosDoAgenteRegistrarContaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbProcessosDoAgenteRegistrarContaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbProcessosDoAgenteRegistrarContaMouseClicked

    private void kButtonRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButtonRegistrarActionPerformed
        // TODO add your handling code here:
        if ("  /  /    ".equals(jFDataEnvio.getText()) || "".equals(jFDataEnvio.getText())) {
            JOptionPane.showMessageDialog(this, "ANTES ESCOLHA O PROCESSO A QUAL IRÁ ALTERAR AS DESPESAS!", "ATENÇÂO", JOptionPane.WARNING_MESSAGE);
            panelTabbedKGVerficiar();
        } else {
            jTabbedPanelDespesas.setSelectedIndex(2);
            panelTabbedKGVerficiar();
            somaTotal();
        }
    }//GEN-LAST:event_kButtonRegistrarActionPerformed

    private void jFDataEnvioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFDataEnvioFocusLost
        // TODO add your handling code here:
        jFDataEnvio.setText(jFDataEnvio.getText().toUpperCase());
    }//GEN-LAST:event_jFDataEnvioFocusLost

    private void jFDataEnvioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFDataEnvioKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jFDataRecebimento.requestFocus();
        }
    }//GEN-LAST:event_jFDataEnvioKeyPressed

    private void jFDataRecebimentoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFDataRecebimentoFocusLost
        // TODO add your handling code here:
        jFDataEnvio.setText(jFDataEnvio.getText().toUpperCase());
    }//GEN-LAST:event_jFDataRecebimentoFocusLost

    private void jFDataRecebimentoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFDataRecebimentoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            tfObservacao.requestFocus();
        }
    }//GEN-LAST:event_jFDataRecebimentoKeyPressed

    private void cbTipoDespesaPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbTipoDespesaPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
    }//GEN-LAST:event_cbTipoDespesaPopupMenuWillBecomeInvisible

    private void tfObservacaoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfObservacaoFocusGained
        // TODO add your handling code here:
        if ("Nenhuma observação.".equals(tfObservacao.getText())) {
            tfObservacao.setText("");
        }
    }//GEN-LAST:event_tfObservacaoFocusGained

    private void tfObservacaoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfObservacaoFocusLost
        // TODO add your handling code here:
        if (!"".equals(tfObservacao.getText())) {
            tfObservacao.setText(tfObservacao.getText());
        } else {
            tfObservacao.setText("Nenhuma observação.");
        }
    }//GEN-LAST:event_tfObservacaoFocusLost

    private void btAdiantamentoAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAdiantamentoAlterarActionPerformed
        // TODO add your handling code here:
        if (!("".equals(tfDescricaoDespesaAlterar.getText())) && !("R$ 0,00".equals(tfValorDespesaAlterar.getText())) && !("  /  /    ".equals(tfDataDespesaAlterar.getText()))) {
            String insertar = "INSERT INTO adiantamentos(cod_despesa,descricao,valor,data) VALUES (?,?,?,?)";
            int codDespesa = 0;
            try {
                codDespesa = (Integer.parseInt(jtfCodigoConta.getText()));
            } catch (NumberFormatException e) {
                codDespesa = 0;
            }
            try {
                // variável para obter o número de bytes de uma imagem.
                PreparedStatement pst = cn.prepareStatement(insertar);
                pst.setInt(1, codDespesa);
                pst.setString(2, tfDescricaoDespesaAlterar.getText());
                pst.setString(3, (new Moeda().FommatoStringoSomarMil(tfValorDespesaAlterar.getText())));
                pst.setString(4, (new Mascaras().DataSalvanosql(tfDataDespesaAlterar.getText())));

                int i = pst.executeUpdate();
                if (i > 0) {
                    // JOptionPane.showMessageDialog(null, "OCUPAÇÃO SALVO COM SUCESSO!");
                    this.carregarAdiantamentosAlteracao();
                    this.tfValorDespesaAlterar.setText("R$ 0,00");
                    this.tfDataDespesaAlterar.setText("");
                    this.tfDescricaoDespesaAlterar.setText("");
                    somaTotal();
                    tfValidarDataAlterar.setVisible(false);
                    tfValidarValorAlterar.setVisible(false);
                    tfValidarDescricaoAlterar.setVisible(false);
                }
            } catch (SQLException | HeadlessException e) {
                JOptionPane.showMessageDialog(this, "Erro ao adicionar!", "", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            if ("".equals(tfDescricaoDespesaAlterar.getText())) {

                tfValidarDescricaoAlterar.setVisible(true);
            } else {
                tfValidarDescricaoAlterar.setVisible(false);
            }
            if ("R$ 0,00".equals(tfValorDespesaAlterar.getText())) {
                tfValidarValorAlterar.setVisible(true);
            } else {
                tfValidarValorAlterar.setVisible(false);
            }
            if ("  /  /    ".equals(tfDataDespesaAlterar.getText())) {
                tfValidarDataAlterar.setVisible(true);
            } else {
                tfValidarDataAlterar.setVisible(false);
            }
        }
    }//GEN-LAST:event_btAdiantamentoAlterarActionPerformed

    private void btExcluirAdiantamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirAdiantamentoActionPerformed
        // TODO add your handling code here:
        int fila = tbAdiantamentoAlterar.getSelectedRow();
        if (fila > -1) {
            String cod = tbAdiantamentoAlterar.getValueAt(fila, 0).toString();
            String sqlElim = "DELETE FROM adiantamentos WHERE codigo='" + cod + "'";
            try {
                PreparedStatement pst = cn.prepareStatement(sqlElim);
                int n = pst.executeUpdate();
                if (n > 0) {
                    // JOptionPane.showMessageDialog(null, "O Adiantamento foi deletado com sucesso!");
                    this.carregarAdiantamentosAlteracao();
                    somaTotal();
                    tfValidarDataAlterar.setVisible(false);
                    tfValidarValorAlterar.setVisible(false);
                    tfValidarDescricaoAlterar.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Ocorreu um erro ao deletar adiantamento");
                }
            } catch (SQLException ex) {
                Logger.getLogger(ViewCadOcupacao.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_btExcluirAdiantamentoActionPerformed

    private void tfValorDespesaAlterarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfValorDespesaAlterarFocusGained
        // TODO add your handling code here:
        if ("R$ 0,00".equals(tfValorDespesaAlterar.getText())) {
            tfValorDespesaAlterar.setText("");
        }
    }//GEN-LAST:event_tfValorDespesaAlterarFocusGained

    private void tfValorDespesaAlterarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfValorDespesaAlterarFocusLost
        // TODO add your handling code here:
        try {
            if ("".equals(tfValorDespesaAlterar.getText())) {
                tfValorDespesaAlterar.setText("R$ 0,00");
            }
            this.tfValorDespesaAlterar.setText(new Moeda().valorStringTODoubleAtt(tfValorDespesaAlterar.getText()));
            jfAgenteTotalRepasse.setText(somaValorAgente());
            somaTotal();
        } catch (NumberFormatException e) {
            tfValorDespesaAlterar.setText("R$ 0,00");
            jfAgenteTotalRepasse.setText(somaValorAgente());
            somaTotal();
        }
    }//GEN-LAST:event_tfValorDespesaAlterarFocusLost

    private void tfValorDespesaAlterarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfValorDespesaAlterarKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btAdiantamentoAlterar.requestFocus();
        }
    }//GEN-LAST:event_tfValorDespesaAlterarKeyPressed

    private void tfDataDespesaAlterarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfDataDespesaAlterarFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_tfDataDespesaAlterarFocusLost

    private void tfDataDespesaAlterarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfDataDespesaAlterarKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            tfValorDespesaAlterar.requestFocus();
        }
    }//GEN-LAST:event_tfDataDespesaAlterarKeyPressed

    private void tfDescricaoDespesaAlterarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfDescricaoDespesaAlterarKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            tfDataDespesaAlterar.requestFocus();
        }
    }//GEN-LAST:event_tfDescricaoDespesaAlterarKeyPressed

    private void kButtonProcessos4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButtonProcessos4ActionPerformed
        // TODO add your handling code here:
        somaAdiantamentosEfetuados();
        somaValorAgente();
        somaTotal();
    }//GEN-LAST:event_kButtonProcessos4ActionPerformed

    private void jFValorNegFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFValorNegFocusGained
        // TODO add your handling code here:
        if ("R$ 0,00".equals(jFValorNeg.getText())) {
            jFValorNeg.setText("");
        }
    }//GEN-LAST:event_jFValorNegFocusGained

    private void jFValorNegFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFValorNegFocusLost
        // TODO add your handling code here:
        try {
            jFValorNeg.setText(new Moeda().valorStringTODoubleAtt(jFValorNeg.getText()));
            this.jfAgenteTotalRepasse.setText(somaValorAgente());
            somaTotal();
        } catch (NumberFormatException e) {
            if (jFValorNeg.getText().equals("") || (Double.parseDouble(new Moeda().valorStringTODoubleAtt(jFValorNeg.getText())) <= 0)) {
                jFValorNeg.setText("R$ 0,00");
            }
            this.jfAgenteTotalRepasse.setText(somaValorAgente());
            somaTotal();
        }
    }//GEN-LAST:event_jFValorNegFocusLost

    private void jFValorNegKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFValorNegKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jFDataNeg.requestFocus();
            this.jfAgenteTotalRepasse.setText(somaValorAgente());
            somaTotal();
        }
    }//GEN-LAST:event_jFValorNegKeyPressed

    private void jFDataNegFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFDataNegFocusLost
        // TODO add your handling code here:
        jFDataNeg.setText(jFDataNeg.getText().toUpperCase());
    }//GEN-LAST:event_jFDataNegFocusLost

    private void jFDataNegKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFDataNegKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            tfValorDespesaAlterar.requestFocus();
        }
    }//GEN-LAST:event_jFDataNegKeyPressed

    private void jfAgenteHonorarioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jfAgenteHonorarioFocusGained
        // TODO add your handling code here:
        if ("R$ 0,00".equals(jfAgenteHonorario.getText())) {
            jfAgenteHonorario.setText("");
        }
    }//GEN-LAST:event_jfAgenteHonorarioFocusGained

    private void jfAgenteHonorarioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jfAgenteHonorarioFocusLost
        //Valor pago ao agente referente aos honorarios
        if ("".equals(jfAgenteHonorario.getText()) || Double.parseDouble(new Moeda().FommatoStringoSomarMil(jfAgenteHonorario.getText())) <= 0) {
            jfAgenteHonorario.setText("R$ 0,00");
            jfAgenteTotalRepasse.setText(somaValorAgente());
            somaTotal();
        }
        try {
            this.jfAgenteHonorario.setText(new Moeda().valorStringTODoubleAtt(jfAgenteHonorario.getText()));
            jfAgenteTotalRepasse.setText(somaValorAgente());
            somaTotal();
        } catch (NumberFormatException e) {
            if ("".equals(jfAgenteHonorario.getText()) || Double.parseDouble(new Moeda().FommatoStringoSomarMil(jfAgenteHonorario.getText())) <= 0) {
                jfAgenteHonorario.setText("R$ 0,00");
                jfAgenteTotalRepasse.setText(somaValorAgente());
                somaTotal();
            }
        }
    }//GEN-LAST:event_jfAgenteHonorarioFocusLost

    private void jfAgenteHonorarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jfAgenteHonorarioKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jfAgenteKmPercorrido.requestFocus();
            if ("".equals(jfAgenteHonorario.getText()) || Double.parseDouble(new Moeda().FommatoStringoSomarMil(jfAgenteHonorario.getText())) <= 0) {
                jfAgenteHonorario.setText("R$ 0,00");
                this.jfAgenteTotalRepasse.setText(somaValorAgente());
                somaTotal();
            } else {
                this.jfAgenteTotalRepasse.setText(somaValorAgente());
                somaTotal();
            }
        }
    }//GEN-LAST:event_jfAgenteHonorarioKeyPressed

    private void jfAgenteKmPercorridoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jfAgenteKmPercorridoFocusGained
        // TODO add your handling code here:
        // TODO add your handling code here:
        if ("0".equals(jfAgenteKmPercorrido.getText()) || (Integer.parseInt(jfAgenteKmPercorrido.getText()) <= 0)) {
            jfAgenteKmPercorrido.setText("");
        } else {
            jfAgenteKmPercorrido.setText(jfAgenteKmPercorrido.getText());
        }
    }//GEN-LAST:event_jfAgenteKmPercorridoFocusGained

    private void jfAgenteKmPercorridoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jfAgenteKmPercorridoFocusLost
        // KILOMETRO PERCORRIDO
        try {
            this.jfAgenteKmPercorrido.setText(jfAgenteKmPercorrido.getText());
            this.jfAgenteTotalRepasse.setText(somaValorAgente());
            somaTotal();
            if (jfAgenteKmPercorrido.getText().equals("")) {
                jfAgenteKmPercorrido.setText("0");
            }
        } catch (NumberFormatException e) {
            this.jfAgenteKmPercorrido.setText("0");
            this.jfAgenteTotalRepasse.setText(somaValorAgente());
            somaTotal();
        }
    }//GEN-LAST:event_jfAgenteKmPercorridoFocusLost

    private void jfAgenteKmPercorridoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jfAgenteKmPercorridoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jfPgtoKmSegudradora.requestFocus();
            this.jfAgenteTotalRepasse.setText(somaValorAgente());
            somaTotal();
        }
    }//GEN-LAST:event_jfAgenteKmPercorridoKeyPressed

    private void jfPgtoKmSegudradoraFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jfPgtoKmSegudradoraFocusGained
        // TODO add your handling code here:
        if ("R$ 0,00".equals(jfPgtoKmSegudradora.getText())) {
            jfPgtoKmSegudradora.setText("");
        }
    }//GEN-LAST:event_jfPgtoKmSegudradoraFocusGained

    private void jfPgtoKmSegudradoraFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jfPgtoKmSegudradoraFocusLost
        // TODO add your handling code here:
        try {
            jfPgtoKmSegudradora.setText(new Moeda().valorStringTODoubleAtt(jfPgtoKmSegudradora.getText()));
            jfAgenteTotalRepasse.setText(somaValorAgente());
            somaTotal();
        } catch (NumberFormatException e) {
            jfPgtoKmSegudradora.setText("R$ 0,00");
            jfAgenteTotalRepasse.setText(somaValorAgente());
            somaTotal();
        }
    }//GEN-LAST:event_jfPgtoKmSegudradoraFocusLost

    private void jfPgtoKmSegudradoraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jfPgtoKmSegudradoraKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            this.JbSAlvar.requestFocus();
            jfAgenteTotalRepasse.setText(somaValorAgente());
            somaTotal();
        }
    }//GEN-LAST:event_jfPgtoKmSegudradoraKeyPressed

    private void JbSAlvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JbSAlvarActionPerformed
        //verificação
        if (Double.parseDouble(String.valueOf(tfTotalAReceber.getText().replace("R$", "").replace(" ", "").replace(".", "").replace(",", "."))) < 0) {
            jLCampoNegativo.setVisible(true);
        } else {
            jLCampoNegativo.setVisible(false);
        }
        if ((cbTipoDespesa.getSelectedObject() == null) || "".equals(tfCodigoProcesso.getText()) || (Double.parseDouble(String.valueOf(tfTotalAReceber.getText().replace("R$", "").replace(" ", "").replace(".", "").replace(",", "."))) < 0)) {
            JOptionPane.showMessageDialog(this, "Verifique todos os campos!", "ATENÇÂO", JOptionPane.WARNING_MESSAGE);
        } else {
            this.alterar();
            jTabbedPanelDespesas.setSelectedIndex(0);
            panelTabbedKGVerficiar();
            limparDados();
            carregarContasPendentes();
            somaDespesasPendentes();
            carregarContasEfetuadas();
            somaDespesasEfetuados();
        }
    }//GEN-LAST:event_JbSAlvarActionPerformed

    private void btCancelarVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarVoltarActionPerformed
        // TODO add your handling code here:
        panelTabbedKGVerficiar();
        if (tipoCadastro != null && tipoCadastro.equals("aberto")) {
            this.salvar();
            jTabbedPanelDespesas.setSelectedIndex(0);
            panelTabbedKGVerficiar();
            limparDados();
            carregarContasPendentes();
            somaDespesasPendentes();
            carregarContasEfetuadas();
            somaDespesasEfetuados();
        } else {
            jTabbedPanelDespesas.setSelectedIndex(0);
            this.limparDados();
            panelTabbedKGVerficiar();
        }
    }//GEN-LAST:event_btCancelarVoltarActionPerformed

    private void tfDescricaoDespesaAlterarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfDescricaoDespesaAlterarFocusLost
        // TODO add your handling code here:
        tfDescricaoDespesaAlterar.setText(tfDescricaoDespesaAlterar.getText().toUpperCase());
    }//GEN-LAST:event_tfDescricaoDespesaAlterarFocusLost
    private void panelTabbedKGVerficiar() {

        if (jTabbedPanelDespesas.getSelectedIndex() == 0) {
            kButtonPendentes.setSelected(true);
        } else {
            kButtonPendentes.setSelected(false);
        }
        if (jTabbedPanelDespesas.getSelectedIndex() == 1) {
            kButtonPagas.setSelected(true);
        } else {
            kButtonPagas.setSelected(false);
        }
        if (jTabbedPanelDespesas.getSelectedIndex() == 2) {
            kButtonRegistrar.setSelected(true);
        } else {
            kButtonRegistrar.setSelected(false);
        }
    }

    void carregarAdiantamentosAlteracao() {
        String DescAdiantamentos = "";
        if ((int) tbAdiantamentoAlterar.getRowCount() > 0) {
            DefaultTableModel modelo = (DefaultTableModel) tbAdiantamentoAlterar.getModel();
            modelo.setNumRows(0);
        }
        try {
            int codigo = 0;
            codigo = (Integer.parseInt(jtfCodigoConta.getText()));
            DefaultTableModel model = (DefaultTableModel) tbAdiantamentoAlterar.getModel();
            String consultar = "SELECT * FROM adiantamentos WHERE cod_despesa='" + codigo + "'";
            String[] datos = new String[4];
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(consultar);
            while (rs.next()) {
                datos[0] = rs.getString("codigo");
                datos[1] = rs.getString("descricao");
                datos[2] = (new Moeda().valorStringTODoubleAtt(rs.getString("valor")));
                datos[3] = (new Mascaras().DataRecuperasql(rs.getString("data")));

                model.addRow(datos);
                DescAdiantamentos = DescAdiantamentos + " \n " + rs.getString("descricao") + " Pago: " + (new Moeda().valorStringTODoubleAtt(rs.getString("valor")));
            }
            tbAdiantamentoAlterar.setModel(model);
            jLDescricaoAdiantamento.setText(DescAdiantamentos);
        } catch (SQLException ex) {
            Logger.getLogger(ViewCadISS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Excluir dados do banco
     *
     * @return
     */
    private boolean excluirConta() {
        int linha = tbDespesasPendentes.getSelectedRow();
        int nome = (int) tbDespesasPendentes.getValueAt(linha, 1);
        int codigo = (int) tbDespesasPendentes.getValueAt(linha, 1);

        //pegunta se realmente deseja excluir
        int opcao = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja"
                + " excluir o registro:\n" + "\n " + nome + "?", "Atenção", JOptionPane.YES_NO_OPTION);
        //se sim exclui, se não não faz nada
        if (opcao == JOptionPane.OK_OPTION) {
            if (controllerContaAgentes.excluirContaAgentesController(codigo)) {
                JOptionPane.showMessageDialog(this, "Registro excluido com suscesso!");
                carregarContasPendentes();
            } else {
                JOptionPane.showMessageDialog(this, "Você não excluiu!", "ERRO", JOptionPane.WARNING_MESSAGE);
            }
            return true;
        } else {
            JOptionPane.showMessageDialog(this, "Você não excluiu!", "Atenção", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }

    private void carregarContasPendentes() {
        Datas bl = new Datas();

        java.sql.Date dataInicial = null;
        java.sql.Date dataFinal = null;

        try {
            dataInicial = bl.converterDataParaDateUS(bl.converterDataParaDateUS(jdcDataInicialPendentes.getDate()));
            dataFinal = bl.converterDataParaDateUS(bl.converterDataParaDateUS(jdcDataFinalPendentes.getDate()));
        } catch (Exception ex) {
            /*Logger.getLogger(ViewOp.class.getName()).log(Level.SEVERE, null, ex);*/
        }

        //String dataComeco = String.valueOf(dataInicial);
        //String dataFinal = String.valueOf(dataFinal)
        ModelContaAgentes pModelContaAgentes = new ModelContaAgentes();

        DefaultTableModel modelo = (DefaultTableModel) tbDespesasPendentes.getModel();
        int pSituacao = 0;
        controllerContaAgentes.FiltraContaTodosAgentesFluxoDadosController(pSituacao, dataInicial, dataFinal);

        listaModelContaAgentes = controllerContaAgentes.FiltraContaTodosAgentesFluxoDadosController(pSituacao, dataInicial, dataFinal);
        modelo.setNumRows(0);
        //CARREGA OS DADOS DA LISTA NA TABELA
        int cont = listaModelContaAgentes.size();
        for (int i = 0; i < cont; i++) {
            modelOp = controllerOp.getOpController(listaModelContaAgentes.get(i).getProcessos());
            modelFormaPagamento = controllerTipoPagamento.getFormaPagamentoController(modelOp.getTipo());
            modelSeguradora = controllerSeguradora.getSeguradoraController(modelOp.getSeguradoras());
            modelServicos = controllerServicos.getServicosController(modelOp.getServicosCodigo());
            modelFuncionario = controllerFuncionario.getFuncionarioController(listaModelContaAgentes.get(i).getCodigoPessoa());
            modelo.addRow(new Object[]{
                modelFuncionario.getNome(),
                listaModelContaAgentes.get(i).getCodigo(),
                modelOp.getCodigo(),
                (new Mascaras().DataRecuperasql(listaModelContaAgentes.get(i).getDataEnvio())),
                modelSeguradora.getNome(),
                modelOp.getNumeroSinistro(),
                modelServicos.getNome(),
                (new Moeda().valorStringTODoubleAtt(String.valueOf(listaModelContaAgentes.get(i).getValorNeg()))),
                (new Moeda().valorStringTODoubleAtt(String.valueOf(listaModelContaAgentes.get(i).getValorHonorarioAgt()))),
                (listaModelContaAgentes.get(i).getAgenteKmPercorrido()),
                (new Moeda().valorStringTODoubleAtt(String.valueOf(listaModelContaAgentes.get(i).getValorPGTOKMSeguradora()))),
                (new Moeda().valorStringTODoubleAtt(String.valueOf(listaModelContaAgentes.get(i).getValorPGTOtotalKM()))),
                (new Mascaras().DataRecuperasql(listaModelContaAgentes.get(i).getDataRecebimento())),
                listaModelContaAgentes.get(i).getDescricao(),
                (new Moeda().valorStringTODoubleAtt(String.valueOf(listaModelContaAgentes.get(i).getValorAdiantamento()))),
                (new Moeda().valorStringTODoubleAtt(String.valueOf(listaModelContaAgentes.get(i).getValorHRMaisKM()))),
                (new Moeda().valorStringTODoubleAtt(String.valueOf(listaModelContaAgentes.get(i).getValorAReceber())))
            });
        }
    }

    private void carregarContasEfetuadas() {
        Datas bl = new Datas();

        java.sql.Date dataInicial = null;
        java.sql.Date dataFinal = null;

        try {
            dataInicial = bl.converterDataParaDateUS(bl.converterDataParaDateUS(jdcDataInicialEfetuado.getDate()));
            dataFinal = bl.converterDataParaDateUS(bl.converterDataParaDateUS(jdcDataFinalEfetuado.getDate()));
        } catch (Exception ex) {
            /*Logger.getLogger(ViewOp.class.getName()).log(Level.SEVERE, null, ex);*/
        }

        //String dataComeco = String.valueOf(dataInicial);
        //String dataFinal = String.valueOf(dataFinal)
        ModelContaAgentes pModelContaAgentes = new ModelContaAgentes();

        DefaultTableModel modelo = (DefaultTableModel) tbDespesasEfetuadas.getModel();
        int pSituacao = 1;
        controllerContaAgentes.FiltraContaTodosAgentesFluxoDadosController(pSituacao, dataInicial, dataFinal);

        listaModelContaAgentes = controllerContaAgentes.FiltraContaTodosAgentesFluxoDadosController(pSituacao, dataInicial, dataFinal);
        // listaModelContaAgentes = controllerContaAgentes.getListaContasController(1);
        listaModelOp = controllerOp.getListaCompletaController();
        modelo.setNumRows(0);
        String DescricaoAdiantamento;
        //CARREGA OS DADOS DA LISTA NA TABELA
        int cont = listaModelContaAgentes.size();
        for (int i = 0; i < cont; i++) {
            modelOp = controllerOp.getOpController(listaModelContaAgentes.get(i).getProcessos());
            modelFormaPagamento = controllerTipoPagamento.getFormaPagamentoController(modelOp.getTipo());
            modelSeguradora = controllerSeguradora.getSeguradoraController(modelOp.getSeguradoras());
            modelServicos = controllerServicos.getServicosController(modelOp.getServicosCodigo());
            modelFuncionario = controllerFuncionario.getFuncionarioController(listaModelContaAgentes.get(i).getCodigoPessoa());

            modelo.addRow(new Object[]{
                modelFuncionario.getNome(),
                listaModelContaAgentes.get(i).getCodigo(),
                modelOp.getCodigo(),
                (new Mascaras().DataRecuperasql(listaModelContaAgentes.get(i).getDataEnvio())),
                modelSeguradora.getNome(),
                modelOp.getNumeroSinistro(),
                modelServicos.getNome(),
                (new Moeda().valorStringTODoubleAtt(String.valueOf(listaModelContaAgentes.get(i).getValorNeg()))),
                (new Moeda().valorStringTODoubleAtt(String.valueOf(listaModelContaAgentes.get(i).getValorHonorarioAgt()))),
                listaModelContaAgentes.get(i).getAgenteKmPercorrido(),
                (new Moeda().valorStringTODoubleAtt(String.valueOf(listaModelContaAgentes.get(i).getValorPGTOKMSeguradora()))),
                (new Moeda().valorStringTODoubleAtt(String.valueOf(listaModelContaAgentes.get(i).getValorPGTOtotalKM()))),
                (new Mascaras().DataRecuperasql(listaModelContaAgentes.get(i).getDataRecebimento())),
                listaModelContaAgentes.get(i).getDescricao(),
                (new Moeda().valorStringTODoubleAtt(String.valueOf(listaModelContaAgentes.get(i).getValorAdiantamento()))),
                (new Moeda().valorStringTODoubleAtt(String.valueOf(listaModelContaAgentes.get(i).getValorHRMaisKM()))),
                (new Moeda().valorStringTODoubleAtt(String.valueOf(listaModelContaAgentes.get(i).getValorAReceber())))
            });
        }
    }

    Mascaras swMascaras = new Mascaras();

    private void somaDespesasPendentes() {
        somaAdiantamentosPendentes();
        somaNegHrKmPendentes();
        somaAReceberPendentes();
        somaSeparadasPendentes();
    }

    private void somaSeparadasPendentes() {
        float Totalneg = 0;
        float TotalHr = 0;
        float TotalKmPerco = 0;
        float TotalPgtoKm = 0;
        float valor = 0;
        int cont = tbDespesasPendentes.getRowCount();
        for (int i = 0; i < cont; i++) {
            valor = Float.parseFloat(new Moeda().FommatoStringoSomarMil(String.valueOf(tbDespesasPendentes.getValueAt(i, 7))));
            Totalneg = valor + Totalneg;
        }
        valor = 0;
        for (int i = 0; i < cont; i++) {
            valor = Float.parseFloat(new Moeda().FommatoStringoSomarMil(String.valueOf(tbDespesasPendentes.getValueAt(i, 8))));
            TotalHr = valor + TotalHr;
        }
        valor = 0;
        for (int i = 0; i < cont; i++) {
            valor = Float.parseFloat(new Moeda().FommatoStringoSomarMil(String.valueOf(tbDespesasPendentes.getValueAt(i, 9))));
            TotalKmPerco = valor + TotalKmPerco;
        }
        valor = 0;
        for (int i = 0; i < cont; i++) {
            valor = Float.parseFloat(new Moeda().FommatoStringoSomarMil(String.valueOf(tbDespesasPendentes.getValueAt(i, 11))));
            TotalPgtoKm = valor + TotalPgtoKm;
        }
        jFTotalnegPendentes.setText(new Moeda().valorStringTODoubleAtt(String.valueOf(Totalneg)));
        jFTotalHrPendentes.setText(new Moeda().valorStringTODoubleAtt(String.valueOf(TotalHr)));
        jFTotalKmPercoPendentes.setText(new Moeda().valorStringTODoubleAtt(String.valueOf(TotalKmPerco)));
        jFTotalPgtoKmPendentes.setText(new Moeda().valorStringTODoubleAtt(String.valueOf(TotalPgtoKm)));
    }

    private void somaAdiantamentosPendentes() {
        float soma = 0;
        float valor = 0;
        int cont = tbDespesasPendentes.getRowCount();
        for (int i = 0; i < cont; i++) {
            valor = Float.parseFloat(new Moeda().FommatoStringoSomarMil(String.valueOf(tbDespesasPendentes.getValueAt(i, 14))));
            soma = valor + soma;
        }
        tfAdiantamentosPendentes.setText(new Moeda().valorStringTODoubleAtt(String.valueOf(soma)));
    }

    private void somaNegHrKmPendentes() {
        float soma = 0;
        float valor = 0;
        int cont = tbDespesasPendentes.getRowCount();
        for (int i = 0; i < cont; i++) {
            valor = Float.parseFloat(new Moeda().FommatoStringoSomarMil(String.valueOf(tbDespesasPendentes.getValueAt(i, 15))));
            soma = valor + soma;
        }
        tfNegHrKmPendentes.setText(new Moeda().valorStringTODoubleAtt(String.valueOf(String.valueOf(soma))));
    }

    private void somaAReceberPendentes() {
        float soma = 0;
        float valor = 0;
        int cont = tbDespesasPendentes.getRowCount();
        for (int i = 0; i < cont; i++) {
            valor = Float.parseFloat(new Moeda().FommatoStringoSomarMil(String.valueOf(tbDespesasPendentes.getValueAt(i, 16))));
            soma = valor + soma;
        }
        tfAreceberPendentes.setText(new Moeda().valorStringTODoubleAtt(String.valueOf(soma)));
    }

    private void somaDespesasEfetuados() {
        somaAdiantamentosEfetuados();
        somaNegHrKmEfetuados();
        somaAReceberEfetuados();
        somaSeparadasEfetuadas();
    }

    private void somaSeparadasEfetuadas() {
        float Totalneg = 0;
        float TotalHr = 0;
        float TotalKmPerco = 0;
        float TotalPgtoKm = 0;
        float valor = 0;
        int cont = tbDespesasEfetuadas.getRowCount();
        for (int i = 0; i < cont; i++) {
            valor = Float.parseFloat(new Moeda().FommatoStringoSomarMil(String.valueOf(tbDespesasEfetuadas.getValueAt(i, 7))));
            Totalneg = valor + Totalneg;
        }
        valor = 0;
        for (int i = 0; i < cont; i++) {
            valor = Float.parseFloat(new Moeda().FommatoStringoSomarMil(String.valueOf(tbDespesasEfetuadas.getValueAt(i, 8))));
            TotalHr = valor + TotalHr;
        }
        valor = 0;
        for (int i = 0; i < cont; i++) {
            valor = Float.parseFloat(new Moeda().FommatoStringoSomarMil(String.valueOf(tbDespesasEfetuadas.getValueAt(i, 9))));
            TotalKmPerco = valor + TotalKmPerco;
        }
        valor = 0;
        for (int i = 0; i < cont; i++) {
            valor = Float.parseFloat(new Moeda().FommatoStringoSomarMil(String.valueOf(tbDespesasEfetuadas.getValueAt(i, 11))));
            TotalPgtoKm = valor + TotalPgtoKm;
        }
        jFTotalnegEfetuado.setText(new Moeda().valorStringTODoubleAtt(String.valueOf(Totalneg)));
        jFTotalHrEfetuado.setText(new Moeda().valorStringTODoubleAtt(String.valueOf(TotalHr)));
        jFTotalKmPercoEfetuado.setText(new Moeda().valorStringTODoubleAtt(String.valueOf(TotalKmPerco)));
        jFTotalPgtoKmEfetuado.setText(new Moeda().valorStringTODoubleAtt(String.valueOf(TotalPgtoKm)));
    }

    private void somaAdiantamentosEfetuados() {
        float soma = 0;
        float valor = 0;
        int cont = tbDespesasEfetuadas.getRowCount();
        for (int i = 0; i < cont; i++) {
            valor = Float.parseFloat(new Moeda().FommatoStringoSomarMil(String.valueOf(tbDespesasEfetuadas.getValueAt(i, 14))));
            soma = valor + soma;
        }
        tfAdiantamentosEfetuado.setText(new Moeda().valorStringTODoubleAtt(String.valueOf(soma)));
    }

    private void somaNegHrKmEfetuados() {
        float soma = 0;
        float valor = 0;
        int cont = tbDespesasEfetuadas.getRowCount();
        for (int i = 0; i < cont; i++) {
            valor = Float.parseFloat(new Moeda().FommatoStringoSomarMil(String.valueOf(tbDespesasEfetuadas.getValueAt(i, 15))));
            soma = valor + soma;
        }
        tfNegHrkmEfetuado.setText(new Moeda().valorStringTODoubleAtt(String.valueOf(soma)));
    }

    private void somaAReceberEfetuados() {
        float soma = 0;
        float valor = 0;
        int cont = tbDespesasEfetuadas.getRowCount();
        for (int i = 0; i < cont; i++) {
            valor = Float.parseFloat(new Moeda().FommatoStringoSomarMil(String.valueOf(tbDespesasEfetuadas.getValueAt(i, 16))));
            soma = valor + soma;
        }
        tfAreceberEfetuado.setText(new Moeda().valorStringTODoubleAtt(String.valueOf(soma)));
    }

    /**
     * REcuperar dados do banco para alterar
     *
     * @param pCodigoConta
     * @return
     */
    private boolean recuperarConta(int pCodigoConta) {
        modelContaAgentes = new ModelContaAgentes();
        modelContaAgentes = controllerContaAgentes.getContaAgentesController(pCodigoConta);
        this.cbFuncionario.setSelectedItem(controllerFuncionario.getFuncionarioController(modelContaAgentes.getCodigoPessoa()).getNome());
        cbCodFuncionario.setSelectedItem(controllerFuncionario.getFuncionarioController(modelContaAgentes.getCodigoPessoa()).getCodigo());
        this.jtfCodigoConta.setText(String.valueOf(pCodigoConta));
        this.jLDescricaoAdiantamento.setText(modelContaAgentes.getDescricao());
        this.tfObservacao.setText(modelContaAgentes.getObservacao());
        this.tfDataDespesaAlterar.setText(new Mascaras().DataRecuperasql(modelContaAgentes.getDataDespesa()));
        this.jFDataEnvio.setText(new Mascaras().DataRecuperasql(modelContaAgentes.getDataEnvio()));
        if (!(modelContaAgentes.getDataRecebimento().equals("  /  /    ")) && !(modelContaAgentes.getDataRecebimento().equals(""))) {
            this.jFDataRecebimento.setText(new Mascaras().DataRecuperasql(modelContaAgentes.getDataRecebimento()));
        }
        this.totalAdiantamentosAlterar.setText(new Moeda().valorStringTODoubleAtt(String.valueOf(modelContaAgentes.getValorAdiantamento())));
        this.cbTipoDespesa.setSelectedItem(controllerTipoDespesas.getTipoDespesasController(modelContaAgentes.getTipoPagamento()).getNome());
        this.cbBancos.setSelectedItem(controllerBancos.getBancosControllerCod(modelContaAgentes.getBanco()).getBanco_nome());
        //VERIFICA SE EXISTE REPASSE
        this.tfCodigoProcesso.setText(String.valueOf(controllerOp.getOpController(modelContaAgentes.getProcessos()).getCodigo()));
        this.jfPgtoKmSegudradora.setText(new Moeda().valorStringTODoubleAtt(modelContaAgentes.getValorPGTOKMSeguradora().toString()));
        this.jfAgenteHonorario.setText(new Moeda().valorStringTODoubleAtt(String.valueOf(modelContaAgentes.getValorHonorarioAgt())));
        this.jfAgenteKmPercorrido.setText(String.valueOf(modelContaAgentes.getAgenteKmPercorrido().toString()));
        this.jFPagamentoTotalKM.setText(new Moeda().valorStringTODoubleAtt(String.valueOf(modelContaAgentes.getValorPGTOtotalKM())));
        this.jfAgenteTotalRepasse.setText(new Moeda().valorStringTODoubleAtt(String.valueOf(modelContaAgentes.getValorHRMaisKM())));
        this.tfTotalAReceber.setText(new Moeda().valorStringTODoubleAtt(String.valueOf(modelContaAgentes.getValorAReceber())));
        this.jLNomeAgente.setText(cbFuncionario.getSelectedItem().toString());
        this.jFValorNeg.setText(new Moeda().valorStringTODoubleAtt(String.valueOf(modelContaAgentes.getValorNeg())));
        this.jFDataNeg.setText(new Mascaras().DataRecuperasql(modelContaAgentes.getDataNeg()));
        this.recuperarDadosPorProcesso();

        carregarAdiantamentosAlteracao();
        this.somaAdiantamentosEfetuados();
        somaValorAgente();
        somaTotal();
        return true;
    }

    /**
     * Salvar dados
     *
     * @return
     */
    private boolean salvar() {
        modelContaAgentes.setTipoConta("PAGAR");
        modelContaAgentes.setCodigoPessoa(controllerFuncionario.getFuncionarioController(this.cbFuncionario.getSelectedItem().toString()).getCodigo());
        modelContaAgentes.setDescricao(this.jLDescricaoAdiantamento.getText());
        modelContaAgentes.setDataDespesa(new Mascaras().DataSalvanosql(String.valueOf(tfDataDespesaAlterar.getText())));
        modelContaAgentes.setDataEnvio(new Mascaras().DataSalvanosql(String.valueOf(jFDataEnvio.getText())));
        modelContaAgentes.setDataRecebimento(new Mascaras().DataSalvanosql(String.valueOf(jFDataRecebimento.getText())));
        modelContaAgentes.setDataNeg(new Mascaras().DataSalvanosql(String.valueOf(jFDataNeg.getText())));
        modelContaAgentes.setProcessos(controllerOp.getOpController(Integer.parseInt(this.tfCodigoProcesso.getText())).getCodigo());
        modelContaAgentes.setValorHonorarioAgt(Double.parseDouble(new Moeda().FommatoStringoSomarMil(jfAgenteHonorario.getText())));
        modelContaAgentes.setAgenteKmPercorrido(Integer.parseInt(jfAgenteKmPercorrido.getText().replace(".", "")));
        modelContaAgentes.setValorPGTOKMSeguradora(Double.parseDouble(new Moeda().FommatoStringoSomarMil(jfPgtoKmSegudradora.getText())));
        modelContaAgentes.setValorPGTOtotalKM(Double.parseDouble(new Moeda().FommatoStringoSomarMil(jFPagamentoTotalKM.getText())));
        modelContaAgentes.setSituacao(modelContaAgentes.isSituacao());
        modelContaAgentes.setValorAdiantamento(Double.parseDouble((new Moeda().FommatoStringoSomarMil(this.totalAdiantamentosAlterar.getText()))));
        modelContaAgentes.setTipoPagamento(controllerTipoDespesas.getTipoDespesasController(this.cbTipoDespesa.getSelectedItem().toString()).getCodigo());
        modelContaAgentes.setObservacao(this.tfObservacao.getText());
        modelContaAgentes.setValorHRMaisKM(Double.parseDouble(new Moeda().FommatoStringoSomarMil(jfAgenteTotalRepasse.getText())));
        modelContaAgentes.setValorAReceber(Double.parseDouble(new Moeda().FommatoStringoSomarMil(tfTotalAReceber.getText())));
        modelContaAgentes.setBanco(controllerBancos.getBancosControllerNome(cbBancos.getSelectedItem().toString()).getCodigo());
        modelContaAgentes.setValorNeg(Double.parseDouble(new Moeda().FommatoStringoSomarMil(jFValorNeg.getText())));

        //salvar 
        if (controllerContaAgentes.salvarContaAgentesController(modelContaAgentes) > 0) {
            //JOptionPane.showMessageDialog(this, "Registro Salvo!");
            return true;
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao salvar os dados!", "ERRO", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /**
     * Alterar dados
     *
     * @return
     */
    private boolean alterar() {

        Datas bl = new Datas();
        modelContaAgentes.setTipoConta("PAGAR");
        modelContaAgentes.setCodigoPessoa(controllerFuncionario.getFuncionarioController(this.cbFuncionario.getSelectedItem().toString()).getCodigo());
        modelContaAgentes.setDescricao(this.jLDescricaoAdiantamento.getText());
        modelContaAgentes.setDataDespesa(new Mascaras().DataSalvanosql(String.valueOf(tfDataDespesaAlterar.getText())));
        modelContaAgentes.setDataEnvio(new Mascaras().DataSalvanosql(String.valueOf(jFDataEnvio.getText())));
        modelContaAgentes.setDataRecebimento(new Mascaras().DataSalvanosql(String.valueOf(jFDataRecebimento.getText())));
        modelContaAgentes.setDataNeg(new Mascaras().DataSalvanosql(String.valueOf(jFDataNeg.getText())));
        modelContaAgentes.setProcessos(controllerOp.getOpController(Integer.parseInt(this.tfCodigoProcesso.getText())).getCodigo());
        modelContaAgentes.setValorHonorarioAgt(Double.parseDouble(new Moeda().FommatoStringoSomarMil(jfAgenteHonorario.getText())));
        modelContaAgentes.setAgenteKmPercorrido(Integer.parseInt(jfAgenteKmPercorrido.getText().replace(".", "")));
        modelContaAgentes.setValorPGTOKMSeguradora(Double.parseDouble(new Moeda().FommatoStringoSomarMil(jfPgtoKmSegudradora.getText())));
        modelContaAgentes.setValorPGTOtotalKM(Double.parseDouble(new Moeda().FommatoStringoSomarMil(jFPagamentoTotalKM.getText())));
        modelContaAgentes.setSituacao(modelContaAgentes.isSituacao());
        modelContaAgentes.setValorAdiantamento(Double.parseDouble((new Moeda().FommatoStringoSomarMil(this.totalAdiantamentosAlterar.getText()))));
        modelContaAgentes.setTipoPagamento(controllerTipoDespesas.getTipoDespesasController(this.cbTipoDespesa.getSelectedItem().toString()).getCodigo());
        modelContaAgentes.setObservacao(this.tfObservacao.getText());
        modelContaAgentes.setValorHRMaisKM(Double.parseDouble(new Moeda().FommatoStringoSomarMil(jfAgenteTotalRepasse.getText())));
        modelContaAgentes.setValorAReceber(Double.parseDouble(new Moeda().FommatoStringoSomarMil(tfTotalAReceber.getText())));
        modelContaAgentes.setBanco(controllerBancos.getBancosControllerNome(cbBancos.getSelectedItem().toString()).getCodigo());
        modelContaAgentes.setValorNeg(Double.parseDouble(new Moeda().FommatoStringoSomarMil(jFValorNeg.getText())));

        //salvar 
        if (controllerContaAgentes.atualizarContaAgentesController(modelContaAgentes)) {
            JOptionPane.showMessageDialog(this, "Registro atualizado com sucesso!");
            return true;
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao alterar os dados!", "ERRO", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private void limparDados() {
        listarFuncionarios();
        listarTipoDespesa();
        listarBancos();
        this.jtfCodigoConta.setText("Nova Despesa");
        this.tfDescricaoDespesaAlterar.setText("");
        this.tfObservacao.setText("");
        this.tfValorDespesaAlterar.setText("R$ 0,00");
        this.tfDataDespesaAlterar.setText("");
        this.jFDataEnvio.setText("");
        this.tfTotalAReceber.setText("R$ 0,00");
        this.cbCodFuncionario.setSelectedIndex(-1);
        this.jfAgenteHonorario.setText("R$ 0,00");
        this.jfAgenteKmPercorrido.setText("0");
        this.jFPagamentoTotalKM.setText("R$ 0,00");
        this.jfAgenteTotalRepasse.setText("R$ 0,00");
        this.tfSeguradoraProcesso.setText("");
        this.cbFuncionario.setSelectedIndex(-1);
        this.tfSinistroProcesso.setText("");
        this.tfDataProcesso.setText("");
        this.tfCodigoProcesso.setText("");
        this.jLNomeAgente.setText("Nome do Agente");
        this.jFValorNeg.setText("R$ 0,00");
        this.jfPgtoKmSegudradora.setText("R$ 0,00");
        this.totalAdiantamentosAlterar.setText("R$ 0,00");
        tfValidarDataAlterar.setVisible(false);
        tfValidarValorAlterar.setVisible(false);
        tfValidarDescricaoAlterar.setVisible(false);
        DefaultTableModel modelo = (DefaultTableModel) tbAdiantamentoAlterar.getModel();
        modelo.setNumRows(0);
    }

    private void listarTipoDespesa() {
        listaModelTipoDespesas = controllerTipoDespesas.getListaTipoDespesasController();
        cbTipoDespesa.removeAllItems();
        for (int i = 0; i < listaModelTipoDespesas.size(); i++) {
            cbTipoDespesa.addItem(listaModelTipoDespesas.get(i).getNome());
        }
    }

    //CALCULO DO REPASSE AO AGENTE PROCESSO ----------------------------------------------------------------------------------------------
    public String somaValorAgente() {
        if ("".equals(jfAgenteHonorario.getText()) || (Float.parseFloat(new Moeda().FommatoStringoSomarMil(jfAgenteHonorario.getText()))) <= 0) {
            this.jfAgenteHonorario.setText("R$ 0,00");
        }
        if ("".equals(jfAgenteKmPercorrido.getText()) || (Float.parseFloat(new Moeda().FommatoStringoSomarMil(jfAgenteKmPercorrido.getText()))) <= 0) {
            this.jfAgenteKmPercorrido.setText("0");
        }
        if ("".equals(jfPgtoKmSegudradora.getText()) || (Float.parseFloat(new Moeda().FommatoStringoSomarMil(jfPgtoKmSegudradora.getText()))) <= 0) {
            this.jfPgtoKmSegudradora.setText("R$ 0,00");
        }
        Float total;
        Float kmtotal;
        Float Negativa = Float.parseFloat(new Moeda().FommatoStringoSomarMil(this.jFValorNeg.getText()));
        Float honorarios = Float.parseFloat(new Moeda().FommatoStringoSomarMil(this.jfAgenteHonorario.getText()));
        Float km = Float.parseFloat(new Moeda().FommatoStringoSomarMil(jfPgtoKmSegudradora.getText()));
        Float percorrido = Float.parseFloat(new Moeda().FommatoStringoSomarMil(String.valueOf(jfAgenteKmPercorrido.getText())));
        //CALCULO
        kmtotal = percorrido * km;
        if (kmtotal > 0) {
            this.jFPagamentoTotalKM.setText(new Moeda().valorStringTODoubleAtt(Double.toString(kmtotal)));
        }
        if (kmtotal == 0) {
            this.jFPagamentoTotalKM.setText("R$ 0,00");
        }
        total = honorarios + kmtotal + Negativa;
        if (total > 0) {
        }
        if (total == 0) {
            this.jfAgenteTotalRepasse.setText("R$ 0,00");
        }
        return String.format(new Moeda().valorStringTODoubleAtt(Double.toString(total)));
    }

    private void somaTotal() {
        somaAdiantamentosEfetuados();
        Double adiantamento = (Double.parseDouble(new Moeda().FommatoStringoSomarMil(totalAdiantamentosAlterar.getText())));
        Double totalRepasse = (Double.parseDouble(new Moeda().FommatoStringoSomarMil(jfAgenteTotalRepasse.getText())));
        Double Negativa = (Double.parseDouble(new Moeda().FommatoStringoSomarMil(jFValorNeg.getText())));
        Double total = totalRepasse + Negativa;
        total = total - adiantamento;
        this.tfTotalAReceber.setText(new Moeda().valorStringTODoubleAtt(String.valueOf(total)));
        if (Double.parseDouble(String.valueOf(tfTotalAReceber.getText().replace("R$", "").replace(" ", "").replace(".", "").replace(",", "."))) < 0) {
            jLCampoNegativo.setVisible(true);
        } else {
            jLCampoNegativo.setVisible(false);
        }
    }

    private void somaAdiantamentos() {
        float soma = 0;
        float valor;
        int cont;
        cont = tbAdiantamentoAlterar.getRowCount();
        for (int i = 0; i < cont; i++) {
            valor = Float.parseFloat(new Moeda().FommatoStringoSomarMil(String.valueOf(tbAdiantamentoAlterar.getValueAt(i, 2))));
            soma = valor + soma;
        }
        totalAdiantamentosAlterar.setText(new Moeda().valorStringTODoubleAtt(String.valueOf(soma)));
    }

    private void listarBancos() {
        listaModelBancos = controllerBancos.getListaBancosController();
        cbBancos.removeAllItems();
        for (int i = 0; i < listaModelBancos.size(); i++) {
            cbBancos.addItem(listaModelBancos.get(i).getBanco_nome());
        }
    }

    private void recuperarDadosPorProcesso() {
        if (Integer.parseInt(this.tfCodigoProcesso.getText()) > 0) {
            modelOp = controllerOp.getOpController(Integer.parseInt(tfCodigoProcesso.getText()));
            //recupera o código
            this.tfSeguradoraProcesso.setText(controllerSeguradora.getSeguradoraController(modelOp.getSeguradoras()).getNome());
            this.tfSinistroProcesso.setText(String.valueOf(modelOp.getNumeroSinistro()));
            this.tfDataProcesso.setText(new Mascaras().DataRecuperasql(modelOp.getDataEntrada()));
        }
    }

    void carregarDosAgentesNaTabelaEmContas() {

        int cod = (Integer.parseInt(cbCodFuncionario.getSelectedItem().toString()));
        listaModelOp = controllerOp.getPorAgenteApenasEMAndamentoController(cod);
        DefaultTableModel modelo = (DefaultTableModel) tbProcessosDoAgenteRegistrarConta.getModel();
        modelo.setNumRows(0);
        //CARREGA OS DADOS DA LISTA NA TABELA
        int cont = listaModelOp.size();
        for (int i = 0; i < cont; i++) {
            modelFormaPagamento = controllerTipoPagamento.getFormaPagamentoController(listaModelOp.get(i).getTipo());
            modelSeguradora = controllerSeguradora.getSeguradoraController(listaModelOp.get(i).getSeguradoras());
            modelo.addRow(new Object[]{
                listaModelOp.get(i).getCodigo(),
                modelSeguradora.getNome(),
                (new Mascaras().DataRecuperasql(String.valueOf(listaModelOp.get(i).getDataEntrada()))),
                listaModelOp.get(i).getNumeroSinistro(),
                (new Moeda().valorStringTODoubleAtt(listaModelOp.get(i).getValorTotalHonorariosSemRetencao().toString())),
                modelFormaPagamento.getDescricao()
            });
        }

        if (tbProcessosDoAgenteRegistrarConta.getRowCount() < 1) {
            DefaultTableModel modelo1 = (DefaultTableModel) tbProcessosDoAgenteRegistrarConta.getModel();
            modelo1.setNumRows(0);
            //CARREGA OS DADOS DA LISTA NA TABELA
            int cont1 = 1;
            for (int i = 0; i < cont1; i++) {
                modelo.addRow(new Object[]{
                    "",
                    "NENHUM PROCESSO DESIGNADO AO AGENTE"
                });
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel DespesasEfetuados;
    private javax.swing.JPanel DespesasPendentes;
    private javax.swing.JButton JbSAlvar;
    private javax.swing.JPanel RegistrarContas;
    private javax.swing.JButton btAdiantamentoAlterar;
    private javax.swing.JButton btAlterar;
    private javax.swing.JButton btCancelarVoltar;
    private javax.swing.JButton btClearEfetuado;
    private javax.swing.JButton btClearPendentes;
    private javax.swing.JButton btDefinirDataEfetuado;
    private javax.swing.JButton btDefinirDataPendentes;
    private javax.swing.JButton btExcluirAdiantamento;
    private javax.swing.JButton btExportarDados;
    private componentes.UJComboBox cbBancos;
    private javax.swing.JComboBox cbCodAgenteDesignar;
    private javax.swing.JComboBox cbCodFuncionario;
    private componentes.UJComboBox cbFuncionario;
    private javax.swing.JComboBox<String> cbFuncionariosEfetuado;
    private javax.swing.JComboBox<String> cbFuncionariosPendentes;
    private componentes.UJComboBox cbTipoDespesa;
    private javax.swing.JFormattedTextField jFDataEnvio;
    private javax.swing.JFormattedTextField jFDataNeg;
    private javax.swing.JFormattedTextField jFDataRecebimento;
    private javax.swing.JLabel jFPagamentoTotalKM;
    private javax.swing.JLabel jFTotalHrEfetuado;
    private javax.swing.JLabel jFTotalHrPendentes;
    private javax.swing.JLabel jFTotalKmPercoEfetuado;
    private javax.swing.JLabel jFTotalKmPercoPendentes;
    private javax.swing.JLabel jFTotalPgtoKmEfetuado;
    private javax.swing.JLabel jFTotalPgtoKmPendentes;
    private javax.swing.JLabel jFTotalnegEfetuado;
    private javax.swing.JLabel jFTotalnegPendentes;
    private javax.swing.JFormattedTextField jFValorNeg;
    private javax.swing.JLabel jLAgenteHonorarios;
    private javax.swing.JLabel jLAgenteKMpercorrido;
    private javax.swing.JLabel jLAgenteRepasseTotal;
    private javax.swing.JLabel jLAgenteRepasseTotal1;
    private javax.swing.JLabel jLAgenteSeguradoraKm;
    private javax.swing.JLabel jLAgenteTotalKm;
    private javax.swing.JLabel jLAgenteTotalKm1;
    private javax.swing.JLabel jLCampoNegativo;
    private javax.swing.JTextArea jLDescricaoAdiantamento;
    private javax.swing.JTextArea jLNomeAgente;
    private javax.swing.JLabel jLTotalGeral;
    private javax.swing.JLabel jLTotalGeral1;
    private javax.swing.JLabel jLTotalGeral2;
    private javax.swing.JLabel jLTotalNeg1;
    private javax.swing.JLabel jLTotalNegEfetuado;
    private javax.swing.JLabel jLTotalPgtoKm1;
    private javax.swing.JLabel jLTotalPgtoKmEfetuado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLtotalHr1;
    private javax.swing.JLabel jLtotalHrEfetuado;
    private javax.swing.JLabel jLtotalKmPerco1;
    private javax.swing.JLabel jLtotalKmPercoEfetuado;
    private javax.swing.JPanel jPanelPrincipal;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPaneAnalista;
    private javax.swing.JScrollPane jScrollPaneAnalista1;
    private javax.swing.JScrollPane jScrollPaneAnalista2;
    private javax.swing.JScrollPane jScrollPaneAnalista3;
    private javax.swing.JTabbedPane jTabbedPanelDespesas;
    private javax.swing.JButton jbExcluirPagar;
    private javax.swing.JButton jbPagarConta;
    private com.toedter.calendar.JDateChooser jdcDataFinalEfetuado;
    private com.toedter.calendar.JDateChooser jdcDataFinalPendentes;
    private com.toedter.calendar.JDateChooser jdcDataInicialEfetuado;
    private com.toedter.calendar.JDateChooser jdcDataInicialPendentes;
    private javax.swing.JFormattedTextField jfAgenteHonorario;
    private javax.swing.JFormattedTextField jfAgenteKmPercorrido;
    private javax.swing.JLabel jfAgenteTotalRepasse;
    private javax.swing.JFormattedTextField jfPgtoKmSegudradora;
    private javax.swing.JButton jpRevorgarConta;
    private javax.swing.JLabel jtfCodigoConta;
    private keeptoo.KButton kButtonPagas;
    private keeptoo.KButton kButtonPendentes;
    private keeptoo.KButton kButtonProcessos2;
    private keeptoo.KButton kButtonProcessos4;
    private keeptoo.KButton kButtonRegistrar;
    private keeptoo.KGradientPanel kGFuncionarioAgente;
    private keeptoo.KGradientPanel kGPAlterarAdiantamentos;
    private keeptoo.KGradientPanel kGRepasseAoAgente;
    private keeptoo.KGradientPanel kGTotalGeral;
    private keeptoo.KGradientPanel kGTotalGeral1;
    private keeptoo.KGradientPanel kGradientPanel1;
    private keeptoo.KGradientPanel kGradientPanel10;
    private keeptoo.KGradientPanel kGradientPanel11;
    private keeptoo.KGradientPanel kGradientPanel14;
    private keeptoo.KGradientPanel kGradientPanel15;
    private keeptoo.KGradientPanel kGradientPanel16;
    private keeptoo.KGradientPanel kGradientPanel18;
    private keeptoo.KGradientPanel kGradientPanel2;
    private keeptoo.KGradientPanel kGradientPanel3;
    private keeptoo.KGradientPanel kGradientPanel4;
    private keeptoo.KGradientPanel kGradientPanel5;
    private keeptoo.KGradientPanel kGradientPanel6;
    private keeptoo.KGradientPanel kGradientPanel7;
    private keeptoo.KGradientPanel kGradientPanel8;
    private keeptoo.KGradientPanel kGradientPanel9;
    private javax.swing.JLabel labelPagamento;
    private javax.swing.JTable tbAdiantamentoAlterar;
    private javax.swing.JTable tbDespesasEfetuadas;
    private javax.swing.JTable tbDespesasPendentes;
    private javax.swing.JTable tbProcessosDoAgenteRegistrarConta;
    private javax.swing.JTextField tfAdiantamentosEfetuado;
    private javax.swing.JTextField tfAdiantamentosPendentes;
    private javax.swing.JTextField tfAreceberEfetuado;
    private javax.swing.JTextField tfAreceberPendentes;
    private javax.swing.JLabel tfCodigoProcesso;
    private javax.swing.JFormattedTextField tfDataDespesaAlterar;
    private javax.swing.JLabel tfDataProcesso;
    private javax.swing.JTextField tfDescricaoDespesaAlterar;
    private javax.swing.JTextField tfNegHrKmPendentes;
    private javax.swing.JTextField tfNegHrkmEfetuado;
    private javax.swing.JTextArea tfObservacao;
    private javax.swing.JLabel tfSeguradora1;
    private javax.swing.JTextArea tfSeguradoraProcesso;
    private javax.swing.JTextArea tfSinistroProcesso;
    private javax.swing.JLabel tfTotalAReceber;
    private javax.swing.JLabel tfValidarDataAlterar;
    private javax.swing.JLabel tfValidarDescricaoAlterar;
    private javax.swing.JLabel tfValidarValorAlterar;
    private javax.swing.JFormattedTextField tfValorDespesaAlterar;
    private javax.swing.JLabel totalAdiantamentosAlterar;
    // End of variables declaration//GEN-END:variables
      /**
     *
     */

    void limparPendentes() {
        tfAdiantamentosPendentes.setText("R$ 0,00");
        tfNegHrKmPendentes.setText("R$ 0,00");
        tfAreceberPendentes.setText("R$ 0,00");
        jdcDataInicialPendentes.setDate(null);
        jdcDataFinalPendentes.setDate(null);
        DefaultTableModel modelo = (DefaultTableModel) tbDespesasPendentes.getModel();
        modelo.setNumRows(0);
    }

    void limparPagas() {
        cbFuncionariosEfetuado.setSelectedIndex(0);
        tfAdiantamentosEfetuado.setText("R$ 0,00");
        tfNegHrkmEfetuado.setText("R$ 0,00");
        tfAreceberEfetuado.setText("R$ 0,00");
        jdcDataInicialEfetuado.setDate(null);
        jdcDataFinalEfetuado.setDate(null);
        DefaultTableModel modelo = (DefaultTableModel) tbDespesasEfetuadas.getModel();
        modelo.setNumRows(0);
    }

    public static ViewDespesasAgentes viewdespesasagentes;

    ConexaoBanco cc = new ConexaoBanco();
    Connection cn = cc.conectar();

    private void SetIcone() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("ictrol.png")));
    }

}
