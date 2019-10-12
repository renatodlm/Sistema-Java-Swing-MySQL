/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ControllerFormaPagamento;
import Controller.ControllerSeguradora;
import Controller.ControllerOp;
import Controller.ControllerUsuario;
import Model.ModelFormaPagamento;
import Model.ModelOp;
import Model.ModelSeguradora;
import Model.ModelSessaoUsuario;
import Model.ModelUsuario;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static View.ViewOp.op;
import conexao.ConexaoBanco;
import java.awt.Toolkit;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import javax.swing.table.DefaultTableModel;
import util.Moeda;
import util.Datas;
import util.Mascaras;

/**
 *
 * @author studiomicroweb
 */
public final class ViewPrincipal extends javax.swing.JFrame {

    ControllerOp controllerOp = new ControllerOp();
    ArrayList<ModelOp> listaModelOpAlterar = new ArrayList<>();
    ModelOp modelOp = new ModelOp();
    ArrayList<ModelOp> listaModelOp = new ArrayList<>();

    ArrayList<ModelSeguradora> listaModelSeguradora = new ArrayList<>();
    ControllerSeguradora controllerSeguradora = new ControllerSeguradora();
    ModelSeguradora modelSeguradora = new ModelSeguradora();

    ControllerFormaPagamento controllerTipoPagamento = new ControllerFormaPagamento();
    ArrayList<ModelFormaPagamento> listaModelTipoPagamentos = new ArrayList<>();
    ModelFormaPagamento modelFormaPagamento = new ModelFormaPagamento();

    ControllerUsuario controllerUsuario = new ControllerUsuario();
    ArrayList<ModelUsuario> listaModelUsuario = new ArrayList<>();
    ModelUsuario modelUsuario = new ModelUsuario();

