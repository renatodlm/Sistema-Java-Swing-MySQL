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
public class ModelContatosSeguradora {

    private int codigo;
    private int cod_seguradora;
    private String cttseg_nome;
    private String cttseg_telefone;
    private String cttseg_email;
    private String cttseg_obs;

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
     * @return the cod_seguradora
     */
    public int getCod_seguradora() {
        return cod_seguradora;
    }

    /**
     * @param cod_seguradora the cod_seguradora to set
     */
    public void setCod_seguradora(int cod_seguradora) {
        this.cod_seguradora = cod_seguradora;
    }

    /**
     * @return the cttseg_nome
     */
    public String getCttseg_nome() {
        return cttseg_nome;
    }

    /**
     * @param cttseg_nome the cttseg_nome to set
     */
    public void setCttseg_nome(String cttseg_nome) {
        this.cttseg_nome = cttseg_nome;
    }

    /**
     * @return the cttseg_telefone
     */
    public String getCttseg_telefone() {
        return cttseg_telefone;
    }

    /**
     * @param cttseg_telefone the cttseg_telefone to set
     */
    public void setCttseg_telefone(String cttseg_telefone) {
        this.cttseg_telefone = cttseg_telefone;
    }

    /**
     * @return the cttseg_email
     */
    public String getCttseg_email() {
        return cttseg_email;
    }

    /**
     * @param cttseg_email the cttseg_email to set
     */
    public void setCttseg_email(String cttseg_email) {
        this.cttseg_email = cttseg_email;
    }

    /**
     * @return the cttseg_obs
     */
    public String getCttseg_obs() {
        return cttseg_obs;
    }

    /**
     * @param cttseg_obs the cttseg_obs to set
     */
    public void setCttseg_obs(String cttseg_obs) {
        this.cttseg_obs = cttseg_obs;
    }

    @Override
    public String toString() {
        return "ModelContatosSeguradora {" + "::codigo = " + this.getCodigo() + "::cod_seguradora = " + this.getCod_seguradora() + "::cttseg_nome = " + this.getCttseg_nome() + "::cttseg_telefone = " + this.getCttseg_telefone() + "::cttseg_email = " + this.getCttseg_email() + "::cttseg_obs = " + this.getCttseg_obs() + "}";
    }

}
