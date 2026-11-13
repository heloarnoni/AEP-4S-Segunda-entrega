package br.com.ecotrilha.dao;

import br.com.ecotrilha.model.AreaVerde;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AreaVerdeDAO extends DAOBase<AreaVerde> {

    @Override
    public void inserir(AreaVerde area) throws SQLException {
        area.setId(inserirRetornandoId(
                "INSERT INTO areas_verdes (nome, tipo, cidade, area_hectares) VALUES (?, ?, ?, ?)",
                area.getNome(), area.getTipo(), area.getCidade(), area.getAreaHectares()));
    }

    @Override
    public AreaVerde buscarPorId(int id) throws SQLException {
        return consultarUm("SELECT * FROM areas_verdes WHERE id = ?", id);
    }

    @Override
    public List<AreaVerde> listarTodos() throws SQLException {
        return consultar("SELECT * FROM areas_verdes ORDER BY id");
    }

    @Override
    public void atualizar(AreaVerde area) throws SQLException {
        executar("UPDATE areas_verdes SET nome = ?, tipo = ?, cidade = ?, area_hectares = ? WHERE id = ?",
                area.getNome(), area.getTipo(), area.getCidade(), area.getAreaHectares(), area.getId());
    }

    @Override
    public void excluir(int id) throws SQLException {
        executar("DELETE FROM areas_verdes WHERE id = ?", id);
    }

    @Override
    protected AreaVerde mapear(ResultSet rs) throws SQLException {
        return new AreaVerde(rs.getInt("id"), rs.getString("nome"), rs.getString("tipo"),
                rs.getString("cidade"), rs.getDouble("area_hectares"));
    }
}
