package DAO;

import conexao.ConexaoBanco;
import java.util.ArrayList;
import Model.ModelContaPagar;
import java.sql.Date;
import java.sql.SQLException;

/**
 *
 * @author studiomicroweb
 */
public class DAOContaPagar extends ConexaoBanco {

    /**
     * grava ContaPagar
     *
     * @param pModelContaPagar return int
     * @return Salva a Contas a Pagar no SQL
     */
    public int salvarContaPagarDAO(ModelContaPagar pModelContaPagar) {
        try {
            this.conectar();
            return this.insertSQL(
                    "INSERT INTO contas_pagar ("
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
                    + "'" + pModelContaPagar.getCodigoPessoa() + "',"
                    + "'" + pModelContaPagar.getDescricao() + "',"
                    + "'" + pModelContaPagar.getData() + "',"
                    + "'" + pModelContaPagar.getVencimento() + "',"
                    + "'" + pModelContaPagar.getPagamento() + "',"
                    + "'" + pModelContaPagar.getTipoPagamento() + "',"
                    + "'" + pModelContaPagar.getObservacao() + "',"
                    + "'" + pModelContaPagar.isSituacao() + "',"
                    + "'" + pModelContaPagar.getValor() + "',"
                    + "'" + pModelContaPagar.getProcessos() + "',"
                    + "'" + pModelContaPagar.getRepasse() + "',"
                    + "'" + pModelContaPagar.getBanco() + "',"
                    + "'" + pModelContaPagar.isFuncionarioCheck() + "'"
                    + ");"
            );
        } catch (Exception e) {
            return 0;
        } finally {
            this.fecharConexao();
        }
    }

