/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ControllerFuncionario;
import Controller.ControllerContaReceber;
import Controller.ControllerCobertura;
import Controller.ControllerOp;
import Controller.ControllerBancos;
import Controller.ControllerSeguradora;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import Model.ModelFuncionario;
import Model.ModelContaReceber;
import Model.ModelCobertura;
import Model.ModelOp;
import Model.ModelBancos;
import Model.ModelSeguradora;
import java.awt.Color;
import java.awt.Toolkit;
import java.io.File;
import java.util.Date;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import util.Datas;
import util.Exporter;
import util.Mascaras;
import util.Moeda;

/**
 *
 * @author Administrador
 */
public class ViewContasReceber extends javax.swing.JFrame {

    ModelContaReceber modelContaReceber = new ModelContaReceber();
    ControllerFuncionario controllerFuncionario = new ControllerFuncionario();
    ArrayList<ModelContaReceber> listaModelContaReceber = new ArrayList<>();
    ControllerContaReceber controllerContaReceber = new ControllerContaReceber();
    ControllerCobertura controllerCobertura = new ControllerCobertura();
    ArrayList<ModelCobertura> listaModelCobertura = new ArrayList<>();
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

    /**
     * /**
     * Creates new form ViewContasReceber
     */
    public ViewContasReceber() {
        initComponents();
        SetIcone();
        this.limparDados();
        this.habilitaDesabilitarCampos(false);
        jTabbedPanelContasAReceber.setSelectedIndex(0);
        panelTabbedKGVerficiar();
        this.carregarContasReceber();
        listarFuncionarios();
        this.carregarContasPagas();
    }

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
                new ViewContasReceber().setVisible(true);
            }
        });
    }

    private void panelTabbedKGVerficiar() {
        if (jTabbedPanelContasAReceber.getSelectedIndex() == 0) {
            KBContasReceber.setSelected(true);
        } else {
            KBContasReceber.setSelected(false);
        }
        if (jTabbedPanelContasAReceber.getSelectedIndex() == 1) {
            kBContasPagas.setSelected(true);
        } else {
            kBContasPagas.setSelected(false);
        }
        if (jTabbedPanelContasAReceber.getSelectedIndex() == 2) {
            kBRegistrarContas.setSelected(true);
        } else {
            kBRegistrarContas.setSelected(false);
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

        kGRepasseAoAgente = new keeptoo.KGradientPanel();
        jLAgenteHonorarios = new javax.swing.JLabel();
        jfAgenteHonorario = new javax.swing.JFormattedTextField();
        jLAgenteSeguradoraKm = new javax.swing.JLabel();
        jLAgenteRepasseTotal = new javax.swing.JLabel();
        jfAgenteTotalRepasse = new javax.swing.JFormattedTextField();
        jLAgenteKMpercorrido = new javax.swing.JLabel();
        jfAgenteKmPercorrido = new javax.swing.JFormattedTextField();
        jFPagamentoTotalKM = new javax.swing.JTextField();
        jLAgenteTotalKm = new javax.swing.JLabel();
        cbAgentePtgoPorKMSeguradoraProcessos = new componentes.UJComboBox();
        tfSeguradora = new javax.swing.JLabel();
        cbCodFuncionario = new javax.swing.JComboBox();
        cbSeguradorasProcesso = new componentes.UJComboBox();
        cbSinistroProcesso = new componentes.UJComboBox();
        cbDataProcesso = new componentes.UJComboBox();
        jcbProcessos = new componentes.UJComboBox();
        kGradientPanel14 = new keeptoo.KGradientPanel();
        KBContasReceber = new keeptoo.KButton();
        kBContasPagas = new keeptoo.KButton();
        kBRegistrarContas = new keeptoo.KButton();
        kButtonPendentes1 = new keeptoo.KButton();
        jTabbedPanelContasAReceber = new javax.swing.JTabbedPane();
        ContasAReceber = new javax.swing.JPanel();
        kGradientPanel6 = new keeptoo.KGradientPanel();
        jLabel19 = new javax.swing.JLabel();
        cbFuncionariosEfetuado = new javax.swing.JComboBox<String>();
        btClearEfetuado = new javax.swing.JButton();
        kGradientPanel7 = new keeptoo.KGradientPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tbContasReceber = new javax.swing.JTable();
        kGradientPanel5 = new keeptoo.KGradientPanel();
        jbReceberConta = new javax.swing.JButton();
        btNovo = new javax.swing.JButton();
        btAlterar = new javax.swing.JButton();
        jbExcluirReceber = new javax.swing.JButton();
        ContasRecebidas = new javax.swing.JPanel();
        kGradientPanel10 = new keeptoo.KGradientPanel();
        jLabel11 = new javax.swing.JLabel();
        jdcDataInicial = new com.toedter.calendar.JDateChooser();
        jdcDataFinal = new com.toedter.calendar.JDateChooser();
        btDefinirData = new javax.swing.JButton();
        cbFuncionarios = new javax.swing.JComboBox<String>();
        jLabel21 = new javax.swing.JLabel();
        btClear = new javax.swing.JButton();
        kGradientPanel8 = new keeptoo.KGradientPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tbContasRecebidas = new javax.swing.JTable();
        kGradientPanel9 = new keeptoo.KGradientPanel();
        jpRevorgarConta = new javax.swing.JButton();
        jbVisualizarConta = new javax.swing.JButton();
        RegistrarContas = new javax.swing.JPanel();
        kGDetalhesDaConta = new keeptoo.KGradientPanel();
        jtfCodigoConta = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        labelPagamento = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jtfDescricao = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jFValor = new javax.swing.JFormattedTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtfObservacao = new javax.swing.JTextPane();
        jLabel10 = new javax.swing.JLabel();
        jdData = new javax.swing.JFormattedTextField();
        jdVencimento = new javax.swing.JFormattedTextField();
        jdPagamento = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        jcbTipoConta = new componentes.UJComboBox();
        kGVincularFunc = new keeptoo.KGradientPanel();
        jChecKFuncionario = new javax.swing.JCheckBox();
        kGFuncionarioAgente = new keeptoo.KGradientPanel();
        tfSeguradora1 = new javax.swing.JLabel();
        cbFuncionario = new componentes.UJComboBox();
        jLabel2 = new javax.swing.JLabel();
        cbBancos = new componentes.UJComboBox();
        kGTipoConta = new keeptoo.KGradientPanel();
        jCheckRepasse = new javax.swing.JCheckBox();
        kGDadosDoProcesso = new keeptoo.KGradientPanel();
        jLSeguradoraProcesso = new javax.swing.JLabel();
        jLNumeroProcesso = new javax.swing.JLabel();
        jLNumeroSinistroProcesso = new javax.swing.JLabel();
        jLDataEntradaProcesso = new javax.swing.JLabel();
        jLDataProcesso = new javax.swing.JLabel();
        tfProcessoNumero = new javax.swing.JTextField();
        jlProcessoNaoEncontrado = new javax.swing.JLabel();
        jScrollPaneAnalista3 = new javax.swing.JScrollPane();
        jLSisnitroProcesso = new javax.swing.JTextArea();
        jScrollPaneAnalista2 = new javax.swing.JScrollPane();
        jLnomeSegProcesso = new javax.swing.JTextArea();
        kGradientPanel11 = new keeptoo.KGradientPanel();
        kGradientPanel1 = new keeptoo.KGradientPanel();
        btCancelar2 = new javax.swing.JButton();
        JbSAlvar = new javax.swing.JButton();

        kGRepasseAoAgente.setBackground(new java.awt.Color(228, 235, 241));
        kGRepasseAoAgente.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Repasse ao Agente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(51, 51, 51))); // NOI18N
        kGRepasseAoAgente.setForeground(new java.awt.Color(51, 51, 51));
        kGRepasseAoAgente.setkBorderRadius(0);
        kGRepasseAoAgente.setkEndColor(new java.awt.Color(234, 239, 243));
        kGRepasseAoAgente.setkGradientFocus(0);
        kGRepasseAoAgente.setkStartColor(new java.awt.Color(255, 255, 255));
        kGRepasseAoAgente.setMinimumSize(new java.awt.Dimension(1080, 110));
        kGRepasseAoAgente.setPreferredSize(new java.awt.Dimension(1080, 120));
        kGRepasseAoAgente.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLAgenteHonorarios.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLAgenteHonorarios.setForeground(new java.awt.Color(51, 51, 51));
        jLAgenteHonorarios.setText("Pgto. Honorário");
        kGRepasseAoAgente.add(jLAgenteHonorarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 100, 20));

        jfAgenteHonorario.setBackground(new java.awt.Color(227, 236, 245));
        jfAgenteHonorario.setForeground(new java.awt.Color(51, 51, 51));
        jfAgenteHonorario.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getCurrencyInstance())));
        jfAgenteHonorario.setText("R$ 0,00");
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
        kGRepasseAoAgente.add(jfAgenteHonorario, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 130, 28));

        jLAgenteSeguradoraKm.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLAgenteSeguradoraKm.setForeground(new java.awt.Color(51, 51, 51));
        jLAgenteSeguradoraKm.setText("Pgto. KM");
        kGRepasseAoAgente.add(jLAgenteSeguradoraKm, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 30, 80, 20));

        jLAgenteRepasseTotal.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLAgenteRepasseTotal.setForeground(new java.awt.Color(51, 51, 51));
        jLAgenteRepasseTotal.setText("Pgto. Total");
        kGRepasseAoAgente.add(jLAgenteRepasseTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 30, 80, 20));

        jfAgenteTotalRepasse.setEditable(false);
        jfAgenteTotalRepasse.setBackground(new java.awt.Color(153, 153, 153));
        jfAgenteTotalRepasse.setForeground(new java.awt.Color(255, 255, 255));
        jfAgenteTotalRepasse.setText("R$ 0,00");
        jfAgenteTotalRepasse.setMinimumSize(new java.awt.Dimension(80, 35));
        jfAgenteTotalRepasse.setPreferredSize(new java.awt.Dimension(80, 35));
        kGRepasseAoAgente.add(jfAgenteTotalRepasse, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 50, 130, 35));

        jLAgenteKMpercorrido.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLAgenteKMpercorrido.setForeground(new java.awt.Color(51, 51, 51));
        jLAgenteKMpercorrido.setText("KM Percorrido");
        kGRepasseAoAgente.add(jLAgenteKMpercorrido, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 30, 100, 20));

        jfAgenteKmPercorrido.setBackground(new java.awt.Color(227, 236, 245));
        jfAgenteKmPercorrido.setForeground(new java.awt.Color(51, 51, 51));
        jfAgenteKmPercorrido.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jfAgenteKmPercorrido.setText("0");
        jfAgenteKmPercorrido.setToolTipText("");
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
        kGRepasseAoAgente.add(jfAgenteKmPercorrido, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 50, 100, 28));

        jFPagamentoTotalKM.setEditable(false);
        jFPagamentoTotalKM.setBackground(new java.awt.Color(153, 153, 153));
        jFPagamentoTotalKM.setForeground(new java.awt.Color(255, 255, 255));
        jFPagamentoTotalKM.setText("R$ 0,00");
        kGRepasseAoAgente.add(jFPagamentoTotalKM, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 50, 130, 35));

        jLAgenteTotalKm.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLAgenteTotalKm.setForeground(new java.awt.Color(51, 51, 51));
        jLAgenteTotalKm.setText("Pgto. Total Km");
        kGRepasseAoAgente.add(jLAgenteTotalKm, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 30, 100, 20));

        cbAgentePtgoPorKMSeguradoraProcessos.setEnabled(false);
        kGRepasseAoAgente.add(cbAgentePtgoPorKMSeguradoraProcessos, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 50, 120, 35));

        tfSeguradora.setText("Cod.");

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

        cbSeguradorasProcesso.setEnabled(false);

        cbSinistroProcesso.setEnabled(false);

        cbDataProcesso.setEnabled(false);

        jcbProcessos.setEnabled(false);
        jcbProcessos.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
                jcbProcessosPopupMenuCanceled(evt);
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jcbProcessosPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("CONTAS A RECEBER");
        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1080, 730));
        setMinimumSize(new java.awt.Dimension(1080, 730));
        setPreferredSize(new java.awt.Dimension(1080, 730));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        kGradientPanel14.setBackground(new java.awt.Color(255, 255, 255));
        kGradientPanel14.setkBorderRadius(0);
        kGradientPanel14.setkEndColor(new java.awt.Color(146, 171, 197));
        kGradientPanel14.setkGradientFocus(0);
        kGradientPanel14.setkStartColor(new java.awt.Color(146, 171, 197));
        kGradientPanel14.setMaximumSize(new java.awt.Dimension(1080, 720));
        kGradientPanel14.setName(""); // NOI18N
        kGradientPanel14.setPreferredSize(new java.awt.Dimension(1080, 40));

        KBContasReceber.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Fall.png"))); // NOI18N
        KBContasReceber.setText("CONTAS A RECEBER");
        KBContasReceber.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        KBContasReceber.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        KBContasReceber.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        KBContasReceber.setIconTextGap(10);
        KBContasReceber.setkBackGroundColor(new java.awt.Color(255, 255, 255));
        KBContasReceber.setkBorderRadius(0);
        KBContasReceber.setkEndColor(new java.awt.Color(197, 201, 206));
        KBContasReceber.setkForeGround(new java.awt.Color(102, 102, 102));
        KBContasReceber.setkHoverEndColor(new java.awt.Color(234, 239, 243));
        KBContasReceber.setkHoverForeGround(new java.awt.Color(102, 102, 102));
        KBContasReceber.setkHoverStartColor(new java.awt.Color(234, 239, 243));
        KBContasReceber.setkPressedColor(new java.awt.Color(153, 153, 153));
        KBContasReceber.setkSelectedColor(new java.awt.Color(255, 255, 255));
        KBContasReceber.setkStartColor(new java.awt.Color(197, 201, 206));
        KBContasReceber.setPreferredSize(new java.awt.Dimension(210, 45));
        KBContasReceber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KBContasReceberActionPerformed(evt);
            }
        });

        kBContasPagas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Down.png"))); // NOI18N
        kBContasPagas.setText("CONTAS RECEBIDAS");
        kBContasPagas.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        kBContasPagas.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        kBContasPagas.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        kBContasPagas.setIconTextGap(10);
        kBContasPagas.setkBackGroundColor(new java.awt.Color(255, 255, 255));
        kBContasPagas.setkBorderRadius(0);
        kBContasPagas.setkEndColor(new java.awt.Color(197, 201, 206));
        kBContasPagas.setkForeGround(new java.awt.Color(102, 102, 102));
        kBContasPagas.setkHoverEndColor(new java.awt.Color(234, 239, 243));
        kBContasPagas.setkHoverForeGround(new java.awt.Color(102, 102, 102));
        kBContasPagas.setkHoverStartColor(new java.awt.Color(234, 239, 243));
        kBContasPagas.setkPressedColor(new java.awt.Color(153, 153, 153));
        kBContasPagas.setkSelectedColor(new java.awt.Color(255, 255, 255));
        kBContasPagas.setkStartColor(new java.awt.Color(197, 201, 206));
        kBContasPagas.setPreferredSize(new java.awt.Dimension(240, 45));
        kBContasPagas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kBContasPagasActionPerformed(evt);
            }
        });

        kBRegistrarContas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Notes.png"))); // NOI18N
        kBRegistrarContas.setText("INFORMAÇÕES DA CONTA");
        kBRegistrarContas.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        kBRegistrarContas.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        kBRegistrarContas.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        kBRegistrarContas.setIconTextGap(10);
        kBRegistrarContas.setkBackGroundColor(new java.awt.Color(255, 255, 255));
        kBRegistrarContas.setkBorderRadius(0);
        kBRegistrarContas.setkEndColor(new java.awt.Color(197, 201, 206));
        kBRegistrarContas.setkForeGround(new java.awt.Color(102, 102, 102));
        kBRegistrarContas.setkHoverEndColor(new java.awt.Color(234, 239, 243));
        kBRegistrarContas.setkHoverForeGround(new java.awt.Color(102, 102, 102));
        kBRegistrarContas.setkHoverStartColor(new java.awt.Color(234, 239, 243));
        kBRegistrarContas.setkPressedColor(new java.awt.Color(153, 153, 153));
        kBRegistrarContas.setkSelectedColor(new java.awt.Color(255, 255, 255));
        kBRegistrarContas.setkStartColor(new java.awt.Color(197, 201, 206));
        kBRegistrarContas.setPreferredSize(new java.awt.Dimension(250, 45));
        kBRegistrarContas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kBRegistrarContasActionPerformed(evt);
            }
        });

        kButtonPendentes1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/refresh.png"))); // NOI18N
        kButtonPendentes1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        kButtonPendentes1.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        kButtonPendentes1.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        kButtonPendentes1.setIconTextGap(20);
        kButtonPendentes1.setkAllowGradient(false);
        kButtonPendentes1.setkAllowTab(false);
        kButtonPendentes1.setkBackGroundColor(new java.awt.Color(255, 255, 255));
        kButtonPendentes1.setkBorderRadius(0);
        kButtonPendentes1.setkEndColor(new java.awt.Color(234, 239, 243));
        kButtonPendentes1.setkForeGround(new java.awt.Color(51, 51, 51));
        kButtonPendentes1.setkHoverEndColor(new java.awt.Color(234, 239, 243));
        kButtonPendentes1.setkHoverForeGround(new java.awt.Color(51, 51, 51));
        kButtonPendentes1.setkHoverStartColor(new java.awt.Color(234, 239, 243));
        kButtonPendentes1.setkPressedColor(new java.awt.Color(234, 239, 243));
        kButtonPendentes1.setkSelectedColor(new java.awt.Color(197, 201, 206));
        kButtonPendentes1.setkStartColor(new java.awt.Color(255, 255, 255));
        kButtonPendentes1.setPreferredSize(new java.awt.Dimension(60, 40));
        kButtonPendentes1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButtonPendentes1ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout kGradientPanel14Layout = new org.jdesktop.layout.GroupLayout(kGradientPanel14);
        kGradientPanel14.setLayout(kGradientPanel14Layout);
        kGradientPanel14Layout.setHorizontalGroup(
            kGradientPanel14Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(kGradientPanel14Layout.createSequentialGroup()
                .add(10, 10, 10)
                .add(KBContasReceber, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(10, 10, 10)
                .add(kBContasPagas, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(10, 10, 10)
                .add(kBRegistrarContas, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 278, Short.MAX_VALUE)
                .add(kButtonPendentes1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        kGradientPanel14Layout.setVerticalGroup(
            kGradientPanel14Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(kGradientPanel14Layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(kGradientPanel14Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, kGradientPanel14Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(kBRegistrarContas, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 40, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(kBContasPagas, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 40, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(KBContasReceber, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 40, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, kButtonPendentes1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
        );

        getContentPane().add(kGradientPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 50));

        jTabbedPanelContasAReceber.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPanelContasAReceber.setMaximumSize(new java.awt.Dimension(1080, 720));
        jTabbedPanelContasAReceber.setMinimumSize(new java.awt.Dimension(1080, 720));
        jTabbedPanelContasAReceber.setPreferredSize(new java.awt.Dimension(1080, 720));

        ContasAReceber.setBackground(new java.awt.Color(255, 255, 255));
        ContasAReceber.setPreferredSize(new java.awt.Dimension(1080, 1080));

        kGradientPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "FILTRAR", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(51, 51, 51))); // NOI18N
        kGradientPanel6.setkBorderRadius(0);
        kGradientPanel6.setkEndColor(new java.awt.Color(234, 239, 243));
        kGradientPanel6.setkGradientFocus(0);
        kGradientPanel6.setkStartColor(new java.awt.Color(255, 255, 255));
        kGradientPanel6.setPreferredSize(new java.awt.Dimension(1080, 110));
        kGradientPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLabel19.setText("Funcionário:");
        kGradientPanel6.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        cbFuncionariosEfetuado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbFuncionariosEfetuado.setSelectedIndex(-1);
        cbFuncionariosEfetuado.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbFuncionariosEfetuadoPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                cbFuncionariosEfetuadoPopupMenuWillBecomeVisible(evt);
            }
        });
        kGradientPanel6.add(cbFuncionariosEfetuado, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 330, 30));

        btClearEfetuado.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btClearEfetuado.setForeground(new java.awt.Color(255, 255, 255));
        btClearEfetuado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Erase.png"))); // NOI18N
        btClearEfetuado.setPreferredSize(new java.awt.Dimension(70, 40));
        btClearEfetuado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btClearEfetuadoActionPerformed(evt);
            }
        });
        kGradientPanel6.add(btClearEfetuado, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 50, -1, -1));

        ContasAReceber.add(kGradientPanel6);

        kGradientPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "PENDENTES A RECEBER", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(51, 51, 51))); // NOI18N
        kGradientPanel7.setkBorderRadius(0);
        kGradientPanel7.setkEndColor(new java.awt.Color(240, 230, 214));
        kGradientPanel7.setkGradientFocus(0);
        kGradientPanel7.setkStartColor(new java.awt.Color(255, 255, 255));
        kGradientPanel7.setPreferredSize(new java.awt.Dimension(1080, 450));
        kGradientPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane7.setBackground(new java.awt.Color(247, 247, 247));
        jScrollPane7.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane7.setPreferredSize(new java.awt.Dimension(1220, 180));

        tbContasReceber.setAutoCreateRowSorter(true);
        tbContasReceber.setBackground(new java.awt.Color(247, 247, 247));
        tbContasReceber.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        tbContasReceber.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cod.", "Descrição", "Funcionário", "Data", "Vencimento", "Pagamento", "Valor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbContasReceber.setGridColor(new java.awt.Color(247, 247, 247));
        tbContasReceber.setMinimumSize(new java.awt.Dimension(610, 0));
        tbContasReceber.setRowHeight(20);
        tbContasReceber.setSelectionBackground(new java.awt.Color(0, 153, 0));
        tbContasReceber.getTableHeader().setReorderingAllowed(false);
        jScrollPane7.setViewportView(tbContasReceber);
        if (tbContasReceber.getColumnModel().getColumnCount() > 0) {
            tbContasReceber.getColumnModel().getColumn(0).setMinWidth(50);
            tbContasReceber.getColumnModel().getColumn(0).setPreferredWidth(50);
            tbContasReceber.getColumnModel().getColumn(0).setMaxWidth(50);
            tbContasReceber.getColumnModel().getColumn(1).setMinWidth(200);
            tbContasReceber.getColumnModel().getColumn(2).setMinWidth(200);
            tbContasReceber.getColumnModel().getColumn(3).setMinWidth(120);
            tbContasReceber.getColumnModel().getColumn(3).setPreferredWidth(120);
            tbContasReceber.getColumnModel().getColumn(3).setMaxWidth(120);
            tbContasReceber.getColumnModel().getColumn(4).setMinWidth(120);
            tbContasReceber.getColumnModel().getColumn(4).setPreferredWidth(120);
            tbContasReceber.getColumnModel().getColumn(4).setMaxWidth(120);
            tbContasReceber.getColumnModel().getColumn(6).setMinWidth(120);
            tbContasReceber.getColumnModel().getColumn(6).setPreferredWidth(120);
            tbContasReceber.getColumnModel().getColumn(6).setMaxWidth(120);
        }

        kGradientPanel7.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 1020, 380));

        ContasAReceber.add(kGradientPanel7);

        kGradientPanel5.setkBorderRadius(0);
        kGradientPanel5.setkEndColor(new java.awt.Color(255, 255, 255));
        kGradientPanel5.setkGradientFocus(0);
        kGradientPanel5.setkStartColor(new java.awt.Color(255, 255, 255));
        kGradientPanel5.setPreferredSize(new java.awt.Dimension(1080, 100));
        kGradientPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jbReceberConta.setBackground(new java.awt.Color(0, 153, 0));
        jbReceberConta.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jbReceberConta.setForeground(new java.awt.Color(255, 255, 255));
        jbReceberConta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Good-mark.png"))); // NOI18N
        jbReceberConta.setText("Receber conta");
        jbReceberConta.setPreferredSize(new java.awt.Dimension(200, 35));
        jbReceberConta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbReceberContaActionPerformed(evt);
            }
        });
        kGradientPanel5.add(jbReceberConta, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 20, -1, -1));

        btNovo.setBackground(new java.awt.Color(0, 153, 0));
        btNovo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btNovo.setForeground(new java.awt.Color(255, 255, 255));
        btNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Create.png"))); // NOI18N
        btNovo.setText("Novo");
        btNovo.setPreferredSize(new java.awt.Dimension(200, 40));
        btNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNovoActionPerformed(evt);
            }
        });
        kGradientPanel5.add(btNovo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, -1, -1));

        btAlterar.setBackground(new java.awt.Color(0, 102, 255));
        btAlterar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btAlterar.setForeground(new java.awt.Color(255, 255, 255));
        btAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/editar.png"))); // NOI18N
        btAlterar.setText("Alterar");
        btAlterar.setPreferredSize(new java.awt.Dimension(150, 30));
        btAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAlterarActionPerformed(evt);
            }
        });
        kGradientPanel5.add(btAlterar, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 20, -1, -1));

        jbExcluirReceber.setBackground(new java.awt.Color(255, 0, 0));
        jbExcluirReceber.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbExcluirReceber.setForeground(new java.awt.Color(255, 255, 255));
        jbExcluirReceber.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/No-entry.png"))); // NOI18N
        jbExcluirReceber.setText("Excluir");
        jbExcluirReceber.setPreferredSize(new java.awt.Dimension(150, 30));
        jbExcluirReceber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbExcluirReceberActionPerformed(evt);
            }
        });
        kGradientPanel5.add(jbExcluirReceber, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 20, -1, -1));

        ContasAReceber.add(kGradientPanel5);

        jTabbedPanelContasAReceber.addTab("CONTAS A RECEBER", ContasAReceber);

        ContasRecebidas.setBackground(new java.awt.Color(255, 255, 255));

        kGradientPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "FILTRAR", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(51, 51, 51))); // NOI18N
        kGradientPanel10.setkBorderRadius(0);
        kGradientPanel10.setkEndColor(new java.awt.Color(234, 239, 243));
        kGradientPanel10.setkGradientFocus(0);
        kGradientPanel10.setkStartColor(new java.awt.Color(255, 255, 255));
        kGradientPanel10.setPreferredSize(new java.awt.Dimension(1080, 110));
        kGradientPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setText("Período:");
        kGradientPanel10.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        jdcDataInicial.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jdcDataInicialFocusLost(evt);
            }
        });
        kGradientPanel10.add(jdcDataInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 150, 28));
        kGradientPanel10.add(jdcDataFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 50, 150, 28));

        btDefinirData.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btDefinirData.setForeground(new java.awt.Color(51, 51, 51));
        btDefinirData.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/page_find.png"))); // NOI18N
        btDefinirData.setText("APLICAR");
        btDefinirData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDefinirDataActionPerformed(evt);
            }
        });
        kGradientPanel10.add(btDefinirData, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 50, 130, 35));

        cbFuncionarios.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbFuncionarios.setSelectedIndex(-1);
        cbFuncionarios.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbFuncionariosPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        kGradientPanel10.add(cbFuncionarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 50, 370, 30));

        jLabel21.setText("Funcionário:");
        kGradientPanel10.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 30, 90, -1));

        btClear.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btClear.setForeground(new java.awt.Color(255, 255, 255));
        btClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Erase.png"))); // NOI18N
        btClear.setPreferredSize(new java.awt.Dimension(110, 40));
        btClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btClearActionPerformed(evt);
            }
        });
        kGradientPanel10.add(btClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 50, 70, -1));

        ContasRecebidas.add(kGradientPanel10);

        kGradientPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "CONTAS RECEBIDAS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(51, 51, 51))); // NOI18N
        kGradientPanel8.setkBorderRadius(0);
        kGradientPanel8.setkEndColor(new java.awt.Color(199, 223, 242));
        kGradientPanel8.setkGradientFocus(0);
        kGradientPanel8.setkStartColor(new java.awt.Color(255, 255, 255));
        kGradientPanel8.setPreferredSize(new java.awt.Dimension(1080, 450));
        kGradientPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane8.setBackground(new java.awt.Color(247, 247, 247));
        jScrollPane8.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane8.setPreferredSize(new java.awt.Dimension(1220, 180));

        tbContasRecebidas.setAutoCreateRowSorter(true);
        tbContasRecebidas.setBackground(new java.awt.Color(247, 247, 247));
        tbContasRecebidas.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        tbContasRecebidas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cod.", "Descrição", "Funcionário", "Data", "Vencimento", "Pagamento", "Valor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbContasRecebidas.setGridColor(new java.awt.Color(247, 247, 247));
        tbContasRecebidas.setMinimumSize(new java.awt.Dimension(610, 0));
        tbContasRecebidas.setRowHeight(20);
        tbContasRecebidas.setSelectionBackground(new java.awt.Color(0, 153, 0));
        tbContasRecebidas.getTableHeader().setReorderingAllowed(false);
        jScrollPane8.setViewportView(tbContasRecebidas);
        if (tbContasRecebidas.getColumnModel().getColumnCount() > 0) {
            tbContasRecebidas.getColumnModel().getColumn(0).setMinWidth(50);
            tbContasRecebidas.getColumnModel().getColumn(0).setPreferredWidth(50);
            tbContasRecebidas.getColumnModel().getColumn(0).setMaxWidth(50);
            tbContasRecebidas.getColumnModel().getColumn(1).setMinWidth(200);
            tbContasRecebidas.getColumnModel().getColumn(2).setMinWidth(200);
            tbContasRecebidas.getColumnModel().getColumn(3).setMinWidth(120);
            tbContasRecebidas.getColumnModel().getColumn(3).setPreferredWidth(120);
            tbContasRecebidas.getColumnModel().getColumn(3).setMaxWidth(120);
            tbContasRecebidas.getColumnModel().getColumn(5).setMinWidth(120);
            tbContasRecebidas.getColumnModel().getColumn(5).setPreferredWidth(120);
            tbContasRecebidas.getColumnModel().getColumn(5).setMaxWidth(120);
            tbContasRecebidas.getColumnModel().getColumn(6).setMinWidth(120);
            tbContasRecebidas.getColumnModel().getColumn(6).setPreferredWidth(120);
            tbContasRecebidas.getColumnModel().getColumn(6).setMaxWidth(120);
        }

        kGradientPanel8.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 1020, 380));

        ContasRecebidas.add(kGradientPanel8);

        kGradientPanel9.setBackground(new java.awt.Color(255, 255, 255));
        kGradientPanel9.setkBorderRadius(0);
        kGradientPanel9.setkEndColor(new java.awt.Color(255, 255, 255));
        kGradientPanel9.setkGradientFocus(0);
        kGradientPanel9.setkStartColor(new java.awt.Color(255, 255, 255));
        kGradientPanel9.setPreferredSize(new java.awt.Dimension(1080, 100));
        kGradientPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpRevorgarConta.setBackground(new java.awt.Color(255, 0, 0));
        jpRevorgarConta.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jpRevorgarConta.setForeground(new java.awt.Color(255, 255, 255));
        jpRevorgarConta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Bad mark.png"))); // NOI18N
        jpRevorgarConta.setText("Revogar recebimento");
        jpRevorgarConta.setPreferredSize(new java.awt.Dimension(200, 35));
        jpRevorgarConta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jpRevorgarContaActionPerformed(evt);
            }
        });
        kGradientPanel9.add(jpRevorgarConta, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 20, -1, -1));

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
        kGradientPanel9.add(jbVisualizarConta, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, -1, -1));

        ContasRecebidas.add(kGradientPanel9);

        jTabbedPanelContasAReceber.addTab("CONTAS RECEBIDAS", ContasRecebidas);

        RegistrarContas.setBackground(new java.awt.Color(255, 255, 255));

        kGDetalhesDaConta.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "INFORMAÇÕES DA CONTA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(51, 51, 51))); // NOI18N
        kGDetalhesDaConta.setForeground(new java.awt.Color(51, 51, 51));
        kGDetalhesDaConta.setkBorderRadius(0);
        kGDetalhesDaConta.setkEndColor(new java.awt.Color(234, 239, 243));
        kGDetalhesDaConta.setkGradientFocus(0);
        kGDetalhesDaConta.setkStartColor(new java.awt.Color(255, 255, 255));
        kGDetalhesDaConta.setPreferredSize(new java.awt.Dimension(1080, 190));
        kGDetalhesDaConta.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtfCodigoConta.setEditable(false);
        jtfCodigoConta.setBackground(new java.awt.Color(204, 255, 204));
        jtfCodigoConta.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        kGDetalhesDaConta.add(jtfCodigoConta, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 100, 28));

        jLabel5.setText("Código da conta:");
        kGDetalhesDaConta.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel3.setText("Valor da conta:");
        kGDetalhesDaConta.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, -1));

        labelPagamento.setText("Pagamento (Opcional):");
        kGDetalhesDaConta.add(labelPagamento, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 30, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel7.setText("Vencimento:");
        kGDetalhesDaConta.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 30, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel6.setText("Data de Registro:");
        kGDetalhesDaConta.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 30, 130, -1));

        jtfDescricao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtfDescricaoKeyPressed(evt);
            }
        });
        kGDetalhesDaConta.add(jtfDescricao, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 50, 400, 28));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel4.setText("Descrição da conta:");
        kGDetalhesDaConta.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 30, -1, -1));

        jFValor.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getCurrencyInstance())));
        jFValor.setText("R$ 0,00");
        jFValor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jFValorFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jFValorFocusLost(evt);
            }
        });
        jFValor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFValorKeyPressed(evt);
            }
        });
        kGDetalhesDaConta.add(jFValor, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 130, 28));

        jtfObservacao.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfObservacaoFocusLost(evt);
            }
        });
        jScrollPane1.setViewportView(jtfObservacao);

        kGDetalhesDaConta.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 120, 530, 30));

        jLabel10.setText("Observação:");
        kGDetalhesDaConta.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 100, -1, -1));

        try {
            jdData.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jdData.setPreferredSize(new java.awt.Dimension(130, 28));
        jdData.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jdDataFocusLost(evt);
            }
        });
        jdData.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jdDataKeyPressed(evt);
            }
        });
        kGDetalhesDaConta.add(jdData, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 50, -1, -1));

        try {
            jdVencimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jdVencimento.setPreferredSize(new java.awt.Dimension(130, 28));
        jdVencimento.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jdVencimentoFocusLost(evt);
            }
        });
        jdVencimento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jdVencimentoKeyPressed(evt);
            }
        });
        kGDetalhesDaConta.add(jdVencimento, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 50, -1, -1));

        try {
            jdPagamento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jdPagamento.setPreferredSize(new java.awt.Dimension(130, 28));
        jdPagamento.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jdPagamentoFocusLost(evt);
            }
        });
        jdPagamento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jdPagamentoKeyPressed(evt);
            }
        });
        kGDetalhesDaConta.add(jdPagamento, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 50, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Tipo de Conta");
        kGDetalhesDaConta.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 100, -1, -1));

        jcbTipoConta.setEditable(true);
        kGDetalhesDaConta.add(jcbTipoConta, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 120, 260, 28));

        kGVincularFunc.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "SELECIONAR FUNCIONÁRIO", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(51, 51, 51)))); // NOI18N
        kGVincularFunc.setkBorderRadius(0);
        kGVincularFunc.setkEndColor(new java.awt.Color(255, 255, 255));
        kGVincularFunc.setkGradientFocus(0);
        kGVincularFunc.setkStartColor(new java.awt.Color(255, 255, 255));
        kGVincularFunc.setPreferredSize(new java.awt.Dimension(1080, 40));
        kGVincularFunc.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jChecKFuncionario.setBackground(new java.awt.Color(255, 255, 255));
        jChecKFuncionario.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jChecKFuncionario.setForeground(new java.awt.Color(51, 51, 51));
        jChecKFuncionario.setText("VINCULAR FUNCIONÁRIO");
        jChecKFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jChecKFuncionarioActionPerformed(evt);
            }
        });
        kGVincularFunc.add(jChecKFuncionario, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, -1, -1));

        kGFuncionarioAgente.setkBorderRadius(0);
        kGFuncionarioAgente.setkEndColor(new java.awt.Color(234, 239, 243));
        kGFuncionarioAgente.setkGradientFocus(0);
        kGFuncionarioAgente.setkStartColor(new java.awt.Color(255, 255, 255));
        kGFuncionarioAgente.setPreferredSize(new java.awt.Dimension(1080, 115));
        kGFuncionarioAgente.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tfSeguradora1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tfSeguradora1.setText("Funcionário");
        kGFuncionarioAgente.add(tfSeguradora1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 250, -1));

        cbFuncionario.setEnabled(false);
        cbFuncionario.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbFuncionarioPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                cbFuncionarioPopupMenuWillBecomeVisible(evt);
            }
        });
        kGFuncionarioAgente.add(cbFuncionario, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 690, 28));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Banco do Depósito");
        kGFuncionarioAgente.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 30, -1, -1));

        cbBancos.setEnabled(false);
        kGFuncionarioAgente.add(cbBancos, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 50, 260, 28));

        kGTipoConta.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "SELECIONAR PROCESSO", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(51, 51, 51)))); // NOI18N
        kGTipoConta.setkBorderRadius(0);
        kGTipoConta.setkEndColor(new java.awt.Color(255, 255, 255));
        kGTipoConta.setkGradientFocus(0);
        kGTipoConta.setkStartColor(new java.awt.Color(255, 255, 255));
        kGTipoConta.setPreferredSize(new java.awt.Dimension(1080, 40));
        kGTipoConta.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jCheckRepasse.setBackground(new java.awt.Color(255, 255, 255));
        jCheckRepasse.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jCheckRepasse.setForeground(new java.awt.Color(51, 51, 51));
        jCheckRepasse.setText("VINCULAR A PROCESSO");
        jCheckRepasse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckRepasseActionPerformed(evt);
            }
        });
        kGTipoConta.add(jCheckRepasse, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, -1, -1));

        kGDadosDoProcesso.setkBorderRadius(0);
        kGDadosDoProcesso.setkEndColor(new java.awt.Color(234, 239, 243));
        kGDadosDoProcesso.setkGradientFocus(0);
        kGDadosDoProcesso.setkStartColor(new java.awt.Color(255, 255, 255));
        kGDadosDoProcesso.setPreferredSize(new java.awt.Dimension(1080, 125));
        kGDadosDoProcesso.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLSeguradoraProcesso.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLSeguradoraProcesso.setText("Seguradora do Processo");
        kGDadosDoProcesso.add(jLSeguradoraProcesso, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 40, 200, -1));

        jLNumeroProcesso.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLNumeroProcesso.setText("Processo");
        kGDadosDoProcesso.add(jLNumeroProcesso, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, -1, -1));

        jLNumeroSinistroProcesso.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLNumeroSinistroProcesso.setText("Número do Sinistro");
        kGDadosDoProcesso.add(jLNumeroSinistroProcesso, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 40, 140, -1));

        jLDataEntradaProcesso.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLDataEntradaProcesso.setText("Data Entrada");
        kGDadosDoProcesso.add(jLDataEntradaProcesso, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 40, 110, -1));

        jLDataProcesso.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLDataProcesso.setForeground(new java.awt.Color(102, 0, 0));
        jLDataProcesso.setText("Data");
        kGDadosDoProcesso.add(jLDataProcesso, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 60, 110, 30));

        tfProcessoNumero.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfProcessoNumeroFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfProcessoNumeroFocusLost(evt);
            }
        });
        tfProcessoNumero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfProcessoNumeroKeyPressed(evt);
            }
        });
        kGDadosDoProcesso.add(tfProcessoNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 110, 30));

        jlProcessoNaoEncontrado.setForeground(new java.awt.Color(204, 0, 0));
        jlProcessoNaoEncontrado.setText("nenhum processo encontrado.");
        kGDadosDoProcesso.add(jlProcessoNaoEncontrado, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 220, 30));

        jScrollPaneAnalista3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPaneAnalista3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPaneAnalista3.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jScrollPaneAnalista3.setPreferredSize(new java.awt.Dimension(675, 25));

        jLSisnitroProcesso.setEditable(false);
        jLSisnitroProcesso.setBackground(new java.awt.Color(242, 245, 247));
        jLSisnitroProcesso.setColumns(20);
        jLSisnitroProcesso.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLSisnitroProcesso.setRows(5);
        jLSisnitroProcesso.setText("\n");
        jScrollPaneAnalista3.setViewportView(jLSisnitroProcesso);

        kGDadosDoProcesso.add(jScrollPaneAnalista3, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 60, 170, 30));

        jScrollPaneAnalista2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPaneAnalista2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPaneAnalista2.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jScrollPaneAnalista2.setPreferredSize(new java.awt.Dimension(675, 25));

        jLnomeSegProcesso.setEditable(false);
        jLnomeSegProcesso.setBackground(new java.awt.Color(242, 245, 247));
        jLnomeSegProcesso.setColumns(20);
        jLnomeSegProcesso.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLnomeSegProcesso.setRows(5);
        jLnomeSegProcesso.setText("\n");
        jScrollPaneAnalista2.setViewportView(jLnomeSegProcesso);

        kGDadosDoProcesso.add(jScrollPaneAnalista2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 60, 520, 30));

        kGradientPanel11.setkBorderRadius(0);
        kGradientPanel11.setkEndColor(new java.awt.Color(255, 255, 255));
        kGradientPanel11.setkGradientFocus(0);
        kGradientPanel11.setkStartColor(new java.awt.Color(234, 239, 243));
        kGradientPanel11.setPreferredSize(new java.awt.Dimension(1080, 40));
        kGradientPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        kGradientPanel1.setkBorderRadius(0);
        kGradientPanel1.setkEndColor(new java.awt.Color(255, 255, 255));
        kGradientPanel1.setkGradientFocus(0);
        kGradientPanel1.setkStartColor(new java.awt.Color(255, 255, 255));
        kGradientPanel1.setPreferredSize(new java.awt.Dimension(1080, 100));
        kGradientPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btCancelar2.setBackground(new java.awt.Color(255, 102, 0));
        btCancelar2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btCancelar2.setForeground(new java.awt.Color(255, 255, 255));
        btCancelar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Close.png"))); // NOI18N
        btCancelar2.setText("Cancelar");
        btCancelar2.setPreferredSize(new java.awt.Dimension(150, 35));
        btCancelar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelar2ActionPerformed(evt);
            }
        });
        kGradientPanel1.add(btCancelar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, -1));

        JbSAlvar.setBackground(new java.awt.Color(0, 153, 0));
        JbSAlvar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        JbSAlvar.setForeground(new java.awt.Color(255, 255, 255));
        JbSAlvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/save.png"))); // NOI18N
        JbSAlvar.setText("Salvar");
        JbSAlvar.setPreferredSize(new java.awt.Dimension(150, 35));
        JbSAlvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JbSAlvarActionPerformed(evt);
            }
        });
        kGradientPanel1.add(JbSAlvar, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 20, -1, -1));

        org.jdesktop.layout.GroupLayout RegistrarContasLayout = new org.jdesktop.layout.GroupLayout(RegistrarContas);
        RegistrarContas.setLayout(RegistrarContasLayout);
        RegistrarContasLayout.setHorizontalGroup(
            RegistrarContasLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(RegistrarContasLayout.createSequentialGroup()
                .add(RegistrarContasLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(kGDetalhesDaConta, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(kGVincularFunc, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(kGFuncionarioAgente, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(kGTipoConta, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(kGDadosDoProcesso, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(kGradientPanel11, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(kGradientPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(0, 0, 0))
        );
        RegistrarContasLayout.setVerticalGroup(
            RegistrarContasLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(RegistrarContasLayout.createSequentialGroup()
                .add(5, 5, 5)
                .add(kGDetalhesDaConta, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(5, 5, 5)
                .add(kGVincularFunc, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(5, 5, 5)
                .add(kGFuncionarioAgente, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(5, 5, 5)
                .add(kGTipoConta, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(5, 5, 5)
                .add(kGDadosDoProcesso, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(0, 0, 0)
                .add(kGradientPanel11, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(0, 0, 0)
                .add(kGradientPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPanelContasAReceber.addTab("REGISTRAR CONTA", RegistrarContas);

        getContentPane().add(jTabbedPanelContasAReceber, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 18, -1, 720));
        jTabbedPanelContasAReceber.getAccessibleContext().setAccessibleName("CONTAS A RECEBER");

        getAccessibleContext().setAccessibleDescription("");
        getAccessibleContext().setAccessibleParent(this);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jbExcluirReceberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbExcluirReceberActionPerformed
        this.excluirConta();
    }//GEN-LAST:event_jbExcluirReceberActionPerformed

    private void btAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAlterarActionPerformed
        try {
            int linha = tbContasReceber.getSelectedRow();
            int codigo = (int) tbContasReceber.getValueAt(linha, 0);
            //this.limparDados();
            this.habilitaDesabilitarCampos(true);
            tipoCadastro = "alteracao";
            //seta o código para alteração
            recuperarConta(codigo);
            //avançar para aba 3
            jTabbedPanelContasAReceber.setSelectedIndex(jTabbedPanelContasAReceber.getSelectedIndex() + 2);
            panelTabbedKGVerficiar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Você precisa selecionar uma conta para alterar!", "ATENÇÂO", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btAlterarActionPerformed

    private void btNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNovoActionPerformed
        // TODO add your handling code here:
        this.limparDados();
        this.habilitaDesabilitarCampos(true);
        Datas bl = new Datas();
        Date d = new Date();
        tipoCadastro = "cadastro";
        this.jtfCodigoConta.setText("Nova Conta");
        try {
            this.jdData.setText(new Mascaras().DataRecuperasql(String.valueOf(bl.converterDataParaDateUS(d))));
        } catch (Exception ex) {
            Logger.getLogger(ViewContasReceber.class.getName()).log(Level.SEVERE, null, ex);
        }
        //avançar para aba 3
        jTabbedPanelContasAReceber.setSelectedIndex(jTabbedPanelContasAReceber.getSelectedIndex() + 2);
        panelTabbedKGVerficiar();
    }//GEN-LAST:event_btNovoActionPerformed

    private void btCancelar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelar2ActionPerformed
        // TODO add your handling code here:
        this.limparDados();
        this.habilitaDesabilitarCampos(false);
        jTabbedPanelContasAReceber.setSelectedIndex(jTabbedPanelContasAReceber.getSelectedIndex() - 2);
        panelTabbedKGVerficiar();
    }//GEN-LAST:event_btCancelar2ActionPerformed

    private void JbSAlvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JbSAlvarActionPerformed
        if (jtfDescricao.getText().equals("") || jFValor.getText().equals("") || "  /  /    ".equals(jdData.getText()) || "  /  /    ".equals(jdVencimento.getText())) {
            JOptionPane.showMessageDialog(this, "Você deve preencher todos os campos!", "ATENÇÂO", JOptionPane.WARNING_MESSAGE);
        } else if ((this.cbCodFuncionario.getSelectedItem() == null) && (this.jChecKFuncionario.isSelected())) {
            JOptionPane.showMessageDialog(this, "VINCULO DE FUNCIONÁRIO ESTÁ HABILITADO, \n SELECIONE O FUNCIONÁRIO OU DESMARQUE A OPÇÃO!", "ATENÇÂO", JOptionPane.WARNING_MESSAGE);
        } else if ((this.cbBancos.getSelectedObject() == null) && (this.jChecKFuncionario.isSelected())) {
            JOptionPane.showMessageDialog(this, "VINCULO DE FUNCIONÁRIO ESTÁ HABILITADO, \n SELECIONE O BANCO OU DESMARQUE A OPÇÃO!", "ATENÇÂO", JOptionPane.WARNING_MESSAGE);
        } else if ((this.jcbTipoConta.getSelectedObject() == null) && (this.jChecKFuncionario.isSelected())) {
            JOptionPane.showMessageDialog(this, "VINCULO DE FUNCIONÁRIO ESTÁ HABILITADO, \n SELECIONE O TIPO DE CONTA OU DESMARQUE A OPÇÃO!", "ATENÇÂO", JOptionPane.WARNING_MESSAGE);
        } else if ((this.jcbProcessos.getSelectedObject() == null) && (this.jCheckRepasse.isSelected())) {
            JOptionPane.showMessageDialog(this, "VINCULO DE PROCESSO ESTÁ HABILITADO, \n SELECIONE O PROCESSO OU DESMARQUE A OPÇÃO!", "ATENÇÂO", JOptionPane.WARNING_MESSAGE);
        } else {
            if (tipoCadastro.equals("cadastro")) {
                try {
                    this.salvar();
                } catch (Exception ex) {
                    System.out.println(ex);
                    Logger.getLogger(ViewContasReceber.class.getName()).log(Level.SEVERE, null, ex);
                }
                jTabbedPanelContasAReceber.setSelectedIndex(jTabbedPanelContasAReceber.getSelectedIndex() - 2);
            } else {
                try {
                    this.alterar();
                } catch (Exception ex) {
                    System.out.println(ex);
                    Logger.getLogger(ViewContasReceber.class.getName()).log(Level.SEVERE, null, ex);
                }
                jTabbedPanelContasAReceber.setSelectedIndex(jTabbedPanelContasAReceber.getSelectedIndex() - 2);
            }
        }
        panelTabbedKGVerficiar();
    }//GEN-LAST:event_JbSAlvarActionPerformed

    private void jbReceberContaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbReceberContaActionPerformed
        modelContaReceber = new ModelContaReceber();
        try {
            int linha = tbContasReceber.getSelectedRow();
            int codigo = (int) tbContasReceber.getValueAt(linha, 0);
            Datas bl = new Datas();

            modelContaReceber.setCodigo(codigo);
            modelContaReceber.setSituacao(1);
            String Verifica = (String) tbContasReceber.getValueAt(linha, 5);
            if ("".equals(Verifica) || "  /  /    ".equals(Verifica) || "null".equals(Verifica) || Verifica == null) {
                modelContaReceber.setPagamento(String.valueOf(bl.converterDataParaDateUS(new java.util.Date(System.currentTimeMillis()))));
            } else {
                modelContaReceber.setPagamento(new Mascaras().DataSalvanosql(Verifica));
            }
            //pegunta
            int opcao = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja"
                    + " receber a conta nº:\n" + "\n " + codigo + "?", "Atenção", JOptionPane.YES_NO_OPTION);
            //se sim paga, se não não faz nada
            if (opcao == JOptionPane.OK_OPTION) {
                if (controllerContaReceber.receberContaReceberController(modelContaReceber)) {
                    JOptionPane.showMessageDialog(this, "Conta Recebida!");
                    this.carregarContasReceber();
                    this.carregarContasPagas();
                }
            }

            jTabbedPanelContasAReceber.setSelectedIndex(jTabbedPanelContasAReceber.getSelectedIndex() + 1);
            panelTabbedKGVerficiar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Você deve selecionar uma conta!", "ATENÇÂO", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jbReceberContaActionPerformed

    private void jpRevorgarContaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jpRevorgarContaActionPerformed
        modelContaReceber = new ModelContaReceber();
        try {
            int linha = tbContasRecebidas.getSelectedRow();
            int codigo = (int) tbContasRecebidas.getValueAt(linha, 0);
            Datas bl = new Datas();

            modelContaReceber.setCodigo(codigo);
            modelContaReceber.setSituacao(0);

            int opcao = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja"
                    + " revogar o recebimento da conta nº:\n" + "\n " + codigo + "?", "Atenção", JOptionPane.YES_NO_OPTION);
            //se sim revoga, se não não faz nada
            if (opcao == JOptionPane.OK_OPTION) {
                if (controllerContaReceber.receberContaReceberController(modelContaReceber)) {
                    JOptionPane.showMessageDialog(this, "Conta revogada!");
                    this.carregarContasReceber();
                    this.carregarContasPagas();
                }
            }

            jTabbedPanelContasAReceber.setSelectedIndex(jTabbedPanelContasAReceber.getSelectedIndex() - 1);
            panelTabbedKGVerficiar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Você deve selecionar uma conta!", "ATENÇÂO", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jpRevorgarContaActionPerformed

    private void cbCodFuncionarioPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbCodFuncionarioPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
        if (this.cbCodFuncionario.isPopupVisible()) {
            modelFuncionario = controllerFuncionario.getFuncionarioController(Integer.parseInt(cbCodFuncionario.getSelectedItem().toString()));
            //recupera o nome
            this.cbFuncionario.setSelectedItem(String.valueOf(modelFuncionario.getNome()));
        }
    }//GEN-LAST:event_cbCodFuncionarioPopupMenuWillBecomeInvisible

    private void cbFuncionarioPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbFuncionarioPopupMenuWillBecomeInvisible
        if (this.cbFuncionario.isPopupVisible() && (cbFuncionario.getSelectedObject() != null)) {
            modelFuncionario = controllerFuncionario.getFuncionarioController(cbFuncionario.getSelectedItem().toString());
            //recupera o código
            this.cbCodFuncionario.setSelectedItem(modelFuncionario.getCodigo());
        }
    }//GEN-LAST:event_cbFuncionarioPopupMenuWillBecomeInvisible

    private void jcbProcessosPopupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jcbProcessosPopupMenuCanceled
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbProcessosPopupMenuCanceled

    private void jcbProcessosPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jcbProcessosPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
        if (this.jcbProcessos.isPopupVisible() || !(this.jcbProcessos.getSelectedObject() == null) || !"".equals(this.jcbProcessos.getSelectedObject().toString()) || (Integer.parseInt(this.jcbProcessos.getSelectedObject().toString())) > 0) {

            modelOp = controllerOp.getOpController(Integer.parseInt(jcbProcessos.getSelectedItem().toString()));
            this.cbSeguradorasProcesso.setSelectedItem(controllerSeguradora.getSeguradoraController(modelOp.getSeguradoras()).getNome());
            this.cbAgentePtgoPorKMSeguradoraProcessos.setSelectedItem(new Moeda().valorStringTODoubleAtt(String.valueOf(controllerSeguradora.getSeguradoraController(modelOp.getSeguradoras()).getSeg_km())));
            this.cbSinistroProcesso.setSelectedItem(String.valueOf(modelOp.getNumeroSinistro()));
            this.cbDataProcesso.setSelectedItem(String.valueOf(modelOp.getDataEntrada()));
            this.jLnomeSegProcesso.setText(this.cbSeguradorasProcesso.getSelectedObject().toString());
            this.jLSisnitroProcesso.setText(this.cbSinistroProcesso.getSelectedObject().toString());
            this.jLDataProcesso.setText(new Mascaras().DataRecuperasql(this.cbDataProcesso.getSelectedObject().toString()));
        } else {
            jcbProcessos.setSelectedIndex(-1);
            this.cbSeguradorasProcesso.setSelectedIndex(-1);
            this.cbSinistroProcesso.setSelectedIndex(-1);
            this.cbDataProcesso.setSelectedIndex(-1);
        }

    }//GEN-LAST:event_jcbProcessosPopupMenuWillBecomeInvisible

    private void jFValorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFValorFocusLost
        // TODO add your handling code here:
        if ("".equals(jFValor.getText()) || "R$ 0,00".equals(jFValor.getText()) || (Float.parseFloat(new Moeda().FommatoStringoSomarMil(jFValor.getText()))) < 0) {
            this.jFValor.setText("R$ 0,00");
        }
        this.jFValor.setText(new Moeda().valorStringTODoubleAtt(jFValor.getText()));
    }//GEN-LAST:event_jFValorFocusLost

    private void jfAgenteHonorarioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jfAgenteHonorarioFocusGained
        // LIMPAR AO CLICAR QUANDO ESTIVER ZERADO
        if ("R$ 0,00".equals(jfAgenteHonorario.getText())) {
            jfAgenteHonorario.setText("");
        }
    }//GEN-LAST:event_jfAgenteHonorarioFocusGained

    private void jfAgenteHonorarioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jfAgenteHonorarioFocusLost
        //Valor pago ao agente referente aos honorarios
        try {
            this.jfAgenteHonorario.setText(new Moeda().valorStringTODoubleAtt(jfAgenteHonorario.getText()));
        } catch (NumberFormatException e) {
            jfAgenteHonorario.setText("R$ 0,00");
        }

    }//GEN-LAST:event_jfAgenteHonorarioFocusLost

    private void jfAgenteKmPercorridoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jfAgenteKmPercorridoFocusGained
        // TODO add your handling code here:
        if ("0".equals(jfAgenteKmPercorrido.getText()) || (Integer.parseInt(jfAgenteKmPercorrido.getText()) <= 0)) {
            jfAgenteKmPercorrido.setText("");
        }
    }//GEN-LAST:event_jfAgenteKmPercorridoFocusGained

    private void jfAgenteKmPercorridoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jfAgenteKmPercorridoFocusLost
        // KILOMETRO PERCORRIDO
        try {
            if ("".equals(jfAgenteKmPercorrido.getText())) {
                this.jfAgenteKmPercorrido.setText("0");
            }
            this.jfAgenteKmPercorrido.setText(jfAgenteKmPercorrido.getText());
        } catch (NumberFormatException e) {
            this.jfAgenteKmPercorrido.setText("0");
        }
    }//GEN-LAST:event_jfAgenteKmPercorridoFocusLost

    private void jFValorFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFValorFocusGained
        // TODO add your handling code here:
        if ("R$ 0,00".equals(jFValor.getText())) {
            jFValor.setText("");
        }
    }//GEN-LAST:event_jFValorFocusGained

    private void jCheckRepasseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckRepasseActionPerformed
        // TODO add your handling code here:
        verificarTipoConta();
    }//GEN-LAST:event_jCheckRepasseActionPerformed

    private void jtfObservacaoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfObservacaoFocusLost
        // TODO add your handling code here:
        if ("".equals(jtfObservacao.getText())) {
            this.jtfObservacao.setText("Nenhuma observação");
        }
    }//GEN-LAST:event_jtfObservacaoFocusLost

    private void jbVisualizarContaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbVisualizarContaActionPerformed

        if (tbContasRecebidas.getRowCount() > 0) {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivos de excel", "xls");
            chooser.setFileFilter(filter);
            chooser.setDialogTitle("Salvar Arquivo");
            chooser.setAcceptAllFileFilterUsed(false);
            if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                List<JTable> tb = new ArrayList<JTable>();
                List<String> nom = new ArrayList<String>();
                tb.add(tbContasRecebidas);
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
    }//GEN-LAST:event_jbVisualizarContaActionPerformed

    private void KBContasReceberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KBContasReceberActionPerformed
        // TODO add your handling code here:
        jTabbedPanelContasAReceber.setSelectedIndex(0);
        panelTabbedKGVerficiar();
    }//GEN-LAST:event_KBContasReceberActionPerformed

    private void kBContasPagasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kBContasPagasActionPerformed
        // TODO add your handling code here:
        jTabbedPanelContasAReceber.setSelectedIndex(1);
        panelTabbedKGVerficiar();
    }//GEN-LAST:event_kBContasPagasActionPerformed

    private void kBRegistrarContasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kBRegistrarContasActionPerformed
        // TODO add your handling code here:
        if (jtfCodigoConta.isEnabled()) {
            jTabbedPanelContasAReceber.setSelectedIndex(2);
            panelTabbedKGVerficiar();
        } else {
            JOptionPane.showMessageDialog(this, "Você precisa clicar em nova conta antes de selecionar essa aba!", "ATENÇÂO", JOptionPane.WARNING_MESSAGE);
            panelTabbedKGVerficiar();
        }
    }//GEN-LAST:event_kBRegistrarContasActionPerformed

    private void jChecKFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jChecKFuncionarioActionPerformed
        // TODO add your handling code here:
        if (jChecKFuncionario.isSelected()) {
            cbFuncionario.setEnabled(true);
            cbBancos.setEnabled(true);
            tfSeguradora1.setEnabled(true);
            jLabel2.setEnabled(true);
        } else {
            cbFuncionario.setEnabled(false);
            cbBancos.setEnabled(false);
            tfSeguradora1.setEnabled(false);
            jLabel2.setEnabled(false);
            cbFuncionario.setSelectedIndex(-1);
            cbBancos.setSelectedIndex(-1);
        }
    }//GEN-LAST:event_jChecKFuncionarioActionPerformed

    private void cbFuncionarioPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbFuncionarioPopupMenuWillBecomeVisible
        // TODO add your handling code here:
        listarFuncionarios();
    }//GEN-LAST:event_cbFuncionarioPopupMenuWillBecomeVisible

    private void kButtonPendentes1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButtonPendentes1ActionPerformed
        // TODO add your handling code here:
        panelTabbedKGVerficiar();
        this.carregarContasReceber();
        this.carregarContasPagas();
    }//GEN-LAST:event_kButtonPendentes1ActionPerformed

    private void tfProcessoNumeroFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfProcessoNumeroFocusLost
        // TODO add your handling code here:
        try {
            modelOp = controllerOp.getOpController(Integer.parseInt(tfProcessoNumero.getText()));
            this.cbSeguradorasProcesso.setSelectedItem(controllerSeguradora.getSeguradoraController(modelOp.getSeguradoras()).getNome());
            jcbProcessos.setSelectedItem(modelOp.getCodigo());
            this.cbAgentePtgoPorKMSeguradoraProcessos.setSelectedItem(new Moeda().valorStringTODoubleAtt(String.valueOf(controllerSeguradora.getSeguradoraController(modelOp.getSeguradoras()).getSeg_km())));
            this.cbSinistroProcesso.setSelectedItem(String.valueOf(modelOp.getNumeroSinistro()));
            this.cbDataProcesso.setSelectedItem(String.valueOf(modelOp.getDataEntrada()));
            this.jLnomeSegProcesso.setText(this.cbSeguradorasProcesso.getSelectedObject().toString());
            this.jLSisnitroProcesso.setText(this.cbSinistroProcesso.getSelectedObject().toString());
            this.jLDataProcesso.setText(new Mascaras().DataRecuperasql(this.cbDataProcesso.getSelectedObject().toString()));
            jlProcessoNaoEncontrado.setVisible(false);
        } catch (NumberFormatException e) {
            jLnomeSegProcesso.setText("");
            jLSisnitroProcesso.setText("");
            jLDataProcesso.setText("");
            jcbProcessos.setSelectedIndex(-1);
            this.cbSeguradorasProcesso.setSelectedIndex(-1);
            this.cbSinistroProcesso.setSelectedIndex(-1);
            this.cbDataProcesso.setSelectedIndex(-1);
            jlProcessoNaoEncontrado.setVisible(true);
        }
    }//GEN-LAST:event_tfProcessoNumeroFocusLost

    private void tfProcessoNumeroFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfProcessoNumeroFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_tfProcessoNumeroFocusGained

    private void jdDataFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jdDataFocusLost
        // TODO add your handling code here:
        jdData.setText(jdData.getText());
    }//GEN-LAST:event_jdDataFocusLost

    private void jdVencimentoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jdVencimentoFocusLost
        // TODO add your handling code here:
        jdVencimento.setText(jdVencimento.getText());
    }//GEN-LAST:event_jdVencimentoFocusLost

    private void jdPagamentoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jdPagamentoFocusLost
        // TODO add your handling code here:
        jdPagamento.setText(jdPagamento.getText());
    }//GEN-LAST:event_jdPagamentoFocusLost

    private void jtfDescricaoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfDescricaoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == evt.VK_TAB) {
            jdData.requestFocus();
        }
    }//GEN-LAST:event_jtfDescricaoKeyPressed

    private void jdDataKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jdDataKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == evt.VK_TAB) {
            jFValor.requestFocus();
        }
    }//GEN-LAST:event_jdDataKeyPressed

    private void jFValorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFValorKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == evt.VK_TAB) {
            jdVencimento.requestFocus();
        }
    }//GEN-LAST:event_jFValorKeyPressed

    private void jdVencimentoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jdVencimentoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == evt.VK_TAB) {
            jdPagamento.requestFocus();
        }
    }//GEN-LAST:event_jdVencimentoKeyPressed

    private void jdPagamentoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jdPagamentoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == evt.VK_TAB) {
            jtfObservacao.requestFocus();
        }
    }//GEN-LAST:event_jdPagamentoKeyPressed

    private void tfProcessoNumeroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfProcessoNumeroKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == evt.VK_ENTER) {
            // TODO add your handling code here:
            try {
                modelOp = controllerOp.getOpController(Integer.parseInt(tfProcessoNumero.getText()));
                this.cbSeguradorasProcesso.setSelectedItem(controllerSeguradora.getSeguradoraController(modelOp.getSeguradoras()).getNome());
                jcbProcessos.setSelectedItem(modelOp.getCodigo());
                this.cbAgentePtgoPorKMSeguradoraProcessos.setSelectedItem(new Moeda().valorStringTODoubleAtt(String.valueOf(controllerSeguradora.getSeguradoraController(modelOp.getSeguradoras()).getSeg_km())));
                this.cbSinistroProcesso.setSelectedItem(String.valueOf(modelOp.getNumeroSinistro()));
                this.cbDataProcesso.setSelectedItem(String.valueOf(modelOp.getDataEntrada()));
                this.jLnomeSegProcesso.setText(this.cbSeguradorasProcesso.getSelectedObject().toString());
                this.jLSisnitroProcesso.setText(this.cbSinistroProcesso.getSelectedObject().toString());
                this.jLDataProcesso.setText(new Mascaras().DataRecuperasql(this.cbDataProcesso.getSelectedObject().toString()));
                jlProcessoNaoEncontrado.setVisible(false);
            } catch (NumberFormatException e) {
                jLnomeSegProcesso.setText("");
                jLSisnitroProcesso.setText("");
                jLDataProcesso.setText("");
                jcbProcessos.setSelectedIndex(-1);
                this.cbSeguradorasProcesso.setSelectedIndex(-1);
                this.cbSinistroProcesso.setSelectedIndex(-1);
                this.cbDataProcesso.setSelectedIndex(-1);
                jlProcessoNaoEncontrado.setVisible(true);
            }
        }
    }//GEN-LAST:event_tfProcessoNumeroKeyPressed

    private void jdcDataInicialFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jdcDataInicialFocusLost
        // TODO add your handling code here:
        carregarContasPagas();
    }//GEN-LAST:event_jdcDataInicialFocusLost

    private void btDefinirDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDefinirDataActionPerformed
        // TODO add your handling code here:
        carregarContasRecebidasBusca();
    }//GEN-LAST:event_btDefinirDataActionPerformed

    private void cbFuncionariosPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbFuncionariosPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
        if (this.cbFuncionarios.isPopupVisible() && this.cbFuncionarios.getSelectedItem() != null) {
            modelFuncionario = controllerFuncionario.getFuncionarioController(cbFuncionarios.getSelectedItem().toString());
            DefaultTableModel tabela = (DefaultTableModel) this.tbContasRecebidas.getModel();
            carregarContasRecebidasBusca();
            final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tabela);
            this.tbContasRecebidas.setRowSorter(sorter);
            String text = cbFuncionarios.getSelectedItem().toString();
            sorter.setRowFilter(RowFilter.regexFilter(text, 2));
        } else {
            DefaultTableModel tabela = (DefaultTableModel) this.tbContasRecebidas.getModel();
            this.tbContasRecebidas.setRowSorter(null);
        }
    }//GEN-LAST:event_cbFuncionariosPopupMenuWillBecomeInvisible

    private void btClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btClearActionPerformed
        // TODO add your handling code here:
        DefaultTableModel tabela = (DefaultTableModel) this.tbContasRecebidas.getModel();
        this.tbContasRecebidas.setRowSorter(null);
        listarFuncionarios();
        cbFuncionarios.setSelectedIndex(-1);
        cbFuncionarios.setSelectedItem(null);
        jdcDataInicial.setDate(null);
        jdcDataFinal.setDate(null);
        carregarContasPagas();
    }//GEN-LAST:event_btClearActionPerformed

    private void btClearEfetuadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btClearEfetuadoActionPerformed
        // TODO add your handling code here:
        DefaultTableModel tabela = (DefaultTableModel) this.tbContasReceber.getModel();
        this.tbContasReceber.setRowSorter(null);
        listarFuncionarios();
        cbFuncionariosEfetuado.setSelectedIndex(-1);
        cbFuncionariosEfetuado.setSelectedItem(null);
        carregarContasReceber();
    }//GEN-LAST:event_btClearEfetuadoActionPerformed

    private void cbFuncionariosEfetuadoPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbFuncionariosEfetuadoPopupMenuWillBecomeVisible
        // TODO add your handling code here:
        listarFuncionarios();
    }//GEN-LAST:event_cbFuncionariosEfetuadoPopupMenuWillBecomeVisible

    private void cbFuncionariosEfetuadoPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbFuncionariosEfetuadoPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
        if (this.cbFuncionariosEfetuado.isPopupVisible() && this.cbFuncionariosEfetuado.getSelectedItem() != null) {
            modelFuncionario = controllerFuncionario.getFuncionarioController(cbFuncionariosEfetuado.getSelectedItem().toString());
            DefaultTableModel tabela = (DefaultTableModel) this.tbContasReceber.getModel();
            final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tabela);
            this.tbContasReceber.setRowSorter(sorter);
            String text = cbFuncionariosEfetuado.getSelectedItem().toString();
            sorter.setRowFilter(RowFilter.regexFilter(text, 2));
            carregarContasPagas();
        } else {
            DefaultTableModel tabela = (DefaultTableModel) this.tbContasReceber.getModel();
            this.tbContasReceber.setRowSorter(null);
            carregarContasPagas();
        }
    }//GEN-LAST:event_cbFuncionariosEfetuadoPopupMenuWillBecomeInvisible

    /**
     * REcuperar dados do banco para alterar
     *
     * @param pCodigoConta
     * @return
     */
    private boolean recuperarConta(int pCodigoConta) {
        Datas bl = new Datas();

        modelContaReceber = new ModelContaReceber();
        modelContaReceber = controllerContaReceber.getContaReceberController(pCodigoConta);
        this.jCheckRepasse.setSelected(modelContaReceber.getRepasse());
        this.jChecKFuncionario.setSelected(modelContaReceber.isFuncionarioCheck());
        if (jChecKFuncionario.isSelected()) {
            this.cbFuncionario.setSelectedItem(controllerFuncionario.getFuncionarioController(modelContaReceber.getCodigoPessoa()).getNome());
            this.cbCodFuncionario.setSelectedItem(controllerFuncionario.getFuncionarioController(modelContaReceber.getCodigoPessoa()).getCodigo());
            this.cbBancos.setSelectedItem(controllerBancos.getBancosControllerCod(modelContaReceber.getBanco()).getBanco_nome());
        } else {
            this.cbFuncionario.setSelectedIndex(-1);
            this.cbCodFuncionario.setSelectedIndex(-1);
            this.cbBancos.setSelectedIndex(-1);
        }
        this.jcbTipoConta.setSelectedItem(modelContaReceber.getTipoPagamento());
        this.jtfCodigoConta.setText(String.valueOf(pCodigoConta));
        this.jtfDescricao.setText(modelContaReceber.getDescricao());
        this.jtfObservacao.setText(modelContaReceber.getObservacao());
        this.jdData.setText(new Mascaras().DataRecuperasql(String.valueOf(modelContaReceber.getData())));
        this.jdPagamento.setText(new Mascaras().DataRecuperasql(String.valueOf(modelContaReceber.getPagamento())));
        this.jdVencimento.setText(new Mascaras().DataRecuperasql(String.valueOf(modelContaReceber.getVencimento())));
        this.jFValor.setText(new Moeda().valorStringTODoubleAtt(String.valueOf(modelContaReceber.getValor())));

        //VERIFICA SE EXISTE REPASSE
        if (jCheckRepasse.isSelected()) {
            this.jcbProcessos.setSelectedItem(controllerOp.getOpController(modelContaReceber.getProcessos()).getCodigo());
            this.tfProcessoNumero.setText(String.valueOf(modelContaReceber.getProcessos()));
            //dados
            modelOp = controllerOp.getOpController(Integer.parseInt(tfProcessoNumero.getText()));
            this.cbSeguradorasProcesso.setSelectedItem(controllerSeguradora.getSeguradoraController(modelOp.getSeguradoras()).getNome());
            this.cbAgentePtgoPorKMSeguradoraProcessos.setSelectedItem(new Moeda().valorStringTODoubleAtt(String.valueOf(controllerSeguradora.getSeguradoraController(modelOp.getSeguradoras()).getSeg_km())));
            this.cbSinistroProcesso.setSelectedItem(String.valueOf(modelOp.getNumeroSinistro()));
            this.cbDataProcesso.setSelectedItem(String.valueOf(modelOp.getDataEntrada()));
            this.jLnomeSegProcesso.setText(this.cbSeguradorasProcesso.getSelectedObject().toString());
            this.jLSisnitroProcesso.setText(this.cbSinistroProcesso.getSelectedObject().toString());
            this.jLDataProcesso.setText(new Mascaras().DataRecuperasql(this.cbDataProcesso.getSelectedObject().toString()));
            jlProcessoNaoEncontrado.setVisible(false);
        } else {
            this.jcbProcessos.setSelectedIndex(-1);
        }
        verificarTipoConta();
        return true;
    }

    /**
     * Excluir dados do banco
     *
     * @return
     */
    private boolean excluirConta() {
        int linha = tbContasReceber.getSelectedRow();
        String nome = (String) tbContasReceber.getValueAt(linha, 1);
        int codigo = (int) tbContasReceber.getValueAt(linha, 0);

        //pegunta se realmente deseja excluir
        int opcao = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja"
                + " excluir o registro:\n" + "\n " + nome + "?", "Atenção", JOptionPane.YES_NO_OPTION);
        //se sim exclui, se não não faz nada
        if (opcao == JOptionPane.OK_OPTION) {
            if (controllerContaReceber.excluirContaReceberController(codigo)) {
                JOptionPane.showMessageDialog(this, "Registro excluido com suscesso!");
                carregarContasReceber();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao e os dados!", "ERRO", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return true;
    }

    /**
     * Salvar dados
     *
     * @return
     */
    private boolean salvar() throws Exception {
        modelContaReceber.setTipoConta("PAGAR");
        modelContaReceber.setFuncionarioCheck(jChecKFuncionario.isSelected());

        if (jChecKFuncionario.isSelected()) {
            modelContaReceber.setCodigoPessoa(controllerFuncionario.getFuncionarioController(this.cbFuncionario.getSelectedItem().toString()).getCodigo());
            modelContaReceber.setBanco(controllerBancos.getBancosControllerNome(cbBancos.getSelectedItem().toString()).getCodigo());
            modelContaReceber.setTipoPagamento(controllerCobertura.getCoberturaController(this.jcbTipoConta.getSelectedItem().toString()).getCodigo());
        } else {
            modelContaReceber.setCodigoPessoa(0);
            modelContaReceber.setBanco(0);
            modelContaReceber.setTipoPagamento(0);
        }
        modelContaReceber.setDescricao(this.jtfDescricao.getText());
        modelContaReceber.setData(new Mascaras().DataSalvanosql(String.valueOf(jdData.getText())));
        modelContaReceber.setVencimento(new Mascaras().DataSalvanosql(String.valueOf(jdVencimento.getText())));
        modelContaReceber.setPagamento(new Mascaras().DataSalvanosql(String.valueOf(jdPagamento.getText())));
        if (jCheckRepasse.isSelected()) {
            modelContaReceber.setProcessos(Integer.parseInt(tfProcessoNumero.getText()));
        }
        modelContaReceber.setSituacao(0);
        modelContaReceber.setValor(Double.parseDouble((new Moeda().FommatoStringoSomarMil(this.jFValor.getText()))));
        modelContaReceber.setObservacao(this.jtfObservacao.getText());
        modelContaReceber.setRepasse(jCheckRepasse.isSelected());

        //salvar 
        if (controllerContaReceber.salvarContaReceberController(modelContaReceber) > 0) {
            JOptionPane.showMessageDialog(this, "Registro gravado com sucesso!");
            this.limparDados();
            this.habilitaDesabilitarCampos(false);
            this.carregarContasReceber();
            return true;
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao gravar os dados!", "ERRO", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /**
     * Alterar dados
     *
     * @return
     */
    private boolean alterar() throws Exception {
        modelContaReceber.setTipoConta("PAGAR");
        modelContaReceber.setFuncionarioCheck(jChecKFuncionario.isSelected());

        if (jChecKFuncionario.isSelected()) {
            modelContaReceber.setCodigoPessoa(controllerFuncionario.getFuncionarioController(this.cbFuncionario.getSelectedItem().toString()).getCodigo());
            modelContaReceber.setBanco(controllerBancos.getBancosControllerNome(cbBancos.getSelectedItem().toString()).getCodigo());
            modelContaReceber.setTipoPagamento(controllerCobertura.getCoberturaController(this.jcbTipoConta.getSelectedItem().toString()).getCodigo());
        } else {
            modelContaReceber.setCodigoPessoa(0);
            modelContaReceber.setBanco(0);
            modelContaReceber.setTipoPagamento(0);
        }
        modelContaReceber.setDescricao(this.jtfDescricao.getText());
        modelContaReceber.setData(new Mascaras().DataSalvanosql(String.valueOf(jdData.getText())));
        modelContaReceber.setVencimento(new Mascaras().DataSalvanosql(String.valueOf(jdVencimento.getText())));
        modelContaReceber.setPagamento(new Mascaras().DataSalvanosql(String.valueOf(jdPagamento.getText())));
        if (jCheckRepasse.isSelected()) {
            modelContaReceber.setProcessos(Integer.parseInt(tfProcessoNumero.getText()));
        }
        modelContaReceber.setSituacao(0);
        modelContaReceber.setValor(Double.parseDouble((new Moeda().FommatoStringoSomarMil(this.jFValor.getText()))));
        modelContaReceber.setObservacao(this.jtfObservacao.getText());
        modelContaReceber.setRepasse(jCheckRepasse.isSelected());
        //salvar 
        if (controllerContaReceber.atualizarContaReceberController(modelContaReceber)) {
            JOptionPane.showMessageDialog(this, "Registro alterado com sucesso!");
            this.limparDados();
            this.habilitaDesabilitarCampos(false);
            this.carregarContasReceber();
            return true;
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao alterar os dados!", "ERRO", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    //FUNCÕES
    private void recuperarDadosPorProcesso() {
        if (Integer.parseInt(this.jcbProcessos.getSelectedItem().toString()) > 0) {
            modelOp = controllerOp.getOpController(Integer.parseInt(jcbProcessos.getSelectedItem().toString()));
            //recupera o código
            this.cbSeguradorasProcesso.setSelectedItem(controllerSeguradora.getSeguradoraController(modelOp.getSeguradoras()).getNome());
            this.cbAgentePtgoPorKMSeguradoraProcessos.setSelectedItem(new Moeda().valorStringTODoubleAtt(String.valueOf(controllerSeguradora.getSeguradoraController(modelOp.getSeguradoras()).getSeg_km())));
            this.cbSinistroProcesso.setSelectedItem(String.valueOf(modelOp.getNumeroSinistro()));
            this.cbDataProcesso.setSelectedItem(String.valueOf(modelOp.getDataEntrada()));
        }
    }

    private void recuperarCodigoPorFuncionario() {
        if (this.cbFuncionario.getSelectedIndex() > 0) {
            modelFuncionario = controllerFuncionario.getFuncionarioController(cbFuncionario.getSelectedItem().toString());
            //recupera o código
            this.cbCodFuncionario.setSelectedItem(modelFuncionario.getCodigo());
        }
    }

    //VERIFICAR AGENTE E AVULSOS
    private void verificarTipoConta() {
        if (jCheckRepasse.isSelected()) {
            jcbProcessos.setEnabled(true);
            tfProcessoNumero.setEnabled(true);
            kGDadosDoProcesso.setEnabled(true);
            //label
            jLDataEntradaProcesso.setEnabled(true);
            jLNumeroSinistroProcesso.setEnabled(true);
            jLSeguradoraProcesso.setEnabled(true);
            jLNumeroProcesso.setEnabled(true);
            //campos agente
            jfAgenteKmPercorrido.setEnabled(true);
            jFPagamentoTotalKM.setEnabled(true);
            jfAgenteTotalRepasse.setEnabled(true);
            //label
            jLAgenteHonorarios.setEnabled(true);
            jLAgenteKMpercorrido.setEnabled(true);
            jLAgenteTotalKm.setEnabled(true);
            jLAgenteRepasseTotal.setEnabled(true);
            kGRepasseAoAgente.setEnabled(true);
            jfAgenteHonorario.setEnabled(true);
        } else {
            jcbProcessos.setEnabled(false);
            tfProcessoNumero.setEnabled(false);
            kGDadosDoProcesso.setEnabled(false);
            //label
            jLDataEntradaProcesso.setEnabled(false);
            jLNumeroSinistroProcesso.setEnabled(false);
            jLSeguradoraProcesso.setEnabled(false);
            jLNumeroProcesso.setEnabled(false);
            //campos agente
            jfAgenteKmPercorrido.setEnabled(false);
            cbAgentePtgoPorKMSeguradoraProcessos.setEnabled(false);
            jFPagamentoTotalKM.setEnabled(false);
            jfAgenteTotalRepasse.setEnabled(false);
            //label
            jLAgenteHonorarios.setEnabled(false);
            jLAgenteKMpercorrido.setEnabled(false);
            jLAgenteTotalKm.setEnabled(false);
            jLAgenteRepasseTotal.setEnabled(false);
            kGRepasseAoAgente.setEnabled(false);
            jfAgenteHonorario.setEnabled(false);
            //LIMPAR
            jLnomeSegProcesso.setText("");
            jLSisnitroProcesso.setText("");
            jLDataProcesso.setText("");
            jcbProcessos.setSelectedIndex(-1);
            this.cbSeguradorasProcesso.setSelectedIndex(-1);
            this.cbSinistroProcesso.setSelectedIndex(-1);
            this.cbDataProcesso.setSelectedIndex(-1);
            jlProcessoNaoEncontrado.setVisible(false);
            tfProcessoNumero.setText("");
        }
    }

    private void carregarContasReceber() {
        listaModelContaReceber = controllerContaReceber.getListaContaReceberController(0);
        DefaultTableModel modelo = (DefaultTableModel) tbContasReceber.getModel();
        modelo.setNumRows(0);
        String nomeFuncionario;
        //CARREGA OS DADOS DA LISTA NA TABELA
        int cont = listaModelContaReceber.size();
        for (int i = 0; i < cont; i++) {
            nomeFuncionario = controllerFuncionario.getFuncionarioController(listaModelContaReceber.get(i).getCodigoPessoa()).getNome();
            if ("".equals(nomeFuncionario) || nomeFuncionario == null) {
                nomeFuncionario = "Nenhum Funcionário";
            }
            modelo.addRow(new Object[]{
                listaModelContaReceber.get(i).getCodigo(),
                listaModelContaReceber.get(i).getDescricao(),
                nomeFuncionario,
                (new Mascaras().DataRecuperasql(String.valueOf(listaModelContaReceber.get(i).getData()))),
                (new Mascaras().DataRecuperasql(String.valueOf(listaModelContaReceber.get(i).getVencimento()))),
                (new Mascaras().DataRecuperasql(String.valueOf(listaModelContaReceber.get(i).getPagamento()))),
                (new Moeda().valorStringTODoubleAtt(String.valueOf(listaModelContaReceber.get(i).getValor())))});
        }
    }

    private void carregarContasPagas() {
        listaModelContaReceber = controllerContaReceber.getListaContaReceberController(1);
        DefaultTableModel modelo = (DefaultTableModel) tbContasRecebidas.getModel();
        modelo.setNumRows(0);
        String nomeFuncionario;
        //CARREGA OS DADOS DA LISTA NA TABELA
        int cont = listaModelContaReceber.size();
        for (int i = 0; i < cont; i++) {
            nomeFuncionario = controllerFuncionario.getFuncionarioController(listaModelContaReceber.get(i).getCodigoPessoa()).getNome();
            if ("".equals(nomeFuncionario) || nomeFuncionario == null) {
                nomeFuncionario = "Nenhum Funcionário";
            }
            modelo.addRow(new Object[]{
                listaModelContaReceber.get(i).getCodigo(),
                listaModelContaReceber.get(i).getDescricao(),
                nomeFuncionario,
                (new Mascaras().DataRecuperasql(String.valueOf(listaModelContaReceber.get(i).getData()))),
                (new Mascaras().DataRecuperasql(String.valueOf(listaModelContaReceber.get(i).getVencimento()))),
                (new Mascaras().DataRecuperasql(String.valueOf(listaModelContaReceber.get(i).getPagamento()))),
                (new Moeda().valorStringTODoubleAtt(String.valueOf(listaModelContaReceber.get(i).getValor())))});
        }
    }

    private void listarFuncionarios() {
        listaModelFuncionario = controllerFuncionario.getListaFuncionarioController();
        cbFuncionario.removeAllItems();
        cbFuncionarios.removeAllItems();;
        cbFuncionariosEfetuado.removeAllItems();
        cbCodFuncionario.removeAllItems();
        for (int i = 0; i < listaModelFuncionario.size(); i++) {
            cbFuncionario.addItem(listaModelFuncionario.get(i).getNome());
            cbFuncionarios.addItem(listaModelFuncionario.get(i).getNome());
            cbCodFuncionario.addItem(listaModelFuncionario.get(i).getCodigo());
            cbFuncionariosEfetuado.addItem(listaModelFuncionario.get(i).getNome());
        }
        cbFuncionariosEfetuado.setSelectedIndex(-1);
        cbFuncionario.setSelectedIndex(-1);
        cbFuncionarios.setSelectedIndex(-1);
        cbCodFuncionario.setSelectedIndex(-1);
    }

    private void listarTipoConta() {
        listaModelCobertura = controllerCobertura.getListaCoberturaController();
        jcbTipoConta.removeAllItems();
        for (int i = 0; i < listaModelCobertura.size(); i++) {
            jcbTipoConta.addItem(listaModelCobertura.get(i).getCober_nome());
        }
    }

    private void listarProcessos() {
        listaModelOp = controllerOp.getListaOpController();
        jcbProcessos.removeAllItems();
        for (int i = 0; i < listaModelOp.size(); i++) {
            jcbProcessos.addItem(listaModelOp.get(i).getCodigo());
        }
    }

    private void listarSeguradoras() {
        listaModelSeguradora = controllerSeguradora.getListaSeguradoraController();
        cbSeguradorasProcesso.removeAllItems();
        for (int i = 0; i < listaModelSeguradora.size(); i++) {
            cbSeguradorasProcesso.addItem(listaModelSeguradora.get(i).getNome());
        }
    }

    private void listarProcessosData() {
        listaModelOp = controllerOp.getListaOpController();
        cbDataProcesso.removeAllItems();
        for (int i = 0; i < listaModelOp.size(); i++) {
            cbDataProcesso.addItem(String.valueOf(listaModelOp.get(i).getDataEntrada()));
        }
    }

    private void listarSeguradorasKm() {
        listaModelSeguradora = controllerSeguradora.getListaSeguradoraController();
        cbAgentePtgoPorKMSeguradoraProcessos.removeAllItems();
        for (int i = 0; i < listaModelSeguradora.size(); i++) {
            cbAgentePtgoPorKMSeguradoraProcessos.addItem(new Moeda().valorStringTODoubleAtt(String.valueOf(listaModelSeguradora.get(i).getSeg_km())));
        }
    }

    private void listarProcessosSinistro() {
        listaModelOp = controllerOp.getListaOpController();
        cbSinistroProcesso.removeAllItems();
        for (int i = 0; i < listaModelOp.size(); i++) {
            cbSinistroProcesso.addItem(String.valueOf(listaModelOp.get(i).getNumeroSinistro()));
        }
    }

    private void listarBancos() {
        listaModelBancos = controllerBancos.getListaBancosController();
        cbBancos.removeAllItems();
        for (int i = 0; i < listaModelBancos.size(); i++) {
            cbBancos.addItem(listaModelBancos.get(i).getBanco_nome());
        }
    }

    private void limparDados() {
        listarFuncionarios();
        listarTipoConta();
        listarBancos();
        listarProcessos();
        listarProcessosData();
        listarSeguradoras();
        listarProcessosSinistro();
        carregarContasReceber();
        carregarContasPagas();
        verificarTipoConta();
        listarSeguradorasKm();
        this.jtfDescricao.setText("");
        this.jtfObservacao.setText("");
        this.jFValor.setText("R$ 0,00");
        this.jdData.setText("");
        this.jdPagamento.setText("");
        this.jdVencimento.setText("");
        this.jCheckRepasse.setSelected(false);
        this.cbBancos.setSelectedIndex(-1);
        this.jcbTipoConta.setSelectedIndex(-1);
        this.cbCodFuncionario.setSelectedIndex(-1);
        this.jfAgenteHonorario.setText("R$ 0,00");
        this.jfAgenteKmPercorrido.setText("0");
        this.cbAgentePtgoPorKMSeguradoraProcessos.setSelectedIndex(-1);
        this.jFPagamentoTotalKM.setText("R$ 0,00");
        this.jfAgenteTotalRepasse.setText("R$ 0,00");
        this.cbSeguradorasProcesso.setSelectedIndex(-1);
        this.cbFuncionario.setSelectedIndex(-1);
        this.cbSinistroProcesso.setSelectedIndex(-1);
        this.cbDataProcesso.setSelectedIndex(-1);
        this.jcbProcessos.setSelectedIndex(-1);
        jLnomeSegProcesso.setText("");
        jLSisnitroProcesso.setText("");
        jLDataProcesso.setText("");
        jlProcessoNaoEncontrado.setVisible(false);
        jChecKFuncionario.setSelected(false);
        jtfCodigoConta.setEnabled(false);
        cbFuncionario.setEnabled(false);
        cbBancos.setEnabled(false);
        tfSeguradora1.setEnabled(false);
        jLabel2.setEnabled(false);
        jLabel8.setEnabled(false);
        tfProcessoNumero.setText("");
    }

    /**
     * Habilitar ou desabilitar campos do formulário
     *
     * @param operacao
     */
    private void habilitaDesabilitarCampos(boolean operacao) {
        this.jtfDescricao.setEnabled(operacao);
        this.jtfObservacao.setEnabled(operacao);
        this.jFValor.setEnabled(operacao);
        this.jdData.setEnabled(operacao);
        this.jdPagamento.setEnabled(operacao);
        this.jdVencimento.setEnabled(operacao);
        this.JbSAlvar.setEnabled(operacao);
        jCheckRepasse.setEnabled(operacao);
        jChecKFuncionario.setEnabled(operacao);
        jtfCodigoConta.setEnabled(operacao);
        tfProcessoNumero.setEditable(operacao);
        jcbTipoConta.setEnabled(operacao);
        jLabel8.setEnabled(operacao);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ContasAReceber;
    private javax.swing.JPanel ContasRecebidas;
    private javax.swing.JButton JbSAlvar;
    private keeptoo.KButton KBContasReceber;
    private javax.swing.JPanel RegistrarContas;
    private javax.swing.JButton btAlterar;
    private javax.swing.JButton btCancelar2;
    private javax.swing.JButton btClear;
    private javax.swing.JButton btClearEfetuado;
    private javax.swing.JButton btDefinirData;
    private javax.swing.JButton btNovo;
    private componentes.UJComboBox cbAgentePtgoPorKMSeguradoraProcessos;
    private componentes.UJComboBox cbBancos;
    private javax.swing.JComboBox cbCodFuncionario;
    private componentes.UJComboBox cbDataProcesso;
    private componentes.UJComboBox cbFuncionario;
    private javax.swing.JComboBox<String> cbFuncionarios;
    private javax.swing.JComboBox<String> cbFuncionariosEfetuado;
    private componentes.UJComboBox cbSeguradorasProcesso;
    private componentes.UJComboBox cbSinistroProcesso;
    private javax.swing.JCheckBox jChecKFuncionario;
    private javax.swing.JCheckBox jCheckRepasse;
    private javax.swing.JTextField jFPagamentoTotalKM;
    private javax.swing.JFormattedTextField jFValor;
    private javax.swing.JLabel jLAgenteHonorarios;
    private javax.swing.JLabel jLAgenteKMpercorrido;
    private javax.swing.JLabel jLAgenteRepasseTotal;
    private javax.swing.JLabel jLAgenteSeguradoraKm;
    private javax.swing.JLabel jLAgenteTotalKm;
    private javax.swing.JLabel jLDataEntradaProcesso;
    private javax.swing.JLabel jLDataProcesso;
    private javax.swing.JLabel jLNumeroProcesso;
    private javax.swing.JLabel jLNumeroSinistroProcesso;
    private javax.swing.JLabel jLSeguradoraProcesso;
    private javax.swing.JTextArea jLSisnitroProcesso;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextArea jLnomeSegProcesso;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPaneAnalista2;
    private javax.swing.JScrollPane jScrollPaneAnalista3;
    private javax.swing.JTabbedPane jTabbedPanelContasAReceber;
    private javax.swing.JButton jbExcluirReceber;
    private javax.swing.JButton jbReceberConta;
    private javax.swing.JButton jbVisualizarConta;
    private componentes.UJComboBox jcbProcessos;
    private componentes.UJComboBox jcbTipoConta;
    private javax.swing.JFormattedTextField jdData;
    private javax.swing.JFormattedTextField jdPagamento;
    private javax.swing.JFormattedTextField jdVencimento;
    private com.toedter.calendar.JDateChooser jdcDataFinal;
    private com.toedter.calendar.JDateChooser jdcDataInicial;
    private javax.swing.JFormattedTextField jfAgenteHonorario;
    private javax.swing.JFormattedTextField jfAgenteKmPercorrido;
    private javax.swing.JFormattedTextField jfAgenteTotalRepasse;
    private javax.swing.JLabel jlProcessoNaoEncontrado;
    private javax.swing.JButton jpRevorgarConta;
    private javax.swing.JTextField jtfCodigoConta;
    private javax.swing.JTextField jtfDescricao;
    private javax.swing.JTextPane jtfObservacao;
    private keeptoo.KButton kBContasPagas;
    private keeptoo.KButton kBRegistrarContas;
    private keeptoo.KButton kButtonPendentes1;
    private keeptoo.KGradientPanel kGDadosDoProcesso;
    private keeptoo.KGradientPanel kGDetalhesDaConta;
    private keeptoo.KGradientPanel kGFuncionarioAgente;
    private keeptoo.KGradientPanel kGRepasseAoAgente;
    private keeptoo.KGradientPanel kGTipoConta;
    private keeptoo.KGradientPanel kGVincularFunc;
    private keeptoo.KGradientPanel kGradientPanel1;
    private keeptoo.KGradientPanel kGradientPanel10;
    private keeptoo.KGradientPanel kGradientPanel11;
    private keeptoo.KGradientPanel kGradientPanel14;
    private keeptoo.KGradientPanel kGradientPanel5;
    private keeptoo.KGradientPanel kGradientPanel6;
    private keeptoo.KGradientPanel kGradientPanel7;
    private keeptoo.KGradientPanel kGradientPanel8;
    private keeptoo.KGradientPanel kGradientPanel9;
    private javax.swing.JLabel labelPagamento;
    private javax.swing.JTable tbContasReceber;
    private javax.swing.JTable tbContasRecebidas;
    private javax.swing.JTextField tfProcessoNumero;
    private javax.swing.JLabel tfSeguradora;
    private javax.swing.JLabel tfSeguradora1;
    // End of variables declaration//GEN-END:variables
      /**
     *
     */
    private void carregarContasRecebidasBusca() {
        Datas bl = new Datas();

        java.sql.Date dataInicial = null;
        java.sql.Date dataFinal = null;

        try {
            dataInicial = bl.converterDataParaDateUS(bl.converterDataParaDateUS(jdcDataInicial.getDate()));
            dataFinal = bl.converterDataParaDateUS(bl.converterDataParaDateUS(jdcDataFinal.getDate()));
        } catch (Exception ex) {
            Logger.getLogger(ViewContasReceber.class.getName()).log(Level.SEVERE, null, ex);
        }

        ModelContaReceber modelContasReceber = new ModelContaReceber();
        DefaultTableModel modelo = (DefaultTableModel) tbContasRecebidas.getModel();
        modelContasReceber.setSituacao(1);
        controllerContaReceber.FiltraContaReceberFluxoDados(modelContaReceber, dataInicial, dataFinal);

        listaModelContaReceber = controllerContaReceber.FiltraContaReceberFluxoDados(modelContaReceber, dataInicial, dataFinal);
        modelo.setNumRows(0);
        String nomeFuncionario, tipoPagamento = "";
        //CARREGA OS DADOS DA LISTA NA TABELA
        int cont = listaModelContaReceber.size();
        for (int i = 0; i < cont; i++) {
            nomeFuncionario = controllerFuncionario.getFuncionarioController(listaModelContaReceber.get(i).getCodigoPessoa()).getNome();
            if ("".equals(nomeFuncionario) || nomeFuncionario == null) {
                nomeFuncionario = "Nenhum Funcionário";
            }
            modelo.addRow(new Object[]{
                listaModelContaReceber.get(i).getCodigo(),
                listaModelContaReceber.get(i).getDescricao(),
                nomeFuncionario,
                (new Mascaras().DataRecuperasql(String.valueOf(listaModelContaReceber.get(i).getData()))),
                (new Mascaras().DataRecuperasql(String.valueOf(listaModelContaReceber.get(i).getPagamento()))),
                (new Moeda().valorStringTODoubleAtt(String.valueOf(listaModelContaReceber.get(i).getValor())))});
        }
    }

    public static ViewContasReceber viewcontasreceber;

    private void SetIcone() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("ictrol.png")));
    }

}
