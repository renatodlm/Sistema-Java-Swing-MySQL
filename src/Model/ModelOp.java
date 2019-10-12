/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author studiomicroweb
 */
public class ModelOp {

    /**
     * @return the ValorDespesasTotalRegistro
     */
    public Double getValorDespesasTotalRegistro() {
        return ValorDespesasTotalRegistro;
    }

    /**
     * @param ValorDespesasTotalRegistro the ValorDespesasTotalRegistro to set
     */
    public void setValorDespesasTotalRegistro(Double ValorDespesasTotalRegistro) {
        this.ValorDespesasTotalRegistro = ValorDespesasTotalRegistro;
    }

    /**
     * @return the cod_agente
     */
    public int getCod_agente() {
        return cod_agente;
    }

    /**
     * @param cod_agente the cod_agente to set
     */
    public void setCod_agente(int cod_agente) {
        this.cod_agente = cod_agente;
    }

    private ArrayList<ModelOp> listamModelOp;
    private int codigo;
    private int Seguradoras;
    private int ServicosCodigo;
    private Double ValorTotalHonorariosSemRetencao;
    private String ObsSegurado;
    private int Tipo;
    private String DataProcesso;
    private int Cts;
    private String DataEntrada;
    private String DataSaida;
    private String NomeSegurado;
    private int TipoPagamento;
    private int CodCidade;
    private int CodEstado;
    private String ObsSinistro;
    private String HoraSinistro;
    private String DataSinistro;
    private Double AlicotaPercentualHonorarios;
    private Double PercentuallRetidoReal;
    private String SinistroBairro;
    private String DataEmissaoNFhonorarios;
    private String DataPrevisaoPgtoNFhonorarios;
    private String SituacaoNotaFiscal;
    private Double HonorarioDefinido;
    private String NumeroNFProcesso;
    private String DataPgtoNF;
    private Double ValorNotaRealIndevido;
    private Double ValorNotaRealNegIndevido;
    private Double ValorTotalHonorariosComRetencao;
    private Double ValorHonorariosRetido;
    private Double ValorHonorarioProcesso;
    private Double RetidoReal;
    private Double AlicotaNeg;
    private Double ValorRetidoRetencaoNeg;
    private String DataEmissaoNegativa;
    private String DataPgtoNegativa;
    private String DataPrevisaoPgtoNegativa;
    private String NfNegPrejuizo;
    private Double ValorNegativaTotal;
    private Double ValorRetencaoTotalNeg;
    private Double PercentuallRetidoRealNeg;
    private Double RetidoRealNegativa;
    private String NumeroSinistro;
    private Double Prejuizo;
    private String SeguradoPlaca;
    private String Analista;
    private String ObsHonorariosProcesso;
    private String ObsRetencaoAlicotaIndevido;
    private String ObsRetencaoISSNeg;
    private boolean IndevidoISS;
    private boolean IndevidoISSNegativa;
    private String SituacaoPgtoNegativa;
    private int SituacaoPgtoProcesso;
    private int cod_agente;
    private Double ValorDespesasTotalRegistro;

    //JTABLE TERCEIROS
    private String JTableTerceiroNome;
    private String JTableTerceiroPlaca;
    private String JTableTerceiroObs;

    /**
     * @return the SituacaoPgtoNegativa
     */
    public String getSituacaoPgtoNegativa() {
        return SituacaoPgtoNegativa;
    }

    /**
     * @param SituacaoPgtoNegativa the SituacaoPgtoNegativa to set
     */
    public void setSituacaoPgtoNegativa(String SituacaoPgtoNegativa) {
        this.SituacaoPgtoNegativa = SituacaoPgtoNegativa;
    }

    /**
     * @return the SituacaoPgtoProcesso
     */
    public int getSituacaoPgtoProcesso() {
        return SituacaoPgtoProcesso;
    }

    /**
     * @param SituacaoPgtoProcesso the SituacaoPgtoProcesso to set
     */
    public void setSituacaoPgtoProcesso(int SituacaoPgtoProcesso) {
        this.SituacaoPgtoProcesso = SituacaoPgtoProcesso;
    }