    /**
     * recupera ContaPagar
     *
     * @param pCodigo return ModelConta
     * @return Retorna a Conta a Pagar por codigo
     */
    public ModelContaPagar getContaPagarDAO(int pCodigo) {
        ModelContaPagar modelContaPagar = new ModelContaPagar();
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
                    + " contas_pagar"
                    + " WHERE"
                    + " pk_codigo = '" + pCodigo + "'"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelContaPagar.setCodigo(this.getResultSet().getInt(1));
                modelContaPagar.setCodigoPessoa(this.getResultSet().getInt(2));
                modelContaPagar.setDescricao(this.getResultSet().getString(3));
                modelContaPagar.setData(this.getResultSet().getString(4));
                modelContaPagar.setVencimento(this.getResultSet().getString(5));
                modelContaPagar.setPagamento(this.getResultSet().getString(6));
                modelContaPagar.setTipoPagamento(this.getResultSet().getString(7));
                modelContaPagar.setObservacao(this.getResultSet().getString(8));
                modelContaPagar.setSituacao(this.getResultSet().getInt(9));
                modelContaPagar.setValor(this.getResultSet().getDouble(10));
                modelContaPagar.setProcessos(this.getResultSet().getInt(11));
                modelContaPagar.setRepasse(this.getResultSet().getBoolean(12));
                modelContaPagar.setBanco(this.getResultSet().getInt(13));
                modelContaPagar.setFuncionarioCheck(this.getResultSet().getBoolean(14));
            }
        } catch (SQLException e) {
        } finally {
            this.fecharConexao();
        }
        return modelContaPagar;
    }

    /**
     * recupera uma lista de ContaPagar return ArrayList
     *
     * @return Retorna do Banco de Dados Lista de Contas a Pagar
     */
    public ArrayList<ModelContaPagar> getListaContaPagarDAO() {
        ArrayList<ModelContaPagar> listamodelConta = new ArrayList();
        ModelContaPagar modelContaPagar = new ModelContaPagar();
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
                    + " contas_pagar"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelContaPagar = new ModelContaPagar();
                modelContaPagar.setCodigo(this.getResultSet().getInt(1));
                modelContaPagar.setCodigoPessoa(this.getResultSet().getInt(2));
                modelContaPagar.setDescricao(this.getResultSet().getString(3));
                modelContaPagar.setData(this.getResultSet().getString(4));
                modelContaPagar.setVencimento(this.getResultSet().getString(5));
                modelContaPagar.setPagamento(this.getResultSet().getString(6));
                modelContaPagar.setTipoPagamento(this.getResultSet().getString(7));
                modelContaPagar.setObservacao(this.getResultSet().getString(8));
                modelContaPagar.setSituacao(this.getResultSet().getInt(9));
                modelContaPagar.setValor(this.getResultSet().getDouble(10));
                modelContaPagar.setProcessos(this.getResultSet().getInt(11));
                modelContaPagar.setRepasse(this.getResultSet().getBoolean(12));
                modelContaPagar.setBanco(this.getResultSet().getInt(13));
                modelContaPagar.setFuncionarioCheck(this.getResultSet().getBoolean(14));
                listamodelConta.add(modelContaPagar);
            }
        } catch (SQLException e) {
        } finally {
            this.fecharConexao();
        }
        return listamodelConta;
    }

    /**
     * recupera uma lista de ContaPagar return ArrayList
     *
     * @param pStatus Verifica o Status para montar a lista das contas a pagar
     * @return Retorna a Lista de Contas a pagar de acordo com status
     */
    public ArrayList<ModelContaPagar> getListaContaPagarDAO(int pStatus) {
        ArrayList<ModelContaPagar> listamodelContaPagar = new ArrayList();
        ModelContaPagar modelContaPagar = new ModelContaPagar();
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
                    + " contas_pagar where situacao = '" + pStatus + "'"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelContaPagar = new ModelContaPagar();
                modelContaPagar.setCodigo(this.getResultSet().getInt(1));
                modelContaPagar.setCodigoPessoa(this.getResultSet().getInt(2));
                modelContaPagar.setDescricao(this.getResultSet().getString(3));
                modelContaPagar.setData(this.getResultSet().getString(4));
                modelContaPagar.setVencimento(this.getResultSet().getString(5));
                modelContaPagar.setPagamento(this.getResultSet().getString(6));
                modelContaPagar.setTipoPagamento(this.getResultSet().getString(7));
                modelContaPagar.setObservacao(this.getResultSet().getString(8));
                modelContaPagar.setSituacao(this.getResultSet().getInt(9));
                modelContaPagar.setValor(this.getResultSet().getDouble(10));
                modelContaPagar.setProcessos(this.getResultSet().getInt(11));
                modelContaPagar.setRepasse(this.getResultSet().getBoolean(12));
                modelContaPagar.setBanco(this.getResultSet().getInt(13));
                modelContaPagar.setFuncionarioCheck(this.getResultSet().getBoolean(14));
                listamodelContaPagar.add(modelContaPagar);
            }
        } catch (SQLException e) {
        } finally {
            this.fecharConexao();
        }
        return listamodelContaPagar;
    }

    /**
     * recupera uma lista de ContaPagar return ArrayList
     *
     * @param pStatus Lista de Contas a Pagar por Staus de pagamento
     * @return Verifica se foi feita a listagem
     */
    public ArrayList<ModelContaPagar> getListaContasDAO(int pStatus) {
        ArrayList<ModelContaPagar> listamodelContaPagar = new ArrayList();
        ModelContaPagar modelContaPagar = new ModelContaPagar();
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
                    + " contas_pagar where situacao = '" + pStatus + "'"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelContaPagar = new ModelContaPagar();
                modelContaPagar.setCodigo(this.getResultSet().getInt(1));
                modelContaPagar.setCodigoPessoa(this.getResultSet().getInt(2));
                modelContaPagar.setDescricao(this.getResultSet().getString(3));
                modelContaPagar.setData(this.getResultSet().getString(4));
                modelContaPagar.setVencimento(this.getResultSet().getString(5));
                modelContaPagar.setPagamento(this.getResultSet().getString(6));
                modelContaPagar.setTipoPagamento(this.getResultSet().getString(7));
                modelContaPagar.setObservacao(this.getResultSet().getString(8));
                modelContaPagar.setSituacao(this.getResultSet().getInt(9));
                modelContaPagar.setValor(this.getResultSet().getDouble(10));
                modelContaPagar.setProcessos(this.getResultSet().getInt(11));
                modelContaPagar.setRepasse(this.getResultSet().getBoolean(12));
                modelContaPagar.setBanco(this.getResultSet().getInt(13));
                modelContaPagar.setFuncionarioCheck(this.getResultSet().getBoolean(14));
                listamodelContaPagar.add(modelContaPagar);
            }
        } catch (SQLException e) {
        } finally {
            this.fecharConexao();
        }
        return listamodelContaPagar;
    }

    /**
     * recupera uma lista de ContaPagar por data return ArrayList
     *
     * @param pModelConta Lista de Contas Por situação
     * @return Retorna Lista por Situação e tipo de pagamento
     */
    public ArrayList<ModelContaPagar> getListaContasDAO(ModelContaPagar pModelConta) {
        ArrayList<ModelContaPagar> listamodelContaPagar = new ArrayList();
        ModelContaPagar modelContaPagar = new ModelContaPagar();
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
                    + " contas_pagar where situacao = '" + pModelConta.isSituacao() + "' AND pagamento = '" + pModelConta.getPagamento() + "'"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelContaPagar = new ModelContaPagar();
                modelContaPagar.setCodigo(this.getResultSet().getInt(1));
                modelContaPagar.setCodigoPessoa(this.getResultSet().getInt(2));
                modelContaPagar.setDescricao(this.getResultSet().getString(3));
                modelContaPagar.setData(this.getResultSet().getString(4));
                modelContaPagar.setVencimento(this.getResultSet().getString(5));
                modelContaPagar.setPagamento(this.getResultSet().getString(6));
                modelContaPagar.setTipoPagamento(this.getResultSet().getString(7));
                modelContaPagar.setObservacao(this.getResultSet().getString(8));
                modelContaPagar.setSituacao(this.getResultSet().getInt(9));
                modelContaPagar.setValor(this.getResultSet().getDouble(10));
                modelContaPagar.setProcessos(this.getResultSet().getInt(11));
                modelContaPagar.setRepasse(this.getResultSet().getBoolean(12));
                modelContaPagar.setBanco(this.getResultSet().getInt(13));
                modelContaPagar.setFuncionarioCheck(this.getResultSet().getBoolean(14));
                listamodelContaPagar.add(modelContaPagar);
            }
        } catch (SQLException e) {
        } finally {
            this.fecharConexao();
        }
        return listamodelContaPagar;
    }

    /**
     * atualiza ContaPagar
     *
     * @param pModelContaPagar return boolean
     * @return Atualiza os dados da Contas a Pagar
     */
    public boolean atualizarContaPagarDAO(ModelContaPagar pModelContaPagar) {
        try {
            this.conectar();
            return this.executarUpdateDeleteSQL(
                    "UPDATE contas_pagar SET "
                    + "pk_codigo = '" + pModelContaPagar.getCodigo() + "',"
                    + "fk_codigo_pessoa = '" + pModelContaPagar.getCodigoPessoa() + "',"
                    + "descricao = '" + pModelContaPagar.getDescricao() + "',"
                    + "data = '" + pModelContaPagar.getData() + "',"
                    + "vencimento = '" + pModelContaPagar.getVencimento() + "',"
                    + "pagamento = '" + pModelContaPagar.getPagamento() + "',"
                    + "fk_tipo_pagamento = '" + pModelContaPagar.getTipoPagamento() + "',"
                    + "observacao = '" + pModelContaPagar.getObservacao() + "',"
                    + "situacao = '" + pModelContaPagar.isSituacao() + "',"
                    + "valor = '" + pModelContaPagar.getValor() + "',"
                    + "Processos = '" + pModelContaPagar.getProcessos() + "',"
                    + "Repasse = '" + pModelContaPagar.getRepasse() + "',"
                    + "banco = '" + pModelContaPagar.getBanco() + "',"
                    + "FuncionarioCheck = '" + pModelContaPagar.isFuncionarioCheck() + "'"
                    + " WHERE "
                    + "pk_codigo = '" + pModelContaPagar.getCodigo() + "'"
                    + ";"
            );
        } catch (Exception e) {
            return false;
        } finally {
            this.fecharConexao();
        }
    }

    /**
     * exclui ContaPagar
     *
     * @param pCodigo exclui ContaPagar
     * @return verifica se foi excluido com sucesso
     */
    public boolean excluirContaPagarDAO(int pCodigo) {
        try {
            this.conectar();
            return this.executarUpdateDeleteSQL(
                    "DELETE FROM contas_pagar "
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

    public boolean pagarContaPagarDAO(ModelContaPagar pModelContaPagar) {
        try {
            this.conectar();
            return this.executarUpdateDeleteSQL(
                    "UPDATE contas_pagar SET "
                    + "pagamento = '" + pModelContaPagar.getPagamento() + "',"
                    + "situacao = '" + pModelContaPagar.isSituacao() + "'"
                    + " WHERE "
                    + "pk_codigo = '" + pModelContaPagar.getCodigo() + "'"
                    + ";"
            );
        } catch (Exception e) {
            return false;
        } finally {
            this.fecharConexao();
        }
    }

    /**
     * recupera uma lista de ContaPagar por data return ArrayList
     *
     * @param pModelContaPagar Lista de Contas a Pagar
     * @param pDataInicial Lista de Contas a Pagar por data de entrada
     * @param dataFinal Lista de Contas a Pagar por data final
     * @return Verifica se executou com sucesso
     */
    public ArrayList<ModelContaPagar> FiltraContaPagarFluxoDados(ModelContaPagar pModelContaPagar, Date pDataInicial, Date dataFinal) {
        ArrayList<ModelContaPagar> listamodelContaPagar = new ArrayList();
        ModelContaPagar modelContaPagar = new ModelContaPagar();
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
                    + " contas_pagar where situacao = 1 and (pagamento BETWEEN '" + pDataInicial + "'AND '" + dataFinal + "')"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelContaPagar = new ModelContaPagar();
                modelContaPagar.setCodigo(this.getResultSet().getInt(1));
                modelContaPagar.setCodigoPessoa(this.getResultSet().getInt(2));
                modelContaPagar.setDescricao(this.getResultSet().getString(3));
                modelContaPagar.setData(this.getResultSet().getString(4));
                modelContaPagar.setVencimento(this.getResultSet().getString(5));
                modelContaPagar.setPagamento(this.getResultSet().getString(6));
                modelContaPagar.setTipoPagamento(this.getResultSet().getString(7));
                modelContaPagar.setObservacao(this.getResultSet().getString(8));
                modelContaPagar.setSituacao(this.getResultSet().getInt(9));
                modelContaPagar.setValor(this.getResultSet().getDouble(10));
                modelContaPagar.setProcessos(this.getResultSet().getInt(11));
                modelContaPagar.setRepasse(this.getResultSet().getBoolean(12));
                modelContaPagar.setBanco(this.getResultSet().getInt(13));
                modelContaPagar.setFuncionarioCheck(this.getResultSet().getBoolean(14));
                listamodelContaPagar.add(modelContaPagar);
            }
        } catch (SQLException e) {
        } finally {
            this.fecharConexao();
        }
        return listamodelContaPagar;
    }

}
