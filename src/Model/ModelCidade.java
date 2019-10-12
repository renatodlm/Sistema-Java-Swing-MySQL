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
public class ModelCidade {

    private int codigo;
    private String nome;
    private int fk_cod_estado;

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
     * @return the fk_cod_estado
     */
    public int getFk_cod_estado() {
        return fk_cod_estado;
    }

    /**
     * @param fk_cod_estado the fk_cod_estado to set
     */
    public void setFk_cod_estado(int fk_cod_estado) {
        this.fk_cod_estado = fk_cod_estado;
    }

    @Override
    public String toString() {
        return "ModelCidade {" + "::codigo = " + this.codigo + "::nome = " + this.nome + "::fk_cod_estado = " + this.fk_cod_estado + "}";
    }

}
