/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.ModelBancos;
import conexao.ConexaoBanco;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author studiomicroweb
 */
public class DAOBancos extends ConexaoBanco {

    public int salvarBancosDAO(ModelBancos pModelBancos) {
        try {
            this.conectar();
            return this.insertSQL(
                    "INSERT INTO bancos ("
                    + "banco_nome,"
                    + "banco_num"
                    + ") VALUES ("
                    + "'" + pModelBancos.getBanco_nome() + "',"
                    + "'" + pModelBancos.getBanco_num() + "'"
                    + ");"
            );
        } catch (Exception e) {
            return 0;
        } finally {
            this.fecharConexao();
        }
    }

    /**
     * recupera Bancos
     *
     * @param pCodigo Retorna Bancos por codigo
     * @return Verifica se os Bancos retornaram com sucesso
     */
    public ModelBancos getBancosDAO(int pCodigo) {
        ModelBancos modelBancos = new ModelBancos();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "codigo,"
                    + "banco_nome,"
                    + "banco_num "
                    + " FROM"
                    + " bancos"
                    + " WHERE"
                    + " codigo = '" + pCodigo + "'"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelBancos.setCodigo(this.getResultSet().getInt(1));
                modelBancos.setBanco_nome(this.getResultSet().getString(2));
                modelBancos.setBanco_num(this.getResultSet().getString(3));

            }
        } catch (SQLException e) {
        } finally {
            this.fecharConexao();
        }
        return modelBancos;
    }

    /**
     * recupera Bancos
     *
     * @param pBanco_nome Retorna Bancos por nome
     * @return verifica se foi realizado com sucesso
     */
    public ModelBancos getBancosDAO(String pBanco_nome) {
        ModelBancos modelBancos = new ModelBancos();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "codigo,"
                    + "banco_nome,"
                    + "banco_num "
                    + " FROM"
                    + " bancos"
                    + " WHERE"
                    + " banco_nome = '" + pBanco_nome + "'"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelBancos.setCodigo(this.getResultSet().getInt(1));
                modelBancos.setBanco_nome(this.getResultSet().getString(2));
                modelBancos.setBanco_num(this.getResultSet().getString(3));
            }
        } catch (SQLException e) {
        } finally {
            this.fecharConexao();
        }
        return modelBancos;
    }

    /**
     * recupera uma lista de Bancos
     *
     * @return verifica se recuperou a lista de Bancos com sucesso
     */
    public ArrayList<ModelBancos> getListaBancosDAO() {
        ArrayList<ModelBancos> listamodelBancos = new ArrayList();
        ModelBancos modelBancos = new ModelBancos();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "codigo,"
                    + "banco_nome,"
                    + "banco_num "
                    + " FROM"
                    + " bancos"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelBancos = new ModelBancos();
                modelBancos.setCodigo(this.getResultSet().getInt(1));
                modelBancos.setBanco_nome(this.getResultSet().getString(2));
                modelBancos.setBanco_num(this.getResultSet().getString(3));
                listamodelBancos.add(modelBancos);
            }
        } catch (SQLException e) {
        } finally {
            this.fecharConexao();
        }
        return listamodelBancos;
    }

    /**
     * atualiza Bancos
     *
     * @param pModelBancos atualiza Bancos
     * @return verifica se atualizou o Bancos com sucesso
     */
    public boolean atualizarBancosDAO(ModelBancos pModelBancos) {
        try {
            this.conectar();
            this.executarUpdateDeleteSQL(
                    "UPDATE bancos SET "
                    + "codigo = '" + pModelBancos.getCodigo() + "',"
                    + "banco_nome = '" + pModelBancos.getBanco_nome() + "',"
                    + "banco_num = '" + pModelBancos.getBanco_num() + "'"
                    + " WHERE "
                    + "codigo = '" + pModelBancos.getCodigo() + "'"
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
     * Exclui Bancos
     *
     * @param pCodigo Exclui Bancos
     * @return Verifica se foi excluido com sucesso
     */
    public boolean excluirBancosDAO(int pCodigo) {
        try {
            this.conectar();
            return this.executarUpdateDeleteSQL(
                    "DELETE FROM bancos "
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
