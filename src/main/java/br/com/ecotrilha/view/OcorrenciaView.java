package br.com.ecotrilha.view;

import br.com.ecotrilha.controller.AreaVerdeController;
import br.com.ecotrilha.controller.EspecieController;
import br.com.ecotrilha.controller.OcorrenciaController;
import br.com.ecotrilha.model.Ameaca;
import br.com.ecotrilha.model.Especie;
import br.com.ecotrilha.model.Ocorrencia;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class OcorrenciaView extends MenuCrud<Ocorrencia> {

    private final OcorrenciaController controller = new OcorrenciaController();
    private final AreaVerdeController areaController = new AreaVerdeController();
    private final EspecieController especieController = new EspecieController();

    @Override
    public String getTitulo() {
        return "Ocorrências (monitoramentos)";
    }

    @Override
    protected void cadastrar() throws SQLException {
        areaController.listar().forEach(System.out::println);
        int areaId = areaController.buscar(ConsoleUtil.lerInteiro("ID da área verde")).getId();
        especieController.listar().forEach(System.out::println);
        Especie especie = especieController.buscar(ConsoleUtil.lerInteiro("ID da espécie"));
        Ocorrencia ocorrencia = new Ocorrencia(null, areaId, especie, ConsoleUtil.lerData("Data", LocalDate.now()),
                ConsoleUtil.lerInteiro("Quantidade de indivíduos"), ConsoleUtil.lerTexto("Observador"),
                ConsoleUtil.lerOpcional("Observações", null));
        Ameaca gerada = controller.registrar(ocorrencia);
        System.out.println("Ocorrência registrada com o ID " + ocorrencia.getId() + ".");
        if (gerada != null) {
            System.out.println("RN03: espécie exótica! Ameaça gerada automaticamente: " + gerada);
        }
    }

    @Override
    protected List<Ocorrencia> listar() throws SQLException {
        return controller.listar();
    }

    @Override
    protected Ocorrencia buscar(int id) throws SQLException {
        return controller.buscar(id);
    }

    @Override
    protected void editar(Ocorrencia ocorrencia) throws SQLException {
        ocorrencia.setAreaId(ConsoleUtil.lerInteiro("ID da área verde", ocorrencia.getAreaId()));
        ocorrencia.setEspecie(especieController.buscar(
                ConsoleUtil.lerInteiro("ID da espécie", ocorrencia.getEspecie().getId())));
        ocorrencia.setData(ConsoleUtil.lerData("Data", ocorrencia.getData()));
        ocorrencia.setQuantidade(ConsoleUtil.lerInteiro("Quantidade de indivíduos", ocorrencia.getQuantidade()));
        ocorrencia.setObservador(ConsoleUtil.lerTexto("Observador", ocorrencia.getObservador()));
        ocorrencia.setObservacao(ConsoleUtil.lerOpcional("Observações", ocorrencia.getObservacao()));
        controller.atualizar(ocorrencia);
    }

    @Override
    protected void excluir(int id) throws SQLException {
        controller.excluir(id);
    }
}
