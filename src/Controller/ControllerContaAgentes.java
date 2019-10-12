package Controller;

import DAO.DAOContaAgentes;
import java.sql.Date;
import java.util.ArrayList;
import Model.ModelContaAgentes;

/**
 *
 * @author contato@blsoft.com.br
 */
public class ControllerContaAgentes {

    private final DAOContaAgentes daoContaAgentes = new DAOContaAgentes();

    /**
     * grava ContaAgentes
     *
     * @param pModelContaAgentes grava ContaAgentes
     * @return verifica
     */
    public int salvarContaAgentesController(ModelContaAgentes pModelContaAgentes) {
        return this.daoContaAgentes.salvarContaAgentesDAO(pModelContaAgentes);
    }

    public boolean salvarAdiantamentosController(ModelContaAgentes ModelContaAgentes) {
        return this.daoContaAgentes.salvarAdiantamentosDAO(ModelContaAgentes);
    }

    /**
     * recupera ContaAgentes
     *
     * @param pCodigo recuperada por ContaAgentes codigo
     * @return Verifica se a conta a agentes foi recuperada por codigo
     */
    public ModelContaAgentes getContaAgentesController(int pCodigo) {
        return this.daoContaAgentes.getContaAgentesDAO(pCodigo);
    }

    public ModelContaAgentes getDescricaoPorDespesaController(int pCodigo) {
        return this.daoContaAgentes.getDescricaoPorDespesaDAO(pCodigo);
    }

    public ModelContaAgentes getContaAgentesControllerPorProcesso(int pProcesso) {
        return this.daoContaAgentes.getContaAgentesPorProcessoDAO(pProcesso);
    }

    /**
     * recupera uma lista deContaAgentes
     *
     * @return Verifica se a lista de contas a pgar foi retornada com sucesso
     */
    public ArrayList<ModelContaAgentes> getListaContaAgentesController() {
        return this.daoContaAgentes.getListaContaAgentesDAO();
    }

    /**
     * recupera uma lista deContaAgentes
     *
     * @param pStatus recupera uma lista de todas as Contas por status
     * @return Verifica
     */
    public ArrayList<ModelContaAgentes> getListaContaAgentesController(int pStatus) {
        return this.daoContaAgentes.getListaContaAgentesDAO(pStatus);
    }

    /**
     * recupera uma lista de todas as Contas
     *
     * @param pStatus recupera uma lista de todas as Contas por status
     * @return verifica
     */
    public ArrayList<ModelContaAgentes> getListaContasController(int pStatus) {
        return this.daoContaAgentes.getListaContasDAO(pStatus);
    }

    /**
     * atualiza ContaAgentes
     *
     * @param pModelContaAgentes atualiza ContaAgentes
     * @return verifica se atualiza ContaAgentes
     */
    public boolean atualizarContaAgentesController(ModelContaAgentes pModelContaAgentes) {
        return this.daoContaAgentes.atualizarContaAgentesDAO(pModelContaAgentes);
    }

    /**
     * atualiza ContaAgentes
     *
     * @param pModelConta atualiza ContaAgentes
     * @return verifica se atualiza ContaAgentes
     */
    public boolean pagarContaAgentesController(ModelContaAgentes pModelConta) {
        return this.daoContaAgentes.agentesContaAgentesDAO(pModelConta);
    }

    /**
     * exclui ContaAgentes
     *
     * @param pCodigo exclui ContaAgentes
     * @return verifica se exclui ContaAgentes
     */
    public boolean excluirContaAgentesController(int pCodigo) {
        return this.daoContaAgentes.excluirContaAgentesDAO(pCodigo);
    }

    public ArrayList<ModelContaAgentes> getListaContasController(ModelContaAgentes pModelContaAgentes) {
        return this.daoContaAgentes.getListaContasDAO(pModelContaAgentes);
    }

    public ArrayList<ModelContaAgentes> FiltraContaAgentesPorSituacaoDataController(int AgenteCod, int pSituacao, Date pDataInicial, Date dataFinal) {
        return this.daoContaAgentes.FiltraContaAgentesFluxoDados(AgenteCod, pSituacao, pDataInicial, dataFinal);

    }

    public ArrayList<ModelContaAgentes> FiltraContaTodosAgentesFluxoDadosController(int pSituacao, Date pDataInicial, Date dataFinal) {
        return this.daoContaAgentes.FiltraContaTodosAgentesFluxoDados(pSituacao, pDataInicial, dataFinal);

    }

    public ArrayList<ModelContaAgentes> FiltraContaAgentesFluxoDadosPorAgenteController(int pAgente, Date pDataInicial, Date dataFinal) {
        return this.daoContaAgentes.FiltraContaAgentesFluxoDadosPorAgenteDAO(pAgente, pDataInicial, dataFinal);

    }

}
