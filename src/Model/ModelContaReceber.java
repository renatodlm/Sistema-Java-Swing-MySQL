package Model;

import java.util.Date;

/**
 *
 * @author studiomicroweb
 */
public class ModelContaReceber {

    /**
     * @return the FuncionarioCheck
     */
    public boolean isFuncionarioCheck() {
        return FuncionarioCheck;
    }

    /**
     * @param FuncionarioCheck the FuncionarioCheck to set
     */
    public void setFuncionarioCheck(boolean FuncionarioCheck) {
        this.FuncionarioCheck = FuncionarioCheck;
    }

    /**
     * @return the banco
     */
    public int getBanco() {
        return banco;
    }

    /**
     * @param banco the banco to set
     */
    public void setBanco(int banco) {
        this.banco = banco;
    }

    /**
     * @return the Repasse
     */
    public boolean getRepasse() {
        return Repasse;
    }

    /**
     * @param Repasse the Repasse to set
     */
    public void setRepasse(boolean Repasse) {
        this.Repasse = Repasse;
    }

    private int codigo;
    private int codigoPessoa;
    private String descricao;
    private String data;
    private String vencimento;
    private String pagamento;
    private int tipoPagamento;
    private String observacao;
    private int situacao;
    private Double valor;
    private String tipoConta;
    private int Processos;
    private boolean Repasse;
    private boolean FuncionarioCheck;
    private int banco;

    /**
     * @return the Processos
     */
    public int getProcessos() {
        return Processos;
    }

    /**
     * @param Processos the Processos to set
     */
    public void setProcessos(int Processos) {
        this.Processos = Processos;
    }

    /**
     * @return the AgenteKmPercorrido
     */
    /**
     * Construtor
     */
    public ModelContaReceber() {
    }

    /**
     * seta o valor de codigo
     *
     * @param pCodigo retorna o codigo
     */
    public void setCodigo(int pCodigo) {
        this.codigo = pCodigo;
    }

    /**
     * return pk_codigo
     *
     * @return retorna o codigo da pessoa envolvida de acordo com a tabela
     * correspondente
     */
    public int getCodigo() {
        return this.codigo;
    }

    /**
     * seta o valor de codigoPessoa
     *
     * @param pCodigoPessoa seta o codigo da pessoa envolvida
     */
    public void setCodigoPessoa(int pCodigoPessoa) {
        this.codigoPessoa = pCodigoPessoa;
    }

    /**
     * return fk_codigoPessoa
     *
     * @return retorna o codigo da pessoa envolvida
     */
    public int getCodigoPessoa() {
        return this.codigoPessoa;
    }

    /**
     * seta o valor de descricao
     *
     * @param pDescricao seta a descrição
     */
    public void setDescricao(String pDescricao) {
        this.descricao = pDescricao;
    }

    /**
     * return descricao
     *
     * @return retorna descrição
     */
    public String getDescricao() {
        return this.descricao;
    }

    /**
     * seta o valor de data
     *
     * @param pData seta a data
     */
    public void setData(String pData) {
        this.data = pData;
    }

    /**
     * return data
     *
     * @return retorna a data
     */
    public String getData() {
        return this.data;
    }

    /**
     * seta o valor de vencimento
     *
     * @param pVencimento seta o vencimento
     */
    public void setVencimento(String pVencimento) {
        this.vencimento = pVencimento;
    }

    /**
     * return vencimento
     *
     * @return retorna o vencimento
     */
    public String getVencimento() {
        return this.vencimento;
    }

    /**
     * seta o valor de pagamento
     *
     * @param pPagamento seta o valor de pagamento
     */
    public void setPagamento(String pPagamento) {
        this.pagamento = pPagamento;
    }

    /**
     * return pagamento
     *
     * @return retona o pagamento
     */
    public String getPagamento() {
        return this.pagamento;
    }

    /**
     * seta o valor de tipoPagamento
     *
     * @param pTipoPagamento seta tipo de pagamento
     */
    public void setTipoPagamento(int pTipoPagamento) {
        this.tipoPagamento = pTipoPagamento;
    }

    /**
     * return fk_tipoPagamento
     *
     * @return retorna tipo de pagamento
     */
    public int getTipoPagamento() {
        return this.tipoPagamento;
    }

    /**
     * seta o valor de observacao
     *
     * @param pObservacao seta observacao
     */
    public void setObservacao(String pObservacao) {
        this.observacao = pObservacao;
    }

    /**
     * return observacao
     *
     * @return retorna observacao
     */
    public String getObservacao() {
        return this.observacao;
    }

    /**
     * seta o valor de situacao
     *
     * @param pSituacao seta sitaução
     */
    public void setSituacao(int pSituacao) {
        this.situacao = pSituacao;
    }

    /**
     * return situacao
     *
     * @return retorna sitaução
     */
    public int isSituacao() {
        return this.situacao;
    }

    /**
     * seta o valor de valor
     *
     * @param pValor seta o valor
     */
    public void setValor(Double pValor) {
        this.valor = pValor;
    }

    /**
     * return valor
     *
     * @return retorna o valor
     */
    public Double getValor() {
        return this.valor;
    }

    /**
     * @return the tipoConta
     */
    public String getTipoConta() {
        return tipoConta;
    }

    /**
     * @param tipoConta the tipoConta to set
     */
    public void setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }

    @Override
    public String toString() {
        return "ModelContaReceber {" + "::codigo = " + this.codigo + "::fk_codigo_pessoa = " + this.codigoPessoa + "::descricao = " + this.descricao + "::data = " + this.data + "::vencimento = " + this.vencimento + "::pagamento = " + this.pagamento + "::fk_tipo_pagamento = " + this.tipoPagamento + "::observacao = " + this.observacao + "::situacao = " + this.situacao + "::valor = " + this.valor + "::Processos = " + this.Processos + "::Repasse = " + this.Repasse + "::banco = " + this.banco + "::FuncionarioCheck = " + this.FuncionarioCheck + "}";
    }
}
