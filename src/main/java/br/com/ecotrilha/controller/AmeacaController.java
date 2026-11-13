package br.com.ecotrilha.controller;

import br.com.ecotrilha.dao.AmeacaDAO;
import br.com.ecotrilha.model.Ameaca;
import br.com.ecotrilha.model.AreaVerde;

import java.sql.SQLException;
import java.util.List;

public class AmeacaController {

    private final AmeacaDAO dao = new AmeacaDAO();
    private final AreaVerdeController areaController = new AreaVerdeController();

    public void registrar(Ameaca ameaca) throws SQLException {
        AreaVerde area = areaController.buscar(ameaca.getAreaId());
        area.registrarAmeaca(ameaca);
        dao.inserir(ameaca);
    }

    public List<Ameaca> listar() throws SQLException {
        return dao.listarTodos();
    }

    public Ameaca buscar(int id) throws SQLException {
        Ameaca ameaca = dao.buscarPorId(id);
        if (ameaca == null) {
            throw new IllegalArgumentException("Ameaça #" + id + " não está cadastrada.");
        }
        return ameaca;
    }

    public void atualizar(Ameaca ameaca) throws SQLException {
        areaController.buscar(ameaca.getAreaId());
        dao.atualizar(ameaca);
    }

    public void excluir(int id) throws SQLException {
        buscar(id);
        dao.excluir(id);
    }
}
