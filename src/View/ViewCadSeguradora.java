/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ControllerCidade;
import Controller.ControllerSeguradora;
import Controller.ControllerEstado;
import Controller.ControllerSeguradoraCidadeEstado;
import Model.ModelCidade;
import Model.ModelEstado;
import Model.ModelSeguradora;
import Model.ModelSeguradoraCidadeEstado;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import static View.ViewCidade.cidade;
import static View.ViewSeguradora.seguradora;
import conexao.ConexaoBanco;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import util.Moeda;

/**
 *
 * @author studiomicroweb
 */
public final class ViewCadSeguradora extends javax.swing.JFrame {

    String tipoCadastro = "null";
    ControllerCidade controllerCidade = new ControllerCidade();
    ControllerEstado controllerEstado = new ControllerEstado();
    ControllerSeguradoraCidadeEstado controllerSeguradoraCidadeEstado = new ControllerSeguradoraCidadeEstado();
    ModelSeguradora modelSeguradora = new ModelSeguradora();
    ArrayList<ModelCidade> listaModelCidades = new ArrayList<>();
    ArrayList<ModelEstado> listaModelEstados = new ArrayList<>();
    ArrayList<ModelSeguradoraCidadeEstado> listaModelSeguradoraCidadeEstados = new ArrayList<>();
    ControllerSeguradora controllerSeguradora = new ControllerSeguradora();

