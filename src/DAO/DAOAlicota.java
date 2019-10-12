/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import conexao.ConexaoBanco;
import Model.ModelAlicota;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Work
 */
public class DAOAlicota extends ConexaoBanco {

    public int salvarAlicotaDAO(ModelAlicota pModelAlicota) {
        try {
            this.conectar();
            return this.insertSQL(
                    "INSERT INTO alicota ("
                    + "ali_iss,"
                    + "ali_data"
                    + ") VALUES ("
                    + "'" + pModelAlicota.getAli_iss() + "',"
                    + "'" + pModelAlicota.getAli_data() + "'"
                    + ");"
            );
        } catch (Exception e) {
            return 0;
        } finally {
            this.fecharConexao();
        }
    }

    /**
     * recupera Alicota
     *
     * @param pCodigo Retorna Alicota por codigo
     * @return Verifica se os Alicota retornaram com sucesso
     */
    public ModelAlicota getAlicotaDAO(int pCodigo) {
        ModelAlicota modelAlicota = new ModelAlicota();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "codigo,"
                    + "ali_iss,"
                    + "ali_data"
                    + " FROM"
                    + " alicota"
                    + " WHERE"
                    + " codigo = '" + pCodigo + "'"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelAlicota.setCodigo(this.getResultSet().getInt(1));
                modelAlicota.setAli_iss(this.getResultSet().getDouble(2));
                modelAlicota.setAli_data(this.getResultSet().getString(3));

            }
        } catch (SQLException e) {
        } finally {
            this.fecharConexao();
        }
        return modelAlicota;
    }

    /**
     * recupera Alicotas
     *
     * @param pIss Retorna Alicota por Iss
     * @return verifica se foi realizado com sucesso
     */
    public ModelAlicota getAlicotaDAO(String pIss) {
        ModelAlicota modelAlicota = new ModelAlicota();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "codigo,"
                    + "ali_iss,"
                    + "ali_data "
                    + " FROM"
                    + " alicota"
                    + " WHERE"
                    + " ali_iss = '" + pIss + "'"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelAlicota.setCodigo(this.getResultSet().getInt(1));
                modelAlicota.setAli_iss(this.getResultSet().getDouble(2));
                modelAlicota.setAli_data(this.getResultSet().getString(3));
            }
        } catch (SQLException e) {
        } finally {
            this.fecharConexao();
        }
        return modelAlicota;
    }

    /**
     * recupera uma lista de Alicota return ArrayList
     *
     * @return verifica se recuperou a lista de Alicota com sucesso
     */
    public ArrayList<ModelAlicota> getListaAlicotaDAO() {
        ArrayList<ModelAlicota> listaAlicota = new ArrayList();
        ModelAlicota modelAlicota = new ModelAlicota();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "codigo,"
                    + "ali_iss,"
                    + "ali_data"
                    + " FROM"
                    + " alicota"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelAlicota = new ModelAlicota();
                modelAlicota.setCodigo(this.getResultSet().getInt(1));
                modelAlicota.setAli_iss(this.getResultSet().getDouble(2));
                modelAlicota.setAli_data(this.getResultSet().getString(3));
                listaAlicota.add(modelAlicota);
            }
        } catch (SQLException e) {
        } finally {
            this.fecharConexao();
        }
        return listaAlicota;
    }

    /**
     * atualiza Alicota
     *
     * @param pModelAlicota atualiza Alicota
     * @return verifica se atualizou o Alicota com sucesso
     */
    public boolean atualizarAlicotaDAO(ModelAlicota pModelAlicota) {
        try {
            this.conectar();
            this.executarUpdateDeleteSQL(
                    "UPDATE alicota SET "
                    + "codigo = '" + pModelAlicota.getCodigo() + "',"
                    + "ali_iss = '" + pModelAlicota.getAli_iss() + "',"
                    + "ali_data = '" + pModelAlicota.getAli_data() + "'"
                    + " WHERE "
                    + "codigo = '" + pModelAlicota.getCodigo() + "'"
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
     * Exclui Alicota
     *
     * @param pCodigo Exclui Alicota
     * @return Verifica se foi excluido com sucesso
     */
    public boolean excluirAlicotaDAO(int pCodigo) {
        try {
            this.conectar();
            return this.executarUpdateDeleteSQL(
                    "DELETE FROM alicota "
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
