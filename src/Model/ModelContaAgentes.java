package Model;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author studiomicroweb
 */
public class ModelContaAgentes {

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
    private ArrayList<ModelContaAgentes> listaModelContaAgentes;
    private int codigo;
    private int codigoDespesa;
    private int codigoPessoa;
    private String descricao;
    private String dataDespesa;
    private String dataEnvio;
    private String dataRecebimento;
    private int tipoPagamento;
    private String observacao;
    private int situacao;
    private Double valorAdiantamento;
    private String tipoConta;
    private int Processos;
    private Double ValorHonorarioAgt;
    private int AgenteKmPercorrido;
    private Double ValorPGTOKMSeguradora;
    private Double ValorPGTOtotalKM;
    private Double ValorHRMaisKM;
    private Double ValorAReceber;
    private boolean Repasse;
    private int banco;
    private String DataNeg;
    private Double ValorNeg;

    /**
     * @return the listamModelOp
     */
    public ArrayList<ModelContaAgentes> getListamModelContaAgentes() {
        return listaModelContaAgentes;
    }

    /**
     * @param listamModelContaAgentes the listamModelOp to set
     */
    public void setListamModelContaAgentes(ArrayList<ModelContaAgentes> listamModelContaAgentes) {
        this.listaModelContaAgentes = listamModelContaAgentes;
    }

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
     * @return the ValorHonorarioAgt
     */
    public Double getValorHonorarioAgt() {
        return ValorHonorarioAgt;
    }

    /**
     * @param ValorHonorarioAgt the ValorHonorarioAgt to set
     */
    public void setValorHonorarioAgt(Double ValorHonorarioAgt) {
        this.ValorHonorarioAgt = ValorHonorarioAgt;
    }

    /**
     * @return the AgenteKmPercorrido
     */
    public Integer getAgenteKmPercorrido() {
        return AgenteKmPercorrido;
    }

    /**
     * @param AgenteKmPercorrido the AgenteKmPercorrido to set
     */
    public void setAgenteKmPercorrido(Integer AgenteKmPercorrido) {
        this.AgenteKmPercorrido = AgenteKmPercorrido;
    }

    /**
     * @return the ValorPGTOKMSeguradora
     */
    public Double getValorPGTOKMSeguradora() {
        return ValorPGTOKMSeguradora;
    }

    /**
     * @param ValorPGTOKMSeguradora the ValorPGTOKMSeguradora to set
     */
    public void setValorPGTOKMSeguradora(Double ValorPGTOKMSeguradora) {
        this.ValorPGTOKMSeguradora = ValorPGTOKMSeguradora;
    }

    /**
     * @return the ValorPGTOtotalKM
     */
    public Double getValorPGTOtotalKM() {
        return ValorPGTOtotalKM;
    }

    /**
     * @param ValorPGTOtotalKM the ValorPGTOtotalKM to set
     */
    public void setValorPGTOtotalKM(Double ValorPGTOtotalKM) {
        this.ValorPGTOtotalKM = ValorPGTOtotalKM;
    }

    /**
     * @return the ValorHRMaisKM
     */
    public Double getValorHRMaisKM() {
        return ValorHRMaisKM;
    }

    /**
     * @param ValorHRMaisKM the ValorHRMaisKM to set
     */
    public void setValorHRMaisKM(Double ValorHRMaisKM) {
        this.ValorHRMaisKM = ValorHRMaisKM;
    }

    /**
     * @return the ValorAReceber
     */
    public Double getValorAReceber() {
        return ValorAReceber;
    }

    /**
     * @param ValorAReceber the ValorAReceber to set
     */
    public void setValorAReceber(Double ValorAReceber) {
        this.ValorAReceber = ValorAReceber;
    }

    /**
     * Construtor
     */
    public ModelContaAgentes() {
    }

    /**
     * seta o valorAdiantamento de codigo
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
     * seta o valorAdiantamento de codigoPessoa
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
     * seta o valorAdiantamento de descricao
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
     * seta o valorAdiantamento de dataDespesa
     *
     * @param pData seta a dataDespesa
     */
    public void setDataDespesa(String pData) {
        this.dataDespesa = pData;
    }

