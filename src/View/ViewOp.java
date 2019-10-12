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
public final class ViewOp extends javax.swing.JFrame {

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
    ControllerCobertura controllerCobertura = new ControllerCobertura();
    ArrayList<ModelCobertura> listaModelCobertura = new ArrayList<>();
    ModelFormaPagamento modelFormaPagamento = new ModelFormaPagamento();

    public ViewOp() {
        initComponents();
        SetIcone();
        this.carregarProcessosReceber();
        this.carregarProcessosRecebidos();
        this.carregarProcessosAguardandoPagamento();
        kButtonProcessos.setSelected(true);
        jTabbedPanelDespesas.setSelectedIndex(0);
        listarSeguradoras();
    }
    static int codigo = 0;
    static String tipoCadastro = null;

    private void panelTabbedKGVerficiar() {
        if (jTabbedPanelDespesas.getSelectedIndex() == 0) {
            kButtonProcessos.setSelected(true);
        } else {
            kButtonProcessos.setSelected(false);
        }
        if (jTabbedPanelDespesas.getSelectedIndex() == 1) {
            kButtonAndamento.setSelected(true);
        } else {
            kButtonAndamento.setSelected(false);
        }
        if (jTabbedPanelDespesas.getSelectedIndex() == 2) {
            kButtonPendentes.setSelected(true);
        } else {
            kButtonPendentes.setSelected(false);
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
                new ViewOp().setVisible(true);
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

        kGradientPanel15 = new keeptoo.KGradientPanel();
        kButtonProcessos = new keeptoo.KButton();
        kButtonAndamento = new keeptoo.KButton();
        kButtonPendentes = new keeptoo.KButton();
        kButtonPendentes1 = new keeptoo.KButton();
        jTabbedPanelDespesas = new javax.swing.JTabbedPane();
        EmAberto = new javax.swing.JPanel();
        kGradientPanel7 = new keeptoo.KGradientPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbProcessosAbertos = new javax.swing.JTable();
        kGradientPanel6 = new keeptoo.KGradientPanel();
        btBuscarAbertos = new javax.swing.JButton();
        tfBuscarAbertos = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        btLimparAbertos = new javax.swing.JButton();
        btNovo = new javax.swing.JButton();
        btAlterar = new javax.swing.JButton();
        jbExcluirReceber = new javax.swing.JButton();
        cbBuscarAbertos = new javax.swing.JComboBox<String>();
        jLabel17 = new javax.swing.JLabel();
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
        jbReceberConta1 = new javax.swing.JButton();
        jpRevorgarConta1 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        cbBuscarAndamento = new javax.swing.JComboBox<String>();
        Concluidos = new javax.swing.JPanel();
        kGradientPanel8 = new keeptoo.KGradientPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tbProcessosConcluidos = new javax.swing.JTable();
        kGradientPanel10 = new keeptoo.KGradientPanel();
        btPesquisaConcluidos = new javax.swing.JButton();
        tfPesquisarConcluidos = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        btLimparConcluidos = new javax.swing.JButton();
        jpRevorgarConta = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        cbBuscarConcluidos = new javax.swing.JComboBox<String>();
        jLabel20 = new javax.swing.JLabel();
        cbSeguradoras = new javax.swing.JComboBox<String>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Processos");
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

        kButtonProcessos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Pinion.png"))); // NOI18N
        kButtonProcessos.setText("PROCESSOS EM ABERTO");
        kButtonProcessos.setFont(new java.awt.Font("Tahoma", 1, 9)); // NOI18N
        kButtonProcessos.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        kButtonProcessos.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        kButtonProcessos.setIconTextGap(10);
        kButtonProcessos.setkBackGroundColor(new java.awt.Color(255, 255, 255));
        kButtonProcessos.setkBorderRadius(0);
        kButtonProcessos.setkEndColor(new java.awt.Color(234, 239, 243));
        kButtonProcessos.setkForeGround(new java.awt.Color(51, 51, 51));
        kButtonProcessos.setkHoverEndColor(new java.awt.Color(234, 239, 243));
        kButtonProcessos.setkHoverForeGround(new java.awt.Color(51, 51, 51));
        kButtonProcessos.setkHoverStartColor(new java.awt.Color(234, 239, 243));
        kButtonProcessos.setkPressedColor(new java.awt.Color(153, 153, 153));
        kButtonProcessos.setkSelectedColor(new java.awt.Color(255, 255, 255));
        kButtonProcessos.setkStartColor(new java.awt.Color(197, 201, 206));
        kButtonProcessos.setPreferredSize(new java.awt.Dimension(220, 39));
        kButtonProcessos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButtonProcessosActionPerformed(evt);
            }
        });

        kButtonAndamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Dollar.png"))); // NOI18N
        kButtonAndamento.setText("PROCESSOS AGUARDANDO PAGAMENTO");
        kButtonAndamento.setFont(new java.awt.Font("Tahoma", 1, 9)); // NOI18N
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
        kButtonAndamento.setPreferredSize(new java.awt.Dimension(320, 39));
        kButtonAndamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButtonAndamentoActionPerformed(evt);
            }
        });

        kButtonPendentes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/OK.png"))); // NOI18N
        kButtonPendentes.setText("PROCESSOS CONCLUÍDOS");
        kButtonPendentes.setFont(new java.awt.Font("Tahoma", 1, 9)); // NOI18N
        kButtonPendentes.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        kButtonPendentes.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        kButtonPendentes.setIconTextGap(10);
        kButtonPendentes.setkBackGroundColor(new java.awt.Color(255, 255, 255));
        kButtonPendentes.setkBorderRadius(0);
        kButtonPendentes.setkEndColor(new java.awt.Color(197, 201, 206));
        kButtonPendentes.setkForeGround(new java.awt.Color(51, 51, 51));
        kButtonPendentes.setkHoverEndColor(new java.awt.Color(234, 239, 243));
        kButtonPendentes.setkHoverForeGround(new java.awt.Color(51, 51, 51));
        kButtonPendentes.setkHoverStartColor(new java.awt.Color(234, 239, 243));
        kButtonPendentes.setkPressedColor(new java.awt.Color(153, 153, 153));
        kButtonPendentes.setkSelectedColor(new java.awt.Color(255, 255, 255));
        kButtonPendentes.setkStartColor(new java.awt.Color(197, 201, 206));
        kButtonPendentes.setPreferredSize(new java.awt.Dimension(230, 39));
        kButtonPendentes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButtonPendentesActionPerformed(evt);
            }
        });

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

        org.jdesktop.layout.GroupLayout kGradientPanel15Layout = new org.jdesktop.layout.GroupLayout(kGradientPanel15);
        kGradientPanel15.setLayout(kGradientPanel15Layout);
        kGradientPanel15Layout.setHorizontalGroup(
            kGradientPanel15Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(kGradientPanel15Layout.createSequentialGroup()
                .add(5, 5, 5)
                .add(kButtonProcessos, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 179, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(kButtonAndamento, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 256, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(kButtonPendentes, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 186, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 377, Short.MAX_VALUE)
                .add(kButtonPendentes1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        kGradientPanel15Layout.setVerticalGroup(
            kGradientPanel15Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
            .add(kGradientPanel15Layout.createSequentialGroup()
                .add(2, 2, 2)
                .add(kGradientPanel15Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(kButtonPendentes1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(kGradientPanel15Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(kButtonPendentes, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 39, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(kButtonAndamento, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 39, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(kButtonProcessos, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 39, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
        );

        getContentPane().add(kGradientPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1085, -1));

        jTabbedPanelDespesas.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPanelDespesas.setFocusable(false);
        jTabbedPanelDespesas.setMaximumSize(new java.awt.Dimension(1084, 730));
        jTabbedPanelDespesas.setMinimumSize(new java.awt.Dimension(1084, 730));
        jTabbedPanelDespesas.setPreferredSize(new java.awt.Dimension(1084, 730));

        EmAberto.setBackground(new java.awt.Color(255, 255, 255));
        EmAberto.setMinimumSize(new java.awt.Dimension(1084, 575));
        EmAberto.setPreferredSize(new java.awt.Dimension(1084, 575));

        kGradientPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Processos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(51, 51, 51))); // NOI18N
        kGradientPanel7.setkBorderRadius(0);
        kGradientPanel7.setkEndColor(new java.awt.Color(234, 239, 243));
        kGradientPanel7.setkGradientFocus(0);
        kGradientPanel7.setkStartColor(new java.awt.Color(255, 255, 255));
        kGradientPanel7.setMinimumSize(new java.awt.Dimension(1040, 410));
        kGradientPanel7.setPreferredSize(new java.awt.Dimension(1080, 450));
        kGradientPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane6.setBackground(new java.awt.Color(247, 247, 247));
        jScrollPane6.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane6.setPreferredSize(new java.awt.Dimension(1220, 180));

        tbProcessosAbertos.setAutoCreateRowSorter(true);
        tbProcessosAbertos.setBackground(new java.awt.Color(247, 247, 247));
        tbProcessosAbertos.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        tbProcessosAbertos.setModel(new javax.swing.table.DefaultTableModel(
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
        tbProcessosAbertos.setGridColor(new java.awt.Color(247, 247, 247));
        tbProcessosAbertos.setMinimumSize(new java.awt.Dimension(610, 0));
        tbProcessosAbertos.setRowHeight(20);
        tbProcessosAbertos.setSelectionBackground(new java.awt.Color(0, 153, 0));
        tbProcessosAbertos.getTableHeader().setReorderingAllowed(false);
        jScrollPane6.setViewportView(tbProcessosAbertos);
        if (tbProcessosAbertos.getColumnModel().getColumnCount() > 0) {
            tbProcessosAbertos.getColumnModel().getColumn(0).setMinWidth(80);
            tbProcessosAbertos.getColumnModel().getColumn(0).setPreferredWidth(80);
            tbProcessosAbertos.getColumnModel().getColumn(0).setMaxWidth(80);
            tbProcessosAbertos.getColumnModel().getColumn(2).setMinWidth(150);
            tbProcessosAbertos.getColumnModel().getColumn(2).setPreferredWidth(150);
            tbProcessosAbertos.getColumnModel().getColumn(2).setMaxWidth(150);
            tbProcessosAbertos.getColumnModel().getColumn(3).setMinWidth(100);
            tbProcessosAbertos.getColumnModel().getColumn(3).setPreferredWidth(100);
            tbProcessosAbertos.getColumnModel().getColumn(3).setMaxWidth(100);
            tbProcessosAbertos.getColumnModel().getColumn(4).setMinWidth(120);
            tbProcessosAbertos.getColumnModel().getColumn(4).setPreferredWidth(120);
            tbProcessosAbertos.getColumnModel().getColumn(4).setMaxWidth(120);
            tbProcessosAbertos.getColumnModel().getColumn(5).setMinWidth(120);
            tbProcessosAbertos.getColumnModel().getColumn(5).setPreferredWidth(120);
            tbProcessosAbertos.getColumnModel().getColumn(5).setMaxWidth(120);
            tbProcessosAbertos.getColumnModel().getColumn(6).setMinWidth(150);
            tbProcessosAbertos.getColumnModel().getColumn(6).setPreferredWidth(150);
            tbProcessosAbertos.getColumnModel().getColumn(6).setMaxWidth(150);
        }

        kGradientPanel7.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 1010, 400));

        EmAberto.add(kGradientPanel7);

        kGradientPanel6.setkBorderRadius(0);
        kGradientPanel6.setkEndColor(new java.awt.Color(234, 239, 243));
        kGradientPanel6.setkGradientFocus(0);
        kGradientPanel6.setkStartColor(new java.awt.Color(255, 255, 255));
        kGradientPanel6.setPreferredSize(new java.awt.Dimension(1080, 100));
        kGradientPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btBuscarAbertos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/View.png"))); // NOI18N
        btBuscarAbertos.setPreferredSize(new java.awt.Dimension(50, 40));
        btBuscarAbertos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBuscarAbertosActionPerformed(evt);
            }
        });
        kGradientPanel6.add(btBuscarAbertos, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 20, 50, 40));

        tfBuscarAbertos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfBuscarAbertosKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfBuscarAbertosKeyReleased(evt);
            }
        });
        kGradientPanel6.add(tfBuscarAbertos, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 30, 110, 28));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(102, 102, 102));
        jLabel15.setText("DIGITE:");
        kGradientPanel6.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 10, 100, -1));

        btLimparAbertos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Erase.png"))); // NOI18N
        btLimparAbertos.setText("LIMPAR FILTRO");
        btLimparAbertos.setPreferredSize(new java.awt.Dimension(50, 40));
        btLimparAbertos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLimparAbertosActionPerformed(evt);
            }
        });
        kGradientPanel6.add(btLimparAbertos, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 20, 180, -1));

        btNovo.setBackground(new java.awt.Color(0, 153, 0));
        btNovo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btNovo.setForeground(new java.awt.Color(255, 255, 255));
        btNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Create.png"))); // NOI18N
        btNovo.setText("NOVO");
        btNovo.setPreferredSize(new java.awt.Dimension(150, 40));
        btNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNovoActionPerformed(evt);
            }
        });
        kGradientPanel6.add(btNovo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, -1, -1));

        btAlterar.setBackground(new java.awt.Color(0, 102, 255));
        btAlterar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btAlterar.setForeground(new java.awt.Color(255, 255, 255));
        btAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Modify.png"))); // NOI18N
        btAlterar.setText("Alterar");
        btAlterar.setPreferredSize(new java.awt.Dimension(120, 40));
        btAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAlterarActionPerformed(evt);
            }
        });
        kGradientPanel6.add(btAlterar, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, -1, -1));

        jbExcluirReceber.setBackground(new java.awt.Color(255, 0, 0));
        jbExcluirReceber.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbExcluirReceber.setForeground(new java.awt.Color(255, 255, 255));
        jbExcluirReceber.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/delete.png"))); // NOI18N
        jbExcluirReceber.setText("Excluir");
        jbExcluirReceber.setPreferredSize(new java.awt.Dimension(110, 35));
        jbExcluirReceber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbExcluirReceberActionPerformed(evt);
            }
        });
        kGradientPanel6.add(jbExcluirReceber, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 20, -1, -1));

        cbBuscarAbertos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Código", "Sinistro", "Segurado", "Analista", "Endereço" }));
        cbBuscarAbertos.setPreferredSize(new java.awt.Dimension(120, 28));
        kGradientPanel6.add(cbBuscarAbertos, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 30, -1, 28));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(102, 102, 102));
        jLabel17.setText("BUSCAR POR:");
        kGradientPanel6.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 10, 100, -1));

        EmAberto.add(kGradientPanel6);

        jTabbedPanelDespesas.addTab("PROCESSOS EM ABERTO", EmAberto);

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
            tbProcessosAndamento.getColumnModel().getColumn(2).setMinWidth(150);
            tbProcessosAndamento.getColumnModel().getColumn(2).setPreferredWidth(150);
            tbProcessosAndamento.getColumnModel().getColumn(2).setMaxWidth(150);
            tbProcessosAndamento.getColumnModel().getColumn(3).setMinWidth(100);
            tbProcessosAndamento.getColumnModel().getColumn(3).setPreferredWidth(100);
            tbProcessosAndamento.getColumnModel().getColumn(3).setMaxWidth(100);
            tbProcessosAndamento.getColumnModel().getColumn(4).setMinWidth(120);
            tbProcessosAndamento.getColumnModel().getColumn(4).setPreferredWidth(120);
            tbProcessosAndamento.getColumnModel().getColumn(4).setMaxWidth(120);
            tbProcessosAndamento.getColumnModel().getColumn(5).setMinWidth(120);
            tbProcessosAndamento.getColumnModel().getColumn(5).setPreferredWidth(120);
            tbProcessosAndamento.getColumnModel().getColumn(5).setMaxWidth(120);
            tbProcessosAndamento.getColumnModel().getColumn(6).setMinWidth(150);
            tbProcessosAndamento.getColumnModel().getColumn(6).setPreferredWidth(150);
            tbProcessosAndamento.getColumnModel().getColumn(6).setMaxWidth(150);
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
        btAlterar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Modify.png"))); // NOI18N
        btAlterar1.setText("EDITAR PGTO.");
        btAlterar1.setPreferredSize(new java.awt.Dimension(150, 40));
        btAlterar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAlterar1ActionPerformed(evt);
            }
        });
        kGradientPanel11.add(btAlterar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 140, -1));

        jbReceberConta1.setBackground(new java.awt.Color(0, 102, 0));
        jbReceberConta1.setFont(new java.awt.Font("Tahoma", 1, 9)); // NOI18N
        jbReceberConta1.setForeground(new java.awt.Color(255, 255, 255));
        jbReceberConta1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Apply.png"))); // NOI18N
        jbReceberConta1.setText("CONCLUÍR PROCESSO");
        jbReceberConta1.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jbReceberConta1.setPreferredSize(new java.awt.Dimension(150, 40));
        jbReceberConta1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbReceberConta1ActionPerformed(evt);
            }
        });
        kGradientPanel11.add(jbReceberConta1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, 170, -1));

        jpRevorgarConta1.setBackground(new java.awt.Color(255, 102, 0));
        jpRevorgarConta1.setFont(new java.awt.Font("Tahoma", 1, 9)); // NOI18N
        jpRevorgarConta1.setForeground(new java.awt.Color(255, 255, 255));
        jpRevorgarConta1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Back.png"))); // NOI18N
        jpRevorgarConta1.setText("ANDAMENTO");
        jpRevorgarConta1.setPreferredSize(new java.awt.Dimension(100, 30));
        jpRevorgarConta1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jpRevorgarConta1ActionPerformed(evt);
            }
        });
        kGradientPanel11.add(jpRevorgarConta1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 20, 130, -1));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(102, 102, 102));
        jLabel18.setText("BUSCAR POR:");
        kGradientPanel11.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 10, 100, -1));

        cbBuscarAndamento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Código", "Sinistro", "Segurado", "Analista", "Endereço" }));
        cbBuscarAndamento.setPreferredSize(new java.awt.Dimension(120, 28));
        kGradientPanel11.add(cbBuscarAndamento, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 30, -1, 28));

        AguardandoPagamento.add(kGradientPanel11);

        jTabbedPanelDespesas.addTab("PROCESSOS AGUARDANDO PAGAMENTO", AguardandoPagamento);

        Concluidos.setBackground(new java.awt.Color(255, 255, 255));
        Concluidos.setMinimumSize(new java.awt.Dimension(1084, 575));
        Concluidos.setPreferredSize(new java.awt.Dimension(1084, 575));

        kGradientPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Processos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(51, 51, 51))); // NOI18N
        kGradientPanel8.setkBorderRadius(0);
        kGradientPanel8.setkEndColor(new java.awt.Color(234, 239, 243));
        kGradientPanel8.setkGradientFocus(0);
        kGradientPanel8.setkStartColor(new java.awt.Color(255, 255, 255));
        kGradientPanel8.setMinimumSize(new java.awt.Dimension(1040, 410));
        kGradientPanel8.setPreferredSize(new java.awt.Dimension(1080, 450));
        kGradientPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane8.setBackground(new java.awt.Color(247, 247, 247));
        jScrollPane8.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane8.setPreferredSize(new java.awt.Dimension(1220, 180));

        tbProcessosConcluidos.setBackground(new java.awt.Color(247, 247, 247));
        tbProcessosConcluidos.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        tbProcessosConcluidos.setModel(new javax.swing.table.DefaultTableModel(
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
        tbProcessosConcluidos.setGridColor(new java.awt.Color(247, 247, 247));
        tbProcessosConcluidos.setMinimumSize(new java.awt.Dimension(610, 0));
        tbProcessosConcluidos.setRowHeight(20);
        tbProcessosConcluidos.setSelectionBackground(new java.awt.Color(0, 153, 0));
        tbProcessosConcluidos.getTableHeader().setReorderingAllowed(false);
        jScrollPane8.setViewportView(tbProcessosConcluidos);
        if (tbProcessosConcluidos.getColumnModel().getColumnCount() > 0) {
            tbProcessosConcluidos.getColumnModel().getColumn(0).setMinWidth(80);
            tbProcessosConcluidos.getColumnModel().getColumn(0).setPreferredWidth(80);
            tbProcessosConcluidos.getColumnModel().getColumn(0).setMaxWidth(80);
            tbProcessosConcluidos.getColumnModel().getColumn(2).setMinWidth(150);
            tbProcessosConcluidos.getColumnModel().getColumn(2).setPreferredWidth(150);
            tbProcessosConcluidos.getColumnModel().getColumn(2).setMaxWidth(150);
            tbProcessosConcluidos.getColumnModel().getColumn(3).setMinWidth(100);
            tbProcessosConcluidos.getColumnModel().getColumn(3).setPreferredWidth(100);
            tbProcessosConcluidos.getColumnModel().getColumn(3).setMaxWidth(100);
            tbProcessosConcluidos.getColumnModel().getColumn(4).setMinWidth(120);
            tbProcessosConcluidos.getColumnModel().getColumn(4).setPreferredWidth(120);
            tbProcessosConcluidos.getColumnModel().getColumn(4).setMaxWidth(120);
            tbProcessosConcluidos.getColumnModel().getColumn(5).setMinWidth(120);
            tbProcessosConcluidos.getColumnModel().getColumn(5).setPreferredWidth(120);
            tbProcessosConcluidos.getColumnModel().getColumn(5).setMaxWidth(120);
            tbProcessosConcluidos.getColumnModel().getColumn(6).setMinWidth(150);
            tbProcessosConcluidos.getColumnModel().getColumn(6).setPreferredWidth(150);
            tbProcessosConcluidos.getColumnModel().getColumn(6).setMaxWidth(150);
        }

        kGradientPanel8.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 1010, 400));

        Concluidos.add(kGradientPanel8);

        kGradientPanel10.setkBorderRadius(0);
        kGradientPanel10.setkEndColor(new java.awt.Color(234, 239, 243));
        kGradientPanel10.setkGradientFocus(0);
        kGradientPanel10.setkStartColor(new java.awt.Color(255, 255, 255));
        kGradientPanel10.setPreferredSize(new java.awt.Dimension(1080, 110));
        kGradientPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btPesquisaConcluidos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Find.png"))); // NOI18N
        btPesquisaConcluidos.setPreferredSize(new java.awt.Dimension(50, 40));
        btPesquisaConcluidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisaConcluidosActionPerformed(evt);
            }
        });
        kGradientPanel10.add(btPesquisaConcluidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 20, 50, 40));

        tfPesquisarConcluidos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfPesquisarConcluidosKeyPressed(evt);
            }
        });
        kGradientPanel10.add(tfPesquisarConcluidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 30, 110, 28));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(102, 102, 102));
        jLabel16.setText("BUSCAR:");
        kGradientPanel10.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 10, -1, -1));

        btLimparConcluidos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Erase.png"))); // NOI18N
        btLimparConcluidos.setText("LIMPAR FILTRO");
        btLimparConcluidos.setPreferredSize(new java.awt.Dimension(50, 40));
        btLimparConcluidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLimparConcluidosActionPerformed(evt);
            }
        });
        kGradientPanel10.add(btLimparConcluidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 20, 180, -1));

        jpRevorgarConta.setBackground(new java.awt.Color(255, 0, 0));
        jpRevorgarConta.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jpRevorgarConta.setForeground(new java.awt.Color(255, 255, 255));
        jpRevorgarConta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/24x24/Back.png"))); // NOI18N
        jpRevorgarConta.setText("REABRIR PROCESSO");
        jpRevorgarConta.setPreferredSize(new java.awt.Dimension(200, 40));
        jpRevorgarConta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jpRevorgarContaActionPerformed(evt);
            }
        });
        kGradientPanel10.add(jpRevorgarConta, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, -1, -1));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(102, 102, 102));
        jLabel19.setText("BUSCAR POR:");
        kGradientPanel10.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 10, 100, -1));

        cbBuscarConcluidos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Código", "Sinistro", "Segurado", "Analista", "Endereço" }));
        cbBuscarConcluidos.setPreferredSize(new java.awt.Dimension(120, 28));
        kGradientPanel10.add(cbBuscarConcluidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 30, -1, 28));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(102, 102, 102));
        jLabel20.setText("SEGURADORA:");
        kGradientPanel10.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, -1, -1));

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
        kGradientPanel10.add(cbSeguradoras, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 30, 240, 30));

        Concluidos.add(kGradientPanel10);

        jTabbedPanelDespesas.addTab("PROCESSOS FINALIZADOS", Concluidos);

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

    private void jbExcluirReceberActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jbExcluirReceberActionPerformed
        //recebe a linha selecionada
        int linha = this.tbProcessosAbertos.getSelectedRow();
        //pega o codigo na linha selecionada
        int pCodigo = (Integer) tbProcessosAbertos.getValueAt(linha, 0);
        //pegunta se realmente deseja excluir
        int opcao = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja"
                + " transformar o pedido nº:" + pCodigo + " em um orçamento ?", "Atenção", JOptionPane.YES_NO_OPTION);
        //se sim exclui, se não não faz nada    
        if (opcao == JOptionPane.OK_OPTION) {
            //desativa pedido
            controllerOp.desaprovarOpController(pCodigo);
            //carregar lista de v e o
            this.carregarProcessosReceber();
        } else {
            JOptionPane.showMessageDialog(this, "Você cancelou a operação!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jbExcluirReceberActionPerformed

    private void btAlterarActionPerformed(ActionEvent evt) {//GEN-FIRST:event_btAlterarActionPerformed
        try {
            int linha = tbProcessosAbertos.getSelectedRow();
            tipoCadastro = "alteracao";
            String nome = (String) tbProcessosAbertos.getValueAt(linha, 1);
            codigo = (Integer) tbProcessosAbertos.getValueAt(linha, 0);
            //System.out.println("codigo; " + codigo);
            modelOp = controllerOp.getOpController(codigo);
            new ViewCadOp(modelOp).setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Você selecionar uma Despesa para alterar!", "ATENÇÂO", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btAlterarActionPerformed

    private void btNovoActionPerformed(ActionEvent evt) {//GEN-FIRST:event_btNovoActionPerformed
        // TODO add your handling code here:
        tipoCadastro = "novo";
        new ViewCadOp().setVisible(true);
    }//GEN-LAST:event_btNovoActionPerformed

    private void jpRevorgarContaActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jpRevorgarContaActionPerformed
        modelOp = new ModelOp();
        try {
            int linha = tbProcessosConcluidos.getSelectedRow();
            int ViewOpcodigo = (int) tbProcessosConcluidos.getValueAt(linha, 0);
            Datas bl = new Datas();

            modelOp.setCodigo(ViewOpcodigo);
            modelOp.setTipo(5);
            modelOp.setTipoPagamento(5);

            //pegunta
            int opcao = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja"
                    + " reabrir o processo nº:\n" + "\n " + ViewOpcodigo + "?", "Atenção", JOptionPane.YES_NO_OPTION);
            //se sim revoga, se não não faz nada
            if (opcao == JOptionPane.OK_OPTION) {
                if (controllerOp.pagarReceberProcessoController(modelOp)) {
                    JOptionPane.showMessageDialog(this, "Processo Reaberto!");
                    carregarProcessosReceber();
                    carregarProcessosRecebidos();
                    carregarProcessosAguardandoPagamento();
                }
            }

            jTabbedPanelDespesas.setSelectedIndex(jTabbedPanelDespesas.getSelectedIndex() - 1);
            panelTabbedKGVerficiar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Você deve selecionar um processo!", "ATENÇÂO", JOptionPane.WARNING_MESSAGE);
        }
        panelTabbedKGVerficiar();
    }//GEN-LAST:event_jpRevorgarContaActionPerformed

    private void cbTipoDespesaPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbTipoDespesaPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
    }//GEN-LAST:event_cbTipoDespesaPopupMenuWillBecomeInvisible

    private void jbReceberConta1ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jbReceberConta1ActionPerformed
        // TODO add your handling code here:
        modelOp = new ModelOp();
        try {
            int linha = tbProcessosAndamento.getSelectedRow();
            int codigo = (int) tbProcessosAndamento.getValueAt(linha, 0);
            Datas bl = new Datas();

            modelOp.setCodigo(codigo);
            modelOp.setTipo(3);
            modelOp.setTipoPagamento(3);
            //pegunta
            int opcao = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja"
                    + " finalizar o processo nº:\n" + "\n " + codigo + "?", "Atenção", JOptionPane.YES_NO_OPTION);
            carregarProcessosAguardandoPagamento();
            //se sim paga, se não não faz nada
            if (opcao == JOptionPane.OK_OPTION) {
                if (controllerOp.pagarReceberProcessoController(modelOp)) {
                    JOptionPane.showMessageDialog(this, "PROCESSO DEFINIDO COMO CONCLUÍDO!");
                    this.carregarProcessosReceber();
                    this.carregarProcessosRecebidos();
                    carregarProcessosAguardandoPagamento();
                }
            }

            jTabbedPanelDespesas.setSelectedIndex(jTabbedPanelDespesas.getSelectedIndex() + 1);
            panelTabbedKGVerficiar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "SELECIONE UM PROCESSO PRIMEIRO!", "ATENÇÂO", JOptionPane.WARNING_MESSAGE);
            carregarProcessosAguardandoPagamento();
        }
    }//GEN-LAST:event_jbReceberConta1ActionPerformed

    private void btAlterar1ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_btAlterar1ActionPerformed
        // TODO add your handling code here:
        try {
            int linha = tbProcessosAndamento.getSelectedRow();
            tipoCadastro = "alteracao";
            String nome = (String) tbProcessosAndamento.getValueAt(linha, 1);
            codigo = (Integer) tbProcessosAndamento.getValueAt(linha, 0);
            //System.out.println("codigo; " + codigo);
            modelOp = controllerOp.getOpController(codigo);
            new ViewCadOp(modelOp).setVisible(true);
            carregarProcessosAguardandoPagamento();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Você selecionar uma Despesa para alterar!", "ATENÇÂO", JOptionPane.WARNING_MESSAGE);
            carregarProcessosAguardandoPagamento();
        }
    }//GEN-LAST:event_btAlterar1ActionPerformed

    private void kButtonProcessosActionPerformed(ActionEvent evt) {//GEN-FIRST:event_kButtonProcessosActionPerformed
        // TODO add your handling code here:
        carregarProcessosReceber();
        jTabbedPanelDespesas.setSelectedIndex(0);
        panelTabbedKGVerficiar();
    }//GEN-LAST:event_kButtonProcessosActionPerformed

    private void kButtonAndamentoActionPerformed(ActionEvent evt) {//GEN-FIRST:event_kButtonAndamentoActionPerformed
        // TODO add your handling code here:
        carregarProcessosAguardandoPagamento();
        jTabbedPanelDespesas.setSelectedIndex(1);
        panelTabbedKGVerficiar();
    }//GEN-LAST:event_kButtonAndamentoActionPerformed

    private void kButtonPendentesActionPerformed(ActionEvent evt) {//GEN-FIRST:event_kButtonPendentesActionPerformed
        // TODO add your handling code here:
        carregarProcessosRecebidos();
        jTabbedPanelDespesas.setSelectedIndex(2);
        panelTabbedKGVerficiar();
    }//GEN-LAST:event_kButtonPendentesActionPerformed

    private void kButtonPendentes1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButtonPendentes1ActionPerformed
        // TODO add your handling code here:
        if (jTabbedPanelDespesas.getSelectedIndex() == 0) {
            panelTabbedKGVerficiar();
            carregarProcessosReceber();
        } else if (jTabbedPanelDespesas.getSelectedIndex() == 1) {
            panelTabbedKGVerficiar();
            carregarProcessosAguardandoPagamento();
        } else if (jTabbedPanelDespesas.getSelectedIndex() == 2) {
            panelTabbedKGVerficiar();
            carregarProcessosRecebidos();
        }
    }//GEN-LAST:event_kButtonPendentes1ActionPerformed

    private void btBuscarAndamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBuscarAndamentoActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        int situacao = 5;
        String seleciona = "";
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
            listaModelOp = controllerOp.getListaOpControllerBusca(seleciona, conteudo, situacao);
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
                lista.get(i).getCodigo(),
                Seguradora,
                CNPJ,
                (new Mascaras().DataRecuperasql(lista.get(i).getDataEntrada())),
                lista.get(i).getNumeroSinistro(),
                (new Moeda().valorStringTODoubleAtt(lista.get(i).getValorTotalHonorariosSemRetencao().toString())),
                Situacao
            });
        }
    }

    private void btLimparAndamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLimparAndamentoActionPerformed
        // TODO add your handling code here:
        this.tbProcessosAndamento.setRowSorter(null);
        tfPesquisarAndamento.setText("");
        this.carregarProcessosAguardandoPagamento();

    }//GEN-LAST:event_btLimparAndamentoActionPerformed

    private void btBuscarAbertosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBuscarAbertosActionPerformed
        // TODO add your handling code here:
        String seleciona = "";
        String conteudo = tfBuscarAbertos.getText();
        int i = cbBuscarAbertos.getSelectedIndex();
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
            listaModelOp = controllerOp.getListaOpControllerAbertos(seleciona, conteudo);
            pesquisaAbertos(listaModelOp);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Nenhum processo encontrado!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btBuscarAbertosActionPerformed

    private void pesquisaAbertos(ArrayList<ModelOp> lista) {
        DefaultTableModel modelo = (DefaultTableModel) tbProcessosAbertos.getModel();
        modelo.setNumRows(0);

        //CARREGA OS DADOS DA LISTA NA TABELA
        int cont = lista.size();
        for (int i = 0; i < cont; i++) {
            String Seguradora = controllerSeguradora.getSeguradoraController(lista.get(i).getSeguradoras()).getNome();
            String CNPJ = controllerSeguradora.getSeguradoraController(lista.get(i).getSeguradoras()).getCnpj();
            String Situacao = controllerTipoPagamento.getFormaPagamentoController(lista.get(i).getTipo()).getDescricao();
            modelo.addRow(new Object[]{
                lista.get(i).getCodigo(),
                Seguradora,
                CNPJ,
                (new Mascaras().DataRecuperasql(lista.get(i).getDataEntrada())),
                lista.get(i).getNumeroSinistro(),
                (new Moeda().valorStringTODoubleAtt(lista.get(i).getValorTotalHonorariosSemRetencao().toString())),
                Situacao,});
        }
    }

    private void btLimparAbertosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLimparAbertosActionPerformed
        // TODO add your handling code here:
        this.tbProcessosAbertos.setRowSorter(null);
        tfBuscarAbertos.setText("");
        this.carregarProcessosReceber();

    }//GEN-LAST:event_btLimparAbertosActionPerformed

    private void btPesquisaConcluidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisaConcluidosActionPerformed
        // TODO add your handling code here:
        int situacao = 3;
        String seleciona = "";
        String conteudo = tfPesquisarConcluidos.getText();
        int i = cbBuscarConcluidos.getSelectedIndex();
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
            listaModelOp = controllerOp.getListaOpPesquisaConsultasController(seleciona, conteudo, situacao);
            pesquisa(listaModelOp);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Nenhum processo encontrado!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btPesquisaConcluidosActionPerformed

    private void pesquisa(ArrayList<ModelOp> lista) {
        DefaultTableModel modelo = (DefaultTableModel) tbProcessosConcluidos.getModel();
        modelo.setNumRows(0);

        //CARREGA OS DADOS DA LISTA NA TABELA
        int cont = lista.size();
        for (int i = 0; i < cont; i++) {
            String Seguradora = controllerSeguradora.getSeguradoraController(lista.get(i).getSeguradoras()).getNome();
            String CNPJ = controllerSeguradora.getSeguradoraController(lista.get(i).getSeguradoras()).getCnpj();
            String Situacao = controllerTipoPagamento.getFormaPagamentoController(lista.get(i).getTipo()).getDescricao();
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
    private void btLimparConcluidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLimparConcluidosActionPerformed
        // TODO add your handling code here:
        this.tbProcessosConcluidos.setRowSorter(null);
        tfPesquisarConcluidos.setText("");
        this.carregarProcessosRecebidos();

    }//GEN-LAST:event_btLimparConcluidosActionPerformed

    private void jpRevorgarConta1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jpRevorgarConta1ActionPerformed
        // TODO add your handling code here:
        modelOp = new ModelOp();
        try {
            int linha = tbProcessosConcluidos.getSelectedRow();
            int ViewOpcodigo = (int) tbProcessosConcluidos.getValueAt(linha, 0);
            Datas bl = new Datas();

            modelOp.setCodigo(ViewOpcodigo);
            modelOp.setTipo(4);
            modelOp.setTipoPagamento(4);

            //pegunta
            int opcao = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja"
                    + " voltar para ANDAMENTO o porcesso de nº:\n" + "\n " + ViewOpcodigo + "?", "Atenção", JOptionPane.YES_NO_OPTION);
            //se sim revoga, se não não faz nada
            if (opcao == JOptionPane.OK_OPTION) {
                if (controllerOp.pagarReceberProcessoController(modelOp)) {
                    JOptionPane.showMessageDialog(this, "Processo Reaberto!");
                    carregarProcessosReceber();
                    carregarProcessosRecebidos();
                    carregarProcessosAguardandoPagamento();
                }
            }

            jTabbedPanelDespesas.setSelectedIndex(jTabbedPanelDespesas.getSelectedIndex() - 1);
            panelTabbedKGVerficiar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Você deve selecionar um processo!", "ATENÇÂO", JOptionPane.WARNING_MESSAGE);
        }
        panelTabbedKGVerficiar();
    }//GEN-LAST:event_jpRevorgarConta1ActionPerformed

    private void tfBuscarAbertosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfBuscarAbertosKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == evt.VK_ENTER) {
            String seleciona = "";
            String conteudo = tfBuscarAbertos.getText();
            int i = cbBuscarAbertos.getSelectedIndex();
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
                listaModelOp = controllerOp.getListaOpControllerAbertos(seleciona, conteudo);
                pesquisaAbertos(listaModelOp);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Nenhum processo encontrado!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
        }

    }//GEN-LAST:event_tfBuscarAbertosKeyPressed

    private void tfPesquisarAndamentoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPesquisarAndamentoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == evt.VK_ENTER) {
            int situacao = 5;
            String seleciona = "";
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
                listaModelOp = controllerOp.getListaOpControllerBusca(seleciona, conteudo, situacao);
                pesquisaAndamento(listaModelOp);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Nenhum processo encontrado!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_tfPesquisarAndamentoKeyPressed

    private void tfPesquisarConcluidosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPesquisarConcluidosKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == evt.VK_ENTER) {
            int situacao = 3;
            String seleciona = "";
            String conteudo = tfPesquisarConcluidos.getText();
            int i = cbBuscarConcluidos.getSelectedIndex();
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
                listaModelOp = controllerOp.getListaOpControllerBusca(seleciona, conteudo, situacao);
                pesquisa(listaModelOp);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Nenhum processo encontrado!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_tfPesquisarConcluidosKeyPressed

    private void tfBuscarAbertosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfBuscarAbertosKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tfBuscarAbertosKeyReleased

    private void cbSeguradorasPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbSeguradorasPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
        if (this.cbSeguradoras.isPopupVisible() && (this.cbSeguradoras.getSelectedItem() != null) && !(this.cbSeguradoras.getSelectedItem().equals(""))) {
            modelSeguradora = controllerSeguradora.getSeguradoraController(cbSeguradoras.getSelectedItem().toString());
            DefaultTableModel tabela = (DefaultTableModel) this.tbProcessosConcluidos.getModel();
            final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tabela);
            this.tbProcessosConcluidos.setRowSorter(sorter);
            String text = cbSeguradoras.getSelectedItem().toString();
            sorter.setRowFilter(RowFilter.regexFilter(text, 1));
        } else {
            DefaultTableModel tabela = (DefaultTableModel) this.tbProcessosConcluidos.getModel();
            this.tbProcessosConcluidos.setRowSorter(null);
        }
    }//GEN-LAST:event_cbSeguradorasPopupMenuWillBecomeInvisible

    /**
     * Excluir dados do banco
     *
     * @return
     */
    private boolean excluirConta() {
        int linha = tbProcessosAbertos.getSelectedRow();
        String nome = (String) tbProcessosAbertos.getValueAt(linha, 1);
        int ViewOpcodigo = (int) tbProcessosAbertos.getValueAt(linha, 0);

        //pegunta se realmente deseja excluir
        int opcao = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja"
                + " excluir o registro:\n" + "\n " + nome + "?", "Atenção", JOptionPane.YES_NO_OPTION);
        //se sim exclui, se não não faz nada
        if (opcao == JOptionPane.OK_OPTION) {
            if (controllerOp.excluirOpController(ViewOpcodigo)) {
                JOptionPane.showMessageDialog(this, "Registro excluido com suscesso!");
                carregarProcessosReceber();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao e os dados!", "ERRO", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return true;
    }

    public void carregarProcessosReceber() {
        listaModelOp = controllerOp.FiltraOpDecrescenteController();
        DefaultTableModel modelo = (DefaultTableModel) tbProcessosAbertos.getModel();
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

    void carregarProcessosRecebidos() {
        listaModelOp = controllerOp.getProcessosRecebidosController(3);
        DefaultTableModel modelo = (DefaultTableModel) tbProcessosConcluidos.getModel();
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

    void carregarProcessosAguardandoPagamento() {
        listaModelOp = controllerOp.getProcessosRecebidosController(5);
        DefaultTableModel modelo = (DefaultTableModel) tbProcessosAndamento.getModel();
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

    private void pesquisaRecebidos(ArrayList<ModelOp> lista) {
        DefaultTableModel modelo = (DefaultTableModel) tbProcessosAndamento.getModel();
        modelo.setNumRows(0);

        //CARREGA OS DADOS DA LISTA NA TABELA
        int cont = lista.size();
        for (int i = 0; i < cont; i++) {
            modelSeguradora = controllerSeguradora.getSeguradoraController(listaModelOp.get(i).getSeguradoras());
            modelo.addRow(new Object[]{
                lista.get(i).getCodigo(),
                lista.get(i).getSeguradoras(),
                modelSeguradora.getNome(),
                modelSeguradora.getCnpj(),
                lista.get(i).getDataEntrada(),
                lista.get(i).getNumeroSinistro(),
                lista.get(i).getValorTotalHonorariosSemRetencao(),});
        }
    }

    private void pesquisaReceber(ArrayList<ModelOp> lista) {
        DefaultTableModel modelo = (DefaultTableModel) tbProcessosAbertos.getModel();
        modelo.setNumRows(0);

        //CARREGA OS DADOS DA LISTA NA TABELA
        int cont = lista.size();
        for (int i = 0; i < cont; i++) {
            modelSeguradora = controllerSeguradora.getSeguradoraController(listaModelOp.get(i).getSeguradoras());
            modelo.addRow(new Object[]{
                lista.get(i).getCodigo(),
                lista.get(i).getSeguradoras(),
                modelSeguradora.getNome(),
                modelSeguradora.getCnpj(),
                lista.get(i).getDataEntrada(),
                lista.get(i).getNumeroSinistro(),
                lista.get(i).getValorTotalHonorariosSemRetencao(),});
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AguardandoPagamento;
    private javax.swing.JPanel Concluidos;
    private javax.swing.JPanel EmAberto;
    private javax.swing.JButton btAlterar;
    private javax.swing.JButton btAlterar1;
    private javax.swing.JButton btBuscarAbertos;
    private javax.swing.JButton btBuscarAndamento;
    private javax.swing.JButton btLimparAbertos;
    private javax.swing.JButton btLimparAndamento;
    private javax.swing.JButton btLimparConcluidos;
    private javax.swing.JButton btNovo;
    private javax.swing.JButton btPesquisaConcluidos;
    private javax.swing.JComboBox<String> cbBuscarAbertos;
    private javax.swing.JComboBox<String> cbBuscarAndamento;
    private javax.swing.JComboBox<String> cbBuscarConcluidos;
    private javax.swing.JComboBox<String> cbSeguradoras;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTabbedPane jTabbedPanelDespesas;
    private javax.swing.JButton jbExcluirReceber;
    private javax.swing.JButton jbReceberConta1;
    private javax.swing.JButton jpRevorgarConta;
    private javax.swing.JButton jpRevorgarConta1;
    private keeptoo.KButton kButtonAndamento;
    private keeptoo.KButton kButtonPendentes;
    private keeptoo.KButton kButtonPendentes1;
    private keeptoo.KButton kButtonProcessos;
    private keeptoo.KGradientPanel kGradientPanel10;
    private keeptoo.KGradientPanel kGradientPanel11;
    private keeptoo.KGradientPanel kGradientPanel12;
    private keeptoo.KGradientPanel kGradientPanel15;
    private keeptoo.KGradientPanel kGradientPanel6;
    private keeptoo.KGradientPanel kGradientPanel7;
    private keeptoo.KGradientPanel kGradientPanel8;
    private javax.swing.JTable tbProcessosAbertos;
    private javax.swing.JTable tbProcessosAndamento;
    private javax.swing.JTable tbProcessosConcluidos;
    private javax.swing.JTextField tfBuscarAbertos;
    private javax.swing.JTextField tfPesquisarAndamento;
    private javax.swing.JTextField tfPesquisarConcluidos;
    // End of variables declaration//GEN-END:variables

    private void listarSeguradoras() {
        listaModelSeguradora = controllerSeguradora.getListaSeguradoraController();
        //  cbSeguradoras.removeAllItems();
        for (int i = 0; i < listaModelSeguradora.size(); i++) {
            cbSeguradoras.addItem(listaModelSeguradora.get(i).getNome());
        }
        //   cbSeguradoras.setSelectedIndex(-1);
    }
    public static ViewOp op;
    ConexaoBanco cc = new ConexaoBanco();
    Connection cn = cc.conectar();

    private void SetIcone() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("ictrol.png")));
    }

}
