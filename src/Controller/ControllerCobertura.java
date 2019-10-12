/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DAOCobertura;
import Model.ModelCobertura;
import java.util.ArrayList;

/**
 *
 * @author Work
 */
public class ControllerCobertura {

    private final DAOCobertura daoCobertura;

    public ControllerCobertura() {
        this.daoCobertura = new DAOCobertura();
    }

    /**
     * grava Cobertura
     *
     * @param pModelCobertura grava Cobertura
     * @return verifica se foi realizado com sucesso
     */
    public int salvarCoberturaController(ModelCobertura pModelCobertura) {
        return this.daoCobertura.salvarCoberturaDAO(pModelCobertura);
    }

    /**
     * recupera Cobertura
     *
     * @param pCodigo recupera Cobertura
     * @return verifica se foi realizado com sucesso
     */
    public ModelCobertura getCoberturaController(int pCodigo) {
        return this.daoCobertura.getCoberturaDAO(pCodigo);
    }

    /**
     * recupera Cobertura
     *
     * @param pNome recupera Cobertura
     * @return verifica se foi realizado com sucesso
     */
    public ModelCobertura getCoberturaController(String pNome) {
        return this.daoCobertura.getCoberturaDAO(pNome);
    }

    /**
     * recupera uma lista Cobertura
     *
     * @return retorna uma lista Cobertura
     */
    public ArrayList<ModelCobertura> getListaCoberturaController() {
        return this.daoCobertura.getListaCoberturaDAO();
    }

    /**
     * atualiza Cobertura
     *
     * @param pModelCobertura atualiza Cobertura
     * @return verifica se foi realizado com sucesso
     */
    public boolean atualizarCoberturaController(ModelCobertura pModelCobertura) {
        return this.daoCobertura.atualizarCoberturaDAO(pModelCobertura);
    }

    /**
     * exclui Cobertura
     *
     * @param pCodigo exclui Cobertura
     * @return verficia se foi realizado com sucesso
     */
    public boolean excluirCoberturaController(int pCodigo) {
        return this.daoCobertura.excluirCoberturaDAO(pCodigo);
    }
}
