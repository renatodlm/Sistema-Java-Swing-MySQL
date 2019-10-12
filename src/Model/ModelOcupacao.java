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
public class ModelOcupacao {

    private int codigo;
    private String ocup_descricao;
    private int cod_permissao;
    private ArrayList<ModelOcupacao> listaModelOcupacao;

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
     * @return the ocup_descricao
     */
    public String getOcup_descricao() {
        return ocup_descricao;
    }

    /**
     * @param ocup_descricao the ocup_descricao to set
     */
    public void setOcup_descricao(String ocup_descricao) {
        this.ocup_descricao = ocup_descricao;
    }

    /**
     * @return the cod_permissao
     */
    public int getCod_permissao() {
        return cod_permissao;
    }

    /**
     * @param cod_permissao the cod_permissao to set
     */
    public void setCod_permissao(int cod_permissao) {
        this.cod_permissao = cod_permissao;
    }

    @Override
    public String toString() {
        return "ModelOcupacao {" + "::codigo = " + this.getCodigo() + "::ocup_descricao = " + this.getOcup_descricao() + "::ocup_departamento = " + this.getCod_permissao() + "}";
    }

    /**
     * @return the listaModelOcupacao
     */
    public ArrayList<ModelOcupacao> getListaModelOcupacao() {
        return listaModelOcupacao;
    }

    /**
     * @param listaModelOcupacao the listaModelServicos to set
     */
    public void setListaModelOcupacao(ArrayList<ModelOcupacao> listaModelOcupacao) {
        this.listaModelOcupacao = listaModelOcupacao;
    }

}