    /**
     * @return the Prejuizo
     */
    public Double getPrejuizo() {
        return Prejuizo;
    }

    /**
     * @param Prejuizo the Prejuizo to set
     */
    public void setPrejuizo(Double Prejuizo) {
        this.Prejuizo = Prejuizo;
    }

    /**
     * @return the JTableTerceiroNome
     */
    public String getJTableTerceiroNome() {
        return JTableTerceiroNome;
    }

    /**
     * @param JTableTerceiroNome the JTableTerceiroNome to set
     */
    public void setJTableTerceiroNome(String JTableTerceiroNome) {
        this.JTableTerceiroNome = JTableTerceiroNome;
    }

    /**
     * @return the JTableTerceiroPlaca
     */
    public String getJTableTerceiroPlaca() {
        return JTableTerceiroPlaca;
    }

    /**
     * @param JTableTerceiroPlaca the JTableTerceiroPlaca to set
     */
    public void setJTableTerceiroPlaca(String JTableTerceiroPlaca) {
        this.JTableTerceiroPlaca = JTableTerceiroPlaca;
    }

    /**
     * @return the JTableTerceiroObs
     */
    public String getJTableTerceiroObs() {
        return JTableTerceiroObs;
    }

    /**
     * @param JTableTerceiroObs the JTableTerceiroObs to set
     */
    public void setJTableTerceiroObs(String JTableTerceiroObs) {
        this.JTableTerceiroObs = JTableTerceiroObs;
    }

    /**
     * @return the NumeroSinistro
     */
    public String getNumeroSinistro() {
        return NumeroSinistro;
    }

    /**
     * @param NumeroSinistro the NumeroSinistro to set
     */
    public void setNumeroSinistro(String NumeroSinistro) {
        this.NumeroSinistro = NumeroSinistro;
    }

    /**
     * @return the listamModelOp
     */
    public ArrayList<ModelOp> getListamModelOp() {
        return listamModelOp;
    }

    /**
     * @param listamModelOp the listamModelOp to set
     */
    public void setListamModelOp(ArrayList<ModelOp> listamModelOp) {
        this.listamModelOp = listamModelOp;
    }

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the Seguradoras
     */
    public int getSeguradoras() {
        return Seguradoras;
    }

    /**
     * @param Seguradoras the Seguradoras to set
     */
    public void setSeguradoras(int Seguradoras) {
        this.Seguradoras = Seguradoras;
    }

    /**
     * @return the ServicosCodigo
     */
    public int getServicosCodigo() {
        return ServicosCodigo;
    }

    /**
     * @param ServicosCodigo the ServicosCodigo to set
     */
    public void setServicosCodigo(int ServicosCodigo) {
        this.ServicosCodigo = ServicosCodigo;
    }

    /**
     * @return the ValorTotalHonorariosSemRetencao
     */
    public Double getValorTotalHonorariosSemRetencao() {
        return ValorTotalHonorariosSemRetencao;
    }

    /**
     * @param ValorTotalHonorariosSemRetencao the
     * ValorTotalHonorariosSemRetencao to set
     */
    public void setValorTotalHonorariosSemRetencao(Double ValorTotalHonorariosSemRetencao) {
        this.ValorTotalHonorariosSemRetencao = ValorTotalHonorariosSemRetencao;
    }

    /**
     * @return the ObsSegurado
     */
    public String getObsSegurado() {
        return ObsSegurado;
    }

    /**
     * @param ObsSegurado the ObsSegurado to set
     */
    public void setObsSegurado(String ObsSegurado) {
        this.ObsSegurado = ObsSegurado;
    }

    /**
     * @return the Tipo
     */
    public int getTipo() {
        return Tipo;
    }

    /**
     * @param Tipo the Tipo to set
     */
    public void setTipo(int Tipo) {
        this.Tipo = Tipo;
    }

    /**
     * @return the DataProcesso
     */
    public String getDataProcesso() {
        return DataProcesso;
    }

