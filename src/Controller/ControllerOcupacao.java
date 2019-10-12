/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DAOOcupacao;
import Model.ModelOcupacao;
import java.util.ArrayList;

/**
 *
 * @author Work
 */
public class ControllerOcupacao {

    private final DAOOcupacao daoOcupacao;

    public ControllerOcupacao() {
        this.daoOcupacao = new DAOOcupacao();
    }

    /**
     * grava Ocupacao
     *
     * @param pModelOcupacao grava Ocupacao
     * @return verifica se foi realizado com sucesso
     */
    public int salvarOcupacaoController(ModelOcupacao pModelOcupacao) {
        return this.daoOcupacao.salvarOcupacaoDAO(pModelOcupacao);
    }

    /**
     * recupera Ocupacao
     *
     * @param pCodigo recupera Ocupacao
     * @return verifica se foi realizado com sucesso
     */
    public ModelOcupacao getOcupacaoControllerCod(int pCodigo) {
        return this.daoOcupacao.getOcupacaoDAO(pCodigo);
    }

    /**
     * recupera Ocupacao
     *
     * @param pNome recupera Ocupacao
     * @return verifica se foi realizado com sucesso
     */
    public ModelOcupacao getOcupacaoControllerNome(String pNome) {
        return this.daoOcupacao.getOcupacaoDAO(pNome);
    }

    /**
     * recupera uma lista Ocupacao
     *
     * @return retorna uma lista Ocupacao
     */
    public ArrayList<ModelOcupacao> getListaOcupacaoController() {
        return this.daoOcupacao.getListaOcupacaoDAO();
    }

    /**
     * atualiza Ocupacao
     *
     * @param pModelOcupacao atualiza Ocupacao
     * @return verifica se foi realizado com sucesso
     */
    public boolean atualizarOcupacaoController(ModelOcupacao pModelOcupacao) {
        return this.daoOcupacao.atualizarOcupacaoDAO(pModelOcupacao);
    }

    /**
     * exclui Ocupacao
     *
     * @param pCodigo exclui Ocupacao
     * @return verficia se foi realizado com sucesso
     */
    public boolean excluirOcupacaoController(int pCodigo) {
        return this.daoOcupacao.excluirOcupacaoDAO(pCodigo);
    }

    public ModelOcupacao getOcupacaoController(int parseInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Object getBancosControllerNome(String toString) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
