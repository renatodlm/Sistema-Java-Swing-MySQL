package DAO;

import conexao.ConexaoBanco;
import java.util.ArrayList;
import Model.ModelContaAgentes;
import java.sql.Date;
import java.sql.SQLException;

/**
 *
 * @author studiomicroweb
 */
public class DAOContaAgentes extends ConexaoBanco {

    /**
     * grava ContaAgentes
     *
     * @param pModelContaAgentes return int
     * @return Salva a Contas a Agentes no SQL
     */
    public int salvarContaAgentesDAO(ModelContaAgentes pModelContaAgentes) {
        try {
            this.conectar();
            return this.insertSQL(
                    "INSERT INTO conta_agentes ("
                    + "fk_codigo_pessoa,"
                    + "descricao,"
                    + "data,"
                    + "vencimento,"
                    + "pagamento,"
                    + "fk_tipo_pagamento,"
                    + "observacao,"
                    + "situacao,"
                    + "valor,"
                    + "Processos,"
                    + "AgenteHonorario,"
                    + "AgenteKmPercorrido,"
                    + "AgentePtgoPorKMSeguradoraProcessos,"
                    + "PagamentoTotalKM,"
                    + "AgenteTotalRepasse,"
                    + "TotalGeral,"
                    + "Repasse,"
                    + "banco,"
                    + "DataNeg,"
                    + "ValorNeg"
                    + ") VALUES ("
                    + "'" + pModelContaAgentes.getCodigoPessoa() + "',"
                    + "'" + pModelContaAgentes.getDescricao() + "',"
                    + "'" + pModelContaAgentes.getDataDespesa() + "',"
                    + "'" + pModelContaAgentes.getDataEnvio() + "',"
                    + "'" + pModelContaAgentes.getDataRecebimento() + "',"
                    + "'" + pModelContaAgentes.getTipoPagamento() + "',"
                    + "'" + pModelContaAgentes.getObservacao() + "',"
                    + "'" + pModelContaAgentes.isSituacao() + "',"
                    + "'" + pModelContaAgentes.getValorAdiantamento() + "',"
                    + "'" + pModelContaAgentes.getProcessos() + "',"
                    + "'" + pModelContaAgentes.getValorHonorarioAgt() + "',"
                    + "'" + pModelContaAgentes.getAgenteKmPercorrido() + "',"
                    + "'" + pModelContaAgentes.getValorPGTOKMSeguradora() + "',"
                    + "'" + pModelContaAgentes.getValorPGTOtotalKM() + "',"
                    + "'" + pModelContaAgentes.getValorHRMaisKM() + "',"
                    + "'" + pModelContaAgentes.getValorAReceber() + "',"
                    + "'" + pModelContaAgentes.getRepasse() + "',"
                    + "'" + pModelContaAgentes.getBanco() + "',"
                    + "'" + pModelContaAgentes.getDataNeg() + "',"
                    + "'" + pModelContaAgentes.getValorNeg() + "'"
                    + ");"
            );
        } catch (Exception e) {
            return 0;
        } finally {
            this.fecharConexao();
        }
    }

