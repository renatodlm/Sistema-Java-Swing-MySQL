/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DAOEstado;
import Model.ModelEstado;
import java.util.ArrayList;

/**
 *
 * @author studiomicroweb
 */
public class ControllerEstado {

    private final DAOEstado daoEstado = new DAOEstado();

    /**
     * grava Estado
     *
     * @param pModelEstado grava Estado
     * @return verifica se grava Estado
     */
    public int salvarEstadoController(ModelEstado pModelEstado) {
        return this.daoEstado.salvarEstadoDAO(pModelEstado);
    }

    /**
     * recupera Estado
     *
     * @param Codigo recupera Estado por codigo
     * @return verifica se recupera Estado por codigo com sucesso
     */
    public ModelEstado getEstadoController(int Codigo) {
        return this.daoEstado.getEstadoDAO(Codigo);
    }

    /**
     * recupera Estado
     *
     * @param Nome recupera Estado por nome
     * @return verifica se recupera Estado por nome com sucesso
     */
    public ModelEstado getEstadoController(String Nome) {
        return this.daoEstado.getEstadoDAO(Nome);
    }

    /**
     * recupera Estado
     *
     * @param UF recupera Estado por sigla
     * @return verifica se recupera Estado por sigla com sucesso
     */
    public ModelEstado getEstadoUFController(String UF) {
        return this.daoEstado.getEstadoUFDAO(UF);
    }

    /**
     * recupera uma lista de Estado
     *
     * @return verifica se recupera uma lista de Estado com sucesso
     */
    public ArrayList<ModelEstado> getListaEstadoController() {
        return this.daoEstado.getListaEstadoDAO();
    }

    /**
     * atualiza Estado
     *
     * @param ModelEstado atualiza Estado
     * @return verifica se atualiza Estado com sucesso
     */
    public boolean atualizarEstadoController(ModelEstado ModelEstado) {
        return this.daoEstado.atualizarEstadoDAO(ModelEstado);
    }

    /**
     * exclui Estado
     *
     * @param Codigo return boolean
     * @return verifica se exclui Estado
     */
    public boolean excluirEstadoController(int Codigo) {
        return this.daoEstado.excluirEstadoDAO(Codigo);
    }

}
