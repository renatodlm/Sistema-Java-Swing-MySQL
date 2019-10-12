/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DAOContatosSeguradora;
import Model.ModelContatosSeguradoraSeg;
import java.util.ArrayList;

/**
 *
 * @author Work
 */
public class ControllerContatosSeguradoras {

    private final DAOContatosSeguradora daoContatosSeguradora = new DAOContatosSeguradora();

    /**
     * recupera uma lista ContatosSeguradora
     *
     * @return Verifica se retornou a lista de contato com base nas seguradoras
     */
    public ArrayList<ModelContatosSeguradoraSeg> getListaContatosSeguradoraController() {
        return this.daoContatosSeguradora.getListaModelContatosSeguradoraDAO();
    }

}
