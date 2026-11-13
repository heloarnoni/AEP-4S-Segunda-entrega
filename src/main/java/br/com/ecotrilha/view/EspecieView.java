package br.com.ecotrilha.view;

import br.com.ecotrilha.controller.EspecieController;
import br.com.ecotrilha.model.Especie;
import br.com.ecotrilha.model.Fauna;
import br.com.ecotrilha.model.Flora;

import java.sql.SQLException;
import java.util.List;

public class EspecieView extends MenuCrud<Especie> {

    private static final List<String> ORIGENS = List.of("Nativa", "Exótica");

    private final EspecieController controller = new EspecieController();

    @Override
    public String getTitulo() {
        return "Espécies de flora e fauna";
    }

    @Override
    protected void cadastrar() throws SQLException {
        String categoria = ConsoleUtil.escolher("Categoria", List.of("Flora", "Fauna"));
        String nomePopular = ConsoleUtil.lerTexto("Nome popular");
        String nomeCientifico = ConsoleUtil.lerTexto("Nome científico");
        String status = escolherStatus(null);
        boolean nativa = ConsoleUtil.escolher("Origem", ORIGENS).equals("Nativa");
        Especie especie = categoria.equals("Flora")
                ? new Flora(null, nomePopular, nomeCientifico, status, nativa,
                        ConsoleUtil.escolher("Forma de vida", Flora.FORMAS_VIDA))
                : new Fauna(null, nomePopular, nomeCientifico, status, nativa,
                        ConsoleUtil.escolher("Grupo", Fauna.GRUPOS));
        controller.cadastrar(especie);
        System.out.println("Espécie cadastrada com o ID " + especie.getId() + ".");
    }

    @Override
    protected List<Especie> listar() throws SQLException {
        return controller.listar();
    }

    @Override
    protected Especie buscar(int id) throws SQLException {
        return controller.buscar(id);
    }

    @Override
    protected void mostrar(Especie especie) {
        System.out.println(especie);
        System.out.println("Recomendação de manejo: " + especie.getRecomendacaoManejo());
    }

    @Override
    protected void editar(Especie especie) throws SQLException {
        especie.setNomePopular(ConsoleUtil.lerTexto("Nome popular", especie.getNomePopular()));
        especie.setNomeCientifico(ConsoleUtil.lerTexto("Nome científico", especie.getNomeCientifico()));
        especie.setStatusConservacao(escolherStatus(especie.getStatusConservacao()));
        especie.setNativa(ConsoleUtil.escolher("Origem", ORIGENS, especie.getOrigem()).equals("Nativa"));
        if (especie instanceof Flora flora) {
            flora.setFormaVida(ConsoleUtil.escolher("Forma de vida", Flora.FORMAS_VIDA, flora.getFormaVida()));
        } else if (especie instanceof Fauna fauna) {
            fauna.setGrupo(ConsoleUtil.escolher("Grupo", Fauna.GRUPOS, fauna.getGrupo()));
        }
        controller.atualizar(especie);
    }

    @Override
    protected void excluir(int id) throws SQLException {
        controller.excluir(id);
    }

    private String escolherStatus(String atual) {
        String padrao = null;
        for (String status : Especie.STATUS) {
            if (atual != null && status.startsWith(atual)) {
                padrao = status;
            }
        }
        return ConsoleUtil.escolher("Status de conservação (IUCN)", Especie.STATUS, padrao).substring(0, 2);
    }
}
