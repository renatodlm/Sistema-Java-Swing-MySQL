package DAO;

import conexao.ConexaoBanco;
import java.util.ArrayList;
import Model.ModelContaReceber;
import java.sql.Date;
import java.sql.SQLException;

/**
 *
 * @author studiomicroweb
 */
public class DAOContaReceber extends ConexaoBanco {

    /**
     * grava ContaReceber
     *
     * @param pModelContaReceber return int
     * @return Salva a Contas a Receber no SQL
     */
    public int salvarContaReceberDAO(ModelContaReceber pModelContaReceber) {
        try {
            this.conectar();
            return this.insertSQL(
                    "INSERT INTO contas_receber ("
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
                    + "Repasse,"
                    + "banco,"
                    + "FuncionarioCheck"
                    + ") VALUES ("
                    + "'" + pModelContaReceber.getCodigoPessoa() + "',"
                    + "'" + pModelContaReceber.getDescricao() + "',"
                    + "'" + pModelContaReceber.getData() + "',"
                    + "'" + pModelContaReceber.getVencimento() + "',"
                    + "'" + pModelContaReceber.getPagamento() + "',"
                    + "'" + pModelContaReceber.getTipoPagamento() + "',"
                    + "'" + pModelContaReceber.getObservacao() + "',"
                    + "'" + pModelContaReceber.isSituacao() + "',"
                    + "'" + pModelContaReceber.getValor() + "',"
                    + "'" + pModelContaReceber.getProcessos() + "',"
                    + "'" + pModelContaReceber.getRepasse() + "',"
                    + "'" + pModelContaReceber.getBanco() + "',"
                    + "'" + pModelContaReceber.isFuncionarioCheck() + "'"
                    + ");"
            );
        } catch (Exception e) {
            return 0;
        } finally {
            this.fecharConexao();
        }
    }

