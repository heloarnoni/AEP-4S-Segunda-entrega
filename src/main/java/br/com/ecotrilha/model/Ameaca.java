package br.com.ecotrilha.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Ameaca {

    public static final List<String> TIPOS = List.of("Queimada", "Desmatamento", "Descarte irregular de lixo",
            "Caça ou captura", "Espécie invasora");
    public static final List<String> GRAVIDADES = List.of("Baixa", "Média", "Alta");

    private Integer id;
    private Integer areaId;
    private String tipo;
    private String gravidade;
    private LocalDate data;
    private String status;

    public Ameaca(Integer id, Integer areaId, String tipo, String gravidade, LocalDate data, String status) {
        this.id = id;
        this.areaId = areaId;
        this.tipo = tipo;
        this.gravidade = gravidade;
        setData(data);
        this.status = status;
    }

    public static Ameaca criarEspecieInvasora(Integer areaId, LocalDate data) {
        return new Ameaca(null, areaId, "Espécie invasora", "Alta", data, "Ativa");
    }

    public void resolver() {
        status = "Resolvida";
    }

    public boolean isAtiva() {
        return "Ativa".equals(status);
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getGravidade() {
        return gravidade;
    }

    public void setGravidade(String gravidade) {
        this.gravidade = gravidade;
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

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return String.format("#%d | Área %d | %s | Gravidade %s | %s | %s", id, areaId, tipo, gravidade,
                data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), status);
    }
}
