package br.com.ecotrilha;

import br.com.ecotrilha.connection.ConexaoBD;
import br.com.ecotrilha.view.AreaVerdeView;
import br.com.ecotrilha.view.ConsoleUtil;
import br.com.ecotrilha.view.Menu;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try (Connection conexao = ConexaoBD.getConexao()) {
            System.out.println("EcoTrilha conectado ao PostgreSQL.");
        } catch (SQLException e) {
            System.out.println("Não foi possível conectar ao banco de dados: " + e.getMessage());
            return;
        }

        List<Menu> menus = List.of(new AreaVerdeView());

        while (true) {
            System.out.println("\n=== EcoTrilha - Monitoramento da Biodiversidade (ODS 15) ===");
            for (int i = 0; i < menus.size(); i++) {
                System.out.println((i + 1) + ") " + menus.get(i).getTitulo());
            }
            System.out.println("0) Sair");
            int opcao = ConsoleUtil.lerInteiro("Opção");
            if (opcao == 0) {
                return;
            }
            if (opcao >= 1 && opcao <= menus.size()) {
                menus.get(opcao - 1).exibir();
            } else {
                System.out.println("Opção inválida.");
            }
        }
    }
}
