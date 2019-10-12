/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DAOFuncionarioCidadeEstado;
import java.util.ArrayList;
import Model.ModelFuncionarioCidadeEstado;

/**
 *
 * @author studiomicroweb
 */
public class ControllerFuncionarioCidadeEstado {

    private final DAOFuncionarioCidadeEstado dAOClienteCidadeEstado = new DAOFuncionarioCidadeEstado();

    /**
     * recupera uma lista deCidadeEstado
     *
     * @return verifica se recuperou a lista de cidades
     */
    public ArrayList<ModelFuncionarioCidadeEstado> getListaFuncionarioCidadeEstadoController() {
        return this.dAOClienteCidadeEstado.getListaFuncionarioCidadeEstadoDAO();
    }

    public ArrayList<ModelFuncionarioCidadeEstado> pesquisaListafuncionarioCidadeEstadoController(String tipo, String contendo) {
        return this.dAOClienteCidadeEstado.pesquisaFuncionarioCidadeEstadoDAO(tipo, contendo);
    }
}
