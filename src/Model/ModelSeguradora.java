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
public class ModelSeguradora {

    private int codigo;
    private String nome;
    private String endereco;
    private String bairro;
    private int codCidade;
    private int codEstado;
    private String cep;
    private String telefone;
    private String celular;
    private String email;
    private String cnpj;
    private String observacao;
    private String complemento;
    private int numero;
    private Double seg_km;
    private Double seg_honorarios;
    private String seg_negativa;
    private String seg_iss;
    private int fk_cod_contatos;
    private Double seg_hmeio;

    /**
     * Construtor
     */
    public ModelSeguradora() {
    }

    /**
     * @return the seg_hmeio
     */
    public Double getSeg_hmeio() {
        return seg_hmeio;
    }

    /**
     * @param seg_hmeio the seg_hmeio to set
     */
    public void setSeg_hmeio(Double seg_hmeio) {
        this.seg_hmeio = seg_hmeio;
    }

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param pCodigo the codigo to set
     */
    public void setCodigo(int pCodigo) {
        this.codigo = pCodigo;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param pNome the nome to set
     */
    public void setNome(String pNome) {
        this.nome = pNome;
    }

    /**
     * @return the endereco
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * @param pEndereco the endereco to set
     */
    public void setEndereco(String pEndereco) {
        this.endereco = pEndereco;
    }

    /**
     * @return the bairro
     */
    public String getBairro() {
        return bairro;
    }

    /**
     * @param pBairro the bairro to set
     */
    public void setBairro(String pBairro) {
        this.bairro = pBairro;
    }

    /**
     * @return the codCidade
     */
    public int getCodCidade() {
        return codCidade;
    }

    /**
     * @param pCodCidade the codCidade to set
     */
    public void setCodCidade(int pCodCidade) {
        this.codCidade = pCodCidade;
    }

    /**
     * @return the codEstado
     */
    public int getCodEstado() {
        return codEstado;
    }

    /**
     * @param pCodEstado the codEstado to set
     */
    public void setCodEstado(int pCodEstado) {
        this.codEstado = pCodEstado;
    }

    /**
     * @return the cep
     */
    public String getCep() {
        return cep;
    }

    /**
     * @param pCep the cep to set
     */
    public void setCep(String pCep) {
        this.cep = pCep;
    }

    /**
     * @return the telefone
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * @param pTelefone the telefone to set
     */
    public void setTelefone(String pTelefone) {
        this.telefone = pTelefone;
    }

    /**
     * @return the celular
     */
    public String getCelular() {
        return celular;
    }

    /**
     * @param pCelular the celular to set
     */
    public void setCelular(String pCelular) {
        this.celular = pCelular;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param pEmail the email to set
     */
    public void setEmail(String pEmail) {
        this.email = pEmail;
    }

    /**
     * @return the cnpj
     */
    public String getCnpj() {
        return cnpj;
    }

    /**
     * @param pCnpj the cpf to set
     */
    public void setCnpj(String pCnpj) {
        this.cnpj = pCnpj;
    }

    /**
     * @return the observacao
     */
    public String getObservacao() {
        return observacao;
    }

    /**
     * @param pObservacao the observacao to set
     */
    public void setObservacao(String pObservacao) {
        this.observacao = pObservacao;
    }

    /**
     * @return the complemento
     */
    public String getComplemento() {
        return complemento;
    }

    /**
     * @param pComplemento the complemento to set
     */
    public void setComplemento(String pComplemento) {
        this.complemento = pComplemento;
    }

    /**
     * @return the numero
     */
    public int getNumero() {
        return numero;
    }

    /**
     * @param pNumero the numero to set
     */
    public void setNumero(int pNumero) {
        this.numero = pNumero;
    }

    /**
     * @return the seg_km
     */
    public Double getSeg_km() {
        return seg_km;
    }

    /**
     * @param seg_km the seg_km to set
     */
    public void setSeg_km(Double seg_km) {
        this.seg_km = seg_km;
    }

    /**
     * @return the seg_honorarios
     */
    public Double getSeg_honorarios() {
        return seg_honorarios;
    }

    /**
     * @param seg_honorarios the seg_honorarios to set
     */
    public void setSeg_honorarios(Double seg_honorarios) {
        this.seg_honorarios = seg_honorarios;
    }

    /**
     * @return the seg_negativa
     */
    public String getSeg_negativa() {
        return seg_negativa;
    }

    /**
     * @param seg_negativa the seg_negativa to set
     */
    public void setSeg_negativa(String seg_negativa) {
        this.seg_negativa = seg_negativa;
    }

    /**
     * @return the seg_iss
     */
    public String getSeg_iss() {
        return seg_iss;
    }

    /**
     * @param seg_iss the seg_iss to set
     */
    public void setSeg_iss(String seg_iss) {
        this.seg_iss = seg_iss;
    }

    /**
     * @return the fk_cod_contatos
     */
    public int getFk_cod_contatos() {
        return fk_cod_contatos;
    }

    /**
     * @param fk_cod_contatos the fk_cod_contatos to set
     */
    public void setFk_cod_contatos(int fk_cod_contatos) {
        this.fk_cod_contatos = fk_cod_contatos;
    }

    @Override
    public String toString() {
        return "ModelSeguradora {" + "::codigo = " + this.codigo + "::nome = " + this.nome + "::endereco = " + this.endereco + "::bairro = " + this.bairro + "::cidade = " + this.codCidade + "::uf = " + this.codEstado + "::cep = " + this.cep + "::telefone = " + this.telefone + "::celular = " + this.celular + "::email= " + this.email + "::cnpj = " + this.cnpj + "::observacao = " + this.observacao + "::complemento = " + this.complemento + "::numero = " + this.numero + "::seg_km = " + this.seg_km + "::seg_honorarios = " + this.seg_honorarios + "::seg_negativa = " + this.seg_honorarios + "::seg_iss = " + this.seg_iss + "::fk_cod_contatos = " + this.fk_cod_contatos + "::seg_hmeio = " + this.getSeg_hmeio() + "}";
    }

}
