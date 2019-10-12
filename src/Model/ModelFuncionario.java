/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;

/**
 *
 * @author studiomicroweb
 */
public class ModelFuncionario {

    private int codigo;
    private String nome;
    private String endereco;
    private String bairro;
    private int codCidade;
    private int codEstado;
    private String cep;
    private String telefone;
    private String celular;
    private String email;
    private String cpf;
    private String observacao;
    private String complemento;
    private int numero;
    private String casa;
    private Date nascimento;
    private String rg;
    private String cnh;
    private String filiacao;
    private String vinculo;
    private int ocupacao;
    private Date data_admissao;
    private String data_uferias;
    private String data_vferias;
    private String op_cts;
    private String ob_ocupacao;
    private String obs_Func;
    private int banco_nome;
    private String banco_tipo;
    private String banco_agencia;
    private String banco_conta;
    private String banco_op;
    private String banco_obs;

    /**
     * Construtor
     */
    public ModelFuncionario() {
    }

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param pCodigo the codigo to set
     */
    public void setCodigo(int pCodigo) {
        this.codigo = pCodigo;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param pNome the nome to set
     */
    public void setNome(String pNome) {
        this.nome = pNome;
    }

    /**
     * @return the endereco
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * @param pEndereco the endereco to set
     */
    public void setEndereco(String pEndereco) {
        this.endereco = pEndereco;
    }

    /**
     * @return the bairro
     */
    public String getBairro() {
        return bairro;
    }

    /**
     * @param pBairro the bairro to set
     */
    public void setBairro(String pBairro) {
        this.bairro = pBairro;
    }

    /**
     * @return the codCidade
     */
    public int getCodCidade() {
        return codCidade;
    }

    /**
     * @param pCodCidade the codCidade to set
     */
    public void setCodCidade(int pCodCidade) {
        this.codCidade = pCodCidade;
    }

    /**
     * @return the codEstado
     */
    public int getCodEstado() {
        return codEstado;
    }

    /**
     * @param pCodEstado the codEstado to set
     */
    public void setCodEstado(int pCodEstado) {
        this.codEstado = pCodEstado;
    }

    /**
     * @return the cep
     */
    public String getCep() {
        return cep;
    }

    /**
     * @param pCep the cep to set
     */
    public void setCep(String pCep) {
        this.cep = pCep;
    }

    /**
     * @return the telefone
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * @param pTelefone the telefone to set
     */
    public void setTelefone(String pTelefone) {
        this.telefone = pTelefone;
    }

    /**
     * @return the celular
     */
    public String getCelular() {
        return celular;
    }

    /**
     * @param pCelular the celular to set
     */
    public void setCelular(String pCelular) {
        this.celular = pCelular;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param pEmail the email to set
     */
    public void setEmail(String pEmail) {
        this.email = pEmail;
    }

    /**
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param pCpf the cpf to set
     */
    public void setCpf(String pCpf) {
        this.cpf = pCpf;
    }

    /**
     * @return the observacao
     */
    public String getObservacao() {
        return observacao;
    }

    /**
     * @param pObservacao the observacao to set
     */
    public void setObservacao(String pObservacao) {
        this.observacao = pObservacao;
    }

    /**
     * @return the complemento
     */
    public String getComplemento() {
        return complemento;
    }

    /**
     * @param pComplemento the complemento to set
     */
    public void setComplemento(String pComplemento) {
        this.complemento = pComplemento;
    }

    /**
     * @return the casa
     */
    public String getCasa() {
        return casa;
    }

    /**
     * @param casa the casa to set
     */
    public void setCasa(String casa) {
        this.casa = casa;
    }

    /**
     * @return the numero
     */
    public int getNumero() {
        return numero;
    }

    /**
     * @param pNumero the numero to set
     */
    public void setNumero(int pNumero) {
        this.numero = pNumero;
    }

    /**
     * @return the nascimento
     */
    public Date getNascimento() {
        return nascimento;
    }

    /**
     * @param pNascimento retorna tipo de variavel de Nascimento
     */
    public void setNascimento(Date pNascimento) {
        this.nascimento = pNascimento;
    }

    /**
     * @return the rg
     */
    public String getRg() {
        return rg;
    }

    /**
     * @param rg the rg to set
     */
    public void setRg(String rg) {
        this.rg = rg;
    }

    /**
     * @return the cnh
     */
    public String getCnh() {
        return cnh;
    }

    /**
     * @param cnh the cnh to set
     */
    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    /**
     * @return the filiacao
     */
    public String getFiliacao() {
        return filiacao;
    }

    /**
     * @param filiacao the filiacao to set
     */
    public void setFiliacao(String filiacao) {
        this.filiacao = filiacao;
    }

    /**
     * @return the vinculo
     */
    public String getVinculo() {
        return vinculo;
    }

    /**
     * @param vinculo the vinculo to set
     */
    public void setVinculo(String vinculo) {
        this.vinculo = vinculo;
    }

    /**
     * @return the ocupacao
     */
    public int getOcupacao() {
        return ocupacao;
    }

    /**
     * @param ocupacao the ocupacao to set
     */
    public void setOcupacao(int ocupacao) {
        this.ocupacao = ocupacao;
    }

    /**
     * @return the data_admissao
     */
    public Date getData_admissao() {
        return data_admissao;
    }

    /**
     * @param data_admissao the data_admissao to set
     */
    public void setData_admissao(Date data_admissao) {
        this.data_admissao = data_admissao;
    }

    /**
     * @return the data_uferias
     */
    public String getData_uferias() {
        return data_uferias;
    }

    /**
     * @param data_uferias the data_uferias to set
     */
    public void setData_uferias(String data_uferias) {
        this.data_uferias = data_uferias;
    }

    /**
     * @return the data_vferias
     */
    public String getData_vferias() {
        return data_vferias;
    }

    /**
     * @param data_vferias the data_vferias to set
     */
    public void setData_vferias(String data_vferias) {
        this.data_vferias = data_vferias;
    }

    /**
     * @return the op_cts
     */
    public String getOp_cts() {
        return op_cts;
    }

    /**
     * @param op_cts the op_cts to set
     */
    public void setOp_cts(String op_cts) {
        this.op_cts = op_cts;
    }

    /**
     * @return the ob_ocupacao
     */
    public String getOb_ocupacao() {
        return ob_ocupacao;
    }

    /**
     * @param ob_ocupacao the ob_ocupacao to set
     */
    public void setOb_ocupacao(String ob_ocupacao) {
        this.ob_ocupacao = ob_ocupacao;
    }

    /**
     * @return the obs_Func
     */
    public String getObs_Func() {
        return obs_Func;
    }

    /**
     * @param obs_Func the obs_Func to set
     */
    public void setObs_Func(String obs_Func) {
        this.obs_Func = obs_Func;
    }

    /**
     * @return the banco_nome
     */
    public Integer getBanco_nome() {
        return banco_nome;
    }

    /**
     * @param banco_nome the banco_nome to set
     */
    public void setBanco_nome(Integer banco_nome) {
        this.banco_nome = banco_nome;
    }

    /**
     * @return the banco_tipo
     */
    public String getBanco_tipo() {
        return banco_tipo;
    }

    /**
     * @param banco_tipo the banco_tipo to set
     */
    public void setBanco_tipo(String banco_tipo) {
        this.banco_tipo = banco_tipo;
    }

    /**
     * @return the banco_agencia
     */
    public String getBanco_agencia() {
        return banco_agencia;
    }

    /**
     * @param banco_agencia the banco_agencia to set
     */
    public void setBanco_agencia(String banco_agencia) {
        this.banco_agencia = banco_agencia;
    }

    /**
     * @return the banco_conta
     */
    public String getBanco_conta() {
        return banco_conta;
    }

    /**
     * @param banco_conta the banco_conta to set
     */
    public void setBanco_conta(String banco_conta) {
        this.banco_conta = banco_conta;
    }

    /**
     * @return the banco_op
     */
    public String getBanco_op() {
        return banco_op;
    }

    /**
     * @param banco_op the banco_op to set
     */
    public void setBanco_op(String banco_op) {
        this.banco_op = banco_op;
    }

    /**
     * @return the banco_obs
     */
    public String getBanco_obs() {
        return banco_obs;
    }

    /**
     * @param banco_obs the banco_obs to set
     */
    public void setBanco_obs(String banco_obs) {
        this.banco_obs = banco_obs;
    }

    @Override
    public String toString() {
        return "ModelFuncionario {" + "::codigo = " + this.getCodigo() + "::nome = " + this.getNome() + "::endereco = " + this.getEndereco() + "::bairro = " + this.getBairro() + "::cidade = " + this.getCodCidade() + "::uf = " + this.getCodEstado() + "::cep = " + this.getCep() + "::telefone = " + this.getTelefone() + "::celular = " + this.getCelular() + "::email= " + this.getEmail() + "::cpf = " + this.getCpf() + "::observacao = " + this.getObservacao() + "::complemento = " + this.getComplemento() + "::numero = " + this.getNumero() + "::nascimento = " + this.getNascimento() + "::rg = " + this.getRg() + "::cnh = " + this.getCnh() + "::filiacao = " + this.getFiliacao() + "::vinculo = " + this.getVinculo() + "::ocupacao = " + this.getOcupacao() + "::data_admissao = " + this.getData_admissao() + "::data_uferias = " + this.getData_uferias() + "::data_vferias = " + this.getData_vferias() + "::op_cts = " + this.getOp_cts() + "::ob_ocupacao = " + this.getOb_ocupacao() + "::obs_Func = " + this.getObs_Func() + "::banco_agencia = " + this.getBanco_conta() + "::banco_op = " + this.getBanco_op() + "::banco_agencia = " + this.getBanco_agencia() + "::banco_tipo = " + this.getBanco_tipo() + "::banco_nome = " + this.getBanco_nome() + "::banco_obs = " + this.getBanco_obs() + "}";
    }

}
