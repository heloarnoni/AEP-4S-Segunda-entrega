package br.com.ecotrilha.model;

import java.util.List;

public class Fauna extends Especie {

    public static final List<String> GRUPOS = List.of("Ave", "Mamífero", "Réptil", "Anfíbio", "Inseto");

    private String grupo;

    public Fauna(Integer id, String nomePopular, String nomeCientifico, String statusConservacao,
                 boolean nativa, String grupo) {
        super(id, nomePopular, nomeCientifico, statusConservacao, nativa);
        this.grupo = grupo;
    }

    @Override
    public String getCategoria() {
        return "FAUNA";
    }

    @Override
    public String getCaracteristica() {
        return grupo;
    }

    @Override
    public String getRecomendacaoManejo() {
        if (isExotica()) {
            return "Animal exótico: comunicar o órgão ambiental responsável, sem fazer captura por conta própria.";
        }
        if (isAmeacada()) {
            return "Animal ameaçado: preservar o habitat e reforçar o monitoramento da população na área.";
        }
        return "Animal nativo e não ameaçado: manter o monitoramento periódico.";
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }
}
