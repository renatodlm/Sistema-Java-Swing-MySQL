/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ModelCidade;
import DAO.DAOCidade;
import java.util.ArrayList;

/**
 *
 * @author studiomicroweb
 */
public class ControllerCidade {

    private final DAOCidade daoCidade = new DAOCidade();

    /**
     * grava Cidade
     *
     * @param pModelCidade return int grava Cidade
     * @return Verifica se salvou a cidade
     */
    public int salvarCidadeController(ModelCidade pModelCidade) {
        return this.daoCidade.salvarCidadeDAO(pModelCidade);
    }

    /**
     * recupera Cidade
     *
     * @param pCodigo recupera a cidade por codigo
     * @return verifica se recuperou a cidade por codigo
     */
    public ModelCidade getCidadeController(int pCodigo) {
        return this.daoCidade.getCidadeDAO(pCodigo);
    }

    /**
     * recupera Cidade
     *
     * @param pNome Retorna cidade por nome
     * @return verifica se realizou com sucesso
     */
    public ModelCidade getCidadeController(String pNome) {
        return this.daoCidade.getCidadeDAO(pNome);
    }

    /**
     * recupera uma lista deCidade
     *
     * @return Verifica se realizou com sucesso
     */
    public ArrayList<ModelCidade> getListaCidadeController() {
        return this.daoCidade.getListaCidadeDAO();
    }

    /**
     * recupera uma lista de Cidade
     *
     * @param pCodigoEstado recupera uma lista de Cidade por codigo
     * @return Verifica se foi recuperado com sucesso
     */
    public ArrayList<ModelCidade> getListaCidadePorEstadoController(int pCodigoEstado) {
        return this.daoCidade.getListaCidadePorEstadoDAO(pCodigoEstado);
    }

    /**
     * atualiza Cidade
     *
     * @param pModelCidade return boolean
     * @return verifica se atualiza Cidade
     */
    public boolean atualizarCidadeController(ModelCidade pModelCidade) {
        return this.daoCidade.atualizarCidadeDAO(pModelCidade);
    }

    /**
     * exclui Cidade
     *
     * @param pCodigo exclui Cidade
     * @return Verifica se excluiu acidade
     */
    public boolean excluirCidadeController(int pCodigo) {
        return this.daoCidade.excluirCidadeDAO(pCodigo);
    }

}
