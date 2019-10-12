/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ControllerContaPagar;
import Controller.ControllerContaReceber;
import Controller.ControllerFormaPagamento;
import Controller.ControllerFuncionario;
import Controller.ControllerSeguradora;
import Model.ModelContaPagar;
import Model.ModelContaReceber;
import Model.ModelFormaPagamento;
import Model.ModelFuncionario;
import Model.ModelOp;
import Model.ModelSeguradora;
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
 * @author Work
 */
public class ViewFluxoContasDespesas extends javax.swing.JFrame {

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

    ModelFuncionario modelFuncionario = new ModelFuncionario();

    Mascaras swMascaras = new Mascaras();

    /**
     * Creates new form FluxoContasDespesas
     */
    public ViewFluxoContasDespesas() {
        initComponents();
        listarFuncionarios();
        SetIcone();
        setLocationRelativeTo(null);
        cbFuncionarios.setSelectedIndex(-1);
        cbFuncionarios.setSelectedItem(null);
        carregarContas();
        somaEAtualizaValorTotal();
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
                new ViewFluxoContasDespesas().setVisible(true);
            }
        });
    }

    private void carregarContas() {
        Datas bl = new Datas();

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
        modelContasPagar.setSituacao(1);
        controllerContaPagar.FiltraContaPagarFluxoDados(modelContaPagar, dataInicial, dataFinal);

        listaModelContaPagar = controllerContaPagar.FiltraContaPagarFluxoDados(modelContaPagar, dataInicial, dataFinal);
        modelo.setNumRows(0);
        String nomeBeneficiario, tipoPagamento = "";
        //CARREGA OS DADOS DA LISTA NA TABELA as contas a pagar
        int contPagar = listaModelContaPagar.size();
        for (int i = 0; i < contPagar; i++) {
            nomeBeneficiario = controllerFuncionario.getFuncionarioController(listaModelContaPagar.get(i).getCodigoPessoa()).getNome();
            tipoPagamento = controllerTipoPagamento.getFormaPagamentoController(listaModelContaPagar.get(i).getTipoPagamento()).getDescricao();
            modelo.addRow(new Object[]{
                listaModelContaPagar.get(i).getCodigo(),
                "PAGA " + listaModelContaPagar.get(i).getDescricao(),
                nomeBeneficiario,
                (new Mascaras().DataRecuperasql(listaModelContaPagar.get(i).getData())),
                (new Mascaras().DataRecuperasql(listaModelContaPagar.get(i).getVencimento())),
                (new Moeda().valorStringTODoubleAtt(String.valueOf(listaModelContaPagar.get(i).getValor()))),
                "DÉBITO",
                tipoPagamento,
                "CONTA"
            });
            CorNaLinhaCredito();
        }

        modelContaReceber.setSituacao(1);
        ModelContaReceber modelContaReceber = new ModelContaReceber();
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
                "RECEBIDA " + listaModelContaReceber.get(i).getDescricao(),
                nomeBeneficiario,
                (new Mascaras().DataRecuperasql(listaModelContaReceber.get(i).getData())),
                (new Mascaras().DataRecuperasql(listaModelContaReceber.get(i).getVencimento())),
                (new Moeda().valorStringTODoubleAtt(String.valueOf(listaModelContaReceber.get(i).getValor()))),
                "CRÉDITO",
                tipoPagamento,
                "RECEBER"
            });
            CorNaLinhaCredito();
        }
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
        jlDebito.setText(swMascaras.arredondamentoComPontoDuasCasasString(totalPagar));
        jlCredito.setText(swMascaras.arredondamentoComPontoDuasCasasString(totalReceber));
        jlSaldoTotalCaixa.setText(swMascaras.arredondamentoComPontoDuasCasasString(totalReceber - totalPagar));
        float corJpanel = totalReceber - totalPagar;
        if (corJpanel < 0) {
            jPanel3.setBackground(Color.red);
        } else {
            jPanel3.setBackground(Color.GREEN);
        }

    }

    private void listarFuncionarios() {
        listaModelFuncionario = controllerFuncionario.getListaFuncionarioController();
        cbFuncionarios.removeAllItems();
        for (int i = 0; i < listaModelFuncionario.size(); i++) {
            cbFuncionarios.addItem(listaModelFuncionario.get(i).getNome());
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

        jPanelContasDespesas = new javax.swing.JPanel();
        jdcDataInicial = new com.toedter.calendar.JDateChooser();
        btDefinirData = new javax.swing.JButton();
        jdcDataFinal = new com.toedter.calendar.JDateChooser();
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
        cbFuncionarios = new javax.swing.JComboBox<String>();
        jLabel19 = new javax.swing.JLabel();
        btClear = new javax.swing.JButton();
        kGPanelFiltro = new keeptoo.KGradientPanel();
        lbFluxoCaixa = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1260, 690));
        setPreferredSize(new java.awt.Dimension(1260, 690));
        setResizable(false);

        jPanelContasDespesas.setBackground(new java.awt.Color(255, 255, 255));
        jPanelContasDespesas.setMinimumSize(new java.awt.Dimension(1250, 680));
        jPanelContasDespesas.setPreferredSize(new java.awt.Dimension(1250, 670));
        jPanelContasDespesas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jdcDataInicial.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jdcDataInicialFocusLost(evt);
            }
        });
        jPanelContasDespesas.add(jdcDataInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 150, 28));

        btDefinirData.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btDefinirData.setForeground(new java.awt.Color(51, 51, 51));
        btDefinirData.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/page_find.png"))); // NOI18N
        btDefinirData.setText("APLICAR");
        btDefinirData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDefinirDataActionPerformed(evt);
            }
        });
        jPanelContasDespesas.add(btDefinirData, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 80, 130, 35));
        jPanelContasDespesas.add(jdcDataFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 80, 150, 28));

        jLabel3.setText("Selecione o dia:");
        jPanelContasDespesas.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, -1));

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
                "Cod.", "Descrição", "Funcionário", "Entrada", "Vencimento", "Valor", "Movimento", "Situação", "Tipo Conta"
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
            tbFluxoDados.getColumnModel().getColumn(0).setMinWidth(50);
            tbFluxoDados.getColumnModel().getColumn(0).setPreferredWidth(50);
            tbFluxoDados.getColumnModel().getColumn(0).setMaxWidth(50);
            tbFluxoDados.getColumnModel().getColumn(1).setMinWidth(200);
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

        jPanelContasDespesas.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 1220, 400));

        kGradientPanel1.setkBorderRadius(0);
        kGradientPanel1.setkEndColor(new java.awt.Color(234, 239, 243));
        kGradientPanel1.setkGradientFocus(-5);
        kGradientPanel1.setkStartColor(new java.awt.Color(255, 255, 255));
        kGradientPanel1.setPreferredSize(new java.awt.Dimension(1250, 210));
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

        kGradientPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 180, -1, -1));

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
        kGradientPanel1.add(jbVisualizarConta, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, -1));

        jPanelContasDespesas.add(kGradientPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 380, 1250, 300));

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
        jPanelContasDespesas.add(cbFuncionarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 80, 470, 30));

        jLabel19.setText("Especificar Funcionário");
        jPanelContasDespesas.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 60, -1, -1));

        btClear.setBackground(new java.awt.Color(153, 153, 153));
        btClear.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btClear.setForeground(new java.awt.Color(255, 255, 255));
        btClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Erase.png"))); // NOI18N
        btClear.setText("LIMPAR");
        btClear.setPreferredSize(new java.awt.Dimension(110, 40));
        btClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btClearActionPerformed(evt);
            }
        });
        jPanelContasDespesas.add(btClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 80, 120, -1));

        kGPanelFiltro.setkBorderRadius(0);
        kGPanelFiltro.setkEndColor(new java.awt.Color(228, 235, 241));
        kGPanelFiltro.setkGradientFocus(0);
        kGPanelFiltro.setkStartColor(new java.awt.Color(255, 255, 255));
        kGPanelFiltro.setMaximumSize(new java.awt.Dimension(1280, 140));
        kGPanelFiltro.setMinimumSize(new java.awt.Dimension(1280, 140));
        kGPanelFiltro.setPreferredSize(new java.awt.Dimension(1280, 140));
        kGPanelFiltro.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbFluxoCaixa.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbFluxoCaixa.setText("CONTAS A PAGAR E RECEBER");
        kGPanelFiltro.add(lbFluxoCaixa, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 320, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/24x24/Calculator.png"))); // NOI18N
        kGPanelFiltro.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/agentes.png"))); // NOI18N
        kGPanelFiltro.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 80, -1, -1));

        jPanelContasDespesas.add(kGPanelFiltro, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 130));

        getContentPane().add(jPanelContasDespesas, java.awt.BorderLayout.CENTER);

        getAccessibleContext().setAccessibleParent(this);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jdcDataInicialFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jdcDataInicialFocusLost
        // TODO add your handling code here:
        carregarContas();
        somaEAtualizaValorTotal();
    }//GEN-LAST:event_jdcDataInicialFocusLost

    private void btDefinirDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDefinirDataActionPerformed
        // TODO add your handling code here:
        carregarContas();
        somaEAtualizaValorTotal();
    }//GEN-LAST:event_btDefinirDataActionPerformed

    private void cbFuncionariosPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbFuncionariosPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
        if (this.cbFuncionarios.isPopupVisible() && this.cbFuncionarios.getSelectedItem() != null) {
            modelFuncionario = controllerFuncionario.getFuncionarioController(cbFuncionarios.getSelectedItem().toString());
            DefaultTableModel tabela = (DefaultTableModel) this.tbFluxoDados.getModel();
            final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tabela);
            this.tbFluxoDados.setRowSorter(sorter);
            String text = cbFuncionarios.getSelectedItem().toString();
            sorter.setRowFilter(RowFilter.regexFilter(text, 2));
            somaEAtualizaValorTotal();
        } else {
            DefaultTableModel tabela = (DefaultTableModel) this.tbFluxoDados.getModel();
            this.tbFluxoDados.setRowSorter(null);
            somaEAtualizaValorTotal();
        }

    }//GEN-LAST:event_cbFuncionariosPopupMenuWillBecomeInvisible

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
                nom.add("Salvar Fluxo");
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

    private void btClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btClearActionPerformed
        // TODO add your handling code here:
        DefaultTableModel tabela = (DefaultTableModel) this.tbFluxoDados.getModel();
        this.tbFluxoDados.setRowSorter(null);
        listarFuncionarios();
        cbFuncionarios.setSelectedIndex(-1);
        cbFuncionarios.setSelectedItem(null);
        carregarContas();
        somaEAtualizaValorTotal();
    }//GEN-LAST:event_btClearActionPerformed

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
                    tbFluxoDados.setSelectionForeground(Color.LIGHT_GRAY);
                } else {
                    c = Color.RED;
                    label.setForeground(c);
                    label.setFont(new Font("Tahoma", Font.BOLD, 12));
                    tbFluxoDados.setSelectionForeground(Color.LIGHT_GRAY);
                }
                return label;
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btClear;
    private javax.swing.JButton btDefinirData;
    private javax.swing.JComboBox<String> cbFuncionarios;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanelContasDespesas;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JButton jbVisualizarConta;
    private com.toedter.calendar.JDateChooser jdcDataFinal;
    private com.toedter.calendar.JDateChooser jdcDataInicial;
    private static javax.swing.JLabel jlCredito;
    private static javax.swing.JLabel jlDebito;
    private static javax.swing.JLabel jlSaldoTotalCaixa;
    private keeptoo.KGradientPanel kGPanelFiltro;
    private keeptoo.KGradientPanel kGradientPanel1;
    private javax.swing.JLabel lbFluxoCaixa;
    private javax.swing.JTable tbFluxoDados;
    // End of variables declaration//GEN-END:variables

    public static ViewFluxoContasDespesas viewfluxocontasdespesas;

    private void SetIcone() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("ictrol.png")));
    }
}
