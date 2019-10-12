/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Work
 */
public class ModelCobertura {

    private int codigo;
    private String cober_nome;
    private Double cober_valor;

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
     * @return the cober_nome
     */
    public String getCober_nome() {
        return cober_nome;
    }

    /**
     * @param cober_nome the cober_nome to set
     */
    public void setCober_nome(String cober_nome) {
        this.cober_nome = cober_nome;
    }

    /**
     * @return the cober_valor
     */
    public Double getCober_valor() {
        return cober_valor;
    }

    /**
     * @param cober_valor the cober_valor to set
     */
    public void setCober_valor(Double cober_valor) {
        this.cober_valor = cober_valor;
    }

    @Override
    public String toString() {
        return "ModelCobertura {" + "::codigo = " + this.getCodigo() + "::cober_nome = " + this.getCober_nome() + "::cober_valor = " + this.getCober_valor() + "}";
    }

}