    /**
     * return dataDespesa
     *
     * @return retorna a dataDespesa
     */
    public String getDataDespesa() {
        return this.dataDespesa;
    }

    /**
     * seta o valorAdiantamento de dataEnvio
     *
     * @param pVencimento seta o dataEnvio
     */
    public void setDataEnvio(String pVencimento) {
        this.dataEnvio = pVencimento;
    }

    /**
     * return dataEnvio
     *
     * @return retorna o dataEnvio
     */
    public String getDataEnvio() {
        return this.dataEnvio;
    }

    /**
     * seta o valorAdiantamento de dataRecebimento
     *
     * @param pPagamento seta o valorAdiantamento de dataRecebimento
     */
    public void setDataRecebimento(String pPagamento) {
        this.dataRecebimento = pPagamento;
    }

    /**
     * return dataRecebimento
     *
     * @return retona o dataRecebimento
     */
    public String getDataRecebimento() {
        return this.dataRecebimento;
    }

    /**
     * seta o valorAdiantamento de tipoPagamento
     *
     * @param pTipoPagamento seta tipo de dataRecebimento
     */
    public void setTipoPagamento(int pTipoPagamento) {
        this.tipoPagamento = pTipoPagamento;
    }

    /**
     * return fk_tipoPagamento
     *
     * @return retorna tipo de dataRecebimento
     */
    public int getTipoPagamento() {
        return this.tipoPagamento;
    }

    /**
     * seta o valorAdiantamento de observacao
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
     * seta o valorAdiantamento de situacao
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
     * seta o valorAdiantamento de valorAdiantamento
     *
     * @param pValor seta o valorAdiantamento
     */
    public void setValorAdiantamento(Double pValor) {
        this.valorAdiantamento = pValor;
    }

    /**
     * return valorAdiantamento
     *
     * @return retorna o valorAdiantamento
     */
    public Double getValorAdiantamento() {
        return this.valorAdiantamento;
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
        return "ModelContaAgentes {" + "::codigo = " + this.codigo + "::fk_codigo_pessoa = " + this.codigoPessoa + "::descricao = " + this.descricao + "::data = " + this.dataDespesa + "::vencimento = " + this.dataEnvio + "::pagamento = " + this.dataRecebimento + "::fk_tipo_pagamento = " + this.tipoPagamento + "::observacao = " + this.observacao + "::situacao = " + this.situacao + "::valor = " + this.valorAdiantamento + "::Processos = " + this.Processos + "::AgenteHonorario = " + this.ValorHonorarioAgt + "::AgenteKmPercorrido = " + this.AgenteKmPercorrido + "::AgentePtgoPorKMSeguradoraProcessos = " + this.ValorPGTOKMSeguradora + "::PagamentoTotalKM = " + this.ValorPGTOtotalKM + "::AgenteTotalRepasse = " + this.ValorHRMaisKM + "::TotalGeral = " + this.ValorAReceber + "::Repasse = " + this.Repasse + "::banco = " + this.banco + "::DataNeg = " + this.DataNeg + "::ValorNeg = " + this.ValorNeg + "}";
    }

    /**
     * @return the DataNeg
     */
    public String getDataNeg() {
        return DataNeg;
    }

    /**
     * @param DataNeg the DataNeg to set
     */
    public void setDataNeg(String DataNeg) {
        this.DataNeg = DataNeg;
    }

    /**
     * @return the ValorNeg
     */
    public Double getValorNeg() {
        return ValorNeg;
    }

    /**
     * @param ValorNeg the ValorNeg to set
     */
    public void setValorNeg(Double ValorNeg) {
        this.ValorNeg = ValorNeg;
    }

    /**
     * @return the codigoDespesa
     */
    public int getCodigoDespesa() {
        return codigoDespesa;
    }

    /**
     * @param codigoDespesa the codigoDespesa to set
     */
    public void setCodigoDespesa(int codigoDespesa) {
        this.codigoDespesa = codigoDespesa;
    }
}