    /**
     * Creates new form ViewCadSeguradora
     */
    public ViewCadSeguradora() {
        initComponents();
        SetIcone();
        this.listarEstados();
        this.listarCidades();
        setLocationRelativeTo(null);
        if (ViewSeguradora.seguradora.tipoCadastro != null && ViewSeguradora.seguradora.tipoCadastro.equals("alteracao")) {
            this.recuperarSeguradora();
        }
        this.carrerContatosSeguradora();
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
                new ViewCadSeguradora().setVisible(true);
            }
        });
    }

    void limparCamposCONTATOS() {
        tfNomeContato.setText(null);
        tfTelefoneContato.setText(null);
        EmailContato.setText(null);
        tfObsContato.setText(null);
        tfCodContato.setText(null);

    }

    void carrerContatosSeguradora() {
        if ((int) jTableContatos.getRowCount() > 0) {
            DefaultTableModel modelo = (DefaultTableModel) jTableContatos.getModel();
            modelo.setNumRows(0);
        }
        try {
            DefaultTableModel model = (DefaultTableModel) jTableContatos.getModel();
            int codigo = 0;
            if (!"Novo +".equals(tfCodigo.getText())) {
                codigo = (Integer.parseInt(tfCodigo.getText()));
            }

            String consultar = "SELECT * FROM contatos_seguradora WHERE cod_seguradora='" + codigo + "'";
            String[] datos = new String[5];
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(consultar);
            while (rs.next()) {
                datos[0] = rs.getString("codigo");
                datos[1] = rs.getString("cttseg_nome");
                datos[2] = rs.getString("cttseg_telefone");
                datos[3] = rs.getString("cttseg_email");
                datos[4] = rs.getString("cttseg_obs");

                model.addRow(datos);
            }
            jTableContatos.setModel(model);

        } catch (SQLException ex) {
            Logger.getLogger(ViewCadSeguradora.class.getName()).log(Level.SEVERE, null, ex);
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

        tfCodContato = new javax.swing.JTextField();
        jPanelPrincipal = new javax.swing.JPanel();
        kGradientPanel3 = new keeptoo.KGradientPanel();
        kGradientPanel22 = new keeptoo.KGradientPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        kGradientPanel1 = new keeptoo.KGradientPanel();
        jlCodigo = new javax.swing.JLabel();
        jlNomeRazao = new javax.swing.JLabel();
        tfNomeRazao = new javax.swing.JTextField();
        jlCnpj = new javax.swing.JLabel();
        jlEndereco = new javax.swing.JLabel();
        tfEndereco = new javax.swing.JTextField();
        jlBairro = new javax.swing.JLabel();
        tfBairro = new javax.swing.JTextField();
        jlNumero = new javax.swing.JLabel();
        tfNumero = new javax.swing.JTextField();
        jlComplemento = new javax.swing.JLabel();
        tfComplemento = new javax.swing.JTextField();
        jlCep = new javax.swing.JLabel();
        jlEstado = new javax.swing.JLabel();
        jlCidade = new javax.swing.JLabel();
        cbNegativa = new javax.swing.JComboBox();
        cbCidade = new javax.swing.JComboBox();
        btCidade = new javax.swing.JButton();
        jlValorhonario = new javax.swing.JLabel();
        jlValorkm = new javax.swing.JLabel();
        jlEmail = new javax.swing.JLabel();
        tfEmail = new javax.swing.JTextField();
        jlObs = new javax.swing.JLabel();
        tfObservacao = new javax.swing.JTextField();
        jlValorneg = new javax.swing.JLabel();
        jlIssretido = new javax.swing.JLabel();
        cbUf = new javax.swing.JComboBox();
        jfCnpj = new javax.swing.JFormattedTextField();
        jfCep = new javax.swing.JFormattedTextField();
        jfValorHonorarios = new javax.swing.JFormattedTextField();
        jfValorKm = new javax.swing.JFormattedTextField();
        tfCodigo = new javax.swing.JTextField();
        jfCelular = new javax.swing.JFormattedTextField();
        jfTelefone = new javax.swing.JFormattedTextField();
        tfCelular = new javax.swing.JLabel();
        tfTelefone = new javax.swing.JLabel();
        cbISSretido = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jFMeioHonorario = new javax.swing.JFormattedTextField();
        kGradientPanel2 = new keeptoo.KGradientPanel();
        kGContato = new keeptoo.KGradientPanel();
        jLNomeContato = new javax.swing.JLabel();
        jlTelefoneContato = new javax.swing.JLabel();
        jLObsContato = new javax.swing.JLabel();
        jLEmailContato = new javax.swing.JLabel();
        tfNomeContato = new javax.swing.JTextField();
        tfObsContato = new javax.swing.JTextField();
        tfTelefoneContato = new javax.swing.JTextField();
        EmailContato = new javax.swing.JTextField();
        btSalvarContato = new javax.swing.JButton();
        btExcluirContato = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTableContatos = new javax.swing.JTable();
        btAtualizarTerceiro = new javax.swing.JButton();
        btAlterarTerceiro = new javax.swing.JButton();
        btCancelarContato = new javax.swing.JButton();
        jSalvar = new javax.swing.JPanel();
        btCancelar = new javax.swing.JButton();
        btSalvar = new javax.swing.JButton();

        tfCodContato.setEditable(false);
        tfCodContato.setEnabled(false);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Seguradoras");
        setBackground(new java.awt.Color(102, 102, 102));
        setMinimumSize(new java.awt.Dimension(970, 720));
        setPreferredSize(new java.awt.Dimension(970, 720));
        setResizable(false);

        jPanelPrincipal.setBackground(new java.awt.Color(255, 255, 255));
        jPanelPrincipal.setMinimumSize(new java.awt.Dimension(960, 600));
        jPanelPrincipal.setPreferredSize(new java.awt.Dimension(960, 600));

        kGradientPanel3.setkBorderRadius(0);
        kGradientPanel3.setkEndColor(new java.awt.Color(146, 171, 197));
        kGradientPanel3.setkGradientFocus(0);
        kGradientPanel3.setkStartColor(new java.awt.Color(146, 171, 197));
        kGradientPanel3.setPreferredSize(new java.awt.Dimension(960, 50));

        kGradientPanel22.setkBorderRadius(0);
        kGradientPanel22.setkEndColor(new java.awt.Color(146, 171, 197));
        kGradientPanel22.setkGradientFocus(125);
        kGradientPanel22.setkStartColor(new java.awt.Color(255, 255, 255));

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 51, 102));
        jLabel27.setText("SEGURADORAS");

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(0, 51, 102));
        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/24x24/seg24x24.png"))); // NOI18N
        jLabel28.setPreferredSize(new java.awt.Dimension(60, 60));

        org.jdesktop.layout.GroupLayout kGradientPanel22Layout = new org.jdesktop.layout.GroupLayout(kGradientPanel22);
        kGradientPanel22.setLayout(kGradientPanel22Layout);
        kGradientPanel22Layout.setHorizontalGroup(
            kGradientPanel22Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, kGradientPanel22Layout.createSequentialGroup()
                .add(17, 17, 17)
                .add(jLabel28, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 26, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel27, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 460, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
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

        org.jdesktop.layout.GroupLayout kGradientPanel3Layout = new org.jdesktop.layout.GroupLayout(kGradientPanel3);
        kGradientPanel3.setLayout(kGradientPanel3Layout);
        kGradientPanel3Layout.setHorizontalGroup(
            kGradientPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(kGradientPanel3Layout.createSequentialGroup()
                .add(kGradientPanel22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 553, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(0, 407, Short.MAX_VALUE))
        );
        kGradientPanel3Layout.setVerticalGroup(
            kGradientPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(kGradientPanel3Layout.createSequentialGroup()
                .add(kGradientPanel22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(0, 0, Short.MAX_VALUE))
        );

        jPanelPrincipal.add(kGradientPanel3);

        kGradientPanel1.setkBorderRadius(0);
        kGradientPanel1.setkEndColor(new java.awt.Color(234, 239, 243));
        kGradientPanel1.setkGradientFocus(0);
        kGradientPanel1.setkStartColor(new java.awt.Color(255, 255, 255));
        kGradientPanel1.setMinimumSize(new java.awt.Dimension(940, 320));
        kGradientPanel1.setPreferredSize(new java.awt.Dimension(950, 340));
        kGradientPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlCodigo.setText("Código:");
        kGradientPanel1.add(jlCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 70, -1));

        jlNomeRazao.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jlNomeRazao.setText("Nome Razão:");
        kGradientPanel1.add(jlNomeRazao, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 100, -1));

        tfNomeRazao.setMinimumSize(new java.awt.Dimension(630, 25));
        tfNomeRazao.setPreferredSize(new java.awt.Dimension(630, 25));
        tfNomeRazao.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfNomeRazaoFocusLost(evt);
            }
        });
        kGradientPanel1.add(tfNomeRazao, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, -1, 30));

        jlCnpj.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jlCnpj.setText("CNPJ:");
        kGradientPanel1.add(jlCnpj, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 20, -1, -1));

        jlEndereco.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jlEndereco.setText("Endereço:");
        kGradientPanel1.add(jlEndereco, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 150, -1));

        tfEndereco.setMinimumSize(new java.awt.Dimension(440, 20));
        tfEndereco.setPreferredSize(new java.awt.Dimension(440, 25));
        tfEndereco.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfEnderecoFocusLost(evt);
            }
        });
        kGradientPanel1.add(tfEndereco, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, 30));

        jlBairro.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jlBairro.setText("Bairro:");
        kGradientPanel1.add(jlBairro, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 80, 70, -1));

        tfBairro.setPreferredSize(new java.awt.Dimension(167, 25));
        tfBairro.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfBairroFocusLost(evt);
            }
        });
        kGradientPanel1.add(tfBairro, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 100, -1, 30));

        jlNumero.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jlNumero.setText("Número:");
        kGradientPanel1.add(jlNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 80, 60, -1));

        tfNumero.setPreferredSize(new java.awt.Dimension(60, 25));
        kGradientPanel1.add(tfNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 100, -1, 30));

        jlComplemento.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jlComplemento.setText("Complemento:");
        kGradientPanel1.add(jlComplemento, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 80, 120, -1));

        tfComplemento.setPreferredSize(new java.awt.Dimension(130, 25));
        tfComplemento.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfComplementoFocusLost(evt);
            }
        });
        kGradientPanel1.add(tfComplemento, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 100, -1, 30));

        jlCep.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jlCep.setText("CEP:");
        kGradientPanel1.add(jlCep, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 140, 40, -1));

        jlEstado.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jlEstado.setText("Estado");
        kGradientPanel1.add(jlEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 140, -1, -1));

        jlCidade.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jlCidade.setText("Cidade:");
        kGradientPanel1.add(jlCidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 140, -1, -1));

        cbNegativa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sim", "Não" }));
        cbNegativa.setSelectedIndex(-1);
        cbNegativa.setPreferredSize(new java.awt.Dimension(111, 25));
        cbNegativa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbNegativaActionPerformed(evt);
            }
        });
        kGradientPanel1.add(cbNegativa, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 230, 100, 30));

        cbCidade.setPreferredSize(new java.awt.Dimension(144, 25));
        cbCidade.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cbCidadeFocusGained(evt);
            }
        });
        cbCidade.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                cbCidadePopupMenuWillBecomeVisible(evt);
            }
        });
        kGradientPanel1.add(cbCidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 160, -1, 30));

        btCidade.setBackground(new java.awt.Color(0, 153, 0));
        btCidade.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btCidade.setForeground(new java.awt.Color(255, 255, 255));
        btCidade.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Create.png"))); // NOI18N
        btCidade.setText("Cidade");
        btCidade.setPreferredSize(new java.awt.Dimension(120, 30));
        btCidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCidadeActionPerformed(evt);
            }
        });
        kGradientPanel1.add(btCidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 160, -1, -1));

        jlValorhonario.setText("Honorário");
        kGradientPanel1.add(jlValorhonario, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 110, -1));

        jlValorkm.setText("Pgto. por Km");
        kGradientPanel1.add(jlValorkm, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 210, 90, -1));

        jlEmail.setText("E-mail:");
        kGradientPanel1.add(jlEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 70, -1));

        tfEmail.setPreferredSize(new java.awt.Dimension(170, 25));
        tfEmail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfEmailFocusLost(evt);
            }
        });
        kGradientPanel1.add(tfEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 300, 30));

        jlObs.setText("Observação:");
        kGradientPanel1.add(jlObs, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, -1, -1));

        tfObservacao.setPreferredSize(new java.awt.Dimension(510, 25));
        tfObservacao.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfObservacaoFocusLost(evt);
            }
        });
        kGradientPanel1.add(tfObservacao, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, 860, 30));

        jlValorneg.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jlValorneg.setText("Valor Negativa");
        kGradientPanel1.add(jlValorneg, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 210, 110, -1));

        jlIssretido.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jlIssretido.setText("ISS Retido");
        kGradientPanel1.add(jlIssretido, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 210, 70, -1));

        cbUf.setPreferredSize(new java.awt.Dimension(111, 25));
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
        kGradientPanel1.add(cbUf, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 160, 90, 30));

        try {
            jfCnpj.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###/####-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jfCnpj.setMinimumSize(new java.awt.Dimension(130, 25));
        jfCnpj.setPreferredSize(new java.awt.Dimension(130, 25));
        jfCnpj.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jfCnpjFocusLost(evt);
            }
        });
        kGradientPanel1.add(jfCnpj, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 40, -1, 30));

        try {
            jfCep.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jfCep.setMinimumSize(new java.awt.Dimension(6, 25));
        jfCep.setName(""); // NOI18N
        jfCep.setPreferredSize(new java.awt.Dimension(109, 25));
        jfCep.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jfCepFocusLost(evt);
            }
        });
        kGradientPanel1.add(jfCep, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 160, 130, 30));

        jfValorHonorarios.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getCurrencyInstance())));
        jfValorHonorarios.setText("R$ 0,00");
        jfValorHonorarios.setMinimumSize(new java.awt.Dimension(100, 30));
        jfValorHonorarios.setPreferredSize(new java.awt.Dimension(100, 30));
        jfValorHonorarios.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jfValorHonorariosFocusLost(evt);
            }
        });
        kGradientPanel1.add(jfValorHonorarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, -1, -1));

        jfValorKm.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getCurrencyInstance())));
        jfValorKm.setText("R$ 0,00");
        jfValorKm.setMinimumSize(new java.awt.Dimension(100, 30));
        jfValorKm.setPreferredSize(new java.awt.Dimension(100, 30));
        jfValorKm.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jfValorKmFocusLost(evt);
            }
        });
        kGradientPanel1.add(jfValorKm, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 230, -1, -1));

        tfCodigo.setBackground(new java.awt.Color(227, 236, 245));
        tfCodigo.setText("Novo +");
        tfCodigo.setEnabled(false);
        tfCodigo.setMinimumSize(new java.awt.Dimension(60, 20));
        tfCodigo.setPreferredSize(new java.awt.Dimension(60, 25));
        tfCodigo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfCodigoFocusLost(evt);
            }
        });
        kGradientPanel1.add(tfCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, -1, 30));

        try {
            jfCelular.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jfCelular.setMinimumSize(new java.awt.Dimension(130, 25));
        jfCelular.setPreferredSize(new java.awt.Dimension(130, 25));
        kGradientPanel1.add(jfCelular, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 230, 140, 30));

        try {
            jfTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jfTelefone.setPreferredSize(new java.awt.Dimension(6, 20));
        kGradientPanel1.add(jfTelefone, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 230, 130, 30));

        tfCelular.setText("Celular");
        kGradientPanel1.add(tfCelular, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 210, 70, -1));

        tfTelefone.setText("Telefone");
        kGradientPanel1.add(tfTelefone, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 210, 110, -1));

        cbISSretido.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sim", "Não" }));
        cbISSretido.setSelectedIndex(-1);
        cbISSretido.setPreferredSize(new java.awt.Dimension(111, 25));
        cbISSretido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbISSretidoActionPerformed(evt);
            }
        });
        kGradientPanel1.add(cbISSretido, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 230, 100, 30));

        jLabel1.setText("1/2 Honorário");
        kGradientPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 210, 90, -1));

        jFMeioHonorario.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getCurrencyInstance())));
        jFMeioHonorario.setText("R$ 0,00");
        jFMeioHonorario.setPreferredSize(new java.awt.Dimension(100, 30));
        jFMeioHonorario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jFMeioHonorarioFocusLost(evt);
            }
        });
        kGradientPanel1.add(jFMeioHonorario, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 230, -1, -1));

        kGradientPanel2.setkBorderRadius(0);
        kGradientPanel2.setkEndColor(new java.awt.Color(234, 239, 243));
        kGradientPanel2.setkGradientFocus(0);
        kGradientPanel2.setkStartColor(new java.awt.Color(255, 255, 255));

        org.jdesktop.layout.GroupLayout kGradientPanel2Layout = new org.jdesktop.layout.GroupLayout(kGradientPanel2);
        kGradientPanel2.setLayout(kGradientPanel2Layout);
        kGradientPanel2Layout.setHorizontalGroup(
            kGradientPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 950, Short.MAX_VALUE)
        );
        kGradientPanel2Layout.setVerticalGroup(
            kGradientPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 140, Short.MAX_VALUE)
        );

        kGradientPanel1.add(kGradientPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 950, 140));

        jPanelPrincipal.add(kGradientPanel1);

        kGContato.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Contatos da Seguradora", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(51, 51, 51))); // NOI18N
        kGContato.setkBorderRadius(0);
        kGContato.setkEndColor(new java.awt.Color(234, 239, 243));
        kGContato.setkGradientFocus(0);
        kGContato.setkStartColor(new java.awt.Color(255, 255, 255));
        kGContato.setMinimumSize(new java.awt.Dimension(940, 240));
        kGContato.setName(""); // NOI18N
        kGContato.setPreferredSize(new java.awt.Dimension(950, 220));
        kGContato.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLNomeContato.setText("Nome do Contato");
        kGContato.add(jLNomeContato, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 190, -1));

        jlTelefoneContato.setText("Telefone");
        kGContato.add(jlTelefoneContato, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 30, 120, -1));

        jLObsContato.setText("Observações:");
        kGContato.add(jLObsContato, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 30, 120, -1));

        jLEmailContato.setText("E-mail");
        kGContato.add(jLEmailContato, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 30, 120, -1));

        tfNomeContato.setPreferredSize(new java.awt.Dimension(200, 28));
        tfNomeContato.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfNomeContatoFocusLost(evt);
            }
        });
        kGContato.add(tfNomeContato, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, -1, -1));

        tfObsContato.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfObsContatoFocusLost(evt);
            }
        });
        kGContato.add(tfObsContato, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 50, 270, 28));

        tfTelefoneContato.setPreferredSize(new java.awt.Dimension(150, 28));
        tfTelefoneContato.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfTelefoneContatoFocusLost(evt);
            }
        });
        kGContato.add(tfTelefoneContato, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 50, 150, 28));

        EmailContato.setPreferredSize(new java.awt.Dimension(200, 28));
        EmailContato.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EmailContatoFocusLost(evt);
            }
        });
        kGContato.add(EmailContato, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 50, 200, 28));

        btSalvarContato.setBackground(new java.awt.Color(0, 153, 0));
        btSalvarContato.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btSalvarContato.setForeground(new java.awt.Color(255, 255, 255));
        btSalvarContato.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/save.png"))); // NOI18N
        btSalvarContato.setMaximumSize(new java.awt.Dimension(60, 40));
        btSalvarContato.setMinimumSize(new java.awt.Dimension(60, 40));
        btSalvarContato.setPreferredSize(new java.awt.Dimension(60, 40));
        btSalvarContato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSalvarContatoActionPerformed(evt);
            }
        });
        kGContato.add(btSalvarContato, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));

        btExcluirContato.setBackground(new java.awt.Color(255, 0, 0));
        btExcluirContato.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btExcluirContato.setForeground(new java.awt.Color(255, 255, 255));
        btExcluirContato.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/delete.png"))); // NOI18N
        btExcluirContato.setMaximumSize(new java.awt.Dimension(60, 40));
        btExcluirContato.setMinimumSize(new java.awt.Dimension(60, 40));
        btExcluirContato.setPreferredSize(new java.awt.Dimension(60, 40));
        btExcluirContato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExcluirContatoActionPerformed(evt);
            }
        });
        kGContato.add(btExcluirContato, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));

        jScrollPane6.setBackground(new java.awt.Color(247, 247, 247));
        jScrollPane6.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane6.setPreferredSize(new java.awt.Dimension(1170, 170));

        jTableContatos.setAutoCreateRowSorter(true);
        jTableContatos.setBackground(new java.awt.Color(247, 247, 247));
        jTableContatos.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jTableContatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cod.", "Nome do Contato", "Telefone", "E-mail", "Observações"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableContatos.setGridColor(new java.awt.Color(247, 247, 247));
        jTableContatos.setMinimumSize(new java.awt.Dimension(610, 0));
        jTableContatos.setRowHeight(20);
        jTableContatos.setSelectionBackground(new java.awt.Color(0, 153, 0));
        jTableContatos.getTableHeader().setReorderingAllowed(false);
        jScrollPane6.setViewportView(jTableContatos);
        if (jTableContatos.getColumnModel().getColumnCount() > 0) {
            jTableContatos.getColumnModel().getColumn(0).setMinWidth(0);
            jTableContatos.getColumnModel().getColumn(0).setPreferredWidth(0);
            jTableContatos.getColumnModel().getColumn(0).setMaxWidth(0);
            jTableContatos.getColumnModel().getColumn(2).setMinWidth(150);
            jTableContatos.getColumnModel().getColumn(2).setPreferredWidth(150);
            jTableContatos.getColumnModel().getColumn(2).setMaxWidth(150);
        }

        kGContato.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, 730, 120));

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
        kGContato.add(btAtualizarTerceiro, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 140, -1, -1));

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
        kGContato.add(btAlterarTerceiro, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 90, -1, -1));

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
        kGContato.add(btCancelarContato, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 90, -1, -1));

        jPanelPrincipal.add(kGContato);

        jSalvar.setBackground(new java.awt.Color(255, 255, 255));
        jSalvar.setMinimumSize(new java.awt.Dimension(960, 50));
        jSalvar.setPreferredSize(new java.awt.Dimension(960, 50));

        btCancelar.setBackground(new java.awt.Color(195, 3, 18));
        btCancelar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Close.png"))); // NOI18N
        btCancelar.setText("CANCELAR");
        btCancelar.setMaximumSize(new java.awt.Dimension(120, 25));
        btCancelar.setMinimumSize(new java.awt.Dimension(120, 25));
        btCancelar.setPreferredSize(new java.awt.Dimension(130, 35));
        btCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarActionPerformed(evt);
            }
        });
        jSalvar.add(btCancelar);

        btSalvar.setBackground(new java.awt.Color(0, 153, 0));
        btSalvar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btSalvar.setForeground(new java.awt.Color(255, 255, 255));
        btSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/save.png"))); // NOI18N
        btSalvar.setText("SALVAR");
        btSalvar.setMaximumSize(new java.awt.Dimension(120, 25));
        btSalvar.setMinimumSize(new java.awt.Dimension(120, 25));
        btSalvar.setPreferredSize(new java.awt.Dimension(150, 40));
        btSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSalvarActionPerformed(evt);
            }
        });
        jSalvar.add(btSalvar);

        jPanelPrincipal.add(jSalvar);

        getContentPane().add(jPanelPrincipal, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tfNomeRazaoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfNomeRazaoFocusLost
        // TODO add your handling code here:
        this.tfNomeRazao.setText(tfNomeRazao.getText().toUpperCase());
    }//GEN-LAST:event_tfNomeRazaoFocusLost

    private void tfEnderecoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfEnderecoFocusLost
        // TODO add your handling code here:
        this.tfEndereco.setText(tfEndereco.getText().toUpperCase());
    }//GEN-LAST:event_tfEnderecoFocusLost

    private void tfBairroFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfBairroFocusLost
        // TODO add your handling code here:
        this.tfBairro.setText(tfBairro.getText().toUpperCase());
    }//GEN-LAST:event_tfBairroFocusLost

    private void tfComplementoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfComplementoFocusLost
        // TODO add your handling code here:
        this.tfComplemento.setText(tfComplemento.getText().toUpperCase());
    }//GEN-LAST:event_tfComplementoFocusLost

    private void tfEmailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfEmailFocusLost
        // TODO add your handling code here:
        this.tfEmail.setText(tfEmail.getText().toUpperCase());
    }//GEN-LAST:event_tfEmailFocusLost

    private void tfObservacaoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfObservacaoFocusLost
        // TODO add your handling code here:
        this.tfObservacao.setText(tfObservacao.getText().toUpperCase());
    }//GEN-LAST:event_tfObservacaoFocusLost

    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarActionPerformed
        // TODO add your handling code here:
        if (ViewSeguradora.seguradora.tipoCadastro != null && ViewSeguradora.seguradora.tipoCadastro.equals("alteracao")) {
            alteraSeguradora();
        } else {
            salvarSeguradora();
            ViewSeguradora.seguradora.carregarSeguradoras();
        }
    }//GEN-LAST:event_btSalvarActionPerformed

    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarActionPerformed
        // TODO add your handling code here:
        limpaCampos();
        dispose();
        ViewSeguradora.seguradora = new ViewSeguradora();
        ViewSeguradora.seguradora.requestFocus();
    }//GEN-LAST:event_btCancelarActionPerformed

    private void btCidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCidadeActionPerformed
        // TODO add your handling code here:
        cidade.setVisible(true);
    }//GEN-LAST:event_btCidadeActionPerformed

    private void cbUfPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbUfPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
        if (this.cbUf.isPopupVisible()) {
            listarCidades();
        }
    }//GEN-LAST:event_cbUfPopupMenuWillBecomeInvisible

    private void cbNegativaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbNegativaActionPerformed
        // NEGATIVA
        this.cbNegativa.setSelectedItem(cbNegativa.getSelectedItem().toString());
    }//GEN-LAST:event_cbNegativaActionPerformed

    private void jfCnpjFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jfCnpjFocusLost
        // TODO add your handling code here:
        jfCnpj.setText(jfCnpj.getText());
        //Desativado Temporariamente
        /*if (isValidCNPJ(jfCnpj.getText())) {
         } else {
         JOptionPane.showMessageDialog(this, " CNPJ Inválido", "ATENÇÂO", JOptionPane.WARNING_MESSAGE);
         }*/
    }//GEN-LAST:event_jfCnpjFocusLost

    private void jfCepFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jfCepFocusLost
        //CEP
        this.jfCep.setText(jfCep.getText().toUpperCase());
    }//GEN-LAST:event_jfCepFocusLost

    private void jfValorHonorariosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jfValorHonorariosFocusLost
        //Valor Honorarios fixo
        try {
            this.jfValorHonorarios.setText(new Moeda().valorStringTODoubleAtt(jfValorHonorarios.getText()));
        } catch (NumberFormatException e) {
            jfValorHonorarios.setText("R$ 0,00");
        }
    }//GEN-LAST:event_jfValorHonorariosFocusLost

    private void jfValorKmFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jfValorKmFocusLost
        // Valor pago por KM
        try {
            this.jfValorKm.setText(new Moeda().valorStringTODoubleAtt(jfValorKm.getText()));
        } catch (NumberFormatException e) {
            jfValorKm.setText("R$ 0,00");
        }
    }//GEN-LAST:event_jfValorKmFocusLost

    private void tfCodigoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfCodigoFocusLost
        // Apenas tras o codigo não adiciona
        this.tfCodigo.getText().toUpperCase();
    }//GEN-LAST:event_tfCodigoFocusLost

    private void cbISSretidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbISSretidoActionPerformed
        // TODO add your handling code here:
        this.cbISSretido.setSelectedItem(cbISSretido.getSelectedItem().toString());
    }//GEN-LAST:event_cbISSretidoActionPerformed

    private void jFMeioHonorarioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFMeioHonorarioFocusLost
        // MEIO HONORARIO
        try {
            this.jFMeioHonorario.setText(new Moeda().valorStringTODoubleAtt(jFMeioHonorario.getText()));
        } catch (NumberFormatException e) {
            jFMeioHonorario.setText("R$ 0,00");
        }
    }//GEN-LAST:event_jFMeioHonorarioFocusLost

    private void btExcluirContatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirContatoActionPerformed
        // TODO add your handling code here:
        int fila = jTableContatos.getSelectedRow();
        if (fila > -1) {
            String cod = jTableContatos.getValueAt(fila, 0).toString();
            String sqlElim = "DELETE FROM contatos_seguradora WHERE codigo='" + cod + "'";
            try {
                PreparedStatement pst = cn.prepareStatement(sqlElim);
                int n = pst.executeUpdate();
                if (n > 0) {
                    JOptionPane.showMessageDialog(null, "Os dados foram atualizados com sucesso!");
                    carrerContatosSeguradora();

                } else {
                    JOptionPane.showMessageDialog(null, "Ocorreu um erro ao atualizar os dados");
                }
            } catch (SQLException ex) {
                Logger.getLogger(ViewCadSeguradora.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_btExcluirContatoActionPerformed

    private void btCancelarContatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarContatoActionPerformed
        // TODO add your handling code here:
        carrerContatosSeguradora();
        limparCamposCONTATOS();
        btSalvarContato.setEnabled(true);
        btAtualizarTerceiro.setEnabled(false);
        btAlterarTerceiro.setVisible(true);
        btAlterarTerceiro.setEnabled(true);
        btCancelarContato.setVisible(false);
        btCancelarContato.setEnabled(true);
    }//GEN-LAST:event_btCancelarContatoActionPerformed

    private void btSalvarContatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarContatoActionPerformed
        // TODO add your handling code here:
        String insertar = "INSERT INTO contatos_seguradora(cod_seguradora,cttseg_nome,cttseg_telefone,cttseg_email,cttseg_obs) VALUES (?,?,?,?,?)";
        try {
            // variável para obter o número de bytes de uma imagem.
            PreparedStatement pst = cn.prepareStatement(insertar);
            pst.setString(1, tfCodigo.getText());
            pst.setString(2, tfNomeContato.getText());
            pst.setString(3, tfTelefoneContato.getText());
            pst.setString(4, EmailContato.getText());
            pst.setString(5, tfObsContato.getText());

            int i = pst.executeUpdate();
            if (i > 0) {
                JOptionPane.showMessageDialog(null, "CONTATO SALVO COM SUCESSO!");
            }
            //numeros();
            carrerContatosSeguradora();
            limparCamposCONTATOS();
        } catch (SQLException ex) {
            Logger.getLogger(ViewCadSeguradora.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btSalvarContatoActionPerformed

    private void EmailContatoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_EmailContatoFocusLost
        // Operação da Conta
        this.EmailContato.setText(EmailContato.getText().toUpperCase());
    }//GEN-LAST:event_EmailContatoFocusLost

    private void tfTelefoneContatoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfTelefoneContatoFocusLost
        // AGENCIA
        this.tfTelefoneContato.setText(tfTelefoneContato.getText().toUpperCase());
    }//GEN-LAST:event_tfTelefoneContatoFocusLost

    private void tfObsContatoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfObsContatoFocusLost
        // Observações do CONTATO
        if ("".equals(tfObsContato.getText())) {
            this.tfObsContato.setText("Nehuma obeservação.");
        }
        this.tfObsContato.setText(tfObsContato.getText());
    }//GEN-LAST:event_tfObsContatoFocusLost

    private void tfNomeContatoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfNomeContatoFocusLost
        // NOME CONTATOS
        this.tfNomeContato.setText(tfNomeContato.getText().toUpperCase());
    }//GEN-LAST:event_tfNomeContatoFocusLost

    private void btAtualizarTerceiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAtualizarTerceiroActionPerformed
        // TODO add your handling code here:
        try {

            int cod = (Integer.parseInt(tfCodContato.getText()));

            String sql = "UPDATE contatos_seguradora SET cttseg_nome = ?, "
                    + "cttseg_telefone = ?,"
                    + "cttseg_email = ?,"
                    + "cttseg_obs = ?"
                    + "WHERE codigo = '" + cod + "'";

            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, tfNomeContato.getText());
            pst.setString(2, tfTelefoneContato.getText());
            pst.setString(3, EmailContato.getText());
            pst.setString(4, tfObsContato.getText());
            int n = pst.executeUpdate();

            if (n > 0) {
                JOptionPane.showMessageDialog(null, "Os dados foram atualizados com sucesso!");
                tfCodContato.setText(null);
                // TODO add your handling code here:
                btSalvarContato.setEnabled(true);
                tfNomeContato.setText(null);
                tfTelefoneContato.setText(null);
                EmailContato.setText(null);
                tfObsContato.setText(null);
                this.carrerContatosSeguradora();
                limparCamposCONTATOS();
                btAtualizarTerceiro.setEnabled(false);
                btAlterarTerceiro.setVisible(true);
                btAlterarTerceiro.setEnabled(true);
                btCancelarContato.setVisible(false);
                btCancelarContato.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao atualizar os dados");
                this.carrerContatosSeguradora();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ViewCadOp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btAtualizarTerceiroActionPerformed

    private void btAlterarTerceiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAlterarTerceiroActionPerformed
        // TODO add your handling code here:

        int fila = jTableContatos.getSelectedRow();
        if (fila >= 0) {
            btSalvarContato.setEnabled(false);
            btAtualizarTerceiro.setEnabled(true);
            String cod = jTableContatos.getValueAt(fila, 0).toString();
            String nome = jTableContatos.getValueAt(fila, 1).toString();
            String tel = jTableContatos.getValueAt(fila, 2).toString();
            String email = jTableContatos.getValueAt(fila, 3).toString();
            String obs = jTableContatos.getValueAt(fila, 4).toString();
            tfCodContato.setText(cod);
            tfNomeContato.setText(nome);
            tfTelefoneContato.setText(tel);
            tfObsContato.setText(obs);
            EmailContato.setText(email);
            btAlterarTerceiro.setEnabled(false);
            btAlterarTerceiro.setVisible(false);
            btCancelarContato.setVisible(true);
            btCancelarContato.setEnabled(true);
            this.carrerContatosSeguradora();
        } else {
            JOptionPane.showMessageDialog(null, "Escolha uma linha na tabela antes!");
        }
    }//GEN-LAST:event_btAlterarTerceiroActionPerformed

    private void cbCidadePopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbCidadePopupMenuWillBecomeVisible
        // TODO add your handling code here:
        if (this.cbUf.isPopupVisible()) {
            listarCidades();
        }
    }//GEN-LAST:event_cbCidadePopupMenuWillBecomeVisible

    private void cbUfPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbUfPopupMenuWillBecomeVisible
        // TODO add your handling code here:
        if (this.cbUf.isPopupVisible()) {
            listarCidades();
        }
    }//GEN-LAST:event_cbUfPopupMenuWillBecomeVisible

    private void cbCidadeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cbCidadeFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_cbCidadeFocusGained

    private void cbUfFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cbUfFocusLost
        // TODO add your handling code here:
        listarCidades();
    }//GEN-LAST:event_cbUfFocusLost
    /**
     * /**
     *
     * /**
     * Preencher Combobox Estados
     */
    public void listarEstados() {
        listaModelEstados = controllerEstado.getListaEstadoController();
        cbUf.removeAllItems();
        for (int i = 0; i < listaModelEstados.size(); i++) {
            cbUf.addItem(listaModelEstados.get(i).getUf());
        }
    }

    /**
     * Preencher Combobox Cidades
     */
    private boolean salvarSeguradora() {
        modelSeguradora.setNome(this.tfNomeRazao.getText().toUpperCase());
        modelSeguradora.setEndereco(this.tfEndereco.getText());
        modelSeguradora.setBairro(this.tfBairro.getText());
        modelSeguradora.setCodCidade(controllerCidade.getCidadeController(this.cbCidade.getSelectedItem().toString()).getCodigo());
        modelSeguradora.setCodEstado(controllerEstado.getEstadoUFController(ViewCadSeguradora.cbUf.getSelectedItem().toString()).getCodigo());
        modelSeguradora.setCep(this.jfCep.getText());
        modelSeguradora.setTelefone(this.jfTelefone.getText());
        modelSeguradora.setCelular(this.jfCelular.getText());
        modelSeguradora.setEmail(this.tfEmail.getText());
        modelSeguradora.setCnpj(this.jfCnpj.getText());
        modelSeguradora.setObservacao(this.tfObservacao.getText());
        modelSeguradora.setNumero(Integer.parseInt(this.tfNumero.getText()));
        modelSeguradora.setComplemento(this.tfComplemento.getText());
        modelSeguradora.setSeg_iss(cbISSretido.getSelectedItem().toString());
        modelSeguradora.setSeg_honorarios(Double.parseDouble(new Moeda().FommatoStringoSomarMil(jfValorHonorarios.getText())));
        modelSeguradora.setSeg_km(Double.parseDouble(new Moeda().FommatoStringoSomarMil(jfValorKm.getText())));
        modelSeguradora.setSeg_negativa(this.cbNegativa.getSelectedItem().toString());
        modelSeguradora.setSeg_hmeio(Double.parseDouble(new Moeda().FommatoStringoSomarMil(jFMeioHonorario.getText())));
        //Salvar
        if (controllerSeguradora.salvarSeguradoraController(modelSeguradora) > 0) {
            JOptionPane.showMessageDialog(this, "Registro gravado com sucesso!");
            limpaCampos();
            dispose();
            ViewSeguradora.seguradora.requestFocus();
            return true;
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao gravar os dados!", "ERRO", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }//fecha salvarSeguradora

    private boolean alteraSeguradora() {
        tipoCadastro = "alteracao";
        modelSeguradora.setCodigo(Integer.parseInt(this.tfCodigo.getText()));
        modelSeguradora.setNome(this.tfNomeRazao.getText());
        modelSeguradora.setEndereco(this.tfEndereco.getText());
        modelSeguradora.setBairro(this.tfBairro.getText());
        modelSeguradora.setCodEstado(controllerEstado.getEstadoUFController(this.cbUf.getSelectedItem().toString()).getCodigo());
        modelSeguradora.setCodCidade(controllerCidade.getCidadeController(this.cbCidade.getSelectedItem().toString()).getCodigo());
        modelSeguradora.setCep(this.jfCep.getText());
        modelSeguradora.setTelefone(this.jfTelefone.getText());
        modelSeguradora.setCelular(this.jfCelular.getText());
        modelSeguradora.setEmail(this.tfEmail.getText());
        modelSeguradora.setCnpj(this.jfCnpj.getText());
        modelSeguradora.setObservacao(this.tfObservacao.getText());
        modelSeguradora.setNumero(Integer.parseInt(this.tfNumero.getText()));
        modelSeguradora.setComplemento(this.tfComplemento.getText());
        modelSeguradora.setSeg_iss(cbISSretido.getSelectedItem().toString());
        modelSeguradora.setSeg_honorarios(Double.parseDouble(new Moeda().FommatoStringoSomarMil(jfValorHonorarios.getText())));
        modelSeguradora.setSeg_km(Double.parseDouble(new Moeda().FommatoStringoSomarMil(jfValorKm.getText())));
        modelSeguradora.setSeg_hmeio(Double.parseDouble(new Moeda().FommatoStringoSomarMil(this.jFMeioHonorario.getText())));
        modelSeguradora.setSeg_negativa(cbNegativa.getSelectedItem().toString());
        //alterar
        if (controllerSeguradora.atualizarSeguradoraController(modelSeguradora)) {
            JOptionPane.showMessageDialog(this, "Registro alterado com sucesso");
            ViewSeguradora.seguradora.tipoCadastro = null;
            // ViewSeguradora.seguradora = new ViewSeguradora();
            seguradora.carregarSeguradoras();
            limpaCampos();
            dispose();
            ViewSeguradora.seguradora.requestFocus();
            return true;
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao alterar os dados!", "ERRO", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }//fecha alterar Seguradoras

    private boolean recuperarSeguradora() {
        try {
            //recupera os dados do CONTATOS
            modelSeguradora = controllerSeguradora.getSeguradoraController(ViewSeguradora.codigo);
            //seta os dados na interface
            this.tfCodigo.setText(String.valueOf(modelSeguradora.getCodigo()));
            this.tfNomeRazao.setText(modelSeguradora.getNome());
            this.tfEndereco.setText(modelSeguradora.getEndereco());
            this.tfBairro.setText(modelSeguradora.getBairro());
            this.cbUf.setSelectedItem(controllerEstado.getEstadoController(modelSeguradora.getCodEstado()).getUf());
            this.listarCidades();
            this.cbCidade.setSelectedItem(controllerCidade.getCidadeController(modelSeguradora.getCodCidade()).getNome());
            this.jfCep.setText(modelSeguradora.getCep());
            this.jfTelefone.setText(modelSeguradora.getTelefone());
            this.jfCelular.setText(modelSeguradora.getCelular());
            this.tfEmail.setText(modelSeguradora.getEmail());
            this.jfCnpj.setText(modelSeguradora.getCnpj());
            this.tfObservacao.setText(modelSeguradora.getObservacao());
            this.tfNumero.setText("" + modelSeguradora.getNumero());
            this.tfComplemento.setText(modelSeguradora.getComplemento());
            this.jfValorHonorarios.setText(new Moeda().valorStringTODoubleAtt(String.valueOf(modelSeguradora.getSeg_honorarios())));
            this.jfValorKm.setText(new Moeda().valorStringTODoubleAtt(String.valueOf(modelSeguradora.getSeg_km())));
            this.cbISSretido.setSelectedItem(modelSeguradora.getSeg_iss());
            this.cbNegativa.setSelectedItem(modelSeguradora.getSeg_negativa());
            this.jFMeioHonorario.setText(new Moeda().valorStringTODoubleAtt(String.valueOf(modelSeguradora.getSeg_hmeio())));
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Codigo inválido ou nenhum registro selecionado", "Aviso", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }

    private void limpaCampos() {
        tfNomeRazao.setText("");
        tfEndereco.setText("");
        tfBairro.setText("");
        cbUf.setSelectedIndex(0);
        jfCep.setText("");
        jfTelefone.setText("");
        jfCelular.setText("");
        tfEmail.setText("");
        jfCnpj.setText("");
        tfObservacao.setText("");
        tfNumero.setText("");
        jfValorHonorarios.setText("");
        jfValorKm.setText("");
        cbISSretido.setSelectedIndex(0);
        cbNegativa.setSelectedIndex(0);
        jFMeioHonorario.setText("");
    }

    public void listarCidades() {
        listaModelCidades = controllerCidade.getListaCidadePorEstadoController(controllerEstado.getEstadoUFController(ViewCadSeguradora.cbUf.getSelectedItem().toString()).getCodigo());
        cbCidade.removeAllItems();
        for (int i = 0; i < listaModelCidades.size(); i++) {
            cbCidade.addItem(listaModelCidades.get(i).getNome());
        }
    }
    private static final int[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
    private static final int[] pesoCNPJ = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

    private static int calcularDigito(String str, int[] peso) {
        int soma = 0;
        String str1 = str.replace("/", "").replace(".", "").replace("-", "");
        for (int indice = str1.length() - 1, digito; indice >= 0; indice--) {
            digito = Integer.parseInt(str1.substring(indice, indice + 1));
            soma += digito * peso[peso.length - str1.length() + indice];
        }
        soma = 11 - soma % 11;
        return soma > 9 ? 0 : soma;
    }

    public static boolean isValidCNPJ(String cnpj) {
        if ((cnpj == null) || (cnpj.length() != 18)) {
            return false;
        }

        Integer digito1 = calcularDigito(cnpj.substring(0, 12), pesoCNPJ);
        Integer digito2 = calcularDigito(cnpj.substring(0, 12) + digito1, pesoCNPJ);
        return cnpj.equals(cnpj.substring(0, 12) + digito1.toString() + digito2.toString());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField EmailContato;
    private javax.swing.JButton btAlterarTerceiro;
    private javax.swing.JButton btAtualizarTerceiro;
    private javax.swing.JButton btCancelar;
    private javax.swing.JButton btCancelarContato;
    private javax.swing.JButton btCidade;
    private javax.swing.JButton btExcluirContato;
    private javax.swing.JButton btSalvar;
    private javax.swing.JButton btSalvarContato;
    public static javax.swing.JComboBox cbCidade;
    public static javax.swing.JComboBox cbISSretido;
    public static javax.swing.JComboBox cbNegativa;
    public static javax.swing.JComboBox cbUf;
    private javax.swing.JFormattedTextField jFMeioHonorario;
    private javax.swing.JLabel jLEmailContato;
    private javax.swing.JLabel jLNomeContato;
    private javax.swing.JLabel jLObsContato;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JPanel jPanelPrincipal;
    private javax.swing.JPanel jSalvar;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable jTableContatos;
    private javax.swing.JFormattedTextField jfCelular;
    private javax.swing.JFormattedTextField jfCep;
    private javax.swing.JFormattedTextField jfCnpj;
    private javax.swing.JFormattedTextField jfTelefone;
    private javax.swing.JFormattedTextField jfValorHonorarios;
    private javax.swing.JFormattedTextField jfValorKm;
    private javax.swing.JLabel jlBairro;
    private javax.swing.JLabel jlCep;
    private javax.swing.JLabel jlCidade;
    private javax.swing.JLabel jlCnpj;
    private javax.swing.JLabel jlCodigo;
    private javax.swing.JLabel jlComplemento;
    private javax.swing.JLabel jlEmail;
    private javax.swing.JLabel jlEndereco;
    private javax.swing.JLabel jlEstado;
    private javax.swing.JLabel jlIssretido;
    private javax.swing.JLabel jlNomeRazao;
    private javax.swing.JLabel jlNumero;
    private javax.swing.JLabel jlObs;
    private javax.swing.JLabel jlTelefoneContato;
    private javax.swing.JLabel jlValorhonario;
    private javax.swing.JLabel jlValorkm;
    private javax.swing.JLabel jlValorneg;
    private keeptoo.KGradientPanel kGContato;
    private keeptoo.KGradientPanel kGradientPanel1;
    private keeptoo.KGradientPanel kGradientPanel2;
    private keeptoo.KGradientPanel kGradientPanel22;
    private keeptoo.KGradientPanel kGradientPanel3;
    private javax.swing.JTextField tfBairro;
    private javax.swing.JLabel tfCelular;
    private javax.swing.JTextField tfCodContato;
    private javax.swing.JTextField tfCodigo;
    private javax.swing.JTextField tfComplemento;
    private javax.swing.JTextField tfEmail;
    private javax.swing.JTextField tfEndereco;
    private javax.swing.JTextField tfNomeContato;
    private javax.swing.JTextField tfNomeRazao;
    private javax.swing.JTextField tfNumero;
    private javax.swing.JTextField tfObsContato;
    private javax.swing.JTextField tfObservacao;
    private javax.swing.JLabel tfTelefone;
    private javax.swing.JTextField tfTelefoneContato;
    // End of variables declaration//GEN-END:variables

    ConexaoBanco cc = new ConexaoBanco();
    Connection cn = cc.conectar();

    private void SetIcone() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("ictrol.png")));
    }
}
