package br.com.ecotrilha.view;

import br.com.ecotrilha.controller.AmeacaController;
import br.com.ecotrilha.controller.AreaVerdeController;
import br.com.ecotrilha.model.Ameaca;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class AmeacaView extends MenuCrud<Ameaca> {

    private final AmeacaController controller = new AmeacaController();
    private final AreaVerdeController areaController = new AreaVerdeController();

    @Override
    public String getTitulo() {
        return "Ameaças ambientais";
    }

    @Override
    protected void cadastrar() throws SQLException {
        areaController.listar().forEach(System.out::println);
        int areaId = areaController.buscar(ConsoleUtil.lerInteiro("ID da área verde")).getId();
        Ameaca ameaca = new Ameaca(null, areaId, ConsoleUtil.escolher("Tipo", Ameaca.TIPOS),
                ConsoleUtil.escolher("Gravidade", Ameaca.GRAVIDADES), ConsoleUtil.lerData("Data", LocalDate.now()),
                "Ativa");
        controller.registrar(ameaca);
        System.out.println("Ameaça registrada com o ID " + ameaca.getId() + ".");
    }

    @Override
    protected List<Ameaca> listar() throws SQLException {
        return controller.listar();
    }

    @Override
    protected Ameaca buscar(int id) throws SQLException {
        return controller.buscar(id);
    }

    @Override
    protected void editar(Ameaca ameaca) throws SQLException {
        ameaca.setTipo(ConsoleUtil.escolher("Tipo", Ameaca.TIPOS, ameaca.getTipo()));
        ameaca.setGravidade(ConsoleUtil.escolher("Gravidade", Ameaca.GRAVIDADES, ameaca.getGravidade()));
        ameaca.setData(ConsoleUtil.lerData("Data", ameaca.getData()));
        if (ameaca.isAtiva() && ConsoleUtil.confirmar("Marcar como resolvida?")) {
            ameaca.resolver();
        }
        controller.atualizar(ameaca);
    }

    @Override
    protected void excluir(int id) throws SQLException {
        controller.excluir(id);
    }
}
