package br.com.ecotrilha.dao;

import br.com.ecotrilha.model.Ameaca;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AmeacaDAO extends DAOBase<Ameaca> {

    @Override
    public void inserir(Ameaca ameaca) throws SQLException {
        ameaca.setId(inserirRetornandoId(
                "INSERT INTO ameacas (area_id, tipo, gravidade, data_registro, status) VALUES (?, ?, ?, ?, ?)",
                ameaca.getAreaId(), ameaca.getTipo(), ameaca.getGravidade(), ameaca.getData(), ameaca.getStatus()));
    }

    @Override
    public Ameaca buscarPorId(int id) throws SQLException {
        return consultarUm("SELECT * FROM ameacas WHERE id = ?", id);
    }

    @Override
    public List<Ameaca> listarTodos() throws SQLException {
        return consultar("SELECT * FROM ameacas ORDER BY data_registro DESC, id");
    }

    public List<Ameaca> listarPorArea(int areaId) throws SQLException {
        return consultar("SELECT * FROM ameacas WHERE area_id = ? ORDER BY data_registro DESC, id", areaId);
    }

    @Override
    public void atualizar(Ameaca ameaca) throws SQLException {
        executar("UPDATE ameacas SET area_id = ?, tipo = ?, gravidade = ?, data_registro = ?, status = ? WHERE id = ?",
                ameaca.getAreaId(), ameaca.getTipo(), ameaca.getGravidade(), ameaca.getData(), ameaca.getStatus(),
                ameaca.getId());
    }

    @Override
    public void excluir(int id) throws SQLException {
        executar("DELETE FROM ameacas WHERE id = ?", id);
    }

    @Override
    protected Ameaca mapear(ResultSet rs) throws SQLException {
        return new Ameaca(rs.getInt("id"), rs.getInt("area_id"), rs.getString("tipo"), rs.getString("gravidade"),
                rs.getDate("data_registro").toLocalDate(), rs.getString("status"));
    }
}