    /**
     * Creates new form Principal
     */
    public ViewPrincipal() {
        initComponents();
        SetIcone();
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.carregarProcessosReceber();
        carregarProcessosVencimento();
        verificarPermissao();
        carregarProcessosAndamento();
        carregarProcessosAguardandoPgto();
        System.out.println(ModelSessaoUsuario.codigo);
        aplicarNomeEDataUsuario();
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
                new ViewPrincipal().setVisible(true);
            }
        });
    }

    public void encerrar() {
        int confirmed = JOptionPane.showConfirmDialog(null,
                "Tem certeza de que deseja sair do programa?", "Exit Program Message Box",
                JOptionPane.YES_NO_OPTION);
        if (confirmed == JOptionPane.YES_OPTION) {
            System.exit(0);
            //dispose();
        }
    }

    public void verificarPermissao() {
        String usuarioPermissao = ModelSessaoUsuario.login;
        int permissao = controllerUsuario.getUsuarioController(usuarioPermissao).getPermissao();

        //EXEMPLO DE PERMISSÃO  consulta  redator  financeiro  administracao  diretoria
        if (permissao == 1) {
            //agente
            aplicarPermissao(true, false, false, false, false, false, false);
        } else if (permissao == 2) {
            //redator
            aplicarPermissao(false, true, false, false, false, false, false);
        } else if (permissao == 3) {
            //financeiro
            aplicarPermissao(false, true, true, false, false, false, true);
        } else if (permissao == 4) {
            //Administração
            aplicarPermissao(false, true, true, true, false, false, true);
        } else if (permissao == 5) {
            //Administração
            aplicarPermissao(true, true, true, true, true, true, false);
        }
        System.out.println(permissao);
    }

    public void aplicarPermissao(boolean agente, boolean redator, boolean financeiro, boolean administracao, boolean diretoria, boolean atalhosAdm, boolean AtalhosFin) {

        //CONSULTA
        jMConsultaRedator.setVisible(redator);

        //AGENTE
        jMConsultaAgentes.setVisible(agente);
        jMConsultaAgentes.setEnabled(agente);
        jmMyProcessos.setEnabled(agente);
        jmMyRepasses.setEnabled(agente);

        //PRODUCAO
        jMProcucao.setVisible(financeiro);
        jmProcessos2.setEnabled(financeiro);
        mnProcessosDeletados.setEnabled(financeiro);

        //CADASTROS
        jMCadastros.setVisible(financeiro);
        jmSeguradorasR.setEnabled(financeiro);
        jmFuncionarios.setEnabled(financeiro);
        jmAliquiota.setEnabled(financeiro);
        jmCoberturas.setEnabled(financeiro);

        //FINANCEIRO 
        jMFInanceiro.setVisible(financeiro);
        jmContasPagarF.setEnabled(financeiro);
        jmContasReceber.setEnabled(financeiro);
        jPanelBackGround.setVisible(financeiro);
        jPanelBackGround1.setVisible(financeiro);

        //RELATORIO
        jMRelatorios.setVisible(financeiro);
        jmControleProcessos.setEnabled(financeiro);
        jmControleDeContas.setEnabled(financeiro);
        jmControleDeDados.setEnabled(financeiro);

        //MENU
        kGProducao.setVisible(financeiro);
        KGContas.setVisible(financeiro);
        KGCadastros.setVisible(AtalhosFin);
        kGRelaFinanceiro.setVisible(financeiro);
        kGRelaDiretoria.setVisible(atalhosAdm);

        //CADASTRO SISTEMA (ADM)    
        jMCadastrosAdm.setVisible(administracao);
        jmUsuarioSistema.setEnabled(administracao);
        jmOcupacaoCad.setEnabled(administracao);
        jmVinculosContratuias.setEnabled(administracao);

        //ACESSO RESTRITO DIRETOR
        jMRepassesDiretoria.setVisible(diretoria);
        jmControleAgentesFast.setEnabled(diretoria);
        jmControleAgentes.setEnabled(diretoria);
        PlanilhasDeAgentes.setEnabled(diretoria);

    }

    private void carregarProcessosReceber() {
        Datas bl = new Datas();
        listaModelOp = controllerOp.getProcessosRecebidosController(1);
        DefaultTableModel modelo = (DefaultTableModel) tbProcessosReceber.getModel();
        modelo.setNumRows(0);
        //CARREGA OS DADOS DA LISTA NA TABELA
        int cont = listaModelOp.size();
        for (int i = 0; i < cont; i++) {
            modelFormaPagamento = controllerTipoPagamento.getFormaPagamentoController(listaModelOp.get(i).getTipo());
            modelSeguradora = controllerSeguradora.getSeguradoraController(listaModelOp.get(i).getSeguradoras());
            modelo.addRow(new Object[]{
                listaModelOp.get(i).getCodigo(),
                modelSeguradora.getNome(),
                listaModelOp.get(i).getNumeroSinistro(),
                listaModelOp.get(i).getNomeSegurado(),
                (new Mascaras().DataRecuperasql(String.valueOf(listaModelOp.get(i).getDataEntrada()))),
                (new Moeda().valorStringTODoubleAtt(listaModelOp.get(i).getValorTotalHonorariosSemRetencao().toString())),
                modelFormaPagamento.getDescricao()
            });
        }
    }

    private void carregarProcessosAndamento() {
        Datas bl = new Datas();
        listaModelOp = controllerOp.getProcessosRecebidosController(4);
        DefaultTableModel modelo = (DefaultTableModel) tbProcessosAndamento.getModel();
        modelo.setNumRows(0);
        //CARREGA OS DADOS DA LISTA NA TABELA
        int cont = listaModelOp.size();
        for (int i = 0; i < cont; i++) {
            modelFormaPagamento = controllerTipoPagamento.getFormaPagamentoController(listaModelOp.get(i).getTipo());
            modelSeguradora = controllerSeguradora.getSeguradoraController(listaModelOp.get(i).getSeguradoras());
            modelo.addRow(new Object[]{
                listaModelOp.get(i).getCodigo(),
                modelSeguradora.getNome(),
                listaModelOp.get(i).getNumeroSinistro(),
                listaModelOp.get(i).getNomeSegurado(),
                (new Mascaras().DataRecuperasql(String.valueOf(listaModelOp.get(i).getDataEntrada()))),
                (new Moeda().valorStringTODoubleAtt(listaModelOp.get(i).getValorTotalHonorariosSemRetencao().toString())),
                modelFormaPagamento.getDescricao()
            });
        }
    }

    private void carregarProcessosAguardandoPgto() {
        Datas bl = new Datas();
        listaModelOp = controllerOp.getProcessosRecebidosController(5);
        DefaultTableModel modelo = (DefaultTableModel) tbProcessosAguardandoPgto.getModel();
        modelo.setNumRows(0);
        //CARREGA OS DADOS DA LISTA NA TABELA
        int cont = listaModelOp.size();
        for (int i = 0; i < cont; i++) {
            modelFormaPagamento = controllerTipoPagamento.getFormaPagamentoController(listaModelOp.get(i).getTipo());
            modelSeguradora = controllerSeguradora.getSeguradoraController(listaModelOp.get(i).getSeguradoras());
            modelo.addRow(new Object[]{
                listaModelOp.get(i).getCodigo(),
                modelSeguradora.getNome(),
                listaModelOp.get(i).getNumeroSinistro(),
                listaModelOp.get(i).getNomeSegurado(),
                (new Mascaras().DataRecuperasql(String.valueOf(listaModelOp.get(i).getDataEntrada()))),
                (new Moeda().valorStringTODoubleAtt(listaModelOp.get(i).getValorTotalHonorariosSemRetencao().toString())),
                modelFormaPagamento.getDescricao()
            });
        }
    }

    private String dataAtual() {
        Date d = new Date();
        Locale local = new Locale("pt", "BR");
        SimpleDateFormat format = new SimpleDateFormat("EEEE, d' de 'MMMM' de 'yyyy", local);

        //Desativado
        /*String datastring;
         datastring = format.format(d);*/
        return String.format((format.format(d)));

    }

    void aplicarNomeEDataUsuario() {
        txtNome.setText(ModelSessaoUsuario.nome);
        jLDataAtual.setText(dataAtual());
    }

    private String dataAtualFormaBR() {
        Date d = new Date();
        Locale local = new Locale("pt", "BR");
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", local);

        //Desativado
        /*String datastring;
         datastring = format.format(d);*/
        return String.format((format.format(d)));
    }

    private void carregarProcessosVencimento() {
        Datas bl = new Datas();
        listaModelOp = controllerOp.FiltraOpDecrescentePorDataController();
        DefaultTableModel modelo = (DefaultTableModel) tbProcessosPorData.getModel();
        modelo.setNumRows(0);
        //CARREGA OS DADOS DA LISTA NA TABELA
        int cont = listaModelOp.size();
        for (int i = 0; i < cont; i++) {
            modelFormaPagamento = controllerTipoPagamento.getFormaPagamentoController(listaModelOp.get(i).getTipo());
            modelSeguradora = controllerSeguradora.getSeguradoraController(listaModelOp.get(i).getSeguradoras());
            modelo.addRow(new Object[]{
                listaModelOp.get(i).getCodigo(),
                modelSeguradora.getNome(),
                listaModelOp.get(i).getNumeroSinistro(),
                listaModelOp.get(i).getNomeSegurado(),
                (new Mascaras().DataRecuperasql(String.valueOf(listaModelOp.get(i).getDataEntrada()))),
                (new Mascaras().DataRecuperasql(String.valueOf(listaModelOp.get(i).getDataPrevisaoPgtoNFhonorarios()))),
                (new Moeda().valorStringTODoubleAtt(listaModelOp.get(i).getValorTotalHonorariosSemRetencao().toString())),
                modelFormaPagamento.getDescricao()
            });
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

        kButtonRepassesAgts = new keeptoo.KButton();
        jmControleAgentesFast = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JSeparator();
        jPBase = new javax.swing.JPanel();
        jPanelUsuarioEdata = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPaneAnalista = new javax.swing.JScrollPane();
        txtNome = new javax.swing.JTextArea();
        jScrollPaneAnalista1 = new javax.swing.JScrollPane();
        jLDataAtual = new javax.swing.JTextArea();
        kGradientPanel2 = new keeptoo.KGradientPanel();
        kGProducao = new keeptoo.KGradientPanel();
        kButton11 = new keeptoo.KButton();
        KGContas = new keeptoo.KGradientPanel();
        kButton10 = new keeptoo.KButton();
        kButton9 = new keeptoo.KButton();
        kGRelaDiretoria = new keeptoo.KGradientPanel();
        kGBPlalinhasAgentes = new keeptoo.KButton();
        kGControleAgentes = new keeptoo.KButton();
        KGCadastros = new keeptoo.KGradientPanel();
        kButtonCOBERTURAS = new keeptoo.KButton();
        kButtonALICOTA = new keeptoo.KButton();
        kButtonFUNCIONARIOS = new keeptoo.KButton();
        kButtonSEGURADORAS = new keeptoo.KButton();
        kGRelaFinanceiro = new keeptoo.KGradientPanel();
        kButtonRELCONTAS = new keeptoo.KButton();
        kButtonRELProcessos = new keeptoo.KButton();
        jPanelBackGround = new javax.swing.JPanel();
        kGradientPanel1 = new keeptoo.KGradientPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        kButtonPendentes1 = new keeptoo.KButton();
        kGradientPanel5 = new keeptoo.KGradientPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbProcessosReceber = new javax.swing.JTable();
        kGradientPanel6 = new keeptoo.KGradientPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        kButtonPendentes2 = new keeptoo.KButton();
        kGradientPanel7 = new keeptoo.KGradientPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbProcessosPorData = new javax.swing.JTable();
        kGradientPanel25 = new keeptoo.KGradientPanel();
        jPanelBackGround1 = new javax.swing.JPanel();
        kGradientPanel4 = new keeptoo.KGradientPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        kButtonPendentes3 = new keeptoo.KButton();
        kGradientPanel8 = new keeptoo.KGradientPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tbProcessosAndamento = new javax.swing.JTable();
        kGradientPanel9 = new keeptoo.KGradientPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        kButtonPendentes4 = new keeptoo.KButton();
        kGradientPanel10 = new keeptoo.KGradientPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tbProcessosAguardandoPgto = new javax.swing.JTable();
        kGradientPanel26 = new keeptoo.KGradientPanel();
        jLabel22 = new javax.swing.JLabel();
        kGradientPanel3 = new keeptoo.KGradientPanel();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jMenuBar = new javax.swing.JMenuBar();
        jMRepassesDiretoria = new javax.swing.JMenu();
        jmControleAgentes = new javax.swing.JMenuItem();
        PlanilhasDeAgentes = new javax.swing.JMenuItem();
        jMProcucao = new javax.swing.JMenu();
        jmProcessos2 = new javax.swing.JMenuItem();
        mnProcessosDeletados = new javax.swing.JMenuItem();
        jMConsultaRedator = new javax.swing.JMenu();
        jMCadastros = new javax.swing.JMenu();
        jmSeguradorasR = new javax.swing.JMenuItem();
        jmFuncionarios = new javax.swing.JMenuItem();
        jmAliquiota = new javax.swing.JMenuItem();
        jmCoberturas = new javax.swing.JMenuItem();
        jmCadCidades = new javax.swing.JMenuItem();
        jmCadTipoDeContas = new javax.swing.JMenuItem();
        jMFInanceiro = new javax.swing.JMenu();
        jmContasPagarF = new javax.swing.JMenuItem();
        jmContasReceber = new javax.swing.JMenuItem();
        jMRelatorios = new javax.swing.JMenu();
        jmControleDeDados = new javax.swing.JMenuItem();
        jmControleDeContas = new javax.swing.JMenuItem();
        jmControleProcessos = new javax.swing.JMenuItem();
        jMCadastrosAdm = new javax.swing.JMenu();
        jmUsuarioSistema = new javax.swing.JMenuItem();
        jmOcupacaoCad = new javax.swing.JMenuItem();
        jmVinculosContratuias = new javax.swing.JMenuItem();
        jMConsultaAgentes = new javax.swing.JMenu();
        jmMyProcessos = new javax.swing.JMenuItem();
        jmMyRepasses = new javax.swing.JMenuItem();
        jmLogout = new javax.swing.JMenu();
        jSeparator1 = new javax.swing.JSeparator();
        mnuLogOut = new javax.swing.JMenuItem();
        mnuSair = new javax.swing.JMenuItem();

        kButtonRepassesAgts.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Create.png"))); // NOI18N
        kButtonRepassesAgts.setText("   REGISTRAR DESPESAS");
        kButtonRepassesAgts.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        kButtonRepassesAgts.setIconTextGap(5);
        kButtonRepassesAgts.setkAllowTab(false);
        kButtonRepassesAgts.setkBackGroundColor(new java.awt.Color(255, 255, 255));
        kButtonRepassesAgts.setkBorderRadius(0);
        kButtonRepassesAgts.setkEndColor(new java.awt.Color(0, 102, 51));
        kButtonRepassesAgts.setkForeGround(new java.awt.Color(51, 51, 51));
        kButtonRepassesAgts.setkHoverEndColor(new java.awt.Color(234, 239, 243));
        kButtonRepassesAgts.setkHoverForeGround(new java.awt.Color(51, 51, 51));
        kButtonRepassesAgts.setkHoverStartColor(new java.awt.Color(234, 239, 243));
        kButtonRepassesAgts.setkSelectedColor(new java.awt.Color(51, 51, 51));
        kButtonRepassesAgts.setkStartColor(new java.awt.Color(204, 255, 204));
        kButtonRepassesAgts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButtonRepassesAgtsActionPerformed(evt);
            }
        });

        jmControleAgentesFast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Create.png"))); // NOI18N
        jmControleAgentesFast.setText("Registrar Despesas");
        jmControleAgentesFast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmControleAgentesFastActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Controle 1.2");
        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1360, 670));
        setName("Controle 1.2"); // NOI18N
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().add(jSeparator3, java.awt.BorderLayout.PAGE_START);

        jPBase.setBackground(new java.awt.Color(255, 255, 255));
        jPBase.setAlignmentX(0.0F);
        jPBase.setAlignmentY(0.0F);
        jPBase.setAutoscrolls(true);
        jPBase.setMinimumSize(new java.awt.Dimension(1360, 670));
        jPBase.setPreferredSize(new java.awt.Dimension(32767, 670));
        jPBase.setRequestFocusEnabled(false);
        jPBase.setVerifyInputWhenFocusTarget(false);

        jPanelUsuarioEdata.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 153, 153));
        jLabel2.setText("Olá");

        jScrollPaneAnalista.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPaneAnalista.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPaneAnalista.setPreferredSize(new java.awt.Dimension(675, 25));

        txtNome.setEditable(false);
        txtNome.setColumns(20);
        txtNome.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtNome.setRows(5);
        txtNome.setText("\n");
        txtNome.setFocusable(false);
        jScrollPaneAnalista.setViewportView(txtNome);

        jScrollPaneAnalista1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPaneAnalista1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPaneAnalista1.setPreferredSize(new java.awt.Dimension(675, 25));

        jLDataAtual.setEditable(false);
        jLDataAtual.setColumns(20);
        jLDataAtual.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLDataAtual.setRows(5);
        jLDataAtual.setText("Semana, Dia Mes e Ano");
        jLDataAtual.setFocusable(false);
        jScrollPaneAnalista1.setViewportView(jLDataAtual);

        org.jdesktop.layout.GroupLayout jPanelUsuarioEdataLayout = new org.jdesktop.layout.GroupLayout(jPanelUsuarioEdata);
        jPanelUsuarioEdata.setLayout(jPanelUsuarioEdataLayout);
        jPanelUsuarioEdataLayout.setHorizontalGroup(
            jPanelUsuarioEdataLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanelUsuarioEdataLayout.createSequentialGroup()
                .add(0, 0, 0)
                .add(jLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPaneAnalista, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 372, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 640, Short.MAX_VALUE)
                .add(jScrollPaneAnalista1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 239, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
        jPanelUsuarioEdataLayout.setVerticalGroup(
            jPanelUsuarioEdataLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
            .add(jPanelUsuarioEdataLayout.createSequentialGroup()
                .add(0, 0, 0)
                .add(jPanelUsuarioEdataLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jScrollPaneAnalista, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .add(jScrollPaneAnalista1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .add(jLabel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)))
        );

        jPBase.add(jPanelUsuarioEdata);

        kGradientPanel2.setBackground(new java.awt.Color(255, 255, 255));
        kGradientPanel2.setkBorderRadius(0);
        kGradientPanel2.setkEndColor(new java.awt.Color(146, 171, 197));
        kGradientPanel2.setkGradientFocus(0);
        kGradientPanel2.setkStartColor(new java.awt.Color(146, 171, 197));
        kGradientPanel2.setPreferredSize(new java.awt.Dimension(32767, 50));

        kGProducao.setkBorderRadius(0);
        kGProducao.setkEndColor(new java.awt.Color(146, 171, 197));
        kGProducao.setkGradientFocus(0);
        kGProducao.setkStartColor(new java.awt.Color(255, 255, 255));
        kGProducao.setPreferredSize(new java.awt.Dimension(310, 110));
        kGProducao.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        kButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/cog.png"))); // NOI18N
        kButton11.setText("    PROCESSOS");
        kButton11.setToolTipText("");
        kButton11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        kButton11.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        kButton11.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        kButton11.setIconTextGap(18);
        kButton11.setkAllowTab(false);
        kButton11.setkBackGroundColor(new java.awt.Color(255, 255, 255));
        kButton11.setkBorderRadius(0);
        kButton11.setkEndColor(new java.awt.Color(255, 255, 204));
        kButton11.setkForeGround(new java.awt.Color(51, 51, 0));
        kButton11.setkHoverEndColor(new java.awt.Color(234, 239, 243));
        kButton11.setkHoverForeGround(new java.awt.Color(51, 51, 51));
        kButton11.setkHoverStartColor(new java.awt.Color(234, 239, 243));
        kButton11.setkSelectedColor(new java.awt.Color(51, 51, 51));
        kButton11.setkStartColor(new java.awt.Color(102, 153, 255));
        kButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButton11ActionPerformed(evt);
            }
        });
        kGProducao.add(kButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 170, 50));

        KGContas.setkBorderRadius(0);
        KGContas.setkEndColor(new java.awt.Color(146, 171, 197));
        KGContas.setkGradientFocus(0);
        KGContas.setkStartColor(new java.awt.Color(255, 255, 255));
        KGContas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        kButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Up.png"))); // NOI18N
        kButton10.setText("A receber");
        kButton10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        kButton10.setIconTextGap(15);
        kButton10.setkAllowTab(false);
        kButton10.setkBackGroundColor(new java.awt.Color(255, 255, 255));
        kButton10.setkBorderRadius(0);
        kButton10.setkEndColor(new java.awt.Color(0, 51, 51));
        kButton10.setkHoverEndColor(new java.awt.Color(234, 239, 243));
        kButton10.setkHoverForeGround(new java.awt.Color(51, 51, 51));
        kButton10.setkHoverStartColor(new java.awt.Color(234, 239, 243));
        kButton10.setkSelectedColor(new java.awt.Color(51, 51, 51));
        kButton10.setkStartColor(new java.awt.Color(51, 204, 0));
        kButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButton10ActionPerformed(evt);
            }
        });
        KGContas.add(kButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 0, 130, -1));

        kButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Fall.png"))); // NOI18N
        kButton9.setText("A Pagar");
        kButton9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        kButton9.setIconTextGap(18);
        kButton9.setkAllowTab(false);
        kButton9.setkBackGroundColor(new java.awt.Color(255, 255, 255));
        kButton9.setkBorderRadius(0);
        kButton9.setkEndColor(new java.awt.Color(0, 51, 51));
        kButton9.setkHoverEndColor(new java.awt.Color(234, 239, 243));
        kButton9.setkHoverForeGround(new java.awt.Color(51, 51, 51));
        kButton9.setkHoverStartColor(new java.awt.Color(234, 239, 243));
        kButton9.setkSelectedColor(new java.awt.Color(51, 51, 51));
        kButton9.setkStartColor(new java.awt.Color(51, 204, 0));
        kButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButton9ActionPerformed(evt);
            }
        });
        KGContas.add(kButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 130, -1));

        kGRelaDiretoria.setkBorderRadius(0);
        kGRelaDiretoria.setkEndColor(new java.awt.Color(146, 171, 197));
        kGRelaDiretoria.setkGradientFocus(0);
        kGRelaDiretoria.setkStartColor(new java.awt.Color(255, 255, 255));
        kGRelaDiretoria.setPreferredSize(new java.awt.Dimension(420, 110));
        kGRelaDiretoria.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        kGBPlalinhasAgentes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/3d bar chart.png"))); // NOI18N
        kGBPlalinhasAgentes.setText("    PLANILHAS DE AGENTES");
        kGBPlalinhasAgentes.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        kGBPlalinhasAgentes.setIconTextGap(8);
        kGBPlalinhasAgentes.setkAllowTab(false);
        kGBPlalinhasAgentes.setkBackGroundColor(new java.awt.Color(255, 255, 255));
        kGBPlalinhasAgentes.setkBorderRadius(0);
        kGBPlalinhasAgentes.setkEndColor(new java.awt.Color(0, 102, 51));
        kGBPlalinhasAgentes.setkForeGround(new java.awt.Color(51, 51, 51));
        kGBPlalinhasAgentes.setkHoverEndColor(new java.awt.Color(234, 239, 243));
        kGBPlalinhasAgentes.setkHoverForeGround(new java.awt.Color(51, 51, 51));
        kGBPlalinhasAgentes.setkHoverStartColor(new java.awt.Color(234, 239, 243));
        kGBPlalinhasAgentes.setkSelectedColor(new java.awt.Color(51, 51, 51));
        kGBPlalinhasAgentes.setkStartColor(new java.awt.Color(204, 255, 204));
        kGBPlalinhasAgentes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kGBPlalinhasAgentesActionPerformed(evt);
            }
        });
        kGRelaDiretoria.add(kGBPlalinhasAgentes, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, 190, -1));

        kGControleAgentes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Boss.png"))); // NOI18N
        kGControleAgentes.setText("    CONTROLE DE AGENTES");
        kGControleAgentes.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        kGControleAgentes.setIconTextGap(15);
        kGControleAgentes.setkAllowTab(false);
        kGControleAgentes.setkBackGroundColor(new java.awt.Color(255, 255, 255));
        kGControleAgentes.setkBorderRadius(0);
        kGControleAgentes.setkEndColor(new java.awt.Color(0, 102, 51));
        kGControleAgentes.setkForeGround(new java.awt.Color(51, 51, 51));
        kGControleAgentes.setkHoverEndColor(new java.awt.Color(234, 239, 243));
        kGControleAgentes.setkHoverForeGround(new java.awt.Color(51, 51, 51));
        kGControleAgentes.setkHoverStartColor(new java.awt.Color(234, 239, 243));
        kGControleAgentes.setkSelectedColor(new java.awt.Color(51, 51, 51));
        kGControleAgentes.setkStartColor(new java.awt.Color(204, 255, 204));
        kGControleAgentes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kGControleAgentesActionPerformed(evt);
            }
        });
        kGRelaDiretoria.add(kGControleAgentes, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, -1));

        KGCadastros.setkBorderRadius(0);
        KGCadastros.setkEndColor(new java.awt.Color(146, 171, 197));
        KGCadastros.setkGradientFocus(0);
        KGCadastros.setkStartColor(new java.awt.Color(255, 255, 255));
        KGCadastros.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        kButtonCOBERTURAS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Component.png"))); // NOI18N
        kButtonCOBERTURAS.setText("Coberturas");
        kButtonCOBERTURAS.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        kButtonCOBERTURAS.setIconTextGap(10);
        kButtonCOBERTURAS.setkAllowTab(false);
        kButtonCOBERTURAS.setkBackGroundColor(new java.awt.Color(255, 255, 255));
        kButtonCOBERTURAS.setkBorderRadius(0);
        kButtonCOBERTURAS.setkEndColor(new java.awt.Color(0, 51, 204));
        kButtonCOBERTURAS.setkForeGround(new java.awt.Color(51, 0, 102));
        kButtonCOBERTURAS.setkHoverEndColor(new java.awt.Color(234, 239, 243));
        kButtonCOBERTURAS.setkHoverForeGround(new java.awt.Color(51, 51, 51));
        kButtonCOBERTURAS.setkHoverStartColor(new java.awt.Color(234, 239, 243));
        kButtonCOBERTURAS.setkSelectedColor(new java.awt.Color(51, 51, 51));
        kButtonCOBERTURAS.setkStartColor(new java.awt.Color(153, 204, 255));
        kButtonCOBERTURAS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButtonCOBERTURASActionPerformed(evt);
            }
        });
        KGCadastros.add(kButtonCOBERTURAS, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 0, 140, -1));

        kButtonALICOTA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Table.png"))); // NOI18N
        kButtonALICOTA.setText("Alíquotas");
        kButtonALICOTA.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        kButtonALICOTA.setIconTextGap(14);
        kButtonALICOTA.setkAllowTab(false);
        kButtonALICOTA.setkBackGroundColor(new java.awt.Color(255, 255, 255));
        kButtonALICOTA.setkBorderRadius(0);
        kButtonALICOTA.setkEndColor(new java.awt.Color(0, 51, 204));
        kButtonALICOTA.setkForeGround(new java.awt.Color(51, 0, 102));
        kButtonALICOTA.setkHoverEndColor(new java.awt.Color(234, 239, 243));
        kButtonALICOTA.setkHoverForeGround(new java.awt.Color(51, 51, 51));
        kButtonALICOTA.setkHoverStartColor(new java.awt.Color(234, 239, 243));
        kButtonALICOTA.setkSelectedColor(new java.awt.Color(51, 51, 51));
        kButtonALICOTA.setkStartColor(new java.awt.Color(153, 204, 255));
        kButtonALICOTA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButtonALICOTAActionPerformed(evt);
            }
        });
        KGCadastros.add(kButtonALICOTA, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 0, 130, -1));

        kButtonFUNCIONARIOS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/User group.png"))); // NOI18N
        kButtonFUNCIONARIOS.setText("   Funcionários");
        kButtonFUNCIONARIOS.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        kButtonFUNCIONARIOS.setIconTextGap(8);
        kButtonFUNCIONARIOS.setkAllowTab(false);
        kButtonFUNCIONARIOS.setkBackGroundColor(new java.awt.Color(255, 255, 255));
        kButtonFUNCIONARIOS.setkBorderRadius(0);
        kButtonFUNCIONARIOS.setkEndColor(new java.awt.Color(0, 51, 204));
        kButtonFUNCIONARIOS.setkForeGround(new java.awt.Color(51, 0, 102));
        kButtonFUNCIONARIOS.setkHoverEndColor(new java.awt.Color(234, 239, 243));
        kButtonFUNCIONARIOS.setkHoverForeGround(new java.awt.Color(51, 51, 51));
        kButtonFUNCIONARIOS.setkHoverStartColor(new java.awt.Color(234, 239, 243));
        kButtonFUNCIONARIOS.setkSelectedColor(new java.awt.Color(51, 51, 51));
        kButtonFUNCIONARIOS.setkStartColor(new java.awt.Color(153, 204, 255));
        kButtonFUNCIONARIOS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButtonFUNCIONARIOSActionPerformed(evt);
            }
        });
        KGCadastros.add(kButtonFUNCIONARIOS, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 0, 130, -1));

        kButtonSEGURADORAS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/seguradoras.png"))); // NOI18N
        kButtonSEGURADORAS.setText("   Seguradoras");
        kButtonSEGURADORAS.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        kButtonSEGURADORAS.setIconTextGap(6);
        kButtonSEGURADORAS.setkAllowTab(false);
        kButtonSEGURADORAS.setkBackGroundColor(new java.awt.Color(255, 255, 255));
        kButtonSEGURADORAS.setkBorderRadius(0);
        kButtonSEGURADORAS.setkEndColor(new java.awt.Color(0, 51, 204));
        kButtonSEGURADORAS.setkForeGround(new java.awt.Color(51, 0, 102));
        kButtonSEGURADORAS.setkHoverEndColor(new java.awt.Color(234, 239, 243));
        kButtonSEGURADORAS.setkHoverForeGround(new java.awt.Color(51, 51, 51));
        kButtonSEGURADORAS.setkHoverStartColor(new java.awt.Color(234, 239, 243));
        kButtonSEGURADORAS.setkSelectedColor(new java.awt.Color(51, 51, 51));
        kButtonSEGURADORAS.setkStartColor(new java.awt.Color(153, 204, 255));
        kButtonSEGURADORAS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButtonSEGURADORASActionPerformed(evt);
            }
        });
        KGCadastros.add(kButtonSEGURADORAS, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 130, -1));

        kGRelaFinanceiro.setkBorderRadius(0);
        kGRelaFinanceiro.setkEndColor(new java.awt.Color(146, 171, 197));
        kGRelaFinanceiro.setkGradientFocus(0);
        kGRelaFinanceiro.setkStartColor(new java.awt.Color(255, 255, 255));
        kGRelaFinanceiro.setPreferredSize(new java.awt.Dimension(420, 110));
        kGRelaFinanceiro.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        kButtonRELCONTAS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Pie chart.png"))); // NOI18N
        kButtonRELCONTAS.setText("Controle Contas");
        kButtonRELCONTAS.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        kButtonRELCONTAS.setkAllowTab(false);
        kButtonRELCONTAS.setkBackGroundColor(new java.awt.Color(255, 255, 255));
        kButtonRELCONTAS.setkBorderRadius(0);
        kButtonRELCONTAS.setkEndColor(new java.awt.Color(255, 204, 102));
        kButtonRELCONTAS.setkHoverEndColor(new java.awt.Color(234, 239, 243));
        kButtonRELCONTAS.setkHoverForeGround(new java.awt.Color(51, 51, 51));
        kButtonRELCONTAS.setkHoverStartColor(new java.awt.Color(234, 239, 243));
        kButtonRELCONTAS.setkSelectedColor(new java.awt.Color(51, 51, 51));
        kButtonRELCONTAS.setkStartColor(new java.awt.Color(102, 51, 0));
        kButtonRELCONTAS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButtonRELCONTASActionPerformed(evt);
            }
        });
        kGRelaFinanceiro.add(kButtonRELCONTAS, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 0, 130, -1));

        kButtonRELProcessos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Report.png"))); // NOI18N
        kButtonRELProcessos.setText("Controle Processos");
        kButtonRELProcessos.setFont(new java.awt.Font("Tahoma", 1, 9)); // NOI18N
        kButtonRELProcessos.setIconTextGap(1);
        kButtonRELProcessos.setkAllowTab(false);
        kButtonRELProcessos.setkBackGroundColor(new java.awt.Color(255, 255, 255));
        kButtonRELProcessos.setkBorderRadius(0);
        kButtonRELProcessos.setkEndColor(new java.awt.Color(255, 204, 102));
        kButtonRELProcessos.setkHoverEndColor(new java.awt.Color(234, 239, 243));
        kButtonRELProcessos.setkHoverForeGround(new java.awt.Color(51, 51, 51));
        kButtonRELProcessos.setkHoverStartColor(new java.awt.Color(234, 239, 243));
        kButtonRELProcessos.setkSelectedColor(new java.awt.Color(51, 51, 51));
        kButtonRELProcessos.setkStartColor(new java.awt.Color(102, 51, 0));
        kButtonRELProcessos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButtonRELProcessosActionPerformed(evt);
            }
        });
        kGRelaFinanceiro.add(kButtonRELProcessos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 130, -1));

        org.jdesktop.layout.GroupLayout kGradientPanel2Layout = new org.jdesktop.layout.GroupLayout(kGradientPanel2);
        kGradientPanel2.setLayout(kGradientPanel2Layout);
        kGradientPanel2Layout.setHorizontalGroup(
            kGradientPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(kGradientPanel2Layout.createSequentialGroup()
                .add(15650, 15650, 15650)
                .add(kGProducao, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 190, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(0, 0, 0)
                .add(KGContas, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 270, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(kGRelaDiretoria, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 401, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(0, 0, 0)
                .add(KGCadastros, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 539, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(0, 0, 0)
                .add(kGRelaFinanceiro, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 269, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        kGradientPanel2Layout.setVerticalGroup(
            kGradientPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, kGRelaDiretoria, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .add(kGradientPanel2Layout.createSequentialGroup()
                .add(kGradientPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(kGProducao, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(KGContas, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(KGCadastros, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(kGRelaFinanceiro, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(0, 0, Short.MAX_VALUE))
        );

        jPBase.add(kGradientPanel2);

        jPanelBackGround.setBackground(new java.awt.Color(255, 255, 255));
        jPanelBackGround.setMaximumSize(new java.awt.Dimension(32767, 670));
        jPanelBackGround.setMinimumSize(new java.awt.Dimension(1, 1));
        jPanelBackGround.setPreferredSize(new java.awt.Dimension(680, 580));

        kGradientPanel1.setkBorderRadius(0);
        kGradientPanel1.setkEndColor(new java.awt.Color(234, 239, 243));
        kGradientPanel1.setkGradientFocus(0);
        kGradientPanel1.setkStartColor(new java.awt.Color(255, 255, 255));
        kGradientPanel1.setPreferredSize(new java.awt.Dimension(32767, 45));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("ULTIMOS PROCESSOS");

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/information.png"))); // NOI18N

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
        kButtonPendentes1.setPreferredSize(new java.awt.Dimension(60, 50));
        kButtonPendentes1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButtonPendentes1ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout kGradientPanel1Layout = new org.jdesktop.layout.GroupLayout(kGradientPanel1);
        kGradientPanel1.setLayout(kGradientPanel1Layout);
        kGradientPanel1Layout.setHorizontalGroup(
            kGradientPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, kGradientPanel1Layout.createSequentialGroup()
                .addContainerGap(16071, Short.MAX_VALUE)
                .add(jLabel9)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel8)
                .add(388, 388, 388)
                .add(kButtonPendentes1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(16060, 16060, 16060))
        );
        kGradientPanel1Layout.setVerticalGroup(
            kGradientPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(kGradientPanel1Layout.createSequentialGroup()
                .add(kGradientPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(kGradientPanel1Layout.createSequentialGroup()
                        .add(kGradientPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(jLabel9, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(jLabel8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 40, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(0, 0, Short.MAX_VALUE))
                    .add(kGradientPanel1Layout.createSequentialGroup()
                        .add(0, 0, Short.MAX_VALUE)
                        .add(kButtonPendentes1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanelBackGround.add(kGradientPanel1);

        kGradientPanel5.setkBorderRadius(0);
        kGradientPanel5.setkEndColor(new java.awt.Color(146, 171, 197));
        kGradientPanel5.setkGradientFocus(0);
        kGradientPanel5.setkStartColor(new java.awt.Color(255, 255, 255));
        kGradientPanel5.setPreferredSize(new java.awt.Dimension(940, 200));
        kGradientPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane6.setBackground(new java.awt.Color(247, 247, 247));
        jScrollPane6.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane6.setPreferredSize(new java.awt.Dimension(1220, 180));

        tbProcessosReceber.setAutoCreateRowSorter(true);
        tbProcessosReceber.setBackground(new java.awt.Color(247, 247, 247));
        tbProcessosReceber.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        tbProcessosReceber.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cod.", "Seguradora", "Nº Sinistro", "Segurado", "Data Entrada", "Honorário", "Situação"
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
        jScrollPane6.setViewportView(tbProcessosReceber);
        if (tbProcessosReceber.getColumnModel().getColumnCount() > 0) {
            tbProcessosReceber.getColumnModel().getColumn(0).setMinWidth(80);
            tbProcessosReceber.getColumnModel().getColumn(0).setPreferredWidth(80);
            tbProcessosReceber.getColumnModel().getColumn(0).setMaxWidth(80);
            tbProcessosReceber.getColumnModel().getColumn(1).setMinWidth(200);
            tbProcessosReceber.getColumnModel().getColumn(3).setMinWidth(0);
            tbProcessosReceber.getColumnModel().getColumn(3).setPreferredWidth(0);
            tbProcessosReceber.getColumnModel().getColumn(3).setMaxWidth(0);
            tbProcessosReceber.getColumnModel().getColumn(4).setMinWidth(120);
            tbProcessosReceber.getColumnModel().getColumn(4).setPreferredWidth(120);
            tbProcessosReceber.getColumnModel().getColumn(4).setMaxWidth(120);
            tbProcessosReceber.getColumnModel().getColumn(5).setMinWidth(0);
            tbProcessosReceber.getColumnModel().getColumn(5).setPreferredWidth(0);
            tbProcessosReceber.getColumnModel().getColumn(5).setMaxWidth(0);
        }

        kGradientPanel5.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, 660, -1));

        jPanelBackGround.add(kGradientPanel5);

        kGradientPanel6.setkBorderRadius(0);
        kGradientPanel6.setkEndColor(new java.awt.Color(234, 239, 243));
        kGradientPanel6.setkGradientFocus(0);
        kGradientPanel6.setkStartColor(new java.awt.Color(255, 255, 255));
        kGradientPanel6.setPreferredSize(new java.awt.Dimension(32767, 45));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("PROCESSOS POR VENCIMENTO");

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/exclamation.png"))); // NOI18N

        kButtonPendentes2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/24x24/Refresh.png"))); // NOI18N
        kButtonPendentes2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        kButtonPendentes2.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        kButtonPendentes2.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        kButtonPendentes2.setIconTextGap(20);
        kButtonPendentes2.setkAllowGradient(false);
        kButtonPendentes2.setkAllowTab(false);
        kButtonPendentes2.setkBackGroundColor(new java.awt.Color(255, 255, 255));
        kButtonPendentes2.setkBorderRadius(0);
        kButtonPendentes2.setkEndColor(new java.awt.Color(234, 239, 243));
        kButtonPendentes2.setkForeGround(new java.awt.Color(51, 51, 51));
        kButtonPendentes2.setkHoverEndColor(new java.awt.Color(234, 239, 243));
        kButtonPendentes2.setkHoverForeGround(new java.awt.Color(51, 51, 51));
        kButtonPendentes2.setkHoverStartColor(new java.awt.Color(234, 239, 243));
        kButtonPendentes2.setkPressedColor(new java.awt.Color(234, 239, 243));
        kButtonPendentes2.setkSelectedColor(new java.awt.Color(197, 201, 206));
        kButtonPendentes2.setkStartColor(new java.awt.Color(255, 255, 255));
        kButtonPendentes2.setPreferredSize(new java.awt.Dimension(60, 50));
        kButtonPendentes2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButtonPendentes2ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout kGradientPanel6Layout = new org.jdesktop.layout.GroupLayout(kGradientPanel6);
        kGradientPanel6.setLayout(kGradientPanel6Layout);
        kGradientPanel6Layout.setHorizontalGroup(
            kGradientPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, kGradientPanel6Layout.createSequentialGroup()
                .addContainerGap(16067, Short.MAX_VALUE)
                .add(jLabel11)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel10)
                .add(320, 320, 320)
                .add(kButtonPendentes2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(16065, 16065, 16065))
        );
        kGradientPanel6Layout.setVerticalGroup(
            kGradientPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(kGradientPanel6Layout.createSequentialGroup()
                .add(kGradientPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jLabel11, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jLabel10, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(kButtonPendentes2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelBackGround.add(kGradientPanel6);

        kGradientPanel7.setkBorderRadius(0);
        kGradientPanel7.setkEndColor(new java.awt.Color(146, 171, 197));
        kGradientPanel7.setkGradientFocus(0);
        kGradientPanel7.setkStartColor(new java.awt.Color(255, 255, 255));
        kGradientPanel7.setPreferredSize(new java.awt.Dimension(940, 200));
        kGradientPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane5.setBackground(new java.awt.Color(247, 247, 247));
        jScrollPane5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane5.setPreferredSize(new java.awt.Dimension(1220, 180));

        tbProcessosPorData.setAutoCreateRowSorter(true);
        tbProcessosPorData.setBackground(new java.awt.Color(247, 247, 247));
        tbProcessosPorData.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        tbProcessosPorData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cod.", "Seguradora", "Nº Sinistro", "Segurado", "Data Entrada", "Previsão Pgto.", "Honorário", "Situação"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbProcessosPorData.setGridColor(new java.awt.Color(247, 247, 247));
        tbProcessosPorData.setMinimumSize(new java.awt.Dimension(610, 0));
        tbProcessosPorData.setRowHeight(20);
        tbProcessosPorData.setSelectionBackground(new java.awt.Color(0, 153, 0));
        tbProcessosPorData.getTableHeader().setReorderingAllowed(false);
        jScrollPane5.setViewportView(tbProcessosPorData);
        if (tbProcessosPorData.getColumnModel().getColumnCount() > 0) {
            tbProcessosPorData.getColumnModel().getColumn(0).setMinWidth(80);
            tbProcessosPorData.getColumnModel().getColumn(0).setPreferredWidth(80);
            tbProcessosPorData.getColumnModel().getColumn(0).setMaxWidth(80);
            tbProcessosPorData.getColumnModel().getColumn(1).setMinWidth(200);
            tbProcessosPorData.getColumnModel().getColumn(2).setMinWidth(150);
            tbProcessosPorData.getColumnModel().getColumn(2).setPreferredWidth(150);
            tbProcessosPorData.getColumnModel().getColumn(2).setMaxWidth(150);
            tbProcessosPorData.getColumnModel().getColumn(3).setMinWidth(0);
            tbProcessosPorData.getColumnModel().getColumn(3).setPreferredWidth(0);
            tbProcessosPorData.getColumnModel().getColumn(3).setMaxWidth(0);
            tbProcessosPorData.getColumnModel().getColumn(4).setMinWidth(120);
            tbProcessosPorData.getColumnModel().getColumn(4).setPreferredWidth(120);
            tbProcessosPorData.getColumnModel().getColumn(4).setMaxWidth(120);
            tbProcessosPorData.getColumnModel().getColumn(5).setMinWidth(120);
            tbProcessosPorData.getColumnModel().getColumn(5).setPreferredWidth(120);
            tbProcessosPorData.getColumnModel().getColumn(5).setMaxWidth(120);
            tbProcessosPorData.getColumnModel().getColumn(6).setMinWidth(0);
            tbProcessosPorData.getColumnModel().getColumn(6).setPreferredWidth(0);
            tbProcessosPorData.getColumnModel().getColumn(6).setMaxWidth(0);
        }

        kGradientPanel7.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, 660, -1));

        jPanelBackGround.add(kGradientPanel7);

        kGradientPanel25.setkBorderRadius(0);
        kGradientPanel25.setkEndColor(new java.awt.Color(255, 255, 255));
        kGradientPanel25.setkGradientFocus(0);
        kGradientPanel25.setkStartColor(new java.awt.Color(234, 239, 243));
        kGradientPanel25.setMaximumSize(new java.awt.Dimension(2147483647, 100));
        kGradientPanel25.setMinimumSize(new java.awt.Dimension(10, 5));
        kGradientPanel25.setPreferredSize(new java.awt.Dimension(32767, 60));
        kGradientPanel25.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanelBackGround.add(kGradientPanel25);

        jPBase.add(jPanelBackGround);

        jPanelBackGround1.setBackground(new java.awt.Color(255, 255, 255));
        jPanelBackGround1.setMaximumSize(new java.awt.Dimension(32767, 670));
        jPanelBackGround1.setMinimumSize(new java.awt.Dimension(1, 1));
        jPanelBackGround1.setPreferredSize(new java.awt.Dimension(680, 580));

        kGradientPanel4.setkBorderRadius(0);
        kGradientPanel4.setkEndColor(new java.awt.Color(234, 239, 243));
        kGradientPanel4.setkGradientFocus(0);
        kGradientPanel4.setkStartColor(new java.awt.Color(255, 255, 255));
        kGradientPanel4.setPreferredSize(new java.awt.Dimension(32767, 45));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("PROCESSOS EM ANDAMENTOS");

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/information.png"))); // NOI18N

        kButtonPendentes3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/24x24/Refresh.png"))); // NOI18N
        kButtonPendentes3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        kButtonPendentes3.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        kButtonPendentes3.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        kButtonPendentes3.setIconTextGap(20);
        kButtonPendentes3.setkAllowGradient(false);
        kButtonPendentes3.setkAllowTab(false);
        kButtonPendentes3.setkBackGroundColor(new java.awt.Color(255, 255, 255));
        kButtonPendentes3.setkBorderRadius(0);
        kButtonPendentes3.setkEndColor(new java.awt.Color(234, 239, 243));
        kButtonPendentes3.setkForeGround(new java.awt.Color(51, 51, 51));
        kButtonPendentes3.setkHoverEndColor(new java.awt.Color(234, 239, 243));
        kButtonPendentes3.setkHoverForeGround(new java.awt.Color(51, 51, 51));
        kButtonPendentes3.setkHoverStartColor(new java.awt.Color(234, 239, 243));
        kButtonPendentes3.setkPressedColor(new java.awt.Color(234, 239, 243));
        kButtonPendentes3.setkSelectedColor(new java.awt.Color(197, 201, 206));
        kButtonPendentes3.setkStartColor(new java.awt.Color(255, 255, 255));
        kButtonPendentes3.setPreferredSize(new java.awt.Dimension(60, 50));
        kButtonPendentes3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButtonPendentes3ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout kGradientPanel4Layout = new org.jdesktop.layout.GroupLayout(kGradientPanel4);
        kGradientPanel4.setLayout(kGradientPanel4Layout);
        kGradientPanel4Layout.setHorizontalGroup(
            kGradientPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, kGradientPanel4Layout.createSequentialGroup()
                .addContainerGap(16066, Short.MAX_VALUE)
                .add(jLabel13)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel12, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 229, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(317, 317, 317)
                .add(kButtonPendentes3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(16064, 16064, 16064))
        );
        kGradientPanel4Layout.setVerticalGroup(
            kGradientPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(kGradientPanel4Layout.createSequentialGroup()
                .add(kGradientPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(kGradientPanel4Layout.createSequentialGroup()
                        .add(kGradientPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(jLabel13, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(jLabel12, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 40, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(0, 0, Short.MAX_VALUE))
                    .add(kGradientPanel4Layout.createSequentialGroup()
                        .add(0, 0, Short.MAX_VALUE)
                        .add(kButtonPendentes3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanelBackGround1.add(kGradientPanel4);

        kGradientPanel8.setkBorderRadius(0);
        kGradientPanel8.setkEndColor(new java.awt.Color(146, 171, 197));
        kGradientPanel8.setkGradientFocus(0);
        kGradientPanel8.setkStartColor(new java.awt.Color(255, 255, 255));
        kGradientPanel8.setPreferredSize(new java.awt.Dimension(940, 200));
        kGradientPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane7.setBackground(new java.awt.Color(247, 247, 247));
        jScrollPane7.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane7.setPreferredSize(new java.awt.Dimension(1220, 180));

        tbProcessosAndamento.setAutoCreateRowSorter(true);
        tbProcessosAndamento.setBackground(new java.awt.Color(247, 247, 247));
        tbProcessosAndamento.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        tbProcessosAndamento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cod.", "Seguradora", "Nº Sinistro", "Segurado", "Data Entrada", "Honorário", "Situação"
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
        jScrollPane7.setViewportView(tbProcessosAndamento);
        if (tbProcessosAndamento.getColumnModel().getColumnCount() > 0) {
            tbProcessosAndamento.getColumnModel().getColumn(0).setMinWidth(80);
            tbProcessosAndamento.getColumnModel().getColumn(0).setPreferredWidth(80);
            tbProcessosAndamento.getColumnModel().getColumn(0).setMaxWidth(80);
            tbProcessosAndamento.getColumnModel().getColumn(1).setMinWidth(200);
            tbProcessosAndamento.getColumnModel().getColumn(3).setMinWidth(0);
            tbProcessosAndamento.getColumnModel().getColumn(3).setPreferredWidth(0);
            tbProcessosAndamento.getColumnModel().getColumn(3).setMaxWidth(0);
            tbProcessosAndamento.getColumnModel().getColumn(4).setMinWidth(120);
            tbProcessosAndamento.getColumnModel().getColumn(4).setPreferredWidth(120);
            tbProcessosAndamento.getColumnModel().getColumn(4).setMaxWidth(120);
            tbProcessosAndamento.getColumnModel().getColumn(5).setMinWidth(0);
            tbProcessosAndamento.getColumnModel().getColumn(5).setPreferredWidth(0);
            tbProcessosAndamento.getColumnModel().getColumn(5).setMaxWidth(0);
        }

        kGradientPanel8.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, 650, 170));

        jPanelBackGround1.add(kGradientPanel8);

        kGradientPanel9.setkBorderRadius(0);
        kGradientPanel9.setkEndColor(new java.awt.Color(234, 239, 243));
        kGradientPanel9.setkGradientFocus(0);
        kGradientPanel9.setkStartColor(new java.awt.Color(255, 255, 255));
        kGradientPanel9.setPreferredSize(new java.awt.Dimension(32767, 45));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        jLabel14.setText("PROCESSOS AGUARDANDO PAGAMENTO");

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/exclamation.png"))); // NOI18N

        kButtonPendentes4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/24x24/Refresh.png"))); // NOI18N
        kButtonPendentes4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        kButtonPendentes4.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        kButtonPendentes4.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        kButtonPendentes4.setIconTextGap(20);
        kButtonPendentes4.setkAllowGradient(false);
        kButtonPendentes4.setkAllowTab(false);
        kButtonPendentes4.setkBackGroundColor(new java.awt.Color(255, 255, 255));
        kButtonPendentes4.setkBorderRadius(0);
        kButtonPendentes4.setkEndColor(new java.awt.Color(234, 239, 243));
        kButtonPendentes4.setkForeGround(new java.awt.Color(51, 51, 51));
        kButtonPendentes4.setkHoverEndColor(new java.awt.Color(234, 239, 243));
        kButtonPendentes4.setkHoverForeGround(new java.awt.Color(51, 51, 51));
        kButtonPendentes4.setkHoverStartColor(new java.awt.Color(234, 239, 243));
        kButtonPendentes4.setkPressedColor(new java.awt.Color(234, 239, 243));
        kButtonPendentes4.setkSelectedColor(new java.awt.Color(197, 201, 206));
        kButtonPendentes4.setkStartColor(new java.awt.Color(255, 255, 255));
        kButtonPendentes4.setPreferredSize(new java.awt.Dimension(60, 50));
        kButtonPendentes4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButtonPendentes4ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout kGradientPanel9Layout = new org.jdesktop.layout.GroupLayout(kGradientPanel9);
        kGradientPanel9.setLayout(kGradientPanel9Layout);
        kGradientPanel9Layout.setHorizontalGroup(
            kGradientPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, kGradientPanel9Layout.createSequentialGroup()
                .addContainerGap(16070, Short.MAX_VALUE)
                .add(jLabel15)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel14)
                .add(252, 252, 252)
                .add(kButtonPendentes4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(16066, 16066, 16066))
        );
        kGradientPanel9Layout.setVerticalGroup(
            kGradientPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(kGradientPanel9Layout.createSequentialGroup()
                .add(kGradientPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jLabel15, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jLabel14, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(kButtonPendentes4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelBackGround1.add(kGradientPanel9);

        kGradientPanel10.setkBorderRadius(0);
        kGradientPanel10.setkEndColor(new java.awt.Color(146, 171, 197));
        kGradientPanel10.setkGradientFocus(0);
        kGradientPanel10.setkStartColor(new java.awt.Color(255, 255, 255));
        kGradientPanel10.setPreferredSize(new java.awt.Dimension(940, 200));
        kGradientPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane8.setBackground(new java.awt.Color(247, 247, 247));
        jScrollPane8.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane8.setPreferredSize(new java.awt.Dimension(1220, 180));

        tbProcessosAguardandoPgto.setAutoCreateRowSorter(true);
        tbProcessosAguardandoPgto.setBackground(new java.awt.Color(247, 247, 247));
        tbProcessosAguardandoPgto.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        tbProcessosAguardandoPgto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cod.", "Seguradora", "Nº Sinistro", "Segurado", "Data Entrada", "Previsão Pgto.", "Honorário", "Situação"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbProcessosAguardandoPgto.setGridColor(new java.awt.Color(247, 247, 247));
        tbProcessosAguardandoPgto.setMinimumSize(new java.awt.Dimension(610, 0));
        tbProcessosAguardandoPgto.setRowHeight(20);
        tbProcessosAguardandoPgto.setSelectionBackground(new java.awt.Color(0, 153, 0));
        tbProcessosAguardandoPgto.getTableHeader().setReorderingAllowed(false);
        jScrollPane8.setViewportView(tbProcessosAguardandoPgto);
        if (tbProcessosAguardandoPgto.getColumnModel().getColumnCount() > 0) {
            tbProcessosAguardandoPgto.getColumnModel().getColumn(0).setMinWidth(80);
            tbProcessosAguardandoPgto.getColumnModel().getColumn(0).setPreferredWidth(80);
            tbProcessosAguardandoPgto.getColumnModel().getColumn(0).setMaxWidth(80);
            tbProcessosAguardandoPgto.getColumnModel().getColumn(1).setMinWidth(200);
            tbProcessosAguardandoPgto.getColumnModel().getColumn(2).setMinWidth(150);
            tbProcessosAguardandoPgto.getColumnModel().getColumn(2).setPreferredWidth(150);
            tbProcessosAguardandoPgto.getColumnModel().getColumn(2).setMaxWidth(150);
            tbProcessosAguardandoPgto.getColumnModel().getColumn(3).setMinWidth(0);
            tbProcessosAguardandoPgto.getColumnModel().getColumn(3).setPreferredWidth(0);
            tbProcessosAguardandoPgto.getColumnModel().getColumn(3).setMaxWidth(0);
            tbProcessosAguardandoPgto.getColumnModel().getColumn(4).setMinWidth(120);
            tbProcessosAguardandoPgto.getColumnModel().getColumn(4).setPreferredWidth(120);
            tbProcessosAguardandoPgto.getColumnModel().getColumn(4).setMaxWidth(120);
            tbProcessosAguardandoPgto.getColumnModel().getColumn(5).setMinWidth(120);
            tbProcessosAguardandoPgto.getColumnModel().getColumn(5).setPreferredWidth(120);
            tbProcessosAguardandoPgto.getColumnModel().getColumn(5).setMaxWidth(120);
            tbProcessosAguardandoPgto.getColumnModel().getColumn(6).setMinWidth(0);
            tbProcessosAguardandoPgto.getColumnModel().getColumn(6).setPreferredWidth(0);
            tbProcessosAguardandoPgto.getColumnModel().getColumn(6).setMaxWidth(0);
        }

        kGradientPanel10.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, 650, -1));

        jPanelBackGround1.add(kGradientPanel10);

        kGradientPanel26.setkBorderRadius(0);
        kGradientPanel26.setkEndColor(new java.awt.Color(255, 255, 255));
        kGradientPanel26.setkGradientFocus(0);
        kGradientPanel26.setkStartColor(new java.awt.Color(234, 239, 243));
        kGradientPanel26.setMaximumSize(new java.awt.Dimension(2147483647, 100));
        kGradientPanel26.setMinimumSize(new java.awt.Dimension(10, 5));
        kGradientPanel26.setPreferredSize(new java.awt.Dimension(32767, 60));
        kGradientPanel26.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/jmmenu.png"))); // NOI18N
        kGradientPanel26.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(16580, 10, 120, -1));

        jPanelBackGround1.add(kGradientPanel26);

        jPBase.add(jPanelBackGround1);

        kGradientPanel3.setBackground(new java.awt.Color(255, 255, 255));
        kGradientPanel3.setkBorderRadius(0);
        kGradientPanel3.setkEndColor(new java.awt.Color(255, 255, 255));
        kGradientPanel3.setkGradientFocus(0);
        kGradientPanel3.setkStartColor(new java.awt.Color(255, 255, 255));
        kGradientPanel3.setMinimumSize(new java.awt.Dimension(90, 67));
        kGradientPanel3.setPreferredSize(new java.awt.Dimension(90, 110));
        kGradientPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPBase.add(kGradientPanel3);
        jPBase.add(jSeparator4);
        jPBase.add(jSeparator5);

        getContentPane().add(jPBase, java.awt.BorderLayout.CENTER);

        jMenuBar.setBackground(new java.awt.Color(255, 255, 255));
        jMenuBar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jMenuBar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jMenuBar.setFocusable(false);
        jMenuBar.setMaximumSize(new java.awt.Dimension(32767, 32767));
        jMenuBar.setPreferredSize(new java.awt.Dimension(200, 60));

        jMRepassesDiretoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/24x24/User group.png"))); // NOI18N
        jMRepassesDiretoria.setText("REPASSES DE AGENTES");
        jMRepassesDiretoria.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMRepassesDiretoria.setIconTextGap(12);

        jmControleAgentes.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jmControleAgentes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Boss.png"))); // NOI18N
        jmControleAgentes.setText("Controle de Agentes");
        jmControleAgentes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmControleAgentesActionPerformed(evt);
            }
        });
        jMRepassesDiretoria.add(jmControleAgentes);

        PlanilhasDeAgentes.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        PlanilhasDeAgentes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/3d bar chart.png"))); // NOI18N
        PlanilhasDeAgentes.setText("Planilhas de Agentes");
        PlanilhasDeAgentes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PlanilhasDeAgentesActionPerformed(evt);
            }
        });
        jMRepassesDiretoria.add(PlanilhasDeAgentes);

        jMenuBar.add(jMRepassesDiretoria);

        jMProcucao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/24x24/Application.png"))); // NOI18N
        jMProcucao.setText("PRODUÇÃO");
        jMProcucao.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMProcucao.setIconTextGap(12);
        jMProcucao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMProcucaoActionPerformed(evt);
            }
        });

        jmProcessos2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_MASK));
        jmProcessos2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Application.png"))); // NOI18N
        jmProcessos2.setText("Processos");
        jmProcessos2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmProcessos2ActionPerformed(evt);
            }
        });
        jMProcucao.add(jmProcessos2);

        mnProcessosDeletados.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.SHIFT_MASK));
        mnProcessosDeletados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/lixo.png"))); // NOI18N
        mnProcessosDeletados.setText("Processos Deletados");
        mnProcessosDeletados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnProcessosDeletadosActionPerformed(evt);
            }
        });
        jMProcucao.add(mnProcessosDeletados);

        jMenuBar.add(jMProcucao);

        jMConsultaRedator.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/24x24/Find.png"))); // NOI18N
        jMConsultaRedator.setText("CONSULTA");
        jMConsultaRedator.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMConsultaRedator.setIconTextGap(12);
        jMConsultaRedator.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMConsultaRedatorMouseClicked(evt);
            }
        });
        jMConsultaRedator.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMConsultaRedatorActionPerformed(evt);
            }
        });
        jMenuBar.add(jMConsultaRedator);

        jMCadastros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/24x24/Table.png"))); // NOI18N
        jMCadastros.setText("CADASTROS");
        jMCadastros.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMCadastros.setIconTextGap(12);

        jmSeguradorasR.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK));
        jmSeguradorasR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/24x24/seg24x24.png"))); // NOI18N
        jmSeguradorasR.setText("Seguradoras");
        jmSeguradorasR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmSeguradorasRActionPerformed(evt);
            }
        });
        jMCadastros.add(jmSeguradorasR);

        jmFuncionarios.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.ALT_MASK));
        jmFuncionarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/User group.png"))); // NOI18N
        jmFuncionarios.setText("Funcionários");
        jmFuncionarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmFuncionariosActionPerformed(evt);
            }
        });
        jMCadastros.add(jmFuncionarios);

        jmAliquiota.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jmAliquiota.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Calendar.png"))); // NOI18N
        jmAliquiota.setText("Alíquota");
        jmAliquiota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmAliquiotaActionPerformed(evt);
            }
        });
        jMCadastros.add(jmAliquiota);

        jmCoberturas.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jmCoberturas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Application.png"))); // NOI18N
        jmCoberturas.setText("Coberturas");
        jmCoberturas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmCoberturasActionPerformed(evt);
            }
        });
        jMCadastros.add(jmCoberturas);

        jmCadCidades.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.SHIFT_MASK));
        jmCadCidades.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Flag.png"))); // NOI18N
        jmCadCidades.setText("Cadastro de Cidades");
        jmCadCidades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmCadCidadesActionPerformed(evt);
            }
        });
        jMCadastros.add(jmCadCidades);

        jmCadTipoDeContas.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.SHIFT_MASK));
        jmCadTipoDeContas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Up-down.png"))); // NOI18N
        jmCadTipoDeContas.setText("Tipo de Contas");
        jmCadTipoDeContas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmCadTipoDeContasActionPerformed(evt);
            }
        });
        jMCadastros.add(jmCadTipoDeContas);

        jMenuBar.add(jMCadastros);

        jMFInanceiro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/24x24/Dollar.png"))); // NOI18N
        jMFInanceiro.setText("FINANCEIRO");
        jMFInanceiro.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMFInanceiro.setIconTextGap(12);

        jmContasPagarF.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jmContasPagarF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Fall.png"))); // NOI18N
        jmContasPagarF.setText("Contas a Pagar");
        jmContasPagarF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmContasPagarFActionPerformed(evt);
            }
        });
        jMFInanceiro.add(jmContasPagarF);

        jmContasReceber.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jmContasReceber.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Up.png"))); // NOI18N
        jmContasReceber.setText("Contas a Receber");
        jmContasReceber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmContasReceberActionPerformed(evt);
            }
        });
        jMFInanceiro.add(jmContasReceber);

        jMenuBar.add(jMFInanceiro);

        jMRelatorios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/24x24/Report.png"))); // NOI18N
        jMRelatorios.setText("RELATÓRIOS");
        jMRelatorios.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMRelatorios.setIconTextGap(12);

        jmControleDeDados.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.SHIFT_MASK));
        jmControleDeDados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/3d bar chart.png"))); // NOI18N
        jmControleDeDados.setText("Controle de Dados");
        jmControleDeDados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmControleDeDadosActionPerformed(evt);
            }
        });
        jMRelatorios.add(jmControleDeDados);

        jmControleDeContas.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.SHIFT_MASK));
        jmControleDeContas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Pie chart.png"))); // NOI18N
        jmControleDeContas.setText("Controle de Contas");
        jmControleDeContas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmControleDeContasActionPerformed(evt);
            }
        });
        jMRelatorios.add(jmControleDeContas);

        jmControleProcessos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.SHIFT_MASK));
        jmControleProcessos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Report.png"))); // NOI18N
        jmControleProcessos.setText("Controle de Processos");
        jmControleProcessos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmControleProcessosActionPerformed(evt);
            }
        });
        jMRelatorios.add(jmControleProcessos);

        jMenuBar.add(jMRelatorios);

        jMCadastrosAdm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/24x24/Database.png"))); // NOI18N
        jMCadastrosAdm.setText("REGISTROS");
        jMCadastrosAdm.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMCadastrosAdm.setIconTextGap(12);

        jmUsuarioSistema.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.SHIFT_MASK));
        jmUsuarioSistema.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/People.png"))); // NOI18N
        jmUsuarioSistema.setText("Usuário do Sistema");
        jmUsuarioSistema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmFuncionarioActionPerformed(evt);
            }
        });
        jMCadastrosAdm.add(jmUsuarioSistema);

        jmOcupacaoCad.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.SHIFT_MASK));
        jmOcupacaoCad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Boss.png"))); // NOI18N
        jmOcupacaoCad.setText("Cargos e Ocupações");
        jmOcupacaoCad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmOcupacaoCadActionPerformed(evt);
            }
        });
        jMCadastrosAdm.add(jmOcupacaoCad);

        jmVinculosContratuias.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.SHIFT_MASK));
        jmVinculosContratuias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Component.png"))); // NOI18N
        jmVinculosContratuias.setText("Vínculos Contratuais");
        jmVinculosContratuias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmVinculosContratuiasActionPerformed(evt);
            }
        });
        jMCadastrosAdm.add(jmVinculosContratuias);

        jMenuBar.add(jMCadastrosAdm);

        jMConsultaAgentes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/24x24/Text preview.png"))); // NOI18N
        jMConsultaAgentes.setText("CONSULTA PARA AGENTES");
        jMConsultaAgentes.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMConsultaAgentes.setIconTextGap(12);
        jMConsultaAgentes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMConsultaAgentesMouseClicked(evt);
            }
        });
        jMConsultaAgentes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMConsultaAgentesActionPerformed(evt);
            }
        });

        jmMyProcessos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.ALT_MASK));
        jmMyProcessos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Pinion.png"))); // NOI18N
        jmMyProcessos.setText("Processos");
        jmMyProcessos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmMyProcessosActionPerformed(evt);
            }
        });
        jMConsultaAgentes.add(jmMyProcessos);

        jmMyRepasses.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.ALT_MASK));
        jmMyRepasses.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Dollar.png"))); // NOI18N
        jmMyRepasses.setText("Repasses");
        jmMyRepasses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmMyRepassesActionPerformed(evt);
            }
        });
        jMConsultaAgentes.add(jmMyRepasses);

        jMenuBar.add(jMConsultaAgentes);

        jmLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/24x24/Turn off.png"))); // NOI18N
        jmLogout.setText("LOGOUT");
        jmLogout.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jmLogout.setIconTextGap(12);
        jmLogout.add(jSeparator1);

        mnuLogOut.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        mnuLogOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Lock.png"))); // NOI18N
        mnuLogOut.setText("Trocar de Usuário");
        mnuLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuLogOutActionPerformed(evt);
            }
        });
        jmLogout.add(mnuLogOut);

        mnuSair.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        mnuSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Close.png"))); // NOI18N
        mnuSair.setText("Sair");
        mnuSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuSairActionPerformed(evt);
            }
        });
        jmLogout.add(mnuSair);

        jMenuBar.add(jmLogout);

        setJMenuBar(jMenuBar);

        getAccessibleContext().setAccessibleDescription("");

        setSize(new java.awt.Dimension(1410, 806));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void mnuSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuSairActionPerformed
        System.exit(0);
    }//GEN-LAST:event_mnuSairActionPerformed

    private void jmVinculosContratuiasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmVinculosContratuiasActionPerformed
        // TODO add your handling code here:
        new ViewCadVinculo().setVisible(true);
    }//GEN-LAST:event_jmVinculosContratuiasActionPerformed

    private void jmOcupacaoCadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmOcupacaoCadActionPerformed
        // TODO add your handling code here:
        new ViewCadOcupacao().setVisible(true);
    }//GEN-LAST:event_jmOcupacaoCadActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        encerrar();
    }//GEN-LAST:event_formWindowClosing

    private void jmControleAgentesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmControleAgentesActionPerformed
        // TODO add your handling code here:
        new ViewCadDespesasAgentes().setVisible(true);

    }//GEN-LAST:event_jmControleAgentesActionPerformed

    private void jmFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmFuncionarioActionPerformed
        // TODO add your handling code here:
        new ViewCadUser().setVisible(true);
    }//GEN-LAST:event_jmFuncionarioActionPerformed

    int codigo = 0;
    static String tipoCadastro = null;
    private void kButtonRELCONTASActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButtonRELCONTASActionPerformed
        // TODO add your handling code here:
        new ViewFluxoContasDespesas().setVisible(true);

    }//GEN-LAST:event_kButtonRELCONTASActionPerformed

    private void kButtonRELProcessosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButtonRELProcessosActionPerformed
        // TODO add your handling code here:
        new ViewControleProcessos().setVisible(true);
    }//GEN-LAST:event_kButtonRELProcessosActionPerformed

    private void kButtonCOBERTURASActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButtonCOBERTURASActionPerformed
        // TODO add your handling code here:
        new ViewCobertura().setVisible(true);
    }//GEN-LAST:event_kButtonCOBERTURASActionPerformed

    private void kButtonFUNCIONARIOSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButtonFUNCIONARIOSActionPerformed
        // TODO add your handling code here:
        new ViewFuncionario().setVisible(true);
    }//GEN-LAST:event_kButtonFUNCIONARIOSActionPerformed

    private void kButtonSEGURADORASActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButtonSEGURADORASActionPerformed
        // TODO add your handling code here:
        new ViewSeguradora().setVisible(true);
    }//GEN-LAST:event_kButtonSEGURADORASActionPerformed

    private void kButtonALICOTAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButtonALICOTAActionPerformed
        // TODO add your handling code here:
        new ViewCadISS().setVisible(true);
    }//GEN-LAST:event_kButtonALICOTAActionPerformed

    private void kButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButton9ActionPerformed
        // TODO add your handling code here:
        new ViewContasPagar().setVisible(true);
    }//GEN-LAST:event_kButton9ActionPerformed

    private void kButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButton10ActionPerformed
        // TODO add your handling code here:
        new ViewContasReceber().setVisible(true);
    }//GEN-LAST:event_kButton10ActionPerformed

    private void kButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButton11ActionPerformed
        // TODO add your handling code here:
        new ViewOp().setVisible(true);
    }//GEN-LAST:event_kButton11ActionPerformed

    private void jmControleProcessosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmControleProcessosActionPerformed
        // TODO add your handling code here:
        new ViewControleProcessos().setVisible(true);
    }//GEN-LAST:event_jmControleProcessosActionPerformed

    private void jmSeguradorasRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmSeguradorasRActionPerformed
        // TODO add your handling code here:
        new ViewSeguradora().setVisible(true);
    }//GEN-LAST:event_jmSeguradorasRActionPerformed

    private void jmContasPagarFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmContasPagarFActionPerformed
        // TODO add your handling code here:
        new ViewContasPagar().setVisible(true);
    }//GEN-LAST:event_jmContasPagarFActionPerformed

    private void jmControleDeContasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmControleDeContasActionPerformed
        // TODO add your handling code here:
        new ViewFluxoContasDespesas().setVisible(true);
    }//GEN-LAST:event_jmControleDeContasActionPerformed

    private void jmControleDeDadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmControleDeDadosActionPerformed
        // TODO add your handling code here:
        new ViewFluxoDados().setVisible(true);
    }//GEN-LAST:event_jmControleDeDadosActionPerformed

    private void jmFuncionariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmFuncionariosActionPerformed
        // TODO add your handling code here:
        new ViewFuncionario().setVisible(true);
    }//GEN-LAST:event_jmFuncionariosActionPerformed

    private void jmAliquiotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmAliquiotaActionPerformed
        // TODO add your handling code here:
        new ViewCadISS().setVisible(true);
    }//GEN-LAST:event_jmAliquiotaActionPerformed

    private void jmCoberturasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmCoberturasActionPerformed
        // TODO add your handling code here:
        new ViewCobertura().setVisible(true);
    }//GEN-LAST:event_jmCoberturasActionPerformed

    private void jmContasReceberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmContasReceberActionPerformed
        // TODO add your handling code here:
        new ViewContasReceber().setVisible(true);
    }//GEN-LAST:event_jmContasReceberActionPerformed

    private void PlanilhasDeAgentesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PlanilhasDeAgentesActionPerformed
        // TODO add your handling code here:
        new ViewDespesasAgentes().setVisible(true);
    }//GEN-LAST:event_PlanilhasDeAgentesActionPerformed

    private void mnProcessosDeletadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnProcessosDeletadosActionPerformed
        // TODO add your handling code here:
        new ViewOpDeletados().setVisible(true);
    }//GEN-LAST:event_mnProcessosDeletadosActionPerformed

    private void jMProcucaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMProcucaoActionPerformed
        // TODO add your handling code here:
        new ViewOp().setVisible(true);
    }//GEN-LAST:event_jMProcucaoActionPerformed

    private void jmProcessos2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmProcessos2ActionPerformed
        // TODO add your handling code here:
        new ViewOp().setVisible(true);
    }//GEN-LAST:event_jmProcessos2ActionPerformed

    private void jmControleAgentesFastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmControleAgentesFastActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jmControleAgentesFastActionPerformed

    private void jMConsultaRedatorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMConsultaRedatorActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jMConsultaRedatorActionPerformed

    private void jMConsultaRedatorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMConsultaRedatorMouseClicked
        // TODO add your handling code here:
        new ViewOpConsulta().setVisible(true);
    }//GEN-LAST:event_jMConsultaRedatorMouseClicked

    private void kButtonPendentes1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButtonPendentes1ActionPerformed
        // TODO add your handling code here:
        carregarProcessosReceber();
        carregarProcessosVencimento();
        carregarProcessosAndamento();
        carregarProcessosAguardandoPgto();
    }//GEN-LAST:event_kButtonPendentes1ActionPerformed

    private void kButtonPendentes2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButtonPendentes2ActionPerformed
        // TODO add your handling code here:
        carregarProcessosReceber();
        carregarProcessosVencimento();
        carregarProcessosAndamento();
        carregarProcessosAguardandoPgto();
    }//GEN-LAST:event_kButtonPendentes2ActionPerformed

    private void kButtonPendentes3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButtonPendentes3ActionPerformed
        // TODO add your handling code here:
        carregarProcessosReceber();
        carregarProcessosVencimento();
        carregarProcessosAndamento();
        carregarProcessosAguardandoPgto();
    }//GEN-LAST:event_kButtonPendentes3ActionPerformed

    private void kButtonPendentes4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButtonPendentes4ActionPerformed
        // TODO add your handling code here:
        carregarProcessosReceber();
        carregarProcessosVencimento();
        carregarProcessosAndamento();
        carregarProcessosAguardandoPgto();
    }//GEN-LAST:event_kButtonPendentes4ActionPerformed

    private void mnuLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuLogOutActionPerformed
        // TODO add your handling code here:

        new ViewLogin().setVisible(true);
        dispose();
        op.dispose();
    }//GEN-LAST:event_mnuLogOutActionPerformed

    private void jMConsultaAgentesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMConsultaAgentesMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jMConsultaAgentesMouseClicked

    private void jMConsultaAgentesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMConsultaAgentesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMConsultaAgentesActionPerformed

    private void jmMyProcessosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmMyProcessosActionPerformed
        // TODO add your handling code here:
        new ViewMyOpConsulta().setVisible(true);
    }//GEN-LAST:event_jmMyProcessosActionPerformed

    private void jmMyRepassesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmMyRepassesActionPerformed
        // TODO add your handling code here:
        new ViewMyDespesasAgentes().setVisible(true);
    }//GEN-LAST:event_jmMyRepassesActionPerformed

    private void jmCadCidadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmCadCidadesActionPerformed
        // TODO add your handling code here:
        new ViewCidade().setVisible(true);
    }//GEN-LAST:event_jmCadCidadesActionPerformed

    private void jmCadTipoDeContasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmCadTipoDeContasActionPerformed
        // TODO add your handling code here:
        new ViewCadTipoContas().setVisible(true);
    }//GEN-LAST:event_jmCadTipoDeContasActionPerformed

    private void kGBPlalinhasAgentesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kGBPlalinhasAgentesActionPerformed
        // TODO add your handling code here:
        new ViewDespesasAgentes().setVisible(true);
    }//GEN-LAST:event_kGBPlalinhasAgentesActionPerformed

    private void kButtonRepassesAgtsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButtonRepassesAgtsActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_kButtonRepassesAgtsActionPerformed

    private void kGControleAgentesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kGControleAgentesActionPerformed
        // TODO add your handling code here:
        new ViewCadDespesasAgentes().setVisible(true);
    }//GEN-LAST:event_kGControleAgentesActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private keeptoo.KGradientPanel KGCadastros;
    private keeptoo.KGradientPanel KGContas;
    private javax.swing.JMenuItem PlanilhasDeAgentes;
    private javax.swing.JTextArea jLDataAtual;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMCadastros;
    private javax.swing.JMenu jMCadastrosAdm;
    private javax.swing.JMenu jMConsultaAgentes;
    private javax.swing.JMenu jMConsultaRedator;
    private javax.swing.JMenu jMFInanceiro;
    private javax.swing.JMenu jMProcucao;
    private javax.swing.JMenu jMRelatorios;
    private javax.swing.JMenu jMRepassesDiretoria;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JPanel jPBase;
    private javax.swing.JPanel jPanelBackGround;
    private javax.swing.JPanel jPanelBackGround1;
    private javax.swing.JPanel jPanelUsuarioEdata;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPaneAnalista;
    private javax.swing.JScrollPane jScrollPaneAnalista1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JMenuItem jmAliquiota;
    private javax.swing.JMenuItem jmCadCidades;
    private javax.swing.JMenuItem jmCadTipoDeContas;
    private javax.swing.JMenuItem jmCoberturas;
    private javax.swing.JMenuItem jmContasPagarF;
    private javax.swing.JMenuItem jmContasReceber;
    private javax.swing.JMenuItem jmControleAgentes;
    private javax.swing.JMenuItem jmControleAgentesFast;
    private javax.swing.JMenuItem jmControleDeContas;
    private javax.swing.JMenuItem jmControleDeDados;
    private javax.swing.JMenuItem jmControleProcessos;
    private javax.swing.JMenuItem jmFuncionarios;
    private javax.swing.JMenu jmLogout;
    private javax.swing.JMenuItem jmMyProcessos;
    private javax.swing.JMenuItem jmMyRepasses;
    private javax.swing.JMenuItem jmOcupacaoCad;
    private javax.swing.JMenuItem jmProcessos2;
    private javax.swing.JMenuItem jmSeguradorasR;
    private javax.swing.JMenuItem jmUsuarioSistema;
    private javax.swing.JMenuItem jmVinculosContratuias;
    private keeptoo.KButton kButton10;
    private keeptoo.KButton kButton11;
    private keeptoo.KButton kButton9;
    private keeptoo.KButton kButtonALICOTA;
    private keeptoo.KButton kButtonCOBERTURAS;
    private keeptoo.KButton kButtonFUNCIONARIOS;
    private keeptoo.KButton kButtonPendentes1;
    private keeptoo.KButton kButtonPendentes2;
    private keeptoo.KButton kButtonPendentes3;
    private keeptoo.KButton kButtonPendentes4;
    private keeptoo.KButton kButtonRELCONTAS;
    private keeptoo.KButton kButtonRELProcessos;
    private keeptoo.KButton kButtonRepassesAgts;
    private keeptoo.KButton kButtonSEGURADORAS;
    private keeptoo.KButton kGBPlalinhasAgentes;
    private keeptoo.KButton kGControleAgentes;
    private keeptoo.KGradientPanel kGProducao;
    private keeptoo.KGradientPanel kGRelaDiretoria;
    private keeptoo.KGradientPanel kGRelaFinanceiro;
    private keeptoo.KGradientPanel kGradientPanel1;
    private keeptoo.KGradientPanel kGradientPanel10;
    private keeptoo.KGradientPanel kGradientPanel2;
    private keeptoo.KGradientPanel kGradientPanel25;
    private keeptoo.KGradientPanel kGradientPanel26;
    private keeptoo.KGradientPanel kGradientPanel3;
    private keeptoo.KGradientPanel kGradientPanel4;
    private keeptoo.KGradientPanel kGradientPanel5;
    private keeptoo.KGradientPanel kGradientPanel6;
    private keeptoo.KGradientPanel kGradientPanel7;
    private keeptoo.KGradientPanel kGradientPanel8;
    private keeptoo.KGradientPanel kGradientPanel9;
    private javax.swing.JMenuItem mnProcessosDeletados;
    private javax.swing.JMenuItem mnuLogOut;
    private javax.swing.JMenuItem mnuSair;
    private javax.swing.JTable tbProcessosAguardandoPgto;
    private javax.swing.JTable tbProcessosAndamento;
    private javax.swing.JTable tbProcessosPorData;
    private javax.swing.JTable tbProcessosReceber;
    private javax.swing.JTextArea txtNome;
    // End of variables declaration//GEN-END:variables

    /**
     *
     */
    ConexaoBanco cc = new ConexaoBanco();
    Connection cn = cc.conectar();

    public static ViewPrincipal principal;

    private void SetIcone() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("ictrol.png")));
    }

}
