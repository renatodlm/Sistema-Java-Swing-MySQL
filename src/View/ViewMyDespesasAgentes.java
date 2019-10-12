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
import Model.ModelSessaoUsuario;
import Model.ModelTipoDespesas;
import conexao.ConexaoBanco;
import java.awt.Toolkit;
import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import util.Exporter;
import util.Moeda;

/**
 *
 * @author Administrador
 */
public final class ViewMyDespesasAgentes extends javax.swing.JFrame {

    //DESPESAS
    ModelContaAgentes modelContaAgentes = new ModelContaAgentes();
    ControllerFuncionario controllerFuncionario = new ControllerFuncionario();
    ArrayList<ModelContaAgentes> listamModelContaAgentes = new ArrayList<>();
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
                new ViewMyDespesasAgentes().setVisible(true);
            }
        });
    }

    public ViewMyDespesasAgentes() {
        initComponents();
        SetIcone();
        jTabbedPanelDespesas.setSelectedIndex(0);
        panelTabbedKGVerficiar();
        kButtonPendentes.setSelected(true);
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
        jpRevorgarConta = new javax.swing.JButton();
        jPanelPrincipal = new javax.swing.JPanel();
        kGradientPanel11 = new keeptoo.KGradientPanel();
        kGradientPanel18 = new keeptoo.KGradientPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        kButtonPendentes = new keeptoo.KButton();
        kButtonProcessos2 = new keeptoo.KButton();
        jTabbedPanelDespesas = new javax.swing.JTabbedPane();
        DespesasPagas = new javax.swing.JPanel();
        kGradientPanel8 = new keeptoo.KGradientPanel();
        jScrollPane14 = new javax.swing.JScrollPane();
        tbDespesasRecebidas = new javax.swing.JTable();
        kGradientPanel10 = new keeptoo.KGradientPanel();
        btClearEfetuado = new javax.swing.JButton();
        tfAreceberEfetuado = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        tfAdiantamentosEfetuado = new javax.swing.JTextField();
        tfNegHrkmEfetuado = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        btDefinirDataEfetuado = new javax.swing.JButton();
        jdcDataFinal = new com.toedter.calendar.JDateChooser();
        jdcDataInicial = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        kGradientPanel9 = new keeptoo.KGradientPanel();
        jbVisualizarConta = new javax.swing.JButton();

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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("PLANILHAS E CONTROLE DE AGENTES");
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFocusable(false);
        setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        setForeground(new java.awt.Color(51, 51, 51));
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

        kButtonPendentes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/24x24/Dollar.png"))); // NOI18N
        kButtonPendentes.setText("MEUS PAGAMENTOS");
        kButtonPendentes.setToolTipText("");
        kButtonPendentes.setActionCommand("  MEUS PAGAMENTOS");
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
        kGradientPanel11.add(kButtonPendentes, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 0, 190, 50));

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

        jPanelPrincipal.add(kGradientPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jTabbedPanelDespesas.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPanelDespesas.setMinimumSize(new java.awt.Dimension(1180, 650));
        jTabbedPanelDespesas.setPreferredSize(new java.awt.Dimension(1180, 630));

        DespesasPagas.setBackground(new java.awt.Color(255, 255, 255));
        DespesasPagas.setMinimumSize(new java.awt.Dimension(1170, 380));
        DespesasPagas.setPreferredSize(new java.awt.Dimension(1170, 370));

        kGradientPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "MEUS PAGAMENTOS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(0, 51, 102))); // NOI18N
        kGradientPanel8.setkBorderRadius(0);
        kGradientPanel8.setkEndColor(new java.awt.Color(199, 223, 242));
        kGradientPanel8.setkGradientFocus(0);
        kGradientPanel8.setkStartColor(new java.awt.Color(255, 255, 255));
        kGradientPanel8.setMinimumSize(new java.awt.Dimension(1170, 430));
        kGradientPanel8.setPreferredSize(new java.awt.Dimension(1360, 430));
        kGradientPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane14.setBackground(new java.awt.Color(247, 247, 247));
        jScrollPane14.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane14.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane14.setPreferredSize(new java.awt.Dimension(1220, 180));

        tbDespesasRecebidas.setAutoCreateRowSorter(true);
        tbDespesasRecebidas.setBackground(new java.awt.Color(247, 247, 247));
        tbDespesasRecebidas.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        tbDespesasRecebidas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cod.Despesa", "Processo", "Envio", "Seguradora", "Sinistro", "Serviço", "Negativa", "Honorário", "KM Perco.", "Valor KM", "Total KM", "Recebimento", "Descrição", "Neg+Honorário+KM", "Adiantamento", "Valor a Receber", "agente", "Situação"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbDespesasRecebidas.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbDespesasRecebidas.setGridColor(new java.awt.Color(247, 247, 247));
        tbDespesasRecebidas.setMinimumSize(new java.awt.Dimension(610, 0));
        tbDespesasRecebidas.setRowHeight(20);
        tbDespesasRecebidas.setSelectionBackground(new java.awt.Color(0, 153, 0));
        tbDespesasRecebidas.getTableHeader().setReorderingAllowed(false);
        jScrollPane14.setViewportView(tbDespesasRecebidas);
        if (tbDespesasRecebidas.getColumnModel().getColumnCount() > 0) {
            tbDespesasRecebidas.getColumnModel().getColumn(0).setMinWidth(0);
            tbDespesasRecebidas.getColumnModel().getColumn(0).setPreferredWidth(0);
            tbDespesasRecebidas.getColumnModel().getColumn(0).setMaxWidth(0);
            tbDespesasRecebidas.getColumnModel().getColumn(1).setMinWidth(80);
            tbDespesasRecebidas.getColumnModel().getColumn(1).setPreferredWidth(80);
            tbDespesasRecebidas.getColumnModel().getColumn(1).setMaxWidth(80);
            tbDespesasRecebidas.getColumnModel().getColumn(2).setMinWidth(100);
            tbDespesasRecebidas.getColumnModel().getColumn(3).setMinWidth(300);
            tbDespesasRecebidas.getColumnModel().getColumn(4).setMinWidth(150);
            tbDespesasRecebidas.getColumnModel().getColumn(5).setMinWidth(100);
            tbDespesasRecebidas.getColumnModel().getColumn(5).setPreferredWidth(100);
            tbDespesasRecebidas.getColumnModel().getColumn(5).setMaxWidth(100);
            tbDespesasRecebidas.getColumnModel().getColumn(6).setMinWidth(80);
            tbDespesasRecebidas.getColumnModel().getColumn(6).setPreferredWidth(80);
            tbDespesasRecebidas.getColumnModel().getColumn(6).setMaxWidth(80);
            tbDespesasRecebidas.getColumnModel().getColumn(7).setMinWidth(80);
            tbDespesasRecebidas.getColumnModel().getColumn(7).setPreferredWidth(80);
            tbDespesasRecebidas.getColumnModel().getColumn(7).setMaxWidth(80);
            tbDespesasRecebidas.getColumnModel().getColumn(8).setMinWidth(80);
            tbDespesasRecebidas.getColumnModel().getColumn(9).setMinWidth(80);
            tbDespesasRecebidas.getColumnModel().getColumn(10).setMinWidth(80);
            tbDespesasRecebidas.getColumnModel().getColumn(11).setMinWidth(80);
            tbDespesasRecebidas.getColumnModel().getColumn(12).setMinWidth(100);
            tbDespesasRecebidas.getColumnModel().getColumn(13).setMinWidth(0);
            tbDespesasRecebidas.getColumnModel().getColumn(13).setPreferredWidth(0);
            tbDespesasRecebidas.getColumnModel().getColumn(13).setMaxWidth(0);
            tbDespesasRecebidas.getColumnModel().getColumn(14).setMinWidth(80);
            tbDespesasRecebidas.getColumnModel().getColumn(15).setMinWidth(0);
            tbDespesasRecebidas.getColumnModel().getColumn(15).setPreferredWidth(0);
            tbDespesasRecebidas.getColumnModel().getColumn(15).setMaxWidth(0);
            tbDespesasRecebidas.getColumnModel().getColumn(16).setMinWidth(0);
            tbDespesasRecebidas.getColumnModel().getColumn(16).setPreferredWidth(0);
            tbDespesasRecebidas.getColumnModel().getColumn(16).setMaxWidth(0);
            tbDespesasRecebidas.getColumnModel().getColumn(17).setMinWidth(100);
        }

        kGradientPanel8.add(jScrollPane14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 1320, 380));

        DespesasPagas.add(kGradientPanel8);

        kGradientPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "FILTRAR", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(51, 51, 51))); // NOI18N
        kGradientPanel10.setkBorderRadius(0);
        kGradientPanel10.setkEndColor(new java.awt.Color(234, 239, 243));
        kGradientPanel10.setkGradientFocus(0);
        kGradientPanel10.setkStartColor(new java.awt.Color(255, 255, 255));
        kGradientPanel10.setMinimumSize(new java.awt.Dimension(1170, 99));
        kGradientPanel10.setPreferredSize(new java.awt.Dimension(1360, 110));
        kGradientPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        kGradientPanel10.add(btClearEfetuado, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 40, 100, -1));

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
        kGradientPanel10.add(btDefinirDataEfetuado, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 40, 130, 35));
        kGradientPanel10.add(jdcDataFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, 130, 28));
        kGradientPanel10.add(jdcDataInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 130, 28));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel3.setText("Periodo:");
        kGradientPanel10.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 90, -1));

        DespesasPagas.add(kGradientPanel10);

        kGradientPanel9.setBackground(new java.awt.Color(255, 255, 255));
        kGradientPanel9.setkBorderRadius(0);
        kGradientPanel9.setkEndColor(new java.awt.Color(234, 239, 243));
        kGradientPanel9.setkGradientFocus(0);
        kGradientPanel9.setkStartColor(new java.awt.Color(255, 255, 255));
        kGradientPanel9.setMinimumSize(new java.awt.Dimension(1170, 50));
        kGradientPanel9.setPreferredSize(new java.awt.Dimension(1360, 50));
        kGradientPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jbVisualizarConta.setBackground(new java.awt.Color(0, 153, 0));
        jbVisualizarConta.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbVisualizarConta.setForeground(new java.awt.Color(255, 255, 255));
        jbVisualizarConta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/excel.png"))); // NOI18N
        jbVisualizarConta.setText("EXPORTAR");
        jbVisualizarConta.setPreferredSize(new java.awt.Dimension(150, 30));
        jbVisualizarConta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbVisualizarContaActionPerformed(evt);
            }
        });
        kGradientPanel9.add(jbVisualizarConta, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 10, -1, -1));

        DespesasPagas.add(kGradientPanel9);

        jTabbedPanelDespesas.addTab("DESPESAS PAGAS", DespesasPagas);

        jPanelPrincipal.add(jTabbedPanelDespesas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 1360, 640));

        getContentPane().add(jPanelPrincipal, java.awt.BorderLayout.PAGE_START);

        getAccessibleContext().setAccessibleDescription("");
        getAccessibleContext().setAccessibleParent(this);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jbVisualizarPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbVisualizarPagarActionPerformed

    }//GEN-LAST:event_jbVisualizarPagarActionPerformed

    private void jbVisualizarPagar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbVisualizarPagar1ActionPerformed

    }//GEN-LAST:event_jbVisualizarPagar1ActionPerformed

    private void jpRevorgarContaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jpRevorgarContaActionPerformed
        modelContaAgentes = new ModelContaAgentes();
        try {
            int linha = tbDespesasRecebidas.getSelectedRow();
            int codigo = (int) tbDespesasRecebidas.getValueAt(linha, 0);
            Datas bl = new Datas();

            modelContaAgentes.setCodigo(codigo);
            modelContaAgentes.setSituacao(0);
            modelContaAgentes.setDataRecebimento(String.valueOf(bl.converterDataParaDateUS(new java.util.Date(System.currentTimeMillis()))));
            //pegunta
            int opcao = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja"
                    + " revogar o pagamento da Despesa nº:\n" + "\n " + codigo + "?", "Atenção", JOptionPane.YES_NO_OPTION);
            //se sim revoga, se não não faz nada
            if (opcao == JOptionPane.OK_OPTION) {
                if (controllerContaAgentes.pagarContaAgentesController(modelContaAgentes)) {
                    JOptionPane.showMessageDialog(this, "Despesa revogada!");
                }
            }
            carregarContasPagas();
            jTabbedPanelDespesas.setSelectedIndex(jTabbedPanelDespesas.getSelectedIndex() - 1);
            panelTabbedKGVerficiar();
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

    private void jbVisualizarContaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbVisualizarContaActionPerformed

        if (tbDespesasRecebidas.getRowCount() > 0) {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivos de excel", "xls");
            chooser.setFileFilter(filter);
            chooser.setDialogTitle("Salvar Arquivo");
            chooser.setAcceptAllFileFilterUsed(false);
            if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                List<JTable> tb = new ArrayList<JTable>();
                List<String> nom = new ArrayList<String>();
                tb.add(tbDespesasRecebidas);
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

    private void btClearEfetuadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btClearEfetuadoActionPerformed
        // TODO add your handling code here:
        DefaultTableModel tabela = (DefaultTableModel) this.tbDespesasRecebidas.getModel();
        this.tbDespesasRecebidas.setRowSorter(null);
        tfNegHrkmEfetuado.setText("R$ 0,00");
        tfAdiantamentosEfetuado.setText("R$ 0,00");
        tfAreceberEfetuado.setText("R$ 0,00");
        somaDespesasPagas();
        carregarContasPagas();
    }//GEN-LAST:event_btClearEfetuadoActionPerformed

    private void kButtonPendentesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButtonPendentesActionPerformed
        // TODO add your handling code here:
        jTabbedPanelDespesas.setSelectedIndex(0);
        panelTabbedKGVerficiar();
    }//GEN-LAST:event_kButtonPendentesActionPerformed

    private void kButtonProcessos2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButtonProcessos2ActionPerformed
        // TODO add your handling code here:
        carregarContasPagas();
        somaDespesasPagas();
    }//GEN-LAST:event_kButtonProcessos2ActionPerformed

    private void btDefinirDataEfetuadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDefinirDataEfetuadoActionPerformed
        // TODO add your handling code here:
        carregarContasPagas();
        somaDespesasPagas();
    }//GEN-LAST:event_btDefinirDataEfetuadoActionPerformed
    private void panelTabbedKGVerficiar() {

        if (jTabbedPanelDespesas.getSelectedIndex() == 0) {
            kButtonPendentes.setSelected(true);
        } else {
            kButtonPendentes.setSelected(false);
        }
    }

    private void carregarContasPagas() {
        Datas bl = new Datas();

        java.sql.Date dataInicial = null;
        java.sql.Date dataFinal = null;

        try {
            dataInicial = bl.converterDataParaDateUS(bl.converterDataParaDateUS(jdcDataInicial.getDate()));
            dataFinal = bl.converterDataParaDateUS(bl.converterDataParaDateUS(jdcDataFinal.getDate()));
        } catch (Exception ex) {
            /*Logger.getLogger(ViewOp.class.getName()).log(Level.SEVERE, null, ex);*/
        }

        //String dataComeco = String.valueOf(dataInicial);
        //String dataFinal = String.valueOf(dataFinal)
        DefaultTableModel modelo = (DefaultTableModel) tbDespesasRecebidas.getModel();
        int AgenteCod = controllerFuncionario.getFuncionarioController(ModelSessaoUsuario.nome).getCodigo();
        System.out.println("Agente Cod:");
        System.out.println(AgenteCod);
        System.out.println("Nome do Ususario sessao");
        System.out.println(ModelSessaoUsuario.nome);
        int pSituacao = 1;
        int pAgente = AgenteCod;
        controllerContaAgentes.FiltraContaAgentesFluxoDadosPorAgenteController(pAgente, dataInicial, dataFinal);
        listamModelContaAgentes = controllerContaAgentes.FiltraContaAgentesFluxoDadosPorAgenteController(pAgente, dataInicial, dataFinal);

        listaModelOp = controllerOp.getListaCompletaController();
        modelo.setNumRows(0);
        String pagoOun;
        //CARREGA OS DADOS DA LISTA NA TABELA
        int cont = listamModelContaAgentes.size();
        for (int i = 0; i < cont; i++) {
            modelContaAgentes = controllerContaAgentes.getContaAgentesController(listamModelContaAgentes.get(i).getCodigo());
            if (modelContaAgentes.isSituacao() > 0) {
                pagoOun = "PAGO";
            } else {
                pagoOun = "PENDENTE";
            }

            modelOp = controllerOp.getOpController(listamModelContaAgentes.get(i).getProcessos());
            modelFormaPagamento = controllerTipoPagamento.getFormaPagamentoController(modelOp.getTipo());
            modelSeguradora = controllerSeguradora.getSeguradoraController(modelOp.getSeguradoras());
            modelServicos = controllerServicos.getServicosController(modelOp.getServicosCodigo());
            modelFuncionario = controllerFuncionario.getFuncionarioController(listamModelContaAgentes.get(i).getCodigoPessoa());

            modelo.addRow(new Object[]{
                listamModelContaAgentes.get(i).getCodigo(),
                modelOp.getCodigo(),
                (new Mascaras().DataRecuperasql(listamModelContaAgentes.get(i).getDataEnvio())),
                modelSeguradora.getNome(),
                modelOp.getNumeroSinistro(),
                modelServicos.getNome(),
                (new Moeda().valorStringTODoubleAtt(String.valueOf(listamModelContaAgentes.get(i).getValorNeg()))),
                (new Moeda().valorStringTODoubleAtt(String.valueOf(listamModelContaAgentes.get(i).getValorHonorarioAgt()))),
                (new Moeda().valorStringTODoubleAtt(String.valueOf(listamModelContaAgentes.get(i).getAgenteKmPercorrido()))),
                (new Moeda().valorStringTODoubleAtt(String.valueOf(listamModelContaAgentes.get(i).getValorPGTOKMSeguradora()))),
                (new Moeda().valorStringTODoubleAtt(String.valueOf(listamModelContaAgentes.get(i).getValorPGTOtotalKM()))),
                (new Mascaras().DataRecuperasql(listamModelContaAgentes.get(i).getDataRecebimento())),
                listamModelContaAgentes.get(i).getDescricao(),
                (new Moeda().valorStringTODoubleAtt(String.valueOf(listamModelContaAgentes.get(i).getValorHRMaisKM()))),
                (new Moeda().valorStringTODoubleAtt(String.valueOf(listamModelContaAgentes.get(i).getValorAdiantamento()))),
                (new Moeda().valorStringTODoubleAtt(String.valueOf(listamModelContaAgentes.get(i).getValorAReceber()))),
                modelFuncionario.getNome(),
                pagoOun
            });
        }
    }
    Mascaras swMascaras = new Mascaras();

    private void somaDespesasPagas() {
        somakm();
        somaHonorario();
        somaGeral();
    }

    private void somakm() {
        float soma = 0;
        float valor = 0;
        int cont = tbDespesasRecebidas.getRowCount();
        for (int i = 0; i < cont; i++) {
            valor = Float.parseFloat(new Moeda().FommatoStringoSomarMil(String.valueOf(tbDespesasRecebidas.getValueAt(i, 14))));
            soma = valor + soma;
        }
        tfAdiantamentosEfetuado.setText(new Moeda().valorStringTODoubleAtt(String.valueOf(soma)));
    }

    private void somaHonorario() {
        float soma = 0;
        float valor = 0;
        int cont = tbDespesasRecebidas.getRowCount();
        for (int i = 0; i < cont; i++) {
            valor = Float.parseFloat(new Moeda().FommatoStringoSomarMil(String.valueOf(tbDespesasRecebidas.getValueAt(i, 13))));
            soma = valor + soma;
        }
        tfNegHrkmEfetuado.setText(new Moeda().valorStringTODoubleAtt(String.valueOf(soma)));
    }

    private void somaGeral() {
        float soma = 0;
        float valor = 0;
        int cont = tbDespesasRecebidas.getRowCount();
        for (int i = 0; i < cont; i++) {
            valor = Float.parseFloat(new Moeda().FommatoStringoSomarMil(String.valueOf(tbDespesasRecebidas.getValueAt(i, 15))));
            soma = valor + soma;
        }
        tfAreceberEfetuado.setText(new Moeda().valorStringTODoubleAtt(String.valueOf(soma)));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel DespesasPagas;
    private javax.swing.JButton btClearEfetuado;
    private javax.swing.JButton btDefinirDataEfetuado;
    private javax.swing.JComboBox cbCodAgenteDesignar;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanelPrincipal;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JTabbedPane jTabbedPanelDespesas;
    private javax.swing.JButton jbVisualizarConta;
    private com.toedter.calendar.JDateChooser jdcDataFinal;
    private com.toedter.calendar.JDateChooser jdcDataInicial;
    private javax.swing.JButton jpRevorgarConta;
    private keeptoo.KButton kButtonPendentes;
    private keeptoo.KButton kButtonProcessos2;
    private keeptoo.KGradientPanel kGradientPanel10;
    private keeptoo.KGradientPanel kGradientPanel11;
    private keeptoo.KGradientPanel kGradientPanel18;
    private keeptoo.KGradientPanel kGradientPanel8;
    private keeptoo.KGradientPanel kGradientPanel9;
    private javax.swing.JTable tbDespesasRecebidas;
    private javax.swing.JTextField tfAdiantamentosEfetuado;
    private javax.swing.JTextField tfAreceberEfetuado;
    private javax.swing.JTextField tfNegHrkmEfetuado;
    // End of variables declaration//GEN-END:variables
      /**
     *
     */
    public static ViewMyDespesasAgentes viewMydespesasagentes;

    ConexaoBanco cc = new ConexaoBanco();
    Connection cn = cc.conectar();

    private void SetIcone() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("ictrol.png")));
    }

}
