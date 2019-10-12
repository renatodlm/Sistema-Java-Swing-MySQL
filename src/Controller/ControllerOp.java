/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ModelOp;
import DAO.DAOOp;
import java.sql.Date;
/*import java.util.Date;*/
import java.util.ArrayList;

/**
 *
 * @author xiculim
 */
public class ControllerOp {

    private final DAOOp daoOp = new DAOOp();

    /**
     * grava op
     *
     * @param pModelOp grava
     * @return grava op
     */
    public int salvarOpController(ModelOp pModelOp) {
        return this.daoOp.salvarOpDAO(pModelOp);
    }

    /**
     * recupera Op
     *
     * @param pCodigo return ModelOp
     * @return recupera op
     */
    public ModelOp getOpController(int pCodigo) {
        return this.daoOp.getOpDAO(pCodigo);
    }

    /**
     * recupera uma lista Op
     *
     * @return recuperalista
     */
    public ArrayList<ModelOp> getListaOpController() {
        return this.daoOp.getListaPedidosDAO();
    }

    public ArrayList<ModelOp> getListaCompletaController() {
        return this.daoOp.getListaCompletaDAO();
    }
    /* recupera uma lista Op
     * @param pCodigo
     * return ArrayList
     */

    public ArrayList<ModelOp> getListaOpController(int pCodigo) {
        return this.daoOp.getListaOpDAO(pCodigo);
    }

    public ArrayList<ModelOp> getPorAgenteController(int pCodigo) {
        return this.daoOp.getPorAgenteDAO(pCodigo);
    }

    public ArrayList<ModelOp> getPorAgenteApenasEMAndamentoController(int pCodigo) {
        return this.daoOp.getPorAgenteApenasEMAndamentoDAO(pCodigo);
    }

    public ArrayList<ModelOp> FiltraOpDecrescentePorDataController() {
        return this.daoOp.FiltraOpDecrescentePorData();
    }

    public ArrayList<ModelOp> FiltraOpDecrescenteController() {
        return this.daoOp.FiltraOpDecrescente();
    }

    public ArrayList<ModelOp> FiltraOpDecrescenteConsultasController() {
        return this.daoOp.FiltraOpDecrescenteConsultas();
    }

    /**
     * atualiza op
     *
     * @param pModelOp return boolean
     * @return atualiza op
     */
    public boolean atualizarOpController(ModelOp pModelOp) {
        return this.daoOp.atualizarOpDAO(pModelOp);
    }

    /**
     * exclui op
     *
     * @param pCodigo return boolean
     * @return exclui op
     */
    public boolean excluirOpController(int pCodigo) {
        return this.daoOp.excluirOpDAO(pCodigo);
    }

    public boolean salvarOpServicosController(ModelOp modelOp) {
        return this.daoOp.salvarServicosOpDAO(modelOp);
    }

    public ArrayList<ModelOp> ListaProcessosPorDatasController(Date pDataInicial, Date dataFinal) {
        return this.daoOp.ListaProcessosPorDatas(pDataInicial, dataFinal);
    }

    public boolean pagarReceberProcessoController(ModelOp pMdelOp) {
        return this.daoOp.pagarReceberProcessoDAO(pMdelOp);
    }

    public boolean designarAgenteOpController(ModelOp pModelOp) {
        return this.daoOp.designarAgenteOpDAO(pModelOp);
    }

    public boolean finalizarProcessoOpController(ModelOp pModelOp) {
        return this.daoOp.finalizarProcessoOpDAO(pModelOp);
    }

    /**
     * Excluir os produtos de uma venda
     *
     * @param pCodigo exluir por codigo
     * @return excluir
     */
    public boolean excluirServicoOpController(int pCodigo) {
        return this.daoOp.excluirServicosOpDAO(pCodigo);
    }

    public ArrayList<ModelOp> getListaOpController(ModelOp pMdelOp) {
        return this.daoOp.getListaPedidosDAO(pMdelOp);
    }

    public ArrayList<ModelOp> getProcessosRecebidosController(int pStatus) {
        return this.daoOp.getProcessosRecebidosDAO(pStatus);
    }

    public ArrayList<ModelOp> getProcessosRecebidosAguardandoPgtoController(int pStatus, int pStatus2) {
        return this.daoOp.getProcessosRecebidosAguardandoPgtoDAO(pStatus, pStatus2);
    }

    public ArrayList<ModelOp> getOpFinalizadoENegController() {
        return this.daoOp.getOpFinalizadoENegDAO();
    }

    public ArrayList<ModelOp> getOpAguardandoPGTOController() {
        return this.daoOp.getOpAguardandoPGTOAO();
    }

    /**
     * desaprovar op
     *
     * @param pCodigo return boolean
     * @return desaprova op
     */
    public boolean desaprovarOpController(int pCodigo) {
        return this.daoOp.desaprovarPedidoDAO(pCodigo);
    }

    public ArrayList<ModelOp> getListaOpControllerBusca(String seleciona, String conteudo, int situacao) {
        return this.daoOp.getListaOpPesquisa(seleciona, conteudo, situacao);
    }

    public ArrayList<ModelOp> getListaOpControllerAbertos(String seleciona, String conteudo) {
        return this.daoOp.getListaOpPesquisaAbertos(seleciona, conteudo);
    }

    public ArrayList<ModelOp> getListaOpPesquisaConsultasController(String tipo, String contendo, int situacao) {
        return this.daoOp.getListaOpPesquisaConsultas(tipo, contendo, situacao);
    }

    public ArrayList<ModelOp> getListaOpPesquisaConsultasAgenteEspecificoController(String tipo, String contendo, int situacao, int situacao2, int AgenteSessao) {
        return this.daoOp.getListaOpPesquisaConsultasAgenteEspecifico(tipo, contendo, situacao, situacao2, AgenteSessao);
    }

}
