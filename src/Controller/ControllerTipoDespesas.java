/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DAOTipoDespesas;
import Model.ModelTipoDespesas;
import java.util.ArrayList;

/**
 *
 * @author Work
 */
public class ControllerTipoDespesas {

    private final DAOTipoDespesas daoTipoDespesas;

    public ControllerTipoDespesas() {
        this.daoTipoDespesas = new DAOTipoDespesas();
    }

    /**
     * grava TipoDespesas
     *
     * @param pModelTipoDespesas grava TipoDespesas
     * @return verifica se foi realizado com sucesso
     */
    public int salvarTipoDespesasController(ModelTipoDespesas pModelTipoDespesas) {
        return this.daoTipoDespesas.salvarTipoDespesasDAO(pModelTipoDespesas);
    }

    /**
     * recupera TipoDespesas
     *
     * @param pCodigo recupera TipoDespesas
     * @return verifica se foi realizado com sucesso
     */
    public ModelTipoDespesas getTipoDespesasController(int pCodigo) {
        return this.daoTipoDespesas.getTipoDespesasDAO(pCodigo);
    }

    /**
     * recupera TipoDespesas
     *
     * @param pNome recupera TipoDespesas
     * @return verifica se foi realizado com sucesso
     */
    public ModelTipoDespesas getTipoDespesasController(String pNome) {
        return this.daoTipoDespesas.getTipoDespesasDAO(pNome);
    }

    /**
     * recupera uma lista TipoDespesas
     *
     * @return retorna uma lista TipoDespesas
     */
    public ArrayList<ModelTipoDespesas> getListaTipoDespesasController() {
        return this.daoTipoDespesas.getListaTipoDespesasDAO();
    }

    /**
     * atualiza TipoDespesas
     *
     * @param pModelTipoDespesas atualiza TipoDespesas
     * @return verifica se foi realizado com sucesso
     */
    public boolean atualizarTipoDespesasController(ModelTipoDespesas pModelTipoDespesas) {
        return this.daoTipoDespesas.atualizarTipoDespesasDAO(pModelTipoDespesas);
    }

    /**
     * exclui TipoDespesas
     *
     * @param pCodigo exclui TipoDespesas
     * @return verficia se foi realizado com sucesso
     */
    public boolean excluirTipoDespesasController(int pCodigo) {
        return this.daoTipoDespesas.excluirTipoDespesasDAO(pCodigo);
    }

}
