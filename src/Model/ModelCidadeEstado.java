/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author studiomicroweb
 */
public class ModelCidadeEstado {

    private ModelCidade modelCidade;
    private ModelEstado modelEstado;
    private ArrayList<ModelCidadeEstado> listaModelCidadeEstados;

    /**
     * Construtor
     */
    public ModelCidadeEstado() {
    }

    /**
     * seta o valor de modelCidade
     *
     * @param pModelCidade seta a cidade
     */
    public void setModelCidade(ModelCidade pModelCidade) {
        this.modelCidade = pModelCidade;
    }

    /**
     * return modelCidade
     *
     * @return retorna a cidade
     */
    public ModelCidade getModelCidade() {
        return this.modelCidade;
    }

    /**
     * seta o valor de modelEstado
     *
     * @param pModelEstado seta o estado
     */
    public void setModelEstado(ModelEstado pModelEstado) {
        this.modelEstado = pModelEstado;
    }

    /**
     * return modelEstado
     *
     * @return Retorna o estado
     */
    public ModelEstado getModelEstado() {
        return this.modelEstado;
    }

    @Override
    public String toString() {
        return "ModelCidadeEstado {" + "::modelCidade = " + this.modelCidade + "::modelEstado = " + this.modelEstado + "}";
    }

    /**
     * @return the listaModelCidadeEstados
     */
    public ArrayList<ModelCidadeEstado> getListaModelCidadeEstados() {
        return listaModelCidadeEstados;
    }

    /**
     * @param listaModelCidadeEstados the listaModelCidadeEstados to set
     */
    public void setListaModelCidadeEstados(ArrayList<ModelCidadeEstado> listaModelCidadeEstados) {
        this.listaModelCidadeEstados = listaModelCidadeEstados;
    }

}
