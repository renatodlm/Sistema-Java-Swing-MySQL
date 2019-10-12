/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.ModelEstado;
import conexao.ConexaoBanco;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author studiomicroweb
 */
public class DAOEstado extends ConexaoBanco {

    /**
     * grava Estado
     *
     * @param pModelEstado Salva os Estados no Banco de dados
     * @return Verifica se foi executado com sucesso
     */
    public int salvarEstadoDAO(ModelEstado pModelEstado) {
        try {
            this.conectar();
            return this.insertSQL(
                    "INSERT INTO estado ("
                    + "codigo,"
                    + "uf,"
                    + "nome"
                    + ") VALUES ("
                    + "'" + pModelEstado.getCodigo() + "',"
                    + "'" + pModelEstado.getUf() + "',"
                    + "'" + pModelEstado.getNome() + "'"
                    + ");"
            );
        } catch (Exception e) {
            return 0;
        } finally {
            this.fecharConexao();
        }
    }

    /**
     * recupera Estado
     *
     * @param Codigo return ModelEstado
     * @return Verifica se foi executado com sucesso
     */
    public ModelEstado getEstadoDAO(int Codigo) {
        ModelEstado modelEstado = new ModelEstado();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "codigo,"
                    + "uf,"
                    + "nome"
                    + " FROM"
                    + " estado"
                    + " WHERE"
                    + " codigo = '" + Codigo + "'"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelEstado.setCodigo(this.getResultSet().getInt(1));
                modelEstado.setUf(this.getResultSet().getString(2));
                modelEstado.setNome(this.getResultSet().getString(3));
            }
        } catch (SQLException e) {
        } finally {
            this.fecharConexao();
        }
        return modelEstado;
    }

    /**
     * recupera Estado
     *
     * @param Nome return ModelEstado
     * @return Verifica se foi executado com sucesso
     */
    public ModelEstado getEstadoDAO(String Nome) {
        ModelEstado modelEstado = new ModelEstado();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "codigo,"
                    + "uf,"
                    + "nome"
                    + " FROM"
                    + " estado"
                    + " WHERE"
                    + " nome = '" + Nome + "';"
            );
            while (this.getResultSet().next()) {
                modelEstado.setCodigo(this.getResultSet().getInt(1));
                modelEstado.setUf(this.getResultSet().getString(2));
                modelEstado.setNome(this.getResultSet().getString(3));
            }
        } catch (SQLException e) {
        } finally {
            this.fecharConexao();
        }
        return modelEstado;
    }

    /**
     * recupera Estado
     *
     * @param Nome Recupera lista de estados por nome
     * @return Verifica se foi executado com sucesso
     */
    public ModelEstado getEstadoUFDAO(String Nome) {
        ModelEstado modelEstado = new ModelEstado();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "codigo,"
                    + "uf,"
                    + "nome"
                    + " FROM"
                    + " estado"
                    + " WHERE"
                    + " uf = '" + Nome + "';"
            );
            while (this.getResultSet().next()) {
                modelEstado.setCodigo(this.getResultSet().getInt(1));
                modelEstado.setUf(this.getResultSet().getString(2));
                modelEstado.setNome(this.getResultSet().getString(3));
            }
        } catch (SQLException e) {
        } finally {
            this.fecharConexao();
        }
        return modelEstado;
    }

    /**
     * recupera uma lista de Estado return ArrayList
     *
     * @return Verifica se foi executado com sucesso
     */
    public ArrayList<ModelEstado> getListaEstadoDAO() {
        ArrayList<ModelEstado> listamodelEstado = new ArrayList();
        ModelEstado modelEstado = new ModelEstado();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "codigo,"
                    + "uf,"
                    + "nome"
                    + " FROM"
                    + " estado"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelEstado = new ModelEstado();
                modelEstado.setCodigo(this.getResultSet().getInt(1));
                modelEstado.setUf(this.getResultSet().getString(2));
                modelEstado.setNome(this.getResultSet().getString(3));
                listamodelEstado.add(modelEstado);
            }
        } catch (SQLException e) {
        } finally {
            this.fecharConexao();
        }
        return listamodelEstado;
    }

    /**
     * atualiza Estado
     *
     * @param ModelEstado Atualiza o Estado
     * @return Verifica se foi executado com sucesso
     */
    public boolean atualizarEstadoDAO(ModelEstado ModelEstado) {
        try {
            this.conectar();
            return this.executarUpdateDeleteSQL(
                    "UPDATE estado SET "
                    + "codigo = '" + ModelEstado.getCodigo() + "',"
                    + "uf = '" + ModelEstado.getUf() + "',"
                    + "nome = '" + ModelEstado.getNome() + "'"
                    + " WHERE "
                    + "codigo = '" + ModelEstado.getCodigo() + "'"
                    + ";"
            );
        } catch (Exception e) {
            return false;
        } finally {
            this.fecharConexao();
        }
    }

    /**
     * exclui Estado
     *
     * @param Codigo Exclui o Estado
     * @return Verifica se foi executado com sucesso
     */
    public boolean excluirEstadoDAO(int Codigo) {
        try {
            this.conectar();
            return this.executarUpdateDeleteSQL(
                    "DELETE FROM estado "
                    + " WHERE "
                    + "codigo = '" + Codigo + "'"
                    + ";"
            );
        } catch (Exception e) {
            return false;
        } finally {
            this.fecharConexao();
        }
    }

}