    /**
     * @param DataProcesso the DataProcesso to set
     */
    public void setDataProcesso(String DataProcesso) {
        this.DataProcesso = DataProcesso;
    }

    /**
     * @return the Cts
     */
    public int getCts() {
        return Cts;
    }

    /**
     * @param Cts the Cts to set
     */
    public void setCts(int Cts) {
        this.Cts = Cts;
    }

    /**
     * @return the DataEntrada
     */
    public String getDataEntrada() {
        return DataEntrada;
    }

    /**
     * @param DataEntrada the DataEntrada to set
     */
    public void setDataEntrada(String DataEntrada) {
        this.DataEntrada = DataEntrada;
    }

    /**
     * @return the DataSaida
     */
    public String getDataSaida() {
        return DataSaida;
    }

    /**
     * @param DataSaida the DataSaida to set
     */
    public void setDataSaida(String DataSaida) {
        this.DataSaida = DataSaida;
    }

    /**
     * @return the NomeSegurado
     */
    public String getNomeSegurado() {
        return NomeSegurado;
    }

    /**
     * @param NomeSegurado the NomeSegurado to set
     */
    public void setNomeSegurado(String NomeSegurado) {
        this.NomeSegurado = NomeSegurado;
    }

    /**
     * @return the TipoPagamento
     */
    public int getTipoPagamento() {
        return TipoPagamento;
    }

    /**
     * @param TipoPagamento the TipoPagamento to set
     */
    public void setTipoPagamento(int TipoPagamento) {
        this.TipoPagamento = TipoPagamento;
    }

    /**
     * @return the CodCidade
     */
    public int getCodCidade() {
        return CodCidade;
    }

    /**
     * @param CodCidade the CodCidade to set
     */
    public void setCodCidade(int CodCidade) {
        this.CodCidade = CodCidade;
    }

    /**
     * @return the CodEstado
     */
    public int getCodEstado() {
        return CodEstado;
    }

    /**
     * @param CodEstado the CodEstado to set
     */
    public void setCodEstado(int CodEstado) {
        this.CodEstado = CodEstado;
    }

    /**
     * @return the ObsSinistro
     */
    public String getObsSinistro() {
        return ObsSinistro;
    }

    /**
     * @param ObsSinistro the ObsSinistro to set
     */
    public void setObsSinistro(String ObsSinistro) {
        this.ObsSinistro = ObsSinistro;
    }

    /**
     * @return the HoraSinistro
     */
    public String getHoraSinistro() {
        return HoraSinistro;
    }

    /**
     * @param HoraSinistro the HoraSinistro to set
     */
    public void setHoraSinistro(String HoraSinistro) {
        this.HoraSinistro = HoraSinistro;
    }

    /**
     * @return the DataSinistro
     */
    public String getDataSinistro() {
        return DataSinistro;
    }

    /**
     * @param DataSinistro the DataSinistro to set
     */
    public void setDataSinistro(String DataSinistro) {
        this.DataSinistro = DataSinistro;
    }

    /**
     * @return the AlicotaPercentualHonorarios
     */
    public Double getAlicotaPercentualHonorarios() {
        return AlicotaPercentualHonorarios;
    }

    /**
     * @param AlicotaPercentualHonorarios the AlicotaPercentualHonorarios to set
     */
    public void setAlicotaPercentualHonorarios(Double AlicotaPercentualHonorarios) {
        this.AlicotaPercentualHonorarios = AlicotaPercentualHonorarios;
    }

    /**
     * @return the PercentuallRetidoReal
     */
    public Double getPercentuallRetidoReal() {
        return PercentuallRetidoReal;
    }

    /**
     * @param PercentuallRetidoReal the PercentuallRetidoReal to set
     */
    public void setPercentuallRetidoReal(Double PercentuallRetidoReal) {
        this.PercentuallRetidoReal = PercentuallRetidoReal;
    }

    /**
     * @return the SinistroBairro
     */
    public String getSinistroBairro() {
        return SinistroBairro;
    }

