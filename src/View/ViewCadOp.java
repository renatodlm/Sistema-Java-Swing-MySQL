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
import static View.ViewCidade.cidade;
import Model.ModelAlicota;
import static View.ViewOp.op;

import util.Datas;
import util.Mascaras;
import conexao.ConexaoBanco;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Moeda;
import util.Porcentagem;

/**
 *
 * @author studiomicroweb
 */
public final class ViewCadOp extends javax.swing.JFrame {

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
    ViewOp viewoprocesso = new ViewOp();

    /**
     * Creates new form ViewOrdemProcesso
     */
    public ViewCadOp() {
        initComponents();
        listarServicos();
        listarCodigoServicos();
        this.listarEstados();
        this.listarCidades();
        listarSeguradoras();
        listarCodigosSeguradoras();
        retornarDadosSeguradoras();
        listarTaxaISS();
        this.jFDataEntrada.setText(dataAtual());
        kGAlterarTerceiro.setVisible(false);
        if (viewoprocesso.tipoCadastro != null && viewoprocesso.tipoCadastro.equals("alteracao")) {
            recuperarOp(modelOp);
        }
        this.setLocationRelativeTo(null);
        jTPanelProcessos.setSelectedIndex(0);
        panelTabbedKGVerficiar();
        aplicarCidadeTomador();
        retencaoEnegativaPorSeguradoras();
        VerificarEditarTudo();
        testeverificaseg();
        SetIcone();

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
                new ViewCadOp().setVisible(true);
            }
        });
    }

    void VerificarEditarTudo() {

        if (viewoprocesso.tipoCadastro != null && viewoprocesso.tipoCadastro.equals("alteracao")) {
            kButtonEditarTudo.setEnabled(true);
            kButtonEditarTudo.setVisible(true);
        } else {
            kButtonEditarTudo.setEnabled(false);
            kButtonEditarTudo.setVisible(false);
        }
    }

    void aplicarCidadeTomador() {
        modelSeguradora = controllerSeguradora.getSeguradoraController(cbSeguradoras.getSelectedItem().toString());
        this.tfEstadoRetencao.setText(controllerEstado.getEstadoController(modelSeguradora.getCodEstado()).getUf());
        this.tfCidadeRetencao.setText(controllerCidade.getCidadeController(modelSeguradora.getCodCidade()).getNome());
        this.tfEstadoRetencaoNeg.setText(controllerEstado.getEstadoController(modelSeguradora.getCodEstado()).getUf());
        this.tfCidadeRetencaoNeg.setText(controllerCidade.getCidadeController(modelSeguradora.getCodCidade()).getNome());
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

    public ViewCadOp(ModelOp modelOp) {
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

    void cancelar() {
        tfNomeTerceiroAlterar.setText(null);
        tfPlacaTerceiroAlteracao.setText(null);
        tfTerceiroObsAlteracao.setText(null);
        tfCodTerceiro.setText("0");
        btSalvarTerceiro.setEnabled(true);
        btAtualizarTerceiro.setEnabled(false);
        btAlterarTerceiro.setEnabled(true);
        btAlterarTerceiro.setVisible(true);
        btCancelar.setVisible(false);
        btCancelar.setEnabled(false);
    }

    //RETENÇÃO DE HONORARIOS -------------------------------------------------------------------
    public void retencaoProcesso() {
        modelSeguradora = controllerSeguradora.getSeguradoraController(Integer.parseInt(cbCodSeguradora.getSelectedItem().toString()));
        if ((Float.parseFloat(new Moeda().FommatoStringoSomarMil(jFHonorarioDefinido.getText()))) > 0 || !"R$ 0,00".equals(jFHonorarioDefinido.getText()) || !"".equals(jFHonorarioDefinido.getText()) || "Sim".equals(modelSeguradora.getSeg_iss())) {
            Float Total;
            Float somaRetido;
            Float totalMAIShonorario;
            Float totalProcesso = Float.parseFloat(new Moeda().FommatoStringoSomarMil(jFHonorarioDefinido.getText()));
            Float TaxaISS = Float.parseFloat(new Porcentagem().ValorSemPercentual(cbAlicotaPercentualHonorarios.getSelectedItem().toString()));
//            Float Despesas = Float.parseFloat(new Moeda().FommatoStringoSomarMil(jFValorDespesasTotalRegistro.getText()));
            totalMAIShonorario = totalProcesso;
            somaRetido = (totalMAIShonorario * TaxaISS) / 100;
            Total = totalMAIShonorario - somaRetido;

            //VALOR RETIDO
            this.jFValorHonorariosRetido.setText(new Moeda().valorStringTODoubleAtt(String.valueOf(somaRetido)));
            //VALOR DA NOTA APOS RETENCAO
            this.jFValorTotalHonorariosComRetencao.setText(new Moeda().valorStringTODoubleAtt(String.valueOf(Total)));
            this.jFValorTotalHonorariosComRetencao.setBackground(Color.GREEN);
            this.jFValorTotalHonorariosComRetencao.setForeground(Color.DARK_GRAY);
            this.jFValorTotalHonorariosComRetencao.setFont(new Font("Tahoma", Font.BOLD, 12));
            this.jFValorHonorariosRetido.setForeground(Color.green);
            this.jFValorHonorariosRetido.setBackground(Color.DARK_GRAY);
            if ("Sim".equals(modelSeguradora.getSeg_iss()) || jRCheckIndevidoISS.isSelected() || !"".equals(jFValorNotaRealIndevido.getText()) || !"R$ 0,00".equals(jFValorNotaRealIndevido.getText())) {
                retencaoProcessoIndevido();
            }
        } else {
            this.jFValorTotalHonorariosComRetencao.setBackground(Color.GRAY);
            this.jFValorTotalHonorariosComRetencao.setForeground(Color.DARK_GRAY);
            this.jFValorTotalHonorariosComRetencao.setFont(new Font("Tahoma", Font.BOLD, 11));
            this.jFValorHonorariosRetido.setForeground(Color.DARK_GRAY);
            this.jFValorHonorariosRetido.setBackground(Color.GRAY);
            this.jFValorHonorariosRetido.setText("R$ 0,00");
            this.jFValorTotalHonorariosComRetencao.setText("R$ 0,00");
            this.jFHonorarioDefinido.setText("R$ 0,00");
            if ("Sim".equals(modelSeguradora.getSeg_iss()) || jRCheckIndevidoISS.isSelected() || !"".equals(jFValorNotaRealIndevido.getText()) || !"R$ 0,00".equals(jFValorNotaRealIndevido.getText())) {
                retencaoProcessoIndevido();
            }
        }
    }

    //RETENÇÃO DE HONORARIOS INDEVIDO -------------------------------------------------------------------
    public void retencaoProcessoIndevido() {
        if (jRCheckIndevidoISS.isSelected()) {
            modelSeguradora = controllerSeguradora.getSeguradoraController(Integer.parseInt(cbCodSeguradora.getSelectedItem().toString()));
            if ((Float.parseFloat(new Moeda().FommatoStringoSomarMil(jFHonorarioDefinido.getText()))) > 0 || (Float.parseFloat(new Moeda().FommatoStringoSomarMil(jFValorNotaRealIndevido.getText())) > 0 || (jRCheckIndevidoISS.isSelected()) || "Sim".equals(modelSeguradora.getSeg_iss())) || "R$ 0,00".equals(this.jFValorNotaRealIndevido.getText()) || "Sim".equals(modelSeguradora.getSeg_iss()) || "Sim".equals(modelSeguradora.getSeg_negativa())) {
                Float Total;
                Float somaRetido;
                Float totalProcessoReal = Float.parseFloat(new Moeda().FommatoStringoSomarMil(jFValorNotaRealIndevido.getText()));
                Float totalProcesso = Float.parseFloat(new Moeda().FommatoStringoSomarMil(jFHonorarioDefinido.getText()));
                // Float Despesas = Float.parseFloat(new Moeda().FommatoStringoSomarMil(jFValorDespesasTotalRegistro.getText()));
                totalProcesso = totalProcesso;
                somaRetido = totalProcesso - totalProcessoReal;

                if (somaRetido > 0) {
                    //VALOR RETIDO
                    this.jFRetidoReal.setText(new Moeda().valorStringTODoubleAtt(String.valueOf(somaRetido)));
                    //MUDANDO COR DO TEXTO
                    this.jLRetidoReal.setText("Retido Real");
                    this.jLRetidoReal.setForeground(Color.red);
                    //CALCULO PERCENTUAL REAL
                    Total = (somaRetido / totalProcesso) * 100;
                    this.tfPercentualRetidoReal.setText(new Porcentagem().ValorComPercentual(new Mascaras().arredondamentoComPontoDuasCasasString(Total)));
                    this.jLRetidoReal.setForeground(Color.DARK_GRAY);
                    this.jFRetidoReal.setForeground(Color.green);
                    this.jLAlicotaProcessoReal.setForeground(Color.DARK_GRAY);
                    this.tfPercentualRetidoReal.setBackground(Color.DARK_GRAY);
                    this.tfPercentualRetidoReal.setForeground(Color.green);
                    this.jFRetidoReal.setBackground(Color.DARK_GRAY);
                }
                if (somaRetido == 0) {
                    modelSeguradora = controllerSeguradora.getSeguradoraController(cbSeguradoras.getSelectedItem().toString());
                    //CASO NÃO RETENHA
                    this.tfPercentualRetidoReal.setText("0,0%");
                    this.jFRetidoReal.setText("R$ 0,00");
                    this.jLRetidoReal.setText("Não Retido");
                    this.jLRetidoReal.setForeground(Color.ORANGE);
                    this.jFRetidoReal.setForeground(Color.ORANGE);
                    this.jLAlicotaProcessoReal.setForeground(Color.ORANGE);
                    this.tfPercentualRetidoReal.setForeground(Color.DARK_GRAY);
                    this.tfPercentualRetidoReal.setBackground(Color.orange);
                }
                if (somaRetido < 0) {
                    modelSeguradora = controllerSeguradora.getSeguradoraController(cbSeguradoras.getSelectedItem().toString());
                    JOptionPane.showMessageDialog(this, "O VALOR PAGO INDEVIDO FOI LIMPO! Pois o valor informado é superior ao total da Nota Fiscal!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
                    this.tfPercentualRetidoReal.setText("0,0%");
                    this.jFRetidoReal.setText("R$ 0,00");
                    this.jLRetidoReal.setText("Retido Real");
                    this.jLRetidoReal.setForeground(Color.DARK_GRAY);
                    this.jFRetidoReal.setForeground(Color.GRAY);
                    this.jFValorNotaRealIndevido.setText("R$ 0,00");
                    this.jFValorNotaRealIndevido.setForeground(Color.WHITE);
                    this.jFValorNotaRealIndevido.setBackground(Color.GRAY);
                    this.jLAlicotaProcessoReal.setForeground(Color.DARK_GRAY);
                    this.tfPercentualRetidoReal.setBackground(Color.GRAY);
                    this.tfPercentualRetidoReal.setForeground(Color.WHITE);
                    this.jRCheckIndevidoISS.setSelected(false);
                    this.jFValorNotaRealIndevido.setEnabled(false);
                }
            }
        }
    }
    //RETENÃO DE NEGATIVA --------------------------------------------------------------------------------------------

    public void retencaoNegativa() {
        modelSeguradora = controllerSeguradora.getSeguradoraController(cbSeguradoras.getSelectedItem().toString());
        if ((Float.parseFloat(new Moeda().FommatoStringoSomarMil(jFValorNegativaTotal.getText()))) > 0 || !"R$ 0,00".equals(jFValorNegativaTotal.getText()) || !"".equals(jFValorNegativaTotal.getText()) || "Sim".equals(modelSeguradora.getSeg_negativa()) || "Sim".equals(modelSeguradora.getSeg_iss())) {
            Float Total;
            Float somaRetido;
            Float totalProcesso = Float.parseFloat(new Moeda().FommatoStringoSomarMil(jFValorNegativaTotal.getText()));
            Float TaxaISS = Float.parseFloat(new Porcentagem().ValorSemPercentual(cbAlicotaNeg.getSelectedItem().toString()));
            somaRetido = (totalProcesso * TaxaISS) / 100;
            Total = totalProcesso - somaRetido;

            //VALOR RETIDO
            this.jFValorRetidoRetencaoNeg.setText(new Moeda().valorStringTODoubleAtt(String.valueOf(somaRetido)));
            //VALOR DA NOTA APOS RETENCAO
            this.jFValorRetencaoTotalNeg.setText(new Moeda().valorStringTODoubleAtt(String.valueOf(Total)));
            this.jFValorRetencaoTotalNeg.setBackground(Color.green);
            this.jFValorRetencaoTotalNeg.setForeground(Color.DARK_GRAY);
            this.jFValorRetencaoTotalNeg.setFont(new Font("Tahoma", Font.BOLD, 12));
            this.jFValorRetidoRetencaoNeg.setForeground(Color.green);
            this.jFValorRetidoRetencaoNeg.setBackground(Color.DARK_GRAY);
        } else {
            this.jFValorRetencaoTotalNeg.setBackground(Color.GRAY);
            this.jFValorRetencaoTotalNeg.setFont(new Font("Tahoma", Font.BOLD, 11));
            this.jFValorRetidoRetencaoNeg.setForeground(Color.DARK_GRAY);
            this.jFValorRetidoRetencaoNeg.setBackground(Color.GRAY);
            this.jFValorRetidoRetencaoNeg.setText("R$ 0,00");
            this.jFValorRetencaoTotalNeg.setText("R$ 0,00");
            this.jFValorNegativaTotal.setText("R$ 0,00");
        }
    }

    //RETENÇÃO DE NEGATIVA INDEVIDO ----------------------------------------------------------------
    public void retencaoNegativaIndevido() {
        if (jRCheckIndevidoISSNegativa.isSelected()) {
            modelSeguradora = controllerSeguradora.getSeguradoraController(cbSeguradoras.getSelectedItem().toString());
            if ((Float.parseFloat(new Moeda().FommatoStringoSomarMil(jFValorNegativaTotal.getText()))) > 0 || (Float.parseFloat(new Moeda().FommatoStringoSomarMil(jFValorNotaRealNegIndevido.getText())) > 0 || (jRCheckIndevidoISSNegativa.isSelected()) || "Sim".equals(modelSeguradora.getSeg_negativa()) || "Sim".equals(modelSeguradora.getSeg_iss()))) {
                Float Total;
                Float somaRetido;
                Float totalProcessoReal = Float.parseFloat(new Moeda().FommatoStringoSomarMil(jFValorNotaRealNegIndevido.getText()));
                Float totalProcesso = Float.parseFloat(new Moeda().FommatoStringoSomarMil(jFValorNegativaTotal.getText()));
                somaRetido = totalProcesso - totalProcessoReal;
                if (somaRetido > 0) {
                    //VALOR RETIDO
                    this.jFRetidoRealNegativa.setText(new Moeda().valorStringTODoubleAtt(String.valueOf(somaRetido)));
                    //MUDANDO COR DO TEXTO
                    this.jLRetidoRealNegativa.setText("Retido Real");
                    this.jLRetidoRealNegativa.setForeground(Color.red);
                    //CALCULO PERCENTUAL REAL
                    Total = (somaRetido / totalProcesso) * 100;
                    this.tfPercentuallRetidoRealNeg.setText(new Porcentagem().ValorComPercentual(new Mascaras().arredondamentoComPontoDuasCasasString(Total)));
                    this.jLRetidoRealNegativa.setForeground(Color.DARK_GRAY);
                    this.jFRetidoRealNegativa.setForeground(Color.green);
                    this.jLAlicotalRealNegativa.setForeground(Color.DARK_GRAY);
                    this.tfPercentuallRetidoRealNeg.setBackground(Color.DARK_GRAY);
                    this.tfPercentuallRetidoRealNeg.setForeground(Color.green);
                    this.jFRetidoRealNegativa.setBackground(Color.DARK_GRAY);
                }
                if (somaRetido == 0) {
                    //CASO NÃO RETENHA
                    this.tfPercentuallRetidoRealNeg.setText("0,00%");
                    this.jFRetidoRealNegativa.setText("R$ 0,00");
                    this.jLRetidoRealNegativa.setText("Não Retido");
                    this.jLRetidoRealNegativa.setForeground(Color.ORANGE);
                    this.jFRetidoRealNegativa.setForeground(Color.ORANGE);
                    this.jLAlicotalRealNegativa.setForeground(Color.ORANGE);
                    this.tfPercentuallRetidoRealNeg.setForeground(Color.DARK_GRAY);
                    this.tfPercentuallRetidoRealNeg.setBackground(Color.orange);
                    modelSeguradora = controllerSeguradora.getSeguradoraController(cbSeguradoras.getSelectedItem().toString());
                    if ("Sim".equals(modelSeguradora.getSeg_negativa())) {
                        retencaoNegativa();
                    }
                }
                if (somaRetido < 0) {
                    JOptionPane.showMessageDialog(this, "O VALOR PAGO INDEVIDO FOI LIMPO! Pois o valor informado é superior ao total da Nota Fiscal!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
                    this.tfPercentuallRetidoRealNeg.setText("0,00%");
                    this.jFRetidoRealNegativa.setText("R$ 0,00");
                    this.jLRetidoRealNegativa.setText("Retido Real");
                    this.jLRetidoRealNegativa.setForeground(Color.DARK_GRAY);
                    this.jFRetidoRealNegativa.setForeground(Color.GRAY);
                    this.jFValorNotaRealNegIndevido.setText("R$ 0,00");
                    this.jFValorNotaRealNegIndevido.setForeground(Color.WHITE);
                    this.jFValorNotaRealNegIndevido.setBackground(Color.GRAY);
                    this.jLAlicotalRealNegativa.setForeground(Color.DARK_GRAY);
                    this.tfPercentuallRetidoRealNeg.setBackground(Color.GRAY);
                    this.tfPercentuallRetidoRealNeg.setForeground(Color.WHITE);
                    this.jRCheckIndevidoISSNegativa.setSelected(false);
                    this.jFValorNotaRealNegIndevido.setEnabled(false);
                    modelSeguradora = controllerSeguradora.getSeguradoraController(cbSeguradoras.getSelectedItem().toString());
                    if ("Sim".equals(modelSeguradora.getSeg_negativa())) {
                        retencaoNegativa();
                    }
                }
            }
        }
    }

    //VERIFICAR RETENÇÃO E NEGATIVA POR SEGURADORA
    private void retencaoEnegativaPorSeguradoras() {
        //EXEMPLO CONFIGURAÇÃO
        //Retenção, Alerta da não Retenção, Retenção Negativa, Alerta da não Retenção Negativa, Negativa, Alerta da não Negativa
        //Use true ou false para ativar de acordo com as condições
        //modelSeguradora = controllerSeguradora.getSeguradoraController(cbSeguradoras.getSelectedItem().toString());
        if ("Sim".equals(modelSeguradora.getSeg_iss()) && "Sim".equals(modelSeguradora.getSeg_negativa())) {
            retencaoEnegativaVerificacao(true, false, true, false, true, false);
            CheckIndevidoISS();
            CheckIndevidoISSNegativa();
        }
        if ("Sim".equals(modelSeguradora.getSeg_iss()) && !("Sim".equals(modelSeguradora.getSeg_negativa()))) {
            retencaoEnegativaVerificacao(true, false, false, true, false, true);
            CheckIndevidoISS();
        }
        if (!("Sim".equals(modelSeguradora.getSeg_iss())) && "Sim".equals(modelSeguradora.getSeg_negativa())) {
            retencaoEnegativaVerificacao(false, true, false, true, true, false);
            CheckIndevidoISSNegativa();
        }
        if (!("Sim".equals(modelSeguradora.getSeg_iss())) && !("Sim".equals(modelSeguradora.getSeg_negativa()))) {
            retencaoEnegativaVerificacao(false, true, false, true, false, true);
        }
    }

    //VERIFICA RETENÇÃO
    private void retencaoEnegativaVerificacao(boolean retencao, boolean retencaoInverso, boolean retencaoNeg, boolean retencaoNegInverso, boolean negativa, boolean negativaInverso) {

        //-------RETENÇÃO-------#
        //CAMPOS RETENÇÃO ENABLE
        //TEXTO INVERSO
        jLNaoRetemHonorario.setVisible(retencaoInverso);
        //###############

        cbAlicotaPercentualHonorarios.setEnabled(retencao);
        jFValorHonorariosRetido.setEnabled(retencao);
        jFValorTotalHonorariosComRetencao.setEnabled(retencao);
        tfEstadoRetencao.setEnabled(retencao);
        tfCidadeRetencao.setEnabled(retencao);
        jFValorNotaRealIndevido.setEnabled(retencao);
        jRCheckIndevidoISS.setEnabled(retencao);
        jFRetidoReal.setEnabled(retencao);
        tfPercentualRetidoReal.setEnabled(retencao);
        ObsRetencaoAlicotaIndevido.setEnabled(retencao);

        //CAMPOS RETENÇÃO VISIBLE
        cbAlicotaPercentualHonorarios.setVisible(retencao);
        jFValorHonorariosRetido.setVisible(retencao);
        jFValorTotalHonorariosComRetencao.setVisible(retencao);
        tfEstadoRetencao.setVisible(retencao);
        tfCidadeRetencao.setVisible(retencao);
        jFValorNotaRealIndevido.setVisible(retencao);
        jRCheckIndevidoISS.setVisible(retencao);
        jFRetidoReal.setVisible(retencao);
        tfPercentualRetidoReal.setVisible(retencao);
        ObsRetencaoAlicotaIndevido.setVisible(retencao);

        //LABLE RETENÇÃO ENABLE
        jLRetidoRetencao.setEnabled(retencao);
        jLTotalRedito.setEnabled(retencao);
        jLEstadoTomador.setVisible(retencao);
        jLCidadeTomador.setEnabled(retencao);
        jLValorNotaIndevido.setEnabled(retencao);
        jLTextoAbaixoCheckBox.setEnabled(retencao);
        jLRetidoReal.setEnabled(retencao);
        jLAlicotaProcessoReal.setEnabled(retencao);
        jLRetencaoObs.setEnabled(retencao);

        //LABLE RETENÇÃO VISIBLE
        jLAlicotaRetencao.setVisible(retencao);
        jLRetidoRetencao.setVisible(retencao);
        jLTotalRedito.setVisible(retencao);
        jLEstadoTomador.setVisible(retencao);
        jLCidadeTomador.setVisible(retencao);
        jLValorNotaIndevido.setVisible(retencao);
        jLTextoAbaixoCheckBox.setVisible(retencao);
        jLRetidoReal.setVisible(retencao);
        jLAlicotaProcessoReal.setVisible(retencao);
        jLRetencaoObs.setVisible(retencao);

        //-------RETENÇÃO NEGATIVA
        //CAMPOS RETENÇÃO NEGATIVA ENABLE
        //TEXTO INVERSO
        jLNaoRetemNegativa.setVisible(retencaoNegInverso);
        //###############

        cbAlicotaNeg.setEnabled(retencaoNeg);
        jFValorRetidoRetencaoNeg.setEnabled(retencaoNeg);
        tfEstadoRetencaoNeg.setEnabled(retencaoNeg);
        tfCidadeRetencaoNeg.setEnabled(retencaoNeg);
        jRCheckIndevidoISSNegativa.setEnabled(retencaoNeg);
        jFValorNotaRealNegIndevido.setEnabled(retencaoNeg);
        jFRetidoRealNegativa.setEnabled(retencaoNeg);
        tfPercentuallRetidoRealNeg.setEnabled(retencaoNeg);
        tfObsRetencaoISSNeg.setEnabled(retencaoNeg);
        jFValorRetencaoTotalNeg.setEnabled(retencaoNeg);

        //CAMPOS RETENÇÃO NEGATIVA VISIBLE
        cbAlicotaNeg.setVisible(retencaoNeg);
        jFValorRetidoRetencaoNeg.setVisible(retencaoNeg);
        tfEstadoRetencaoNeg.setVisible(retencaoNeg);
        tfCidadeRetencaoNeg.setVisible(retencaoNeg);
        jRCheckIndevidoISSNegativa.setVisible(retencaoNeg);
        jFValorNotaRealNegIndevido.setVisible(retencaoNeg);
        jFRetidoRealNegativa.setVisible(retencaoNeg);
        tfPercentuallRetidoRealNeg.setVisible(retencaoNeg);
        tfObsRetencaoISSNeg.setVisible(retencaoNeg);
        jFValorRetencaoTotalNeg.setVisible(retencaoNeg);

        //LABEL RETENÇÃO NEGATIVA ENABLE
        jLTaxaIssNeg.setEnabled(retencaoNeg);
        jLRetidoNeg.setEnabled(retencaoNeg);
        jLRetencaoValorTotalNeg.setEnabled(retencaoNeg);
        jLUfRetencaoNeg.setEnabled(retencaoNeg);
        jLCidadeRetencaoNeg.setEnabled(retencaoNeg);
        jLTextoAbaixoCheckBoxIndevidoNegativa.setEnabled(retencaoNeg);
        jLValorNotaIndevidoNegativa.setEnabled(retencaoNeg);
        jLRetidoRealNegativa.setEnabled(retencaoNeg);
        jLAlicotalRealNegativa.setEnabled(retencaoNeg);
        jLObsRetencaoNegativa.setEnabled(retencaoNeg);

        //LABEL RETENÇÃO NEGATIVA VISIBLE
        jLTaxaIssNeg.setVisible(retencaoNeg);
        jLRetidoNeg.setVisible(retencaoNeg);
        jLRetencaoValorTotalNeg.setVisible(retencaoNeg);
        jLUfRetencaoNeg.setVisible(retencaoNeg);
        jLCidadeRetencaoNeg.setVisible(retencaoNeg);
        jLTextoAbaixoCheckBoxIndevidoNegativa.setVisible(retencaoNeg);
        jLValorNotaIndevidoNegativa.setVisible(retencaoNeg);
        jLRetidoRealNegativa.setVisible(retencaoNeg);
        jLAlicotalRealNegativa.setVisible(retencaoNeg);
        jLObsRetencaoNegativa.setVisible(retencaoNeg);

        //-------NEGATIVA-------#
        //CAMPOS NEGATIVA ENABLE
        jLNaoPagaNegativa.setVisible(negativaInverso);
        //###############

        //TEXTO INVERSO
        jFValorPrejuizoNegativa.setEnabled(negativa);
        jFValorNegativaTotal.setEnabled(negativa);
        tfNumeroNFNegativa.setEnabled(negativa);
        jFDataEmissaoNegativa.setEnabled(negativa);
        jFDataPrevisaoPgtoNegativa.setEnabled(negativa);
        jFDataPgtoNegativa.setEnabled(negativa);
        cbSituacaoPgtoNegativa.setEnabled(negativa);

        //CAMPOS NEGATIVA VISIBLE     
        jFValorPrejuizoNegativa.setVisible(negativa);
        jFValorNegativaTotal.setVisible(negativa);
        tfNumeroNFNegativa.setVisible(negativa);
        jFDataEmissaoNegativa.setVisible(negativa);
        jFDataPrevisaoPgtoNegativa.setVisible(negativa);
        jFDataPgtoNegativa.setVisible(negativa);
        cbSituacaoPgtoNegativa.setVisible(negativa);

        //LABEL NEGATIVA ENABLE
        jLPrejuizoValor.setEnabled(negativa);
        jLNegativaValor.setEnabled(negativa);
        jLNumeroNFNegativa.setEnabled(negativa);
        jLDataEmissaoNegativa.setEnabled(negativa);
        jLPrevPgtoNegativa.setEnabled(negativa);
        jLDataPgtoNegativa.setEnabled(negativa);
        jLSituacaoPagamento.setEnabled(negativa);

        //LABEL NEGATIVA VISIBLE
        jLPrejuizoValor.setVisible(negativa);
        jLNegativaValor.setVisible(negativa);
        jLNumeroNFNegativa.setVisible(negativa);
        jLDataEmissaoNegativa.setVisible(negativa);
        jLPrevPgtoNegativa.setVisible(negativa);
        jLDataPgtoNegativa.setVisible(negativa);
        jLSituacaoPagamento.setVisible(negativa);
    }

    //VERIFICACAO DE VALOR DE HONORARIOS -------------------------------------------------------------------
    public void vefiricarValorHonorario() {
        if (!("R$ 0,00".equals(this.jFHonorarioDefinido.getText()) || "".equals(this.jFHonorarioDefinido.getText()))) {
            JOptionPane.showMessageDialog(this, "Valor de Honorario diferente do fixo, O valor não foi atualizado!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
            this.jFHonorarioDefinido.setBackground(Color.DARK_GRAY);
            this.jFHonorarioDefinido.setForeground(Color.green);
            this.jFHonorarioDefinido.setFont(new Font("Tahoma", Font.BOLD, 12));
            modelSeguradora = controllerSeguradora.getSeguradoraController(cbSeguradoras.getSelectedItem().toString());
            if ("Sim".equals(modelSeguradora.getSeg_iss())) {
                retencaoProcesso();
            }
            if ("Sim".equals(modelSeguradora.getSeg_iss()) || jRCheckIndevidoISS.isSelected() || !"".equals(jFValorNotaRealIndevido.getText()) || !"R$ 0,00".equals(jFValorNotaRealIndevido.getText())) {
                retencaoProcessoIndevido();
            }
        } else {
            this.jFHonorarioDefinido.setText("R$ 0,00");
            this.jFHonorarioDefinido.setForeground(Color.getHSBColor(51, 51, 51));
            this.jFHonorarioDefinido.setFont(new Font("Tahoma", Font.BOLD, 11));
            this.jFHonorarioDefinido.setText("R$ 0,00");
            this.jFHonorarioDefinido.setForeground(Color.getHSBColor(51, 51, 51));
            modelSeguradora = controllerSeguradora.getSeguradoraController(cbSeguradoras.getSelectedItem().toString());
            if ("Sim".equals(modelSeguradora.getSeg_iss())) {
                retencaoProcesso();
            }
            if ("Sim".equals(modelSeguradora.getSeg_iss()) || jRCheckIndevidoISS.isSelected() || !"".equals(jFValorNotaRealIndevido.getText()) || !"R$ 0,00".equals(jFValorNotaRealIndevido.getText())) {
                retencaoProcessoIndevido();
            }
        }
    }

    public void listarTaxaISS() {
        listaModelAlicota = controllerAlicota.getListaAlicotaController();
        cbAlicotaPercentualHonorarios.removeAllItems();
        cbAlicotaNeg.removeAllItems();
        for (int i = 0; i < listaModelAlicota.size(); i++) {
            //ADICIONA TODAS TAXAS REGISTRADAS
            cbAlicotaPercentualHonorarios.addItem(new Porcentagem().ValorComPercentual(listaModelAlicota.get(i).getAli_iss().toString()));
            cbAlicotaNeg.addItem(new Porcentagem().ValorComPercentual(listaModelAlicota.get(i).getAli_iss().toString()));
            //SETA O ULTIMO REGISTRADO
            this.cbAlicotaPercentualHonorarios.setSelectedIndex(i);
            this.cbAlicotaNeg.setSelectedIndex(i);
        }
    }

    /* public void verificarTaxaCadastrada() {
     if (!(cbAlicotaPercentualHonorarios.getSelectedIndex() > 0)) {
     JOptionPane.showMessageDialog(this, "Nenhuma Taxa de ISS cadastrada!", "ATENÇÃO", JOptionPane.ERROR_MESSAGE);
     }
     }*/
    private String dataAtual() {
        java.util.Date d = new java.util.Date();
        Locale local = new Locale("pt", "BR");
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", local);
        return String.format((format.format(d)));
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
        tfTipoNF = new javax.swing.JTextField();
        tfValorComRetencaoAnexo = new javax.swing.JTextField();
        cbCodServico = new javax.swing.JComboBox<String>();
        cbCodSeguradora = new javax.swing.JComboBox();
        kGradientPanel1 = new keeptoo.KGradientPanel();
        jTPanelProcessos = new javax.swing.JTabbedPane();
        jPanelEntradadeProcessos = new javax.swing.JPanel();
        kGSeguradoraSolicitante = new keeptoo.KGradientPanel();
        jLNomeSeguradora = new javax.swing.JLabel();
        tfCodigo = new javax.swing.JTextField();
        cbSeguradoras = new javax.swing.JComboBox();
        jLDataEntrada = new javax.swing.JLabel();
        jLDataSaida = new javax.swing.JLabel();
        jLNumeroProcesso = new javax.swing.JLabel();
        jfCNPJ = new javax.swing.JFormattedTextField();
        jLCtsRamo = new javax.swing.JLabel();
        jFDataEntrada = new javax.swing.JFormattedTextField();
        jFDataSaida = new javax.swing.JFormattedTextField();
        jFCts = new javax.swing.JFormattedTextField();
        jLCnpj1 = new javax.swing.JLabel();
        kGPDadosSinistro = new keeptoo.KGradientPanel();
        jLAnalista = new javax.swing.JLabel();
        jLCobertura = new javax.swing.JLabel();
        jLDetalhesSinistro = new javax.swing.JLabel();
        jLEstado = new javax.swing.JLabel();
        jLCidade = new javax.swing.JLabel();
        jLBairro = new javax.swing.JLabel();
        tfBairro = new javax.swing.JTextField();
        cbCidade = new javax.swing.JComboBox<String>();
        cbUf = new javax.swing.JComboBox<String>();
        tfAnalista = new javax.swing.JTextField();
        jLHoraSinistro = new javax.swing.JLabel();
        jLDataSinistro = new javax.swing.JLabel();
        tfNumSinistro = new javax.swing.JTextField();
        jLNumeroSinistro = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTObsSinistro = new javax.swing.JTextArea();
        jFHoraSinistro = new javax.swing.JFormattedTextField();
        btCidadeAdd = new javax.swing.JButton();
        jFDataSinistro = new javax.swing.JFormattedTextField();
        cbServico = new componentes.UJComboBox();
        kGPDadosSegurado = new keeptoo.KGradientPanel();
        jLNomeSegurado = new javax.swing.JLabel();
        jLPlacaSegurado = new javax.swing.JLabel();
        jLObsSegurado = new javax.swing.JLabel();
        tfSeguradoPlaca = new javax.swing.JTextField();
        tfObsSegurado = new javax.swing.JTextField();
        tfNomeSegurado = new javax.swing.JTextField();
        kGAlterarTerceiro = new keeptoo.KGradientPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbTerceiros = new javax.swing.JTable();
        jLNomeSegurado2 = new javax.swing.JLabel();
        jLPlacaSegurado2 = new javax.swing.JLabel();
        jLObsSegurado2 = new javax.swing.JLabel();
        tfPlacaTerceiroAlteracao = new javax.swing.JTextField();
        tfTerceiroObsAlteracao = new javax.swing.JTextField();
        tfNomeTerceiroAlterar = new javax.swing.JTextField();
        btSalvarTerceiro = new javax.swing.JButton();
        btAtualizarTerceiro = new javax.swing.JButton();
        btAlterarTerceiro = new javax.swing.JButton();
        btExcluirTerceiro = new javax.swing.JButton();
        btCancelar = new javax.swing.JButton();
        kGPIncluirTerceiro = new keeptoo.KGradientPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbServicos = new javax.swing.JTable();
        jLNomeSegurado1 = new javax.swing.JLabel();
        jLPlacaSegurado1 = new javax.swing.JLabel();
        jLObsSegurado1 = new javax.swing.JLabel();
        tfTerceiroPlaca = new javax.swing.JTextField();
        tfTerceiroObs = new javax.swing.JTextField();
        tfTerceiroNome = new javax.swing.JTextField();
        jButtonAddTerceiro = new javax.swing.JButton();
        jButtonRemoveTerceiro = new javax.swing.JButton();
        jPanelSevicosDespesas = new javax.swing.JPanel();
        kGPAplicacaoFixoMeio = new keeptoo.KGradientPanel();
        jLHonorarioDefinido = new javax.swing.JLabel();
        jFHonorarioDefinido = new javax.swing.JFormattedTextField();
        kGPHonorarios = new keeptoo.KGradientPanel();
        jLNumeroNFHonorarios = new javax.swing.JLabel();
        jFDataEmissaoNFhonorarios = new javax.swing.JFormattedTextField();
        jFDataPrevisaoPgtoNFhonorarios = new javax.swing.JFormattedTextField();
        jLPrevisaoPagamentoHonorarios = new javax.swing.JLabel();
        jLDataPagamentoHonorarios = new javax.swing.JLabel();
        jLSituacaoPagamentoHonorarios = new javax.swing.JLabel();
        jFDataPgtoNFhonorarios = new javax.swing.JFormattedTextField();
        jLDataEmissaoNFHonorarios = new javax.swing.JLabel();
        tfNumeroNFProcesso = new javax.swing.JTextField();
        cbStatusNotaFiscal = new javax.swing.JComboBox<String>();
        jLHonorarioObs = new javax.swing.JLabel();
        tfObsHonorariosProcesso = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jFValorDespesasTotalRegistro = new javax.swing.JFormattedTextField();
        kGPRetencao = new keeptoo.KGradientPanel();
        jLCidadeTomador = new javax.swing.JLabel();
        jLRetidoRetencao = new javax.swing.JLabel();
        jLAlicotaRetencao = new javax.swing.JLabel();
        jLRetencaoObs = new javax.swing.JLabel();
        jLTotalRedito = new javax.swing.JLabel();
        jLEstadoTomador = new javax.swing.JLabel();
        jFValorHonorariosRetido = new javax.swing.JFormattedTextField();
        jFValorTotalHonorariosComRetencao = new javax.swing.JFormattedTextField();
        jFRetidoReal = new javax.swing.JFormattedTextField();
        tfPercentualRetidoReal = new javax.swing.JTextField();
        jLAlicotaProcessoReal = new javax.swing.JLabel();
        jLValorNotaIndevido = new javax.swing.JLabel();
        cbAlicotaPercentualHonorarios = new javax.swing.JComboBox<String>();
        ObsRetencaoAlicotaIndevido = new javax.swing.JTextField();
        jFValorNotaRealIndevido = new javax.swing.JFormattedTextField();
        jLRetidoReal = new javax.swing.JLabel();
        jLTextoAbaixoCheckBox = new javax.swing.JLabel();
        jRCheckIndevidoISS = new javax.swing.JRadioButton();
        jLNaoRetemHonorario = new javax.swing.JLabel();
        kGradientPanel6 = new keeptoo.KGradientPanel();
        tfEstadoRetencao = new javax.swing.JLabel();
        tfCidadeRetencao = new javax.swing.JLabel();
        kGPNegativa = new keeptoo.KGradientPanel();
        jLNegativaValor = new javax.swing.JLabel();
        jLPrejuizoValor = new javax.swing.JLabel();
        jLNumeroNFNegativa = new javax.swing.JLabel();
        jLDataEmissaoNegativa = new javax.swing.JLabel();
        jLPrevPgtoNegativa = new javax.swing.JLabel();
        jLDataPgtoNegativa = new javax.swing.JLabel();
        jLSituacaoPagamento = new javax.swing.JLabel();
        tfNumeroNFNegativa = new javax.swing.JTextField();
        jFValorPrejuizoNegativa = new javax.swing.JFormattedTextField();
        jFValorNegativaTotal = new javax.swing.JFormattedTextField();
        jFDataEmissaoNegativa = new javax.swing.JFormattedTextField();
        jFDataPrevisaoPgtoNegativa = new javax.swing.JFormattedTextField();
        jFDataPgtoNegativa = new javax.swing.JFormattedTextField();
        jLNaoPagaNegativa = new javax.swing.JLabel();
        cbSituacaoPgtoNegativa = new javax.swing.JComboBox<String>();
        kGPRetencaoNeg = new keeptoo.KGradientPanel();
        jLCidadeRetencaoNeg = new javax.swing.JLabel();
        jLRetidoNeg = new javax.swing.JLabel();
        jLTaxaIssNeg = new javax.swing.JLabel();
        jLObsRetencaoNegativa = new javax.swing.JLabel();
        jLRetencaoValorTotalNeg = new javax.swing.JLabel();
        jLUfRetencaoNeg = new javax.swing.JLabel();
        jFValorRetidoRetencaoNeg = new javax.swing.JFormattedTextField();
        jFValorRetencaoTotalNeg = new javax.swing.JFormattedTextField();
        jLTextoAbaixoCheckBoxIndevidoNegativa = new javax.swing.JLabel();
        jRCheckIndevidoISSNegativa = new javax.swing.JRadioButton();
        jLRetidoRealNegativa = new javax.swing.JLabel();
        jFRetidoRealNegativa = new javax.swing.JFormattedTextField();
        tfPercentuallRetidoRealNeg = new javax.swing.JTextField();
        jLAlicotalRealNegativa = new javax.swing.JLabel();
        jLValorNotaIndevidoNegativa = new javax.swing.JLabel();
        jFValorNotaRealNegIndevido = new javax.swing.JFormattedTextField();
        tfObsRetencaoISSNeg = new javax.swing.JTextField();
        cbAlicotaNeg = new javax.swing.JComboBox<String>();
        jLNaoRetemNegativa = new javax.swing.JLabel();
        kGradientPanel5 = new keeptoo.KGradientPanel();
        tfCidadeRetencaoNeg = new javax.swing.JLabel();
        tfEstadoRetencaoNeg = new javax.swing.JLabel();
        jPanelSalvarCancelar = new javax.swing.JPanel();
        btCancelarServicos1 = new javax.swing.JButton();
        btSalvarOp = new javax.swing.JButton();
        kGradientPanel14 = new keeptoo.KGradientPanel();
        kButtonProcessos = new keeptoo.KButton();
        kButtonAndamento = new keeptoo.KButton();
        kButtonLimparDados = new keeptoo.KButton();
        kButtonEditarTudo = new keeptoo.KButton();

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

        tfTipoNF.setEditable(false);
        tfTipoNF.setBackground(new java.awt.Color(204, 204, 255));
        tfTipoNF.setFocusable(false);

        tfValorComRetencaoAnexo.setEditable(false);
        tfValorComRetencaoAnexo.setBackground(new java.awt.Color(204, 204, 255));

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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Processos");
        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1300, 730));
        setPreferredSize(new java.awt.Dimension(1300, 730));
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
        jPanelEntradadeProcessos.setMinimumSize(new java.awt.Dimension(1290, 680));
        jPanelEntradadeProcessos.setPreferredSize(new java.awt.Dimension(1290, 680));

        kGSeguradoraSolicitante.setBackground(new java.awt.Color(228, 235, 241));
        kGSeguradoraSolicitante.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Solicitante", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(51, 51, 51))); // NOI18N
        kGSeguradoraSolicitante.setForeground(new java.awt.Color(51, 51, 51));
        kGSeguradoraSolicitante.setkBorderRadius(0);
        kGSeguradoraSolicitante.setkEndColor(new java.awt.Color(234, 239, 243));
        kGSeguradoraSolicitante.setkGradientFocus(0);
        kGSeguradoraSolicitante.setkStartColor(new java.awt.Color(255, 255, 255));
        kGSeguradoraSolicitante.setMinimumSize(new java.awt.Dimension(1280, 110));
        kGSeguradoraSolicitante.setPreferredSize(new java.awt.Dimension(1280, 100));
        kGSeguradoraSolicitante.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLNomeSeguradora.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLNomeSeguradora.setForeground(new java.awt.Color(51, 51, 51));
        jLNomeSeguradora.setText("Nome Seguradora");
        kGSeguradoraSolicitante.add(jLNomeSeguradora, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 30, 280, 20));

        tfCodigo.setEditable(false);
        tfCodigo.setForeground(new java.awt.Color(51, 51, 51));
        tfCodigo.setText("Novo +");
        tfCodigo.setEnabled(false);
        tfCodigo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfCodigoFocusLost(evt);
            }
        });
        kGSeguradoraSolicitante.add(tfCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 90, 35));

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
        kGSeguradoraSolicitante.add(cbSeguradoras, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 50, 420, 28));

        jLDataEntrada.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLDataEntrada.setForeground(new java.awt.Color(51, 51, 51));
        jLDataEntrada.setText("Data da Entrada");
        kGSeguradoraSolicitante.add(jLDataEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 30, 130, 20));

        jLDataSaida.setForeground(new java.awt.Color(51, 51, 51));
        jLDataSaida.setText("Data da Saída");
        kGSeguradoraSolicitante.add(jLDataSaida, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 30, 130, 20));

        jLNumeroProcesso.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLNumeroProcesso.setForeground(new java.awt.Color(51, 51, 51));
        jLNumeroProcesso.setText("Nº do Proceso:");
        kGSeguradoraSolicitante.add(jLNumeroProcesso, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, 20));

        jfCNPJ.setEditable(false);
        jfCNPJ.setBackground(new java.awt.Color(153, 153, 153));
        jfCNPJ.setForeground(new java.awt.Color(204, 204, 204));
        jfCNPJ.setMinimumSize(new java.awt.Dimension(6, 28));
        jfCNPJ.setPreferredSize(new java.awt.Dimension(6, 28));
        kGSeguradoraSolicitante.add(jfCNPJ, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 50, 180, -1));

        jLCtsRamo.setForeground(new java.awt.Color(51, 51, 51));
        jLCtsRamo.setText("Ramo/CTS");
        kGSeguradoraSolicitante.add(jLCtsRamo, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 30, 80, 20));

        jFDataEntrada.setForeground(new java.awt.Color(51, 51, 51));
        try {
            jFDataEntrada.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFDataEntrada.setMinimumSize(new java.awt.Dimension(6, 28));
        jFDataEntrada.setPreferredSize(new java.awt.Dimension(6, 28));
        jFDataEntrada.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jFDataEntradaFocusLost(evt);
            }
        });
        kGSeguradoraSolicitante.add(jFDataEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 50, 130, -1));

        jFDataSaida.setForeground(new java.awt.Color(51, 51, 51));
        try {
            jFDataSaida.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFDataSaida.setMinimumSize(new java.awt.Dimension(6, 28));
        jFDataSaida.setPreferredSize(new java.awt.Dimension(6, 28));
        jFDataSaida.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jFDataSaidaFocusLost(evt);
            }
        });
        kGSeguradoraSolicitante.add(jFDataSaida, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 50, 130, -1));

        jFCts.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jFCts.setText("0");
        jFCts.setPreferredSize(new java.awt.Dimension(60, 28));
        jFCts.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jFCtsFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jFCtsFocusLost(evt);
            }
        });
        kGSeguradoraSolicitante.add(jFCts, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 50, -1, -1));

        jLCnpj1.setForeground(new java.awt.Color(51, 51, 51));
        jLCnpj1.setText("CNPJ");
        kGSeguradoraSolicitante.add(jLCnpj1, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 30, 40, 20));

        jPanelEntradadeProcessos.add(kGSeguradoraSolicitante);

        kGPDadosSinistro.setBackground(new java.awt.Color(174, 194, 213));
        kGPDadosSinistro.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Sinistro", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(51, 51, 51))); // NOI18N
        kGPDadosSinistro.setkBorderRadius(0);
        kGPDadosSinistro.setkEndColor(new java.awt.Color(234, 239, 243));
        kGPDadosSinistro.setkGradientFocus(0);
        kGPDadosSinistro.setkStartColor(new java.awt.Color(255, 255, 255));
        kGPDadosSinistro.setMinimumSize(new java.awt.Dimension(1280, 160));
        kGPDadosSinistro.setPreferredSize(new java.awt.Dimension(1280, 160));
        kGPDadosSinistro.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLAnalista.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLAnalista.setForeground(new java.awt.Color(51, 51, 51));
        jLAnalista.setText("Analista");
        kGPDadosSinistro.add(jLAnalista, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 30, 150, -1));

        jLCobertura.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLCobertura.setForeground(new java.awt.Color(51, 51, 51));
        jLCobertura.setText("Cobertura");
        kGPDadosSinistro.add(jLCobertura, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 30, 70, -1));

        jLDetalhesSinistro.setForeground(new java.awt.Color(51, 51, 51));
        jLDetalhesSinistro.setText("Detalhes do Sinistro");
        kGPDadosSinistro.add(jLDetalhesSinistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 30, 130, -1));

        jLEstado.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLEstado.setForeground(new java.awt.Color(51, 51, 51));
        jLEstado.setText("Estado");
        kGPDadosSinistro.add(jLEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 100, -1));

        jLCidade.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLCidade.setForeground(new java.awt.Color(51, 51, 51));
        jLCidade.setText("Cidade");
        kGPDadosSinistro.add(jLCidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, 150, -1));

        jLBairro.setForeground(new java.awt.Color(51, 51, 51));
        jLBairro.setText("Endereço:");
        kGPDadosSinistro.add(jLBairro, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 90, 120, -1));

        tfBairro.setForeground(new java.awt.Color(51, 51, 51));
        tfBairro.setMinimumSize(new java.awt.Dimension(500, 28));
        tfBairro.setPreferredSize(new java.awt.Dimension(500, 28));
        tfBairro.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfBairroFocusLost(evt);
            }
        });
        kGPDadosSinistro.add(tfBairro, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 110, 350, -1));

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
        kGPDadosSinistro.add(cbCidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 110, 150, 28));

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
        kGPDadosSinistro.add(cbUf, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 100, 28));

        tfAnalista.setForeground(new java.awt.Color(51, 51, 51));
        tfAnalista.setMinimumSize(new java.awt.Dimension(6, 28));
        tfAnalista.setName(""); // NOI18N
        tfAnalista.setPreferredSize(new java.awt.Dimension(6, 28));
        tfAnalista.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfAnalistaFocusLost(evt);
            }
        });
        kGPDadosSinistro.add(tfAnalista, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 50, 150, -1));

        jLHoraSinistro.setForeground(new java.awt.Color(51, 51, 51));
        jLHoraSinistro.setText("Hora do Sinistro");
        kGPDadosSinistro.add(jLHoraSinistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 30, 100, -1));

        jLDataSinistro.setForeground(new java.awt.Color(51, 51, 51));
        jLDataSinistro.setText("Data do Sinistro");
        kGPDadosSinistro.add(jLDataSinistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 30, 130, -1));

        tfNumSinistro.setForeground(new java.awt.Color(51, 51, 51));
        tfNumSinistro.setMinimumSize(new java.awt.Dimension(6, 28));
        tfNumSinistro.setPreferredSize(new java.awt.Dimension(6, 28));
        tfNumSinistro.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfNumSinistroFocusLost(evt);
            }
        });
        kGPDadosSinistro.add(tfNumSinistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 100, -1));

        jLNumeroSinistro.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLNumeroSinistro.setForeground(new java.awt.Color(51, 51, 51));
        jLNumeroSinistro.setText("Número do Sinistro");
        kGPDadosSinistro.add(jLNumeroSinistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 130, -1));

        jScrollPane2.setForeground(new java.awt.Color(51, 51, 51));

        jTObsSinistro.setColumns(20);
        jTObsSinistro.setRows(5);
        jTObsSinistro.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTObsSinistroFocusLost(evt);
            }
        });
        jScrollPane2.setViewportView(jTObsSinistro);

        kGPDadosSinistro.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 50, 370, -1));

        jFHoraSinistro.setForeground(new java.awt.Color(51, 51, 51));
        jFHoraSinistro.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getTimeInstance(java.text.DateFormat.SHORT))));
        jFHoraSinistro.setText("00:00");
        jFHoraSinistro.setMinimumSize(new java.awt.Dimension(6, 28));
        jFHoraSinistro.setPreferredSize(new java.awt.Dimension(6, 28));
        jFHoraSinistro.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jFHoraSinistroFocusLost(evt);
            }
        });
        kGPDadosSinistro.add(jFHoraSinistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 50, 100, -1));

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
        kGPDadosSinistro.add(btCidadeAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 100, -1, -1));

        jFDataSinistro.setForeground(new java.awt.Color(51, 51, 51));
        try {
            jFDataSinistro.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFDataSinistro.setPreferredSize(new java.awt.Dimension(6, 28));
        kGPDadosSinistro.add(jFDataSinistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 50, 130, -1));

        cbServico.setMaximumRowCount(100);
        cbServico.setToolTipText("");
        cbServico.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cbServicoFocusLost(evt);
            }
        });
        cbServico.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbServicoPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        kGPDadosSinistro.add(cbServico, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 50, 210, 28));

        jPanelEntradadeProcessos.add(kGPDadosSinistro);

        kGPDadosSegurado.setBackground(new java.awt.Color(172, 193, 212));
        kGPDadosSegurado.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Segurado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(51, 51, 51))); // NOI18N
        kGPDadosSegurado.setkBorderRadius(0);
        kGPDadosSegurado.setkEndColor(new java.awt.Color(234, 239, 243));
        kGPDadosSegurado.setkGradientFocus(0);
        kGPDadosSegurado.setkStartColor(new java.awt.Color(255, 255, 255));
        kGPDadosSegurado.setMinimumSize(new java.awt.Dimension(1280, 120));
        kGPDadosSegurado.setPreferredSize(new java.awt.Dimension(1280, 100));
        kGPDadosSegurado.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLNomeSegurado.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLNomeSegurado.setForeground(new java.awt.Color(51, 51, 51));
        jLNomeSegurado.setText("Nome do Segurado");
        kGPDadosSegurado.add(jLNomeSegurado, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 140, 10));

        jLPlacaSegurado.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLPlacaSegurado.setForeground(new java.awt.Color(51, 51, 51));
        jLPlacaSegurado.setText("Placa Segurado");
        kGPDadosSegurado.add(jLPlacaSegurado, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 30, 100, 10));

        jLObsSegurado.setForeground(new java.awt.Color(51, 51, 51));
        jLObsSegurado.setText("Observações");
        kGPDadosSegurado.add(jLObsSegurado, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 30, 80, 10));

        tfSeguradoPlaca.setForeground(new java.awt.Color(51, 51, 51));
        tfSeguradoPlaca.setMinimumSize(new java.awt.Dimension(100, 28));
        tfSeguradoPlaca.setPreferredSize(new java.awt.Dimension(100, 28));
        tfSeguradoPlaca.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfSeguradoPlacaFocusLost(evt);
            }
        });
        kGPDadosSegurado.add(tfSeguradoPlaca, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 50, 100, 30));

        tfObsSegurado.setForeground(new java.awt.Color(51, 51, 51));
        tfObsSegurado.setMinimumSize(new java.awt.Dimension(100, 28));
        tfObsSegurado.setPreferredSize(new java.awt.Dimension(100, 28));
        tfObsSegurado.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfObsSeguradoFocusLost(evt);
            }
        });
        kGPDadosSegurado.add(tfObsSegurado, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 50, 720, 30));

        tfNomeSegurado.setPreferredSize(new java.awt.Dimension(320, 28));
        tfNomeSegurado.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfNomeSeguradoFocusLost(evt);
            }
        });
        kGPDadosSegurado.add(tfNomeSegurado, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 320, -1));

        jPanelEntradadeProcessos.add(kGPDadosSegurado);

        kGAlterarTerceiro.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Alterar Terceiro", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(51, 51, 51))); // NOI18N
        kGAlterarTerceiro.setForeground(new java.awt.Color(51, 51, 51));
        kGAlterarTerceiro.setkBorderRadius(0);
        kGAlterarTerceiro.setkEndColor(new java.awt.Color(227, 236, 245));
        kGAlterarTerceiro.setkGradientFocus(0);
        kGAlterarTerceiro.setkStartColor(new java.awt.Color(255, 255, 255));
        kGAlterarTerceiro.setMinimumSize(new java.awt.Dimension(1280, 220));
        kGAlterarTerceiro.setPreferredSize(new java.awt.Dimension(1280, 220));
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
            tbTerceiros.getColumnModel().getColumn(0).setMinWidth(0);
            tbTerceiros.getColumnModel().getColumn(0).setPreferredWidth(0);
            tbTerceiros.getColumnModel().getColumn(0).setMaxWidth(0);
            tbTerceiros.getColumnModel().getColumn(2).setMinWidth(100);
            tbTerceiros.getColumnModel().getColumn(2).setPreferredWidth(100);
            tbTerceiros.getColumnModel().getColumn(2).setMaxWidth(100);
        }

        kGAlterarTerceiro.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, 1040, 100));

        jLNomeSegurado2.setForeground(new java.awt.Color(51, 51, 51));
        jLNomeSegurado2.setText("Nome do Terceiro");
        kGAlterarTerceiro.add(jLNomeSegurado2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 140, 10));

        jLPlacaSegurado2.setForeground(new java.awt.Color(51, 51, 51));
        jLPlacaSegurado2.setText("Placa Terceiro");
        kGAlterarTerceiro.add(jLPlacaSegurado2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 30, 100, 10));

        jLObsSegurado2.setForeground(new java.awt.Color(51, 51, 51));
        jLObsSegurado2.setText("Observações");
        kGAlterarTerceiro.add(jLObsSegurado2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 30, 80, 10));

        tfPlacaTerceiroAlteracao.setForeground(new java.awt.Color(51, 51, 51));
        tfPlacaTerceiroAlteracao.setMinimumSize(new java.awt.Dimension(100, 28));
        tfPlacaTerceiroAlteracao.setPreferredSize(new java.awt.Dimension(100, 28));
        tfPlacaTerceiroAlteracao.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfPlacaTerceiroAlteracaoFocusLost(evt);
            }
        });
        kGAlterarTerceiro.add(tfPlacaTerceiroAlteracao, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 50, 100, 30));

        tfTerceiroObsAlteracao.setForeground(new java.awt.Color(51, 51, 51));
        tfTerceiroObsAlteracao.setMinimumSize(new java.awt.Dimension(100, 28));
        tfTerceiroObsAlteracao.setPreferredSize(new java.awt.Dimension(100, 28));
        tfTerceiroObsAlteracao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfTerceiroObsAlteracaoActionPerformed(evt);
            }
        });
        kGAlterarTerceiro.add(tfTerceiroObsAlteracao, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 50, 720, 30));

        tfNomeTerceiroAlterar.setPreferredSize(new java.awt.Dimension(320, 28));
        tfNomeTerceiroAlterar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfNomeTerceiroAlterarFocusLost(evt);
            }
        });
        kGAlterarTerceiro.add(tfNomeTerceiroAlterar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 320, -1));

        btSalvarTerceiro.setBackground(new java.awt.Color(0, 153, 0));
        btSalvarTerceiro.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btSalvarTerceiro.setForeground(new java.awt.Color(255, 255, 255));
        btSalvarTerceiro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/save.png"))); // NOI18N
        btSalvarTerceiro.setMaximumSize(new java.awt.Dimension(60, 40));
        btSalvarTerceiro.setMinimumSize(new java.awt.Dimension(60, 40));
        btSalvarTerceiro.setPreferredSize(new java.awt.Dimension(60, 40));
        btSalvarTerceiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSalvarTerceiroActionPerformed(evt);
            }
        });
        kGAlterarTerceiro.add(btSalvarTerceiro, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, -1));

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
        kGAlterarTerceiro.add(btAtualizarTerceiro, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, -1, -1));

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
        kGAlterarTerceiro.add(btAlterarTerceiro, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 100, -1, -1));

        btExcluirTerceiro.setBackground(new java.awt.Color(255, 0, 0));
        btExcluirTerceiro.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btExcluirTerceiro.setForeground(new java.awt.Color(255, 255, 255));
        btExcluirTerceiro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/delete.png"))); // NOI18N
        btExcluirTerceiro.setMaximumSize(new java.awt.Dimension(60, 40));
        btExcluirTerceiro.setMinimumSize(new java.awt.Dimension(60, 40));
        btExcluirTerceiro.setPreferredSize(new java.awt.Dimension(60, 40));
        btExcluirTerceiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExcluirTerceiroActionPerformed(evt);
            }
        });
        kGAlterarTerceiro.add(btExcluirTerceiro, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 160, -1, -1));

        btCancelar.setBackground(new java.awt.Color(56, 178, 255));
        btCancelar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/cancel.png"))); // NOI18N
        btCancelar.setEnabled(false);
        btCancelar.setMaximumSize(new java.awt.Dimension(60, 40));
        btCancelar.setMinimumSize(new java.awt.Dimension(60, 40));
        btCancelar.setPreferredSize(new java.awt.Dimension(60, 40));
        btCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarActionPerformed(evt);
            }
        });
        kGAlterarTerceiro.add(btCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 100, -1, -1));

        jPanelEntradadeProcessos.add(kGAlterarTerceiro);

        kGPIncluirTerceiro.setBackground(new java.awt.Color(172, 193, 212));
        kGPIncluirTerceiro.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Incluir Terceiros", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(51, 51, 51))); // NOI18N
        kGPIncluirTerceiro.setkBorderRadius(0);
        kGPIncluirTerceiro.setkEndColor(new java.awt.Color(234, 239, 243));
        kGPIncluirTerceiro.setkGradientFocus(0);
        kGPIncluirTerceiro.setkStartColor(new java.awt.Color(255, 255, 255));
        kGPIncluirTerceiro.setMinimumSize(new java.awt.Dimension(1280, 200));
        kGPIncluirTerceiro.setPreferredSize(new java.awt.Dimension(1280, 220));
        kGPIncluirTerceiro.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbServicos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cod", "Cobertura", "Nome Terceiro", "Placa", "Observações"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbServicos.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tbServicos);
        if (tbServicos.getColumnModel().getColumnCount() > 0) {
            tbServicos.getColumnModel().getColumn(0).setMinWidth(50);
            tbServicos.getColumnModel().getColumn(0).setPreferredWidth(50);
            tbServicos.getColumnModel().getColumn(0).setMaxWidth(50);
            tbServicos.getColumnModel().getColumn(1).setMinWidth(150);
            tbServicos.getColumnModel().getColumn(1).setPreferredWidth(150);
            tbServicos.getColumnModel().getColumn(1).setMaxWidth(150);
            tbServicos.getColumnModel().getColumn(3).setMinWidth(100);
            tbServicos.getColumnModel().getColumn(3).setPreferredWidth(100);
            tbServicos.getColumnModel().getColumn(3).setMaxWidth(100);
        }

        kGPIncluirTerceiro.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, 1100, 100));

        jLNomeSegurado1.setForeground(new java.awt.Color(51, 51, 51));
        jLNomeSegurado1.setText("Nome do Terceiro");
        kGPIncluirTerceiro.add(jLNomeSegurado1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 140, 10));

        jLPlacaSegurado1.setForeground(new java.awt.Color(51, 51, 51));
        jLPlacaSegurado1.setText("Placa Terceiro");
        kGPIncluirTerceiro.add(jLPlacaSegurado1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 30, 100, 10));

        jLObsSegurado1.setForeground(new java.awt.Color(51, 51, 51));
        jLObsSegurado1.setText("Observações");
        kGPIncluirTerceiro.add(jLObsSegurado1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 30, 80, 10));

        tfTerceiroPlaca.setForeground(new java.awt.Color(51, 51, 51));
        tfTerceiroPlaca.setMinimumSize(new java.awt.Dimension(100, 28));
        tfTerceiroPlaca.setPreferredSize(new java.awt.Dimension(100, 28));
        tfTerceiroPlaca.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfTerceiroPlacaFocusLost(evt);
            }
        });
        kGPIncluirTerceiro.add(tfTerceiroPlaca, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 50, 100, 30));

        tfTerceiroObs.setForeground(new java.awt.Color(51, 51, 51));
        tfTerceiroObs.setMinimumSize(new java.awt.Dimension(100, 28));
        tfTerceiroObs.setPreferredSize(new java.awt.Dimension(100, 28));
        tfTerceiroObs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfTerceiroObsActionPerformed(evt);
            }
        });
        kGPIncluirTerceiro.add(tfTerceiroObs, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 50, 720, 30));

        tfTerceiroNome.setPreferredSize(new java.awt.Dimension(320, 28));
        tfTerceiroNome.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfTerceiroNomeFocusLost(evt);
            }
        });
        kGPIncluirTerceiro.add(tfTerceiroNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 320, -1));

        jButtonAddTerceiro.setBackground(new java.awt.Color(0, 153, 0));
        jButtonAddTerceiro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Add.png"))); // NOI18N
        jButtonAddTerceiro.setPreferredSize(new java.awt.Dimension(60, 40));
        jButtonAddTerceiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddTerceiroActionPerformed(evt);
            }
        });
        kGPIncluirTerceiro.add(jButtonAddTerceiro, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, -1));

        jButtonRemoveTerceiro.setBackground(new java.awt.Color(204, 0, 0));
        jButtonRemoveTerceiro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Remove.png"))); // NOI18N
        jButtonRemoveTerceiro.setPreferredSize(new java.awt.Dimension(60, 40));
        jButtonRemoveTerceiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoveTerceiroActionPerformed(evt);
            }
        });
        kGPIncluirTerceiro.add(jButtonRemoveTerceiro, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, -1, -1));

        jPanelEntradadeProcessos.add(kGPIncluirTerceiro);

        jTPanelProcessos.addTab("INFORMAÇÕES DO PROCESSO", jPanelEntradadeProcessos);

        jPanelSevicosDespesas.setBackground(new java.awt.Color(255, 255, 255));
        jPanelSevicosDespesas.setMinimumSize(new java.awt.Dimension(1300, 660));
        jPanelSevicosDespesas.setPreferredSize(new java.awt.Dimension(1300, 660));

        kGPAplicacaoFixoMeio.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Valores do Processo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(51, 51, 51))); // NOI18N
        kGPAplicacaoFixoMeio.setkBorderRadius(0);
        kGPAplicacaoFixoMeio.setkEndColor(new java.awt.Color(234, 239, 243));
        kGPAplicacaoFixoMeio.setkGradientFocus(0);
        kGPAplicacaoFixoMeio.setkStartColor(new java.awt.Color(234, 239, 243));
        kGPAplicacaoFixoMeio.setMinimumSize(new java.awt.Dimension(1280, 160));
        kGPAplicacaoFixoMeio.setPreferredSize(new java.awt.Dimension(1280, 160));
        kGPAplicacaoFixoMeio.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLHonorarioDefinido.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLHonorarioDefinido.setForeground(new java.awt.Color(51, 51, 51));
        jLHonorarioDefinido.setText("Valor Defenido");
        kGPAplicacaoFixoMeio.add(jLHonorarioDefinido, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 110, 20));

        jFHonorarioDefinido.setForeground(new java.awt.Color(51, 51, 51));
        jFHonorarioDefinido.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getCurrencyInstance())));
        jFHonorarioDefinido.setText("R$ 0,00");
        jFHonorarioDefinido.setPreferredSize(new java.awt.Dimension(100, 28));
        jFHonorarioDefinido.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jFHonorarioDefinidoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jFHonorarioDefinidoFocusLost(evt);
            }
        });
        kGPAplicacaoFixoMeio.add(jFHonorarioDefinido, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, -1, 30));

        kGPHonorarios.setBackground(new java.awt.Color(228, 235, 241));
        kGPHonorarios.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "INFORMAÇÕES DA NF.", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(51, 51, 51))); // NOI18N
        kGPHonorarios.setForeground(new java.awt.Color(51, 51, 51));
        kGPHonorarios.setkBorderRadius(0);
        kGPHonorarios.setkEndColor(new java.awt.Color(234, 239, 243));
        kGPHonorarios.setkGradientFocus(0);
        kGPHonorarios.setkStartColor(new java.awt.Color(255, 255, 255));
        kGPHonorarios.setPreferredSize(new java.awt.Dimension(1280, 160));
        kGPHonorarios.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLNumeroNFHonorarios.setForeground(new java.awt.Color(51, 51, 51));
        jLNumeroNFHonorarios.setText("Número NF.  Honorários");
        kGPHonorarios.add(jLNumeroNFHonorarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 150, 20));

        jFDataEmissaoNFhonorarios.setForeground(new java.awt.Color(51, 51, 51));
        try {
            jFDataEmissaoNFhonorarios.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFDataEmissaoNFhonorarios.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jFDataEmissaoNFhonorariosFocusLost(evt);
            }
        });
        kGPHonorarios.add(jFDataEmissaoNFhonorarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 70, 130, 28));

        jFDataPrevisaoPgtoNFhonorarios.setForeground(new java.awt.Color(51, 51, 51));
        try {
            jFDataPrevisaoPgtoNFhonorarios.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFDataPrevisaoPgtoNFhonorarios.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jFDataPrevisaoPgtoNFhonorariosFocusLost(evt);
            }
        });
        kGPHonorarios.add(jFDataPrevisaoPgtoNFhonorarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 70, 130, 28));

        jLPrevisaoPagamentoHonorarios.setForeground(new java.awt.Color(51, 51, 51));
        jLPrevisaoPagamentoHonorarios.setText("Previsão de Pagamento");
        kGPHonorarios.add(jLPrevisaoPagamentoHonorarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 50, 150, 20));

        jLDataPagamentoHonorarios.setForeground(new java.awt.Color(51, 51, 51));
        jLDataPagamentoHonorarios.setText("Data do Pagamento");
        kGPHonorarios.add(jLDataPagamentoHonorarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 50, 130, 20));

        jLSituacaoPagamentoHonorarios.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLSituacaoPagamentoHonorarios.setForeground(new java.awt.Color(51, 51, 51));
        jLSituacaoPagamentoHonorarios.setText("Status do Processo");
        kGPHonorarios.add(jLSituacaoPagamentoHonorarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 30, 150, 20));

        jFDataPgtoNFhonorarios.setForeground(new java.awt.Color(51, 51, 51));
        try {
            jFDataPgtoNFhonorarios.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFDataPgtoNFhonorarios.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jFDataPgtoNFhonorariosFocusLost(evt);
            }
        });
        kGPHonorarios.add(jFDataPgtoNFhonorarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 70, 130, 28));

        jLDataEmissaoNFHonorarios.setForeground(new java.awt.Color(51, 51, 51));
        jLDataEmissaoNFHonorarios.setText("Data Emissão NF.");
        kGPHonorarios.add(jLDataEmissaoNFHonorarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 50, 130, 20));

        tfNumeroNFProcesso.setPreferredSize(new java.awt.Dimension(230, 28));
        tfNumeroNFProcesso.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfNumeroNFProcessoFocusLost(evt);
            }
        });
        kGPHonorarios.add(tfNumeroNFProcesso, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 230, 28));

        cbStatusNotaFiscal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecionar", "Regular", "A Deliberação", "Negado" }));
        kGPHonorarios.add(cbStatusNotaFiscal, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 50, 180, 28));

        kGPAplicacaoFixoMeio.add(kGPHonorarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 0, 970, -1));

        jLHonorarioObs.setForeground(new java.awt.Color(51, 51, 51));
        jLHonorarioObs.setText("Observações");
        kGPAplicacaoFixoMeio.add(jLHonorarioObs, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 90, 20));

        tfObsHonorariosProcesso.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfObsHonorariosProcessoFocusLost(evt);
            }
        });
        kGPAplicacaoFixoMeio.add(tfObsHonorariosProcesso, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 240, 28));

        jLabel1.setText("Total Despesas");
        kGPAplicacaoFixoMeio.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 30, 100, 20));

        jFValorDespesasTotalRegistro.setForeground(new java.awt.Color(51, 51, 51));
        jFValorDespesasTotalRegistro.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getCurrencyInstance())));
        jFValorDespesasTotalRegistro.setText("R$ 0,00");
        jFValorDespesasTotalRegistro.setPreferredSize(new java.awt.Dimension(100, 28));
        jFValorDespesasTotalRegistro.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jFValorDespesasTotalRegistroFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jFValorDespesasTotalRegistroFocusLost(evt);
            }
        });
        kGPAplicacaoFixoMeio.add(jFValorDespesasTotalRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 50, 110, 30));

        jPanelSevicosDespesas.add(kGPAplicacaoFixoMeio);

        kGPRetencao.setBackground(new java.awt.Color(228, 235, 241));
        kGPRetencao.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Retenção", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(51, 51, 51))); // NOI18N
        kGPRetencao.setForeground(new java.awt.Color(51, 51, 51));
        kGPRetencao.setkBorderRadius(0);
        kGPRetencao.setkEndColor(new java.awt.Color(234, 239, 243));
        kGPRetencao.setkGradientFocus(0);
        kGPRetencao.setkStartColor(new java.awt.Color(255, 255, 255));
        kGPRetencao.setPreferredSize(new java.awt.Dimension(1280, 140));
        kGPRetencao.setLayout(null);

        jLCidadeTomador.setForeground(new java.awt.Color(51, 51, 51));
        jLCidadeTomador.setText("Cidade - Tomador");
        kGPRetencao.add(jLCidadeTomador);
        jLCidadeTomador.setBounds(420, 30, 200, 20);

        jLRetidoRetencao.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLRetidoRetencao.setForeground(new java.awt.Color(51, 51, 51));
        jLRetidoRetencao.setText("Valor Retido");
        kGPRetencao.add(jLRetidoRetencao);
        jLRetidoRetencao.setBounds(130, 30, 90, 20);

        jLAlicotaRetencao.setForeground(new java.awt.Color(51, 51, 51));
        jLAlicotaRetencao.setText("Alicota");
        kGPRetencao.add(jLAlicotaRetencao);
        jLAlicotaRetencao.setBounds(40, 30, 90, 20);

        jLRetencaoObs.setForeground(new java.awt.Color(51, 51, 51));
        jLRetencaoObs.setText("Observações:");
        kGPRetencao.add(jLRetencaoObs);
        jLRetencaoObs.setBounds(1080, 30, 90, 20);

        jLTotalRedito.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLTotalRedito.setForeground(new java.awt.Color(51, 51, 51));
        jLTotalRedito.setText("Valor Total");
        kGPRetencao.add(jLTotalRedito);
        jLTotalRedito.setBounds(240, 30, 90, 20);

        jLEstadoTomador.setForeground(new java.awt.Color(51, 51, 51));
        jLEstadoTomador.setText("Estado");
        kGPRetencao.add(jLEstadoTomador);
        jLEstadoTomador.setBounds(360, 30, 50, 20);

        jFValorHonorariosRetido.setEditable(false);
        jFValorHonorariosRetido.setBackground(new java.awt.Color(102, 102, 102));
        jFValorHonorariosRetido.setForeground(new java.awt.Color(255, 255, 255));
        jFValorHonorariosRetido.setText("R$ 0,00");
        jFValorHonorariosRetido.setMinimumSize(new java.awt.Dimension(80, 35));
        jFValorHonorariosRetido.setPreferredSize(new java.awt.Dimension(100, 35));
        jFValorHonorariosRetido.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jFValorHonorariosRetidoFocusGained(evt);
            }
        });
        jFValorHonorariosRetido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jFValorHonorariosRetidoMouseClicked(evt);
            }
        });
        kGPRetencao.add(jFValorHonorariosRetido);
        jFValorHonorariosRetido.setBounds(130, 50, 100, 35);

        jFValorTotalHonorariosComRetencao.setEditable(false);
        jFValorTotalHonorariosComRetencao.setBackground(new java.awt.Color(102, 102, 102));
        jFValorTotalHonorariosComRetencao.setForeground(new java.awt.Color(255, 255, 255));
        jFValorTotalHonorariosComRetencao.setText("R$ 0,00");
        jFValorTotalHonorariosComRetencao.setMinimumSize(new java.awt.Dimension(80, 35));
        jFValorTotalHonorariosComRetencao.setPreferredSize(new java.awt.Dimension(100, 35));
        kGPRetencao.add(jFValorTotalHonorariosComRetencao);
        jFValorTotalHonorariosComRetencao.setBounds(240, 50, 100, 35);

        jFRetidoReal.setEditable(false);
        jFRetidoReal.setBackground(new java.awt.Color(102, 102, 102));
        jFRetidoReal.setForeground(new java.awt.Color(255, 255, 255));
        jFRetidoReal.setText("R$ 0,00");
        jFRetidoReal.setPreferredSize(new java.awt.Dimension(100, 35));
        kGPRetencao.add(jFRetidoReal);
        jFRetidoReal.setBounds(860, 50, 100, 35);

        tfPercentualRetidoReal.setEditable(false);
        tfPercentualRetidoReal.setBackground(new java.awt.Color(102, 102, 102));
        tfPercentualRetidoReal.setForeground(new java.awt.Color(255, 255, 255));
        tfPercentualRetidoReal.setText("0,00%");
        tfPercentualRetidoReal.setPreferredSize(new java.awt.Dimension(100, 35));
        kGPRetencao.add(tfPercentualRetidoReal);
        tfPercentualRetidoReal.setBounds(970, 50, 100, 35);

        jLAlicotaProcessoReal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLAlicotaProcessoReal.setForeground(new java.awt.Color(51, 51, 51));
        jLAlicotaProcessoReal.setText("Alícota Real");
        kGPRetencao.add(jLAlicotaProcessoReal);
        jLAlicotaProcessoReal.setBounds(970, 30, 90, 15);

        jLValorNotaIndevido.setForeground(new java.awt.Color(51, 51, 51));
        jLValorNotaIndevido.setText("Valor da Nota");
        kGPRetencao.add(jLValorNotaIndevido);
        jLValorNotaIndevido.setBounds(750, 30, 100, 14);

        cbAlicotaPercentualHonorarios.setBackground(new java.awt.Color(153, 153, 153));
        cbAlicotaPercentualHonorarios.setEditable(true);
        cbAlicotaPercentualHonorarios.setForeground(new java.awt.Color(51, 51, 51));
        cbAlicotaPercentualHonorarios.setBorder(null);
        cbAlicotaPercentualHonorarios.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
                cbAlicotaPercentualHonorariosPopupMenuCanceled(evt);
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbAlicotaPercentualHonorariosPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                cbAlicotaPercentualHonorariosPopupMenuWillBecomeVisible(evt);
            }
        });
        kGPRetencao.add(cbAlicotaPercentualHonorarios);
        cbAlicotaPercentualHonorarios.setBounds(40, 50, 80, 35);

        ObsRetencaoAlicotaIndevido.setForeground(new java.awt.Color(51, 51, 51));
        ObsRetencaoAlicotaIndevido.setEnabled(false);
        ObsRetencaoAlicotaIndevido.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                ObsRetencaoAlicotaIndevidoFocusLost(evt);
            }
        });
        kGPRetencao.add(ObsRetencaoAlicotaIndevido);
        ObsRetencaoAlicotaIndevido.setBounds(1080, 50, 160, 28);

        jFValorNotaRealIndevido.setForeground(new java.awt.Color(51, 51, 51));
        jFValorNotaRealIndevido.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getCurrencyInstance())));
        jFValorNotaRealIndevido.setText("R$ 0,00");
        jFValorNotaRealIndevido.setEnabled(false);
        jFValorNotaRealIndevido.setPreferredSize(new java.awt.Dimension(100, 28));
        jFValorNotaRealIndevido.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jFValorNotaRealIndevidoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jFValorNotaRealIndevidoFocusLost(evt);
            }
        });
        kGPRetencao.add(jFValorNotaRealIndevido);
        jFValorNotaRealIndevido.setBounds(750, 50, 100, 28);

        jLRetidoReal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLRetidoReal.setForeground(new java.awt.Color(51, 51, 51));
        jLRetidoReal.setText("Retido Real");
        kGPRetencao.add(jLRetidoReal);
        jLRetidoReal.setBounds(860, 30, 110, 15);

        jLTextoAbaixoCheckBox.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLTextoAbaixoCheckBox.setForeground(new java.awt.Color(153, 0, 0));
        jLTextoAbaixoCheckBox.setText("Indevido");
        kGPRetencao.add(jLTextoAbaixoCheckBox);
        jLTextoAbaixoCheckBox.setBounds(660, 70, 100, 20);

        jRCheckIndevidoISS.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jRCheckIndevidoISS.setForeground(new java.awt.Color(153, 0, 0));
        jRCheckIndevidoISS.setText("Pagamento");
        jRCheckIndevidoISS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRCheckIndevidoISSActionPerformed(evt);
            }
        });
        kGPRetencao.add(jRCheckIndevidoISS);
        jRCheckIndevidoISS.setBounds(640, 50, 100, 23);

        jLNaoRetemHonorario.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLNaoRetemHonorario.setForeground(new java.awt.Color(204, 204, 204));
        jLNaoRetemHonorario.setText("SEGURADORA NÃO RETEM!");
        kGPRetencao.add(jLNaoRetemHonorario);
        jLNaoRetemHonorario.setBounds(100, 70, 290, 17);

        kGradientPanel6.setkBorderRadius(0);
        kGradientPanel6.setkEndColor(new java.awt.Color(234, 239, 243));
        kGradientPanel6.setkGradientFocus(0);
        kGradientPanel6.setkStartColor(new java.awt.Color(234, 239, 243));
        kGPRetencao.add(kGradientPanel6);
        kGradientPanel6.setBounds(630, 0, 650, 140);

        tfEstadoRetencao.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tfEstadoRetencao.setText("Estado Iss");
        kGPRetencao.add(tfEstadoRetencao);
        tfEstadoRetencao.setBounds(360, 60, 60, 15);

        tfCidadeRetencao.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tfCidadeRetencao.setText("Cidade Iss");
        kGPRetencao.add(tfCidadeRetencao);
        tfCidadeRetencao.setBounds(420, 60, 200, 15);

        jPanelSevicosDespesas.add(kGPRetencao);

        kGPNegativa.setBackground(new java.awt.Color(228, 235, 241));
        kGPNegativa.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Negativa", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(51, 51, 51))); // NOI18N
        kGPNegativa.setForeground(new java.awt.Color(51, 51, 51));
        kGPNegativa.setkBorderRadius(0);
        kGPNegativa.setkEndColor(new java.awt.Color(255, 255, 255));
        kGPNegativa.setkGradientFocus(0);
        kGPNegativa.setkStartColor(new java.awt.Color(234, 239, 243));
        kGPNegativa.setMinimumSize(new java.awt.Dimension(1250, 180));
        kGPNegativa.setPreferredSize(new java.awt.Dimension(1280, 140));
        kGPNegativa.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLNegativaValor.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLNegativaValor.setForeground(new java.awt.Color(51, 51, 51));
        jLNegativaValor.setText("Valor da Negativa");
        kGPNegativa.add(jLNegativaValor, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 40, 100, 20));

        jLPrejuizoValor.setForeground(new java.awt.Color(51, 51, 51));
        jLPrejuizoValor.setText("Prejuízo Seg.");
        kGPNegativa.add(jLPrejuizoValor, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 80, 20));

        jLNumeroNFNegativa.setForeground(new java.awt.Color(51, 51, 51));
        jLNumeroNFNegativa.setText("Número NF. Negativa");
        kGPNegativa.add(jLNumeroNFNegativa, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 40, 150, 20));

        jLDataEmissaoNegativa.setForeground(new java.awt.Color(51, 51, 51));
        jLDataEmissaoNegativa.setText("Data Emissão NF.");
        kGPNegativa.add(jLDataEmissaoNegativa, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 40, 130, 20));

        jLPrevPgtoNegativa.setForeground(new java.awt.Color(51, 51, 51));
        jLPrevPgtoNegativa.setText("Previsão de Pagamento");
        kGPNegativa.add(jLPrevPgtoNegativa, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 40, 130, 20));

        jLDataPgtoNegativa.setForeground(new java.awt.Color(51, 51, 51));
        jLDataPgtoNegativa.setText("Data do Pagamento");
        kGPNegativa.add(jLDataPgtoNegativa, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 40, 130, 20));

        jLSituacaoPagamento.setForeground(new java.awt.Color(51, 51, 51));
        jLSituacaoPagamento.setText("Status da Negativa");
        kGPNegativa.add(jLSituacaoPagamento, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 40, 150, 20));

        tfNumeroNFNegativa.setForeground(new java.awt.Color(51, 51, 51));
        tfNumeroNFNegativa.setMinimumSize(new java.awt.Dimension(130, 24));
        tfNumeroNFNegativa.setPreferredSize(new java.awt.Dimension(130, 24));
        tfNumeroNFNegativa.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfNumeroNFNegativaFocusLost(evt);
            }
        });
        kGPNegativa.add(tfNumeroNFNegativa, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 60, 230, 28));

        jFValorPrejuizoNegativa.setForeground(new java.awt.Color(51, 51, 51));
        jFValorPrejuizoNegativa.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getCurrencyInstance())));
        jFValorPrejuizoNegativa.setText("R$ 0,00");
        jFValorPrejuizoNegativa.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jFValorPrejuizoNegativaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jFValorPrejuizoNegativaFocusLost(evt);
            }
        });
        kGPNegativa.add(jFValorPrejuizoNegativa, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 100, 28));

        jFValorNegativaTotal.setForeground(new java.awt.Color(51, 51, 51));
        jFValorNegativaTotal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getCurrencyInstance())));
        jFValorNegativaTotal.setText("R$ 0,00");
        jFValorNegativaTotal.setMinimumSize(new java.awt.Dimension(80, 24));
        jFValorNegativaTotal.setPreferredSize(new java.awt.Dimension(80, 24));
        jFValorNegativaTotal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jFValorNegativaTotalFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jFValorNegativaTotalFocusLost(evt);
            }
        });
        kGPNegativa.add(jFValorNegativaTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 60, 100, 28));

        jFDataEmissaoNegativa.setForeground(new java.awt.Color(51, 51, 51));
        try {
            jFDataEmissaoNegativa.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFDataEmissaoNegativa.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jFDataEmissaoNegativaFocusLost(evt);
            }
        });
        kGPNegativa.add(jFDataEmissaoNegativa, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 60, 130, 28));

        jFDataPrevisaoPgtoNegativa.setForeground(new java.awt.Color(51, 51, 51));
        try {
            jFDataPrevisaoPgtoNegativa.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFDataPrevisaoPgtoNegativa.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jFDataPrevisaoPgtoNegativaFocusLost(evt);
            }
        });
        kGPNegativa.add(jFDataPrevisaoPgtoNegativa, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 60, 130, 28));

        jFDataPgtoNegativa.setForeground(new java.awt.Color(51, 51, 51));
        try {
            jFDataPgtoNegativa.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFDataPgtoNegativa.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jFDataPgtoNegativaFocusLost(evt);
            }
        });
        kGPNegativa.add(jFDataPgtoNegativa, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 60, 130, 28));

        jLNaoPagaNegativa.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLNaoPagaNegativa.setForeground(new java.awt.Color(204, 204, 204));
        jLNaoPagaNegativa.setText("SEGURADORA NÃO PAGA!");
        kGPNegativa.add(jLNaoPagaNegativa, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, 340, -1));

        cbSituacaoPgtoNegativa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecionar", "Regular", "A Deliberação", "Negado" }));
        kGPNegativa.add(cbSituacaoPgtoNegativa, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 60, 180, 28));

        jPanelSevicosDespesas.add(kGPNegativa);

        kGPRetencaoNeg.setBackground(new java.awt.Color(228, 235, 241));
        kGPRetencaoNeg.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Negativa Retenção", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(51, 51, 51))); // NOI18N
        kGPRetencaoNeg.setForeground(new java.awt.Color(51, 51, 51));
        kGPRetencaoNeg.setkBorderRadius(0);
        kGPRetencaoNeg.setkEndColor(new java.awt.Color(234, 239, 243));
        kGPRetencaoNeg.setkGradientFocus(0);
        kGPRetencaoNeg.setkStartColor(new java.awt.Color(255, 255, 255));
        kGPRetencaoNeg.setPreferredSize(new java.awt.Dimension(1280, 140));
        kGPRetencaoNeg.setLayout(null);

        jLCidadeRetencaoNeg.setForeground(new java.awt.Color(51, 51, 51));
        jLCidadeRetencaoNeg.setText("Cidade - Tomador");
        kGPRetencaoNeg.add(jLCidadeRetencaoNeg);
        jLCidadeRetencaoNeg.setBounds(420, 30, 200, 20);

        jLRetidoNeg.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLRetidoNeg.setForeground(new java.awt.Color(51, 51, 51));
        jLRetidoNeg.setText("Valor Retido");
        kGPRetencaoNeg.add(jLRetidoNeg);
        jLRetidoNeg.setBounds(130, 30, 80, 20);

        jLTaxaIssNeg.setForeground(new java.awt.Color(51, 51, 51));
        jLTaxaIssNeg.setText("Alicota");
        kGPRetencaoNeg.add(jLTaxaIssNeg);
        jLTaxaIssNeg.setBounds(40, 30, 77, 20);

        jLObsRetencaoNegativa.setForeground(new java.awt.Color(51, 51, 51));
        jLObsRetencaoNegativa.setText("Observações:");
        kGPRetencaoNeg.add(jLObsRetencaoNegativa);
        jLObsRetencaoNegativa.setBounds(1080, 30, 90, 20);

        jLRetencaoValorTotalNeg.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLRetencaoValorTotalNeg.setForeground(new java.awt.Color(51, 51, 51));
        jLRetencaoValorTotalNeg.setText("Valor Total");
        kGPRetencaoNeg.add(jLRetencaoValorTotalNeg);
        jLRetencaoValorTotalNeg.setBounds(240, 30, 80, 20);

        jLUfRetencaoNeg.setForeground(new java.awt.Color(51, 51, 51));
        jLUfRetencaoNeg.setText("Estado");
        kGPRetencaoNeg.add(jLUfRetencaoNeg);
        jLUfRetencaoNeg.setBounds(360, 30, 50, 20);

        jFValorRetidoRetencaoNeg.setEditable(false);
        jFValorRetidoRetencaoNeg.setBackground(new java.awt.Color(102, 102, 102));
        jFValorRetidoRetencaoNeg.setForeground(new java.awt.Color(255, 255, 255));
        jFValorRetidoRetencaoNeg.setText("R$ 0,00");
        jFValorRetidoRetencaoNeg.setMinimumSize(new java.awt.Dimension(80, 35));
        jFValorRetidoRetencaoNeg.setPreferredSize(new java.awt.Dimension(80, 35));
        jFValorRetidoRetencaoNeg.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jFValorRetidoRetencaoNegFocusGained(evt);
            }
        });
        jFValorRetidoRetencaoNeg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jFValorRetidoRetencaoNegMouseClicked(evt);
            }
        });
        kGPRetencaoNeg.add(jFValorRetidoRetencaoNeg);
        jFValorRetidoRetencaoNeg.setBounds(130, 50, 100, 35);

        jFValorRetencaoTotalNeg.setEditable(false);
        jFValorRetencaoTotalNeg.setBackground(new java.awt.Color(102, 102, 102));
        jFValorRetencaoTotalNeg.setForeground(new java.awt.Color(255, 255, 255));
        jFValorRetencaoTotalNeg.setText("R$ 0,00");
        jFValorRetencaoTotalNeg.setMinimumSize(new java.awt.Dimension(80, 35));
        jFValorRetencaoTotalNeg.setPreferredSize(new java.awt.Dimension(80, 35));
        kGPRetencaoNeg.add(jFValorRetencaoTotalNeg);
        jFValorRetencaoTotalNeg.setBounds(240, 50, 100, 35);

        jLTextoAbaixoCheckBoxIndevidoNegativa.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLTextoAbaixoCheckBoxIndevidoNegativa.setForeground(new java.awt.Color(153, 0, 0));
        jLTextoAbaixoCheckBoxIndevidoNegativa.setText("Indevido");
        kGPRetencaoNeg.add(jLTextoAbaixoCheckBoxIndevidoNegativa);
        jLTextoAbaixoCheckBoxIndevidoNegativa.setBounds(660, 70, 100, 20);

        jRCheckIndevidoISSNegativa.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jRCheckIndevidoISSNegativa.setForeground(new java.awt.Color(153, 0, 0));
        jRCheckIndevidoISSNegativa.setText("Pagamento");
        jRCheckIndevidoISSNegativa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRCheckIndevidoISSNegativaActionPerformed(evt);
            }
        });
        kGPRetencaoNeg.add(jRCheckIndevidoISSNegativa);
        jRCheckIndevidoISSNegativa.setBounds(640, 50, 100, 23);

        jLRetidoRealNegativa.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLRetidoRealNegativa.setForeground(new java.awt.Color(51, 51, 51));
        jLRetidoRealNegativa.setText("Retido Real");
        kGPRetencaoNeg.add(jLRetidoRealNegativa);
        jLRetidoRealNegativa.setBounds(860, 30, 100, 15);

        jFRetidoRealNegativa.setEditable(false);
        jFRetidoRealNegativa.setBackground(new java.awt.Color(102, 102, 102));
        jFRetidoRealNegativa.setForeground(new java.awt.Color(255, 255, 255));
        jFRetidoRealNegativa.setText("R$ 0,00");
        jFRetidoRealNegativa.setPreferredSize(new java.awt.Dimension(80, 35));
        kGPRetencaoNeg.add(jFRetidoRealNegativa);
        jFRetidoRealNegativa.setBounds(860, 50, 100, 35);

        tfPercentuallRetidoRealNeg.setEditable(false);
        tfPercentuallRetidoRealNeg.setBackground(new java.awt.Color(102, 102, 102));
        tfPercentuallRetidoRealNeg.setForeground(new java.awt.Color(255, 255, 255));
        tfPercentuallRetidoRealNeg.setText("0,00%");
        tfPercentuallRetidoRealNeg.setPreferredSize(new java.awt.Dimension(80, 35));
        kGPRetencaoNeg.add(tfPercentuallRetidoRealNeg);
        tfPercentuallRetidoRealNeg.setBounds(970, 50, 100, 35);

        jLAlicotalRealNegativa.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLAlicotalRealNegativa.setForeground(new java.awt.Color(51, 51, 51));
        jLAlicotalRealNegativa.setText("Alícota Real");
        kGPRetencaoNeg.add(jLAlicotalRealNegativa);
        jLAlicotalRealNegativa.setBounds(970, 30, 90, 15);

        jLValorNotaIndevidoNegativa.setForeground(new java.awt.Color(51, 51, 51));
        jLValorNotaIndevidoNegativa.setText("Valor da Negativa");
        kGPRetencaoNeg.add(jLValorNotaIndevidoNegativa);
        jLValorNotaIndevidoNegativa.setBounds(750, 30, 100, 14);

        jFValorNotaRealNegIndevido.setForeground(new java.awt.Color(51, 51, 51));
        jFValorNotaRealNegIndevido.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getCurrencyInstance())));
        jFValorNotaRealNegIndevido.setText("R$ 0,00");
        jFValorNotaRealNegIndevido.setEnabled(false);
        jFValorNotaRealNegIndevido.setPreferredSize(new java.awt.Dimension(80, 28));
        jFValorNotaRealNegIndevido.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jFValorNotaRealNegIndevidoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jFValorNotaRealNegIndevidoFocusLost(evt);
            }
        });
        kGPRetencaoNeg.add(jFValorNotaRealNegIndevido);
        jFValorNotaRealNegIndevido.setBounds(750, 50, 100, 28);

        tfObsRetencaoISSNeg.setForeground(new java.awt.Color(51, 51, 51));
        tfObsRetencaoISSNeg.setEnabled(false);
        tfObsRetencaoISSNeg.setPreferredSize(new java.awt.Dimension(180, 28));
        tfObsRetencaoISSNeg.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfObsRetencaoISSNegFocusLost(evt);
            }
        });
        kGPRetencaoNeg.add(tfObsRetencaoISSNeg);
        tfObsRetencaoISSNeg.setBounds(1080, 50, 160, 28);

        cbAlicotaNeg.setBackground(new java.awt.Color(153, 153, 153));
        cbAlicotaNeg.setEditable(true);
        cbAlicotaNeg.setForeground(new java.awt.Color(51, 51, 51));
        cbAlicotaNeg.setBorder(null);
        cbAlicotaNeg.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
                cbAlicotaNegPopupMenuCanceled(evt);
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbAlicotaNegPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        kGPRetencaoNeg.add(cbAlicotaNeg);
        cbAlicotaNeg.setBounds(40, 50, 80, 35);

        jLNaoRetemNegativa.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLNaoRetemNegativa.setForeground(new java.awt.Color(204, 204, 204));
        jLNaoRetemNegativa.setText("SEGURADORA NÃO RETEM!");
        kGPRetencaoNeg.add(jLNaoRetemNegativa);
        jLNaoRetemNegativa.setBounds(100, 60, 420, 17);

        kGradientPanel5.setkBorderRadius(0);
        kGradientPanel5.setkEndColor(new java.awt.Color(234, 239, 243));
        kGradientPanel5.setkGradientFocus(0);
        kGradientPanel5.setkStartColor(new java.awt.Color(234, 239, 243));
        kGPRetencaoNeg.add(kGradientPanel5);
        kGradientPanel5.setBounds(630, 0, 660, 140);

        tfCidadeRetencaoNeg.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tfCidadeRetencaoNeg.setText("Cidade Negativa");
        kGPRetencaoNeg.add(tfCidadeRetencaoNeg);
        tfCidadeRetencaoNeg.setBounds(420, 60, 200, 15);

        tfEstadoRetencaoNeg.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tfEstadoRetencaoNeg.setText("EstadoNeg");
        kGPRetencaoNeg.add(tfEstadoRetencaoNeg);
        tfEstadoRetencaoNeg.setBounds(360, 60, 50, 15);

        jPanelSevicosDespesas.add(kGPRetencaoNeg);

        jTPanelProcessos.addTab("HONORÁRIOS E RETENÇÕES", jPanelSevicosDespesas);

        kGradientPanel1.add(jTPanelProcessos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -30, -1, 630));
        jTPanelProcessos.getAccessibleContext().setAccessibleName("Cadastro Ordem de Processos");

        jPanelSalvarCancelar.setBackground(new java.awt.Color(255, 255, 255));
        jPanelSalvarCancelar.setMinimumSize(new java.awt.Dimension(1300, 25));
        jPanelSalvarCancelar.setName(""); // NOI18N
        jPanelSalvarCancelar.setPreferredSize(new java.awt.Dimension(1300, 50));
        jPanelSalvarCancelar.setRequestFocusEnabled(false);

        btCancelarServicos1.setBackground(new java.awt.Color(255, 0, 0));
        btCancelarServicos1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btCancelarServicos1.setForeground(new java.awt.Color(255, 255, 255));
        btCancelarServicos1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Close.png"))); // NOI18N
        btCancelarServicos1.setText("CANCELAR");
        btCancelarServicos1.setMaximumSize(new java.awt.Dimension(120, 25));
        btCancelarServicos1.setMinimumSize(new java.awt.Dimension(120, 25));
        btCancelarServicos1.setPreferredSize(new java.awt.Dimension(130, 35));
        btCancelarServicos1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarServicos1ActionPerformed(evt);
            }
        });
        jPanelSalvarCancelar.add(btCancelarServicos1);

        btSalvarOp.setBackground(new java.awt.Color(0, 153, 0));
        btSalvarOp.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btSalvarOp.setForeground(new java.awt.Color(255, 255, 255));
        btSalvarOp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/save.png"))); // NOI18N
        btSalvarOp.setText("SALVAR");
        btSalvarOp.setMaximumSize(new java.awt.Dimension(120, 25));
        btSalvarOp.setMinimumSize(new java.awt.Dimension(120, 25));
        btSalvarOp.setPreferredSize(new java.awt.Dimension(150, 35));
        btSalvarOp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSalvarOpActionPerformed(evt);
            }
        });
        jPanelSalvarCancelar.add(btSalvarOp);

        kGradientPanel1.add(jPanelSalvarCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 600, -1, -1));

        getContentPane().add(kGradientPanel1, java.awt.BorderLayout.CENTER);

        kGradientPanel14.setBackground(new java.awt.Color(146, 171, 197));
        kGradientPanel14.setkBorderRadius(0);
        kGradientPanel14.setkEndColor(new java.awt.Color(146, 171, 197));
        kGradientPanel14.setkStartColor(new java.awt.Color(146, 171, 197));
        kGradientPanel14.setName(""); // NOI18N
        kGradientPanel14.setPreferredSize(new java.awt.Dimension(1300, 40));

        kButtonProcessos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Clipboard.png"))); // NOI18N
        kButtonProcessos.setText("          INFORMAÇÕES DO PROCESSO");
        kButtonProcessos.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        kButtonProcessos.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        kButtonProcessos.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        kButtonProcessos.setIconTextGap(15);
        kButtonProcessos.setkBackGroundColor(new java.awt.Color(255, 255, 255));
        kButtonProcessos.setkBorderRadius(0);
        kButtonProcessos.setkEndColor(new java.awt.Color(197, 201, 206));
        kButtonProcessos.setkForeGround(new java.awt.Color(51, 51, 51));
        kButtonProcessos.setkHoverEndColor(new java.awt.Color(234, 239, 243));
        kButtonProcessos.setkHoverForeGround(new java.awt.Color(51, 51, 51));
        kButtonProcessos.setkHoverStartColor(new java.awt.Color(234, 239, 243));
        kButtonProcessos.setkIndicatorColor(new java.awt.Color(0, 102, 255));
        kButtonProcessos.setkIndicatorThickness(0);
        kButtonProcessos.setkPressedColor(new java.awt.Color(234, 239, 243));
        kButtonProcessos.setkSelectedColor(new java.awt.Color(255, 255, 255));
        kButtonProcessos.setkStartColor(new java.awt.Color(197, 201, 206));
        kButtonProcessos.setPreferredSize(new java.awt.Dimension(210, 45));
        kButtonProcessos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButtonProcessosActionPerformed(evt);
            }
        });

        kButtonAndamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Dollar.png"))); // NOI18N
        kButtonAndamento.setText("        HONORÁRIOS E RETENÇÕES");
        kButtonAndamento.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
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
        kButtonAndamento.setkIndicatorColor(new java.awt.Color(0, 102, 255));
        kButtonAndamento.setkIndicatorThickness(0);
        kButtonAndamento.setkPressedColor(new java.awt.Color(234, 239, 243));
        kButtonAndamento.setkSelectedColor(new java.awt.Color(255, 255, 255));
        kButtonAndamento.setkStartColor(new java.awt.Color(197, 201, 206));
        kButtonAndamento.setPreferredSize(new java.awt.Dimension(240, 45));
        kButtonAndamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButtonAndamentoActionPerformed(evt);
            }
        });

        kButtonLimparDados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Erase.png"))); // NOI18N
        kButtonLimparDados.setText("LIMPAR DADOS");
        kButtonLimparDados.setToolTipText("");
        kButtonLimparDados.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        kButtonLimparDados.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        kButtonLimparDados.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        kButtonLimparDados.setIconTextGap(10);
        kButtonLimparDados.setInheritsPopupMenu(true);
        kButtonLimparDados.setkAllowTab(false);
        kButtonLimparDados.setkBackGroundColor(new java.awt.Color(255, 255, 255));
        kButtonLimparDados.setkBorderRadius(0);
        kButtonLimparDados.setkEndColor(new java.awt.Color(255, 255, 255));
        kButtonLimparDados.setkForeGround(new java.awt.Color(51, 51, 51));
        kButtonLimparDados.setkHoverEndColor(new java.awt.Color(234, 239, 243));
        kButtonLimparDados.setkHoverForeGround(new java.awt.Color(51, 51, 51));
        kButtonLimparDados.setkHoverStartColor(new java.awt.Color(234, 239, 243));
        kButtonLimparDados.setkIndicatorColor(new java.awt.Color(0, 102, 255));
        kButtonLimparDados.setkIndicatorThickness(0);
        kButtonLimparDados.setkPressedColor(new java.awt.Color(234, 239, 243));
        kButtonLimparDados.setkSelectedColor(new java.awt.Color(234, 239, 243));
        kButtonLimparDados.setkStartColor(new java.awt.Color(255, 255, 255));
        kButtonLimparDados.setPreferredSize(new java.awt.Dimension(200, 45));
        kButtonLimparDados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButtonLimparDadosActionPerformed(evt);
            }
        });

        kButtonEditarTudo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/18x18/Modify.png"))); // NOI18N
        kButtonEditarTudo.setText("EDITAR TUDO");
        kButtonEditarTudo.setToolTipText("");
        kButtonEditarTudo.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        kButtonEditarTudo.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        kButtonEditarTudo.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        kButtonEditarTudo.setIconTextGap(15);
        kButtonEditarTudo.setkAllowTab(false);
        kButtonEditarTudo.setkBackGroundColor(new java.awt.Color(255, 255, 255));
        kButtonEditarTudo.setkBorderRadius(0);
        kButtonEditarTudo.setkEndColor(new java.awt.Color(255, 255, 255));
        kButtonEditarTudo.setkForeGround(new java.awt.Color(51, 51, 51));
        kButtonEditarTudo.setkHoverEndColor(new java.awt.Color(234, 239, 243));
        kButtonEditarTudo.setkHoverForeGround(new java.awt.Color(51, 51, 51));
        kButtonEditarTudo.setkHoverStartColor(new java.awt.Color(234, 239, 243));
        kButtonEditarTudo.setkIndicatorColor(new java.awt.Color(0, 102, 255));
        kButtonEditarTudo.setkIndicatorThickness(0);
        kButtonEditarTudo.setkPressedColor(new java.awt.Color(234, 239, 243));
        kButtonEditarTudo.setkSelectedColor(new java.awt.Color(234, 239, 243));
        kButtonEditarTudo.setkStartColor(new java.awt.Color(255, 255, 255));
        kButtonEditarTudo.setPreferredSize(new java.awt.Dimension(200, 45));
        kButtonEditarTudo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButtonEditarTudoActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout kGradientPanel14Layout = new org.jdesktop.layout.GroupLayout(kGradientPanel14);
        kGradientPanel14.setLayout(kGradientPanel14Layout);
        kGradientPanel14Layout.setHorizontalGroup(
            kGradientPanel14Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(kGradientPanel14Layout.createSequentialGroup()
                .add(10, 10, 10)
                .add(kButtonProcessos, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 237, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(kButtonAndamento, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 218, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 486, Short.MAX_VALUE)
                .add(kButtonEditarTudo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 159, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(kButtonLimparDados, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 159, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        kGradientPanel14Layout.setVerticalGroup(
            kGradientPanel14Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(kGradientPanel14Layout.createSequentialGroup()
                .add(2, 2, 2)
                .add(kGradientPanel14Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(kButtonProcessos, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 39, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(kButtonAndamento, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 39, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(kButtonLimparDados, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 39, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(kButtonEditarTudo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 39, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void btCancelarServicos1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarServicos1ActionPerformed
        // Fechar tela
        limpaCampos();
        dispose();
        ViewOp.op.requestFocus();

    }//GEN-LAST:event_btCancelarServicos1ActionPerformed

    private void btSalvarOpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarOpActionPerformed
        // TODO add your handling code here:
        if (viewoprocesso.tipoCadastro != null && viewoprocesso.tipoCadastro.equals("alteracao")) {
            alteraOp();
        } else {
            SalvaOp();
        }
    }//GEN-LAST:event_btSalvarOpActionPerformed

    private void jFValorPrejuizoNegativaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFValorPrejuizoNegativaFocusLost
        //Valor Prejuizo (Negativa)     
        try {
            if ("".equals(jFValorPrejuizoNegativa.getText()) || (Float.parseFloat(new Moeda().FommatoStringoSomarMil(jFValorPrejuizoNegativa.getText()))) < 0) {
                jFValorPrejuizoNegativa.setText("R$ 0,00");
            }
            this.jFValorPrejuizoNegativa.setText(new Moeda().valorStringTODoubleAtt(jFValorPrejuizoNegativa.getText()));
        } catch (NumberFormatException e) {
            jFValorPrejuizoNegativa.setText("R$ 0,00");
        }
    }//GEN-LAST:event_jFValorPrejuizoNegativaFocusLost

    private void jFDataEmissaoNFhonorariosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFDataEmissaoNFhonorariosFocusLost
        // DATA EMISSAO
        this.jFDataEmissaoNFhonorarios.setText(jFDataEmissaoNFhonorarios.getText().toUpperCase());
    }//GEN-LAST:event_jFDataEmissaoNFhonorariosFocusLost

    private void jFDataPrevisaoPgtoNFhonorariosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFDataPrevisaoPgtoNFhonorariosFocusLost
        // PREVISAO PAGAMENTO
        this.jFDataPrevisaoPgtoNFhonorarios.setText(jFDataPrevisaoPgtoNFhonorarios.getText().toUpperCase());
    }//GEN-LAST:event_jFDataPrevisaoPgtoNFhonorariosFocusLost

    private void jFDataPgtoNFhonorariosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFDataPgtoNFhonorariosFocusLost
        // DATA DE PAGAMENTO NF DO PROCESSO
        this.jFDataPgtoNFhonorarios.setText(jFDataPgtoNFhonorarios.getText().toUpperCase());

    }//GEN-LAST:event_jFDataPgtoNFhonorariosFocusLost

    private void jFDataEmissaoNegativaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFDataEmissaoNegativaFocusLost
        // DATA DE EMISSÃO NEGATIVA
        this.jFDataEmissaoNegativa.setText(jFDataEmissaoNegativa.getText().toUpperCase());
    }//GEN-LAST:event_jFDataEmissaoNegativaFocusLost

    private void jFDataPrevisaoPgtoNegativaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFDataPrevisaoPgtoNegativaFocusLost
        //PREVISÃO DE PAGAMENTO NEGATIVA
        this.jFDataPrevisaoPgtoNegativa.setText(jFDataPrevisaoPgtoNegativa.getText().toUpperCase());
    }//GEN-LAST:event_jFDataPrevisaoPgtoNegativaFocusLost

    private void tfNumeroNFNegativaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfNumeroNFNegativaFocusLost
        // NUMERO DA NOTA FISCAL NEGATIVA
        this.tfNumeroNFNegativa.setText(tfNumeroNFNegativa.getText().toUpperCase());
    }//GEN-LAST:event_tfNumeroNFNegativaFocusLost
    private void retornarDadosSeguradoras() {
        modelSeguradora = controllerSeguradora.getSeguradoraController(Integer.parseInt(cbCodSeguradora.getSelectedItem().toString()));
        //recupera o nome
        this.cbSeguradoras.setSelectedItem(String.valueOf(modelSeguradora.getNome()));
        this.jfCNPJ.setText(modelSeguradora.getCnpj());

    }
    private void jFValorNegativaTotalFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFValorNegativaTotalFocusLost
        // VALOR NETATIVA TOTAL
        try {
            if ("".equals(jFValorNegativaTotal.getText()) || (Double.parseDouble(new Moeda().FommatoStringoSomarMil(jFValorNegativaTotal.getText()))) < 0) {
                jFValorNegativaTotal.setText("R$ 0,00");
                jFValorNotaRealNegIndevido.setEnabled(false);
                jFValorNotaRealNegIndevido.setText("R$ 0,00");
                jFRetidoRealNegativa.setText("R$ 0,00");
                tfPercentuallRetidoRealNeg.setText("0,00%");
                jFValorNotaRealNegIndevido.setForeground(Color.WHITE);
                jFValorNotaRealNegIndevido.setBackground(Color.GRAY);
                tfPercentuallRetidoRealNeg.setForeground(Color.WHITE);
                tfPercentuallRetidoRealNeg.setBackground(Color.GRAY);
                jLRetidoRealNegativa.setForeground(Color.DARK_GRAY);
                jLAlicotalRealNegativa.setForeground(Color.DARK_GRAY);
                tfObsRetencaoISSNeg.setText("");
                tfObsRetencaoISSNeg.setEnabled(false);
                modelSeguradora = controllerSeguradora.getSeguradoraController(cbSeguradoras.getSelectedItem().toString());
                if ("Sim".equals(modelSeguradora.getSeg_negativa()) || "Sim".equals(modelSeguradora.getSeg_iss())) {
                    retencaoNegativa();
                }
                if (this.jRCheckIndevidoISSNegativa.isSelected()) {
                    retencaoNegativaIndevido();
                }
            } else {
                this.jFValorNegativaTotal.setText(new Moeda().valorStringTODoubleAtt(jFValorNegativaTotal.getText()));
                modelSeguradora = controllerSeguradora.getSeguradoraController(cbSeguradoras.getSelectedItem().toString());
                if ("Sim".equals(modelSeguradora.getSeg_negativa()) || "Sim".equals(modelSeguradora.getSeg_iss())) {
                    retencaoNegativa();
                }
                if (this.jRCheckIndevidoISSNegativa.isSelected()) {
                    retencaoNegativaIndevido();
                }
            }
        } catch (NumberFormatException e) {
            this.jFValorNegativaTotal.setText("R$ 0,00");
            modelSeguradora = controllerSeguradora.getSeguradoraController(cbSeguradoras.getSelectedItem().toString());
            if ("Sim".equals(modelSeguradora.getSeg_negativa()) || "Sim".equals(modelSeguradora.getSeg_iss())) {
                retencaoNegativa();
            }
            if (this.jRCheckIndevidoISSNegativa.isSelected()) {
                retencaoNegativaIndevido();
            }
            jFValorNotaRealNegIndevido.setEnabled(false);
            jFValorNotaRealNegIndevido.setText("R$ 0,00");
            jFRetidoRealNegativa.setText("R$ 0,00");
            tfPercentuallRetidoRealNeg.setText("0,00%");
            jFValorNotaRealNegIndevido.setForeground(Color.WHITE);
            jFValorNotaRealNegIndevido.setBackground(Color.GRAY);
            tfPercentuallRetidoRealNeg.setForeground(Color.WHITE);
            tfPercentuallRetidoRealNeg.setBackground(Color.GRAY);
            jLRetidoRealNegativa.setForeground(Color.DARK_GRAY);
            jLAlicotalRealNegativa.setForeground(Color.DARK_GRAY);
            tfObsRetencaoISSNeg.setText("");
            tfObsRetencaoISSNeg.setEnabled(false);
        }
    }//GEN-LAST:event_jFValorNegativaTotalFocusLost

    private void jFValorPrejuizoNegativaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFValorPrejuizoNegativaFocusGained
        // LIMPAR AO CLICAR QUANDO ESTIVER ZERADO
        if ("R$ 0,00".equals(jFValorPrejuizoNegativa.getText())) {
            jFValorPrejuizoNegativa.setText("");
        }
    }//GEN-LAST:event_jFValorPrejuizoNegativaFocusGained

    private void jFValorNegativaTotalFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFValorNegativaTotalFocusGained
        // LIMPAR AO CLICAR QUANDO ESTIVER ZERADO
        if ("R$ 0,00".equals(jFValorNegativaTotal.getText())) {
            jFValorNegativaTotal.setText("");
        }
    }//GEN-LAST:event_jFValorNegativaTotalFocusGained

    private void testeverificaseg() {
        modelSeguradora = controllerSeguradora.getSeguradoraController(cbSeguradoras.getSelectedItem().toString());
        //recupera o código e seta CNPJ
        this.cbCodSeguradora.setSelectedItem(modelSeguradora.getCodigo());
        this.jfCNPJ.setText(modelSeguradora.getCnpj());
        this.jfCNPJ.setForeground(Color.white);
        this.jfCNPJ.setForeground(Color.getHSBColor(51, 51, 51));

    }
    private void jFHonorarioDefinidoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFHonorarioDefinidoFocusLost
        //HONORARIO DEFINIDO
        if ("".equals(jFHonorarioDefinido.getText())) {
            jFHonorarioDefinido.setText("R$ 0,00");
        }
        try {
            if (!("".equals(jFHonorarioDefinido.getText())) || (Float.parseFloat(new Moeda().FommatoStringoSomarMil(jFHonorarioDefinido.getText()))) > 0) {
                this.jFHonorarioDefinido.setText(new Moeda().valorStringTODoubleAtt(jFHonorarioDefinido.getText()));

                modelSeguradora = controllerSeguradora.getSeguradoraController(cbSeguradoras.getSelectedItem().toString());
                if ("Sim".equals(modelSeguradora.getSeg_iss())) {
                    retencaoProcesso();
                }
                if ("Sim".equals(modelSeguradora.getSeg_iss()) || jRCheckIndevidoISS.isSelected() || !"".equals(jFValorNotaRealIndevido.getText()) || !"R$ 0,00".equals(jFValorNotaRealIndevido.getText())) {
                    retencaoProcessoIndevido();
                }
            }
        } catch (NumberFormatException e) {
            System.out.println((new Moeda().valorStringTODoubleAtt(jFHonorarioDefinido.getText())));
            modelSeguradora = controllerSeguradora.getSeguradoraController(cbSeguradoras.getSelectedItem().toString());
            jFHonorarioDefinido.setText("R$ 0,00");

            if ("Sim".equals(modelSeguradora.getSeg_iss())) {
                retencaoProcesso();
            }
            if ("Sim".equals(modelSeguradora.getSeg_iss()) || jRCheckIndevidoISS.isSelected() || !"".equals(jFValorNotaRealIndevido.getText()) || !"R$ 0,00".equals(jFValorNotaRealIndevido.getText())) {
                retencaoProcessoIndevido();
            }
        }
    }//GEN-LAST:event_jFHonorarioDefinidoFocusLost

    private void jFHonorarioDefinidoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFHonorarioDefinidoFocusGained
        // TODO add your handling code here:
        if ("R$ 0,00".equals(jFHonorarioDefinido.getText())) {
            jFHonorarioDefinido.setText("");
        }
    }//GEN-LAST:event_jFHonorarioDefinidoFocusGained

    private void jRCheckIndevidoISSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRCheckIndevidoISSActionPerformed
        // CHECK RETENÇÃO INDEVIDA
        CheckIndevidoISS();
    }//GEN-LAST:event_jRCheckIndevidoISSActionPerformed

    private void CheckIndevidoISS() {
        if (jRCheckIndevidoISS.isSelected()) {
            jFValorNotaRealIndevido.setEnabled(true);
            ObsRetencaoAlicotaIndevido.setEnabled(true);
        } else {
            //JOptionPane.showMessageDialog(this, "Campos do pagamento indevido foram limpos!", "ATENÇÃO", JOptionPane.OK_CANCEL_OPTION);
            jFValorNotaRealIndevido.setEnabled(false);
            jFValorNotaRealIndevido.setText("R$ 0,00");
            jFRetidoReal.setText("R$ 0,00");
            tfPercentualRetidoReal.setText("0,00%");
            jFRetidoReal.setForeground(Color.WHITE);
            jFRetidoReal.setBackground(Color.GRAY);
            tfPercentualRetidoReal.setForeground(Color.WHITE);
            tfPercentualRetidoReal.setBackground(Color.GRAY);
            jLRetidoReal.setForeground(Color.DARK_GRAY);
            jLAlicotaProcessoReal.setForeground(Color.DARK_GRAY);
            ObsRetencaoAlicotaIndevido.setText("");
            ObsRetencaoAlicotaIndevido.setEnabled(false);
        }
    }

    private void jRCheckIndevidoISSNegativaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRCheckIndevidoISSNegativaActionPerformed
        //ISS indevido Negativa
        CheckIndevidoISSNegativa();
    }//GEN-LAST:event_jRCheckIndevidoISSNegativaActionPerformed

    private void CheckIndevidoISSNegativa() {
        if (jRCheckIndevidoISSNegativa.isSelected()) {
            jFValorNotaRealNegIndevido.setEnabled(true);
            tfObsRetencaoISSNeg.setEnabled(true);
        } else {
            //JOptionPane.showMessageDialog(this, "Campos do pagamento indevido foram limpos!", "ATENÇÃO", JOptionPane.OK_CANCEL_OPTION);
            jFValorNotaRealNegIndevido.setEnabled(false);
            jFValorNotaRealNegIndevido.setText("R$ 0,00");
            jFRetidoRealNegativa.setText("R$ 0,00");
            tfPercentuallRetidoRealNeg.setText("0,00%");
            jFValorNotaRealNegIndevido.setForeground(Color.WHITE);
            jFValorNotaRealNegIndevido.setBackground(Color.GRAY);
            tfPercentuallRetidoRealNeg.setForeground(Color.WHITE);
            tfPercentuallRetidoRealNeg.setBackground(Color.GRAY);
            jLRetidoRealNegativa.setForeground(Color.DARK_GRAY);
            jLAlicotalRealNegativa.setForeground(Color.DARK_GRAY);
            tfObsRetencaoISSNeg.setText("");
            tfObsRetencaoISSNeg.setEnabled(false);
        }
    }

    private void ObsRetencaoAlicotaIndevidoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ObsRetencaoAlicotaIndevidoFocusLost
        //Observação Retenção
        if ("".equals(ObsRetencaoAlicotaIndevido.getText())) {
            this.ObsRetencaoAlicotaIndevido.setText("Nenhuma observação.");
        }
        this.ObsRetencaoAlicotaIndevido.setText(ObsRetencaoAlicotaIndevido.getText());
    }//GEN-LAST:event_ObsRetencaoAlicotaIndevidoFocusLost

    private void jFValorNotaRealIndevidoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFValorNotaRealIndevidoFocusLost
        //VALOR REAL DA NOTA
        try {
            jFValorNotaRealIndevido.setText(new Moeda().valorStringTODoubleAtt(jFValorNotaRealIndevido.getText()));
            retencaoProcessoIndevido();
        } catch (NumberFormatException e) {
            jFValorNotaRealIndevido.setText("R$ 0,00");
            retencaoProcessoIndevido();
        }
    }//GEN-LAST:event_jFValorNotaRealIndevidoFocusLost

    private void jFValorNotaRealIndevidoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFValorNotaRealIndevidoFocusGained
        //VALOR DA NOTA REAL
        if ("R$ 0,00".equals(jFValorNotaRealIndevido.getText())) {
            this.jFValorNotaRealIndevido.setText("");
        }
    }//GEN-LAST:event_jFValorNotaRealIndevidoFocusGained

    private void jFValorNotaRealNegIndevidoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFValorNotaRealNegIndevidoFocusGained
        // TODO add your handling code here:
        if ("R$ 0,00".equals(jFValorNotaRealNegIndevido.getText())) {
            jFValorNotaRealNegIndevido.setText("");
        }
    }//GEN-LAST:event_jFValorNotaRealNegIndevidoFocusGained

    private void jFValorNotaRealNegIndevidoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFValorNotaRealNegIndevidoFocusLost
        //VALOR DA NOTA REAL DE NEGATIVA - PAGAMENTO INDEVIDO

        try {
            if ("".equals(jFValorNotaRealNegIndevido.getText()) || (Float.parseFloat(new Moeda().FommatoStringoSomarMil(jFValorNotaRealNegIndevido.getText()))) < 0) {
                this.jFValorNotaRealNegIndevido.setText("R$ 0,00");
                modelSeguradora = controllerSeguradora.getSeguradoraController(cbSeguradoras.getSelectedItem().toString());
                if ("Sim".equals(modelSeguradora.getSeg_negativa()) || "Sim".equals(modelSeguradora.getSeg_iss())) {
                    retencaoNegativa();
                }
                if (this.jRCheckIndevidoISSNegativa.isSelected()) {
                    retencaoNegativaIndevido();
                }
            }
            this.jFValorNotaRealNegIndevido.setText(new Moeda().valorStringTODoubleAtt(jFValorNotaRealNegIndevido.getText()));
            if (jRCheckIndevidoISSNegativa.isSelected() || !"".equals(jFValorNotaRealNegIndevido.getText()) || !"R$ 0,00".equals(jFValorNotaRealNegIndevido.getText())) {
                modelSeguradora = controllerSeguradora.getSeguradoraController(cbSeguradoras.getSelectedItem().toString());
                if ("Sim".equals(modelSeguradora.getSeg_negativa()) || "Sim".equals(modelSeguradora.getSeg_iss())) {
                    retencaoNegativa();
                }
                if (this.jRCheckIndevidoISSNegativa.isSelected()) {
                    retencaoNegativaIndevido();
                }
            }
        } catch (NumberFormatException e) {
            this.jFValorNotaRealNegIndevido.setText("R$ 0,00");
            modelSeguradora = controllerSeguradora.getSeguradoraController(cbSeguradoras.getSelectedItem().toString());
            if ("Sim".equals(modelSeguradora.getSeg_negativa()) || "Sim".equals(modelSeguradora.getSeg_iss())) {
                retencaoNegativa();
            }
            if (this.jRCheckIndevidoISSNegativa.isSelected()) {
                retencaoNegativaIndevido();
            }
        }
    }//GEN-LAST:event_jFValorNotaRealNegIndevidoFocusLost

    private void tfObsRetencaoISSNegFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfObsRetencaoISSNegFocusLost
        // OBS RETENÇÃO NEGATIVA
        if ("".equals(tfObsRetencaoISSNeg.getText())) {
            this.tfObsRetencaoISSNeg.setText("Nenhuma observação.");
        }
        this.tfObsRetencaoISSNeg.setText(tfObsRetencaoISSNeg.getText());
    }//GEN-LAST:event_tfObsRetencaoISSNegFocusLost

    private void tfNumeroNFProcessoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfNumeroNFProcessoFocusLost
        //NUMERO NF PROCESSO HONORARIOS
        this.tfNumeroNFProcesso.setText(tfNumeroNFProcesso.getText().toUpperCase());
    }//GEN-LAST:event_tfNumeroNFProcessoFocusLost

    private void jFDataPgtoNegativaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFDataPgtoNegativaFocusLost
        //DATA DE PAGAMENTO NEGATIVA
        this.jFDataPgtoNegativa.setText(jFDataPgtoNegativa.getText());
    }//GEN-LAST:event_jFDataPgtoNegativaFocusLost

    private void tfObsHonorariosProcessoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfObsHonorariosProcessoFocusLost
        // TODO add your handling code here:
        if ("".equals(tfObsHonorariosProcesso.getText())) {
            this.tfObsHonorariosProcesso.setText("Nenhuma observação");
        }
        this.tfObsHonorariosProcesso.setText(tfObsHonorariosProcesso.getText());
    }//GEN-LAST:event_tfObsHonorariosProcessoFocusLost

    private void jTPanelProcessosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTPanelProcessosKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTPanelProcessosKeyPressed

    private void jTPanelProcessosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTPanelProcessosMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTPanelProcessosMouseClicked

    private void jTPanelProcessosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTPanelProcessosKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTPanelProcessosKeyTyped

    private void jButtonRemoveTerceiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoveTerceiroActionPerformed
        // TODO add your handling code here:
        int linhaSelecionada = tbServicos.getSelectedRow();
        // Verificamos se existe realmente alguma linha selecionada
        if (linhaSelecionada < 0) {
            JOptionPane.showMessageDialog(this, "Você deve selecionar um item na tabela antes de clicar no botão!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
        } else {
            // Obtem o modelo da JTable
            DefaultTableModel modelo = (DefaultTableModel) tbServicos.getModel();
            // Remove a linha
            modelo.removeRow(linhaSelecionada);

        }
    }//GEN-LAST:event_jButtonRemoveTerceiroActionPerformed

    private void jButtonAddTerceiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddTerceiroActionPerformed
        // TODO add your handling code here:
        incluirTerceiro();
    }//GEN-LAST:event_jButtonAddTerceiroActionPerformed

    private void tfTerceiroNomeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfTerceiroNomeFocusLost
        // TODO add your handling code here:
        this.tfTerceiroNome.setText(tfTerceiroNome.getText().toUpperCase());
    }//GEN-LAST:event_tfTerceiroNomeFocusLost

    private void tfTerceiroObsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfTerceiroObsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfTerceiroObsActionPerformed

    private void tfTerceiroPlacaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfTerceiroPlacaFocusLost
        // TODO add your handling code here:
        this.tfTerceiroPlaca.setText(tfTerceiroPlaca.getText().toUpperCase());
    }//GEN-LAST:event_tfTerceiroPlacaFocusLost

    private void tfNomeSeguradoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfNomeSeguradoFocusLost
        // TODO add your handling code here:
        this.tfNomeSegurado.setText(tfNomeSegurado.getText().toUpperCase());
    }//GEN-LAST:event_tfNomeSeguradoFocusLost

    private void tfObsSeguradoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfObsSeguradoFocusLost
        // TODO add your handling code here:
        if ("".equals(tfObsSegurado.getText())) {
            this.tfObsSegurado.setText("Nenhuma observação");
        }
        this.tfObsSegurado.setText(tfObsSegurado.getText());
    }//GEN-LAST:event_tfObsSeguradoFocusLost

    private void tfSeguradoPlacaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfSeguradoPlacaFocusLost
        // SeguradoPlaca
        this.tfSeguradoPlaca.setText(tfSeguradoPlaca.getText().toUpperCase());
    }//GEN-LAST:event_tfSeguradoPlacaFocusLost

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

    private void btCidadeAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCidadeAddActionPerformed
        // TODO add your handling code here:
        cidade.setVisible(true);
    }//GEN-LAST:event_btCidadeAddActionPerformed

    private void jFHoraSinistroFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFHoraSinistroFocusLost
        // HORA DO SINISTRO
        if ("".equals(jFHoraSinistro.getText())) {
            this.jFHoraSinistro.setText("00:00");
        }
        this.jFHoraSinistro.setText(jFHoraSinistro.getText().toUpperCase());
    }//GEN-LAST:event_jFHoraSinistroFocusLost

    private void jTObsSinistroFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTObsSinistroFocusLost
        // TODO add your handling code here:
        if ("".equals(jTObsSinistro.getText())) {
            this.jTObsSinistro.setText("Nenhuma observação");
        }
        this.jTObsSinistro.setText(jTObsSinistro.getText());
    }//GEN-LAST:event_jTObsSinistroFocusLost

    private void tfNumSinistroFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfNumSinistroFocusLost
        // Numero do SINISTRO
        this.tfNumSinistro.setText(tfNumSinistro.getText().toUpperCase());
    }//GEN-LAST:event_tfNumSinistroFocusLost

    private void tfAnalistaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfAnalistaFocusLost
        // Analista
        this.tfAnalista.setText(tfAnalista.getText().toUpperCase());
    }//GEN-LAST:event_tfAnalistaFocusLost

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

    private void tfBairroFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfBairroFocusLost
        //Bairro
        this.tfBairro.setText(tfBairro.getText().toUpperCase());
    }//GEN-LAST:event_tfBairroFocusLost

    private void jFCtsFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFCtsFocusLost
        // TODO add your handling code here:
        try {
            if ("".equals(jFCts.getText())) {
                jFCts.setText("0");
            } else {
                this.jFCts.setText(jFCts.getText());
            }
        } catch (NumberFormatException e) {
            this.jFCts.setText("0");
        }

    }//GEN-LAST:event_jFCtsFocusLost

    private void jFCtsFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFCtsFocusGained
        // TODO add your handling code here:
        if ("0".equals(jFCts.getText())) {
            jFCts.setText("");
        }
    }//GEN-LAST:event_jFCtsFocusGained

    private void jFDataSaidaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFDataSaidaFocusLost
        // DATA DE SAIDA DO PROCESSO
        this.jFDataSaida.setText(jFDataSaida.getText().toUpperCase());
    }//GEN-LAST:event_jFDataSaidaFocusLost

    private void jFDataEntradaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFDataEntradaFocusLost
        // DATA ENTRADA DO PROCESSO
        if ("".equals(this.jFDataEntrada.getText())) {
            this.jFDataEntrada.setText(dataAtual());
        }
        this.jFDataEntrada.setText(jFDataEntrada.getText().toUpperCase());
    }//GEN-LAST:event_jFDataEntradaFocusLost

    private void cbSeguradorasPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbSeguradorasPopupMenuWillBecomeVisible
        // TODO add your handling code here:
        //ATUALIZA VALOR HONORARIO CASO FIXO APENAS

    }//GEN-LAST:event_cbSeguradorasPopupMenuWillBecomeVisible

    private void cbSeguradorasPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbSeguradorasPopupMenuWillBecomeInvisible
        // ESCOLHER SEGURADORA
        modelSeguradora = controllerSeguradora.getSeguradoraController(cbSeguradoras.getSelectedItem().toString());
        this.jFDataEntrada.setText(dataAtual());
        //recupera o código e seta CNPJ
        this.cbCodSeguradora.setSelectedItem(modelSeguradora.getCodigo());
        aplicarCidadeTomador();
        this.retencaoEnegativaPorSeguradoras();
        this.jfCNPJ.setText(modelSeguradora.getCnpj());
        this.jfCNPJ.setForeground(Color.white);
        this.jfCNPJ.setForeground(Color.getHSBColor(51, 51, 51));

    }//GEN-LAST:event_cbSeguradorasPopupMenuWillBecomeInvisible

    private void cbSeguradorasPopupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbSeguradorasPopupMenuCanceled
        // TODO add your handling code here:
        modelSeguradora = controllerSeguradora.getSeguradoraController(cbSeguradoras.getSelectedItem().toString());
        vefiricarValorHonorario();
        if ("Sim".equals(modelSeguradora.getSeg_iss())) {
            retencaoProcesso();
        }
        if ("Sim".equals(modelSeguradora.getSeg_iss()) || jRCheckIndevidoISS.isSelected() || !"".equals(jFValorNotaRealIndevido.getText()) || !"R$ 0,00".equals(jFValorNotaRealIndevido.getText())) {
            retencaoProcessoIndevido();
        }
    }//GEN-LAST:event_cbSeguradorasPopupMenuCanceled

    private void tfCodigoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfCodigoFocusLost
        // APENAS RECEBE O CODIGO
        //  this.tfCodigo.getText().toUpperCase();
    }//GEN-LAST:event_tfCodigoFocusLost

    private void cbCodSeguradoraPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbCodSeguradoraPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
        if (this.cbCodSeguradora.isPopupVisible()) {
            this.retornarSeguradoraPeloCodigo();
        }
    }//GEN-LAST:event_cbCodSeguradoraPopupMenuWillBecomeInvisible

    private void tfPlacaTerceiroAlteracaoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfPlacaTerceiroAlteracaoFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_tfPlacaTerceiroAlteracaoFocusLost

    private void tfTerceiroObsAlteracaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfTerceiroObsAlteracaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfTerceiroObsAlteracaoActionPerformed

    private void tfNomeTerceiroAlterarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfNomeTerceiroAlterarFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNomeTerceiroAlterarFocusLost

    private void btSalvarTerceiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarTerceiroActionPerformed
        // TODO add your handling code here:
        if ("".equals(tfTerceiroObsAlteracao.getText()) || tfTerceiroObsAlteracao.getText() == null) {
            tfTerceiroObsAlteracao.setText("Nenhuma observação.");
        }
        String insertar = "INSERT INTO terceiros_processos(cod_ordem_servico,nome,placa,obs) VALUES (?,?,?,?)";
        try {
            PreparedStatement pst = cn.prepareStatement(insertar);

            pst.setString(1, tfCodigo.getText());
            pst.setString(2, tfNomeTerceiroAlterar.getText());
            pst.setString(3, tfPlacaTerceiroAlteracao.getText());
            pst.setString(4, tfTerceiroObsAlteracao.getText());

            int i = pst.executeUpdate();
            if (i > 0) {
                JOptionPane.showMessageDialog(null, "Salvo com Sucesso!");
                tfNomeTerceiroAlterar.setText(null);
                tfPlacaTerceiroAlteracao.setText(null);
                tfTerceiroObsAlteracao.setText(null);
                tfCodTerceiro.setText("0");
            }
            carregarTerceirosAlteracao();
        } catch (SQLException ex) {
            Logger.getLogger(ViewCadOp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btSalvarTerceiroActionPerformed

    private void btAtualizarTerceiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAtualizarTerceiroActionPerformed
        // TODO add your handling code here:
        try {

            int cod = (Integer.parseInt(tfCodTerceiro.getText()));

            String sql = "UPDATE terceiros_processos SET nome = ?, "
                    + "placa = ?,"
                    + "obs = ?"
                    + "WHERE codigo = '" + cod + "'";

            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, tfNomeTerceiroAlterar.getText());
            pst.setString(2, tfPlacaTerceiroAlteracao.getText());
            pst.setString(3, tfTerceiroObsAlteracao.getText());
            int n = pst.executeUpdate();

            if (n > 0) {
                JOptionPane.showMessageDialog(null, "Os dados foram atualizados com sucesso!");
                tfCodTerceiro.setText(null);
                // TODO add your handling code here:
                btSalvarTerceiro.setVisible(true);
                btSalvarTerceiro.setEnabled(true);
                tfNomeTerceiroAlterar.setText("");
                tfPlacaTerceiroAlteracao.setText("");
                tfTerceiroObsAlteracao.setText("");
                carregarTerceirosAlteracao();
            } else {
                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao atualizar os dados");
                carregarTerceirosAlteracao();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ViewCadOp.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btAtualizarTerceiroActionPerformed

    private void btAlterarTerceiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAlterarTerceiroActionPerformed
        // TODO add your handling code here:

        int fila = tbTerceiros.getSelectedRow();
        if (fila >= 0) {
            btSalvarTerceiro.setEnabled(false);
            btAtualizarTerceiro.setEnabled(true);
            String cod = tbTerceiros.getValueAt(fila, 0).toString();
            String nome = tbTerceiros.getValueAt(fila, 1).toString();
            String placa = tbTerceiros.getValueAt(fila, 2).toString();
            String obs = tbTerceiros.getValueAt(fila, 3).toString();
            tfCodTerceiro.setText(cod);
            tfNomeTerceiroAlterar.setText(nome);
            tfPlacaTerceiroAlteracao.setText(placa);
            tfTerceiroObsAlteracao.setText(obs);
            btAlterarTerceiro.setEnabled(false);
            btAlterarTerceiro.setVisible(false);
            btCancelar.setVisible(true);
            btCancelar.setEnabled(true);
            carregarTerceirosAlteracao();
        } else {
            JOptionPane.showMessageDialog(null, "Escolha uma linha na tabela antes!");
        }

    }//GEN-LAST:event_btAlterarTerceiroActionPerformed

    private void btExcluirTerceiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirTerceiroActionPerformed
        // TODO add your handling code here:
        int fila = tbTerceiros.getSelectedRow();
        if (fila > -1) {
            String cod = tbTerceiros.getValueAt(fila, 0).toString();
            String sqlElim = "DELETE FROM terceiros_processos WHERE codigo ='" + cod + "'";
            try {
                PreparedStatement pst = cn.prepareStatement(sqlElim);
                int n = pst.executeUpdate();
                if (n > 0) {
                    JOptionPane.showMessageDialog(null, "Os dados foram atualizados com sucesso!");
                    carregarTerceirosAlteracao();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocorreu um erro ao atualizar os dados");
                    carregarTerceirosAlteracao();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ViewCadOp.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_btExcluirTerceiroActionPerformed

    private void cbUfPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbUfPopupMenuWillBecomeVisible
        // TODO add your handling code here:
        this.listarEstados();
    }//GEN-LAST:event_cbUfPopupMenuWillBecomeVisible

    private void cbCidadePopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbCidadePopupMenuWillBecomeVisible
        // TODO add your handling code here:
        this.listarCidades();
    }//GEN-LAST:event_cbCidadePopupMenuWillBecomeVisible

    private void jFValorDespesasTotalRegistroFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFValorDespesasTotalRegistroFocusGained
        // TODO add your handling code here:
        if ("R$ 0,00".equals(jFValorDespesasTotalRegistro.getText())) {
            jFValorDespesasTotalRegistro.setText("");
        }
    }//GEN-LAST:event_jFValorDespesasTotalRegistroFocusGained

    private void jFValorDespesasTotalRegistroFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFValorDespesasTotalRegistroFocusLost
        // TODO add your handling code here:

        if ("".equals(jFValorDespesasTotalRegistro.getText())) {
            jFValorDespesasTotalRegistro.setText("R$ 0,00");
        }
        try {
            if (!("".equals(jFValorDespesasTotalRegistro.getText())) || (Float.parseFloat(new Moeda().FommatoStringoSomarMil(jFValorDespesasTotalRegistro.getText()))) > 0) {
                this.jFValorDespesasTotalRegistro.setText(new Moeda().valorStringTODoubleAtt(jFValorDespesasTotalRegistro.getText()));

                modelSeguradora = controllerSeguradora.getSeguradoraController(cbSeguradoras.getSelectedItem().toString());
                if ("Sim".equals(modelSeguradora.getSeg_iss())) {
                    retencaoProcesso();
                }
                if ("Sim".equals(modelSeguradora.getSeg_iss()) || jRCheckIndevidoISS.isSelected() || !"".equals(jFValorNotaRealIndevido.getText()) || !"R$ 0,00".equals(jFValorNotaRealIndevido.getText())) {
                    retencaoProcessoIndevido();
                }
            }
        } catch (NumberFormatException e) {
            modelSeguradora = controllerSeguradora.getSeguradoraController(cbSeguradoras.getSelectedItem().toString());
            jFValorDespesasTotalRegistro.setText("R$ 0,00");

            if ("Sim".equals(modelSeguradora.getSeg_iss())) {
                retencaoProcesso();
            }
            if ("Sim".equals(modelSeguradora.getSeg_iss()) || jRCheckIndevidoISS.isSelected() || !"".equals(jFValorNotaRealIndevido.getText()) || !"R$ 0,00".equals(jFValorNotaRealIndevido.getText())) {
                retencaoProcessoIndevido();
            }
        }
    }//GEN-LAST:event_jFValorDespesasTotalRegistroFocusLost

    private void kButtonProcessosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButtonProcessosActionPerformed
        // TODO add your handling code here:
        jTPanelProcessos.setSelectedIndex(0);
        panelTabbedKGVerficiar();
    }//GEN-LAST:event_kButtonProcessosActionPerformed

    private void kButtonAndamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButtonAndamentoActionPerformed
        // TODO add your handling code here:
        aplicarCidadeTomador();
        this.retencaoEnegativaPorSeguradoras();
        jTPanelProcessos.setSelectedIndex(1);
        panelTabbedKGVerficiar();
    }//GEN-LAST:event_kButtonAndamentoActionPerformed

    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarActionPerformed
        // TODO add your handling code here:
        cancelar();
        carregarTerceirosAlteracao();
    }//GEN-LAST:event_btCancelarActionPerformed

    private void kButtonLimparDadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButtonLimparDadosActionPerformed
        // TODO add your handling code here:
        limpaCampos();
        cbServico.setEnabled(true);
    }//GEN-LAST:event_kButtonLimparDadosActionPerformed

    private void kButtonEditarTudoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButtonEditarTudoActionPerformed
        // TODO add your handling code here:
        cbSeguradoras.setEnabled(true);
        cbServico.setEnabled(true);
    }//GEN-LAST:event_kButtonEditarTudoActionPerformed

    private void cbUfFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cbUfFocusLost
        // TODO add your handling code here:
        listarCidades();
    }//GEN-LAST:event_cbUfFocusLost

    private void cbAlicotaPercentualHonorariosPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbAlicotaPercentualHonorariosPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
        retencaoProcesso();
        retencaoProcessoIndevido();
        jFValorHonorariosRetido.requestFocus();
    }//GEN-LAST:event_cbAlicotaPercentualHonorariosPopupMenuWillBecomeInvisible

    private void cbAlicotaPercentualHonorariosPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbAlicotaPercentualHonorariosPopupMenuWillBecomeVisible
        // TODO add your handling code here:
        retencaoProcesso();
        retencaoProcessoIndevido();
    }//GEN-LAST:event_cbAlicotaPercentualHonorariosPopupMenuWillBecomeVisible

    private void cbAlicotaPercentualHonorariosPopupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbAlicotaPercentualHonorariosPopupMenuCanceled
        // TODO add your handling code here:
        retencaoProcesso();
        retencaoProcessoIndevido();
        jFValorHonorariosRetido.requestFocus();
    }//GEN-LAST:event_cbAlicotaPercentualHonorariosPopupMenuCanceled

    private void jFValorHonorariosRetidoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFValorHonorariosRetidoFocusGained
        // TODO add your handling code here:
        retencaoProcesso();
        retencaoProcessoIndevido();
    }//GEN-LAST:event_jFValorHonorariosRetidoFocusGained

    private void jFValorHonorariosRetidoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jFValorHonorariosRetidoMouseClicked
        // TODO add your handling code here:
        retencaoProcesso();
        retencaoProcessoIndevido();
    }//GEN-LAST:event_jFValorHonorariosRetidoMouseClicked

    private void cbAlicotaNegPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbAlicotaNegPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
        try {
            if ("".equals(jFValorNegativaTotal.getText()) || (Double.parseDouble(new Moeda().FommatoStringoSomarMil(jFValorNegativaTotal.getText()))) < 0) {
                jFValorNegativaTotal.setText("R$ 0,00");
                jFValorNotaRealNegIndevido.setEnabled(false);
                jFValorNotaRealNegIndevido.setText("R$ 0,00");
                jFRetidoRealNegativa.setText("R$ 0,00");
                tfPercentuallRetidoRealNeg.setText("0,00%");
                jFValorNotaRealNegIndevido.setForeground(Color.WHITE);
                jFValorNotaRealNegIndevido.setBackground(Color.GRAY);
                tfPercentuallRetidoRealNeg.setForeground(Color.WHITE);
                tfPercentuallRetidoRealNeg.setBackground(Color.GRAY);
                jLRetidoRealNegativa.setForeground(Color.DARK_GRAY);
                jLAlicotalRealNegativa.setForeground(Color.DARK_GRAY);
                tfObsRetencaoISSNeg.setText("");
                tfObsRetencaoISSNeg.setEnabled(false);
                modelSeguradora = controllerSeguradora.getSeguradoraController(cbSeguradoras.getSelectedItem().toString());
                if ("Sim".equals(modelSeguradora.getSeg_negativa()) || "Sim".equals(modelSeguradora.getSeg_iss())) {
                    retencaoNegativa();
                }
                if (this.jRCheckIndevidoISSNegativa.isSelected()) {
                    retencaoNegativaIndevido();
                }
            } else {
                this.jFValorNegativaTotal.setText(new Moeda().valorStringTODoubleAtt(jFValorNegativaTotal.getText()));
                modelSeguradora = controllerSeguradora.getSeguradoraController(cbSeguradoras.getSelectedItem().toString());
                if ("Sim".equals(modelSeguradora.getSeg_negativa()) || "Sim".equals(modelSeguradora.getSeg_iss())) {
                    retencaoNegativa();
                }
                if (this.jRCheckIndevidoISSNegativa.isSelected()) {
                    retencaoNegativaIndevido();
                }
            }
        } catch (NumberFormatException e) {
            this.jFValorNegativaTotal.setText("R$ 0,00");
            modelSeguradora = controllerSeguradora.getSeguradoraController(cbSeguradoras.getSelectedItem().toString());
            if ("Sim".equals(modelSeguradora.getSeg_negativa()) || "Sim".equals(modelSeguradora.getSeg_iss())) {
                retencaoNegativa();
            }
            if (this.jRCheckIndevidoISSNegativa.isSelected()) {
                retencaoNegativaIndevido();
            }
            jFValorNotaRealNegIndevido.setEnabled(false);
            jFValorNotaRealNegIndevido.setText("R$ 0,00");
            jFRetidoRealNegativa.setText("R$ 0,00");
            tfPercentuallRetidoRealNeg.setText("0,00%");
            jFValorNotaRealNegIndevido.setForeground(Color.WHITE);
            jFValorNotaRealNegIndevido.setBackground(Color.GRAY);
            tfPercentuallRetidoRealNeg.setForeground(Color.WHITE);
            tfPercentuallRetidoRealNeg.setBackground(Color.GRAY);
            jLRetidoRealNegativa.setForeground(Color.DARK_GRAY);
            jLAlicotalRealNegativa.setForeground(Color.DARK_GRAY);
            tfObsRetencaoISSNeg.setText("");
            tfObsRetencaoISSNeg.setEnabled(false);
        }
    }//GEN-LAST:event_cbAlicotaNegPopupMenuWillBecomeInvisible

    private void cbAlicotaNegPopupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbAlicotaNegPopupMenuCanceled
        // TODO add your handling code here:
        retencaoNegativa();
        retencaoNegativaIndevido();
    }//GEN-LAST:event_cbAlicotaNegPopupMenuCanceled

    private void jFValorRetidoRetencaoNegMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jFValorRetidoRetencaoNegMouseClicked
        // TODO add your handling code here:
        retencaoNegativa();
        retencaoNegativaIndevido();
    }//GEN-LAST:event_jFValorRetidoRetencaoNegMouseClicked

    private void jFValorRetidoRetencaoNegFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFValorRetidoRetencaoNegFocusGained
        // TODO add your handling code here:
        retencaoNegativa();
        retencaoNegativaIndevido();
    }//GEN-LAST:event_jFValorRetidoRetencaoNegFocusGained

    private void cbServicoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cbServicoFocusLost
        // TODO add your handling code here:
        if (this.cbServico.isPopupVisible()) {
            modelServicos = controllerServicos.getServicosController(cbServico.getSelectedItem().toString());
            //recupera o nome
            this.cbServico.setSelectedItem(cbServico.getSelectedItem());
            this.cbCodServico.setSelectedItem(String.valueOf(modelServicos.getCodigo()));
        }
    }//GEN-LAST:event_cbServicoFocusLost
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

    private void incluirTerceiro() {
        // TODO add your handling code here:
        if ("".equals(tfTerceiroNome.getText())) {
            JOptionPane.showMessageDialog(this, "O campo Nome é Obrigatório!", "ATENÇÃO", JOptionPane.ERROR_MESSAGE);
            tfTerceiroNome.setText("");
        }
        if ("".equals(tfTerceiroPlaca.getText())) {
            JOptionPane.showMessageDialog(this, "O campo Placa é Obrigatório!", "ATENÇÃO", JOptionPane.ERROR_MESSAGE);
            tfTerceiroPlaca.setText("");
        } else {
            if ("".equals(tfTerceiroObs.getText())) {
                tfTerceiroObs.setText("Nenhuma observação.");
            }
            DefaultTableModel modelo = (DefaultTableModel) tbServicos.getModel();

            int codigoservico = Integer.parseInt(this.cbCodServico.getSelectedItem().toString());
            //pega a quantidade de linhas e joga na variavel
            modelo.addRow(new Object[]{
                codigoservico,
                modelServicos.getNome(),
                this.tfTerceiroNome.getText(),
                this.tfTerceiroPlaca.getText(),
                this.tfTerceiroObs.getText()

            });
            //LIMPA CAMPOS
            this.tfTerceiroNome.setText("");
            this.tfTerceiroPlaca.setText("");
            this.tfTerceiroObs.setText("");
        }

    }

    //usa apenas para carregar op dados da interface quando abre o software
    private void retornarSegueradoraPeloCodigo2(ModelOp model) {
        modelSeguradora = controllerSeguradora.getSeguradoraController(Integer.parseInt(cbCodSeguradora.getSelectedItem().toString()));
        //recupera 
        this.cbSeguradoras.setSelectedItem(String.valueOf(modelSeguradora.getNome()));
        jfCNPJ.setText(modelSeguradora.getCnpj());
    }

    private boolean SalvaOp() {
        if (tfNumSinistro.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Você deve preencher todos os campos!", "ATENÇÂO", JOptionPane.WARNING_MESSAGE);
            return false;
        } else {
            listaModelOp = new ArrayList<>();
            listaModelOp = new ArrayList<>();
            int codigoServico;
            Double zerar = 0.00;
            int zero = 0;
            Datas bl = new Datas();
            if (tbServicos.getRowCount() > 0) {
                for (int i = 0; i < tbServicos.getRowCount(); i++) {
                    modelOp = new ModelOp();
                    modelServicos = new ModelServicos();
                    modelOp.setSeguradoras((controllerSeguradora.getSeguradoraController(cbSeguradoras.getSelectedItem().toString()).getCodigo()));
                    modelOp.setObsSegurado(tfObsSegurado.getText());
                    modelOp.setTipo(1);
                    modelOp.setCts(Integer.parseInt(jFCts.getText()));
                    modelOp.setNomeSegurado(tfNomeSegurado.getText());
                    modelOp.setCodCidade(controllerCidade.getCidadeController(this.cbCidade.getSelectedItem().toString()).getCodigo());
                    modelOp.setCodEstado(controllerEstado.getEstadoUFController(this.cbUf.getSelectedItem().toString()).getCodigo());
                    modelOp.setObsSinistro(jTObsSinistro.getText());
                    modelOp.setHoraSinistro(jFHoraSinistro.getText());
                    modelOp.setSituacaoNotaFiscal(cbStatusNotaFiscal.getSelectedItem().toString());
                    modelOp.setSinistroBairro(this.tfBairro.getText());

                    modelOp.setNumeroNFProcesso(tfNumeroNFProcesso.getText());
                    //COMEÇO VALORES DOUBLE
                    try {
                        modelOp.setPrejuizo(Double.parseDouble(new Moeda().FommatoStringoSomarMil(jFValorPrejuizoNegativa.getText())));
                    } catch (NumberFormatException e) {
                        modelOp.setPrejuizo(zerar);
                    }
                    try {
                        modelOp.setHonorarioDefinido(Double.parseDouble(new Moeda().FommatoStringoSomarMil(jFHonorarioDefinido.getText())));
                    } catch (NumberFormatException e) {
                        modelOp.setHonorarioDefinido(zerar);
                    }
                    try {
                        modelOp.setPercentuallRetidoReal(Double.parseDouble(new Porcentagem().ValorSemPercentual(tfPercentualRetidoReal.getText())));
                    } catch (NumberFormatException e) {
                        modelOp.setPercentuallRetidoReal(zerar);
                    }
                    try {
                        modelOp.setAlicotaPercentualHonorarios(Double.parseDouble(new Porcentagem().ValorSemPercentual(cbAlicotaPercentualHonorarios.getSelectedItem().toString())));
                    } catch (NumberFormatException e) {
                        modelOp.setAlicotaPercentualHonorarios(zerar);
                    }
                    try {
                        modelOp.setValorTotalHonorariosSemRetencao(Double.parseDouble(new Moeda().FommatoStringoSomarMil(jFHonorarioDefinido.getText())));
                    } catch (NumberFormatException e) {
                        modelOp.setValorTotalHonorariosSemRetencao(zerar);
                    }
                    try {
                        modelOp.setRetidoRealNegativa(Double.parseDouble(new Moeda().FommatoStringoSomarMil(jFRetidoRealNegativa.getText())));
                    } catch (NumberFormatException e) {
                        modelOp.setRetidoRealNegativa(zerar);
                    }
                    try {
                        modelOp.setPercentuallRetidoRealNeg(Double.parseDouble(new Porcentagem().ValorSemPercentual(tfPercentuallRetidoRealNeg.getText())));
                    } catch (NumberFormatException e) {
                        modelOp.setPercentuallRetidoRealNeg(zerar);
                    }
                    try {
                        modelOp.setValorRetencaoTotalNeg(Double.parseDouble(new Moeda().FommatoStringoSomarMil(jFValorRetencaoTotalNeg.getText())));
                    } catch (NumberFormatException e) {
                        modelOp.setValorRetencaoTotalNeg(zerar);
                    }
                    try {
                        modelOp.setValorNegativaTotal(Double.parseDouble(new Moeda().FommatoStringoSomarMil(jFValorNegativaTotal.getText())));
                    } catch (NumberFormatException e) {
                        modelOp.setValorNegativaTotal(zerar);
                    }
                    try {
                        modelOp.setValorRetidoRetencaoNeg(Double.parseDouble(new Moeda().FommatoStringoSomarMil(jFValorRetidoRetencaoNeg.getText())));
                    } catch (NumberFormatException e) {
                        modelOp.setValorRetidoRetencaoNeg(zerar);
                    }
                    try {
                        modelOp.setAlicotaNeg(Double.parseDouble(new Porcentagem().ValorSemPercentual(cbAlicotaNeg.getSelectedItem().toString())));
                    } catch (NumberFormatException e) {
                        modelOp.setAlicotaNeg(zerar);
                    }
                    try {
                        modelOp.setRetidoReal(Double.parseDouble(new Moeda().FommatoStringoSomarMil(jFRetidoReal.getText())));
                    } catch (NumberFormatException e) {
                        modelOp.setRetidoReal(zerar);
                    }
                    try {
                        modelOp.setValorHonorarioProcesso(Double.parseDouble(new Moeda().FommatoStringoSomarMil("0")));
                    } catch (NumberFormatException e) {
                        modelOp.setValorHonorarioProcesso(zerar);
                    }
                    try {
                        modelOp.setValorHonorariosRetido(Double.parseDouble(new Moeda().FommatoStringoSomarMil(jFValorHonorariosRetido.getText())));
                    } catch (NumberFormatException e) {
                        modelOp.setValorHonorariosRetido(zerar);
                    }
                    try {
                        modelOp.setValorTotalHonorariosComRetencao(Double.parseDouble(new Moeda().FommatoStringoSomarMil(jFValorTotalHonorariosComRetencao.getText())));
                    } catch (NumberFormatException e) {
                        modelOp.setValorTotalHonorariosComRetencao(zerar);
                    }
                    try {
                        modelOp.setValorNotaRealNegIndevido(Double.parseDouble(new Moeda().FommatoStringoSomarMil(jFValorNotaRealNegIndevido.getText())));
                    } catch (NumberFormatException e) {
                        modelOp.setValorNotaRealNegIndevido(zerar);
                    }
                    try {
                        modelOp.setValorNotaRealIndevido(Double.parseDouble(new Moeda().FommatoStringoSomarMil(jFValorNotaRealIndevido.getText())));
                    } catch (NumberFormatException e) {
                        modelOp.setValorNotaRealIndevido(zerar);
                    }
                    try {
                        modelOp.setValorDespesasTotalRegistro(Double.parseDouble(new Moeda().FommatoStringoSomarMil(jFValorDespesasTotalRegistro.getText())));
                    } catch (NumberFormatException e) {
                        modelOp.setValorDespesasTotalRegistro(zerar);
                    }

                    //FIM DOS VALORES DOUBLE
                    //INICIO DAS DATAS
                    modelOp.setDataProcesso(new Mascaras().DataSalvanosql(dataAtual()));
                    modelOp.setDataEmissaoNegativa(new Mascaras().DataSalvanosql(jFDataEmissaoNegativa.getText()));
                    modelOp.setDataPgtoNegativa(new Mascaras().DataSalvanosql(jFDataPgtoNegativa.getText()));
                    modelOp.setDataPrevisaoPgtoNegativa(new Mascaras().DataSalvanosql(jFDataPrevisaoPgtoNegativa.getText()));
                    modelOp.setDataEntrada(new Mascaras().DataSalvanosql(jFDataEntrada.getText()));
                    modelOp.setDataSaida(new Mascaras().DataSalvanosql(jFDataSaida.getText()));
                    modelOp.setDataSinistro(new Mascaras().DataSalvanosql(jFDataSinistro.getText()));
                    modelOp.setDataEmissaoNFhonorarios(new Mascaras().DataSalvanosql(jFDataEmissaoNFhonorarios.getText()));
                    modelOp.setDataPrevisaoPgtoNFhonorarios(new Mascaras().DataSalvanosql(jFDataPrevisaoPgtoNFhonorarios.getText()));
                    modelOp.setDataPgtoNF(new Mascaras().DataSalvanosql(jFDataPgtoNFhonorarios.getText()));
                    //FIM DAS DATAS

                    modelOp.setNfNegPrejuizo(tfNumeroNFNegativa.getText());
                    modelOp.setNumeroSinistro(tfNumSinistro.getText());
                    modelOp.setSeguradoPlaca(tfSeguradoPlaca.getText());
                    modelOp.setAnalista(tfAnalista.getText());
                    modelOp.setObsHonorariosProcesso(tfObsHonorariosProcesso.getText());
                    modelOp.setObsRetencaoAlicotaIndevido(ObsRetencaoAlicotaIndevido.getText());
                    modelOp.setObsRetencaoISSNeg(tfObsRetencaoISSNeg.getText());
                    modelOp.setIndevidoISS(jRCheckIndevidoISS.isSelected());
                    modelOp.setIndevidoISSNegativa(jRCheckIndevidoISSNegativa.isSelected());
                    modelOp.setSituacaoPgtoNegativa(cbSituacaoPgtoNegativa.getSelectedItem().toString());
                    modelOp.setSituacaoPgtoProcesso(1);

                    codigoServico = Integer.parseInt(tbServicos.getValueAt(i, 0).toString());
                    modelOp.setJTableTerceiroNome(String.valueOf(tbServicos.getValueAt(i, 2).toString()));
                    modelOp.setJTableTerceiroPlaca(String.valueOf(tbServicos.getValueAt(i, 3).toString()));
                    modelOp.setJTableTerceiroObs(String.valueOf(tbServicos.getValueAt(i, 4).toString()));
                    modelOp.setTipoPagamento(1);

                    modelOp.setCodigo((codigoServico));
                    listaModelOp.add(modelOp);
                    listaModelServicos.add(modelServicos);

                }
                modelOp.setListamModelOp(listaModelOp);
                modelOp.setServicosCodigo(controllerServicos.getServicosController(cbServico.getSelectedObject().toString()).getCodigo());
                modelServicos.setListaModelServicos(listaModelServicos);

                //salvar venda
                int codigoOp = controllerOp.salvarOpController(modelOp);
                if (codigoOp > 0) {
                    modelOp.setCodigo(codigoOp);

                    //salvar lista de servicos
                    controllerOp.salvarOpServicosController(modelOp);
                    JOptionPane.showMessageDialog(this, "Registro gravado com sucesso!");
                    ViewOp.op.requestFocus();
                    op.carregarProcessosReceber();
                    /*this.carregarPedidos();*/
                    dispose();
                    return true;
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao gravar os dados!", "ERRO", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            } else {
                modelOp = new ModelOp();
                modelServicos = new ModelServicos();
                modelOp.setSeguradoras((controllerSeguradora.getSeguradoraController(cbSeguradoras.getSelectedItem().toString()).getCodigo()));
                modelOp.setObsSegurado(tfObsSegurado.getText());
                modelOp.setTipo(1);
                modelOp.setCts(Integer.parseInt(jFCts.getText()));
                modelOp.setNomeSegurado(tfNomeSegurado.getText());
                modelOp.setCodCidade(controllerCidade.getCidadeController(this.cbCidade.getSelectedItem().toString()).getCodigo());
                modelOp.setCodEstado(controllerEstado.getEstadoUFController(this.cbUf.getSelectedItem().toString()).getCodigo());
                modelOp.setObsSinistro(jTObsSinistro.getText());
                modelOp.setHoraSinistro(jFHoraSinistro.getText());
                modelOp.setSituacaoNotaFiscal(cbStatusNotaFiscal.getSelectedItem().toString());
                modelOp.setSinistroBairro(this.tfBairro.getText());
                modelOp.setNumeroNFProcesso(tfNumeroNFProcesso.getText());

                //COMEÇO VALORES DOUBLE
                try {
                    modelOp.setPrejuizo(Double.parseDouble(new Moeda().FommatoStringoSomarMil(jFValorPrejuizoNegativa.getText())));
                } catch (NumberFormatException e) {
                    modelOp.setPrejuizo(zerar);
                }
                try {
                    modelOp.setHonorarioDefinido(Double.parseDouble(new Moeda().FommatoStringoSomarMil(jFHonorarioDefinido.getText())));
                } catch (NumberFormatException e) {
                    modelOp.setHonorarioDefinido(zerar);
                }
                try {
                    modelOp.setPercentuallRetidoReal(Double.parseDouble(new Porcentagem().ValorSemPercentual(tfPercentualRetidoReal.getText())));
                } catch (NumberFormatException e) {
                    modelOp.setPercentuallRetidoReal(zerar);
                }
                try {
                    modelOp.setAlicotaPercentualHonorarios(Double.parseDouble(new Porcentagem().ValorSemPercentual(cbAlicotaPercentualHonorarios.getSelectedItem().toString())));
                } catch (NumberFormatException e) {
                    modelOp.setAlicotaPercentualHonorarios(zerar);
                }
                try {
                    modelOp.setValorTotalHonorariosSemRetencao(Double.parseDouble(new Moeda().FommatoStringoSomarMil(jFHonorarioDefinido.getText())));
                } catch (NumberFormatException e) {
                    modelOp.setValorTotalHonorariosSemRetencao(zerar);
                }
                try {
                    modelOp.setRetidoRealNegativa(Double.parseDouble(new Moeda().FommatoStringoSomarMil(jFRetidoRealNegativa.getText())));
                } catch (NumberFormatException e) {
                    modelOp.setRetidoRealNegativa(zerar);
                }
                try {
                    modelOp.setPercentuallRetidoRealNeg(Double.parseDouble(new Porcentagem().ValorSemPercentual(tfPercentuallRetidoRealNeg.getText())));
                } catch (NumberFormatException e) {
                    modelOp.setPercentuallRetidoRealNeg(zerar);
                }
                try {
                    modelOp.setValorRetencaoTotalNeg(Double.parseDouble(new Moeda().FommatoStringoSomarMil(jFValorRetencaoTotalNeg.getText())));
                } catch (NumberFormatException e) {
                    modelOp.setValorRetencaoTotalNeg(zerar);
                }
                try {
                    modelOp.setValorNegativaTotal(Double.parseDouble(new Moeda().FommatoStringoSomarMil(jFValorNegativaTotal.getText())));
                } catch (NumberFormatException e) {
                    modelOp.setValorNegativaTotal(zerar);
                }
                try {
                    modelOp.setValorRetidoRetencaoNeg(Double.parseDouble(new Moeda().FommatoStringoSomarMil(jFValorRetidoRetencaoNeg.getText())));
                } catch (NumberFormatException e) {
                    modelOp.setValorRetidoRetencaoNeg(zerar);
                }
                try {
                    modelOp.setAlicotaNeg(Double.parseDouble(new Porcentagem().ValorSemPercentual(cbAlicotaNeg.getSelectedItem().toString())));
                } catch (NumberFormatException e) {
                    modelOp.setAlicotaNeg(zerar);
                }
                try {
                    modelOp.setRetidoReal(Double.parseDouble(new Moeda().FommatoStringoSomarMil(jFRetidoReal.getText())));
                } catch (NumberFormatException e) {
                    modelOp.setRetidoReal(zerar);
                }
                try {
                    modelOp.setValorHonorarioProcesso(Double.parseDouble(new Moeda().FommatoStringoSomarMil("0")));
                } catch (NumberFormatException e) {
                    modelOp.setValorHonorarioProcesso(zerar);
                }
                try {
                    modelOp.setValorHonorariosRetido(Double.parseDouble(new Moeda().FommatoStringoSomarMil(jFValorHonorariosRetido.getText())));
                } catch (NumberFormatException e) {
                    modelOp.setValorHonorariosRetido(zerar);
                }
                try {
                    modelOp.setValorTotalHonorariosComRetencao(Double.parseDouble(new Moeda().FommatoStringoSomarMil(jFValorTotalHonorariosComRetencao.getText())));
                } catch (NumberFormatException e) {
                    modelOp.setValorTotalHonorariosComRetencao(zerar);
                }
                try {
                    modelOp.setValorNotaRealNegIndevido(Double.parseDouble(new Moeda().FommatoStringoSomarMil(jFValorNotaRealNegIndevido.getText())));
                } catch (NumberFormatException e) {
                    modelOp.setValorNotaRealNegIndevido(zerar);
                }
                try {
                    modelOp.setValorNotaRealIndevido(Double.parseDouble(new Moeda().FommatoStringoSomarMil(jFValorNotaRealIndevido.getText())));
                } catch (NumberFormatException e) {
                    modelOp.setValorNotaRealIndevido(zerar);
                }
                try {
                    modelOp.setValorDespesasTotalRegistro(Double.parseDouble(new Moeda().FommatoStringoSomarMil(jFValorDespesasTotalRegistro.getText())));
                } catch (NumberFormatException e) {
                    modelOp.setValorDespesasTotalRegistro(zerar);
                }
                //FIM DOS VALORES DOUBLE
                //INICIO DAS DATAS
                modelOp.setDataProcesso(new Mascaras().DataSalvanosql(dataAtual()));
                modelOp.setDataEmissaoNegativa(new Mascaras().DataSalvanosql(jFDataEmissaoNegativa.getText()));
                modelOp.setDataPgtoNegativa(new Mascaras().DataSalvanosql(jFDataPgtoNegativa.getText()));
                modelOp.setDataPrevisaoPgtoNegativa(new Mascaras().DataSalvanosql(jFDataPrevisaoPgtoNegativa.getText()));
                modelOp.setDataEntrada(new Mascaras().DataSalvanosql(jFDataEntrada.getText()));
                modelOp.setDataSaida(new Mascaras().DataSalvanosql(jFDataSaida.getText()));
                modelOp.setDataSinistro(new Mascaras().DataSalvanosql(jFDataSinistro.getText()));
                modelOp.setDataEmissaoNFhonorarios(new Mascaras().DataSalvanosql(jFDataEmissaoNFhonorarios.getText()));
                modelOp.setDataPrevisaoPgtoNFhonorarios(new Mascaras().DataSalvanosql(jFDataPrevisaoPgtoNFhonorarios.getText()));
                modelOp.setDataPgtoNF(new Mascaras().DataSalvanosql(jFDataPgtoNFhonorarios.getText()));
                //FIM DAS DATAS

                //FIM DOS VALORES DOUBLE
                modelOp.setNfNegPrejuizo(tfNumeroNFNegativa.getText());
                modelOp.setNumeroSinistro(tfNumSinistro.getText());
                modelOp.setSeguradoPlaca(tfSeguradoPlaca.getText());
                modelOp.setAnalista(tfAnalista.getText());
                modelOp.setObsHonorariosProcesso(tfObsHonorariosProcesso.getText());
                modelOp.setObsRetencaoAlicotaIndevido(ObsRetencaoAlicotaIndevido.getText());
                modelOp.setObsRetencaoISSNeg(tfObsRetencaoISSNeg.getText());
                modelOp.setIndevidoISS(jRCheckIndevidoISS.isSelected());
                modelOp.setIndevidoISSNegativa(jRCheckIndevidoISSNegativa.isSelected());
                modelOp.setSituacaoPgtoNegativa(cbSituacaoPgtoNegativa.getSelectedItem().toString());
                modelOp.setSituacaoPgtoProcesso(1);
                modelOp.setTipoPagamento(1);
                //modelOp.setCodigo((codigoServico));
                listaModelOp.add(modelOp);
                // listaModelServicos.add(modelServicos);
                modelOp.setServicosCodigo(controllerServicos.getServicosController(cbServico.getSelectedObject().toString()).getCodigo());
                modelOp.setListamModelOp(listaModelOp);
                // modelServicos.setListaModelServicos(listaModelServicos);

                //salvar venda
                int codigoOp = controllerOp.salvarOpController(modelOp);
                if (codigoOp > 0) {
                    modelOp.setCodigo(codigoOp);

                    //salvar lista de servicos
                    //controllerOp.salvarOpServicosController(modelOp);
                    JOptionPane.showMessageDialog(this, "Registro gravado com sucesso!");
                    ViewOp.op.requestFocus();
                    op.carregarProcessosReceber();
                    op.carregarProcessosRecebidos();
                    op.carregarProcessosAguardandoPagamento();
                    /*this.carregarPedidos();*/
                    dispose();
                    return true;
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao gravar os dados!", "ERRO", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
        }
    }

    private void limpaCampos() {
        //tfCodigo.setText("Novo");
        listarSeguradoras();
        listarServicos();
        listarCodigoServicos();
        this.listarEstados();
        this.listarCidades();
        listarSeguradoras();
        listarCodigosSeguradoras();
        retornarDadosSeguradoras();
        listarTaxaISS();
        this.cbSeguradoras.setEnabled(true);
        this.cbServico.setEnabled(false);
        this.jFHonorarioDefinido.setText("R$ 0,00");
        this.tfObsSegurado.setText("");
        this.jFCts.setText("0");
        this.jFDataEntrada.setText("");
        this.jFDataSaida.setText("");
        this.tfNomeSegurado.setText("");
        this.cbSituacaoPgtoProcesso.setSelectedIndex(0);
        this.jTObsSinistro.setText("");
        this.jFHoraSinistro.setText("");
        this.jFDataSinistro.setText("");
        this.tfPercentualRetidoReal.setText("0,00%");
        this.jFRetidoRealNegativa.setText("R$ 0,00");
        this.jFValorNotaRealNegIndevido.setText("R$ 0,00");
        this.jFDataEmissaoNFhonorarios.setText("");
        this.jFDataPrevisaoPgtoNFhonorarios.setText("");
        this.jFHonorarioDefinido.setText("R$ 0,00");
        this.tfNumeroNFProcesso.setText("");
        this.jFDataPgtoNFhonorarios.setText("");
        this.jFValorNotaRealIndevido.setText("R$ 0,00");
        this.tfPercentuallRetidoRealNeg.setText("0,00%");
        this.jFValorTotalHonorariosComRetencao.setText("R$ 0,00");
        this.jFValorHonorariosRetido.setText("R$ 0,00");
        this.jFRetidoReal.setText("R$ 0,00");
        this.jFValorRetidoRetencaoNeg.setText("R$ 0,00");
        this.jFDataEmissaoNegativa.setText("");
        this.jFDataPgtoNegativa.setText("");
        this.jFDataPrevisaoPgtoNegativa.setText("");
        this.jFValorPrejuizoNegativa.setText("R$ 0,00");
        this.jFValorNegativaTotal.setText("R$ 0,00");
        this.jFValorRetencaoTotalNeg.setText("");
        this.cbSituacaoPgtoNegativa.setSelectedIndex(0);
        this.jFRetidoRealNegativa.setText("R$ 0,00");
        this.tfObsHonorariosProcesso.setText("");
        this.tfNumSinistro.setText("");
        //provavelmente sem model
        this.tfBairro.setText("");
        this.tfAnalista.setText("");
        this.tfSeguradoPlaca.setText("");
        this.tfNumeroNFNegativa.setText("");
        this.tfObsRetencaoISSNeg.setText("");
        this.ObsRetencaoAlicotaIndevido.setText("");
        this.jFValorDespesasTotalRegistro.setText("R$ 0,00");

        //NÃO SAO VARIAVEIS DO MODEL, APENAS PARA AMOSTRA NA TELA
        this.tfEstadoRetencao.setText("");
        this.tfCidadeRetencao.setText("");
        this.tfEstadoRetencaoNeg.setText("");
        this.tfCidadeRetencaoNeg.setText("");
        this.tfTerceiroNome.setText("");
        this.tfTerceiroPlaca.setText("");
        this.tfTerceiroObs.setText("");
        this.jfCNPJ.setText("");

        //---------------------------------------------------------
        this.jRCheckIndevidoISS.setSelected(false);
        this.jRCheckIndevidoISSNegativa.setSelected(false);
        DefaultTableModel modelo = (DefaultTableModel) tbServicos.getModel();
        modelo.setNumRows(0);

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
        listarTaxaISS();
        jTPanelProcessos.setSelectedIndex(0);
        panelTabbedKGVerficiar();
        modelSeguradora = controllerSeguradora.getSeguradoraController(cbSeguradoras.getSelectedItem().toString());
        //recupera o código e seta CNPJ
        this.cbCodSeguradora.setSelectedItem(modelSeguradora.getCodigo());
        this.jfCNPJ.setText(modelSeguradora.getCnpj());
        this.jfCNPJ.setForeground(Color.white);
        this.jfCNPJ.setForeground(Color.getHSBColor(51, 51, 51));

        //RECUPERA VALOR HONORARIOS POR SEGURADORA
        try {
            SetIcone();
            int codigoServico;
            this.cbSeguradoras.setEnabled(false);
            this.cbServico.setEnabled(false);
            modelOp.setCodigo(op.codigo);
            modelOp = controllerOp.getOpController(op.codigo);
            this.cbCodSeguradora.setSelectedItem(modelOp.getSeguradoras());
            this.tfCodigo.setText(String.valueOf(modelOp.getCodigo()));
            this.cbCodSeguradora.setSelectedItem(modelOp.getSeguradoras());
            this.listarServicos();
            this.listarCodigoServicos();
            this.cbServico.setSelectedItem(String.valueOf(controllerServicos.getServicosController(modelOp.getServicosCodigo()).getNome()));
            this.cbCodServico.setSelectedItem(String.valueOf(controllerServicos.getServicosController(modelOp.getServicosCodigo()).getCodigo()));
            this.jFHonorarioDefinido.setText(new Moeda().valorStringTODoubleAtt(String.valueOf(modelOp.getValorTotalHonorariosSemRetencao())));
            this.tfObsSegurado.setText(modelOp.getObsSegurado());
            this.jFCts.setText(String.valueOf(modelOp.getCts()));
            this.tfNomeSegurado.setText(modelOp.getNomeSegurado());
            //this.cbTipodePagamento.setSelectedItem(controllerTipoPagamento.getFormaPagamentoController(modelOp.getTipoPagamento()).getDescricao());
            this.cbUf.setSelectedItem(controllerEstado.getEstadoController(modelOp.getCodEstado()).getUf());
            this.listarCidades();
            this.cbCidade.setSelectedItem(controllerCidade.getCidadeController(modelOp.getCodCidade()).getNome());
            this.jTObsSinistro.setText(modelOp.getObsSinistro());
            this.jFHoraSinistro.setText(modelOp.getHoraSinistro());
            this.cbAlicotaPercentualHonorarios.setSelectedItem(new Porcentagem().ValorComPercentual(String.valueOf(modelOp.getAlicotaPercentualHonorarios().toString())));
            this.tfPercentualRetidoReal.setText(new Porcentagem().ValorComPercentual(String.valueOf(modelOp.getPercentuallRetidoReal())));
            this.jFHonorarioDefinido.setText(new Moeda().valorStringTODoubleAtt(String.valueOf(modelOp.getHonorarioDefinido())));
            this.tfNumeroNFProcesso.setText(modelOp.getNumeroNFProcesso());
            this.jFValorNotaRealIndevido.setText(new Moeda().valorStringTODoubleAtt(String.valueOf(modelOp.getValorNotaRealIndevido())));
            this.jFValorNotaRealNegIndevido.setText(new Moeda().valorStringTODoubleAtt(String.valueOf(modelOp.getValorNotaRealNegIndevido())));
            this.jFValorTotalHonorariosComRetencao.setText(new Moeda().valorStringTODoubleAtt(String.valueOf(modelOp.getValorTotalHonorariosComRetencao())));
            this.jFValorHonorariosRetido.setText(new Moeda().valorStringTODoubleAtt(String.valueOf(modelOp.getValorHonorariosRetido())));
            this.jFRetidoReal.setText(new Moeda().valorStringTODoubleAtt(String.valueOf(modelOp.getRetidoReal())));
            this.cbAlicotaNeg.setSelectedItem(new Porcentagem().ValorComPercentual(String.valueOf(modelOp.getAlicotaNeg().toString())));
            this.jFValorRetidoRetencaoNeg.setText(new Moeda().valorStringTODoubleAtt(String.valueOf(modelOp.getValorRetidoRetencaoNeg())));
            this.tfNumeroNFNegativa.setText(modelOp.getNfNegPrejuizo());
            this.jFValorNegativaTotal.setText(new Moeda().valorStringTODoubleAtt(String.valueOf(modelOp.getValorNegativaTotal())));
            this.jFRetidoRealNegativa.setText(new Moeda().valorStringTODoubleAtt(String.valueOf(modelOp.getRetidoRealNegativa())));
            this.jFValorRetencaoTotalNeg.setText(new Moeda().valorStringTODoubleAtt(String.valueOf(modelOp.getValorRetencaoTotalNeg())));
            this.tfPercentuallRetidoRealNeg.setText(new Porcentagem().ValorComPercentual(String.valueOf(modelOp.getPercentuallRetidoRealNeg())));
            this.tfNumSinistro.setText(modelOp.getNumeroSinistro());
            this.jFValorPrejuizoNegativa.setText(new Moeda().valorStringTODoubleAtt(String.valueOf(modelOp.getPrejuizo())));
            this.tfAnalista.setText(modelOp.getAnalista());
            this.tfSeguradoPlaca.setText(modelOp.getSeguradoPlaca());
            this.tfObsHonorariosProcesso.setText(modelOp.getObsHonorariosProcesso());
            this.ObsRetencaoAlicotaIndevido.setText(modelOp.getObsRetencaoAlicotaIndevido());
            this.tfObsRetencaoISSNeg.setText(modelOp.getObsRetencaoISSNeg());
            this.jRCheckIndevidoISS.setSelected(modelOp.isIndevidoISS());
            this.jRCheckIndevidoISSNegativa.setSelected(modelOp.isIndevidoISSNegativa());
            this.cbSituacaoPgtoNegativa.setSelectedItem(modelOp.getSituacaoPgtoNegativa());
            this.cbStatusNotaFiscal.setSelectedItem(modelOp.getSituacaoNotaFiscal());
            this.cbSituacaoPgtoProcesso.setSelectedItem(controllerTipoPagamento.getFormaPagamentoController(modelOp.getTipo()).getDescricao());
            this.tfBairro.setText(modelOp.getSinistroBairro());
            this.jFValorDespesasTotalRegistro.setText(new Moeda().valorStringTODoubleAtt(String.valueOf(modelOp.getValorDespesasTotalRegistro())));

            //INICIO DAS DATAS
            this.jFDataEmissaoNegativa.setText(new Mascaras().DataRecuperasql(modelOp.getDataEmissaoNegativa()));
            this.jFDataPgtoNegativa.setText(new Mascaras().DataRecuperasql(modelOp.getDataPgtoNegativa()));
            this.jFDataPrevisaoPgtoNegativa.setText(new Mascaras().DataRecuperasql(modelOp.getDataPrevisaoPgtoNegativa()));
            //jFDataEntrada.setText(new Mascaras().DataRecuperasql(modelOp.getDataEntrada()));
            this.jFDataEntrada.setText(new Mascaras().DataRecuperasql(modelOp.getDataEntrada()));
            this.jFDataSaida.setText(new Mascaras().DataRecuperasql(modelOp.getDataSaida()));
            this.jFDataSinistro.setText(new Mascaras().DataRecuperasql(modelOp.getDataSinistro()));
            this.jFDataEmissaoNFhonorarios.setText(new Mascaras().DataRecuperasql(modelOp.getDataEmissaoNFhonorarios()));
            this.jFDataPrevisaoPgtoNFhonorarios.setText(new Mascaras().DataRecuperasql(modelOp.getDataPrevisaoPgtoNFhonorarios()));
            this.jFDataPgtoNFhonorarios.setText(new Mascaras().DataRecuperasql(modelOp.getDataPgtoNF()));
            //FIM DAS DATAS

            //condições
            carregarTerceirosAlteracao();
            this.jButtonAddTerceiro.setEnabled(false);
            this.jButtonRemoveTerceiro.setEnabled(false);
            //verifica JRChackIndevido Retencao Honorarios
            if (jRCheckIndevidoISS.isSelected()) {
                jFValorNotaRealIndevido.setEnabled(true);
                ObsRetencaoAlicotaIndevido.setEnabled(true);
            } else {
                jFValorNotaRealIndevido.setEnabled(false);
                jFValorNotaRealIndevido.setText("R$ 0,00");
                jFRetidoReal.setText("R$ 0,00");
                tfPercentualRetidoReal.setText("0,00%");
                jFRetidoReal.setForeground(Color.WHITE);
                jFRetidoReal.setBackground(Color.GRAY);
                tfPercentualRetidoReal.setForeground(Color.WHITE);
                tfPercentualRetidoReal.setBackground(Color.GRAY);
                jLRetidoReal.setForeground(Color.DARK_GRAY);
                jLAlicotaProcessoReal.setForeground(Color.DARK_GRAY);
                ObsRetencaoAlicotaIndevido.setText("");
                ObsRetencaoAlicotaIndevido.setEnabled(false);
            }

            //Verifica JRCheckIndevido Retencao Indevida Negativa
            if (jRCheckIndevidoISSNegativa.isSelected()) {
                jFValorNotaRealNegIndevido.setEnabled(true);
                tfObsRetencaoISSNeg.setEnabled(true);
            } else {
                jFValorNotaRealNegIndevido.setEnabled(false);
                jFValorNotaRealNegIndevido.setText("R$ 0,00");
                jFRetidoRealNegativa.setText("R$ 0,00");
                tfPercentuallRetidoRealNeg.setText("0,00%");
                jFValorNotaRealNegIndevido.setForeground(Color.WHITE);
                jFValorNotaRealNegIndevido.setBackground(Color.GRAY);
                tfPercentuallRetidoRealNeg.setForeground(Color.WHITE);
                tfPercentuallRetidoRealNeg.setBackground(Color.GRAY);
                jLRetidoRealNegativa.setForeground(Color.DARK_GRAY);
                jLAlicotalRealNegativa.setForeground(Color.DARK_GRAY);
                tfObsRetencaoISSNeg.setText("");
                tfObsRetencaoISSNeg.setEnabled(false);
            }
            //recupera os dados do banco
            listaModelOpAlterar = controllerOp.getListaOpController(op.codigo);
            //carregar lista de produtos da venda
            DefaultTableModel modelo = (DefaultTableModel) tbServicos.getModel();
            modelo.setNumRows(0);

            int cont = listaModelOpAlterar.size();

            for (int i = 0; i < cont; i++) {
                codigoServico = (listaModelOpAlterar.get(i).getServicosCodigo());
                modelServicos = controllerServicos.getServicosController(codigoServico);
                modelo.addRow(new Object[]{
                    listaModelOpAlterar.get(i).getServicosCodigo(),
                    modelServicos.getNome(),
                    listaModelOpAlterar.get(i).getJTableTerceiroNome(),
                    listaModelOpAlterar.get(i).getJTableTerceiroPlaca(),
                    listaModelOpAlterar.get(i).getJTableTerceiroObs()
                });
            }
            return true;
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Código inválido ou nenhum registro selecionado", "Aviso", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }

    private boolean alteraOp() {
        if (tfNumSinistro.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Você deve preencher todos os campos!", "ATENÇÂO", JOptionPane.WARNING_MESSAGE);
            return false;
        } else {
            //tipoCadastro = "alteracao";
            listaModelOp = new ArrayList<>();
            listaModelOp = new ArrayList<>();
            Double zerar = 0.00;
            int codigoProcesso;
            Datas bl = new Datas();
            // for (int i = 0; i < tbServicos.getRowCount(); i++) {
            modelOp = new ModelOp();
            codigoProcesso = (Integer.parseInt(this.tfCodigo.getText()));
            //modelServicos = new ModelServicos();
            modelOp.setSeguradoras((controllerSeguradora.getSeguradoraController(cbSeguradoras.getSelectedItem().toString()).getCodigo()));
            modelOp.setObsSegurado(tfObsSegurado.getText());
            modelOp.setCts(Integer.parseInt(jFCts.getText()));
            modelOp.setNomeSegurado(tfNomeSegurado.getText());
            modelOp.setCodCidade(controllerCidade.getCidadeController(this.cbCidade.getSelectedItem().toString()).getCodigo());
            modelOp.setCodEstado(controllerEstado.getEstadoUFController(this.cbUf.getSelectedItem().toString()).getCodigo());
            modelOp.setObsSinistro(jTObsSinistro.getText());
            modelOp.setHoraSinistro(jFHoraSinistro.getText());
            modelOp.setSinistroBairro(this.tfBairro.getText());
            modelOp.setNumeroNFProcesso(tfNumeroNFProcesso.getText());
            modelOp.setSituacaoNotaFiscal(cbStatusNotaFiscal.getSelectedItem().toString());
            //COMEÇO VALORES DOUBLE
            try {
                modelOp.setPrejuizo(Double.parseDouble(new Moeda().FommatoStringoSomarMil(jFValorPrejuizoNegativa.getText())));
            } catch (NumberFormatException e) {
                modelOp.setPrejuizo(zerar);
            }
            try {
                modelOp.setHonorarioDefinido(Double.parseDouble(new Moeda().FommatoStringoSomarMil(jFHonorarioDefinido.getText())));
            } catch (NumberFormatException e) {
                modelOp.setHonorarioDefinido(zerar);
            }
            try {
                modelOp.setPercentuallRetidoReal(Double.parseDouble(new Porcentagem().ValorSemPercentual(tfPercentualRetidoReal.getText())));
            } catch (NumberFormatException e) {
                modelOp.setPercentuallRetidoReal(zerar);
            }
            try {
                modelOp.setAlicotaPercentualHonorarios(Double.parseDouble(new Porcentagem().ValorSemPercentual(cbAlicotaPercentualHonorarios.getSelectedItem().toString())));
            } catch (NumberFormatException e) {
                modelOp.setAlicotaPercentualHonorarios(zerar);
            }
            try {
                modelOp.setValorTotalHonorariosSemRetencao(Double.parseDouble(new Moeda().FommatoStringoSomarMil(jFHonorarioDefinido.getText())));
            } catch (NumberFormatException e) {
                modelOp.setValorTotalHonorariosSemRetencao(zerar);
            }
            try {
                modelOp.setRetidoRealNegativa(Double.parseDouble(new Moeda().FommatoStringoSomarMil(jFRetidoRealNegativa.getText())));
            } catch (NumberFormatException e) {
                modelOp.setRetidoRealNegativa(zerar);
            }
            try {
                modelOp.setPercentuallRetidoRealNeg(Double.parseDouble(new Porcentagem().ValorSemPercentual(tfPercentuallRetidoRealNeg.getText())));
            } catch (NumberFormatException e) {
                modelOp.setPercentuallRetidoRealNeg(zerar);
            }
            try {
                modelOp.setValorRetencaoTotalNeg(Double.parseDouble(new Moeda().FommatoStringoSomarMil(jFValorRetencaoTotalNeg.getText())));
            } catch (NumberFormatException e) {
                modelOp.setValorRetencaoTotalNeg(zerar);
            }
            try {
                modelOp.setValorNegativaTotal(Double.parseDouble(new Moeda().FommatoStringoSomarMil(jFValorNegativaTotal.getText())));
            } catch (NumberFormatException e) {
                modelOp.setValorNegativaTotal(zerar);
            }
            try {
                modelOp.setValorRetidoRetencaoNeg(Double.parseDouble(new Moeda().FommatoStringoSomarMil(jFValorRetidoRetencaoNeg.getText())));
            } catch (NumberFormatException e) {
                modelOp.setValorRetidoRetencaoNeg(zerar);
            }
            try {
                modelOp.setAlicotaNeg(Double.parseDouble(new Porcentagem().ValorSemPercentual(cbAlicotaNeg.getSelectedItem().toString())));
            } catch (NumberFormatException e) {
                modelOp.setAlicotaNeg(zerar);
            }
            try {
                modelOp.setRetidoReal(Double.parseDouble(new Moeda().FommatoStringoSomarMil(jFRetidoReal.getText())));
            } catch (NumberFormatException e) {
                modelOp.setRetidoReal(zerar);
            }
            modelOp.setValorHonorarioProcesso(zerar);
            try {
                modelOp.setValorHonorariosRetido(Double.parseDouble(new Moeda().FommatoStringoSomarMil(jFValorHonorariosRetido.getText())));
            } catch (NumberFormatException e) {
                modelOp.setValorHonorariosRetido(zerar);
            }
            try {
                modelOp.setValorTotalHonorariosComRetencao(Double.parseDouble(new Moeda().FommatoStringoSomarMil(jFValorTotalHonorariosComRetencao.getText())));
            } catch (NumberFormatException e) {
                modelOp.setValorTotalHonorariosComRetencao(zerar);
            }
            try {
                modelOp.setValorNotaRealNegIndevido(Double.parseDouble(new Moeda().FommatoStringoSomarMil(jFValorNotaRealNegIndevido.getText())));
            } catch (NumberFormatException e) {
                modelOp.setValorNotaRealNegIndevido(zerar);
            }
            try {
                modelOp.setValorNotaRealIndevido(Double.parseDouble(new Moeda().FommatoStringoSomarMil(jFValorNotaRealIndevido.getText())));
            } catch (NumberFormatException e) {
                modelOp.setValorNotaRealIndevido(zerar);
            }
            try {
                modelOp.setValorDespesasTotalRegistro(Double.parseDouble(new Moeda().FommatoStringoSomarMil(jFValorDespesasTotalRegistro.getText())));
            } catch (NumberFormatException e) {
                modelOp.setValorDespesasTotalRegistro(zerar);
            }
            //FIM DOS VALORES DOUBLE

            //INICIO DAS DATAS
            modelOp.setDataProcesso(modelOp.getDataProcesso());
            modelOp.setDataEmissaoNegativa(new Mascaras().DataSalvanosql(jFDataEmissaoNegativa.getText()));
            modelOp.setDataPgtoNegativa(new Mascaras().DataSalvanosql(jFDataPgtoNegativa.getText()));
            modelOp.setDataPrevisaoPgtoNegativa(new Mascaras().DataSalvanosql(jFDataPrevisaoPgtoNegativa.getText()));
            modelOp.setDataEntrada(new Mascaras().DataSalvanosql(jFDataEntrada.getText()));
            modelOp.setDataSaida(new Mascaras().DataSalvanosql(jFDataSaida.getText()));
            modelOp.setDataSinistro(new Mascaras().DataSalvanosql(jFDataSinistro.getText()));
            modelOp.setDataEmissaoNFhonorarios(new Mascaras().DataSalvanosql(jFDataEmissaoNFhonorarios.getText()));
            modelOp.setDataPrevisaoPgtoNFhonorarios(new Mascaras().DataSalvanosql(jFDataPrevisaoPgtoNFhonorarios.getText()));
            modelOp.setDataPgtoNF(new Mascaras().DataSalvanosql(jFDataPgtoNFhonorarios.getText()));
            //FIM DAS DATAS

            modelOp.setNfNegPrejuizo(tfNumeroNFNegativa.getText());
            modelOp.setNumeroSinistro(tfNumSinistro.getText());
            modelOp.setServicosCodigo(Integer.parseInt(this.cbCodServico.getSelectedItem().toString()));
            modelOp.setSeguradoPlaca(tfSeguradoPlaca.getText());
            modelOp.setAnalista(tfAnalista.getText());
            modelOp.setObsHonorariosProcesso(tfObsHonorariosProcesso.getText());
            modelOp.setObsRetencaoAlicotaIndevido(ObsRetencaoAlicotaIndevido.getText());
            modelOp.setObsRetencaoISSNeg(tfObsRetencaoISSNeg.getText());
            modelOp.setIndevidoISS(jRCheckIndevidoISS.isSelected());
            modelOp.setIndevidoISSNegativa(jRCheckIndevidoISSNegativa.isSelected());
            modelOp.setSituacaoPgtoNegativa(cbSituacaoPgtoNegativa.getSelectedItem().toString());

            // modelOp.setServicosCodigo(codigoProcesso);
            // modelOp.setSituacaoPgtoProcesso(controllerTipoPagamento.getFormaPagamentoController(this.cbSituacaoPgtoProcesso.getSelectedItem().toString()).getCodigo());
            // modelOp.setTipoPagamento(controllerTipoPagamento.getFormaPagamentoController(this.cbSituacaoPgtoProcesso.getSelectedItem().toString()).getCodigo());
            modelOp.setCodigo(codigoProcesso);
            listaModelOp.add(modelOp);
            //listaModelServicos.add(modelServicos);
            //}
            modelOp.setListamModelOp(listaModelOp);
            //modelServicos.setListaModelServicos(listaModelServicos);

            //alterar
            if (controllerOp.atualizarOpController(modelOp)) {
                JOptionPane.showMessageDialog(this, "Registro alterado com sucesso");
                ViewOp.op.tipoCadastro = null;
                limpaCampos();
                kGAlterarTerceiro.setVisible(false);
                dispose();
                ViewOp.op.requestFocus();
                op.carregarProcessosReceber();
                op.carregarProcessosRecebidos();
                op.carregarProcessosAguardandoPagamento();
                return true;
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao alterar os dados!", "ERRO", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField ObsRetencaoAlicotaIndevido;
    private javax.swing.JButton btAlterarTerceiro;
    private javax.swing.JButton btAtualizarTerceiro;
    private javax.swing.JButton btCancelar;
    private javax.swing.JButton btCancelarServicos1;
    private javax.swing.JButton btCidadeAdd;
    private javax.swing.JButton btExcluirTerceiro;
    private javax.swing.JButton btSalvarOp;
    private javax.swing.JButton btSalvarTerceiro;
    private javax.swing.JComboBox<String> cbAlicotaNeg;
    private javax.swing.JComboBox<String> cbAlicotaPercentualHonorarios;
    private javax.swing.JComboBox<String> cbCidade;
    private javax.swing.JComboBox cbCodSeguradora;
    private javax.swing.JComboBox<String> cbCodServico;
    private javax.swing.JComboBox cbSeguradoras;
    private componentes.UJComboBox cbServico;
    private javax.swing.JComboBox<String> cbSituacaoPgtoNegativa;
    private javax.swing.JComboBox<String> cbSituacaoPgtoProcesso;
    private javax.swing.JComboBox<String> cbStatusNotaFiscal;
    private javax.swing.JComboBox<String> cbUf;
    private javax.swing.JButton jButtonAddTerceiro;
    private javax.swing.JButton jButtonRemoveTerceiro;
    private javax.swing.JFormattedTextField jFCts;
    private javax.swing.JFormattedTextField jFDataEmissaoNFhonorarios;
    private javax.swing.JFormattedTextField jFDataEmissaoNegativa;
    private javax.swing.JFormattedTextField jFDataEntrada;
    private javax.swing.JFormattedTextField jFDataPgtoNFhonorarios;
    private javax.swing.JFormattedTextField jFDataPgtoNegativa;
    private javax.swing.JFormattedTextField jFDataPrevisaoPgtoNFhonorarios;
    private javax.swing.JFormattedTextField jFDataPrevisaoPgtoNegativa;
    private javax.swing.JFormattedTextField jFDataSaida;
    private javax.swing.JFormattedTextField jFDataSinistro;
    private javax.swing.JFormattedTextField jFHonorarioDefinido;
    private javax.swing.JFormattedTextField jFHoraSinistro;
    private javax.swing.JFormattedTextField jFRetidoReal;
    private javax.swing.JFormattedTextField jFRetidoRealNegativa;
    private javax.swing.JFormattedTextField jFValorDespesasTotalRegistro;
    private javax.swing.JFormattedTextField jFValorHonorariosRetido;
    private javax.swing.JFormattedTextField jFValorNegativaTotal;
    private javax.swing.JFormattedTextField jFValorNotaRealIndevido;
    private javax.swing.JFormattedTextField jFValorNotaRealNegIndevido;
    private javax.swing.JFormattedTextField jFValorPrejuizoNegativa;
    private javax.swing.JFormattedTextField jFValorRetencaoTotalNeg;
    private javax.swing.JFormattedTextField jFValorRetidoRetencaoNeg;
    private javax.swing.JFormattedTextField jFValorTotalHonorariosComRetencao;
    private javax.swing.JLabel jLAlicotaProcessoReal;
    private javax.swing.JLabel jLAlicotaRetencao;
    private javax.swing.JLabel jLAlicotalRealNegativa;
    private javax.swing.JLabel jLAnalista;
    private javax.swing.JLabel jLBairro;
    private javax.swing.JLabel jLCidade;
    private javax.swing.JLabel jLCidadeRetencaoNeg;
    private javax.swing.JLabel jLCidadeTomador;
    private javax.swing.JLabel jLCnpj1;
    private javax.swing.JLabel jLCobertura;
    private javax.swing.JLabel jLCtsRamo;
    private javax.swing.JLabel jLDataEmissaoNFHonorarios;
    private javax.swing.JLabel jLDataEmissaoNegativa;
    private javax.swing.JLabel jLDataEntrada;
    private javax.swing.JLabel jLDataPagamentoHonorarios;
    private javax.swing.JLabel jLDataPgtoNegativa;
    private javax.swing.JLabel jLDataSaida;
    private javax.swing.JLabel jLDataSinistro;
    private javax.swing.JLabel jLDetalhesSinistro;
    private javax.swing.JLabel jLEstado;
    private javax.swing.JLabel jLEstadoTomador;
    private javax.swing.JLabel jLHonorarioDefinido;
    private javax.swing.JLabel jLHonorarioObs;
    private javax.swing.JLabel jLHoraSinistro;
    private javax.swing.JLabel jLNaoPagaNegativa;
    private javax.swing.JLabel jLNaoRetemHonorario;
    private javax.swing.JLabel jLNaoRetemNegativa;
    private javax.swing.JLabel jLNegativaValor;
    private javax.swing.JLabel jLNomeSegurado;
    private javax.swing.JLabel jLNomeSegurado1;
    private javax.swing.JLabel jLNomeSegurado2;
    private javax.swing.JLabel jLNomeSeguradora;
    private javax.swing.JLabel jLNumeroNFHonorarios;
    private javax.swing.JLabel jLNumeroNFNegativa;
    private javax.swing.JLabel jLNumeroProcesso;
    private javax.swing.JLabel jLNumeroSinistro;
    private javax.swing.JLabel jLObsRetencaoNegativa;
    private javax.swing.JLabel jLObsSegurado;
    private javax.swing.JLabel jLObsSegurado1;
    private javax.swing.JLabel jLObsSegurado2;
    private javax.swing.JLabel jLPlacaSegurado;
    private javax.swing.JLabel jLPlacaSegurado1;
    private javax.swing.JLabel jLPlacaSegurado2;
    private javax.swing.JLabel jLPrejuizoValor;
    private javax.swing.JLabel jLPrevPgtoNegativa;
    private javax.swing.JLabel jLPrevisaoPagamentoHonorarios;
    private javax.swing.JLabel jLRetencaoObs;
    private javax.swing.JLabel jLRetencaoValorTotalNeg;
    private javax.swing.JLabel jLRetidoNeg;
    private javax.swing.JLabel jLRetidoReal;
    private javax.swing.JLabel jLRetidoRealNegativa;
    private javax.swing.JLabel jLRetidoRetencao;
    private javax.swing.JLabel jLSituacaoPagamento;
    private javax.swing.JLabel jLSituacaoPagamentoHonorarios;
    private javax.swing.JLabel jLTaxaIssNeg;
    private javax.swing.JLabel jLTextoAbaixoCheckBox;
    private javax.swing.JLabel jLTextoAbaixoCheckBoxIndevidoNegativa;
    private javax.swing.JLabel jLTotalRedito;
    private javax.swing.JLabel jLUfRetencaoNeg;
    private javax.swing.JLabel jLValorNotaIndevido;
    private javax.swing.JLabel jLValorNotaIndevidoNegativa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanelEntradadeProcessos;
    private javax.swing.JPanel jPanelSalvarCancelar;
    private javax.swing.JPanel jPanelSevicosDespesas;
    private javax.swing.JRadioButton jRCheckIndevidoISS;
    private javax.swing.JRadioButton jRCheckIndevidoISSNegativa;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTObsSinistro;
    private javax.swing.JTabbedPane jTPanelProcessos;
    private javax.swing.JFormattedTextField jfCNPJ;
    private keeptoo.KButton kButtonAndamento;
    private keeptoo.KButton kButtonEditarTudo;
    private keeptoo.KButton kButtonLimparDados;
    private keeptoo.KButton kButtonProcessos;
    private keeptoo.KGradientPanel kGAlterarTerceiro;
    private keeptoo.KGradientPanel kGPAplicacaoFixoMeio;
    private keeptoo.KGradientPanel kGPDadosSegurado;
    private keeptoo.KGradientPanel kGPDadosSinistro;
    private keeptoo.KGradientPanel kGPHonorarios;
    private keeptoo.KGradientPanel kGPIncluirTerceiro;
    private keeptoo.KGradientPanel kGPNegativa;
    private keeptoo.KGradientPanel kGPRetencao;
    private keeptoo.KGradientPanel kGPRetencaoNeg;
    private keeptoo.KGradientPanel kGSeguradoraSolicitante;
    private keeptoo.KGradientPanel kGradientPanel1;
    private keeptoo.KGradientPanel kGradientPanel14;
    private keeptoo.KGradientPanel kGradientPanel5;
    private keeptoo.KGradientPanel kGradientPanel6;
    private javax.swing.JTable tbServicos;
    private javax.swing.JTable tbTerceiros;
    private javax.swing.JTextField tfAnalista;
    private javax.swing.JTextField tfBairro;
    private javax.swing.JLabel tfCidadeRetencao;
    private javax.swing.JLabel tfCidadeRetencaoNeg;
    private javax.swing.JTextField tfCodTerceiro;
    private javax.swing.JTextField tfCodigo;
    private javax.swing.JLabel tfEstadoRetencao;
    private javax.swing.JLabel tfEstadoRetencaoNeg;
    private javax.swing.JTextField tfNomeSegurado;
    private javax.swing.JTextField tfNomeTerceiroAlterar;
    private javax.swing.JTextField tfNumSinistro;
    private javax.swing.JTextField tfNumeroNFNegativa;
    private javax.swing.JTextField tfNumeroNFProcesso;
    private javax.swing.JTextField tfObsHonorariosProcesso;
    private javax.swing.JTextField tfObsRetencaoISSNeg;
    private javax.swing.JTextField tfObsSegurado;
    private javax.swing.JTextField tfPercentualRetidoReal;
    private javax.swing.JTextField tfPercentuallRetidoRealNeg;
    private javax.swing.JTextField tfPlacaTerceiroAlteracao;
    private javax.swing.JTextField tfSeguradoPlaca;
    private javax.swing.JTextField tfTerceiroNome;
    private javax.swing.JTextField tfTerceiroObs;
    private javax.swing.JTextField tfTerceiroObsAlteracao;
    private javax.swing.JTextField tfTerceiroPlaca;
    private javax.swing.JTextField tfTipoNF;
    private javax.swing.JTextField tfValorComRetencaoAnexo;
    // End of variables declaration//GEN-END:variables

    private void panelTabbedKGVerficiar() {
        if (jTPanelProcessos.getSelectedIndex() == 0) {
            kButtonProcessos.setSelected(true);
        } else {
            kButtonProcessos.setSelected(false);
        }
        if (jTPanelProcessos.getSelectedIndex() == 1) {
            kButtonAndamento.setSelected(true);
        } else {
            kButtonAndamento.setSelected(false);
        }
    }

    ConexaoBanco cc = new ConexaoBanco();
    Connection cn = cc.conectar();

    private void SetIcone() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("ictrol.png")));
    }
}
