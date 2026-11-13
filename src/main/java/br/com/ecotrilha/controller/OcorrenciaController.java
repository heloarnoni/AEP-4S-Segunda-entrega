package br.com.ecotrilha.controller;

import br.com.ecotrilha.dao.AmeacaDAO;
import br.com.ecotrilha.dao.OcorrenciaDAO;
import br.com.ecotrilha.model.Ameaca;
import br.com.ecotrilha.model.AreaVerde;
import br.com.ecotrilha.model.Ocorrencia;

import java.sql.SQLException;
import java.util.List;

public class OcorrenciaController {

    private final OcorrenciaDAO dao = new OcorrenciaDAO();
    private final AmeacaDAO ameacaDAO = new AmeacaDAO();
    private final AreaVerdeController areaController = new AreaVerdeController();

    public Ameaca registrar(Ocorrencia ocorrencia) throws SQLException {
        AreaVerde area = areaController.buscar(ocorrencia.getAreaId());
        area.registrarOcorrencia(ocorrencia);
        dao.inserir(ocorrencia);
        if (!ocorrencia.getEspecie().isExotica()) {
            return null;
        }
        Ameaca ameaca = Ameaca.criarEspecieInvasora(area.getId(), ocorrencia.getData());
        area.registrarAmeaca(ameaca);
        ameacaDAO.inserir(ameaca);
        return ameaca;
    }

    public List<Ocorrencia> listar() throws SQLException {
        return dao.listarTodos();
    }

    public Ocorrencia buscar(int id) throws SQLException {
        Ocorrencia ocorrencia = dao.buscarPorId(id);
        if (ocorrencia == null) {
            throw new IllegalArgumentException("Ocorrência #" + id + " não está cadastrada.");
        }
        return ocorrencia;
    }

    public void atualizar(Ocorrencia ocorrencia) throws SQLException {
        areaController.buscar(ocorrencia.getAreaId());
        dao.atualizar(ocorrencia);
    }

    public void excluir(int id) throws SQLException {
        buscar(id);
        dao.excluir(id);
    }
}
