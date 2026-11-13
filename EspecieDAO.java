package br.com.ecotrilha.dao;

import br.com.ecotrilha.model.Especie;
import br.com.ecotrilha.model.Fauna;
import br.com.ecotrilha.model.Flora;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EspecieDAO extends DAOBase<Especie> {

    @Override
    public void inserir(Especie especie) throws SQLException {
        especie.setId(inserirRetornandoId("INSERT INTO especies (categoria, nome_popular, nome_cientifico, "
                        + "status_conservacao, nativa, forma_vida, grupo) VALUES (?, ?, ?, ?, ?, ?, ?)",
                especie.getCategoria(), especie.getNomePopular(), especie.getNomeCientifico(),
                especie.getStatusConservacao(), especie.isNativa(), formaVida(especie), grupo(especie)));
    }

    @Override
    public Especie buscarPorId(int id) throws SQLException {
        return consultarUm("SELECT * FROM especies WHERE id = ?", id);
    }

    public Especie buscarPorNomeCientifico(String nomeCientifico) throws SQLException {
        return consultarUm("SELECT * FROM especies WHERE LOWER(nome_cientifico) = LOWER(?)", nomeCientifico);
    }

    @Override
    public List<Especie> listarTodos() throws SQLException {
        return consultar("SELECT * FROM especies ORDER BY categoria, nome_popular");
    }

    @Override
    public void atualizar(Especie especie) throws SQLException {
        executar("UPDATE especies SET nome_popular = ?, nome_cientifico = ?, status_conservacao = ?, "
                        + "nativa = ?, forma_vida = ?, grupo = ? WHERE id = ?",
                especie.getNomePopular(), especie.getNomeCientifico(), especie.getStatusConservacao(),
                especie.isNativa(), formaVida(especie), grupo(especie), especie.getId());
    }

    @Override
    public void excluir(int id) throws SQLException {
        executar("DELETE FROM especies WHERE id = ?", id);
    }

    @Override
    protected Especie mapear(ResultSet rs) throws SQLException {
        if ("FLORA".equals(rs.getString("categoria"))) {
            return new Flora(rs.getInt("id"), rs.getString("nome_popular"), rs.getString("nome_cientifico"),
                    rs.getString("status_conservacao"), rs.getBoolean("nativa"), rs.getString("forma_vida"));
        }
        return new Fauna(rs.getInt("id"), rs.getString("nome_popular"), rs.getString("nome_cientifico"),
                rs.getString("status_conservacao"), rs.getBoolean("nativa"), rs.getString("grupo"));
    }

    private String formaVida(Especie especie) {
        return especie instanceof Flora ? especie.getCaracteristica() : null;
    }

    private String grupo(Especie especie) {
        return especie instanceof Fauna ? especie.getCaracteristica() : null;
    }
}
