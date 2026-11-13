package br.com.ecotrilha.dao;

import br.com.ecotrilha.connection.ConexaoBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class DAOBase<T> implements CrudDAO<T> {

    protected abstract T mapear(ResultSet rs) throws SQLException;

    protected List<T> consultar(String sql, Object... parametros) throws SQLException {
        List<T> lista = new ArrayList<>();
        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement ps = preparar(conexao, sql, parametros);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        }
        return lista;
    }

    protected T consultarUm(String sql, Object... parametros) throws SQLException {
        List<T> lista = consultar(sql, parametros);
        return lista.isEmpty() ? null : lista.get(0);
    }

    protected int inserirRetornandoId(String sql, Object... parametros) throws SQLException {
        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement ps = preparar(conexao, sql + " RETURNING id", parametros);
             ResultSet rs = ps.executeQuery()) {
            rs.next();
            return rs.getInt(1);
        }
    }

    protected void executar(String sql, Object... parametros) throws SQLException {
        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement ps = preparar(conexao, sql, parametros)) {
            ps.executeUpdate();
        }
    }

    private PreparedStatement preparar(Connection conexao, String sql, Object... parametros) throws SQLException {
        PreparedStatement ps = conexao.prepareStatement(sql);
        for (int i = 0; i < parametros.length; i++) {
            ps.setObject(i + 1, parametros[i]);
        }
        return ps;
    }
}
