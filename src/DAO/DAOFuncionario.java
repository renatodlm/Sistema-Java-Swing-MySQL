/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import conexao.ConexaoBanco;
import Model.ModelFuncionario;
import java.util.ArrayList;

/**
 *
 * @author studiomicroweb
 */
public class DAOFuncionario extends ConexaoBanco {

    /**
     * grava Funcionario
     *
     * @param pModelFuncionario return int
     * @return return int
     */
    public int salvarFuncionarioDAO(ModelFuncionario pModelFuncionario) {
        try {
            this.conectar();
            return this.insertSQL(
                    "INSERT INTO funcionarios ("
                    + "nome,"
                    + "endereco,"
                    + "bairro,"
                    + "cod_cidade,"
                    + "cod_estado,"
                    + "cep,"
                    + "telefone,"
                    + "celular,"
                    + "email,"
                    + "cpf,"
                    + "obs, "
                    + "numero,"
                    + "complemento,"
                    + "rg,"
                    + "cnh,"
                    + "filiacao,"
                    + "vinculo,"
                    + "ocupacao,"
                    + "op_cts,"
                    + "nascimento,"
                    + "data_admissao,"
                    + "data_uferias,"
                    + "data_vferias,"
                    + "ob_ocupacao,"
                    + "obs_func"
                    + ") VALUES ("
                    + "'" + pModelFuncionario.getNome() + "',"
                    + "'" + pModelFuncionario.getEndereco() + "',"
                    + "'" + pModelFuncionario.getBairro() + "',"
                    + "'" + pModelFuncionario.getCodCidade() + "',"
                    + "'" + pModelFuncionario.getCodEstado() + "',"
                    + "'" + pModelFuncionario.getCep() + "',"
                    + "'" + pModelFuncionario.getTelefone() + "',"
                    + "'" + pModelFuncionario.getCelular() + "',"
                    + "'" + pModelFuncionario.getEmail() + "',"
                    + "'" + pModelFuncionario.getCpf() + "',"
                    + "'" + pModelFuncionario.getObservacao() + "',"
                    + "'" + pModelFuncionario.getNumero() + "',"
                    + "'" + pModelFuncionario.getComplemento() + "',"
                    + "'" + pModelFuncionario.getRg() + "',"
                    + "'" + pModelFuncionario.getCnh() + "',"
                    + "'" + pModelFuncionario.getFiliacao() + "',"
                    + "'" + pModelFuncionario.getVinculo() + "',"
                    + "'" + pModelFuncionario.getOcupacao() + "',"
                    + "'" + pModelFuncionario.getOp_cts() + "',"
                    + "'" + pModelFuncionario.getNascimento() + "',"
                    + "'" + pModelFuncionario.getData_admissao() + "',"
                    + "'" + pModelFuncionario.getData_uferias() + "',"
                    + "'" + pModelFuncionario.getData_vferias() + "',"
                    + "'" + pModelFuncionario.getOb_ocupacao() + "',"
                    + "'" + pModelFuncionario.getObs_Func() + "'"
                    + ");"
            );
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            this.fecharConexao();
        }
    }

