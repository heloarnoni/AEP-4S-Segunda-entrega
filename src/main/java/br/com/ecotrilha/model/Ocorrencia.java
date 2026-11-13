package br.com.ecotrilha.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Ocorrencia {

    private Integer id;
    private Integer areaId;
    private Especie especie;
    private LocalDate data;
    private int quantidade;
    private String observador;
    private String observacao;

    public Ocorrencia(Integer id, Integer areaId, Especie especie, LocalDate data, int quantidade,
                      String observador, String observacao) {
        this.id = id;
        this.areaId = areaId;
        this.especie = especie;
        setData(data);
        setQuantidade(quantidade);
        this.observador = observador;
        this.observacao = observacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        if (data.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("A data não pode estar no futuro.");
        }
        this.data = data;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade de indivíduos deve ser maior que zero.");
        }
        this.quantidade = quantidade;
    }

    public String getObservador() {
        return observador;
    }

    public void setObservador(String observador) {
        this.observador = observador;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @Override
    public String toString() {
        return String.format("#%d | Área %d | %s | %s | %d indivíduo(s) | %s%s", id, areaId,
                especie.getNomePopular(), data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), quantidade,
                observador, observacao == null ? "" : " | " + observacao);
    }
}
