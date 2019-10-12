/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import conexao.ConexaoBanco;
import java.util.ArrayList;
import Model.ModelCidade;
import Model.ModelFuncionario;
import Model.ModelFuncionarioCidadeEstado;
import Model.ModelEstado;

/**
 *
 * @author studiomicroweb
 */
public class DAOFuncionarioCidadeEstado extends ConexaoBanco {

    /**
     * recupera uma lista de CidadeEstado return ArrayList
     *
     * @return Verifica se retornou com sucesso
     */
    public ArrayList<ModelFuncionarioCidadeEstado> getListaFuncionarioCidadeEstadoDAO() {
        ArrayList<ModelFuncionarioCidadeEstado> listaModelFuncionarioCidadeEstados = new ArrayList();
        ModelFuncionarioCidadeEstado modelFuncionarioCidadeEstado = new ModelFuncionarioCidadeEstado();
        ModelFuncionario modelFuncionario = new ModelFuncionario();
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
                    + "funcionarios.codigo, "
                    + "funcionarios.nome, "
                    + "funcionarios.cpf, "
                    + "funcionarios.endereco, "
                    + "funcionarios.bairro, "
                    + "funcionarios.numero,"
                    + "funcionarios.complemento,"
                    + "funcionarios.cod_cidade, "
                    + "funcionarios.cod_estado, "
                    + "funcionarios.telefone, "
                    + "funcionarios.celular, "
                    + "funcionarios.email, "
                    + "funcionarios.obs,"
                    + "funcionarios.nascimento,"
                    + "funcionarios.rg,"
                    + "funcionarios.cnh,"
                    + "funcionarios.filiacao,"
                    + "funcionarios.vinculo,"
                    + "funcionarios.ocupacao,"
                    + "funcionarios.data_admissao,"
                    + "funcionarios.data_uferias,"
                    + "funcionarios.data_vferias,"
                    + "funcionarios.op_cts"
                    + " FROM"
                    + " cidade inner join estado on estado.codigo = cidade.fk_cod_estado "
                    + " inner join funcionarios on cidade.codigo = funcionarios.cod_cidade  "
                    + "ORDER BY funcionarios.codigo DESC"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelCidade = new ModelCidade();
                modelEstado = new ModelEstado();
                modelFuncionario = new ModelFuncionario();
                modelFuncionarioCidadeEstado = new ModelFuncionarioCidadeEstado();
                modelCidade.setCodigo(this.getResultSet().getInt(1));
                modelCidade.setNome(this.getResultSet().getString(2));
                modelCidade.setFk_cod_estado(this.getResultSet().getInt(3));
                modelEstado.setCodigo(this.getResultSet().getInt(4));
                modelEstado.setUf(this.getResultSet().getString(5));
                modelEstado.setNome(this.getResultSet().getString(6));
                modelFuncionario.setCodigo(this.getResultSet().getInt(7));
                modelFuncionario.setNome(this.getResultSet().getString(8));
                modelFuncionario.setCpf(this.getResultSet().getString(9));
                modelFuncionario.setEndereco(this.getResultSet().getString(10));
                modelFuncionario.setBairro(this.getResultSet().getString(11));
                modelFuncionario.setNumero(this.getResultSet().getInt(12));
                modelFuncionario.setComplemento(this.getResultSet().getString(13));
                modelFuncionario.setCodCidade(this.getResultSet().getInt(14));
                modelFuncionario.setCodEstado(this.getResultSet().getInt(15));
                modelFuncionario.setCep(this.getResultSet().getString(16));
                modelFuncionario.setTelefone(this.getResultSet().getString(16));
                modelFuncionario.setCelular(this.getResultSet().getString(17));
                modelFuncionario.setEmail(this.getResultSet().getString(18));
                modelFuncionario.setObservacao(this.getResultSet().getString(19));
                modelFuncionario.setOcupacao(this.getResultSet().getInt(25));
                modelFuncionarioCidadeEstado.setModelCidade(modelCidade);
                modelFuncionarioCidadeEstado.setModelEstado(modelEstado);
                modelFuncionarioCidadeEstado.setModelFuncionario(modelFuncionario);
                listaModelFuncionarioCidadeEstados.add(modelFuncionarioCidadeEstado);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.fecharConexao();
        }
        return listaModelFuncionarioCidadeEstados;
    }

    public ArrayList<ModelFuncionarioCidadeEstado> pesquisaFuncionarioCidadeEstadoDAO(String tipo, String contendo) {
        ArrayList<ModelFuncionarioCidadeEstado> listaModelFuncionarioCidadeEstados = new ArrayList();
        ModelFuncionarioCidadeEstado modelFuncionarioCidadeEstado = new ModelFuncionarioCidadeEstado();
        ModelFuncionario modelFuncionario = new ModelFuncionario();
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
                    + "funcionarios.codigo, "
                    + "funcionarios.nome, "
                    + "funcionarios.cpf, "
                    + "funcionarios.endereco, "
                    + "funcionarios.bairro, "
                    + "funcionarios.numero,"
                    + "funcionarios.complemento,"
                    + "funcionarios.cod_cidade, "
                    + "funcionarios.cod_estado, "
                    + "funcionarios.telefone, "
                    + "funcionarios.celular, "
                    + "funcionarios.email, "
                    + "funcionarios.obs,"
                    + "funcionarios.nascimento,"
                    + "funcionarios.rg,"
                    + "funcionarios.cnh,"
                    + "funcionarios.filiacao,"
                    + "funcionarios.vinculo,"
                    + "funcionarios.ocupacao,"
                    + "funcionarios.data_admissao,"
                    + "funcionarios.data_uferias,"
                    + "funcionarios.data_vferias,"
                    + "funcionarios.op_cts"
                    + " FROM"
                    + " cidade inner join estado on estado.codigo = cidade.fk_cod_estado "
                    + " inner join funcionarios on cidade.codigo = funcionarios.cod_cidade "
                    + " WHERE " + tipo + " like '" + contendo + "%'";
            this.executarSQL(sql);

            while (this.getResultSet().next()) {
                modelCidade = new ModelCidade();
                modelEstado = new ModelEstado();
                modelFuncionario = new ModelFuncionario();
                modelFuncionarioCidadeEstado = new ModelFuncionarioCidadeEstado();
                modelCidade.setCodigo(this.getResultSet().getInt(1));
                modelCidade.setNome(this.getResultSet().getString(2));
                modelCidade.setFk_cod_estado(this.getResultSet().getInt(3));
                modelEstado.setCodigo(this.getResultSet().getInt(4));
                modelEstado.setUf(this.getResultSet().getString(5));
                modelEstado.setNome(this.getResultSet().getString(6));
                modelFuncionario.setCodigo(this.getResultSet().getInt(7));
                modelFuncionario.setNome(this.getResultSet().getString(8));
                modelFuncionario.setCpf(this.getResultSet().getString(9));
                modelFuncionario.setEndereco(this.getResultSet().getString(10));
                modelFuncionario.setBairro(this.getResultSet().getString(11));
                modelFuncionario.setNumero(this.getResultSet().getInt(12));
                modelFuncionario.setComplemento(this.getResultSet().getString(13));
                modelFuncionario.setCodCidade(this.getResultSet().getInt(14));
                modelFuncionario.setCodEstado(this.getResultSet().getInt(15));
                modelFuncionario.setCep(this.getResultSet().getString(16));
                modelFuncionario.setTelefone(this.getResultSet().getString(16));
                modelFuncionario.setCelular(this.getResultSet().getString(17));
                modelFuncionario.setEmail(this.getResultSet().getString(18));
                modelFuncionario.setObservacao(this.getResultSet().getString(19));
                modelFuncionario.setOcupacao(this.getResultSet().getInt(25));
                modelFuncionarioCidadeEstado.setModelCidade(modelCidade);
                modelFuncionarioCidadeEstado.setModelEstado(modelEstado);
                modelFuncionarioCidadeEstado.setModelFuncionario(modelFuncionario);
                listaModelFuncionarioCidadeEstados.add(modelFuncionarioCidadeEstado);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.fecharConexao();
        }
        return listaModelFuncionarioCidadeEstados;
    }

}
