/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ModelUsuario;
import DAO.DAOUsuario;

/**
 *
 * @author studiomicroweb
 */
public class ControllerUsuario {

    private final DAOUsuario daoUsuario = new DAOUsuario();

    /**
     * recupera Usuario
     *
     * @param pCodigo seta usuario por codigo
     * @return recupera usuario por codigo
     */
    public ModelUsuario getUsuarioController(int pCodigo) {
        return this.daoUsuario.getUsuarioDAO(pCodigo);
    }

    /**
     * recupera Usuario
     *
     * @param pLogin seta usuario por login
     * @return recupera Usuario por login
     */
    public ModelUsuario getUsuarioController(String pLogin) {
        return this.daoUsuario.getUsuarioDAO(pLogin);
    }

    /**
     * recupera Usuario
     *
     * @param pModelUsuario Seta Usuario
     * @return recupera Usuario
     */
    public boolean getUsuarioController(ModelUsuario pModelUsuario) {
        return this.daoUsuario.getUsuarioDAO(pModelUsuario);
    }

}
