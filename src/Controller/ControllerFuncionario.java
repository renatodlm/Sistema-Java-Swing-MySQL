/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ModelFuncionario;
import DAO.DAOFuncionario;
import java.util.ArrayList;

/**
 *
 * @author studiomicroweb
 */
public class ControllerFuncionario {

    private final DAOFuncionario daoFuncionario = new DAOFuncionario();

    /**
     * grava Funcionario
     *
     * @param pModelFuncionario return int
     * @return return
     */
    public int salvarFuncionarioController(ModelFuncionario pModelFuncionario) {
        return this.daoFuncionario.salvarFuncionarioDAO(pModelFuncionario);
    }

    /**
     * recupera Funcionario
     *
     * @param pCodigo return ModelFuncionario
     * @return recuperafuncionario
     */
    public ModelFuncionario getFuncionarioController(int pCodigo) {
        return this.daoFuncionario.getFuncionarioDAO(pCodigo);
    }

    /**
     * recupera Funcionario
     *
     * @param pNome return ModelFuncionario
     * @return return return
     *
     */
    public ModelFuncionario getFuncionarioController(String pNome) {
        return this.daoFuncionario.getFuncionarioDAO(pNome);
    }

    public ArrayList<ModelFuncionario> getTipoFuncProcessosController(int pOcupacao) {
        return this.daoFuncionario.getTipoFuncProcessosDAO(pOcupacao);
    }

    /**
     * recupera uma lista de Funcionario
     *
     * @return return
     */
    public ArrayList<ModelFuncionario> getListaFuncionarioController() {
        return this.daoFuncionario.getListaFuncionarioDAO();
    }

    public ArrayList<ModelFuncionario> getListaSomenteAgentesController() {
        return this.daoFuncionario.getListaSomenteAgentesDAO();
    }

    /**
     * altera Funcionario
     *
     * @param ModelFuncionario return boolean
     * @return return
     */
    public boolean atualizarFuncionarioController(ModelFuncionario ModelFuncionario) {
        return this.daoFuncionario.atualizarFuncionarioDAO(ModelFuncionario);
    }

    public boolean excluirFuncionarioController(int Codigo) {
        return this.daoFuncionario.excluirFuncionarioDAO(Codigo);
    }
}
