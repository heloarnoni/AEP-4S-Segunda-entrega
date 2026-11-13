package br.com.ecotrilha.model;

import java.util.List;

public abstract class Especie {

    public static final List<String> STATUS = List.of("LC - Pouco preocupante", "NT - Quase ameaçada",
            "VU - Vulnerável", "EN - Em perigo", "CR - Criticamente em perigo", "EW - Extinta na natureza",
            "EX - Extinta", "DD - Dados insuficientes");

    protected Integer id;
    protected String nomePopular;
    protected String nomeCientifico;
    protected String statusConservacao;
    protected boolean nativa;

    protected Especie(Integer id, String nomePopular, String nomeCientifico, String statusConservacao,
                      boolean nativa) {
        this.id = id;
        this.nomePopular = nomePopular;
        this.nomeCientifico = nomeCientifico;
        this.statusConservacao = statusConservacao;
        this.nativa = nativa;
    }

    public abstract String getCategoria();

    public abstract String getCaracteristica();

    public abstract String getRecomendacaoManejo();

    public boolean isAmeacada() {
        return List.of("VU", "EN", "CR").contains(statusConservacao);
    }

    public boolean isExotica() {
        return !nativa;
    }

    public String getOrigem() {
        return nativa ? "Nativa" : "Exótica";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomePopular() {
        return nomePopular;
    }

    public void setNomePopular(String nomePopular) {
        this.nomePopular = nomePopular;
    }

    public String getNomeCientifico() {
        return nomeCientifico;
    }

    public void setNomeCientifico(String nomeCientifico) {
        this.nomeCientifico = nomeCientifico;
    }

    public String getStatusConservacao() {
        return statusConservacao;
    }

    public void setStatusConservacao(String statusConservacao) {
        this.statusConservacao = statusConservacao;
    }

    public boolean isNativa() {
        return nativa;
    }

    public void setNativa(boolean nativa) {
        this.nativa = nativa;
    }

    @Override
    public String toString() {
        return String.format("#%d | %s (%s) | %s - %s | %s | %s%s", id, nomePopular, nomeCientifico,
                getCategoria(), getCaracteristica(), getOrigem(), statusConservacao,
                isAmeacada() ? " (ameaçada)" : "");
    }
}