    /**
     * grava terceiro
     *
     * @param pModelContaAgentes insert terceiro
     * @return grava terceiro
     */
    public boolean salvarAdiantamentosDAO(ModelContaAgentes pModelContaAgentes) {
        try {
            this.conectar();
            int sizeLista = pModelContaAgentes.getListamModelContaAgentes().size();
            for (int i = 0; i < sizeLista; i++) {
                this.insertSQL(
                        "insert into adiantamentos( "
                        + "cod_despesa,"
                        + "descricao,"
                        + "valor,"
                        + "data) "
                        + " VALUES ("
                        + "'" + pModelContaAgentes.getListamModelContaAgentes().get(i).getCodigo() + "',"
                        + "'" + pModelContaAgentes.getListamModelContaAgentes().get(i).getDescricao() + "',"
                        + "'" + pModelContaAgentes.getListamModelContaAgentes().get(i).getValorAdiantamento() + "',"
                        + "'" + pModelContaAgentes.getListamModelContaAgentes().get(i).getDataDespesa() + "'"
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
     * recupera ContaAgentes
     *
     * @param pCodigo return ModelConta
     * @return Retorna a Conta a Agentes por codigo
     */
    public ModelContaAgentes getDescricaoPorDespesaDAO(int pCodigo) {
        ModelContaAgentes modelContaAgentes = new ModelContaAgentes();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "codigo,"
                    + "cod_despesa,"
                    + "descricao,"
                    + "valor,"
                    + "data "
                    + " FROM"
                    + " adiantamentos"
                    + " WHERE"
                    + " codigo = '" + pCodigo + "'"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelContaAgentes.setCodigo(this.getResultSet().getInt(1));
                modelContaAgentes.setCodigoDespesa(this.getResultSet().getInt(2));
                modelContaAgentes.setDescricao(this.getResultSet().getString(3));
                modelContaAgentes.setValorAdiantamento(this.getResultSet().getDouble(4));
                modelContaAgentes.setDataDespesa(this.getResultSet().getString(5));
            }
        } catch (SQLException e) {
        } finally {
            this.fecharConexao();
        }
        return modelContaAgentes;
    }

    /**
     * recupera ContaAgentes
     *
     * @param pProcesso return ModelConta
     * @return Retorna a Conta a Agentes por codigo
     */
    public ModelContaAgentes getContaAgentesPorProcessoDAO(int pProcesso) {
        ModelContaAgentes modelContaAgentes = new ModelContaAgentes();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "pk_codigo,"
                    + "fk_codigo_pessoa,"
                    + "descricao,"
                    + "data,"
                    + "vencimento,"
                    + "pagamento,"
                    + "fk_tipo_pagamento,"
                    + "observacao,"
                    + "situacao,"
                    + "valor,"
                    + "Processos,"
                    + "AgenteHonorario,"
                    + "AgenteKmPercorrido,"
                    + "AgentePtgoPorKMSeguradoraProcessos,"
                    + "PagamentoTotalKM,"
                    + "AgenteTotalRepasse,"
                    + "TotalGeral,"
                    + "Repasse,"
                    + "banco,"
                    + "DataNeg,"
                    + "ValorNeg"
                    + " FROM"
                    + " conta_agentes"
                    + " WHERE"
                    + " Processos = '" + pProcesso + "'"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelContaAgentes.setCodigo(this.getResultSet().getInt(1));
                modelContaAgentes.setCodigoPessoa(this.getResultSet().getInt(2));
                modelContaAgentes.setDescricao(this.getResultSet().getString(3));
                modelContaAgentes.setDataDespesa(this.getResultSet().getString(4));
                modelContaAgentes.setDataEnvio(this.getResultSet().getString(5));
                modelContaAgentes.setDataRecebimento(this.getResultSet().getString(6));
                modelContaAgentes.setTipoPagamento(this.getResultSet().getInt(7));
                modelContaAgentes.setObservacao(this.getResultSet().getString(8));
                modelContaAgentes.setSituacao(this.getResultSet().getInt(9));
                modelContaAgentes.setValorAdiantamento(this.getResultSet().getDouble(10));
                modelContaAgentes.setProcessos(this.getResultSet().getInt(11));
                modelContaAgentes.setValorHonorarioAgt(this.getResultSet().getDouble(12));
                modelContaAgentes.setAgenteKmPercorrido(this.getResultSet().getInt(13));
                modelContaAgentes.setValorPGTOKMSeguradora(this.getResultSet().getDouble(14));
                modelContaAgentes.setValorPGTOtotalKM(this.getResultSet().getDouble(15));
                modelContaAgentes.setValorHRMaisKM(this.getResultSet().getDouble(16));
                modelContaAgentes.setValorAReceber(this.getResultSet().getDouble(17));
                modelContaAgentes.setRepasse(this.getResultSet().getBoolean(18));
                modelContaAgentes.setBanco(this.getResultSet().getInt(19));
                modelContaAgentes.setDataNeg(this.getResultSet().getString(20));
                modelContaAgentes.setValorNeg(this.getResultSet().getDouble(21));
            }
        } catch (SQLException e) {
        } finally {
            this.fecharConexao();
        }
        return modelContaAgentes;
    }

