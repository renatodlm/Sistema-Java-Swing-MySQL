/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.ModelTipoDespesas;
import conexao.ConexaoBanco;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Work
 */
public class DAOTipoDespesas extends ConexaoBanco {

    public int salvarTipoDespesasDAO(ModelTipoDespesas pModelTipoDespesas) {
        try {
            this.conectar();
            return this.insertSQL(
                    "INSERT INTO tipodespesas ("
                    + "cober_nome,"
                    + "cober_valor"
                    + ") VALUES ("
                    + "'" + pModelTipoDespesas.getNome() + "',"
                    + "'" + pModelTipoDespesas.getValor() + "'"
                    + ");"
            );
        } catch (Exception e) {
            return 0;
        } finally {
            this.fecharConexao();
        }
    }

    /**
     * recupera TipoDespesas
     *
     * @param pCodigo Retorna TipoDespesas por codigo
     * @return Verifica se os TipoDespesas retornaram com sucesso
     */
    public ModelTipoDespesas getTipoDespesasDAO(int pCodigo) {
        ModelTipoDespesas modelTipoDespesas = new ModelTipoDespesas();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "codigo,"
                    + "cober_nome,"
                    + "cober_valor"
                    + " FROM"
                    + " tipodespesas"
                    + " WHERE"
                    + " codigo = '" + pCodigo + "'"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelTipoDespesas.setCodigo(this.getResultSet().getInt(1));
                modelTipoDespesas.setNome(this.getResultSet().getString(2));
                modelTipoDespesas.setValor(this.getResultSet().getDouble(3));

            }
        } catch (SQLException e) {
        } finally {
            this.fecharConexao();
        }
        return modelTipoDespesas;
    }

    /**
     * recupera TipoDespesas
     *
     * @param pNome Retorna TipoDespesaspor pNome
     * @return verifica se foi realizado com sucesso
     */
    public ModelTipoDespesas getTipoDespesasDAO(String pNome) {
        ModelTipoDespesas modelTipoDespesas = new ModelTipoDespesas();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "codigo,"
                    + "cober_nome,"
                    + "cober_valor "
                    + " FROM"
                    + " tipodespesas"
                    + " WHERE"
                    + " cober_nome = '" + pNome + "'"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelTipoDespesas.setCodigo(this.getResultSet().getInt(1));
                modelTipoDespesas.setNome(this.getResultSet().getString(2));
                modelTipoDespesas.setValor(this.getResultSet().getDouble(3));
            }
        } catch (SQLException e) {
        } finally {
            this.fecharConexao();
        }
        return modelTipoDespesas;
    }

    /**
     * recupera uma lista de TipoDespesas return ArrayList
     *
     * @return verifica se recuperou a lista de TipoDespesas com sucesso
     */
    public ArrayList<ModelTipoDespesas> getListaTipoDespesasDAO() {
        ArrayList<ModelTipoDespesas> listaTipoDespesas = new ArrayList();
        ModelTipoDespesas modelTipoDespesas = new ModelTipoDespesas();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "codigo,"
                    + "cober_nome,"
                    + "cober_valor"
                    + " FROM"
                    + " tipodespesas"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelTipoDespesas = new ModelTipoDespesas();
                modelTipoDespesas.setCodigo(this.getResultSet().getInt(1));
                modelTipoDespesas.setNome(this.getResultSet().getString(2));
                modelTipoDespesas.setValor(this.getResultSet().getDouble(3));
                listaTipoDespesas.add(modelTipoDespesas);
            }
        } catch (SQLException e) {
        } finally {
            this.fecharConexao();
        }
        return listaTipoDespesas;
    }

    /**
     * atualiza TipoDespesas
     *
     * @param pModelTipoDespesas atualiza TipoDespesas
     * @return verifica se atualizou o TipoDespesas com sucesso
     */
    public boolean atualizarTipoDespesasDAO(ModelTipoDespesas pModelTipoDespesas) {
        try {
            this.conectar();
            this.executarUpdateDeleteSQL(
                    "UPDATE tipodespesas SET "
                    + "codigo = '" + pModelTipoDespesas.getCodigo() + "',"
                    + "cober_nome = '" + pModelTipoDespesas.getNome() + "',"
                    + "cober_valor = '" + pModelTipoDespesas.getValor() + "'"
                    + " WHERE "
                    + "codigo = '" + pModelTipoDespesas.getCodigo() + "'"
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
     * Exclui TipoDespesas
     *
     * @param pCodigo Exclui TipoDespesas
     * @return Verifica se foi excluido com sucesso
     */
    public boolean excluirTipoDespesasDAO(int pCodigo) {
        try {
            this.conectar();
            return this.executarUpdateDeleteSQL(
                    "DELETE FROM tipodespesas "
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
