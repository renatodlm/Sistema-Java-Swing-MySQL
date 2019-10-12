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
public class ModelServicos {

    private int codigo;
    private String nome;
    private ArrayList<ModelServicos> listaModelServicos;

    /**
     * Construtor
     */
    public ModelServicos() {
    }

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the valor
     */
    @Override
    public String toString() {
        return "ModelServicos::codigo = " + this.codigo + "::nome = " + this.nome + "::valor = " + "}";
    }

    /**
     * @return the listaModelServicos
     */
    public ArrayList<ModelServicos> getListaModelServicos() {
        return listaModelServicos;
    }

    /**
     * @param listaModelServicos the listaModelServicos to set
     */
    public void setListaModelServicos(ArrayList<ModelServicos> listaModelServicos) {
        this.listaModelServicos = listaModelServicos;
    }

}
