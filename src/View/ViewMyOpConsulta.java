/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ControllerCobertura;
import Controller.ControllerFormaPagamento;
import util.Datas;
import Controller.ControllerOp;
import Controller.ControllerSeguradora;
import Controller.ControllerServicos;
import Model.ModelCobertura;
import Model.ModelFormaPagamento;
import Model.ModelOp;
import Model.ModelSeguradora;
import Model.ModelServicos;
import conexao.ConexaoBanco;
import java.sql.Connection;
import util.Mascaras;
import util.Moeda;
import Controller.ControllerCidade;
import Controller.ControllerFuncionario;
import Controller.ControllerEstado;
import Model.ModelSessaoUsuario;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Administrador
 */
public final class ViewMyOpConsulta extends javax.swing.JFrame {

    Mascaras Mascaras = new Mascaras();
    ModelServicos modelServicos = new ModelServicos();
    ArrayList<ModelServicos> listaModelServicos = new ArrayList<>();
    ControllerServicos controllerServicos = new ControllerServicos();
    ArrayList<ModelOp> listaModelOp = new ArrayList<>();

    ControllerOp controllerOp = new ControllerOp();
    ArrayList<ModelOp> listaModelOpAlterar = new ArrayList<>();
    ModelOp modelOp = new ModelOp();
    ArrayList<ModelSeguradora> listaModelSeguradora = new ArrayList<>();
    ControllerSeguradora controllerSeguradora = new ControllerSeguradora();
    ModelSeguradora modelSeguradora = new ModelSeguradora();
    ControllerEstado controllerEstado = new ControllerEstado();
    ControllerCidade controllerCidade = new ControllerCidade();
    ControllerFormaPagamento controllerTipoPagamento = new ControllerFormaPagamento();
    ArrayList<ModelFormaPagamento> listaModelTipoPagamentos = new ArrayList<>();
    float valorCartao, valorCheque, valorDinheiro, valorVale;
    ControllerFuncionario controllerFuncionario = new ControllerFuncionario();
    ModelCobertura modelCobertura = new ModelCobertura();
    ArrayList<ModelCobertura> listaModelCobertura = new ArrayList<>();
    ControllerCobertura controllerCobertura = new ControllerCobertura();
    ModelFormaPagamento modelFormaPagamento = new ModelFormaPagamento();

