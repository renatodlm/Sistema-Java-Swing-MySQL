package Controller;

import DAO.DAOContaPagar;
import java.sql.Date;
import java.util.ArrayList;
import Model.ModelContaPagar;

/**
 *
 * @author contato@blsoft.com.br
 */
public class ControllerContaPagar {

    private final DAOContaPagar daoContaPagar = new DAOContaPagar();

    /**
     * grava ContaPagar
     *
     * @param pModelContaPagar grava ContaPagar
     * @return verifica
     */
    public int salvarContaPagarController(ModelContaPagar pModelContaPagar) {
        return this.daoContaPagar.salvarContaPagarDAO(pModelContaPagar);
    }

    /**
     * recupera ContaPagar
     *
     * @param pCodigo recuperada por ContaPagar codigo
     * @return Verifica se a conta a pagar foi recuperada por codigo
     */
    public ModelContaPagar getContaPagarController(int pCodigo) {
        return this.daoContaPagar.getContaPagarDAO(pCodigo);
    }

    /**
     * recupera uma lista deContaPagar
     *
     * @return Verifica se a lista de contas a pgar foi retornada com sucesso
     */
    public ArrayList<ModelContaPagar> getListaContaPagarController() {
        return this.daoContaPagar.getListaContaPagarDAO();
    }

    /**
     * recupera uma lista deContaPagar
     *
     * @param pStatus recupera uma lista de todas as Contas por status
     * @return Verifica
     */
    public ArrayList<ModelContaPagar> getListaContaPagarController(int pStatus) {
        return this.daoContaPagar.getListaContaPagarDAO(pStatus);
    }

    /**
     * recupera uma lista de todas as Contas
     *
     * @param pStatus recupera uma lista de todas as Contas por status
     * @return verifica
     */
    public ArrayList<ModelContaPagar> getListaContasController(int pStatus) {
        return this.daoContaPagar.getListaContasDAO(pStatus);
    }

    /**
     * atualiza ContaPagar
     *
     * @param pModelContaPagar atualiza ContaPagar
     * @return verifica se atualiza ContaPagar
     */
    public boolean atualizarContaPagarController(ModelContaPagar pModelContaPagar) {
        return this.daoContaPagar.atualizarContaPagarDAO(pModelContaPagar);
    }

    /**
     * atualiza ContaPagar
     *
     * @param pModelConta atualiza ContaPagar
     * @return verifica se atualiza ContaPagar
     */
    public boolean pagarContaPagarController(ModelContaPagar pModelConta) {
        return this.daoContaPagar.pagarContaPagarDAO(pModelConta);
    }

    /**
     * exclui ContaPagar
     *
     * @param pCodigo exclui ContaPagar
     * @return verifica se exclui ContaPagar
     */
    public boolean excluirContaPagarController(int pCodigo) {
        return this.daoContaPagar.excluirContaPagarDAO(pCodigo);
    }

    public ArrayList<ModelContaPagar> getListaContasController(ModelContaPagar pModelContaPagar) {
        return this.daoContaPagar.getListaContasDAO(pModelContaPagar);
    }

    public ArrayList<ModelContaPagar> FiltraContaPagarFluxoDados(ModelContaPagar pModelContaPagar, Date pDataInicial, Date dataFinal) {
        return this.daoContaPagar.FiltraContaPagarFluxoDados(pModelContaPagar, pDataInicial, dataFinal);

    }

}
