/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DAOBancos;
import Model.ModelBancos;
import java.util.ArrayList;

/**
 *
 * @author Work
 */
public class ControllerBancos {

    private final DAOBancos daoBancos;

    public ControllerBancos() {
        this.daoBancos = new DAOBancos();
    }

    /**
     * grava Bancos
     *
     * @param pModelBancos grava Bancos
     * @return verifica se foi realizado com sucesso
     */
    public int salvarBancosController(ModelBancos pModelBancos) {
        return this.daoBancos.salvarBancosDAO(pModelBancos);
    }

    /**
     * recupera Bancos
     *
     * @param pCodigo recupera Bancos
     * @return verifica se foi realizado com sucesso
     */
    public ModelBancos getBancosControllerCod(int pCodigo) {
        return this.daoBancos.getBancosDAO(pCodigo);
    }

    /**
     * recupera Bancos
     *
     * @param pNome recupera Bancos
     * @return verifica se foi realizado com sucesso
     */
    public ModelBancos getBancosControllerNome(String pNome) {
        return this.daoBancos.getBancosDAO(pNome);
    }

    /**
     * recupera uma lista Bancos
     *
     * @return retorna uma lista Bancos
     */
    public ArrayList<ModelBancos> getListaBancosController() {
        return this.daoBancos.getListaBancosDAO();
    }

    /**
     * atualiza Bancos
     *
     * @param pModelBancos atualiza Bancos
     * @return verifica se foi realizado com sucesso
     */
    public boolean atualizarBancosController(ModelBancos pModelBancos) {
        return this.daoBancos.atualizarBancosDAO(pModelBancos);
    }

    /**
     * exclui Bancos
     *
     * @param pCodigo exclui Bancos
     * @return verficia se foi realizado com sucesso
     */
    public boolean excluirBancosController(int pCodigo) {
        return this.daoBancos.excluirBancosDAO(pCodigo);
    }
    /*
     public boolean gerarRelatorioBancos() {
     return this.daoRelatorios.gerarRelatorioBancos();
     }*/

    public ModelBancos getBancosController(int parseInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