    /**
     * @param SinistroBairro the SinistroBairro to set
     */
    public void setSinistroBairro(String SinistroBairro) {
        this.SinistroBairro = SinistroBairro;
    }

    /**
     * @return the DataEmissaoNFhonorarios
     */
    public String getDataEmissaoNFhonorarios() {
        return DataEmissaoNFhonorarios;
    }

    /**
     * @param DataEmissaoNFhonorarios the DataEmissaoNFhonorarios to set
     */
    public void setDataEmissaoNFhonorarios(String DataEmissaoNFhonorarios) {
        this.DataEmissaoNFhonorarios = DataEmissaoNFhonorarios;
    }

    /**
     * @return the DataPrevisaoPgtoNFhonorarios
     */
    public String getDataPrevisaoPgtoNFhonorarios() {
        return DataPrevisaoPgtoNFhonorarios;
    }

    /**
     * @param DataPrevisaoPgtoNFhonorarios the DataPrevisaoPgtoNFhonorarios to
     * set
     */
    public void setDataPrevisaoPgtoNFhonorarios(String DataPrevisaoPgtoNFhonorarios) {
        this.DataPrevisaoPgtoNFhonorarios = DataPrevisaoPgtoNFhonorarios;
    }

    /**
     * @return the SituacaoNotaFiscal
     */
    public String getSituacaoNotaFiscal() {
        return SituacaoNotaFiscal;
    }

    /**
     * @param SituacaoNotaFiscal the SituacaoNotaFiscal to set
     */
    public void setSituacaoNotaFiscal(String SituacaoNotaFiscal) {
        this.SituacaoNotaFiscal = SituacaoNotaFiscal;
    }

    /**
     * @return the HonorarioDefinido
     */
    public Double getHonorarioDefinido() {
        return HonorarioDefinido;
    }

    /**
     * @param HonorarioDefinido the HonorarioDefinido to set
     */
    public void setHonorarioDefinido(Double HonorarioDefinido) {
        this.HonorarioDefinido = HonorarioDefinido;
    }

    /**
     * @return the NumeroNFProcesso
     */
    public String getNumeroNFProcesso() {
        return NumeroNFProcesso;
    }

    /**
     * @param NumeroNFProcesso the NumeroNFProcesso to set
     */
    public void setNumeroNFProcesso(String NumeroNFProcesso) {
        this.NumeroNFProcesso = NumeroNFProcesso;
    }

    /**
     * @return the DataPgtoNF
     */
    public String getDataPgtoNF() {
        return DataPgtoNF;
    }

    /**
     * @param DataPgtoNF the DataPgtoNF to set
     */
    public void setDataPgtoNF(String DataPgtoNF) {
        this.DataPgtoNF = DataPgtoNF;
    }

    /**
     * @return the ValorNotaRealIndevido
     */
    public Double getValorNotaRealIndevido() {
        return ValorNotaRealIndevido;
    }

    /**
     * @param ValorNotaRealIndevido the ValorNotaRealIndevido to set
     */
    public void setValorNotaRealIndevido(Double ValorNotaRealIndevido) {
        this.ValorNotaRealIndevido = ValorNotaRealIndevido;
    }

    /**
     * @return the ValorNotaRealNegIndevido
     */
    public Double getValorNotaRealNegIndevido() {
        return ValorNotaRealNegIndevido;
    }

    /**
     * @param ValorNotaRealNegIndevido the ValorNotaRealNegIndevido to set
     */
    public void setValorNotaRealNegIndevido(Double ValorNotaRealNegIndevido) {
        this.ValorNotaRealNegIndevido = ValorNotaRealNegIndevido;
    }

    /**
     * @return the ValorTotalHonorariosComRetencao
     */
    public Double getValorTotalHonorariosComRetencao() {
        return ValorTotalHonorariosComRetencao;
    }

    /**
     * @param ValorTotalHonorariosComRetencao the
     * ValorTotalHonorariosComRetencao to set
     */
    public void setValorTotalHonorariosComRetencao(Double ValorTotalHonorariosComRetencao) {
        this.ValorTotalHonorariosComRetencao = ValorTotalHonorariosComRetencao;
    }

