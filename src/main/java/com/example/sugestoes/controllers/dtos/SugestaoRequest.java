package com.example.sugestoes.controllers.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
@Schema(description = "Requisição de sugestão")
public class SugestaoRequest {
    @Schema(description = "Título da sugestão", example = "Sugestão de melhoria")
    @NotBlank
    private String titulo;
    @Schema(description = "Descrição da sugestão", example = "Sugestão para melhoria do produto ou serviço")
    @NotBlank
    private String descricao;

    public @NotBlank String getTitulo() {
        return titulo;
    }

    public void setTitulo(@NotBlank String titulo) {
        this.titulo = titulo;
    }

    public @NotBlank String getDescricao() {
        return descricao;
    }

    public void setDescricao(@NotBlank String descricao) {
        this.descricao = descricao;
    }
}
