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
public class ModelSeguradoraCidadeEstado {

    private ModelCidade modelCidade;
    private ModelEstado modelEstado;
    private ModelSeguradora modelSeguradora;

    /**
     * Construtor
     */
    public ModelSeguradoraCidadeEstado() {
    }

    /**
     * seta o valor de modelCidade
     *
     * @param pModelCidade seta os parametros de modelCidade
     */
    public void setModelCidade(ModelCidade pModelCidade) {
        this.modelCidade = pModelCidade;
    }

    /**
     * return modelCidade
     *
     * @return Retorna o valor de modelCidade parametros
     */
    public ModelCidade getModelCidade() {
        return this.modelCidade;
    }

    /**
     * seta o valor de modelEstado
     *
     * @param pModelEstado seta o valor de modelEstado
     */
    public void setModelEstado(ModelEstado pModelEstado) {
        this.modelEstado = pModelEstado;
    }

    /**
     * return modelEstado
     *
     * @return Verifica o retorno Modelo do que são os parametros de relação com
     * banco e variaveis
     */
    public ModelEstado getModelEstado() {
        return this.modelEstado;
    }

    @Override
    public String toString() {
        return "ModelCidadeEstado {" + "::modelCidade = " + this.modelCidade + "::modelEstado = " + this.modelEstado + "}";
    }

    /**
     * @return the modelSeguradora
     */
    public ModelSeguradora getModelSeguradora() {
        return modelSeguradora;
    }

    /**
     * @param modelSeguradora the modelSeguradora to set
     */
    public void setModelSeguradora(ModelSeguradora modelSeguradora) {
        this.modelSeguradora = modelSeguradora;
    }

}
