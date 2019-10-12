/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DAOSeguradoraCidadeEstado;
import java.util.ArrayList;
import Model.ModelSeguradoraCidadeEstado;

/**
 *
 * @author studiomicroweb
 */
public class ControllerSeguradoraCidadeEstado {

    private final DAOSeguradoraCidadeEstado dAOSeguradoraCidadeEstado = new DAOSeguradoraCidadeEstado();

    /**
     * recupera uma lista de seguradoras estado cidade
     *
     * @return verifica se recupera uma lista de seguradoras estado cidade
     */
    public ArrayList<ModelSeguradoraCidadeEstado> getlistaSeguradoraCidadeEstadosController() {
        return this.dAOSeguradoraCidadeEstado.getListaSeguradoraCidadeEstadoDAO();
    }

    public ArrayList<ModelSeguradoraCidadeEstado> pesquisaListaSeguradoraCidadeEstadoController(String tipo, String contendo) {
        return this.dAOSeguradoraCidadeEstado.pesquisaSeguradoraCidadeEstadoDAO(tipo, contendo);
    }

}
