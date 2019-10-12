package DAO;

import conexao.ConexaoBanco;
import Model.ModelSeguradora;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author studiomicroweb
 */
public class DAOSeguradora extends ConexaoBanco {

    public int salvarSeguradorasDAO(ModelSeguradora ModelSeguradora) {
        try {
            this.conectar();
            return this.insertSQL(
                    "INSERT INTO seguradoras ("
                    + "nome,"
                    + "endereco,"
                    + "bairro,"
                    + "cod_cidade,"
                    + "cod_estado,"
                    + "cep,"
                    + "telefone,"
                    + "celular,"
                    + "email,"
                    + "cnpj,"
                    + "obs,"
                    + "numero,"
                    + "complemento,"
                    + "seguradoras.seg_km,"
                    + "seguradoras.seg_honorarios,"
                    + "seguradoras.seg_negativa,"
                    + "seguradoras.seg_iss,"
                    + "seg_hmeio"
                    + ") VALUES ("
                    + "'" + ModelSeguradora.getNome() + "',"
                    + "'" + ModelSeguradora.getEndereco() + "',"
                    + "'" + ModelSeguradora.getBairro() + "',"
                    + "'" + ModelSeguradora.getCodCidade() + "',"
                    + "'" + ModelSeguradora.getCodEstado() + "',"
                    + "'" + ModelSeguradora.getCep() + "',"
                    + "'" + ModelSeguradora.getTelefone() + "',"
                    + "'" + ModelSeguradora.getCelular() + "',"
                    + "'" + ModelSeguradora.getEmail() + "',"
                    + "'" + ModelSeguradora.getCnpj() + "',"
                    + "'" + ModelSeguradora.getObservacao() + "',"
                    + "'" + ModelSeguradora.getNumero() + "',"
                    + "'" + ModelSeguradora.getComplemento() + "',"
                    + "'" + ModelSeguradora.getSeg_km() + "',"
                    + "'" + ModelSeguradora.getSeg_honorarios() + "',"
                    + "'" + ModelSeguradora.getSeg_negativa() + "',"
                    + "'" + ModelSeguradora.getSeg_iss() + "',"
                    + "'" + ModelSeguradora.getSeg_hmeio() + "'"
                    + ");"
            );
        } catch (Exception e) {
            return 0;
        } finally {
            this.fecharConexao();
        }
    }

