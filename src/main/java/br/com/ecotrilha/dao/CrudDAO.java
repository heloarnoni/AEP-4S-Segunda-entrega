package br.com.ecotrilha.dao;

import java.sql.SQLException;
import java.util.List;

public interface CrudDAO<T> {

    void inserir(T entidade) throws SQLException;

    T buscarPorId(int id) throws SQLException;

    List<T> listarTodos() throws SQLException;

    void atualizar(T entidade) throws SQLException;

    void excluir(int id) throws SQLException;
}
