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
public class ModelContatosSeguradoraSeg {

    private ModelContatosSeguradora modelContatosSeguradroas;
    private ModelSeguradora modelSeguradorasContatos;
    private ArrayList<ModelContatosSeguradoraSeg> listaModelContatosSeguradora;

    /**
     * Construtor
     */
    public ModelContatosSeguradoraSeg() {
    }

    /**
     * seta o valor de ModelContatosSeguradora
     *
     * @param pModelContatosSeguradora seta a cidade
     */
    public void setContatosSeguradora(ModelContatosSeguradora pModelContatosSeguradora) {
        this.modelContatosSeguradroas = pModelContatosSeguradora;
    }

    /**
     * return ContatosSeguradora
     *
     * @return retorna a ContatosSeguradora
     */
    public ModelContatosSeguradora getContatosSeguradora() {
        return this.modelContatosSeguradroas;
    }

    /**
     * seta o valor de modelEstado
     *
     * @param pModelSeguradora seta o estado
     */
    public void setModelSeguradora(ModelSeguradora pModelSeguradora) {
        this.modelSeguradorasContatos = pModelSeguradora;
    }

    /**
     * return modelSeguradorasContatos
     *
     * @return Retorna o modelSeguradorasContatos
     */
    public ModelSeguradora getModelSeguradora() {
        return this.modelSeguradorasContatos;
    }

    @Override
    public String toString() {
        return "ModelContatosSeguradoraSeg {" + "::cod_seguradora = " + this.modelContatosSeguradroas + "::fk_cod_contatos = " + this.modelSeguradorasContatos + "}";
    }

    /**
     * @return the listaModelContatosSeguradora
     */
    public ArrayList<ModelContatosSeguradoraSeg> getListaModelContatosSeguradora() {
        return listaModelContatosSeguradora;
    }

    /**
     * @param listaModelContatosSeguradora the listaModelContatosSeguradora to
     * set
     */
    public void setListaModelContatosSeguradora(ArrayList<ModelContatosSeguradoraSeg> listaModelContatosSeguradora) {
        this.listaModelContatosSeguradora = listaModelContatosSeguradora;
    }

}