    public ViewMyOpConsulta() {
        initComponents();
        SetIcone();
        this.carregarProcessosDeConsultar();
        jTabbedPanelDespesas.setSelectedIndex(0);
        listarSeguradoras();
    }
    static int codigo = 0;
    static String tipoCadastro = null;

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
                new ViewMyOpConsulta().setVisible(true);
            }
        });
    }

    private void listarSeguradoras() {
        listaModelSeguradora = controllerSeguradora.getListaSeguradoraController();
        // cbSeguradoras.removeAllItems();
        for (int i = 0; i < listaModelSeguradora.size(); i++) {
            cbSeguradoras.addItem(listaModelSeguradora.get(i).getNome());
        }
        // cbSeguradoras.setSelectedIndex(-1);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        kGradientPanel15 = new keeptoo.KGradientPanel();
        kButtonPendentes1 = new keeptoo.KButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTabbedPanelDespesas = new javax.swing.JTabbedPane();
        AguardandoPagamento = new javax.swing.JPanel();
        kGradientPanel12 = new keeptoo.KGradientPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tbProcessosAndamento = new javax.swing.JTable();
        kGradientPanel11 = new keeptoo.KGradientPanel();
        btBuscarAndamento = new javax.swing.JButton();
        tfPesquisarAndamento = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        btLimparAndamento = new javax.swing.JButton();
        btAlterar1 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        cbBuscarAndamento = new javax.swing.JComboBox<String>();
        jLabel20 = new javax.swing.JLabel();
        cbSeguradoras = new javax.swing.JComboBox<String>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("CONSULTAR PROCESSOS");
        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1085, 614));
        setPreferredSize(new java.awt.Dimension(1085, 614));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        kGradientPanel15.setBackground(new java.awt.Color(146, 171, 197));
        kGradientPanel15.setkBorderRadius(0);
        kGradientPanel15.setkEndColor(new java.awt.Color(146, 171, 197));
        kGradientPanel15.setkGradientFocus(0);
        kGradientPanel15.setkStartColor(new java.awt.Color(146, 171, 197));
        kGradientPanel15.setName(""); // NOI18N
        kGradientPanel15.setPreferredSize(new java.awt.Dimension(1300, 40));

        kButtonPendentes1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/24x24/Refresh.png"))); // NOI18N
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

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 102));
        jLabel1.setText("CONSULTAR PROCESSOS");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/24x24/Find.png"))); // NOI18N

        org.jdesktop.layout.GroupLayout kGradientPanel15Layout = new org.jdesktop.layout.GroupLayout(kGradientPanel15);
        kGradientPanel15.setLayout(kGradientPanel15Layout);
        kGradientPanel15Layout.setHorizontalGroup(
            kGradientPanel15Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(kGradientPanel15Layout.createSequentialGroup()
                .add(33, 33, 33)
                .add(jLabel2)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 285, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 664, Short.MAX_VALUE)
                .add(kButtonPendentes1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        kGradientPanel15Layout.setVerticalGroup(
            kGradientPanel15Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
            .add(kGradientPanel15Layout.createSequentialGroup()
                .add(2, 2, 2)
                .add(kButtonPendentes1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
            .add(org.jdesktop.layout.GroupLayout.LEADING, kGradientPanel15Layout.createSequentialGroup()
                .add(9, 9, 9)
                .add(kGradientPanel15Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 24, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel2)))
        );

        getContentPane().add(kGradientPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1085, -1));

        jTabbedPanelDespesas.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPanelDespesas.setFocusable(false);
        jTabbedPanelDespesas.setMaximumSize(new java.awt.Dimension(1084, 730));
        jTabbedPanelDespesas.setMinimumSize(new java.awt.Dimension(1084, 730));
        jTabbedPanelDespesas.setPreferredSize(new java.awt.Dimension(1084, 730));

        AguardandoPagamento.setBackground(new java.awt.Color(255, 255, 255));
        AguardandoPagamento.setMaximumSize(new java.awt.Dimension(1084, 575));
        AguardandoPagamento.setMinimumSize(new java.awt.Dimension(1084, 575));
        AguardandoPagamento.setPreferredSize(new java.awt.Dimension(1084, 575));

        kGradientPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Processos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(51, 51, 51))); // NOI18N
        kGradientPanel12.setkBorderRadius(0);
        kGradientPanel12.setkEndColor(new java.awt.Color(234, 239, 243));
        kGradientPanel12.setkGradientFocus(0);
        kGradientPanel12.setkStartColor(new java.awt.Color(255, 255, 255));
        kGradientPanel12.setPreferredSize(new java.awt.Dimension(1080, 450));
        kGradientPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane7.setBackground(new java.awt.Color(247, 247, 247));
        jScrollPane7.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane7.setPreferredSize(new java.awt.Dimension(1220, 180));

        tbProcessosAndamento.setBackground(new java.awt.Color(247, 247, 247));
        tbProcessosAndamento.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        tbProcessosAndamento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cod.", "Seguradora", "Data Entrada", "Nº Sinistro", "Segurado", "Cobertura", "Analista"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbProcessosAndamento.setGridColor(new java.awt.Color(247, 247, 247));
        tbProcessosAndamento.setMinimumSize(new java.awt.Dimension(610, 0));
        tbProcessosAndamento.setRowHeight(20);
        tbProcessosAndamento.setSelectionBackground(new java.awt.Color(0, 153, 0));
        tbProcessosAndamento.getTableHeader().setReorderingAllowed(false);
        jScrollPane7.setViewportView(tbProcessosAndamento);
        if (tbProcessosAndamento.getColumnModel().getColumnCount() > 0) {
            tbProcessosAndamento.getColumnModel().getColumn(0).setMinWidth(80);
            tbProcessosAndamento.getColumnModel().getColumn(0).setPreferredWidth(80);
            tbProcessosAndamento.getColumnModel().getColumn(0).setMaxWidth(80);
            tbProcessosAndamento.getColumnModel().getColumn(2).setMinWidth(100);
            tbProcessosAndamento.getColumnModel().getColumn(2).setPreferredWidth(100);
            tbProcessosAndamento.getColumnModel().getColumn(2).setMaxWidth(100);
            tbProcessosAndamento.getColumnModel().getColumn(3).setMinWidth(120);
            tbProcessosAndamento.getColumnModel().getColumn(3).setPreferredWidth(120);
            tbProcessosAndamento.getColumnModel().getColumn(3).setMaxWidth(120);
            tbProcessosAndamento.getColumnModel().getColumn(5).setMinWidth(120);
            tbProcessosAndamento.getColumnModel().getColumn(5).setPreferredWidth(120);
            tbProcessosAndamento.getColumnModel().getColumn(5).setMaxWidth(120);
        }

        kGradientPanel12.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 1010, 400));

        AguardandoPagamento.add(kGradientPanel12);

        kGradientPanel11.setkBorderRadius(0);
        kGradientPanel11.setkEndColor(new java.awt.Color(234, 239, 243));
        kGradientPanel11.setkGradientFocus(0);
        kGradientPanel11.setkStartColor(new java.awt.Color(255, 255, 255));
        kGradientPanel11.setPreferredSize(new java.awt.Dimension(1080, 110));
        kGradientPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btBuscarAndamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/View.png"))); // NOI18N
        btBuscarAndamento.setPreferredSize(new java.awt.Dimension(50, 40));
        btBuscarAndamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBuscarAndamentoActionPerformed(evt);
            }
        });
        kGradientPanel11.add(btBuscarAndamento, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 20, 50, 40));

        tfPesquisarAndamento.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfPesquisarAndamentoFocusGained(evt);
            }
        });
        tfPesquisarAndamento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfPesquisarAndamentoKeyPressed(evt);
            }
        });
        kGradientPanel11.add(tfPesquisarAndamento, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 30, 110, 28));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(102, 102, 102));
        jLabel14.setText("BUSCAR:");
        kGradientPanel11.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 10, -1, -1));

        btLimparAndamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Erase.png"))); // NOI18N
        btLimparAndamento.setText("LIMPAR FILTRO");
        btLimparAndamento.setPreferredSize(new java.awt.Dimension(50, 40));
        btLimparAndamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLimparAndamentoActionPerformed(evt);
            }
        });
        kGradientPanel11.add(btLimparAndamento, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 20, 180, -1));

        btAlterar1.setBackground(new java.awt.Color(0, 102, 255));
        btAlterar1.setFont(new java.awt.Font("Tahoma", 1, 9)); // NOI18N
        btAlterar1.setForeground(new java.awt.Color(255, 255, 255));
        btAlterar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/visualizar.png"))); // NOI18N
        btAlterar1.setText("VISUALIZAR");
        btAlterar1.setPreferredSize(new java.awt.Dimension(150, 40));
        btAlterar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAlterar1ActionPerformed(evt);
            }
        });
        kGradientPanel11.add(btAlterar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 140, -1));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(102, 102, 102));
        jLabel18.setText("BUSCAR POR:");
        kGradientPanel11.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 10, 100, -1));

        cbBuscarAndamento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Código", "Sinistro", "Segurado", "Analista", "Endereço" }));
        cbBuscarAndamento.setPreferredSize(new java.awt.Dimension(120, 28));
        kGradientPanel11.add(cbBuscarAndamento, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 30, -1, 28));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(102, 102, 102));
        jLabel20.setText("SEGURADORA:");
        kGradientPanel11.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, -1, -1));

        cbSeguradoras.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
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
        kGradientPanel11.add(cbSeguradoras, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 30, 240, 30));

        AguardandoPagamento.add(kGradientPanel11);

        jTabbedPanelDespesas.addTab("PROCESSOS AGUARDANDO PAGAMENTO", AguardandoPagamento);

        getContentPane().add(jTabbedPanelDespesas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 8, 1085, 590));

        getAccessibleContext().setAccessibleDescription("");
        getAccessibleContext().setAccessibleParent(this);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btCancelarFechar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarFechar1ActionPerformed
        // habilita ou desabilita campos
        dispose();
    }//GEN-LAST:event_btCancelarFechar1ActionPerformed

    private void cbTipoDespesaPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbTipoDespesaPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
    }//GEN-LAST:event_cbTipoDespesaPopupMenuWillBecomeInvisible

    private void btAlterar1ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_btAlterar1ActionPerformed
        // TODO add your handling code here:
        try {
            int linha = tbProcessosAndamento.getSelectedRow();
            tipoCadastro = "alteracao";
            String nome = (String) tbProcessosAndamento.getValueAt(linha, 1);
            codigo = (Integer) tbProcessosAndamento.getValueAt(linha, 0);
            //System.out.println("codigo; " + codigo);
            modelOp = controllerOp.getOpController(codigo);
            new ViewMyOpVisualizar(modelOp).setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Você selecionar uma Despesa para alterar!", "ATENÇÂO", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btAlterar1ActionPerformed

    private void kButtonPendentes1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButtonPendentes1ActionPerformed
        // TODO add your handling code here:
        carregarProcessosDeConsultar();
    }//GEN-LAST:event_kButtonPendentes1ActionPerformed

    private void btBuscarAndamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBuscarAndamentoActionPerformed
        // TODO add your handling code here:
        int situacao = 4;
        int situacao2 = 5;
        String seleciona = "";
        int AgenteSessao = controllerFuncionario.getFuncionarioController(ModelSessaoUsuario.nome).getCodigo();
        String conteudo = tfPesquisarAndamento.getText();
        int i = cbBuscarAndamento.getSelectedIndex();
        switch (i) {
            case 0:
                seleciona = "ordem_processo.codigo";
                break;
            case 1:
                seleciona = "ordem_processo.NumeroSinistro";
                break;
            case 2:
                seleciona = "ordem_processo.NomeSegurado";
                break;
            case 3:
                seleciona = "ordem_processo.Analista";
                break;
            case 4:
                seleciona = "ordem_processo.SinistroBairro";
                break;
            case 5:
                break;
            default:
                System.out.println("Não selecionado");
                break;
        }
        try {
            listaModelOp = controllerOp.getListaOpPesquisaConsultasAgenteEspecificoController(seleciona, conteudo, situacao, situacao2, AgenteSessao);
            pesquisaAndamento(listaModelOp);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Nenhum processo encontrado!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btBuscarAndamentoActionPerformed

    private void pesquisaAndamento(ArrayList<ModelOp> lista) {
        DefaultTableModel modelo = (DefaultTableModel) tbProcessosAndamento.getModel();
        modelo.setNumRows(0);

        //CARREGA OS DADOS DA LISTA NA TABELA
        int cont = lista.size();
        for (int i = 0; i < cont; i++) {
            String Seguradora = controllerSeguradora.getSeguradoraController(lista.get(i).getSeguradoras()).getNome();
            String CNPJ = controllerSeguradora.getSeguradoraController(lista.get(i).getSeguradoras()).getCnpj();
            String Situacao = controllerTipoPagamento.getFormaPagamentoController(lista.get(i).getTipo()).getDescricao();
            modelo.addRow(new Object[]{
                listaModelOp.get(i).getCodigo(),
                Seguradora,
                (new Mascaras().DataRecuperasql(String.valueOf(listaModelOp.get(i).getDataEntrada()))),
                listaModelOp.get(i).getNumeroSinistro(),
                listaModelOp.get(i).getNomeSegurado(),
                modelServicos.getNome(),
                listaModelOp.get(i).getAnalista()
            });
        }
    }

    private void btLimparAndamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLimparAndamentoActionPerformed
        // TODO add your handling code here:
        this.tbProcessosAndamento.setRowSorter(null);
        tfPesquisarAndamento.setText("");
        carregarProcessosDeConsultar();
    }//GEN-LAST:event_btLimparAndamentoActionPerformed

    private void tfPesquisarAndamentoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfPesquisarAndamentoFocusGained
        // TODO add your handling code here:

    }//GEN-LAST:event_tfPesquisarAndamentoFocusGained

    private void tfPesquisarAndamentoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPesquisarAndamentoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == evt.VK_ENTER) {
            int situacao = 4;
            int situacao2 = 5;
            String seleciona = "";
            int AgenteSessao = controllerFuncionario.getFuncionarioController(ModelSessaoUsuario.nome).getCodigo();
            String conteudo = tfPesquisarAndamento.getText();
            int i = cbBuscarAndamento.getSelectedIndex();
            switch (i) {
                case 0:
                    seleciona = "ordem_processo.codigo";
                    break;
                case 1:
                    seleciona = "ordem_processo.NumeroSinistro";
                    break;
                case 2:
                    seleciona = "ordem_processo.NomeSegurado";
                    break;
                case 3:
                    seleciona = "ordem_processo.Analista";
                    break;
                case 4:
                    seleciona = "ordem_processo.SinistroBairro";
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Não selecionado");
                    break;
            }
            try {
                listaModelOp = controllerOp.getListaOpPesquisaConsultasAgenteEspecificoController(seleciona, conteudo, situacao, situacao2, AgenteSessao);
                pesquisaAndamento(listaModelOp);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Nenhum processo encontrado!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_tfPesquisarAndamentoKeyPressed

    private void cbSeguradorasPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbSeguradorasPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
        if (this.cbSeguradoras.isPopupVisible() && (this.cbSeguradoras.getSelectedItem() != null) && !(this.cbSeguradoras.getSelectedItem().equals(""))) {
            modelSeguradora = controllerSeguradora.getSeguradoraController(cbSeguradoras.getSelectedItem().toString());
            DefaultTableModel tabela = (DefaultTableModel) this.tbProcessosAndamento.getModel();
            final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tabela);
            this.tbProcessosAndamento.setRowSorter(sorter);
            String text = cbSeguradoras.getSelectedItem().toString();
            sorter.setRowFilter(RowFilter.regexFilter(text, 1));
        } else {
            DefaultTableModel tabela = (DefaultTableModel) this.tbProcessosAndamento.getModel();
            this.tbProcessosAndamento.setRowSorter(null);
        }
    }//GEN-LAST:event_cbSeguradorasPopupMenuWillBecomeInvisible

    public void carregarProcessosDeConsultar() {
        int AgenteSessao = controllerFuncionario.getFuncionarioController(ModelSessaoUsuario.nome).getCodigo();
        listaModelOp = controllerOp.getPorAgenteApenasEMAndamentoController(AgenteSessao);
        DefaultTableModel modelo = (DefaultTableModel) tbProcessosAndamento.getModel();
        modelo.setNumRows(0);
        //CARREGA OS DADOS DA LISTA NA TABELA
        int cont = listaModelOp.size();
        for (int i = 0; i < cont; i++) {
            modelSeguradora = controllerSeguradora.getSeguradoraController(listaModelOp.get(i).getSeguradoras());
            modelServicos = controllerServicos.getServicosController(listaModelOp.get(i).getServicosCodigo());
            modelo.addRow(new Object[]{
                listaModelOp.get(i).getCodigo(),
                modelSeguradora.getNome(),
                (new Mascaras().DataRecuperasql(String.valueOf(listaModelOp.get(i).getDataEntrada()))),
                listaModelOp.get(i).getNumeroSinistro(),
                listaModelOp.get(i).getNomeSegurado(),
                modelServicos.getNome(),
                listaModelOp.get(i).getAnalista()
            });
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AguardandoPagamento;
    private javax.swing.JButton btAlterar1;
    private javax.swing.JButton btBuscarAndamento;
    private javax.swing.JButton btLimparAndamento;
    private javax.swing.JComboBox<String> cbBuscarAndamento;
    private javax.swing.JComboBox<String> cbSeguradoras;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPanelDespesas;
    private keeptoo.KButton kButtonPendentes1;
    private keeptoo.KGradientPanel kGradientPanel11;
    private keeptoo.KGradientPanel kGradientPanel12;
    private keeptoo.KGradientPanel kGradientPanel15;
    private javax.swing.JTable tbProcessosAndamento;
    private javax.swing.JTextField tfPesquisarAndamento;
    // End of variables declaration//GEN-END:variables

    public static ViewMyOpConsulta ViewMyOpConsulta;
    ConexaoBanco cc = new ConexaoBanco();
    Connection cn = cc.conectar();

    private void SetIcone() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("ictrol.png")));
    }

}
