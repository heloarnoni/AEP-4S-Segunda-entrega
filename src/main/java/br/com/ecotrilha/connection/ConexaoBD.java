package br.com.ecotrilha.connection;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexaoBD {

    public static Connection getConexao() throws SQLException {
        Properties config = new Properties();
        try (InputStream entrada = ConexaoBD.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (entrada == null) {
                throw new SQLException("Arquivo src/main/resources/db.properties não encontrado.");
            }
            config.load(entrada);
        } catch (IOException e) {
            throw new SQLException("Erro ao ler o db.properties: " + e.getMessage());
        }
        return DriverManager.getConnection(config.getProperty("db.url"),
                config.getProperty("db.usuario"), config.getProperty("db.senha"));
    }
}
