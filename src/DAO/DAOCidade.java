/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.ModelCidade;
import conexao.ConexaoBanco;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author studiomicroweb
 */
public class DAOCidade extends ConexaoBanco {

    /**
     * grava Cidade
     *
     * @param pModelCidade return int
     * @return return int
     */
    public int salvarCidadeDAO(ModelCidade pModelCidade) {
        try {
            this.conectar();
            return this.insertSQL(
                    "INSERT INTO cidade ("
                    + "nome,"
                    + "fk_cod_estado"
                    + ") VALUES ("
                    + "'" + pModelCidade.getNome() + "',"
                    + "'" + pModelCidade.getFk_cod_estado() + "'"
                    + ");"
            );
        } catch (Exception e) {
            return 0;
        } finally {
            this.fecharConexao();
        }
    }

    /**
     * recupera Cidade
     *
     * @param pCodigo return ModelCidade
     * @return Retorna Lista de Cidades por codigo
     */
    public ModelCidade getCidadeDAO(int pCodigo) {
        ModelCidade modelCidade = new ModelCidade();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "codigo,"
                    + "nome,"
                    + "fk_cod_estado"
                    + " FROM"
                    + " cidade"
                    + " WHERE"
                    + " codigo = '" + pCodigo + "'"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelCidade.setCodigo(this.getResultSet().getInt(1));
                modelCidade.setNome(this.getResultSet().getString(2));
                modelCidade.setFk_cod_estado(this.getResultSet().getInt(3));
            }
        } catch (SQLException e) {
        } finally {
            this.fecharConexao();
        }
        return modelCidade;
    }

    /**
     * recupera Cidade
     *
     * @param pNome return ModelCidade
     * @return Retorna Listas de Cidade por estado
     */
    public ModelCidade getCidadeDAO(String pNome) {
        ModelCidade modelCidade = new ModelCidade();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "codigo,"
                    + "nome,"
                    + "fk_cod_estado"
                    + " FROM"
                    + " cidade"
                    + " WHERE"
                    + " nome = '" + pNome + "'"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelCidade.setCodigo(this.getResultSet().getInt(1));
                modelCidade.setNome(this.getResultSet().getString(2));
                modelCidade.setFk_cod_estado(this.getResultSet().getInt(3));
            }
        } catch (SQLException e) {
        } finally {
            this.fecharConexao();
        }
        return modelCidade;
    }

    /**
     * recupera uma lista de Cidade return ArrayList
     *
     * @return Salva Cidades no Banco de acordo com o Estado
     */
    public ArrayList<ModelCidade> getListaCidadeDAO() {
        ArrayList<ModelCidade> listamodelCidade = new ArrayList();
        ModelCidade modelCidade = new ModelCidade();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "codigo,"
                    + "nome,"
                    + "fk_cod_estado"
                    + " FROM"
                    + " cidade"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelCidade = new ModelCidade();
                modelCidade.setCodigo(this.getResultSet().getInt(1));
                modelCidade.setNome(this.getResultSet().getString(2));
                modelCidade.setFk_cod_estado(this.getResultSet().getInt(3));
                listamodelCidade.add(modelCidade);
            }
        } catch (SQLException e) {
        } finally {
            this.fecharConexao();
        }
        return listamodelCidade;
    }

    /**
     * recupera uma lista de Cidade return ArrayList
     *
     * @param pCodigoEstado Retorna Lista de Cidades
     * @return Relaciona a cidade ao Estado correspondente
     */
    public ArrayList<ModelCidade> getListaCidadePorEstadoDAO(int pCodigoEstado) {
        ArrayList<ModelCidade> listamodelCidade = new ArrayList();
        ModelCidade modelCidade = new ModelCidade();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "codigo,"
                    + "nome,"
                    + "fk_cod_estado"
                    + " FROM"
                    + " cidade"
                    + " where fk_cod_estado = '" + pCodigoEstado + "';"
            );

            while (this.getResultSet().next()) {
                modelCidade = new ModelCidade();
                modelCidade.setCodigo(this.getResultSet().getInt(1));
                modelCidade.setNome(this.getResultSet().getString(2));
                modelCidade.setFk_cod_estado(this.getResultSet().getInt(3));
                listamodelCidade.add(modelCidade);
            }
        } catch (SQLException e) {
        } finally {
            this.fecharConexao();
        }
        return listamodelCidade;
    }

    /**
     * atualiza Cidade
     *
     * @param pModelCidade return boolean
     * @return Atualiza as Cidades
     */
    public boolean atualizarCidadeDAO(ModelCidade pModelCidade) {
        try {
            this.conectar();
            return this.executarUpdateDeleteSQL(
                    "UPDATE cidade SET "
                    + "codigo = '" + pModelCidade.getCodigo() + "',"
                    + "nome = '" + pModelCidade.getNome() + "',"
                    + "fk_cod_estado = '" + pModelCidade.getFk_cod_estado() + "'"
                    + " WHERE "
                    + "codigo = '" + pModelCidade.getCodigo() + "'"
                    + ";"
            );
        } catch (Exception e) {
            return false;
        } finally {
            this.fecharConexao();
        }
    }

    /**
     * exclui Cidade
     *
     * @param pCodigo return boolean
     * @return Exclui as cidades
     */
    public boolean excluirCidadeDAO(int pCodigo) {
        try {
            this.conectar();
            return this.executarUpdateDeleteSQL(
                    "DELETE FROM cidade "
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
