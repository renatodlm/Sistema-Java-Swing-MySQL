package View;

import Controller.ControllerCidade;
import Controller.ControllerFuncionario;
import Controller.ControllerFuncionarioCidadeEstado;
import Controller.ControllerEstado;
import Controller.ControllerOcupacao;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Model.ModelFuncionario;
import Model.ModelFuncionarioCidadeEstado;
import Model.ModelOcupacao;
import java.awt.Toolkit;
import java.io.File;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import util.Exporter;

/**
 *
 * @author studiomicroweb
 */
public class ViewFuncionario extends javax.swing.JFrame {

    ArrayList<ModelFuncionarioCidadeEstado> listaModelFuncionarioCidadeEstados = new ArrayList<>();
    ControllerFuncionarioCidadeEstado controllerFuncionarioCidadeEstado = new ControllerFuncionarioCidadeEstado();
    ControllerFuncionario controllerFuncionario = new ControllerFuncionario(); //excluir
    ModelFuncionario modelFuncionario = new ModelFuncionario();
    /*Pesquisar*/
    /*List<ModelFuncionario> listafuncionario = new ArrayList<>();
     DAOFuncionario funcionarioDAO = new DAOFuncionario();*/

    /*Box Cidade e Estado */
    ControllerCidade controllerCidade = new ControllerCidade();
    ControllerEstado controllerEstado = new ControllerEstado();

    //OCUPAÇÃO
    ArrayList<ModelOcupacao> listaModelOcupacao = new ArrayList<>();
    ControllerOcupacao controllerOcupacao = new ControllerOcupacao();
    ModelOcupacao modelOcupacao = new ModelOcupacao();