    /**
     * recupera Funcionario
     *
     * @param pCodigo return ModelFuncionario
     * @return return int
     */
    public ModelFuncionario getFuncionarioDAO(int pCodigo) {
        ModelFuncionario modelFuncionario = new ModelFuncionario();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "codigo,"
                    + "nome,"
                    + "endereco,"
                    + "bairro,"
                    + "cod_cidade,"
                    + "cod_estado,"
                    + "cep,"
                    + "telefone,"
                    + "celular,"
                    + "email,"
                    + "cpf,"
                    + "obs,"
                    + "numero,"
                    + "complemento,"
                    + "rg,"
                    + "cnh,"
                    + "filiacao,"
                    + "vinculo,"
                    + "ocupacao,"
                    + "op_cts,"
                    + "nascimento,"
                    + "data_admissao,"
                    + "data_uferias,"
                    + "data_vferias,"
                    + "ob_ocupacao,"
                    + "obs_func"
                    + " FROM"
                    + " funcionarios"
                    + " WHERE"
                    + " codigo = '" + pCodigo + "'"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelFuncionario.setCodigo(this.getResultSet().getInt(1));
                modelFuncionario.setNome(this.getResultSet().getString(2));
                modelFuncionario.setEndereco(this.getResultSet().getString(3));
                modelFuncionario.setBairro(this.getResultSet().getString(4));
                modelFuncionario.setCodCidade(this.getResultSet().getInt(5));
                modelFuncionario.setCodEstado(this.getResultSet().getInt(6));
                modelFuncionario.setCep(this.getResultSet().getString(7));
                modelFuncionario.setTelefone(this.getResultSet().getString(8));
                modelFuncionario.setCelular(this.getResultSet().getString(9));
                modelFuncionario.setEmail(this.getResultSet().getString(10));
                modelFuncionario.setCpf(this.getResultSet().getString(11));
                modelFuncionario.setObservacao(this.getResultSet().getString(12));
                modelFuncionario.setNumero(this.getResultSet().getInt(13));
                modelFuncionario.setComplemento(this.getResultSet().getString(14));
                modelFuncionario.setRg(this.getResultSet().getString(15));
                modelFuncionario.setCnh(this.getResultSet().getString(16));
                modelFuncionario.setFiliacao(this.getResultSet().getString(17));
                modelFuncionario.setVinculo(this.getResultSet().getString(18));
                modelFuncionario.setOcupacao(this.getResultSet().getInt(19));
                modelFuncionario.setOp_cts(this.getResultSet().getString(20));
                modelFuncionario.setNascimento(this.getResultSet().getDate(21));
                modelFuncionario.setData_admissao(this.getResultSet().getDate(22));
                modelFuncionario.setData_uferias(this.getResultSet().getString(23));
                modelFuncionario.setData_vferias(this.getResultSet().getString(24));
                modelFuncionario.setOb_ocupacao(this.getResultSet().getString(25));
                modelFuncionario.setObs_Func(this.getResultSet().getString(26));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.fecharConexao();
        }
        return modelFuncionario;
    }

    /**
     * recupera Funcionario
     *
     * @param pNome return ModelFuncionario
     * @return return int
     */
    public ModelFuncionario getFuncionarioDAO(String pNome) {
        ModelFuncionario modelFuncionario = new ModelFuncionario();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "codigo,"
                    + "nome,"
                    + "endereco,"
                    + "bairro,"
                    + "cod_cidade,"
                    + "cod_estado,"
                    + "cep,"
                    + "telefone,"
                    + "celular,"
                    + "email,"
                    + "cpf,"
                    + "obs,"
                    + "numero,"
                    + "complemento,"
                    + "rg,"
                    + "cnh,"
                    + "filiacao,"
                    + "vinculo,"
                    + "ocupacao,"
                    + "op_cts,"
                    + "nascimento,"
                    + "data_admissao,"
                    + "data_uferias,"
                    + "data_vferias,"
                    + "ob_ocupacao,"
                    + "obs_func"
                    + " FROM"
                    + " funcionarios"
                    + " WHERE"
                    + " nome = '" + pNome + "'"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelFuncionario.setCodigo(this.getResultSet().getInt(1));
                modelFuncionario.setNome(this.getResultSet().getString(2));
                modelFuncionario.setEndereco(this.getResultSet().getString(3));
                modelFuncionario.setBairro(this.getResultSet().getString(4));
                modelFuncionario.setCodCidade(this.getResultSet().getInt(5));
                modelFuncionario.setCodEstado(this.getResultSet().getInt(6));
                modelFuncionario.setCep(this.getResultSet().getString(7));
                modelFuncionario.setTelefone(this.getResultSet().getString(8));
                modelFuncionario.setCelular(this.getResultSet().getString(9));
                modelFuncionario.setEmail(this.getResultSet().getString(10));
                modelFuncionario.setCpf(this.getResultSet().getString(11));
                modelFuncionario.setObservacao(this.getResultSet().getString(12));
                modelFuncionario.setNumero(this.getResultSet().getInt(13));
                modelFuncionario.setComplemento(this.getResultSet().getString(14));
                modelFuncionario.setRg(this.getResultSet().getString(15));
                modelFuncionario.setCnh(this.getResultSet().getString(16));
                modelFuncionario.setFiliacao(this.getResultSet().getString(17));
                modelFuncionario.setVinculo(this.getResultSet().getString(18));
                modelFuncionario.setOcupacao(this.getResultSet().getInt(19));
                modelFuncionario.setOp_cts(this.getResultSet().getString(20));
                modelFuncionario.setNascimento(this.getResultSet().getDate(21));
                modelFuncionario.setData_admissao(this.getResultSet().getDate(22));
                modelFuncionario.setData_uferias(this.getResultSet().getString(23));
                modelFuncionario.setData_vferias(this.getResultSet().getString(24));
                modelFuncionario.setOb_ocupacao(this.getResultSet().getString(25));
                modelFuncionario.setObs_Func(this.getResultSet().getString(26));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.fecharConexao();
        }
        return modelFuncionario;
    }

    /**
     * recupera uma lista de Funcionario return ArrayList
     *
     * @return return
     */
    public ArrayList<ModelFuncionario> getListaFuncionarioDAO() {
        ArrayList<ModelFuncionario> listamodelFuncionario = new ArrayList();
        ModelFuncionario modelFuncionario = new ModelFuncionario();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "codigo,"
                    + "nome,"
                    + "endereco,"
                    + "bairro,"
                    + "cod_cidade,"
                    + "cod_estado,"
                    + "cep,"
                    + "telefone,"
                    + "celular,"
                    + "email,"
                    + "cpf,"
                    + "obs,"
                    + "numero,"
                    + "complemento,"
                    + "rg,"
                    + "cnh,"
                    + "filiacao,"
                    + "vinculo,"
                    + "ocupacao,"
                    + "op_cts,"
                    + "nascimento,"
                    + "data_admissao,"
                    + "data_uferias,"
                    + "data_vferias,"
                    + "ob_ocupacao,"
                    + "obs_func"
                    + " FROM"
                    + " funcionarios"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelFuncionario = new ModelFuncionario();
                modelFuncionario.setCodigo(this.getResultSet().getInt(1));
                modelFuncionario.setNome(this.getResultSet().getString(2));
                modelFuncionario.setEndereco(this.getResultSet().getString(3));
                modelFuncionario.setBairro(this.getResultSet().getString(4));
                modelFuncionario.setCodCidade(this.getResultSet().getInt(5));
                modelFuncionario.setCodEstado(this.getResultSet().getInt(6));
                modelFuncionario.setCep(this.getResultSet().getString(7));
                modelFuncionario.setTelefone(this.getResultSet().getString(8));
                modelFuncionario.setCelular(this.getResultSet().getString(9));
                modelFuncionario.setEmail(this.getResultSet().getString(10));
                modelFuncionario.setCpf(this.getResultSet().getString(11));
                modelFuncionario.setObservacao(this.getResultSet().getString(12));
                modelFuncionario.setNumero(this.getResultSet().getInt(13));
                modelFuncionario.setComplemento(this.getResultSet().getString(14));
                modelFuncionario.setRg(this.getResultSet().getString(15));
                modelFuncionario.setCnh(this.getResultSet().getString(16));
                modelFuncionario.setFiliacao(this.getResultSet().getString(17));
                modelFuncionario.setVinculo(this.getResultSet().getString(18));
                modelFuncionario.setOcupacao(this.getResultSet().getInt(19));
                modelFuncionario.setOp_cts(this.getResultSet().getString(20));
                modelFuncionario.setNascimento(this.getResultSet().getDate(21));
                modelFuncionario.setData_admissao(this.getResultSet().getDate(22));
                modelFuncionario.setData_uferias(this.getResultSet().getString(23));
                modelFuncionario.setData_vferias(this.getResultSet().getString(24));
                modelFuncionario.setOb_ocupacao(this.getResultSet().getString(25));
                modelFuncionario.setObs_Func(this.getResultSet().getString(26));
                listamodelFuncionario.add(modelFuncionario);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.fecharConexao();
        }
        return listamodelFuncionario;
    }

    public ArrayList<ModelFuncionario> getListaSomenteAgentesDAO() {
        ArrayList<ModelFuncionario> listamodelFuncionario = new ArrayList();
        ModelFuncionario modelFuncionario = new ModelFuncionario();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "codigo,"
                    + "nome,"
                    + "endereco,"
                    + "bairro,"
                    + "cod_cidade,"
                    + "cod_estado,"
                    + "cep,"
                    + "telefone,"
                    + "celular,"
                    + "email,"
                    + "cpf,"
                    + "obs,"
                    + "numero,"
                    + "complemento,"
                    + "rg,"
                    + "cnh,"
                    + "filiacao,"
                    + "vinculo,"
                    + "ocupacao,"
                    + "op_cts,"
                    + "nascimento,"
                    + "data_admissao,"
                    + "data_uferias,"
                    + "data_vferias,"
                    + "ob_ocupacao,"
                    + "obs_func"
                    + " FROM"
                    + " funcionarios WHERE ocupacao = 1"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelFuncionario = new ModelFuncionario();
                modelFuncionario.setCodigo(this.getResultSet().getInt(1));
                modelFuncionario.setNome(this.getResultSet().getString(2));
                modelFuncionario.setEndereco(this.getResultSet().getString(3));
                modelFuncionario.setBairro(this.getResultSet().getString(4));
                modelFuncionario.setCodCidade(this.getResultSet().getInt(5));
                modelFuncionario.setCodEstado(this.getResultSet().getInt(6));
                modelFuncionario.setCep(this.getResultSet().getString(7));
                modelFuncionario.setTelefone(this.getResultSet().getString(8));
                modelFuncionario.setCelular(this.getResultSet().getString(9));
                modelFuncionario.setEmail(this.getResultSet().getString(10));
                modelFuncionario.setCpf(this.getResultSet().getString(11));
                modelFuncionario.setObservacao(this.getResultSet().getString(12));
                modelFuncionario.setNumero(this.getResultSet().getInt(13));
                modelFuncionario.setComplemento(this.getResultSet().getString(14));
                modelFuncionario.setRg(this.getResultSet().getString(15));
                modelFuncionario.setCnh(this.getResultSet().getString(16));
                modelFuncionario.setFiliacao(this.getResultSet().getString(17));
                modelFuncionario.setVinculo(this.getResultSet().getString(18));
                modelFuncionario.setOcupacao(this.getResultSet().getInt(19));
                modelFuncionario.setOp_cts(this.getResultSet().getString(20));
                modelFuncionario.setNascimento(this.getResultSet().getDate(21));
                modelFuncionario.setData_admissao(this.getResultSet().getDate(22));
                modelFuncionario.setData_uferias(this.getResultSet().getString(23));
                modelFuncionario.setData_vferias(this.getResultSet().getString(24));
                modelFuncionario.setOb_ocupacao(this.getResultSet().getString(25));
                modelFuncionario.setObs_Func(this.getResultSet().getString(26));
                listamodelFuncionario.add(modelFuncionario);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.fecharConexao();
        }
        return listamodelFuncionario;
    }

    /*Alterar funcionarios*/
    public boolean atualizarFuncionarioDAO(ModelFuncionario ModelFuncionario) {
        /*System.out.println("Entrei aqui");
         System.out.println("codigo:" + ModelFuncionario.getCodigo());*/
        try {
            this.conectar();
            this.executarUpdateDeleteSQL(
                    "UPDATE funcionarios SET "
                    + "codigo = '" + ModelFuncionario.getCodigo() + "',"
                    + "nome = '" + ModelFuncionario.getNome() + "',"
                    + "endereco = '" + ModelFuncionario.getEndereco() + "',"
                    + "bairro = '" + ModelFuncionario.getBairro() + "',"
                    + "cod_cidade = '" + ModelFuncionario.getCodCidade() + "',"
                    + "cod_estado = '" + ModelFuncionario.getCodEstado() + "',"
                    + "cep = '" + ModelFuncionario.getCep() + "',"
                    + "telefone = '" + ModelFuncionario.getTelefone() + "',"
                    + "celular = '" + ModelFuncionario.getCelular() + "',"
                    + "email = '" + ModelFuncionario.getEmail() + "',"
                    + "cpf = '" + ModelFuncionario.getCpf() + "',"
                    + "obs = '" + ModelFuncionario.getObservacao() + "',"
                    + "numero = '" + ModelFuncionario.getNumero() + "',"
                    + "complemento = '" + ModelFuncionario.getComplemento() + "',"
                    + "rg = '" + ModelFuncionario.getRg() + "',"
                    + "cnh = '" + ModelFuncionario.getCnh() + "',"
                    + "filiacao = '" + ModelFuncionario.getFiliacao() + "',"
                    + "vinculo = '" + ModelFuncionario.getVinculo() + "',"
                    + "ocupacao = '" + ModelFuncionario.getOcupacao() + "',"
                    + "op_cts = '" + ModelFuncionario.getOp_cts() + "',"
                    + "nascimento = '" + ModelFuncionario.getNascimento() + "',"
                    + "data_admissao = '" + ModelFuncionario.getData_admissao() + "',"
                    + "data_uferias = '" + ModelFuncionario.getData_uferias() + "',"
                    + "data_vferias = '" + ModelFuncionario.getData_vferias() + "',"
                    + "ob_ocupacao = '" + ModelFuncionario.getOb_ocupacao() + "',"
                    + "obs_func = '" + ModelFuncionario.getObs_Func() + "'"
                    + " WHERE "
                    + "codigo = '" + ModelFuncionario.getCodigo() + "'"
                    + ";"
            );
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            this.fecharConexao();
        }
    }

    public ArrayList<ModelFuncionario> getTipoFuncProcessosDAO(int pOcupacao) {
        ArrayList<ModelFuncionario> listamodelModelFuncionario = new ArrayList();
        ModelFuncionario modelFuncionario = new ModelFuncionario();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "codigo,"
                    + "nome,"
                    + "endereco,"
                    + "bairro,"
                    + "cod_cidade,"
                    + "cod_estado,"
                    + "cep,"
                    + "telefone,"
                    + "celular,"
                    + "email,"
                    + "cpf,"
                    + "obs,"
                    + "numero,"
                    + "complemento,"
                    + "rg,"
                    + "cnh,"
                    + "filiacao,"
                    + "vinculo,"
                    + "ocupacao,"
                    + "op_cts,"
                    + "nascimento,"
                    + "data_admissao,"
                    + "data_uferias,"
                    + "data_vferias,"
                    + "ob_ocupacao,"
                    + "obs_func"
                    + " FROM"
                    + " funcionarios where ocupacao  = '" + pOcupacao + "'"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelFuncionario.setCodigo(this.getResultSet().getInt(1));
                modelFuncionario.setNome(this.getResultSet().getString(2));
                modelFuncionario.setEndereco(this.getResultSet().getString(3));
                modelFuncionario.setBairro(this.getResultSet().getString(4));
                modelFuncionario.setCodCidade(this.getResultSet().getInt(5));
                modelFuncionario.setCodEstado(this.getResultSet().getInt(6));
                modelFuncionario.setCep(this.getResultSet().getString(7));
                modelFuncionario.setTelefone(this.getResultSet().getString(8));
                modelFuncionario.setCelular(this.getResultSet().getString(9));
                modelFuncionario.setEmail(this.getResultSet().getString(10));
                modelFuncionario.setCpf(this.getResultSet().getString(11));
                modelFuncionario.setObservacao(this.getResultSet().getString(12));
                modelFuncionario.setNumero(this.getResultSet().getInt(13));
                modelFuncionario.setComplemento(this.getResultSet().getString(14));
                modelFuncionario.setRg(this.getResultSet().getString(15));
                modelFuncionario.setCnh(this.getResultSet().getString(16));
                modelFuncionario.setFiliacao(this.getResultSet().getString(17));
                modelFuncionario.setVinculo(this.getResultSet().getString(18));
                modelFuncionario.setOcupacao(this.getResultSet().getInt(19));
                modelFuncionario.setOp_cts(this.getResultSet().getString(20));
                modelFuncionario.setNascimento(this.getResultSet().getDate(21));
                modelFuncionario.setData_admissao(this.getResultSet().getDate(22));
                modelFuncionario.setData_uferias(this.getResultSet().getString(23));
                modelFuncionario.setData_vferias(this.getResultSet().getString(24));
                modelFuncionario.setOb_ocupacao(this.getResultSet().getString(25));
                modelFuncionario.setObs_Func(this.getResultSet().getString(26));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.fecharConexao();
        }
        return listamodelModelFuncionario;
    }

    /**
     * exclui Funcionario
     *
     * @param Codigo return boolean
     * @return return
     */
    public boolean excluirFuncionarioDAO(int Codigo) {
        try {
            this.conectar();
            return this.executarUpdateDeleteSQL(
                    "DELETE FROM funcionarios "
                    + " WHERE "
                    + "codigo = '" + Codigo + "'"
                    + ";"
            );
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            this.fecharConexao();
        }
    }

}
