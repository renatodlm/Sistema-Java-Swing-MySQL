package Controller;

import Model.ModelFormaPagamento;
import DAO.DAOFormaPagamento;
import java.util.ArrayList;

/**
 *
 * @author BLSoft Desenvolvimento de Sistemas
 */
public class ControllerFormaPagamento {

    private final DAOFormaPagamento daoFormaPagamento = new DAOFormaPagamento();

    /**
     * grava FormaPagamento
     *
     * @param pModelFormaPagamento grava FormaPagamento
     * @return verifica se grava FormaPagamento com sucesso
     */
    public int salvarFormaPagamentoController(ModelFormaPagamento pModelFormaPagamento) {
        return this.daoFormaPagamento.salvarFormaPagamentoDAO(pModelFormaPagamento);
    }

    /**
     * recupera FormaPagamento
     *
     * @param pCodigo recupera FormaPagamento por codigo
     * @return recupera FormaPagamento por codigo
     */
    public ModelFormaPagamento getFormaPagamentoController(int pCodigo) {
        return this.daoFormaPagamento.getFormaPagamentoDAO(pCodigo);
    }

    /**
     * recupera FormaPagamento
     *
     * @param pCodigo recupera FormaPagamento por codigo
     * @return verifica se recupera FormaPagamento por codigo com sucesso
     */
    public ModelFormaPagamento getFormaPagamentoController(String pCodigo) {
        return this.daoFormaPagamento.getFormaPagamentoDAO(pCodigo);
    }

    /**
     * recupera uma lista deFormaPagamento
     *
     * @return verifica se recupera uma lista de FormaPagamento com sucesso
     */
    public ArrayList<ModelFormaPagamento> getListaFormaPagamentoController() {
        return this.daoFormaPagamento.getListaFormaPagamentoDAO();
    }

    /**
     * atualiza FormaPagamento
     *
     * @param pModelFormaPagamento atualiza FormaPagamento
     * @return verifica se exclui FormaPagamento com sucesso
     */
    public boolean atualizarFormaPagamentoController(ModelFormaPagamento pModelFormaPagamento) {
        return this.daoFormaPagamento.atualizarFormaPagamentoDAO(pModelFormaPagamento);
    }

    /**
     * exclui FormaPagamento
     *
     * @param pCodigo exclui FormaPagamento
     * @return verifica exclui FormaPagamento com sucesso
     */
    public boolean excluirFormaPagamentoController(int pCodigo) {
        return this.daoFormaPagamento.excluirFormaPagamentoDAO(pCodigo);
    }
}