    /**
     * recupera Seguradora
     *
     * @param pCodigo recupera Seguradora por codigo
     * @return Verifica se foi realizado com sucesso
     */
    public ModelSeguradora getSeguradoraDAO(int pCodigo) {
        ModelSeguradora modelSeguradora = new ModelSeguradora();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "codigo,"
                    + "nome,"
                    + "endereco,"
                    + "bairro,"
                    + "cod_cidade,"
                    + "cod_estado,"
                    + "cep,"
                    + "telefone,"
                    + "celular,"
                    + "email,"
                    + "cnpj,"
                    + "obs, "
                    + "numero,"
                    + "complemento,"
                    + "seguradoras.seg_km,"
                    + "seguradoras.seg_honorarios,"
                    + "seguradoras.seg_negativa,"
                    + "seguradoras.seg_iss,"
                    + "seg_hmeio"
                    + " FROM"
                    + " seguradoras"
                    + " WHERE"
                    + " codigo = '" + pCodigo + "'"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelSeguradora.setCodigo(this.getResultSet().getInt(1));
                modelSeguradora.setNome(this.getResultSet().getString(2));
                modelSeguradora.setEndereco(this.getResultSet().getString(3));
                modelSeguradora.setBairro(this.getResultSet().getString(4));
                modelSeguradora.setCodCidade(this.getResultSet().getInt(5));
                modelSeguradora.setCodEstado(this.getResultSet().getInt(6));
                modelSeguradora.setCep(this.getResultSet().getString(7));
                modelSeguradora.setTelefone(this.getResultSet().getString(8));
                modelSeguradora.setCelular(this.getResultSet().getString(9));
                modelSeguradora.setEmail(this.getResultSet().getString(10));
                modelSeguradora.setCnpj(this.getResultSet().getString(11));
                modelSeguradora.setObservacao(this.getResultSet().getString(12));
                modelSeguradora.setNumero(this.getResultSet().getInt(13));
                modelSeguradora.setComplemento(this.getResultSet().getString(14));
                modelSeguradora.setSeg_km(this.getResultSet().getDouble(15));
                modelSeguradora.setSeg_honorarios(this.getResultSet().getDouble(16));
                modelSeguradora.setSeg_negativa(this.getResultSet().getString(17));
                modelSeguradora.setSeg_iss(this.getResultSet().getString(18));
                modelSeguradora.setSeg_hmeio(this.getResultSet().getDouble(19));
            }
        } catch (SQLException e) {
        } finally {
            this.fecharConexao();
        }
        return modelSeguradora;
    }

    /**
     * recupera Seguradora
     *
     * @param pNome recupera Seguradora por nome
     * @return verifica se foi realizado com sucesso
     */
    public ModelSeguradora getSeguradoraDAO(String pNome) {
        ModelSeguradora modelSeguradora = new ModelSeguradora();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "codigo,"
                    + "nome,"
                    + "endereco,"
                    + "bairro,"
                    + "cod_cidade,"
                    + "cod_estado,"
                    + "cep,"
                    + "telefone,"
                    + "celular,"
                    + "email,"
                    + "cnpj,"
                    + "obs,"
                    + "numero,"
                    + "complemento,"
                    + "seguradoras.seg_km, "
                    + "seguradoras.seg_honorarios, "
                    + "seguradoras.seg_negativa, "
                    + "seguradoras.seg_iss,"
                    + "seg_hmeio"
                    + " FROM"
                    + " seguradoras"
                    + " WHERE"
                    + " nome = '" + pNome + "'"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelSeguradora.setCodigo(this.getResultSet().getInt(1));
                modelSeguradora.setNome(this.getResultSet().getString(2));
                modelSeguradora.setEndereco(this.getResultSet().getString(3));
                modelSeguradora.setBairro(this.getResultSet().getString(4));
                modelSeguradora.setCodCidade(this.getResultSet().getInt(5));
                modelSeguradora.setCodEstado(this.getResultSet().getInt(6));
                modelSeguradora.setCep(this.getResultSet().getString(7));
                modelSeguradora.setTelefone(this.getResultSet().getString(8));
                modelSeguradora.setCelular(this.getResultSet().getString(9));
                modelSeguradora.setEmail(this.getResultSet().getString(10));
                modelSeguradora.setCnpj(this.getResultSet().getString(11));
                modelSeguradora.setObservacao(this.getResultSet().getString(12));
                modelSeguradora.setNumero(this.getResultSet().getInt(13));
                modelSeguradora.setComplemento(this.getResultSet().getString(14));
                modelSeguradora.setSeg_km(this.getResultSet().getDouble(15));
                modelSeguradora.setSeg_honorarios(this.getResultSet().getDouble(16));
                modelSeguradora.setSeg_negativa(this.getResultSet().getString(17));
                modelSeguradora.setSeg_iss(this.getResultSet().getString(18));
                modelSeguradora.setSeg_hmeio(this.getResultSet().getDouble(19));
            }
        } catch (SQLException e) {
        } finally {
            this.fecharConexao();
        }
        return modelSeguradora;
    }

    /*Alterar Seguradoras*/
    public boolean atualizarSeguradoraDAO(ModelSeguradora ModelSeguradora) {
        /*System.out.println("Entrei aqui");
         System.out.println("codigo:" + ModelSeguradora.getCodigo());*/
        try {
            this.conectar();
            this.executarUpdateDeleteSQL(
                    "UPDATE seguradoras SET "
                    + "codigo = '" + ModelSeguradora.getCodigo() + "',"
                    + "nome = '" + ModelSeguradora.getNome() + "',"
                    + "endereco = '" + ModelSeguradora.getEndereco() + "',"
                    + "bairro = '" + ModelSeguradora.getBairro() + "',"
                    + "cod_cidade = '" + ModelSeguradora.getCodCidade() + "',"
                    + "cod_estado = '" + ModelSeguradora.getCodEstado() + "',"
                    + "cep = '" + ModelSeguradora.getCep() + "',"
                    + "telefone = '" + ModelSeguradora.getTelefone() + "',"
                    + "celular = '" + ModelSeguradora.getCelular() + "',"
                    + "email = '" + ModelSeguradora.getEmail() + "',"
                    + "cnpj = '" + ModelSeguradora.getCnpj() + "',"
                    + "obs = '" + ModelSeguradora.getObservacao() + "',"
                    + "numero = '" + ModelSeguradora.getNumero() + "',"
                    + "complemento = '" + ModelSeguradora.getComplemento() + "',"
                    + "seguradoras.seg_km = '" + ModelSeguradora.getSeg_km() + "',"
                    + "seguradoras.seg_honorarios = '" + ModelSeguradora.getSeg_honorarios() + "',"
                    + "seguradoras.seg_negativa = '" + ModelSeguradora.getSeg_negativa() + "',"
                    + "seguradoras.seg_iss = '" + ModelSeguradora.getSeg_iss() + "',"
                    + "seguradoras.seg_hmeio = '" + ModelSeguradora.getSeg_hmeio() + "'"
                    + " WHERE "
                    + "codigo = '" + ModelSeguradora.getCodigo() + "'"
                    + ";"
            );
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            this.fecharConexao();
        }
    }

    /**
     * recupera uma lista de Seguradora return ArrayList
     *
     * @return recupera uma lista de Seguradora
     */
    public ArrayList<ModelSeguradora> getListaSeguradoraDAO() {
        ArrayList<ModelSeguradora> listamodelSeguradora = new ArrayList();
        ModelSeguradora modelSeguradora = new ModelSeguradora();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "codigo,"
                    + "nome,"
                    + "endereco,"
                    + "bairro,"
                    + "cod_cidade,"
                    + "cod_estado,"
                    + "cep,"
                    + "telefone,"
                    + "celular,"
                    + "email,"
                    + "cnpj,"
                    + "obs,"
                    + "numero,"
                    + "complemento,"
                    + "seguradoras.seg_km,"
                    + "seguradoras.seg_honorarios,"
                    + "seguradoras.seg_negativa,"
                    + "seguradoras.seg_iss,"
                    + "seg_hmeio"
                    + " FROM"
                    + " seguradoras"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelSeguradora = new ModelSeguradora();
                modelSeguradora.setCodigo(this.getResultSet().getInt(1));
                modelSeguradora.setNome(this.getResultSet().getString(2));
                modelSeguradora.setEndereco(this.getResultSet().getString(3));
                modelSeguradora.setBairro(this.getResultSet().getString(4));
                modelSeguradora.setCodCidade(this.getResultSet().getInt(5));
                modelSeguradora.setCodEstado(this.getResultSet().getInt(6));
                modelSeguradora.setCep(this.getResultSet().getString(7));
                modelSeguradora.setTelefone(this.getResultSet().getString(8));
                modelSeguradora.setCelular(this.getResultSet().getString(9));
                modelSeguradora.setEmail(this.getResultSet().getString(10));
                modelSeguradora.setCnpj(this.getResultSet().getString(11));
                modelSeguradora.setObservacao(this.getResultSet().getString(12));
                modelSeguradora.setNumero(this.getResultSet().getInt(13));
                modelSeguradora.setComplemento(this.getResultSet().getString(14));
                modelSeguradora.setSeg_km(this.getResultSet().getDouble(15));
                modelSeguradora.setSeg_honorarios(this.getResultSet().getDouble(16));
                modelSeguradora.setSeg_negativa(this.getResultSet().getString(17));
                modelSeguradora.setSeg_iss(this.getResultSet().getString(18));
                modelSeguradora.setSeg_hmeio(this.getResultSet().getDouble(19));
                listamodelSeguradora.add(modelSeguradora);
            }
        } catch (SQLException e) {
        } finally {
            this.fecharConexao();
        }
        return listamodelSeguradora;
    }

    /**
     * exclui Seguradora
     *
     * @param Codigo Exclui Seguradora
     * @return Verifica se foi excluido com sucesso
     */
    public boolean excluirSeguradorasDAO(int Codigo) {
        try {
            this.conectar();
            return this.executarUpdateDeleteSQL(
                    "DELETE FROM seguradoras "
                    + " WHERE "
                    + "codigo = '" + Codigo + "'"
                    + ";"
            );
        } catch (Exception e) {
            return false;
        } finally {
            this.fecharConexao();
        }
    }

}
