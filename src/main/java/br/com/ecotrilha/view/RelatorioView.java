package br.com.ecotrilha.view;

import br.com.ecotrilha.controller.AreaVerdeController;
import br.com.ecotrilha.controller.RelatorioController;

import java.sql.SQLException;

public class RelatorioView implements Menu {

    private final RelatorioController controller = new RelatorioController();
    private final AreaVerdeController areaController = new AreaVerdeController();

    @Override
    public String getTitulo() {
        return "Relatório por área verde";
    }

    @Override
    public void exibir() {
        try {
            areaController.listar().forEach(System.out::println);
            System.out.println(controller.gerar(ConsoleUtil.lerInteiro("ID da área verde")));
        } catch (SQLException e) {
            System.out.println("Erro no banco de dados: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Atenção: " + e.getMessage());
        }
    }
}
