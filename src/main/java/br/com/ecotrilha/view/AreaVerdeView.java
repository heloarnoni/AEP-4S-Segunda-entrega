package br.com.ecotrilha.view;

import br.com.ecotrilha.controller.AreaVerdeController;
import br.com.ecotrilha.model.AreaVerde;

import java.sql.SQLException;
import java.util.List;

public class AreaVerdeView extends MenuCrud<AreaVerde> {

    private final AreaVerdeController controller = new AreaVerdeController();

    @Override
    public String getTitulo() {
        return "Áreas verdes";
    }

    @Override
    protected void cadastrar() throws SQLException {
        AreaVerde area = new AreaVerde(null, ConsoleUtil.lerTexto("Nome"),
                ConsoleUtil.escolher("Tipo", AreaVerde.TIPOS), ConsoleUtil.lerTexto("Cidade"),
                ConsoleUtil.lerDecimal("Área em hectares"));
        controller.cadastrar(area);
        System.out.println("Área verde cadastrada com o ID " + area.getId() + ".");
    }

    @Override
    protected List<AreaVerde> listar() throws SQLException {
        return controller.listar();
    }

    @Override
    protected AreaVerde buscar(int id) throws SQLException {
        return controller.buscar(id);
    }

    @Override
    protected void editar(AreaVerde area) throws SQLException {
        area.setNome(ConsoleUtil.lerTexto("Nome", area.getNome()));
        area.setTipo(ConsoleUtil.escolher("Tipo", AreaVerde.TIPOS, area.getTipo()));
        area.setCidade(ConsoleUtil.lerTexto("Cidade", area.getCidade()));
        area.setAreaHectares(ConsoleUtil.lerDecimal("Área em hectares", area.getAreaHectares()));
        controller.atualizar(area);
    }

    @Override
    protected void excluir(int id) throws SQLException {
        controller.excluir(id);
        System.out.println("RN01: as ocorrências e ameaças desta área também foram excluídas.");
    }
}
