/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ControllerAlicota;
import Controller.ControllerCidade;
import Controller.ControllerCobertura;
import Controller.ControllerSeguradora;
import Controller.ControllerEstado;
import Controller.ControllerFormaPagamento;
import Controller.ControllerFuncionario;
import Controller.ControllerOp;
import Controller.ControllerServicos;
import Model.ModelCidade;
import Model.ModelCobertura;
import Model.ModelSeguradora;
import Model.ModelFormaPagamento;
import Model.ModelOp;
import Model.ModelServicos;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Model.ModelEstado;
import Model.ModelFuncionario;
import Model.ModelSeguradoraCidadeEstado;
import Model.ModelAlicota;
import static View.ViewOpConsulta.opConsulta;

import util.Mascaras;
import conexao.ConexaoBanco;
import java.awt.Color;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Porcentagem;

/**
 *
 * @author studiomicroweb
 */
public final class ViewOpVisualizar extends javax.swing.JFrame {

    Mascaras Mascaras = new Mascaras();
    ModelServicos modelServicos = new ModelServicos();
    ArrayList<ModelServicos> listaModelServicos = new ArrayList<>();
    ControllerServicos controllerServicos = new ControllerServicos();
    ArrayList<ModelOp> listaModelOp = new ArrayList<>();
    ModelAlicota modelAlicota = new ModelAlicota();
    ArrayList<ModelAlicota> listaModelAlicota = new ArrayList<>();
    ControllerAlicota controllerAlicota = new ControllerAlicota();

    ModelCobertura modelCobertura = new ModelCobertura();
    ArrayList<ModelCobertura> listaModelCobertura = new ArrayList<>();
    ControllerCobertura controllerCobertura = new ControllerCobertura();

    ControllerOp controllerOp = new ControllerOp();
    ArrayList<ModelOp> listaModelOpAlterar = new ArrayList<>();
    ModelOp modelOp = new ModelOp();
    ArrayList<ModelSeguradora> listaModelSeguradora = new ArrayList<>();
    ControllerSeguradora controllerSeguradora = new ControllerSeguradora();
    ModelSeguradora modelSeguradora = new ModelSeguradora();
    ControllerEstado controllerEstado = new ControllerEstado();
    ControllerCidade controllerCidade = new ControllerCidade();
    ArrayList<ModelCidade> listaModelCidades = new ArrayList<>();
    ArrayList<ModelEstado> listaModelEstados = new ArrayList<>();
    ArrayList<ModelSeguradoraCidadeEstado> listaModelSeguradoraCidadeEstados = new ArrayList<>();
    ControllerFormaPagamento controllerTipoPagamento = new ControllerFormaPagamento();
    ArrayList<ModelFormaPagamento> listaModelTipoPagamentos = new ArrayList<>();
    ArrayList<ModelFuncionario> listaModelFuncionarios = new ArrayList<>();
    ControllerFuncionario controllerFuncionarios = new ControllerFuncionario();
    ModelFuncionario modelFuncionarios = new ModelFuncionario();
    Double valorCartao, valorCheque, valorDinheiro, valorVale;
    String tipoCadastro;
    ViewSeguradora viewseguradora = new ViewSeguradora();
    ViewOpConsulta viewoprocesso = new ViewOpConsulta();

    /**
     * Creates new form ViewOrdemProcesso
     */
    public ViewOpVisualizar() {
        initComponents();
        SetIcone();
        listarServicos();
        listarCodigoServicos();
        this.listarEstados();
        this.listarCidades();
        listarSeguradoras();
        listarCodigosSeguradoras();
        retornarDadosSeguradoras();
        if (viewoprocesso.tipoCadastro != null && viewoprocesso.tipoCadastro.equals("alteracao")) {
            recuperarOp(modelOp);
        }
        this.setLocationRelativeTo(null);
        this.jTPanelProcessos.setSelectedIndex(0);
        testeverificaseg();
    }

