/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DAOServicos;
import Model.ModelServicos;
import java.util.ArrayList;

/**
 *
 * @author studiomicroweb
 */
public class ControllerServicos {

    private final DAOServicos daoServicos;

    public ControllerServicos() {
        this.daoServicos = new DAOServicos();
    }

    /**
     * grava Servicos
     *
     * @param pModelServicos grava Servicos
     * @return verifica se foi realizado com sucesso
     */
    public int salvarServicosController(ModelServicos pModelServicos) {
        return this.daoServicos.salvarServicosDAO(pModelServicos);
    }

    /**
     * recupera Servicos
     *
     * @param pCodigo recupera Servicos
     * @return verifica se foi realizado com sucesso
     */
    public ModelServicos getServicosController(int pCodigo) {
        return this.daoServicos.getServicosDAO(pCodigo);
    }

    /**
     * recupera Servicos
     *
     * @param pNome recupera Servicos
     * @return verifica se foi realizado com sucesso
     */
    public ModelServicos getServicosController(String pNome) {
        return this.daoServicos.getServicosDAO(pNome);
    }

    /**
     * recupera uma lista Servicos
     *
     * @return retorna uma lista Servicos
     */
    public ArrayList<ModelServicos> getListaServicosController() {
        return this.daoServicos.getListaServicosDAO();
    }

    /**
     * atualiza Servicos
     *
     * @param pModelServicos atualiza Servicos
     * @return verifica se foi realizado com sucesso
     */
    public boolean atualizarServicosController(ModelServicos pModelServicos) {
        return this.daoServicos.atualizarServicosDAO(pModelServicos);
    }

    /**
     * exclui Servicos
     *
     * @param pCodigo exclui Servicos
     * @return verficia se foi realizado com sucesso
     */
    public boolean excluirServicosController(int pCodigo) {
        return this.daoServicos.excluirServicosDAO(pCodigo);
    }

}
