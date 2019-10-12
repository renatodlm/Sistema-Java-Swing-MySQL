/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ControllerCidade;
import Controller.ControllerFuncionario;
import Controller.ControllerFuncionarioCidadeEstado;
import Controller.ControllerEstado;
import Model.ModelCidade;
import Model.ModelFuncionario;
import Model.ModelFuncionarioCidadeEstado;
import Model.ModelEstado;
import static View.ViewCidade.cidade;
import util.Mascaras;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import Controller.ControllerBancos;
import Controller.ControllerOcupacao;
import Model.ModelBancos;
import Model.ModelOcupacao;
import static View.ViewFuncionario.Funcionario;
import conexao.ConexaoBanco;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import util.Datas;
import util.Exporter;

/**
 *
 * @author studiomicroweb
 */
public final class ViewCadFuncionario extends javax.swing.JFrame {

    String tipoCadastro = "null";
    ControllerCidade controllerCidade = new ControllerCidade();
    ControllerEstado controllerEstado = new ControllerEstado();
    ControllerFuncionarioCidadeEstado controllerFuncionarioCidadeEstado = new ControllerFuncionarioCidadeEstado();
    ModelFuncionario modelFuncionario = new ModelFuncionario();
    ArrayList<ModelCidade> listaModelCidades = new ArrayList<>();
    ArrayList<ModelEstado> listaModelEstados = new ArrayList<>();
    ArrayList<ModelFuncionarioCidadeEstado> listaModelFuncionarioCidadeEstados = new ArrayList<>();
    ControllerFuncionario controllerFuncionario = new ControllerFuncionario();
    ViewFuncionario viewFuncionario = new ViewFuncionario();
    //BANCOS
    ModelBancos modelBancos = new ModelBancos();
    ArrayList<ModelBancos> listaModelBancos = new ArrayList<>();
    ControllerBancos controllerBancos = new ControllerBancos();

    //OCUPAÇÃO
    ArrayList<ModelOcupacao> listaModelOcupacao = new ArrayList<>();
    ControllerOcupacao controllerOcupacao = new ControllerOcupacao();
    ModelOcupacao modelOcupacao = new ModelOcupacao();

