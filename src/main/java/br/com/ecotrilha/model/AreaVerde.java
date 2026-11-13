package br.com.ecotrilha.model;

import java.util.List;

public class AreaVerde {

    public static final List<String> TIPOS = List.of("Parque", "Bosque", "Fragmento de mata");

    private Integer id;
    private String nome;
    private String tipo;
    private String cidade;
    private double areaHectares;

    public AreaVerde(Integer id, String nome, String tipo, String cidade, double areaHectares) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.cidade = cidade;
        setAreaHectares(areaHectares);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public double getAreaHectares() {
        return areaHectares;
    }

    public void setAreaHectares(double areaHectares) {
        if (areaHectares <= 0) {
            throw new IllegalArgumentException("A área em hectares deve ser maior que zero.");
        }
        this.areaHectares = areaHectares;
    }

    @Override
    public String toString() {
        return String.format("#%d | %s | %s | %s | %.2f ha", id, nome, tipo, cidade, areaHectares);
    }
}
