/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import conexao.ConexaoBanco;
import java.util.ArrayList;
import Model.ModelCidade;
import Model.ModelSeguradora;
import Model.ModelSeguradoraCidadeEstado;
import Model.ModelEstado;
import java.sql.SQLException;

/**
 *
 * @author studiomicroweb
 */
public class DAOSeguradoraCidadeEstado extends ConexaoBanco {

    /**
     * recupera uma lista de CidadeEstado return ArrayList
     *
     * @return Verifica se foi realizado com sucesso a lista de cidade por
     * estado
     */
    public ArrayList<ModelSeguradoraCidadeEstado> getListaSeguradoraCidadeEstadoDAO() {
        ArrayList<ModelSeguradoraCidadeEstado> listaModelSeguradoraCidadeEstados = new ArrayList();
        ModelSeguradoraCidadeEstado modelSeguradoraCidadeEstado = new ModelSeguradoraCidadeEstado();
        ModelSeguradora modelSeguradora = new ModelSeguradora();
        ModelCidade modelCidade = new ModelCidade();
        ModelEstado modelEstado = new ModelEstado();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "cidade.codigo,"
                    + "cidade.nome,"
                    + "cidade.fk_cod_estado,"
                    + "estado.codigo,"
                    + "estado.uf,"
                    + "estado.nome,"
                    + "seguradoras.codigo,"
                    + "seguradoras.nome,"
                    + "seguradoras.cnpj,"
                    + "seguradoras.endereco,"
                    + "seguradoras.bairro,"
                    + "seguradoras.numero,"
                    + "seguradoras.complemento,"
                    + "seguradoras.cod_cidade,"
                    + "seguradoras.cod_estado,"
                    + "seguradoras.telefone,"
                    + "seguradoras.celular,"
                    + "seguradoras.email,"
                    + "seguradoras.obs,"
                    + "seguradoras.cep,"
                    + "seguradoras.seg_km,"
                    + "seguradoras.seg_honorarios,"
                    + "seguradoras.seg_negativa,"
                    + "seguradoras.seg_iss"
                    + " FROM"
                    + " cidade inner join estado on estado.codigo = cidade.fk_cod_estado "
                    + " inner join seguradoras on cidade.codigo = seguradoras.cod_cidade  "
                    + "ORDER BY seguradoras.codigo DESC"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelCidade = new ModelCidade();
                modelEstado = new ModelEstado();
                modelSeguradora = new ModelSeguradora();
                modelSeguradoraCidadeEstado = new ModelSeguradoraCidadeEstado();
                modelCidade.setCodigo(this.getResultSet().getInt(1));
                modelCidade.setNome(this.getResultSet().getString(2));
                modelCidade.setFk_cod_estado(this.getResultSet().getInt(3));
                modelEstado.setCodigo(this.getResultSet().getInt(4));
                modelEstado.setUf(this.getResultSet().getString(5));
                modelEstado.setNome(this.getResultSet().getString(6));
                modelSeguradora.setCodigo(this.getResultSet().getInt(7));
                modelSeguradora.setNome(this.getResultSet().getString(8));
                modelSeguradora.setCnpj(this.getResultSet().getString(9));
                modelSeguradora.setEndereco(this.getResultSet().getString(10));
                modelSeguradora.setBairro(this.getResultSet().getString(11));
                modelSeguradora.setNumero(this.getResultSet().getInt(12));
                modelSeguradora.setComplemento(this.getResultSet().getString(13));
                modelSeguradora.setCodCidade(this.getResultSet().getInt(14));
                modelSeguradora.setCodEstado(this.getResultSet().getInt(15));
                modelSeguradora.setTelefone(this.getResultSet().getString(16));
                modelSeguradora.setCelular(this.getResultSet().getString(17));
                modelSeguradora.setEmail(this.getResultSet().getString(18));
                modelSeguradora.setObservacao(this.getResultSet().getString(19));
                modelSeguradora.setCep(this.getResultSet().getString(20));
                modelSeguradora.setSeg_km(this.getResultSet().getDouble(21));
                modelSeguradora.setSeg_honorarios(this.getResultSet().getDouble(22));
                modelSeguradora.setSeg_negativa(this.getResultSet().getString(23));
                modelSeguradora.setSeg_iss(this.getResultSet().getString(24));
                modelSeguradoraCidadeEstado.setModelCidade(modelCidade);
                modelSeguradoraCidadeEstado.setModelEstado(modelEstado);
                modelSeguradoraCidadeEstado.setModelSeguradora(modelSeguradora);
                listaModelSeguradoraCidadeEstados.add(modelSeguradoraCidadeEstado);
            }
        } catch (SQLException e) {
        } finally {
            this.fecharConexao();
        }
        return listaModelSeguradoraCidadeEstados;
    }

    public ArrayList<ModelSeguradoraCidadeEstado> pesquisaSeguradoraCidadeEstadoDAO(String tipo, String contendo) {
        ArrayList<ModelSeguradoraCidadeEstado> listaModelSeguradoraCidadeEstados = new ArrayList();
        ModelSeguradoraCidadeEstado modelSeguradoraCidadeEstado = new ModelSeguradoraCidadeEstado();
        ModelSeguradora modelSeguradora = new ModelSeguradora();
        ModelCidade modelCidade = new ModelCidade();
        ModelEstado modelEstado = new ModelEstado();
        try {
            this.conectar();
            String sql = "SELECT "
                    + "cidade.codigo,"
                    + "cidade.nome,"
                    + "cidade.fk_cod_estado,"
                    + "estado.codigo,"
                    + "estado.uf,"
                    + "estado.nome,"
                    + "seguradoras.codigo,"
                    + "seguradoras.nome,"
                    + "seguradoras.cnpj,"
                    + "seguradoras.endereco,"
                    + "seguradoras.bairro,"
                    + "seguradoras.numero,"
                    + "seguradoras.complemento,"
                    + "seguradoras.cod_cidade,"
                    + "seguradoras.cod_estado,"
                    + "seguradoras.telefone,"
                    + "seguradoras.celular,"
                    + "seguradoras.email,"
                    + "seguradoras.obs,"
                    + "seguradoras.cep,"
                    + "seguradoras.seg_km,"
                    + "seguradoras.seg_honorarios,"
                    + "seguradoras.seg_negativa,"
                    + "seguradoras.seg_iss"
                    + " FROM"
                    + " cidade inner join estado on estado.codigo = cidade.fk_cod_estado "
                    + " inner join seguradoras on cidade.codigo = seguradoras.cod_cidade "
                    + " WHERE " + tipo + " like '" + contendo + "%'";
            this.executarSQL(sql);

            while (this.getResultSet().next()) {
                modelCidade = new ModelCidade();
                modelEstado = new ModelEstado();
                modelSeguradora = new ModelSeguradora();
                modelSeguradoraCidadeEstado = new ModelSeguradoraCidadeEstado();
                modelCidade.setCodigo(this.getResultSet().getInt(1));
                modelCidade.setNome(this.getResultSet().getString(2));
                modelCidade.setFk_cod_estado(this.getResultSet().getInt(3));
                modelEstado.setCodigo(this.getResultSet().getInt(4));
                modelEstado.setUf(this.getResultSet().getString(5));
                modelEstado.setNome(this.getResultSet().getString(6));
                modelSeguradora.setCodigo(this.getResultSet().getInt(7));
                modelSeguradora.setNome(this.getResultSet().getString(8));
                modelSeguradora.setCnpj(this.getResultSet().getString(9));
                modelSeguradora.setEndereco(this.getResultSet().getString(10));
                modelSeguradora.setBairro(this.getResultSet().getString(11));
                modelSeguradora.setNumero(this.getResultSet().getInt(12));
                modelSeguradora.setComplemento(this.getResultSet().getString(13));
                modelSeguradora.setCodCidade(this.getResultSet().getInt(14));
                modelSeguradora.setCodEstado(this.getResultSet().getInt(15));
                modelSeguradora.setTelefone(this.getResultSet().getString(16));
                modelSeguradora.setCelular(this.getResultSet().getString(17));
                modelSeguradora.setEmail(this.getResultSet().getString(18));
                modelSeguradora.setObservacao(this.getResultSet().getString(19));
                modelSeguradora.setCep(this.getResultSet().getString(20));
                modelSeguradora.setSeg_km(this.getResultSet().getDouble(21));
                modelSeguradora.setSeg_honorarios(this.getResultSet().getDouble(22));
                modelSeguradora.setSeg_negativa(this.getResultSet().getString(23));
                modelSeguradora.setSeg_iss(this.getResultSet().getString(24));
                modelSeguradoraCidadeEstado.setModelCidade(modelCidade);
                modelSeguradoraCidadeEstado.setModelEstado(modelEstado);
                modelSeguradoraCidadeEstado.setModelSeguradora(modelSeguradora);
                listaModelSeguradoraCidadeEstados.add(modelSeguradoraCidadeEstado);
            }

        } catch (SQLException e) {
        } finally {
            this.fecharConexao();
        }
        return listaModelSeguradoraCidadeEstados;
    }

}
