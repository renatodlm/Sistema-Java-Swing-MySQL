/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author studiomicroweb
 */
public class ModelFuncionarioCidadeEstado {

    private ModelCidade modelCidade;
    private ModelEstado modelEstado;
    private ModelFuncionario modelFuncionario;

    /**
     * Construtor
     */
    public ModelFuncionarioCidadeEstado() {
    }

    /**
     * seta o valor de modelFuncionario
     *
     * @param pModelCidade seta o parametros de modelFuncionario
     */
    public void setModelCidade(ModelCidade pModelCidade) {
        this.modelCidade = pModelCidade;
    }

    /**
     * return modelCidade
     *
     * @return retorna parametro da lista de cidades
     */
    public ModelCidade getModelCidade() {
        return this.modelCidade;
    }

    /**
     * seta o valor de modelEstado
     *
     * @param pModelEstado seta os parametro de estados
     */
    public void setModelEstado(ModelEstado pModelEstado) {
        this.modelEstado = pModelEstado;
    }

    /**
     * return modelEstado
     *
     * @return Retorna os estados com seu parametros
     */
    public ModelEstado getModelEstado() {
        return this.modelEstado;
    }

    @Override
    public String toString() {
        return "ModelCidadeEstado {" + "::modelCidade = " + this.modelCidade + "::modelEstado = " + this.modelEstado + "}";
    }

    /**
     * @return the modelFuncionario
     */
    public ModelFuncionario getModelFuncionario() {
        return modelFuncionario;
    }

    /**
     * @param modelFuncionario the modelFuncionario to set
     */
    public void setModelFuncionario(ModelFuncionario modelFuncionario) {
        this.modelFuncionario = modelFuncionario;
    }

}