    /**
     * Creates new form ViewCadFuncionario
     */
    public ViewCadFuncionario() {
        initComponents();
        SetIcone();
        this.listarEstados();
        this.listarCidades();
        this.listarBancos();
        this.listarCodigoBancos();
        listarOcupacao();
        btAtualizarBanco.setEnabled(false);
        if (viewFuncionario.tipoCadastro != null && viewFuncionario.tipoCadastro.equals("alteracao")) {
            this.recuperarFuncionario();
        }
        setLocationRelativeTo(null);
        this.carrerBancosPorFunc();
        carregarFeriasRegistradas();
        verificarFeriasporVinculo();
        jTabbedCadFuncionarios.setSelectedIndex(0);
        kButtonInformacoes.setSelected(true);
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
                new ViewCadFuncionario().setVisible(true);
            }
        });
    }

    private void listarBancos() {
        carrerBancosPorFunc();
        listaModelBancos = controllerBancos.getListaBancosController();
        cbBanco.removeAllItems();
        for (int i = 0; i < listaModelBancos.size(); i++) {
            cbBanco.addItem(listaModelBancos.get(i).getBanco_nome());
        }
        cbBanco.setSelectedIndex(-1);
    }

    private void listarOcupacao() {
        listaModelOcupacao = controllerOcupacao.getListaOcupacaoController();
        cbOcupacao.removeAllItems();
        for (int i = 0; i < listaModelOcupacao.size(); i++) {
            cbOcupacao.addItem(listaModelOcupacao.get(i).getOcup_descricao());
        }
        cbOcupacao.setSelectedIndex(-1);
    }

    private void listarCodigoBancos() {
        carrerBancosPorFunc();
        listaModelBancos = controllerBancos.getListaBancosController();
        cbCodBanco.removeAllItems();
        for (int i = 0; i < listaModelBancos.size(); i++) {
            cbCodBanco.addItem(Integer.toString(listaModelBancos.get(i).getCodigo()));
        }
        cbCodBanco.setSelectedIndex(-1);
    }

    void verificarFeriasporVinculo() {
        if ((cbVinculo.getSelectedIndex() >= 0) && (cbOcupacao.getSelectedIndex() >= 0) && !("Agente".equals(cbOcupacao.getSelectedItem().toString())) && ("Funcionário".equals(cbVinculo.getSelectedItem().toString()) || "Colaborador".equals(cbVinculo.getSelectedItem().toString()))) {
            jLUltimasFerias.setEnabled(true);
            jFUltimasFerias.setEnabled(true);
            jLVencerFerias.setEnabled(true);
            jFVencerFerias.setEnabled(true);
            jLBanco2.setEnabled(true);
            tfMostraAdmissao.setEnabled(true);
            jLTipoConta1.setEnabled(true);
            jDUltimasFerias.setEnabled(true);
            jDVencimento.setEnabled(true);
            jLBancoConta1.setEnabled(true);
            jLBancoObs1.setEnabled(true);
            tfObsFerias.setEnabled(true);
            btSalvarFerias.setEnabled(true);
            btExcluirFerias.setEnabled(true);
            btAlterarTerceiro1.setEnabled(true);
            btExportarFerias.setEnabled(true);
            btAtualizarFerias.setEnabled(false);
            btCancelarContato1.setEnabled(false);
            jTableFerias.setEnabled(true);
            jScrollPane7.setEnabled(true);
            //VISIBLE
            jLUltimasFerias.setVisible(true);
            jFUltimasFerias.setVisible(true);
            jLVencerFerias.setVisible(true);
            jFVencerFerias.setVisible(true);
            jLBanco2.setVisible(true);
            tfMostraAdmissao.setVisible(true);
            jLTipoConta1.setVisible(true);
            jDUltimasFerias.setVisible(true);
            jDVencimento.setVisible(true);
            jLBancoConta1.setVisible(true);
            jLBancoObs1.setVisible(true);
            tfObsFerias.setVisible(true);
            btExcluirFerias.setVisible(true);
            btAlterarTerceiro1.setVisible(true);
            btExportarFerias.setVisible(true);
            btSalvarFerias.setVisible(true);
            btAtualizarFerias.setVisible(true);
            btCancelarContato1.setVisible(true);
            jTableFerias.setVisible(true);
            jScrollPane7.setVisible(true);
        } else {
            jLUltimasFerias.setEnabled(false);
            jFUltimasFerias.setEnabled(false);
            jLVencerFerias.setEnabled(false);
            jFVencerFerias.setEnabled(false);
            jLBanco2.setEnabled(false);
            tfMostraAdmissao.setEnabled(false);
            jLTipoConta1.setEnabled(false);
            jDUltimasFerias.setEnabled(false);
            jDVencimento.setEnabled(false);
            jLBancoConta1.setEnabled(false);
            jLBancoObs1.setEnabled(false);
            tfObsFerias.setEnabled(false);
            btExcluirFerias.setEnabled(false);
            btAlterarTerceiro1.setEnabled(false);
            btExportarFerias.setEnabled(false);
            btSalvarFerias.setEnabled(false);
            btAtualizarFerias.setEnabled(false);
            btCancelarContato1.setEnabled(false);
            jTableFerias.setEnabled(false);
            jScrollPane7.setEnabled(false);
            //VISIBLE
            jLUltimasFerias.setVisible(false);
            jFUltimasFerias.setVisible(false);
            jLVencerFerias.setVisible(false);
            jFVencerFerias.setVisible(false);
            jLBanco2.setVisible(false);
            tfMostraAdmissao.setVisible(false);
            jLTipoConta1.setVisible(false);
            jDUltimasFerias.setVisible(false);
            jDVencimento.setVisible(false);
            jLBancoConta1.setVisible(false);
            jLBancoObs1.setVisible(false);
            tfObsFerias.setVisible(false);
            btExcluirFerias.setVisible(false);
            btAlterarTerceiro1.setVisible(false);
            btExportarFerias.setVisible(false);
            btSalvarFerias.setVisible(false);
            btAtualizarFerias.setVisible(false);
            btCancelarContato1.setVisible(false);
            jTableFerias.setVisible(false);
            jScrollPane7.setVisible(false);
        }

    }

    void limparCamposBanco() {
        tfConta.setText("");
        tfAgencia.setText("");
        tfOperacao.setText("");
        tfBancoObs.setText("");
        cbBanco.setSelectedIndex(0);
        cbTipoConta.setSelectedIndex(0);
    }

    void carrerBancosPorFunc() {

        if ((int) jTableBancos.getRowCount() > 0) {
            DefaultTableModel modelo = (DefaultTableModel) jTableBancos.getModel();
            modelo.setNumRows(0);
        }
        try {
            DefaultTableModel model = (DefaultTableModel) jTableBancos.getModel();

            int codigo = 0;
            if (!"Novo +".equals(tfCodigo.getText())) {
                codigo = (Integer.parseInt(tfCodigo.getText()));
            }

            String consultar = "SELECT * FROM contas_bancos WHERE cod_func='" + codigo + "'";
            String[] datos = new String[7];
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(consultar);
            while (rs.next()) {
                datos[0] = rs.getString("codigo");
                int codbando = (Integer.parseInt(rs.getString("cod_banco")));
                String NomeBando = (controllerBancos.getBancosControllerCod(codbando).getBanco_nome());
                datos[1] = NomeBando;
                datos[2] = rs.getString("banco_conta");
                datos[3] = rs.getString("banco_agencia");
                datos[4] = rs.getString("banco_tipo");
                datos[5] = rs.getString("banco_op");
                datos[6] = rs.getString("banco_obs");

                model.addRow(datos);
            }
            jTableBancos.setModel(model);

        } catch (SQLException ex) {
            Logger.getLogger(ViewCadFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void carregarFeriasRegistradas() {

        if ((int) jTableFerias.getRowCount() > 0) {
            DefaultTableModel modelo = (DefaultTableModel) jTableFerias.getModel();
            modelo.setNumRows(0);
        }
        try {
            DefaultTableModel model = (DefaultTableModel) jTableFerias.getModel();

            String consultar = "SELECT * FROM cadastro_ferias WHERE cod_func='" + tfCodigo.getText() + "'";
            String[] datos = new String[4];
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(consultar);
            while (rs.next()) {
                datos[0] = rs.getString("codigo");
                datos[1] = (new Mascaras().DataRecuperasql(rs.getString("data_uferias")));
                datos[2] = (new Mascaras().DataRecuperasql(rs.getString("data_vferias")));
                datos[3] = rs.getString("obs");
                jFUltimasFerias.setText(datos[1]);
                jFVencerFerias.setText(datos[2]);
                model.addRow(datos);
            }
            jTableFerias.setModel(model);

        } catch (SQLException ex) {
            Logger.getLogger(ViewCadFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void limparCamposFerias() {
        jDUltimasFerias.setDate(null);
        jDVencimento.setDate(null);
        tfObsFerias.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cbCodBanco = new javax.swing.JComboBox<String>();
        btAtualizarBanco = new javax.swing.JButton();
        btAlterarBanco = new javax.swing.JButton();
        tfCodBanco = new javax.swing.JTextField();
        tfCodFerias = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        kGradientPanel14 = new keeptoo.KGradientPanel();
        kButtonInformacoes = new keeptoo.KButton();
        kButtonContasFerias = new keeptoo.KButton();
        kGradientPanel22 = new keeptoo.KGradientPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jTabbedCadFuncionarios = new javax.swing.JTabbedPane();
        DadosFuncionario = new javax.swing.JPanel();
        kGrDadosPessoais = new keeptoo.KGradientPanel();
        jLObs = new javax.swing.JLabel();
        tfCodigo = new javax.swing.JTextField();
        tfNome = new javax.swing.JTextField();
        tfRg = new javax.swing.JTextField();
        jLCodigo = new javax.swing.JLabel();
        jLNascimento = new javax.swing.JLabel();
        jLRg = new javax.swing.JLabel();
        jLCpf_Cnpj = new javax.swing.JLabel();
        tfObservacao = new javax.swing.JTextField();
        jLNome = new javax.swing.JLabel();
        tfCnh = new javax.swing.JTextField();
        jLCnh = new javax.swing.JLabel();
        jfCpf = new javax.swing.JFormattedTextField();
        jLFiliacao = new javax.swing.JLabel();
        tfFiliacao = new javax.swing.JTextField();
        jFNascimento = new com.toedter.calendar.JDateChooser();
        kGInfoContato = new keeptoo.KGradientPanel();
        jLBairro = new javax.swing.JLabel();
        jLEndereco = new javax.swing.JLabel();
        jLTelefone = new javax.swing.JLabel();
        jLCelular = new javax.swing.JLabel();
        jLEmail = new javax.swing.JLabel();
        tfEmail = new javax.swing.JTextField();
        tfComplemento = new javax.swing.JTextField();
        jLComplemento = new javax.swing.JLabel();
        jLNumero = new javax.swing.JLabel();
        jLEstado = new javax.swing.JLabel();
        jLCidade = new javax.swing.JLabel();
        tfEndereco = new javax.swing.JTextField();
        tfBairro = new javax.swing.JTextField();
        cbUf = new javax.swing.JComboBox();
        tfNumero = new javax.swing.JTextField();
        jLCep = new javax.swing.JLabel();
        cbCidade = new javax.swing.JComboBox();
        jfCep = new javax.swing.JFormattedTextField();
        jfTelefone = new javax.swing.JFormattedTextField();
        jfCelular = new javax.swing.JFormattedTextField();
        jLObsFuncionario = new javax.swing.JLabel();
        tfObsContatoFuncionario = new javax.swing.JTextField();
        btCidadeAdd = new javax.swing.JButton();
        kGDadosFuncionais = new keeptoo.KGradientPanel();
        jLOcupacao = new javax.swing.JLabel();
        jLUltimasFerias = new javax.swing.JLabel();
        jLVencerFerias = new javax.swing.JLabel();
        jLObsOcupacao = new javax.swing.JLabel();
        tfObservacaoOcupacao = new javax.swing.JTextField();
        jLVinculo = new javax.swing.JLabel();
        cbVinculo = new javax.swing.JComboBox();
        cbOcupacao = new javax.swing.JComboBox<String>();
        jFAdmissao = new com.toedter.calendar.JDateChooser();
        jLBanco3 = new javax.swing.JLabel();
        jFVencerFerias = new javax.swing.JLabel();
        jFUltimasFerias = new javax.swing.JLabel();
        ContasFerias = new javax.swing.JPanel();
        kGContaBancaria = new keeptoo.KGradientPanel();
        cbTipoConta = new javax.swing.JComboBox<String>();
        cbBanco = new javax.swing.JComboBox<String>();
        jLBanco = new javax.swing.JLabel();
        jLTipoConta = new javax.swing.JLabel();
        jLBancoConta = new javax.swing.JLabel();
        jLBancoAgencia = new javax.swing.JLabel();
        jLBancoObs = new javax.swing.JLabel();
        jLBancoOp = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTableBancos = new javax.swing.JTable();
        tfConta = new javax.swing.JTextField();
        tfBancoObs = new javax.swing.JTextField();
        tfAgencia = new javax.swing.JTextField();
        tfOperacao = new javax.swing.JTextField();
        btSalvarBanco = new javax.swing.JButton();
        btExcluirBanco = new javax.swing.JButton();
        btAtualizarTerceiro = new javax.swing.JButton();
        btAlterarTerceiro = new javax.swing.JButton();
        btCancelarContato = new javax.swing.JButton();
        kGContaBancaria1 = new keeptoo.KGradientPanel();
        jLBanco2 = new javax.swing.JLabel();
        jLTipoConta1 = new javax.swing.JLabel();
        jLBancoConta1 = new javax.swing.JLabel();
        jLBancoObs1 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTableFerias = new javax.swing.JTable();
        tfObsFerias = new javax.swing.JTextField();
        btSalvarFerias = new javax.swing.JButton();
        btExcluirFerias = new javax.swing.JButton();
        jDUltimasFerias = new com.toedter.calendar.JDateChooser();
        jDVencimento = new com.toedter.calendar.JDateChooser();
        btExportarFerias = new javax.swing.JButton();
        btAtualizarFerias = new javax.swing.JButton();
        btAlterarTerceiro1 = new javax.swing.JButton();
        btCancelarContato1 = new javax.swing.JButton();
        tfMostraAdmissao = new javax.swing.JLabel();
        jPanelSalvarCancelar = new javax.swing.JPanel();
        btCancelaNOBLOCK = new javax.swing.JButton();
        btSalvarNOBLOCK = new javax.swing.JButton();

        cbCodBanco.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<selecione>" }));
        cbCodBanco.setEnabled(false);
        cbCodBanco.setMinimumSize(new java.awt.Dimension(85, 25));
        cbCodBanco.setPreferredSize(new java.awt.Dimension(85, 25));
        cbCodBanco.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbCodBancoPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        btAtualizarBanco.setBackground(new java.awt.Color(56, 178, 255));
        btAtualizarBanco.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btAtualizarBanco.setText("Atualizar");
        btAtualizarBanco.setPreferredSize(new java.awt.Dimension(120, 28));
        btAtualizarBanco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAtualizarBancoActionPerformed(evt);
            }
        });

        btAlterarBanco.setBackground(new java.awt.Color(0, 102, 255));
        btAlterarBanco.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btAlterarBanco.setForeground(new java.awt.Color(255, 255, 255));
        btAlterarBanco.setPreferredSize(new java.awt.Dimension(50, 30));
        btAlterarBanco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAlterarBancoActionPerformed(evt);
            }
        });

        tfCodBanco.setEditable(false);
        tfCodBanco.setEnabled(false);

        tfCodFerias.setEditable(false);
        tfCodFerias.setEnabled(false);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro Funcionários");
        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1269, 675));
        setPreferredSize(new java.awt.Dimension(1269, 685));
        setResizable(false);

        jPanel2.setMinimumSize(new java.awt.Dimension(1264, 641));
        jPanel2.setPreferredSize(new java.awt.Dimension(1264, 641));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        kGradientPanel14.setBackground(new java.awt.Color(146, 171, 197));
        kGradientPanel14.setkBorderRadius(0);
        kGradientPanel14.setkEndColor(new java.awt.Color(146, 171, 197));
        kGradientPanel14.setkGradientFocus(0);
        kGradientPanel14.setkStartColor(new java.awt.Color(146, 171, 197));
        kGradientPanel14.setName(""); // NOI18N
        kGradientPanel14.setPreferredSize(new java.awt.Dimension(1264, 40));

        kButtonInformacoes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/24x24/Text.png"))); // NOI18N
        kButtonInformacoes.setText("        INFORMAÇÕES PESSOAS");
        kButtonInformacoes.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        kButtonInformacoes.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        kButtonInformacoes.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        kButtonInformacoes.setIconTextGap(10);
        kButtonInformacoes.setkBackGroundColor(new java.awt.Color(255, 255, 255));
        kButtonInformacoes.setkBorderRadius(0);
        kButtonInformacoes.setkEndColor(new java.awt.Color(197, 201, 206));
        kButtonInformacoes.setkForeGround(new java.awt.Color(51, 51, 51));
        kButtonInformacoes.setkHoverEndColor(new java.awt.Color(234, 239, 243));
        kButtonInformacoes.setkHoverForeGround(new java.awt.Color(51, 51, 51));
        kButtonInformacoes.setkHoverStartColor(new java.awt.Color(234, 239, 243));
        kButtonInformacoes.setkPressedColor(new java.awt.Color(153, 153, 153));
        kButtonInformacoes.setkSelectedColor(new java.awt.Color(255, 255, 255));
        kButtonInformacoes.setkStartColor(new java.awt.Color(197, 201, 206));
        kButtonInformacoes.setPreferredSize(new java.awt.Dimension(240, 48));
        kButtonInformacoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButtonInformacoesActionPerformed(evt);
            }
        });

        kButtonContasFerias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/24x24/Calendar.png"))); // NOI18N
        kButtonContasFerias.setText("     CONTAS E FÉRIAS");
        kButtonContasFerias.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        kButtonContasFerias.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        kButtonContasFerias.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        kButtonContasFerias.setIconTextGap(10);
        kButtonContasFerias.setkBackGroundColor(new java.awt.Color(255, 255, 255));
        kButtonContasFerias.setkBorderRadius(0);
        kButtonContasFerias.setkEndColor(new java.awt.Color(197, 201, 206));
        kButtonContasFerias.setkForeGround(new java.awt.Color(51, 51, 51));
        kButtonContasFerias.setkHoverEndColor(new java.awt.Color(234, 239, 243));
        kButtonContasFerias.setkHoverForeGround(new java.awt.Color(51, 51, 51));
        kButtonContasFerias.setkHoverStartColor(new java.awt.Color(234, 239, 243));
        kButtonContasFerias.setkPressedColor(new java.awt.Color(153, 153, 153));
        kButtonContasFerias.setkSelectedColor(new java.awt.Color(255, 255, 255));
        kButtonContasFerias.setkStartColor(new java.awt.Color(197, 201, 206));
        kButtonContasFerias.setPreferredSize(new java.awt.Dimension(240, 48));
        kButtonContasFerias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButtonContasFeriasActionPerformed(evt);
            }
        });

        kGradientPanel22.setkBorderRadius(0);
        kGradientPanel22.setkEndColor(new java.awt.Color(146, 171, 197));
        kGradientPanel22.setkGradientFocus(125);
        kGradientPanel22.setkStartColor(new java.awt.Color(255, 255, 255));

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 51, 102));
        jLabel27.setText("FUNCIONÁRIO");

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(0, 51, 102));
        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/24x24/User group.png"))); // NOI18N
        jLabel28.setPreferredSize(new java.awt.Dimension(60, 60));

        org.jdesktop.layout.GroupLayout kGradientPanel22Layout = new org.jdesktop.layout.GroupLayout(kGradientPanel22);
        kGradientPanel22.setLayout(kGradientPanel22Layout);
        kGradientPanel22Layout.setHorizontalGroup(
            kGradientPanel22Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, kGradientPanel22Layout.createSequentialGroup()
                .add(17, 17, 17)
                .add(jLabel28, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 26, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel27, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 105, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        kGradientPanel22Layout.setVerticalGroup(
            kGradientPanel22Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(kGradientPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .add(kGradientPanel22Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel27, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jLabel28, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE))
                .addContainerGap())
        );

        org.jdesktop.layout.GroupLayout kGradientPanel14Layout = new org.jdesktop.layout.GroupLayout(kGradientPanel14);
        kGradientPanel14.setLayout(kGradientPanel14Layout);
        kGradientPanel14Layout.setHorizontalGroup(
            kGradientPanel14Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, kGradientPanel14Layout.createSequentialGroup()
                .add(kGradientPanel22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 167, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(kButtonInformacoes, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 185, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(kButtonContasFerias, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 161, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(733, Short.MAX_VALUE))
        );
        kGradientPanel14Layout.setVerticalGroup(
            kGradientPanel14Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(kGradientPanel14Layout.createSequentialGroup()
                .add(kGradientPanel14Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(kGradientPanel22, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .add(kButtonInformacoes, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(kButtonContasFerias, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .add(0, 0, Short.MAX_VALUE))
        );

        jPanel2.add(kGradientPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1270, 50));

        jTabbedCadFuncionarios.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedCadFuncionarios.setForeground(new java.awt.Color(51, 51, 51));
        jTabbedCadFuncionarios.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        DadosFuncionario.setBackground(new java.awt.Color(255, 255, 255));
        DadosFuncionario.setMinimumSize(new java.awt.Dimension(1264, 640));
        DadosFuncionario.setPreferredSize(new java.awt.Dimension(1280, 560));
        DadosFuncionario.setRequestFocusEnabled(false);

        kGrDadosPessoais.setBackground(new java.awt.Color(255, 255, 255));
        kGrDadosPessoais.setkBorderRadius(0);
        kGrDadosPessoais.setkEndColor(new java.awt.Color(234, 239, 243));
        kGrDadosPessoais.setkGradientFocus(0);
        kGrDadosPessoais.setkStartColor(new java.awt.Color(255, 255, 255));
        kGrDadosPessoais.setPreferredSize(new java.awt.Dimension(1260, 180));
        kGrDadosPessoais.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLObs.setText("Observação:");
        kGrDadosPessoais.add(jLObs, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 100, -1, -1));

        tfCodigo.setText("Novo +");
        tfCodigo.setEnabled(false);
        tfCodigo.setPreferredSize(new java.awt.Dimension(60, 35));
        tfCodigo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfCodigoFocusLost(evt);
            }
        });
        kGrDadosPessoais.add(tfCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, -1, 35));

        tfNome.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfNomeFocusLost(evt);
            }
        });
        kGrDadosPessoais.add(tfNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 470, 28));

        tfRg.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfRgFocusLost(evt);
            }
        });
        kGrDadosPessoais.add(tfRg, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 50, 130, 28));

        jLCodigo.setText("Código:");
        kGrDadosPessoais.add(jLCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 50, 20));

        jLNascimento.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLNascimento.setText("Nascimento");
        kGrDadosPessoais.add(jLNascimento, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 30, 70, 20));

        jLRg.setText("RG");
        kGrDadosPessoais.add(jLRg, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 30, 30, 20));

        jLCpf_Cnpj.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLCpf_Cnpj.setText("CPF");
        kGrDadosPessoais.add(jLCpf_Cnpj, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 30, 40, 20));

        tfObservacao.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfObservacaoFocusLost(evt);
            }
        });
        kGrDadosPessoais.add(tfObservacao, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 120, 600, 28));

        jLNome.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLNome.setText("Nome:");
        kGrDadosPessoais.add(jLNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, 50, 20));

        tfCnh.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfCnhFocusLost(evt);
            }
        });
        kGrDadosPessoais.add(tfCnh, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 50, 130, 28));

        jLCnh.setText("CNH");
        kGrDadosPessoais.add(jLCnh, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 30, 30, 20));

        try {
            jfCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jfCpf.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jfCpfFocusLost(evt);
            }
        });
        kGrDadosPessoais.add(jfCpf, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 50, 130, 28));

        jLFiliacao.setText("Afiliação");
        kGrDadosPessoais.add(jLFiliacao, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, -1));

        tfFiliacao.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfFiliacaoFocusLost(evt);
            }
        });
        kGrDadosPessoais.add(tfFiliacao, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 550, 28));

        jFNascimento.setPreferredSize(new java.awt.Dimension(150, 30));
        kGrDadosPessoais.add(jFNascimento, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 50, -1, -1));

        DadosFuncionario.add(kGrDadosPessoais);

        kGInfoContato.setBackground(new java.awt.Color(255, 255, 255));
        kGInfoContato.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Informações de Contato", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(51, 51, 51))); // NOI18N
        kGInfoContato.setkBorderRadius(0);
        kGInfoContato.setkEndColor(new java.awt.Color(234, 239, 243));
        kGInfoContato.setkGradientFocus(0);
        kGInfoContato.setkStartColor(new java.awt.Color(255, 255, 255));
        kGInfoContato.setPreferredSize(new java.awt.Dimension(1260, 230));
        kGInfoContato.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLBairro.setText("Bairro:");
        kGInfoContato.add(jLBairro, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 90, -1, -1));

        jLEndereco.setText("Endereço:");
        kGInfoContato.add(jLEndereco, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, -1, -1));

        jLTelefone.setText("Telefone:");
        kGInfoContato.add(jLTelefone, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, -1, -1));

        jLCelular.setText("Celular:");
        kGInfoContato.add(jLCelular, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 20, -1, -1));

        jLEmail.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLEmail.setText("E-mail:");
        kGInfoContato.add(jLEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 60, 20));

        tfEmail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfEmailFocusLost(evt);
            }
        });
        kGInfoContato.add(tfEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 270, 28));

        tfComplemento.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfComplementoFocusLost(evt);
            }
        });
        kGInfoContato.add(tfComplemento, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 110, 170, 28));

        jLComplemento.setText("Complemento:");
        kGInfoContato.add(jLComplemento, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 90, -1, -1));

        jLNumero.setText("Número:");
        kGInfoContato.add(jLNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 90, -1, -1));

        jLEstado.setText("Estado:");
        kGInfoContato.add(jLEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 20, -1, -1));

        jLCidade.setText("Cidade:");
        kGInfoContato.add(jLCidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 20, 90, -1));

        tfEndereco.setPreferredSize(new java.awt.Dimension(400, 28));
        tfEndereco.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfEnderecoFocusLost(evt);
            }
        });
        kGInfoContato.add(tfEndereco, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 430, -1));

        tfBairro.setPreferredSize(new java.awt.Dimension(400, 28));
        tfBairro.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfBairroFocusLost(evt);
            }
        });
        tfBairro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfBairroActionPerformed(evt);
            }
        });
        kGInfoContato.add(tfBairro, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 110, -1, -1));

        cbUf.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<selecione>", "Item 2", "Item 3", "Item 4" }));
        cbUf.setSelectedIndex(-1);
        cbUf.setPreferredSize(new java.awt.Dimension(120, 28));
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
        cbUf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbUfActionPerformed(evt);
            }
        });
        kGInfoContato.add(cbUf, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 40, 80, -1));

        tfNumero.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfNumeroFocusLost(evt);
            }
        });
        kGInfoContato.add(tfNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 110, 80, 28));

        jLCep.setText("CEP:");
        kGInfoContato.add(jLCep, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 20, -1, -1));

        cbCidade.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<selecione>", "Item 2", "Item 3", "Item 4" }));
        cbCidade.setSelectedIndex(-1);
        cbCidade.setPreferredSize(new java.awt.Dimension(120, 28));
        cbCidade.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                cbCidadePopupMenuWillBecomeVisible(evt);
            }
        });
        kGInfoContato.add(cbCidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 40, 140, -1));

        try {
            jfCep.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jfCep.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jfCepFocusLost(evt);
            }
        });
        kGInfoContato.add(jfCep, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 40, 150, 28));

        try {
            jfTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jfTelefone.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jfTelefoneFocusLost(evt);
            }
        });
        kGInfoContato.add(jfTelefone, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 40, 130, 28));

        try {
            jfCelular.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jfCelular.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jfCelularFocusLost(evt);
            }
        });
        kGInfoContato.add(jfCelular, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 40, 130, 28));

        jLObsFuncionario.setText("Observação:");
        kGInfoContato.add(jLObsFuncionario, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, -1, -1));

        tfObsContatoFuncionario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfObsContatoFuncionarioFocusLost(evt);
            }
        });
        kGInfoContato.add(tfObsContatoFuncionario, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 1170, 28));

        btCidadeAdd.setBackground(new java.awt.Color(0, 153, 0));
        btCidadeAdd.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btCidadeAdd.setForeground(new java.awt.Color(255, 255, 255));
        btCidadeAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Create.png"))); // NOI18N
        btCidadeAdd.setText("CIDADE");
        btCidadeAdd.setMaximumSize(new java.awt.Dimension(150, 30));
        btCidadeAdd.setMinimumSize(new java.awt.Dimension(150, 30));
        btCidadeAdd.setPreferredSize(new java.awt.Dimension(120, 35));
        btCidadeAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCidadeAddActionPerformed(evt);
            }
        });
        kGInfoContato.add(btCidadeAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 30, -1, -1));

        DadosFuncionario.add(kGInfoContato);

        kGDadosFuncionais.setBackground(new java.awt.Color(255, 255, 255));
        kGDadosFuncionais.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Dados Funcionais", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(51, 51, 51))); // NOI18N
        kGDadosFuncionais.setkBorderRadius(0);
        kGDadosFuncionais.setkEndColor(new java.awt.Color(234, 239, 243));
        kGDadosFuncionais.setkGradientFocus(0);
        kGDadosFuncionais.setkStartColor(new java.awt.Color(255, 255, 255));
        kGDadosFuncionais.setPreferredSize(new java.awt.Dimension(1260, 140));
        kGDadosFuncionais.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLOcupacao.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLOcupacao.setText("Ocupação");
        kGDadosFuncionais.add(jLOcupacao, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, 60, -1));

        jLUltimasFerias.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLUltimasFerias.setForeground(new java.awt.Color(51, 51, 51));
        jLUltimasFerias.setText("Ultimas Férias:");
        kGDadosFuncionais.add(jLUltimasFerias, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 40, 90, -1));

        jLVencerFerias.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLVencerFerias.setForeground(new java.awt.Color(51, 51, 51));
        jLVencerFerias.setText("Férias a vencer:");
        kGDadosFuncionais.add(jLVencerFerias, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 40, -1, -1));

        jLObsOcupacao.setText("Observação:");
        kGDadosFuncionais.add(jLObsOcupacao, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 40, -1, -1));

        tfObservacaoOcupacao.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfObservacaoOcupacaoFocusLost(evt);
            }
        });
        kGDadosFuncionais.add(tfObservacaoOcupacao, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 60, 380, 28));

        jLVinculo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLVinculo.setText("Vínculo");
        kGDadosFuncionais.add(jLVinculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, -1, -1));

        cbVinculo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Funcionário", "Colaborador", "Parceiro", "Terceiro", "Sócio" }));
        cbVinculo.setSelectedIndex(-1);
        cbVinculo.setMinimumSize(new java.awt.Dimension(120, 28));
        cbVinculo.setPreferredSize(new java.awt.Dimension(150, 28));
        cbVinculo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cbVinculoFocusLost(evt);
            }
        });
        cbVinculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbVinculoActionPerformed(evt);
            }
        });
        kGDadosFuncionais.add(cbVinculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, -1, -1));

        cbOcupacao.setMinimumSize(new java.awt.Dimension(120, 28));
        cbOcupacao.setPreferredSize(new java.awt.Dimension(150, 28));
        cbOcupacao.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cbOcupacaoFocusLost(evt);
            }
        });
        cbOcupacao.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbOcupacaoPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        kGDadosFuncionais.add(cbOcupacao, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, -1, 28));

        jFAdmissao.setPreferredSize(new java.awt.Dimension(150, 30));
        jFAdmissao.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jFAdmissaoFocusLost(evt);
            }
        });
        kGDadosFuncionais.add(jFAdmissao, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 60, -1, -1));

        jLBanco3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLBanco3.setText("Admissão");
        kGDadosFuncionais.add(jLBanco3, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 40, 60, -1));

        jFVencerFerias.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jFVencerFerias.setForeground(new java.awt.Color(51, 0, 0));
        jFVencerFerias.setText("99/99/9999");
        kGDadosFuncionais.add(jFVencerFerias, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 60, -1, -1));

        jFUltimasFerias.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jFUltimasFerias.setForeground(new java.awt.Color(51, 0, 0));
        jFUltimasFerias.setText("99/99/9999");
        kGDadosFuncionais.add(jFUltimasFerias, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 60, -1, -1));

        DadosFuncionario.add(kGDadosFuncionais);

        jTabbedCadFuncionarios.addTab("INFORMAÇÕES PESSOAS", DadosFuncionario);

        ContasFerias.setBackground(new java.awt.Color(255, 255, 255));
        ContasFerias.setMinimumSize(new java.awt.Dimension(1264, 290));
        ContasFerias.setPreferredSize(new java.awt.Dimension(1264, 310));

        kGContaBancaria.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Conta Bancária", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(51, 51, 51))); // NOI18N
        kGContaBancaria.setkBorderRadius(0);
        kGContaBancaria.setkEndColor(new java.awt.Color(234, 239, 243));
        kGContaBancaria.setkGradientFocus(0);
        kGContaBancaria.setkStartColor(new java.awt.Color(255, 255, 255));
        kGContaBancaria.setPreferredSize(new java.awt.Dimension(1260, 250));
        kGContaBancaria.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbTipoConta.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "C.Corrente", "C.Poupança" }));
        cbTipoConta.setSelectedIndex(-1);
        cbTipoConta.setMinimumSize(new java.awt.Dimension(85, 25));
        cbTipoConta.setPreferredSize(new java.awt.Dimension(120, 28));
        cbTipoConta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTipoContaActionPerformed(evt);
            }
        });
        kGContaBancaria.add(cbTipoConta, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 50, 120, -1));

        cbBanco.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<selecione>" }));
        cbBanco.setSelectedIndex(-1);
        cbBanco.setMinimumSize(new java.awt.Dimension(85, 25));
        cbBanco.setPreferredSize(new java.awt.Dimension(220, 28));
        cbBanco.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbBancoPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        kGContaBancaria.add(cbBanco, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 220, -1));

        jLBanco.setText("Banco");
        kGContaBancaria.add(jLBanco, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 60, -1));

        jLTipoConta.setText("Tipo de Conta");
        kGContaBancaria.add(jLTipoConta, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 30, -1, -1));

        jLBancoConta.setText("Conta e Digito");
        kGContaBancaria.add(jLBancoConta, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 30, 90, -1));

        jLBancoAgencia.setText("Agência e Digito");
        kGContaBancaria.add(jLBancoAgencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 30, 90, -1));

        jLBancoObs.setText("Observações:");
        kGContaBancaria.add(jLBancoObs, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 30, 120, -1));

        jLBancoOp.setText("OP:");
        kGContaBancaria.add(jLBancoOp, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 30, 30, -1));

        jScrollPane6.setBackground(new java.awt.Color(247, 247, 247));
        jScrollPane6.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane6.setPreferredSize(new java.awt.Dimension(1170, 170));

        jTableBancos.setAutoCreateRowSorter(true);
        jTableBancos.setBackground(new java.awt.Color(247, 247, 247));
        jTableBancos.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jTableBancos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cod.", "Banco", "Conta", "Agência", "Tipo Conta", "Op", "Observações"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableBancos.setGridColor(new java.awt.Color(247, 247, 247));
        jTableBancos.setMinimumSize(new java.awt.Dimension(610, 0));
        jTableBancos.setRowHeight(20);
        jTableBancos.setSelectionBackground(new java.awt.Color(0, 153, 0));
        jTableBancos.getTableHeader().setReorderingAllowed(false);
        jScrollPane6.setViewportView(jTableBancos);
        if (jTableBancos.getColumnModel().getColumnCount() > 0) {
            jTableBancos.getColumnModel().getColumn(0).setMinWidth(0);
            jTableBancos.getColumnModel().getColumn(0).setPreferredWidth(0);
            jTableBancos.getColumnModel().getColumn(0).setMaxWidth(0);
            jTableBancos.getColumnModel().getColumn(1).setMinWidth(150);
            jTableBancos.getColumnModel().getColumn(1).setPreferredWidth(150);
            jTableBancos.getColumnModel().getColumn(1).setMaxWidth(150);
            jTableBancos.getColumnModel().getColumn(2).setMinWidth(150);
            jTableBancos.getColumnModel().getColumn(2).setPreferredWidth(150);
            jTableBancos.getColumnModel().getColumn(2).setMaxWidth(150);
            jTableBancos.getColumnModel().getColumn(3).setMinWidth(150);
            jTableBancos.getColumnModel().getColumn(3).setPreferredWidth(150);
            jTableBancos.getColumnModel().getColumn(3).setMaxWidth(150);
            jTableBancos.getColumnModel().getColumn(4).setMinWidth(100);
            jTableBancos.getColumnModel().getColumn(4).setPreferredWidth(100);
            jTableBancos.getColumnModel().getColumn(4).setMaxWidth(100);
            jTableBancos.getColumnModel().getColumn(5).setMinWidth(50);
            jTableBancos.getColumnModel().getColumn(5).setPreferredWidth(50);
            jTableBancos.getColumnModel().getColumn(5).setMaxWidth(50);
        }

        kGContaBancaria.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, 80));

        tfConta.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfContaFocusLost(evt);
            }
        });
        kGContaBancaria.add(tfConta, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 50, 150, 28));

        tfBancoObs.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfBancoObsFocusLost(evt);
            }
        });
        kGContaBancaria.add(tfBancoObs, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 50, 340, 28));

        tfAgencia.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfAgenciaFocusLost(evt);
            }
        });
        kGContaBancaria.add(tfAgencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 50, 150, 28));

        tfOperacao.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfOperacaoFocusLost(evt);
            }
        });
        kGContaBancaria.add(tfOperacao, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 50, 50, 28));

        btSalvarBanco.setBackground(new java.awt.Color(0, 153, 0));
        btSalvarBanco.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btSalvarBanco.setForeground(new java.awt.Color(255, 255, 255));
        btSalvarBanco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/save.png"))); // NOI18N
        btSalvarBanco.setPreferredSize(new java.awt.Dimension(60, 40));
        btSalvarBanco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSalvarBancoActionPerformed(evt);
            }
        });
        kGContaBancaria.add(btSalvarBanco, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, -1, -1));

        btExcluirBanco.setBackground(new java.awt.Color(255, 0, 0));
        btExcluirBanco.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btExcluirBanco.setForeground(new java.awt.Color(255, 255, 255));
        btExcluirBanco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/delete.png"))); // NOI18N
        btExcluirBanco.setPreferredSize(new java.awt.Dimension(60, 40));
        btExcluirBanco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExcluirBancoActionPerformed(evt);
            }
        });
        kGContaBancaria.add(btExcluirBanco, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 190, -1, -1));

        btAtualizarTerceiro.setBackground(new java.awt.Color(0, 102, 255));
        btAtualizarTerceiro.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btAtualizarTerceiro.setForeground(new java.awt.Color(255, 255, 255));
        btAtualizarTerceiro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/refresh.png"))); // NOI18N
        btAtualizarTerceiro.setEnabled(false);
        btAtualizarTerceiro.setMaximumSize(new java.awt.Dimension(60, 40));
        btAtualizarTerceiro.setMinimumSize(new java.awt.Dimension(60, 40));
        btAtualizarTerceiro.setPreferredSize(new java.awt.Dimension(60, 40));
        btAtualizarTerceiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAtualizarTerceiroActionPerformed(evt);
            }
        });
        kGContaBancaria.add(btAtualizarTerceiro, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 190, -1, -1));

        btAlterarTerceiro.setBackground(new java.awt.Color(0, 102, 255));
        btAlterarTerceiro.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btAlterarTerceiro.setForeground(new java.awt.Color(255, 255, 255));
        btAlterarTerceiro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Modify.png"))); // NOI18N
        btAlterarTerceiro.setMaximumSize(new java.awt.Dimension(60, 40));
        btAlterarTerceiro.setMinimumSize(new java.awt.Dimension(60, 40));
        btAlterarTerceiro.setPreferredSize(new java.awt.Dimension(60, 40));
        btAlterarTerceiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAlterarTerceiroActionPerformed(evt);
            }
        });
        kGContaBancaria.add(btAlterarTerceiro, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 190, -1, -1));

        btCancelarContato.setBackground(new java.awt.Color(255, 102, 0));
        btCancelarContato.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btCancelarContato.setForeground(new java.awt.Color(255, 255, 255));
        btCancelarContato.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Erase.png"))); // NOI18N
        btCancelarContato.setMaximumSize(new java.awt.Dimension(60, 40));
        btCancelarContato.setMinimumSize(new java.awt.Dimension(60, 40));
        btCancelarContato.setPreferredSize(new java.awt.Dimension(60, 40));
        btCancelarContato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarContatoActionPerformed(evt);
            }
        });
        kGContaBancaria.add(btCancelarContato, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 190, -1, -1));

        ContasFerias.add(kGContaBancaria);

        kGContaBancaria1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Ferias Registradas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(51, 51, 51))); // NOI18N
        kGContaBancaria1.setkBorderRadius(0);
        kGContaBancaria1.setkEndColor(new java.awt.Color(234, 239, 243));
        kGContaBancaria1.setkGradientFocus(0);
        kGContaBancaria1.setkStartColor(new java.awt.Color(255, 255, 255));
        kGContaBancaria1.setPreferredSize(new java.awt.Dimension(1260, 300));
        kGContaBancaria1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLBanco2.setText("Data de Admissão:");
        kGContaBancaria1.add(jLBanco2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 110, -1));

        jLTipoConta1.setText("Ultimas Férias");
        kGContaBancaria1.add(jLTipoConta1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 30, -1, -1));

        jLBancoConta1.setText("Vencimento");
        kGContaBancaria1.add(jLBancoConta1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 30, 90, -1));

        jLBancoObs1.setText("Observações:");
        kGContaBancaria1.add(jLBancoObs1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 30, 120, -1));

        jScrollPane7.setBackground(new java.awt.Color(247, 247, 247));
        jScrollPane7.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane7.setPreferredSize(new java.awt.Dimension(1170, 170));

        jTableFerias.setAutoCreateRowSorter(true);
        jTableFerias.setBackground(new java.awt.Color(247, 247, 247));
        jTableFerias.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jTableFerias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cod.", "Ultimas Férias", "Vencimento", "Observações"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableFerias.setGridColor(new java.awt.Color(247, 247, 247));
        jTableFerias.setMinimumSize(new java.awt.Dimension(610, 0));
        jTableFerias.setRowHeight(20);
        jTableFerias.setSelectionBackground(new java.awt.Color(0, 153, 0));
        jTableFerias.getTableHeader().setReorderingAllowed(false);
        jScrollPane7.setViewportView(jTableFerias);
        if (jTableFerias.getColumnModel().getColumnCount() > 0) {
            jTableFerias.getColumnModel().getColumn(0).setMinWidth(0);
            jTableFerias.getColumnModel().getColumn(0).setPreferredWidth(0);
            jTableFerias.getColumnModel().getColumn(0).setMaxWidth(0);
            jTableFerias.getColumnModel().getColumn(1).setMinWidth(150);
            jTableFerias.getColumnModel().getColumn(1).setPreferredWidth(150);
            jTableFerias.getColumnModel().getColumn(1).setMaxWidth(150);
            jTableFerias.getColumnModel().getColumn(2).setMinWidth(150);
            jTableFerias.getColumnModel().getColumn(2).setPreferredWidth(150);
            jTableFerias.getColumnModel().getColumn(2).setMaxWidth(150);
        }

        kGContaBancaria1.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 1000, 180));

        tfObsFerias.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfObsFeriasFocusLost(evt);
            }
        });
        kGContaBancaria1.add(tfObsFerias, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 50, 660, 28));

        btSalvarFerias.setBackground(new java.awt.Color(0, 153, 0));
        btSalvarFerias.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btSalvarFerias.setForeground(new java.awt.Color(255, 255, 255));
        btSalvarFerias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/save.png"))); // NOI18N
        btSalvarFerias.setPreferredSize(new java.awt.Dimension(60, 40));
        btSalvarFerias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSalvarFeriasActionPerformed(evt);
            }
        });
        kGContaBancaria1.add(btSalvarFerias, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 110, -1, -1));

        btExcluirFerias.setBackground(new java.awt.Color(255, 0, 0));
        btExcluirFerias.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btExcluirFerias.setForeground(new java.awt.Color(255, 255, 255));
        btExcluirFerias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/delete.png"))); // NOI18N
        btExcluirFerias.setPreferredSize(new java.awt.Dimension(60, 40));
        btExcluirFerias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExcluirFeriasActionPerformed(evt);
            }
        });
        kGContaBancaria1.add(btExcluirFerias, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 160, -1, -1));

        jDUltimasFerias.setPreferredSize(new java.awt.Dimension(150, 30));
        kGContaBancaria1.add(jDUltimasFerias, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, -1, -1));

        jDVencimento.setPreferredSize(new java.awt.Dimension(150, 30));
        kGContaBancaria1.add(jDVencimento, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 50, -1, -1));

        btExportarFerias.setBackground(new java.awt.Color(0, 153, 0));
        btExportarFerias.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btExportarFerias.setForeground(new java.awt.Color(255, 255, 255));
        btExportarFerias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/excel.png"))); // NOI18N
        btExportarFerias.setText("EXPORTAR");
        btExportarFerias.setPreferredSize(new java.awt.Dimension(140, 40));
        btExportarFerias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExportarFeriasActionPerformed(evt);
            }
        });
        kGContaBancaria1.add(btExportarFerias, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 220, -1, -1));

        btAtualizarFerias.setBackground(new java.awt.Color(0, 102, 255));
        btAtualizarFerias.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btAtualizarFerias.setForeground(new java.awt.Color(255, 255, 255));
        btAtualizarFerias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/refresh.png"))); // NOI18N
        btAtualizarFerias.setEnabled(false);
        btAtualizarFerias.setMaximumSize(new java.awt.Dimension(60, 40));
        btAtualizarFerias.setMinimumSize(new java.awt.Dimension(60, 40));
        btAtualizarFerias.setPreferredSize(new java.awt.Dimension(60, 40));
        btAtualizarFerias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAtualizarFeriasActionPerformed(evt);
            }
        });
        kGContaBancaria1.add(btAtualizarFerias, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 110, -1, -1));

        btAlterarTerceiro1.setBackground(new java.awt.Color(0, 102, 255));
        btAlterarTerceiro1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btAlterarTerceiro1.setForeground(new java.awt.Color(255, 255, 255));
        btAlterarTerceiro1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Modify.png"))); // NOI18N
        btAlterarTerceiro1.setMaximumSize(new java.awt.Dimension(60, 40));
        btAlterarTerceiro1.setMinimumSize(new java.awt.Dimension(60, 40));
        btAlterarTerceiro1.setPreferredSize(new java.awt.Dimension(60, 40));
        btAlterarTerceiro1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAlterarTerceiro1ActionPerformed(evt);
            }
        });
        kGContaBancaria1.add(btAlterarTerceiro1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 160, -1, -1));

        btCancelarContato1.setBackground(new java.awt.Color(255, 102, 0));
        btCancelarContato1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btCancelarContato1.setForeground(new java.awt.Color(255, 255, 255));
        btCancelarContato1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Erase.png"))); // NOI18N
        btCancelarContato1.setMaximumSize(new java.awt.Dimension(60, 40));
        btCancelarContato1.setMinimumSize(new java.awt.Dimension(60, 40));
        btCancelarContato1.setPreferredSize(new java.awt.Dimension(60, 40));
        btCancelarContato1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarContato1ActionPerformed(evt);
            }
        });
        kGContaBancaria1.add(btCancelarContato1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 160, -1, -1));

        tfMostraAdmissao.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tfMostraAdmissao.setForeground(new java.awt.Color(51, 0, 0));
        tfMostraAdmissao.setText("99/99/9999");
        kGContaBancaria1.add(tfMostraAdmissao, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 55, -1, -1));

        ContasFerias.add(kGContaBancaria1);

        jTabbedCadFuncionarios.addTab("CONTAS E FÉRIAS", ContasFerias);

        jPanel2.add(jTabbedCadFuncionarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1270, -1));

        jPanelSalvarCancelar.setBackground(new java.awt.Color(255, 255, 255));
        jPanelSalvarCancelar.setMinimumSize(new java.awt.Dimension(1264, 50));
        jPanelSalvarCancelar.setPreferredSize(new java.awt.Dimension(1264, 65));
        jPanelSalvarCancelar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btCancelaNOBLOCK.setBackground(new java.awt.Color(195, 3, 18));
        btCancelaNOBLOCK.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btCancelaNOBLOCK.setForeground(new java.awt.Color(255, 255, 255));
        btCancelaNOBLOCK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Close.png"))); // NOI18N
        btCancelaNOBLOCK.setText("CANCELAR");
        btCancelaNOBLOCK.setToolTipText("");
        btCancelaNOBLOCK.setMaximumSize(new java.awt.Dimension(100, 25));
        btCancelaNOBLOCK.setMinimumSize(new java.awt.Dimension(120, 30));
        btCancelaNOBLOCK.setPreferredSize(new java.awt.Dimension(130, 35));
        btCancelaNOBLOCK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelaNOBLOCKActionPerformed(evt);
            }
        });
        jPanelSalvarCancelar.add(btCancelaNOBLOCK, new org.netbeans.lib.awtextra.AbsoluteConstraints(497, 10, -1, -1));

        btSalvarNOBLOCK.setBackground(new java.awt.Color(0, 153, 0));
        btSalvarNOBLOCK.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btSalvarNOBLOCK.setForeground(new java.awt.Color(255, 255, 255));
        btSalvarNOBLOCK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/save.png"))); // NOI18N
        btSalvarNOBLOCK.setText("SALVAR");
        btSalvarNOBLOCK.setMaximumSize(new java.awt.Dimension(100, 25));
        btSalvarNOBLOCK.setMinimumSize(new java.awt.Dimension(100, 25));
        btSalvarNOBLOCK.setPreferredSize(new java.awt.Dimension(150, 40));
        btSalvarNOBLOCK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSalvarNOBLOCKActionPerformed(evt);
            }
        });
        jPanelSalvarCancelar.add(btSalvarNOBLOCK, new org.netbeans.lib.awtextra.AbsoluteConstraints(632, 10, -1, -1));

        jPanel2.add(jPanelSalvarCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 590, -1, -1));

        getContentPane().add(jPanel2, java.awt.BorderLayout.LINE_END);

        getAccessibleContext().setAccessibleParent(this);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tfCodigoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfCodigoFocusLost
        // CODIGO
        this.tfCodigo.getText().toUpperCase();
    }//GEN-LAST:event_tfCodigoFocusLost

    private void tfNomeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfNomeFocusLost
        // Letras maiusculas
        this.tfNome.setText(tfNome.getText().toUpperCase());
    }//GEN-LAST:event_tfNomeFocusLost

    private void tfRgFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfRgFocusLost
        this.tfRg.setText(tfRg.getText().toUpperCase());
    }//GEN-LAST:event_tfRgFocusLost

    private void tfObservacaoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfObservacaoFocusLost
        this.tfObservacao.setText(tfObservacao.getText().toUpperCase());
    }//GEN-LAST:event_tfObservacaoFocusLost

    private void tfCnhFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfCnhFocusLost
        // TODO add your handling code here:
        this.tfCnh.setText(tfCnh.getText().toUpperCase());
    }//GEN-LAST:event_tfCnhFocusLost

    private void jfCpfFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jfCpfFocusLost
        // CPF
        this.jfCpf.setText(jfCpf.getText().toUpperCase());
    }//GEN-LAST:event_jfCpfFocusLost

    private void tfFiliacaoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfFiliacaoFocusLost
        // TODO add your handling code here:
        this.tfFiliacao.setText(tfFiliacao.getText().toUpperCase());
    }//GEN-LAST:event_tfFiliacaoFocusLost

    private void tfObservacaoOcupacaoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfObservacaoOcupacaoFocusLost
        // TODO add your handling code here:
        if ("".equals(tfObservacaoOcupacao.getText())) {
            this.tfObservacaoOcupacao.setText("Nehuma obeservação.");
        }
        this.tfObservacaoOcupacao.setText(tfObservacaoOcupacao.getText());
    }//GEN-LAST:event_tfObservacaoOcupacaoFocusLost

    private void cbVinculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbVinculoActionPerformed
        // TODO add your handling code here:
        this.cbVinculo.setPrototypeDisplayValue(cbVinculo.getPropertyChangeListeners(tipoCadastro));
        verificarFeriasporVinculo();
    }//GEN-LAST:event_cbVinculoActionPerformed

    private void tfEmailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfEmailFocusLost
        this.tfEmail.setText(tfEmail.getText().toUpperCase());
    }//GEN-LAST:event_tfEmailFocusLost

    private void tfComplementoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfComplementoFocusLost
        this.tfComplemento.setText(tfComplemento.getText().toUpperCase());
    }//GEN-LAST:event_tfComplementoFocusLost

    private void tfEnderecoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfEnderecoFocusLost
        this.tfEndereco.setText(tfEndereco.getText().toUpperCase());
    }//GEN-LAST:event_tfEnderecoFocusLost

    private void tfBairroFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfBairroFocusLost
        this.tfBairro.setText(tfBairro.getText().toUpperCase());
    }//GEN-LAST:event_tfBairroFocusLost

    private void cbUfPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbUfPopupMenuWillBecomeInvisible
        if (this.cbUf.isPopupVisible()) {
            listarCidades();
        }
    }//GEN-LAST:event_cbUfPopupMenuWillBecomeInvisible

    private void cbUfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbUfActionPerformed

    }//GEN-LAST:event_cbUfActionPerformed

    private void tfNumeroFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfNumeroFocusLost
        // TODO add your handling code here:
        this.tfNumero.setText(tfNumero.getText().toUpperCase());
    }//GEN-LAST:event_tfNumeroFocusLost

    private void jfCepFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jfCepFocusLost
        // CEP
        this.jfCep.setText(jfCep.getText().toUpperCase());
    }//GEN-LAST:event_jfCepFocusLost

    private void jfTelefoneFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jfTelefoneFocusLost
        // Telefone
        this.jfTelefone.setText(jfTelefone.getText().toUpperCase());
    }//GEN-LAST:event_jfTelefoneFocusLost

    private void jfCelularFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jfCelularFocusLost
        // Celular
        this.jfCelular.setText(jfCelular.getText().toUpperCase());
    }//GEN-LAST:event_jfCelularFocusLost

    private void tfObsContatoFuncionarioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfObsContatoFuncionarioFocusLost
        // tfObsFuncionario
        if ("".equals(tfObsContatoFuncionario.getText())) {
            this.tfObsContatoFuncionario.setText("Nehuma obeservação.");
        }
        this.tfObsContatoFuncionario.setText(tfObsContatoFuncionario.getText());
    }//GEN-LAST:event_tfObsContatoFuncionarioFocusLost

    private void cbTipoContaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTipoContaActionPerformed
        // TODO add your handling code here:
        //this.cbOcupacao.setPrototypeDisplayValue(cbOcupacao.getPropertyChangeListeners(tipoCadastro));
    }//GEN-LAST:event_cbTipoContaActionPerformed

    private void cbBancoPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbBancoPopupMenuWillBecomeInvisible
        // Listar bancos do SQL
        if (this.cbBanco.isPopupVisible() && cbBanco.getSelectedIndex() >= 0) {
            modelBancos = controllerBancos.getBancosControllerNome(cbBanco.getSelectedItem().toString());
            //recupera o nome
            this.cbCodBanco.setSelectedItem(Integer.toString(modelBancos.getCodigo()));
            //this.tfFuturoCampo.setText(String.valueOf(modelBancos.getFUrutoCampo()));
        }
    }//GEN-LAST:event_cbBancoPopupMenuWillBecomeInvisible

    private void tfContaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfContaFocusLost
        // CONTA BANCO
        this.tfConta.setText(tfConta.getText().toUpperCase());
    }//GEN-LAST:event_tfContaFocusLost

    private void tfBancoObsFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfBancoObsFocusLost
        // Observações do Banco
        if ("".equals(tfBancoObs.getText())) {
            this.tfBancoObs.setText("Nehuma obeservação.");
        }
        this.tfBancoObs.setText(tfBancoObs.getText());
    }//GEN-LAST:event_tfBancoObsFocusLost

    private void tfAgenciaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfAgenciaFocusLost
        // AGENCIA
        this.tfAgencia.setText(tfAgencia.getText().toUpperCase());
    }//GEN-LAST:event_tfAgenciaFocusLost

    private void tfOperacaoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfOperacaoFocusLost
        // Operação da Conta
        try {
            this.tfOperacao.setText(tfOperacao.getText().toUpperCase());
        } catch (NumberFormatException e) {
            tfOperacao.setText("");
        }
    }//GEN-LAST:event_tfOperacaoFocusLost

    private void cbCodBancoPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbCodBancoPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
        if (this.cbCodBanco.isPopupVisible()) {
            modelBancos = controllerBancos.getBancosController(Integer.parseInt(cbCodBanco.getSelectedItem().toString()));
            //recupera o código
            this.cbBanco.setSelectedItem(String.valueOf(modelBancos.getBanco_nome()));
            //this.tfFuturoCampo.setText(String.valueOf(modelServicos.getFuturocmapo()));
        }
    }//GEN-LAST:event_cbCodBancoPopupMenuWillBecomeInvisible

    private void btCancelaNOBLOCKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelaNOBLOCKActionPerformed
        limpaCampos();
        dispose();
        ViewFuncionario.Funcionario.requestFocus();
    }//GEN-LAST:event_btCancelaNOBLOCKActionPerformed

    private void btAlterarBancoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAlterarBancoActionPerformed
        // TODO add your handling code here:
        int fila = jTableBancos.getSelectedRow();
        if (fila >= 0) {
            btSalvarNOBLOCK.setEnabled(false);
            btAtualizarBanco.setEnabled(true);
            String cod = jTableBancos.getValueAt(fila, 0).toString();
            String banco = jTableBancos.getValueAt(fila, 1).toString();
            String conta = jTableBancos.getValueAt(fila, 2).toString();
            String agencia = jTableBancos.getValueAt(fila, 3).toString();
            String tipo = jTableBancos.getValueAt(fila, 4).toString();
            String op = jTableBancos.getValueAt(fila, 5).toString();
            String obs = jTableBancos.getValueAt(fila, 6).toString();
            tfCodigo.setText(cod);
            cbBanco.setSelectedItem(banco);
            cbCodBanco.setSelectedItem(controllerBancos.getBancosControllerNome(banco).getCodigo());
            tfConta.setText(conta);
            tfAgencia.setText(agencia);
            cbTipoConta.setSelectedItem(tipo);
            tfOperacao.setText(op);
            tfBancoObs.setText(obs);
        } else {
            JOptionPane.showMessageDialog(null, "Escolha uma linha na tabela antes!");
        }
    }//GEN-LAST:event_btAlterarBancoActionPerformed

    private void btExcluirBancoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirBancoActionPerformed
        // TODO add your handling code here:
        int fila = jTableBancos.getSelectedRow();
        if (fila > -1) {
            String cod = jTableBancos.getValueAt(fila, 0).toString();
            String sqlElim = "DELETE FROM contas_bancos WHERE codigo='" + cod + "'";
            try {
                PreparedStatement pst = cn.prepareStatement(sqlElim);
                int n = pst.executeUpdate();
                if (n > 0) {
                    JOptionPane.showMessageDialog(null, "Os dados foram atualizados com sucesso!");
                    carrerBancosPorFunc();

                } else {
                    JOptionPane.showMessageDialog(null, "Ocorreu um erro ao atualizar os dados");
                }
            } catch (SQLException ex) {
                Logger.getLogger(ViewCadFuncionario.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_btExcluirBancoActionPerformed

    private void btSalvarBancoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarBancoActionPerformed
        // TODO add your handling code here:
        if (!("".equals(tfAgencia.getText())) && !("".equals(tfConta.getText())) && (cbBanco.getSelectedItem() != null) && (cbTipoConta.getSelectedItem() != null)) {
            modelBancos = controllerBancos.getBancosControllerNome(cbBanco.getSelectedItem().toString());
            int codigobanco = 0;
            codigobanco = controllerBancos.getBancosControllerNome(this.cbBanco.getSelectedItem().toString()).getCodigo();

            String insertar = "INSERT INTO contas_bancos(cod_func,cod_banco,banco_conta,banco_tipo,banco_agencia,banco_op,banco_obs) VALUES (?,?,?,?,?,?,?)";
            try {
                // variável para obter o número de bytes de uma imagem.
                PreparedStatement pst = cn.prepareStatement(insertar);
                pst.setString(1, tfCodigo.getText());
                pst.setInt(2, codigobanco);
                pst.setString(3, tfConta.getText());
                pst.setString(4, cbTipoConta.getSelectedItem().toString());
                pst.setString(5, tfAgencia.getText());
                pst.setString(6, tfOperacao.getText());
                pst.setString(7, tfBancoObs.getText());

                int i = pst.executeUpdate();
                if (i > 0) {
                    JOptionPane.showMessageDialog(null, "BANCO SALVO COM SUCESSO!");
                }
                carrerBancosPorFunc();
                limparCamposBanco();
            } catch (SQLException ex) {
                Logger.getLogger(ViewCadFuncionario.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "PREENCHA TODOS OS CAMPOS!");
        }
    }//GEN-LAST:event_btSalvarBancoActionPerformed

    private void btAtualizarBancoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAtualizarBancoActionPerformed
        // TODO add your handling code here:
        modelBancos = controllerBancos.getBancosControllerNome(cbBanco.getSelectedItem().toString());
        int codigobanco = 0;
        codigobanco = controllerBancos.getBancosControllerNome(this.cbBanco.getSelectedItem().toString()).getCodigo();
        int cod = 0;
        cod = (Integer.parseInt(tfCodigo.getText()));
        try {
            //estas sentencia ya lo explique en insertar datos que esta mas arriba
            FileInputStream archivofoto;
            String sql = "UPDATE contas_bancos SET codigo = ?,"
                    + "cod_func = ?,"
                    + "cod_banco = ?,"
                    + "banco_conta = ?,"
                    + "banco_tipo = ?,"
                    + "banco_agencia = ?,"
                    + "banco_op = ?,"
                    + "banco_obs = ?"
                    + "WHERE cod_func = '" + cod + "'";

            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, cod);
            pst.setInt(2, cod);
            pst.setInt(3, codigobanco);
            pst.setString(4, tfConta.getText());
            pst.setString(5, cbTipoConta.getSelectedItem().toString());
            pst.setString(6, tfAgencia.getText());
            pst.setString(7, tfOperacao.getText());
            pst.setString(8, tfBancoObs.getText());

            int n = pst.executeUpdate();
            if (n > 0) {
                JOptionPane.showMessageDialog(null, "BANCO ATUALIZADO COM SUCESSO!");
                carrerBancosPorFunc();
                limparCamposBanco();
            } else {
                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao atualizar os dados");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ViewCadFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btAtualizarBancoActionPerformed

    private void btSalvarNOBLOCKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarNOBLOCKActionPerformed
        //String tipoCadastro = "novo";
        if (viewFuncionario.tipoCadastro != null && viewFuncionario.tipoCadastro.equals("alteracao")) {
            try {
                alteraFuncionario();
            } catch (Exception ex) {
                Logger.getLogger(ViewCadFuncionario.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                salvarFuncionario();
            } catch (Exception ex) {
                Logger.getLogger(ViewCadFuncionario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btSalvarNOBLOCKActionPerformed

    private void btCidadeAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCidadeAddActionPerformed
        // TODO add your handling code here:
        cidade.setVisible(true);
        listarOcupacao();
    }//GEN-LAST:event_btCidadeAddActionPerformed

    private void cbOcupacaoPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbOcupacaoPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
        if (this.cbOcupacao.isPopupVisible()) {
            modelOcupacao = controllerOcupacao.getOcupacaoControllerNome(String.valueOf(cbOcupacao.getSelectedItem()));
            //recupera o nome
            //this.cbOcupacao.setSelectedItem(String.valueOf(modelOcupacao.getOcup_descricao()));
            verificarFeriasporVinculo();
        }
    }//GEN-LAST:event_cbOcupacaoPopupMenuWillBecomeInvisible

    private void tfBairroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfBairroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfBairroActionPerformed

    private void btSalvarFeriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarFeriasActionPerformed
        // TODO add your handling code here:
        Datas bl = new Datas();
        if (!("".equals(tfMostraAdmissao.getText())) && (jDUltimasFerias.getDate() != null) && (jDVencimento.getDate() != null)) {
            String insertar = "INSERT INTO cadastro_ferias(cod_func,data_uferias,data_vferias,obs) VALUES (?,?,?,?)";
            try {
                PreparedStatement pst = cn.prepareStatement(insertar);
                pst.setString(1, tfCodigo.getText());
                pst.setDate(2, (Date) (bl.converterDataParaDateUS(jDUltimasFerias.getDate())));
                pst.setDate(3, (Date) (bl.converterDataParaDateUS(jDVencimento.getDate())));
                pst.setString(4, tfObsFerias.getText());

                int i = pst.executeUpdate();
                if (i > 0) {
                    JOptionPane.showMessageDialog(null, "REGISTRO DE FÉRIAS SALVO COM SUCESSO!");
                }
                carregarFeriasRegistradas();
                limparCamposFerias();
            } catch (SQLException ex) {
                Logger.getLogger(ViewCadFuncionario.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(ViewCadFuncionario.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "PREENCHA TODOS OS CAMPOS!");
        }
    }//GEN-LAST:event_btSalvarFeriasActionPerformed

    private void btExcluirFeriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirFeriasActionPerformed
        // TODO add your handling code here:
        int fila = jTableFerias.getSelectedRow();
        if (fila > -1) {
            String cod = jTableFerias.getValueAt(fila, 0).toString();
            String sqlElim = "DELETE FROM cadastro_ferias WHERE codigo='" + cod + "'";
            try {
                PreparedStatement pst = cn.prepareStatement(sqlElim);
                int n = pst.executeUpdate();
                if (n > 0) {
                    JOptionPane.showMessageDialog(null, "Os dados foram apagados com sucesso!");
                    carregarFeriasRegistradas();

                } else {
                    JOptionPane.showMessageDialog(null, "Ocorreu um erro ao apagar os dados");
                }
            } catch (SQLException ex) {
                Logger.getLogger(ViewCadFuncionario.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_btExcluirFeriasActionPerformed

    private void tfObsFeriasFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfObsFeriasFocusLost
        // TODO add your handling code here:
        if ("".equals(tfObsFerias.getText())) {
            this.tfObsFerias.setText("Nehuma obeservação.");
        }
        this.tfObsFerias.setText(tfObsFerias.getText());
    }//GEN-LAST:event_tfObsFeriasFocusLost

    private void btExportarFeriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExportarFeriasActionPerformed
        //exportar
        if (jTableFerias.getRowCount() > 0) {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de excel", "xls");
            chooser.setFileFilter(filter);
            chooser.setDialogTitle("Salvar Arquivo");
            chooser.setAcceptAllFileFilterUsed(false);
            if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                List<JTable> tb = new ArrayList<JTable>();
                List<String> nom = new ArrayList<String>();
                tb.add(jTableFerias);
                nom.add("Personas");
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
    }//GEN-LAST:event_btExportarFeriasActionPerformed

    private void jFAdmissaoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFAdmissaoFocusLost
        // TODO add your handling code here:
        //Datas bl = new Datas();
        tfMostraAdmissao.setText(String.valueOf(jFAdmissao.getDate()));
    }//GEN-LAST:event_jFAdmissaoFocusLost

    private void btAtualizarTerceiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAtualizarTerceiroActionPerformed
        // TODO add your handling code here:
        modelBancos = controllerBancos.getBancosControllerNome(cbBanco.getSelectedItem().toString());
        int codigobanco = 0;
        codigobanco = controllerBancos.getBancosControllerNome(this.cbBanco.getSelectedItem().toString()).getCodigo();
        try {

            String sql = "UPDATE contas_bancos SET cod_banco = ?, "
                    + "banco_tipo = ?,"
                    + "banco_conta = ?,"
                    + "banco_agencia = ?,"
                    + "banco_op = ?,"
                    + "banco_obs = ?"
                    + "WHERE codigo = '" + tfCodBanco.getText() + "'";

            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, codigobanco);
            pst.setString(2, cbTipoConta.getSelectedItem().toString());
            pst.setString(3, tfConta.getText());
            pst.setString(4, tfAgencia.getText());
            pst.setString(5, tfOperacao.getText());
            pst.setString(6, tfBancoObs.getText());
            int n = pst.executeUpdate();

            if (n > 0) {
                JOptionPane.showMessageDialog(null, "Os dados foram atualizados com sucesso!");
                tfCodBanco.setText(null);
                limparCamposBanco();
                btAtualizarTerceiro.setEnabled(false);
                btAlterarTerceiro.setVisible(true);
                btAlterarTerceiro.setEnabled(true);
                btCancelarContato.setVisible(false);
                btCancelarContato.setEnabled(true);
                btSalvarBanco.setEnabled(true);
                carrerBancosPorFunc();
            } else {
                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao atualizar os dados");
                this.carrerBancosPorFunc();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ViewCadOp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btAtualizarTerceiroActionPerformed

    private void btCancelarContatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarContatoActionPerformed
        // TODO add your handling code here:
        carrerBancosPorFunc();
        limparCamposBanco();
        btSalvarBanco.setEnabled(true);
        btAtualizarTerceiro.setEnabled(false);
        btAlterarTerceiro.setVisible(true);
        btAlterarTerceiro.setEnabled(true);
        btCancelarContato.setVisible(false);
        btCancelarContato.setEnabled(true);
    }//GEN-LAST:event_btCancelarContatoActionPerformed

    private void btAlterarTerceiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAlterarTerceiroActionPerformed
        // TODO add your handling code here:

        int fila = jTableBancos.getSelectedRow();
        if (fila >= 0) {
            btSalvarBanco.setEnabled(false);
            btAtualizarTerceiro.setEnabled(true);
            String cod = jTableBancos.getValueAt(fila, 0).toString();
            String banco = jTableBancos.getValueAt(fila, 1).toString();
            String conta = jTableBancos.getValueAt(fila, 2).toString();
            String agencia = jTableBancos.getValueAt(fila, 3).toString();
            String tipo = jTableBancos.getValueAt(fila, 4).toString();
            String op = jTableBancos.getValueAt(fila, 5).toString();
            String obs = jTableBancos.getValueAt(fila, 6).toString();
            tfCodBanco.setText(cod);
            cbBanco.setSelectedItem(banco);
            tfConta.setText(conta);
            tfAgencia.setText(agencia);
            cbTipoConta.setSelectedItem(tipo);
            tfOperacao.setText(op);
            tfBancoObs.setText(obs);
            btAlterarTerceiro.setEnabled(false);
            btAlterarTerceiro.setVisible(false);
            btCancelarContato.setVisible(true);
            btCancelarContato.setEnabled(true);
            this.carrerBancosPorFunc();
        } else {
            JOptionPane.showMessageDialog(null, "Escolha uma conta na tabela antes!");
        }
    }//GEN-LAST:event_btAlterarTerceiroActionPerformed

    private void btAtualizarFeriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAtualizarFeriasActionPerformed
        // TODO add your handling code here:
        Datas bl = new Datas();
        try {

            String sql = "UPDATE cadastro_ferias SET data_uferias = ?, "
                    + "data_vferias = ?,"
                    + "obs = ?"
                    + "WHERE codigo = '" + tfCodFerias.getText() + "'";

            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setDate(1, (Date) (bl.converterDataParaDateUS(jDUltimasFerias.getDate())));
            pst.setDate(2, (Date) (bl.converterDataParaDateUS(jDVencimento.getDate())));
            pst.setString(3, tfObsFerias.getText());
            int n = pst.executeUpdate();

            if (n > 0) {
                JOptionPane.showMessageDialog(null, "Os dados foram atualizados com sucesso!");
                tfCodFerias.setText(null);
                // TODO add your handling code here:
                btSalvarFerias.setEnabled(true);
                this.carregarFeriasRegistradas();
                limparCamposFerias();
                btAtualizarTerceiro.setEnabled(false);
                btAlterarTerceiro.setVisible(true);
                btAlterarTerceiro.setEnabled(true);
                btCancelarContato.setVisible(false);
                btCancelarContato.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao atualizar os dados");
                this.carregarFeriasRegistradas();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ViewCadOp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ViewCadFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btAtualizarFeriasActionPerformed

    private void btCancelarContato1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarContato1ActionPerformed
        // TODO add your handling code here:
        carregarFeriasRegistradas();
        limparCamposFerias();
        btSalvarFerias.setEnabled(true);
        btAtualizarTerceiro.setEnabled(false);
        btAlterarTerceiro.setVisible(true);
        btAlterarTerceiro.setEnabled(true);
        btCancelarContato.setVisible(false);
        btCancelarContato.setEnabled(true);
    }//GEN-LAST:event_btCancelarContato1ActionPerformed

    private void btAlterarTerceiro1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAlterarTerceiro1ActionPerformed
        // TODO add your handling code here:

        int fila = jTableFerias.getSelectedRow();
        if (fila >= 0) {
            btSalvarFerias.setEnabled(false);
            btAtualizarFerias.setEnabled(true);
            String cod = jTableFerias.getValueAt(fila, 0).toString();
            String ultimas = jTableFerias.getValueAt(fila, 1).toString();
            String vencimento = jTableFerias.getValueAt(fila, 2).toString();
            String obs = jTableFerias.getValueAt(fila, 3).toString();
            tfCodFerias.setText(cod);
            try {
                Date uferias = Date.valueOf(new Mascaras().DataSalvanosql(ultimas));
                jDUltimasFerias.setDate(uferias);
            } catch (NumberFormatException e) {
                jDUltimasFerias.setDate(null);
            }
            try {
                Date vferias = Date.valueOf(new Mascaras().DataSalvanosql(vencimento));
                jDVencimento.setDate(vferias);
            } catch (NumberFormatException e) {
                jDVencimento.setDate(null);
            }

            tfObsFerias.setText(obs);
            btAlterarTerceiro.setEnabled(false);
            btAlterarTerceiro.setVisible(false);
            btCancelarContato.setVisible(true);
            btCancelarContato.setEnabled(true);
            this.carregarFeriasRegistradas();
        } else {
            JOptionPane.showMessageDialog(null, "Escolha uma linha na tabela antes!");
        }
    }//GEN-LAST:event_btAlterarTerceiro1ActionPerformed

    private void cbCidadePopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbCidadePopupMenuWillBecomeVisible
        // TODO add your handling code here:
        listarCidades();
    }//GEN-LAST:event_cbCidadePopupMenuWillBecomeVisible

    private void cbUfPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbUfPopupMenuWillBecomeVisible
        // TODO add your handling code here:
        listarEstados();
    }//GEN-LAST:event_cbUfPopupMenuWillBecomeVisible

    private void cbUfFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cbUfFocusLost
        // TODO add your handling code here:
        listarCidades();
    }//GEN-LAST:event_cbUfFocusLost

    private void cbVinculoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cbVinculoFocusLost
        // TODO add your handling code here:
        verificarFeriasporVinculo();
    }//GEN-LAST:event_cbVinculoFocusLost

    private void cbOcupacaoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cbOcupacaoFocusLost
        // TODO add your handling code here:
        verificarFeriasporVinculo();
    }//GEN-LAST:event_cbOcupacaoFocusLost

    private void kButtonInformacoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButtonInformacoesActionPerformed
        // TODO add your handling code here:
        jTabbedCadFuncionarios.setSelectedIndex(0);
        panelTabbedKGVerficiar();
    }//GEN-LAST:event_kButtonInformacoesActionPerformed

    private void kButtonContasFeriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButtonContasFeriasActionPerformed
        // TODO add your handling code here:
        jTabbedCadFuncionarios.setSelectedIndex(1);
        panelTabbedKGVerficiar();
    }//GEN-LAST:event_kButtonContasFeriasActionPerformed

    /**
     * Preencher combobox estados
     */
    public void listarEstados() {
        listaModelEstados = controllerEstado.getListaEstadoController();
        cbUf.removeAllItems();
        for (int i = 0; i < listaModelEstados.size(); i++) {
            cbUf.addItem(listaModelEstados.get(i).getUf());
        }
    }

    private void panelTabbedKGVerficiar() {
        if (jTabbedCadFuncionarios.getSelectedIndex() == 0) {
            kButtonInformacoes.setSelected(true);
        } else {
            kButtonInformacoes.setSelected(false);
        }
    }

    /**
     * Preencher combobox cidades
     */
    public void listarCidades() {
        listaModelCidades = controllerCidade.getListaCidadePorEstadoController(controllerEstado.getEstadoUFController(this.cbUf.getSelectedItem().toString()).getCodigo());
        cbCidade.removeAllItems();
        for (int i = 0; i < listaModelCidades.size(); i++) {
            cbCidade.addItem(listaModelCidades.get(i).getNome());
        }
    }

    private boolean salvarFuncionario() throws Exception {
        Datas bl = new Datas();

        modelFuncionario.setNome(this.tfNome.getText().toUpperCase());
        modelFuncionario.setEndereco(this.tfEndereco.getText());
        modelFuncionario.setBairro(this.tfBairro.getText());
        modelFuncionario.setCodCidade(controllerCidade.getCidadeController(this.cbCidade.getSelectedItem().toString()).getCodigo());
        modelFuncionario.setCodEstado(controllerEstado.getEstadoUFController(this.cbUf.getSelectedItem().toString()).getCodigo());
        modelFuncionario.setCep(this.jfCep.getText());
        modelFuncionario.setTelefone(this.jfTelefone.getText());
        modelFuncionario.setCelular(this.jfCelular.getText());
        modelFuncionario.setEmail(this.tfEmail.getText());
        modelFuncionario.setCpf(this.jfCpf.getText());
        modelFuncionario.setRg(this.tfRg.getText());
        modelFuncionario.setObservacao(this.tfObservacao.getText());
        modelFuncionario.setNumero(Integer.parseInt(this.tfNumero.getText()));
        modelFuncionario.setComplemento(this.tfComplemento.getText());
        modelFuncionario.setNascimento(bl.converterDataParaDateUS(jFNascimento.getDate()));
        modelFuncionario.setData_admissao(bl.converterDataParaDateUS(jFAdmissao.getDate()));
        modelFuncionario.setData_uferias(new Mascaras().DataSalvanosql(jFUltimasFerias.getText()));
        modelFuncionario.setData_vferias(new Mascaras().DataSalvanosql(jFVencerFerias.getText()));
        modelFuncionario.setVinculo(this.cbVinculo.getSelectedItem().toString());
        modelFuncionario.setOcupacao(controllerOcupacao.getOcupacaoControllerNome(cbOcupacao.getSelectedItem().toString()).getCodigo());
        modelFuncionario.setCnh(this.tfCnh.getText());
        modelFuncionario.setFiliacao(this.tfFiliacao.getText());
        modelFuncionario.setOb_ocupacao(this.tfObservacaoOcupacao.getText());
        modelFuncionario.setObs_Func(this.tfObsContatoFuncionario.getText());
        //salvar 
        if (controllerFuncionario.salvarFuncionarioController(modelFuncionario) > 0) {
            JOptionPane.showMessageDialog(this, "Registro gravado com sucesso!");
            limpaCampos();
            dispose();
            ViewFuncionario.Funcionario.requestFocus();
            return true;
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao gravar os dados!", "ERRO", JOptionPane.ERROR_MESSAGE);
            return false;
        }

    }

    // mostrode
    private boolean alteraFuncionario() throws Exception {

        Datas bl = new Datas();
        tipoCadastro = "alteracao";
        modelFuncionario.setNome(this.tfNome.getText().toUpperCase());
        modelFuncionario.setEndereco(this.tfEndereco.getText());
        modelFuncionario.setBairro(this.tfBairro.getText());
        modelFuncionario.setCodCidade(controllerCidade.getCidadeController(this.cbCidade.getSelectedItem().toString()).getCodigo());
        modelFuncionario.setCodEstado(controllerEstado.getEstadoUFController(this.cbUf.getSelectedItem().toString()).getCodigo());
        modelFuncionario.setCep(this.jfCep.getText());
        modelFuncionario.setTelefone(this.jfTelefone.getText());
        modelFuncionario.setCelular(this.jfCelular.getText());
        modelFuncionario.setEmail(this.tfEmail.getText());
        modelFuncionario.setCpf(this.jfCpf.getText());
        modelFuncionario.setRg(this.tfRg.getText());
        modelFuncionario.setObservacao(this.tfObservacao.getText());
        modelFuncionario.setNumero(Integer.parseInt(this.tfNumero.getText()));
        modelFuncionario.setComplemento(this.tfComplemento.getText());
        modelFuncionario.setNascimento(bl.converterDataParaDateUS(jFNascimento.getDate()));
        modelFuncionario.setData_admissao(bl.converterDataParaDateUS(jFAdmissao.getDate()));
        modelFuncionario.setData_uferias(new Mascaras().DataSalvanosql(jFUltimasFerias.getText()));
        modelFuncionario.setData_vferias(new Mascaras().DataSalvanosql(jFVencerFerias.getText()));
        modelFuncionario.setVinculo(this.cbVinculo.getSelectedItem().toString());
        modelFuncionario.setOcupacao(controllerOcupacao.getOcupacaoControllerNome(cbOcupacao.getSelectedItem().toString()).getCodigo());
        modelFuncionario.setCnh(this.tfCnh.getText());
        modelFuncionario.setFiliacao(this.tfFiliacao.getText());
        modelFuncionario.setOb_ocupacao(this.tfObservacaoOcupacao.getText());
        modelFuncionario.setObs_Func(this.tfObsContatoFuncionario.getText());

        //alterar 
        if (controllerFuncionario.atualizarFuncionarioController(modelFuncionario)) {
            JOptionPane.showMessageDialog(this, "Registro alterado com sucesso!");
            viewFuncionario.tipoCadastro = null;
            Funcionario.carregarFuncionario();
            limpaCampos();
            dispose();
            ViewFuncionario.Funcionario.requestFocus();
            return true;
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao alterar os dados!", "ERRO", JOptionPane.ERROR_MESSAGE);
            return false;
        }

    }

    private boolean recuperarFuncionario() {
        listarOcupacao();
        Datas bl = new Datas();
        try {
            //recupera os dados do banco
            modelFuncionario = controllerFuncionario.getFuncionarioController(ViewFuncionario.codigo);
            //seta os dados na interface
            this.tfCodigo.setText(String.valueOf(modelFuncionario.getCodigo()));
            this.tfNome.setText(modelFuncionario.getNome());
            this.tfEndereco.setText(modelFuncionario.getEndereco());
            this.tfBairro.setText(modelFuncionario.getBairro());
            this.cbUf.setSelectedItem(controllerEstado.getEstadoController(modelFuncionario.getCodEstado()).getUf());
            this.listarCidades();
            this.cbCidade.setSelectedItem(controllerCidade.getCidadeController(modelFuncionario.getCodCidade()).getNome());
            this.jfCep.setText(modelFuncionario.getCep());
            this.jfTelefone.setText(modelFuncionario.getTelefone());
            this.jfCelular.setText(modelFuncionario.getCelular());
            this.tfEmail.setText(modelFuncionario.getEmail());
            this.tfRg.setText(modelFuncionario.getRg());
            this.jfCpf.setText(modelFuncionario.getCpf());
            this.tfObservacao.setText(modelFuncionario.getObservacao());
            this.tfNumero.setText("" + modelFuncionario.getNumero());
            this.tfComplemento.setText(modelFuncionario.getComplemento());
            this.jFAdmissao.setDate(modelFuncionario.getData_admissao());
            tfMostraAdmissao.setText(bl.formatarData(jFAdmissao.getDate()));
            this.jFNascimento.setDate(modelFuncionario.getNascimento());
            this.jFUltimasFerias.setText(new Mascaras().DataRecuperasql(modelFuncionario.getData_uferias()));
            this.jFVencerFerias.setText(new Mascaras().DataRecuperasql(modelFuncionario.getData_vferias()));
            this.cbVinculo.setSelectedItem((String) modelFuncionario.getVinculo());
            this.cbOcupacao.setSelectedItem(controllerOcupacao.getOcupacaoControllerCod(modelFuncionario.getOcupacao()).getOcup_descricao());
            this.tfObservacaoOcupacao.setText(modelFuncionario.getOb_ocupacao());
            this.tfCnh.setText(modelFuncionario.getCnh());
            this.tfFiliacao.setText(modelFuncionario.getFiliacao());
            this.tfObsContatoFuncionario.setText(modelFuncionario.getObs_Func());
            this.cbBanco.setSelectedIndex(modelFuncionario.getBanco_nome());
            this.cbCodBanco.setSelectedIndex(modelFuncionario.getBanco_nome());
            this.tfConta.setText(modelFuncionario.getBanco_conta());
            this.tfOperacao.setText(modelFuncionario.getBanco_op());
            this.tfAgencia.setText(modelFuncionario.getBanco_agencia());
            this.tfBancoObs.setText(modelFuncionario.getBanco_obs());
            this.cbTipoConta.setSelectedItem((String) modelFuncionario.getBanco_tipo());
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Código inválido ou nenhum registro selecionado", "Aviso", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }

    private static final int[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
    private static final int[] pesoCNPJ = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

    private static int calcularDigito(String str, int[] peso) {
        int soma = 0;
        for (int indice = str.length() - 1, digito; indice >= 0; indice--) {
            digito = Integer.parseInt(str.substring(indice, indice + 1));
            soma += digito * peso[peso.length - str.length() + indice];
        }
        soma = 11 - soma % 11;
        return soma > 9 ? 0 : soma;
    }

    public static boolean isValidCPF(String cpf) {
        if ((cpf == null) || (cpf.length() != 11)) {
            return false;
        }

        Integer digito1 = calcularDigito(cpf.substring(0, 9), pesoCPF);
        Integer digito2 = calcularDigito(cpf.substring(0, 9) + digito1, pesoCPF);
        return cpf.equals(cpf.substring(0, 9) + digito1.toString() + digito2.toString());
    }

    public static boolean isValidCNPJ(String cnpj) {
        if ((cnpj == null) || (cnpj.length() != 14)) {
            return false;
        }

        Integer digito1 = calcularDigito(cnpj.substring(0, 12), pesoCNPJ);
        Integer digito2 = calcularDigito(cnpj.substring(0, 12) + digito1, pesoCNPJ);
        return cnpj.equals(cnpj.substring(0, 12) + digito1.toString() + digito2.toString());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ContasFerias;
    private javax.swing.JPanel DadosFuncionario;
    private javax.swing.JButton btAlterarBanco;
    private javax.swing.JButton btAlterarTerceiro;
    private javax.swing.JButton btAlterarTerceiro1;
    private javax.swing.JButton btAtualizarBanco;
    private javax.swing.JButton btAtualizarFerias;
    private javax.swing.JButton btAtualizarTerceiro;
    private javax.swing.JButton btCancelaNOBLOCK;
    private javax.swing.JButton btCancelarContato;
    private javax.swing.JButton btCancelarContato1;
    private javax.swing.JButton btCidadeAdd;
    private javax.swing.JButton btExcluirBanco;
    private javax.swing.JButton btExcluirFerias;
    private javax.swing.JButton btExportarFerias;
    private javax.swing.JButton btSalvarBanco;
    private javax.swing.JButton btSalvarFerias;
    private javax.swing.JButton btSalvarNOBLOCK;
    private javax.swing.JComboBox<String> cbBanco;
    public static javax.swing.JComboBox cbCidade;
    private javax.swing.JComboBox<String> cbCodBanco;
    private javax.swing.JComboBox<String> cbOcupacao;
    private javax.swing.JComboBox<String> cbTipoConta;
    public static javax.swing.JComboBox cbUf;
    private javax.swing.JComboBox cbVinculo;
    private com.toedter.calendar.JDateChooser jDUltimasFerias;
    private com.toedter.calendar.JDateChooser jDVencimento;
    private com.toedter.calendar.JDateChooser jFAdmissao;
    private com.toedter.calendar.JDateChooser jFNascimento;
    private javax.swing.JLabel jFUltimasFerias;
    private javax.swing.JLabel jFVencerFerias;
    private javax.swing.JLabel jLBairro;
    private javax.swing.JLabel jLBanco;
    private javax.swing.JLabel jLBanco2;
    private javax.swing.JLabel jLBanco3;
    private javax.swing.JLabel jLBancoAgencia;
    private javax.swing.JLabel jLBancoConta;
    private javax.swing.JLabel jLBancoConta1;
    private javax.swing.JLabel jLBancoObs;
    private javax.swing.JLabel jLBancoObs1;
    private javax.swing.JLabel jLBancoOp;
    private javax.swing.JLabel jLCelular;
    private javax.swing.JLabel jLCep;
    private javax.swing.JLabel jLCidade;
    private javax.swing.JLabel jLCnh;
    private javax.swing.JLabel jLCodigo;
    private javax.swing.JLabel jLComplemento;
    private javax.swing.JLabel jLCpf_Cnpj;
    private javax.swing.JLabel jLEmail;
    private javax.swing.JLabel jLEndereco;
    private javax.swing.JLabel jLEstado;
    private javax.swing.JLabel jLFiliacao;
    private javax.swing.JLabel jLNascimento;
    private javax.swing.JLabel jLNome;
    private javax.swing.JLabel jLNumero;
    private javax.swing.JLabel jLObs;
    private javax.swing.JLabel jLObsFuncionario;
    private javax.swing.JLabel jLObsOcupacao;
    private javax.swing.JLabel jLOcupacao;
    private javax.swing.JLabel jLRg;
    private javax.swing.JLabel jLTelefone;
    private javax.swing.JLabel jLTipoConta;
    private javax.swing.JLabel jLTipoConta1;
    private javax.swing.JLabel jLUltimasFerias;
    private javax.swing.JLabel jLVencerFerias;
    private javax.swing.JLabel jLVinculo;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelSalvarCancelar;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedCadFuncionarios;
    private javax.swing.JTable jTableBancos;
    private javax.swing.JTable jTableFerias;
    private javax.swing.JFormattedTextField jfCelular;
    private javax.swing.JFormattedTextField jfCep;
    private javax.swing.JFormattedTextField jfCpf;
    private javax.swing.JFormattedTextField jfTelefone;
    private keeptoo.KButton kButtonContasFerias;
    private keeptoo.KButton kButtonInformacoes;
    private keeptoo.KGradientPanel kGContaBancaria;
    private keeptoo.KGradientPanel kGContaBancaria1;
    private keeptoo.KGradientPanel kGDadosFuncionais;
    private keeptoo.KGradientPanel kGInfoContato;
    private keeptoo.KGradientPanel kGrDadosPessoais;
    private keeptoo.KGradientPanel kGradientPanel14;
    private keeptoo.KGradientPanel kGradientPanel22;
    private javax.swing.JTextField tfAgencia;
    public static javax.swing.JTextField tfBairro;
    private javax.swing.JTextField tfBancoObs;
    public static javax.swing.JTextField tfCnh;
    private javax.swing.JTextField tfCodBanco;
    private javax.swing.JTextField tfCodFerias;
    private javax.swing.JTextField tfCodigo;
    public static javax.swing.JTextField tfComplemento;
    private javax.swing.JTextField tfConta;
    public static javax.swing.JTextField tfEmail;
    public static javax.swing.JTextField tfEndereco;
    public static javax.swing.JTextField tfFiliacao;
    private javax.swing.JLabel tfMostraAdmissao;
    public static javax.swing.JTextField tfNome;
    public static javax.swing.JTextField tfNumero;
    public static javax.swing.JTextField tfObsContatoFuncionario;
    private javax.swing.JTextField tfObsFerias;
    public static javax.swing.JTextField tfObservacao;
    public static javax.swing.JTextField tfObservacaoOcupacao;
    private javax.swing.JTextField tfOperacao;
    public static javax.swing.JTextField tfRg;
    // End of variables declaration//GEN-END:variables

    private void limpaCampos() {
        cbCidade.setSelectedIndex(0);
        cbOcupacao.setSelectedIndex(0);
        cbUf.setSelectedIndex(0);
        cbVinculo.setSelectedIndex(0);
        jfCelular.setText("");
        jfCep.setText("");
        jfCpf.setText("");
        jfTelefone.setText("");
        tfBairro.setText("");
        tfCnh.setText("");
        tfCodigo.setText("");
        tfComplemento.setText("");
        tfEmail.setText("");
        tfEndereco.setText("");
        tfFiliacao.setText("");
        tfNome.setText("");
        tfNumero.setText("");
        tfObsContatoFuncionario.setText("");
        tfObservacao.setText("");
        tfObservacaoOcupacao.setText("");
        tfRg.setText("");
        cbBanco.setSelectedIndex(0);
        cbTipoConta.setSelectedIndex(0);
        tfConta.setText("");
        tfAgencia.setText("");
        tfOperacao.setText("");
        tfBancoObs.setText("");
    }

    ConexaoBanco cc = new ConexaoBanco();
    Connection cn = cc.conectar();

    private void SetIcone() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("ictrol.png")));
    }

}
