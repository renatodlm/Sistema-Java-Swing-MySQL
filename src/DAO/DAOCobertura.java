/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.ModelCobertura;
import conexao.ConexaoBanco;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Work
 */
public class DAOCobertura extends ConexaoBanco {

    public int salvarCoberturaDAO(ModelCobertura pModelCobertura) {
        try {
            this.conectar();
            return this.insertSQL(
                    "INSERT INTO cobertura ("
                    + "cober_nome,"
                    + "cober_valor"
                    + ") VALUES ("
                    + "'" + pModelCobertura.getCober_nome() + "',"
                    + "'" + pModelCobertura.getCober_valor() + "'"
                    + ");"
            );
        } catch (Exception e) {
            return 0;
        } finally {
            this.fecharConexao();
        }
    }

    /**
     * recupera Cobertura
     *
     * @param pCodigo Retorna Cobertura por codigo
     * @return Verifica se os Cobertura retornaram com sucesso
     */
    public ModelCobertura getCoberturaDAO(int pCodigo) {
        ModelCobertura modelCobertura = new ModelCobertura();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "codigo,"
                    + "cober_nome,"
                    + "cober_valor"
                    + " FROM"
                    + " cobertura"
                    + " WHERE"
                    + " codigo = '" + pCodigo + "'"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelCobertura.setCodigo(this.getResultSet().getInt(1));
                modelCobertura.setCober_nome(this.getResultSet().getString(2));
                modelCobertura.setCober_valor(this.getResultSet().getDouble(3));

            }
        } catch (SQLException e) {
        } finally {
            this.fecharConexao();
        }
        return modelCobertura;
    }

    /**
     * recupera Cobertura
     *
     * @param pNome Retorna Coberturapor pNome
     * @return verifica se foi realizado com sucesso
     */
    public ModelCobertura getCoberturaDAO(String pNome) {
        ModelCobertura modelCobertura = new ModelCobertura();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "codigo,"
                    + "cober_nome,"
                    + "cober_valor "
                    + " FROM"
                    + " cobertura"
                    + " WHERE"
                    + " cober_nome = '" + pNome + "'"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelCobertura.setCodigo(this.getResultSet().getInt(1));
                modelCobertura.setCober_nome(this.getResultSet().getString(2));
                modelCobertura.setCober_valor(this.getResultSet().getDouble(3));
            }
        } catch (SQLException e) {
        } finally {
            this.fecharConexao();
        }
        return modelCobertura;
    }

    /**
     * recupera uma lista de Cobertura return ArrayList
     *
     * @return verifica se recuperou a lista de Cobertura com sucesso
     */
    public ArrayList<ModelCobertura> getListaCoberturaDAO() {
        ArrayList<ModelCobertura> listaCobertura = new ArrayList();
        ModelCobertura modelCobertura = new ModelCobertura();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "codigo,"
                    + "cober_nome,"
                    + "cober_valor"
                    + " FROM"
                    + " cobertura"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelCobertura = new ModelCobertura();
                modelCobertura.setCodigo(this.getResultSet().getInt(1));
                modelCobertura.setCober_nome(this.getResultSet().getString(2));
                modelCobertura.setCober_valor(this.getResultSet().getDouble(3));
                listaCobertura.add(modelCobertura);
            }
        } catch (SQLException e) {
        } finally {
            this.fecharConexao();
        }
        return listaCobertura;
    }

    /**
     * atualiza Cobertura
     *
     * @param pModelCobertura atualiza Cobertura
     * @return verifica se atualizou o Cobertura com sucesso
     */
    public boolean atualizarCoberturaDAO(ModelCobertura pModelCobertura) {
        try {
            this.conectar();
            this.executarUpdateDeleteSQL(
                    "UPDATE cobertura SET "
                    + "codigo = '" + pModelCobertura.getCodigo() + "',"
                    + "cober_nome = '" + pModelCobertura.getCober_nome() + "',"
                    + "cober_valor = '" + pModelCobertura.getCober_valor() + "'"
                    + " WHERE "
                    + "codigo = '" + pModelCobertura.getCodigo() + "'"
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
     * Exclui Cobertura
     *
     * @param pCodigo Exclui Cobertura
     * @return Verifica se foi excluido com sucesso
     */
    public boolean excluirCoberturaDAO(int pCodigo) {
        try {
            this.conectar();
            return this.executarUpdateDeleteSQL(
                    "DELETE FROM cobertura "
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
