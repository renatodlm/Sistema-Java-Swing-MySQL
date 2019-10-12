/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DAOAlicota;
import Model.ModelAlicota;
import java.util.ArrayList;

/**
 *
 * @author Work
 */
public class ControllerAlicota {

    private final DAOAlicota daoAlicota;

    public ControllerAlicota() {
        this.daoAlicota = new DAOAlicota();
    }

    /**
     * grava Alicota
     *
     * @param pModelAlicota grava Alicota
     * @return verifica se foi realizado com sucesso
     */
    public int salvarAlicotaController(ModelAlicota pModelAlicota) {
        return this.daoAlicota.salvarAlicotaDAO(pModelAlicota);
    }

    /**
     * recupera Alicota
     *
     * @param pCodigo recupera Alicota
     * @return verifica se foi realizado com sucesso
     */
    public ModelAlicota getAlicotaController(int pCodigo) {
        return this.daoAlicota.getAlicotaDAO(pCodigo);
    }

    /**
     * recupera Alicota
     *
     * @param pIss recupera Alicota
     * @return verifica se foi realizado com sucesso
     */
    public ModelAlicota getAlicotaController(String pIss) {
        return this.daoAlicota.getAlicotaDAO(pIss);
    }

    /**
     * recupera uma lista Alicota
     *
     * @return retorna uma lista Alicota
     */
    public ArrayList<ModelAlicota> getListaAlicotaController() {
        return this.daoAlicota.getListaAlicotaDAO();
    }

    /**
     * atualiza Alicota
     *
     * @param pModelAlicota atualiza Alicota
     * @return verifica se foi realizado com sucesso
     */
    public boolean atualizarAlicotaController(ModelAlicota pModelAlicota) {
        return this.daoAlicota.atualizarAlicotaDAO(pModelAlicota);
    }

    /**
     * exclui Alicota
     *
     * @param pCodigo exclui Alicota
     * @return verficia se foi realizado com sucesso
     */
    public boolean excluirAlicotaController(int pCodigo) {
        return this.daoAlicota.excluirAlicotaDAO(pCodigo);
    }

}
