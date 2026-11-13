package br.com.ecotrilha.controller;

import br.com.ecotrilha.dao.EspecieDAO;
import br.com.ecotrilha.model.Especie;

import java.sql.SQLException;
import java.util.List;

public class EspecieController {

    private final EspecieDAO dao = new EspecieDAO();

    public void cadastrar(Especie especie) throws SQLException {
        validarNomeCientificoUnico(especie);
        dao.inserir(especie);
    }

    public List<Especie> listar() throws SQLException {
        return dao.listarTodos();
    }

    public Especie buscar(int id) throws SQLException {
        Especie especie = dao.buscarPorId(id);
        if (especie == null) {
            throw new IllegalArgumentException("Espécie #" + id + " não está cadastrada.");
        }
        return especie;
    }

    public void atualizar(Especie especie) throws SQLException {
        validarNomeCientificoUnico(especie);
        dao.atualizar(especie);
    }

    public void excluir(int id) throws SQLException {
        buscar(id);
        try {
            dao.excluir(id);
        } catch (SQLException e) {
            if ("23503".equals(e.getSQLState())) {
                throw new IllegalArgumentException("A espécie possui ocorrências registradas e não pode ser excluída.");
            }
            throw e;
        }
    }

    private void validarNomeCientificoUnico(Especie especie) throws SQLException {
        Especie existente = dao.buscarPorNomeCientifico(especie.getNomeCientifico());
        if (existente != null && !existente.getId().equals(especie.getId())) {
            throw new IllegalArgumentException("RN02: já existe uma espécie com o nome científico \""
                    + existente.getNomeCientifico() + "\".");
        }
    }
}