    void carregarTerceirosAlteracao() {
        if ((int) tbTerceiros.getRowCount() > 0) {
            DefaultTableModel modelo = (DefaultTableModel) tbTerceiros.getModel();
            modelo.setNumRows(0);
        }
        try {
            int codigo = 0;
            codigo = (Integer.parseInt(tfCodigo.getText()));
            DefaultTableModel model = (DefaultTableModel) tbTerceiros.getModel();
            String consultar = "SELECT * FROM terceiros_processos WHERE cod_ordem_servico='" + codigo + "'";
            String[] datos = new String[4];
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(consultar);
            while (rs.next()) {
                datos[0] = rs.getString("codigo");
                datos[1] = rs.getString("nome");
                datos[2] = rs.getString("placa");
                datos[3] = rs.getString("obs");

                model.addRow(datos);
            }
            tbTerceiros.setModel(model);

        } catch (SQLException ex) {
            Logger.getLogger(ViewCadISS.class.getName()).log(Level.SEVERE, null, ex);
        }
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
                new ViewOpVisualizar().setVisible(true);
            }
        });
    }

    public ViewOpVisualizar(ModelOp modelOp) {
        initComponents();
        recuperarOp(modelOp);
        retornarSeguradoraPeloCodigo2(modelOp);
    }

    public void listarEstados() {
        listaModelEstados = controllerEstado.getListaEstadoController();
        cbUf.removeAllItems();
        for (int i = 0; i < listaModelEstados.size(); i++) {
            cbUf.addItem(listaModelEstados.get(i).getUf());
        }
    }

    public void listarCidades() {
        listaModelCidades = controllerCidade.getListaCidadePorEstadoController(controllerEstado.getEstadoUFController(this.cbUf.getSelectedItem().toString()).getCodigo());
        cbCidade.removeAllItems();
        for (int i = 0; i < listaModelCidades.size(); i++) {
            cbCidade.addItem(listaModelCidades.get(i).getNome());
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

        tfCodTerceiro = new javax.swing.JTextField();
        cbSituacaoPgtoProcesso = new javax.swing.JComboBox<String>();
        cbCodServico = new javax.swing.JComboBox<String>();
        cbCodSeguradora = new javax.swing.JComboBox();
        cbUf = new javax.swing.JComboBox<String>();
        cbCidade = new javax.swing.JComboBox<String>();
        cbServico = new componentes.UJComboBox();
        cbSeguradoras = new javax.swing.JComboBox();
        kGradientPanel1 = new keeptoo.KGradientPanel();
        jTPanelProcessos = new javax.swing.JTabbedPane();
        jPanelEntradadeProcessos = new javax.swing.JPanel();
        kGSeguradoraSolicitante = new keeptoo.KGradientPanel();
        jLNomeSeguradora = new javax.swing.JLabel();
        jLDataEntrada = new javax.swing.JLabel();
        jLDataSaida = new javax.swing.JLabel();
        jLNumeroProcesso = new javax.swing.JLabel();
        jLCnpj1 = new javax.swing.JLabel();
        jfCNPJ = new javax.swing.JLabel();
        jFDataEntrada = new javax.swing.JLabel();
        jFDataSaida = new javax.swing.JLabel();
        tfCodigo = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jLPuxarnomeSeguradora = new javax.swing.JTextArea();
        kGradientPanel15 = new keeptoo.KGradientPanel();
        kGradientPanel7 = new keeptoo.KGradientPanel();
        jLabel2 = new javax.swing.JLabel();
        kGradientPanel13 = new keeptoo.KGradientPanel();
        kGPDadosSinistro = new keeptoo.KGradientPanel();
        jLAnalista = new javax.swing.JLabel();
        jLCobertura = new javax.swing.JLabel();
        jLDetalhesSinistro = new javax.swing.JLabel();
        jLEstado = new javax.swing.JLabel();
        jLCidade = new javax.swing.JLabel();
        jLBairro = new javax.swing.JLabel();
        jLHoraSinistro = new javax.swing.JLabel();
        jLDataSinistro = new javax.swing.JLabel();
        jLNumeroSinistro = new javax.swing.JLabel();
        jLNomeCobertura = new javax.swing.JLabel();
        jFDataSinistro = new javax.swing.JLabel();
        jFHoraSinistro = new javax.swing.JLabel();
        jLNomeEstado = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jLNomeCidade = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTObsSinistro = new javax.swing.JTextArea();
        kGradientPanel2 = new keeptoo.KGradientPanel();
        jScrollPaneAnalista = new javax.swing.JScrollPane();
        tfAnalista = new javax.swing.JTextArea();
        jScrollPaneAnalista1 = new javax.swing.JScrollPane();
        tfNumSinistro = new javax.swing.JTextArea();
        kGradientPanel3 = new keeptoo.KGradientPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tfBairro = new javax.swing.JTextArea();
        kGradientPanel16 = new keeptoo.KGradientPanel();
        kGradientPanel8 = new keeptoo.KGradientPanel();
        jLabel3 = new javax.swing.JLabel();
        kGradientPanel10 = new keeptoo.KGradientPanel();
        kGPDadosSegurado = new keeptoo.KGradientPanel();
        jLNomeSegurado = new javax.swing.JLabel();
        jLPlacaSegurado = new javax.swing.JLabel();
        jLObsSegurado = new javax.swing.JLabel();
        tfSeguradoPlaca = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tfObsSegurado = new javax.swing.JTextArea();
        jScrollPane8 = new javax.swing.JScrollPane();
        tfNomeSegurado = new javax.swing.JTextArea();
        kGradientPanel17 = new keeptoo.KGradientPanel();
        kGradientPanel9 = new keeptoo.KGradientPanel();
        jLabel4 = new javax.swing.JLabel();
        kGradientPanel11 = new keeptoo.KGradientPanel();
        kGAlterarTerceiro = new keeptoo.KGradientPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbTerceiros = new javax.swing.JTable();
        kGradientPanel18 = new keeptoo.KGradientPanel();
        kGradientPanel14 = new keeptoo.KGradientPanel();
        kGradientPanel6 = new keeptoo.KGradientPanel();
        kGradientPanel12 = new keeptoo.KGradientPanel();
        kGradientPanel22 = new keeptoo.KGradientPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();

        tfCodTerceiro.setEditable(false);
        tfCodTerceiro.setEnabled(false);
        tfCodTerceiro.setPreferredSize(new java.awt.Dimension(30, 30));

        cbSituacaoPgtoProcesso.setForeground(new java.awt.Color(51, 51, 51));
        cbSituacaoPgtoProcesso.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<selecione>", "Aberto", "Em andamento", "Pendente", "Concluido" }));
        cbSituacaoPgtoProcesso.setSelectedIndex(-1);
        cbSituacaoPgtoProcesso.setEnabled(false);
        cbSituacaoPgtoProcesso.setFocusable(false);
        cbSituacaoPgtoProcesso.setMinimumSize(new java.awt.Dimension(150, 28));
        cbSituacaoPgtoProcesso.setPreferredSize(new java.awt.Dimension(220, 28));

        cbCodServico.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbCodServico.setSelectedIndex(-1);
        cbCodServico.setEnabled(false);
        cbCodServico.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbCodServicoPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        cbCodSeguradora.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<selecione>", "Item 2", "Item 3", "Item 4" }));
        cbCodSeguradora.setSelectedIndex(-1);
        cbCodSeguradora.setEnabled(false);
        cbCodSeguradora.setMinimumSize(new java.awt.Dimension(100, 20));
        cbCodSeguradora.setPreferredSize(new java.awt.Dimension(100, 20));
        cbCodSeguradora.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbCodSeguradoraPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        cbUf.setForeground(new java.awt.Color(51, 51, 51));
        cbUf.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<selecione>", "Item 2", "Item 3", "Item 4" }));
        cbUf.setSelectedIndex(-1);
        cbUf.setMinimumSize(new java.awt.Dimension(100, 24));
        cbUf.setPreferredSize(new java.awt.Dimension(100, 24));
        cbUf.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cbUfFocusLost(evt);
            }
        });
        cbUf.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbUfPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                cbUfPopupMenuWillBecomeVisible(evt);
            }
        });

        cbCidade.setForeground(new java.awt.Color(51, 51, 51));
        cbCidade.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<selecione>", "Item 2", "Item 3", "Item 4" }));
        cbCidade.setSelectedIndex(-1);
        cbCidade.setMinimumSize(new java.awt.Dimension(150, 24));
        cbCidade.setPreferredSize(new java.awt.Dimension(150, 24));
        cbCidade.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
                cbCidadePopupMenuCanceled(evt);
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbCidadePopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                cbCidadePopupMenuWillBecomeVisible(evt);
            }
        });

        cbServico.setMaximumRowCount(100);
        cbServico.setToolTipText("");
        cbServico.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbServicoPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        cbSeguradoras.setForeground(new java.awt.Color(51, 51, 51));
        cbSeguradoras.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<selecione>", "Item 2", "Item 3", "Item 4" }));
        cbSeguradoras.setSelectedIndex(-1);
        cbSeguradoras.setMinimumSize(new java.awt.Dimension(420, 28));
        cbSeguradoras.setPreferredSize(new java.awt.Dimension(420, 28));
        cbSeguradoras.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
                cbSeguradorasPopupMenuCanceled(evt);
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbSeguradorasPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                cbSeguradorasPopupMenuWillBecomeVisible(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("VISUALIZAR PROCESSO");
        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1300, 660));
        setPreferredSize(new java.awt.Dimension(1300, 660));
        setResizable(false);

        kGradientPanel1.setBackground(new java.awt.Color(255, 255, 255));
        kGradientPanel1.setkBorderRadius(0);
        kGradientPanel1.setkEndColor(new java.awt.Color(255, 255, 255));
        kGradientPanel1.setkGradientFocus(0);
        kGradientPanel1.setkStartColor(new java.awt.Color(255, 255, 255));
        kGradientPanel1.setPreferredSize(new java.awt.Dimension(1300, 720));
        kGradientPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTPanelProcessos.setBackground(new java.awt.Color(255, 255, 255));
        jTPanelProcessos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTPanelProcessos.setMinimumSize(new java.awt.Dimension(1300, 680));
        jTPanelProcessos.setOpaque(true);
        jTPanelProcessos.setPreferredSize(new java.awt.Dimension(1300, 660));
        jTPanelProcessos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTPanelProcessosMouseClicked(evt);
            }
        });
        jTPanelProcessos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTPanelProcessosKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTPanelProcessosKeyTyped(evt);
            }
        });

        jPanelEntradadeProcessos.setBackground(new java.awt.Color(255, 255, 255));
        jPanelEntradadeProcessos.setInheritsPopupMenu(true);
        jPanelEntradadeProcessos.setMinimumSize(new java.awt.Dimension(1290, 670));
        jPanelEntradadeProcessos.setPreferredSize(new java.awt.Dimension(1290, 670));

        kGSeguradoraSolicitante.setBackground(new java.awt.Color(146, 171, 197));
        kGSeguradoraSolicitante.setForeground(new java.awt.Color(51, 51, 51));
        kGSeguradoraSolicitante.setkBorderRadius(0);
        kGSeguradoraSolicitante.setkEndColor(new java.awt.Color(234, 239, 243));
        kGSeguradoraSolicitante.setkGradientFocus(0);
        kGSeguradoraSolicitante.setkStartColor(new java.awt.Color(255, 255, 255));
        kGSeguradoraSolicitante.setMinimumSize(new java.awt.Dimension(1280, 110));
        kGSeguradoraSolicitante.setPreferredSize(new java.awt.Dimension(1290, 80));
        kGSeguradoraSolicitante.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLNomeSeguradora.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLNomeSeguradora.setForeground(new java.awt.Color(51, 51, 51));
        jLNomeSeguradora.setText("Nome Seguradora:");
        kGSeguradoraSolicitante.add(jLNomeSeguradora, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, 280, 20));

        jLDataEntrada.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLDataEntrada.setForeground(new java.awt.Color(51, 51, 51));
        jLDataEntrada.setText("Data da Entrada:");
        kGSeguradoraSolicitante.add(jLDataEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 20, 130, 20));

        jLDataSaida.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLDataSaida.setForeground(new java.awt.Color(51, 51, 51));
        jLDataSaida.setText("Data da Saída:");
        kGSeguradoraSolicitante.add(jLDataSaida, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 20, 130, 20));

        jLNumeroProcesso.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLNumeroProcesso.setForeground(new java.awt.Color(51, 51, 51));
        jLNumeroProcesso.setText("Nº do Proceso:");
        kGSeguradoraSolicitante.add(jLNumeroProcesso, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, -1, 20));

        jLCnpj1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLCnpj1.setForeground(new java.awt.Color(51, 51, 51));
        jLCnpj1.setText("CNPJ:");
        kGSeguradoraSolicitante.add(jLCnpj1, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 20, 40, 20));

        jfCNPJ.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jfCNPJ.setForeground(new java.awt.Color(0, 51, 153));
        jfCNPJ.setText("CNPJ");
        kGSeguradoraSolicitante.add(jfCNPJ, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 40, 290, 20));

        jFDataEntrada.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jFDataEntrada.setForeground(new java.awt.Color(0, 51, 153));
        jFDataEntrada.setText("Não Informado.");
        kGSeguradoraSolicitante.add(jFDataEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 40, 150, 20));

        jFDataSaida.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jFDataSaida.setForeground(new java.awt.Color(0, 51, 153));
        jFDataSaida.setText("Não Informado.");
        kGSeguradoraSolicitante.add(jFDataSaida, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 40, 150, 20));

        tfCodigo.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        tfCodigo.setForeground(new java.awt.Color(102, 0, 51));
        tfCodigo.setText("COD.");
        kGSeguradoraSolicitante.add(tfCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 110, 20));

        jScrollPane5.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane5.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jLPuxarnomeSeguradora.setEditable(false);
        jLPuxarnomeSeguradora.setBackground(new java.awt.Color(242, 245, 247));
        jLPuxarnomeSeguradora.setColumns(20);
        jLPuxarnomeSeguradora.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLPuxarnomeSeguradora.setRows(5);
        jLPuxarnomeSeguradora.setText("\n");
        jScrollPane5.setViewportView(jLPuxarnomeSeguradora);

        kGSeguradoraSolicitante.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 40, 440, 25));

        jPanelEntradadeProcessos.add(kGSeguradoraSolicitante);

        kGradientPanel15.setBackground(new java.awt.Color(146, 171, 197));
        kGradientPanel15.setkBorderRadius(0);
        kGradientPanel15.setkEndColor(new java.awt.Color(255, 255, 255));
        kGradientPanel15.setkStartColor(new java.awt.Color(255, 255, 255));
        kGradientPanel15.setName(""); // NOI18N
        kGradientPanel15.setPreferredSize(new java.awt.Dimension(1300, 20));

        kGradientPanel7.setkBorderRadius(0);
        kGradientPanel7.setkEndColor(new java.awt.Color(255, 255, 255));
        kGradientPanel7.setkGradientFocus(125);
        kGradientPanel7.setkStartColor(new java.awt.Color(234, 239, 243));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(66, 66, 66));
        jLabel2.setText("SINISTRO");

        org.jdesktop.layout.GroupLayout kGradientPanel7Layout = new org.jdesktop.layout.GroupLayout(kGradientPanel7);
        kGradientPanel7.setLayout(kGradientPanel7Layout);
        kGradientPanel7Layout.setHorizontalGroup(
            kGradientPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(kGradientPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel2)
                .addContainerGap(186, Short.MAX_VALUE))
        );
        kGradientPanel7Layout.setVerticalGroup(
            kGradientPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(kGradientPanel7Layout.createSequentialGroup()
                .add(jLabel2)
                .add(0, 3, Short.MAX_VALUE))
        );

        kGradientPanel13.setkBorderRadius(0);
        kGradientPanel13.setkEndColor(new java.awt.Color(234, 239, 243));
        kGradientPanel13.setkGradientFocus(250);
        kGradientPanel13.setkStartColor(new java.awt.Color(255, 255, 255));

        org.jdesktop.layout.GroupLayout kGradientPanel13Layout = new org.jdesktop.layout.GroupLayout(kGradientPanel13);
        kGradientPanel13.setLayout(kGradientPanel13Layout);
        kGradientPanel13Layout.setHorizontalGroup(
            kGradientPanel13Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 380, Short.MAX_VALUE)
        );
        kGradientPanel13Layout.setVerticalGroup(
            kGradientPanel13Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 20, Short.MAX_VALUE)
        );

        org.jdesktop.layout.GroupLayout kGradientPanel15Layout = new org.jdesktop.layout.GroupLayout(kGradientPanel15);
        kGradientPanel15.setLayout(kGradientPanel15Layout);
        kGradientPanel15Layout.setHorizontalGroup(
            kGradientPanel15Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(kGradientPanel15Layout.createSequentialGroup()
                .add(kGradientPanel7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 271, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 649, Short.MAX_VALUE)
                .add(kGradientPanel13, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
        kGradientPanel15Layout.setVerticalGroup(
            kGradientPanel15Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, kGradientPanel15Layout.createSequentialGroup()
                .add(0, 0, Short.MAX_VALUE)
                .add(kGradientPanel15Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, kGradientPanel7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(kGradientPanel13, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
        );

        jPanelEntradadeProcessos.add(kGradientPanel15);

        kGPDadosSinistro.setBackground(new java.awt.Color(146, 171, 197));
        kGPDadosSinistro.setkBorderRadius(0);
        kGPDadosSinistro.setkEndColor(new java.awt.Color(234, 239, 243));
        kGPDadosSinistro.setkGradientFocus(0);
        kGPDadosSinistro.setkStartColor(new java.awt.Color(255, 255, 255));
        kGPDadosSinistro.setMinimumSize(new java.awt.Dimension(1280, 180));
        kGPDadosSinistro.setPreferredSize(new java.awt.Dimension(1290, 160));
        kGPDadosSinistro.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLAnalista.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLAnalista.setForeground(new java.awt.Color(51, 51, 51));
        jLAnalista.setText("Analista:");
        kGPDadosSinistro.add(jLAnalista, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 15, 150, -1));

        jLCobertura.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLCobertura.setForeground(new java.awt.Color(51, 51, 51));
        jLCobertura.setText("Cobertura");
        kGPDadosSinistro.add(jLCobertura, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 70, 10));

        jLDetalhesSinistro.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLDetalhesSinistro.setForeground(new java.awt.Color(51, 51, 51));
        jLDetalhesSinistro.setText("Detalhes do Sinistro:");
        kGPDadosSinistro.add(jLDetalhesSinistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 70, 190, -1));

        jLEstado.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLEstado.setForeground(new java.awt.Color(51, 51, 51));
        jLEstado.setText("Estado:");
        kGPDadosSinistro.add(jLEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 70, 80, 10));

        jLCidade.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLCidade.setForeground(new java.awt.Color(51, 51, 51));
        jLCidade.setText("Cidade:");
        kGPDadosSinistro.add(jLCidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 70, 100, 10));

        jLBairro.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLBairro.setForeground(new java.awt.Color(51, 51, 51));
        jLBairro.setText("Endereço:");
        kGPDadosSinistro.add(jLBairro, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 120, -1));

        jLHoraSinistro.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLHoraSinistro.setForeground(new java.awt.Color(51, 51, 51));
        jLHoraSinistro.setText("Hora do Sinistro:");
        kGPDadosSinistro.add(jLHoraSinistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 15, 110, -1));

        jLDataSinistro.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLDataSinistro.setForeground(new java.awt.Color(51, 51, 51));
        jLDataSinistro.setText("Data do Sinistro:");
        kGPDadosSinistro.add(jLDataSinistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 15, 130, -1));

        jLNumeroSinistro.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLNumeroSinistro.setForeground(new java.awt.Color(51, 51, 51));
        jLNumeroSinistro.setText("Número do Sinistro:");
        kGPDadosSinistro.add(jLNumeroSinistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 15, 130, -1));

        jLNomeCobertura.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLNomeCobertura.setForeground(new java.awt.Color(102, 102, 102));
        jLNomeCobertura.setText("COBERTURA");
        kGPDadosSinistro.add(jLNomeCobertura, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, 330, 30));

        jFDataSinistro.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jFDataSinistro.setForeground(new java.awt.Color(0, 51, 102));
        jFDataSinistro.setText("Não Informado.");
        kGPDadosSinistro.add(jFDataSinistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 25, 140, 30));

        jFHoraSinistro.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jFHoraSinistro.setForeground(new java.awt.Color(0, 51, 102));
        jFHoraSinistro.setText("Não Informado.");
        kGPDadosSinistro.add(jFHoraSinistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 25, 130, 30));

        jLNomeEstado.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLNomeEstado.setForeground(new java.awt.Color(102, 102, 102));
        jLNomeEstado.setText("ESTADO");
        kGPDadosSinistro.add(jLNomeEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 80, 100, 30));

        jScrollPane6.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane6.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jLNomeCidade.setEditable(false);
        jLNomeCidade.setBackground(new java.awt.Color(242, 245, 247));
        jLNomeCidade.setColumns(20);
        jLNomeCidade.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLNomeCidade.setRows(5);
        jLNomeCidade.setText("\n");
        jLNomeCidade.setPreferredSize(new java.awt.Dimension(164, 60));
        jScrollPane6.setViewportView(jLNomeCidade);

        kGPDadosSinistro.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 85, 230, 25));

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jTObsSinistro.setEditable(false);
        jTObsSinistro.setBackground(new java.awt.Color(242, 245, 247));
        jTObsSinistro.setColumns(20);
        jTObsSinistro.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTObsSinistro.setRows(5);
        jTObsSinistro.setText("Não Informado.");
        jScrollPane2.setViewportView(jTObsSinistro);

        kGPDadosSinistro.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 90, 510, 60));

        kGradientPanel2.setkBorderRadius(0);
        kGradientPanel2.setkEndColor(new java.awt.Color(234, 239, 243));
        kGradientPanel2.setkGradientFocus(0);
        kGradientPanel2.setkStartColor(new java.awt.Color(255, 255, 255));
        kGradientPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPaneAnalista.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPaneAnalista.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPaneAnalista.setPreferredSize(new java.awt.Dimension(675, 25));

        tfAnalista.setEditable(false);
        tfAnalista.setBackground(new java.awt.Color(242, 245, 247));
        tfAnalista.setColumns(20);
        tfAnalista.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tfAnalista.setRows(5);
        tfAnalista.setText("\n");
        jScrollPaneAnalista.setViewportView(tfAnalista);

        kGradientPanel2.add(jScrollPaneAnalista, new org.netbeans.lib.awtextra.AbsoluteConstraints(245, 30, 670, -1));

        jScrollPaneAnalista1.setForeground(new java.awt.Color(102, 0, 0));
        jScrollPaneAnalista1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPaneAnalista1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPaneAnalista1.setPreferredSize(new java.awt.Dimension(675, 25));

        tfNumSinistro.setEditable(false);
        tfNumSinistro.setBackground(new java.awt.Color(242, 245, 247));
        tfNumSinistro.setColumns(20);
        tfNumSinistro.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tfNumSinistro.setForeground(new java.awt.Color(102, 0, 0));
        tfNumSinistro.setRows(5);
        tfNumSinistro.setText("\n");
        jScrollPaneAnalista1.setViewportView(tfNumSinistro);

        kGradientPanel2.add(jScrollPaneAnalista1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 180, -1));

        kGPDadosSinistro.add(kGradientPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1290, 60));

        kGradientPanel3.setkBorderRadius(0);
        kGradientPanel3.setkEndColor(new java.awt.Color(255, 255, 255));
        kGradientPanel3.setkGradientFocus(0);
        kGradientPanel3.setkStartColor(new java.awt.Color(234, 239, 243));
        kGradientPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane4.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane4.setPreferredSize(new java.awt.Dimension(670, 25));

        tfBairro.setEditable(false);
        tfBairro.setBackground(new java.awt.Color(242, 245, 247));
        tfBairro.setColumns(20);
        tfBairro.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tfBairro.setRows(5);
        tfBairro.setText("\n");
        jScrollPane4.setViewportView(tfBairro);

        kGradientPanel3.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 15, 670, 25));

        kGPDadosSinistro.add(kGradientPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 710, 50));

        jPanelEntradadeProcessos.add(kGPDadosSinistro);

        kGradientPanel16.setBackground(new java.awt.Color(146, 171, 197));
        kGradientPanel16.setkBorderRadius(0);
        kGradientPanel16.setkEndColor(new java.awt.Color(255, 255, 255));
        kGradientPanel16.setkStartColor(new java.awt.Color(255, 255, 255));
        kGradientPanel16.setName(""); // NOI18N
        kGradientPanel16.setPreferredSize(new java.awt.Dimension(1300, 20));

        kGradientPanel8.setkBorderRadius(0);
        kGradientPanel8.setkEndColor(new java.awt.Color(255, 255, 255));
        kGradientPanel8.setkGradientFocus(125);
        kGradientPanel8.setkStartColor(new java.awt.Color(234, 239, 243));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(66, 66, 66));
        jLabel3.setText("SEGURADO");

        org.jdesktop.layout.GroupLayout kGradientPanel8Layout = new org.jdesktop.layout.GroupLayout(kGradientPanel8);
        kGradientPanel8.setLayout(kGradientPanel8Layout);
        kGradientPanel8Layout.setHorizontalGroup(
            kGradientPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(kGradientPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel3)
                .addContainerGap(180, Short.MAX_VALUE))
        );
        kGradientPanel8Layout.setVerticalGroup(
            kGradientPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(kGradientPanel8Layout.createSequentialGroup()
                .add(jLabel3)
                .add(0, 3, Short.MAX_VALUE))
        );

        kGradientPanel10.setkBorderRadius(0);
        kGradientPanel10.setkEndColor(new java.awt.Color(234, 239, 243));
        kGradientPanel10.setkGradientFocus(250);
        kGradientPanel10.setkStartColor(new java.awt.Color(255, 255, 255));

        org.jdesktop.layout.GroupLayout kGradientPanel10Layout = new org.jdesktop.layout.GroupLayout(kGradientPanel10);
        kGradientPanel10.setLayout(kGradientPanel10Layout);
        kGradientPanel10Layout.setHorizontalGroup(
            kGradientPanel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 831, Short.MAX_VALUE)
        );
        kGradientPanel10Layout.setVerticalGroup(
            kGradientPanel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 20, Short.MAX_VALUE)
        );

        org.jdesktop.layout.GroupLayout kGradientPanel16Layout = new org.jdesktop.layout.GroupLayout(kGradientPanel16);
        kGradientPanel16.setLayout(kGradientPanel16Layout);
        kGradientPanel16Layout.setHorizontalGroup(
            kGradientPanel16Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(kGradientPanel16Layout.createSequentialGroup()
                .add(kGradientPanel8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 271, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 198, Short.MAX_VALUE)
                .add(kGradientPanel10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 831, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
        kGradientPanel16Layout.setVerticalGroup(
            kGradientPanel16Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, kGradientPanel16Layout.createSequentialGroup()
                .add(0, 0, Short.MAX_VALUE)
                .add(kGradientPanel16Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, kGradientPanel8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, kGradientPanel10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
        );

        jPanelEntradadeProcessos.add(kGradientPanel16);

        kGPDadosSegurado.setBackground(new java.awt.Color(172, 193, 212));
        kGPDadosSegurado.setkBorderRadius(0);
        kGPDadosSegurado.setkEndColor(new java.awt.Color(234, 239, 243));
        kGPDadosSegurado.setkGradientFocus(0);
        kGPDadosSegurado.setkStartColor(new java.awt.Color(255, 255, 255));
        kGPDadosSegurado.setMinimumSize(new java.awt.Dimension(1280, 100));
        kGPDadosSegurado.setPreferredSize(new java.awt.Dimension(1290, 110));
        kGPDadosSegurado.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLNomeSegurado.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLNomeSegurado.setForeground(new java.awt.Color(51, 51, 51));
        jLNomeSegurado.setText("Nome do Segurado");
        kGPDadosSegurado.add(jLNomeSegurado, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 140, 10));

        jLPlacaSegurado.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLPlacaSegurado.setForeground(new java.awt.Color(51, 51, 51));
        jLPlacaSegurado.setText("Placa Segurado");
        kGPDadosSegurado.add(jLPlacaSegurado, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 100, 10));

        jLObsSegurado.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLObsSegurado.setForeground(new java.awt.Color(51, 51, 51));
        jLObsSegurado.setText("Observações");
        kGPDadosSegurado.add(jLObsSegurado, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 10, 160, 20));

        tfSeguradoPlaca.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        tfSeguradoPlaca.setForeground(new java.awt.Color(0, 51, 102));
        tfSeguradoPlaca.setText("Não Informado.");
        kGPDadosSegurado.add(tfSeguradoPlaca, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 210, 30));

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        tfObsSegurado.setEditable(false);
        tfObsSegurado.setBackground(new java.awt.Color(242, 245, 247));
        tfObsSegurado.setColumns(20);
        tfObsSegurado.setRows(5);
        tfObsSegurado.setText("Não Informado.");
        jScrollPane1.setViewportView(tfObsSegurado);

        kGPDadosSegurado.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 30, 510, 70));

        jScrollPane8.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane8.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane8.setPreferredSize(new java.awt.Dimension(650, 25));

        tfNomeSegurado.setEditable(false);
        tfNomeSegurado.setBackground(new java.awt.Color(242, 245, 247));
        tfNomeSegurado.setColumns(20);
        tfNomeSegurado.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tfNomeSegurado.setRows(5);
        tfNomeSegurado.setText("\n");
        tfNomeSegurado.setPreferredSize(new java.awt.Dimension(224, 69));
        jScrollPane8.setViewportView(tfNomeSegurado);

        kGPDadosSegurado.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 33, 650, 25));

        jPanelEntradadeProcessos.add(kGPDadosSegurado);

        kGradientPanel17.setBackground(new java.awt.Color(146, 171, 197));
        kGradientPanel17.setkBorderRadius(0);
        kGradientPanel17.setkEndColor(new java.awt.Color(255, 255, 255));
        kGradientPanel17.setkStartColor(new java.awt.Color(255, 255, 255));
        kGradientPanel17.setName(""); // NOI18N
        kGradientPanel17.setPreferredSize(new java.awt.Dimension(1300, 20));

        kGradientPanel9.setkBorderRadius(0);
        kGradientPanel9.setkEndColor(new java.awt.Color(255, 255, 255));
        kGradientPanel9.setkGradientFocus(125);
        kGradientPanel9.setkStartColor(new java.awt.Color(234, 239, 243));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(66, 66, 66));
        jLabel4.setText("TERCEIROS");

        org.jdesktop.layout.GroupLayout kGradientPanel9Layout = new org.jdesktop.layout.GroupLayout(kGradientPanel9);
        kGradientPanel9.setLayout(kGradientPanel9Layout);
        kGradientPanel9Layout.setHorizontalGroup(
            kGradientPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(kGradientPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel4)
                .addContainerGap(175, Short.MAX_VALUE))
        );
        kGradientPanel9Layout.setVerticalGroup(
            kGradientPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(kGradientPanel9Layout.createSequentialGroup()
                .add(jLabel4)
                .add(0, 3, Short.MAX_VALUE))
        );

        kGradientPanel11.setkBorderRadius(0);
        kGradientPanel11.setkEndColor(new java.awt.Color(234, 239, 243));
        kGradientPanel11.setkGradientFocus(250);
        kGradientPanel11.setkStartColor(new java.awt.Color(255, 255, 255));

        org.jdesktop.layout.GroupLayout kGradientPanel11Layout = new org.jdesktop.layout.GroupLayout(kGradientPanel11);
        kGradientPanel11.setLayout(kGradientPanel11Layout);
        kGradientPanel11Layout.setHorizontalGroup(
            kGradientPanel11Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 831, Short.MAX_VALUE)
        );
        kGradientPanel11Layout.setVerticalGroup(
            kGradientPanel11Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 20, Short.MAX_VALUE)
        );

        org.jdesktop.layout.GroupLayout kGradientPanel17Layout = new org.jdesktop.layout.GroupLayout(kGradientPanel17);
        kGradientPanel17.setLayout(kGradientPanel17Layout);
        kGradientPanel17Layout.setHorizontalGroup(
            kGradientPanel17Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(kGradientPanel17Layout.createSequentialGroup()
                .add(kGradientPanel9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 271, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 198, Short.MAX_VALUE)
                .add(kGradientPanel11, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 831, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
        kGradientPanel17Layout.setVerticalGroup(
            kGradientPanel17Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, kGradientPanel17Layout.createSequentialGroup()
                .add(0, 0, Short.MAX_VALUE)
                .add(kGradientPanel17Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, kGradientPanel9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, kGradientPanel11, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
        );

        jPanelEntradadeProcessos.add(kGradientPanel17);

        kGAlterarTerceiro.setBackground(new java.awt.Color(146, 171, 197));
        kGAlterarTerceiro.setForeground(new java.awt.Color(51, 51, 51));
        kGAlterarTerceiro.setkBorderRadius(0);
        kGAlterarTerceiro.setkEndColor(new java.awt.Color(234, 239, 243));
        kGAlterarTerceiro.setkGradientFocus(0);
        kGAlterarTerceiro.setkStartColor(new java.awt.Color(255, 255, 255));
        kGAlterarTerceiro.setMinimumSize(new java.awt.Dimension(1280, 160));
        kGAlterarTerceiro.setPreferredSize(new java.awt.Dimension(1290, 140));
        kGAlterarTerceiro.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbTerceiros.setAutoCreateRowSorter(true);
        tbTerceiros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cod.", "Nome Terceiro", "Placa", "Observações"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbTerceiros.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tbTerceiros);
        if (tbTerceiros.getColumnModel().getColumnCount() > 0) {
            tbTerceiros.getColumnModel().getColumn(0).setMinWidth(60);
            tbTerceiros.getColumnModel().getColumn(0).setPreferredWidth(60);
            tbTerceiros.getColumnModel().getColumn(0).setMaxWidth(60);
            tbTerceiros.getColumnModel().getColumn(2).setMinWidth(100);
            tbTerceiros.getColumnModel().getColumn(2).setPreferredWidth(100);
            tbTerceiros.getColumnModel().getColumn(2).setMaxWidth(100);
        }

        kGAlterarTerceiro.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 1200, 100));

        jPanelEntradadeProcessos.add(kGAlterarTerceiro);

        kGradientPanel18.setBackground(new java.awt.Color(255, 255, 255));
        kGradientPanel18.setkBorderRadius(0);
        kGradientPanel18.setkEndColor(new java.awt.Color(255, 255, 255));
        kGradientPanel18.setkStartColor(new java.awt.Color(255, 255, 255));
        kGradientPanel18.setName(""); // NOI18N
        kGradientPanel18.setPreferredSize(new java.awt.Dimension(1300, 10));

        org.jdesktop.layout.GroupLayout kGradientPanel18Layout = new org.jdesktop.layout.GroupLayout(kGradientPanel18);
        kGradientPanel18.setLayout(kGradientPanel18Layout);
        kGradientPanel18Layout.setHorizontalGroup(
            kGradientPanel18Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 1300, Short.MAX_VALUE)
        );
        kGradientPanel18Layout.setVerticalGroup(
            kGradientPanel18Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 10, Short.MAX_VALUE)
        );

        jPanelEntradadeProcessos.add(kGradientPanel18);

        jTPanelProcessos.addTab("INFORMAÇÕES DO PROCESSO", jPanelEntradadeProcessos);

        kGradientPanel1.add(jTPanelProcessos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -30, -1, 640));
        jTPanelProcessos.getAccessibleContext().setAccessibleName("Cadastro Ordem de Processos");

        getContentPane().add(kGradientPanel1, java.awt.BorderLayout.CENTER);

        kGradientPanel14.setBackground(new java.awt.Color(255, 255, 255));
        kGradientPanel14.setkBorderRadius(0);
        kGradientPanel14.setkEndColor(new java.awt.Color(146, 171, 197));
        kGradientPanel14.setkStartColor(new java.awt.Color(146, 171, 197));
        kGradientPanel14.setName(""); // NOI18N
        kGradientPanel14.setPreferredSize(new java.awt.Dimension(1300, 40));

        kGradientPanel6.setkBorderRadius(0);
        kGradientPanel6.setkEndColor(new java.awt.Color(255, 255, 255));
        kGradientPanel6.setkGradientFocus(125);
        kGradientPanel6.setkStartColor(new java.awt.Color(234, 239, 243));

        org.jdesktop.layout.GroupLayout kGradientPanel6Layout = new org.jdesktop.layout.GroupLayout(kGradientPanel6);
        kGradientPanel6.setLayout(kGradientPanel6Layout);
        kGradientPanel6Layout.setHorizontalGroup(
            kGradientPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 271, Short.MAX_VALUE)
        );
        kGradientPanel6Layout.setVerticalGroup(
            kGradientPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 20, Short.MAX_VALUE)
        );

        kGradientPanel12.setkBorderRadius(0);
        kGradientPanel12.setkEndColor(new java.awt.Color(234, 239, 243));
        kGradientPanel12.setkGradientFocus(250);
        kGradientPanel12.setkStartColor(new java.awt.Color(255, 255, 255));

        org.jdesktop.layout.GroupLayout kGradientPanel12Layout = new org.jdesktop.layout.GroupLayout(kGradientPanel12);
        kGradientPanel12.setLayout(kGradientPanel12Layout);
        kGradientPanel12Layout.setHorizontalGroup(
            kGradientPanel12Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 380, Short.MAX_VALUE)
        );
        kGradientPanel12Layout.setVerticalGroup(
            kGradientPanel12Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 20, Short.MAX_VALUE)
        );

        kGradientPanel22.setkBorderRadius(0);
        kGradientPanel22.setkEndColor(new java.awt.Color(146, 171, 197));
        kGradientPanel22.setkGradientFocus(125);
        kGradientPanel22.setkStartColor(new java.awt.Color(255, 255, 255));

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 51, 102));
        jLabel27.setText("VER PROCESSO");

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(0, 51, 102));
        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/24x24/Text preview.png"))); // NOI18N
        jLabel28.setPreferredSize(new java.awt.Dimension(60, 60));

        org.jdesktop.layout.GroupLayout kGradientPanel22Layout = new org.jdesktop.layout.GroupLayout(kGradientPanel22);
        kGradientPanel22.setLayout(kGradientPanel22Layout);
        kGradientPanel22Layout.setHorizontalGroup(
            kGradientPanel22Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, kGradientPanel22Layout.createSequentialGroup()
                .add(17, 17, 17)
                .add(jLabel28, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 26, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jLabel27, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                .add(7, 7, 7))
        );
        kGradientPanel22Layout.setVerticalGroup(
            kGradientPanel22Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(kGradientPanel22Layout.createSequentialGroup()
                .add(8, 8, 8)
                .add(kGradientPanel22Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel27, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jLabel28, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE))
                .addContainerGap())
        );

        org.jdesktop.layout.GroupLayout kGradientPanel14Layout = new org.jdesktop.layout.GroupLayout(kGradientPanel14);
        kGradientPanel14.setLayout(kGradientPanel14Layout);
        kGradientPanel14Layout.setHorizontalGroup(
            kGradientPanel14Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(kGradientPanel14Layout.createSequentialGroup()
                .add(68, 68, 68)
                .add(kGradientPanel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 271, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 581, Short.MAX_VALUE)
                .add(kGradientPanel12, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
            .add(kGradientPanel14Layout.createSequentialGroup()
                .add(kGradientPanel22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 179, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(0, 0, Short.MAX_VALUE))
        );
        kGradientPanel14Layout.setVerticalGroup(
            kGradientPanel14Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(kGradientPanel14Layout.createSequentialGroup()
                .add(kGradientPanel22, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                .add(6, 6, 6)
                .add(kGradientPanel14Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, kGradientPanel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, kGradientPanel12, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
        );

        getContentPane().add(kGradientPanel14, java.awt.BorderLayout.PAGE_START);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tfPlacaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfPlacaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfPlacaActionPerformed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7ActionPerformed
    private void retornarDadosSeguradoras() {
        modelSeguradora = controllerSeguradora.getSeguradoraController(Integer.parseInt(cbCodSeguradora.getSelectedItem().toString()));
        //recupera o nome
        this.cbSeguradoras.setSelectedItem(String.valueOf(modelSeguradora.getNome()));
        this.jfCNPJ.setText(modelSeguradora.getCnpj());

    }

    private void testeverificaseg() {
        modelSeguradora = controllerSeguradora.getSeguradoraController(cbSeguradoras.getSelectedItem().toString());
        //recupera o código e seta CNPJ
        this.cbCodSeguradora.setSelectedItem(modelSeguradora.getCodigo());
        this.jfCNPJ.setText(modelSeguradora.getCnpj());
    }

    private void jTPanelProcessosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTPanelProcessosKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTPanelProcessosKeyPressed

    private void jTPanelProcessosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTPanelProcessosMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTPanelProcessosMouseClicked

    private void jTPanelProcessosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTPanelProcessosKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTPanelProcessosKeyTyped

    private void cbServicoPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbServicoPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
        if (this.cbServico.isPopupVisible()) {
            modelServicos = controllerServicos.getServicosController(cbServico.getSelectedItem().toString());
            //recupera o nome
            this.cbServico.setSelectedItem(cbServico.getSelectedItem());
            this.cbCodServico.setSelectedItem(String.valueOf(modelServicos.getCodigo()));
        }
    }//GEN-LAST:event_cbServicoPopupMenuWillBecomeInvisible

    private void cbCodServicoPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbCodServicoPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
        if (this.cbCodServico.isPopupVisible()) {
            modelServicos = controllerServicos.getServicosController(Integer.parseInt(cbCodServico.getSelectedItem().toString()));
            //recupera o código
            //this.cbServico.setSelectedItem(String.valueOf(modelServicos.getNome()));
        }
    }//GEN-LAST:event_cbCodServicoPopupMenuWillBecomeInvisible

    private void cbUfPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbUfPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
        if (this.cbUf.isPopupVisible()) {
            listarCidades();
        }
    }//GEN-LAST:event_cbUfPopupMenuWillBecomeInvisible

    private void cbCidadePopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbCidadePopupMenuWillBecomeInvisible
        // APENAS SETAR NA CIDADE TOMADORA
    }//GEN-LAST:event_cbCidadePopupMenuWillBecomeInvisible

    private void cbCidadePopupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbCidadePopupMenuCanceled
        // TODO add your handling code here:
    }//GEN-LAST:event_cbCidadePopupMenuCanceled

    private void cbSeguradorasPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbSeguradorasPopupMenuWillBecomeVisible
        // TODO add your handling code here:
    }//GEN-LAST:event_cbSeguradorasPopupMenuWillBecomeVisible

    private void cbSeguradorasPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbSeguradorasPopupMenuWillBecomeInvisible
        // ESCOLHER SEGURADORA
        modelSeguradora = controllerSeguradora.getSeguradoraController(cbSeguradoras.getSelectedItem().toString());
        //recupera o código e seta CNPJ
        this.cbCodSeguradora.setSelectedItem(modelSeguradora.getCodigo());

    }//GEN-LAST:event_cbSeguradorasPopupMenuWillBecomeInvisible

    private void cbSeguradorasPopupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbSeguradorasPopupMenuCanceled
        // TODO add your handling code here:
    }//GEN-LAST:event_cbSeguradorasPopupMenuCanceled

    private void cbCodSeguradoraPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbCodSeguradoraPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
        if (this.cbCodSeguradora.isPopupVisible()) {
            this.retornarSeguradoraPeloCodigo();
        }
    }//GEN-LAST:event_cbCodSeguradoraPopupMenuWillBecomeInvisible

    private void cbUfPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbUfPopupMenuWillBecomeVisible
        // TODO add your handling code here:
        this.listarEstados();
    }//GEN-LAST:event_cbUfPopupMenuWillBecomeVisible

    private void cbCidadePopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbCidadePopupMenuWillBecomeVisible
        // TODO add your handling code here:
        this.listarCidades();
    }//GEN-LAST:event_cbCidadePopupMenuWillBecomeVisible

    private void cbUfFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cbUfFocusLost
        // TODO add your handling code here:
        listarCidades();
    }//GEN-LAST:event_cbUfFocusLost
    //usa apenas para carregar op dados da interface quando abre o software

    private void retornarSeguradoraPeloCodigo2(ModelOp model) {

        modelSeguradora = controllerSeguradora.getSeguradoraController(Integer.parseInt(cbCodSeguradora.getSelectedItem().toString()));
        //recupera 
        this.cbSeguradoras.setSelectedItem(String.valueOf(modelSeguradora.getNome()));
        this.jfCNPJ.setText(modelSeguradora.getCnpj());
    }

    //usa apenas para carregar op dados da interface quando abre o software
    private void retornarSeguradoraPeloCodigo() {
        modelSeguradora = controllerSeguradora.getSeguradoraController(cbCodSeguradora.getSelectedItem().toString());
        this.cbSeguradoras.setSelectedItem(String.valueOf(modelSeguradora.getNome()));
        jfCNPJ.setText(modelSeguradora.getCnpj());
    }
    // calcula o valor total do produto com quantidade

    private void listarCodigoServicos() {
        listaModelServicos = controllerServicos.getListaServicosController();
        cbCodServico.removeAllItems();
        for (int i = 0; i < listaModelServicos.size(); i++) {
            cbCodServico.addItem(String.valueOf(listaModelServicos.get(i).getCodigo()));
        }
    }

    private void listarServicos() {
        listaModelServicos = controllerServicos.getListaServicosController();
        cbServico.removeAllItems();
        for (int i = 0; i < listaModelServicos.size(); i++) {
            cbServico.addItem(listaModelServicos.get(i).getNome());
        }
    }

    private void listarSeguradoras() {
        listaModelSeguradora = controllerSeguradora.getListaSeguradoraController();
        cbSeguradoras.removeAllItems();
        for (int i = 0; i < listaModelSeguradora.size(); i++) {
            cbSeguradoras.addItem(listaModelSeguradora.get(i).getNome());
        }
    }

    private void listarCodigosSeguradoras() {
        listaModelSeguradora = controllerSeguradora.getListaSeguradoraController();
        cbCodSeguradora.removeAllItems();
        for (int i = 0; i < listaModelSeguradora.size(); i++) {
            cbCodSeguradora.addItem(listaModelSeguradora.get(i).getCodigo());
        }
    }

    //usa apenas para carregar op dados da interface quando abre o software
    private void retornarSegueradoraPeloCodigo2(ModelOp model) {
        modelSeguradora = controllerSeguradora.getSeguradoraController(Integer.parseInt(cbCodSeguradora.getSelectedItem().toString()));
        //recupera 
        this.cbSeguradoras.setSelectedItem(String.valueOf(modelSeguradora.getNome()));
        jfCNPJ.setText(modelSeguradora.getCnpj());
    }

    private boolean recuperarOp(ModelOp modelOp) {
        listarSeguradoras();
        listarCodigosSeguradoras();
        listarServicos();
        this.listarEstados();
        this.listarCidades();
        listarSeguradoras();
        listarCodigosSeguradoras();
        retornarDadosSeguradoras();
        modelSeguradora = controllerSeguradora.getSeguradoraController(cbSeguradoras.getSelectedItem().toString());
        //recupera o código e seta CNPJ
        this.cbCodSeguradora.setSelectedItem(modelSeguradora.getCodigo());
        this.jfCNPJ.setText(modelSeguradora.getCnpj());

        //RECUPERA VALOR HONORARIOS POR SEGURADORA
        try {
            SetIcone();
            this.cbSeguradoras.setEnabled(false);
            this.cbServico.setEnabled(false);
            modelOp.setCodigo(opConsulta.codigo);
            modelOp = controllerOp.getOpController(opConsulta.codigo);
            this.cbCodSeguradora.setSelectedItem(modelOp.getSeguradoras());
            this.tfCodigo.setText(String.valueOf(modelOp.getCodigo()));
            this.cbCodSeguradora.setSelectedItem(modelOp.getSeguradoras());
            this.listarServicos();
            this.listarCodigoServicos();
            this.cbServico.setSelectedItem(String.valueOf(controllerServicos.getServicosController(modelOp.getServicosCodigo()).getNome()));
            this.cbCodServico.setSelectedItem(String.valueOf(controllerServicos.getServicosController(modelOp.getServicosCodigo()).getCodigo()));
            this.tfObsSegurado.setText(modelOp.getObsSegurado());
            this.tfNomeSegurado.setText(modelOp.getNomeSegurado());
            this.cbUf.setSelectedItem(controllerEstado.getEstadoController(modelOp.getCodEstado()).getUf());
            this.listarCidades();
            this.cbCidade.setSelectedItem(controllerCidade.getCidadeController(modelOp.getCodCidade()).getNome());
            this.jTObsSinistro.setText(modelOp.getObsSinistro());
            this.jFHoraSinistro.setText(modelOp.getHoraSinistro());
            this.tfNumSinistro.setText(modelOp.getNumeroSinistro());
            this.tfAnalista.setText(modelOp.getAnalista());
            this.tfSeguradoPlaca.setText(modelOp.getSeguradoPlaca());
            this.cbSituacaoPgtoProcesso.setSelectedItem(controllerTipoPagamento.getFormaPagamentoController(modelOp.getTipo()).getDescricao());
            this.tfBairro.setText(modelOp.getSinistroBairro());

            //INICIO DAS DATAS
            jFDataEntrada.setText(new Mascaras().DataRecuperasql(modelOp.getDataEntrada()));
            jFDataSaida.setText(new Mascaras().DataRecuperasql(modelOp.getDataSaida()));
            jFDataSinistro.setText(new Mascaras().DataRecuperasql(modelOp.getDataSinistro()));

            //FIM DAS DATAS
            //recupera os dados do banco
            listaModelOpAlterar = controllerOp.getListaOpController(opConsulta.codigo);
            //carregar lista de produtos da venda

            //MOSTRAR DADOS DE JCOMBO BOX
            jLPuxarnomeSeguradora.setText(controllerSeguradora.getSeguradoraController(modelOp.getSeguradoras()).getNome());
            jLNomeCobertura.setText(this.cbServico.getSelectedItem().toString());
            jLNomeCidade.setText(this.cbCidade.getSelectedItem().toString());
            jLNomeEstado.setText(this.cbUf.getSelectedItem().toString());
            carregarTerceirosAlteracao();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Código inválido ou nenhum registro selecionado", "Aviso", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbCidade;
    private javax.swing.JComboBox cbCodSeguradora;
    private javax.swing.JComboBox<String> cbCodServico;
    private javax.swing.JComboBox cbSeguradoras;
    private componentes.UJComboBox cbServico;
    private javax.swing.JComboBox<String> cbSituacaoPgtoProcesso;
    private javax.swing.JComboBox<String> cbUf;
    private javax.swing.JLabel jFDataEntrada;
    private javax.swing.JLabel jFDataSaida;
    private javax.swing.JLabel jFDataSinistro;
    private javax.swing.JLabel jFHoraSinistro;
    private javax.swing.JLabel jLAnalista;
    private javax.swing.JLabel jLBairro;
    private javax.swing.JLabel jLCidade;
    private javax.swing.JLabel jLCnpj1;
    private javax.swing.JLabel jLCobertura;
    private javax.swing.JLabel jLDataEntrada;
    private javax.swing.JLabel jLDataSaida;
    private javax.swing.JLabel jLDataSinistro;
    private javax.swing.JLabel jLDetalhesSinistro;
    private javax.swing.JLabel jLEstado;
    private javax.swing.JLabel jLHoraSinistro;
    private javax.swing.JTextArea jLNomeCidade;
    private javax.swing.JLabel jLNomeCobertura;
    private javax.swing.JLabel jLNomeEstado;
    private javax.swing.JLabel jLNomeSegurado;
    private javax.swing.JLabel jLNomeSeguradora;
    private javax.swing.JLabel jLNumeroProcesso;
    private javax.swing.JLabel jLNumeroSinistro;
    private javax.swing.JLabel jLObsSegurado;
    private javax.swing.JLabel jLPlacaSegurado;
    private javax.swing.JTextArea jLPuxarnomeSeguradora;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanelEntradadeProcessos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPaneAnalista;
    private javax.swing.JScrollPane jScrollPaneAnalista1;
    private javax.swing.JTextArea jTObsSinistro;
    private javax.swing.JTabbedPane jTPanelProcessos;
    private javax.swing.JLabel jfCNPJ;
    private keeptoo.KGradientPanel kGAlterarTerceiro;
    private keeptoo.KGradientPanel kGPDadosSegurado;
    private keeptoo.KGradientPanel kGPDadosSinistro;
    private keeptoo.KGradientPanel kGSeguradoraSolicitante;
    private keeptoo.KGradientPanel kGradientPanel1;
    private keeptoo.KGradientPanel kGradientPanel10;
    private keeptoo.KGradientPanel kGradientPanel11;
    private keeptoo.KGradientPanel kGradientPanel12;
    private keeptoo.KGradientPanel kGradientPanel13;
    private keeptoo.KGradientPanel kGradientPanel14;
    private keeptoo.KGradientPanel kGradientPanel15;
    private keeptoo.KGradientPanel kGradientPanel16;
    private keeptoo.KGradientPanel kGradientPanel17;
    private keeptoo.KGradientPanel kGradientPanel18;
    private keeptoo.KGradientPanel kGradientPanel2;
    private keeptoo.KGradientPanel kGradientPanel22;
    private keeptoo.KGradientPanel kGradientPanel3;
    private keeptoo.KGradientPanel kGradientPanel6;
    private keeptoo.KGradientPanel kGradientPanel7;
    private keeptoo.KGradientPanel kGradientPanel8;
    private keeptoo.KGradientPanel kGradientPanel9;
    private javax.swing.JTable tbTerceiros;
    private javax.swing.JTextArea tfAnalista;
    private javax.swing.JTextArea tfBairro;
    private javax.swing.JTextField tfCodTerceiro;
    private javax.swing.JLabel tfCodigo;
    private javax.swing.JTextArea tfNomeSegurado;
    private javax.swing.JTextArea tfNumSinistro;
    private javax.swing.JTextArea tfObsSegurado;
    private javax.swing.JLabel tfSeguradoPlaca;
    // End of variables declaration//GEN-END:variables

    ConexaoBanco cc = new ConexaoBanco();
    Connection cn = cc.conectar();

    private void SetIcone() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("ictrol.png")));
    }
}
