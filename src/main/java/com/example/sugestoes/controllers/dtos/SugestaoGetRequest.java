package com.example.sugestoes.controllers.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Requisição para obter sugestões")
public class SugestaoGetRequest {
    @Schema(description = "String que o título da sugestão deve conter (se nulo, string vazia)", example = "melhoria")
    private String titulo;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
