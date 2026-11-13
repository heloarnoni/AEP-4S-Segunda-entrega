package br.com.ecotrilha.controller;

import br.com.ecotrilha.dao.AmeacaDAO;
import br.com.ecotrilha.dao.OcorrenciaDAO;
import br.com.ecotrilha.model.Ameaca;
import br.com.ecotrilha.model.AreaVerde;
import br.com.ecotrilha.model.Especie;
import br.com.ecotrilha.model.Ocorrencia;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class RelatorioController {

    private final AreaVerdeController areaController = new AreaVerdeController();
    private final OcorrenciaDAO ocorrenciaDAO = new OcorrenciaDAO();
    private final AmeacaDAO ameacaDAO = new AmeacaDAO();

    public String gerar(int areaId) throws SQLException {
        AreaVerde area = areaController.buscar(areaId);
        ocorrenciaDAO.listarPorArea(areaId).forEach(area::registrarOcorrencia);
        ameacaDAO.listarPorArea(areaId).forEach(area::registrarAmeaca);

        Map<Integer, Especie> especies = new LinkedHashMap<>();
        for (Ocorrencia ocorrencia : area.getOcorrencias()) {
            especies.putIfAbsent(ocorrencia.getEspecie().getId(), ocorrencia.getEspecie());
        }

        int nativas = 0;
        int ameacadas = 0;
        StringBuilder recomendacoes = new StringBuilder();
        for (Especie especie : especies.values()) {
            if (especie.isNativa()) {
                nativas++;
            }
            if (especie.isAmeacada()) {
                ameacadas++;
            }
            if (especie.isExotica() || especie.isAmeacada()) {
                recomendacoes.append("  - ").append(especie.getNomePopular()).append(" (")
                        .append(especie.getNomeCientifico()).append("): ")
                        .append(especie.getRecomendacaoManejo()).append("\n");
            }
        }

        StringBuilder ameacasAbertas = new StringBuilder();
        for (Ameaca ameaca : area.getAmeacas()) {
            if (ameaca.isAtiva()) {
                ameacasAbertas.append("  ").append(ameaca).append("\n");
            }
        }

        return "\n=== Relatório da área: " + area.getNome() + " ===\n"
                + area + "\n"
                + "Total de espécies encontradas: " + especies.size() + "\n"
                + "Nativas: " + nativas + " | Exóticas: " + (especies.size() - nativas)
                + " | Ameaçadas: " + ameacadas + "\n"
                + "\nAmeaças em aberto:\n" + (ameacasAbertas.length() == 0 ? "  Nenhuma.\n" : ameacasAbertas)
                + "\nRecomendações de preservação e manejo:\n"
                + (recomendacoes.length() == 0 ? "  Nenhuma espécie exótica ou ameaçada.\n" : recomendacoes);
    }
}