    /**
     * @return the ValorHonorariosRetido
     */
    public Double getValorHonorariosRetido() {
        return ValorHonorariosRetido;
    }

    /**
     * @param ValorHonorariosRetido the ValorHonorariosRetido to set
     */
    public void setValorHonorariosRetido(Double ValorHonorariosRetido) {
        this.ValorHonorariosRetido = ValorHonorariosRetido;
    }

    /**
     * @return the ValorHonorarioProcesso
     */
    public Double getValorHonorarioProcesso() {
        return ValorHonorarioProcesso;
    }

    /**
     * @param ValorHonorarioProcesso the ValorHonorarioProcesso to set
     */
    public void setValorHonorarioProcesso(Double ValorHonorarioProcesso) {
        this.ValorHonorarioProcesso = ValorHonorarioProcesso;
    }

    /**
     * @return the RetidoReal
     */
    public Double getRetidoReal() {
        return RetidoReal;
    }

    /**
     * @param RetidoReal the RetidoReal to set
     */
    public void setRetidoReal(Double RetidoReal) {
        this.RetidoReal = RetidoReal;
    }

    /**
     * @return the AlicotaNeg
     */
    public Double getAlicotaNeg() {
        return AlicotaNeg;
    }

    /**
     * @param AlicotaNeg the AlicotaNeg to set
     */
    public void setAlicotaNeg(Double AlicotaNeg) {
        this.AlicotaNeg = AlicotaNeg;
    }

    /**
     * @return the ValorRetidoRetencaoNeg
     */
    public Double getValorRetidoRetencaoNeg() {
        return ValorRetidoRetencaoNeg;
    }

    /**
     * @param ValorRetidoRetencaoNeg the ValorRetidoRetencaoNeg to set
     */
    public void setValorRetidoRetencaoNeg(Double ValorRetidoRetencaoNeg) {
        this.ValorRetidoRetencaoNeg = ValorRetidoRetencaoNeg;
    }

    /**
     * @return the DataEmissaoNegativa
     */
    public String getDataEmissaoNegativa() {
        return DataEmissaoNegativa;
    }

    /**
     * @param DataEmissaoNegativa the DataEmissaoNegativa to set
     */
    public void setDataEmissaoNegativa(String DataEmissaoNegativa) {
        this.DataEmissaoNegativa = DataEmissaoNegativa;
    }

    /**
     * @return the DataPgtoNegativa
     */
    public String getDataPgtoNegativa() {
        return DataPgtoNegativa;
    }

    /**
     * @param DataPgtoNegativa the DataPgtoNegativa to set
     */
    public void setDataPgtoNegativa(String DataPgtoNegativa) {
        this.DataPgtoNegativa = DataPgtoNegativa;
    }

    /**
     * @return the DataPrevisaoPgtoNegativa
     */
    public String getDataPrevisaoPgtoNegativa() {
        return DataPrevisaoPgtoNegativa;
    }

    /**
     * @param DataPrevisaoPgtoNegativa the DataPrevisaoPgtoNegativa to set
     */
    public void setDataPrevisaoPgtoNegativa(String DataPrevisaoPgtoNegativa) {
        this.DataPrevisaoPgtoNegativa = DataPrevisaoPgtoNegativa;
    }

    /**
     * @return the NfNegPrejuizo
     */
    public String getNfNegPrejuizo() {
        return NfNegPrejuizo;
    }

    /**
     * @param NfNegPrejuizo the NfNegPrejuizo to set
     */
    public void setNfNegPrejuizo(String NfNegPrejuizo) {
        this.NfNegPrejuizo = NfNegPrejuizo;
    }

    /**
     * @return the ValorNegativaTotal
     */
    public Double getValorNegativaTotal() {
        return ValorNegativaTotal;
    }

    /**
     * @param ValorNegativaTotal the ValorNegativaTotal to set
     */
    public void setValorNegativaTotal(Double ValorNegativaTotal) {
        this.ValorNegativaTotal = ValorNegativaTotal;
    }

