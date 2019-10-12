/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.ModelUsuario;
import conexao.ConexaoBanco;
import java.sql.SQLException;

/**
 *
 * @author studiomicroweb
 */
public class DAOUsuario extends ConexaoBanco {

    /**
     * recupera Usuario
     *
     * @param pCodigo recupera Usuario por codigo
     * @return verifica se foi realizado com sucesso
     */
    public ModelUsuario getUsuarioDAO(int pCodigo) {
        ModelUsuario modelUsuario = new ModelUsuario();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "pk_codigo,"
                    + "nome,"
                    + "login,"
                    + "senha"
                    + " FROM"
                    + " tbl_usuario"
                    + " WHERE"
                    + " pk_codigo = '" + pCodigo + "'"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelUsuario.setCodigo(this.getResultSet().getInt(1));
                modelUsuario.setNome(this.getResultSet().getString(2));
                modelUsuario.setLogin(this.getResultSet().getString(3));
                modelUsuario.setSenha(this.getResultSet().getString(4));
            }
        } catch (SQLException e) {
        } finally {
            this.fecharConexao();
        }
        return modelUsuario;
    }

    /**
     * recupera Usuario
     *
     * @param pLogin recupera Usuario por login
     * @return Verifica se realizou com sucesso
     */
    public ModelUsuario getUsuarioDAO(String pLogin) {
        ModelUsuario modelUsuario = new ModelUsuario();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "pk_codigo,"
                    + "nome,"
                    + "login,"
                    + "senha,"
                    + "permissao"
                    + " FROM"
                    + " tbl_usuario"
                    + " WHERE"
                    + " login = '" + pLogin + "'"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelUsuario.setCodigo(this.getResultSet().getInt(1));
                modelUsuario.setNome(this.getResultSet().getString(2));
                modelUsuario.setLogin(this.getResultSet().getString(3));
                modelUsuario.setSenha(this.getResultSet().getString(4));
                modelUsuario.setPermissao(this.getResultSet().getInt(5));
            }
        } catch (SQLException e) {
        } finally {
            this.fecharConexao();
        }
        return modelUsuario;
    }

    /**
     * recupera Usuario
     *
     * @param pModelUsuario recupera Usuario
     * @return Verifica se foi realizado com sucesso
     */
    public boolean getUsuarioDAO(ModelUsuario pModelUsuario) {
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "pk_codigo,"
                    + "nome,"
                    + "login,"
                    + "senha"
                    + " FROM"
                    + " tbl_usuario"
                    + " WHERE"
                    + " login = '" + pModelUsuario.getLogin() + "' AND senha = '" + pModelUsuario.getSenha() + "' "
                    + ";"
            );

            return getResultSet().next();
        } catch (SQLException e) {
            return false;
        } finally {
            this.fecharConexao();
        }
    }

}
