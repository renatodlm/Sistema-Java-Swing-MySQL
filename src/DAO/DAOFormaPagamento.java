package DAO;

import conexao.ConexaoBanco;
import Model.ModelFormaPagamento;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author BLSoft Desenvolvimento de Sistemas
 */
public class DAOFormaPagamento extends ConexaoBanco {

    /**
     * grava FormaPagamento
     *
     * @param pModelFormaPagamento return int Salva a forma de pagamento
     * @return Verifica se foi executado com sucesso
     */
    public int salvarFormaPagamentoDAO(ModelFormaPagamento pModelFormaPagamento) {
        try {
            this.conectar();
            int situacao = 0;
            if (pModelFormaPagamento.isSituacao()) {
                situacao = 1;
            } else {
                situacao = 0;
            }
            return this.insertSQL(
                    "INSERT INTO forma_pagamento ("
                    + "descricao,"
                    + "desconto,"
                    + "quantidade_parcelas,"
                    + "observacao,"
                    + "situacao"
                    + ") VALUES ("
                    + "'" + pModelFormaPagamento.getDescricao() + "',"
                    + "'" + pModelFormaPagamento.getDesconto() + "',"
                    + "'" + pModelFormaPagamento.getQuantidadeParcelas() + "',"
                    + "'" + pModelFormaPagamento.getObservacao() + "',"
                    + "'" + situacao + "'"
                    + ");"
            );
        } catch (Exception e) {
            return 0;
        } finally {
            this.fecharConexao();
        }
    }

    /**
     * recupera FormaPagamento
     *
     * @param pCodigo recupera FormaPagamento
     * @return Verifica se foi executado com sucesso
     */
    public ModelFormaPagamento getFormaPagamentoDAO(int pCodigo) {
        ModelFormaPagamento modelFormaPagamento = new ModelFormaPagamento();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "codigo,"
                    + "descricao,"
                    + "desconto,"
                    + "quantidade_parcelas,"
                    + "observacao,"
                    + "situacao"
                    + " FROM"
                    + " forma_pagamento"
                    + " WHERE"
                    + " codigo = '" + pCodigo + "'"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelFormaPagamento.setCodigo(this.getResultSet().getInt(1));
                modelFormaPagamento.setDescricao(this.getResultSet().getString(2));
                modelFormaPagamento.setDesconto(this.getResultSet().getFloat(3));
                modelFormaPagamento.setQuantidadeParcelas(this.getResultSet().getInt(4));
                modelFormaPagamento.setObservacao(this.getResultSet().getString(5));
                modelFormaPagamento.setSituacao(this.getResultSet().getBoolean(6));
            }
        } catch (SQLException e) {
        } finally {
            this.fecharConexao();
        }
        return modelFormaPagamento;
    }

    /**
     * recupera FormaPagamento
     *
     * @param pDescricao recupera FormaPagamento por descrição
     * @return Verifica se foi realizado com sucesso
     */
    public ModelFormaPagamento getFormaPagamentoDAO(String pDescricao) {
        ModelFormaPagamento modelFormaPagamento = new ModelFormaPagamento();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "codigo,"
                    + "descricao,"
                    + "desconto,"
                    + "quantidade_parcelas,"
                    + "observacao,"
                    + "situacao"
                    + " FROM"
                    + " forma_pagamento"
                    + " WHERE"
                    + " descricao = '" + pDescricao + "'"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelFormaPagamento.setCodigo(this.getResultSet().getInt(1));
                modelFormaPagamento.setDescricao(this.getResultSet().getString(2));
                modelFormaPagamento.setDesconto(this.getResultSet().getFloat(3));
                modelFormaPagamento.setQuantidadeParcelas(this.getResultSet().getInt(4));
                modelFormaPagamento.setObservacao(this.getResultSet().getString(5));
                modelFormaPagamento.setSituacao(this.getResultSet().getBoolean(6));
            }
        } catch (SQLException e) {
        } finally {
            this.fecharConexao();
        }
        return modelFormaPagamento;
    }

    /**
     * recupera uma lista de FormaPagamento return ArrayList
     *
     * @return Verifica se foi relaizado com sucesso a listagem de formas de
     * pagamento
     */
    public ArrayList<ModelFormaPagamento> getListaFormaPagamentoDAO() {
        ArrayList<ModelFormaPagamento> listamodelFormaPagamento = new ArrayList();
        ModelFormaPagamento modelFormaPagamento = new ModelFormaPagamento();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "codigo,"
                    + "descricao,"
                    + "desconto,"
                    + "quantidade_parcelas,"
                    + "observacao,"
                    + "situacao"
                    + " FROM"
                    + " forma_pagamento"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelFormaPagamento = new ModelFormaPagamento();
                modelFormaPagamento.setCodigo(this.getResultSet().getInt(1));
                modelFormaPagamento.setDescricao(this.getResultSet().getString(2));
                modelFormaPagamento.setDesconto(this.getResultSet().getFloat(3));
                modelFormaPagamento.setQuantidadeParcelas(this.getResultSet().getInt(4));
                modelFormaPagamento.setObservacao(this.getResultSet().getString(5));
                modelFormaPagamento.setSituacao(this.getResultSet().getBoolean(6));
                listamodelFormaPagamento.add(modelFormaPagamento);
            }
        } catch (SQLException e) {
        } finally {
            this.fecharConexao();
        }
        return listamodelFormaPagamento;
    }

    /**
     * atualiza FormaPagamento
     *
     * @param pModelFormaPagamento return boolean
     * @return Atualiza a forma de pagmento
     */
    public boolean atualizarFormaPagamentoDAO(ModelFormaPagamento pModelFormaPagamento) {
        try {
            this.conectar();
            int situacao = 0;
            if (pModelFormaPagamento.isSituacao()) {
                situacao = 1;
            } else {
                situacao = 0;
            }
            return this.executarUpdateDeleteSQL(
                    "UPDATE forma_pagamento SET "
                    + "codigo = '" + pModelFormaPagamento.getCodigo() + "',"
                    + "descricao = '" + pModelFormaPagamento.getDescricao() + "',"
                    + "desconto = '" + pModelFormaPagamento.getDesconto() + "',"
                    + "quantidade_parcelas = '" + pModelFormaPagamento.getQuantidadeParcelas() + "',"
                    + "observacao = '" + pModelFormaPagamento.getObservacao() + "',"
                    + "situacao = '" + situacao + "'"
                    + " WHERE "
                    + "codigo = '" + pModelFormaPagamento.getCodigo() + "'"
                    + ";"
            );
        } catch (Exception e) {
            return false;
        } finally {
            this.fecharConexao();
        }
    }

    /**
     * exclui FormaPagamento
     *
     * @param pCodigo rexclui FormaPagamento
     * @return Verifica se foi realizado exclusão com sucesso
     */
    public boolean excluirFormaPagamentoDAO(int pCodigo) {
        try {
            this.conectar();
            return this.executarUpdateDeleteSQL(
                    "DELETE FROM forma_pagamento "
                    + " WHERE "
                    + "codigo = '" + pCodigo + "'"
                    + ";"
            );
        } catch (Exception e) {
            return false;
        } finally {
            this.fecharConexao();
        }
    }
}
