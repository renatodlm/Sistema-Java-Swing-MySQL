/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.ModelOp;
import conexao.ConexaoBanco;
import java.sql.Date;
/*import java.util.Date;*/
import java.util.ArrayList;

/**
 *
 * @author work
 */
public class DAOOp extends ConexaoBanco {

    /**
     * grava op
     *
     * @param pModelOp return int
     * @return insert seguradoras
     */
    public int salvarOpDAO(ModelOp pModelOp) {
        try {
            this.conectar();
            return this.insertSQL(
                    "INSERT INTO ordem_processo ("
                    + "Seguradoras,"
                    + "Cod_servico,"
                    + "HonorarioSemRetencao,"
                    + "ObsSegurado,"
                    + "Tipo,"
                    + "DataProcesso,"
                    + "Cts,"
                    + "DataEntrada,"
                    + "DataSaida,"
                    + "NomeSegurado,"
                    + "TipoPagamento,"
                    + "CodCidade,"
                    + "CodEstado,"
                    + "ObsSinistro,"
                    + "HoraSinistro,"
                    + "SisnistroData,"
                    + "AlicotaPercentualHonorarios,"
                    + "PercentuallRetidoReal,"
                    + "SinistroBairro,"
                    + "DataEmissaoNFhonorarios,"
                    + "PrevisãoPgtoNFhonorarios,"
                    + "SituacaoNotaFiscal,"
                    + "HonorarioDefinido,"
                    + "NumeroNFProcesso,"
                    + "DataPgtoNF,"
                    + "ValorNotaRealIndevido,"
                    + "ValorNotaRealNegIndevido,"
                    + "ValorTotalHonorariosComRetencao,"
                    + "ValorHonorariosRetido,"
                    + "ValorHonorarioProcesso,"
                    + "RetidoReal,"
                    + "AlicotaNeg,"
                    + "ValorRetidoRetencaoNeg,"
                    + "DataEmissaoNegativa,"
                    + "DataPgtoNegativa,"
                    + "PrevisaoPgtoNegativa,"
                    + "NfNegPrejuizo,"
                    + "ValorNegativaTotal,"
                    + "ValorRetencaoTotalNeg,"
                    + "PercentuallRetidoRealNeg,"
                    + "RetidoRealNegativa,"
                    + "NumeroSinistro,"
                    + "Prejuizo,"
                    + "Analista,"
                    + "SeguradoPlaca,"
                    + "ObsHonorariosProcesso,"
                    + "ObsRetencaoAlicotaIndevido,"
                    + "ObsRetencaoISSNeg,"
                    + "IndevidoISS,"
                    + "IndevidoISSNegativa,"
                    + "SituacaoPgtoNegativa,"
                    + "SituacaoPgtoProcesso,"
                    + "ValorDespesasTotalRegistro"
                    + ") VALUES ("
                    + "'" + pModelOp.getSeguradoras() + "',"
                    + "'" + pModelOp.getServicosCodigo() + "',"
                    + "'" + pModelOp.getValorTotalHonorariosSemRetencao() + "',"
                    + "'" + pModelOp.getObsSegurado() + "',"
                    + "'" + pModelOp.getTipo() + "',"
                    + "'" + pModelOp.getDataProcesso() + "',"
                    + "'" + pModelOp.getCts() + "',"
                    + "'" + pModelOp.getDataEntrada() + "',"
                    + "'" + pModelOp.getDataSaida() + "',"
                    + "'" + pModelOp.getNomeSegurado() + "',"
                    + "'" + pModelOp.getTipoPagamento() + "',"
                    + "'" + pModelOp.getCodCidade() + "',"
                    + "'" + pModelOp.getCodEstado() + "',"
                    + "'" + pModelOp.getObsSinistro() + "',"
                    + "'" + pModelOp.getHoraSinistro() + "',"
                    + "'" + pModelOp.getDataSinistro() + "',"
                    + "'" + pModelOp.getAlicotaPercentualHonorarios() + "',"
                    + "'" + pModelOp.getPercentuallRetidoReal() + "',"
                    + "'" + pModelOp.getSinistroBairro() + "',"
                    + "'" + pModelOp.getDataEmissaoNFhonorarios() + "',"
                    + "'" + pModelOp.getDataPrevisaoPgtoNFhonorarios() + "',"
                    + "'" + pModelOp.getSituacaoNotaFiscal() + "',"
                    + "'" + pModelOp.getHonorarioDefinido() + "',"
                    + "'" + pModelOp.getNumeroNFProcesso() + "',"
                    + "'" + pModelOp.getDataPgtoNF() + "',"
                    + "'" + pModelOp.getValorNotaRealIndevido() + "',"
                    + "'" + pModelOp.getValorNotaRealNegIndevido() + "',"
                    + "'" + pModelOp.getValorTotalHonorariosComRetencao() + "',"
                    + "'" + pModelOp.getValorHonorariosRetido() + "',"
                    + "'" + pModelOp.getValorHonorarioProcesso() + "',"
                    + "'" + pModelOp.getRetidoReal() + "',"
                    + "'" + pModelOp.getAlicotaNeg() + "',"
                    + "'" + pModelOp.getValorRetidoRetencaoNeg() + "',"
                    + "'" + pModelOp.getDataEmissaoNegativa() + "',"
                    + "'" + pModelOp.getDataPgtoNegativa() + "',"
                    + "'" + pModelOp.getDataPrevisaoPgtoNegativa() + "',"
                    + "'" + pModelOp.getNfNegPrejuizo() + "',"
                    + "'" + pModelOp.getValorNegativaTotal() + "',"
                    + "'" + pModelOp.getValorRetencaoTotalNeg() + "',"
                    + "'" + pModelOp.getPercentuallRetidoRealNeg() + "',"
                    + "'" + pModelOp.getRetidoRealNegativa() + "',"
                    + "'" + pModelOp.getNumeroSinistro() + "',"
                    + "'" + pModelOp.getPrejuizo() + "',"
                    + "'" + pModelOp.getAnalista() + "',"
                    + "'" + pModelOp.getSeguradoPlaca() + "',"
                    + "'" + pModelOp.getObsHonorariosProcesso() + "',"
                    + "'" + pModelOp.getObsRetencaoAlicotaIndevido() + "',"
                    + "'" + pModelOp.getObsRetencaoISSNeg() + "',"
                    + "'" + pModelOp.isIndevidoISS() + "',"
                    + "'" + pModelOp.isIndevidoISSNegativa() + "',"
                    + "'" + pModelOp.getSituacaoPgtoNegativa() + "',"
                    + "'" + pModelOp.getSituacaoPgtoProcesso() + "',"
                    + "'" + pModelOp.getValorDespesasTotalRegistro() + "'"
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
     * grava terceiro
     *
     * @param pModelOp insert terceiro
     * @return grava terceiro
     */
    public boolean salvarServicosOpDAO(ModelOp pModelOp) {
        try {
            this.conectar();
            int sizeLista = pModelOp.getListamModelOp().size();
            for (int i = 0; i < sizeLista; i++) {
                this.insertSQL(
                        "insert into terceiros_processos( "
                        + "codigo_servico,"
                        + "cod_ordem_servico,"
                        + "nome,"
                        + "placa,"
                        + "obs) "
                        + " VALUES ("
                        + "'" + pModelOp.getListamModelOp().get(i).getServicosCodigo() + "',"
                        + "'" + pModelOp.getCodigo() + "',"
                        + "'" + pModelOp.getListamModelOp().get(i).getJTableTerceiroNome() + "',"
                        + "'" + pModelOp.getListamModelOp().get(i).getJTableTerceiroPlaca() + "',"
                        + "'" + pModelOp.getListamModelOp().get(i).getJTableTerceiroObs() + "'"
                        + ");"
                );
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            this.fecharConexao();
        }
    }

    /**
     * recupera op
     *
     * @param Codigo return ModelVendas
     * @return recupera op
     */
    public ModelOp getOpDAO(int Codigo) {
        ModelOp modelOp = new ModelOp();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "codigo,"
                    + "Seguradoras,"
                    + "Cod_servico,"
                    + "HonorarioSemRetencao,"
                    + "ObsSegurado,"
                    + "Tipo,"
                    + "DataProcesso,"
                    + "Cts,"
                    + "DataEntrada,"
                    + "DataSaida,"
                    + "NomeSegurado,"
                    + "TipoPagamento,"
                    + "CodCidade,"
                    + "CodEstado,"
                    + "ObsSinistro,"
                    + "HoraSinistro,"
                    + "SisnistroData,"
                    + "AlicotaPercentualHonorarios,"
                    + "PercentuallRetidoReal,"
                    + "SinistroBairro,"
                    + "DataEmissaoNFhonorarios,"
                    + "PrevisãoPgtoNFhonorarios,"
                    + "SituacaoNotaFiscal,"
                    + "HonorarioDefinido,"
                    + "NumeroNFProcesso,"
                    + "DataPgtoNF,"
                    + "ValorNotaRealIndevido,"
                    + "ValorNotaRealNegIndevido,"
                    + "ValorTotalHonorariosComRetencao,"
                    + "ValorHonorariosRetido,"
                    + "ValorHonorarioProcesso,"
                    + "RetidoReal,"
                    + "AlicotaNeg,"
                    + "ValorRetidoRetencaoNeg,"
                    + "DataEmissaoNegativa,"
                    + "DataPgtoNegativa,"
                    + "PrevisaoPgtoNegativa,"
                    + "NfNegPrejuizo,"
                    + "ValorNegativaTotal,"
                    + "ValorRetencaoTotalNeg,"
                    + "PercentuallRetidoRealNeg,"
                    + "RetidoRealNegativa,"
                    + "NumeroSinistro,"
                    + "Prejuizo,"
                    + "Analista,"
                    + "SeguradoPlaca,"
                    + "ObsHonorariosProcesso,"
                    + "ObsRetencaoAlicotaIndevido,"
                    + "ObsRetencaoISSNeg,"
                    + "IndevidoISS,"
                    + "IndevidoISSNegativa,"
                    + "SituacaoPgtoNegativa,"
                    + "SituacaoPgtoProcesso,"
                    + "ValorDespesasTotalRegistro"
                    + " FROM"
                    + " ordem_processo "
                    + " WHERE"
                    + " codigo = '" + Codigo + "'"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelOp.setCodigo(this.getResultSet().getInt(1));
                modelOp.setSeguradoras(this.getResultSet().getInt(2));
                modelOp.setServicosCodigo(this.getResultSet().getInt(3));
                modelOp.setValorTotalHonorariosSemRetencao(this.getResultSet().getDouble(4));
                modelOp.setObsSegurado(this.getResultSet().getString(5));
                modelOp.setTipo(this.getResultSet().getInt(6));
                modelOp.setDataProcesso(this.getResultSet().getString(7));
                modelOp.setCts(this.getResultSet().getInt(8));
                modelOp.setDataEntrada(this.getResultSet().getString(9));
                modelOp.setDataSaida(this.getResultSet().getString(10));
                modelOp.setNomeSegurado(this.getResultSet().getString(11));
                modelOp.setTipoPagamento(this.getResultSet().getInt(12));
                modelOp.setCodCidade(this.getResultSet().getInt(13));
                modelOp.setCodEstado(this.getResultSet().getInt(14));
                modelOp.setObsSinistro(this.getResultSet().getString(15));
                modelOp.setHoraSinistro(this.getResultSet().getString(16));
                modelOp.setDataSinistro(this.getResultSet().getString(17));
                modelOp.setAlicotaPercentualHonorarios(this.getResultSet().getDouble(18));
                modelOp.setPercentuallRetidoReal(this.getResultSet().getDouble(19));
                modelOp.setSinistroBairro(this.getResultSet().getString(20));
                modelOp.setDataEmissaoNFhonorarios(this.getResultSet().getString(21));
                modelOp.setDataPrevisaoPgtoNFhonorarios(this.getResultSet().getString(22));
                modelOp.setSituacaoNotaFiscal(this.getResultSet().getString(23));
                modelOp.setHonorarioDefinido(this.getResultSet().getDouble(24));
                modelOp.setNumeroNFProcesso(this.getResultSet().getString(25));
                modelOp.setDataPgtoNF(this.getResultSet().getString(26));
                modelOp.setValorNotaRealIndevido(this.getResultSet().getDouble(27));
                modelOp.setValorNotaRealNegIndevido(this.getResultSet().getDouble(28));
                modelOp.setValorTotalHonorariosComRetencao(this.getResultSet().getDouble(29));
                modelOp.setValorHonorariosRetido(this.getResultSet().getDouble(30));
                modelOp.setValorHonorarioProcesso(this.getResultSet().getDouble(31));
                modelOp.setRetidoReal(this.getResultSet().getDouble(32));
                modelOp.setAlicotaNeg(this.getResultSet().getDouble(33));
                modelOp.setValorRetidoRetencaoNeg(this.getResultSet().getDouble(34));
                modelOp.setDataEmissaoNegativa(this.getResultSet().getString(35));
                modelOp.setDataPgtoNegativa(this.getResultSet().getString(36));
                modelOp.setDataPrevisaoPgtoNegativa(this.getResultSet().getString(37));
                modelOp.setNfNegPrejuizo(this.getResultSet().getString(38));
                modelOp.setValorNegativaTotal(this.getResultSet().getDouble(39));
                modelOp.setValorRetencaoTotalNeg(this.getResultSet().getDouble(40));
                modelOp.setPercentuallRetidoRealNeg(this.getResultSet().getDouble(41));
                modelOp.setRetidoRealNegativa(this.getResultSet().getDouble(42));
                modelOp.setNumeroSinistro(this.getResultSet().getString(43));
                modelOp.setPrejuizo(this.getResultSet().getDouble(44));
                modelOp.setAnalista(this.getResultSet().getString(45));
                modelOp.setSeguradoPlaca(this.getResultSet().getString(46));
                modelOp.setObsHonorariosProcesso(this.getResultSet().getString(47));
                modelOp.setObsRetencaoAlicotaIndevido(this.getResultSet().getString(48));
                modelOp.setObsRetencaoISSNeg(this.getResultSet().getString(49));
                modelOp.setIndevidoISS(this.getResultSet().getBoolean(50));
                modelOp.setIndevidoISSNegativa(this.getResultSet().getBoolean(51));
                modelOp.setSituacaoPgtoNegativa(this.getResultSet().getString(52));
                modelOp.setSituacaoPgtoProcesso(this.getResultSet().getInt(53));
                modelOp.setValorDespesasTotalRegistro(this.getResultSet().getDouble(54));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.fecharConexao();
        }
        return modelOp;
    }

    /**
     * recupera uma lista de op return ArrayList
     *
     * @return recupera lista op
     */
    public ArrayList<ModelOp> getListaPedidosDAO() {
        ArrayList<ModelOp> listamodelOp = new ArrayList();
        ModelOp modelOp = new ModelOp();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "codigo,"
                    + "Seguradoras,"
                    + "Cod_servico,"
                    + "HonorarioSemRetencao,"
                    + "ObsSegurado,"
                    + "Tipo,"
                    + "DataProcesso,"
                    + "Cts,"
                    + "DataEntrada,"
                    + "DataSaida,"
                    + "NomeSegurado,"
                    + "TipoPagamento,"
                    + "CodCidade,"
                    + "CodEstado,"
                    + "ObsSinistro,"
                    + "HoraSinistro,"
                    + "SisnistroData,"
                    + "AlicotaPercentualHonorarios,"
                    + "PercentuallRetidoReal,"
                    + "SinistroBairro,"
                    + "DataEmissaoNFhonorarios,"
                    + "PrevisãoPgtoNFhonorarios,"
                    + "SituacaoNotaFiscal,"
                    + "HonorarioDefinido,"
                    + "NumeroNFProcesso,"
                    + "DataPgtoNF,"
                    + "ValorNotaRealIndevido,"
                    + "ValorNotaRealNegIndevido,"
                    + "ValorTotalHonorariosComRetencao,"
                    + "ValorHonorariosRetido,"
                    + "ValorHonorarioProcesso,"
                    + "RetidoReal,"
                    + "AlicotaNeg,"
                    + "ValorRetidoRetencaoNeg,"
                    + "DataEmissaoNegativa,"
                    + "DataPgtoNegativa,"
                    + "PrevisaoPgtoNegativa,"
                    + "NfNegPrejuizo,"
                    + "ValorNegativaTotal,"
                    + "ValorRetencaoTotalNeg,"
                    + "PercentuallRetidoRealNeg,"
                    + "RetidoRealNegativa,"
                    + "NumeroSinistro,"
                    + "Prejuizo,"
                    + "Analista,"
                    + "SeguradoPlaca,"
                    + "ObsHonorariosProcesso,"
                    + "ObsRetencaoAlicotaIndevido,"
                    + "ObsRetencaoISSNeg,"
                    + "IndevidoISS,"
                    + "IndevidoISSNegativa,"
                    + "SituacaoPgtoNegativa,"
                    + "SituacaoPgtoProcesso,"
                    + "ValorDespesasTotalRegistro"
                    + " FROM"
                    + " ordem_processo "
                    + "WHERE tipo = 1 "
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelOp = new ModelOp();
                modelOp.setCodigo(this.getResultSet().getInt(1));
                modelOp.setSeguradoras(this.getResultSet().getInt(2));
                modelOp.setServicosCodigo(this.getResultSet().getInt(3));
                modelOp.setValorTotalHonorariosSemRetencao(this.getResultSet().getDouble(4));
                modelOp.setObsSegurado(this.getResultSet().getString(5));
                modelOp.setTipo(this.getResultSet().getInt(6));
                modelOp.setDataProcesso(this.getResultSet().getString(7));
                modelOp.setCts(this.getResultSet().getInt(8));
                modelOp.setDataEntrada(this.getResultSet().getString(9));
                modelOp.setDataSaida(this.getResultSet().getString(10));
                modelOp.setNomeSegurado(this.getResultSet().getString(11));
                modelOp.setTipoPagamento(this.getResultSet().getInt(12));
                modelOp.setCodCidade(this.getResultSet().getInt(13));
                modelOp.setCodEstado(this.getResultSet().getInt(14));
                modelOp.setObsSinistro(this.getResultSet().getString(15));
                modelOp.setHoraSinistro(this.getResultSet().getString(16));
                modelOp.setDataSinistro(this.getResultSet().getString(17));
                modelOp.setAlicotaPercentualHonorarios(this.getResultSet().getDouble(18));
                modelOp.setPercentuallRetidoReal(this.getResultSet().getDouble(19));
                modelOp.setSinistroBairro(this.getResultSet().getString(20));
                modelOp.setDataEmissaoNFhonorarios(this.getResultSet().getString(21));
                modelOp.setDataPrevisaoPgtoNFhonorarios(this.getResultSet().getString(22));
                modelOp.setSituacaoNotaFiscal(this.getResultSet().getString(23));
                modelOp.setHonorarioDefinido(this.getResultSet().getDouble(24));
                modelOp.setNumeroNFProcesso(this.getResultSet().getString(25));
                modelOp.setDataPgtoNF(this.getResultSet().getString(26));
                modelOp.setValorNotaRealIndevido(this.getResultSet().getDouble(27));
                modelOp.setValorNotaRealNegIndevido(this.getResultSet().getDouble(28));
                modelOp.setValorTotalHonorariosComRetencao(this.getResultSet().getDouble(29));
                modelOp.setValorHonorariosRetido(this.getResultSet().getDouble(30));
                modelOp.setValorHonorarioProcesso(this.getResultSet().getDouble(31));
                modelOp.setRetidoReal(this.getResultSet().getDouble(32));
                modelOp.setAlicotaNeg(this.getResultSet().getDouble(33));
                modelOp.setValorRetidoRetencaoNeg(this.getResultSet().getDouble(34));
                modelOp.setDataEmissaoNegativa(this.getResultSet().getString(35));
                modelOp.setDataPgtoNegativa(this.getResultSet().getString(36));
                modelOp.setDataPrevisaoPgtoNegativa(this.getResultSet().getString(37));
                modelOp.setNfNegPrejuizo(this.getResultSet().getString(38));
                modelOp.setValorNegativaTotal(this.getResultSet().getDouble(39));
                modelOp.setValorRetencaoTotalNeg(this.getResultSet().getDouble(40));
                modelOp.setPercentuallRetidoRealNeg(this.getResultSet().getDouble(41));
                modelOp.setRetidoRealNegativa(this.getResultSet().getDouble(42));
                modelOp.setNumeroSinistro(this.getResultSet().getString(43));
                modelOp.setPrejuizo(this.getResultSet().getDouble(44));
                modelOp.setAnalista(this.getResultSet().getString(45));
                modelOp.setSeguradoPlaca(this.getResultSet().getString(46));
                modelOp.setObsHonorariosProcesso(this.getResultSet().getString(47));
                modelOp.setObsRetencaoAlicotaIndevido(this.getResultSet().getString(48));
                modelOp.setObsRetencaoISSNeg(this.getResultSet().getString(49));
                modelOp.setIndevidoISS(this.getResultSet().getBoolean(50));
                modelOp.setIndevidoISSNegativa(this.getResultSet().getBoolean(51));
                modelOp.setSituacaoPgtoNegativa(this.getResultSet().getString(52));
                modelOp.setSituacaoPgtoProcesso(this.getResultSet().getInt(53));
                modelOp.setValorDespesasTotalRegistro(this.getResultSet().getDouble(54));
                listamodelOp.add(modelOp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.fecharConexao();
        }
        return listamodelOp;
    }

    /**
     * recupera uma lista de terceiro return ArrayList
     *
     * @param pCodigo recupera lista terceiro
     * @return recupera lista terceiro
     */
    public ArrayList<ModelOp> getListaOpDAO(int pCodigo) {
        ArrayList<ModelOp> listamodelOp = new ArrayList();
        ModelOp modelOp = new ModelOp();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "codigo_servico,"
                    + "cod_ordem_servico,"
                    + "nome,"
                    + "placa,"
                    + "obs"
                    + " FROM "
                    + " terceiros_processos WHERE cod_ordem_servico = '" + pCodigo + "'"
                    + ";"
            );
            while (this.getResultSet().next()) {
                modelOp = new ModelOp();
                modelOp.setServicosCodigo(this.getResultSet().getInt(1));
                modelOp.setServicosCodigo(this.getResultSet().getInt(2));
                modelOp.setJTableTerceiroNome(this.getResultSet().getString(3));
                modelOp.setJTableTerceiroPlaca(this.getResultSet().getString(4));
                modelOp.setJTableTerceiroObs(this.getResultSet().getString(5));
                listamodelOp.add(modelOp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.fecharConexao();
        }
        return listamodelOp;
    }

    /**
     * recupera uma lista de Ordem Servico return ArrayList
     *
     * @param pModelOp recupera lista op
     * @return recupera lista op
     */
    public ArrayList<ModelOp> getListaPedidosDAO(ModelOp pModelOp) {
        ArrayList<ModelOp> listamodelOp = new ArrayList();
        ModelOp modelOp = new ModelOp();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "codigo,"
                    + "Seguradoras,"
                    + "Cod_servico,"
                    + "HonorarioSemRetencao,"
                    + "ObsSegurado,"
                    + "Tipo,"
                    + "DataProcesso,"
                    + "Cts,"
                    + "DataEntrada,"
                    + "DataSaida,"
                    + "NomeSegurado,"
                    + "TipoPagamento,"
                    + "CodCidade,"
                    + "CodEstado,"
                    + "ObsSinistro,"
                    + "HoraSinistro,"
                    + "SisnistroData,"
                    + "AlicotaPercentualHonorarios,"
                    + "PercentuallRetidoReal,"
                    + "SinistroBairro,"
                    + "DataEmissaoNFhonorarios,"
                    + "PrevisãoPgtoNFhonorarios,"
                    + "SituacaoNotaFiscal,"
                    + "HonorarioDefinido,"
                    + "NumeroNFProcesso,"
                    + "DataPgtoNF,"
                    + "ValorNotaRealIndevido,"
                    + "ValorNotaRealNegIndevido,"
                    + "ValorTotalHonorariosComRetencao,"
                    + "ValorHonorariosRetido,"
                    + "ValorHonorarioProcesso,"
                    + "RetidoReal,"
                    + "AlicotaNeg,"
                    + "ValorRetidoRetencaoNeg,"
                    + "DataEmissaoNegativa,"
                    + "DataPgtoNegativa,"
                    + "PrevisaoPgtoNegativa,"
                    + "NfNegPrejuizo,"
                    + "ValorNegativaTotal,"
                    + "ValorRetencaoTotalNeg,"
                    + "PercentuallRetidoRealNeg,"
                    + "RetidoRealNegativa,"
                    + "NumeroSinistro,"
                    + "Prejuizo,"
                    + "Analista,"
                    + "SeguradoPlaca,"
                    + "ObsHonorariosProcesso,"
                    + "ObsRetencaoAlicotaIndevido,"
                    + "ObsRetencaoISSNeg,"
                    + "IndevidoISS,"
                    + "IndevidoISSNegativa,"
                    + "SituacaoPgtoNegativa,"
                    + "SituacaoPgtoProcesso,"
                    + "ValorDespesasTotalRegistro"
                    + " FROM"
                    + " ordem_processo "
                    + " WHERE"
                    + " DataEntrada = '" + pModelOp.getDataEntrada() + "' AND tipo = 1"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelOp = new ModelOp();
                modelOp.setCodigo(this.getResultSet().getInt(1));
                modelOp.setSeguradoras(this.getResultSet().getInt(2));
                modelOp.setServicosCodigo(this.getResultSet().getInt(3));
                modelOp.setValorTotalHonorariosSemRetencao(this.getResultSet().getDouble(4));
                modelOp.setObsSegurado(this.getResultSet().getString(5));
                modelOp.setTipo(this.getResultSet().getInt(6));
                modelOp.setDataProcesso(this.getResultSet().getString(7));
                modelOp.setCts(this.getResultSet().getInt(8));
                modelOp.setDataEntrada(this.getResultSet().getString(9));
                modelOp.setDataSaida(this.getResultSet().getString(10));
                modelOp.setNomeSegurado(this.getResultSet().getString(11));
                modelOp.setTipoPagamento(this.getResultSet().getInt(12));
                modelOp.setCodCidade(this.getResultSet().getInt(13));
                modelOp.setCodEstado(this.getResultSet().getInt(14));
                modelOp.setObsSinistro(this.getResultSet().getString(15));
                modelOp.setHoraSinistro(this.getResultSet().getString(16));
                modelOp.setDataSinistro(this.getResultSet().getString(17));
                modelOp.setAlicotaPercentualHonorarios(this.getResultSet().getDouble(18));
                modelOp.setPercentuallRetidoReal(this.getResultSet().getDouble(19));
                modelOp.setSinistroBairro(this.getResultSet().getString(20));
                modelOp.setDataEmissaoNFhonorarios(this.getResultSet().getString(21));
                modelOp.setDataPrevisaoPgtoNFhonorarios(this.getResultSet().getString(22));
                modelOp.setSituacaoNotaFiscal(this.getResultSet().getString(23));
                modelOp.setHonorarioDefinido(this.getResultSet().getDouble(24));
                modelOp.setNumeroNFProcesso(this.getResultSet().getString(25));
                modelOp.setDataPgtoNF(this.getResultSet().getString(26));
                modelOp.setValorNotaRealIndevido(this.getResultSet().getDouble(27));
                modelOp.setValorNotaRealNegIndevido(this.getResultSet().getDouble(28));
                modelOp.setValorTotalHonorariosComRetencao(this.getResultSet().getDouble(29));
                modelOp.setValorHonorariosRetido(this.getResultSet().getDouble(30));
                modelOp.setValorHonorarioProcesso(this.getResultSet().getDouble(31));
                modelOp.setRetidoReal(this.getResultSet().getDouble(32));
                modelOp.setAlicotaNeg(this.getResultSet().getDouble(33));
                modelOp.setValorRetidoRetencaoNeg(this.getResultSet().getDouble(34));
                modelOp.setDataEmissaoNegativa(this.getResultSet().getString(35));
                modelOp.setDataPgtoNegativa(this.getResultSet().getString(36));
                modelOp.setDataPrevisaoPgtoNegativa(this.getResultSet().getString(37));
                modelOp.setNfNegPrejuizo(this.getResultSet().getString(38));
                modelOp.setValorNegativaTotal(this.getResultSet().getDouble(39));
                modelOp.setValorRetencaoTotalNeg(this.getResultSet().getDouble(40));
                modelOp.setPercentuallRetidoRealNeg(this.getResultSet().getDouble(41));
                modelOp.setRetidoRealNegativa(this.getResultSet().getDouble(42));
                modelOp.setNumeroSinistro(this.getResultSet().getString(43));
                modelOp.setPrejuizo(this.getResultSet().getDouble(44));
                modelOp.setAnalista(this.getResultSet().getString(45));
                modelOp.setSeguradoPlaca(this.getResultSet().getString(46));
                modelOp.setObsHonorariosProcesso(this.getResultSet().getString(47));
                modelOp.setObsRetencaoAlicotaIndevido(this.getResultSet().getString(48));
                modelOp.setObsRetencaoISSNeg(this.getResultSet().getString(49));
                modelOp.setIndevidoISS(this.getResultSet().getBoolean(50));
                modelOp.setIndevidoISSNegativa(this.getResultSet().getBoolean(51));
                modelOp.setSituacaoPgtoNegativa(this.getResultSet().getString(52));
                modelOp.setSituacaoPgtoProcesso(this.getResultSet().getInt(53));
                modelOp.setValorDespesasTotalRegistro(this.getResultSet().getDouble(54));
                listamodelOp.add(modelOp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.fecharConexao();
        }
        return listamodelOp;
    }

    public ArrayList<ModelOp> getListaOpPesquisa(String tipo, String contendo, int situacao) {
        ArrayList<ModelOp> listamodelOp = new ArrayList();
        ModelOp modelOp = new ModelOp();

        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "codigo,"
                    + "Seguradoras,"
                    + "Cod_servico,"
                    + "HonorarioSemRetencao,"
                    + "ObsSegurado,"
                    + "Tipo,"
                    + "DataProcesso,"
                    + "Cts,"
                    + "DataEntrada,"
                    + "DataSaida,"
                    + "NomeSegurado,"
                    + "TipoPagamento,"
                    + "CodCidade,"
                    + "CodEstado,"
                    + "ObsSinistro,"
                    + "HoraSinistro,"
                    + "SisnistroData,"
                    + "AlicotaPercentualHonorarios,"
                    + "PercentuallRetidoReal,"
                    + "SinistroBairro,"
                    + "DataEmissaoNFhonorarios,"
                    + "PrevisãoPgtoNFhonorarios,"
                    + "SituacaoNotaFiscal,"
                    + "HonorarioDefinido,"
                    + "NumeroNFProcesso,"
                    + "DataPgtoNF,"
                    + "ValorNotaRealIndevido,"
                    + "ValorNotaRealNegIndevido,"
                    + "ValorTotalHonorariosComRetencao,"
                    + "ValorHonorariosRetido,"
                    + "ValorHonorarioProcesso,"
                    + "RetidoReal,"
                    + "AlicotaNeg,"
                    + "ValorRetidoRetencaoNeg,"
                    + "DataEmissaoNegativa,"
                    + "DataPgtoNegativa,"
                    + "PrevisaoPgtoNegativa,"
                    + "NfNegPrejuizo,"
                    + "ValorNegativaTotal,"
                    + "ValorRetencaoTotalNeg,"
                    + "PercentuallRetidoRealNeg,"
                    + "RetidoRealNegativa,"
                    + "NumeroSinistro,"
                    + "Prejuizo,"
                    + "Analista,"
                    + "SeguradoPlaca,"
                    + "ObsHonorariosProcesso,"
                    + "ObsRetencaoAlicotaIndevido,"
                    + "ObsRetencaoISSNeg,"
                    + "IndevidoISS,"
                    + "IndevidoISSNegativa,"
                    + "SituacaoPgtoNegativa,"
                    + "SituacaoPgtoProcesso,"
                    + "ValorDespesasTotalRegistro"
                    + " FROM"
                    + " ordem_processo "
                    + " WHERE " + tipo + " like '%" + contendo + "%'"
                    + "AND Tipo = " + situacao + ""
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelOp = new ModelOp();
                modelOp.setCodigo(this.getResultSet().getInt(1));
                modelOp.setSeguradoras(this.getResultSet().getInt(2));
                modelOp.setServicosCodigo(this.getResultSet().getInt(3));
                modelOp.setValorTotalHonorariosSemRetencao(this.getResultSet().getDouble(4));
                modelOp.setObsSegurado(this.getResultSet().getString(5));
                modelOp.setTipo(this.getResultSet().getInt(6));
                modelOp.setDataProcesso(this.getResultSet().getString(7));
                modelOp.setCts(this.getResultSet().getInt(8));
                modelOp.setDataEntrada(this.getResultSet().getString(9));
                modelOp.setDataSaida(this.getResultSet().getString(10));
                modelOp.setNomeSegurado(this.getResultSet().getString(11));
                modelOp.setTipoPagamento(this.getResultSet().getInt(12));
                modelOp.setCodCidade(this.getResultSet().getInt(13));
                modelOp.setCodEstado(this.getResultSet().getInt(14));
                modelOp.setObsSinistro(this.getResultSet().getString(15));
                modelOp.setHoraSinistro(this.getResultSet().getString(16));
                modelOp.setDataSinistro(this.getResultSet().getString(17));
                modelOp.setAlicotaPercentualHonorarios(this.getResultSet().getDouble(18));
                modelOp.setPercentuallRetidoReal(this.getResultSet().getDouble(19));
                modelOp.setSinistroBairro(this.getResultSet().getString(20));
                modelOp.setDataEmissaoNFhonorarios(this.getResultSet().getString(21));
                modelOp.setDataPrevisaoPgtoNFhonorarios(this.getResultSet().getString(22));
                modelOp.setSituacaoNotaFiscal(this.getResultSet().getString(23));
                modelOp.setHonorarioDefinido(this.getResultSet().getDouble(24));
                modelOp.setNumeroNFProcesso(this.getResultSet().getString(25));
                modelOp.setDataPgtoNF(this.getResultSet().getString(26));
                modelOp.setValorNotaRealIndevido(this.getResultSet().getDouble(27));
                modelOp.setValorNotaRealNegIndevido(this.getResultSet().getDouble(28));
                modelOp.setValorTotalHonorariosComRetencao(this.getResultSet().getDouble(29));
                modelOp.setValorHonorariosRetido(this.getResultSet().getDouble(30));
                modelOp.setValorHonorarioProcesso(this.getResultSet().getDouble(31));
                modelOp.setRetidoReal(this.getResultSet().getDouble(32));
                modelOp.setAlicotaNeg(this.getResultSet().getDouble(33));
                modelOp.setValorRetidoRetencaoNeg(this.getResultSet().getDouble(34));
                modelOp.setDataEmissaoNegativa(this.getResultSet().getString(35));
                modelOp.setDataPgtoNegativa(this.getResultSet().getString(36));
                modelOp.setDataPrevisaoPgtoNegativa(this.getResultSet().getString(37));
                modelOp.setNfNegPrejuizo(this.getResultSet().getString(38));
                modelOp.setValorNegativaTotal(this.getResultSet().getDouble(39));
                modelOp.setValorRetencaoTotalNeg(this.getResultSet().getDouble(40));
                modelOp.setPercentuallRetidoRealNeg(this.getResultSet().getDouble(41));
                modelOp.setRetidoRealNegativa(this.getResultSet().getDouble(42));
                modelOp.setNumeroSinistro(this.getResultSet().getString(43));
                modelOp.setPrejuizo(this.getResultSet().getDouble(44));
                modelOp.setAnalista(this.getResultSet().getString(45));
                modelOp.setSeguradoPlaca(this.getResultSet().getString(46));
                modelOp.setObsHonorariosProcesso(this.getResultSet().getString(47));
                modelOp.setObsRetencaoAlicotaIndevido(this.getResultSet().getString(48));
                modelOp.setObsRetencaoISSNeg(this.getResultSet().getString(49));
                modelOp.setIndevidoISS(this.getResultSet().getBoolean(50));
                modelOp.setIndevidoISSNegativa(this.getResultSet().getBoolean(51));
                modelOp.setSituacaoPgtoNegativa(this.getResultSet().getString(52));
                modelOp.setSituacaoPgtoProcesso(this.getResultSet().getInt(53));
                modelOp.setValorDespesasTotalRegistro(this.getResultSet().getDouble(54));
                listamodelOp.add(modelOp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.fecharConexao();
        }
        return listamodelOp;
    }

    public ArrayList<ModelOp> getListaOpPesquisaConsultas(String tipo, String contendo, int situacao) {
        ArrayList<ModelOp> listamodelOp = new ArrayList();
        ModelOp modelOp = new ModelOp();

        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "codigo,"
                    + "Seguradoras,"
                    + "Cod_servico,"
                    + "HonorarioSemRetencao,"
                    + "ObsSegurado,"
                    + "Tipo,"
                    + "DataProcesso,"
                    + "Cts,"
                    + "DataEntrada,"
                    + "DataSaida,"
                    + "NomeSegurado,"
                    + "TipoPagamento,"
                    + "CodCidade,"
                    + "CodEstado,"
                    + "ObsSinistro,"
                    + "HoraSinistro,"
                    + "SisnistroData,"
                    + "AlicotaPercentualHonorarios,"
                    + "PercentuallRetidoReal,"
                    + "SinistroBairro,"
                    + "DataEmissaoNFhonorarios,"
                    + "PrevisãoPgtoNFhonorarios,"
                    + "SituacaoNotaFiscal,"
                    + "HonorarioDefinido,"
                    + "NumeroNFProcesso,"
                    + "DataPgtoNF,"
                    + "ValorNotaRealIndevido,"
                    + "ValorNotaRealNegIndevido,"
                    + "ValorTotalHonorariosComRetencao,"
                    + "ValorHonorariosRetido,"
                    + "ValorHonorarioProcesso,"
                    + "RetidoReal,"
                    + "AlicotaNeg,"
                    + "ValorRetidoRetencaoNeg,"
                    + "DataEmissaoNegativa,"
                    + "DataPgtoNegativa,"
                    + "PrevisaoPgtoNegativa,"
                    + "NfNegPrejuizo,"
                    + "ValorNegativaTotal,"
                    + "ValorRetencaoTotalNeg,"
                    + "PercentuallRetidoRealNeg,"
                    + "RetidoRealNegativa,"
                    + "NumeroSinistro,"
                    + "Prejuizo,"
                    + "Analista,"
                    + "SeguradoPlaca,"
                    + "ObsHonorariosProcesso,"
                    + "ObsRetencaoAlicotaIndevido,"
                    + "ObsRetencaoISSNeg,"
                    + "IndevidoISS,"
                    + "IndevidoISSNegativa,"
                    + "SituacaoPgtoNegativa,"
                    + "SituacaoPgtoProcesso,"
                    + "ValorDespesasTotalRegistro"
                    + " FROM"
                    + " ordem_processo "
                    + " WHERE " + tipo + " like '%" + contendo + "%'"
                    + "AND Tipo != " + situacao + ""
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelOp = new ModelOp();
                modelOp.setCodigo(this.getResultSet().getInt(1));
                modelOp.setSeguradoras(this.getResultSet().getInt(2));
                modelOp.setServicosCodigo(this.getResultSet().getInt(3));
                modelOp.setValorTotalHonorariosSemRetencao(this.getResultSet().getDouble(4));
                modelOp.setObsSegurado(this.getResultSet().getString(5));
                modelOp.setTipo(this.getResultSet().getInt(6));
                modelOp.setDataProcesso(this.getResultSet().getString(7));
                modelOp.setCts(this.getResultSet().getInt(8));
                modelOp.setDataEntrada(this.getResultSet().getString(9));
                modelOp.setDataSaida(this.getResultSet().getString(10));
                modelOp.setNomeSegurado(this.getResultSet().getString(11));
                modelOp.setTipoPagamento(this.getResultSet().getInt(12));
                modelOp.setCodCidade(this.getResultSet().getInt(13));
                modelOp.setCodEstado(this.getResultSet().getInt(14));
                modelOp.setObsSinistro(this.getResultSet().getString(15));
                modelOp.setHoraSinistro(this.getResultSet().getString(16));
                modelOp.setDataSinistro(this.getResultSet().getString(17));
                modelOp.setAlicotaPercentualHonorarios(this.getResultSet().getDouble(18));
                modelOp.setPercentuallRetidoReal(this.getResultSet().getDouble(19));
                modelOp.setSinistroBairro(this.getResultSet().getString(20));
                modelOp.setDataEmissaoNFhonorarios(this.getResultSet().getString(21));
                modelOp.setDataPrevisaoPgtoNFhonorarios(this.getResultSet().getString(22));
                modelOp.setSituacaoNotaFiscal(this.getResultSet().getString(23));
                modelOp.setHonorarioDefinido(this.getResultSet().getDouble(24));
                modelOp.setNumeroNFProcesso(this.getResultSet().getString(25));
                modelOp.setDataPgtoNF(this.getResultSet().getString(26));
                modelOp.setValorNotaRealIndevido(this.getResultSet().getDouble(27));
                modelOp.setValorNotaRealNegIndevido(this.getResultSet().getDouble(28));
                modelOp.setValorTotalHonorariosComRetencao(this.getResultSet().getDouble(29));
                modelOp.setValorHonorariosRetido(this.getResultSet().getDouble(30));
                modelOp.setValorHonorarioProcesso(this.getResultSet().getDouble(31));
                modelOp.setRetidoReal(this.getResultSet().getDouble(32));
                modelOp.setAlicotaNeg(this.getResultSet().getDouble(33));
                modelOp.setValorRetidoRetencaoNeg(this.getResultSet().getDouble(34));
                modelOp.setDataEmissaoNegativa(this.getResultSet().getString(35));
                modelOp.setDataPgtoNegativa(this.getResultSet().getString(36));
                modelOp.setDataPrevisaoPgtoNegativa(this.getResultSet().getString(37));
                modelOp.setNfNegPrejuizo(this.getResultSet().getString(38));
                modelOp.setValorNegativaTotal(this.getResultSet().getDouble(39));
                modelOp.setValorRetencaoTotalNeg(this.getResultSet().getDouble(40));
                modelOp.setPercentuallRetidoRealNeg(this.getResultSet().getDouble(41));
                modelOp.setRetidoRealNegativa(this.getResultSet().getDouble(42));
                modelOp.setNumeroSinistro(this.getResultSet().getString(43));
                modelOp.setPrejuizo(this.getResultSet().getDouble(44));
                modelOp.setAnalista(this.getResultSet().getString(45));
                modelOp.setSeguradoPlaca(this.getResultSet().getString(46));
                modelOp.setObsHonorariosProcesso(this.getResultSet().getString(47));
                modelOp.setObsRetencaoAlicotaIndevido(this.getResultSet().getString(48));
                modelOp.setObsRetencaoISSNeg(this.getResultSet().getString(49));
                modelOp.setIndevidoISS(this.getResultSet().getBoolean(50));
                modelOp.setIndevidoISSNegativa(this.getResultSet().getBoolean(51));
                modelOp.setSituacaoPgtoNegativa(this.getResultSet().getString(52));
                modelOp.setSituacaoPgtoProcesso(this.getResultSet().getInt(53));
                modelOp.setValorDespesasTotalRegistro(this.getResultSet().getDouble(54));
                listamodelOp.add(modelOp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.fecharConexao();
        }
        return listamodelOp;
    }

    public ArrayList<ModelOp> getListaOpPesquisaConsultasAgenteEspecifico(String tipo, String contendo, int situacao, int situacao2, int AgenteSessao) {
        ArrayList<ModelOp> listamodelOp = new ArrayList();
        ModelOp modelOp = new ModelOp();

        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "codigo,"
                    + "Seguradoras,"
                    + "Cod_servico,"
                    + "HonorarioSemRetencao,"
                    + "ObsSegurado,"
                    + "Tipo,"
                    + "DataProcesso,"
                    + "Cts,"
                    + "DataEntrada,"
                    + "DataSaida,"
                    + "NomeSegurado,"
                    + "TipoPagamento,"
                    + "CodCidade,"
                    + "CodEstado,"
                    + "ObsSinistro,"
                    + "HoraSinistro,"
                    + "SisnistroData,"
                    + "AlicotaPercentualHonorarios,"
                    + "PercentuallRetidoReal,"
                    + "SinistroBairro,"
                    + "DataEmissaoNFhonorarios,"
                    + "PrevisãoPgtoNFhonorarios,"
                    + "SituacaoNotaFiscal,"
                    + "HonorarioDefinido,"
                    + "NumeroNFProcesso,"
                    + "DataPgtoNF,"
                    + "ValorNotaRealIndevido,"
                    + "ValorNotaRealNegIndevido,"
                    + "ValorTotalHonorariosComRetencao,"
                    + "ValorHonorariosRetido,"
                    + "ValorHonorarioProcesso,"
                    + "RetidoReal,"
                    + "AlicotaNeg,"
                    + "ValorRetidoRetencaoNeg,"
                    + "DataEmissaoNegativa,"
                    + "DataPgtoNegativa,"
                    + "PrevisaoPgtoNegativa,"
                    + "NfNegPrejuizo,"
                    + "ValorNegativaTotal,"
                    + "ValorRetencaoTotalNeg,"
                    + "PercentuallRetidoRealNeg,"
                    + "RetidoRealNegativa,"
                    + "NumeroSinistro,"
                    + "Prejuizo,"
                    + "Analista,"
                    + "SeguradoPlaca,"
                    + "ObsHonorariosProcesso,"
                    + "ObsRetencaoAlicotaIndevido,"
                    + "ObsRetencaoISSNeg,"
                    + "IndevidoISS,"
                    + "IndevidoISSNegativa,"
                    + "SituacaoPgtoNegativa,"
                    + "SituacaoPgtoProcesso,"
                    + "ValorDespesasTotalRegistro"
                    + " FROM"
                    + " ordem_processo "
                    + " WHERE " + tipo + " like '%" + contendo + "%' AND ordem_processo.Tipo = " + situacao + " and cod_agente = '" + AgenteSessao + "' or " + tipo + " like '%" + contendo + "%' AND ordem_processo.Tipo = " + situacao2 + " and cod_agente = '" + AgenteSessao + "'"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelOp = new ModelOp();
                modelOp.setCodigo(this.getResultSet().getInt(1));
                modelOp.setSeguradoras(this.getResultSet().getInt(2));
                modelOp.setServicosCodigo(this.getResultSet().getInt(3));
                modelOp.setValorTotalHonorariosSemRetencao(this.getResultSet().getDouble(4));
                modelOp.setObsSegurado(this.getResultSet().getString(5));
                modelOp.setTipo(this.getResultSet().getInt(6));
                modelOp.setDataProcesso(this.getResultSet().getString(7));
                modelOp.setCts(this.getResultSet().getInt(8));
                modelOp.setDataEntrada(this.getResultSet().getString(9));
                modelOp.setDataSaida(this.getResultSet().getString(10));
                modelOp.setNomeSegurado(this.getResultSet().getString(11));
                modelOp.setTipoPagamento(this.getResultSet().getInt(12));
                modelOp.setCodCidade(this.getResultSet().getInt(13));
                modelOp.setCodEstado(this.getResultSet().getInt(14));
                modelOp.setObsSinistro(this.getResultSet().getString(15));
                modelOp.setHoraSinistro(this.getResultSet().getString(16));
                modelOp.setDataSinistro(this.getResultSet().getString(17));
                modelOp.setAlicotaPercentualHonorarios(this.getResultSet().getDouble(18));
                modelOp.setPercentuallRetidoReal(this.getResultSet().getDouble(19));
                modelOp.setSinistroBairro(this.getResultSet().getString(20));
                modelOp.setDataEmissaoNFhonorarios(this.getResultSet().getString(21));
                modelOp.setDataPrevisaoPgtoNFhonorarios(this.getResultSet().getString(22));
                modelOp.setSituacaoNotaFiscal(this.getResultSet().getString(23));
                modelOp.setHonorarioDefinido(this.getResultSet().getDouble(24));
                modelOp.setNumeroNFProcesso(this.getResultSet().getString(25));
                modelOp.setDataPgtoNF(this.getResultSet().getString(26));
                modelOp.setValorNotaRealIndevido(this.getResultSet().getDouble(27));
                modelOp.setValorNotaRealNegIndevido(this.getResultSet().getDouble(28));
                modelOp.setValorTotalHonorariosComRetencao(this.getResultSet().getDouble(29));
                modelOp.setValorHonorariosRetido(this.getResultSet().getDouble(30));
                modelOp.setValorHonorarioProcesso(this.getResultSet().getDouble(31));
                modelOp.setRetidoReal(this.getResultSet().getDouble(32));
                modelOp.setAlicotaNeg(this.getResultSet().getDouble(33));
                modelOp.setValorRetidoRetencaoNeg(this.getResultSet().getDouble(34));
                modelOp.setDataEmissaoNegativa(this.getResultSet().getString(35));
                modelOp.setDataPgtoNegativa(this.getResultSet().getString(36));
                modelOp.setDataPrevisaoPgtoNegativa(this.getResultSet().getString(37));
                modelOp.setNfNegPrejuizo(this.getResultSet().getString(38));
                modelOp.setValorNegativaTotal(this.getResultSet().getDouble(39));
                modelOp.setValorRetencaoTotalNeg(this.getResultSet().getDouble(40));
                modelOp.setPercentuallRetidoRealNeg(this.getResultSet().getDouble(41));
                modelOp.setRetidoRealNegativa(this.getResultSet().getDouble(42));
                modelOp.setNumeroSinistro(this.getResultSet().getString(43));
                modelOp.setPrejuizo(this.getResultSet().getDouble(44));
                modelOp.setAnalista(this.getResultSet().getString(45));
                modelOp.setSeguradoPlaca(this.getResultSet().getString(46));
                modelOp.setObsHonorariosProcesso(this.getResultSet().getString(47));
                modelOp.setObsRetencaoAlicotaIndevido(this.getResultSet().getString(48));
                modelOp.setObsRetencaoISSNeg(this.getResultSet().getString(49));
                modelOp.setIndevidoISS(this.getResultSet().getBoolean(50));
                modelOp.setIndevidoISSNegativa(this.getResultSet().getBoolean(51));
                modelOp.setSituacaoPgtoNegativa(this.getResultSet().getString(52));
                modelOp.setSituacaoPgtoProcesso(this.getResultSet().getInt(53));
                modelOp.setValorDespesasTotalRegistro(this.getResultSet().getDouble(54));
                listamodelOp.add(modelOp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.fecharConexao();
        }
        return listamodelOp;
    }

    public ArrayList<ModelOp> getListaOpPesquisaAbertos(String tipo, String contendo) {
        ArrayList<ModelOp> listamodelOp = new ArrayList();
        ModelOp modelOp = new ModelOp();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "codigo,"
                    + "Seguradoras,"
                    + "Cod_servico,"
                    + "HonorarioSemRetencao,"
                    + "ObsSegurado,"
                    + "Tipo,"
                    + "DataProcesso,"
                    + "Cts,"
                    + "DataEntrada,"
                    + "DataSaida,"
                    + "NomeSegurado,"
                    + "TipoPagamento,"
                    + "CodCidade,"
                    + "CodEstado,"
                    + "ObsSinistro,"
                    + "HoraSinistro,"
                    + "SisnistroData,"
                    + "AlicotaPercentualHonorarios,"
                    + "PercentuallRetidoReal,"
                    + "SinistroBairro,"
                    + "DataEmissaoNFhonorarios,"
                    + "PrevisãoPgtoNFhonorarios,"
                    + "SituacaoNotaFiscal,"
                    + "HonorarioDefinido,"
                    + "NumeroNFProcesso,"
                    + "DataPgtoNF,"
                    + "ValorNotaRealIndevido,"
                    + "ValorNotaRealNegIndevido,"
                    + "ValorTotalHonorariosComRetencao,"
                    + "ValorHonorariosRetido,"
                    + "ValorHonorarioProcesso,"
                    + "RetidoReal,"
                    + "AlicotaNeg,"
                    + "ValorRetidoRetencaoNeg,"
                    + "DataEmissaoNegativa,"
                    + "DataPgtoNegativa,"
                    + "PrevisaoPgtoNegativa,"
                    + "NfNegPrejuizo,"
                    + "ValorNegativaTotal,"
                    + "ValorRetencaoTotalNeg,"
                    + "PercentuallRetidoRealNeg,"
                    + "RetidoRealNegativa,"
                    + "NumeroSinistro,"
                    + "Prejuizo,"
                    + "Analista,"
                    + "SeguradoPlaca,"
                    + "ObsHonorariosProcesso,"
                    + "ObsRetencaoAlicotaIndevido,"
                    + "ObsRetencaoISSNeg,"
                    + "IndevidoISS,"
                    + "IndevidoISSNegativa,"
                    + "SituacaoPgtoNegativa,"
                    + "SituacaoPgtoProcesso,"
                    + "ValorDespesasTotalRegistro"
                    + " FROM"
                    + " ordem_processo "
                    + " WHERE " + tipo + " like '%" + contendo + "%'"
                    + " AND ordem_processo.Tipo != 3 ORDER BY ordem_processo.codigo DESC"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelOp = new ModelOp();
                modelOp.setCodigo(this.getResultSet().getInt(1));
                modelOp.setSeguradoras(this.getResultSet().getInt(2));
                modelOp.setServicosCodigo(this.getResultSet().getInt(3));
                modelOp.setValorTotalHonorariosSemRetencao(this.getResultSet().getDouble(4));
                modelOp.setObsSegurado(this.getResultSet().getString(5));
                modelOp.setTipo(this.getResultSet().getInt(6));
                modelOp.setDataProcesso(this.getResultSet().getString(7));
                modelOp.setCts(this.getResultSet().getInt(8));
                modelOp.setDataEntrada(this.getResultSet().getString(9));
                modelOp.setDataSaida(this.getResultSet().getString(10));
                modelOp.setNomeSegurado(this.getResultSet().getString(11));
                modelOp.setTipoPagamento(this.getResultSet().getInt(12));
                modelOp.setCodCidade(this.getResultSet().getInt(13));
                modelOp.setCodEstado(this.getResultSet().getInt(14));
                modelOp.setObsSinistro(this.getResultSet().getString(15));
                modelOp.setHoraSinistro(this.getResultSet().getString(16));
                modelOp.setDataSinistro(this.getResultSet().getString(17));
                modelOp.setAlicotaPercentualHonorarios(this.getResultSet().getDouble(18));
                modelOp.setPercentuallRetidoReal(this.getResultSet().getDouble(19));
                modelOp.setSinistroBairro(this.getResultSet().getString(20));
                modelOp.setDataEmissaoNFhonorarios(this.getResultSet().getString(21));
                modelOp.setDataPrevisaoPgtoNFhonorarios(this.getResultSet().getString(22));
                modelOp.setSituacaoNotaFiscal(this.getResultSet().getString(23));
                modelOp.setHonorarioDefinido(this.getResultSet().getDouble(24));
                modelOp.setNumeroNFProcesso(this.getResultSet().getString(25));
                modelOp.setDataPgtoNF(this.getResultSet().getString(26));
                modelOp.setValorNotaRealIndevido(this.getResultSet().getDouble(27));
                modelOp.setValorNotaRealNegIndevido(this.getResultSet().getDouble(28));
                modelOp.setValorTotalHonorariosComRetencao(this.getResultSet().getDouble(29));
                modelOp.setValorHonorariosRetido(this.getResultSet().getDouble(30));
                modelOp.setValorHonorarioProcesso(this.getResultSet().getDouble(31));
                modelOp.setRetidoReal(this.getResultSet().getDouble(32));
                modelOp.setAlicotaNeg(this.getResultSet().getDouble(33));
                modelOp.setValorRetidoRetencaoNeg(this.getResultSet().getDouble(34));
                modelOp.setDataEmissaoNegativa(this.getResultSet().getString(35));
                modelOp.setDataPgtoNegativa(this.getResultSet().getString(36));
                modelOp.setDataPrevisaoPgtoNegativa(this.getResultSet().getString(37));
                modelOp.setNfNegPrejuizo(this.getResultSet().getString(38));
                modelOp.setValorNegativaTotal(this.getResultSet().getDouble(39));
                modelOp.setValorRetencaoTotalNeg(this.getResultSet().getDouble(40));
                modelOp.setPercentuallRetidoRealNeg(this.getResultSet().getDouble(41));
                modelOp.setRetidoRealNegativa(this.getResultSet().getDouble(42));
                modelOp.setNumeroSinistro(this.getResultSet().getString(43));
                modelOp.setPrejuizo(this.getResultSet().getDouble(44));
                modelOp.setAnalista(this.getResultSet().getString(45));
                modelOp.setSeguradoPlaca(this.getResultSet().getString(46));
                modelOp.setObsHonorariosProcesso(this.getResultSet().getString(47));
                modelOp.setObsRetencaoAlicotaIndevido(this.getResultSet().getString(48));
                modelOp.setObsRetencaoISSNeg(this.getResultSet().getString(49));
                modelOp.setIndevidoISS(this.getResultSet().getBoolean(50));
                modelOp.setIndevidoISSNegativa(this.getResultSet().getBoolean(51));
                modelOp.setSituacaoPgtoNegativa(this.getResultSet().getString(52));
                modelOp.setSituacaoPgtoProcesso(this.getResultSet().getInt(53));
                modelOp.setValorDespesasTotalRegistro(this.getResultSet().getDouble(54));
                listamodelOp.add(modelOp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.fecharConexao();
        }
        return listamodelOp;
    }

    public boolean pagarReceberProcessoDAO(ModelOp pModelOp) {
        try {
            this.conectar();
            return this.executarUpdateDeleteSQL(
                    "UPDATE ordem_processo SET "
                    + "Tipo = '" + pModelOp.getTipo() + "',"
                    + "TipoPagamento = '" + pModelOp.getTipoPagamento() + "',"
                    + "SinistroBairro = '" + pModelOp.getSinistroBairro() + "'"
                    + " WHERE "
                    + "codigo = '" + pModelOp.getCodigo() + "'"
                    + ";"
            );
        } catch (Exception e) {
            return false;
        } finally {
            this.fecharConexao();
        }
    }

    public ArrayList<ModelOp> getProcessosRecebidosDAO(int pStatus) {
        ArrayList<ModelOp> listamodelOp = new ArrayList();
        ModelOp modelOp = new ModelOp();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "codigo,"
                    + "Seguradoras,"
                    + "Cod_servico,"
                    + "HonorarioSemRetencao,"
                    + "ObsSegurado,"
                    + "Tipo,"
                    + "DataProcesso,"
                    + "Cts,"
                    + "DataEntrada,"
                    + "DataSaida,"
                    + "NomeSegurado,"
                    + "TipoPagamento,"
                    + "CodCidade,"
                    + "CodEstado,"
                    + "ObsSinistro,"
                    + "HoraSinistro,"
                    + "SisnistroData,"
                    + "AlicotaPercentualHonorarios,"
                    + "PercentuallRetidoReal,"
                    + "SinistroBairro,"
                    + "DataEmissaoNFhonorarios,"
                    + "PrevisãoPgtoNFhonorarios,"
                    + "SituacaoNotaFiscal,"
                    + "HonorarioDefinido,"
                    + "NumeroNFProcesso,"
                    + "DataPgtoNF,"
                    + "ValorNotaRealIndevido,"
                    + "ValorNotaRealNegIndevido,"
                    + "ValorTotalHonorariosComRetencao,"
                    + "ValorHonorariosRetido,"
                    + "ValorHonorarioProcesso,"
                    + "RetidoReal,"
                    + "AlicotaNeg,"
                    + "ValorRetidoRetencaoNeg,"
                    + "DataEmissaoNegativa,"
                    + "DataPgtoNegativa,"
                    + "PrevisaoPgtoNegativa,"
                    + "NfNegPrejuizo,"
                    + "ValorNegativaTotal,"
                    + "ValorRetencaoTotalNeg,"
                    + "PercentuallRetidoRealNeg,"
                    + "RetidoRealNegativa,"
                    + "NumeroSinistro,"
                    + "Prejuizo,"
                    + "Analista,"
                    + "SeguradoPlaca,"
                    + "ObsHonorariosProcesso,"
                    + "ObsRetencaoAlicotaIndevido,"
                    + "ObsRetencaoISSNeg,"
                    + "IndevidoISS,"
                    + "IndevidoISSNegativa,"
                    + "SituacaoPgtoNegativa,"
                    + "SituacaoPgtoProcesso,"
                    + "ValorDespesasTotalRegistro,"
                    + "cod_agente"
                    + " FROM"
                    + " ordem_processo "
                    + " WHERE"
                    + " Tipo = '" + pStatus + "'"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelOp = new ModelOp();
                modelOp.setCodigo(this.getResultSet().getInt(1));
                modelOp.setSeguradoras(this.getResultSet().getInt(2));
                modelOp.setServicosCodigo(this.getResultSet().getInt(3));
                modelOp.setValorTotalHonorariosSemRetencao(this.getResultSet().getDouble(4));
                modelOp.setObsSegurado(this.getResultSet().getString(5));
                modelOp.setTipo(this.getResultSet().getInt(6));
                modelOp.setDataProcesso(this.getResultSet().getString(7));
                modelOp.setCts(this.getResultSet().getInt(8));
                modelOp.setDataEntrada(this.getResultSet().getString(9));
                modelOp.setDataSaida(this.getResultSet().getString(10));
                modelOp.setNomeSegurado(this.getResultSet().getString(11));
                modelOp.setTipoPagamento(this.getResultSet().getInt(12));
                modelOp.setCodCidade(this.getResultSet().getInt(13));
                modelOp.setCodEstado(this.getResultSet().getInt(14));
                modelOp.setObsSinistro(this.getResultSet().getString(15));
                modelOp.setHoraSinistro(this.getResultSet().getString(16));
                modelOp.setDataSinistro(this.getResultSet().getString(17));
                modelOp.setAlicotaPercentualHonorarios(this.getResultSet().getDouble(18));
                modelOp.setPercentuallRetidoReal(this.getResultSet().getDouble(19));
                modelOp.setSinistroBairro(this.getResultSet().getString(20));
                modelOp.setDataEmissaoNFhonorarios(this.getResultSet().getString(21));
                modelOp.setDataPrevisaoPgtoNFhonorarios(this.getResultSet().getString(22));
                modelOp.setSituacaoNotaFiscal(this.getResultSet().getString(23));
                modelOp.setHonorarioDefinido(this.getResultSet().getDouble(24));
                modelOp.setNumeroNFProcesso(this.getResultSet().getString(25));
                modelOp.setDataPgtoNF(this.getResultSet().getString(26));
                modelOp.setValorNotaRealIndevido(this.getResultSet().getDouble(27));
                modelOp.setValorNotaRealNegIndevido(this.getResultSet().getDouble(28));
                modelOp.setValorTotalHonorariosComRetencao(this.getResultSet().getDouble(29));
                modelOp.setValorHonorariosRetido(this.getResultSet().getDouble(30));
                modelOp.setValorHonorarioProcesso(this.getResultSet().getDouble(31));
                modelOp.setRetidoReal(this.getResultSet().getDouble(32));
                modelOp.setAlicotaNeg(this.getResultSet().getDouble(33));
                modelOp.setValorRetidoRetencaoNeg(this.getResultSet().getDouble(34));
                modelOp.setDataEmissaoNegativa(this.getResultSet().getString(35));
                modelOp.setDataPgtoNegativa(this.getResultSet().getString(36));
                modelOp.setDataPrevisaoPgtoNegativa(this.getResultSet().getString(37));
                modelOp.setNfNegPrejuizo(this.getResultSet().getString(38));
                modelOp.setValorNegativaTotal(this.getResultSet().getDouble(39));
                modelOp.setValorRetencaoTotalNeg(this.getResultSet().getDouble(40));
                modelOp.setPercentuallRetidoRealNeg(this.getResultSet().getDouble(41));
                modelOp.setRetidoRealNegativa(this.getResultSet().getDouble(42));
                modelOp.setNumeroSinistro(this.getResultSet().getString(43));
                modelOp.setPrejuizo(this.getResultSet().getDouble(44));
                modelOp.setAnalista(this.getResultSet().getString(45));
                modelOp.setSeguradoPlaca(this.getResultSet().getString(46));
                modelOp.setObsHonorariosProcesso(this.getResultSet().getString(47));
                modelOp.setObsRetencaoAlicotaIndevido(this.getResultSet().getString(48));
                modelOp.setObsRetencaoISSNeg(this.getResultSet().getString(49));
                modelOp.setIndevidoISS(this.getResultSet().getBoolean(50));
                modelOp.setIndevidoISSNegativa(this.getResultSet().getBoolean(51));
                modelOp.setSituacaoPgtoNegativa(this.getResultSet().getString(52));
                modelOp.setSituacaoPgtoProcesso(this.getResultSet().getInt(53));
                modelOp.setValorDespesasTotalRegistro(this.getResultSet().getDouble(54));
                modelOp.setCod_agente(this.getResultSet().getInt(55));
                listamodelOp.add(modelOp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.fecharConexao();
        }
        return listamodelOp;
    }

    public ArrayList<ModelOp> getProcessosRecebidosAguardandoPgtoDAO(int pStatus, int pStatus2) {
        ArrayList<ModelOp> listamodelOp = new ArrayList();
        ModelOp modelOp = new ModelOp();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "codigo,"
                    + "Seguradoras,"
                    + "Cod_servico,"
                    + "HonorarioSemRetencao,"
                    + "ObsSegurado,"
                    + "Tipo,"
                    + "DataProcesso,"
                    + "Cts,"
                    + "DataEntrada,"
                    + "DataSaida,"
                    + "NomeSegurado,"
                    + "TipoPagamento,"
                    + "CodCidade,"
                    + "CodEstado,"
                    + "ObsSinistro,"
                    + "HoraSinistro,"
                    + "SisnistroData,"
                    + "AlicotaPercentualHonorarios,"
                    + "PercentuallRetidoReal,"
                    + "SinistroBairro,"
                    + "DataEmissaoNFhonorarios,"
                    + "PrevisãoPgtoNFhonorarios,"
                    + "SituacaoNotaFiscal,"
                    + "HonorarioDefinido,"
                    + "NumeroNFProcesso,"
                    + "DataPgtoNF,"
                    + "ValorNotaRealIndevido,"
                    + "ValorNotaRealNegIndevido,"
                    + "ValorTotalHonorariosComRetencao,"
                    + "ValorHonorariosRetido,"
                    + "ValorHonorarioProcesso,"
                    + "RetidoReal,"
                    + "AlicotaNeg,"
                    + "ValorRetidoRetencaoNeg,"
                    + "DataEmissaoNegativa,"
                    + "DataPgtoNegativa,"
                    + "PrevisaoPgtoNegativa,"
                    + "NfNegPrejuizo,"
                    + "ValorNegativaTotal,"
                    + "ValorRetencaoTotalNeg,"
                    + "PercentuallRetidoRealNeg,"
                    + "RetidoRealNegativa,"
                    + "NumeroSinistro,"
                    + "Prejuizo,"
                    + "Analista,"
                    + "SeguradoPlaca,"
                    + "ObsHonorariosProcesso,"
                    + "ObsRetencaoAlicotaIndevido,"
                    + "ObsRetencaoISSNeg,"
                    + "IndevidoISS,"
                    + "IndevidoISSNegativa,"
                    + "SituacaoPgtoNegativa,"
                    + "SituacaoPgtoProcesso,"
                    + "ValorDespesasTotalRegistro,"
                    + "cod_agente"
                    + " FROM"
                    + " ordem_processo "
                    + " WHERE"
                    + " Tipo = '" + pStatus + "' or Tipo = '" + pStatus2 + "'"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelOp = new ModelOp();
                modelOp.setCodigo(this.getResultSet().getInt(1));
                modelOp.setSeguradoras(this.getResultSet().getInt(2));
                modelOp.setServicosCodigo(this.getResultSet().getInt(3));
                modelOp.setValorTotalHonorariosSemRetencao(this.getResultSet().getDouble(4));
                modelOp.setObsSegurado(this.getResultSet().getString(5));
                modelOp.setTipo(this.getResultSet().getInt(6));
                modelOp.setDataProcesso(this.getResultSet().getString(7));
                modelOp.setCts(this.getResultSet().getInt(8));
                modelOp.setDataEntrada(this.getResultSet().getString(9));
                modelOp.setDataSaida(this.getResultSet().getString(10));
                modelOp.setNomeSegurado(this.getResultSet().getString(11));
                modelOp.setTipoPagamento(this.getResultSet().getInt(12));
                modelOp.setCodCidade(this.getResultSet().getInt(13));
                modelOp.setCodEstado(this.getResultSet().getInt(14));
                modelOp.setObsSinistro(this.getResultSet().getString(15));
                modelOp.setHoraSinistro(this.getResultSet().getString(16));
                modelOp.setDataSinistro(this.getResultSet().getString(17));
                modelOp.setAlicotaPercentualHonorarios(this.getResultSet().getDouble(18));
                modelOp.setPercentuallRetidoReal(this.getResultSet().getDouble(19));
                modelOp.setSinistroBairro(this.getResultSet().getString(20));
                modelOp.setDataEmissaoNFhonorarios(this.getResultSet().getString(21));
                modelOp.setDataPrevisaoPgtoNFhonorarios(this.getResultSet().getString(22));
                modelOp.setSituacaoNotaFiscal(this.getResultSet().getString(23));
                modelOp.setHonorarioDefinido(this.getResultSet().getDouble(24));
                modelOp.setNumeroNFProcesso(this.getResultSet().getString(25));
                modelOp.setDataPgtoNF(this.getResultSet().getString(26));
                modelOp.setValorNotaRealIndevido(this.getResultSet().getDouble(27));
                modelOp.setValorNotaRealNegIndevido(this.getResultSet().getDouble(28));
                modelOp.setValorTotalHonorariosComRetencao(this.getResultSet().getDouble(29));
                modelOp.setValorHonorariosRetido(this.getResultSet().getDouble(30));
                modelOp.setValorHonorarioProcesso(this.getResultSet().getDouble(31));
                modelOp.setRetidoReal(this.getResultSet().getDouble(32));
                modelOp.setAlicotaNeg(this.getResultSet().getDouble(33));
                modelOp.setValorRetidoRetencaoNeg(this.getResultSet().getDouble(34));
                modelOp.setDataEmissaoNegativa(this.getResultSet().getString(35));
                modelOp.setDataPgtoNegativa(this.getResultSet().getString(36));
                modelOp.setDataPrevisaoPgtoNegativa(this.getResultSet().getString(37));
                modelOp.setNfNegPrejuizo(this.getResultSet().getString(38));
                modelOp.setValorNegativaTotal(this.getResultSet().getDouble(39));
                modelOp.setValorRetencaoTotalNeg(this.getResultSet().getDouble(40));
                modelOp.setPercentuallRetidoRealNeg(this.getResultSet().getDouble(41));
                modelOp.setRetidoRealNegativa(this.getResultSet().getDouble(42));
                modelOp.setNumeroSinistro(this.getResultSet().getString(43));
                modelOp.setPrejuizo(this.getResultSet().getDouble(44));
                modelOp.setAnalista(this.getResultSet().getString(45));
                modelOp.setSeguradoPlaca(this.getResultSet().getString(46));
                modelOp.setObsHonorariosProcesso(this.getResultSet().getString(47));
                modelOp.setObsRetencaoAlicotaIndevido(this.getResultSet().getString(48));
                modelOp.setObsRetencaoISSNeg(this.getResultSet().getString(49));
                modelOp.setIndevidoISS(this.getResultSet().getBoolean(50));
                modelOp.setIndevidoISSNegativa(this.getResultSet().getBoolean(51));
                modelOp.setSituacaoPgtoNegativa(this.getResultSet().getString(52));
                modelOp.setSituacaoPgtoProcesso(this.getResultSet().getInt(53));
                modelOp.setValorDespesasTotalRegistro(this.getResultSet().getDouble(54));
                modelOp.setCod_agente(this.getResultSet().getInt(55));
                listamodelOp.add(modelOp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.fecharConexao();
        }
        return listamodelOp;
    }

    /**
     * atualiza Op
     *
     * @param pModelOp atualiza op
     * @return atualiza op
     */
    public boolean atualizarOpDAO(ModelOp pModelOp) {
        try {
            this.conectar();
            this.executarUpdateDeleteSQL(
                    "UPDATE ordem_processo SET "
                    + "codigo = '" + pModelOp.getCodigo() + "',"
                    + "Seguradoras = '" + pModelOp.getSeguradoras() + "',"
                    + "HonorarioSemRetencao = '" + pModelOp.getValorTotalHonorariosSemRetencao() + "',"
                    + "ObsSegurado = '" + pModelOp.getObsSegurado() + "',"
                    + "Cts = '" + pModelOp.getCts() + "',"
                    + "DataEntrada = '" + pModelOp.getDataEntrada() + "',"
                    + "DataSaida = '" + pModelOp.getDataSaida() + "',"
                    + "NomeSegurado = '" + pModelOp.getNomeSegurado() + "',"
                    + "TipoPagamento = '" + pModelOp.getTipoPagamento() + "',"
                    + "CodCidade = '" + pModelOp.getCodCidade() + "',"
                    + "CodEstado = '" + pModelOp.getCodEstado() + "',"
                    + "ObsSinistro = '" + pModelOp.getObsSinistro() + "',"
                    + "HoraSinistro = '" + pModelOp.getHoraSinistro() + "',"
                    + "SisnistroData = '" + pModelOp.getDataSinistro() + "',"
                    + "AlicotaPercentualHonorarios = '" + pModelOp.getAlicotaPercentualHonorarios() + "',"
                    + "PercentuallRetidoReal = '" + pModelOp.getPercentuallRetidoReal() + "',"
                    + "SinistroBairro = '" + pModelOp.getSinistroBairro() + "',"
                    + "DataEmissaoNFhonorarios = '" + pModelOp.getDataEmissaoNFhonorarios() + "',"
                    + "PrevisãoPgtoNFhonorarios = '" + pModelOp.getDataPrevisaoPgtoNFhonorarios() + "',"
                    + "SituacaoNotaFiscal = '" + pModelOp.getSituacaoNotaFiscal() + "',"
                    + "HonorarioDefinido = '" + pModelOp.getHonorarioDefinido() + "',"
                    + "NumeroNFProcesso = '" + pModelOp.getNumeroNFProcesso() + "',"
                    + "DataPgtoNF = '" + pModelOp.getDataPgtoNF() + "',"
                    + "ValorNotaRealIndevido = '" + pModelOp.getValorNotaRealIndevido() + "',"
                    + "ValorNotaRealNegIndevido = '" + pModelOp.getValorNotaRealNegIndevido() + "',"
                    + "ValorTotalHonorariosComRetencao = '" + pModelOp.getValorTotalHonorariosComRetencao() + "',"
                    + "ValorHonorariosRetido = '" + pModelOp.getValorHonorariosRetido() + "',"
                    + "ValorHonorarioProcesso = '" + pModelOp.getValorHonorarioProcesso() + "',"
                    + "RetidoReal = '" + pModelOp.getRetidoReal() + "',"
                    + "AlicotaNeg = '" + pModelOp.getAlicotaNeg() + "',"
                    + "ValorRetidoRetencaoNeg = '" + pModelOp.getValorRetidoRetencaoNeg() + "',"
                    + "DataEmissaoNegativa = '" + pModelOp.getDataEmissaoNegativa() + "',"
                    + "DataPgtoNegativa = '" + pModelOp.getDataPgtoNegativa() + "',"
                    + "PrevisaoPgtoNegativa = '" + pModelOp.getDataPrevisaoPgtoNegativa() + "',"
                    + "NfNegPrejuizo = '" + pModelOp.getNfNegPrejuizo() + "',"
                    + "ValorNegativaTotal = '" + pModelOp.getValorNegativaTotal() + "',"
                    + "ValorRetencaoTotalNeg = '" + pModelOp.getValorRetencaoTotalNeg() + "',"
                    + "PercentuallRetidoRealNeg = '" + pModelOp.getPercentuallRetidoRealNeg() + "',"
                    + "RetidoRealNegativa = '" + pModelOp.getRetidoReal() + "',"
                    + "NumeroSinistro = '" + pModelOp.getNumeroSinistro() + "',"
                    + "Prejuizo = '" + pModelOp.getPrejuizo() + "',"
                    + "Analista = '" + pModelOp.getAnalista() + "',"
                    + "SeguradoPlaca = '" + pModelOp.getSeguradoPlaca() + "',"
                    + "ObsHonorariosProcesso = '" + pModelOp.getObsHonorariosProcesso() + "',"
                    + "ObsRetencaoAlicotaIndevido = '" + pModelOp.getObsRetencaoAlicotaIndevido() + "',"
                    + "ObsRetencaoISSNeg = '" + pModelOp.getObsRetencaoISSNeg() + "',"
                    + "IndevidoISS = '" + pModelOp.isIndevidoISS() + "',"
                    + "IndevidoISSNegativa = '" + pModelOp.isIndevidoISSNegativa() + "',"
                    + "SituacaoPgtoNegativa = '" + pModelOp.getSituacaoPgtoNegativa() + "',"
                    + "SituacaoPgtoProcesso = '" + pModelOp.getSituacaoPgtoProcesso() + "',"
                    + "Cod_servico = '" + pModelOp.getServicosCodigo() + "',"
                    + "ValorDespesasTotalRegistro = '" + pModelOp.getValorDespesasTotalRegistro() + "'"
                    + " WHERE "
                    + "codigo = '" + pModelOp.getCodigo() + "'"
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

    /**
     * atualiza Op
     *
     * @param pModelOp atualiza op
     * @return atualiza op
     */
    public boolean designarAgenteOpDAO(ModelOp pModelOp) {
        try {
            this.conectar();
            this.executarUpdateDeleteSQL(
                    "UPDATE ordem_processo SET "
                    + "codigo = '" + pModelOp.getCodigo() + "',"
                    + "Tipo = '" + pModelOp.getTipo() + "',"
                    + "	TipoPagamento = '" + pModelOp.getTipoPagamento() + "',"
                    + "cod_agente = '" + pModelOp.getCod_agente() + "'"
                    + " WHERE "
                    + "codigo = '" + pModelOp.getCodigo() + "'"
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

    public boolean finalizarProcessoOpDAO(ModelOp pModelOp) {
        try {
            this.conectar();
            this.executarUpdateDeleteSQL(
                    "UPDATE ordem_processo SET "
                    + "codigo = '" + pModelOp.getCodigo() + "',"
                    + "Tipo = '" + pModelOp.getTipo() + "',"
                    + "	TipoPagamento = '" + pModelOp.getTipoPagamento() + "'"
                    + " WHERE "
                    + "codigo = '" + pModelOp.getCodigo() + "'"
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

    /**
     * desaprovar op
     *
     * @param pCodigo return boolean
     * @return desaprovas
     */
    public boolean desaprovarPedidoDAO(int pCodigo) {
        try {
            this.conectar();
            this.executarUpdateDeleteSQL(
                    "UPDATE ordem_processo SET "
                    + "tipo = 0 "
                    + " WHERE "
                    + "codigo = '" + pCodigo + "'"
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

    /**
     * exclui Op
     *
     * @param pCodigo return boolean
     * @return exclui op setando pra 0
     */
    public boolean excluirOpDAO(int pCodigo) {
        try {
            this.conectar();
            return this.executarUpdateDeleteSQL(
                    "UPDATE ordem_processo SET "
                    + "tipo = 0 "
                    + " WHERE "
                    + "codigo = '" + pCodigo + "'"
                    + ";"
            );
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            this.fecharConexao();
        }
    }

    public boolean excluirServicosOpDAO(int pCodigo) {
        try {
            this.conectar();
            this.executarUpdateDeleteSQL(
                    "DELETE FROM terceiros_processos WHERE "
                    + "cod_ordem_servico = '" + pCodigo + "'"
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

    /**
     * recupera uma lista de Op return ArrayList
     *
     * @param pCodigo recupera lista op por codigo
     * @return recupera lista op por codigo
     */
    public ArrayList<ModelOp> getListaOpServicosDAO(int pCodigo) {
        ArrayList<ModelOp> listamodelOp = new ArrayList();
        ModelOp modelOp = new ModelOp();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "codigo_servico,"
                    + "cod_ordem_servico,"
                    + " FROM "
                    + " vendas_produto WHERE codigo_venda = '" + pCodigo + "'"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelOp = new ModelOp();
                modelOp.setServicosCodigo(this.getResultSet().getInt(1));
                modelOp.setCodigo(this.getResultSet().getInt(2));
                listamodelOp.add(modelOp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.fecharConexao();
        }
        return listamodelOp;
    }

    public ArrayList<ModelOp> ListaProcessosPorDatas(Date pDataInicial, Date dataFinal) {
        ArrayList<ModelOp> listamodelOp = new ArrayList();
        ModelOp modelOp = new ModelOp();
        try {
            this.conectar();
            this.executarSQL("SELECT "
                    + "codigo,"
                    + "Seguradoras,"
                    + "Cod_servico,"
                    + "HonorarioSemRetencao,"
                    + "ObsSegurado,"
                    + "Tipo,"
                    + "DataProcesso,"
                    + "Cts,"
                    + "DataEntrada,"
                    + "DataSaida,"
                    + "NomeSegurado,"
                    + "TipoPagamento,"
                    + "CodCidade,"
                    + "CodEstado,"
                    + "ObsSinistro,"
                    + "HoraSinistro,"
                    + "SisnistroData,"
                    + "AlicotaPercentualHonorarios,"
                    + "PercentuallRetidoReal,"
                    + "SinistroBairro,"
                    + "DataEmissaoNFhonorarios,"
                    + "PrevisãoPgtoNFhonorarios,"
                    + "SituacaoNotaFiscal,"
                    + "HonorarioDefinido,"
                    + "NumeroNFProcesso,"
                    + "DataPgtoNF,"
                    + "ValorNotaRealIndevido,"
                    + "ValorNotaRealNegIndevido,"
                    + "ValorTotalHonorariosComRetencao,"
                    + "ValorHonorariosRetido,"
                    + "ValorHonorarioProcesso,"
                    + "RetidoReal,"
                    + "AlicotaNeg,"
                    + "ValorRetidoRetencaoNeg,"
                    + "DataEmissaoNegativa,"
                    + "DataPgtoNegativa,"
                    + "PrevisaoPgtoNegativa,"
                    + "NfNegPrejuizo,"
                    + "ValorNegativaTotal,"
                    + "ValorRetencaoTotalNeg,"
                    + "PercentuallRetidoRealNeg,"
                    + "RetidoRealNegativa,"
                    + "NumeroSinistro,"
                    + "Prejuizo,"
                    + "Analista,"
                    + "SeguradoPlaca,"
                    + "ObsHonorariosProcesso,"
                    + "ObsRetencaoAlicotaIndevido,"
                    + "ObsRetencaoISSNeg,"
                    + "IndevidoISS,"
                    + "IndevidoISSNegativa,"
                    + "SituacaoPgtoNegativa,"
                    + "SituacaoPgtoProcesso,"
                    + "ValorDespesasTotalRegistro,"
                    + "cod_agente"
                    + " FROM"
                    + " ordem_processo "
                    + "WHERE"
                    + "(DataEntrada BETWEEN  '" + pDataInicial + "' AND '" + dataFinal + "' )"
                    + " and Tipo = 3"
                    + ";"
            );
            while (this.getResultSet().next()) {
                modelOp = new ModelOp();
                modelOp.setCodigo(this.getResultSet().getInt(1));
                modelOp.setSeguradoras(this.getResultSet().getInt(2));
                modelOp.setServicosCodigo(this.getResultSet().getInt(3));
                modelOp.setValorTotalHonorariosSemRetencao(this.getResultSet().getDouble(4));
                modelOp.setObsSegurado(this.getResultSet().getString(5));
                modelOp.setTipo(this.getResultSet().getInt(6));
                modelOp.setDataProcesso(this.getResultSet().getString(7));
                modelOp.setCts(this.getResultSet().getInt(8));
                modelOp.setDataEntrada(this.getResultSet().getString(9));
                modelOp.setDataSaida(this.getResultSet().getString(10));
                modelOp.setNomeSegurado(this.getResultSet().getString(11));
                modelOp.setTipoPagamento(this.getResultSet().getInt(12));
                modelOp.setCodCidade(this.getResultSet().getInt(13));
                modelOp.setCodEstado(this.getResultSet().getInt(14));
                modelOp.setObsSinistro(this.getResultSet().getString(15));
                modelOp.setHoraSinistro(this.getResultSet().getString(16));
                modelOp.setDataSinistro(this.getResultSet().getString(17));
                modelOp.setAlicotaPercentualHonorarios(this.getResultSet().getDouble(18));
                modelOp.setPercentuallRetidoReal(this.getResultSet().getDouble(19));
                modelOp.setSinistroBairro(this.getResultSet().getString(20));
                modelOp.setDataEmissaoNFhonorarios(this.getResultSet().getString(21));
                modelOp.setDataPrevisaoPgtoNFhonorarios(this.getResultSet().getString(22));
                modelOp.setSituacaoNotaFiscal(this.getResultSet().getString(23));
                modelOp.setHonorarioDefinido(this.getResultSet().getDouble(24));
                modelOp.setNumeroNFProcesso(this.getResultSet().getString(25));
                modelOp.setDataPgtoNF(this.getResultSet().getString(26));
                modelOp.setValorNotaRealIndevido(this.getResultSet().getDouble(27));
                modelOp.setValorNotaRealNegIndevido(this.getResultSet().getDouble(28));
                modelOp.setValorTotalHonorariosComRetencao(this.getResultSet().getDouble(29));
                modelOp.setValorHonorariosRetido(this.getResultSet().getDouble(30));
                modelOp.setValorHonorarioProcesso(this.getResultSet().getDouble(31));
                modelOp.setRetidoReal(this.getResultSet().getDouble(32));
                modelOp.setAlicotaNeg(this.getResultSet().getDouble(33));
                modelOp.setValorRetidoRetencaoNeg(this.getResultSet().getDouble(34));
                modelOp.setDataEmissaoNegativa(this.getResultSet().getString(35));
                modelOp.setDataPgtoNegativa(this.getResultSet().getString(36));
                modelOp.setDataPrevisaoPgtoNegativa(this.getResultSet().getString(37));
                modelOp.setNfNegPrejuizo(this.getResultSet().getString(38));
                modelOp.setValorNegativaTotal(this.getResultSet().getDouble(39));
                modelOp.setValorRetencaoTotalNeg(this.getResultSet().getDouble(40));
                modelOp.setPercentuallRetidoRealNeg(this.getResultSet().getDouble(41));
                modelOp.setRetidoRealNegativa(this.getResultSet().getDouble(42));
                modelOp.setNumeroSinistro(this.getResultSet().getString(43));
                modelOp.setPrejuizo(this.getResultSet().getDouble(44));
                modelOp.setAnalista(this.getResultSet().getString(45));
                modelOp.setSeguradoPlaca(this.getResultSet().getString(46));
                modelOp.setObsHonorariosProcesso(this.getResultSet().getString(47));
                modelOp.setObsRetencaoAlicotaIndevido(this.getResultSet().getString(48));
                modelOp.setObsRetencaoISSNeg(this.getResultSet().getString(49));
                modelOp.setIndevidoISS(this.getResultSet().getBoolean(50));
                modelOp.setIndevidoISSNegativa(this.getResultSet().getBoolean(51));
                modelOp.setSituacaoPgtoNegativa(this.getResultSet().getString(52));
                modelOp.setSituacaoPgtoProcesso(this.getResultSet().getInt(53));
                modelOp.setValorDespesasTotalRegistro(this.getResultSet().getDouble(54));
                modelOp.setCod_agente(this.getResultSet().getInt(55));
                listamodelOp.add(modelOp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.fecharConexao();
        }
        return listamodelOp;
    }

    public ArrayList<ModelOp> FiltraOpDecrescentePorData() {
        ArrayList<ModelOp> listamodelOp = new ArrayList();
        ModelOp modelOp = new ModelOp();
        try {
            this.conectar();
            this.executarSQL("SELECT "
                    + "codigo,"
                    + "Seguradoras,"
                    + "Cod_servico,"
                    + "HonorarioSemRetencao,"
                    + "ObsSegurado,"
                    + "Tipo,"
                    + "DataProcesso,"
                    + "Cts,"
                    + "DataEntrada,"
                    + "DataSaida,"
                    + "NomeSegurado,"
                    + "TipoPagamento,"
                    + "CodCidade,"
                    + "CodEstado,"
                    + "ObsSinistro,"
                    + "HoraSinistro,"
                    + "SisnistroData,"
                    + "AlicotaPercentualHonorarios,"
                    + "PercentuallRetidoReal,"
                    + "SinistroBairro,"
                    + "DataEmissaoNFhonorarios,"
                    + "PrevisãoPgtoNFhonorarios,"
                    + "SituacaoNotaFiscal,"
                    + "HonorarioDefinido,"
                    + "NumeroNFProcesso,"
                    + "DataPgtoNF,"
                    + "ValorNotaRealIndevido,"
                    + "ValorNotaRealNegIndevido,"
                    + "ValorTotalHonorariosComRetencao,"
                    + "ValorHonorariosRetido,"
                    + "ValorHonorarioProcesso,"
                    + "RetidoReal,"
                    + "AlicotaNeg,"
                    + "ValorRetidoRetencaoNeg,"
                    + "DataEmissaoNegativa,"
                    + "DataPgtoNegativa,"
                    + "PrevisaoPgtoNegativa,"
                    + "NfNegPrejuizo,"
                    + "ValorNegativaTotal,"
                    + "ValorRetencaoTotalNeg,"
                    + "PercentuallRetidoRealNeg,"
                    + "RetidoRealNegativa,"
                    + "NumeroSinistro,"
                    + "Prejuizo,"
                    + "Analista,"
                    + "SeguradoPlaca,"
                    + "ObsHonorariosProcesso,"
                    + "ObsRetencaoAlicotaIndevido,"
                    + "ObsRetencaoISSNeg,"
                    + "IndevidoISS,"
                    + "IndevidoISSNegativa,"
                    + "SituacaoPgtoNegativa,"
                    + "SituacaoPgtoProcesso,"
                    + "ValorDespesasTotalRegistro"
                    + " FROM"
                    + " ordem_processo ORDER BY ordem_processo.PrevisãoPgtoNFhonorarios DESC,"
                    + "Tipo = 1"
                    + ";"
            );
            while (this.getResultSet().next()) {
                modelOp = new ModelOp();
                modelOp.setCodigo(this.getResultSet().getInt(1));
                modelOp.setSeguradoras(this.getResultSet().getInt(2));
                modelOp.setServicosCodigo(this.getResultSet().getInt(3));
                modelOp.setValorTotalHonorariosSemRetencao(this.getResultSet().getDouble(4));
                modelOp.setObsSegurado(this.getResultSet().getString(5));
                modelOp.setTipo(this.getResultSet().getInt(6));
                modelOp.setDataProcesso(this.getResultSet().getString(7));
                modelOp.setCts(this.getResultSet().getInt(8));
                modelOp.setDataEntrada(this.getResultSet().getString(9));
                modelOp.setDataSaida(this.getResultSet().getString(10));
                modelOp.setNomeSegurado(this.getResultSet().getString(11));
                modelOp.setTipoPagamento(this.getResultSet().getInt(12));
                modelOp.setCodCidade(this.getResultSet().getInt(13));
                modelOp.setCodEstado(this.getResultSet().getInt(14));
                modelOp.setObsSinistro(this.getResultSet().getString(15));
                modelOp.setHoraSinistro(this.getResultSet().getString(16));
                modelOp.setDataSinistro(this.getResultSet().getString(17));
                modelOp.setAlicotaPercentualHonorarios(this.getResultSet().getDouble(18));
                modelOp.setPercentuallRetidoReal(this.getResultSet().getDouble(19));
                modelOp.setSinistroBairro(this.getResultSet().getString(20));
                modelOp.setDataEmissaoNFhonorarios(this.getResultSet().getString(21));
                modelOp.setDataPrevisaoPgtoNFhonorarios(this.getResultSet().getString(22));
                modelOp.setSituacaoNotaFiscal(this.getResultSet().getString(23));
                modelOp.setHonorarioDefinido(this.getResultSet().getDouble(24));
                modelOp.setNumeroNFProcesso(this.getResultSet().getString(25));
                modelOp.setDataPgtoNF(this.getResultSet().getString(26));
                modelOp.setValorNotaRealIndevido(this.getResultSet().getDouble(27));
                modelOp.setValorNotaRealNegIndevido(this.getResultSet().getDouble(28));
                modelOp.setValorTotalHonorariosComRetencao(this.getResultSet().getDouble(29));
                modelOp.setValorHonorariosRetido(this.getResultSet().getDouble(30));
                modelOp.setValorHonorarioProcesso(this.getResultSet().getDouble(31));
                modelOp.setRetidoReal(this.getResultSet().getDouble(32));
                modelOp.setAlicotaNeg(this.getResultSet().getDouble(33));
                modelOp.setValorRetidoRetencaoNeg(this.getResultSet().getDouble(34));
                modelOp.setDataEmissaoNegativa(this.getResultSet().getString(35));
                modelOp.setDataPgtoNegativa(this.getResultSet().getString(36));
                modelOp.setDataPrevisaoPgtoNegativa(this.getResultSet().getString(37));
                modelOp.setNfNegPrejuizo(this.getResultSet().getString(38));
                modelOp.setValorNegativaTotal(this.getResultSet().getDouble(39));
                modelOp.setValorRetencaoTotalNeg(this.getResultSet().getDouble(40));
                modelOp.setPercentuallRetidoRealNeg(this.getResultSet().getDouble(41));
                modelOp.setRetidoRealNegativa(this.getResultSet().getDouble(42));
                modelOp.setNumeroSinistro(this.getResultSet().getString(43));
                modelOp.setPrejuizo(this.getResultSet().getDouble(44));
                modelOp.setAnalista(this.getResultSet().getString(45));
                modelOp.setSeguradoPlaca(this.getResultSet().getString(46));
                modelOp.setObsHonorariosProcesso(this.getResultSet().getString(47));
                modelOp.setObsRetencaoAlicotaIndevido(this.getResultSet().getString(48));
                modelOp.setObsRetencaoISSNeg(this.getResultSet().getString(49));
                modelOp.setIndevidoISS(this.getResultSet().getBoolean(50));
                modelOp.setIndevidoISSNegativa(this.getResultSet().getBoolean(51));
                modelOp.setSituacaoPgtoNegativa(this.getResultSet().getString(52));
                modelOp.setSituacaoPgtoProcesso(this.getResultSet().getInt(53));
                modelOp.setValorDespesasTotalRegistro(this.getResultSet().getDouble(54));
                listamodelOp.add(modelOp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.fecharConexao();
        }
        return listamodelOp;
    }

    public ArrayList<ModelOp> FiltraOpDecrescente() {
        ArrayList<ModelOp> listamodelOp = new ArrayList();
        ModelOp modelOp = new ModelOp();
        try {
            this.conectar();
            this.executarSQL("SELECT "
                    + "codigo,"
                    + "Seguradoras,"
                    + "Cod_servico,"
                    + "HonorarioSemRetencao,"
                    + "ObsSegurado,"
                    + "Tipo,"
                    + "DataProcesso,"
                    + "Cts,"
                    + "DataEntrada,"
                    + "DataSaida,"
                    + "NomeSegurado,"
                    + "TipoPagamento,"
                    + "CodCidade,"
                    + "CodEstado,"
                    + "ObsSinistro,"
                    + "HoraSinistro,"
                    + "SisnistroData,"
                    + "AlicotaPercentualHonorarios,"
                    + "PercentuallRetidoReal,"
                    + "SinistroBairro,"
                    + "DataEmissaoNFhonorarios,"
                    + "PrevisãoPgtoNFhonorarios,"
                    + "SituacaoNotaFiscal,"
                    + "HonorarioDefinido,"
                    + "NumeroNFProcesso,"
                    + "DataPgtoNF,"
                    + "ValorNotaRealIndevido,"
                    + "ValorNotaRealNegIndevido,"
                    + "ValorTotalHonorariosComRetencao,"
                    + "ValorHonorariosRetido,"
                    + "ValorHonorarioProcesso,"
                    + "RetidoReal,"
                    + "AlicotaNeg,"
                    + "ValorRetidoRetencaoNeg,"
                    + "DataEmissaoNegativa,"
                    + "DataPgtoNegativa,"
                    + "PrevisaoPgtoNegativa,"
                    + "NfNegPrejuizo,"
                    + "ValorNegativaTotal,"
                    + "ValorRetencaoTotalNeg,"
                    + "PercentuallRetidoRealNeg,"
                    + "RetidoRealNegativa,"
                    + "NumeroSinistro,"
                    + "Prejuizo,"
                    + "Analista,"
                    + "SeguradoPlaca,"
                    + "ObsHonorariosProcesso,"
                    + "ObsRetencaoAlicotaIndevido,"
                    + "ObsRetencaoISSNeg,"
                    + "IndevidoISS,"
                    + "IndevidoISSNegativa,"
                    + "SituacaoPgtoNegativa,"
                    + "SituacaoPgtoProcesso,"
                    + "ValorDespesasTotalRegistro,"
                    + "cod_agente"
                    + " FROM"
                    + " ordem_processo WHERE ordem_processo.Tipo != 3 and ordem_processo.Tipo != 0 ORDER BY ordem_processo.codigo DESC"
                    + ";"
            );
            while (this.getResultSet().next()) {
                modelOp = new ModelOp();
                modelOp.setCodigo(this.getResultSet().getInt(1));
                modelOp.setSeguradoras(this.getResultSet().getInt(2));
                modelOp.setServicosCodigo(this.getResultSet().getInt(3));
                modelOp.setValorTotalHonorariosSemRetencao(this.getResultSet().getDouble(4));
                modelOp.setObsSegurado(this.getResultSet().getString(5));
                modelOp.setTipo(this.getResultSet().getInt(6));
                modelOp.setDataProcesso(this.getResultSet().getString(7));
                modelOp.setCts(this.getResultSet().getInt(8));
                modelOp.setDataEntrada(this.getResultSet().getString(9));
                modelOp.setDataSaida(this.getResultSet().getString(10));
                modelOp.setNomeSegurado(this.getResultSet().getString(11));
                modelOp.setTipoPagamento(this.getResultSet().getInt(12));
                modelOp.setCodCidade(this.getResultSet().getInt(13));
                modelOp.setCodEstado(this.getResultSet().getInt(14));
                modelOp.setObsSinistro(this.getResultSet().getString(15));
                modelOp.setHoraSinistro(this.getResultSet().getString(16));
                modelOp.setDataSinistro(this.getResultSet().getString(17));
                modelOp.setAlicotaPercentualHonorarios(this.getResultSet().getDouble(18));
                modelOp.setPercentuallRetidoReal(this.getResultSet().getDouble(19));
                modelOp.setSinistroBairro(this.getResultSet().getString(20));
                modelOp.setDataEmissaoNFhonorarios(this.getResultSet().getString(21));
                modelOp.setDataPrevisaoPgtoNFhonorarios(this.getResultSet().getString(22));
                modelOp.setSituacaoNotaFiscal(this.getResultSet().getString(23));
                modelOp.setHonorarioDefinido(this.getResultSet().getDouble(24));
                modelOp.setNumeroNFProcesso(this.getResultSet().getString(25));
                modelOp.setDataPgtoNF(this.getResultSet().getString(26));
                modelOp.setValorNotaRealIndevido(this.getResultSet().getDouble(27));
                modelOp.setValorNotaRealNegIndevido(this.getResultSet().getDouble(28));
                modelOp.setValorTotalHonorariosComRetencao(this.getResultSet().getDouble(29));
                modelOp.setValorHonorariosRetido(this.getResultSet().getDouble(30));
                modelOp.setValorHonorarioProcesso(this.getResultSet().getDouble(31));
                modelOp.setRetidoReal(this.getResultSet().getDouble(32));
                modelOp.setAlicotaNeg(this.getResultSet().getDouble(33));
                modelOp.setValorRetidoRetencaoNeg(this.getResultSet().getDouble(34));
                modelOp.setDataEmissaoNegativa(this.getResultSet().getString(35));
                modelOp.setDataPgtoNegativa(this.getResultSet().getString(36));
                modelOp.setDataPrevisaoPgtoNegativa(this.getResultSet().getString(37));
                modelOp.setNfNegPrejuizo(this.getResultSet().getString(38));
                modelOp.setValorNegativaTotal(this.getResultSet().getDouble(39));
                modelOp.setValorRetencaoTotalNeg(this.getResultSet().getDouble(40));
                modelOp.setPercentuallRetidoRealNeg(this.getResultSet().getDouble(41));
                modelOp.setRetidoRealNegativa(this.getResultSet().getDouble(42));
                modelOp.setNumeroSinistro(this.getResultSet().getString(43));
                modelOp.setPrejuizo(this.getResultSet().getDouble(44));
                modelOp.setAnalista(this.getResultSet().getString(45));
                modelOp.setSeguradoPlaca(this.getResultSet().getString(46));
                modelOp.setObsHonorariosProcesso(this.getResultSet().getString(47));
                modelOp.setObsRetencaoAlicotaIndevido(this.getResultSet().getString(48));
                modelOp.setObsRetencaoISSNeg(this.getResultSet().getString(49));
                modelOp.setIndevidoISS(this.getResultSet().getBoolean(50));
                modelOp.setIndevidoISSNegativa(this.getResultSet().getBoolean(51));
                modelOp.setSituacaoPgtoNegativa(this.getResultSet().getString(52));
                modelOp.setSituacaoPgtoProcesso(this.getResultSet().getInt(53));
                modelOp.setValorDespesasTotalRegistro(this.getResultSet().getDouble(54));
                modelOp.setCod_agente(this.getResultSet().getInt(55));
                listamodelOp.add(modelOp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.fecharConexao();
        }
        return listamodelOp;
    }

    public ArrayList<ModelOp> FiltraOpDecrescenteConsultas() {
        ArrayList<ModelOp> listamodelOp = new ArrayList();
        ModelOp modelOp = new ModelOp();
        try {
            this.conectar();
            this.executarSQL("SELECT "
                    + "codigo,"
                    + "Seguradoras,"
                    + "Cod_servico,"
                    + "HonorarioSemRetencao,"
                    + "ObsSegurado,"
                    + "Tipo,"
                    + "DataProcesso,"
                    + "Cts,"
                    + "DataEntrada,"
                    + "DataSaida,"
                    + "NomeSegurado,"
                    + "TipoPagamento,"
                    + "CodCidade,"
                    + "CodEstado,"
                    + "ObsSinistro,"
                    + "HoraSinistro,"
                    + "SisnistroData,"
                    + "AlicotaPercentualHonorarios,"
                    + "PercentuallRetidoReal,"
                    + "SinistroBairro,"
                    + "DataEmissaoNFhonorarios,"
                    + "PrevisãoPgtoNFhonorarios,"
                    + "SituacaoNotaFiscal,"
                    + "HonorarioDefinido,"
                    + "NumeroNFProcesso,"
                    + "DataPgtoNF,"
                    + "ValorNotaRealIndevido,"
                    + "ValorNotaRealNegIndevido,"
                    + "ValorTotalHonorariosComRetencao,"
                    + "ValorHonorariosRetido,"
                    + "ValorHonorarioProcesso,"
                    + "RetidoReal,"
                    + "AlicotaNeg,"
                    + "ValorRetidoRetencaoNeg,"
                    + "DataEmissaoNegativa,"
                    + "DataPgtoNegativa,"
                    + "PrevisaoPgtoNegativa,"
                    + "NfNegPrejuizo,"
                    + "ValorNegativaTotal,"
                    + "ValorRetencaoTotalNeg,"
                    + "PercentuallRetidoRealNeg,"
                    + "RetidoRealNegativa,"
                    + "NumeroSinistro,"
                    + "Prejuizo,"
                    + "Analista,"
                    + "SeguradoPlaca,"
                    + "ObsHonorariosProcesso,"
                    + "ObsRetencaoAlicotaIndevido,"
                    + "ObsRetencaoISSNeg,"
                    + "IndevidoISS,"
                    + "IndevidoISSNegativa,"
                    + "SituacaoPgtoNegativa,"
                    + "SituacaoPgtoProcesso,"
                    + "ValorDespesasTotalRegistro,"
                    + "cod_agente"
                    + " FROM"
                    + " ordem_processo WHERE ordem_processo.Tipo != 0 ORDER BY ordem_processo.codigo DESC"
                    + ";"
            );
            while (this.getResultSet().next()) {
                modelOp = new ModelOp();
                modelOp.setCodigo(this.getResultSet().getInt(1));
                modelOp.setSeguradoras(this.getResultSet().getInt(2));
                modelOp.setServicosCodigo(this.getResultSet().getInt(3));
                modelOp.setValorTotalHonorariosSemRetencao(this.getResultSet().getDouble(4));
                modelOp.setObsSegurado(this.getResultSet().getString(5));
                modelOp.setTipo(this.getResultSet().getInt(6));
                modelOp.setDataProcesso(this.getResultSet().getString(7));
                modelOp.setCts(this.getResultSet().getInt(8));
                modelOp.setDataEntrada(this.getResultSet().getString(9));
                modelOp.setDataSaida(this.getResultSet().getString(10));
                modelOp.setNomeSegurado(this.getResultSet().getString(11));
                modelOp.setTipoPagamento(this.getResultSet().getInt(12));
                modelOp.setCodCidade(this.getResultSet().getInt(13));
                modelOp.setCodEstado(this.getResultSet().getInt(14));
                modelOp.setObsSinistro(this.getResultSet().getString(15));
                modelOp.setHoraSinistro(this.getResultSet().getString(16));
                modelOp.setDataSinistro(this.getResultSet().getString(17));
                modelOp.setAlicotaPercentualHonorarios(this.getResultSet().getDouble(18));
                modelOp.setPercentuallRetidoReal(this.getResultSet().getDouble(19));
                modelOp.setSinistroBairro(this.getResultSet().getString(20));
                modelOp.setDataEmissaoNFhonorarios(this.getResultSet().getString(21));
                modelOp.setDataPrevisaoPgtoNFhonorarios(this.getResultSet().getString(22));
                modelOp.setSituacaoNotaFiscal(this.getResultSet().getString(23));
                modelOp.setHonorarioDefinido(this.getResultSet().getDouble(24));
                modelOp.setNumeroNFProcesso(this.getResultSet().getString(25));
                modelOp.setDataPgtoNF(this.getResultSet().getString(26));
                modelOp.setValorNotaRealIndevido(this.getResultSet().getDouble(27));
                modelOp.setValorNotaRealNegIndevido(this.getResultSet().getDouble(28));
                modelOp.setValorTotalHonorariosComRetencao(this.getResultSet().getDouble(29));
                modelOp.setValorHonorariosRetido(this.getResultSet().getDouble(30));
                modelOp.setValorHonorarioProcesso(this.getResultSet().getDouble(31));
                modelOp.setRetidoReal(this.getResultSet().getDouble(32));
                modelOp.setAlicotaNeg(this.getResultSet().getDouble(33));
                modelOp.setValorRetidoRetencaoNeg(this.getResultSet().getDouble(34));
                modelOp.setDataEmissaoNegativa(this.getResultSet().getString(35));
                modelOp.setDataPgtoNegativa(this.getResultSet().getString(36));
                modelOp.setDataPrevisaoPgtoNegativa(this.getResultSet().getString(37));
                modelOp.setNfNegPrejuizo(this.getResultSet().getString(38));
                modelOp.setValorNegativaTotal(this.getResultSet().getDouble(39));
                modelOp.setValorRetencaoTotalNeg(this.getResultSet().getDouble(40));
                modelOp.setPercentuallRetidoRealNeg(this.getResultSet().getDouble(41));
                modelOp.setRetidoRealNegativa(this.getResultSet().getDouble(42));
                modelOp.setNumeroSinistro(this.getResultSet().getString(43));
                modelOp.setPrejuizo(this.getResultSet().getDouble(44));
                modelOp.setAnalista(this.getResultSet().getString(45));
                modelOp.setSeguradoPlaca(this.getResultSet().getString(46));
                modelOp.setObsHonorariosProcesso(this.getResultSet().getString(47));
                modelOp.setObsRetencaoAlicotaIndevido(this.getResultSet().getString(48));
                modelOp.setObsRetencaoISSNeg(this.getResultSet().getString(49));
                modelOp.setIndevidoISS(this.getResultSet().getBoolean(50));
                modelOp.setIndevidoISSNegativa(this.getResultSet().getBoolean(51));
                modelOp.setSituacaoPgtoNegativa(this.getResultSet().getString(52));
                modelOp.setSituacaoPgtoProcesso(this.getResultSet().getInt(53));
                modelOp.setValorDespesasTotalRegistro(this.getResultSet().getDouble(54));
                modelOp.setCod_agente(this.getResultSet().getInt(55));
                listamodelOp.add(modelOp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.fecharConexao();
        }
        return listamodelOp;
    }

    public ArrayList<ModelOp> getPorAgenteDAO(int pStatus) {
        ArrayList<ModelOp> listamodelOp = new ArrayList();
        ModelOp modelOp = new ModelOp();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "codigo,"
                    + "Seguradoras,"
                    + "Cod_servico,"
                    + "HonorarioSemRetencao,"
                    + "ObsSegurado,"
                    + "Tipo,"
                    + "DataProcesso,"
                    + "Cts,"
                    + "DataEntrada,"
                    + "DataSaida,"
                    + "NomeSegurado,"
                    + "TipoPagamento,"
                    + "CodCidade,"
                    + "CodEstado,"
                    + "ObsSinistro,"
                    + "HoraSinistro,"
                    + "SisnistroData,"
                    + "AlicotaPercentualHonorarios,"
                    + "PercentuallRetidoReal,"
                    + "SinistroBairro,"
                    + "DataEmissaoNFhonorarios,"
                    + "PrevisãoPgtoNFhonorarios,"
                    + "SituacaoNotaFiscal,"
                    + "HonorarioDefinido,"
                    + "NumeroNFProcesso,"
                    + "DataPgtoNF,"
                    + "ValorNotaRealIndevido,"
                    + "ValorNotaRealNegIndevido,"
                    + "ValorTotalHonorariosComRetencao,"
                    + "ValorHonorariosRetido,"
                    + "ValorHonorarioProcesso,"
                    + "RetidoReal,"
                    + "AlicotaNeg,"
                    + "ValorRetidoRetencaoNeg,"
                    + "DataEmissaoNegativa,"
                    + "DataPgtoNegativa,"
                    + "PrevisaoPgtoNegativa,"
                    + "NfNegPrejuizo,"
                    + "ValorNegativaTotal,"
                    + "ValorRetencaoTotalNeg,"
                    + "PercentuallRetidoRealNeg,"
                    + "RetidoRealNegativa,"
                    + "NumeroSinistro,"
                    + "Prejuizo,"
                    + "Analista,"
                    + "SeguradoPlaca,"
                    + "ObsHonorariosProcesso,"
                    + "ObsRetencaoAlicotaIndevido,"
                    + "ObsRetencaoISSNeg,"
                    + "IndevidoISS,"
                    + "IndevidoISSNegativa,"
                    + "SituacaoPgtoNegativa,"
                    + "SituacaoPgtoProcesso,"
                    + "ValorDespesasTotalRegistro"
                    + " FROM"
                    + " ordem_processo "
                    + " WHERE"
                    + " cod_agente = '" + pStatus + "'"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelOp = new ModelOp();
                modelOp.setCodigo(this.getResultSet().getInt(1));
                modelOp.setSeguradoras(this.getResultSet().getInt(2));
                modelOp.setServicosCodigo(this.getResultSet().getInt(3));
                modelOp.setValorTotalHonorariosSemRetencao(this.getResultSet().getDouble(4));
                modelOp.setObsSegurado(this.getResultSet().getString(5));
                modelOp.setTipo(this.getResultSet().getInt(6));
                modelOp.setDataProcesso(this.getResultSet().getString(7));
                modelOp.setCts(this.getResultSet().getInt(8));
                modelOp.setDataEntrada(this.getResultSet().getString(9));
                modelOp.setDataSaida(this.getResultSet().getString(10));
                modelOp.setNomeSegurado(this.getResultSet().getString(11));
                modelOp.setTipoPagamento(this.getResultSet().getInt(12));
                modelOp.setCodCidade(this.getResultSet().getInt(13));
                modelOp.setCodEstado(this.getResultSet().getInt(14));
                modelOp.setObsSinistro(this.getResultSet().getString(15));
                modelOp.setHoraSinistro(this.getResultSet().getString(16));
                modelOp.setDataSinistro(this.getResultSet().getString(17));
                modelOp.setAlicotaPercentualHonorarios(this.getResultSet().getDouble(18));
                modelOp.setPercentuallRetidoReal(this.getResultSet().getDouble(19));
                modelOp.setSinistroBairro(this.getResultSet().getString(20));
                modelOp.setDataEmissaoNFhonorarios(this.getResultSet().getString(21));
                modelOp.setDataPrevisaoPgtoNFhonorarios(this.getResultSet().getString(22));
                modelOp.setSituacaoNotaFiscal(this.getResultSet().getString(23));
                modelOp.setHonorarioDefinido(this.getResultSet().getDouble(24));
                modelOp.setNumeroNFProcesso(this.getResultSet().getString(25));
                modelOp.setDataPgtoNF(this.getResultSet().getString(26));
                modelOp.setValorNotaRealIndevido(this.getResultSet().getDouble(27));
                modelOp.setValorNotaRealNegIndevido(this.getResultSet().getDouble(28));
                modelOp.setValorTotalHonorariosComRetencao(this.getResultSet().getDouble(29));
                modelOp.setValorHonorariosRetido(this.getResultSet().getDouble(30));
                modelOp.setValorHonorarioProcesso(this.getResultSet().getDouble(31));
                modelOp.setRetidoReal(this.getResultSet().getDouble(32));
                modelOp.setAlicotaNeg(this.getResultSet().getDouble(33));
                modelOp.setValorRetidoRetencaoNeg(this.getResultSet().getDouble(34));
                modelOp.setDataEmissaoNegativa(this.getResultSet().getString(35));
                modelOp.setDataPgtoNegativa(this.getResultSet().getString(36));
                modelOp.setDataPrevisaoPgtoNegativa(this.getResultSet().getString(37));
                modelOp.setNfNegPrejuizo(this.getResultSet().getString(38));
                modelOp.setValorNegativaTotal(this.getResultSet().getDouble(39));
                modelOp.setValorRetencaoTotalNeg(this.getResultSet().getDouble(40));
                modelOp.setPercentuallRetidoRealNeg(this.getResultSet().getDouble(41));
                modelOp.setRetidoRealNegativa(this.getResultSet().getDouble(42));
                modelOp.setNumeroSinistro(this.getResultSet().getString(43));
                modelOp.setPrejuizo(this.getResultSet().getDouble(44));
                modelOp.setAnalista(this.getResultSet().getString(45));
                modelOp.setSeguradoPlaca(this.getResultSet().getString(46));
                modelOp.setObsHonorariosProcesso(this.getResultSet().getString(47));
                modelOp.setObsRetencaoAlicotaIndevido(this.getResultSet().getString(48));
                modelOp.setObsRetencaoISSNeg(this.getResultSet().getString(49));
                modelOp.setIndevidoISS(this.getResultSet().getBoolean(50));
                modelOp.setIndevidoISSNegativa(this.getResultSet().getBoolean(51));
                modelOp.setSituacaoPgtoNegativa(this.getResultSet().getString(52));
                modelOp.setSituacaoPgtoProcesso(this.getResultSet().getInt(53));
                modelOp.setValorDespesasTotalRegistro(this.getResultSet().getDouble(54));
                listamodelOp.add(modelOp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.fecharConexao();
        }
        return listamodelOp;
    }

    public ArrayList<ModelOp> getPorAgenteApenasEMAndamentoDAO(int pStatus) {
        ArrayList<ModelOp> listamodelOp = new ArrayList();
        ModelOp modelOp = new ModelOp();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "codigo,"
                    + "Seguradoras,"
                    + "Cod_servico,"
                    + "HonorarioSemRetencao,"
                    + "ObsSegurado,"
                    + "Tipo,"
                    + "DataProcesso,"
                    + "Cts,"
                    + "DataEntrada,"
                    + "DataSaida,"
                    + "NomeSegurado,"
                    + "TipoPagamento,"
                    + "CodCidade,"
                    + "CodEstado,"
                    + "ObsSinistro,"
                    + "HoraSinistro,"
                    + "SisnistroData,"
                    + "AlicotaPercentualHonorarios,"
                    + "PercentuallRetidoReal,"
                    + "SinistroBairro,"
                    + "DataEmissaoNFhonorarios,"
                    + "PrevisãoPgtoNFhonorarios,"
                    + "SituacaoNotaFiscal,"
                    + "HonorarioDefinido,"
                    + "NumeroNFProcesso,"
                    + "DataPgtoNF,"
                    + "ValorNotaRealIndevido,"
                    + "ValorNotaRealNegIndevido,"
                    + "ValorTotalHonorariosComRetencao,"
                    + "ValorHonorariosRetido,"
                    + "ValorHonorarioProcesso,"
                    + "RetidoReal,"
                    + "AlicotaNeg,"
                    + "ValorRetidoRetencaoNeg,"
                    + "DataEmissaoNegativa,"
                    + "DataPgtoNegativa,"
                    + "PrevisaoPgtoNegativa,"
                    + "NfNegPrejuizo,"
                    + "ValorNegativaTotal,"
                    + "ValorRetencaoTotalNeg,"
                    + "PercentuallRetidoRealNeg,"
                    + "RetidoRealNegativa,"
                    + "NumeroSinistro,"
                    + "Prejuizo,"
                    + "Analista,"
                    + "SeguradoPlaca,"
                    + "ObsHonorariosProcesso,"
                    + "ObsRetencaoAlicotaIndevido,"
                    + "ObsRetencaoISSNeg,"
                    + "IndevidoISS,"
                    + "IndevidoISSNegativa,"
                    + "SituacaoPgtoNegativa,"
                    + "SituacaoPgtoProcesso,"
                    + "ValorDespesasTotalRegistro"
                    + " FROM"
                    + " ordem_processo "
                    + " WHERE"
                    + " cod_agente = '" + pStatus + "' and ordem_processo.Tipo = 4 or cod_agente = '" + pStatus + "' and ordem_processo.Tipo = 5 ORDER BY ordem_processo.codigo DESC"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelOp = new ModelOp();
                modelOp.setCodigo(this.getResultSet().getInt(1));
                modelOp.setSeguradoras(this.getResultSet().getInt(2));
                modelOp.setServicosCodigo(this.getResultSet().getInt(3));
                modelOp.setValorTotalHonorariosSemRetencao(this.getResultSet().getDouble(4));
                modelOp.setObsSegurado(this.getResultSet().getString(5));
                modelOp.setTipo(this.getResultSet().getInt(6));
                modelOp.setDataProcesso(this.getResultSet().getString(7));
                modelOp.setCts(this.getResultSet().getInt(8));
                modelOp.setDataEntrada(this.getResultSet().getString(9));
                modelOp.setDataSaida(this.getResultSet().getString(10));
                modelOp.setNomeSegurado(this.getResultSet().getString(11));
                modelOp.setTipoPagamento(this.getResultSet().getInt(12));
                modelOp.setCodCidade(this.getResultSet().getInt(13));
                modelOp.setCodEstado(this.getResultSet().getInt(14));
                modelOp.setObsSinistro(this.getResultSet().getString(15));
                modelOp.setHoraSinistro(this.getResultSet().getString(16));
                modelOp.setDataSinistro(this.getResultSet().getString(17));
                modelOp.setAlicotaPercentualHonorarios(this.getResultSet().getDouble(18));
                modelOp.setPercentuallRetidoReal(this.getResultSet().getDouble(19));
                modelOp.setSinistroBairro(this.getResultSet().getString(20));
                modelOp.setDataEmissaoNFhonorarios(this.getResultSet().getString(21));
                modelOp.setDataPrevisaoPgtoNFhonorarios(this.getResultSet().getString(22));
                modelOp.setSituacaoNotaFiscal(this.getResultSet().getString(23));
                modelOp.setHonorarioDefinido(this.getResultSet().getDouble(24));
                modelOp.setNumeroNFProcesso(this.getResultSet().getString(25));
                modelOp.setDataPgtoNF(this.getResultSet().getString(26));
                modelOp.setValorNotaRealIndevido(this.getResultSet().getDouble(27));
                modelOp.setValorNotaRealNegIndevido(this.getResultSet().getDouble(28));
                modelOp.setValorTotalHonorariosComRetencao(this.getResultSet().getDouble(29));
                modelOp.setValorHonorariosRetido(this.getResultSet().getDouble(30));
                modelOp.setValorHonorarioProcesso(this.getResultSet().getDouble(31));
                modelOp.setRetidoReal(this.getResultSet().getDouble(32));
                modelOp.setAlicotaNeg(this.getResultSet().getDouble(33));
                modelOp.setValorRetidoRetencaoNeg(this.getResultSet().getDouble(34));
                modelOp.setDataEmissaoNegativa(this.getResultSet().getString(35));
                modelOp.setDataPgtoNegativa(this.getResultSet().getString(36));
                modelOp.setDataPrevisaoPgtoNegativa(this.getResultSet().getString(37));
                modelOp.setNfNegPrejuizo(this.getResultSet().getString(38));
                modelOp.setValorNegativaTotal(this.getResultSet().getDouble(39));
                modelOp.setValorRetencaoTotalNeg(this.getResultSet().getDouble(40));
                modelOp.setPercentuallRetidoRealNeg(this.getResultSet().getDouble(41));
                modelOp.setRetidoRealNegativa(this.getResultSet().getDouble(42));
                modelOp.setNumeroSinistro(this.getResultSet().getString(43));
                modelOp.setPrejuizo(this.getResultSet().getDouble(44));
                modelOp.setAnalista(this.getResultSet().getString(45));
                modelOp.setSeguradoPlaca(this.getResultSet().getString(46));
                modelOp.setObsHonorariosProcesso(this.getResultSet().getString(47));
                modelOp.setObsRetencaoAlicotaIndevido(this.getResultSet().getString(48));
                modelOp.setObsRetencaoISSNeg(this.getResultSet().getString(49));
                modelOp.setIndevidoISS(this.getResultSet().getBoolean(50));
                modelOp.setIndevidoISSNegativa(this.getResultSet().getBoolean(51));
                modelOp.setSituacaoPgtoNegativa(this.getResultSet().getString(52));
                modelOp.setSituacaoPgtoProcesso(this.getResultSet().getInt(53));
                modelOp.setValorDespesasTotalRegistro(this.getResultSet().getDouble(54));
                listamodelOp.add(modelOp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.fecharConexao();
        }
        return listamodelOp;
    }

    public ArrayList<ModelOp> getOpFinalizadoENegDAO() {
        ArrayList<ModelOp> listamodelOp = new ArrayList();
        ModelOp modelOp = new ModelOp();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "codigo,"
                    + "Seguradoras,"
                    + "Cod_servico,"
                    + "HonorarioSemRetencao,"
                    + "ObsSegurado,"
                    + "Tipo,"
                    + "DataProcesso,"
                    + "Cts,"
                    + "DataEntrada,"
                    + "DataSaida,"
                    + "NomeSegurado,"
                    + "TipoPagamento,"
                    + "CodCidade,"
                    + "CodEstado,"
                    + "ObsSinistro,"
                    + "HoraSinistro,"
                    + "SisnistroData,"
                    + "AlicotaPercentualHonorarios,"
                    + "PercentuallRetidoReal,"
                    + "SinistroBairro,"
                    + "DataEmissaoNFhonorarios,"
                    + "PrevisãoPgtoNFhonorarios,"
                    + "SituacaoNotaFiscal,"
                    + "HonorarioDefinido,"
                    + "NumeroNFProcesso,"
                    + "DataPgtoNF,"
                    + "ValorNotaRealIndevido,"
                    + "ValorNotaRealNegIndevido,"
                    + "ValorTotalHonorariosComRetencao,"
                    + "ValorHonorariosRetido,"
                    + "ValorHonorarioProcesso,"
                    + "RetidoReal,"
                    + "AlicotaNeg,"
                    + "ValorRetidoRetencaoNeg,"
                    + "DataEmissaoNegativa,"
                    + "DataPgtoNegativa,"
                    + "PrevisaoPgtoNegativa,"
                    + "NfNegPrejuizo,"
                    + "ValorNegativaTotal,"
                    + "ValorRetencaoTotalNeg,"
                    + "PercentuallRetidoRealNeg,"
                    + "RetidoRealNegativa,"
                    + "NumeroSinistro,"
                    + "Prejuizo,"
                    + "Analista,"
                    + "SeguradoPlaca,"
                    + "ObsHonorariosProcesso,"
                    + "ObsRetencaoAlicotaIndevido,"
                    + "ObsRetencaoISSNeg,"
                    + "IndevidoISS,"
                    + "IndevidoISSNegativa,"
                    + "SituacaoPgtoNegativa,"
                    + "SituacaoPgtoProcesso,"
                    + "ValorDespesasTotalRegistro"
                    + " FROM"
                    + " ordem_processo "
                    + " WHERE"
                    + " Tipo = 3"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelOp = new ModelOp();
                modelOp.setCodigo(this.getResultSet().getInt(1));
                modelOp.setSeguradoras(this.getResultSet().getInt(2));
                modelOp.setServicosCodigo(this.getResultSet().getInt(3));
                modelOp.setValorTotalHonorariosSemRetencao(this.getResultSet().getDouble(4));
                modelOp.setObsSegurado(this.getResultSet().getString(5));
                modelOp.setTipo(this.getResultSet().getInt(6));
                modelOp.setDataProcesso(this.getResultSet().getString(7));
                modelOp.setCts(this.getResultSet().getInt(8));
                modelOp.setDataEntrada(this.getResultSet().getString(9));
                modelOp.setDataSaida(this.getResultSet().getString(10));
                modelOp.setNomeSegurado(this.getResultSet().getString(11));
                modelOp.setTipoPagamento(this.getResultSet().getInt(12));
                modelOp.setCodCidade(this.getResultSet().getInt(13));
                modelOp.setCodEstado(this.getResultSet().getInt(14));
                modelOp.setObsSinistro(this.getResultSet().getString(15));
                modelOp.setHoraSinistro(this.getResultSet().getString(16));
                modelOp.setDataSinistro(this.getResultSet().getString(17));
                modelOp.setAlicotaPercentualHonorarios(this.getResultSet().getDouble(18));
                modelOp.setPercentuallRetidoReal(this.getResultSet().getDouble(19));
                modelOp.setSinistroBairro(this.getResultSet().getString(20));
                modelOp.setDataEmissaoNFhonorarios(this.getResultSet().getString(21));
                modelOp.setDataPrevisaoPgtoNFhonorarios(this.getResultSet().getString(22));
                modelOp.setSituacaoNotaFiscal(this.getResultSet().getString(23));
                modelOp.setHonorarioDefinido(this.getResultSet().getDouble(24));
                modelOp.setNumeroNFProcesso(this.getResultSet().getString(25));
                modelOp.setDataPgtoNF(this.getResultSet().getString(26));
                modelOp.setValorNotaRealIndevido(this.getResultSet().getDouble(27));
                modelOp.setValorNotaRealNegIndevido(this.getResultSet().getDouble(28));
                modelOp.setValorTotalHonorariosComRetencao(this.getResultSet().getDouble(29));
                modelOp.setValorHonorariosRetido(this.getResultSet().getDouble(30));
                modelOp.setValorHonorarioProcesso(this.getResultSet().getDouble(31));
                modelOp.setRetidoReal(this.getResultSet().getDouble(32));
                modelOp.setAlicotaNeg(this.getResultSet().getDouble(33));
                modelOp.setValorRetidoRetencaoNeg(this.getResultSet().getDouble(34));
                modelOp.setDataEmissaoNegativa(this.getResultSet().getString(35));
                modelOp.setDataPgtoNegativa(this.getResultSet().getString(36));
                modelOp.setDataPrevisaoPgtoNegativa(this.getResultSet().getString(37));
                modelOp.setNfNegPrejuizo(this.getResultSet().getString(38));
                modelOp.setValorNegativaTotal(this.getResultSet().getDouble(39));
                modelOp.setValorRetencaoTotalNeg(this.getResultSet().getDouble(40));
                modelOp.setPercentuallRetidoRealNeg(this.getResultSet().getDouble(41));
                modelOp.setRetidoRealNegativa(this.getResultSet().getDouble(42));
                modelOp.setNumeroSinistro(this.getResultSet().getString(43));
                modelOp.setPrejuizo(this.getResultSet().getDouble(44));
                modelOp.setAnalista(this.getResultSet().getString(45));
                modelOp.setSeguradoPlaca(this.getResultSet().getString(46));
                modelOp.setObsHonorariosProcesso(this.getResultSet().getString(47));
                modelOp.setObsRetencaoAlicotaIndevido(this.getResultSet().getString(48));
                modelOp.setObsRetencaoISSNeg(this.getResultSet().getString(49));
                modelOp.setIndevidoISS(this.getResultSet().getBoolean(50));
                modelOp.setIndevidoISSNegativa(this.getResultSet().getBoolean(51));
                modelOp.setSituacaoPgtoNegativa(this.getResultSet().getString(52));
                modelOp.setSituacaoPgtoProcesso(this.getResultSet().getInt(53));
                modelOp.setValorDespesasTotalRegistro(this.getResultSet().getDouble(54));
                listamodelOp.add(modelOp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.fecharConexao();
        }
        return listamodelOp;
    }

    public ArrayList<ModelOp> getOpAguardandoPGTOAO() {
        ArrayList<ModelOp> listamodelOp = new ArrayList();
        ModelOp modelOp = new ModelOp();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "codigo,"
                    + "Seguradoras,"
                    + "Cod_servico,"
                    + "HonorarioSemRetencao,"
                    + "ObsSegurado,"
                    + "Tipo,"
                    + "DataProcesso,"
                    + "Cts,"
                    + "DataEntrada,"
                    + "DataSaida,"
                    + "NomeSegurado,"
                    + "TipoPagamento,"
                    + "CodCidade,"
                    + "CodEstado,"
                    + "ObsSinistro,"
                    + "HoraSinistro,"
                    + "SisnistroData,"
                    + "AlicotaPercentualHonorarios,"
                    + "PercentuallRetidoReal,"
                    + "SinistroBairro,"
                    + "DataEmissaoNFhonorarios,"
                    + "PrevisãoPgtoNFhonorarios,"
                    + "SituacaoNotaFiscal,"
                    + "HonorarioDefinido,"
                    + "NumeroNFProcesso,"
                    + "DataPgtoNF,"
                    + "ValorNotaRealIndevido,"
                    + "ValorNotaRealNegIndevido,"
                    + "ValorTotalHonorariosComRetencao,"
                    + "ValorHonorariosRetido,"
                    + "ValorHonorarioProcesso,"
                    + "RetidoReal,"
                    + "AlicotaNeg,"
                    + "ValorRetidoRetencaoNeg,"
                    + "DataEmissaoNegativa,"
                    + "DataPgtoNegativa,"
                    + "PrevisaoPgtoNegativa,"
                    + "NfNegPrejuizo,"
                    + "ValorNegativaTotal,"
                    + "ValorRetencaoTotalNeg,"
                    + "PercentuallRetidoRealNeg,"
                    + "RetidoRealNegativa,"
                    + "NumeroSinistro,"
                    + "Prejuizo,"
                    + "Analista,"
                    + "SeguradoPlaca,"
                    + "ObsHonorariosProcesso,"
                    + "ObsRetencaoAlicotaIndevido,"
                    + "ObsRetencaoISSNeg,"
                    + "IndevidoISS,"
                    + "IndevidoISSNegativa,"
                    + "SituacaoPgtoNegativa,"
                    + "SituacaoPgtoProcesso,"
                    + "ValorDespesasTotalRegistro"
                    + " FROM"
                    + " ordem_processo "
                    + " WHERE"
                    + " Tipo = 5"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelOp = new ModelOp();
                modelOp.setCodigo(this.getResultSet().getInt(1));
                modelOp.setSeguradoras(this.getResultSet().getInt(2));
                modelOp.setServicosCodigo(this.getResultSet().getInt(3));
                modelOp.setValorTotalHonorariosSemRetencao(this.getResultSet().getDouble(4));
                modelOp.setObsSegurado(this.getResultSet().getString(5));
                modelOp.setTipo(this.getResultSet().getInt(6));
                modelOp.setDataProcesso(this.getResultSet().getString(7));
                modelOp.setCts(this.getResultSet().getInt(8));
                modelOp.setDataEntrada(this.getResultSet().getString(9));
                modelOp.setDataSaida(this.getResultSet().getString(10));
                modelOp.setNomeSegurado(this.getResultSet().getString(11));
                modelOp.setTipoPagamento(this.getResultSet().getInt(12));
                modelOp.setCodCidade(this.getResultSet().getInt(13));
                modelOp.setCodEstado(this.getResultSet().getInt(14));
                modelOp.setObsSinistro(this.getResultSet().getString(15));
                modelOp.setHoraSinistro(this.getResultSet().getString(16));
                modelOp.setDataSinistro(this.getResultSet().getString(17));
                modelOp.setAlicotaPercentualHonorarios(this.getResultSet().getDouble(18));
                modelOp.setPercentuallRetidoReal(this.getResultSet().getDouble(19));
                modelOp.setSinistroBairro(this.getResultSet().getString(20));
                modelOp.setDataEmissaoNFhonorarios(this.getResultSet().getString(21));
                modelOp.setDataPrevisaoPgtoNFhonorarios(this.getResultSet().getString(22));
                modelOp.setSituacaoNotaFiscal(this.getResultSet().getString(23));
                modelOp.setHonorarioDefinido(this.getResultSet().getDouble(24));
                modelOp.setNumeroNFProcesso(this.getResultSet().getString(25));
                modelOp.setDataPgtoNF(this.getResultSet().getString(26));
                modelOp.setValorNotaRealIndevido(this.getResultSet().getDouble(27));
                modelOp.setValorNotaRealNegIndevido(this.getResultSet().getDouble(28));
                modelOp.setValorTotalHonorariosComRetencao(this.getResultSet().getDouble(29));
                modelOp.setValorHonorariosRetido(this.getResultSet().getDouble(30));
                modelOp.setValorHonorarioProcesso(this.getResultSet().getDouble(31));
                modelOp.setRetidoReal(this.getResultSet().getDouble(32));
                modelOp.setAlicotaNeg(this.getResultSet().getDouble(33));
                modelOp.setValorRetidoRetencaoNeg(this.getResultSet().getDouble(34));
                modelOp.setDataEmissaoNegativa(this.getResultSet().getString(35));
                modelOp.setDataPgtoNegativa(this.getResultSet().getString(36));
                modelOp.setDataPrevisaoPgtoNegativa(this.getResultSet().getString(37));
                modelOp.setNfNegPrejuizo(this.getResultSet().getString(38));
                modelOp.setValorNegativaTotal(this.getResultSet().getDouble(39));
                modelOp.setValorRetencaoTotalNeg(this.getResultSet().getDouble(40));
                modelOp.setPercentuallRetidoRealNeg(this.getResultSet().getDouble(41));
                modelOp.setRetidoRealNegativa(this.getResultSet().getDouble(42));
                modelOp.setNumeroSinistro(this.getResultSet().getString(43));
                modelOp.setPrejuizo(this.getResultSet().getDouble(44));
                modelOp.setAnalista(this.getResultSet().getString(45));
                modelOp.setSeguradoPlaca(this.getResultSet().getString(46));
                modelOp.setObsHonorariosProcesso(this.getResultSet().getString(47));
                modelOp.setObsRetencaoAlicotaIndevido(this.getResultSet().getString(48));
                modelOp.setObsRetencaoISSNeg(this.getResultSet().getString(49));
                modelOp.setIndevidoISS(this.getResultSet().getBoolean(50));
                modelOp.setIndevidoISSNegativa(this.getResultSet().getBoolean(51));
                modelOp.setSituacaoPgtoNegativa(this.getResultSet().getString(52));
                modelOp.setSituacaoPgtoProcesso(this.getResultSet().getInt(53));
                modelOp.setValorDespesasTotalRegistro(this.getResultSet().getDouble(54));
                listamodelOp.add(modelOp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.fecharConexao();
        }
        return listamodelOp;
    }

    public ArrayList<ModelOp> getListaCompletaDAO() {
        ArrayList<ModelOp> listamodelOp = new ArrayList();
        ModelOp modelOp = new ModelOp();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "codigo,"
                    + "Seguradoras,"
                    + "Cod_servico,"
                    + "HonorarioSemRetencao,"
                    + "ObsSegurado,"
                    + "Tipo,"
                    + "DataProcesso,"
                    + "Cts,"
                    + "DataEntrada,"
                    + "DataSaida,"
                    + "NomeSegurado,"
                    + "TipoPagamento,"
                    + "CodCidade,"
                    + "CodEstado,"
                    + "ObsSinistro,"
                    + "HoraSinistro,"
                    + "SisnistroData,"
                    + "AlicotaPercentualHonorarios,"
                    + "PercentuallRetidoReal,"
                    + "SinistroBairro,"
                    + "DataEmissaoNFhonorarios,"
                    + "PrevisãoPgtoNFhonorarios,"
                    + "SituacaoNotaFiscal,"
                    + "HonorarioDefinido,"
                    + "NumeroNFProcesso,"
                    + "DataPgtoNF,"
                    + "ValorNotaRealIndevido,"
                    + "ValorNotaRealNegIndevido,"
                    + "ValorTotalHonorariosComRetencao,"
                    + "ValorHonorariosRetido,"
                    + "ValorHonorarioProcesso,"
                    + "RetidoReal,"
                    + "AlicotaNeg,"
                    + "ValorRetidoRetencaoNeg,"
                    + "DataEmissaoNegativa,"
                    + "DataPgtoNegativa,"
                    + "PrevisaoPgtoNegativa,"
                    + "NfNegPrejuizo,"
                    + "ValorNegativaTotal,"
                    + "ValorRetencaoTotalNeg,"
                    + "PercentuallRetidoRealNeg,"
                    + "RetidoRealNegativa,"
                    + "NumeroSinistro,"
                    + "Prejuizo,"
                    + "Analista,"
                    + "SeguradoPlaca,"
                    + "ObsHonorariosProcesso,"
                    + "ObsRetencaoAlicotaIndevido,"
                    + "ObsRetencaoISSNeg,"
                    + "IndevidoISS,"
                    + "IndevidoISSNegativa,"
                    + "SituacaoPgtoNegativa,"
                    + "SituacaoPgtoProcesso,"
                    + "ValorDespesasTotalRegistro"
                    + " FROM ordem_processo"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelOp = new ModelOp();
                modelOp.setCodigo(this.getResultSet().getInt(1));
                modelOp.setSeguradoras(this.getResultSet().getInt(2));
                modelOp.setServicosCodigo(this.getResultSet().getInt(3));
                modelOp.setValorTotalHonorariosSemRetencao(this.getResultSet().getDouble(4));
                modelOp.setObsSegurado(this.getResultSet().getString(5));
                modelOp.setTipo(this.getResultSet().getInt(6));
                modelOp.setDataProcesso(this.getResultSet().getString(7));
                modelOp.setCts(this.getResultSet().getInt(8));
                modelOp.setDataEntrada(this.getResultSet().getString(9));
                modelOp.setDataSaida(this.getResultSet().getString(10));
                modelOp.setNomeSegurado(this.getResultSet().getString(11));
                modelOp.setTipoPagamento(this.getResultSet().getInt(12));
                modelOp.setCodCidade(this.getResultSet().getInt(13));
                modelOp.setCodEstado(this.getResultSet().getInt(14));
                modelOp.setObsSinistro(this.getResultSet().getString(15));
                modelOp.setHoraSinistro(this.getResultSet().getString(16));
                modelOp.setDataSinistro(this.getResultSet().getString(17));
                modelOp.setAlicotaPercentualHonorarios(this.getResultSet().getDouble(18));
                modelOp.setPercentuallRetidoReal(this.getResultSet().getDouble(19));
                modelOp.setSinistroBairro(this.getResultSet().getString(20));
                modelOp.setDataEmissaoNFhonorarios(this.getResultSet().getString(21));
                modelOp.setDataPrevisaoPgtoNFhonorarios(this.getResultSet().getString(22));
                modelOp.setSituacaoNotaFiscal(this.getResultSet().getString(23));
                modelOp.setHonorarioDefinido(this.getResultSet().getDouble(24));
                modelOp.setNumeroNFProcesso(this.getResultSet().getString(25));
                modelOp.setDataPgtoNF(this.getResultSet().getString(26));
                modelOp.setValorNotaRealIndevido(this.getResultSet().getDouble(27));
                modelOp.setValorNotaRealNegIndevido(this.getResultSet().getDouble(28));
                modelOp.setValorTotalHonorariosComRetencao(this.getResultSet().getDouble(29));
                modelOp.setValorHonorariosRetido(this.getResultSet().getDouble(30));
                modelOp.setValorHonorarioProcesso(this.getResultSet().getDouble(31));
                modelOp.setRetidoReal(this.getResultSet().getDouble(32));
                modelOp.setAlicotaNeg(this.getResultSet().getDouble(33));
                modelOp.setValorRetidoRetencaoNeg(this.getResultSet().getDouble(34));
                modelOp.setDataEmissaoNegativa(this.getResultSet().getString(35));
                modelOp.setDataPgtoNegativa(this.getResultSet().getString(36));
                modelOp.setDataPrevisaoPgtoNegativa(this.getResultSet().getString(37));
                modelOp.setNfNegPrejuizo(this.getResultSet().getString(38));
                modelOp.setValorNegativaTotal(this.getResultSet().getDouble(39));
                modelOp.setValorRetencaoTotalNeg(this.getResultSet().getDouble(40));
                modelOp.setPercentuallRetidoRealNeg(this.getResultSet().getDouble(41));
                modelOp.setRetidoRealNegativa(this.getResultSet().getDouble(42));
                modelOp.setNumeroSinistro(this.getResultSet().getString(43));
                modelOp.setPrejuizo(this.getResultSet().getDouble(44));
                modelOp.setAnalista(this.getResultSet().getString(45));
                modelOp.setSeguradoPlaca(this.getResultSet().getString(46));
                modelOp.setObsHonorariosProcesso(this.getResultSet().getString(47));
                modelOp.setObsRetencaoAlicotaIndevido(this.getResultSet().getString(48));
                modelOp.setObsRetencaoISSNeg(this.getResultSet().getString(49));
                modelOp.setIndevidoISS(this.getResultSet().getBoolean(50));
                modelOp.setIndevidoISSNegativa(this.getResultSet().getBoolean(51));
                modelOp.setSituacaoPgtoNegativa(this.getResultSet().getString(52));
                modelOp.setSituacaoPgtoProcesso(this.getResultSet().getInt(53));
                modelOp.setValorDespesasTotalRegistro(this.getResultSet().getDouble(54));
                listamodelOp.add(modelOp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.fecharConexao();
        }
        return listamodelOp;
    }
}