    /**
     * @return the ValorRetencaoTotalNeg
     */
    public Double getValorRetencaoTotalNeg() {
        return ValorRetencaoTotalNeg;
    }

    /**
     * @param ValorRetencaoTotalNeg the ValorRetencaoTotalNeg to set
     */
    public void setValorRetencaoTotalNeg(Double ValorRetencaoTotalNeg) {
        this.ValorRetencaoTotalNeg = ValorRetencaoTotalNeg;
    }

    /**
     * @return the PercentuallRetidoRealNeg
     */
    public Double getPercentuallRetidoRealNeg() {
        return PercentuallRetidoRealNeg;
    }

    /**
     * @param PercentuallRetidoRealNeg the PercentuallRetidoRealNeg to set
     */
    public void setPercentuallRetidoRealNeg(Double PercentuallRetidoRealNeg) {
        this.PercentuallRetidoRealNeg = PercentuallRetidoRealNeg;
    }

    /**
     * @return the RetidoRealNegativa
     */
    public Double getRetidoRealNegativa() {
        return RetidoRealNegativa;
    }

    /**
     * @param RetidoRealNegativa the RetidoRealNegativa to set
     */
    public void setRetidoRealNegativa(Double RetidoRealNegativa) {
        this.RetidoRealNegativa = RetidoRealNegativa;
    }

    /**
     * @return the SeguradoPlaca
     */
    public String getSeguradoPlaca() {
        return SeguradoPlaca;
    }

    /**
     * @param SeguradoPlaca the SeguradoPlaca to set
     */
    public void setSeguradoPlaca(String SeguradoPlaca) {
        this.SeguradoPlaca = SeguradoPlaca;
    }

    /**
     * @return the Analista
     */
    public String getAnalista() {
        return Analista;
    }

    /**
     * @param Analista the Analista to set
     */
    public void setAnalista(String Analista) {
        this.Analista = Analista;
    }

    /**
     * @return the ObsHonorariosProcesso
     */
    public String getObsHonorariosProcesso() {
        return ObsHonorariosProcesso;
    }

    /**
     * @param ObsHonorariosProcesso the ObsHonorariosProcesso to set
     */
    public void setObsHonorariosProcesso(String ObsHonorariosProcesso) {
        this.ObsHonorariosProcesso = ObsHonorariosProcesso;
    }

    /**
     * @return the ObsRetencaoAlicotaIndevido
     */
    public String getObsRetencaoAlicotaIndevido() {
        return ObsRetencaoAlicotaIndevido;
    }

    /**
     * @param ObsRetencaoAlicotaIndevido the ObsRetencaoAlicotaIndevido to set
     */
    public void setObsRetencaoAlicotaIndevido(String ObsRetencaoAlicotaIndevido) {
        this.ObsRetencaoAlicotaIndevido = ObsRetencaoAlicotaIndevido;
    }

    /**
     * @return the ObsRetencaoISSNeg
     */
    public String getObsRetencaoISSNeg() {
        return ObsRetencaoISSNeg;
    }

    /**
     * @param ObsRetencaoISSNeg the ObsRetencaoISSNeg to set
     */
    public void setObsRetencaoISSNeg(String ObsRetencaoISSNeg) {
        this.ObsRetencaoISSNeg = ObsRetencaoISSNeg;
    }

    /**
     * @return the IndevidoISS
     */
    public boolean isIndevidoISS() {
        return IndevidoISS;
    }

    /**
     * @param IndevidoISS the IndevidoISS to set
     */
    public void setIndevidoISS(boolean IndevidoISS) {
        this.IndevidoISS = IndevidoISS;
    }

    /**
     * @return the IndevidoISSNegativa
     */
    public boolean isIndevidoISSNegativa() {
        return IndevidoISSNegativa;
    }

    /**
     * @param IndevidoISSNegativa the IndevidoISSNegativa to set
     */
    public void setIndevidoISSNegativa(boolean IndevidoISSNegativa) {
        this.IndevidoISSNegativa = IndevidoISSNegativa;
    }

}
