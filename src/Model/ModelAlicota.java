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
public class ModelAlicota {

    private int codigo;
    private Double ali_iss;
    private String ali_data;

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
     * @return the ali_iss
     */
    public Double getAli_iss() {
        return ali_iss;
    }

    /**
     * @param ali_iss the ali_iss to set
     */
    public void setAli_iss(Double ali_iss) {
        this.ali_iss = ali_iss;
    }

    /**
     * @return the ali_data
     */
    public String getAli_data() {
        return ali_data;
    }

    /**
     * @param ali_data the ali_data to set
     */
    public void setAli_data(String ali_data) {
        this.ali_data = ali_data;
    }

    @Override
    public String toString() {
        return "ModelAlicota {" + "::codigo = " + this.getCodigo() + "::ali_iss = " + this.getAli_iss() + "::ali_data = " + this.getAli_data() + "}";
    }

}
