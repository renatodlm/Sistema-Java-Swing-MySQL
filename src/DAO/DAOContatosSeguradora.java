/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import conexao.ConexaoBanco;
import java.util.ArrayList;
import java.sql.SQLException;
import Model.ModelSeguradora;
import Model.ModelContatosSeguradora;
import Model.ModelContatosSeguradoraSeg;

/**
 *
 * @author Work
 */
public class DAOContatosSeguradora extends ConexaoBanco {

    /**
     * recupera umaLista de Contatos pela Seguradora return ArrayList
     *
     * @return Retorna Lista de Contatos pela Seguradora
     */
    public ArrayList<ModelContatosSeguradoraSeg> getListaModelContatosSeguradoraDAO() {
        ArrayList<ModelContatosSeguradoraSeg> listaContatosSeguradora = new ArrayList();
        ModelContatosSeguradoraSeg modelContatosSeguradoraSeg = new ModelContatosSeguradoraSeg();
        ModelContatosSeguradora modelContatosSeguradora = new ModelContatosSeguradora();
        ModelSeguradora modelSeguradora = new ModelSeguradora();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "contatos_seguradora.codigo,"
                    + "contatos_seguradora.cod_seguradora,"
                    + "contatos_seguradora.cttseg_nome,"
                    + "contatos_seguradora.cttseg_telefone,"
                    + "contatos_seguradora.cttseg_email,"
                    + "contatos_seguradora.cttseg_obs "
                    + " FROM"
                    + " contatos_seguradora inner join estado on seguradoras.codigo = contatos_seguradora.cod_seguradora"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelContatosSeguradoraSeg = new ModelContatosSeguradoraSeg();
                modelContatosSeguradora = new ModelContatosSeguradora();
                modelSeguradora = new ModelSeguradora();
                modelContatosSeguradora.setCodigo(this.getResultSet().getInt(1));
                modelContatosSeguradora.setCod_seguradora(this.getResultSet().getInt(2));
                modelContatosSeguradora.setCttseg_nome(this.getResultSet().getString(3));
                modelContatosSeguradora.setCttseg_telefone(this.getResultSet().getString(4));
                modelContatosSeguradora.setCttseg_email(this.getResultSet().getString(5));
                modelSeguradora.setFk_cod_contatos(this.getResultSet().getInt(6));
                modelContatosSeguradoraSeg.setContatosSeguradora(modelContatosSeguradora);
                modelContatosSeguradoraSeg.setModelSeguradora(modelSeguradora);
                listaContatosSeguradora.add(modelContatosSeguradoraSeg);
            }

        } catch (SQLException e) {
        } finally {
            this.fecharConexao();
        }
        return listaContatosSeguradora;
    }

}
