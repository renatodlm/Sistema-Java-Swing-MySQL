/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import Controller.ControllerContaAgentes;
import Model.ModelContaAgentes;
import javax.swing.JFileChooser;
import Controller.ControllerBancos;
import Controller.ControllerTipoDespesas;
import Model.ModelBancos;
import Model.ModelTipoDespesas;
import java.io.File;
import java.util.Date;
import java.util.List;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import util.Exporter;
import java.awt.event.KeyEvent;
import Controller.ControllerCobertura;
import Controller.ControllerSeguradora;
import Controller.ControllerFormaPagamento;
import Controller.ControllerFuncionario;
import Controller.ControllerOp;
import Model.ModelCobertura;
import Model.ModelSeguradora;
import Model.ModelFormaPagamento;
import Model.ModelOp;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Model.ModelFuncionario;

import util.Datas;
import util.Mascaras;
import conexao.ConexaoBanco;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import util.Moeda;

/**
 *
 * @author Administrador
 */
public final class ViewCadDespesasAgentes extends javax.swing.JFrame {

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

    /**
     * /**
     * Creates new form ViewContasRecebe
     */
    public ViewCadDespesasAgentes() {
        initComponents();
        SetIcone();
        this.carregarProcessosReceber();
        this.carregarProcessosRecebidos();
        listarFuncionarios();
        jTabbedPanelDespesas.setSelectedIndex(1);
        panelTabbedKGVerficiar();
        somaTotal();
        listarBancos();
        listarTipoDespesa();
        jLCampoNegativo.setVisible(false);
        this.limparDados();
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
                new ViewCadDespesasAgentes().setVisible(true);
            }
        });
    }

    private String dataAtual() {
        java.util.Date d = new java.util.Date();
        Locale local = new Locale("pt", "BR");
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", local);
        return String.format((format.format(d)));
    }

    public void carregarProcessosReceber() {
        listaModelOp = controllerOp.getProcessosRecebidosController(1);
        DefaultTableModel modelo = (DefaultTableModel) tbProcessosReceber.getModel();
        modelo.setNumRows(0);
        //CARREGA OS DADOS DA LISTA NA TABELA
        int cont = listaModelOp.size();
        for (int i = 0; i < cont; i++) {
            modelSeguradora = controllerSeguradora.getSeguradoraController(listaModelOp.get(i).getSeguradoras());
            modelFormaPagamento = controllerTipoPagamento.getFormaPagamentoController(listaModelOp.get(i).getTipo());
            modelo.addRow(new Object[]{
                listaModelOp.get(i).getCodigo(),
                modelSeguradora.getNome(),
                modelSeguradora.getCnpj(),
                (new Mascaras().DataRecuperasql(String.valueOf(listaModelOp.get(i).getDataEntrada()))),
                listaModelOp.get(i).getNumeroSinistro(),
                (new Moeda().valorStringTODoubleAtt(listaModelOp.get(i).getValorTotalHonorariosSemRetencao().toString())),
                modelFormaPagamento.getDescricao()
            });
        }
    }

    private void carregarProcessosRecebidos() {
        listaModelOp = controllerOp.getProcessosRecebidosAguardandoPgtoController(4, 5);
        DefaultTableModel modelo = (DefaultTableModel) tbProcessosRecebidos.getModel();
        modelo.setNumRows(0);
        String nomeFuncionario;
        //CARREGA OS DADOS DA LISTA NA TABELA
        int cont = listaModelOp.size();
        for (int i = 0; i < cont; i++) {
            modelSeguradora = controllerSeguradora.getSeguradoraController(listaModelOp.get(i).getSeguradoras());
            nomeFuncionario = controllerFuncionario.getFuncionarioController(listaModelOp.get(i).getCod_agente()).getNome();
            modelFormaPagamento = controllerTipoPagamento.getFormaPagamentoController(listaModelOp.get(i).getTipo());
            modelContaAgentes = controllerContaAgentes.getContaAgentesControllerPorProcesso(listaModelOp.get(i).getCodigo());
            //modelContaAgentes.getTipoPagamento()
            modelo.addRow(new Object[]{
                listaModelOp.get(i).getCodigo(),
                modelContaAgentes.getCodigo(),
                (new Mascaras().DataRecuperasql(String.valueOf(listaModelOp.get(i).getDataEntrada()))),
                modelFormaPagamento.getDescricao(),
                modelSeguradora.getNome(),
                nomeFuncionario,
                (new Moeda().valorStringTODoubleAtt(String.valueOf(modelContaAgentes.getValorHonorarioAgt()))),
                (new Moeda().valorStringTODoubleAtt(String.valueOf(modelContaAgentes.getValorPGTOtotalKM()))),
                (new Moeda().valorStringTODoubleAtt(String.valueOf(modelContaAgentes.getValorAdiantamento())))

            });
            CorNaLinha();
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

        jRadioButton1 = new javax.swing.JRadioButton();
        jToggleButton1 = new javax.swing.JToggleButton();
        kGDadosDoProcesso = new keeptoo.KGradientPanel();
        tfSeguradora2 = new javax.swing.JLabel();
        cbCodAgenteDesignar = new javax.swing.JComboBox();
        btAplicarProcesso = new javax.swing.JButton();
        jScrollPane13 = new javax.swing.JScrollPane();
        tbProcessosDoAgenteRegistrarConta = new javax.swing.JTable();
        cbFuncionario = new componentes.UJComboBox();
        cbCodFuncionario = new javax.swing.JComboBox();
        tfSeguradora = new javax.swing.JLabel();
        jScrollPaneAnalista3 = new javax.swing.JScrollPane();
        jLDescricaoAdiantamento = new javax.swing.JTextArea();
        jPanelPrincipal = new javax.swing.JPanel();
        kGradientPanel14 = new keeptoo.KGradientPanel();
        kGradientPanel22 = new keeptoo.KGradientPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        kButtonProcessos = new keeptoo.KButton();
        kButtonAndamento = new keeptoo.KButton();
        kButtonRegistrar = new keeptoo.KButton();
        jTabbedPanelDespesas = new javax.swing.JTabbedPane();
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
        kGradientPanel6 = new keeptoo.KGradientPanel();
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
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        tfValidarDescricaoAlterar = new javax.swing.JLabel();
        tfValidarValorAlterar = new javax.swing.JLabel();
        tfValidarDataAlterar = new javax.swing.JLabel();
        kGradientPanel7 = new keeptoo.KGradientPanel();
        totalAdiantamentosAlterar = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        kButtonProcessos2 = new keeptoo.KButton();
        kGradientPanel5 = new keeptoo.KGradientPanel();
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
        DesignarProcesso = new javax.swing.JPanel();
        kGradientPanel13 = new keeptoo.KGradientPanel();
        jbAtribuirSemDespesa = new javax.swing.JButton();
        jbAtribuirMaisDespesa = new javax.swing.JButton();
        kGradientPanel12 = new keeptoo.KGradientPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tbProcessosReceber = new javax.swing.JTable();
        kGradientPanel17 = new keeptoo.KGradientPanel();
        tfPesquisarProcessos = new javax.swing.JTextField();
        kGradientPanel11 = new keeptoo.KGradientPanel();
        cbAgenteDesignar = new componentes.UJComboBox();
        tfSeguradora3 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tbProcessosDoAgente = new javax.swing.JTable();
        tfSeguradora4 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        ProcessosAndamentos = new javax.swing.JPanel();
        kGradientPanel16 = new keeptoo.KGradientPanel();
        jpFinalizarProcesso = new javax.swing.JButton();
        jpRevorgarConta2 = new javax.swing.JButton();
        kGradientPanel15 = new keeptoo.KGradientPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tbProcessosRecebidos = new javax.swing.JTable();
        kGradientPanel20 = new keeptoo.KGradientPanel();
        tfPesquisarProcessos1 = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jbReceberConta2 = new javax.swing.JButton();
        jbVisualizarConta1 = new javax.swing.JButton();

        jRadioButton1.setText("jRadioButton1");

        jToggleButton1.setText("jToggleButton1");

        kGDadosDoProcesso.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Dados do Processo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(51, 51, 51)), "SELECIONAR PROCESSO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(51, 51, 51))); // NOI18N
        kGDadosDoProcesso.setkBorderRadius(0);
        kGDadosDoProcesso.setkEndColor(new java.awt.Color(234, 239, 243));
        kGDadosDoProcesso.setkGradientFocus(0);
        kGDadosDoProcesso.setkStartColor(new java.awt.Color(255, 255, 255));
        kGDadosDoProcesso.setMinimumSize(new java.awt.Dimension(1290, 125));
        kGDadosDoProcesso.setPreferredSize(new java.awt.Dimension(1290, 125));
        kGDadosDoProcesso.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tfSeguradora2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tfSeguradora2.setText("Cod.");

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

        btAplicarProcesso.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btAplicarProcesso.setForeground(new java.awt.Color(51, 51, 51));
        btAplicarProcesso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/24x24/Next.png"))); // NOI18N
        btAplicarProcesso.setText("APLICAR");
        btAplicarProcesso.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        btAplicarProcesso.setPreferredSize(new java.awt.Dimension(150, 40));
        btAplicarProcesso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAplicarProcessoActionPerformed(evt);
            }
        });

        jScrollPane13.setBackground(new java.awt.Color(234, 239, 243));
        jScrollPane13.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane13.setPreferredSize(new java.awt.Dimension(1220, 180));

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
        jScrollPane13.setViewportView(tbProcessosDoAgenteRegistrarConta);
        if (tbProcessosDoAgenteRegistrarConta.getColumnModel().getColumnCount() > 0) {
            tbProcessosDoAgenteRegistrarConta.getColumnModel().getColumn(0).setMinWidth(80);
            tbProcessosDoAgenteRegistrarConta.getColumnModel().getColumn(0).setPreferredWidth(80);
            tbProcessosDoAgenteRegistrarConta.getColumnModel().getColumn(0).setMaxWidth(80);
            tbProcessosDoAgenteRegistrarConta.getColumnModel().getColumn(2).setMinWidth(120);
            tbProcessosDoAgenteRegistrarConta.getColumnModel().getColumn(2).setPreferredWidth(120);
            tbProcessosDoAgenteRegistrarConta.getColumnModel().getColumn(2).setMaxWidth(120);
            tbProcessosDoAgenteRegistrarConta.getColumnModel().getColumn(3).setMinWidth(150);
            tbProcessosDoAgenteRegistrarConta.getColumnModel().getColumn(3).setPreferredWidth(150);
            tbProcessosDoAgenteRegistrarConta.getColumnModel().getColumn(3).setMaxWidth(150);
            tbProcessosDoAgenteRegistrarConta.getColumnModel().getColumn(4).setMinWidth(150);
            tbProcessosDoAgenteRegistrarConta.getColumnModel().getColumn(4).setPreferredWidth(150);
            tbProcessosDoAgenteRegistrarConta.getColumnModel().getColumn(4).setMaxWidth(150);
            tbProcessosDoAgenteRegistrarConta.getColumnModel().getColumn(5).setMinWidth(150);
            tbProcessosDoAgenteRegistrarConta.getColumnModel().getColumn(5).setPreferredWidth(150);
            tbProcessosDoAgenteRegistrarConta.getColumnModel().getColumn(5).setMaxWidth(150);
        }

        cbFuncionario.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbFuncionarioPopupMenuWillBecomeInvisible(evt);
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

        tfSeguradora.setText("Cod.");

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
        setMinimumSize(new java.awt.Dimension(1360, 680));
        setPreferredSize(new java.awt.Dimension(1360, 680));
        setResizable(false);

        jPanelPrincipal.setBackground(new java.awt.Color(146, 171, 197));
        jPanelPrincipal.setMinimumSize(new java.awt.Dimension(1360, 650));
        jPanelPrincipal.setPreferredSize(new java.awt.Dimension(1360, 650));
        jPanelPrincipal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        kGradientPanel14.setBackground(new java.awt.Color(146, 171, 197));
        kGradientPanel14.setkBorderRadius(0);
        kGradientPanel14.setkEndColor(new java.awt.Color(146, 171, 197));
        kGradientPanel14.setkGradientFocus(0);
        kGradientPanel14.setkStartColor(new java.awt.Color(146, 171, 197));
        kGradientPanel14.setName(""); // NOI18N
        kGradientPanel14.setPreferredSize(new java.awt.Dimension(1360, 40));
        kGradientPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        kGradientPanel22.setkBorderRadius(0);
        kGradientPanel22.setkEndColor(new java.awt.Color(255, 255, 255));
        kGradientPanel22.setkStartColor(new java.awt.Color(146, 171, 197));
        kGradientPanel22.setPreferredSize(new java.awt.Dimension(550, 50));
        kGradientPanel22.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 51, 102));
        jLabel27.setText("CONTROLE DE AGENTES - REGISTRO DE DESPESAS DE PROCESSOS");
        kGradientPanel22.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 460, 24));

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(0, 51, 102));
        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/24x24/Person.png"))); // NOI18N
        kGradientPanel22.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, -1, -1));

        kGradientPanel14.add(kGradientPanel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 0, -1, -1));

        kButtonProcessos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Pinion.png"))); // NOI18N
        kButtonProcessos.setText("EM ABERTO");
        kButtonProcessos.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        kButtonProcessos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        kButtonProcessos.setIconTextGap(32);
        kButtonProcessos.setkBackGroundColor(new java.awt.Color(255, 255, 255));
        kButtonProcessos.setkBorderRadius(0);
        kButtonProcessos.setkEndColor(new java.awt.Color(197, 201, 206));
        kButtonProcessos.setkForeGround(new java.awt.Color(102, 102, 102));
        kButtonProcessos.setkHoverEndColor(new java.awt.Color(234, 239, 243));
        kButtonProcessos.setkHoverForeGround(new java.awt.Color(102, 102, 102));
        kButtonProcessos.setkHoverStartColor(new java.awt.Color(234, 239, 243));
        kButtonProcessos.setkPressedColor(new java.awt.Color(153, 153, 153));
        kButtonProcessos.setkSelectedColor(new java.awt.Color(255, 255, 255));
        kButtonProcessos.setkStartColor(new java.awt.Color(197, 201, 206));
        kButtonProcessos.setPreferredSize(new java.awt.Dimension(170, 50));
        kButtonProcessos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButtonProcessosActionPerformed(evt);
            }
        });
        kGradientPanel14.add(kButtonProcessos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        kButtonAndamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/24x24/3d bar chart.png"))); // NOI18N
        kButtonAndamento.setText("EM ANDAMENTO");
        kButtonAndamento.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        kButtonAndamento.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        kButtonAndamento.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        kButtonAndamento.setIconTextGap(10);
        kButtonAndamento.setkBackGroundColor(new java.awt.Color(255, 255, 255));
        kButtonAndamento.setkBorderRadius(0);
        kButtonAndamento.setkEndColor(new java.awt.Color(197, 201, 206));
        kButtonAndamento.setkForeGround(new java.awt.Color(51, 51, 51));
        kButtonAndamento.setkHoverEndColor(new java.awt.Color(234, 239, 243));
        kButtonAndamento.setkHoverForeGround(new java.awt.Color(51, 51, 51));
        kButtonAndamento.setkHoverStartColor(new java.awt.Color(234, 239, 243));
        kButtonAndamento.setkPressedColor(new java.awt.Color(153, 153, 153));
        kButtonAndamento.setkSelectedColor(new java.awt.Color(255, 255, 255));
        kButtonAndamento.setkStartColor(new java.awt.Color(197, 201, 206));
        kButtonAndamento.setPreferredSize(new java.awt.Dimension(170, 50));
        kButtonAndamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButtonAndamentoActionPerformed(evt);
            }
        });
        kGradientPanel14.add(kButtonAndamento, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 0, -1, -1));

        kButtonRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Text.png"))); // NOI18N
        kButtonRegistrar.setText("REGISTRANDO");
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
        kGradientPanel14.add(kButtonRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 0, -1, -1));

        jPanelPrincipal.add(kGradientPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1360, 50));

        jTabbedPanelDespesas.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPanelDespesas.setMinimumSize(new java.awt.Dimension(1180, 650));
        jTabbedPanelDespesas.setPreferredSize(new java.awt.Dimension(1180, 650));

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

        kGradientPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "         DESPESA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(0, 0, 51))); // NOI18N
        kGradientPanel6.setForeground(new java.awt.Color(0, 0, 51));
        kGradientPanel6.setkBorderRadius(0);
        kGradientPanel6.setkEndColor(new java.awt.Color(234, 239, 243));
        kGradientPanel6.setkGradientFocus(0);
        kGradientPanel6.setkStartColor(new java.awt.Color(255, 255, 255));
        kGradientPanel6.setPreferredSize(new java.awt.Dimension(1360, 120));
        kGradientPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtfCodigoConta.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jtfCodigoConta.setForeground(new java.awt.Color(255, 0, 51));
        jtfCodigoConta.setText("NOVO+");
        kGradientPanel6.add(jtfCodigoConta, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 150, 50));

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

        kGradientPanel6.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, 1110, 60));

        jLabel10.setText("OBSERVAÇÕES:");
        kGradientPanel6.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, -1, -1));

        RegistrarContas.add(kGradientPanel6);

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
        if (tbAdiantamentoAlterar.getColumnModel().getColumnCount() > 0) {
            tbAdiantamentoAlterar.getColumnModel().getColumn(0).setMinWidth(0);
            tbAdiantamentoAlterar.getColumnModel().getColumn(0).setPreferredWidth(0);
            tbAdiantamentoAlterar.getColumnModel().getColumn(0).setMaxWidth(0);
            tbAdiantamentoAlterar.getColumnModel().getColumn(1).setMinWidth(150);
            tbAdiantamentoAlterar.getColumnModel().getColumn(2).setMinWidth(80);
            tbAdiantamentoAlterar.getColumnModel().getColumn(3).setMinWidth(80);
        }

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

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setText("DESCRIÇÃO");
        kGPAlterarAdiantamentos.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 210, 20));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setText("DATA:");
        kGPAlterarAdiantamentos.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 30, -1, -1));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel19.setText("VALOR:");
        kGPAlterarAdiantamentos.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 30, 90, -1));

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

        kGradientPanel7.setkBorderRadius(0);
        kGradientPanel7.setkEndColor(new java.awt.Color(255, 255, 255));
        kGradientPanel7.setkStartColor(new java.awt.Color(255, 255, 255));

        totalAdiantamentosAlterar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        totalAdiantamentosAlterar.setForeground(new java.awt.Color(255, 51, 51));
        totalAdiantamentosAlterar.setText("R$ 0,00");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Total Adiantamentos:");

        org.jdesktop.layout.GroupLayout kGradientPanel7Layout = new org.jdesktop.layout.GroupLayout(kGradientPanel7);
        kGradientPanel7.setLayout(kGradientPanel7Layout);
        kGradientPanel7Layout.setHorizontalGroup(
            kGradientPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(kGradientPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel3)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .add(org.jdesktop.layout.GroupLayout.TRAILING, kGradientPanel7Layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(totalAdiantamentosAlterar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 170, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        kGradientPanel7Layout.setVerticalGroup(
            kGradientPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, kGradientPanel7Layout.createSequentialGroup()
                .add(jLabel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(totalAdiantamentosAlterar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        kGPAlterarAdiantamentos.add(kGradientPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 40, 150, 90));

        kButtonProcessos2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/refresh.png"))); // NOI18N
        kButtonProcessos2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        kButtonProcessos2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        kButtonProcessos2.setIconTextGap(15);
        kButtonProcessos2.setkBackGroundColor(new java.awt.Color(234, 239, 243));
        kButtonProcessos2.setkBorderRadius(0);
        kButtonProcessos2.setkEndColor(new java.awt.Color(255, 255, 255));
        kButtonProcessos2.setkForeGround(new java.awt.Color(51, 51, 51));
        kButtonProcessos2.setkHoverEndColor(new java.awt.Color(234, 239, 243));
        kButtonProcessos2.setkHoverForeGround(new java.awt.Color(51, 51, 51));
        kButtonProcessos2.setkHoverStartColor(new java.awt.Color(234, 239, 243));
        kButtonProcessos2.setkPressedColor(new java.awt.Color(255, 255, 255));
        kButtonProcessos2.setkSelectedColor(new java.awt.Color(255, 255, 255));
        kButtonProcessos2.setkStartColor(new java.awt.Color(255, 255, 255));
        kButtonProcessos2.setPreferredSize(new java.awt.Dimension(210, 45));
        kButtonProcessos2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButtonProcessos2ActionPerformed(evt);
            }
        });
        kGPAlterarAdiantamentos.add(kButtonProcessos2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1260, 0, 50, 40));

        RegistrarContas.add(kGPAlterarAdiantamentos);

        kGradientPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "         NEGATIVA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(51, 51, 51))); // NOI18N
        kGradientPanel5.setkBorderRadius(0);
        kGradientPanel5.setkEndColor(new java.awt.Color(234, 239, 243));
        kGradientPanel5.setkGradientFocus(0);
        kGradientPanel5.setkStartColor(new java.awt.Color(255, 255, 255));
        kGradientPanel5.setPreferredSize(new java.awt.Dimension(340, 120));
        kGradientPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        kGradientPanel5.add(jFValorNeg, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 110, 35));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("VALOR:");
        kGradientPanel5.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 90, -1));

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
        kGradientPanel5.add(jFDataNeg, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 70, -1, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("DATA:");
        kGradientPanel5.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, -1, -1));

        RegistrarContas.add(kGradientPanel5);

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

        DesignarProcesso.setBackground(new java.awt.Color(255, 255, 255));
        DesignarProcesso.setMinimumSize(new java.awt.Dimension(1170, 400));
        DesignarProcesso.setPreferredSize(new java.awt.Dimension(1170, 400));

        kGradientPanel13.setkBorderRadius(0);
        kGradientPanel13.setkEndColor(new java.awt.Color(234, 239, 243));
        kGradientPanel13.setkGradientFocus(0);
        kGradientPanel13.setkStartColor(new java.awt.Color(255, 255, 255));
        kGradientPanel13.setMinimumSize(new java.awt.Dimension(1170, 100));
        kGradientPanel13.setPreferredSize(new java.awt.Dimension(1360, 60));
        kGradientPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jbAtribuirSemDespesa.setBackground(new java.awt.Color(0, 153, 0));
        jbAtribuirSemDespesa.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jbAtribuirSemDespesa.setForeground(new java.awt.Color(255, 255, 255));
        jbAtribuirSemDespesa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Forward.png"))); // NOI18N
        jbAtribuirSemDespesa.setText("APENAS ATRIBUIR AGENTE");
        jbAtribuirSemDespesa.setPreferredSize(new java.awt.Dimension(230, 40));
        jbAtribuirSemDespesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAtribuirSemDespesaActionPerformed(evt);
            }
        });
        kGradientPanel13.add(jbAtribuirSemDespesa, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 10, 230, -1));

        jbAtribuirMaisDespesa.setBackground(new java.awt.Color(0, 128, 213));
        jbAtribuirMaisDespesa.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jbAtribuirMaisDespesa.setForeground(new java.awt.Color(255, 255, 255));
        jbAtribuirMaisDespesa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Create.png"))); // NOI18N
        jbAtribuirMaisDespesa.setText("ATRIBUIR E CRIAR DESPESA A AGENTE");
        jbAtribuirMaisDespesa.setPreferredSize(new java.awt.Dimension(200, 40));
        jbAtribuirMaisDespesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAtribuirMaisDespesaActionPerformed(evt);
            }
        });
        kGradientPanel13.add(jbAtribuirMaisDespesa, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 10, 280, -1));

        DesignarProcesso.add(kGradientPanel13);

        kGradientPanel12.setBackground(new java.awt.Color(255, 255, 255));
        kGradientPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "PROCESSOS SEM RESPONSÁVEL", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(51, 51, 51))); // NOI18N
        kGradientPanel12.setkBorderRadius(0);
        kGradientPanel12.setkEndColor(new java.awt.Color(234, 239, 243));
        kGradientPanel12.setkGradientFocus(0);
        kGradientPanel12.setkStartColor(new java.awt.Color(255, 255, 255));
        kGradientPanel12.setMinimumSize(new java.awt.Dimension(1170, 385));
        kGradientPanel12.setPreferredSize(new java.awt.Dimension(1360, 380));
        kGradientPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane8.setBackground(new java.awt.Color(247, 247, 247));
        jScrollPane8.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane8.setPreferredSize(new java.awt.Dimension(1220, 180));

        tbProcessosReceber.setAutoCreateRowSorter(true);
        tbProcessosReceber.setBackground(new java.awt.Color(247, 247, 247));
        tbProcessosReceber.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        tbProcessosReceber.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cod.", "Seguradora", "CNPJ", "Data Entrada", "Nº Sinistro", "Honorário", "Situação"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbProcessosReceber.setGridColor(new java.awt.Color(247, 247, 247));
        tbProcessosReceber.setMinimumSize(new java.awt.Dimension(610, 0));
        tbProcessosReceber.setRowHeight(20);
        tbProcessosReceber.setSelectionBackground(new java.awt.Color(0, 153, 0));
        tbProcessosReceber.getTableHeader().setReorderingAllowed(false);
        jScrollPane8.setViewportView(tbProcessosReceber);
        if (tbProcessosReceber.getColumnModel().getColumnCount() > 0) {
            tbProcessosReceber.getColumnModel().getColumn(0).setMinWidth(100);
            tbProcessosReceber.getColumnModel().getColumn(0).setPreferredWidth(100);
            tbProcessosReceber.getColumnModel().getColumn(0).setMaxWidth(100);
            tbProcessosReceber.getColumnModel().getColumn(1).setMinWidth(200);
            tbProcessosReceber.getColumnModel().getColumn(2).setMinWidth(200);
            tbProcessosReceber.getColumnModel().getColumn(3).setMinWidth(120);
            tbProcessosReceber.getColumnModel().getColumn(3).setPreferredWidth(120);
            tbProcessosReceber.getColumnModel().getColumn(3).setMaxWidth(120);
            tbProcessosReceber.getColumnModel().getColumn(4).setMinWidth(150);
            tbProcessosReceber.getColumnModel().getColumn(4).setPreferredWidth(150);
            tbProcessosReceber.getColumnModel().getColumn(4).setMaxWidth(150);
            tbProcessosReceber.getColumnModel().getColumn(5).setMinWidth(120);
            tbProcessosReceber.getColumnModel().getColumn(5).setPreferredWidth(120);
            tbProcessosReceber.getColumnModel().getColumn(5).setMaxWidth(120);
            tbProcessosReceber.getColumnModel().getColumn(6).setMinWidth(150);
            tbProcessosReceber.getColumnModel().getColumn(6).setPreferredWidth(150);
            tbProcessosReceber.getColumnModel().getColumn(6).setMaxWidth(150);
        }

        kGradientPanel12.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 1320, 330));

        DesignarProcesso.add(kGradientPanel12);

        kGradientPanel17.setkBorderRadius(0);
        kGradientPanel17.setkEndColor(new java.awt.Color(255, 255, 255));
        kGradientPanel17.setkGradientFocus(0);
        kGradientPanel17.setkStartColor(new java.awt.Color(255, 255, 255));
        kGradientPanel17.setPreferredSize(new java.awt.Dimension(1360, 150));
        kGradientPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tfPesquisarProcessos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfPesquisarProcessosKeyPressed(evt);
            }
        });
        kGradientPanel17.add(tfPesquisarProcessos, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 130, 28));

        kGradientPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "SELECIONAR AGENTE", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(51, 51, 51))); // NOI18N
        kGradientPanel11.setkBorderRadius(0);
        kGradientPanel11.setkEndColor(new java.awt.Color(234, 239, 243));
        kGradientPanel11.setkGradientFocus(0);
        kGradientPanel11.setkStartColor(new java.awt.Color(255, 255, 255));
        kGradientPanel11.setMinimumSize(new java.awt.Dimension(1170, 140));
        kGradientPanel11.setPreferredSize(new java.awt.Dimension(1170, 190));
        kGradientPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbAgenteDesignar.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbAgenteDesignarPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                cbAgenteDesignarPopupMenuWillBecomeVisible(evt);
            }
        });
        cbAgenteDesignar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbAgenteDesignarActionPerformed(evt);
            }
        });
        kGradientPanel11.add(cbAgenteDesignar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 290, 28));

        tfSeguradora3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tfSeguradora3.setForeground(new java.awt.Color(102, 102, 102));
        tfSeguradora3.setText("PROCESSOS JÁ COM ESTE AGENTE");
        kGradientPanel11.add(tfSeguradora3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 20, 250, -1));

        jScrollPane9.setBackground(new java.awt.Color(247, 247, 247));
        jScrollPane9.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane9.setPreferredSize(new java.awt.Dimension(1220, 180));

        tbProcessosDoAgente.setAutoCreateRowSorter(true);
        tbProcessosDoAgente.setBackground(new java.awt.Color(247, 247, 247));
        tbProcessosDoAgente.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        tbProcessosDoAgente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cod.", "Seguradora", "Data Entrada", "Nº Sinistro"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbProcessosDoAgente.setGridColor(new java.awt.Color(247, 247, 247));
        tbProcessosDoAgente.setMinimumSize(new java.awt.Dimension(610, 0));
        tbProcessosDoAgente.setRowHeight(20);
        tbProcessosDoAgente.setSelectionBackground(new java.awt.Color(0, 153, 0));
        tbProcessosDoAgente.getTableHeader().setReorderingAllowed(false);
        jScrollPane9.setViewportView(tbProcessosDoAgente);
        if (tbProcessosDoAgente.getColumnModel().getColumnCount() > 0) {
            tbProcessosDoAgente.getColumnModel().getColumn(0).setMinWidth(80);
            tbProcessosDoAgente.getColumnModel().getColumn(0).setPreferredWidth(80);
            tbProcessosDoAgente.getColumnModel().getColumn(0).setMaxWidth(80);
            tbProcessosDoAgente.getColumnModel().getColumn(2).setMinWidth(120);
            tbProcessosDoAgente.getColumnModel().getColumn(2).setPreferredWidth(120);
            tbProcessosDoAgente.getColumnModel().getColumn(2).setMaxWidth(120);
            tbProcessosDoAgente.getColumnModel().getColumn(3).setMinWidth(150);
            tbProcessosDoAgente.getColumnModel().getColumn(3).setPreferredWidth(150);
            tbProcessosDoAgente.getColumnModel().getColumn(3).setMaxWidth(150);
        }

        kGradientPanel11.add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 40, 540, 80));

        tfSeguradora4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tfSeguradora4.setForeground(new java.awt.Color(102, 102, 102));
        tfSeguradora4.setText("ESCOLHA O AGENTE A DESIGNAR");
        kGradientPanel11.add(tfSeguradora4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 250, -1));

        kGradientPanel17.add(kGradientPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 0, 890, 160));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(102, 102, 102));
        jLabel14.setText("DO PROCESSO:");
        kGradientPanel17.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 34, -1, 20));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/View.png"))); // NOI18N
        jButton4.setPreferredSize(new java.awt.Dimension(50, 40));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        kGradientPanel17.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 50, 50, 40));

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Erase.png"))); // NOI18N
        jButton5.setText("LIMPAR FILTRO");
        jButton5.setPreferredSize(new java.awt.Dimension(50, 40));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        kGradientPanel17.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 50, 180, -1));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(102, 102, 102));
        jLabel15.setText("DIGITE O NÚMERO");
        kGradientPanel17.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, -1));

        DesignarProcesso.add(kGradientPanel17);

        jTabbedPanelDespesas.addTab("PROCESSOS Á DESIGNAR", DesignarProcesso);

        ProcessosAndamentos.setBackground(new java.awt.Color(255, 255, 255));
        ProcessosAndamentos.setMinimumSize(new java.awt.Dimension(1170, 380));
        ProcessosAndamentos.setPreferredSize(new java.awt.Dimension(1170, 380));

        kGradientPanel16.setBackground(new java.awt.Color(255, 255, 255));
        kGradientPanel16.setkBorderRadius(0);
        kGradientPanel16.setkEndColor(new java.awt.Color(234, 239, 243));
        kGradientPanel16.setkGradientFocus(0);
        kGradientPanel16.setkStartColor(new java.awt.Color(255, 255, 255));
        kGradientPanel16.setMinimumSize(new java.awt.Dimension(1170, 100));
        kGradientPanel16.setPreferredSize(new java.awt.Dimension(1360, 60));
        kGradientPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpFinalizarProcesso.setBackground(new java.awt.Color(0, 102, 204));
        jpFinalizarProcesso.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jpFinalizarProcesso.setForeground(new java.awt.Color(255, 255, 255));
        jpFinalizarProcesso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Dollar.png"))); // NOI18N
        jpFinalizarProcesso.setText("ENVIAR PARA PAGAMENTO");
        jpFinalizarProcesso.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jpFinalizarProcesso.setPreferredSize(new java.awt.Dimension(250, 40));
        jpFinalizarProcesso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jpFinalizarProcessoActionPerformed(evt);
            }
        });
        kGradientPanel16.add(jpFinalizarProcesso, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 10, -1, -1));

        jpRevorgarConta2.setBackground(new java.awt.Color(255, 0, 0));
        jpRevorgarConta2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jpRevorgarConta2.setForeground(new java.awt.Color(255, 255, 255));
        jpRevorgarConta2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Back.png"))); // NOI18N
        jpRevorgarConta2.setText("REVOGAR AGENTE");
        jpRevorgarConta2.setPreferredSize(new java.awt.Dimension(230, 40));
        jpRevorgarConta2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jpRevorgarConta2ActionPerformed(evt);
            }
        });
        kGradientPanel16.add(jpRevorgarConta2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, 40));

        ProcessosAndamentos.add(kGradientPanel16);

        kGradientPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "PROCESSOS EM ANDAMENTO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(51, 51, 51))); // NOI18N
        kGradientPanel15.setkBorderRadius(0);
        kGradientPanel15.setkEndColor(new java.awt.Color(234, 239, 243));
        kGradientPanel15.setkGradientFocus(0);
        kGradientPanel15.setkStartColor(new java.awt.Color(255, 255, 255));
        kGradientPanel15.setMinimumSize(new java.awt.Dimension(1170, 550));
        kGradientPanel15.setPreferredSize(new java.awt.Dimension(1360, 440));
        kGradientPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane7.setBackground(new java.awt.Color(102, 102, 102));
        jScrollPane7.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane7.setPreferredSize(new java.awt.Dimension(1220, 180));

        tbProcessosRecebidos.setAutoCreateRowSorter(true);
        tbProcessosRecebidos.setBackground(new java.awt.Color(102, 102, 102));
        tbProcessosRecebidos.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        tbProcessosRecebidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Processo", "Cod. Despesa", "Data Entrada", "Situação", "Seguradora", "Agente Responsável", "Honorário AGT", "Pgto. Total KM", "Adiantamento"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbProcessosRecebidos.setGridColor(new java.awt.Color(247, 247, 247));
        tbProcessosRecebidos.setMinimumSize(new java.awt.Dimension(610, 0));
        tbProcessosRecebidos.setRowHeight(20);
        tbProcessosRecebidos.setSelectionBackground(new java.awt.Color(0, 153, 0));
        tbProcessosRecebidos.getTableHeader().setReorderingAllowed(false);
        jScrollPane7.setViewportView(tbProcessosRecebidos);
        if (tbProcessosRecebidos.getColumnModel().getColumnCount() > 0) {
            tbProcessosRecebidos.getColumnModel().getColumn(0).setMinWidth(80);
            tbProcessosRecebidos.getColumnModel().getColumn(0).setPreferredWidth(80);
            tbProcessosRecebidos.getColumnModel().getColumn(0).setMaxWidth(80);
            tbProcessosRecebidos.getColumnModel().getColumn(1).setMinWidth(0);
            tbProcessosRecebidos.getColumnModel().getColumn(1).setPreferredWidth(0);
            tbProcessosRecebidos.getColumnModel().getColumn(1).setMaxWidth(0);
            tbProcessosRecebidos.getColumnModel().getColumn(2).setMinWidth(120);
            tbProcessosRecebidos.getColumnModel().getColumn(2).setPreferredWidth(120);
            tbProcessosRecebidos.getColumnModel().getColumn(2).setMaxWidth(120);
            tbProcessosRecebidos.getColumnModel().getColumn(4).setMinWidth(250);
            tbProcessosRecebidos.getColumnModel().getColumn(5).setMinWidth(200);
            tbProcessosRecebidos.getColumnModel().getColumn(6).setMinWidth(100);
            tbProcessosRecebidos.getColumnModel().getColumn(6).setPreferredWidth(100);
            tbProcessosRecebidos.getColumnModel().getColumn(6).setMaxWidth(100);
            tbProcessosRecebidos.getColumnModel().getColumn(7).setMinWidth(100);
            tbProcessosRecebidos.getColumnModel().getColumn(7).setPreferredWidth(100);
            tbProcessosRecebidos.getColumnModel().getColumn(7).setMaxWidth(100);
            tbProcessosRecebidos.getColumnModel().getColumn(8).setMinWidth(100);
            tbProcessosRecebidos.getColumnModel().getColumn(8).setPreferredWidth(100);
            tbProcessosRecebidos.getColumnModel().getColumn(8).setMaxWidth(100);
        }

        kGradientPanel15.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 1320, 390));

        ProcessosAndamentos.add(kGradientPanel15);

        kGradientPanel20.setkBorderRadius(0);
        kGradientPanel20.setkEndColor(new java.awt.Color(234, 239, 243));
        kGradientPanel20.setkGradientFocus(0);
        kGradientPanel20.setkStartColor(new java.awt.Color(255, 255, 255));
        kGradientPanel20.setPreferredSize(new java.awt.Dimension(1360, 100));
        kGradientPanel20.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tfPesquisarProcessos1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfPesquisarProcessos1KeyPressed(evt);
            }
        });
        kGradientPanel20.add(tfPesquisarProcessos1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 170, 28));

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(102, 102, 102));
        jLabel26.setText("DIGITE O NÚMERO DO PROCESSO:");
        kGradientPanel20.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, -1, -1));

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/View.png"))); // NOI18N
        jButton6.setText("PESQUISAR");
        jButton6.setPreferredSize(new java.awt.Dimension(50, 40));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        kGradientPanel20.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 20, 170, 40));

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Erase.png"))); // NOI18N
        jButton7.setText("LIMPAR FILTRO");
        jButton7.setPreferredSize(new java.awt.Dimension(50, 40));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        kGradientPanel20.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 20, 180, -1));

        jbReceberConta2.setBackground(new java.awt.Color(0, 153, 255));
        jbReceberConta2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jbReceberConta2.setForeground(new java.awt.Color(255, 255, 255));
        jbReceberConta2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Modify.png"))); // NOI18N
        jbReceberConta2.setText("ALTERAR DESPESA");
        jbReceberConta2.setPreferredSize(new java.awt.Dimension(200, 30));
        jbReceberConta2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbReceberConta2ActionPerformed(evt);
            }
        });
        kGradientPanel20.add(jbReceberConta2, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 20, 190, 40));

        jbVisualizarConta1.setBackground(new java.awt.Color(0, 153, 0));
        jbVisualizarConta1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbVisualizarConta1.setForeground(new java.awt.Color(255, 255, 255));
        jbVisualizarConta1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/excel.png"))); // NOI18N
        jbVisualizarConta1.setText("EXPORTAR");
        jbVisualizarConta1.setPreferredSize(new java.awt.Dimension(190, 40));
        jbVisualizarConta1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbVisualizarConta1ActionPerformed(evt);
            }
        });
        kGradientPanel20.add(jbVisualizarConta1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 20, -1, -1));

        ProcessosAndamentos.add(kGradientPanel20);

        jTabbedPanelDespesas.addTab("PROCESSOS DESIGNADOS", ProcessosAndamentos);

        jPanelPrincipal.add(jTabbedPanelDespesas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 1360, -1));

        getContentPane().add(jPanelPrincipal, java.awt.BorderLayout.PAGE_START);

        getAccessibleContext().setAccessibleDescription("");
        getAccessibleContext().setAccessibleParent(this);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btCancelarVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarVoltarActionPerformed
        // TODO add your handling code here:
        panelTabbedKGVerficiar();
        if (tipoCadastro != null && tipoCadastro.equals("aberto")) {
            this.salvar();
            jTabbedPanelDespesas.setSelectedIndex(1);
            panelTabbedKGVerficiar();
            limparDados();
            this.carregarProcessosRecebidos();
            this.carregarProcessosReceber();
        } else {
            jTabbedPanelDespesas.setSelectedIndex(2);
            this.limparDados();
            panelTabbedKGVerficiar();
        }
    }//GEN-LAST:event_btCancelarVoltarActionPerformed

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
            tipoCadastro = "alteracao";
            this.alterar();
            jTabbedPanelDespesas.setSelectedIndex(2);
            panelTabbedKGVerficiar();
            limparDados();
            this.carregarProcessosReceber();
            this.carregarProcessosRecebidos();
        }
    }//GEN-LAST:event_JbSAlvarActionPerformed

    private void jbVisualizarPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbVisualizarPagarActionPerformed

    }//GEN-LAST:event_jbVisualizarPagarActionPerformed

    private void jbVisualizarPagar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbVisualizarPagar1ActionPerformed

    }//GEN-LAST:event_jbVisualizarPagar1ActionPerformed

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

    private void cbTipoDespesaPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbTipoDespesaPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
    }//GEN-LAST:event_cbTipoDespesaPopupMenuWillBecomeInvisible

    private void jTableDespesasAnexoMouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
    }

    private void jbAtribuirSemDespesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAtribuirSemDespesaActionPerformed
        modelOp = new ModelOp();
        try {
            int linha = tbProcessosReceber.getSelectedRow();
            int codigo = (int) tbProcessosReceber.getValueAt(linha, 0);
            Datas bl = new Datas();
            int codAgente = (Integer.parseInt(cbCodAgenteDesignar.getSelectedItem().toString()));

            //pegunta
            int opcao = JOptionPane.showConfirmDialog(this, "CONFIRMA AÇÃO?"
                    + " ATRIBUIR PROCESSO DE NUMERO:\n" + "\n " + codigo + "?", "Atenção", JOptionPane.YES_NO_OPTION);
            //se sim paga, se não não faz nada
            if (opcao == JOptionPane.OK_OPTION && cbAgenteDesignar.getSelectedIndex() >= 0) {
                modelOp.setCodigo(codigo);
                modelOp.setTipo(4);
                modelOp.setTipoPagamento(4);
                modelOp.setCod_agente(codAgente);

                if (controllerOp.designarAgenteOpController(modelOp)) {
                    tipoCadastro = "aberto";
                    JOptionPane.showMessageDialog(this, "PROCESSO DEFINIDO COMO EM ANDAMENTO, \n DESIGNADO AO AGENTE '" + cbAgenteDesignar.getSelectedObject().toString() + "'!");
                    this.cbCodFuncionario.setSelectedItem(this.cbCodAgenteDesignar.getSelectedItem().toString());
                    this.cbFuncionario.setSelectedItem(String.valueOf(this.cbAgenteDesignar.getSelectedObject().toString()));
                    //================================================
                    carregarDosAgentesNaTabelaEmContasProcessoAtribuir(codigo);
                    Date toDay = new Date();
                    try {
                        tfCodigoProcesso.setText(String.valueOf(codigo));
                        tfSeguradoraProcesso.setText((String.valueOf(tbProcessosDoAgenteRegistrarConta.getValueAt(0, 1))));
                        tfDataProcesso.setText((String.valueOf(tbProcessosDoAgenteRegistrarConta.getValueAt(0, 2))));
                        tfSinistroProcesso.setText((String.valueOf(tbProcessosDoAgenteRegistrarConta.getValueAt(0, 3))));
                        tfObservacao.setText("Nenhuma observação.");
                        jLNomeAgente.setText(cbFuncionario.getSelectedItem().toString());
                        jFDataEnvio.setText(dataAtual());
                        panelTabbedKGVerficiar();
                        this.salvar();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, "ERRO AO SALVAR DADOS!", "ATENÇÂO", JOptionPane.ERROR_MESSAGE);
                        this.limparDados();
                    }

                    panelTabbedKGVerficiar();
                    this.carregarProcessosRecebidos();
                    this.carregarProcessosReceber();
                } else {
                    JOptionPane.showMessageDialog(this, "NENHUM DADO SALVO!", "ATENÇÂO", JOptionPane.WARNING_MESSAGE);
                    this.carregarProcessosReceber();
                    this.carregarProcessosRecebidos();
                    panelTabbedKGVerficiar();
                }

            } else {

                panelTabbedKGVerficiar();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "CERTIFIQUE-SE QUE DE SELECIONAR O AGENTE E PROCESSO!", "ATENÇÂO", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jbAtribuirSemDespesaActionPerformed

    private void jpFinalizarProcessoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jpFinalizarProcessoActionPerformed
        modelOp = new ModelOp();
        try {
            int linha = tbProcessosRecebidos.getSelectedRow();
            int ViewOpcodigo = (int) tbProcessosRecebidos.getValueAt(linha, 0);
            Datas bl = new Datas();

            modelOp.setCodigo(ViewOpcodigo);
            modelOp.setTipo(5);
            modelOp.setTipoPagamento(5);

            //pegunta
            int opcao = JOptionPane.showConfirmDialog(this, "CONFIRMA AÇÃO?"
                    + " FINALIZAR PROCEDIMENTO DO PROCESSO DE NUMERO:\n" + "\n " + ViewOpcodigo + "?", "Atenção", JOptionPane.YES_NO_OPTION);
            //se sim revoga, se não não faz nada
            if (opcao == JOptionPane.OK_OPTION) {
                if (controllerOp.finalizarProcessoOpController(modelOp)) {
                    JOptionPane.showMessageDialog(this, "PROCESSO DEFINIDO COMO AGUARDANDO PAGAMENTO!");
                    carregarProcessosReceber();
                    carregarProcessosRecebidos();
                }
            }
            //jTabbedPanelDespesas.setSelectedIndex(jTabbedPanelDespesas.getSelectedIndex() - 1);
            panelTabbedKGVerficiar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "SELECIONE UM PROCESSO PRIMEIRO!", "ATENÇÂO", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jpFinalizarProcessoActionPerformed

    private void cbCodAgenteDesignarPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbCodAgenteDesignarPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
    }//GEN-LAST:event_cbCodAgenteDesignarPopupMenuWillBecomeInvisible

    private void cbAgenteDesignarPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbAgenteDesignarPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
        if (this.cbAgenteDesignar.isPopupVisible() || this.cbAgenteDesignar.getSelectedObject() != null) {
            modelFuncionario = controllerFuncionario.getFuncionarioController(String.valueOf(cbAgenteDesignar.getSelectedItem().toString()));
            //recupera o nome
            this.cbCodAgenteDesignar.setSelectedItem(controllerFuncionario.getFuncionarioController(cbAgenteDesignar.getSelectedObject().toString()).getCodigo());
            carregarDosAgentesNaTabelaCorrespondente();
        }
    }//GEN-LAST:event_cbAgenteDesignarPopupMenuWillBecomeInvisible

    void carregarDosAgentesNaTabelaCorrespondente() {
        if (cbCodAgenteDesignar.getSelectedIndex() >= 0) {
            int cod = (Integer.parseInt(cbCodAgenteDesignar.getSelectedItem().toString()));
            listaModelOp = controllerOp.getPorAgenteApenasEMAndamentoController(cod);
            DefaultTableModel modelo = (DefaultTableModel) tbProcessosDoAgente.getModel();
            modelo.setNumRows(0);
            //CARREGA OS DADOS DA LISTA NA TABELA
            int cont = listaModelOp.size();
            for (int i = 0; i < cont; i++) {
                modelSeguradora = controllerSeguradora.getSeguradoraController(listaModelOp.get(i).getSeguradoras());
                modelo.addRow(new Object[]{
                    listaModelOp.get(i).getCodigo(),
                    modelSeguradora.getNome(),
                    (new Mascaras().DataRecuperasql(String.valueOf(listaModelOp.get(i).getDataEntrada()))),
                    listaModelOp.get(i).getNumeroSinistro(),
                    (new Moeda().valorStringTODoubleAtt(listaModelOp.get(i).getValorTotalHonorariosSemRetencao().toString()))
                });
            }

            if (tbProcessosDoAgente.getRowCount() < 1) {
                DefaultTableModel modelo1 = (DefaultTableModel) tbProcessosDoAgente.getModel();
                modelo1.setNumRows(0);
                //CARREGA OS DADOS DA LISTA NA TABELA
                int cont1 = 1;
                for (int i = 0; i < cont1; i++) {
                    modelo.addRow(new Object[]{
                        "NENHUM"
                    });
                }
            }
        } else {
            DefaultTableModel modelo = (DefaultTableModel) tbProcessosDoAgente.getModel();
            modelo.setNumRows(0);
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

    void carregarDosAgentesNaTabelaEmContasProcessoAtribuir(int pInt) {
        int cod = (Integer.parseInt(cbCodAgenteDesignar.getSelectedItem().toString()));
        listaModelOp = controllerOp.getPorAgenteApenasEMAndamentoController(cod);
        modelOp = controllerOp.getOpController(pInt);
        DefaultTableModel modelo = (DefaultTableModel) tbProcessosDoAgenteRegistrarConta.getModel();
        modelo.setNumRows(0);
        //CARREGA OS DADOS DA LISTA NA TABELA
        int cont = 1;
        for (int i = 0; i < cont; i++) {
            modelFormaPagamento = controllerTipoPagamento.getFormaPagamentoController(modelOp.getTipo());
            modelSeguradora = controllerSeguradora.getSeguradoraController(modelOp.getSeguradoras());
            modelo.addRow(new Object[]{
                modelOp.getCodigo(),
                modelSeguradora.getNome(),
                (new Mascaras().DataRecuperasql(String.valueOf(modelOp.getDataEntrada()))),
                modelOp.getNumeroSinistro(),
                (new Moeda().valorStringTODoubleAtt(modelOp.getValorTotalHonorariosSemRetencao().toString())),
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

    void carregarDosAgentesNaTabelaEmContasProcessoAtribuirRecebidos(int pInt, int pCodigo) {
        int cod = (pCodigo);
        listaModelOp = controllerOp.getPorAgenteApenasEMAndamentoController(cod);
        modelOp = controllerOp.getOpController(pInt);
        DefaultTableModel modelo = (DefaultTableModel) tbProcessosDoAgenteRegistrarConta.getModel();
        modelo.setNumRows(0);
        //CARREGA OS DADOS DA LISTA NA TABELA
        int cont = 1;
        for (int i = 0; i < cont; i++) {
            modelFormaPagamento = controllerTipoPagamento.getFormaPagamentoController(modelOp.getTipo());
            modelSeguradora = controllerSeguradora.getSeguradoraController(modelOp.getSeguradoras());
            modelo.addRow(new Object[]{
                modelOp.getCodigo(),
                modelSeguradora.getNome(),
                (new Mascaras().DataRecuperasql(String.valueOf(modelOp.getDataEntrada()))),
                modelOp.getNumeroSinistro(),
                (new Moeda().valorStringTODoubleAtt(modelOp.getValorTotalHonorariosSemRetencao().toString())),
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
    private void jbVisualizarConta1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbVisualizarConta1ActionPerformed
        // TODO add your handling code here:

        if (tbProcessosRecebidos.getRowCount() > 0) {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de excel", "xls");
            chooser.setFileFilter(filter);
            chooser.setDialogTitle("Salvar Arquivos");
            chooser.setAcceptAllFileFilterUsed(false);
            if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                List<JTable> tb = new ArrayList<JTable>();
                List<String> nom = new ArrayList<String>();
                tb.add(tbProcessosRecebidos);
                nom.add("Por Agentes");
                String file = chooser.getSelectedFile().toString().concat(".xls");
                try {
                    Exporter e = new Exporter(new File(file), tb, nom);
                    if (e.export()) {
                        JOptionPane.showMessageDialog(null, "Os dados foram exportados com sucesso!", "ARQUIVO EXCEL", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Exception Erro " + e.getMessage(), " Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Não há dados para exportar na tabela", "ATENÇÃO", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jbVisualizarConta1ActionPerformed

    private void tbProcessosDoAgenteRegistrarContaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbProcessosDoAgenteRegistrarContaMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_tbProcessosDoAgenteRegistrarContaMouseClicked

    private void btAplicarProcessoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAplicarProcessoActionPerformed
        // TODO add your handling code here:
        Date toDay = new Date();
        try {
            int linha = tbProcessosDoAgenteRegistrarConta.getSelectedRow();
            tfCodigoProcesso.setText((String.valueOf(tbProcessosDoAgenteRegistrarConta.getValueAt(linha, 0))));
            tfSeguradoraProcesso.setText((String.valueOf(tbProcessosDoAgenteRegistrarConta.getValueAt(linha, 1))));
            tfDataProcesso.setText((String.valueOf(tbProcessosDoAgenteRegistrarConta.getValueAt(linha, 2))));
            tfSinistroProcesso.setText((String.valueOf(tbProcessosDoAgenteRegistrarConta.getValueAt(linha, 3))));
            tfObservacao.setText("Nenhuma observação.");
            tfDataDespesaAlterar.setText(String.valueOf(toDay));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "VOCÊ DEVE SELECIONAR UM PROCESSO!", "ATENÇÂO", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_btAplicarProcessoActionPerformed

    private void jpRevorgarConta2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jpRevorgarConta2ActionPerformed
        // TODO add your handling code here:
        modelOp = new ModelOp();
        try {
            int linha = tbProcessosRecebidos.getSelectedRow();
            int ViewOpcodigo = (int) tbProcessosRecebidos.getValueAt(linha, 0);
            int codigoAdiantamento = (int) tbProcessosRecebidos.getValueAt(linha, 1);
            String nome = (String) tbProcessosRecebidos.getValueAt(linha, 5);
            Datas bl = new Datas();
            //pegunta
            int opcao = JOptionPane.showConfirmDialog(this, "CONFIRMA AÇÃO?"
                    + " REVOGAR DESIGNAÇÃO DO PROCESSO DE NUMERO:\n" + "\n " + ViewOpcodigo + "?", "Atenção", JOptionPane.YES_NO_OPTION);
            //se sim revoga, se não não faz nada
            if (opcao == JOptionPane.OK_OPTION) {
                //    this.excluirConta();
                if (excluirConta() == true) {
                    modelOp.setCodigo(ViewOpcodigo);
                    modelOp.setTipo(1);
                    modelOp.setTipoPagamento(1);
                    modelOp.setCod_agente(0);
                    if (controllerOp.designarAgenteOpController(modelOp)) {
                        JOptionPane.showMessageDialog(this, "PROCESSO DEFINIDO COMO EM ABERTO E REPASSE APAGADO, \n O PROCESSO SAIU DA FILA DO AGENTE '" + nome + "'!");
                        carregarProcessosReceber();
                        carregarProcessosRecebidos();

                    }
                }
                excluirAdiantamentosPorDspesa(codigoAdiantamento);
            } else {
                // JOptionPane.showMessageDialog(this, "VOCÊ ABORTOU A REVOGAÇÃO \n O PROCESSO E A DESPESA CONTINUAM EM ANDAMENTO!", "ATENÇÂO", JOptionPane.WARNING_MESSAGE);
            }
            // jTabbedPanelDespesas.setSelectedIndex(jTabbedPanelDespesas.getSelectedIndex() - 1);
            panelTabbedKGVerficiar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "SELECIONE UM PROCESSO PRIMEIRO!", "ATENÇÂO", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_jpRevorgarConta2ActionPerformed

    private void excluirAdiantamentosPorDspesa(int codigoAdiantamento) {
        String codigo = String.valueOf(codigoAdiantamento);
        String sqlElim = "DELETE FROM adiantamentos WHERE cod_despesa='" + codigo + "'";
        try {
            PreparedStatement pst = cn.prepareStatement(sqlElim);
            int n = pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ViewCadOcupacao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void kButtonRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButtonRegistrarActionPerformed
        // TODO add your handling code here:
        if ("  /  /    ".equals(jFDataEnvio.getText()) || "".equals(jFDataEnvio.getText())) {
            JOptionPane.showMessageDialog(this, "ANTES ESCOLHA O PROCESSO A QUAL IRÁ ALTERAR AS DESPESAS!", "ATENÇÂO", JOptionPane.WARNING_MESSAGE);
            panelTabbedKGVerficiar();
        } else {
            jTabbedPanelDespesas.setSelectedIndex(0);
            panelTabbedKGVerficiar();
            somaTotal();
        }
    }//GEN-LAST:event_kButtonRegistrarActionPerformed

    private void kButtonAndamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButtonAndamentoActionPerformed
        // TODO add your handling code here:
        jTabbedPanelDespesas.setSelectedIndex(2);
        panelTabbedKGVerficiar();
    }//GEN-LAST:event_kButtonAndamentoActionPerformed

    private void kButtonProcessosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButtonProcessosActionPerformed
        // TODO add your handling code here:
        jTabbedPanelDespesas.setSelectedIndex(1);
        panelTabbedKGVerficiar();
    }//GEN-LAST:event_kButtonProcessosActionPerformed

    private void cbAgenteDesignarPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbAgenteDesignarPopupMenuWillBecomeVisible
        // TODO add your handling code here:
        listarFuncionarios();
    }//GEN-LAST:event_cbAgenteDesignarPopupMenuWillBecomeVisible

    private void cbAgenteDesignarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbAgenteDesignarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbAgenteDesignarActionPerformed

    private void jbAtribuirMaisDespesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAtribuirMaisDespesaActionPerformed
        // TODO add your handling code here:

        modelOp = new ModelOp();
        try {
            int linha = tbProcessosReceber.getSelectedRow();
            int codigo = (int) tbProcessosReceber.getValueAt(linha, 0);
            Datas bl = new Datas();
            int codAgente = (Integer.parseInt(cbCodAgenteDesignar.getSelectedItem().toString()));

            //pegunta
            int opcao = JOptionPane.showConfirmDialog(this, "CONFIRMA AÇÃO?"
                    + " ATRIBUIR PROCESSO DE NUMERO:\n" + "\n " + codigo + "?", "Atenção", JOptionPane.YES_NO_OPTION);
            //se sim paga, se não não faz nada
            if (opcao == JOptionPane.OK_OPTION && cbAgenteDesignar.getSelectedIndex() >= 0) {
                modelOp.setCodigo(codigo);
                modelOp.setTipo(4);
                modelOp.setTipoPagamento(4);
                modelOp.setCod_agente(codAgente);

                if (controllerOp.designarAgenteOpController(modelOp)) {
                    tipoCadastro = "aberto";
                    JOptionPane.showMessageDialog(this, "PROCESSO DEFINIDO COMO EM ANDAMENTO, \n DESIGNADO AO AGENTE '" + cbAgenteDesignar.getSelectedObject().toString() + "'!");
                    this.cbCodFuncionario.setSelectedItem(this.cbCodAgenteDesignar.getSelectedItem().toString());
                    this.cbFuncionario.setSelectedItem(String.valueOf(this.cbAgenteDesignar.getSelectedObject().toString()));
                    //================================================
                    carregarDosAgentesNaTabelaEmContasProcessoAtribuir(codigo);
                    Date toDay = new Date();
                    try {
                        tfCodigoProcesso.setText(String.valueOf(codigo));
                        tfSeguradoraProcesso.setText((String.valueOf(tbProcessosDoAgenteRegistrarConta.getValueAt(0, 1))));
                        tfDataProcesso.setText((String.valueOf(tbProcessosDoAgenteRegistrarConta.getValueAt(0, 2))));
                        tfSinistroProcesso.setText((String.valueOf(tbProcessosDoAgenteRegistrarConta.getValueAt(0, 3))));
                        tfObservacao.setText("Nenhuma observação.");
                        jLNomeAgente.setText(cbFuncionario.getSelectedItem().toString());
                        jFDataEnvio.setText(dataAtual());
                        this.salvar();
                        int codigoDespesa = controllerContaAgentes.getContaAgentesControllerPorProcesso(codigo).getCodigo();
                        recuperarConta(codigoDespesa);
                        tipoCadastro = "alteracao";
                        jTabbedPanelDespesas.setSelectedIndex(0);
                        panelTabbedKGVerficiar();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, "ERRO AO SALVAR DADOS!", "ATENÇÂO", JOptionPane.ERROR_MESSAGE);
                        this.limparDados();
                    }

                    panelTabbedKGVerficiar();

                    this.carregarProcessosRecebidos();
                    this.carregarProcessosReceber();
                } else {
                    JOptionPane.showMessageDialog(this, "NENHUM DADO SALVO!", "ATENÇÂO", JOptionPane.WARNING_MESSAGE);
                    this.carregarProcessosReceber();
                    this.carregarProcessosRecebidos();
                    //jTabbedPanelDespesas.setSelectedIndex(2);
                    panelTabbedKGVerficiar();
                }
            } else {
                panelTabbedKGVerficiar();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "CERTIFIQUE-SE QUE DE SELECIONAR O AGENTE E PROCESSO!", "ATENÇÂO", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jbAtribuirMaisDespesaActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel tabela = (DefaultTableModel) this.tbProcessosReceber.getModel();
        final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tabela);
        this.tbProcessosReceber.setRowSorter(sorter);
        String text = tfPesquisarProcessos.getText();
        sorter.setRowFilter(RowFilter.regexFilter(text, 0));
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        this.tbProcessosReceber.setRowSorter(null);
        cbAgenteDesignar.setSelectedIndex(-1);
        cbCodAgenteDesignar.setSelectedIndex(-1);
        tfPesquisarProcessos.setText("");
        this.carregarProcessosReceber();
        carregarDosAgentesNaTabelaCorrespondente();

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel tabela = (DefaultTableModel) this.tbProcessosRecebidos.getModel();
        final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tabela);
        this.tbProcessosRecebidos.setRowSorter(sorter);
        String text = tfPesquisarProcessos1.getText();
        sorter.setRowFilter(RowFilter.regexFilter(text, 0));
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        this.tbProcessosRecebidos.setRowSorter(null);
        tfPesquisarProcessos1.setText("");
        this.carregarProcessosRecebidos();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jbReceberConta2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbReceberConta2ActionPerformed
        // TODO add your handling code here:
        try {
            int linha = tbProcessosRecebidos.getSelectedRow();
            int codigo = (int) tbProcessosRecebidos.getValueAt(linha, 1);
            recuperarConta(codigo);
            tipoCadastro = "alteracao";
            jTabbedPanelDespesas.setSelectedIndex(0);
            panelTabbedKGVerficiar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Você selecionar um processo para alterar!", "ATENÇÂO", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jbReceberConta2ActionPerformed

    private void tfObservacaoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfObservacaoFocusLost
        // TODO add your handling code here:
        if (!"".equals(tfObservacao.getText())) {
            tfObservacao.setText(tfObservacao.getText());
        } else {
            tfObservacao.setText("Nenhuma observação.");
        }

    }//GEN-LAST:event_tfObservacaoFocusLost

    private void tfObservacaoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfObservacaoFocusGained
        // TODO add your handling code here:
        if ("Nenhuma observação.".equals(tfObservacao.getText())) {
            tfObservacao.setText("");
        }
    }//GEN-LAST:event_tfObservacaoFocusGained

    private void jFDataRecebimentoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFDataRecebimentoFocusLost
        // TODO add your handling code here:
        jFDataEnvio.setText(jFDataEnvio.getText().toUpperCase());
    }//GEN-LAST:event_jFDataRecebimentoFocusLost

    private void jFDataEnvioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFDataEnvioFocusLost
        // TODO add your handling code here:
        jFDataEnvio.setText(jFDataEnvio.getText().toUpperCase());
    }//GEN-LAST:event_jFDataEnvioFocusLost

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

    private void jFDataNegFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFDataNegFocusLost
        // TODO add your handling code here:
        jFDataNeg.setText(jFDataNeg.getText().toUpperCase());
    }//GEN-LAST:event_jFDataNegFocusLost

    private void jFDataEnvioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFDataEnvioKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jFDataRecebimento.requestFocus();
        }
    }//GEN-LAST:event_jFDataEnvioKeyPressed

    private void jFDataRecebimentoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFDataRecebimentoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            tfObservacao.requestFocus();
        }
    }//GEN-LAST:event_jFDataRecebimentoKeyPressed

    private void jFValorNegKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFValorNegKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jFDataNeg.requestFocus();
            this.jfAgenteTotalRepasse.setText(somaValorAgente());
            somaTotal();
        }
    }//GEN-LAST:event_jFValorNegKeyPressed

    private void jFDataNegKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFDataNegKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            tfValorDespesaAlterar.requestFocus();
        }
    }//GEN-LAST:event_jFDataNegKeyPressed

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

    private void jfAgenteKmPercorridoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jfAgenteKmPercorridoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jfPgtoKmSegudradora.requestFocus();
            this.jfAgenteTotalRepasse.setText(somaValorAgente());
            somaTotal();
        }
    }//GEN-LAST:event_jfAgenteKmPercorridoKeyPressed

    private void tfPesquisarProcessosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPesquisarProcessosKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            DefaultTableModel tabela = (DefaultTableModel) this.tbProcessosReceber.getModel();
            final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tabela);
            this.tbProcessosReceber.setRowSorter(sorter);
            String text = tfPesquisarProcessos.getText();
            sorter.setRowFilter(RowFilter.regexFilter(text, 0));
        }
    }//GEN-LAST:event_tfPesquisarProcessosKeyPressed

    private void tfPesquisarProcessos1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPesquisarProcessos1KeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            DefaultTableModel tabela = (DefaultTableModel) this.tbProcessosRecebidos.getModel();
            final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tabela);
            this.tbProcessosRecebidos.setRowSorter(sorter);
            String text = tfPesquisarProcessos1.getText();
            sorter.setRowFilter(RowFilter.regexFilter(text, 0));
        }
    }//GEN-LAST:event_tfPesquisarProcessos1KeyPressed

    private void jFValorNegFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFValorNegFocusGained
        // TODO add your handling code here:
        if ("R$ 0,00".equals(jFValorNeg.getText())) {
            jFValorNeg.setText("");
        }
    }//GEN-LAST:event_jFValorNegFocusGained

    private void jfAgenteKmPercorridoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jfAgenteKmPercorridoFocusGained
        // TODO add your handling code here:
        // TODO add your handling code here:
        if ("0".equals(jfAgenteKmPercorrido.getText()) || (Integer.parseInt(jfAgenteKmPercorrido.getText()) <= 0)) {
            jfAgenteKmPercorrido.setText("");
        } else {
            jfAgenteKmPercorrido.setText(jfAgenteKmPercorrido.getText());
        }
    }//GEN-LAST:event_jfAgenteKmPercorridoFocusGained

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

    private void jfPgtoKmSegudradoraFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jfPgtoKmSegudradoraFocusGained
        // TODO add your handling code here:
        if ("R$ 0,00".equals(jfPgtoKmSegudradora.getText())) {
            jfPgtoKmSegudradora.setText("");
        }
    }//GEN-LAST:event_jfPgtoKmSegudradoraFocusGained

    private void jfAgenteHonorarioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jfAgenteHonorarioFocusGained
        // TODO add your handling code here:
        if ("R$ 0,00".equals(jfAgenteHonorario.getText())) {
            jfAgenteHonorario.setText("");
        }
    }//GEN-LAST:event_jfAgenteHonorarioFocusGained

    private void jfPgtoKmSegudradoraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jfPgtoKmSegudradoraKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            this.JbSAlvar.requestFocus();
            jfAgenteTotalRepasse.setText(somaValorAgente());
            somaTotal();
        }
    }//GEN-LAST:event_jfPgtoKmSegudradoraKeyPressed

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

    private void kButtonProcessos2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButtonProcessos2ActionPerformed
        // TODO add your handling code here:
        somaAdiantamentos();
        somaValorAgente();
        somaTotal();
    }//GEN-LAST:event_kButtonProcessos2ActionPerformed

    private void tfDescricaoDespesaAlterarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfDescricaoDespesaAlterarFocusLost
        // TODO add your handling code here:
        tfDescricaoDespesaAlterar.setText(tfDescricaoDespesaAlterar.getText().toUpperCase());
    }//GEN-LAST:event_tfDescricaoDespesaAlterarFocusLost
    private void panelTabbedKGVerficiar() {
        if (jTabbedPanelDespesas.getSelectedIndex() == 1) {
            kButtonProcessos.setSelected(true);
        } else {
            kButtonProcessos.setSelected(false);
        }
        if (jTabbedPanelDespesas.getSelectedIndex() == 2) {
            kButtonAndamento.setSelected(true);
        } else {
            kButtonAndamento.setSelected(false);
        }
        if (jTabbedPanelDespesas.getSelectedIndex() == 0) {
            kButtonRegistrar.setSelected(true);
        } else {
            kButtonRegistrar.setSelected(false);
        }
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
        this.somaAdiantamentos();
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

    //FUNCÕES
    private void recuperarDadosPorProcesso() {
        if (Integer.parseInt(this.tfCodigoProcesso.getText()) > 0) {
            modelOp = controllerOp.getOpController(Integer.parseInt(tfCodigoProcesso.getText()));
            //recupera o código
            this.tfSeguradoraProcesso.setText(controllerSeguradora.getSeguradoraController(modelOp.getSeguradoras()).getNome());
            this.tfSinistroProcesso.setText(String.valueOf(modelOp.getNumeroSinistro()));
            this.tfDataProcesso.setText(new Mascaras().DataRecuperasql(String.valueOf(modelOp.getDataEntrada())));
        }
    }

    private void recuperarCodigoPorFuncionario() {
        if (this.cbFuncionario.getSelectedIndex() > 0) {
            modelFuncionario = controllerFuncionario.getFuncionarioController(cbFuncionario.getSelectedItem().toString());
            //recupera o código
            this.cbCodFuncionario.setSelectedItem(modelFuncionario.getCodigo());
        }
    }

    Mascaras swMascaras = new Mascaras();

    private void listarFuncionarios() {
        listaModelFuncionario = controllerFuncionario.getListaSomenteAgentesController();
        cbFuncionario.removeAllItems();
        cbCodFuncionario.removeAllItems();
        cbAgenteDesignar.removeAllItems();
        cbCodAgenteDesignar.removeAllItems();
        for (int i = 0; i < listaModelFuncionario.size(); i++) {
            cbFuncionario.addItem(listaModelFuncionario.get(i).getNome());
            cbCodFuncionario.addItem(listaModelFuncionario.get(i).getCodigo());
            cbCodAgenteDesignar.addItem(listaModelFuncionario.get(i).getCodigo());
            cbAgenteDesignar.addItem(listaModelFuncionario.get(i).getNome());
        }
    }

    void carregarAdiantamentosAlteracao() {
        String DescAdiantamentos = "Adiantamentos = ";
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

    private void listarTipoDespesa() {
        listaModelTipoDespesas = controllerTipoDespesas.getListaTipoDespesasController();
        cbTipoDespesa.removeAllItems();
        for (int i = 0; i < listaModelTipoDespesas.size(); i++) {
            cbTipoDespesa.addItem(listaModelTipoDespesas.get(i).getNome());
        }
    }

    private void listarBancos() {
        listaModelBancos = controllerBancos.getListaBancosController();
        cbBancos.removeAllItems();
        for (int i = 0; i < listaModelBancos.size(); i++) {
            cbBancos.addItem(listaModelBancos.get(i).getBanco_nome());
        }
    }
    //SOMA TABELA DE ANEXO

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
        somaAdiantamentos();
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
        this.cbAgenteDesignar.setSelectedIndex(-1);
        this.totalAdiantamentosAlterar.setText("R$ 0,00");
        tfValidarDataAlterar.setVisible(false);
        tfValidarValorAlterar.setVisible(false);
        tfValidarDescricaoAlterar.setVisible(false);
        DefaultTableModel modelo = (DefaultTableModel) tbAdiantamentoAlterar.getModel();
        modelo.setNumRows(0);
        jLDescricaoAdiantamento.setText("");
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel DesignarProcesso;
    private javax.swing.JButton JbSAlvar;
    private javax.swing.JPanel ProcessosAndamentos;
    private javax.swing.JPanel RegistrarContas;
    private javax.swing.JButton btAdiantamentoAlterar;
    private javax.swing.JButton btAplicarProcesso;
    private javax.swing.JButton btCancelarVoltar;
    private javax.swing.JButton btExcluirAdiantamento;
    private componentes.UJComboBox cbAgenteDesignar;
    private componentes.UJComboBox cbBancos;
    private javax.swing.JComboBox cbCodAgenteDesignar;
    private javax.swing.JComboBox cbCodFuncionario;
    private componentes.UJComboBox cbFuncionario;
    private componentes.UJComboBox cbTipoDespesa;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JFormattedTextField jFDataEnvio;
    private javax.swing.JFormattedTextField jFDataNeg;
    private javax.swing.JFormattedTextField jFDataRecebimento;
    private javax.swing.JLabel jFPagamentoTotalKM;
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
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanelPrincipal;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JScrollPane jScrollPaneAnalista;
    private javax.swing.JScrollPane jScrollPaneAnalista1;
    private javax.swing.JScrollPane jScrollPaneAnalista2;
    private javax.swing.JScrollPane jScrollPaneAnalista3;
    private javax.swing.JTabbedPane jTabbedPanelDespesas;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JButton jbAtribuirMaisDespesa;
    private javax.swing.JButton jbAtribuirSemDespesa;
    private javax.swing.JButton jbReceberConta2;
    private javax.swing.JButton jbVisualizarConta1;
    private javax.swing.JFormattedTextField jfAgenteHonorario;
    private javax.swing.JFormattedTextField jfAgenteKmPercorrido;
    private javax.swing.JLabel jfAgenteTotalRepasse;
    private javax.swing.JFormattedTextField jfPgtoKmSegudradora;
    private javax.swing.JButton jpFinalizarProcesso;
    private javax.swing.JButton jpRevorgarConta2;
    private javax.swing.JLabel jtfCodigoConta;
    private keeptoo.KButton kButtonAndamento;
    private keeptoo.KButton kButtonProcessos;
    private keeptoo.KButton kButtonProcessos2;
    private keeptoo.KButton kButtonRegistrar;
    private keeptoo.KGradientPanel kGDadosDoProcesso;
    private keeptoo.KGradientPanel kGFuncionarioAgente;
    private keeptoo.KGradientPanel kGPAlterarAdiantamentos;
    private keeptoo.KGradientPanel kGRepasseAoAgente;
    private keeptoo.KGradientPanel kGTotalGeral;
    private keeptoo.KGradientPanel kGTotalGeral1;
    private keeptoo.KGradientPanel kGradientPanel1;
    private keeptoo.KGradientPanel kGradientPanel11;
    private keeptoo.KGradientPanel kGradientPanel12;
    private keeptoo.KGradientPanel kGradientPanel13;
    private keeptoo.KGradientPanel kGradientPanel14;
    private keeptoo.KGradientPanel kGradientPanel15;
    private keeptoo.KGradientPanel kGradientPanel16;
    private keeptoo.KGradientPanel kGradientPanel17;
    private keeptoo.KGradientPanel kGradientPanel2;
    private keeptoo.KGradientPanel kGradientPanel20;
    private keeptoo.KGradientPanel kGradientPanel22;
    private keeptoo.KGradientPanel kGradientPanel3;
    private keeptoo.KGradientPanel kGradientPanel4;
    private keeptoo.KGradientPanel kGradientPanel5;
    private keeptoo.KGradientPanel kGradientPanel6;
    private keeptoo.KGradientPanel kGradientPanel7;
    private javax.swing.JLabel labelPagamento;
    private javax.swing.JTable tbAdiantamentoAlterar;
    private javax.swing.JTable tbProcessosDoAgente;
    private javax.swing.JTable tbProcessosDoAgenteRegistrarConta;
    private javax.swing.JTable tbProcessosReceber;
    private javax.swing.JTable tbProcessosRecebidos;
    private javax.swing.JLabel tfCodigoProcesso;
    private javax.swing.JFormattedTextField tfDataDespesaAlterar;
    private javax.swing.JLabel tfDataProcesso;
    private javax.swing.JTextField tfDescricaoDespesaAlterar;
    private javax.swing.JTextArea tfObservacao;
    private javax.swing.JTextField tfPesquisarProcessos;
    private javax.swing.JTextField tfPesquisarProcessos1;
    private javax.swing.JLabel tfSeguradora;
    private javax.swing.JLabel tfSeguradora1;
    private javax.swing.JLabel tfSeguradora2;
    private javax.swing.JLabel tfSeguradora3;
    private javax.swing.JLabel tfSeguradora4;
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

    public void CorNaLinha() {
        final String CLASS = "Em Andamento";
        tbProcessosRecebidos.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value,
                        isSelected, hasFocus, row, column);

                Color c = Color.BLACK;
                Object texto = table.getValueAt(row, 3);
                if (texto != null && CLASS.equals(texto.toString())) {
                    c = Color.ORANGE;
                    label.setForeground(c);
                    label.setFont(new Font("Tahoma", Font.BOLD, 12));
                    tbProcessosRecebidos.setSelectionBackground(Color.BLACK);
                } else {
                    c = Color.GREEN;
                    label.setForeground(c);
                    label.setFont(new Font("Tahoma", Font.BOLD, 12));
                    tbProcessosRecebidos.setSelectionBackground(Color.BLACK);
                }
                return label;
            }
        });
    }

    /**
     * Excluir dados do banco
     *
     * @return
     */
    private boolean excluirConta() {
        int linha = tbProcessosRecebidos.getSelectedRow();
        String nome = (String) tbProcessosRecebidos.getValueAt(linha, 5);
        int codigo = (int) tbProcessosRecebidos.getValueAt(linha, 1);

        //pegunta se realmente deseja excluir
        int opcao = JOptionPane.showConfirmDialog(this, "ESSA AÇÃO IRÁ APAGAR"
                + " OS REGISTROS DE REPASSES PARA O AGENTE:" + "\n " + nome + " \n DESEJA REALMENTE CONTINUAR?", "Atenção", JOptionPane.YES_NO_OPTION);
        //se sim exclui, se não não faz nada
        if (opcao == JOptionPane.OK_OPTION) {
            if (controllerContaAgentes.excluirContaAgentesController(codigo)) {
                //   JOptionPane.showMessageDialog(this, "Registro de Despesa excluido!");
            } else {
                //  JOptionPane.showMessageDialog(this, "Você não excluiu!", "ERRO", JOptionPane.WARNING_MESSAGE);
            }
            return true;
        } else {
            JOptionPane.showMessageDialog(this, "VOCÊ ABORTOU A REVOGAÇÃO \n O PROCESSO E A DESPESA CONTINUAM EM ANDAMENTO!", "ATENÇÂO", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }
    public static ViewCadDespesasAgentes viewcaddespesasagentes;

    ConexaoBanco cc = new ConexaoBanco();
    Connection cn = cc.conectar();

    private void SetIcone() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("ictrol.png")));
    }

}
