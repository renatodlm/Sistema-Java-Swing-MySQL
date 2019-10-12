package Controller;

import DAO.DAOContaReceber;
import java.sql.Date;
import java.util.ArrayList;
import Model.ModelContaReceber;

/**
 *
 * @author contato@blsoft.com.br
 */
public class ControllerContaReceber {

    private final DAOContaReceber daoContaReceber = new DAOContaReceber();

    /**
     * grava ContaReceber
     *
     * @param pModelContaReceber grava ContaReceber
     * @return verifica
     */
    public int salvarContaReceberController(ModelContaReceber pModelContaReceber) {
        return this.daoContaReceber.salvarContaReceberDAO(pModelContaReceber);
    }

    /**
     * recupera ContaReceber
     *
     * @param pCodigo recuperada por ContaReceber codigo
     * @return Verifica se a conta a receber foi recuperada por codigo
     */
    public ModelContaReceber getContaReceberController(int pCodigo) {
        return this.daoContaReceber.getContaReceberDAO(pCodigo);
    }

    /**
     * recupera uma lista deContaReceber
     *
     * @return Verifica se a lista de contas a pgar foi retornada com sucesso
     */
    public ArrayList<ModelContaReceber> getListaContaReceberController() {
        return this.daoContaReceber.getListaContaReceberDAO();
    }

    /**
     * recupera uma lista deContaReceber
     *
     * @param pStatus recupera uma lista de todas as Contas por status
     * @return Verifica
     */
    public ArrayList<ModelContaReceber> getListaContaReceberController(int pStatus) {
        return this.daoContaReceber.getListaContaReceberDAO(pStatus);
    }

    /**
     * recupera uma lista de todas as Contas
     *
     * @param pStatus recupera uma lista de todas as Contas por status
     * @return verifica
     */
    public ArrayList<ModelContaReceber> getListaContasController(int pStatus) {
        return this.daoContaReceber.getListaContasDAO(pStatus);
    }

    /**
     * atualiza ContaReceber
     *
     * @param pModelContaReceber atualiza ContaReceber
     * @return verifica se atualiza ContaReceber
     */
    public boolean atualizarContaReceberController(ModelContaReceber pModelContaReceber) {
        return this.daoContaReceber.atualizarContaReceberDAO(pModelContaReceber);
    }

    /**
     * atualiza ContaReceber
     *
     * @param pModelConta atualiza ContaReceber
     * @return verifica se atualiza ContaReceber
     */
    public boolean receberContaReceberController(ModelContaReceber pModelConta) {
        return this.daoContaReceber.receberContaReceberDAO(pModelConta);
    }

    /**
     * exclui ContaReceber
     *
     * @param pCodigo exclui ContaReceber
     * @return verifica se exclui ContaReceber
     */
    public boolean excluirContaReceberController(int pCodigo) {
        return this.daoContaReceber.excluirContaReceberDAO(pCodigo);
    }

    public ArrayList<ModelContaReceber> getListaContasController(ModelContaReceber pModelContaReceber) {
        return this.daoContaReceber.getListaContasDAO(pModelContaReceber);
    }

    public ArrayList<ModelContaReceber> FiltraContaReceberFluxoDados(ModelContaReceber pModelContaReceber, Date pDataInicial, Date dataFinal) {
        return this.daoContaReceber.FiltraContaReceberFluxoDados(pModelContaReceber, pDataInicial, dataFinal);

    }

}
