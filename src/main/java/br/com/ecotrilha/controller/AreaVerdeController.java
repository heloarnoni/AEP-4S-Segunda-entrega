package br.com.ecotrilha.controller;

import br.com.ecotrilha.dao.AreaVerdeDAO;
import br.com.ecotrilha.model.AreaVerde;

import java.sql.SQLException;
import java.util.List;

public class AreaVerdeController {

    private final AreaVerdeDAO dao = new AreaVerdeDAO();

    public void cadastrar(AreaVerde area) throws SQLException {
        dao.inserir(area);
    }

    public List<AreaVerde> listar() throws SQLException {
        return dao.listarTodos();
    }

    public AreaVerde buscar(int id) throws SQLException {
        AreaVerde area = dao.buscarPorId(id);
        if (area == null) {
            throw new IllegalArgumentException("Área verde #" + id + " não está cadastrada.");
        }
        return area;
    }

    public void atualizar(AreaVerde area) throws SQLException {
        dao.atualizar(area);
    }

    public void excluir(int id) throws SQLException {
        buscar(id);
        dao.excluir(id);
    }
}
