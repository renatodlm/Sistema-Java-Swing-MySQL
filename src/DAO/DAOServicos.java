 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.ModelServicos;
import conexao.ConexaoBanco;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author studiomicroweb
 */
public class DAOServicos extends ConexaoBanco {

    /**
     * grava Servicos
     *
     * @param pModelServicos return int
     * @return Verifica se Salvou os serviços com sucesso
     */
    public int salvarServicosDAO(ModelServicos pModelServicos) {
        try {
            this.conectar();
            return this.insertSQL(
                    "INSERT INTO servicos ("
                    + "nome"
                    + ") VALUES ("
                    + "'" + pModelServicos.getNome() + "'"
                    + ");"
            );
        } catch (Exception e) {
            return 0;
        } finally {
            this.fecharConexao();
        }
    }

    /**
     * recupera Servicos
     *
     * @param pCodigo Retorna Serviço por codigo
     * @return Verifica se os serviços retornaram com sucesso
     */
    public ModelServicos getServicosDAO(int pCodigo) {
        ModelServicos modelServicos = new ModelServicos();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "codigoServico,"
                    + "nome"
                    + " FROM"
                    + " servicos"
                    + " WHERE"
                    + " codigoServico = '" + pCodigo + "'"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelServicos.setCodigo(this.getResultSet().getInt(1));
                modelServicos.setNome(this.getResultSet().getString(2));

            }
        } catch (SQLException e) {
        } finally {
            this.fecharConexao();
        }
        return modelServicos;
    }

    /**
     * recupera Servicos
     *
     * @param pNome Retorna Serviço por nome
     * @return verifica se foi realizado com sucesso
     */
    public ModelServicos getServicosDAO(String pNome) {
        ModelServicos modelServicos = new ModelServicos();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "codigoServico,"
                    + "nome"
                    + " FROM"
                    + " servicos"
                    + " WHERE"
                    + " nome = '" + pNome + "'"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelServicos.setCodigo(this.getResultSet().getInt(1));
                modelServicos.setNome(this.getResultSet().getString(2));
            }
        } catch (SQLException e) {
        } finally {
            this.fecharConexao();
        }
        return modelServicos;
    }

    /**
     * recupera uma lista de Servicos return ArrayList
     *
     * @return verifica se recuperou a lista de serviços com sucesso
     */
    public ArrayList<ModelServicos> getListaServicosDAO() {
        ArrayList<ModelServicos> listamodelServicos = new ArrayList();
        ModelServicos modelServicos = new ModelServicos();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "codigoServico,"
                    + "nome"
                    + " FROM"
                    + " servicos"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelServicos = new ModelServicos();
                modelServicos.setCodigo(this.getResultSet().getInt(1));
                modelServicos.setNome(this.getResultSet().getString(2));
                listamodelServicos.add(modelServicos);
            }
        } catch (SQLException e) {
        } finally {
            this.fecharConexao();
        }
        return listamodelServicos;
    }

    /**
     * atualiza Servicos
     *
     * @param pModelServicos atualiza Servicos
     * @return verifica se atualizou o serviço com sucesso
     */
    public boolean atualizarServicosDAO(ModelServicos pModelServicos) {
        try {
            this.conectar();
            this.executarUpdateDeleteSQL(
                    "UPDATE servicos SET "
                    + "codigoServico = '" + pModelServicos.getCodigo() + "',"
                    + "nome = '" + pModelServicos.getNome() + "'"
                    + " WHERE "
                    + "codigoServico = '" + pModelServicos.getCodigo() + "'"
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
     * Exclui serviços
     *
     * @param pCodigo Exclui serviços
     * @return Verifica se foi excluido com sucesso
     */
    public boolean excluirServicosDAO(int pCodigo) {
        try {
            this.conectar();
            return this.executarUpdateDeleteSQL(
                    "DELETE FROM servicos "
                    + " WHERE "
                    + "codigoServico = '" + pCodigo + "'"
                    + ";"
            );
        } catch (Exception e) {
            return false;
        } finally {
            this.fecharConexao();
        }
    }

}