    public ViewFuncionario() {
        initComponents();
        SetIcone();
        this.carregarFuncionario();
        setLocationRelativeTo(null);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelBackground = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btIncluir = new javax.swing.JButton();
        btEditar = new javax.swing.JButton();
        btExcluir = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        tbFuncionarios = new javax.swing.JTable();
        tfPesquisa = new javax.swing.JTextField();
        btPesquisar = new javax.swing.JButton();
        cbPesquisa = new javax.swing.JComboBox();
        kGradientPanel1 = new keeptoo.KGradientPanel();
        jbVisualizarConta = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        kGradientPanel2 = new keeptoo.KGradientPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(780, 370));
        setResizable(false);
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelBackground.setBackground(new java.awt.Color(255, 255, 255));
        jPanelBackground.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("FUNCIONÁRIOS");
        jPanelBackground.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, -1, -1));

        jLabel3.setBackground(new java.awt.Color(251, 251, 251));
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(242, 242, 242));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/agentes.png"))); // NOI18N
        jPanelBackground.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        btIncluir.setBackground(new java.awt.Color(0, 153, 0));
        btIncluir.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btIncluir.setForeground(new java.awt.Color(255, 255, 255));
        btIncluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/page_add.png"))); // NOI18N
        btIncluir.setText("Incluir");
        btIncluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btIncluirActionPerformed(evt);
            }
        });
        jPanelBackground.add(btIncluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 120, 30));

        btEditar.setBackground(new java.awt.Color(255, 102, 0));
        btEditar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btEditar.setForeground(new java.awt.Color(255, 255, 255));
        btEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/page_edit.png"))); // NOI18N
        btEditar.setText("Editar");
        btEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditarActionPerformed(evt);
            }
        });
        jPanelBackground.add(btEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 90, 120, 30));

        btExcluir.setBackground(new java.awt.Color(255, 0, 0));
        btExcluir.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btExcluir.setForeground(new java.awt.Color(255, 255, 255));
        btExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/page_delete.png"))); // NOI18N
        btExcluir.setText("Excluir");
        btExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExcluirActionPerformed(evt);
            }
        });
        jPanelBackground.add(btExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 90, 120, 30));

        jScrollPane7.setBackground(new java.awt.Color(247, 247, 247));
        jScrollPane7.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane7.setPreferredSize(new java.awt.Dimension(1170, 170));

        tbFuncionarios.setAutoCreateRowSorter(true);
        tbFuncionarios.setBackground(new java.awt.Color(247, 247, 247));
        tbFuncionarios.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        tbFuncionarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cod.", "Ocupação", "Nome", "CPF", "Celular", "Email"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbFuncionarios.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbFuncionarios.setGridColor(new java.awt.Color(247, 247, 247));
        tbFuncionarios.setMinimumSize(new java.awt.Dimension(610, 0));
        tbFuncionarios.setRowHeight(20);
        tbFuncionarios.setSelectionBackground(new java.awt.Color(0, 153, 0));
        tbFuncionarios.getTableHeader().setReorderingAllowed(false);
        jScrollPane7.setViewportView(tbFuncionarios);
        if (tbFuncionarios.getColumnModel().getColumnCount() > 0) {
            tbFuncionarios.getColumnModel().getColumn(0).setMinWidth(50);
            tbFuncionarios.getColumnModel().getColumn(0).setPreferredWidth(50);
            tbFuncionarios.getColumnModel().getColumn(0).setMaxWidth(50);
            tbFuncionarios.getColumnModel().getColumn(1).setMinWidth(150);
            tbFuncionarios.getColumnModel().getColumn(1).setPreferredWidth(150);
            tbFuncionarios.getColumnModel().getColumn(1).setMaxWidth(150);
            tbFuncionarios.getColumnModel().getColumn(2).setMinWidth(200);
            tbFuncionarios.getColumnModel().getColumn(3).setMinWidth(200);
            tbFuncionarios.getColumnModel().getColumn(3).setPreferredWidth(200);
            tbFuncionarios.getColumnModel().getColumn(3).setMaxWidth(200);
            tbFuncionarios.getColumnModel().getColumn(4).setMinWidth(200);
            tbFuncionarios.getColumnModel().getColumn(4).setPreferredWidth(200);
            tbFuncionarios.getColumnModel().getColumn(4).setMaxWidth(200);
            tbFuncionarios.getColumnModel().getColumn(5).setMinWidth(200);
        }

        jPanelBackground.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 1020, 290));

        tfPesquisa.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tfPesquisa.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfPesquisaFocusLost(evt);
            }
        });
        tfPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfPesquisaKeyPressed(evt);
            }
        });
        jPanelBackground.add(tfPesquisa, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 90, 270, 30));

        btPesquisar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btPesquisar.setForeground(new java.awt.Color(51, 51, 51));
        btPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/24x24/Find.png"))); // NOI18N
        btPesquisar.setText("BUSCAR");
        btPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisarActionPerformed(evt);
            }
        });
        jPanelBackground.add(btPesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 90, 120, 30));

        cbPesquisa.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        cbPesquisa.setForeground(new java.awt.Color(51, 51, 51));
        cbPesquisa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Codigo", "Nome", "Cpf/Cnpj" }));
        jPanelBackground.add(cbPesquisa, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 90, 80, 30));

        kGradientPanel1.setkBorderRadius(0);
        kGradientPanel1.setkEndColor(new java.awt.Color(234, 239, 243));
        kGradientPanel1.setkGradientFocus(-5);
        kGradientPanel1.setkStartColor(new java.awt.Color(255, 255, 255));
        kGradientPanel1.setPreferredSize(new java.awt.Dimension(520, 210));
        kGradientPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jbVisualizarConta.setBackground(new java.awt.Color(0, 153, 0));
        jbVisualizarConta.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbVisualizarConta.setForeground(new java.awt.Color(255, 255, 255));
        jbVisualizarConta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/excel.png"))); // NOI18N
        jbVisualizarConta.setText("EXPORTAR");
        jbVisualizarConta.setPreferredSize(new java.awt.Dimension(150, 40));
        jbVisualizarConta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbVisualizarContaActionPerformed(evt);
            }
        });
        kGradientPanel1.add(jbVisualizarConta, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 260, -1, -1));

        jPanelBackground.add(kGradientPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 1100, 320));

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/page_refresh.png"))); // NOI18N
        jButton8.setPreferredSize(new java.awt.Dimension(60, 40));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanelBackground.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 80, -1, -1));

        kGradientPanel2.setkBorderRadius(0);
        kGradientPanel2.setkEndColor(new java.awt.Color(234, 239, 243));
        kGradientPanel2.setkGradientFocus(0);
        kGradientPanel2.setkStartColor(new java.awt.Color(255, 255, 255));
        jPanelBackground.add(kGradientPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 1100, 70));

        getContentPane().add(jPanelBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1100, 520));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btIncluirActionPerformed
        tipoCadastro = "novo";
        new ViewCadFuncionario().setVisible(true);
    }//GEN-LAST:event_btIncluirActionPerformed

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
        carregarFuncionario(); //carregar dados
    }//GEN-LAST:event_formFocusGained

    private void btExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirActionPerformed
        int linha = tbFuncionarios.getSelectedRow();
        String nome = (String) tbFuncionarios.getValueAt(linha, 1);
        int codigo = (int) tbFuncionarios.getValueAt(linha, 0);

        //pegunta se realmente deseja excluir
        int opcao = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja"
                + " excluir o registro:\n" + "\n " + nome + "?", "Atenção", JOptionPane.YES_NO_OPTION);
        //se sim exclui, se não não faz nada    
        if (opcao == JOptionPane.OK_OPTION) {
            if (controllerFuncionario.excluirFuncionarioController(codigo)) {
                JOptionPane.showMessageDialog(this, "Registro excluido com suscesso!");
                carregarFuncionario();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao e os dados!", "ERRO", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btExcluirActionPerformed

    static int codigo = 0;
    static String tipoCadastro = null;

    private void btEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditarActionPerformed
        //System.out.println("valor altera: " + altera);
        tipoCadastro = "alteracao";

        int linha = tbFuncionarios.getSelectedRow();
        String nome = (String) tbFuncionarios.getValueAt(linha, 1);
        codigo = (int) tbFuncionarios.getValueAt(linha, 0);

        modelFuncionario = controllerFuncionario.getFuncionarioController(codigo);

        if (modelFuncionario != null) /*System.out.println(modelFuncionario.getNome());*/ {
            new ViewCadFuncionario().setVisible(true);
        }
    }//GEN-LAST:event_btEditarActionPerformed
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
                new ViewFuncionario().setVisible(true);
            }
        });
    }
    private void btPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarActionPerformed

        String seleciona = "";
        String conteudo = tfPesquisa.getText();
        int i = cbPesquisa.getSelectedIndex();
        switch (i) {
            case 0:
                seleciona = "funcionarios.codigo";
                break;
            case 1:
                seleciona = "funcionarios.nome";
                break;
            case 2:
                seleciona = "funcionarios.cpf";
                break;
            case 3:
                seleciona = "cidade.nome";
                break;
            default:
                System.out.println("Não selecionado");
                break;
        }
        try {
            listaModelFuncionarioCidadeEstados = controllerFuncionarioCidadeEstado.pesquisaListafuncionarioCidadeEstadoController(seleciona, conteudo);
            pesquisa(listaModelFuncionarioCidadeEstados);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Conteúdo não encontrado!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_btPesquisarActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        this.carregarFuncionario();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void tfPesquisaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfPesquisaFocusLost
        // TODO add your handling code here:
        tfPesquisa.setText(tfPesquisa.getText().toUpperCase());
    }//GEN-LAST:event_tfPesquisaFocusLost

    private void jbVisualizarContaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbVisualizarContaActionPerformed

        if (tbFuncionarios.getRowCount() > 0) {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de excel", "xls");
            chooser.setFileFilter(filter);
            chooser.setDialogTitle("Guardar archivo");
            chooser.setAcceptAllFileFilterUsed(false);
            if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                List<JTable> tb = new ArrayList<JTable>();
                List<String> nom = new ArrayList<String>();
                tb.add(tbFuncionarios);
                nom.add("Personas");
                String file = chooser.getSelectedFile().toString().concat(".xls");
                try {
                    Exporter e = new Exporter(new File(file), tb, nom);
                    if (e.export()) {
                        JOptionPane.showMessageDialog(null, "Os dados foram exportados com sucesso!", "ARQUIVO EXCEL", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Hubo un error " + e.getMessage(), " Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Não há dados para exportar na tabela", "ATENÇÃO", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jbVisualizarContaActionPerformed

    private void tfPesquisaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPesquisaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == evt.VK_ENTER) {
            String seleciona = "";
            String conteudo = tfPesquisa.getText();
            int i = cbPesquisa.getSelectedIndex();
            switch (i) {
                case 0:
                    seleciona = "funcionarios.codigo";
                    break;
                case 1:
                    seleciona = "funcionarios.nome";
                    break;
                case 2:
                    seleciona = "funcionarios.cpf";
                    break;
                case 3:
                    seleciona = "cidade.nome";
                    break;
                default:
                    System.out.println("Não selecionado");
                    break;
            }
            try {
                listaModelFuncionarioCidadeEstados = controllerFuncionarioCidadeEstado.pesquisaListafuncionarioCidadeEstadoController(seleciona, conteudo);
                pesquisa(listaModelFuncionarioCidadeEstados);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Conteúdo não encontrado!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_tfPesquisaKeyPressed

    public void carregarFuncionario() {
        listaModelFuncionarioCidadeEstados = controllerFuncionarioCidadeEstado.getListaFuncionarioCidadeEstadoController();
        listaModelOcupacao = controllerOcupacao.getListaOcupacaoController();
        DefaultTableModel modelo = (DefaultTableModel) tbFuncionarios.getModel();
        modelo.setNumRows(0);
        //CARREGA OS DADOS DA LISTA NA TABELA
        int cont = listaModelFuncionarioCidadeEstados.size();
        for (int i = 0; i < cont; i++) {
            String Ocupacao = controllerOcupacao.getOcupacaoControllerCod(listaModelFuncionarioCidadeEstados.get(i).getModelFuncionario().getOcupacao()).getOcup_descricao();
            //System.out.println(Ocupacao);
            modelo.addRow(new Object[]{
                listaModelFuncionarioCidadeEstados.get(i).getModelFuncionario().getCodigo(),
                Ocupacao,
                listaModelFuncionarioCidadeEstados.get(i).getModelFuncionario().getNome(),
                listaModelFuncionarioCidadeEstados.get(i).getModelFuncionario().getCpf(),
                listaModelFuncionarioCidadeEstados.get(i).getModelFuncionario().getCelular(),
                listaModelFuncionarioCidadeEstados.get(i).getModelFuncionario().getEmail()});
        }
    }
    // (String.valueOf(controllerOcupacao.getListaOcupacaoDescController(String.valueOf(lista.get(i).getModelFuncionario().getOcupacao())))),
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btEditar;
    private javax.swing.JButton btExcluir;
    private javax.swing.JButton btIncluir;
    private javax.swing.JButton btPesquisar;
    private javax.swing.JComboBox cbPesquisa;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanelBackground;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JButton jbVisualizarConta;
    private keeptoo.KGradientPanel kGradientPanel1;
    private keeptoo.KGradientPanel kGradientPanel2;
    private javax.swing.JTable tbFuncionarios;
    private javax.swing.JTextField tfPesquisa;
    // End of variables declaration//GEN-END:variables

    //carregamento de arquivo após atualizar
    public static ViewFuncionario Funcionario;

    private void pesquisa(ArrayList<ModelFuncionarioCidadeEstado> lista) {
        DefaultTableModel modelo = (DefaultTableModel) tbFuncionarios.getModel();
        modelo.setNumRows(0);

        //CARREGA OS DADOS DA LISTA NA TABELA
        int cont = lista.size();
        for (int i = 0; i < cont; i++) {
            String Ocupacao = controllerOcupacao.getOcupacaoControllerCod(lista.get(i).getModelFuncionario().getOcupacao()).getOcup_descricao();
            modelo.addRow(new Object[]{
                lista.get(i).getModelFuncionario().getCodigo(),
                Ocupacao,
                lista.get(i).getModelFuncionario().getNome(),
                lista.get(i).getModelFuncionario().getCpf(),
                lista.get(i).getModelFuncionario().getTelefone(),
                lista.get(i).getModelFuncionario().getCelular(),
                lista.get(i).getModelFuncionario().getEmail(),});
        }
    }

    private void SetIcone() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("ictrol.png")));
    }
}
