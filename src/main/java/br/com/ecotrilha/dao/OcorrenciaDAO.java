package br.com.ecotrilha.dao;

import br.com.ecotrilha.model.Ocorrencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OcorrenciaDAO extends DAOBase<Ocorrencia> {

    private static final String SELECT = "SELECT o.id AS ocorrencia_id, o.area_id, o.data_registro, o.quantidade, "
            + "o.observador, o.observacao, e.* FROM ocorrencias o JOIN especies e ON e.id = o.especie_id";

    private final EspecieDAO especieDAO = new EspecieDAO();

    @Override
    public void inserir(Ocorrencia ocorrencia) throws SQLException {
        ocorrencia.setId(inserirRetornandoId("INSERT INTO ocorrencias (area_id, especie_id, data_registro, "
                        + "quantidade, observador, observacao) VALUES (?, ?, ?, ?, ?, ?)",
                ocorrencia.getAreaId(), ocorrencia.getEspecie().getId(), ocorrencia.getData(),
                ocorrencia.getQuantidade(), ocorrencia.getObservador(), ocorrencia.getObservacao()));
    }

    @Override
    public Ocorrencia buscarPorId(int id) throws SQLException {
        return consultarUm(SELECT + " WHERE o.id = ?", id);
    }

    @Override
    public List<Ocorrencia> listarTodos() throws SQLException {
        return consultar(SELECT + " ORDER BY o.data_registro DESC, o.id");
    }

    public List<Ocorrencia> listarPorArea(int areaId) throws SQLException {
        return consultar(SELECT + " WHERE o.area_id = ? ORDER BY o.data_registro DESC, o.id", areaId);
    }

    @Override
    public void atualizar(Ocorrencia ocorrencia) throws SQLException {
        executar("UPDATE ocorrencias SET area_id = ?, especie_id = ?, data_registro = ?, quantidade = ?, "
                        + "observador = ?, observacao = ? WHERE id = ?",
                ocorrencia.getAreaId(), ocorrencia.getEspecie().getId(), ocorrencia.getData(),
                ocorrencia.getQuantidade(), ocorrencia.getObservador(), ocorrencia.getObservacao(),
                ocorrencia.getId());
    }

    @Override
    public void excluir(int id) throws SQLException {
        executar("DELETE FROM ocorrencias WHERE id = ?", id);
    }

    @Override
    protected Ocorrencia mapear(ResultSet rs) throws SQLException {
        return new Ocorrencia(rs.getInt("ocorrencia_id"), rs.getInt("area_id"), especieDAO.mapear(rs),
                rs.getDate("data_registro").toLocalDate(), rs.getInt("quantidade"),
                rs.getString("observador"), rs.getString("observacao"));
    }
}
