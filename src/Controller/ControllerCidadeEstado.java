/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ModelCidadeEstado;
import DAO.DAOCidadeEstado;
import java.util.ArrayList;

/**
 *
 * @author studiomicroweb
 */
public class ControllerCidadeEstado {

    private final DAOCidadeEstado daoCidadeEstado = new DAOCidadeEstado();

    /**
     * recupera uma lista deCidadeEstado
     *
     * @return Verifica se retornou a lista de Cidades com base nos estados
     */
    public ArrayList<ModelCidadeEstado> getListaCidadeEstadoController() {
        return this.daoCidadeEstado.getListaCidadeEstadoDAO();
    }

}
