/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author Work
 */
public class ModelBancos {

    private int codigo;
    private String banco_nome;
    private String banco_num;
    private ArrayList<ModelBancos> listaModelBancos;

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
     * @return the banco_nome
     */
    public String getBanco_nome() {
        return banco_nome;
    }

    /**
     * @param banco_nome the banco_nome to set
     */
    public void setBanco_nome(String banco_nome) {
        this.banco_nome = banco_nome;
    }

    /**
     * @return the banco_num
     */
    public String getBanco_num() {
        return banco_num;
    }

    /**
     * @param banco_num the banco_num to set
     */
    public void setBanco_num(String banco_num) {
        this.banco_num = banco_num;
    }

    @Override
    public String toString() {
        return "ModelBancos {" + "::codigo = " + this.getCodigo() + "::banco_nome = " + this.getBanco_nome() + "::banco_num = " + this.getBanco_num() + "}";
    }

    /**
     * @return the listaModelBancos
     */
    public ArrayList<ModelBancos> getListaModelBancos() {
        return listaModelBancos;
    }

    /**
     * @param listaModelBancos the listaModelServicos to set
     */
    public void setListaModelBancos(ArrayList<ModelBancos> listaModelBancos) {
        this.listaModelBancos = listaModelBancos;
    }

}
