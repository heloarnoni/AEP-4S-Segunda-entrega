package br.com.ecotrilha.model;

import java.util.List;

public class Flora extends Especie {

    public static final List<String> FORMAS_VIDA = List.of("Árvore", "Arbusto", "Herbácea");

    private String formaVida;

    public Flora(Integer id, String nomePopular, String nomeCientifico, String statusConservacao,
                 boolean nativa, String formaVida) {
        super(id, nomePopular, nomeCientifico, statusConservacao, nativa);
        this.formaVida = formaVida;
    }

    @Override
    public String getCategoria() {
        return "FLORA";
    }

    @Override
    public String getCaracteristica() {
        return formaVida;
    }

    @Override
    public String getRecomendacaoManejo() {
        if (isExotica()) {
            return "Planta exótica: fazer a remoção controlada e substituir por mudas de espécies nativas.";
        }
        if (isAmeacada()) {
            return "Planta ameaçada: proteger as árvores matrizes e coletar sementes para produção de mudas em viveiro.";
        }
        return "Planta nativa e não ameaçada: manter o monitoramento periódico.";
    }

    public String getFormaVida() {
        return formaVida;
    }

    public void setFormaVida(String formaVida) {
        this.formaVida = formaVida;
    }
}
