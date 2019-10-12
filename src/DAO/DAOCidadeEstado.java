/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.ModelCidadeEstado;
import conexao.ConexaoBanco;
import java.util.ArrayList;
import Model.ModelCidade;
import Model.ModelEstado;
import java.sql.SQLException;

/**
 *
 * @author studiomicroweb
 */
public class DAOCidadeEstado extends ConexaoBanco {

    /**
     * recupera uma lista de CidadeEstado return ArrayList
     *
     * @return Retorna Lista de Cidades pelo Estado em lista
     */
    public ArrayList<ModelCidadeEstado> getListaCidadeEstadoDAO() {
        ArrayList<ModelCidadeEstado> listamodelCidadeEstado = new ArrayList();
        ModelCidadeEstado modelCidadeEstado = new ModelCidadeEstado();
        ModelCidade modelCidade = new ModelCidade();
        ModelEstado modelEstado = new ModelEstado();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "cidade.codigo,"
                    + "cidade.nome,"
                    + "cidade.fk_cod_estado,"
                    + "estado.codigo,"
                    + "estado.uf,"
                    + "estado.nome "
                    + " FROM"
                    + " cidade inner join estado on estado.codigo = cidade.fk_cod_estado"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelCidade = new ModelCidade();
                modelEstado = new ModelEstado();
                modelCidadeEstado = new ModelCidadeEstado();
                modelCidade.setCodigo(this.getResultSet().getInt(1));
                modelCidade.setNome(this.getResultSet().getString(2));
                modelCidade.setFk_cod_estado(this.getResultSet().getInt(3));
                modelEstado.setCodigo(this.getResultSet().getInt(4));
                modelEstado.setUf(this.getResultSet().getString(5));
                modelEstado.setNome(this.getResultSet().getString(6));
                modelCidadeEstado.setModelCidade(modelCidade);
                modelCidadeEstado.setModelEstado(modelEstado);
                listamodelCidadeEstado.add(modelCidadeEstado);
            }

        } catch (SQLException e) {
        } finally {
            this.fecharConexao();
        }
        return listamodelCidadeEstado;
    }

}