    /**
     * recupera ContaAgentes
     *
     * @param pCodigo return ModelConta
     * @return Retorna a Conta a Agentes por codigo
     */
    public ModelContaAgentes getContaAgentesDAO(int pCodigo) {
        ModelContaAgentes modelContaAgentes = new ModelContaAgentes();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "pk_codigo,"
                    + "fk_codigo_pessoa,"
                    + "descricao,"
                    + "data,"
                    + "vencimento,"
                    + "pagamento,"
                    + "fk_tipo_pagamento,"
                    + "observacao,"
                    + "situacao,"
                    + "valor,"
                    + "Processos,"
                    + "AgenteHonorario,"
                    + "AgenteKmPercorrido,"
                    + "AgentePtgoPorKMSeguradoraProcessos,"
                    + "PagamentoTotalKM,"
                    + "AgenteTotalRepasse,"
                    + "TotalGeral,"
                    + "Repasse,"
                    + "banco,"
                    + "DataNeg,"
                    + "ValorNeg"
                    + " FROM"
                    + " conta_agentes"
                    + " WHERE"
                    + " pk_codigo = '" + pCodigo + " ORDER BY DESC'"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelContaAgentes.setCodigo(this.getResultSet().getInt(1));
                modelContaAgentes.setCodigoPessoa(this.getResultSet().getInt(2));
                modelContaAgentes.setDescricao(this.getResultSet().getString(3));
                modelContaAgentes.setDataDespesa(this.getResultSet().getString(4));
                modelContaAgentes.setDataEnvio(this.getResultSet().getString(5));
                modelContaAgentes.setDataRecebimento(this.getResultSet().getString(6));
                modelContaAgentes.setTipoPagamento(this.getResultSet().getInt(7));
                modelContaAgentes.setObservacao(this.getResultSet().getString(8));
                modelContaAgentes.setSituacao(this.getResultSet().getInt(9));
                modelContaAgentes.setValorAdiantamento(this.getResultSet().getDouble(10));
                modelContaAgentes.setProcessos(this.getResultSet().getInt(11));
                modelContaAgentes.setValorHonorarioAgt(this.getResultSet().getDouble(12));
                modelContaAgentes.setAgenteKmPercorrido(this.getResultSet().getInt(13));
                modelContaAgentes.setValorPGTOKMSeguradora(this.getResultSet().getDouble(14));
                modelContaAgentes.setValorPGTOtotalKM(this.getResultSet().getDouble(15));
                modelContaAgentes.setValorHRMaisKM(this.getResultSet().getDouble(16));
                modelContaAgentes.setValorAReceber(this.getResultSet().getDouble(17));
                modelContaAgentes.setRepasse(this.getResultSet().getBoolean(18));
                modelContaAgentes.setBanco(this.getResultSet().getInt(19));
                modelContaAgentes.setDataNeg(this.getResultSet().getString(20));
                modelContaAgentes.setValorNeg(this.getResultSet().getDouble(21));
            }
        } catch (SQLException e) {
        } finally {
            this.fecharConexao();
        }
        return modelContaAgentes;
    }

    /**
     * recupera uma lista de ContaAgentes return ArrayList
     *
     * @return Retorna do Banco de Dados Lista de Contas a Agentes
     */
    public ArrayList<ModelContaAgentes> getListaContaAgentesDAO() {
        ArrayList<ModelContaAgentes> listamodelConta = new ArrayList();
        ModelContaAgentes modelContaAgentes = new ModelContaAgentes();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "pk_codigo,"
                    + "fk_codigo_pessoa,"
                    + "descricao,"
                    + "data,"
                    + "vencimento,"
                    + "pagamento,"
                    + "fk_tipo_pagamento,"
                    + "observacao,"
                    + "situacao,"
                    + "valor,"
                    + "Processos,"
                    + "AgenteHonorario,"
                    + "AgenteKmPercorrido,"
                    + "AgentePtgoPorKMSeguradoraProcessos,"
                    + "PagamentoTotalKM,"
                    + "TotalGeral,"
                    + "Repasse,"
                    + "banco,"
                    + "DataNeg,"
                    + "ValorNeg"
                    + " FROM"
                    + " conta_agentes"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelContaAgentes = new ModelContaAgentes();
                modelContaAgentes.setCodigo(this.getResultSet().getInt(1));
                modelContaAgentes.setCodigoPessoa(this.getResultSet().getInt(2));
                modelContaAgentes.setDescricao(this.getResultSet().getString(3));
                modelContaAgentes.setDataDespesa(this.getResultSet().getString(4));
                modelContaAgentes.setDataEnvio(this.getResultSet().getString(5));
                modelContaAgentes.setDataRecebimento(this.getResultSet().getString(6));
                modelContaAgentes.setTipoPagamento(this.getResultSet().getInt(7));
                modelContaAgentes.setObservacao(this.getResultSet().getString(8));
                modelContaAgentes.setSituacao(this.getResultSet().getInt(9));
                modelContaAgentes.setValorAdiantamento(this.getResultSet().getDouble(10));
                modelContaAgentes.setProcessos(this.getResultSet().getInt(11));
                modelContaAgentes.setValorHonorarioAgt(this.getResultSet().getDouble(12));
                modelContaAgentes.setAgenteKmPercorrido(this.getResultSet().getInt(13));
                modelContaAgentes.setValorPGTOKMSeguradora(this.getResultSet().getDouble(14));
                modelContaAgentes.setValorPGTOtotalKM(this.getResultSet().getDouble(15));
                modelContaAgentes.setValorHRMaisKM(this.getResultSet().getDouble(16));
                modelContaAgentes.setValorAReceber(this.getResultSet().getDouble(17));
                modelContaAgentes.setRepasse(this.getResultSet().getBoolean(18));
                modelContaAgentes.setBanco(this.getResultSet().getInt(19));
                modelContaAgentes.setDataNeg(this.getResultSet().getString(20));
                modelContaAgentes.setValorNeg(this.getResultSet().getDouble(21));
                listamodelConta.add(modelContaAgentes);
            }
        } catch (SQLException e) {
        } finally {
            this.fecharConexao();
        }
        return listamodelConta;
    }

    /**
     * recupera uma lista de ContaAgentes return ArrayList
     *
     * @param pStatus Verifica o Status para montar a lista das contas a agentes
     * @return Retorna a Lista de Contas a agentes de acordo com status
     */
    public ArrayList<ModelContaAgentes> getListaContaAgentesDAO(int pStatus) {
        ArrayList<ModelContaAgentes> listamodelContaAgentes = new ArrayList();
        ModelContaAgentes modelContaAgentes = new ModelContaAgentes();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "pk_codigo,"
                    + "fk_codigo_pessoa,"
                    + "descricao,"
                    + "data,"
                    + "vencimento,"
                    + "pagamento,"
                    + "fk_tipo_pagamento,"
                    + "observacao,"
                    + "situacao,"
                    + "valor,"
                    + "Processos,"
                    + "AgenteHonorario,"
                    + "AgenteKmPercorrido,"
                    + "AgentePtgoPorKMSeguradoraProcessos,"
                    + "PagamentoTotalKM,"
                    + "AgenteTotalRepasse,"
                    + "TotalGeral,"
                    + "Repasse,"
                    + "banco,"
                    + "DataNeg,"
                    + "ValorNeg"
                    + " FROM"
                    + " conta_agentes where situacao = '" + pStatus + "'"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelContaAgentes = new ModelContaAgentes();
                modelContaAgentes.setCodigo(this.getResultSet().getInt(1));
                modelContaAgentes.setCodigoPessoa(this.getResultSet().getInt(2));
                modelContaAgentes.setDescricao(this.getResultSet().getString(3));
                modelContaAgentes.setDataDespesa(this.getResultSet().getString(4));
                modelContaAgentes.setDataEnvio(this.getResultSet().getString(5));
                modelContaAgentes.setDataRecebimento(this.getResultSet().getString(6));
                modelContaAgentes.setTipoPagamento(this.getResultSet().getInt(7));
                modelContaAgentes.setObservacao(this.getResultSet().getString(8));
                modelContaAgentes.setSituacao(this.getResultSet().getInt(9));
                modelContaAgentes.setValorAdiantamento(this.getResultSet().getDouble(10));
                modelContaAgentes.setProcessos(this.getResultSet().getInt(11));
                modelContaAgentes.setValorHonorarioAgt(this.getResultSet().getDouble(12));
                modelContaAgentes.setAgenteKmPercorrido(this.getResultSet().getInt(13));
                modelContaAgentes.setValorPGTOKMSeguradora(this.getResultSet().getDouble(14));
                modelContaAgentes.setValorPGTOtotalKM(this.getResultSet().getDouble(15));
                modelContaAgentes.setValorHRMaisKM(this.getResultSet().getDouble(16));
                modelContaAgentes.setValorAReceber(this.getResultSet().getDouble(17));
                modelContaAgentes.setRepasse(this.getResultSet().getBoolean(18));
                modelContaAgentes.setBanco(this.getResultSet().getInt(19));
                modelContaAgentes.setDataNeg(this.getResultSet().getString(20));
                modelContaAgentes.setValorNeg(this.getResultSet().getDouble(21));
                listamodelContaAgentes.add(modelContaAgentes);
            }
        } catch (SQLException e) {
        } finally {
            this.fecharConexao();
        }
        return listamodelContaAgentes;
    }

    /**
     * recupera uma lista de ContaAgentes return ArrayList
     *
     * @param pStatus Lista de Contas a Agentes por Staus de pagamento
     * @return Verifica se foi feita a listagem
     */
    public ArrayList<ModelContaAgentes> getListaContasDAO(int pStatus) {
        ArrayList<ModelContaAgentes> listamodelContaAgentes = new ArrayList();
        ModelContaAgentes modelContaAgentes = new ModelContaAgentes();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "pk_codigo,"
                    + "fk_codigo_pessoa,"
                    + "descricao,"
                    + "data,"
                    + "vencimento,"
                    + "pagamento,"
                    + "fk_tipo_pagamento,"
                    + "observacao,"
                    + "situacao,"
                    + "valor,"
                    + "Processos,"
                    + "AgenteHonorario,"
                    + "AgenteKmPercorrido,"
                    + "AgentePtgoPorKMSeguradoraProcessos,"
                    + "PagamentoTotalKM,"
                    + "AgenteTotalRepasse,"
                    + "TotalGeral,"
                    + "Repasse,"
                    + "banco,"
                    + "DataNeg,"
                    + "ValorNeg"
                    + " FROM"
                    + " conta_agentes where situacao = '" + pStatus + "'"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelContaAgentes = new ModelContaAgentes();
                modelContaAgentes.setCodigo(this.getResultSet().getInt(1));
                modelContaAgentes.setCodigoPessoa(this.getResultSet().getInt(2));
                modelContaAgentes.setDescricao(this.getResultSet().getString(3));
                modelContaAgentes.setDataDespesa(this.getResultSet().getString(4));
                modelContaAgentes.setDataEnvio(this.getResultSet().getString(5));
                modelContaAgentes.setDataRecebimento(this.getResultSet().getString(6));
                modelContaAgentes.setTipoPagamento(this.getResultSet().getInt(7));
                modelContaAgentes.setObservacao(this.getResultSet().getString(8));
                modelContaAgentes.setSituacao(this.getResultSet().getInt(9));
                modelContaAgentes.setValorAdiantamento(this.getResultSet().getDouble(10));
                modelContaAgentes.setProcessos(this.getResultSet().getInt(11));
                modelContaAgentes.setValorHonorarioAgt(this.getResultSet().getDouble(12));
                modelContaAgentes.setAgenteKmPercorrido(this.getResultSet().getInt(13));
                modelContaAgentes.setValorPGTOKMSeguradora(this.getResultSet().getDouble(14));
                modelContaAgentes.setValorPGTOtotalKM(this.getResultSet().getDouble(15));
                modelContaAgentes.setValorHRMaisKM(this.getResultSet().getDouble(16));
                modelContaAgentes.setValorAReceber(this.getResultSet().getDouble(17));
                modelContaAgentes.setRepasse(this.getResultSet().getBoolean(18));
                modelContaAgentes.setBanco(this.getResultSet().getInt(19));
                modelContaAgentes.setDataNeg(this.getResultSet().getString(20));
                modelContaAgentes.setValorNeg(this.getResultSet().getDouble(21));
                listamodelContaAgentes.add(modelContaAgentes);
            }
        } catch (SQLException e) {
        } finally {
            this.fecharConexao();
        }
        return listamodelContaAgentes;
    }

    /**
     * recupera uma lista de ContaAgentes por data return ArrayList
     *
     * @param pModelConta Lista de Contas Por situação
     * @return Retorna Lista por Situação e tipo de pagamento
     */
    public ArrayList<ModelContaAgentes> getListaContasDAO(ModelContaAgentes pModelConta) {
        ArrayList<ModelContaAgentes> listamodelContaAgentes = new ArrayList();
        ModelContaAgentes modelContaAgentes = new ModelContaAgentes();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "pk_codigo,"
                    + "fk_codigo_pessoa,"
                    + "descricao,"
                    + "data,"
                    + "vencimento,"
                    + "pagamento,"
                    + "fk_tipo_pagamento,"
                    + "observacao,"
                    + "situacao,"
                    + "valor,"
                    + "Processos,"
                    + "AgenteHonorario,"
                    + "AgenteKmPercorrido,"
                    + "AgentePtgoPorKMSeguradoraProcessos,"
                    + "PagamentoTotalKM,"
                    + "AgenteTotalRepasse,"
                    + "TotalGeral,"
                    + "Repasse,"
                    + "banco,"
                    + "DataNeg,"
                    + "ValorNeg"
                    + " FROM"
                    + " conta_agentes where situacao = '" + pModelConta.isSituacao() + "' AND pagamento = '" + pModelConta.getDataRecebimento() + "'"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelContaAgentes = new ModelContaAgentes();
                modelContaAgentes.setCodigo(this.getResultSet().getInt(1));
                modelContaAgentes.setCodigoPessoa(this.getResultSet().getInt(2));
                modelContaAgentes.setDescricao(this.getResultSet().getString(3));
                modelContaAgentes.setDataDespesa(this.getResultSet().getString(4));
                modelContaAgentes.setDataEnvio(this.getResultSet().getString(5));
                modelContaAgentes.setDataRecebimento(this.getResultSet().getString(6));
                modelContaAgentes.setTipoPagamento(this.getResultSet().getInt(7));
                modelContaAgentes.setObservacao(this.getResultSet().getString(8));
                modelContaAgentes.setSituacao(this.getResultSet().getInt(9));
                modelContaAgentes.setValorAdiantamento(this.getResultSet().getDouble(10));
                modelContaAgentes.setProcessos(this.getResultSet().getInt(11));
                modelContaAgentes.setValorHonorarioAgt(this.getResultSet().getDouble(12));
                modelContaAgentes.setAgenteKmPercorrido(this.getResultSet().getInt(13));
                modelContaAgentes.setValorPGTOKMSeguradora(this.getResultSet().getDouble(14));
                modelContaAgentes.setValorPGTOtotalKM(this.getResultSet().getDouble(15));
                modelContaAgentes.setValorHRMaisKM(this.getResultSet().getDouble(16));
                modelContaAgentes.setValorAReceber(this.getResultSet().getDouble(17));
                modelContaAgentes.setRepasse(this.getResultSet().getBoolean(18));
                modelContaAgentes.setBanco(this.getResultSet().getInt(19));
                modelContaAgentes.setDataNeg(this.getResultSet().getString(20));
                modelContaAgentes.setValorNeg(this.getResultSet().getDouble(21));
                listamodelContaAgentes.add(modelContaAgentes);
            }
        } catch (SQLException e) {
        } finally {
            this.fecharConexao();
        }
        return listamodelContaAgentes;
    }

    /**
     * atualiza ContaAgentes
     *
     * @param pModelContaAgentes return boolean
     * @return Atualiza os dados da Contas a Agentes
     */
    public boolean atualizarContaAgentesDAO(ModelContaAgentes pModelContaAgentes) {
        try {
            this.conectar();
            return this.executarUpdateDeleteSQL(
                    "UPDATE conta_agentes SET "
                    + "pk_codigo = '" + pModelContaAgentes.getCodigo() + "',"
                    + "fk_codigo_pessoa = '" + pModelContaAgentes.getCodigoPessoa() + "',"
                    + "descricao = '" + pModelContaAgentes.getDescricao() + "',"
                    + "data = '" + pModelContaAgentes.getDataDespesa() + "',"
                    + "vencimento = '" + pModelContaAgentes.getDataEnvio() + "',"
                    + "pagamento = '" + pModelContaAgentes.getDataRecebimento() + "',"
                    + "fk_tipo_pagamento = '" + pModelContaAgentes.getTipoPagamento() + "',"
                    + "observacao = '" + pModelContaAgentes.getObservacao() + "',"
                    + "situacao = '" + pModelContaAgentes.isSituacao() + "',"
                    + "valor = '" + pModelContaAgentes.getValorAdiantamento() + "',"
                    + "Processos = '" + pModelContaAgentes.getProcessos() + "',"
                    + "AgenteHonorario = '" + pModelContaAgentes.getValorHonorarioAgt() + "',"
                    + "AgenteKmPercorrido = '" + pModelContaAgentes.getAgenteKmPercorrido() + "',"
                    + "AgentePtgoPorKMSeguradoraProcessos = '" + pModelContaAgentes.getValorPGTOKMSeguradora() + "',"
                    + "PagamentoTotalKM = '" + pModelContaAgentes.getValorPGTOtotalKM() + "',"
                    + "AgenteTotalRepasse = '" + pModelContaAgentes.getValorHRMaisKM() + "',"
                    + "TotalGeral = '" + pModelContaAgentes.getValorAReceber() + "',"
                    + "Repasse = '" + pModelContaAgentes.getRepasse() + "',"
                    + "banco = '" + pModelContaAgentes.getBanco() + "',"
                    + "DataNeg = '" + pModelContaAgentes.getDataNeg() + "',"
                    + "ValorNeg = '" + pModelContaAgentes.getValorNeg() + "'"
                    + " WHERE "
                    + "pk_codigo = '" + pModelContaAgentes.getCodigo() + "'"
                    + ";"
            );
        } catch (Exception e) {
            return false;
        } finally {
            this.fecharConexao();
        }
    }

    /**
     * exclui ContaAgentes
     *
     * @param pCodigo exclui ContaAgentes
     * @return verifica se foi excluido com sucesso
     */
    public boolean excluirContaAgentesDAO(int pCodigo) {
        try {
            this.conectar();
            return this.executarUpdateDeleteSQL(
                    "DELETE FROM conta_agentes "
                    + " WHERE "
                    + "pk_codigo = '" + pCodigo + "'"
                    + ";"
            );
        } catch (Exception e) {
            return false;
        } finally {
            this.fecharConexao();
        }
    }

    public boolean agentesContaAgentesDAO(ModelContaAgentes pModelContaAgentes) {
        try {
            this.conectar();
            return this.executarUpdateDeleteSQL(
                    "UPDATE conta_agentes SET "
                    + "pagamento = '" + pModelContaAgentes.getDataRecebimento() + "',"
                    + "situacao = '" + pModelContaAgentes.isSituacao() + "'"
                    + " WHERE "
                    + "pk_codigo = '" + pModelContaAgentes.getCodigo() + "'"
                    + ";"
            );
        } catch (Exception e) {
            return false;
        } finally {
            this.fecharConexao();
        }
    }

    /**
     * recupera uma lista de ContaAgentes por data return ArrayList
     *
     * @param AgenteCod Descrição
     * @param pSituacao Descrição
     * @param pDataInicial Lista de Contas a Agentes por data de entrada
     * @param dataFinal Lista de Contas a Agentes por data final
     * @return Verifica se executou com sucesso
     */
    public ArrayList<ModelContaAgentes> FiltraContaAgentesFluxoDados(int AgenteCod, int pSituacao, Date pDataInicial, Date dataFinal) {

        ArrayList<ModelContaAgentes> listamodelContaAgentes = new ArrayList();
        ModelContaAgentes modelContaAgentes = new ModelContaAgentes();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "pk_codigo,"
                    + "fk_codigo_pessoa,"
                    + "descricao,"
                    + "data,"
                    + "vencimento,"
                    + "pagamento,"
                    + "fk_tipo_pagamento,"
                    + "observacao,"
                    + "situacao,"
                    + "valor,"
                    + "Processos,"
                    + "AgenteHonorario,"
                    + "AgenteKmPercorrido,"
                    + "AgentePtgoPorKMSeguradoraProcessos,"
                    + "PagamentoTotalKM,"
                    + "AgenteTotalRepasse,"
                    + "TotalGeral,"
                    + "Repasse,"
                    + "banco,"
                    + "DataNeg,"
                    + "ValorNeg"
                    + " FROM"
                    + " conta_agentes where situacao = '" + pSituacao + "' and fk_codigo_pessoa = '" + AgenteCod + "' and (vencimento BETWEEN '" + pDataInicial + "'AND '" + dataFinal + "')"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelContaAgentes = new ModelContaAgentes();
                modelContaAgentes.setCodigo(this.getResultSet().getInt(1));
                modelContaAgentes.setCodigoPessoa(this.getResultSet().getInt(2));
                modelContaAgentes.setDescricao(this.getResultSet().getString(3));
                modelContaAgentes.setDataDespesa(this.getResultSet().getString(4));
                modelContaAgentes.setDataEnvio(this.getResultSet().getString(5));
                modelContaAgentes.setDataRecebimento(this.getResultSet().getString(6));
                modelContaAgentes.setTipoPagamento(this.getResultSet().getInt(7));
                modelContaAgentes.setObservacao(this.getResultSet().getString(8));
                modelContaAgentes.setSituacao(this.getResultSet().getInt(9));
                modelContaAgentes.setValorAdiantamento(this.getResultSet().getDouble(10));
                modelContaAgentes.setProcessos(this.getResultSet().getInt(11));
                modelContaAgentes.setValorHonorarioAgt(this.getResultSet().getDouble(12));
                modelContaAgentes.setAgenteKmPercorrido(this.getResultSet().getInt(13));
                modelContaAgentes.setValorPGTOKMSeguradora(this.getResultSet().getDouble(14));
                modelContaAgentes.setValorPGTOtotalKM(this.getResultSet().getDouble(15));
                modelContaAgentes.setValorHRMaisKM(this.getResultSet().getDouble(16));
                modelContaAgentes.setValorAReceber(this.getResultSet().getDouble(17));
                modelContaAgentes.setRepasse(this.getResultSet().getBoolean(18));
                modelContaAgentes.setBanco(this.getResultSet().getInt(19));
                modelContaAgentes.setDataNeg(this.getResultSet().getString(20));
                modelContaAgentes.setValorNeg(this.getResultSet().getDouble(21));
                listamodelContaAgentes.add(modelContaAgentes);
            }
        } catch (SQLException e) {
        } finally {
            this.fecharConexao();
        }
        return listamodelContaAgentes;
    }

    public ArrayList<ModelContaAgentes> FiltraContaTodosAgentesFluxoDados(int pSituacao, Date pDataInicial, Date dataFinal) {

        ArrayList<ModelContaAgentes> listamodelContaAgentes = new ArrayList();
        ModelContaAgentes modelContaAgentes = new ModelContaAgentes();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "pk_codigo,"
                    + "fk_codigo_pessoa,"
                    + "descricao,"
                    + "data,"
                    + "vencimento,"
                    + "pagamento,"
                    + "fk_tipo_pagamento,"
                    + "observacao,"
                    + "situacao,"
                    + "valor,"
                    + "Processos,"
                    + "AgenteHonorario,"
                    + "AgenteKmPercorrido,"
                    + "AgentePtgoPorKMSeguradoraProcessos,"
                    + "PagamentoTotalKM,"
                    + "AgenteTotalRepasse,"
                    + "TotalGeral,"
                    + "Repasse,"
                    + "banco,"
                    + "DataNeg,"
                    + "ValorNeg"
                    + " FROM"
                    + " conta_agentes where situacao = '" + pSituacao + "' and (vencimento BETWEEN '" + pDataInicial + "'AND '" + dataFinal + "')"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelContaAgentes = new ModelContaAgentes();
                modelContaAgentes.setCodigo(this.getResultSet().getInt(1));
                modelContaAgentes.setCodigoPessoa(this.getResultSet().getInt(2));
                modelContaAgentes.setDescricao(this.getResultSet().getString(3));
                modelContaAgentes.setDataDespesa(this.getResultSet().getString(4));
                modelContaAgentes.setDataEnvio(this.getResultSet().getString(5));
                modelContaAgentes.setDataRecebimento(this.getResultSet().getString(6));
                modelContaAgentes.setTipoPagamento(this.getResultSet().getInt(7));
                modelContaAgentes.setObservacao(this.getResultSet().getString(8));
                modelContaAgentes.setSituacao(this.getResultSet().getInt(9));
                modelContaAgentes.setValorAdiantamento(this.getResultSet().getDouble(10));
                modelContaAgentes.setProcessos(this.getResultSet().getInt(11));
                modelContaAgentes.setValorHonorarioAgt(this.getResultSet().getDouble(12));
                modelContaAgentes.setAgenteKmPercorrido(this.getResultSet().getInt(13));
                modelContaAgentes.setValorPGTOKMSeguradora(this.getResultSet().getDouble(14));
                modelContaAgentes.setValorPGTOtotalKM(this.getResultSet().getDouble(15));
                modelContaAgentes.setValorHRMaisKM(this.getResultSet().getDouble(16));
                modelContaAgentes.setValorAReceber(this.getResultSet().getDouble(17));
                modelContaAgentes.setRepasse(this.getResultSet().getBoolean(18));
                modelContaAgentes.setBanco(this.getResultSet().getInt(19));
                modelContaAgentes.setDataNeg(this.getResultSet().getString(20));
                modelContaAgentes.setValorNeg(this.getResultSet().getDouble(21));
                listamodelContaAgentes.add(modelContaAgentes);
            }
        } catch (SQLException e) {
        } finally {
            this.fecharConexao();
        }
        return listamodelContaAgentes;
    }

    public ArrayList<ModelContaAgentes> FiltraContaAgentesFluxoDadosPorAgenteDAO(int pAgente, Date pDataInicial, Date dataFinal) {
        ArrayList<ModelContaAgentes> listamodelContaAgentes = new ArrayList();
        ModelContaAgentes modelContaAgentes = new ModelContaAgentes();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "pk_codigo,"
                    + "fk_codigo_pessoa,"
                    + "descricao,"
                    + "data,"
                    + "vencimento,"
                    + "pagamento,"
                    + "fk_tipo_pagamento,"
                    + "observacao,"
                    + "situacao,"
                    + "valor,"
                    + "Processos,"
                    + "AgenteHonorario,"
                    + "AgenteKmPercorrido,"
                    + "AgentePtgoPorKMSeguradoraProcessos,"
                    + "PagamentoTotalKM,"
                    + "AgenteTotalRepasse,"
                    + "TotalGeral,"
                    + "Repasse,"
                    + "banco,"
                    + "DataNeg,"
                    + "ValorNeg"
                    + " FROM"
                    + " conta_agentes where fk_codigo_pessoa = '" + pAgente + "' and (vencimento BETWEEN '" + pDataInicial + "'AND '" + dataFinal + "')"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelContaAgentes = new ModelContaAgentes();
                modelContaAgentes.setCodigo(this.getResultSet().getInt(1));
                modelContaAgentes.setCodigoPessoa(this.getResultSet().getInt(2));
                modelContaAgentes.setDescricao(this.getResultSet().getString(3));
                modelContaAgentes.setDataDespesa(this.getResultSet().getString(4));
                modelContaAgentes.setDataEnvio(this.getResultSet().getString(5));
                modelContaAgentes.setDataRecebimento(this.getResultSet().getString(6));
                modelContaAgentes.setTipoPagamento(this.getResultSet().getInt(7));
                modelContaAgentes.setObservacao(this.getResultSet().getString(8));
                modelContaAgentes.setSituacao(this.getResultSet().getInt(9));
                modelContaAgentes.setValorAdiantamento(this.getResultSet().getDouble(10));
                modelContaAgentes.setProcessos(this.getResultSet().getInt(11));
                modelContaAgentes.setValorHonorarioAgt(this.getResultSet().getDouble(12));
                modelContaAgentes.setAgenteKmPercorrido(this.getResultSet().getInt(13));
                modelContaAgentes.setValorPGTOKMSeguradora(this.getResultSet().getDouble(14));
                modelContaAgentes.setValorPGTOtotalKM(this.getResultSet().getDouble(15));
                modelContaAgentes.setValorHRMaisKM(this.getResultSet().getDouble(16));
                modelContaAgentes.setValorAReceber(this.getResultSet().getDouble(17));
                modelContaAgentes.setRepasse(this.getResultSet().getBoolean(18));
                modelContaAgentes.setBanco(this.getResultSet().getInt(19));
                modelContaAgentes.setDataNeg(this.getResultSet().getString(20));
                modelContaAgentes.setValorNeg(this.getResultSet().getDouble(21));
                listamodelContaAgentes.add(modelContaAgentes);
            }
        } catch (SQLException e) {
        } finally {
            this.fecharConexao();
        }
        return listamodelContaAgentes;
    }
}
