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
public class ModelUsuario {

    /**
     * @return the permissao
     */
    public int getPermissao() {
        return permissao;
    }

    /**
     * @param permissao the permissao to set
     */
    public void setPermissao(int permissao) {
        this.permissao = permissao;
    }
    private int codigo;
    private String nome;
    private String login;
    private String senha;
    private int permissao;

    /**
     * Construtor
     */
    public ModelUsuario() {
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
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "ModelUsuario {" + "::codigo = " + this.codigo + "::nome = " + this.nome + "::login = " + this.login + "::senha = " + this.senha + "::permissao = " + this.permissao + "}";
    }

}
