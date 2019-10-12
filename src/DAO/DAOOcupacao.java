/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.ModelOcupacao;
import conexao.ConexaoBanco;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author studiomicroweb
 */
public class DAOOcupacao extends ConexaoBanco {

    public int salvarOcupacaoDAO(ModelOcupacao pModelOcupacao) {
        try {
            this.conectar();
            return this.insertSQL(
                    "INSERT INTO ocupacao ("
                    + "ocup_descricao,"
                    + "cod_permissao"
                    + ") VALUES ("
                    + "'" + pModelOcupacao.getOcup_descricao() + "',"
                    + "'" + pModelOcupacao.getCod_permissao() + "',"
                    + ");"
            );
        } catch (Exception e) {
            return 0;
        } finally {
            this.fecharConexao();
        }
    }

    /**
     * recupera Ocupacao
     *
     * @param pCodigo Retorna Ocupacao por codigo
     * @return Verifica se os Ocupacao retornaram com sucesso
     */
    public ModelOcupacao getOcupacaoDAO(int pCodigo) {
        ModelOcupacao modelOcupacao = new ModelOcupacao();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "codigo,"
                    + "ocup_descricao,"
                    + "cod_permissao"
                    + " FROM"
                    + " ocupacao"
                    + " WHERE"
                    + " codigo = '" + pCodigo + "'"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelOcupacao.setCodigo(this.getResultSet().getInt(1));
                modelOcupacao.setOcup_descricao(this.getResultSet().getString(2));
                modelOcupacao.setCod_permissao(this.getResultSet().getInt(3));

            }
        } catch (SQLException e) {
        } finally {
            this.fecharConexao();
        }
        return modelOcupacao;
    }

    /**
     * recupera Ocupacao
     *
     * @param pOcupacao_nome Retorna Ocupacao por nome
     * @return verifica se foi realizado com sucesso
     */
    public ModelOcupacao getOcupacaoDAO(String pOcupacao_nome) {
        ModelOcupacao modelOcupacao = new ModelOcupacao();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "codigo,"
                    + "ocup_descricao,"
                    + "cod_permissao"
                    + " FROM"
                    + " ocupacao"
                    + " WHERE"
                    + " ocup_descricao = '" + pOcupacao_nome + "'"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelOcupacao.setCodigo(this.getResultSet().getInt(1));
                modelOcupacao.setOcup_descricao(this.getResultSet().getString(2));
                modelOcupacao.setCod_permissao(this.getResultSet().getInt(3));
            }
        } catch (SQLException e) {
        } finally {
            this.fecharConexao();
        }
        return modelOcupacao;
    }

    /**
     * recupera uma lista de Ocupacao
     *
     * @return verifica se recuperou a lista de Ocupacao com sucesso
     */
    public ArrayList<ModelOcupacao> getListaOcupacaoDAO() {
        ArrayList<ModelOcupacao> listamodelOcupacao = new ArrayList();
        ModelOcupacao modelOcupacao = new ModelOcupacao();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "codigo,"
                    + "ocup_descricao,"
                    + "cod_permissao"
                    + " FROM"
                    + " ocupacao"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelOcupacao = new ModelOcupacao();
                modelOcupacao.setCodigo(this.getResultSet().getInt(1));
                modelOcupacao.setOcup_descricao(this.getResultSet().getString(2));
                modelOcupacao.setCod_permissao(this.getResultSet().getInt(3));
                listamodelOcupacao.add(modelOcupacao);
            }
        } catch (SQLException e) {
        } finally {
            this.fecharConexao();
        }
        return listamodelOcupacao;
    }

    /**
     * atualiza Ocupacao
     *
     * @param pModelOcupacao atualiza Ocupacao
     * @return verifica se atualizou o Ocupacao com sucesso
     */
    public boolean atualizarOcupacaoDAO(ModelOcupacao pModelOcupacao) {
        try {
            this.conectar();
            this.executarUpdateDeleteSQL(
                    "UPDATE ocupacao SET "
                    + "codigo = '" + pModelOcupacao.getCodigo() + "',"
                    + "ocup_descricao = '" + pModelOcupacao.getOcup_descricao() + "',"
                    + "cod_permissao = '" + pModelOcupacao.getCod_permissao() + "',"
                    + " WHERE "
                    + "codigo = '" + pModelOcupacao.getCodigo() + "'"
                    + ";"
            );
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            this.fecharConexao();
        }
    }

    /**
     * Exclui Ocupacao
     *
     * @param pCodigo Exclui Ocupacao
     * @return Verifica se foi excluido com sucesso
     */
    public boolean excluirOcupacaoDAO(int pCodigo) {
        try {
            this.conectar();
            return this.executarUpdateDeleteSQL(
                    "DELETE FROM ocupacao "
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
