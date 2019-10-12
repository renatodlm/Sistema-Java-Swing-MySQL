/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DAOSeguradora;
import Model.ModelSeguradora;
import java.util.ArrayList;

/**
 *
 * @author studiomicroweb
 */
public class ControllerSeguradora {

    private final DAOSeguradora daoSeguradora = new DAOSeguradora();

    /**
     * grava Seguradora
     *
     * @param pModelSeguradora return int
     * @return verifica
     */
    public int salvarSeguradoraController(ModelSeguradora pModelSeguradora) {
        return this.daoSeguradora.salvarSeguradorasDAO(pModelSeguradora);
    }

    /**
     * recupera Seguradora
     *
     * @param pCodigo return ModelSeguradora
     * @return verifica se recupera Seguradora com sucesso
     */
    public ModelSeguradora getSeguradoraController(int pCodigo) {
        return this.daoSeguradora.getSeguradoraDAO(pCodigo);
    }

    /**
     * recupera Seguradora
     *
     * @param pNome recupera Seguradora por nome
     * @return verifica se foi realizado com sucesso
     */
    public ModelSeguradora getSeguradoraController(String pNome) {
        return this.daoSeguradora.getSeguradoraDAO(pNome);
    }

    /**
     * recupera uma lista de Seguradora
     *
     * @return recupera uma lista de Seguradora
     */
    public ArrayList<ModelSeguradora> getListaSeguradoraController() {
        return this.daoSeguradora.getListaSeguradoraDAO();
    }

    /**
     * atualiza Seguradora
     *
     * @param ModelSeguradora atualiza Seguradora
     * @return verifica se atualiza Seguradora com sucesso
     */
    public boolean atualizarSeguradoraController(ModelSeguradora ModelSeguradora) {
        return this.daoSeguradora.atualizarSeguradoraDAO(ModelSeguradora);
    }

    /**
     * exclui Seguradora
     *
     * @param Codigo exclui Seguradora
     * @return verifica se excluiu com sucesso
     */
    public boolean excluirSeguradoraController(int Codigo) {
        return this.daoSeguradora.excluirSeguradorasDAO(Codigo);
    }

}