    /**
     * recupera ContaReceber
     *
     * @param pCodigo return ModelConta
     * @return Retorna a Conta a Receber por codigo
     */
    public ModelContaReceber getContaReceberDAO(int pCodigo) {
        ModelContaReceber modelContaReceber = new ModelContaReceber();
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
                    + "Repasse,"
                    + "banco,"
                    + "FuncionarioCheck"
                    + " FROM"
                    + " contas_receber"
                    + " WHERE"
                    + " pk_codigo = '" + pCodigo + "'"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelContaReceber.setCodigo(this.getResultSet().getInt(1));
                modelContaReceber.setCodigoPessoa(this.getResultSet().getInt(2));
                modelContaReceber.setDescricao(this.getResultSet().getString(3));
                modelContaReceber.setData(this.getResultSet().getString(4));
                modelContaReceber.setVencimento(this.getResultSet().getString(5));
                modelContaReceber.setPagamento(this.getResultSet().getString(6));
                modelContaReceber.setTipoPagamento(this.getResultSet().getInt(7));
                modelContaReceber.setObservacao(this.getResultSet().getString(8));
                modelContaReceber.setSituacao(this.getResultSet().getInt(9));
                modelContaReceber.setValor(this.getResultSet().getDouble(10));
                modelContaReceber.setProcessos(this.getResultSet().getInt(11));
                modelContaReceber.setRepasse(this.getResultSet().getBoolean(12));
                modelContaReceber.setBanco(this.getResultSet().getInt(13));
                modelContaReceber.setFuncionarioCheck(this.getResultSet().getBoolean(14));
            }
        } catch (SQLException e) {
        } finally {
            this.fecharConexao();
        }
        return modelContaReceber;
    }

    /**
     * recupera uma lista de ContaReceber return ArrayList
     *
     * @return Retorna do Banco de Dados Lista de Contas a Receber
     */
    public ArrayList<ModelContaReceber> getListaContaReceberDAO() {
        ArrayList<ModelContaReceber> listamodelConta = new ArrayList();
        ModelContaReceber modelContaReceber = new ModelContaReceber();
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
                    + "Repasse,"
                    + "banco,"
                    + "FuncionarioCheck"
                    + " FROM"
                    + " contas_receber"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelContaReceber = new ModelContaReceber();
                modelContaReceber.setCodigo(this.getResultSet().getInt(1));
                modelContaReceber.setCodigoPessoa(this.getResultSet().getInt(2));
                modelContaReceber.setDescricao(this.getResultSet().getString(3));
                modelContaReceber.setData(this.getResultSet().getString(4));
                modelContaReceber.setVencimento(this.getResultSet().getString(5));
                modelContaReceber.setPagamento(this.getResultSet().getString(6));
                modelContaReceber.setTipoPagamento(this.getResultSet().getInt(7));
                modelContaReceber.setObservacao(this.getResultSet().getString(8));
                modelContaReceber.setSituacao(this.getResultSet().getInt(9));
                modelContaReceber.setValor(this.getResultSet().getDouble(10));
                modelContaReceber.setProcessos(this.getResultSet().getInt(11));
                modelContaReceber.setRepasse(this.getResultSet().getBoolean(12));
                modelContaReceber.setBanco(this.getResultSet().getInt(13));
                modelContaReceber.setFuncionarioCheck(this.getResultSet().getBoolean(14));
                listamodelConta.add(modelContaReceber);
            }
        } catch (SQLException e) {
        } finally {
            this.fecharConexao();
        }
        return listamodelConta;
    }

    /**
     * recupera uma lista de ContaReceber return ArrayList
     *
     * @param pStatus Verifica o Status para montar a lista das contas a receber
     * @return Retorna a Lista de Contas a receber de acordo com status
     */
    public ArrayList<ModelContaReceber> getListaContaReceberDAO(int pStatus) {
        ArrayList<ModelContaReceber> listamodelContaReceber = new ArrayList();
        ModelContaReceber modelContaReceber = new ModelContaReceber();
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
                    + "Repasse,"
                    + "banco,"
                    + "FuncionarioCheck"
                    + " FROM"
                    + " contas_receber where situacao = '" + pStatus + "'"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelContaReceber = new ModelContaReceber();
                modelContaReceber.setCodigo(this.getResultSet().getInt(1));
                modelContaReceber.setCodigoPessoa(this.getResultSet().getInt(2));
                modelContaReceber.setDescricao(this.getResultSet().getString(3));
                modelContaReceber.setData(this.getResultSet().getString(4));
                modelContaReceber.setVencimento(this.getResultSet().getString(5));
                modelContaReceber.setPagamento(this.getResultSet().getString(6));
                modelContaReceber.setTipoPagamento(this.getResultSet().getInt(7));
                modelContaReceber.setObservacao(this.getResultSet().getString(8));
                modelContaReceber.setSituacao(this.getResultSet().getInt(9));
                modelContaReceber.setValor(this.getResultSet().getDouble(10));
                modelContaReceber.setProcessos(this.getResultSet().getInt(11));
                modelContaReceber.setRepasse(this.getResultSet().getBoolean(12));
                modelContaReceber.setBanco(this.getResultSet().getInt(13));
                modelContaReceber.setFuncionarioCheck(this.getResultSet().getBoolean(14));
                listamodelContaReceber.add(modelContaReceber);
            }
        } catch (SQLException e) {
        } finally {
            this.fecharConexao();
        }
        return listamodelContaReceber;
    }

    /**
     * recupera uma lista de ContaReceber return ArrayList
     *
     * @param pStatus Lista de Contas a Receber por Staus de pagamento
     * @return Verifica se foi feita a listagem
     */
    public ArrayList<ModelContaReceber> getListaContasDAO(int pStatus) {
        ArrayList<ModelContaReceber> listamodelContaReceber = new ArrayList();
        ModelContaReceber modelContaReceber = new ModelContaReceber();
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
                    + "Repasse,"
                    + "banco,"
                    + "FuncionarioCheck"
                    + " FROM"
                    + " contas_receber where situacao = '" + pStatus + "'"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelContaReceber = new ModelContaReceber();
                modelContaReceber.setCodigo(this.getResultSet().getInt(1));
                modelContaReceber.setCodigoPessoa(this.getResultSet().getInt(2));
                modelContaReceber.setDescricao(this.getResultSet().getString(3));
                modelContaReceber.setData(this.getResultSet().getString(4));
                modelContaReceber.setVencimento(this.getResultSet().getString(5));
                modelContaReceber.setPagamento(this.getResultSet().getString(6));
                modelContaReceber.setTipoPagamento(this.getResultSet().getInt(7));
                modelContaReceber.setObservacao(this.getResultSet().getString(8));
                modelContaReceber.setSituacao(this.getResultSet().getInt(9));
                modelContaReceber.setValor(this.getResultSet().getDouble(10));
                modelContaReceber.setProcessos(this.getResultSet().getInt(11));
                modelContaReceber.setRepasse(this.getResultSet().getBoolean(12));
                modelContaReceber.setBanco(this.getResultSet().getInt(13));
                modelContaReceber.setFuncionarioCheck(this.getResultSet().getBoolean(14));
                listamodelContaReceber.add(modelContaReceber);
            }
        } catch (SQLException e) {
        } finally {
            this.fecharConexao();
        }
        return listamodelContaReceber;
    }

    /**
     * recupera uma lista de ContaReceber por data return ArrayList
     *
     * @param pModelConta Lista de Contas Por situação
     * @return Retorna Lista por Situação e tipo de pagamento
     */
    public ArrayList<ModelContaReceber> getListaContasDAO(ModelContaReceber pModelConta) {
        ArrayList<ModelContaReceber> listamodelContaReceber = new ArrayList();
        ModelContaReceber modelContaReceber = new ModelContaReceber();
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
                    + "Repasse,"
                    + "banco,"
                    + "FuncionarioCheck"
                    + " FROM"
                    + " contas_receber where situacao = '" + pModelConta.isSituacao() + "' AND pagamento = '" + pModelConta.getPagamento() + "'"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelContaReceber = new ModelContaReceber();
                modelContaReceber.setCodigo(this.getResultSet().getInt(1));
                modelContaReceber.setCodigoPessoa(this.getResultSet().getInt(2));
                modelContaReceber.setDescricao(this.getResultSet().getString(3));
                modelContaReceber.setData(this.getResultSet().getString(4));
                modelContaReceber.setVencimento(this.getResultSet().getString(5));
                modelContaReceber.setPagamento(this.getResultSet().getString(6));
                modelContaReceber.setTipoPagamento(this.getResultSet().getInt(7));
                modelContaReceber.setObservacao(this.getResultSet().getString(8));
                modelContaReceber.setSituacao(this.getResultSet().getInt(9));
                modelContaReceber.setValor(this.getResultSet().getDouble(10));
                modelContaReceber.setProcessos(this.getResultSet().getInt(11));
                modelContaReceber.setRepasse(this.getResultSet().getBoolean(12));
                modelContaReceber.setBanco(this.getResultSet().getInt(13));
                modelContaReceber.setFuncionarioCheck(this.getResultSet().getBoolean(14));
                listamodelContaReceber.add(modelContaReceber);
            }
        } catch (SQLException e) {
        } finally {
            this.fecharConexao();
        }
        return listamodelContaReceber;
    }

    /**
     * atualiza ContaReceber
     *
     * @param pModelContaReceber return boolean
     * @return Atualiza os dados da Contas a Receber
     */
    public boolean atualizarContaReceberDAO(ModelContaReceber pModelContaReceber) {
        try {
            this.conectar();
            return this.executarUpdateDeleteSQL(
                    "UPDATE contas_receber SET "
                    + "pk_codigo = '" + pModelContaReceber.getCodigo() + "',"
                    + "fk_codigo_pessoa = '" + pModelContaReceber.getCodigoPessoa() + "',"
                    + "descricao = '" + pModelContaReceber.getDescricao() + "',"
                    + "data = '" + pModelContaReceber.getData() + "',"
                    + "vencimento = '" + pModelContaReceber.getVencimento() + "',"
                    + "pagamento = '" + pModelContaReceber.getPagamento() + "',"
                    + "fk_tipo_pagamento = '" + pModelContaReceber.getTipoPagamento() + "',"
                    + "observacao = '" + pModelContaReceber.getObservacao() + "',"
                    + "situacao = '" + pModelContaReceber.isSituacao() + "',"
                    + "valor = '" + pModelContaReceber.getValor() + "',"
                    + "Processos = '" + pModelContaReceber.getProcessos() + "',"
                    + "Repasse = '" + pModelContaReceber.getRepasse() + "',"
                    + "banco = '" + pModelContaReceber.getBanco() + "',"
                    + "FuncionarioCheck = '" + pModelContaReceber.isFuncionarioCheck() + "'"
                    + " WHERE "
                    + "pk_codigo = '" + pModelContaReceber.getCodigo() + "'"
                    + ";"
            );
        } catch (Exception e) {
            return false;
        } finally {
            this.fecharConexao();
        }
    }

    /**
     * exclui ContaReceber
     *
     * @param pCodigo exclui ContaReceber
     * @return verifica se foi excluido com sucesso
     */
    public boolean excluirContaReceberDAO(int pCodigo) {
        try {
            this.conectar();
            return this.executarUpdateDeleteSQL(
                    "DELETE FROM contas_receber "
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

    public boolean receberContaReceberDAO(ModelContaReceber pModelContaReceber) {
        try {
            this.conectar();
            return this.executarUpdateDeleteSQL(
                    "UPDATE contas_receber SET "
                    + "pagamento = '" + pModelContaReceber.getPagamento() + "',"
                    + "situacao = '" + pModelContaReceber.isSituacao() + "'"
                    + " WHERE "
                    + "pk_codigo = '" + pModelContaReceber.getCodigo() + "'"
                    + ";"
            );
        } catch (Exception e) {
            return false;
        } finally {
            this.fecharConexao();
        }
    }

    /**
     * recupera uma lista de ContaReceber por data return ArrayList
     *
     * @param pModelContaReceber Lista de Contas a Receber
     * @param pDataInicial Lista de Contas a Receber por data de entrada
     * @param dataFinal Lista de Contas a Receber por data final
     * @return Verifica se executou com sucesso
     */
    public ArrayList<ModelContaReceber> FiltraContaReceberFluxoDados(ModelContaReceber pModelContaReceber, Date pDataInicial, Date dataFinal) {
        ArrayList<ModelContaReceber> listamodelContaReceber = new ArrayList();
        ModelContaReceber modelContaReceber = new ModelContaReceber();
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
                    + "Repasse,"
                    + "banco,"
                    + "FuncionarioCheck"
                    + " FROM"
                    + " contas_receber where situacao = 1 and pagamento BETWEEN '" + pDataInicial + "'AND '" + dataFinal + "'"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelContaReceber = new ModelContaReceber();
                modelContaReceber.setCodigo(this.getResultSet().getInt(1));
                modelContaReceber.setCodigoPessoa(this.getResultSet().getInt(2));
                modelContaReceber.setDescricao(this.getResultSet().getString(3));
                modelContaReceber.setData(this.getResultSet().getString(4));
                modelContaReceber.setVencimento(this.getResultSet().getString(5));
                modelContaReceber.setPagamento(this.getResultSet().getString(6));
                modelContaReceber.setTipoPagamento(this.getResultSet().getInt(7));
                modelContaReceber.setObservacao(this.getResultSet().getString(8));
                modelContaReceber.setSituacao(this.getResultSet().getInt(9));
                modelContaReceber.setValor(this.getResultSet().getDouble(10));
                modelContaReceber.setProcessos(this.getResultSet().getInt(11));
                modelContaReceber.setRepasse(this.getResultSet().getBoolean(12));
                modelContaReceber.setBanco(this.getResultSet().getInt(13));
                modelContaReceber.setFuncionarioCheck(this.getResultSet().getBoolean(14));
                listamodelContaReceber.add(modelContaReceber);
            }
        } catch (SQLException e) {
        } finally {
            this.fecharConexao();
        }
        return listamodelContaReceber;
    }

}
