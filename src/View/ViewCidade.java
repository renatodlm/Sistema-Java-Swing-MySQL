/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ControllerCidade;
import Controller.ControllerCidadeEstado;
import Controller.ControllerEstado;
import Model.ModelCidade;
import Model.ModelCidadeEstado;
import Model.ModelEstado;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author studiomicroweb
 */
public class ViewCidade extends javax.swing.JFrame {

    ControllerEstado controllerEstados = new ControllerEstado();
    ControllerCidade controllerCidade = new ControllerCidade();
    ControllerCidadeEstado controllerCidadeEstado = new ControllerCidadeEstado();
    ModelEstado modelEstado = new ModelEstado();
    ModelCidade modelCidade = new ModelCidade();
    ArrayList<ModelCidadeEstado> listaModelCidadeEstados = new ArrayList<>();
    ArrayList<ModelEstado> listaModelEstados = new ArrayList<>();
    ArrayList<ModelCidade> listaModelCidades = new ArrayList<>();

    /**
     * Creates new form ViewCidade
     */
    public ViewCidade() {
        SetIcone();
        initComponents();
        carregarCidades();
        setLocationRelativeTo(null);

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
                new ViewCidade().setVisible(true);
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

        jPanel2 = new javax.swing.JPanel();
        jPanelTabela = new javax.swing.JPanel();
        btIncluir = new javax.swing.JButton();
        btEditar = new javax.swing.JButton();
        btExcluir = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        tbCidades = new javax.swing.JTable();
        tfPesquisa = new javax.swing.JTextField();
        btPesquisar = new javax.swing.JButton();
        jLabelCadCidades = new javax.swing.JLabel();
        jLabelCadCidadesBack = new javax.swing.JLabel();
        kGradientPanel1 = new keeptoo.KGradientPanel();
        kGradientPanel2 = new keeptoo.KGradientPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1030, 400));
        setResizable(false);
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setMinimumSize(new java.awt.Dimension(1030, 400));
        jPanel2.setPreferredSize(new java.awt.Dimension(1030, 400));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelTabela.setBackground(new java.awt.Color(255, 255, 255));
        jPanelTabela.setPreferredSize(new java.awt.Dimension(1030, 400));
        jPanelTabela.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btIncluir.setBackground(new java.awt.Color(0, 153, 0));
        btIncluir.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btIncluir.setForeground(new java.awt.Color(255, 255, 255));
        btIncluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/page_add.png"))); // NOI18N
        btIncluir.setText("Incluir");
        btIncluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btIncluirActionPerformed(evt);
            }
        });
        jPanelTabela.add(btIncluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 120, 30));

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
        jPanelTabela.add(btEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 110, 120, 30));

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
        jPanelTabela.add(btExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 110, 120, 30));

        jScrollPane7.setBackground(new java.awt.Color(247, 247, 247));
        jScrollPane7.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane7.setPreferredSize(new java.awt.Dimension(1170, 170));

        tbCidades.setAutoCreateRowSorter(true);
        tbCidades.setBackground(new java.awt.Color(247, 247, 247));
        tbCidades.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        tbCidades.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cod.", "UF", "Estado", "Cidade"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbCidades.setGridColor(new java.awt.Color(247, 247, 247));
        tbCidades.setMinimumSize(new java.awt.Dimension(610, 0));
        tbCidades.setRowHeight(20);
        tbCidades.setSelectionBackground(new java.awt.Color(0, 153, 0));
        tbCidades.getTableHeader().setReorderingAllowed(false);
        jScrollPane7.setViewportView(tbCidades);
        if (tbCidades.getColumnModel().getColumnCount() > 0) {
            tbCidades.getColumnModel().getColumn(0).setMinWidth(0);
            tbCidades.getColumnModel().getColumn(0).setPreferredWidth(0);
            tbCidades.getColumnModel().getColumn(0).setMaxWidth(0);
            tbCidades.getColumnModel().getColumn(1).setMinWidth(50);
            tbCidades.getColumnModel().getColumn(1).setPreferredWidth(50);
            tbCidades.getColumnModel().getColumn(1).setMaxWidth(50);
        }

        jPanelTabela.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, 940, 210));

        tfPesquisa.setMinimumSize(null);
        tfPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfPesquisaKeyPressed(evt);
            }
        });
        jPanelTabela.add(tfPesquisa, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 110, 320, 30));

        btPesquisar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btPesquisar.setForeground(new java.awt.Color(51, 51, 51));
        btPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/24x24/Find.png"))); // NOI18N
        btPesquisar.setText("BUSCAR");
        btPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisarActionPerformed(evt);
            }
        });
        jPanelTabela.add(btPesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 110, 120, 30));

        jLabelCadCidades.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelCadCidades.setText("CADASTRO DE CIDADES");
        jPanelTabela.add(jLabelCadCidades, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, -1, -1));

        jLabelCadCidadesBack.setBackground(new java.awt.Color(251, 251, 251));
        jLabelCadCidadesBack.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabelCadCidadesBack.setForeground(new java.awt.Color(242, 242, 242));
        jLabelCadCidadesBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/seguradoras.png"))); // NOI18N
        jPanelTabela.add(jLabelCadCidadesBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, -1, -1));

        kGradientPanel1.setkBorderRadius(0);
        kGradientPanel1.setkEndColor(new java.awt.Color(234, 239, 243));
        kGradientPanel1.setkGradientFocus(-5);
        kGradientPanel1.setkStartColor(new java.awt.Color(255, 255, 255));
        kGradientPanel1.setPreferredSize(new java.awt.Dimension(520, 210));
        kGradientPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanelTabela.add(kGradientPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 1030, 240));

        kGradientPanel2.setkBorderRadius(0);
        kGradientPanel2.setkEndColor(new java.awt.Color(234, 239, 243));
        kGradientPanel2.setkGradientFocus(0);
        kGradientPanel2.setkStartColor(new java.awt.Color(255, 255, 255));
        jPanelTabela.add(kGradientPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 1030, 70));

        jPanel2.add(jPanelTabela, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1030, 400));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1030, 400));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btIncluirActionPerformed
        // TODO add your handling code here:
        tipoCadastro = "novo";
        new ViewCadCidade().setVisible(true);

    }//GEN-LAST:event_btIncluirActionPerformed

    private void btEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditarActionPerformed
        //Alterar 
        // TODO add your handling code here:
        tipoCadastro = "alteracao";

        int linha = tbCidades.getSelectedRow();
        String nome = (String) tbCidades.getValueAt(linha, 1);
        codigo = (int) tbCidades.getValueAt(linha, 0);

        modelCidade = controllerCidade.getCidadeController(codigo);
        if (modelCidade != null) {
            new ViewCadCidade().setVisible(true);
        }

    }//GEN-LAST:event_btEditarActionPerformed

    private void btExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirActionPerformed
        // TODO add your handling code here:
        int linha = tbCidades.getSelectedRow();
        String nome = (String) tbCidades.getValueAt(linha, 3);
        int codigo = (int) tbCidades.getValueAt(linha, 0);

        //Pergunta se realmente deseja exclir
        int opcao = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja "
                + "excluir o registro:\n " + "\n" + nome + "?", "Atenção", JOptionPane.YES_NO_OPTION);
        // se sim exclui, se nao nao faz nada
        if (opcao == JOptionPane.OK_OPTION) {
            if (controllerCidade.excluirCidadeController(codigo)) {
                JOptionPane.showMessageDialog(this, "Registro excluido com sucesso!");
                carregarCidades();

            } else {
                JOptionPane.showMessageDialog(this, "Erro ao e os dados!", "ERRO", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btExcluirActionPerformed

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
        // TODO add your handling code here:
        carregarCidades();

    }//GEN-LAST:event_formFocusGained

    private void btPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarActionPerformed
        // TODO add your handling code here:
        DefaultTableModel tabela = (DefaultTableModel) this.tbCidades.getModel();
        final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tabela);
        this.tbCidades.setRowSorter(sorter);
        String text = tfPesquisa.getText();
        sorter.setRowFilter(RowFilter.regexFilter(text, 1));
    }//GEN-LAST:event_btPesquisarActionPerformed

    private void tfPesquisaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPesquisaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == evt.VK_ENTER) {
            DefaultTableModel tabela = (DefaultTableModel) this.tbCidades.getModel();
            final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tabela);
            this.tbCidades.setRowSorter(sorter);
            String text = tfPesquisa.getText();
            sorter.setRowFilter(RowFilter.regexFilter(text, 1));
        }

    }//GEN-LAST:event_tfPesquisaKeyPressed

    public void carregarCidades() {

        listaModelCidadeEstados = controllerCidadeEstado.getListaCidadeEstadoController();
        DefaultTableModel modelo = (DefaultTableModel) tbCidades.getModel();
        modelo.setNumRows(0);
        //CARREGA OS DADOS DA LISTA NA TABELA
        int cont = listaModelCidadeEstados.size();
        for (int i = 0; i < cont; i++) {
            modelo.addRow(new Object[]{
                listaModelCidadeEstados.get(i).getModelCidade().getCodigo(),
                listaModelCidadeEstados.get(i).getModelEstado().getUf(),
                listaModelCidadeEstados.get(i).getModelEstado().getNome(),
                listaModelCidadeEstados.get(i).getModelCidade().getNome()

            });

        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btEditar;
    private javax.swing.JButton btExcluir;
    private javax.swing.JButton btIncluir;
    private javax.swing.JButton btPesquisar;
    private javax.swing.JLabel jLabelCadCidades;
    private javax.swing.JLabel jLabelCadCidadesBack;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelTabela;
    private javax.swing.JScrollPane jScrollPane7;
    private keeptoo.KGradientPanel kGradientPanel1;
    private keeptoo.KGradientPanel kGradientPanel2;
    private javax.swing.JTable tbCidades;
    private javax.swing.JTextField tfPesquisa;
    // End of variables declaration//GEN-END:variables
//carregamento de arquivo após atualizar
    public static ViewCidade cidade;

    private void SetIcone() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("ictrol.png")));
    }
}
